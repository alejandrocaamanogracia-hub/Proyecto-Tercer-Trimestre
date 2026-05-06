package com.concesionario.proyectoTercerTrimestre.controller;

import com.concesionario.proyectoTercerTrimestre.entities.Cliente;
import com.concesionario.proyectoTercerTrimestre.services.ClienteService;

import java.util.List;

public class ClienteController {

    private final ClienteService clienteService;

    public ClienteController() {
        this.clienteService = new ClienteService();
    }

    public void crearCliente(String nombre, String email, String telefono, String direccion) {
        Cliente cliente = new Cliente(nombre, email, telefono, direccion);
        clienteService.crearCliente(cliente);
    }

    public void eliminarCliente(int id) {
        clienteService.eliminarCliente(id);
    }

    public List<Cliente> listarClientes() {
        return clienteService.listarClientes();
    }

    public void exportarClientesTxt() {
        clienteService.exportarClientesTxt();
    }

    public void modificarCliente(int id, Cliente cliente) {

        clienteService.modificarCliente(id, cliente);

    }

}