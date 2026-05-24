package com.concesionario.proyectoTercerTrimestre.entities;

public class Usuario extends Persona {

    private RolUsuario rol;
    private String passwordHash;

    public Usuario() {
    }

    public Usuario(String nombre, String email, RolUsuario rol, String passwordHash) {
        super(0, nombre, email);
        this.rol = rol;
        this.passwordHash = passwordHash;
    }

    public Usuario(int id, String nombre, String email, RolUsuario rol, String passwordHash) {
        super(id, nombre, email);
        this.rol = rol;
        this.passwordHash = passwordHash;
    }

    @Override
    public String mostrarInformacion() {
        return "Usuario: " + getNombre()
                + " | Email: " + getEmail()
                + " | Rol: " + rol;
    }

    @Override
    public String toString() {
        return "ID: " + getId()
                + "\nNombre: " + getNombre()
                + "\nEmail: " + getEmail()
                + "\nRol: " + rol
                + "\nPassword hash: " + passwordHash;
    }

    public RolUsuario getRol() {
        return rol;
    }

    public void setRol(RolUsuario rol) {
        this.rol = rol;
    }

    public String getPasswordHash() {
        return passwordHash;
    }

    public void setPasswordHash(String passwordHash) {
        this.passwordHash = passwordHash;
    }
}