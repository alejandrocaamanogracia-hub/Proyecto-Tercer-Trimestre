package com.concesionario.proyectoTercerTrimestre.entities;

import java.util.Objects;

public class Usuario {

    private int id;
    private String nombre;
    private String email;
    private RolUsuario rolUsuario;
    private String password_hash;

    public Usuario(int id, String nombre, String email, RolUsuario rolUsuario, String password_hash) {
        this.id = id;
        this.nombre = nombre;
        this.email = email;
        this.rolUsuario = rolUsuario;
        this.password_hash = password_hash;
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

    public RolUsuario getRolUsuario() {
        return rolUsuario;
    }

    public void setRolUsuario(RolUsuario rolUsuario) {
        this.rolUsuario = rolUsuario;
    }

    public String getPassword_hash() {
        return password_hash;
    }

    public void setPassword_hash(String password_hash) {
        this.password_hash = password_hash;
    }

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        Usuario usuario = (Usuario) o;
        return id == usuario.id;
    }

    @Override
    public String toString() {
        return "Usuario{" +
                "id=" + id +
                ", nombre='" + nombre + '\'' +
                ", email='" + email + '\'' +
                ", rolUsuario=" + rolUsuario +
                ", password_hash='" + password_hash + '\'' +
                '}';
    }

}
