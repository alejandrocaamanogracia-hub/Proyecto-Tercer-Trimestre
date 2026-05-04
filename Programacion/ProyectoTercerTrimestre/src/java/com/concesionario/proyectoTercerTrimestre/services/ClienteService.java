package com.concesionario.proyectoTercerTrimestre.services;

import com.concesionario.proyectoTercerTrimestre.entities.Cliente;
import com.concesionario.proyectoTercerTrimestre.repositories.ClienteRepository;
import com.concesionario.proyectoTercerTrimestre.repositories.impl.ClienteRepositoryImpl;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.List;
import java.util.Optional;

public class ClienteService {

    private final ClienteRepository clienteRepository;

    public ClienteService() {
        this.clienteRepository = new ClienteRepositoryImpl();
    }

    public void crearCliente(Cliente cliente) {
        if (cliente == null) {
            System.out.println("El cliente no puede ser nulo.");
            return;
        }

        if (cliente.getNombre() == null || cliente.getNombre().isBlank()) {
            System.out.println("El nombre del cliente es obligatorio.");
            return;
        }

        if (cliente.getEmail() == null || cliente.getEmail().isBlank()) {
            System.out.println("El email del cliente es obligatorio.");
            return;
        }

        clienteRepository.crearCliente(cliente);
        System.out.println("Cliente creado correctamente.");
    }

    public void eliminarCliente(int id) {
        if (id <= 0) {
            System.out.println("El ID del cliente no es valido.");
            return;
        }

        clienteRepository.eliminarCliente(id);
        System.out.println("Cliente eliminado correctamente.");
    }

    public List<Cliente> listarClientes() {
        return clienteRepository.listarClientes();
    }

    public void exportarClientesTxt() {

        List<Cliente> clientes = clienteRepository.listarClientes();

        if (clientes.isEmpty()) {
            System.out.println("No hay clientes para exportar.");
            return;
        }

        try (BufferedWriter writer = new BufferedWriter(new FileWriter("clientes.txt"))) {

            writer.write("===== LISTADO DE CLIENTES =====");
            writer.newLine();
            writer.newLine();

            for (Cliente cliente : clientes) {
                writer.write("ID: " + cliente.getId());
                writer.newLine();
                writer.write("Nombre: " + cliente.getNombre());
                writer.newLine();
                writer.write("Email: " + cliente.getEmail());
                writer.newLine();
                writer.write("Telefono: " + cliente.getTelefono());
                writer.newLine();
                writer.write("Direccion: " + cliente.getDireccion());
                writer.newLine();
                writer.write("------------------------------");
                writer.newLine();
            }

            System.out.println("Clientes exportados correctamente a clientes.txt");

        } catch (IOException e) {
            System.out.println("Error al exportar los clientes.");
            e.printStackTrace();
        }
    }

    public Optional<Cliente> buscarCliente(int id) {

        Cliente cliente = clienteRepository.buscarCliente(id);

        if (cliente == null) {
            return Optional.empty();
        }

        return Optional.ofNullable(cliente);

    }

}