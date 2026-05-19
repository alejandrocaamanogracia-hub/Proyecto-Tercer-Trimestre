CREATE DATABASE IF NOT EXISTS crm_coches
CHARACTER SET utf8mb4
COLLATE utf8mb4_general_ci;

USE crm_coches;

-- =========================
-- TABLA CLIENTES
-- =========================
CREATE TABLE clientes (
    id INT AUTO_INCREMENT PRIMARY KEY,
    nombre VARCHAR(100) NOT NULL,
    email VARCHAR(150) UNIQUE,
    telefono VARCHAR(20) NOT NULL,
    direccion VARCHAR(255)
);

-- =========================
-- TABLA USUARIOS
-- =========================
CREATE TABLE usuarios (
    id INT AUTO_INCREMENT PRIMARY KEY,
    nombre VARCHAR(100) NOT NULL,
    email VARCHAR(150) NOT NULL UNIQUE,
    rol VARCHAR(50) NOT NULL,
    password_hash VARCHAR(255) NOT NULL
);

-- =========================
-- TABLA COCHES
-- =========================
CREATE TABLE coches (
    id INT AUTO_INCREMENT PRIMARY KEY,
    marca VARCHAR(100) NOT NULL,
    modelo VARCHAR(100) NOT NULL,
    version VARCHAR(100),
    matricula VARCHAR(20) UNIQUE,
    bastidor VARCHAR(50) NOT NULL UNIQUE,
    anio INT,
    kilometros INT DEFAULT 0,
    combustible VARCHAR(50),
    cambio VARCHAR(50),
    color VARCHAR(50),
    precio DECIMAL(10,2) NOT NULL,
    estado VARCHAR(50) NOT NULL DEFAULT 'Disponible'
);

-- =========================
-- TABLA VENTAS
-- =========================
CREATE TABLE ventas (
    id INT AUTO_INCREMENT PRIMARY KEY,
    cliente_id INT NOT NULL,
    usuario_id INT NOT NULL,
    fecha DATE NOT NULL,
    estado VARCHAR(50) NOT NULL,
    total DECIMAL(10,2) NOT NULL DEFAULT 0.00,

    CONSTRAINT fk_ventas_clientes
        FOREIGN KEY (cliente_id)
        REFERENCES clientes(id)
        ON DELETE RESTRICT
        ON UPDATE CASCADE,

    CONSTRAINT fk_ventas_usuarios
        FOREIGN KEY (usuario_id)
        REFERENCES usuarios(id)
        ON DELETE RESTRICT
        ON UPDATE CASCADE
);

-- =========================
-- TABLA DETALLE_VENTA
-- =========================
CREATE TABLE detalle_venta (
    id INT AUTO_INCREMENT PRIMARY KEY,
    venta_id INT NOT NULL,
    coche_id INT NOT NULL,
    precio_final DECIMAL(10,2) NOT NULL,
    descuento DECIMAL(10,2) NOT NULL DEFAULT 0.00,

    CONSTRAINT fk_detalle_venta_ventas
        FOREIGN KEY (venta_id)
        REFERENCES ventas(id)
        ON DELETE CASCADE
        ON UPDATE CASCADE,

    CONSTRAINT fk_detalle_venta_coches
        FOREIGN KEY (coche_id)
        REFERENCES coches(id)
        ON DELETE RESTRICT
        ON UPDATE CASCADE,

    CONSTRAINT uk_detalle_venta_venta_coche
        UNIQUE (venta_id, coche_id),

    CONSTRAINT uk_detalle_venta_coche
        UNIQUE (coche_id)
);

CREATE TABLE interacciones_cliente (
    id INT AUTO_INCREMENT PRIMARY KEY,
    cliente_id INT NOT NULL,
    usuario_id INT NOT NULL,
    tipo VARCHAR(50) NOT NULL,
    fecha DATETIME NOT NULL,
    asunto VARCHAR(150) NOT NULL,
    descripcion TEXT,
    resultado VARCHAR(100),
    proxima_accion VARCHAR(150),
    fecha_proxima DATETIME,

    CONSTRAINT fk_interacciones_clientes
        FOREIGN KEY (cliente_id)
        REFERENCES clientes(id)
        ON DELETE CASCADE
        ON UPDATE CASCADE,

    CONSTRAINT fk_interacciones_usuarios
        FOREIGN KEY (usuario_id)
        REFERENCES usuarios(id)
        ON DELETE RESTRICT
        ON UPDATE CASCADE
);

-- =========================
-- VALIDACIONES
-- =========================

ALTER TABLE coches
ADD CONSTRAINT chk_coches_precio
CHECK (precio >= 0);

ALTER TABLE coches
ADD CONSTRAINT chk_coches_kilometros
CHECK (kilometros >= 0);

ALTER TABLE coches
ADD CONSTRAINT chk_coches_anio
CHECK (anio >= 1900);

ALTER TABLE detalle_venta
ADD CONSTRAINT chk_detalle_precio_final
CHECK (precio_final >= 0);

ALTER TABLE detalle_venta
ADD CONSTRAINT chk_detalle_descuento
CHECK (descuento >= 0);

ALTER TABLE ventas
ADD CONSTRAINT chk_ventas_total
CHECK (total >= 0);