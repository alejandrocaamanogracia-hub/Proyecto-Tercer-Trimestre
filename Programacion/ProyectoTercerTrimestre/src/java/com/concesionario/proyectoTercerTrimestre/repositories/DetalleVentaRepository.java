package com.concesionario.proyectoTercerTrimestre.repositories;

import com.concesionario.proyectoTercerTrimestre.entities.DetalleVenta;

import java.util.List;

public interface DetalleVentaRepository {

    void crearDetalleVenta(DetalleVenta detalleVenta);

    void eliminarDetalleVenta(int id);

    List<DetalleVenta> listarDetallesVenta();

    void modificarDetalleVenta(int id, DetalleVenta detalleVenta);

    DetalleVenta buscarDetalleVenta(int id);

}