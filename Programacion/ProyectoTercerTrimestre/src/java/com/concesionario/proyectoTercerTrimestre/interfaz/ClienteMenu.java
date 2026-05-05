package com.concesionario.proyectoTercerTrimestre.interfaz;

import com.concesionario.proyectoTercerTrimestre.controller.ClienteController;
import com.concesionario.proyectoTercerTrimestre.entities.Cliente;

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
            System.out.println("0. Volver");
            System.out.print("Elige una opcion: ");

            opcion = Integer.parseInt(scanner.nextLine());

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
        int id = Integer.parseInt(scanner.nextLine());

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
}