package com.concesionario.proyectoTercerTrimestre.controller;

import com.concesionario.proyectoTercerTrimestre.entities.DetalleVenta;
import com.concesionario.proyectoTercerTrimestre.services.DetalleVentaService;

import java.util.List;

public class DetalleVentaController {

    private final DetalleVentaService detalleVentaService;

    public DetalleVentaController() {
        this.detalleVentaService = new DetalleVentaService();
    }

    public void crearDetalleVenta(int ventaId, int cocheId, int cantidad, double precioUnitario) {
        DetalleVenta detalleVenta = new DetalleVenta(ventaId, cocheId, cantidad, precioUnitario);
        detalleVentaService.crearDetalleVenta(detalleVenta);
    }

    public void eliminarDetalleVenta(int id) {
        detalleVentaService.eliminarDetalleVenta(id);
    }

    public List<DetalleVenta> listarDetallesVenta() {
        return detalleVentaService.listarDetallesVenta();
    }

    public void exportarDetallesVentaTxt() {
        detalleVentaService.exportarDetallesVentaTxt();
    }
}