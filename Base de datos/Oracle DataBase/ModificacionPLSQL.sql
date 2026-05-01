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
    SET precio = 14000, estado = 'Reservado'
    WHERE matricula = 'PL1001A';

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
    UPDATE ventas 
    SET estado = 'Completada'
    WHERE cliente_id = (
        SELECT id FROM clientes WHERE email = 'clientepl1@email.com'
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
    v_coches_modificados NUMBER;
    v_interacciones_modificadas NUMBER;
BEGIN
    SELECT COUNT(*) INTO v_coches_modificados
    FROM coches
    WHERE matricula LIKE 'PL%'
    AND precio IN (14000, 12800, 19000, 26000, 11500);

    SELECT COUNT(*) INTO v_interacciones_modificadas
    FROM interacciones_cliente
    WHERE resultado IN (
        'Cliente contactado',
        'Informacion enviada',
        'Pendiente de respuesta',
        'Prueba realizada',
        'Oferta enviada'
    );

    DBMS_OUTPUT.PUT_LINE('Coches modificados: ' || v_coches_modificados);
    DBMS_OUTPUT.PUT_LINE('Interacciones modificadas: ' || v_interacciones_modificadas);
END;
/