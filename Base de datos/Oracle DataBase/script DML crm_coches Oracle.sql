DELETE FROM interacciones_cliente;
DELETE FROM detalle_venta;
DELETE FROM ventas;
DELETE FROM coches;
DELETE FROM usuarios;
DELETE FROM clientes;

-- =========================
-- CLIENTES
-- =========================
INSERT INTO clientes (nombre, email, telefono, direccion) VALUES ('Carlos Martínez', 'carlos.martinez@email.com', '600111222', 'Calle Mayor 12, Madrid');
INSERT INTO clientes (nombre, email, telefono, direccion) VALUES ('Laura Sánchez', 'laura.sanchez@email.com', '600333444', 'Avenida Andalucía 45, Sevilla');
INSERT INTO clientes (nombre, email, telefono, direccion) VALUES ('Miguel Torres', 'miguel.torres@email.com', '600555666', 'Calle Valencia 8, Barcelona');
INSERT INTO clientes (nombre, email, telefono, direccion) VALUES ('Ana Gómez', 'ana.gomez@email.com', '600777888', 'Plaza España 3, Zaragoza');
INSERT INTO clientes (nombre, email, telefono, direccion) VALUES ('Javier Ruiz', 'javier.ruiz@email.com', '600999000', 'Calle Real 21, Valencia');
INSERT INTO clientes (nombre, email, telefono, direccion) VALUES ('Hugo Navarro', 'hugo.navarro@email.com', '688888888', 'Calle Fuencarral 22, Madrid');
INSERT INTO clientes (nombre, email, telefono, direccion) VALUES ('Irene Castillo', 'irene.castillo@email.com', '699999999', 'Calle Atocha 90, Madrid');
INSERT INTO clientes (nombre, email, telefono, direccion) VALUES ('Sergio Blanco', 'sergio.blanco@email.com', '610101010', 'Calle Sevilla 12, Málaga');
INSERT INTO clientes (nombre, email, telefono, direccion) VALUES ('Nerea Ramos', 'nerea.ramos@email.com', '611212121', 'Calle Bilbao 5, Bilbao');
INSERT INTO clientes (nombre, email, telefono, direccion) VALUES ('Óscar León', 'oscar.leon@email.com', NULL, 'Calle Gran Vía 120, Madrid');
INSERT INTO clientes (nombre, email, telefono, direccion) VALUES ('Patricia Vidal', 'patricia.vidal@email.com', '622323232', 'Calle Serrano 44, Madrid');
INSERT INTO clientes (nombre, email, telefono, direccion) VALUES ('Diego Herrera', 'diego.herrera@email.com', '633434343', 'Calle Triana 18, Sevilla');
INSERT INTO clientes (nombre, email, telefono, direccion) VALUES ('Clara Ruiz', 'clara.ruiz@gmail.com', '644545454', 'Calle Colón 77, Valencia');

-- =========================
-- USUARIOS
-- =========================
INSERT INTO usuarios (nombre, email, rol, password_hash) VALUES ('Admin Principal', 'admin@crmcoches.com', 'Administrador', 'hash_admin_123');
INSERT INTO usuarios (nombre, email, rol, password_hash) VALUES ('Pedro López', 'pedro.lopez@crmcoches.com', 'Comercial', 'hash_pedro_123');
INSERT INTO usuarios (nombre, email, rol, password_hash) VALUES ('Marta Fernández', 'marta.fernandez@crmcoches.com', 'Comercial', 'hash_marta_123');
INSERT INTO usuarios (nombre, email, rol, password_hash) VALUES ('Sergio Navarro', 'sergio.navarro@crmcoches.com', 'Gestor', 'hash_sergio_123');
INSERT INTO usuarios (nombre, email, rol, password_hash) VALUES ('Lucía Romero', 'lucia.romero@crmcoches.com', 'Comercial', 'hash_lucia_123');
INSERT INTO usuarios (nombre, email, rol, password_hash) VALUES ('Alberto Martín', 'alberto.martin@crmcoches.com', 'Comercial', 'hash_alberto_123');
INSERT INTO usuarios (nombre, email, rol, password_hash) VALUES ('Beatriz Soto', 'beatriz.soto@crmcoches.com', 'Comercial', 'hash_beatriz_123');
INSERT INTO usuarios (nombre, email, rol, password_hash) VALUES ('Daniel Ortiz', 'daniel.ortiz@crmcoches.com', 'Gestor', 'hash_daniel_123');
INSERT INTO usuarios (nombre, email, rol, password_hash) VALUES ('Silvia Campos', 'silvia.campos@crmcoches.com', 'Comercial', 'hash_silvia_123');

-- =========================
-- COCHES
-- =========================
INSERT INTO coches (marca, modelo, version, matricula, bastidor, anio, kilometros, combustible, cambio, color, precio, estado) VALUES ('SEAT', 'León', '1.5 TSI FR', '1234LBD', 'VSSZZZKLZNR001001', 2022, 32000, 'Gasolina', 'Manual', 'Rojo', 18900.00, 'Disponible');
INSERT INTO coches (marca, modelo, version, matricula, bastidor, anio, kilometros, combustible, cambio, color, precio, estado) VALUES ('Volkswagen', 'Golf', '2.0 TDI Life', '5678MJK', 'WVWZZZCDZPW002002', 2023, 18000, 'Diésel', 'Automático', 'Gris', 24500.00, 'Reservado');
INSERT INTO coches (marca, modelo, version, matricula, bastidor, anio, kilometros, combustible, cambio, color, precio, estado) VALUES ('Toyota', 'Corolla', 'Hybrid Active Plus', '9012KLM', 'JTDBR3FE20J003003', 2021, 45000, 'Híbrido', 'Automático', 'Blanco', 19900.00, 'Disponible');
INSERT INTO coches (marca, modelo, version, matricula, bastidor, anio, kilometros, combustible, cambio, color, precio, estado) VALUES ('BMW', 'Serie 3', '320d M Sport', '3456NPR', 'WBA5R710X0F004004', 2020, 68000, 'Diésel', 'Automático', 'Negro', 28900.00, 'Vendido');
INSERT INTO coches (marca, modelo, version, matricula, bastidor, anio, kilometros, combustible, cambio, color, precio, estado) VALUES ('Tesla', 'Model 3', 'Long Range', '7890PQS', '5YJ3E1EBXNF005005', 2022, 27000, 'Eléctrico', 'Automático', 'Azul', 35900.00, 'Vendido');
INSERT INTO coches (marca, modelo, version, matricula, bastidor, anio, kilometros, combustible, cambio, color, precio, estado) VALUES ('Kia', 'Sportage', '1.6 GDi', '4444DDD', 'KNAJX81AA1J444444', 2022, 30000, 'Gasolina', 'Manual', 'Rojo', 23900, 'Disponible');
INSERT INTO coches (marca, modelo, version, matricula, bastidor, anio, kilometros, combustible, cambio, color, precio, estado) VALUES ('Ford', 'Focus', 'ST-Line', '5555EEE', 'WF0PXXGCHP5555555', 2021, 50000, 'Diésel', 'Manual', 'Azul', 17900, 'Disponible');
INSERT INTO coches (marca, modelo, version, matricula, bastidor, anio, kilometros, combustible, cambio, color, precio, estado) VALUES ('Peugeot', '208', 'Allure', '6666FFF', 'VF3CCBHY6KT666666', 2023, 12000, 'Gasolina', 'Automático', 'Blanco', 18900, 'Reservado');
INSERT INTO coches (marca, modelo, version, matricula, bastidor, anio, kilometros, combustible, cambio, color, precio, estado) VALUES ('Renault', 'Clio', 'Zen', '7777GGG', 'VF1RZB004J7777777', 2020, 60000, 'Gasolina', 'Manual', 'Gris', 12900, 'Disponible');

-- =========================
-- VENTAS
-- =========================

INSERT INTO ventas (cliente_id, usuario_id, fecha, estado, total)
VALUES (
    (SELECT id FROM clientes WHERE email = 'carlos.martinez@email.com'),
    (SELECT id FROM usuarios WHERE email = 'pedro.lopez@crmcoches.com'),
    TO_DATE('2026-04-01','YYYY-MM-DD'),
    'Completada',
    28900.00
);

INSERT INTO ventas (cliente_id, usuario_id, fecha, estado, total)
VALUES (
    (SELECT id FROM clientes WHERE email = 'laura.sanchez@email.com'),
    (SELECT id FROM usuarios WHERE email = 'marta.fernandez@crmcoches.com'),
    TO_DATE('2026-04-03','YYYY-MM-DD'),
    'Completada',
    35900.00
);

INSERT INTO ventas (cliente_id, usuario_id, fecha, estado, total)
VALUES (
    (SELECT id FROM clientes WHERE email = 'miguel.torres@email.com'),
    (SELECT id FROM usuarios WHERE email = 'pedro.lopez@crmcoches.com'),
    TO_DATE('2026-04-03','YYYY-MM-DD'),
    'Pendiente',
    24500.00
);

INSERT INTO ventas (cliente_id, usuario_id, fecha, estado, total)
VALUES (
    (SELECT id FROM clientes WHERE email = 'ana.gomez@email.com'),
    (SELECT id FROM usuarios WHERE email = 'lucia.romero@crmcoches.com'),
    TO_DATE('2026-04-08','YYYY-MM-DD'),
    'Presupuesto',
    18900.00
);

INSERT INTO ventas (cliente_id, usuario_id, fecha, estado, total)
VALUES (
    (SELECT id FROM clientes WHERE email = 'javier.ruiz@email.com'),
    (SELECT id FROM usuarios WHERE email = 'marta.fernandez@crmcoches.com'),
    TO_DATE('2026-04-10','YYYY-MM-DD'),
    'Presupuesto',
    19900.00
);

INSERT INTO ventas (cliente_id, usuario_id, fecha, estado, total)
VALUES (
    (SELECT id FROM clientes WHERE email = 'hugo.navarro@email.com'),
    (SELECT id FROM usuarios WHERE email = 'alberto.martin@crmcoches.com'),
    TO_DATE('2026-04-17','YYYY-MM-DD'),
    'Completada',
    23900
);

INSERT INTO ventas (cliente_id, usuario_id, fecha, estado, total)
VALUES (
    (SELECT id FROM clientes WHERE email = 'irene.castillo@email.com'),
    (SELECT id FROM usuarios WHERE email = 'beatriz.soto@crmcoches.com'),
    TO_DATE('2026-04-18','YYYY-MM-DD'),
    'Pendiente',
    17900
);

INSERT INTO ventas (cliente_id, usuario_id, fecha, estado, total)
VALUES (
    (SELECT id FROM clientes WHERE email = 'sergio.blanco@email.com'),
    (SELECT id FROM usuarios WHERE email = 'daniel.ortiz@crmcoches.com'),
    TO_DATE('2026-04-19','YYYY-MM-DD'),
    'Presupuesto',
    18900
);

INSERT INTO ventas (cliente_id, usuario_id, fecha, estado, total)
VALUES (
    (SELECT id FROM clientes WHERE email = 'nerea.ramos@email.com'),
    (SELECT id FROM usuarios WHERE email = 'silvia.campos@crmcoches.com'),
    TO_DATE('2026-04-20','YYYY-MM-DD'),
    'Completada',
    12900
);

INSERT INTO ventas (cliente_id, usuario_id, fecha, estado, total)
VALUES (
    (SELECT id FROM clientes WHERE email = 'oscar.leon@email.com'),
    (SELECT id FROM usuarios WHERE email = 'alberto.martin@crmcoches.com'),
    TO_DATE('2026-04-21','YYYY-MM-DD'),
    'Completada',
    21900
);

-- =========================
-- DETALLE_VENTA
-- =========================
INSERT INTO detalle_venta (venta_id, coche_id, cantidad, precio_unitario) VALUES
(
    (SELECT id FROM ventas WHERE total = 28900.00 AND ROWNUM = 1),
    (SELECT id FROM coches WHERE matricula = '3456NPR'),
    1,
    28900.00
);

INSERT INTO detalle_venta (venta_id, coche_id, cantidad, precio_unitario) VALUES
(
    (SELECT id FROM ventas WHERE total = 35900.00 AND ROWNUM = 1),
    (SELECT id FROM coches WHERE matricula = '7890PQS'),
    1,
    35900.00
);

INSERT INTO detalle_venta (venta_id, coche_id, cantidad, precio_unitario) VALUES
(
    (SELECT id FROM ventas WHERE total = 24500.00 AND ROWNUM = 1),
    (SELECT id FROM coches WHERE matricula = '5678MJK'),
    1,
    24500.00
);

INSERT INTO detalle_venta (venta_id, coche_id, cantidad, precio_unitario) VALUES
(
    (SELECT id FROM ventas WHERE total = 18900.00 AND ROWNUM = 1),
    (SELECT id FROM coches WHERE matricula = '1234LBD'),
    1,
    18900.00
);

INSERT INTO detalle_venta (venta_id, coche_id, cantidad, precio_unitario) VALUES
(
    (SELECT id FROM ventas WHERE total = 19900.00 AND ROWNUM = 1),
    (SELECT id FROM coches WHERE matricula = '9012KLM'),
    1,
    19900.00
);

INSERT INTO detalle_venta (venta_id, coche_id, cantidad, precio_unitario) VALUES
(
    (SELECT id FROM ventas WHERE total = 23900.00 AND ROWNUM = 1),
    (SELECT id FROM coches WHERE precio = 23900.00 AND ROWNUM = 1),
    1,
    23900.00
);

INSERT INTO detalle_venta (venta_id, coche_id, cantidad, precio_unitario) VALUES
(
    (SELECT id FROM ventas WHERE total = 17900.00 AND ROWNUM = 1),
    (SELECT id FROM coches WHERE precio = 17900.00 AND ROWNUM = 1),
    1,
    17900.00
);

INSERT INTO detalle_venta (venta_id, coche_id, cantidad, precio_unitario) VALUES
(
    (SELECT id FROM ventas WHERE total = 18900.00 AND ROWNUM = 1),
    (SELECT id FROM coches WHERE precio = 18900.00 AND ROWNUM = 1),
    1,
    18900.00
);

INSERT INTO detalle_venta (venta_id, coche_id, cantidad, precio_unitario) VALUES
(
    (SELECT id FROM ventas WHERE total = 12900.00 AND ROWNUM = 1),
    (SELECT id FROM coches WHERE precio = 12900.00 AND ROWNUM = 1),
    1,
    12900.00
);



-- =========================
-- INTERACCIONES_CLIENTE
-- =========================

INSERT INTO interacciones_cliente (cliente_id, usuario_id, tipo, fecha, asunto, descripcion, resultado, proxima_accion, fecha_proxima)
VALUES (
    (SELECT id FROM clientes WHERE email = 'carlos.martinez@email.com'),
    (SELECT id FROM usuarios WHERE email = 'pedro.lopez@crmcoches.com'),
    'Llamada',
    TO_TIMESTAMP('2026-03-28 10:30:00','YYYY-MM-DD HH24:MI:SS'),
    'Interés en BMW Serie 3',
    'El cliente pregunta por financiación y garantía del vehículo.',
    'Interesado',
    'Enviar propuesta de financiación',
    TO_TIMESTAMP('2026-03-28 10:30:00','YYYY-MM-DD HH24:MI:SS')
);

INSERT INTO interacciones_cliente (cliente_id, usuario_id, tipo, fecha, asunto, descripcion, resultado, proxima_accion, fecha_proxima)
VALUES (
    (SELECT id FROM clientes WHERE email = 'laura.sanchez@email.com'),
    (SELECT id FROM usuarios WHERE email = 'marta.fernandez@crmcoches.com'),
    'Email',
    TO_TIMESTAMP('2026-03-30 16:15:00','YYYY-MM-DD HH24:MI:SS'),
    'Consulta Tesla Model 3',
    'La clienta solicita ficha técnica y disponibilidad para prueba.',
    'Respondido',
    'Agendar prueba de conducción',
    TO_TIMESTAMP('2026-04-01 11:00:00','YYYY-MM-DD HH24:MI:SS')
);

INSERT INTO interacciones_cliente (cliente_id, usuario_id, tipo, fecha, asunto, descripcion, resultado, proxima_accion, fecha_proxima)
VALUES (
    (SELECT id FROM clientes WHERE email = 'miguel.torres@email.com'),
    (SELECT id FROM usuarios WHERE email = 'pedro.lopez@crmcoches.com'),
    'WhatsApp',
    TO_TIMESTAMP('2026-04-02 09:45:00','YYYY-MM-DD HH24:MI:SS'),
    'Reserva Volkswagen Golf',
    'El cliente confirma interés y solicita reservar el vehículo.',
    'Reservado',
    'Preparar documentación de reserva',
    TO_TIMESTAMP('2026-04-03 10:00:00','YYYY-MM-DD HH24:MI:SS')
);

INSERT INTO interacciones_cliente (cliente_id, usuario_id, tipo, fecha, asunto, descripcion, resultado, proxima_accion, fecha_proxima)
VALUES (
    (SELECT id FROM clientes WHERE email = 'ana.gomez@email.com'),
    (SELECT id FROM usuarios WHERE email = 'lucia.romero@crmcoches.com'),
    'Reunión',
    TO_TIMESTAMP('2026-04-06 18:00:00','YYYY-MM-DD HH24:MI:SS'),
    'Presupuesto SEAT León',
    'La clienta visita el concesionario y prueba el coche.',
    'Pendiente de decisión',
    'Llamar para seguimiento',
    TO_TIMESTAMP('2026-04-09 17:00:00','YYYY-MM-DD HH24:MI:SS')
);

INSERT INTO interacciones_cliente (cliente_id, usuario_id, tipo, fecha, asunto, descripcion, resultado, proxima_accion, fecha_proxima)
VALUES (
    (SELECT id FROM clientes WHERE email = 'javier.ruiz@email.com'),
    (SELECT id FROM usuarios WHERE email = 'marta.fernandez@crmcoches.com'),
    'Llamada',
    TO_TIMESTAMP('2026-04-09 13:20:00','YYYY-MM-DD HH24:MI:SS'),
    'Consulta Toyota Corolla',
    'El cliente pregunta por consumo, mantenimiento y posibilidad de entrega.',
    'Interesado',
    'Enviar presupuesto por email',
    TO_TIMESTAMP('2026-04-11 09:30:00','YYYY-MM-DD HH24:MI:SS')
);

INSERT INTO interacciones_cliente (cliente_id, usuario_id, tipo, fecha, asunto, descripcion, resultado, proxima_accion, fecha_proxima)
VALUES (
    (SELECT id FROM clientes WHERE email = 'hugo.navarro@email.com'),
    (SELECT id FROM usuarios WHERE email = 'alberto.martin@crmcoches.com'),
    'Llamada',
    TO_TIMESTAMP('2026-04-16 10:00:00','YYYY-MM-DD HH24:MI:SS'),
    'Kia Sportage interés',
    'Quiere financiación flexible',
    'Interesado',
    'Enviar oferta',
    TO_TIMESTAMP('2026-04-17 12:00:00','YYYY-MM-DD HH24:MI:SS')
);

INSERT INTO interacciones_cliente (cliente_id, usuario_id, tipo, fecha, asunto, descripcion, resultado, proxima_accion, fecha_proxima)
VALUES (
    (SELECT id FROM clientes WHERE email = 'irene.castillo@email.com'),
    (SELECT id FROM usuarios WHERE email = 'beatriz.soto@crmcoches.com'),
    'Email',
    TO_TIMESTAMP('2026-04-17 11:30:00','YYYY-MM-DD HH24:MI:SS'),
    'Ford Focus info',
    'Solicita catálogo y precio final',
    'Respondido',
    'Seguimiento llamada',
    TO_TIMESTAMP('2026-04-18 09:00:00','YYYY-MM-DD HH24:MI:SS')
);

INSERT INTO interacciones_cliente (cliente_id, usuario_id, tipo, fecha, asunto, descripcion, resultado, proxima_accion, fecha_proxima)
VALUES (
    (SELECT id FROM clientes WHERE email = 'sergio.blanco@email.com'),
    (SELECT id FROM usuarios WHERE email = 'daniel.ortiz@crmcoches.com'),
    'WhatsApp',
    TO_TIMESTAMP('2026-04-18 15:45:00','YYYY-MM-DD HH24:MI:SS'),
    'Peugeot 208 prueba',
    'Quiere probar el vehículo',
    'Pendiente',
    'Agendar prueba',
    TO_TIMESTAMP('2026-04-19 10:00:00','YYYY-MM-DD HH24:MI:SS')
);

INSERT INTO interacciones_cliente (cliente_id, usuario_id, tipo, fecha, asunto, descripcion, resultado, proxima_accion, fecha_proxima)
VALUES (
    (SELECT id FROM clientes WHERE email = 'nerea.ramos@email.com'),
    (SELECT id FROM usuarios WHERE email = 'silvia.campos@crmcoches.com'),
    'Llamada',
    TO_TIMESTAMP('2026-04-19 09:15:00','YYYY-MM-DD HH24:MI:SS'),
    'Renault Clio financiación',
    'Consulta cuotas mensuales',
    'Interesado',
    'Enviar simulación',
    TO_TIMESTAMP('2026-04-20 11:00:00','YYYY-MM-DD HH24:MI:SS')
);

INSERT INTO interacciones_cliente (cliente_id, usuario_id, tipo, fecha, asunto, descripcion, resultado, proxima_accion, fecha_proxima)
VALUES (
    (SELECT id FROM clientes WHERE email = 'oscar.leon@email.com'),
    (SELECT id FROM usuarios WHERE email = 'alberto.martin@crmcoches.com'),
    'Reunión',
    TO_TIMESTAMP('2026-04-20 18:00:00','YYYY-MM-DD HH24:MI:SS'),
    'Compra cerrada',
    'Cliente confirma compra',
    'Cerrado',
    'Entregar vehículo',
    TO_TIMESTAMP('2026-04-21 09:30:00','YYYY-MM-DD HH24:MI:SS')
);