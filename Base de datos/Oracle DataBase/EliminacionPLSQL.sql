SET SERVEROUTPUT ON;

BEGIN
    -- =========================
    -- DETALLE_VENTA
    -- Primero eliminamos detalle_venta porque depende de ventas y coches
    -- =========================
    DELETE FROM detalle_venta
    WHERE coche_id IN (
        SELECT id 
        FROM coches 
        WHERE matricula IN (
            'PL1001A', 
            'PL1002B', 
            'PL1003C', 
            'PL1004D', 
            'PL1005E'
        )
    );


    -- =========================
    -- INTERACCIONES_CLIENTE
    -- Depende de clientes y usuarios
    -- =========================
    DELETE FROM interacciones_cliente
    WHERE cliente_id IN (
        SELECT id 
        FROM clientes
        WHERE email IN (
            'clientepl1@email.com',
            'clientepl2@email.com',
            'clientepl3@email.com',
            'clientepl4@email.com',
            'clientepl5@email.com'
        )
    );


    -- =========================
    -- VENTAS
    -- Depende de clientes y usuarios
    -- =========================
    DELETE FROM ventas
    WHERE cliente_id IN (
        SELECT id 
        FROM clientes
        WHERE email IN (
            'clientepl1@email.com',
            'clientepl2@email.com',
            'clientepl3@email.com',
            'clientepl4@email.com',
            'clientepl5@email.com'
        )
    );


    -- =========================
    -- COCHES
    -- =========================
    DELETE FROM coches
    WHERE matricula IN (
        'PL1001A', 
        'PL1002B', 
        'PL1003C', 
        'PL1004D', 
        'PL1005E'
    );


    -- =========================
    -- USUARIOS
    -- =========================
    DELETE FROM usuarios
    WHERE email IN (
        'usuariopl1@crm.com',
        'usuariopl2@crm.com',
        'usuariopl3@crm.com',
        'usuariopl4@crm.com',
        'usuariopl5@crm.com'
    );


    -- =========================
    -- CLIENTES
    -- =========================
    DELETE FROM clientes
    WHERE email IN (
        'clientepl1@email.com',
        'clientepl2@email.com',
        'clientepl3@email.com',
        'clientepl4@email.com',
        'clientepl5@email.com'
    );

    COMMIT;

    DBMS_OUTPUT.PUT_LINE('Eliminacion de registros realizada correctamente.');
END;
/

DECLARE
    v_clientes_pl NUMBER;
    v_usuarios_pl NUMBER;
    v_coches_pl NUMBER;
    v_total_clientes NUMBER;
    v_total_usuarios NUMBER;
    v_total_coches NUMBER;
BEGIN
    SELECT COUNT(*) INTO v_clientes_pl
    FROM clientes
    WHERE email LIKE 'clientepl%@email.com';

    SELECT COUNT(*) INTO v_usuarios_pl
    FROM usuarios
    WHERE email LIKE 'usuariopl%@crm.com';

    SELECT COUNT(*) INTO v_coches_pl
    FROM coches
    WHERE matricula LIKE 'PL%';

    SELECT COUNT(*) INTO v_total_clientes FROM clientes;
    SELECT COUNT(*) INTO v_total_usuarios FROM usuarios;
    SELECT COUNT(*) INTO v_total_coches FROM coches;

    DBMS_OUTPUT.PUT_LINE('Clientes PL restantes: ' || v_clientes_pl);
    DBMS_OUTPUT.PUT_LINE('Usuarios PL restantes: ' || v_usuarios_pl);
    DBMS_OUTPUT.PUT_LINE('Coches PL restantes: ' || v_coches_pl);

    DBMS_OUTPUT.PUT_LINE('Total clientes: ' || v_total_clientes);
    DBMS_OUTPUT.PUT_LINE('Total usuarios: ' || v_total_usuarios);
    DBMS_OUTPUT.PUT_LINE('Total coches: ' || v_total_coches);
END;
/