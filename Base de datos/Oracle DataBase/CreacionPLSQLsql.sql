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

BEGIN
    -- =========================
    -- CLIENTES
    -- =========================
    INSERT INTO clientes (nombre, email, telefono, direccion)
    VALUES ('Cliente PL1', 'clientepl1@email.com', '611111111', 'Calle PL 1')
    RETURNING id INTO v_cliente1;

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
    INSERT INTO coches 
    (marca, modelo, version, matricula, bastidor, anio, kilometros, combustible, cambio, color, precio, estado)
    VALUES ('Ford', 'Focus', '1.0 EcoBoost', 'PL1001A', 'PLBASTIDOR001', 2020, 45000, 'Gasolina', 'Manual', 'Azul', 14500, 'Disponible')
    RETURNING id INTO v_coche1;

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

    DBMS_OUTPUT.PUT_LINE('Creacion de registros realizada correctamente.');
END;
/

DECLARE
    v_clientes NUMBER;
    v_usuarios NUMBER;
    v_coches NUMBER;
BEGIN
    SELECT COUNT(*) INTO v_clientes
    FROM clientes
    WHERE email LIKE 'clientepl%@email.com';

    SELECT COUNT(*) INTO v_usuarios
    FROM usuarios
    WHERE email LIKE 'usuariopl%@crm.com';

    SELECT COUNT(*) INTO v_coches
    FROM coches
    WHERE matricula LIKE 'PL%';

    DBMS_OUTPUT.PUT_LINE('Clientes PL creados: ' || v_clientes);
    DBMS_OUTPUT.PUT_LINE('Usuarios PL creados: ' || v_usuarios);
    DBMS_OUTPUT.PUT_LINE('Coches PL creados: ' || v_coches);
END;
/