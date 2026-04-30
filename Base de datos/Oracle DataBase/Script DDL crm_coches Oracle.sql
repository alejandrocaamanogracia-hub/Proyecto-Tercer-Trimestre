
-- =========================
-- TABLA CLIENTES
-- =========================
CREATE TABLE clientes (
    id NUMBER GENERATED ALWAYS AS IDENTITY PRIMARY KEY,
    nombre VARCHAR2(100) NOT NULL,
    email VARCHAR2(150) NOT NULL UNIQUE,
    telefono VARCHAR2(20),
    direccion VARCHAR2(255)
);

-- =========================
-- TABLA USUARIOS
-- =========================
CREATE TABLE usuarios (
    id NUMBER GENERATED ALWAYS AS IDENTITY PRIMARY KEY,
    nombre VARCHAR2(100) NOT NULL,
    email VARCHAR2(150) NOT NULL UNIQUE,
    rol VARCHAR2(50) NOT NULL,
    password_hash VARCHAR2(255) NOT NULL
);

-- =========================
-- TABLA COCHES
-- =========================
CREATE TABLE coches (
    id NUMBER GENERATED ALWAYS AS IDENTITY PRIMARY KEY,
    marca VARCHAR2(100) NOT NULL,
    modelo VARCHAR2(100) NOT NULL,
    version VARCHAR2(100),
    matricula VARCHAR2(20) UNIQUE,
    bastidor VARCHAR2(50) UNIQUE,
    anio NUMBER,
    kilometros NUMBER DEFAULT 0,
    combustible VARCHAR2(50),
    cambio VARCHAR2(50),
    color VARCHAR2(50),
    precio NUMBER(10,2) NOT NULL,
    estado VARCHAR2(50) DEFAULT 'Disponible' NOT NULL
);

-- =========================
-- TABLA VENTAS
-- =========================
CREATE TABLE ventas (
    id NUMBER GENERATED ALWAYS AS IDENTITY PRIMARY KEY,
    cliente_id NUMBER NOT NULL,
    usuario_id NUMBER NOT NULL,
    fecha DATE NOT NULL,
    estado VARCHAR2(50) NOT NULL,
    total NUMBER(10,2) DEFAULT 0 NOT NULL ,

    CONSTRAINT fk_ventas_clientes
        FOREIGN KEY (cliente_id)
        REFERENCES clientes(id),

    CONSTRAINT fk_ventas_usuarios
        FOREIGN KEY (usuario_id)
        REFERENCES usuarios(id)
);

-- =========================
-- TABLA DETALLE_VENTA
-- =========================
CREATE TABLE detalle_venta (
    id NUMBER GENERATED ALWAYS AS IDENTITY PRIMARY KEY,
    venta_id NUMBER NOT NULL,
    coche_id NUMBER NOT NULL,
    cantidad NUMBER DEFAULT 1 NOT NULL,
    precio_unitario NUMBER(10,2) NOT NULL,

    CONSTRAINT fk_detalle_venta_ventas
        FOREIGN KEY (venta_id)
        REFERENCES ventas(id)
        ON DELETE CASCADE,

    CONSTRAINT fk_detalle_venta_coches
        FOREIGN KEY (coche_id)
        REFERENCES coches(id)
);

-- =========================
-- TABLA INTERACCIONES_CLIENTE
-- =========================
CREATE TABLE interacciones_cliente (
    id NUMBER GENERATED ALWAYS AS IDENTITY PRIMARY KEY,
    cliente_id NUMBER NOT NULL,
    usuario_id NUMBER NOT NULL,
    tipo VARCHAR2(50) NOT NULL,
    fecha TIMESTAMP NOT NULL,
    asunto VARCHAR2(150),
    descripcion VARCHAR2(200),
    resultado VARCHAR2(100),
    proxima_accion VARCHAR2(150),
    fecha_proxima TIMESTAMP,

    CONSTRAINT fk_interacciones_clientes
        FOREIGN KEY (cliente_id)
        REFERENCES clientes(id)
        ON DELETE CASCADE,

    CONSTRAINT fk_interacciones_usuarios
        FOREIGN KEY (usuario_id)
        REFERENCES usuarios(id)
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
ADD CONSTRAINT chk_detalle_cantidad
CHECK (cantidad > 0);

ALTER TABLE detalle_venta
ADD CONSTRAINT chk_detalle_precio_unitario
CHECK (precio_unitario >= 0);

ALTER TABLE ventas
ADD CONSTRAINT chk_ventas_total
CHECK (total >= 0);

-- =========================
-- INDICES CLAVES FORÁNEAS
-- =========================

CREATE INDEX idx_ventas_cliente ON ventas(cliente_id);
CREATE INDEX idx_ventas_usuario ON ventas(usuario_id);

CREATE INDEX idx_detalle_venta ON detalle_venta(venta_id);
CREATE INDEX idx_detalle_coche ON detalle_venta(coche_id);

CREATE INDEX idx_interacciones_cliente ON interacciones_cliente(cliente_id);
CREATE INDEX idx_interacciones_usuario ON interacciones_cliente(usuario_id);