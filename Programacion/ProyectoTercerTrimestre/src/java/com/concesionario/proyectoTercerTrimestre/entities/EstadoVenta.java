package com.concesionario.proyectoTercerTrimestre.entities;

public enum EstadoVenta {
    PENDIENTE("Pendiente"),
    COMPLETADA("Completada"),
    PRESUPUESTO("Presupuesto"),
    CANCELADA("Cancelada");

    private final String valorDb;

    EstadoVenta(String valorDb) {
        this.valorDb = valorDb;
    }

    public String getValorDb() {
        return valorDb;
    }

    public static EstadoVenta fromDb(String valorDb) {
        for (EstadoVenta estado : EstadoVenta.values()) {
            if (estado.getValorDb().equalsIgnoreCase(valorDb)) {
                return estado;
            }
        }
        throw new IllegalArgumentException("Estado de venta no valido: " + valorDb);
    }
}