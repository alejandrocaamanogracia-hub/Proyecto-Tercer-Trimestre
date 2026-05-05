package com.concesionario.proyectoTercerTrimestre.entities;

public enum RolUsuario {
    ADMINISTRADOR("Administrador"),
    COMERCIAL("Comercial"),
    GESTOR("Gestor");

    private final String valorDb;

    RolUsuario(String valorDb) {
        this.valorDb = valorDb;
    }

    public String getValorDb() {
        return valorDb;
    }

    public static RolUsuario fromDb(String valorDb) {
        for (RolUsuario rol : RolUsuario.values()) {
            if (rol.getValorDb().equalsIgnoreCase(valorDb)) {
                return rol;
            }
        }
        throw new IllegalArgumentException("Rol de usuario no valido: " + valorDb);
    }
}