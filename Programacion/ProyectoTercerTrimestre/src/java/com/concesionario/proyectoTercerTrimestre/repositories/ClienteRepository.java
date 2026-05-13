package com.concesionario.proyectoTercerTrimestre.repositories;

import com.concesionario.proyectoTercerTrimestre.entities.Cliente;

import java.util.List;

public interface ClienteRepository {

    void crearCliente(Cliente cliente);

    boolean eliminarCliente(int id);

    List<Cliente> listarClientes();

    void modificarCliente(int id, Cliente cliente);

    Cliente buscarCliente(int id);
}