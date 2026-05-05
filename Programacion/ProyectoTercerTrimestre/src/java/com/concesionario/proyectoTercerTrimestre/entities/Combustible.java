package com.concesionario.proyectoTercerTrimestre.entities;

public enum Combustible {
    GASOLINA("Gasolina"),
    DIESEL("Diesel"),
    HIBRIDO("Hibrido"),
    ELECTRICO("Electrico"),
    GLP("GLP");

    private final String valorDb;

    Combustible(String valorDb) {
        this.valorDb = valorDb;
    }

    public String getValorDb() {
        return valorDb;
    }

    public static Combustible fromDb(String valorDb) {
        for (Combustible combustible : Combustible.values()) {
            if (combustible.getValorDb().equalsIgnoreCase(valorDb)) {
                return combustible;
            }
        }
        throw new IllegalArgumentException("Combustible no valido: " + valorDb);
    }
}