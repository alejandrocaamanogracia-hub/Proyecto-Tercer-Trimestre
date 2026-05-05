package com.concesionario.proyectoTercerTrimestre.interfaz;

import com.concesionario.proyectoTercerTrimestre.controller.VentaController;
import com.concesionario.proyectoTercerTrimestre.entities.Venta;
import com.concesionario.proyectoTercerTrimestre.entities.EstadoVenta;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Scanner;

public class VentaMenu {

    private final Scanner scanner;
    private final VentaController ventaController;
    private final DateTimeFormatter formatter;

    public VentaMenu() {
        this.scanner = new Scanner(System.in);
        this.ventaController = new VentaController();
        this.formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
    }

    public void mostrarMenuVentas() {
        int opcion;

        do {
            System.out.println("\n===== MENU VENTAS =====");
            System.out.println("1. Crear venta");
            System.out.println("2. Eliminar venta");
            System.out.println("3. Listar ventas");
            System.out.println("4. Exportar ventas a TXT");
            System.out.println("0. Volver");
            System.out.print("Elige una opcion: ");

            opcion = Integer.parseInt(scanner.nextLine());

            switch (opcion) {
                case 1:
                    crearVenta();
                    break;
                case 2:
                    eliminarVenta();
                    break;
                case 3:
                    listarVentas();
                    break;
                case 4:
                    exportarVentasTxt();
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

    private void crearVenta() {
        System.out.println("\n--- Crear venta ---");

        System.out.print("ID Cliente: ");
        int clienteId = Integer.parseInt(scanner.nextLine());

        System.out.print("ID Usuario: ");
        int usuarioId = Integer.parseInt(scanner.nextLine());

        System.out.print("Fecha venta (yyyy-MM-dd), vacio para hoy: ");
        String fechaTexto = scanner.nextLine();

        LocalDate fecha;

        if (fechaTexto.isBlank()) {
            fecha = LocalDate.now();
        } else {
            fecha = LocalDate.parse(fechaTexto, formatter);
        }

        EstadoVenta estado = seleccionarEstadoVenta();

        System.out.print("Total: ");
        double total = Double.parseDouble(scanner.nextLine());

        ventaController.crearVenta(clienteId, usuarioId, fecha, estado, total);
    }

    private EstadoVenta seleccionarEstadoVenta() {
        System.out.println("Estado de la venta:");
        System.out.println("1. Pendiente");
        System.out.println("2. Completada");
        System.out.println("3. Presupuesto");
        System.out.println("4. Cancelada");
        System.out.print("Elige una opcion: ");

        int opcion = Integer.parseInt(scanner.nextLine());

        switch (opcion) {
            case 1:
                return EstadoVenta.PENDIENTE;
            case 2:
                return EstadoVenta.COMPLETADA;
            case 3:
                return EstadoVenta.PRESUPUESTO;
            case 4:
                return EstadoVenta.CANCELADA;
            default:
                System.out.println("Opcion no valida. Se asigna Pendiente por defecto.");
                return EstadoVenta.PENDIENTE;
        }
    }

    private void eliminarVenta() {
        System.out.println("\n--- Eliminar venta ---");

        System.out.print("Introduce el ID de la venta: ");
        int id = Integer.parseInt(scanner.nextLine());

        ventaController.eliminarVenta(id);
    }

    private void listarVentas() {
        System.out.println("\n--- Lista de ventas ---");

        List<Venta> ventas = ventaController.listarVentas();

        if (ventas.isEmpty()) {
            System.out.println("No hay ventas registradas.");
            return;
        }

        for (Venta venta : ventas) {
            System.out.println(venta);
        }
    }

    private void exportarVentasTxt() {
        ventaController.exportarVentasTxt();
    }
}