package com.concesionario.proyectoTercerTrimestre.entities;

public enum TipoInteraccion {
    LLAMADA("Llamada"),
    EMAIL("Email"),
    WHATSAPP("WhatsApp"),
    REUNION("Reunión"),
    VISITA("Visita"),
    OTRO("Otro");

    private final String valorDb;

    TipoInteraccion(String valorDb) {
        this.valorDb = valorDb;
    }

    public String getValorDb() {
        return valorDb;
    }

    public static TipoInteraccion fromDb(String valorDb) {
        for (TipoInteraccion tipo : TipoInteraccion.values()) {
            if (tipo.getValorDb().equalsIgnoreCase(valorDb)) {
                return tipo;
            }
        }
        throw new IllegalArgumentException("Tipo de interaccion no valido: " + valorDb);
    }
}