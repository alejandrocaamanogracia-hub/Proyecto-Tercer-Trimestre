package com.concesionario.proyectoTercerTrimestre.utils;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.function.IntPredicate;

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

    public static String leerTexto() {
        return scanner.nextLine();
    }

    public static String leerTextoObligatorio(String mensaje) {
        String texto;

        do {
            System.out.print(mensaje);
            texto = scanner.nextLine();

            if (texto.isBlank()) {
                System.out.println("El campo no puede estar vacio.");
            }

        } while (texto.isBlank());

        return texto;
    }

    public static int leerIntMinimo(String mensaje, int minimo) {
        int numero;

        do {
            System.out.print(mensaje);
            numero = leerInt();

            if (numero < minimo) {
                System.out.println("El numero debe ser mayor o igual a " + minimo + ".");
            }

        } while (numero < minimo);

        return numero;
    }

    public static double leerDoubleMinimo(String mensaje, double minimo) {
        double numero;

        do {
            System.out.print(mensaje);
            numero = leerDouble();

            if (numero < minimo) {
                System.out.println("El numero debe ser mayor o igual a " + minimo + ".");
            }

        } while (numero < minimo);

        return numero;
    }


    public static LocalDate leerFecha(String mensaje, DateTimeFormatter formatter) {
        while (true) {
            System.out.print(mensaje);
            String input = leerTexto();

            if (input.isBlank()) {
                return LocalDate.now();
            }

            try {
                return LocalDate.parse(input, formatter);
            } catch (DateTimeParseException e) {
                System.out.println("Formato incorrecto. Introduce yyyy-MM-dd.");
            }
        }
    }

    public static int leerIdExistente(String mensaje, IntPredicate comprobadorExistencia, String mensajeError) {
        int id;
        boolean existe;

        do {
            id = leerIntMinimo(mensaje, 1);
            existe = comprobadorExistencia.test(id);

            if (!existe) {
                System.out.println(mensajeError);
            }

        } while (!existe);

        return id;
    }

    public static String leerEmailObligatorio(String mensaje) {
        String email;

        do {
            System.out.print(mensaje);
            email = leerTexto();

            if (email.isBlank()) {
                System.out.println("El email no puede estar vacio.");
            } else if (!email.contains("@")) {
                System.out.println("El email debe tener un formato valido.");
            }

        } while (email.isBlank() || !email.contains("@"));

        return email;
    }
}