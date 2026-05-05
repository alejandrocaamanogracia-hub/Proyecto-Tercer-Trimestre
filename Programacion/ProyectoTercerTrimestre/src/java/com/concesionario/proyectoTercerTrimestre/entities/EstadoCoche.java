package com.concesionario.proyectoTercerTrimestre.entities;

public enum EstadoCoche {
    DISPONIBLE("Disponible"),
    RESERVADO("Reservado"),
    VENDIDO("Vendido");

    private final String valorDb;

    EstadoCoche(String valorDb) {
        this.valorDb = valorDb;
    }

    public String getValorDb() {
        return valorDb;
    }

    public static EstadoCoche fromDb(String valorDb) {
        for (EstadoCoche estado : EstadoCoche.values()) {
            if (estado.getValorDb().equalsIgnoreCase(valorDb)) {
                return estado;
            }
        }
        throw new IllegalArgumentException("Estado de coche no valido: " + valorDb);
    }
}