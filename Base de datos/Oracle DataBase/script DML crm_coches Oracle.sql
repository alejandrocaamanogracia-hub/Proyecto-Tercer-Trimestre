-- =========================
-- CLIENTES
-- =========================
INSERT INTO clientes (nombre, email, telefono, direccion) VALUES ('Carlos Martínez', 'carlos.martinez@email.com', '600111222', 'Calle Mayor 12, Madrid');
INSERT INTO clientes (nombre, email, telefono, direccion) VALUES ('Laura Sánchez', 'laura.sanchez@email.com', '600333444', 'Avenida Andalucía 45, Sevilla');
INSERT INTO clientes (nombre, email, telefono, direccion) VALUES ('Miguel Torres', 'miguel.torres@email.com', '600555666', 'Calle Valencia 8, Barcelona');
INSERT INTO clientes (nombre, email, telefono, direccion) VALUES ('Ana Gómez', 'ana.gomez@email.com', '600777888', 'Plaza España 3, Zaragoza');
INSERT INTO clientes (nombre, email, telefono, direccion) VALUES ('Javier Ruiz', 'javier.ruiz@email.com', '600999000', 'Calle Real 21, Valencia');

-- =========================
-- USUARIOS
-- =========================
INSERT INTO usuarios (nombre, email, rol, password_hash) VALUES ('Admin Principal', 'admin@crmcoches.com', 'Administrador', 'hash_admin_123');
INSERT INTO usuarios (nombre, email, rol, password_hash) VALUES ('Pedro López', 'pedro.lopez@crmcoches.com', 'Comercial', 'hash_pedro_123');
INSERT INTO usuarios (nombre, email, rol, password_hash) VALUES ('Marta Fernández', 'marta.fernandez@crmcoches.com', 'Comercial', 'hash_marta_123');
INSERT INTO usuarios (nombre, email, rol, password_hash) VALUES ('Sergio Navarro', 'sergio.navarro@crmcoches.com', 'Gestor', 'hash_sergio_123');
INSERT INTO usuarios (nombre, email, rol, password_hash) VALUES ('Lucía Romero', 'lucia.romero@crmcoches.com', 'Comercial', 'hash_lucia_123');

-- =========================
-- COCHES
-- =========================
INSERT INTO coches (marca, modelo, version, matricula, bastidor, anio, kilometros, combustible, cambio, color, precio, estado) VALUES ('SEAT', 'León', '1.5 TSI FR', '1234LBD', 'VSSZZZKLZNR001001', 2022, 32000, 'Gasolina', 'Manual', 'Rojo', 18900.00, 'Disponible');
INSERT INTO coches (marca, modelo, version, matricula, bastidor, anio, kilometros, combustible, cambio, color, precio, estado) VALUES ('Volkswagen', 'Golf', '2.0 TDI Life', '5678MJK', 'WVWZZZCDZPW002002', 2023, 18000, 'Diésel', 'Automático', 'Gris', 24500.00, 'Reservado');
INSERT INTO coches (marca, modelo, version, matricula, bastidor, anio, kilometros, combustible, cambio, color, precio, estado) VALUES ('Toyota', 'Corolla', 'Hybrid Active Plus', '9012KLM', 'JTDBR3FE20J003003', 2021, 45000, 'Híbrido', 'Automático', 'Blanco', 19900.00, 'Disponible');
INSERT INTO coches (marca, modelo, version, matricula, bastidor, anio, kilometros, combustible, cambio, color, precio, estado) VALUES ('BMW', 'Serie 3', '320d M Sport', '3456NPR', 'WBA5R710X0F004004', 2020, 68000, 'Diésel', 'Automático', 'Negro', 28900.00, 'Vendido');
INSERT INTO coches (marca, modelo, version, matricula, bastidor, anio, kilometros, combustible, cambio, color, precio, estado) VALUES ('Tesla', 'Model 3', 'Long Range', '7890PQS', '5YJ3E1EBXNF005005', 2022, 27000, 'Eléctrico', 'Automático', 'Azul', 35900.00, 'Vendido');

-- =========================
-- VENTAS
-- =========================
INSERT INTO ventas (cliente_id, usuario_id, fecha, estado, total) VALUES (1, 2, TO_DATE('2026-04-01','YYYY-MM-DD'), 'Completada', 28900.00);
INSERT INTO ventas (cliente_id, usuario_id, fecha, estado, total) VALUES (2, 3, TO_DATE('2026-04-03','YYYY-MM-DD'), 'Completada', 35900.00);
INSERT INTO ventas (cliente_id, usuario_id, fecha, estado, total) VALUES (3, 2, TO_DATE('2026-04-03','YYYY-MM-DD'), 'Pendiente', 24500.00);
INSERT INTO ventas (cliente_id, usuario_id, fecha, estado, total) VALUES (4, 5, TO_DATE('2026-04-08','YYYY-MM-DD'), 'Presupuesto', 18900.00);
INSERT INTO ventas (cliente_id, usuario_id, fecha, estado, total) VALUES (5, 3, TO_DATE('2026-04-10','YYYY-MM-DD'), 'Presupuesto', 19900.00);

-- =========================
-- DETALLE_VENTA
-- =========================
INSERT INTO detalle_venta (venta_id, coche_id, cantidad, precio_unitario) VALUES (1, 4, 1, 28900.00);
INSERT INTO detalle_venta (venta_id, coche_id, cantidad, precio_unitario) VALUES (2, 5, 1, 35900.00);
INSERT INTO detalle_venta (venta_id, coche_id, cantidad, precio_unitario) VALUES (3, 2, 1, 24500.00);
INSERT INTO detalle_venta (venta_id, coche_id, cantidad, precio_unitario) VALUES (4, 1, 1, 18900.00);
INSERT INTO detalle_venta (venta_id, coche_id, cantidad, precio_unitario) VALUES (5, 3, 1, 19900.00);

-- =========================
-- INTERACCIONES_CLIENTE
-- =========================
INSERT INTO interacciones_cliente (cliente_id, usuario_id, tipo, fecha, asunto, descripcion, resultado, proxima_accion, fecha_proxima)  VALUES
(1, 2, 'Llamada', TO_TIMESTAMP('2026-03-28 10:30:00','YYYY-MM-DD HH24:MI:SS'), 'Interés en BMW Serie 3', 
 'El cliente pregunta por financiación y garantía del vehículo.', 
 'Interesado', 'Enviar propuesta de financiación', TO_TIMESTAMP('2026-03-28 10:30:00','YYYY-MM-DD HH24:MI:SS'));
 
 INSERT INTO interacciones_cliente (cliente_id, usuario_id, tipo, fecha, asunto, descripcion, resultado, proxima_accion, fecha_proxima)  VALUES
(2, 3, 'Email', TO_TIMESTAMP('2026-03-30 16:15:00','YYYY-MM-DD HH24:MI:SS'), 'Consulta Tesla Model 3', 
 'La clienta solicita ficha técnica y disponibilidad para prueba.', 
 'Respondido', 'Agendar prueba de conducción', TO_TIMESTAMP('2026-04-01 11:00:00','YYYY-MM-DD HH24:MI:SS'));

INSERT INTO interacciones_cliente (cliente_id, usuario_id, tipo, fecha, asunto, descripcion, resultado, proxima_accion, fecha_proxima)  VALUES
(3, 2, 'WhatsApp', TO_TIMESTAMP('2026-04-02 09:45:00','YYYY-MM-DD HH24:MI:SS'), 'Reserva Volkswagen Golf', 
 'El cliente confirma interés y solicita reservar el vehículo.', 
 'Reservado', 'Preparar documentación de reserva', TO_TIMESTAMP('2026-04-03 10:00:00','YYYY-MM-DD HH24:MI:SS'));

INSERT INTO interacciones_cliente (cliente_id, usuario_id, tipo, fecha, asunto, descripcion, resultado, proxima_accion, fecha_proxima)  VALUES
(4, 5, 'Reunión', TO_TIMESTAMP('2026-04-06 18:00:00','YYYY-MM-DD HH24:MI:SS'), 'Presupuesto SEAT León', 
 'La clienta visita el concesionario y prueba el coche.', 
 'Pendiente de decisión', 'Llamar para seguimiento', TO_TIMESTAMP('2026-04-09 17:00:00','YYYY-MM-DD HH24:MI:SS'));

INSERT INTO interacciones_cliente (cliente_id, usuario_id, tipo, fecha, asunto, descripcion, resultado, proxima_accion, fecha_proxima)  VALUES
(5, 3, 'Llamada', TO_TIMESTAMP('2026-04-09 13:20:00','YYYY-MM-DD HH24:MI:SS'), 'Consulta Toyota Corolla', 
 'El cliente pregunta por consumo, mantenimiento y posibilidad de entrega.', 
 'Interesado', 'Enviar presupuesto por email', TO_TIMESTAMP('2026-04-11 09:30:00','YYYY-MM-DD HH24:MI:SS'));