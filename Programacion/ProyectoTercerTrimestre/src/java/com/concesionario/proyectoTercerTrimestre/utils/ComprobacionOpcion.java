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

                System.out.println("Opcion fuera de rango.");
                System.out.print("Elige una opcion: ");

            } catch (NumberFormatException e) {
                System.out.println("Introduce un numero valido.");
                System.out.print("Elige una opcion: ");
            }
        }
    }

    public static int leerInt() {
        while (true) {
            try {
                return Integer.parseInt(scanner.nextLine());

            } catch (NumberFormatException e) {
                System.out.println("Introduce un numero valido.");
                System.out.print("Vuelve a intentarlo: ");
            }
        }
    }

    public static double leerDouble() {
        while (true) {
            try {
                return Double.parseDouble(scanner.nextLine().replace(",", "."));

            } catch (NumberFormatException e) {
                System.out.println("Introduce un numero decimal valido.");
                System.out.print("Vuelve a intentarlo: ");
            }
        }
    }
}