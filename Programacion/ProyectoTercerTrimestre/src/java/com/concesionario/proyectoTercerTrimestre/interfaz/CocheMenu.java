package com.concesionario.proyectoTercerTrimestre.interfaz;

import com.concesionario.proyectoTercerTrimestre.controller.CocheController;
import com.concesionario.proyectoTercerTrimestre.entities.Coche;
import com.concesionario.proyectoTercerTrimestre.entities.Combustible;
import com.concesionario.proyectoTercerTrimestre.entities.EstadoCoche;
import com.concesionario.proyectoTercerTrimestre.entities.TipoCambio;
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
            System.out.println("0. Volver");
            System.out.print("Elige una opcion: ");

            opcion = Integer.parseInt(scanner.nextLine());

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
        int anio = Integer.parseInt(scanner.nextLine());

        System.out.print("Kilometros: ");
        int kilometros = Integer.parseInt(scanner.nextLine());

        Combustible combustible = seleccionarCombustible();

        TipoCambio cambio = seleccionarTipoCambio();

        System.out.print("Color: ");
        String color = scanner.nextLine();

        System.out.print("Precio: ");
        double precio = Double.parseDouble(scanner.nextLine());

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

        int opcion = Integer.parseInt(scanner.nextLine());

        switch (opcion) {
            case 1:
                return Combustible.GASOLINA;
            case 2:
                return Combustible.DIESEL;
            case 3:
                return Combustible.HIBRIDO;
            case 4:
                return Combustible.ELECTRICO;
            case 5:
                return Combustible.GLP;
            default:
                System.out.println("Opcion no valida. Se asigna Gasolina por defecto.");
                return Combustible.GASOLINA;
        }
    }

    private TipoCambio seleccionarTipoCambio() {
        System.out.println("Cambio:");
        System.out.println("1. Manual");
        System.out.println("2. Automatico");
        System.out.print("Elige una opcion: ");

        int opcion = Integer.parseInt(scanner.nextLine());

        switch (opcion) {
            case 1:
                return TipoCambio.MANUAL;
            case 2:
                return TipoCambio.AUTOMATICO;
            default:
                System.out.println("Opcion no valida. Se asigna Manual por defecto.");
                return TipoCambio.MANUAL;
        }
    }

    private EstadoCoche seleccionarEstadoCoche() {
        System.out.println("Estado:");
        System.out.println("1. Disponible");
        System.out.println("2. Reservado");
        System.out.println("3. Vendido");
        System.out.print("Elige una opcion: ");

        int opcion = Integer.parseInt(scanner.nextLine());

        switch (opcion) {
            case 1:
                return EstadoCoche.DISPONIBLE;
            case 2:
                return EstadoCoche.RESERVADO;
            case 3:
                return EstadoCoche.VENDIDO;
            default:
                System.out.println("Opcion no valida. Se asigna Disponible por defecto.");
                return EstadoCoche.DISPONIBLE;
        }
    }

    private void eliminarCoche() {
        System.out.println("\n--- Eliminar coche ---");

        System.out.print("Introduce el ID del coche: ");
        int id = Integer.parseInt(scanner.nextLine());

        cocheController.eliminarCoche(id);
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

}