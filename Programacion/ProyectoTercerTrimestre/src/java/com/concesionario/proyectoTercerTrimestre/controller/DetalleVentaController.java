package com.concesionario.proyectoTercerTrimestre.controller;

import com.concesionario.proyectoTercerTrimestre.entities.DetalleVenta;
import com.concesionario.proyectoTercerTrimestre.services.DetalleVentaService;

import java.util.List;

public class DetalleVentaController {

    private final DetalleVentaService detalleVentaService;

    public DetalleVentaController() {
        this.detalleVentaService = new DetalleVentaService();
    }

    public void crearDetalleVenta(int ventaId, int cocheId, double precioFinal, double descuento) {
        DetalleVenta detalleVenta = new DetalleVenta(ventaId, cocheId, precioFinal, descuento);
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

    public void modificarDetalleVenta(int id, DetalleVenta detalleVenta) {
        detalleVentaService.modificarDetalleVenta(id, detalleVenta);
    }

    public DetalleVenta buscarDetalleVenta(int id) {
        return detalleVentaService.buscarDetalleVenta(id);
    }

    public boolean existeVenta(int ventaId) {
        return detalleVentaService.existeVenta(ventaId);
    }

    public boolean existeCoche(int cocheId) {
        return detalleVentaService.existeCoche(cocheId);
    }

    public boolean existeDetalleVentaConCocheExcluyendoId(int cocheId, int idDetalleVenta) {
        return detalleVentaService.existeDetalleVentaConCocheExcluyendoId(cocheId, idDetalleVenta);
    }

    public boolean existeDetalleVentaConCoche(int cocheId) {
        return detalleVentaService.existeDetalleVentaConCoche(cocheId);
    }

}