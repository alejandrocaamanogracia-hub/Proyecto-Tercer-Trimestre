package com.concesionario.proyectoTercerTrimestre.entities;

public class Cliente extends Persona {

    private String telefono;
    private String direccion;

    public Cliente() {
    }

    public Cliente(String nombre, String email, String telefono, String direccion) {
        super(0, nombre, email);
        this.telefono = telefono;
        this.direccion = direccion;
    }

    public Cliente(int id, String nombre, String email, String telefono, String direccion) {
        super(id, nombre, email);
        this.telefono = telefono;
        this.direccion = direccion;
    }

    @Override
    public String mostrarInformacion() {
        return "Cliente: " + getNombre()
                + " | Email: " + getEmail()
                + " | Telefono: " + telefono;
    }

    @Override
    public String toString() {
        return "ID: " + getId()
                + "\nNombre: " + getNombre()
                + "\nEmail: " + getEmail()
                + "\nTelefono: " + telefono
                + "\nDireccion: " + direccion;
    }

    public String getTelefono() {
        return telefono;
    }

    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }

    public String getDireccion() {
        return direccion;
    }

    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }
}