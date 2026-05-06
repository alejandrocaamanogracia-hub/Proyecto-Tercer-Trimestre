package com.concesionario.proyectoTercerTrimestre.repositories;

import com.concesionario.proyectoTercerTrimestre.entities.Usuario;

import java.util.List;

public interface UsuarioRepository {

    void crearUsuario(Usuario usuario);

    void eliminarUsuario(int id);

    List<Usuario> listarUsuarios();

    void modificarUsuario(int id, Usuario usuario);

}