package com.concesionario.proyectoTercerTrimestre.entities;

public enum TipoCambio {
    MANUAL("Manual"),
    AUTOMATICO("Automatico");

    private final String valorDb;

    TipoCambio(String valorDb) {
        this.valorDb = valorDb;
    }

    public String getValorDb() {
        return valorDb;
    }

    public static TipoCambio fromDb(String valorDb) {
        for (TipoCambio cambio : TipoCambio.values()) {
            if (cambio.getValorDb().equalsIgnoreCase(valorDb)) {
                return cambio;
            }
        }
        throw new IllegalArgumentException("Tipo de cambio no valido: " + valorDb);
    }
}