package com.concesionario.proyectoTercerTrimestre.interfaz;

import java.util.Scanner;

public class Menu {

    private final Scanner scanner;
    private final ClienteMenu clienteMenu;
    private final CocheMenu cocheMenu;
    private final UsuarioMenu usuarioMenu;

    public Menu() {
        this.scanner = new Scanner(System.in);
        this.clienteMenu = new ClienteMenu();
        this.cocheMenu = new CocheMenu();
        this.usuarioMenu = new UsuarioMenu();
    }

    public void mostrarMenuPrincipal() {
        int opcion;

        do {
            System.out.println("\n===== MENU PRINCIPAL =====");
            System.out.println("1. Gestionar clientes");
            System.out.println("2. Gestionar coches");
            System.out.println("3. Gestionar usuarios");
            System.out.println("0. Salir");
            System.out.print("Elige una opcion: ");

            opcion = Integer.parseInt(scanner.nextLine());

            switch (opcion) {
                case 1:
                    clienteMenu.mostrarMenuClientes();
                    break;
                case 2:
                    cocheMenu.mostrarMenuCoches();
                    break;
                case 3:
                    usuarioMenu.mostrarMenuUsuarios();
                    break;
                case 0:
                    System.out.println("Saliendo de la aplicacion...");
                    break;
                default:
                    System.out.println("Opcion no valida.");
                    break;
            }

        } while (opcion != 0);
    }
}