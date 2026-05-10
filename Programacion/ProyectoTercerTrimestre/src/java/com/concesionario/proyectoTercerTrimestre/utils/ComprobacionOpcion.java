package com.concesionario.proyectoTercerTrimestre.utils;

import java.util.Scanner;

public class ComprobacionOpcion {

    private static final Scanner scanner = new Scanner(System.in);

    public static int leerOpcion(int min, int max) {
        while (true) {
            try {
                int op = Integer.parseInt(scanner.nextLine());

                if (op >= min && op <= max) {
                    return op;
                }

                System.out.println("Opción fuera de rango.");

            } catch (NumberFormatException e) {
                System.out.println("Introduce un número válido.");
            }
        }
    }

    public static int leerInt() {
        while (true) {
            try {
                return Integer.parseInt(scanner.nextLine());
            } catch (NumberFormatException e) {
                System.out.println("Introduce un número válido.");
            }
        }
    }

    public static double leerDouble() {
        while (true) {
            try {
                return Double.parseDouble(scanner.nextLine());
            } catch (NumberFormatException e) {
                System.out.println("Introduce un número decimal válido.");
            }
        }
    }
}