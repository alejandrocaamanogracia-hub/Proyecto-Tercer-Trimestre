package com.concesionario.proyectoTercerTrimestre.entities;

public class DetalleVenta {

    private int id;
    private int ventaId;
    private int cocheId;
    private double precioFinal;
    private double descuento;

    public DetalleVenta() {
    }

    public DetalleVenta(int ventaId, int cocheId, double precioFinal, double descuento) {
        this.ventaId = ventaId;
        this.cocheId = cocheId;
        this.precioFinal = precioFinal;
        this.descuento = descuento;
    }

    public DetalleVenta(int id, int ventaId, int cocheId, double precioFinal, double descuento) {
        this.id = id;
        this.ventaId = ventaId;
        this.cocheId = cocheId;
        this.precioFinal = precioFinal;
        this.descuento = descuento;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }


    public int getVentaId() {
        return ventaId;
    }

    public void setVentaId(int ventaId) {
        this.ventaId = ventaId;
    }


    public int getCocheId() {
        return cocheId;
    }

    public void setCocheId(int cocheId) {
        this.cocheId = cocheId;
    }


    public double getPrecioFinal() {
        return precioFinal;
    }

    public void setPrecioFinal(double precioFinal) {
        this.precioFinal = precioFinal;
    }


    public double getDescuento() {
        return descuento;
    }

    public void setDescuento(double descuento) {
        this.descuento = descuento;
    }


    @Override
    public String toString() {
        return "DetalleVenta{" +
                "id=" + id +
                ", ventaId=" + ventaId +
                ", cocheId=" + cocheId +
                ", precioFinal=" + precioFinal +
                ", descuento=" + descuento +
                '}';
    }
}