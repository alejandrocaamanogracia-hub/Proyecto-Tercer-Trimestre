SET SERVEROUTPUT ON;

CREATE OR REPLACE PROCEDURE pr_listar_clientes
IS
    v_contador NUMBER := 0;

    CURSOR c_clientes IS
        SELECT id, INITCAP(nombre) AS nombre, LOWER(email) AS email, NVL(telefono, 'Sin telefono') AS telefono, NVL(direccion, 'Sin direccion') AS direccion
        FROM clientes
        ORDER BY id;
BEGIN
    FOR r IN c_clientes LOOP
        v_contador := v_contador + 1;

        IF r.email IS NOT NULL THEN
            DBMS_OUTPUT.PUT_LINE('ID: ' || r.id || ' | Cliente: ' || r.nombre || ' | Email: ' || r.email || ' | Telefono: ' || r.telefono || ' | Direccion: ' || r.direccion);
        ELSE
            DBMS_OUTPUT.PUT_LINE('Cliente sin email registrado.');
        END IF;
    END LOOP;

    IF v_contador = 0 THEN
        DBMS_OUTPUT.PUT_LINE('No hay clientes registrados.');
    ELSE
        DBMS_OUTPUT.PUT_LINE('Total clientes listados: ' || v_contador);
    END IF;
EXCEPTION
    WHEN OTHERS THEN
        DBMS_OUTPUT.PUT_LINE('Error en pr_listar_clientes: ' || SQLERRM);
END;
/

CREATE OR REPLACE PROCEDURE pr_resumen_cliente (
    p_cliente_id IN clientes.id%TYPE
)
IS
    v_existe NUMBER := 0;
    v_total_ventas NUMBER := 0;
    v_total_interacciones NUMBER := 0;
    v_total_importe NUMBER := 0;

    CURSOR c_cliente IS
        SELECT INITCAP(c.nombre) AS nombre, LOWER(c.email) AS email, NVL(c.telefono, 'Sin telefono') AS telefono, NVL(c.direccion, 'Sin direccion') AS direccion
        FROM clientes c
        WHERE c.id = p_cliente_id;

    CURSOR c_ventas IS
        SELECT id, estado, fecha, total
        FROM ventas
        WHERE cliente_id = p_cliente_id
        ORDER BY fecha;
BEGIN
    SELECT COUNT(*)
    INTO v_existe
    FROM clientes
    WHERE id = p_cliente_id;

    IF v_existe = 0 THEN
        DBMS_OUTPUT.PUT_LINE('No existe ningun cliente con ese ID.');
        RETURN;
    END IF;

    FOR c IN c_cliente LOOP
        DBMS_OUTPUT.PUT_LINE('Cliente: ' || c.nombre);
        DBMS_OUTPUT.PUT_LINE('Email: ' || c.email);
        DBMS_OUTPUT.PUT_LINE('Telefono: ' || c.telefono);
        DBMS_OUTPUT.PUT_LINE('Direccion: ' || c.direccion);
    END LOOP;

    FOR v IN c_ventas LOOP
        v_total_ventas := v_total_ventas + 1;
        v_total_importe := v_total_importe + NVL(v.total, 0);

        IF UPPER(v.estado) = 'COMPLETADA' THEN
            DBMS_OUTPUT.PUT_LINE('Venta completada ID ' || v.id || ' | Fecha: ' || TO_CHAR(v.fecha, 'DD/MM/YYYY') || ' | Total: ' || v.total);
        ELSE
            DBMS_OUTPUT.PUT_LINE('Venta no completada ID ' || v.id || ' | Estado: ' || v.estado || ' | Total: ' || v.total);
        END IF;
    END LOOP;

    SELECT COUNT(*)
    INTO v_total_interacciones
    FROM interacciones_cliente
    WHERE cliente_id = p_cliente_id;

    DBMS_OUTPUT.PUT_LINE('Numero de ventas: ' || v_total_ventas);
    DBMS_OUTPUT.PUT_LINE('Numero de interacciones: ' || v_total_interacciones);
    DBMS_OUTPUT.PUT_LINE('Importe total en ventas: ' || ROUND(v_total_importe, 2) || ' euros');
EXCEPTION
    WHEN OTHERS THEN
        DBMS_OUTPUT.PUT_LINE('Error en pr_resumen_cliente: ' || SQLERRM);
END;
/

CREATE OR REPLACE FUNCTION fn_total_ventas_cliente (
    p_cliente_id IN clientes.id%TYPE
)
RETURN NUMBER
IS
    v_total NUMBER := 0;
    v_existe NUMBER := 0;

    CURSOR c_ventas IS
        SELECT NVL(total, 0) AS total, estado
        FROM ventas
        WHERE cliente_id = p_cliente_id;
BEGIN
    SELECT COUNT(*)
    INTO v_existe
    FROM clientes
    WHERE id = p_cliente_id;

    IF v_existe = 0 THEN
        RETURN -1;
    END IF;

    FOR r IN c_ventas LOOP
        IF UPPER(r.estado) = 'COMPLETADA' THEN
            v_total := v_total + r.total;
        END IF;
    END LOOP;

    RETURN ROUND(v_total, 2);
EXCEPTION
    WHEN OTHERS THEN
        RETURN -1;
END;
/

CREATE OR REPLACE FUNCTION fn_num_interacciones_cliente (
    p_cliente_id IN clientes.id%TYPE
)
RETURN NUMBER
IS
    v_total NUMBER := 0;
    v_existe NUMBER := 0;

    CURSOR c_interacciones IS
        SELECT id, NVL(resultado, 'Sin resultado') AS resultado
        FROM interacciones_cliente
        WHERE cliente_id = p_cliente_id;
BEGIN
    SELECT COUNT(*)
    INTO v_existe
    FROM clientes
    WHERE id = p_cliente_id;

    IF v_existe = 0 THEN
        RETURN -1;
    END IF;

    FOR r IN c_interacciones LOOP
        IF LENGTH(r.resultado) > 0 THEN
            v_total := v_total + 1;
        END IF;
    END LOOP;

    RETURN v_total;
EXCEPTION
    WHEN OTHERS THEN
        RETURN -1;
END;
/

CREATE OR REPLACE PROCEDURE pr_listar_usuarios
IS
    v_contador NUMBER := 0;

    CURSOR c_usuarios IS
        SELECT id, INITCAP(nombre) AS nombre, LOWER(email) AS email, INITCAP(rol) AS rol
        FROM usuarios
        ORDER BY id;
BEGIN
    FOR r IN c_usuarios LOOP
        v_contador := v_contador + 1;

        IF UPPER(r.rol) = 'ADMINISTRADOR' THEN
            DBMS_OUTPUT.PUT_LINE('ID: ' || r.id || ' | Usuario administrador: ' || r.nombre || ' | Email: ' || r.email);
        ELSE
            DBMS_OUTPUT.PUT_LINE('ID: ' || r.id || ' | Usuario: ' || r.nombre || ' | Email: ' || r.email || ' | Rol: ' || r.rol);
        END IF;
    END LOOP;

    IF v_contador = 0 THEN
        DBMS_OUTPUT.PUT_LINE('No hay usuarios registrados.');
    ELSE
        DBMS_OUTPUT.PUT_LINE('Total usuarios listados: ' || v_contador);
    END IF;
EXCEPTION
    WHEN OTHERS THEN
        DBMS_OUTPUT.PUT_LINE('Error en pr_listar_usuarios: ' || SQLERRM);
END;
/

CREATE OR REPLACE PROCEDURE pr_resumen_usuario (
    p_usuario_id IN usuarios.id%TYPE
)
IS
    v_existe NUMBER := 0;
    v_num_ventas NUMBER := 0;
    v_num_interacciones NUMBER := 0;
    v_total_ventas NUMBER := 0;

    CURSOR c_usuario IS
        SELECT INITCAP(nombre) AS nombre, LOWER(email) AS email, INITCAP(rol) AS rol
        FROM usuarios
        WHERE id = p_usuario_id;

    CURSOR c_ventas IS
        SELECT id, fecha, estado, NVL(total, 0) AS total
        FROM ventas
        WHERE usuario_id = p_usuario_id
        ORDER BY fecha;
BEGIN
    SELECT COUNT(*)
    INTO v_existe
    FROM usuarios
    WHERE id = p_usuario_id;

    IF v_existe = 0 THEN
        DBMS_OUTPUT.PUT_LINE('No existe ningun usuario con ese ID.');
        RETURN;
    END IF;

    FOR u IN c_usuario LOOP
        DBMS_OUTPUT.PUT_LINE('Usuario: ' || u.nombre);
        DBMS_OUTPUT.PUT_LINE('Email: ' || u.email);
        DBMS_OUTPUT.PUT_LINE('Rol: ' || u.rol);
    END LOOP;

    FOR v IN c_ventas LOOP
        v_num_ventas := v_num_ventas + 1;
        v_total_ventas := v_total_ventas + v.total;

        IF UPPER(v.estado) = 'COMPLETADA' THEN
            DBMS_OUTPUT.PUT_LINE('Venta completada ID: ' || v.id || ' | Fecha: ' || TO_CHAR(v.fecha, 'DD/MM/YYYY') || ' | Total: ' || v.total);
        ELSE
            DBMS_OUTPUT.PUT_LINE('Venta ID: ' || v.id || ' | Estado: ' || v.estado || ' | Total: ' || v.total);
        END IF;
    END LOOP;

    SELECT COUNT(*)
    INTO v_num_interacciones
    FROM interacciones_cliente
    WHERE usuario_id = p_usuario_id;

    DBMS_OUTPUT.PUT_LINE('Ventas gestionadas: ' || v_num_ventas);
    DBMS_OUTPUT.PUT_LINE('Interacciones registradas: ' || v_num_interacciones);
    DBMS_OUTPUT.PUT_LINE('Total gestionado: ' || ROUND(v_total_ventas, 2) || ' euros');
EXCEPTION
    WHEN OTHERS THEN
        DBMS_OUTPUT.PUT_LINE('Error en pr_resumen_usuario: ' || SQLERRM);
END;
/

CREATE OR REPLACE FUNCTION fn_total_ventas_usuario (
    p_usuario_id IN usuarios.id%TYPE
)
RETURN NUMBER
IS
    v_total NUMBER := 0;
    v_existe NUMBER := 0;

    CURSOR c_ventas IS
        SELECT NVL(total, 0) AS total
        FROM ventas
        WHERE usuario_id = p_usuario_id;
BEGIN
    SELECT COUNT(*)
    INTO v_existe
    FROM usuarios
    WHERE id = p_usuario_id;

    IF v_existe = 0 THEN
        RETURN -1;
    END IF;

    FOR r IN c_ventas LOOP
        IF r.total >= 0 THEN
            v_total := v_total + r.total;
        END IF;
    END LOOP;

    RETURN ROUND(v_total, 2);
EXCEPTION
    WHEN OTHERS THEN
        RETURN -1;
END;
/

CREATE OR REPLACE FUNCTION fn_num_interacciones_usuario (
    p_usuario_id IN usuarios.id%TYPE
)
RETURN NUMBER
IS
    v_total NUMBER := 0;
    v_existe NUMBER := 0;

    CURSOR c_interacciones IS
        SELECT id, tipo
        FROM interacciones_cliente
        WHERE usuario_id = p_usuario_id;
BEGIN
    SELECT COUNT(*)
    INTO v_existe
    FROM usuarios
    WHERE id = p_usuario_id;

    IF v_existe = 0 THEN
        RETURN -1;
    END IF;

    FOR r IN c_interacciones LOOP
        IF INITCAP(r.tipo) IS NOT NULL THEN
            v_total := v_total + 1;
        END IF;
    END LOOP;

    RETURN v_total;
EXCEPTION
    WHEN OTHERS THEN
        RETURN -1;
END;
/

CREATE OR REPLACE PROCEDURE pr_listar_coches
IS
    v_contador NUMBER := 0;

    CURSOR c_coches IS
        SELECT id, INITCAP(marca) AS marca, INITCAP(modelo) AS modelo, NVL(version, 'Sin version') AS version, NVL(matricula, 'Sin matricula') AS matricula, precio, INITCAP(estado) AS estado
        FROM coches
        ORDER BY id;
BEGIN
    FOR r IN c_coches LOOP
        v_contador := v_contador + 1;

        IF UPPER(r.estado) = 'VENDIDO' THEN
            DBMS_OUTPUT.PUT_LINE('ID: ' || r.id || ' | ' || r.marca || ' ' || r.modelo || ' | Vendido | Precio: ' || r.precio || ' euros');
        ELSE
            DBMS_OUTPUT.PUT_LINE('ID: ' || r.id || ' | ' || r.marca || ' ' || r.modelo || ' | Version: ' || r.version || ' | Matricula: ' || r.matricula || ' | Estado: ' || r.estado);
        END IF;
    END LOOP;

    IF v_contador = 0 THEN
        DBMS_OUTPUT.PUT_LINE('No hay coches registrados.');
    ELSE
        DBMS_OUTPUT.PUT_LINE('Total coches listados: ' || v_contador);
    END IF;
EXCEPTION
    WHEN OTHERS THEN
        DBMS_OUTPUT.PUT_LINE('Error en pr_listar_coches: ' || SQLERRM);
END;
/

CREATE OR REPLACE PROCEDURE pr_resumen_coche (
    p_coche_id IN coches.id%TYPE
)
IS
    v_existe NUMBER := 0;
    v_aparece_detalle NUMBER := 0;

    CURSOR c_coche IS
        SELECT INITCAP(marca) AS marca, INITCAP(modelo) AS modelo, NVL(version, 'Sin version') AS version, NVL(matricula, 'Sin matricula') AS matricula, anio, kilometros, combustible, cambio, color, precio, estado
        FROM coches
        WHERE id = p_coche_id;
BEGIN
    SELECT COUNT(*)
    INTO v_existe
    FROM coches
    WHERE id = p_coche_id;

    IF v_existe = 0 THEN
        DBMS_OUTPUT.PUT_LINE('No existe ningun coche con ese ID.');
        RETURN;
    END IF;

    FOR r IN c_coche LOOP
        DBMS_OUTPUT.PUT_LINE('Coche: ' || r.marca || ' ' || r.modelo);
        DBMS_OUTPUT.PUT_LINE('Version: ' || r.version);
        DBMS_OUTPUT.PUT_LINE('Matricula: ' || r.matricula);
        DBMS_OUTPUT.PUT_LINE('Año: ' || r.anio);
        DBMS_OUTPUT.PUT_LINE('Kilometros: ' || NVL(r.kilometros, 0));
        DBMS_OUTPUT.PUT_LINE('Precio: ' || ROUND(r.precio, 2) || ' euros');
        DBMS_OUTPUT.PUT_LINE('Estado: ' || r.estado);

        IF UPPER(r.estado) = 'VENDIDO' THEN
            DBMS_OUTPUT.PUT_LINE('El coche esta vendido.');
        ELSE
            DBMS_OUTPUT.PUT_LINE('El coche no esta vendido.');
        END IF;
    END LOOP;

    SELECT COUNT(*)
    INTO v_aparece_detalle
    FROM detalle_venta
    WHERE coche_id = p_coche_id;

    DBMS_OUTPUT.PUT_LINE('Apariciones en detalle_venta: ' || v_aparece_detalle);
EXCEPTION
    WHEN OTHERS THEN
        DBMS_OUTPUT.PUT_LINE('Error en pr_resumen_coche: ' || SQLERRM);
END;
/

CREATE OR REPLACE FUNCTION fn_coche_vendido (
    p_coche_id IN coches.id%TYPE
)
RETURN NUMBER
IS
    v_existe NUMBER := 0;
    v_vendido NUMBER := 0;

    CURSOR c_detalle IS
        SELECT id
        FROM detalle_venta
        WHERE coche_id = p_coche_id;
BEGIN
    SELECT COUNT(*)
    INTO v_existe
    FROM coches
    WHERE id = p_coche_id;

    IF v_existe = 0 THEN
        RETURN -1;
    END IF;

    FOR r IN c_detalle LOOP
        IF r.id IS NOT NULL THEN
            v_vendido := 1;
        END IF;
    END LOOP;

    RETURN v_vendido;
EXCEPTION
    WHEN OTHERS THEN
        RETURN -1;
END;
/

CREATE OR REPLACE FUNCTION fn_antiguedad_coche (
    p_coche_id IN coches.id%TYPE
)
RETURN NUMBER
IS
    v_antiguedad NUMBER := 0;
    v_existe NUMBER := 0;

    CURSOR c_coche IS
        SELECT anio
        FROM coches
        WHERE id = p_coche_id;
BEGIN
    SELECT COUNT(*)
    INTO v_existe
    FROM coches
    WHERE id = p_coche_id;

    IF v_existe = 0 THEN
        RETURN -1;
    END IF;

    FOR r IN c_coche LOOP
        IF r.anio IS NOT NULL THEN
            v_antiguedad := EXTRACT(YEAR FROM SYSDATE) - r.anio;
        ELSE
            v_antiguedad := 0;
        END IF;
    END LOOP;

    RETURN v_antiguedad;
EXCEPTION
    WHEN OTHERS THEN
        RETURN -1;
END;
/

CREATE OR REPLACE PROCEDURE pr_listar_ventas
IS
    v_contador NUMBER := 0;

    CURSOR c_ventas IS
        SELECT v.id, INITCAP(c.nombre) AS cliente, INITCAP(u.nombre) AS usuario, v.fecha, INITCAP(v.estado) AS estado, NVL(v.total, 0) AS total
        FROM ventas v
        JOIN clientes c ON v.cliente_id = c.id
        JOIN usuarios u ON v.usuario_id = u.id
        ORDER BY v.id;
BEGIN
    FOR r IN c_ventas LOOP
        v_contador := v_contador + 1;

        IF UPPER(r.estado) = 'COMPLETADA' THEN
            DBMS_OUTPUT.PUT_LINE('ID: ' || r.id || ' | Cliente: ' || r.cliente || ' | Usuario: ' || r.usuario || ' | Fecha: ' || TO_CHAR(r.fecha, 'DD/MM/YYYY') || ' | Completada | Total: ' || r.total);
        ELSE
            DBMS_OUTPUT.PUT_LINE('ID: ' || r.id || ' | Cliente: ' || r.cliente || ' | Usuario: ' || r.usuario || ' | Estado: ' || r.estado || ' | Total: ' || r.total);
        END IF;
    END LOOP;

    IF v_contador = 0 THEN
        DBMS_OUTPUT.PUT_LINE('No hay ventas registradas.');
    ELSE
        DBMS_OUTPUT.PUT_LINE('Total ventas listadas: ' || v_contador);
    END IF;
EXCEPTION
    WHEN OTHERS THEN
        DBMS_OUTPUT.PUT_LINE('Error en pr_listar_ventas: ' || SQLERRM);
END;
/

CREATE OR REPLACE PROCEDURE pr_resumen_venta (
    p_venta_id IN ventas.id%TYPE
)
IS
    v_existe NUMBER := 0;
    v_total_calculado NUMBER := 0;

    CURSOR c_venta IS
        SELECT v.id, INITCAP(c.nombre) AS cliente, INITCAP(u.nombre) AS usuario, v.fecha, v.estado, v.total
        FROM ventas v
        JOIN clientes c ON v.cliente_id = c.id
        JOIN usuarios u ON v.usuario_id = u.id
        WHERE v.id = p_venta_id;

    CURSOR c_detalle IS
        SELECT co.marca, co.modelo, dv.cantidad, dv.precio_unitario, ROUND(dv.cantidad * dv.precio_unitario, 2) AS subtotal
        FROM detalle_venta dv
        JOIN coches co ON dv.coche_id = co.id
        WHERE dv.venta_id = p_venta_id;
BEGIN
    SELECT COUNT(*)
    INTO v_existe
    FROM ventas
    WHERE id = p_venta_id;

    IF v_existe = 0 THEN
        DBMS_OUTPUT.PUT_LINE('No existe ninguna venta con ese ID.');
        RETURN;
    END IF;

    FOR v IN c_venta LOOP
        DBMS_OUTPUT.PUT_LINE('Venta ID: ' || v.id);
        DBMS_OUTPUT.PUT_LINE('Cliente: ' || v.cliente);
        DBMS_OUTPUT.PUT_LINE('Usuario: ' || v.usuario);
        DBMS_OUTPUT.PUT_LINE('Fecha: ' || TO_CHAR(v.fecha, 'DD/MM/YYYY'));
        DBMS_OUTPUT.PUT_LINE('Estado: ' || v.estado);
        DBMS_OUTPUT.PUT_LINE('Total guardado: ' || v.total);
    END LOOP;

    FOR d IN c_detalle LOOP
        v_total_calculado := v_total_calculado + d.subtotal;

        IF d.subtotal > 0 THEN
            DBMS_OUTPUT.PUT_LINE('Coche: ' || d.marca || ' ' || d.modelo || ' | Subtotal: ' || d.subtotal);
        END IF;
    END LOOP;

    DBMS_OUTPUT.PUT_LINE('Total calculado: ' || ROUND(v_total_calculado, 2) || ' euros');
EXCEPTION
    WHEN OTHERS THEN
        DBMS_OUTPUT.PUT_LINE('Error en pr_resumen_venta: ' || SQLERRM);
END;
/

CREATE OR REPLACE FUNCTION fn_total_venta_calculado (
    p_venta_id IN ventas.id%TYPE
)
RETURN NUMBER
IS
    v_existe NUMBER := 0;
    v_total NUMBER := 0;

    CURSOR c_detalle IS
        SELECT cantidad, precio_unitario
        FROM detalle_venta
        WHERE venta_id = p_venta_id;
BEGIN
    SELECT COUNT(*)
    INTO v_existe
    FROM ventas
    WHERE id = p_venta_id;

    IF v_existe = 0 THEN
        RETURN -1;
    END IF;

    FOR r IN c_detalle LOOP
        IF r.cantidad > 0 THEN
            v_total := v_total + NVL(r.cantidad * r.precio_unitario, 0);
        END IF;
    END LOOP;

    RETURN ROUND(v_total, 2);
EXCEPTION
    WHEN OTHERS THEN
        RETURN -1;
END;
/

CREATE OR REPLACE FUNCTION fn_num_lineas_venta (
    p_venta_id IN ventas.id%TYPE
)
RETURN NUMBER
IS
    v_existe NUMBER := 0;
    v_lineas NUMBER := 0;

    CURSOR c_detalle IS
        SELECT id
        FROM detalle_venta
        WHERE venta_id = p_venta_id;
BEGIN
    SELECT COUNT(*)
    INTO v_existe
    FROM ventas
    WHERE id = p_venta_id;

    IF v_existe = 0 THEN
        RETURN -1;
    END IF;

    FOR r IN c_detalle LOOP
        IF r.id IS NOT NULL THEN
            v_lineas := v_lineas + 1;
        END IF;
    END LOOP;

    RETURN v_lineas;
EXCEPTION
    WHEN OTHERS THEN
        RETURN -1;
END;
/

CREATE OR REPLACE PROCEDURE pr_listar_detalle_venta
IS
    v_contador NUMBER := 0;

    CURSOR c_detalles IS
        SELECT dv.id, dv.venta_id, INITCAP(c.marca) AS marca, INITCAP(c.modelo) AS modelo, dv.cantidad, dv.precio_unitario, ROUND(dv.cantidad * dv.precio_unitario, 2) AS subtotal
        FROM detalle_venta dv
        JOIN coches c ON dv.coche_id = c.id
        ORDER BY dv.id;
BEGIN
    FOR r IN c_detalles LOOP
        v_contador := v_contador + 1;

        IF r.subtotal >= 20000 THEN
            DBMS_OUTPUT.PUT_LINE('ID: ' || r.id || ' | Venta: ' || r.venta_id || ' | Coche: ' || r.marca || ' ' || r.modelo || ' | Subtotal alto: ' || r.subtotal);
        ELSE
            DBMS_OUTPUT.PUT_LINE('ID: ' || r.id || ' | Venta: ' || r.venta_id || ' | Coche: ' || r.marca || ' ' || r.modelo || ' | Subtotal: ' || r.subtotal);
        END IF;
    END LOOP;

    IF v_contador = 0 THEN
        DBMS_OUTPUT.PUT_LINE('No hay detalles de venta registrados.');
    ELSE
        DBMS_OUTPUT.PUT_LINE('Total detalles listados: ' || v_contador);
    END IF;
EXCEPTION
    WHEN OTHERS THEN
        DBMS_OUTPUT.PUT_LINE('Error en pr_listar_detalle_venta: ' || SQLERRM);
END;
/

CREATE OR REPLACE PROCEDURE pr_resumen_detalle_venta (
    p_detalle_id IN detalle_venta.id%TYPE
)
IS
    v_existe NUMBER := 0;

    CURSOR c_detalle IS
        SELECT dv.id, dv.venta_id, dv.coche_id, INITCAP(c.marca) AS marca, INITCAP(c.modelo) AS modelo, dv.cantidad, dv.precio_unitario, ROUND(dv.cantidad * dv.precio_unitario, 2) AS subtotal
        FROM detalle_venta dv
        JOIN coches c ON dv.coche_id = c.id
        WHERE dv.id = p_detalle_id;
BEGIN
    SELECT COUNT(*)
    INTO v_existe
    FROM detalle_venta
    WHERE id = p_detalle_id;

    IF v_existe = 0 THEN
        DBMS_OUTPUT.PUT_LINE('No existe ningun detalle de venta con ese ID.');
        RETURN;
    END IF;

    FOR r IN c_detalle LOOP
        DBMS_OUTPUT.PUT_LINE('Detalle ID: ' || r.id);
        DBMS_OUTPUT.PUT_LINE('Venta ID: ' || r.venta_id);
        DBMS_OUTPUT.PUT_LINE('Coche ID: ' || r.coche_id);
        DBMS_OUTPUT.PUT_LINE('Coche: ' || r.marca || ' ' || r.modelo);
        DBMS_OUTPUT.PUT_LINE('Cantidad: ' || r.cantidad);
        DBMS_OUTPUT.PUT_LINE('Precio unitario: ' || r.precio_unitario);
        DBMS_OUTPUT.PUT_LINE('Subtotal: ' || r.subtotal);

        IF r.subtotal > 30000 THEN
            DBMS_OUTPUT.PUT_LINE('Linea de venta de importe alto.');
        ELSE
            DBMS_OUTPUT.PUT_LINE('Linea de venta de importe normal.');
        END IF;
    END LOOP;
EXCEPTION
    WHEN OTHERS THEN
        DBMS_OUTPUT.PUT_LINE('Error en pr_resumen_detalle_venta: ' || SQLERRM);
END;
/

CREATE OR REPLACE FUNCTION fn_importe_detalle_venta (
    p_detalle_id IN detalle_venta.id%TYPE
)
RETURN NUMBER
IS
    v_existe NUMBER := 0;
    v_importe NUMBER := 0;

    CURSOR c_detalle IS
        SELECT cantidad, precio_unitario
        FROM detalle_venta
        WHERE id = p_detalle_id;
BEGIN
    SELECT COUNT(*)
    INTO v_existe
    FROM detalle_venta
    WHERE id = p_detalle_id;

    IF v_existe = 0 THEN
        RETURN -1;
    END IF;

    FOR r IN c_detalle LOOP
        IF r.cantidad > 0 THEN
            v_importe := NVL(r.cantidad * r.precio_unitario, 0);
        END IF;
    END LOOP;

    RETURN ROUND(v_importe, 2);
EXCEPTION
    WHEN OTHERS THEN
        RETURN -1;
END;
/

CREATE OR REPLACE FUNCTION fn_cantidad_detalle_venta (
    p_detalle_id IN detalle_venta.id%TYPE
)
RETURN NUMBER
IS
    v_existe NUMBER := 0;
    v_cantidad NUMBER := 0;

    CURSOR c_detalle IS
        SELECT cantidad
        FROM detalle_venta
        WHERE id = p_detalle_id;
BEGIN
    SELECT COUNT(*)
    INTO v_existe
    FROM detalle_venta
    WHERE id = p_detalle_id;

    IF v_existe = 0 THEN
        RETURN -1;
    END IF;

    FOR r IN c_detalle LOOP
        IF r.cantidad > 0 THEN
            v_cantidad := r.cantidad;
        ELSE
            v_cantidad := 0;
        END IF;
    END LOOP;

    RETURN v_cantidad;
EXCEPTION
    WHEN OTHERS THEN
        RETURN -1;
END;
/

CREATE OR REPLACE PROCEDURE pr_listar_interacciones
IS
    v_contador NUMBER := 0;

    CURSOR c_interacciones IS
        SELECT i.id, INITCAP(c.nombre) AS cliente, INITCAP(u.nombre) AS usuario, INITCAP(i.tipo) AS tipo, i.fecha, NVL(i.asunto, 'Sin asunto') AS asunto, NVL(i.resultado, 'Sin resultado') AS resultado
        FROM interacciones_cliente i
        JOIN clientes c ON i.cliente_id = c.id
        JOIN usuarios u ON i.usuario_id = u.id
        ORDER BY i.id;
BEGIN
    FOR r IN c_interacciones LOOP
        v_contador := v_contador + 1;

        IF UPPER(r.resultado) = 'INTERESADO' THEN
            DBMS_OUTPUT.PUT_LINE('ID: ' || r.id || ' | Cliente: ' || r.cliente || ' | Usuario: ' || r.usuario || ' | Tipo: ' || r.tipo || ' | Resultado positivo: ' || r.resultado);
        ELSE
            DBMS_OUTPUT.PUT_LINE('ID: ' || r.id || ' | Cliente: ' || r.cliente || ' | Usuario: ' || r.usuario || ' | Tipo: ' || r.tipo || ' | Resultado: ' || r.resultado);
        END IF;
    END LOOP;

    IF v_contador = 0 THEN
        DBMS_OUTPUT.PUT_LINE('No hay interacciones registradas.');
    ELSE
        DBMS_OUTPUT.PUT_LINE('Total interacciones listadas: ' || v_contador);
    END IF;
EXCEPTION
    WHEN OTHERS THEN
        DBMS_OUTPUT.PUT_LINE('Error en pr_listar_interacciones: ' || SQLERRM);
END;
/

CREATE OR REPLACE PROCEDURE pr_resumen_interacciones_cliente (
    p_cliente_id IN clientes.id%TYPE
)
IS
    v_existe NUMBER := 0;
    v_total NUMBER := 0;

    CURSOR c_interacciones IS
        SELECT INITCAP(tipo) AS tipo, fecha, NVL(asunto, 'Sin asunto') AS asunto, NVL(resultado, 'Sin resultado') AS resultado
        FROM interacciones_cliente
        WHERE cliente_id = p_cliente_id
        ORDER BY fecha;
BEGIN
    SELECT COUNT(*)
    INTO v_existe
    FROM clientes
    WHERE id = p_cliente_id;

    IF v_existe = 0 THEN
        DBMS_OUTPUT.PUT_LINE('No existe ningun cliente con ese ID.');
        RETURN;
    END IF;

    FOR r IN c_interacciones LOOP
        v_total := v_total + 1;

        IF r.fecha >= SYSTIMESTAMP THEN
            DBMS_OUTPUT.PUT_LINE('Interaccion futura | Tipo: ' || r.tipo || ' | Asunto: ' || r.asunto);
        ELSE
            DBMS_OUTPUT.PUT_LINE('Interaccion registrada | Tipo: ' || r.tipo || ' | Fecha: ' || TO_CHAR(r.fecha, 'DD/MM/YYYY HH24:MI') || ' | Resultado: ' || r.resultado);
        END IF;
    END LOOP;

    DBMS_OUTPUT.PUT_LINE('Total interacciones del cliente: ' || v_total);
EXCEPTION
    WHEN OTHERS THEN
        DBMS_OUTPUT.PUT_LINE('Error en pr_resumen_interacciones_cliente: ' || SQLERRM);
END;
/

CREATE OR REPLACE FUNCTION fn_num_interacciones_cliente_tabla (
    p_cliente_id IN clientes.id%TYPE
)
RETURN NUMBER
IS
    v_existe NUMBER := 0;
    v_total NUMBER := 0;

    CURSOR c_interacciones IS
        SELECT id
        FROM interacciones_cliente
        WHERE cliente_id = p_cliente_id;
BEGIN
    SELECT COUNT(*)
    INTO v_existe
    FROM clientes
    WHERE id = p_cliente_id;

    IF v_existe = 0 THEN
        RETURN -1;
    END IF;

    FOR r IN c_interacciones LOOP
        IF r.id IS NOT NULL THEN
            v_total := v_total + 1;
        END IF;
    END LOOP;

    RETURN v_total;
EXCEPTION
    WHEN OTHERS THEN
        RETURN -1;
END;
/

CREATE OR REPLACE FUNCTION fn_interacciones_con_proxima_accion (
    p_cliente_id IN clientes.id%TYPE
)
RETURN NUMBER
IS
    v_existe NUMBER := 0;
    v_total NUMBER := 0;

    CURSOR c_interacciones IS
        SELECT NVL(proxima_accion, 'Sin accion') AS proxima_accion
        FROM interacciones_cliente
        WHERE cliente_id = p_cliente_id;
BEGIN
    SELECT COUNT(*)
    INTO v_existe
    FROM clientes
    WHERE id = p_cliente_id;

    IF v_existe = 0 THEN
        RETURN -1;
    END IF;

    FOR r IN c_interacciones LOOP
        IF UPPER(r.proxima_accion) <> 'SIN ACCION' THEN
            v_total := v_total + 1;
        END IF;
    END LOOP;

    RETURN v_total;
EXCEPTION
    WHEN OTHERS THEN
        RETURN -1;
END;
/

BEGIN
    pr_listar_clientes;
END;
/

DECLARE
    v_cliente_id clientes.id%TYPE;
BEGIN
    SELECT MIN(id)
    INTO v_cliente_id
    FROM clientes;

    IF v_cliente_id IS NOT NULL THEN
        pr_resumen_cliente(v_cliente_id);
    ELSE
        DBMS_OUTPUT.PUT_LINE('No hay clientes para probar pr_resumen_cliente.');
    END IF;
END;
/

SELECT 
    id AS cliente_id,
    nombre,
    fn_total_ventas_cliente(id) AS total_ventas_completadas
FROM clientes
WHERE id = (
    SELECT MIN(id)
    FROM clientes
);

SELECT 
    id AS cliente_id,
    nombre,
    fn_num_interacciones_cliente(id) AS numero_interacciones
FROM clientes
WHERE id = (
    SELECT MIN(id)
    FROM clientes
);

BEGIN
    pr_listar_usuarios;
END;
/

DECLARE
    v_usuario_id usuarios.id%TYPE;
BEGIN
    SELECT MIN(id)
    INTO v_usuario_id
    FROM usuarios;

    IF v_usuario_id IS NOT NULL THEN
        pr_resumen_usuario(v_usuario_id);
    ELSE
        DBMS_OUTPUT.PUT_LINE('No hay usuarios para probar pr_resumen_usuario.');
    END IF;
END;
/

SELECT 
    id AS usuario_id,
    nombre,
    fn_total_ventas_usuario(id) AS total_ventas_usuario
FROM usuarios
WHERE id = (
    SELECT MIN(id)
    FROM usuarios
);

SELECT 
    id AS usuario_id,
    nombre,
    fn_num_interacciones_usuario(id) AS numero_interacciones_usuario
FROM usuarios
WHERE id = (
    SELECT MIN(id)
    FROM usuarios
);

BEGIN
    pr_listar_coches;
END;
/

DECLARE
    v_coche_id coches.id%TYPE;
BEGIN
    SELECT MIN(id)
    INTO v_coche_id
    FROM coches;

    IF v_coche_id IS NOT NULL THEN
        pr_resumen_coche(v_coche_id);
    ELSE
        DBMS_OUTPUT.PUT_LINE('No hay coches para probar pr_resumen_coche.');
    END IF;
END;
/

SELECT 
    id AS coche_id,
    marca,
    modelo,
    fn_coche_vendido(id) AS vendido
FROM coches
WHERE id = (
    SELECT MIN(id)
    FROM coches
);

SELECT 
    id AS coche_id,
    marca,
    modelo,
    anio,
    fn_antiguedad_coche(id) AS antiguedad
FROM coches
WHERE id = (
    SELECT MIN(id)
    FROM coches
);

BEGIN
    pr_listar_ventas;
END;
/

DECLARE
    v_venta_id ventas.id%TYPE;
BEGIN
    SELECT MIN(id)
    INTO v_venta_id
    FROM ventas;

    IF v_venta_id IS NOT NULL THEN
        pr_resumen_venta(v_venta_id);
    ELSE
        DBMS_OUTPUT.PUT_LINE('No hay ventas para probar pr_resumen_venta.');
    END IF;
END;
/

SELECT 
    id AS venta_id,
    total AS total_guardado,
    fn_total_venta_calculado(id) AS total_calculado
FROM ventas
WHERE id = (
    SELECT MIN(id)
    FROM ventas
);

SELECT 
    id AS venta_id,
    fn_num_lineas_venta(id) AS numero_lineas
FROM ventas
WHERE id = (
    SELECT MIN(id)
    FROM ventas
);

BEGIN
    pr_listar_detalle_venta;
END;
/

DECLARE
    v_detalle_id detalle_venta.id%TYPE;
BEGIN
    SELECT MIN(id)
    INTO v_detalle_id
    FROM detalle_venta;

    IF v_detalle_id IS NOT NULL THEN
        pr_resumen_detalle_venta(v_detalle_id);
    ELSE
        DBMS_OUTPUT.PUT_LINE('No hay detalles de venta para probar pr_resumen_detalle_venta.');
    END IF;
END;
/

SELECT 
    id AS detalle_id,
    venta_id,
    coche_id,
    fn_importe_detalle_venta(id) AS importe_detalle
FROM detalle_venta
WHERE id = (
    SELECT MIN(id)
    FROM detalle_venta
);

SELECT 
    id AS detalle_id,
    venta_id,
    coche_id,
    fn_cantidad_detalle_venta(id) AS cantidad_detalle
FROM detalle_venta
WHERE id = (
    SELECT MIN(id)
    FROM detalle_venta
);

BEGIN
    pr_listar_interacciones;
END;
/

DECLARE
    v_cliente_id clientes.id%TYPE;
BEGIN
    SELECT NVL(
        (SELECT MIN(cliente_id) FROM interacciones_cliente),
        (SELECT MIN(id) FROM clientes)
    )
    INTO v_cliente_id
    FROM dual;

    IF v_cliente_id IS NOT NULL THEN
        pr_resumen_interacciones_cliente(v_cliente_id);
    ELSE
        DBMS_OUTPUT.PUT_LINE('No hay clientes para probar pr_resumen_interacciones_cliente.');
    END IF;
END;
/
SELECT 
    c.id AS cliente_id,
    c.nombre AS cliente,
    fn_num_interacciones_cliente_tabla(c.id) AS numero_interacciones
FROM clientes c
WHERE c.id = (
    SELECT MIN(cliente_id)
    FROM interacciones_cliente
);

SELECT 
    c.id AS cliente_id,
    c.nombre AS cliente,
    fn_interacciones_con_proxima_accion(c.id) AS interacciones_con_proxima_accion
FROM clientes c
WHERE c.id = (
    SELECT MIN(cliente_id)
    FROM interacciones_cliente
);