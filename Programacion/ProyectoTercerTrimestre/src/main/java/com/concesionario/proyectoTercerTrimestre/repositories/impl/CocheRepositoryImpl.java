package com.concesionario.proyectoTercerTrimestre.repositories.impl;

import com.concesionario.proyectoTercerTrimestre.database.DataBaseConnection;
import com.concesionario.proyectoTercerTrimestre.entities.*;
import com.concesionario.proyectoTercerTrimestre.repositories.CocheRepository;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class CocheRepositoryImpl implements CocheRepository {

    @Override
    public Coche findById(int id) {

        Coche coche = null;

        try (Connection connection = DataBaseConnection.getConnection();){

            String query = "SELECT * FROM coches WHERE id = ?";

            PreparedStatement preparedStatement = connection.prepareStatement(query);

            preparedStatement.setInt(1,id);

            ResultSet resultSet = preparedStatement.executeQuery();

            while (resultSet.next()){
                coche =  new Coche(resultSet.getInt("id"), resultSet.getString("marca"), resultSet.getString("modelo"), resultSet.getString("version"), resultSet.getString("matricula"), resultSet.getString("bastidor"), resultSet.getInt("anio"), resultSet.getInt("kilometros"), Combustible.valueOf(resultSet.getString("combustible").toUpperCase()), TipoCambio.valueOf(resultSet.getString("cambio").toUpperCase()), resultSet.getString("color"), resultSet.getInt("precio"), EstadoCoche.valueOf(resultSet.getString("estado").toUpperCase()));
            }

        }catch (SQLException e){
            e.printStackTrace();
        }

        return coche;

    }

    @Override
    public List<Coche> findAll() {

        List<Coche> coches = new ArrayList<>();

        try (Connection connection = DataBaseConnection.getConnection();){

            String query = "SELECT * FROM coches";

            PreparedStatement preparedStatement = connection.prepareStatement(query);

            ResultSet resultSet = preparedStatement.executeQuery();

            while (resultSet.next()){
                Coche coche =  new Coche(resultSet.getInt("id"), resultSet.getString("marca"), resultSet.getString("modelo"), resultSet.getString("version"), resultSet.getString("matricula"), resultSet.getString("bastidor"), resultSet.getInt("anio"), resultSet.getInt("kilometros"), Combustible.valueOf(resultSet.getString("combustible").toUpperCase()), TipoCambio.valueOf(resultSet.getString("cambio").toUpperCase()), resultSet.getString("color"), resultSet.getInt("precio"), EstadoCoche.valueOf(resultSet.getString("estado").toUpperCase()));

                coches.add(coche);
            }

        }catch (SQLException e){
            e.printStackTrace();
        }

        return coches;

    }

}
