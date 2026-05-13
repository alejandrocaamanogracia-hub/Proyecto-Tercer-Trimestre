package com.concesionario.proyectoTercerTrimestre.repositories;

import com.concesionario.proyectoTercerTrimestre.entities.DetalleVenta;

import java.util.List;

public interface DetalleVentaRepository {

    void crearDetalleVenta(DetalleVenta detalleVenta);

    boolean eliminarDetalleVenta(int id);

    List<DetalleVenta> listarDetallesVenta();

    void modificarDetalleVenta(int id, DetalleVenta detalleVenta);

    DetalleVenta buscarDetalleVenta(int id);

    boolean existeVenta(int ventaId);

    boolean existeCoche(int cocheId);

    boolean existeDetalleVentaConVentaYCoche(int ventaId, int cocheId);

    boolean existeDetalleVentaConCoche(int cocheId);
}