package com.concesionario.proyectoTercerTrimestre.repositories;

import com.concesionario.proyectoTercerTrimestre.entities.Usuario;

import java.util.List;

public interface UsuarioRepository {

    Usuario findById(int id);

    List<Usuario> findAll();

}
