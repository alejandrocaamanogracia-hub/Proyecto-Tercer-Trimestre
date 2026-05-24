SET SERVEROUTPUT ON;

DECLARE
    v_cliente1 clientes.id%TYPE;
    v_cliente2 clientes.id%TYPE;
    v_cliente3 clientes.id%TYPE;
    v_cliente4 clientes.id%TYPE;
    v_cliente5 clientes.id%TYPE;

    v_usuario1 usuarios.id%TYPE;
    v_usuario2 usuarios.id%TYPE;
    v_usuario3 usuarios.id%TYPE;
    v_usuario4 usuarios.id%TYPE;
    v_usuario5 usuarios.id%TYPE;

    v_coche1 coches.id%TYPE;
    v_coche2 coches.id%TYPE;
    v_coche3 coches.id%TYPE;
    v_coche4 coches.id%TYPE;
    v_coche5 coches.id%TYPE;

    v_venta1 ventas.id%TYPE;
    v_venta2 ventas.id%TYPE;
    v_venta3 ventas.id%TYPE;
    v_venta4 ventas.id%TYPE;
    v_venta5 ventas.id%TYPE;
    
    v_existe_cliente NUMBER;
    v_existe_coche NUMBER;

BEGIN
    -- =========================
    -- CLIENTES
    -- =========================
   SELECT COUNT(*) INTO v_existe_cliente
   FROM clientes
   WHERE email = 'clientepl1@email.com';

IF v_existe_cliente = 0 THEN
    INSERT INTO clientes (nombre, email, telefono, direccion)
    VALUES ('Cliente PL1', 'clientepl1@email.com', '611111111', 'Calle PL 1')
    RETURNING id INTO v_cliente1;

    DBMS_OUTPUT.PUT_LINE('Cliente PL1 creado correctamente.');
ELSE
    SELECT id INTO v_cliente1
    FROM clientes
    WHERE email = 'clientepl1@email.com';

    DBMS_OUTPUT.PUT_LINE('El cliente PL1 ya existia. Se reutiliza su ID.');
END IF;

    INSERT INTO clientes (nombre, email, telefono, direccion)
    VALUES ('Cliente PL2', 'clientepl2@email.com', '622222222', 'Calle PL 2')
    RETURNING id INTO v_cliente2;

    INSERT INTO clientes (nombre, email, telefono, direccion)
    VALUES ('Cliente PL3', 'clientepl3@email.com', '633333333', 'Calle PL 3')
    RETURNING id INTO v_cliente3;

    INSERT INTO clientes (nombre, email, telefono, direccion)
    VALUES ('Cliente PL4', 'clientepl4@email.com', '644444444', 'Calle PL 4')
    RETURNING id INTO v_cliente4;

    INSERT INTO clientes (nombre, email, telefono, direccion)
    VALUES ('Cliente PL5', 'clientepl5@email.com', '655555555', 'Calle PL 5')
    RETURNING id INTO v_cliente5;


    -- =========================
    -- USUARIOS
    -- =========================
    INSERT INTO usuarios (nombre, email, rol, password_hash)
    VALUES ('Usuario PL1', 'usuariopl1@crm.com', 'Comercial', 'hash_pl1')
    RETURNING id INTO v_usuario1;

    INSERT INTO usuarios (nombre, email, rol, password_hash)
    VALUES ('Usuario PL2', 'usuariopl2@crm.com', 'Comercial', 'hash_pl2')
    RETURNING id INTO v_usuario2;

    INSERT INTO usuarios (nombre, email, rol, password_hash)
    VALUES ('Usuario PL3', 'usuariopl3@crm.com', 'Gestor', 'hash_pl3')
    RETURNING id INTO v_usuario3;

    INSERT INTO usuarios (nombre, email, rol, password_hash)
    VALUES ('Usuario PL4', 'usuariopl4@crm.com', 'Administrador', 'hash_pl4')
    RETURNING id INTO v_usuario4;

    INSERT INTO usuarios (nombre, email, rol, password_hash)
    VALUES ('Usuario PL5', 'usuariopl5@crm.com', 'Comercial', 'hash_pl5')
    RETURNING id INTO v_usuario5;


    -- =========================
    -- COCHES
    -- =========================
   SELECT COUNT(*) INTO v_existe_coche
   FROM coches
   WHERE matricula = 'PL1001A'
   OR bastidor = 'PLBASTIDOR001';

IF v_existe_coche = 0 THEN
    INSERT INTO coches 
    (marca, modelo, version, matricula, bastidor, anio, kilometros, combustible, cambio, color, precio, estado)
    VALUES ('Ford', 'Focus', '1.0 EcoBoost', 'PL1001A', 'PLBASTIDOR001', 2020, 45000, 'Gasolina', 'Manual', 'Azul', 14500, 'Disponible')
    RETURNING id INTO v_coche1;

    DBMS_OUTPUT.PUT_LINE('Coche Ford Focus creado correctamente.');
ELSE
    SELECT id INTO v_coche1
    FROM coches
    WHERE matricula = 'PL1001A'
       OR bastidor = 'PLBASTIDOR001';

    DBMS_OUTPUT.PUT_LINE('El coche Ford Focus ya existia. Se reutiliza su ID.');
END IF;

    INSERT INTO coches 
    (marca, modelo, version, matricula, bastidor, anio, kilometros, combustible, cambio, color, precio, estado)
    VALUES ('Renault', 'Megane', '1.5 dCi', 'PL1002B', 'PLBASTIDOR002', 2019, 60000, 'Diesel', 'Manual', 'Blanco', 13200, 'Disponible')
    RETURNING id INTO v_coche2;

    INSERT INTO coches 
    (marca, modelo, version, matricula, bastidor, anio, kilometros, combustible, cambio, color, precio, estado)
    VALUES ('Hyundai', 'Kona', 'Hybrid', 'PL1003C', 'PLBASTIDOR003', 2021, 35000, 'Hibrido', 'Automatico', 'Gris', 19800, 'Disponible')
    RETURNING id INTO v_coche3;

    INSERT INTO coches 
    (marca, modelo, version, matricula, bastidor, anio, kilometros, combustible, cambio, color, precio, estado)
    VALUES ('Kia', 'Niro', 'EV', 'PL1004D', 'PLBASTIDOR004', 2022, 25000, 'Electrico', 'Automatico', 'Negro', 26500, 'Disponible')
    RETURNING id INTO v_coche4;

    INSERT INTO coches 
    (marca, modelo, version, matricula, bastidor, anio, kilometros, combustible, cambio, color, precio, estado)
    VALUES ('Opel', 'Corsa', '1.2 Turbo', 'PL1005E', 'PLBASTIDOR005', 2020, 40000, 'Gasolina', 'Manual', 'Rojo', 11900, 'Disponible')
    RETURNING id INTO v_coche5;


    -- =========================
    -- VENTAS
    -- =========================
    INSERT INTO ventas (cliente_id, usuario_id, fecha, estado, total)
    VALUES (v_cliente1, v_usuario1, TO_DATE('2026-05-01', 'YYYY-MM-DD'), 'Pendiente', 14500)
    RETURNING id INTO v_venta1;

    INSERT INTO ventas (cliente_id, usuario_id, fecha, estado, total)
    VALUES (v_cliente2, v_usuario2, TO_DATE('2026-05-02', 'YYYY-MM-DD'), 'Pendiente', 13200)
    RETURNING id INTO v_venta2;

    INSERT INTO ventas (cliente_id, usuario_id, fecha, estado, total)
    VALUES (v_cliente3, v_usuario3, TO_DATE('2026-05-03', 'YYYY-MM-DD'), 'Presupuesto', 19800)
    RETURNING id INTO v_venta3;

    INSERT INTO ventas (cliente_id, usuario_id, fecha, estado, total)
    VALUES (v_cliente4, v_usuario4, TO_DATE('2026-05-04', 'YYYY-MM-DD'), 'Presupuesto', 26500)
    RETURNING id INTO v_venta4;

    INSERT INTO ventas (cliente_id, usuario_id, fecha, estado, total)
    VALUES (v_cliente5, v_usuario5, TO_DATE('2026-05-05', 'YYYY-MM-DD'), 'Pendiente', 11900)
    RETURNING id INTO v_venta5;


    -- =========================
    -- DETALLE_VENTA
    -- =========================
    INSERT INTO detalle_venta (venta_id, coche_id, cantidad, precio_unitario)
    VALUES (v_venta1, v_coche1, 1, 14500);

    INSERT INTO detalle_venta (venta_id, coche_id, cantidad, precio_unitario)
    VALUES (v_venta2, v_coche2, 1, 13200);

    INSERT INTO detalle_venta (venta_id, coche_id, cantidad, precio_unitario)
    VALUES (v_venta3, v_coche3, 1, 19800);

    INSERT INTO detalle_venta (venta_id, coche_id, cantidad, precio_unitario)
    VALUES (v_venta4, v_coche4, 1, 26500);

    INSERT INTO detalle_venta (venta_id, coche_id, cantidad, precio_unitario)
    VALUES (v_venta5, v_coche5, 1, 11900);


    -- =========================
    -- INTERACCIONES_CLIENTE
    -- =========================
    INSERT INTO interacciones_cliente 
    (cliente_id, usuario_id, tipo, fecha, asunto, descripcion, resultado, proxima_accion, fecha_proxima)
    VALUES 
    (v_cliente1, v_usuario1, 'Llamada', SYSTIMESTAMP, 'Consulta Ford Focus', 'Cliente interesado en financiacion.', 'Interesado', 'Enviar presupuesto', SYSTIMESTAMP + 1);

    INSERT INTO interacciones_cliente 
    (cliente_id, usuario_id, tipo, fecha, asunto, descripcion, resultado, proxima_accion, fecha_proxima)
    VALUES 
    (v_cliente2, v_usuario2, 'Email', SYSTIMESTAMP, 'Consulta Renault Megane', 'Cliente solicita mas fotos.', 'Respondido', 'Enviar fotos', SYSTIMESTAMP + 1);

    INSERT INTO interacciones_cliente 
    (cliente_id, usuario_id, tipo, fecha, asunto, descripcion, resultado, proxima_accion, fecha_proxima)
    VALUES 
    (v_cliente3, v_usuario3, 'WhatsApp', SYSTIMESTAMP, 'Consulta Hyundai Kona', 'Cliente pregunta por garantia.', 'Interesado', 'Llamar al cliente', SYSTIMESTAMP + 2);

    INSERT INTO interacciones_cliente 
    (cliente_id, usuario_id, tipo, fecha, asunto, descripcion, resultado, proxima_accion, fecha_proxima)
    VALUES 
    (v_cliente4, v_usuario4, 'Reunion', SYSTIMESTAMP, 'Prueba Kia Niro', 'Cliente realiza prueba de conduccion.', 'Pendiente', 'Seguimiento comercial', SYSTIMESTAMP + 3);

    INSERT INTO interacciones_cliente 
    (cliente_id, usuario_id, tipo, fecha, asunto, descripcion, resultado, proxima_accion, fecha_proxima)
    VALUES 
    (v_cliente5, v_usuario5, 'Visita', SYSTIMESTAMP, 'Visita Opel Corsa', 'Cliente visita el concesionario.', 'Interesado', 'Enviar oferta final', SYSTIMESTAMP + 2);

    COMMIT;
    
    DBMS_OUTPUT.PUT_LINE(' ');
    DBMS_OUTPUT.PUT_LINE('Creacion de registros realizada correctamente.');
END;
/
DECLARE
    v_clientes_creados NUMBER;
    v_usuarios_creados NUMBER;
    v_coches_creados NUMBER;
    v_ventas_creadas NUMBER;
    v_detalles_creados NUMBER;
    v_interacciones_creadas NUMBER;

    CURSOR c_clientes IS
        SELECT id, nombre, email, telefono
        FROM clientes
        WHERE email LIKE 'clientepl%@email.com'
        ORDER BY id;

    CURSOR c_usuarios IS
        SELECT id, nombre, email, rol
        FROM usuarios
        WHERE email LIKE 'usuariopl%@crm.com'
        ORDER BY id;

    CURSOR c_coches IS
        SELECT id, marca, modelo, matricula, precio, estado
        FROM coches
        WHERE matricula LIKE 'PL%'
        ORDER BY id;

    CURSOR c_ventas IS
        SELECT 
            v.id AS venta_id,
            c.nombre AS cliente,
            u.nombre AS usuario,
            v.fecha,
            v.estado,
            v.total
        FROM ventas v
        INNER JOIN clientes c ON v.cliente_id = c.id
        INNER JOIN usuarios u ON v.usuario_id = u.id
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
            u.nombre AS usuario,
            ic.tipo,
            ic.resultado,
            ic.proxima_accion
        FROM interacciones_cliente ic
        INNER JOIN clientes c ON ic.cliente_id = c.id
        INNER JOIN usuarios u ON ic.usuario_id = u.id
        WHERE c.email LIKE 'clientepl%@email.com'
        ORDER BY c.id;

BEGIN
    SELECT COUNT(*) INTO v_clientes_creados
    FROM clientes
    WHERE email LIKE 'clientepl%@email.com';

    SELECT COUNT(*) INTO v_usuarios_creados
    FROM usuarios
    WHERE email LIKE 'usuariopl%@crm.com';

    SELECT COUNT(*) INTO v_coches_creados
    FROM coches
    WHERE matricula LIKE 'PL%';

    SELECT COUNT(*) INTO v_ventas_creadas
    FROM ventas
    WHERE cliente_id IN (
        SELECT id
        FROM clientes
        WHERE email LIKE 'clientepl%@email.com'
    );

    SELECT COUNT(*) INTO v_detalles_creados
    FROM detalle_venta
    WHERE coche_id IN (
        SELECT id
        FROM coches
        WHERE matricula LIKE 'PL%'
    );

    SELECT COUNT(*) INTO v_interacciones_creadas
    FROM interacciones_cliente
    WHERE cliente_id IN (
        SELECT id
        FROM clientes
        WHERE email LIKE 'clientepl%@email.com'
    );

    DBMS_OUTPUT.PUT_LINE('==========================================');
    DBMS_OUTPUT.PUT_LINE('RESUMEN DE CREACION DE REGISTROS');
    DBMS_OUTPUT.PUT_LINE('==========================================');
    DBMS_OUTPUT.PUT_LINE('Clientes creados/reutilizados: ' || v_clientes_creados);
    DBMS_OUTPUT.PUT_LINE('Usuarios creados: ' || v_usuarios_creados);
    DBMS_OUTPUT.PUT_LINE('Coches creados/reutilizados: ' || v_coches_creados);
    DBMS_OUTPUT.PUT_LINE('Ventas creadas: ' || v_ventas_creadas);
    DBMS_OUTPUT.PUT_LINE('Detalles de venta creados: ' || v_detalles_creados);
    DBMS_OUTPUT.PUT_LINE('Interacciones creadas: ' || v_interacciones_creadas);
    DBMS_OUTPUT.PUT_LINE('==========================================');

    DBMS_OUTPUT.PUT_LINE(' ');
    DBMS_OUTPUT.PUT_LINE('CLIENTES CREADOS');
    DBMS_OUTPUT.PUT_LINE('------------------------------------------');

    FOR r_cliente IN c_clientes LOOP
        DBMS_OUTPUT.PUT_LINE(
            'ID: ' || r_cliente.id ||
            ' | Cliente: ' || r_cliente.nombre ||
            ' | Email: ' || r_cliente.email ||
            ' | Telefono: ' || r_cliente.telefono
        );
    END LOOP;

    DBMS_OUTPUT.PUT_LINE(' ');
    DBMS_OUTPUT.PUT_LINE('USUARIOS CREADOS');
    DBMS_OUTPUT.PUT_LINE('------------------------------------------');

    FOR r_usuario IN c_usuarios LOOP
        DBMS_OUTPUT.PUT_LINE(
            'ID: ' || r_usuario.id ||
            ' | Usuario: ' || r_usuario.nombre ||
            ' | Email: ' || r_usuario.email ||
            ' | Rol: ' || r_usuario.rol
        );
    END LOOP;

    DBMS_OUTPUT.PUT_LINE(' ');
    DBMS_OUTPUT.PUT_LINE('COCHES CREADOS');
    DBMS_OUTPUT.PUT_LINE('------------------------------------------');

    FOR r_coche IN c_coches LOOP
        DBMS_OUTPUT.PUT_LINE(
            'ID: ' || r_coche.id ||
            ' | Coche: ' || r_coche.marca || ' ' || r_coche.modelo ||
            ' | Matricula: ' || r_coche.matricula ||
            ' | Precio: ' || r_coche.precio ||
            ' | Estado: ' || r_coche.estado
        );
    END LOOP;

    DBMS_OUTPUT.PUT_LINE(' ');
    DBMS_OUTPUT.PUT_LINE('VENTAS CREADAS');
    DBMS_OUTPUT.PUT_LINE('------------------------------------------');

    FOR r_venta IN c_ventas LOOP
        DBMS_OUTPUT.PUT_LINE(
            'Venta ID: ' || r_venta.venta_id ||
            ' | Cliente: ' || r_venta.cliente ||
            ' | Usuario: ' || r_venta.usuario ||
            ' | Fecha: ' || TO_CHAR(r_venta.fecha, 'DD/MM/YYYY') ||
            ' | Estado: ' || r_venta.estado ||
            ' | Total: ' || r_venta.total
        );
    END LOOP;

    DBMS_OUTPUT.PUT_LINE(' ');
    DBMS_OUTPUT.PUT_LINE('DETALLES DE VENTA CREADOS');
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
    DBMS_OUTPUT.PUT_LINE('INTERACCIONES CREADAS');
    DBMS_OUTPUT.PUT_LINE('------------------------------------------');

    FOR r_interaccion IN c_interacciones LOOP
        DBMS_OUTPUT.PUT_LINE(
            'Cliente: ' || r_interaccion.cliente ||
            ' | Usuario: ' || r_interaccion.usuario ||
            ' | Tipo: ' || r_interaccion.tipo ||
            ' | Resultado: ' || r_interaccion.resultado ||
            ' | Proxima accion: ' || r_interaccion.proxima_accion
        );
    END LOOP;

    DBMS_OUTPUT.PUT_LINE('------------------------------------------');
    DBMS_OUTPUT.PUT_LINE('Listado de registros creados finalizado.');
END;
/