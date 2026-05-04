package com.concesionario.proyectoTercerTrimestre.repositories.impl;

import com.concesionario.proyectoTercerTrimestre.database.DataBaseConnection;
import com.concesionario.proyectoTercerTrimestre.entities.Cliente;
import com.concesionario.proyectoTercerTrimestre.repositories.ClienteRepository;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class ClienteRepositoryImpl implements ClienteRepository {

    @Override
    public Cliente findById(int id) {

        Cliente cliente = null;

        try (Connection connection = DataBaseConnection.getConnection();){

            String query = "SELECT * FROM clientes WHERE id = ?";

            PreparedStatement preparedStatement = connection.prepareStatement(query);

            preparedStatement.setInt(1,id);

            ResultSet resultSet = preparedStatement.executeQuery();

            while (resultSet.next()){
                cliente =  new Cliente(resultSet.getInt("id"), resultSet.getString("nombre"), resultSet.getString("email"), resultSet.getString("telefono"), resultSet.getString("direccion"));
            }

        }catch (SQLException e){
            e.printStackTrace();
        }

        return cliente;

    }

    @Override
    public List<Cliente> findAll() {

        List<Cliente> clientes = new ArrayList<>();

        try (Connection connection = DataBaseConnection.getConnection();){

            String query = "SELECT * FROM clientes";

            PreparedStatement preparedStatement = connection.prepareStatement(query);

            ResultSet resultSet = preparedStatement.executeQuery();

            while (resultSet.next()){
                Cliente cliente =  new Cliente(resultSet.getInt("id"), resultSet.getString("nombre"), resultSet.getString("email"), resultSet.getString("telefono"), resultSet.getString("direccion"));

                clientes.add(cliente);
            }

        }catch (SQLException e){
            e.printStackTrace();
        }

        return clientes;

    }

}
