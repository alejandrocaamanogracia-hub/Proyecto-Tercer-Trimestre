package com.concesionario.proyectoTercerTrimestre.interfaz;

import com.concesionario.proyectoTercerTrimestre.controller.UsuarioController;
import com.concesionario.proyectoTercerTrimestre.entities.Usuario;

import java.util.List;
import java.util.Scanner;

public class UsuarioMenu {

    private final Scanner scanner;
    private final UsuarioController usuarioController;

    public UsuarioMenu() {
        this.scanner = new Scanner(System.in);
        this.usuarioController = new UsuarioController();
    }

    public void mostrarMenuUsuarios() {
        int opcion;

        do {
            System.out.println("\n===== MENU USUARIOS =====");
            System.out.println("1. Crear usuario");
            System.out.println("2. Eliminar usuario");
            System.out.println("3. Listar usuarios");
            System.out.println("4. Exportar usuarios a TXT");
            System.out.println("0. Volver");
            System.out.print("Elige una opcion: ");

            opcion = Integer.parseInt(scanner.nextLine());

            switch (opcion) {
                case 1:
                    crearUsuario();
                    break;
                case 2:
                    eliminarUsuario();
                    break;
                case 3:
                    listarUsuarios();
                    break;
                case 4:
                    exportarUsuariosTxt();
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

    private void crearUsuario() {
        System.out.println("\n--- Crear usuario ---");

        System.out.print("Nombre: ");
        String nombre = scanner.nextLine();

        System.out.print("Email: ");
        String email = scanner.nextLine();

        System.out.print("Rol: ");
        String rol = scanner.nextLine();

        System.out.print("Password hash: ");
        String passwordHash = scanner.nextLine();

        usuarioController.crearUsuario(nombre, email, rol, passwordHash);
    }

    private void eliminarUsuario() {
        System.out.println("\n--- Eliminar usuario ---");

        System.out.print("Introduce el ID del usuario: ");
        int id = Integer.parseInt(scanner.nextLine());

        usuarioController.eliminarUsuario(id);
    }

    private void listarUsuarios() {
        System.out.println("\n--- Lista de usuarios ---");

        List<Usuario> usuarios = usuarioController.listarUsuarios();

        if (usuarios.isEmpty()) {
            System.out.println("No hay usuarios registrados.");
            return;
        }

        for (Usuario usuario : usuarios) {
            System.out.println(usuario);
        }
    }

    private void exportarUsuariosTxt() {
        usuarioController.exportarUsuariosTxt();
    }
}