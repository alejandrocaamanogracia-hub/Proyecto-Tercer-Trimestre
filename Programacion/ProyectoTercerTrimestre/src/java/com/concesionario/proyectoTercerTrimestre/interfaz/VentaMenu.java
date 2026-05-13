package com.concesionario.proyectoTercerTrimestre.interfaz;

import com.concesionario.proyectoTercerTrimestre.controller.ClienteController;
import com.concesionario.proyectoTercerTrimestre.controller.UsuarioController;
import com.concesionario.proyectoTercerTrimestre.controller.VentaController;
import com.concesionario.proyectoTercerTrimestre.entities.*;
import com.concesionario.proyectoTercerTrimestre.utils.ComprobacionOpcion;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
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
            System.out.println("5. Modificar venta");
            System.out.println("6. Buscar venta");
            System.out.println("0. Volver");
            System.out.print("Elige una opcion: ");

            opcion = ComprobacionOpcion.leerInt();

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
                case 5:
                    modificarVenta();
                    break;
                case 6:
                    buscarVentas();
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
        int clienteId = ComprobacionOpcion.leerInt();

        System.out.print("ID Usuario: ");
        int usuarioId = ComprobacionOpcion.leerInt();

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
        double total = ComprobacionOpcion.leerDouble();

        ventaController.crearVenta(clienteId, usuarioId, fecha, estado, total);
    }

    private EstadoVenta seleccionarEstadoVenta() {
        System.out.println("Estado de la venta:");
        System.out.println("1. Pendiente");
        System.out.println("2. Completada");
        System.out.println("3. Presupuesto");
        System.out.println("4. Cancelada");
        System.out.print("Elige una opcion: ");

        int opcion = ComprobacionOpcion.leerOpcion(1, 4);

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

        List<Venta> ventas = ventaController.listarVentas();

        if (ventas.isEmpty()) {
            System.out.println("No hay ventas registradas.");
            return;
        }

        System.out.println("Ventas disponibles:");

        for (Venta venta : ventas) {
            System.out.println(
                    "ID: " + venta.getId()
                            + " | ID Cliente: " + venta.getClienteId()
                            + " | ID Usuario: " + venta.getUsuarioId()
                            + " | Fecha: " + venta.getFecha()
                            + " | Estado: " + venta.getEstado()
                            + " | Total: " + venta.getTotal()
            );
        }

        System.out.print("Introduce el ID de la venta que quieres eliminar: ");
        int id = ComprobacionOpcion.leerInt();

        System.out.println("¿Seguro que quieres eliminar la venta con ID " + id + "?");
        System.out.println("1. Sí");
        System.out.println("2. No");

        int confirmacion = ComprobacionOpcion.leerOpcion(1, 2);

        if (confirmacion == 1) {
            ventaController.eliminarVenta(id);
        } else {
            System.out.println("Eliminación cancelada.");
        }
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

    private void modificarVenta() {

        Venta venta = new Venta();

        System.out.println("Que venta quieres modificar: ");
        List<Venta> ventas = ventaController.listarVentas();
        int iterador = 1;
        for (Venta venta1 : ventas) {
            System.out.println( iterador++ + ". " + venta1);
        }

        int opcion;
        int opcion2;

        opcion = ComprobacionOpcion.leerOpcion(1, ventas.size());

        System.out.println("Modificar id del cliente");
        System.out.println("1. Si");
        System.out.println("2. No");

        opcion2 = ComprobacionOpcion.leerOpcion(1, 2);

        switch (opcion2) {

            case 1 ->{
                System.out.println("Introduce el id del cliente: ");
                while (scanner.hasNextInt()) {
                    opcion2 = scanner.nextInt();
                }
                venta.setClienteId(opcion2);

            }case 2 ->{
                venta.setClienteId(-1);
            }

        }

        System.out.println("Modificar id del usuario");
        System.out.println("1. Si");
        System.out.println("2. No");

        opcion2 = ComprobacionOpcion.leerOpcion(1, 2);

        switch (opcion2) {

            case 1 ->{

                System.out.println("Introduce el id del usuario: ");
                while (scanner.hasNextInt()) {
                    opcion2 = scanner.nextInt();
                }

                venta.setUsuarioId(opcion2);

            }case 2 ->{
                venta.setUsuarioId(-1);
            }

        }

        System.out.println("Modificar la fecha");
        System.out.println("1. Si");
        System.out.println("2. No");

        opcion2 = ComprobacionOpcion.leerOpcion(1, 2);

        switch (opcion2) {

            case 1 -> {
                System.out.print("Fecha venta (yyyy-MM-dd), vacío para hoy: ");

                LocalDate fecha = null;

                while (true) {
                    String input = scanner.nextLine();

                    if (input.isBlank()) {
                        fecha = LocalDate.now();
                        break;
                    }

                    try {
                        fecha = LocalDate.parse(input, formatter);
                        break;
                    } catch (DateTimeParseException e) {
                        System.out.print("Formato incorrecto. Introduce yyyy-MM-dd: ");
                    }
                }

                venta.setFecha(fecha);
            }case 2 ->{
                venta.setFecha(null);
            }

        }

        System.out.println("Modificar estado");
        System.out.println("1. Si");
        System.out.println("2. No");

        opcion2 = ComprobacionOpcion.leerOpcion(1, 2);

        switch (opcion2) {

            case 1 ->{
                venta.setEstado(seleccionarEstadoVenta());
            }case 2 ->{
                venta.setEstado(null);
            }

        }

        System.out.println("Modificar el total");
        System.out.println("1. Si");
        System.out.println("2. No");

        opcion2 = ComprobacionOpcion.leerOpcion(1, 2);

        switch (opcion2) {

            case 1 ->{
                System.out.println("Introduce el total: ");
                venta.setTotal(Integer.parseInt(scanner.nextLine()));
            }case 2 ->{
                venta.setTotal(-1);
            }

        }

        ventaController.modificarVenta(opcion, venta);

    }

    public void buscarVentas() {

        List<Venta> ventas = ventaController.listarVentas();

        if (ventas.isEmpty()) {
            System.out.println("No hay ventas registradas.");
            return;
        }

        System.out.println("Que venta quieres ver: ");

        for (Venta venta : ventas) {
            System.out.println(venta.getId());
        }

        Venta venta = ventaController.buscarVenta(ComprobacionOpcion.leerInt());

        if (venta != null) {
            System.out.println(venta.toString());
        }else {
            System.out.println("No existe un venta con ese id");
        }

    }

}