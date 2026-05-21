package com.concesionario.proyectoTercerTrimestre.interfaz;

import com.concesionario.proyectoTercerTrimestre.controllers.UsuarioController;
import com.concesionario.proyectoTercerTrimestre.entities.Usuario;
import com.concesionario.proyectoTercerTrimestre.entities.RolUsuario;
import com.concesionario.proyectoTercerTrimestre.utils.ComprobacionOpcion;

import java.util.List;


public class UsuarioMenu {


    private final UsuarioController usuarioController;

    public UsuarioMenu() {
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
            System.out.println("5. Modificar usuario");
            System.out.println("6. Buscar usuario");
            System.out.println("0. Volver");
            System.out.print("Elige una opcion: ");

            opcion = ComprobacionOpcion.leerInt();

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
                case 5:
                    modificarUsuario();
                    break;
                case 6:
                    buscarUsuario();
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

        String nombre = ComprobacionOpcion.leerTextoObligatorio("Nombre: ");
        String email = ComprobacionOpcion.leerEmailObligatorio("Email: ");

        RolUsuario rol = seleccionarRolUsuario();

        String passwordHash = ComprobacionOpcion.leerTextoObligatorio("Password hash: ");

        usuarioController.crearUsuario(nombre, email, rol, passwordHash);
    }

    private void eliminarUsuario() {
        System.out.println("\n--- Eliminar usuario ---");

        List<Usuario> usuarios = usuarioController.listarUsuarios();

        if (usuarios.isEmpty()) {
            System.out.println("No hay usuarios registrados.");
            return;
        }

        System.out.println("Usuarios disponibles:");

        for (Usuario usuario : usuarios) {
            System.out.println(
                    "ID: " + usuario.getId()
                            + " | Nombre: " + usuario.getNombre()
                            + " | Email: " + usuario.getEmail()
                            + " | Rol: " + usuario.getRol()
            );
        }

        int id = ComprobacionOpcion.leerIdExistente(
                "Introduce el ID del usuario que quieres eliminar: ",
                usuarioController::existeUsuario,
                "No existe ningun usuario con ese ID."
        );

        System.out.println("¿Seguro que quieres eliminar el usuario con ID " + id + "?");
        System.out.println("1. Sí");
        System.out.println("2. No");

        int confirmacion = ComprobacionOpcion.leerOpcion(1, 2);

        if (confirmacion == 1) {
            boolean eliminado = usuarioController.eliminarUsuario(id);

            if (eliminado) {
                System.out.println("Usuario eliminado correctamente.");
            }
        } else {
            System.out.println("Eliminacion cancelada.");
        }
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

    private void modificarUsuario() {
        System.out.println("\n--- Modificar usuario ---");

        List<Usuario> usuarios = usuarioController.listarUsuarios();

        if (usuarios.isEmpty()) {
            System.out.println("No hay usuarios registrados.");
            return;
        }

        System.out.println("Introduce el numero del usuario que quieres modificar: ");

        System.out.println("Usuarios disponibles:");

        int iterador = 1;
        for (Usuario usuarioActual : usuarios) {
            System.out.println(
                    iterador + ". Nombre: " + usuarioActual.getNombre()
                            + " | Email: " + usuarioActual.getEmail()
                            + " | Rol: " + usuarioActual.getRol()
                            + " | ID real: " + usuarioActual.getId()
            );
            iterador++;
        }

        int opcion = ComprobacionOpcion.leerOpcion(1, usuarios.size());

        Usuario usuarioActual = usuarios.get(opcion - 1);
        int idUsuario = usuarioActual.getId();

        Usuario usuarioModificado = new Usuario();

        usuarioModificado.setNombre(usuarioActual.getNombre());
        usuarioModificado.setEmail(usuarioActual.getEmail());
        usuarioModificado.setPasswordHash(usuarioActual.getPasswordHash());
        usuarioModificado.setRol(usuarioActual.getRol());

        System.out.println("\nModificar nombre actual: " + usuarioActual.getNombre());
        System.out.println("1. Si");
        System.out.println("2. No");

        if (ComprobacionOpcion.leerOpcion(1, 2) == 1) {
            usuarioModificado.setNombre(
                    ComprobacionOpcion.leerTextoObligatorio("Introduce el nombre: ")
            );
        }

        System.out.println("\nModificar email actual: " + usuarioActual.getEmail());
        System.out.println("1. Si");
        System.out.println("2. No");

        if (ComprobacionOpcion.leerOpcion(1, 2) == 1) {
            usuarioModificado.setEmail(
                    ComprobacionOpcion.leerEmailObligatorio("Introduce el email: ")
            );
        }

        System.out.println("\nModificar password actual: " + usuarioActual.getPasswordHash());
        System.out.println("1. Si");
        System.out.println("2. No");

        if (ComprobacionOpcion.leerOpcion(1, 2) == 1) {
            usuarioModificado.setPasswordHash(
                    ComprobacionOpcion.leerTextoObligatorio("Introduce el password: ")
            );
        }

        System.out.println("\nModificar rol actual: " + usuarioActual.getRol());
        System.out.println("1. Si");
        System.out.println("2. No");

        if (ComprobacionOpcion.leerOpcion(1, 2) == 1) {
            usuarioModificado.setRol(seleccionarRolUsuario());
        }

        usuarioController.modificarUsuario(idUsuario, usuarioModificado);
    }

    private RolUsuario seleccionarRolUsuario() {
        System.out.println("Rol:");
        System.out.println("1. Administrador");
        System.out.println("2. Comercial");
        System.out.println("3. Gestor");
        System.out.print("Elige una opcion: ");

        int opcionRol = ComprobacionOpcion.leerOpcion(1, 3);

        return switch (opcionRol) {
            case 1 -> RolUsuario.ADMINISTRADOR;
            case 2 -> RolUsuario.COMERCIAL;
            case 3 -> RolUsuario.GESTOR;
            default -> RolUsuario.COMERCIAL;
        };
    }
    public void buscarUsuario() {
        System.out.println("\n--- Buscar usuario ---");

        List<Usuario> usuarios = usuarioController.listarUsuarios();

        if (usuarios.isEmpty()) {
            System.out.println("No hay usuarios registrados.");
            return;
        }

        System.out.println("Que usuario quieres ver: ");

        for (Usuario usuario : usuarios) {
            System.out.println(
                    usuario.getId() + ". "
                            + usuario.getNombre()
                            + " | " + usuario.getEmail()
                            + " | " + usuario.getRol()
            );
        }

        System.out.print("Introduce el ID del usuario: ");
        Usuario usuario = usuarioController.buscarUsuario(ComprobacionOpcion.leerInt());

        if (usuario != null) {
            System.out.println(usuario);
        }
    }




}