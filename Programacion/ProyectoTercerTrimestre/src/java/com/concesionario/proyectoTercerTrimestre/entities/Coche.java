package com.concesionario.proyectoTercerTrimestre.entities;

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
    private TipoCambio cambio;
    private String color;
    private double precio;
    private EstadoCoche estado;

    public Coche() {
    }

    public Coche(String marca, String modelo, String version, String matricula,
                 String bastidor, int anio, int kilometros, Combustible combustible,
                 TipoCambio cambio, String color, double precio, EstadoCoche estado) {
        this.marca = marca;
        this.modelo = modelo;
        this.version = version;
        this.matricula = matricula;
        this.bastidor = bastidor;
        this.anio = anio;
        this.kilometros = kilometros;
        this.combustible = combustible;
        this.cambio = cambio;
        this.color = color;
        this.precio = precio;
        this.estado = estado;
    }

    public Coche(int id, String marca, String modelo, String version, String matricula,
                 String bastidor, int anio, int kilometros, Combustible combustible,
                 TipoCambio cambio, String color, double precio, EstadoCoche estado) {
        this.id = id;
        this.marca = marca;
        this.modelo = modelo;
        this.version = version;
        this.matricula = matricula;
        this.bastidor = bastidor;
        this.anio = anio;
        this.kilometros = kilometros;
        this.combustible = combustible;
        this.cambio = cambio;
        this.color = color;
        this.precio = precio;
        this.estado = estado;
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


    public TipoCambio getCambio() {
        return cambio;
    }

    public void setCambio(TipoCambio cambio) {
        this.cambio = cambio;
    }


    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }


    public double getPrecio() {
        return precio;
    }

    public void setPrecio(double precio) {
        this.precio = precio;
    }


    public EstadoCoche getEstado() {
        return estado;
    }

    public void setEstado(EstadoCoche estado) {
        this.estado = estado;
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
                ", cambio=" + cambio +
                ", color='" + color + '\'' +
                ", precio=" + precio +
                ", estado=" + estado +
                '}';
    }
}