package com.concesionario.proyectoTercerTrimestre.interfaz;

import com.concesionario.proyectoTercerTrimestre.controller.DetalleVentaController;
import com.concesionario.proyectoTercerTrimestre.entities.DetalleVenta;

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
            System.out.println("0. Volver");
            System.out.print("Elige una opcion: ");

            opcion = Integer.parseInt(scanner.nextLine());

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
        int ventaId = Integer.parseInt(scanner.nextLine());

        System.out.print("ID Coche: ");
        int cocheId = Integer.parseInt(scanner.nextLine());

        System.out.print("Cantidad: ");
        int cantidad = Integer.parseInt(scanner.nextLine());

        System.out.print("Precio unitario: ");
        double precioUnitario = Double.parseDouble(scanner.nextLine());

        detalleVentaController.crearDetalleVenta(ventaId, cocheId, cantidad, precioUnitario);
    }

    private void eliminarDetalleVenta() {
        System.out.println("\n--- Eliminar detalle de venta ---");

        System.out.print("Introduce el ID del detalle de venta: ");
        int id = Integer.parseInt(scanner.nextLine());

        detalleVentaController.eliminarDetalleVenta(id);
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
}