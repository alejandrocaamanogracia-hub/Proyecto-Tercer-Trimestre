package com.concesionario.proyectoTercerTrimestre.services;

import com.concesionario.proyectoTercerTrimestre.entities.Cliente;
import com.concesionario.proyectoTercerTrimestre.repositories.ClienteRepository;
import com.concesionario.proyectoTercerTrimestre.repositories.impl.ClienteRepositoryImpl;

import java.util.List;

public class ClienteService {

    ClienteRepository clienteRepository = new ClienteRepositoryImpl();

    public Cliente findById(int id) {

        Cliente cliente = clienteRepository.findById(id);

        return cliente;

    }

    public List<Cliente> findAll() {

        List<Cliente> clientes = clienteRepository.findAll();

        if (clientes.isEmpty()) {
            throw new RuntimeException("No hay clientes");
        }

        return clientes;

    }

}
