package com.concesionario.proyectoTercerTrimestre.services;

import com.concesionario.proyectoTercerTrimestre.entities.Usuario;
import com.concesionario.proyectoTercerTrimestre.repositories.UsuarioRepository;
import com.concesionario.proyectoTercerTrimestre.repositories.impl.UsuarioRepositoryImpl;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.List;

public class UsuarioService {

    private final UsuarioRepository usuarioRepository;

    public UsuarioService() {
        this.usuarioRepository = new UsuarioRepositoryImpl();
    }

    public void crearUsuario(Usuario usuario) {
        if (usuario == null) {
            System.out.println("El usuario no puede ser nulo.");
            return;
        }

        if (usuario.getNombre() == null || usuario.getNombre().isBlank()) {
            System.out.println("El nombre del usuario es obligatorio.");
            return;
        }

        if (usuario.getEmail() == null || usuario.getEmail().isBlank()) {
            System.out.println("El email del usuario es obligatorio.");
            return;
        }

        if (usuario.getRol() == null) {
            System.out.println("El rol del usuario es obligatorio.");
            return;
        }

        if (usuario.getPasswordHash() == null || usuario.getPasswordHash().isBlank()) {
            System.out.println("La contraseña/hash del usuario es obligatoria.");
            return;
        }

        usuarioRepository.crearUsuario(usuario);
        System.out.println("Usuario creado correctamente.");
    }

    public void eliminarUsuario(int id) {
        if (id <= 0) {
            System.out.println("El ID del usuario no es valido.");
            return;
        }

        usuarioRepository.eliminarUsuario(id);
        System.out.println("Usuario eliminado correctamente.");
    }

    public List<Usuario> listarUsuarios() {
        return usuarioRepository.listarUsuarios();
    }

    public void exportarUsuariosTxt() {
        List<Usuario> usuarios = usuarioRepository.listarUsuarios();

        if (usuarios.isEmpty()) {
            System.out.println("No hay usuarios para exportar.");
            return;
        }

        try (BufferedWriter writer = new BufferedWriter(new FileWriter("usuarios.txt"))) {

            writer.write("===== LISTADO DE USUARIOS =====");
            writer.newLine();
            writer.newLine();

            for (Usuario usuario : usuarios) {
                writer.write("ID: " + usuario.getId());
                writer.newLine();
                writer.write("Nombre: " + usuario.getNombre());
                writer.newLine();
                writer.write("Email: " + usuario.getEmail());
                writer.newLine();
                writer.write("Rol: " + usuario.getRol().getValorDb());
                writer.newLine();
                writer.write("Password Hash: " + usuario.getPasswordHash());
                writer.newLine();
                writer.write("----------------------------------");
                writer.newLine();
            }

            System.out.println("Usuarios exportados correctamente a usuarios.txt");

        } catch (IOException e) {
            System.out.println("Error al exportar los usuarios.");
            e.printStackTrace();
        }
    }
}