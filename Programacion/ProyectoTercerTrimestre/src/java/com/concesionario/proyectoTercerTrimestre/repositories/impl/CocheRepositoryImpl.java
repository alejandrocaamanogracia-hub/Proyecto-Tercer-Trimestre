package com.concesionario.proyectoTercerTrimestre.repositories.impl;

import com.concesionario.proyectoTercerTrimestre.database.DataBaseConnection;
import com.concesionario.proyectoTercerTrimestre.entities.Cliente;
import com.concesionario.proyectoTercerTrimestre.entities.Coche;
import com.concesionario.proyectoTercerTrimestre.repositories.CocheRepository;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class CocheRepositoryImpl implements CocheRepository {

    @Override
    public void crearCoche(Coche coche) {
        String sql = """
                INSERT INTO coches 
                (marca, modelo, version, matricula, bastidor, anio, kilometros, combustible, cambio, color, precio, estado)
                VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)
                """;

        try {
            Connection connection = DataBaseConnection.getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement(sql);

            preparedStatement.setString(1, coche.getMarca());
            preparedStatement.setString(2, coche.getModelo());
            preparedStatement.setString(3, coche.getVersion());
            preparedStatement.setString(4, coche.getMatricula());
            preparedStatement.setString(5, coche.getBastidor());
            preparedStatement.setInt(6, coche.getAnio());
            preparedStatement.setInt(7, coche.getKilometros());
            preparedStatement.setString(8, coche.getCombustible());
            preparedStatement.setString(9, coche.getCambio());
            preparedStatement.setString(10, coche.getColor());
            preparedStatement.setDouble(11, coche.getPrecio());
            preparedStatement.setString(12, coche.getEstado());

            preparedStatement.executeUpdate();

        } catch (SQLException e) {
            System.out.println("Error al crear el coche.");
            e.printStackTrace();
        }
    }

    @Override
    public void eliminarCoche(int id) {
        String sql = "DELETE FROM coches WHERE id = ?";

        try {
            Connection connection = DataBaseConnection.getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement(sql);

            preparedStatement.setInt(1, id);
            preparedStatement.executeUpdate();

        } catch (SQLException e) {
            System.out.println("Error al eliminar el coche.");
            e.printStackTrace();
        }
    }

    @Override
    public List<Coche> listarCoches() {
        List<Coche> coches = new ArrayList<>();

        String sql = """
                SELECT id, marca, modelo, version, matricula, bastidor, anio, kilometros,
                       combustible, cambio, color, precio, estado
                FROM coches
                ORDER BY id
                """;

        try {
            Connection connection = DataBaseConnection.getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            ResultSet resultSet = preparedStatement.executeQuery();

            while (resultSet.next()) {
                Coche coche = new Coche();

                coche.setId(resultSet.getInt("id"));
                coche.setMarca(resultSet.getString("marca"));
                coche.setModelo(resultSet.getString("modelo"));
                coche.setVersion(resultSet.getString("version"));
                coche.setMatricula(resultSet.getString("matricula"));
                coche.setBastidor(resultSet.getString("bastidor"));
                coche.setAnio(resultSet.getInt("anio"));
                coche.setKilometros(resultSet.getInt("kilometros"));
                coche.setCombustible(resultSet.getString("combustible"));
                coche.setCambio(resultSet.getString("cambio"));
                coche.setColor(resultSet.getString("color"));
                coche.setPrecio(resultSet.getDouble("precio"));
                coche.setEstado(resultSet.getString("estado"));

                coches.add(coche);
            }

        } catch (SQLException e) {
            System.out.println("Error al listar los coches.");
            e.printStackTrace();
        }

        return coches;
    }

    @Override
    public Coche buscarCoche(int id){

        String sql = "SELECT * FROM coches WHERE id = ?";
        Coche coche = null;

        try (Connection connection = DataBaseConnection.getConnection();){

            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setInt(1, id);
            ResultSet resultSet = preparedStatement.executeQuery();

            while (resultSet.next()) {

                coche = new Coche(resultSet.getInt("id"), resultSet.getString("marca"), resultSet.getString("modelo"), resultSet.getString("version"), resultSet.getString("matricula"), resultSet.getString("bastidor"), resultSet.getInt("anio"), resultSet.getInt("kilometros"), resultSet.getString("combustible"), resultSet.getString("cambio"), resultSet.getString("color"), resultSet.getDouble("precio"), resultSet.getString("estado"));
            }

        }catch (SQLException e){
            System.out.println("Error al buscar el cliente.");
            e.printStackTrace();
        }

        return coche;

    }

}