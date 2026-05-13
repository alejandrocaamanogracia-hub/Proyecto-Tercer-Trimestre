package com.concesionario.proyectoTercerTrimestre.interfaz;

import com.concesionario.proyectoTercerTrimestre.controller.CocheController;
import com.concesionario.proyectoTercerTrimestre.entities.*;
import com.concesionario.proyectoTercerTrimestre.utils.ComprobacionOpcion;

import java.util.List;
import java.util.Scanner;

public class CocheMenu {

    private final Scanner scanner;
    private final CocheController cocheController;

    public CocheMenu() {
        this.scanner = new Scanner(System.in);
        this.cocheController = new CocheController();
    }

    public void mostrarMenuCoches() {
        int opcion;

        do {
            System.out.println("\n===== MENU COCHES =====");
            System.out.println("1. Crear coche");
            System.out.println("2. Eliminar coche");
            System.out.println("3. Listar coches");
            System.out.println("4. Exportar coches a TXT");
            System.out.println("5. Modificar un coche");
            System.out.println("6. Buscar un coche");
            System.out.println("0. Volver");
            System.out.print("Elige una opcion: ");

            opcion = ComprobacionOpcion.leerInt();

            switch (opcion) {
                case 1:
                    crearCoche();
                    break;
                case 2:
                    eliminarCoche();
                    break;
                case 3:
                    listarCoches();
                    break;
                case 4:
                    exportarCochesTxt();
                    break;
                case 5:
                    modificarCoches();
                case 6:
                    buscarCoche();
                case 0:
                    System.out.println("Volviendo al menu principal...");
                    break;
                default:
                    System.out.println("Opcion no valida.");
                    break;
            }

        } while (opcion != 0);
    }

    private void crearCoche() {
        System.out.println("\n--- Crear coche ---");

        System.out.print("Marca: ");
        String marca = scanner.nextLine();

        System.out.print("Modelo: ");
        String modelo = scanner.nextLine();

        System.out.print("Version: ");
        String version = scanner.nextLine();

        System.out.print("Matricula: ");
        String matricula = scanner.nextLine();

        System.out.print("Bastidor: ");
        String bastidor = scanner.nextLine();

        System.out.print("Anio: ");
        int anio = ComprobacionOpcion.leerInt();

        System.out.print("Kilometros: ");
        int kilometros = ComprobacionOpcion.leerInt();

        Combustible combustible = seleccionarCombustible();

        TipoCambio cambio = seleccionarTipoCambio();

        System.out.print("Color: ");
        String color = scanner.nextLine();

        System.out.print("Precio: ");
        double precio = ComprobacionOpcion.leerDouble();

        EstadoCoche estado = seleccionarEstadoCoche();

        cocheController.crearCoche(
                marca,
                modelo,
                version,
                matricula,
                bastidor,
                anio,
                kilometros,
                combustible,
                cambio,
                color,
                precio,
                estado
        );
    }

    private Combustible seleccionarCombustible() {
        System.out.println("Combustible:");
        System.out.println("1. Gasolina");
        System.out.println("2. Diesel");
        System.out.println("3. Hibrido");
        System.out.println("4. Electrico");
        System.out.println("5. GLP");
        System.out.print("Elige una opcion: ");

        int opcion = ComprobacionOpcion.leerOpcion(1, 5);

        return switch (opcion) {
            case 1 -> Combustible.GASOLINA;
            case 2 -> Combustible.DIESEL;
            case 3 -> Combustible.HIBRIDO;
            case 4 -> Combustible.ELECTRICO;
            case 5 -> Combustible.GLP;
            default -> Combustible.GASOLINA;
        };
    }

    private TipoCambio seleccionarTipoCambio() {
        System.out.println("Cambio:");
        System.out.println("1. Manual");
        System.out.println("2. Automatico");
        System.out.print("Elige una opcion: ");

        int opcion = ComprobacionOpcion.leerOpcion(1, 2);

        return switch (opcion) {
            case 1 -> TipoCambio.MANUAL;
            case 2 -> TipoCambio.AUTOMATICO;
            default -> TipoCambio.MANUAL;
        };
    }

    private EstadoCoche seleccionarEstadoCoche() {
        System.out.println("Estado:");
        System.out.println("1. Disponible");
        System.out.println("2. Reservado");
        System.out.println("3. Vendido");
        System.out.print("Elige una opcion: ");

        int opcion = ComprobacionOpcion.leerOpcion(1, 3);

        return switch (opcion) {
            case 1 -> EstadoCoche.DISPONIBLE;
            case 2 -> EstadoCoche.RESERVADO;
            case 3 -> EstadoCoche.VENDIDO;
            default -> EstadoCoche.DISPONIBLE;
        };
    }

    private void eliminarCoche() {
        System.out.println("\n--- Eliminar coche ---");

        List<Coche> coches = cocheController.listarCoches();

        if (coches.isEmpty()) {
            System.out.println("No hay coches registrados.");
            return;
        }

        System.out.println("Coches disponibles:");

        for (Coche coche : coches) {
            System.out.println(
                    "ID: " + coche.getId()
                            + " | Marca: " + coche.getMarca()
                            + " | Modelo: " + coche.getModelo()
                            + " | Matrícula: " + coche.getMatricula()
                            + " | Estado: " + coche.getEstado()
            );
        }

        System.out.print("Introduce el ID del coche que quieres eliminar: ");
        int id = ComprobacionOpcion.leerInt();

        System.out.println("¿Seguro que quieres eliminar el coche con ID " + id + "?");
        System.out.println("1. Sí");
        System.out.println("2. No");

        int confirmacion = ComprobacionOpcion.leerOpcion(1, 2);

        if (confirmacion == 1) {
            cocheController.eliminarCoche(id);
        } else {
            System.out.println("Eliminación cancelada.");
        }
    }

    private void listarCoches() {
        System.out.println("\n--- Lista de coches ---");

        List<Coche> coches = cocheController.listarCoches();

        if (coches.isEmpty()) {
            System.out.println("No hay coches registrados.");
            return;
        }

        for (Coche coche : coches) {
            System.out.println(coche);
        }
    }

    private void exportarCochesTxt() {
        cocheController.exportarCochesTxt();
    }

    private void modificarCoches(){

        Coche coche = new Coche();

        System.out.println("Que coche quieres modificar: ");
        List<Coche> coches = cocheController.listarCoches();
        int iterador = 1;
        for (Coche coche1 : coches){
            System.out.println(iterador + ". " + coche1.getMatricula());
            iterador++;
        }

        int opcion;
        int opcion2;

        opcion = ComprobacionOpcion.leerOpcion(1, coches.size());

        System.out.println("Modificar marca");
        System.out.println("1. Si");
        System.out.println("2. No");

        opcion2 = ComprobacionOpcion.leerOpcion(1, 2);

        switch (opcion2) {

            case 1 -> {
                System.out.println("Introduce la marca del coche: ");
                coche.setMarca(scanner.nextLine());
            }case 2 -> {
                coche.setMarca(null);
            }

        }

        System.out.println("Modificar modelo");
        System.out.println("1. Si");
        System.out.println("2. No");

        opcion2 = ComprobacionOpcion.leerOpcion(1, 2);

        switch (opcion2) {

            case 1 -> {
                System.out.println("Introduce el modelo del coche: ");
                coche.setModelo(scanner.nextLine());
            }case 2 -> {
                coche.setModelo(null);
            }

        }

        System.out.println("Modificar version");
        System.out.println("1. Si");
        System.out.println("2. No");

        opcion2 = ComprobacionOpcion.leerOpcion(1, 2);

        switch (opcion2) {

            case 1 -> {
                System.out.println("Introduce la version del coche: ");
                coche.setVersion(scanner.nextLine());
            }case 2 -> {
                coche.setVersion(null);
            }

        }

        System.out.println("Modificar matricula");
        System.out.println("1. Si");
        System.out.println("2. No");

        opcion2 = ComprobacionOpcion.leerOpcion(1, 2);

        switch (opcion2) {

            case 1 -> {
                System.out.println("Introduce la matricula: ");
                coche.setMatricula(scanner.nextLine());
            }case 2 -> {
                coche.setMatricula(null);
            }

        }

        System.out.println("Modificar bastidor");
        System.out.println("1. Si");
        System.out.println("2. No");

        opcion2 = ComprobacionOpcion.leerOpcion(1, 2);

        switch (opcion2) {

            case 1 -> {
                System.out.println("Introduce el bastidor del coche: ");
                coche.setBastidor(scanner.nextLine());
            }case 2 -> {
                coche.setBastidor(null);
            }

        }

        System.out.println("Modificar anio");
        System.out.println("1. Si");
        System.out.println("2. No");

        opcion2 = ComprobacionOpcion.leerOpcion(1, 2);

        switch (opcion2) {

            case 1 -> {
                System.out.println("Introduce el año del coche: ");

                int anio = -1;

                while (true) {
                    if (scanner.hasNextInt()) {
                        anio = scanner.nextInt();
                        scanner.nextLine();

                        if (anio >= 1900) {
                            break;
                        } else {
                            System.out.println("El año debe ser mayor o igual a 1900:");
                        }

                    } else {
                        System.out.println("Debes introducir un número válido:");
                        scanner.nextLine();
                    }
                }

                coche.setAnio(anio);

            }case 2 -> {
                coche.setAnio(-1);
            }

        }

        System.out.println("Modificar kilometros");
        System.out.println("1. Si");
        System.out.println("2. No");

        opcion2 = ComprobacionOpcion.leerOpcion(1, 2);

        switch (opcion2) {

            case 1 -> {
                System.out.println("Introduce los kilometros del coche: ");
                if (scanner.hasNextInt()) {
                    coche.setKilometros(scanner.nextInt());
                    scanner.nextLine();
                } else {
                    System.out.println("Error: debes introducir un número.");
                    scanner.nextLine();
                }
            }case 2 -> {
                coche.setKilometros(-1);
            }

        }

        System.out.println("Modificar combustible");
        System.out.println("1. Si");
        System.out.println("2. No");

        opcion2 = ComprobacionOpcion.leerOpcion(1, 2);

        switch (opcion2) {

            case 1 -> {
                coche.setCombustible(seleccionarCombustible());
            }case 2 -> {
                coche.setCombustible(null);
            }

        }

        System.out.println("Modificar cambio");
        System.out.println("1. Si");
        System.out.println("2. No");

        opcion2 = ComprobacionOpcion.leerOpcion(1, 2);

        switch (opcion2) {

            case 1 -> {
                coche.setCambio(seleccionarTipoCambio());
            }case 2 -> {
                coche.setCambio(null);
            }

        }

        System.out.println("Modificar color");
        System.out.println("1. Si");
        System.out.println("2. No");

        opcion2 = ComprobacionOpcion.leerOpcion(1, 2);

        switch (opcion2) {

            case 1 -> {
                System.out.println("Introduce el color del coche: ");
                coche.setColor(scanner.nextLine());
            }case 2 -> {
                coche.setColor(null);
            }

        }

        System.out.println("Modificar precio");
        System.out.println("1. Si");
        System.out.println("2. No");

        opcion2 = ComprobacionOpcion.leerOpcion(1, 2);

        switch (opcion2) {

            case 1 -> {
                System.out.println("Introduce el precio del coche: ");
                if (scanner.hasNextInt()) {
                    coche.setPrecio(scanner.nextInt());
                    scanner.nextLine();
                } else {
                    System.out.println("Error: debes introducir un número.");
                    scanner.nextLine();
                }
            }case 2 -> {
                coche.setPrecio(-1);
            }

        }

        System.out.println("Modificar estado");
        System.out.println("1. Si");
        System.out.println("2. No");

        opcion2 = ComprobacionOpcion.leerOpcion(1, 2);

        switch (opcion2) {

            case 1 -> {
                coche.setEstado(seleccionarEstadoCoche());
            }case 2 -> {
                coche.setEstado(null);
            }

        }

        cocheController.modificarCoche(opcion, coche);

    }

    public void buscarCoche() {

        List<Coche> coches = cocheController.listarCoches();

        if (coches.isEmpty()) {
            System.out.println("No hay coches registrados.");
            return;
        }

        System.out.println("Que coche quieres ver: ");

        for (Coche coche : coches) {
            System.out.println(coche.getId() +  ". " + coche.getMatricula());
        }

        Coche coche = cocheController.buscarCoche(ComprobacionOpcion.leerInt());

        if (coche != null) {
            System.out.println(coche.toString());
        }else  {
            System.out.println("No existe el coche con ese id");
        }

    }

}