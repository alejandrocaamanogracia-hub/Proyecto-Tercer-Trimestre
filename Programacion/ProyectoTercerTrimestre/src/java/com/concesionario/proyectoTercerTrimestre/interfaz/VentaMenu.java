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
        System.out.println("\n--- Modificar venta ---");

        List<Venta> ventas = ventaController.listarVentas();

        if (ventas.isEmpty()) {
            System.out.println("No hay ventas registradas.");
            return;
        }

        System.out.println("Que venta quieres modificar: ");

        int iterador = 1;
        for (Venta ventaActual : ventas) {
            System.out.println(
                    iterador + ". ID: " + ventaActual.getId()
                            + " | ID Cliente: " + ventaActual.getClienteId()
                            + " | ID Usuario: " + ventaActual.getUsuarioId()
                            + " | Fecha: " + ventaActual.getFecha()
                            + " | Estado: " + ventaActual.getEstado()
                            + " | Total: " + ventaActual.getTotal()
            );
            iterador++;
        }

        int opcion = ComprobacionOpcion.leerOpcion(1, ventas.size());

        Venta ventaActual = ventas.get(opcion - 1);
        int idVenta = ventaActual.getId();

        Venta ventaModificada = new Venta();

        ventaModificada.setClienteId(ventaActual.getClienteId());
        ventaModificada.setUsuarioId(ventaActual.getUsuarioId());
        ventaModificada.setFecha(ventaActual.getFecha());
        ventaModificada.setEstado(ventaActual.getEstado());
        ventaModificada.setTotal(ventaActual.getTotal());

        System.out.println("\nModificar ID del cliente actual: " + ventaActual.getClienteId());
        System.out.println("1. Si");
        System.out.println("2. No");

        if (ComprobacionOpcion.leerOpcion(1, 2) == 1) {
            ventaModificada.setClienteId(pedirClienteExistente());
        }

        System.out.println("\nModificar ID del usuario actual: " + ventaActual.getUsuarioId());
        System.out.println("1. Si");
        System.out.println("2. No");

        if (ComprobacionOpcion.leerOpcion(1, 2) == 1) {
            ventaModificada.setUsuarioId(pedirUsuarioExistente());
        }

        System.out.println("\nModificar fecha actual: " + ventaActual.getFecha());
        System.out.println("1. Si");
        System.out.println("2. No");

        if (ComprobacionOpcion.leerOpcion(1, 2) == 1) {
            ventaModificada.setFecha(
                    ComprobacionOpcion.leerFecha("Fecha venta (yyyy-MM-dd), vacio para hoy: ", formatter)
            );
        }

        System.out.println("\nModificar estado actual: " + ventaActual.getEstado());
        System.out.println("1. Si");
        System.out.println("2. No");

        if (ComprobacionOpcion.leerOpcion(1, 2) == 1) {
            ventaModificada.setEstado(seleccionarEstadoVenta());
        }

        System.out.println("\nModificar total actual: " + ventaActual.getTotal());
        System.out.println("1. Si");
        System.out.println("2. No");

        if (ComprobacionOpcion.leerOpcion(1, 2) == 1) {
            ventaModificada.setTotal(
                    ComprobacionOpcion.leerDoubleMinimo("Introduce el total: ", 0)
            );
        }

        ventaController.modificarVenta(idVenta, ventaModificada);
    }

    private int pedirClienteExistente() {
        int clienteId;
        boolean existe;

        do {
            clienteId = ComprobacionOpcion.leerIntMinimo("Introduce el ID del cliente: ", 1);
            existe = ventaController.existeCliente(clienteId);

            if (!existe) {
                System.out.println("No existe ningun cliente con ese ID.");
            }

        } while (!existe);

        return clienteId;
    }

    private int pedirUsuarioExistente() {
        int usuarioId;
        boolean existe;

        do {
            usuarioId = ComprobacionOpcion.leerIntMinimo("Introduce el ID del usuario: ", 1);
            existe = ventaController.existeUsuario(usuarioId);

            if (!existe) {
                System.out.println("No existe ningun usuario con ese ID.");
            }

        } while (!existe);

        return usuarioId;
    }

    public void buscarVentas() {
        System.out.println("\n--- Buscar venta ---");

        List<Venta> ventas = ventaController.listarVentas();

        if (ventas.isEmpty()) {
            System.out.println("No hay ventas registradas.");
            return;
        }

        System.out.println("Que venta quieres ver: ");

        for (Venta venta : ventas) {
            System.out.println(
                    venta.getId()
                            + ". ID Cliente: " + venta.getClienteId()
                            + " | ID Usuario: " + venta.getUsuarioId()
                            + " | Fecha: " + venta.getFecha()
                            + " | Total: " + venta.getTotal()
            );
        }

        System.out.print("Introduce el ID de la venta: ");
        Venta venta = ventaController.buscarVenta(ComprobacionOpcion.leerInt());

        if (venta != null) {
            System.out.println(venta);
        }
    }

}