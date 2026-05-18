package com.concesionario.proyectoTercerTrimestre.repositories;

import com.concesionario.proyectoTercerTrimestre.entities.Venta;

import java.util.List;

public interface VentaRepository {

    void crearVenta(Venta venta);

    boolean eliminarVenta(int id);

    List<Venta> listarVentas();


    void modificarVenta(int id, Venta venta);

    Venta buscarVenta(int id);

    boolean existeCliente(int clienteId);

    boolean existeUsuario(int usuarioId);

}