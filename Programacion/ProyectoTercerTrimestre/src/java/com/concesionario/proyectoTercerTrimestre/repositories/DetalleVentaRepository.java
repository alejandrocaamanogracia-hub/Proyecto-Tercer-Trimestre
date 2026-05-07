package com.concesionario.proyectoTercerTrimestre.repositories;

import com.concesionario.proyectoTercerTrimestre.entities.DetalleVenta;

import java.util.List;

public interface DetalleVentaRepository {

    void crearDetalleVenta(DetalleVenta detalleVenta);

    void eliminarDetalleVenta(int id);

    List<DetalleVenta> listarDetallesVenta();
<<<<<<< HEAD

    void modificarDetalleVenta(int id, DetalleVenta detalleVenta);

    DetalleVenta buscarDetalleVenta(int id);

=======
>>>>>>> b40c4a9e5a9be5217f0a95a74284f60254651042
}