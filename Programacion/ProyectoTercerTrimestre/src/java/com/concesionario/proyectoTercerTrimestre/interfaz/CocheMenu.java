package com.concesionario.proyectoTercerTrimestre.interfaz;

import com.concesionario.proyectoTercerTrimestre.controller.CocheController;
import com.concesionario.proyectoTercerTrimestre.entities.Coche;

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
            System.out.println("5. Mostrar un coche en específico");
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
                    mostrarCocheEspecifico();
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

        System.out.print("Combustible: ");
        String combustible = scanner.nextLine();

        System.out.print("Cambio: ");
        String cambio = scanner.nextLine();

        System.out.print("Color: ");
        String color = scanner.nextLine();

        System.out.print("Precio: ");
        double precio = Double.parseDouble(scanner.nextLine());

        System.out.print("Estado: ");
        String estado = scanner.nextLine();

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

    private void mostrarCocheEspecifico(){

        System.out.println("Introduzca un id: ");
        int id = Integer.parseInt(scanner.nextLine());

        cocheController.buscarCoche(id).ifPresentOrElse(coche -> System.out.println(coche.toString()), () -> System.out.println("Coche no encontrado."));

    }

}