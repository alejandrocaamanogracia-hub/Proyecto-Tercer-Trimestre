package com.concesionario.proyectoTercerTrimestre.repositories;

import com.concesionario.proyectoTercerTrimestre.entities.Venta;

import java.util.List;

public interface VentaRepository {

    void crearVenta(Venta venta);

    void eliminarVenta(int id);

    List<Venta> listarVentas();
<<<<<<< HEAD

    void modificarVenta(int id, Venta venta);

    Venta buscarVenta(int id);

=======
>>>>>>> b40c4a9e5a9be5217f0a95a74284f60254651042
}