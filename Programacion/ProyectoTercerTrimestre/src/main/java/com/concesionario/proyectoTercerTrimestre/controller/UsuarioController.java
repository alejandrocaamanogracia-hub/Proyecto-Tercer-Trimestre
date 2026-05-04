package com.concesionario.proyectoTercerTrimestre.controller;

import com.concesionario.proyectoTercerTrimestre.entities.Usuario;
import com.concesionario.proyectoTercerTrimestre.services.UsuarioService;

import java.util.List;

public class UsuarioController {

    UsuarioService usuarioService = new UsuarioService();

    public Usuario findById(int id) {
        return usuarioService.findById(id);
    }

    public List<Usuario> findAll() {
        return usuarioService.findAll();
    }

}
