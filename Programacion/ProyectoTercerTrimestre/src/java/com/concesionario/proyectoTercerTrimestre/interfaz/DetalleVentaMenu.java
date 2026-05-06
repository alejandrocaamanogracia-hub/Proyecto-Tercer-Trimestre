package com.concesionario.proyectoTercerTrimestre.interfaz;

import com.concesionario.proyectoTercerTrimestre.controller.CocheController;
import com.concesionario.proyectoTercerTrimestre.controller.DetalleVentaController;
import com.concesionario.proyectoTercerTrimestre.controller.VentaController;
import com.concesionario.proyectoTercerTrimestre.entities.Coche;
import com.concesionario.proyectoTercerTrimestre.entities.DetalleVenta;
import com.concesionario.proyectoTercerTrimestre.entities.Usuario;
import com.concesionario.proyectoTercerTrimestre.entities.Venta;
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
                case 5:
                    modificarDetalleVenta();
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

    private void modificarDetalleVenta() {

        DetalleVenta detalleVenta = new DetalleVenta();

        System.out.println("Que detalle venta quieres modificar: ");
        List<DetalleVenta> detalleVentas =  detalleVentaController.listarDetallesVenta();
        int iterador = 1;
        for (DetalleVenta detalleVenta1 : detalleVentas) {
            System.out.println(iterador++ + " - " + detalleVenta1.getId());
        }

        int opcion;
        int opcion2;

        opcion = ComprobacionOpcion.leerOpcion(1, detalleVentas.size());

        System.out.println("Modificar id de la venta");
        System.out.println("1. Si");
        System.out.println("2. No");

        opcion2 = ComprobacionOpcion.leerOpcion(1, 2);

        switch (opcion2) {

            case 1 -> {

                System.out.println("Cual de las ventas desea seleccionar: ");
                VentaController ventaController = new VentaController();
                List <Venta> ventas = ventaController.listarVentas();
                iterador = 1;
                for (Venta venta : ventas) {
                    System.out.println(iterador++ + ". " + venta.getId());
                }
                opcion2 = 0;
                while (opcion2 <= 0 || opcion2 > ventas.size()) {
                    opcion2 = Integer.parseInt(scanner.next());
                    scanner.nextLine();
                }
                detalleVenta.setVentaId(opcion2);

            }case 2 -> {
                detalleVenta.setVentaId(-1);
            }

        }

        System.out.println("Modificar id del coche");
        System.out.println("1. Si");
        System.out.println("2. No");

        opcion2 = ComprobacionOpcion.leerOpcion(1, 2);

        switch (opcion2) {

            case 1 -> {

                System.out.println("Cual de los coches desea seleccionar: ");
                CocheController cocheController = new CocheController();
                List <Coche> coches = cocheController.listarCoches();
                iterador = 1;
                for (Coche coche: coches) {
                    System.out.println(iterador++ + ". " + coche.getMatricula());
                }
                opcion2 = 0;
                while (opcion2 <= 0 || opcion2 > coches.size()) {
                    opcion2 = Integer.parseInt(scanner.next());
                    scanner.nextLine();
                }
                detalleVenta.setCocheId(opcion2);

            }case 2 -> {
                detalleVenta.setCocheId(-1);
            }

        }

        System.out.println("Modificar la cantidad");
        System.out.println("1. Si");
        System.out.println("2. No");

        opcion2 = ComprobacionOpcion.leerOpcion(1, 2);

        switch (opcion2) {
            case 1 -> {
                System.out.println("Introduce la cantidad: ");
                int cantidad = 0;
                while (scanner.hasNextInt()) {
                    cantidad = scanner.nextInt();
                }
                detalleVenta.setCantidad(cantidad);
            }case 2 -> {
                detalleVenta.setCantidad(-1);
            }
        }

        System.out.println("Modificar precio_unitario");
        System.out.println("1. Si");
        System.out.println("2. No");

        opcion2 = ComprobacionOpcion.leerOpcion(1, 2);

        switch (opcion2) {
            case 1 -> {
                System.out.println("Introduce el precio unitario: ");
                int precio = 0;
                while (scanner.hasNextInt()) {
                    precio = scanner.nextInt();
                }
                detalleVenta.setPrecioUnitario(precio);
            }case 2 -> {
                detalleVenta.setPrecioUnitario(-1);
            }
        }

        detalleVentaController.modificarDetalleVenta(opcion, detalleVenta);

    }

}