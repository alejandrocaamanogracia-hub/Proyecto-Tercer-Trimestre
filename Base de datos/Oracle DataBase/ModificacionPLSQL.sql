SET SERVEROUTPUT ON;

BEGIN
    -- =========================
    -- CLIENTES
    -- =========================
    UPDATE clientes 
    SET telefono = '699111111' 
    WHERE email = 'clientepl1@email.com';

    UPDATE clientes 
    SET telefono = '699222222' 
    WHERE email = 'clientepl2@email.com';

    UPDATE clientes 
    SET telefono = '699333333' 
    WHERE email = 'clientepl3@email.com';

    UPDATE clientes 
    SET telefono = '699444444' 
    WHERE email = 'clientepl4@email.com';

    UPDATE clientes 
    SET telefono = '699555555' 
    WHERE email = 'clientepl5@email.com';


    -- =========================
    -- USUARIOS
    -- =========================
    UPDATE usuarios 
    SET rol = 'Gestor' 
    WHERE email = 'usuariopl1@crm.com';

    UPDATE usuarios 
    SET rol = 'Gestor' 
    WHERE email = 'usuariopl2@crm.com';

    UPDATE usuarios 
    SET rol = 'Comercial' 
    WHERE email = 'usuariopl3@crm.com';

    UPDATE usuarios 
    SET rol = 'Comercial' 
    WHERE email = 'usuariopl4@crm.com';

    UPDATE usuarios 
    SET rol = 'Administrador' 
    WHERE email = 'usuariopl5@crm.com';


    -- =========================
    -- COCHES
    -- =========================
    UPDATE coches
    SET 
    precio = CASE
        WHEN kilometros > 40000 THEN precio - 500
        ELSE precio
    END,
    estado = CASE
        WHEN precio <= 14500 THEN 'Reservado'
        ELSE estado
    END
    WHERE matricula = 'PL1001A'
    AND estado = 'Disponible';

    UPDATE coches 
    SET precio = 12800, estado = 'Reservado'
    WHERE matricula = 'PL1002B';

    UPDATE coches 
    SET precio = 19000, estado = 'Disponible'
    WHERE matricula = 'PL1003C';

    UPDATE coches 
    SET precio = 26000, estado = 'Vendido'
    WHERE matricula = 'PL1004D';

    UPDATE coches 
    SET precio = 11500, estado = 'Disponible'
    WHERE matricula = 'PL1005E';


    -- =========================
    -- VENTAS
    -- =========================
    UPDATE ventas v
    SET 
    v.estado = 'Completada',
    v.total = (
        SELECT SUM(dv.cantidad * dv.precio_unitario)
        FROM detalle_venta dv
        WHERE dv.venta_id = v.id
    )
    WHERE v.cliente_id = (
    SELECT id 
    FROM clientes 
    WHERE email = 'clientepl1@email.com'
    )
    AND EXISTS (
    SELECT 1
    FROM detalle_venta dv
    WHERE dv.venta_id = v.id
);

    UPDATE ventas 
    SET estado = 'Completada'
    WHERE cliente_id = (
        SELECT id FROM clientes WHERE email = 'clientepl2@email.com'
    );

    UPDATE ventas 
    SET estado = 'Pendiente'
    WHERE cliente_id = (
        SELECT id FROM clientes WHERE email = 'clientepl3@email.com'
    );

    UPDATE ventas 
    SET estado = 'Cancelada'
    WHERE cliente_id = (
        SELECT id FROM clientes WHERE email = 'clientepl4@email.com'
    );

    UPDATE ventas 
    SET estado = 'Completada'
    WHERE cliente_id = (
        SELECT id FROM clientes WHERE email = 'clientepl5@email.com'
    );


    -- =========================
    -- DETALLE_VENTA
    -- =========================
    UPDATE detalle_venta 
    SET precio_unitario = 14000
    WHERE coche_id = (
        SELECT id FROM coches WHERE matricula = 'PL1001A'
    );

    UPDATE detalle_venta 
    SET precio_unitario = 12800
    WHERE coche_id = (
        SELECT id FROM coches WHERE matricula = 'PL1002B'
    );

    UPDATE detalle_venta 
    SET precio_unitario = 19000
    WHERE coche_id = (
        SELECT id FROM coches WHERE matricula = 'PL1003C'
    );

    UPDATE detalle_venta 
    SET precio_unitario = 26000
    WHERE coche_id = (
        SELECT id FROM coches WHERE matricula = 'PL1004D'
    );

    UPDATE detalle_venta 
    SET precio_unitario = 11500
    WHERE coche_id = (
        SELECT id FROM coches WHERE matricula = 'PL1005E'
    );


    -- =========================
    -- INTERACCIONES_CLIENTE
    -- =========================
    UPDATE interacciones_cliente 
    SET resultado = 'Cliente contactado'
    WHERE cliente_id = (
        SELECT id FROM clientes WHERE email = 'clientepl1@email.com'
    );

    UPDATE interacciones_cliente 
    SET resultado = 'Informacion enviada'
    WHERE cliente_id = (
        SELECT id FROM clientes WHERE email = 'clientepl2@email.com'
    );

    UPDATE interacciones_cliente 
    SET resultado = 'Pendiente de respuesta'
    WHERE cliente_id = (
        SELECT id FROM clientes WHERE email = 'clientepl3@email.com'
    );

    UPDATE interacciones_cliente 
    SET resultado = 'Prueba realizada'
    WHERE cliente_id = (
        SELECT id FROM clientes WHERE email = 'clientepl4@email.com'
    );

    UPDATE interacciones_cliente 
    SET resultado = 'Oferta enviada'
    WHERE cliente_id = (
        SELECT id FROM clientes WHERE email = 'clientepl5@email.com'
    );

    COMMIT;

    DBMS_OUTPUT.PUT_LINE('Modificacion de registros realizada correctamente.');
END;
/

DECLARE
    v_clientes_modificados NUMBER;
    v_usuarios_modificados NUMBER;
    v_coches_modificados NUMBER;
    v_ventas_modificadas NUMBER;
    v_detalles_modificados NUMBER;
    v_interacciones_modificadas NUMBER;

    CURSOR c_clientes IS
        SELECT nombre, email, telefono
        FROM clientes
        WHERE email LIKE 'clientepl%@email.com'
        ORDER BY email;

    CURSOR c_usuarios IS
        SELECT nombre, email, rol
        FROM usuarios
        WHERE email LIKE 'usuariopl%@crm.com'
        ORDER BY email;

    CURSOR c_coches IS
        SELECT marca, modelo, matricula, precio, estado
        FROM coches
        WHERE matricula LIKE 'PL%'
        ORDER BY matricula;

    CURSOR c_ventas IS
        SELECT 
            v.id AS venta_id,
            c.nombre AS cliente,
            v.estado,
            v.total
        FROM ventas v
        INNER JOIN clientes c ON v.cliente_id = c.id
        WHERE c.email LIKE 'clientepl%@email.com'
        ORDER BY v.id;

    CURSOR c_detalles IS
        SELECT 
            dv.venta_id,
            co.marca,
            co.modelo,
            co.matricula,
            dv.cantidad,
            dv.precio_unitario
        FROM detalle_venta dv
        INNER JOIN coches co ON dv.coche_id = co.id
        WHERE co.matricula LIKE 'PL%'
        ORDER BY dv.venta_id;

    CURSOR c_interacciones IS
        SELECT 
            c.nombre AS cliente,
            ic.tipo,
            ic.resultado
        FROM interacciones_cliente ic
        INNER JOIN clientes c ON ic.cliente_id = c.id
        WHERE c.email LIKE 'clientepl%@email.com'
        ORDER BY c.email;

BEGIN
    SELECT COUNT(*) INTO v_clientes_modificados
    FROM clientes
    WHERE email LIKE 'clientepl%@email.com'
    AND telefono LIKE '699%';

    SELECT COUNT(*) INTO v_usuarios_modificados
    FROM usuarios
    WHERE email LIKE 'usuariopl%@crm.com';

    SELECT COUNT(*) INTO v_coches_modificados
    FROM coches
    WHERE matricula LIKE 'PL%';

    SELECT COUNT(*) INTO v_ventas_modificadas
    FROM ventas
    WHERE cliente_id IN (
        SELECT id
        FROM clientes
        WHERE email LIKE 'clientepl%@email.com'
    );

    SELECT COUNT(*) INTO v_detalles_modificados
    FROM detalle_venta
    WHERE coche_id IN (
        SELECT id
        FROM coches
        WHERE matricula LIKE 'PL%'
    );

    SELECT COUNT(*) INTO v_interacciones_modificadas
    FROM interacciones_cliente
    WHERE cliente_id IN (
        SELECT id
        FROM clientes
        WHERE email LIKE 'clientepl%@email.com'
    );

    DBMS_OUTPUT.PUT_LINE('==========================================');
    DBMS_OUTPUT.PUT_LINE('RESUMEN DE MODIFICACION DE REGISTROS');
    DBMS_OUTPUT.PUT_LINE('==========================================');
    DBMS_OUTPUT.PUT_LINE('Clientes modificados: ' || v_clientes_modificados);
    DBMS_OUTPUT.PUT_LINE('Usuarios modificados: ' || v_usuarios_modificados);
    DBMS_OUTPUT.PUT_LINE('Coches modificados: ' || v_coches_modificados);
    DBMS_OUTPUT.PUT_LINE('Ventas modificadas: ' || v_ventas_modificadas);
    DBMS_OUTPUT.PUT_LINE('Detalles de venta modificados: ' || v_detalles_modificados);
    DBMS_OUTPUT.PUT_LINE('Interacciones modificadas: ' || v_interacciones_modificadas);
    DBMS_OUTPUT.PUT_LINE('==========================================');

    DBMS_OUTPUT.PUT_LINE(' ');
    DBMS_OUTPUT.PUT_LINE('CLIENTES MODIFICADOS');
    DBMS_OUTPUT.PUT_LINE('------------------------------------------');

    FOR r_cliente IN c_clientes LOOP
        DBMS_OUTPUT.PUT_LINE(
            'Cliente: ' || r_cliente.nombre ||
            ' | Email: ' || r_cliente.email ||
            ' | Telefono: ' || r_cliente.telefono
        );
    END LOOP;

    DBMS_OUTPUT.PUT_LINE(' ');
    DBMS_OUTPUT.PUT_LINE('USUARIOS MODIFICADOS');
    DBMS_OUTPUT.PUT_LINE('------------------------------------------');

    FOR r_usuario IN c_usuarios LOOP
        DBMS_OUTPUT.PUT_LINE(
            'Usuario: ' || r_usuario.nombre ||
            ' | Email: ' || r_usuario.email ||
            ' | Rol: ' || r_usuario.rol
        );
    END LOOP;

    DBMS_OUTPUT.PUT_LINE(' ');
    DBMS_OUTPUT.PUT_LINE('COCHES MODIFICADOS');
    DBMS_OUTPUT.PUT_LINE('------------------------------------------');

    FOR r_coche IN c_coches LOOP
        DBMS_OUTPUT.PUT_LINE(
            'Coche: ' || r_coche.marca || ' ' || r_coche.modelo ||
            ' | Matricula: ' || r_coche.matricula ||
            ' | Precio: ' || r_coche.precio ||
            ' | Estado: ' || r_coche.estado
        );
    END LOOP;

    DBMS_OUTPUT.PUT_LINE(' ');
    DBMS_OUTPUT.PUT_LINE('VENTAS MODIFICADAS');
    DBMS_OUTPUT.PUT_LINE('------------------------------------------');

    FOR r_venta IN c_ventas LOOP
        DBMS_OUTPUT.PUT_LINE(
            'Venta ID: ' || r_venta.venta_id ||
            ' | Cliente: ' || r_venta.cliente ||
            ' | Estado: ' || r_venta.estado ||
            ' | Total: ' || r_venta.total
        );
    END LOOP;

    DBMS_OUTPUT.PUT_LINE(' ');
    DBMS_OUTPUT.PUT_LINE('DETALLES DE VENTA MODIFICADOS');
    DBMS_OUTPUT.PUT_LINE('------------------------------------------');

    FOR r_detalle IN c_detalles LOOP
        DBMS_OUTPUT.PUT_LINE(
            'Venta ID: ' || r_detalle.venta_id ||
            ' | Coche: ' || r_detalle.marca || ' ' || r_detalle.modelo ||
            ' | Matricula: ' || r_detalle.matricula ||
            ' | Cantidad: ' || r_detalle.cantidad ||
            ' | Precio unitario: ' || r_detalle.precio_unitario
        );
    END LOOP;

    DBMS_OUTPUT.PUT_LINE(' ');
    DBMS_OUTPUT.PUT_LINE('INTERACCIONES MODIFICADAS');
    DBMS_OUTPUT.PUT_LINE('------------------------------------------');

    FOR r_interaccion IN c_interacciones LOOP
        DBMS_OUTPUT.PUT_LINE(
            'Cliente: ' || r_interaccion.cliente ||
            ' | Tipo: ' || r_interaccion.tipo ||
            ' | Resultado: ' || r_interaccion.resultado
        );
    END LOOP;

    DBMS_OUTPUT.PUT_LINE('------------------------------------------');
    DBMS_OUTPUT.PUT_LINE('Listado de registros modificados finalizado.');
END;
/