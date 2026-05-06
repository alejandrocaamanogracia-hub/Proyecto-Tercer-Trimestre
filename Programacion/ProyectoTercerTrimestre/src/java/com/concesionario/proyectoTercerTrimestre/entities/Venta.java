package com.concesionario.proyectoTercerTrimestre.entities;

import java.time.LocalDate;

public class Venta {

    private int id;
    private int clienteId;
    private int usuarioId;
    private LocalDate fecha;
    private String estado;
    private double total;

    public Venta() {
    }

    public Venta(int clienteId, int usuarioId, LocalDate fecha, String estado, double total) {
        this.clienteId = clienteId;
        this.usuarioId = usuarioId;
        this.fecha = fecha;
        this.estado = estado;
        this.total = total;
    }

    public Venta(int id, int clienteId, int usuarioId, LocalDate fecha, String estado, double total) {
        this.id = id;
        this.clienteId = clienteId;
        this.usuarioId = usuarioId;
        this.fecha = fecha;
        this.estado = estado;
        this.total = total;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }


    public int getClienteId() {
        return clienteId;
    }

    public void setClienteId(int clienteId) {
        this.clienteId = clienteId;
    }


    public int getUsuarioId() {
        return usuarioId;
    }

    public void setUsuarioId(int usuarioId) {
        this.usuarioId = usuarioId;
    }


    public LocalDate getFecha() {
        return fecha;
    }

    public void setFecha(LocalDate fecha) {
        this.fecha = fecha;
    }


    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }


    public double getTotal() {
        return total;
    }

    public void setTotal(double total) {
        this.total = total;
    }


    @Override
    public String toString() {
        return "Venta{" +
                "id=" + id +
                ", clienteId=" + clienteId +
                ", usuarioId=" + usuarioId +
                ", fecha=" + fecha +
                ", estado='" + estado + '\'' +
                ", total=" + total +
                '}';
    }
}