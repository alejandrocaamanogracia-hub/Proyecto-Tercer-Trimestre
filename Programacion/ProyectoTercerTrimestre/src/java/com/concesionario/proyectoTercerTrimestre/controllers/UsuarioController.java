package com.concesionario.proyectoTercerTrimestre.controllers;

import com.concesionario.proyectoTercerTrimestre.entities.RolUsuario;
import com.concesionario.proyectoTercerTrimestre.entities.Usuario;
import com.concesionario.proyectoTercerTrimestre.services.UsuarioService;

import java.util.List;

public class UsuarioController {

    private final UsuarioService usuarioService;

    public UsuarioController() {
        this.usuarioService = new UsuarioService();
    }

    public void crearUsuario(String nombre, String email, RolUsuario rol, String passwordHash) {
        Usuario usuario = new Usuario(nombre, email, rol, passwordHash);
        usuarioService.crearUsuario(usuario);
    }

    public boolean eliminarUsuario(int id) {
        return usuarioService.eliminarUsuario(id);
    }

    public boolean existeUsuario(int id) {
        return usuarioService.existeUsuario(id);
    }

    public List<Usuario> listarUsuarios() {
        return usuarioService.listarUsuarios();
    }

    public void exportarUsuariosTxt() {
        usuarioService.exportarUsuariosTxt();
    }

    public void modificarUsuario(int id, Usuario usuario) {
        usuarioService.modificarUsuario(id, usuario);
    }

    public Usuario buscarUsuario(int id) {
        return usuarioService.buscarUsuario(id);
    }

}