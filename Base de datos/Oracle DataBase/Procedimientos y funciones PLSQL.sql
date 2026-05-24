SET SERVEROUTPUT ON;

-- =========================
-- PROCEDIMIENTO 1
-- =========================

CREATE OR REPLACE PROCEDURE pr_resumen_cliente (
    p_cliente_id IN NUMBER
)
IS
    v_total_ventas NUMBER := 0;
    v_num_ventas NUMBER := 0;
    v_num_interacciones NUMBER := 0;
    v_cliente_existe NUMBER := 0;

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
    SELECT COUNT(*)
    INTO v_cliente_existe
    FROM clientes
    WHERE id = p_cliente_id;

    IF v_cliente_existe = 0 THEN
        RAISE_APPLICATION_ERROR(-20001, 'No existe ningun cliente con ese ID.');
    END IF;

    SELECT COUNT(*)
    INTO v_num_interacciones
    FROM interacciones_cliente
    WHERE cliente_id = p_cliente_id;

    DBMS_OUTPUT.PUT_LINE('===== RESUMEN DEL CLIENTE =====');
    DBMS_OUTPUT.PUT_LINE('ID Cliente: ' || p_cliente_id);
    DBMS_OUTPUT.PUT_LINE('Numero de interacciones: ' || v_num_interacciones);
    DBMS_OUTPUT.PUT_LINE('-------------------------------');

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

DECLARE
    v_cliente_id clientes.id%TYPE;
BEGIN
    SELECT cliente_id
    INTO v_cliente_id
    FROM ventas
    WHERE ROWNUM = 1;

    pr_resumen_cliente(v_cliente_id);
END;
/

SELECT 
    c.id AS cliente_id,
    c.nombre AS cliente,
    COUNT(DISTINCT v.id) AS numero_ventas,
    COUNT(DISTINCT i.id) AS numero_interacciones,
    NVL(SUM(dv.cantidad * dv.precio_unitario), 0) AS total_ventas
FROM clientes c
LEFT JOIN ventas v ON c.id = v.cliente_id
LEFT JOIN detalle_venta dv ON v.id = dv.venta_id
LEFT JOIN interacciones_cliente i ON c.id = i.cliente_id
WHERE c.id = (
    SELECT cliente_id
    FROM ventas
    WHERE ROWNUM = 1
)
GROUP BY c.id, c.nombre;


-- =========================
-- PROCEDIMIENTO 2
-- =========================

CREATE OR REPLACE PROCEDURE coche_venta(v_id_venta ventas.id%TYPE)
AS
    v_coche         coches.modelo%TYPE;
    v_marca         coches.marca%TYPE;
    v_precio        detalle_venta.precio_unitario%TYPE;
    v_cantidad      detalle_venta.cantidad%TYPE;
    v_subtotal      NUMBER;
    v_total_lineas  NUMBER := 0;
    v_existe        NUMBER := 0;

    e_venta_no_existe EXCEPTION;

    CURSOR c_detalle IS
        SELECT c.marca,
               c.modelo,
               d.cantidad,
               d.precio_unitario,
               ROUND(d.cantidad * d.precio_unitario, 2) AS subtotal
        FROM coches c
        JOIN detalle_venta d ON d.coche_id = c.id
        JOIN ventas v ON d.venta_id = v.id
        WHERE v.id = v_id_venta
        ORDER BY c.modelo;

BEGIN
    SELECT COUNT(*)
    INTO v_existe
    FROM ventas
    WHERE id = v_id_venta;

    IF v_existe = 0 THEN
        RAISE e_venta_no_existe;
    END IF;

    DBMS_OUTPUT.PUT_LINE('============================================');
    DBMS_OUTPUT.PUT_LINE('  DETALLE DE VENTA ID: ' || v_id_venta);
    DBMS_OUTPUT.PUT_LINE('============================================');

    OPEN c_detalle;
    LOOP
        FETCH c_detalle INTO v_marca, v_coche, v_cantidad, v_precio, v_subtotal;
        EXIT WHEN c_detalle%NOTFOUND;

        v_total_lineas := v_total_lineas + v_subtotal;

        IF v_precio >= 50000 THEN
            v_coche := UPPER(v_coche) || ' [PREMIUM]';
        ELSIF v_precio >= 20000 THEN
            v_coche := v_coche || ' [ESTANDAR]';
        ELSE
            v_coche := v_coche || ' [BASICO]';
        END IF;

        DBMS_OUTPUT.PUT_LINE('Marca    : ' || INITCAP(v_marca));
        DBMS_OUTPUT.PUT_LINE('Modelo   : ' || v_coche);
        DBMS_OUTPUT.PUT_LINE(
            'Cantidad : ' || v_cantidad ||
            '  |  Precio unit.: ' || v_precio || ' euros' ||
            '  |  Subtotal: ' || v_subtotal || ' euros'
        );
        DBMS_OUTPUT.PUT_LINE('--------------------------------------------');

    END LOOP;
    CLOSE c_detalle;

    IF v_total_lineas = 0 THEN
        DBMS_OUTPUT.PUT_LINE('La venta existe pero no tiene lineas de detalle.');
    ELSE
        DBMS_OUTPUT.PUT_LINE('TOTAL VENTA: ' || v_total_lineas || ' euros');
    END IF;

EXCEPTION
    WHEN e_venta_no_existe THEN
        DBMS_OUTPUT.PUT_LINE('ERROR: No existe ninguna venta con ID ' || v_id_venta);
    WHEN OTHERS THEN
        DBMS_OUTPUT.PUT_LINE('Error inesperado: ' || SQLERRM);
        IF c_detalle%ISOPEN THEN
            CLOSE c_detalle;
        END IF;
END;
/

DECLARE
    v_venta_id ventas.id%TYPE;
BEGIN
    SELECT venta_id
    INTO v_venta_id
    FROM detalle_venta
    WHERE ROWNUM = 1;

    coche_venta(v_venta_id);
END;
/

SELECT 
    v.id AS venta_id,
    c.marca,
    c.modelo,
    d.cantidad,
    d.precio_unitario,
    ROUND(d.cantidad * d.precio_unitario, 2) AS subtotal
FROM ventas v
JOIN detalle_venta d ON v.id = d.venta_id
JOIN coches c ON d.coche_id = c.id
WHERE v.id = (
    SELECT venta_id
    FROM detalle_venta
    WHERE ROWNUM = 1
)
ORDER BY c.modelo;


-- =========================
-- FUNCION 1
-- =========================

CREATE OR REPLACE FUNCTION fn_total_gastado_cliente (
    p_cliente_id IN NUMBER
)
RETURN NUMBER
IS
    v_total NUMBER := 0;
    v_cliente_existe NUMBER := 0;

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
    SELECT COUNT(*)
    INTO v_cliente_existe
    FROM clientes
    WHERE id = p_cliente_id;

    IF v_cliente_existe = 0 THEN
        RETURN -1;
    END IF;

    FOR r IN c_ventas_cliente LOOP
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

SELECT fn_total_gastado_cliente(cliente_id) AS total_gastado
FROM ventas
WHERE ROWNUM = 1;

SELECT 
    v.cliente_id,
    v.id AS venta_id,
    v.estado,
    NVL(SUM(dv.cantidad * dv.precio_unitario), 0) AS importe_venta
FROM ventas v
JOIN detalle_venta dv ON v.id = dv.venta_id
WHERE v.cliente_id = (
    SELECT cliente_id
    FROM ventas
    WHERE ROWNUM = 1
)
GROUP BY v.cliente_id, v.id, v.estado
ORDER BY v.id;


-- =========================
-- FUNCION 2
-- =========================

CREATE OR REPLACE FUNCTION total_usuario(v_id_usuario usuarios.id%TYPE)
RETURN NUMBER
AS
    v_suma_dinero   NUMBER := 0;
    v_num_ventas    NUMBER := 0;
    v_promedio      NUMBER := 0;
    v_max_venta     NUMBER := 0;
    v_fecha_inicio  DATE;
    v_fecha_fin     DATE;

    CURSOR c_ventas IS
        SELECT id, total, fecha
        FROM ventas
        WHERE usuario_id = v_id_usuario
        ORDER BY fecha;

    v_venta c_ventas%ROWTYPE;

BEGIN
    BEGIN
        SELECT MIN(fecha), MAX(fecha)
        INTO v_fecha_inicio, v_fecha_fin
        FROM ventas
        WHERE usuario_id = v_id_usuario;

        IF v_fecha_inicio IS NULL THEN
            RAISE NO_DATA_FOUND;
        END IF;

    EXCEPTION
        WHEN NO_DATA_FOUND THEN
            DBMS_OUTPUT.PUT_LINE('El usuario con ID ' || v_id_usuario || ' no tiene ventas registradas.');
            RETURN 0;
    END;

    OPEN c_ventas;
    LOOP
        FETCH c_ventas INTO v_venta;
        EXIT WHEN c_ventas%NOTFOUND;

        v_num_ventas := v_num_ventas + 1;
        v_suma_dinero := v_suma_dinero + v_venta.total;

        IF v_venta.total > v_max_venta THEN
            v_max_venta := v_venta.total;
        END IF;

    END LOOP;
    CLOSE c_ventas;

    v_promedio := ROUND(v_suma_dinero / NULLIF(v_num_ventas, 0), 2);

    DBMS_OUTPUT.PUT_LINE('=== RESUMEN USUARIO ID: ' || v_id_usuario || ' ===');
    DBMS_OUTPUT.PUT_LINE('Numero de ventas   : ' || v_num_ventas);
    DBMS_OUTPUT.PUT_LINE('Promedio por venta : ' || TO_CHAR(v_promedio, '99,999.99') || ' euros');
    DBMS_OUTPUT.PUT_LINE('Venta mas alta     : ' || TO_CHAR(v_max_venta, '99,999.99') || ' euros');
    DBMS_OUTPUT.PUT_LINE('Primera venta      : ' || TO_CHAR(v_fecha_inicio, 'DD/MM/YYYY'));
    DBMS_OUTPUT.PUT_LINE('Ultima venta       : ' || TO_CHAR(v_fecha_fin, 'DD/MM/YYYY'));

    RETURN v_suma_dinero;

EXCEPTION
    WHEN OTHERS THEN
        DBMS_OUTPUT.PUT_LINE('Error inesperado: ' || SQLERRM);
        RETURN -1;
END;
/

DECLARE
    v_usuario_id usuarios.id%TYPE;
BEGIN
    SELECT usuario_id
    INTO v_usuario_id
    FROM ventas
    WHERE ROWNUM = 1;

    DBMS_OUTPUT.PUT_LINE('Total acumulado: ' || total_usuario(v_usuario_id) || ' euros');
END;
/

SELECT 
    usuario_id,
    COUNT(*) AS numero_ventas,
    SUM(total) AS total_acumulado,
    ROUND(AVG(total), 2) AS promedio_venta,
    MAX(total) AS venta_mas_alta,
    MIN(fecha) AS primera_venta,
    MAX(fecha) AS ultima_venta
FROM ventas
WHERE usuario_id = (
    SELECT usuario_id
    FROM ventas
    WHERE ROWNUM = 1
)
GROUP BY usuario_id;