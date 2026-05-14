package com.concesionario.proyectoTercerTrimestre.interfaz;


import com.concesionario.proyectoTercerTrimestre.controller.DetalleVentaController;
import com.concesionario.proyectoTercerTrimestre.entities.*;
import com.concesionario.proyectoTercerTrimestre.utils.ComprobacionOpcion;

import java.util.List;
import java.util.Scanner;

public class DetalleVentaMenu {

    private final Scanner scanner;
    private final DetalleVentaController detalleVentaController;

    public DetalleVentaMenu() {
        this.scanner = new Scanner(System.in);
        this.detalleVentaController = new DetalleVentaController();
    }

    public void mostrarMenuDetalleVenta() {
        int opcion;

        do {
            System.out.println("\n===== MENU DETALLE VENTA =====");
            System.out.println("1. Crear detalle de venta");
            System.out.println("2. Eliminar detalle de venta");
            System.out.println("3. Listar detalles de venta");
            System.out.println("4. Exportar detalles de venta a TXT");
            System.out.println("5. Modificar detalle de venta");
            System.out.println("6. Buscar detalle de venta");
            System.out.println("0. Volver");
            System.out.print("Elige una opcion: ");

            opcion = ComprobacionOpcion.leerInt();

            switch (opcion) {
                case 1:
                    crearDetalleVenta();
                    break;
                case 2:
                    eliminarDetalleVenta();
                    break;
                case 3:
                    listarDetallesVenta();
                    break;
                case 4:
                    exportarDetallesVentaTxt();
                    break;
                case 5:
                    modificarDetalleVenta();
                    break;
                case 6:
                    buscarDetalleVenta();
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

    private void crearDetalleVenta() {
        System.out.println("\n--- Crear detalle de venta ---");

        System.out.print("ID Venta: ");
        int ventaId = ComprobacionOpcion.leerInt();

        System.out.print("ID Coche: ");
        int cocheId = ComprobacionOpcion.leerInt();

        System.out.print("Cantidad: ");
        int cantidad = ComprobacionOpcion.leerInt();

        detalleVentaController.crearDetalleVenta(ventaId, cocheId, cantidad);
    }

    private void eliminarDetalleVenta() {
        System.out.println("\n--- Eliminar detalle de venta ---");

        List<DetalleVenta> detallesVenta = detalleVentaController.listarDetallesVenta();

        if (detallesVenta.isEmpty()) {
            System.out.println("No hay detalles de venta registrados.");
            return;
        }

        System.out.println("Detalles de venta disponibles:");

        for (DetalleVenta detalleVenta : detallesVenta) {
            System.out.println(
                    "ID: " + detalleVenta.getId()
                            + " | ID Venta: " + detalleVenta.getVentaId()
                            + " | ID Coche: " + detalleVenta.getCocheId()
                            + " | Cantidad: " + detalleVenta.getCantidad()
            );
        }

        System.out.print("Introduce el ID del detalle de venta que quieres eliminar: ");
        int id = ComprobacionOpcion.leerInt();

        System.out.println("¿Seguro que quieres eliminar el detalle de venta con ID " + id + "?");
        System.out.println("1. Sí");
        System.out.println("2. No");

        int confirmacion = ComprobacionOpcion.leerOpcion(1, 2);

        if (confirmacion == 1) {
            detalleVentaController.eliminarDetalleVenta(id);
        } else {
            System.out.println("Eliminación cancelada.");
        }
    }

    private void listarDetallesVenta() {
        System.out.println("\n--- Lista de detalles de venta ---");

        List<DetalleVenta> detallesVenta = detalleVentaController.listarDetallesVenta();

        if (detallesVenta.isEmpty()) {
            System.out.println("No hay detalles de venta registrados.");
            return;
        }

        for (DetalleVenta detalleVenta : detallesVenta) {
            System.out.println(detalleVenta);
        }
    }

    private void exportarDetallesVentaTxt() {
        detalleVentaController.exportarDetallesVentaTxt();
    }

    private void modificarDetalleVenta() {
        System.out.println("\n--- Modificar detalle de venta ---");

        List<DetalleVenta> detalleVentas = detalleVentaController.listarDetallesVenta();

        if (detalleVentas.isEmpty()) {
            System.out.println("No hay detalles de venta registrados.");
            return;
        }

        System.out.println("Que detalle de venta quieres modificar: ");

        int iterador = 1;
        for (DetalleVenta detalleVentaActual : detalleVentas) {
            System.out.println(
                    iterador + ". ID: " + detalleVentaActual.getId()
                            + " | ID Venta: " + detalleVentaActual.getVentaId()
                            + " | ID Coche: " + detalleVentaActual.getCocheId()
                            + " | Cantidad: " + detalleVentaActual.getCantidad()
            );
            iterador++;
        }

        int opcion = ComprobacionOpcion.leerOpcion(1, detalleVentas.size());

        DetalleVenta detalleVentaActual = detalleVentas.get(opcion - 1);
        int idDetalleVenta = detalleVentaActual.getId();

        DetalleVenta detalleVentaModificado = new DetalleVenta();

        detalleVentaModificado.setVentaId(detalleVentaActual.getVentaId());
        detalleVentaModificado.setCocheId(detalleVentaActual.getCocheId());
        detalleVentaModificado.setCantidad(detalleVentaActual.getCantidad());

        System.out.println("\nModificar ID de la venta actual: " + detalleVentaActual.getVentaId());
        System.out.println("1. Si");
        System.out.println("2. No");

        if (ComprobacionOpcion.leerOpcion(1, 2) == 1) {
            detalleVentaModificado.setVentaId(pedirVentaExistente());
        }

        System.out.println("\nModificar ID del coche actual: " + detalleVentaActual.getCocheId());
        System.out.println("1. Si");
        System.out.println("2. No");

        if (ComprobacionOpcion.leerOpcion(1, 2) == 1) {
            detalleVentaModificado.setCocheId(pedirCocheDisponibleParaModificar(idDetalleVenta));
        }

        System.out.println("\nModificar cantidad actual: " + detalleVentaActual.getCantidad());
        System.out.println("1. Si");
        System.out.println("2. No");

        if (ComprobacionOpcion.leerOpcion(1, 2) == 1) {
            detalleVentaModificado.setCantidad(
                    ComprobacionOpcion.leerIntMinimo("Introduce la cantidad: ", 1)
            );
        }

        detalleVentaController.modificarDetalleVenta(idDetalleVenta, detalleVentaModificado);
    }

    private int pedirVentaExistente() {
        int ventaId;
        boolean existe;

        do {
            ventaId = ComprobacionOpcion.leerIntMinimo("Introduce el ID de la venta: ", 1);
            existe = detalleVentaController.existeVenta(ventaId);

            if (!existe) {
                System.out.println("No existe ninguna venta con ese ID.");
            }

        } while (!existe);

        return ventaId;
    }

    private int pedirCocheExistente() {
        int cocheId;
        boolean existe;

        do {
            cocheId = ComprobacionOpcion.leerIntMinimo("Introduce el ID del coche: ", 1);
            existe = detalleVentaController.existeCoche(cocheId);

            if (!existe) {
                System.out.println("No existe ningun coche con ese ID.");
            }

        } while (!existe);

        return cocheId;
    }

    private int pedirCocheDisponibleParaModificar(int idDetalleVentaActual) {
        int cocheId;
        boolean cocheExiste;
        boolean cocheYaUsado = false;

        do {
            cocheId = ComprobacionOpcion.leerIntMinimo("Introduce el ID del coche: ", 1);

            cocheExiste = detalleVentaController.existeCoche(cocheId);

            if (!cocheExiste) {
                System.out.println("No existe ningun coche con ese ID.");
                cocheYaUsado = false;
                continue;
            }

            cocheYaUsado = detalleVentaController.existeDetalleVentaConCocheExcluyendoId(cocheId, idDetalleVentaActual);

            if (cocheYaUsado) {
                System.out.println("Ese coche ya esta asignado a otro detalle de venta.");
            }

        } while (!cocheExiste || cocheYaUsado);

        return cocheId;
    }

    public void buscarDetalleVenta() {
        System.out.println("\n--- Buscar detalle de venta ---");

        List<DetalleVenta> detalleVentas = detalleVentaController.listarDetallesVenta();

        if (detalleVentas.isEmpty()) {
            System.out.println("No hay detalles de venta registrados.");
            return;
        }

        System.out.println("Que detalle de venta quieres ver: ");

        for (DetalleVenta detalleVenta : detalleVentas) {
            System.out.println(
                    detalleVenta.getId()
                            + ". ID Venta: " + detalleVenta.getVentaId()
                            + " | ID Coche: " + detalleVenta.getCocheId()
                            + " | Cantidad: " + detalleVenta.getCantidad()
            );
        }

        System.out.print("Introduce el ID del detalle de venta: ");
        DetalleVenta detalleVenta = detalleVentaController.buscarDetalleVenta(ComprobacionOpcion.leerInt());

        if (detalleVenta != null) {
            System.out.println(detalleVenta);
        }
    }

}