package com.concesionario.proyectoTercerTrimestre.controller;

import com.concesionario.proyectoTercerTrimestre.entities.Venta;
import com.concesionario.proyectoTercerTrimestre.services.VentaService;

import java.time.LocalDate;
import java.util.List;

public class VentaController {

    private final VentaService ventaService;

    public VentaController() {
        this.ventaService = new VentaService();
    }

    public void crearVenta(int clienteId, int usuarioId, LocalDate fecha, String estado, double total) {
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
}