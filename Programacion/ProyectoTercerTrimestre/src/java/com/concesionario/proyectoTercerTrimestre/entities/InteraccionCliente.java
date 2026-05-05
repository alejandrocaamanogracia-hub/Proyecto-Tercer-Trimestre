package com.concesionario.proyectoTercerTrimestre.entities;

import java.time.LocalDateTime;

public class InteraccionCliente {

    private int id;
    private int clienteId;
    private int usuarioId;
    private String tipo;
    private LocalDateTime fecha;
    private String asunto;
    private String descripcion;
    private String resultado;
    private String proximaAccion;
    private LocalDateTime fechaProxima;

    public InteraccionCliente() {
    }

    public InteraccionCliente(int clienteId, int usuarioId, String tipo, LocalDateTime fecha,
                              String asunto, String descripcion, String resultado,
                              String proximaAccion, LocalDateTime fechaProxima) {
        this.clienteId = clienteId;
        this.usuarioId = usuarioId;
        this.tipo = tipo;
        this.fecha = fecha;
        this.asunto = asunto;
        this.descripcion = descripcion;
        this.resultado = resultado;
        this.proximaAccion = proximaAccion;
        this.fechaProxima = fechaProxima;
    }

    public InteraccionCliente(int id, int clienteId, int usuarioId, String tipo, LocalDateTime fecha,
                              String asunto, String descripcion, String resultado,
                              String proximaAccion, LocalDateTime fechaProxima) {
        this.id = id;
        this.clienteId = clienteId;
        this.usuarioId = usuarioId;
        this.tipo = tipo;
        this.fecha = fecha;
        this.asunto = asunto;
        this.descripcion = descripcion;
        this.resultado = resultado;
        this.proximaAccion = proximaAccion;
        this.fechaProxima = fechaProxima;
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


    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }


    public LocalDateTime getFecha() {
        return fecha;
    }

    public void setFecha(LocalDateTime fecha) {
        this.fecha = fecha;
    }


    public String getAsunto() {
        return asunto;
    }

    public void setAsunto(String asunto) {
        this.asunto = asunto;
    }


    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }


    public String getResultado() {
        return resultado;
    }

    public void setResultado(String resultado) {
        this.resultado = resultado;
    }


    public String getProximaAccion() {
        return proximaAccion;
    }

    public void setProximaAccion(String proximaAccion) {
        this.proximaAccion = proximaAccion;
    }


    public LocalDateTime getFechaProxima() {
        return fechaProxima;
    }

    public void setFechaProxima(LocalDateTime fechaProxima) {
        this.fechaProxima = fechaProxima;
    }


    @Override
    public String toString() {
        return "InteraccionCliente{" +
                "id=" + id +
                ", clienteId=" + clienteId +
                ", usuarioId=" + usuarioId +
                ", tipo='" + tipo + '\'' +
                ", fecha=" + fecha +
                ", asunto='" + asunto + '\'' +
                ", descripcion='" + descripcion + '\'' +
                ", resultado='" + resultado + '\'' +
                ", proximaAccion='" + proximaAccion + '\'' +
                ", fechaProxima=" + fechaProxima +
                '}';
    }
}