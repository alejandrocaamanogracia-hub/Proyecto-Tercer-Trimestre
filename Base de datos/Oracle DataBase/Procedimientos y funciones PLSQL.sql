---PROCEDIMIENTOS---

CREATE OR REPLACE PROCEDURE pr_resumen_cliente (
    p_cliente_id IN NUMBER
)
IS
    -- Variables
    v_total_ventas NUMBER := 0;
    v_num_ventas NUMBER := 0;
    v_num_interacciones NUMBER := 0;
    v_cliente_existe NUMBER := 0;

    -- Cursor
    CURSOR c_resumen IS
        SELECT 
            INITCAP(c.nombre) AS nombre_cliente,
            v.id AS id_venta,
            v.fecha,
            v.estado AS estado_venta,
            INITCAP(u.nombre) AS nombre_usuario,
            co.marca,
            co.modelo,
            dv.cantidad,
            dv.precio_unitario,
            NVL(dv.cantidad * dv.precio_unitario, 0) AS importe_linea
        FROM clientes c
        INNER JOIN ventas v ON c.id = v.cliente_id
        INNER JOIN usuarios u ON u.id = v.usuario_id
        INNER JOIN detalle_venta dv ON v.id = dv.venta_id
        INNER JOIN coches co ON co.id = dv.coche_id
        WHERE c.id = p_cliente_id
        ORDER BY v.fecha;

BEGIN
    -- Comprobar si existe el cliente
    SELECT COUNT(*)
    INTO v_cliente_existe
    FROM clientes
    WHERE id = p_cliente_id;

    IF v_cliente_existe = 0 THEN
        RAISE_APPLICATION_ERROR(-20001, 'No existe ningun cliente con ese ID.');
    END IF;

    -- Contar interacciones del cliente
    SELECT COUNT(*)
    INTO v_num_interacciones
    FROM interacciones_cliente
    WHERE cliente_id = p_cliente_id;

    DBMS_OUTPUT.PUT_LINE('===== RESUMEN DEL CLIENTE =====');
    DBMS_OUTPUT.PUT_LINE('ID Cliente: ' || p_cliente_id);
    DBMS_OUTPUT.PUT_LINE('Numero de interacciones: ' || v_num_interacciones);
    DBMS_OUTPUT.PUT_LINE('-------------------------------');

    -- Recorrer cursor
    FOR r IN c_resumen LOOP
        v_num_ventas := v_num_ventas + 1;
        v_total_ventas := v_total_ventas + r.importe_linea;

        DBMS_OUTPUT.PUT_LINE('Cliente: ' || r.nombre_cliente);
        DBMS_OUTPUT.PUT_LINE('Venta ID: ' || r.id_venta);
        DBMS_OUTPUT.PUT_LINE('Fecha: ' || TO_CHAR(r.fecha, 'DD/MM/YYYY'));
        DBMS_OUTPUT.PUT_LINE('Estado: ' || r.estado_venta);
        DBMS_OUTPUT.PUT_LINE('Comercial: ' || r.nombre_usuario);
        DBMS_OUTPUT.PUT_LINE('Coche: ' || r.marca || ' ' || r.modelo);
        DBMS_OUTPUT.PUT_LINE('Importe: ' || r.importe_linea || ' euros');

        -- Estructura de control
        IF UPPER(r.estado_venta) = 'COMPLETADA' THEN
            DBMS_OUTPUT.PUT_LINE('Venta finalizada correctamente.');
        ELSIF UPPER(r.estado_venta) = 'PENDIENTE' THEN
            DBMS_OUTPUT.PUT_LINE('Venta pendiente de cerrar.');
        ELSE
            DBMS_OUTPUT.PUT_LINE('Venta en otro estado.');
        END IF;

        DBMS_OUTPUT.PUT_LINE('-------------------------------');
    END LOOP;

    IF v_num_ventas = 0 THEN
        DBMS_OUTPUT.PUT_LINE('Este cliente no tiene ventas registradas.');
    ELSE
        DBMS_OUTPUT.PUT_LINE('Numero de ventas encontradas: ' || v_num_ventas);
        DBMS_OUTPUT.PUT_LINE('Total acumulado: ' || ROUND(v_total_ventas, 2) || ' euros');
    END IF;

EXCEPTION
    WHEN OTHERS THEN
        DBMS_OUTPUT.PUT_LINE('Error en el procedimiento pr_resumen_cliente.');
        DBMS_OUTPUT.PUT_LINE('Codigo de error: ' || SQLCODE);
        DBMS_OUTPUT.PUT_LINE('Mensaje: ' || SQLERRM);
END;
/

BEGIN
    pr_resumen_cliente(1);
END;
/

-----FUNCIONES------

CREATE OR REPLACE FUNCTION fn_total_gastado_cliente (
    p_cliente_id IN NUMBER
)
RETURN NUMBER
IS
    -- Variables
    v_total NUMBER := 0;
    v_cliente_existe NUMBER := 0;
    v_interacciones NUMBER := 0;

    -- Cursor
    CURSOR c_ventas_cliente IS
        SELECT 
            v.id AS id_venta,
            v.estado,
            NVL(SUM(dv.cantidad * dv.precio_unitario), 0) AS importe_venta
        FROM ventas v
        INNER JOIN detalle_venta dv ON v.id = dv.venta_id
        INNER JOIN coches co ON co.id = dv.coche_id
        INNER JOIN usuarios u ON u.id = v.usuario_id
        WHERE v.cliente_id = p_cliente_id
        GROUP BY v.id, v.estado;

BEGIN
    -- Comprobar si existe el cliente
    SELECT COUNT(*)
    INTO v_cliente_existe
    FROM clientes
    WHERE id = p_cliente_id;

    IF v_cliente_existe = 0 THEN
        RETURN -1;
    END IF;

    -- Contar interacciones del cliente
    SELECT COUNT(*)
    INTO v_interacciones
    FROM interacciones_cliente
    WHERE cliente_id = p_cliente_id;

    -- Recorrer ventas del cliente
    FOR r IN c_ventas_cliente LOOP

        -- Solo sumamos ventas completadas
        IF UPPER(r.estado) = 'COMPLETADA' THEN
            v_total := v_total + r.importe_venta;
        END IF;

    END LOOP;

    RETURN ROUND(v_total, 2);

EXCEPTION
    WHEN OTHERS THEN
        RETURN -1;
END;
/
SELECT fn_total_gastado_cliente(1) AS total_gastado
FROM dual;




CREATE OR REPLACE FUNCTION total_usuario(v_id_usuario usuarios.id%type)
return Number
AS
    v_suma_dinero NUMBER;
    
BEGIN

    SELECT SUM(total) INTO v_suma_dinero
    FROM ventas
    WHERE usuario_id = v_id_usuario;
    
    RETURN v_suma_dinero;
    
END;
/

BEGIN
    DBMS_OUTPUT.PUT_LINE(total_usuario(29));
END;
/

CREATE OR REPLACE PROCEDURE coche_venta(v_id_venta ventas.id%type)

AS
    v_coche coches.modelo%type;
    
BEGIN

    SELECT c.modelo INTO v_coche
    FROM coches c
    JOIN detalle_venta d ON d.coche_id = c.id
    JOIN ventas v ON d.venta_id = v.id
    WHERE v.id = v_id_venta;
    
    DBMS_OUTPUT.PUT_LINE('En la venta con el id ' || v_id_venta || ' se ha vendido el coche ' || v_coche);
    
END;
/

BEGIN
    coche_venta(41);
END;
/
