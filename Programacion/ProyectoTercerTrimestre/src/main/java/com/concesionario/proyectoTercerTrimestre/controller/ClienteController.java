package com.concesionario.proyectoTercerTrimestre.controller;

import com.concesionario.proyectoTercerTrimestre.entities.Cliente;
import com.concesionario.proyectoTercerTrimestre.services.ClienteService;

import java.util.List;

public class ClienteController {

    ClienteService clienteService = new ClienteService();

    public Cliente findById(int id) {
        return clienteService.findById(id);
    }

    public List<Cliente> findAll() {
        return clienteService.findAll();
    }

}
