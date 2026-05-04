package com.concesionario.proyectoTercerTrimestre.repositories.impl;

import com.concesionario.proyectoTercerTrimestre.database.DataBaseConnection;
import com.concesionario.proyectoTercerTrimestre.entities.Cliente;
import com.concesionario.proyectoTercerTrimestre.entities.RolUsuario;
import com.concesionario.proyectoTercerTrimestre.entities.Usuario;
import com.concesionario.proyectoTercerTrimestre.repositories.UsuarioRepository;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class UsuarioRepositoryImpl implements UsuarioRepository {

    @Override
    public Usuario findById(int id) {

        Usuario usuario = null;

        try (Connection connection = DataBaseConnection.getConnection();){

            String query = "SELECT * FROM usuarios WHERE id = ?";

            PreparedStatement preparedStatement = connection.prepareStatement(query);

            preparedStatement.setInt(1,id);

            ResultSet resultSet = preparedStatement.executeQuery();

            while (resultSet.next()){
                usuario =  new Usuario(resultSet.getInt("id"), resultSet.getString("nombre"), resultSet.getString("email"), RolUsuario.valueOf(resultSet.getString("rol").toUpperCase()), resultSet.getString("password_hash"));
            }

        }catch (SQLException e){
            e.printStackTrace();
        }

        return usuario;

    }

    @Override
    public List<Usuario> findAll() {

        List<Usuario> usuarios = new ArrayList<>();

        try (Connection connection = DataBaseConnection.getConnection();){

            String query = "SELECT * FROM usuarios";

            PreparedStatement preparedStatement = connection.prepareStatement(query);

            ResultSet resultSet = preparedStatement.executeQuery();

            while (resultSet.next()){
                Usuario usuario =  new Usuario(resultSet.getInt("id"), resultSet.getString("nombre"), resultSet.getString("email"), RolUsuario.valueOf(resultSet.getString("rol").toUpperCase()), resultSet.getString("password_hash"));

                usuarios.add(usuario);
            }

        }catch (SQLException e){
            e.printStackTrace();
        }

        return usuarios;

    }

}
