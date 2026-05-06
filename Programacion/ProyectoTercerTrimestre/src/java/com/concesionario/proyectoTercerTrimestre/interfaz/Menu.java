package com.concesionario.proyectoTercerTrimestre.interfaz;

import java.util.Scanner;

public class Menu {

    private final Scanner scanner;
    private final ClienteMenu clienteMenu;
    private final CocheMenu cocheMenu;
    private final UsuarioMenu usuarioMenu;
    private final InteraccionClienteMenu interaccionClienteMenu;
    private final VentaMenu ventaMenu;
    private final DetalleVentaMenu detalleVentaMenu;

    public Menu() {
        this.scanner = new Scanner(System.in);
        this.clienteMenu = new ClienteMenu();
        this.cocheMenu = new CocheMenu();
        this.usuarioMenu = new UsuarioMenu();
        this.interaccionClienteMenu = new InteraccionClienteMenu();
        this.ventaMenu = new VentaMenu();
        this.detalleVentaMenu = new DetalleVentaMenu();
    }

    public void mostrarMenuPrincipal() {
        int opcion;

        do {
            System.out.println("\n===== MENU PRINCIPAL =====");
            System.out.println("1. Gestionar clientes");
            System.out.println("2. Gestionar coches");
            System.out.println("3. Gestionar usuarios");
            System.out.println("4. Gestionar interacciones cliente");
            System.out.println("5. Gestionar ventas");
            System.out.println("6. Gestionar detalles de venta");
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
                case 4:
                    interaccionClienteMenu.mostrarMenuInteraccionesCliente();
                    break;
                case 5:
                    ventaMenu.mostrarMenuVentas();
                    break;
                case 6:
                    detalleVentaMenu.mostrarMenuDetalleVenta();
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