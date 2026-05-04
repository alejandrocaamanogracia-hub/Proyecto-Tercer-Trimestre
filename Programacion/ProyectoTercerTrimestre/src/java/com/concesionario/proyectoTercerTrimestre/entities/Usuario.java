package com.concesionario.proyectoTercerTrimestre.entities;

public class Usuario {

    private int id;
    private String nombre;
    private String email;
    private String rol;
    private String passwordHash;

    public Usuario() {
    }

    public Usuario(String nombre, String email, String rol, String passwordHash) {
        this.nombre = nombre;
        this.email = email;
        this.rol = rol;
        this.passwordHash = passwordHash;
    }

    public Usuario(int id, String nombre, String email, String rol, String passwordHash) {
        this.id = id;
        this.nombre = nombre;
        this.email = email;
        this.rol = rol;
        this.passwordHash = passwordHash;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }


    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }


    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }


    public String getRol() {
        return rol;
    }

    public void setRol(String rol) {
        this.rol = rol;
    }


    public String getPasswordHash() {
        return passwordHash;
    }

    public void setPasswordHash(String passwordHash) {
        this.passwordHash = passwordHash;
    }


    @Override
    public String toString() {
        return "Usuario{" +
                "id=" + id +
                ", nombre='" + nombre + '\'' +
                ", email='" + email + '\'' +
                ", rol='" + rol + '\'' +
                ", passwordHash='" + passwordHash + '\'' +
                '}';
    }
}