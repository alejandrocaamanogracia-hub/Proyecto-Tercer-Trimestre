SET SERVEROUTPUT ON;

// CLIENTES

DECLARE
    CURSOR c_clientes IS
        SELECT id, nombre, email FROM clientes;

    v_id clientes.id%TYPE;
    v_nombre clientes.nombre%TYPE;
    v_email clientes.email%TYPE;

BEGIN
    OPEN c_clientes;

    LOOP
        FETCH c_clientes INTO v_id, v_nombre, v_email;
        EXIT WHEN c_clientes%NOTFOUND;

        DBMS_OUTPUT.PUT_LINE(v_id || ' - ' || v_nombre || ' - ' || v_email);
    END LOOP;

    CLOSE c_clientes;
END;
/

DECLARE 

    CURSOR c_cliente (v_ciudad clientes.direccion%type) IS
        
        SELECT id, nombre FROM clientes WHERE direccion LIKE ('%' || v_ciudad || '%' );

BEGIN

        FOR cliente IN c_cliente ('Madrid') LOOP
        
            DBMS_OUTPUT.PUT_LINE('El cliente ' || cliente.nombre || ' con id ' || cliente.id || ' vive en Madrid');
            
        END LOOP;

END;
/

DECLARE 

    CURSOR c_cliente IS
        
        SELECT c.id, c.nombre
        FROM clientes c
        JOIN interacciones_cliente i ON i.cliente_id = c.id;

BEGIN

        DBMS_OUTPUT.PUT_LINE('Los clientes que han hecho alguna consulta son: ');

        FOR cliente IN c_cliente LOOP
        
            DBMS_OUTPUT.PUT_LINE('id: ' || cliente.id || ' nombre: ' || cliente.nombre);
            
        END LOOP;


END;
/

DECLARE 

    CURSOR c_cliente IS
        
        SELECT id, nombre
        FROM clientes 
        WHERE telefono IS NULL
        FOR UPDATE;

BEGIN

        DBMS_OUTPUT.PUT_LINE('Los clientes que no tienen telefono asignado son: ');

        FOR cliente IN c_cliente LOOP
        
            DBMS_OUTPUT.PUT_LINE('id: ' || cliente.id || ' nombre: ' || cliente.nombre);
            
            DBMS_OUTPUT.PUT_LINE('Se le asignará el número por defecto 0');
            
            UPDATE clientes 
            SET telefono = 0
            WHERE CURRENT OF c_cliente;
            
        END LOOP;


END;
/

DECLARE 

    CURSOR c_cliente (v_compra ventas.total%type) IS
        
        SELECT c.id, c.nombre, v.total
        FROM clientes c
        JOIN ventas v ON v.cliente_id = c.id
        WHERE v.total > v_compra;

BEGIN

        DBMS_OUTPUT.PUT_LINE('Clientes que han hecho una compra de más de 20000€' );

        FOR cliente IN c_cliente (20000) LOOP
        
            DBMS_OUTPUT.PUT_LINE('id: ' || cliente.id || ' nombre: ' || cliente.nombre || ' total: ' || cliente.total);
            
        END LOOP;


END;
/

// COCHES

DECLARE
    CURSOR c_coches (v_anio coches.anio%type) IS
        SELECT id, marca, modelo, version, anio FROM coches
        WHERE anio > v_anio;

BEGIN

    DBMS_OUTPUT.PUT_LINE('Coches del 2022 a la actualidad: ');

    FOR coche IN c_coches (2022) LOOP
    
        DBMS_OUTPUT.PUT_LINE(coche.id || ' ' || coche.marca || ' ' || coche.modelo || ' ' || coche.anio);
    
    END LOOP;

END;
/

DECLARE
    CURSOR c_coches IS
        SELECT id, marca, modelo, version, anio FROM coches
        WHERE estado LIKE '%Disponible%';

BEGIN

    DBMS_OUTPUT.PUT_LINE('Coches disponibles: ');

    FOR coche IN c_coches LOOP
    
        DBMS_OUTPUT.PUT_LINE(coche.id || ' ' || coche.marca || ' ' || coche.modelo || ' ' || coche.anio);
    
    END LOOP;

END;
/

DECLARE
    CURSOR c_coches IS
        SELECT id, marca, modelo, version, anio FROM coches
        WHERE estado LIKE '%Disponible%' AND color LIKE '%Azul%'
        FOR UPDATE;

BEGIN

    DBMS_OUTPUT.PUT_LINE('Los coches disponibles en color azul son: ');

    FOR coche IN c_coches LOOP
    
        DBMS_OUTPUT.PUT_LINE(coche.id || ' ' || coche.marca || ' ' || coche.modelo || ' ' || coche.anio);
        DBMS_OUTPUT.PUT_LINE('Se han vendido todos los vehiculos azules que estaban disponibles');
        UPDATE coches
        SET estado = 'Vendido'
        WHERE CURRENT OF c_coches;
    
    END LOOP;

END;
/

DECLARE
    CURSOR c_coches IS
        SELECT c.id, c.marca, c.modelo, c.version, v.precio_unitario 
        FROM coches c
        JOIN detalle_venta v ON v.coche_id = c.id;

BEGIN

    DBMS_OUTPUT.PUT_LINE('Coches vendidos: ');

    FOR coche IN c_coches LOOP
    
        DBMS_OUTPUT.PUT_LINE(coche.id || ' ' || coche.marca || ' ' || coche.modelo || ' ' || coche.precio_unitario);
    
    END LOOP;

END;
/

DECLARE
    CURSOR c_coches IS
        SELECT c.id, c.marca, c.modelo, c.version, v.precio_unitario 
        FROM coches c
        JOIN detalle_venta v ON v.coche_id = c.id
        WHERE precio > 20000;

BEGIN

    DBMS_OUTPUT.PUT_LINE('Coches vendidos por mas de 20000€: ');

    FOR coche IN c_coches LOOP
    
        DBMS_OUTPUT.PUT_LINE(coche.id || ' ' || coche.marca || ' ' || coche.modelo || ' ' || coche.precio_unitario);
    
    END LOOP;

END;
/

// USUARIOS

DECLARE
    CURSOR c_usuarios IS
        SELECT id, nombre, rol FROM usuarios;

BEGIN

    DBMS_OUTPUT.PUT_LINE('Usuarios: ');

    FOR usuario IN c_usuarios LOOP
    
        DBMS_OUTPUT.PUT_LINE(usuario.id || ' ' || usuario.nombre || ' ' || usuario.rol);
    
    END LOOP;

END;
/

DECLARE
    CURSOR c_usuarios IS
        SELECT id, nombre, rol FROM usuarios
        WHERE rol LIKE '%Gestor%'
        FOR UPDATE;

BEGIN

    DBMS_OUTPUT.PUT_LINE('Gestores: ');

    FOR usuario IN c_usuarios LOOP
    
        DBMS_OUTPUT.PUT_LINE(usuario.id || ' ' || usuario.nombre || ' ' || usuario.rol);
        DBMS_OUTPUT.PUT_LINE('Se les cambiará la contraseña añadiendo su rol');
        UPDATE usuarios
        SET password_hash = (password_hash || '.gestor')
        WHERE CURRENT OF c_usuarios;
        
    
    END LOOP;

END;
/

DECLARE
    CURSOR c_usuarios IS
        SELECT id, nombre, rol FROM usuarios
        WHERE rol LIKE '%Administrador%';

BEGIN

    DBMS_OUTPUT.PUT_LINE('Administrador: ');

    FOR usuario IN c_usuarios LOOP
    
        DBMS_OUTPUT.PUT_LINE(usuario.id || ' ' || usuario.nombre || ' ' || usuario.rol);
    
    
    END LOOP;

END;
/

DECLARE

    CURSOR c_usuarios IS
        SELECT u.id, u.nombre, COUNT(v.id) AS total_ventas
        FROM usuarios u
        JOIN ventas v ON v.usuario_id = u.id
        GROUP BY u.id, u.nombre
        ORDER BY total_ventas DESC;

    v_ventas_maximas NUMBER;
    v_iterador NUMBER := 1;

BEGIN

    DBMS_OUTPUT.PUT_LINE('Usuarios con más ventas:');

    FOR usuario IN c_usuarios LOOP
    
        IF v_iterador = 1 THEN
        
            v_ventas_maximas := usuario.total_ventas;
            v_iterador := 2;
            
        END IF;
        
        IF usuario.total_ventas = v_ventas_maximas THEN
    
            DBMS_OUTPUT.PUT_LINE('id: ' || usuario.id || ' Nombre: ' || usuario.nombre || ' Ventas: ' || usuario.total_ventas);
            
        END IF;
    
    END LOOP;

END;
/

DECLARE

    CURSOR c_usuarios IS
        SELECT u.id, u.nombre, COUNT(v.id) AS total_ventas
        FROM usuarios u
        JOIN ventas v ON v.usuario_id = u.id
        GROUP BY u.id, u.nombre;


BEGIN

    DBMS_OUTPUT.PUT_LINE('Ventas de cada usuario:');

    FOR usuario IN c_usuarios LOOP
    
        DBMS_OUTPUT.PUT_LINE('id: ' || usuario.id || ' Nombre: ' || usuario.nombre || ' Ventas: ' || usuario.total_ventas);
    
    END LOOP;

END;
/

// VENTAS

DECLARE

    CURSOR c_ventas IS
        SELECT v.id, v.fecha, v.estado, v.total, c.nombre AS cliente
        FROM ventas v
        JOIN clientes c ON c.id = v.cliente_id
        ORDER BY v.id;

BEGIN

    DBMS_OUTPUT.PUT_LINE('Listado de todas las ventas:');

    FOR venta IN c_ventas LOOP
    
        DBMS_OUTPUT.PUT_LINE(
            'ID: ' || venta.id ||
            ' Cliente: ' || venta.cliente ||
            ' Fecha: ' || venta.fecha ||
            ' Estado: ' || venta.estado ||
            ' Total: ' || venta.total
        );
    
    END LOOP;

END;
/

DECLARE

    CURSOR c_venta IS
        SELECT v.id, v.total, c.nombre AS cliente
        FROM ventas v
        JOIN clientes c ON c.id = v.cliente_id
        ORDER BY v.total DESC;

    v_max_total NUMBER := NULL;

BEGIN

    DBMS_OUTPUT.PUT_LINE('Venta más cara:');

    FOR venta IN c_venta LOOP
    
        IF v_max_total IS NULL THEN
            v_max_total := venta.total;
        END IF;

        IF venta.total = v_max_total THEN
        
            DBMS_OUTPUT.PUT_LINE(
                'ID venta: ' || venta.id ||
                ' Cliente: ' || venta.cliente ||
                ' Total: ' || venta.total
            );
        
        END IF;
    
    END LOOP;

END;
/

DECLARE

    v_fecha_limite DATE := TO_DATE('2026-04-05','YYYY-MM-DD');

    CURSOR c_ventas IS
        SELECT v.id, v.fecha, v.estado, v.total, c.nombre AS cliente
        FROM ventas v
        JOIN clientes c ON c.id = v.cliente_id
        WHERE v.fecha > v_fecha_limite
        ORDER BY v.fecha;

BEGIN

    DBMS_OUTPUT.PUT_LINE('Ventas posteriores a ' || TO_CHAR(v_fecha_limite, 'YYYY-MM-DD') || ':');

    FOR venta IN c_ventas LOOP
    
        DBMS_OUTPUT.PUT_LINE(
            'ID: ' || venta.id ||
            ' Cliente: ' || venta.cliente ||
            ' Fecha: ' || TO_CHAR(venta.fecha, 'YYYY-MM-DD') ||
            ' Estado: ' || venta.estado ||
            ' Total: ' || venta.total
        );
    
    END LOOP;

END;
/

DECLARE

    CURSOR c_ventas (v_min_presupuesto NUMBER) IS
        SELECT v.id, v.fecha, v.estado, v.total, c.nombre AS cliente
        FROM ventas v
        JOIN clientes c ON c.id = v.cliente_id
        WHERE v.total > v_min_presupuesto
        ORDER BY v.total DESC;

BEGIN

    DBMS_OUTPUT.PUT_LINE('Ventas con presupuesto mayor a ' || 20000 || ':');

    FOR venta IN c_ventas (20000) LOOP
    
        DBMS_OUTPUT.PUT_LINE(
            'ID: ' || venta.id ||
            ' Cliente: ' || venta.cliente ||
            ' Fecha: ' || TO_CHAR(venta.fecha, 'YYYY-MM-DD') ||
            ' Estado: ' || venta.estado ||
            ' Total: ' || venta.total
        );
    
    END LOOP;

END;
/

DECLARE

    CURSOR c_clientes IS
        SELECT id, nombre, email, direccion
        FROM clientes
        WHERE LOWER(direccion) LIKE '%madrid%'
        ORDER BY nombre;

BEGIN

    DBMS_OUTPUT.PUT_LINE('Clientes que han comprado y son de Madrid:');

    FOR cliente IN c_clientes LOOP
    
        DBMS_OUTPUT.PUT_LINE(
            'ID: ' || cliente.id ||
            ' Nombre: ' || cliente.nombre ||
            ' Email: ' || cliente.email ||
            ' Dirección: ' || cliente.direccion
        );
    
    END LOOP;

END;
/

// DETALLE VENTA

DECLARE
    CURSOR c_detalle IS
        SELECT id, venta_id, coche_id, cantidad, precio_unitario
        FROM detalle_venta;

BEGIN
    FOR d IN c_detalle LOOP
        DBMS_OUTPUT.PUT_LINE(d.id || ' Venta:' || d.venta_id || ' Coche:' || d.coche_id || ' Cantidad:' || d.cantidad || ' Precio:' || d.precio_unitario);
    END LOOP;
END;
/

DECLARE

    CURSOR c_detalle IS
        SELECT d.venta_id, c.marca, c.modelo, d.cantidad, d.precio_unitario
        FROM detalle_venta d
        JOIN coches c ON c.id = d.coche_id;

BEGIN

    FOR d IN c_detalle LOOP
    
        DBMS_OUTPUT.PUT_LINE('Venta: ' || d.venta_id || ' Coche: ' || d.marca || ' ' || d.modelo || ' Cantidad: ' || d.cantidad || ' Total línea: ' || (d.cantidad * d.precio_unitario));
    
    END LOOP;

END;
/

DECLARE

    CURSOR c_detalle IS
        SELECT d.id, d.venta_id, d.coche_id, d.cantidad, d.precio_unitario
        FROM detalle_venta d
        WHERE d.precio_unitario > 20000
        ORDER BY d.precio_unitario DESC;

BEGIN

    DBMS_OUTPUT.PUT_LINE('Detalles de venta con precio mayor a ' || v_precio_min || ':');

    FOR d IN c_detalle LOOP
    
        DBMS_OUTPUT.PUT_LINE('ID: ' || d.id || ' Venta: ' || d.venta_id || ' Coche: ' || d.coche_id || ' Cantidad: ' || d.cantidad || ' Precio: ' || d.precio_unitario);
    
    END LOOP;

END;
/

DECLARE

    CURSOR c_detalle IS
        SELECT d.id, d.venta_id, c.marca, c.modelo, c.cambio, d.cantidad, d.precio_unitario
        FROM detalle_venta d
        JOIN coches c ON c.id = d.coche_id
        WHERE c.cambio = 'Manual'
        ORDER BY d.venta_id;

BEGIN

    DBMS_OUTPUT.PUT_LINE('Detalle de ventas con coches manuales:');

    FOR d IN c_detalle LOOP
    
        DBMS_OUTPUT.PUT_LINE('id detalle: ' || d.id || ' Venta: ' || d.venta_id || ' Coche: ' || d.marca || ' ' || d.modelo || ' Cambio: ' || d.cambio || ' Cantidad: ' || d.cantidad || ' Precio: ' || d.precio_unitario);
    
    END LOOP;

END;
/

DECLARE

    CURSOR c_detalle IS
        SELECT d.id, d.venta_id, c.marca, c.modelo, c.color, d.cantidad, d.precio_unitario
        FROM detalle_venta d
        JOIN coches c ON c.id = d.coche_id
        WHERE c.color = 'Azul'
        ORDER BY d.venta_id;

BEGIN

    DBMS_OUTPUT.PUT_LINE('Detalle de ventas con coches azules:');

    FOR d IN c_detalle LOOP
    
        DBMS_OUTPUT.PUT_LINE('id detalle: ' || d.id || ' Venta: ' || d.venta_id || ' Coche: ' || d.marca || ' ' || d.modelo || ' Color: ' || d.color || ' Cantidad: ' || d.cantidad || ' Precio: ' || d.precio_unitario);
    
    END LOOP;

END;
/

// INTERACCIONES CLIENTES

DECLARE

    CURSOR c_interacciones IS
        SELECT i.id, i.cliente_id, c.nombre cliente, i.usuario_id, u.nombre usuario, i.tipo, i.asunto, i.fecha, i.resultado
        FROM interacciones_cliente i
        JOIN clientes c ON c.id = i.cliente_id
        JOIN usuarios u ON u.id = i.usuario_id
        ORDER BY i.fecha;

BEGIN

    DBMS_OUTPUT.PUT_LINE('Listado de interacciones:');

    FOR i IN c_interacciones LOOP
    
        DBMS_OUTPUT.PUT_LINE('id:' || i.id || ' Cliente:' || i.cliente || ' Usuario:' || i.usuario || ' Tipo:' || i.tipo || ' Asunto:' || i.asunto || ' Fecha:' || TO_CHAR(i.fecha,'YYYY-MM-DD HH24:MI') || ' Resultado:' || i.resultado);
    
    END LOOP;

END;
/

DECLARE

    CURSOR c_interacciones IS
        SELECT i.id, i.cliente_id, c.nombre cliente, i.usuario_id, u.nombre usuario, i.tipo, i.asunto, i.fecha, i.resultado
        FROM interacciones_cliente i
        JOIN clientes c ON c.id = i.cliente_id
        JOIN usuarios u ON u.id = i.usuario_id
        WHERE i.tipo = 'Llamada'
        ORDER BY i.fecha;

BEGIN

    DBMS_OUTPUT.PUT_LINE('Interacciones por llamada:');

    FOR i IN c_interacciones LOOP
    
        DBMS_OUTPUT.PUT_LINE('ID:' || i.id || ' Cliente:' || i.cliente || ' Usuario:' || i.usuario || ' Asunto:' || i.asunto || ' Fecha:' || TO_CHAR(i.fecha,'YYYY-MM-DD HH24:MI') || ' Resultado:' || i.resultado);
    
    END LOOP;

END;
/

DECLARE

    CURSOR c_interacciones IS
        SELECT i.id, c.nombre cliente, c.direccion, u.nombre usuario, i.tipo, i.asunto, i.fecha, i.resultado
        FROM interacciones_cliente i
        JOIN clientes c ON c.id = i.cliente_id
        JOIN usuarios u ON u.id = i.usuario_id
        WHERE c.direccion LIKE '%Sevilla%'
        ORDER BY i.fecha;

BEGIN

    DBMS_OUTPUT.PUT_LINE('Interacciones de clientes de Sevilla:');

    FOR i IN c_interacciones LOOP
    
        DBMS_OUTPUT.PUT_LINE('id:' || i.id || ' Cliente:' || i.cliente || ' Usuario:' || i.usuario || ' Tipo:' || i.tipo || ' Asunto:' || i.asunto || ' Fecha:' || TO_CHAR(i.fecha,'YYYY-MM-DD HH24:MI') || ' Resultado:' || i.resultado);
    
    END LOOP;

END;
/

DECLARE

    v_fecha DATE := TO_DATE('2026-04-01','YYYY-MM-DD');

    CURSOR c_interacciones IS
        SELECT i.id, c.nombre cliente, u.nombre usuario, i.tipo, i.asunto, i.fecha, i.resultado
        FROM interacciones_cliente i
        JOIN clientes c ON c.id = i.cliente_id
        JOIN usuarios u ON u.id = i.usuario_id
        WHERE i.fecha > v_fecha
        ORDER BY i.fecha;

BEGIN

    DBMS_OUTPUT.PUT_LINE('Interacciones posteriores a la fecha indicada:');

    FOR i IN c_interacciones LOOP
    
        DBMS_OUTPUT.PUT_LINE('id:' || i.id || ' Cliente:' || i.cliente || ' Usuario:' || i.usuario || ' Tipo:' || i.tipo || ' Asunto:' || i.asunto || ' Fecha:' || TO_CHAR(i.fecha,'YYYY-MM-DD HH24:MI') || ' Resultado:' || i.resultado);
    
    END LOOP;

END;
/

DECLARE

    CURSOR c_interacciones IS
        SELECT i.id, c.nombre cliente, u.nombre usuario, i.tipo, i.asunto, i.fecha, i.resultado
        FROM interacciones_cliente i
        JOIN clientes c ON c.id = i.cliente_id
        JOIN usuarios u ON u.id = i.usuario_id
        WHERE i.resultado = 'Interesado'
        ORDER BY i.fecha;

BEGIN

    DBMS_OUTPUT.PUT_LINE('Interacciones con resultado Interesado:');

    FOR i IN c_interacciones LOOP
    
        DBMS_OUTPUT.PUT_LINE('id:' || i.id || ' Cliente:' || i.cliente || ' Usuario:' || i.usuario || ' Tipo:' || i.tipo || ' Asunto:' || i.asunto || ' Fecha:' || TO_CHAR(i.fecha,'YYYY-MM-DD HH24:MI') || ' Resultado:' || i.resultado);
    
    END LOOP;

END;
/