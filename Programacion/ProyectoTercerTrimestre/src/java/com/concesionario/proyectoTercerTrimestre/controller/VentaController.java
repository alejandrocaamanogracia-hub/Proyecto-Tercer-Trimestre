package com.concesionario.proyectoTercerTrimestre.controller;

import com.concesionario.proyectoTercerTrimestre.entities.DetalleVenta;
import com.concesionario.proyectoTercerTrimestre.entities.EstadoVenta;
import com.concesionario.proyectoTercerTrimestre.entities.Venta;
import com.concesionario.proyectoTercerTrimestre.services.VentaService;

import java.time.LocalDate;
import java.util.List;

public class VentaController {

    private final VentaService ventaService;

    public VentaController() {
        this.ventaService = new VentaService();
    }

    public void crearVenta(int clienteId, int usuarioId, LocalDate fecha, EstadoVenta estado, double total) {
        Venta venta = new Venta(clienteId, usuarioId, fecha, estado, total);
        ventaService.crearVenta(venta);
    }

    public void eliminarVenta(int id) {
        ventaService.eliminarVenta(id);
    }

    public List<Venta> listarVentas() {
        return ventaService.listarVentas();
    }

    public void exportarVentasTxt() {
        ventaService.exportarVentasTxt();
    }

    public void modificarVenta(int id, Venta venta) {
        ventaService.modificarVenta(id, venta);
    }

    public Venta buscarVenta(int id) {
        return ventaService.buscarVenta(id);
    }

    public boolean existeCliente(int clienteId) {
        return ventaService.existeCliente(clienteId);
    }

    public boolean existeUsuario(int usuarioId) {
        return ventaService.existeUsuario(usuarioId);
    }

}