package com.concesionario.proyectoTercerTrimestre.repositories;

import com.concesionario.proyectoTercerTrimestre.entities.Cliente;

import java.util.List;

public interface ClienteRepository {

    void crearCliente(Cliente cliente);

    void eliminarCliente(int id);

    List<Cliente> listarClientes();
}