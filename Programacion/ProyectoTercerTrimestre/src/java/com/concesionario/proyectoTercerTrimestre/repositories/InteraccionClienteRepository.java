package com.concesionario.proyectoTercerTrimestre.repositories;

import com.concesionario.proyectoTercerTrimestre.entities.InteraccionCliente;

import java.util.List;

public interface InteraccionClienteRepository {

    void crearInteraccionCliente(InteraccionCliente interaccionCliente);

    boolean eliminarInteraccionCliente(int id);

    List<InteraccionCliente> listarInteraccionesCliente();


    void modificarInteraccionCliente(int id, InteraccionCliente interaccionCliente);

    InteraccionCliente bucarInteraccionCliente(int id);

    boolean existeCliente(int clienteId);

    boolean existeUsuario(int usuarioId);

}