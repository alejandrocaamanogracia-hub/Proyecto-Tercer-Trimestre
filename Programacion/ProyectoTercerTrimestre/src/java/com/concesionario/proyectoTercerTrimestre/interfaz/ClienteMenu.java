package com.concesionario.proyectoTercerTrimestre.interfaz;

import com.concesionario.proyectoTercerTrimestre.controller.ClienteController;
import com.concesionario.proyectoTercerTrimestre.entities.Cliente;
import com.concesionario.proyectoTercerTrimestre.utils.ComprobacionOpcion;

import java.util.List;
import java.util.Scanner;

public class ClienteMenu {

    private final Scanner scanner;
    private final ClienteController clienteController;

    public ClienteMenu() {
        this.scanner = new Scanner(System.in);
        this.clienteController = new ClienteController();
    }

    public void mostrarMenuClientes() {
        int opcion;

        do {
            System.out.println("\n===== MENU CLIENTES =====");
            System.out.println("1. Crear cliente");
            System.out.println("2. Eliminar cliente");
            System.out.println("3. Listar clientes");
            System.out.println("4. Exportar clientes a TXT");
            System.out.println("5. Modificar un cliente");
            System.out.println("6. Buscar un cliente");
            System.out.println("0. Volver");
            System.out.print("Elige una opcion: ");

            opcion = ComprobacionOpcion.leerInt();;

            switch (opcion) {
                case 1:
                    crearCliente();
                    break;
                case 2:
                    eliminarCliente();
                    break;
                case 3:
                    listarClientes();
                    break;
                case 4:
                    exportarClientesTxt();
                    break;
                case 5:
                    modificarCliente();
                    break;
                case 6:
                    buscarCliente();
                    break;
                case 0:
                    System.out.println("Volviendo al menu principal...");
                    break;
                default:
                    System.out.println("Opcion no valida.");
                    break;
            }

        } while (opcion != 0);
    }

    private void crearCliente() {
        System.out.println("\n--- Crear cliente ---");

        System.out.print("Nombre: ");
        String nombre = scanner.nextLine();

        System.out.print("Email: ");
        String email = scanner.nextLine();

        System.out.print("Telefono: ");
        String telefono = scanner.nextLine();

        System.out.print("Direccion: ");
        String direccion = scanner.nextLine();

        clienteController.crearCliente(nombre, email, telefono, direccion);
    }

    private void eliminarCliente() {
        System.out.println("\n--- Eliminar cliente ---");

        List<Cliente> clientes = clienteController.listarClientes();

        if (clientes.isEmpty()) {
            System.out.println("No hay clientes registrados.");
            return;
        }

        System.out.println("Clientes disponibles:");

        for (Cliente cliente : clientes) {
            System.out.println(
                    "ID: " + cliente.getId()
                            + " | Nombre: " + cliente.getNombre()
                            + " | Email: " + cliente.getEmail()
                            + " | Teléfono: " + cliente.getTelefono()
            );
        }

        System.out.print("Introduce el ID del cliente que quieres eliminar: ");
        int id = ComprobacionOpcion.leerInt();

        System.out.println("¿Seguro que quieres eliminar el cliente con ID " + id + "?");
        System.out.println("1. Sí");
        System.out.println("2. No");

        int confirmacion = ComprobacionOpcion.leerOpcion(1, 2);

        if (confirmacion == 1) {
            clienteController.eliminarCliente(id);
        } else {
            System.out.println("Eliminación cancelada.");
        }
    }

    private void listarClientes() {
        System.out.println("\n--- Lista de clientes ---");

        List<Cliente> clientes = clienteController.listarClientes();

        if (clientes.isEmpty()) {
            System.out.println("No hay clientes registrados.");
            return;
        }

        for (Cliente cliente : clientes) {
            System.out.println(cliente);
        }
    }

    private void exportarClientesTxt() {
        clienteController.exportarClientesTxt();
    }

    private void modificarCliente() {
        System.out.println("\n--- Modificar cliente ---");

        List<Cliente> clientes = clienteController.listarClientes();

        if (clientes.isEmpty()) {
            System.out.println("No hay clientes registrados.");
            return;
        }

        System.out.println("Que cliente quieres modificar: ");

        int iterador = 1;
        for (Cliente clienteActual : clientes) {
            System.out.println(
                    iterador + ". ID: " + clienteActual.getId()
                            + " | Nombre: " + clienteActual.getNombre()
                            + " | Email: " + clienteActual.getEmail()
                            + " | Telefono: " + clienteActual.getTelefono()
                            + " | Direccion: " + clienteActual.getDireccion()
            );
            iterador++;
        }

        int opcion = ComprobacionOpcion.leerOpcion(1, clientes.size());

        Cliente clienteActual = clientes.get(opcion - 1);
        int idCliente = clienteActual.getId();

        Cliente clienteModificado = new Cliente();

        clienteModificado.setNombre(clienteActual.getNombre());
        clienteModificado.setEmail(clienteActual.getEmail());
        clienteModificado.setTelefono(clienteActual.getTelefono());
        clienteModificado.setDireccion(clienteActual.getDireccion());

        System.out.println("\nModificar nombre actual: " + clienteActual.getNombre());
        System.out.println("1. Si");
        System.out.println("2. No");

        int opcion2 = ComprobacionOpcion.leerOpcion(1, 2);

        if (opcion2 == 1) {
            clienteModificado.setNombre(
                    ComprobacionOpcion.leerTextoObligatorio("Introduce el nombre: ")
            );
        }

        System.out.println("\nModificar email actual: " + clienteActual.getEmail());
        System.out.println("1. Si");
        System.out.println("2. No");

        opcion2 = ComprobacionOpcion.leerOpcion(1, 2);

        if (opcion2 == 1) {
            clienteModificado.setEmail(
                    ComprobacionOpcion.leerTextoObligatorio("Introduce el email: ")
            );
        }

        System.out.println("\nModificar telefono actual: " + clienteActual.getTelefono());
        System.out.println("1. Si");
        System.out.println("2. No");

        opcion2 = ComprobacionOpcion.leerOpcion(1, 2);

        if (opcion2 == 1) {
            clienteModificado.setTelefono(
                    ComprobacionOpcion.leerTextoObligatorio("Introduce el telefono: ")
            );
        }

        System.out.println("\nModificar direccion actual: " + clienteActual.getDireccion());
        System.out.println("1. Si");
        System.out.println("2. No");

        opcion2 = ComprobacionOpcion.leerOpcion(1, 2);

        if (opcion2 == 1) {
            clienteModificado.setDireccion(
                    ComprobacionOpcion.leerTextoObligatorio("Introduce la direccion: ")
            );
        }

        clienteController.modificarCliente(idCliente, clienteModificado);
    }

    public void buscarCliente() {
        List<Cliente> clientes = clienteController.listarClientes();

        if (clientes.isEmpty()) {
            System.out.println("No hay clientes registrados.");
            return;
        }

        System.out.println("Que cliente quieres ver: ");

        for (Cliente cliente : clientes) {
            System.out.println(cliente.getId() + ". " + cliente.getNombre());
        }

        System.out.print("Introduce el ID del cliente: ");
        Cliente cliente = clienteController.buscarCliente(ComprobacionOpcion.leerInt());

        if (cliente != null) {
            System.out.println(cliente);
        }
    }

}