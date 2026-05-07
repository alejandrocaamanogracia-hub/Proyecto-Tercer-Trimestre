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

        System.out.print("Introduce el ID del cliente: ");
        int id = ComprobacionOpcion.leerInt();

        clienteController.eliminarCliente(id);
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

    private void modificarCliente(){

        Cliente cliente = new Cliente();

        System.out.println("Que cliente quieres modificar: ");
        List<Cliente> clientes = clienteController.listarClientes();
        int iterador = 1;
        for (Cliente cliente2 : clientes){
            System.out.println(iterador + ". " + cliente2.getNombre());
            iterador++;
        }

        int opcion;
        int opcion2;

        opcion = ComprobacionOpcion.leerOpcion(1, clientes.size());

        System.out.println("Modificar nombre");
        System.out.println("1. Si");
        System.out.println("2. No");

        opcion2 = ComprobacionOpcion.leerOpcion(1, 2);

        switch (opcion2) {

            case 1 -> {
                System.out.println("Introduce el nombre: ");
                cliente.setNombre(scanner.nextLine());
            }case 2  -> {
                cliente.setNombre(null);
            }

        }

        System.out.println("Modificar email");
        System.out.println("1. Si");
        System.out.println("2. No");

        opcion2 = ComprobacionOpcion.leerOpcion(1, 2);

        switch (opcion2) {

            case 1 -> {
                System.out.println("Introduce el email: ");
                cliente.setEmail(scanner.nextLine());
            }case 2  -> {
                cliente.setEmail(null);
            }

        }

        System.out.println("Modificar telefono");
        System.out.println("1. Si");
        System.out.println("2. No");

        opcion2 = ComprobacionOpcion.leerOpcion(1, 2);

        switch (opcion2) {

            case 1 -> {
                System.out.println("Introduce el telefono: ");
                cliente.setTelefono(scanner.nextLine());
            }case 2  -> {
                cliente.setTelefono(null);
            }

        }

        System.out.println("Modificar direccion");
        System.out.println("1. Si");
        System.out.println("2. No");

        opcion2 = ComprobacionOpcion.leerOpcion(1, 2);

        switch (opcion2) {

            case 1 -> {
                System.out.println("Introduce la direccion: ");
                cliente.setDireccion(scanner.nextLine());
            }case 2  -> {
                cliente.setDireccion(null);
            }

        }

        clienteController.modificarCliente(opcion, cliente);

    }

    public void buscarCliente() {

        List<Cliente> clientes = clienteController.listarClientes();

        if (clientes.isEmpty()) {
            System.out.println("No hay clientes registrados.");
            return;
        }

        System.out.println("Que cliente quieres ver: ");

        for (Cliente cliente : clientes) {
            System.out.println(cliente.getId() +  ". " + cliente.getNombre());
        }

        Cliente cliente = clienteController.buscarCliente(ComprobacionOpcion.leerInt());

        if (cliente != null) {
            System.out.println(cliente.toString());
        }else  {
            System.out.println("No existe el cliente con ese nombre.");
        }

    }

}