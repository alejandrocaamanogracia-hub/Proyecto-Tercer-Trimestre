package com.concesionario.proyectoTercerTrimestre.entities;

public class DetalleVenta {

    private int id;
    private int ventaId;
    private int cocheId;
    private int cantidad;

    public DetalleVenta() {
    }

    public DetalleVenta(int ventaId, int cocheId, int cantidad) {
        this.ventaId = ventaId;
        this.cocheId = cocheId;
        this.cantidad = cantidad;
    }

    public DetalleVenta(int id, int ventaId, int cocheId, int cantidad) {
        this.id = id;
        this.ventaId = ventaId;
        this.cocheId = cocheId;
        this.cantidad = cantidad;
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


    public int getCantidad() {
        return cantidad;
    }

    public void setCantidad(int cantidad) {
        this.cantidad = cantidad;
    }


    @Override
    public String toString() {
        return "DetalleVenta{" +
                "id=" + id +
                ", ventaId=" + ventaId +
                ", cocheId=" + cocheId +
                ", cantidad=" + cantidad +
                '}';
    }
}