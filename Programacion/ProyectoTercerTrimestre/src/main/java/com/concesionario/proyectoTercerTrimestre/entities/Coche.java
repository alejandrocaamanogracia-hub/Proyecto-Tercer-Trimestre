package com.concesionario.proyectoTercerTrimestre.entities;

import java.util.Objects;

public class Coche {

    private int id;
    private String marca;
    private String modelo;
    private String version;
    private String matricula;
    private String bastidor;
    private int anio;
    private int kilometros;
    private Combustible combustible;
    private TipoCambio tipoCambio;
    private String color;
    private int precio;
    private EstadoCoche estadoCoche;

    public Coche(int id, String marca, String modelo, String version, String matricula, String bastidor, int anio, int kilometros, Combustible combustible, TipoCambio tipoCambio, String color, int precio, EstadoCoche estadoCoche) {
        this.id = id;
        this.marca = marca;
        this.modelo = modelo;
        this.version = version;
        this.matricula = matricula;
        this.bastidor = bastidor;
        this.anio = anio;
        this.kilometros = kilometros;
        this.combustible = combustible;
        this.tipoCambio = tipoCambio;
        this.color = color;
        this.precio = precio;
        this.estadoCoche = estadoCoche;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getMarca() {
        return marca;
    }

    public void setMarca(String marca) {
        this.marca = marca;
    }

    public String getModelo() {
        return modelo;
    }

    public void setModelo(String modelo) {
        this.modelo = modelo;
    }

    public String getVersion() {
        return version;
    }

    public void setVersion(String version) {
        this.version = version;
    }

    public String getMatricula() {
        return matricula;
    }

    public void setMatricula(String matricula) {
        this.matricula = matricula;
    }

    public String getBastidor() {
        return bastidor;
    }

    public void setBastidor(String bastidor) {
        this.bastidor = bastidor;
    }

    public int getAnio() {
        return anio;
    }

    public void setAnio(int anio) {
        this.anio = anio;
    }

    public int getKilometros() {
        return kilometros;
    }

    public void setKilometros(int kilometros) {
        this.kilometros = kilometros;
    }

    public Combustible getCombustible() {
        return combustible;
    }

    public void setCombustible(Combustible combustible) {
        this.combustible = combustible;
    }

    public TipoCambio getTipoCambio() {
        return tipoCambio;
    }

    public void setTipoCambio(TipoCambio tipoCambio) {
        this.tipoCambio = tipoCambio;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public int getPrecio() {
        return precio;
    }

    public void setPrecio(int precio) {
        this.precio = precio;
    }

    public EstadoCoche getEstadoCoche() {
        return estadoCoche;
    }

    public void setEstadoCoche(EstadoCoche estadoCoche) {
        this.estadoCoche = estadoCoche;
    }

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        Coche coche = (Coche) o;
        return id == coche.id;
    }

    @Override
    public String toString() {
        return "Coche{" +
                "id=" + id +
                ", marca='" + marca + '\'' +
                ", modelo='" + modelo + '\'' +
                ", version='" + version + '\'' +
                ", matricula='" + matricula + '\'' +
                ", bastidor='" + bastidor + '\'' +
                ", anio=" + anio +
                ", kilometros=" + kilometros +
                ", combustible=" + combustible +
                ", tipoCambio=" + tipoCambio +
                ", color='" + color + '\'' +
                ", precio=" + precio +
                ", estadoCoche=" + estadoCoche +
                '}';
    }

}
