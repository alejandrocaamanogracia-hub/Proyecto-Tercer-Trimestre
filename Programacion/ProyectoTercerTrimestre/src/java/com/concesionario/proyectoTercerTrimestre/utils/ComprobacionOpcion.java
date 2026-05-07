package com.concesionario.proyectoTercerTrimestre.utils;

import java.util.Scanner;

public class ComprobacionOpcion {

    public static int leerOpcion(int min, int max) {
        while (true) {
            try {
                Scanner scanner =  new Scanner(System.in);
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
        Scanner scanner = new Scanner(System.in);

        while (true) {

            try {

                return Integer.parseInt(scanner.nextLine());

            } catch (NumberFormatException e) {

                System.out.println("Introduce un número válido.");
            }
        }
    }

    public static double leerDouble() {

        Scanner scanner = new Scanner(System.in);

        while (true) {

            try {

                return Double.parseDouble(scanner.nextLine());

            } catch (NumberFormatException e) {

                System.out.println("Introduce un número decimal válido.");
            }
        }
    }

}
