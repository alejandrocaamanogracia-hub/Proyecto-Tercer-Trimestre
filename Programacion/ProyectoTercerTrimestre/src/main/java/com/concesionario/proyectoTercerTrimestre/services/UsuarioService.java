package com.concesionario.proyectoTercerTrimestre.services;

import com.concesionario.proyectoTercerTrimestre.entities.Usuario;
import com.concesionario.proyectoTercerTrimestre.repositories.UsuarioRepository;
import com.concesionario.proyectoTercerTrimestre.repositories.impl.UsuarioRepositoryImpl;

import java.util.List;

public class UsuarioService {

    UsuarioRepository usuarioRepository = new UsuarioRepositoryImpl();

    public Usuario findById(int id) {

        Usuario usuario = usuarioRepository.findById(id);

        return usuario;

    }

    public List<Usuario> findAll() {

        List<Usuario> usuarios = usuarioRepository.findAll();

        if (usuarios.isEmpty()) {
            throw new RuntimeException("No hay clientes");
        }

        return usuarios;

    }

}
