package com.concesionario.proyectoTercerTrimestre.repositories.impl;

import com.concesionario.proyectoTercerTrimestre.database.DataBaseConnection;
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
    public void crearUsuario(Usuario usuario) {
        String sql = "INSERT INTO usuarios (nombre, email, rol, password_hash) " +
                "VALUES (?, ?, ?, ?)";

        try {
            Connection connection = DataBaseConnection.getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement(sql);

            preparedStatement.setString(1, usuario.getNombre());
            preparedStatement.setString(2, usuario.getEmail());
            preparedStatement.setString(3, usuario.getRol());
            preparedStatement.setString(4, usuario.getPasswordHash());

            preparedStatement.executeUpdate();

        } catch (SQLException e) {
            System.out.println("Error al crear el usuario.");
            e.printStackTrace();
        }
    }

    @Override
    public void eliminarUsuario(int id) {
        String sql = "DELETE FROM usuarios WHERE id = ?";

        try {
            Connection connection = DataBaseConnection.getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement(sql);

            preparedStatement.setInt(1, id);
            preparedStatement.executeUpdate();

        } catch (SQLException e) {
            System.out.println("Error al eliminar el usuario.");
            e.printStackTrace();
        }
    }

    @Override
    public List<Usuario> listarUsuarios() {
        List<Usuario> usuarios = new ArrayList<>();

        String sql = "SELECT id, nombre, email, rol, password_hash " +
                "FROM usuarios " +
                "ORDER BY id";

        try {
            Connection connection = DataBaseConnection.getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            ResultSet resultSet = preparedStatement.executeQuery();

            while (resultSet.next()) {
                Usuario usuario = new Usuario();

                usuario.setId(resultSet.getInt("id"));
                usuario.setNombre(resultSet.getString("nombre"));
                usuario.setEmail(resultSet.getString("email"));
                usuario.setRol(resultSet.getString("rol"));
                usuario.setPasswordHash(resultSet.getString("password_hash"));

                usuarios.add(usuario);
            }

        } catch (SQLException e) {
            System.out.println("Error al listar los usuarios.");
            e.printStackTrace();
        }

        return usuarios;
    }
}