package com.concesionario.proyectoTercerTrimestre.repositories.impl;

import com.concesionario.proyectoTercerTrimestre.database.DataBaseConnection;
import com.concesionario.proyectoTercerTrimestre.entities.Coche;
import com.concesionario.proyectoTercerTrimestre.repositories.CocheRepository;
import com.concesionario.proyectoTercerTrimestre.entities.Combustible;
import com.concesionario.proyectoTercerTrimestre.entities.EstadoCoche;
import com.concesionario.proyectoTercerTrimestre.entities.TipoCambio;

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
            preparedStatement.setString(8, coche.getCombustible().getValorDb());
            preparedStatement.setString(9, coche.getCambio().getValorDb());
            preparedStatement.setString(10, coche.getColor());
            preparedStatement.setDouble(11, coche.getPrecio());
            preparedStatement.setString(12, coche.getEstado().getValorDb());

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
                coche.setCombustible(Combustible.fromDb(resultSet.getString("combustible")));
                coche.setCambio(TipoCambio.fromDb(resultSet.getString("cambio")));
                coche.setColor(resultSet.getString("color"));
                coche.setPrecio(resultSet.getDouble("precio"));
                coche.setEstado(EstadoCoche.fromDb(resultSet.getString("estado")));

                coches.add(coche);
            }

        } catch (SQLException e) {
            System.out.println("Error al listar los coches.");
            e.printStackTrace();
        }

        return coches;
    }

    @Override
    public void modificarCoche(int id, Coche coche) {

        String sql = "UPDATE coches SET marca = ? WHERE id = ?";

        try (Connection connection = DataBaseConnection.getConnection()){

            if (coche.getMarca() != null) {

                PreparedStatement preparedStatement = connection.prepareStatement(sql);
                preparedStatement.setString(1, coche.getMarca());
                preparedStatement.setInt(2, id);
                preparedStatement.executeUpdate();

            }

            sql = "UPDATE coches SET modelo = ? WHERE id = ?";

            if (coche.getModelo() != null) {

                PreparedStatement preparedStatement = connection.prepareStatement(sql);
                preparedStatement.setString(1, coche.getModelo());
                preparedStatement.setInt(2, id);
                preparedStatement.executeUpdate();

            }

            sql = "UPDATE coches SET version = ? WHERE id = ?";

            if (coche.getVersion() != null) {

                PreparedStatement preparedStatement = connection.prepareStatement(sql);
                preparedStatement.setString(1, coche.getVersion());
                preparedStatement.setInt(2, id);
                preparedStatement.executeUpdate();

            }

            sql = "UPDATE coches SET matricula = ? WHERE id = ?";

            if (coche.getMatricula() != null) {

                PreparedStatement preparedStatement = connection.prepareStatement(sql);
                preparedStatement.setString(1, coche.getMatricula());
                preparedStatement.setInt(2, id);
                preparedStatement.executeUpdate();

            }

            sql = "UPDATE coches SET bastidor = ? WHERE id = ?";

            if (coche.getBastidor() != null) {

                PreparedStatement preparedStatement = connection.prepareStatement(sql);
                preparedStatement.setString(1, coche.getBastidor());
                preparedStatement.setInt(2, id);
                preparedStatement.executeUpdate();

            }

            sql = "UPDATE coches SET anio = ? WHERE id = ?";

            if (coche.getAnio() != -1) {

                PreparedStatement preparedStatement = connection.prepareStatement(sql);
                preparedStatement.setInt(1, coche.getAnio());
                preparedStatement.setInt(2, id);
                preparedStatement.executeUpdate();

            }

            sql = "UPDATE coches SET kilometros = ? WHERE id = ?";

            if (coche.getKilometros() != -1) {

                PreparedStatement preparedStatement = connection.prepareStatement(sql);
                preparedStatement.setInt(1, coche.getKilometros());
                preparedStatement.setInt(2, id);
                preparedStatement.executeUpdate();

            }

            sql = "UPDATE coches SET combustible = ? WHERE id = ?";

            if (coche.getCombustible() != null) {

                PreparedStatement preparedStatement = connection.prepareStatement(sql);
                preparedStatement.setString(1, coche.getCombustible().getValorDb());
                preparedStatement.setInt(2, id);
                preparedStatement.executeUpdate();

            }

            sql = "UPDATE coches SET cambio = ? WHERE id = ?";

            if (coche.getCambio() != null) {

                PreparedStatement preparedStatement = connection.prepareStatement(sql);
                preparedStatement.setString(1, coche.getCambio().getValorDb());
                preparedStatement.setInt(2, id);
                preparedStatement.executeUpdate();

            }

            sql = "UPDATE coches SET color = ? WHERE id = ?";

            if (coche.getColor() != null) {

                PreparedStatement preparedStatement = connection.prepareStatement(sql);
                preparedStatement.setString(1, coche.getColor());
                preparedStatement.setInt(2, id);
                preparedStatement.executeUpdate();

            }

            sql = "UPDATE coches SET precio = ? WHERE id = ?";

            if (coche.getPrecio() != -1) {

                PreparedStatement preparedStatement = connection.prepareStatement(sql);
                preparedStatement.setDouble(1, coche.getPrecio());
                preparedStatement.setInt(2, id);
                preparedStatement.executeUpdate();

            }

            sql = "UPDATE coches SET estado = ? WHERE id = ?";

            if (coche.getEstado() != null) {

                PreparedStatement preparedStatement = connection.prepareStatement(sql);
                preparedStatement.setString(1, coche.getEstado().getValorDb());
                preparedStatement.setInt(2, id);
                preparedStatement.executeUpdate();

            }

        }catch (SQLException e){
            System.out.println("Error al modificar el coche.");
            e.printStackTrace();
        }

    }

    @Override
    public Coche buscarCoche(int id){

        Coche coche = new Coche();

        String sql =  "SELECT * FROM coches WHERE id = ?";

        try (Connection connection = DataBaseConnection.getConnection()){

            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setInt(1, id);
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()){
                coche.setId(resultSet.getInt("id"));
                coche.setMarca(resultSet.getString("marca"));
                coche.setModelo(resultSet.getString("modelo"));
                coche.setVersion(resultSet.getString("version"));
                coche.setMatricula(resultSet.getString("matricula"));
                coche.setBastidor(resultSet.getString("bastidor"));
                coche.setAnio(resultSet.getInt("anio"));
                coche.setKilometros(resultSet.getInt("kilometros"));
                coche.setCombustible(Combustible.fromDb(resultSet.getString("combustible")));
                coche.setCambio(TipoCambio.fromDb(resultSet.getString("cambio")));
                coche.setColor(resultSet.getString("color"));
                coche.setPrecio(resultSet.getDouble("precio"));
                coche.setEstado(EstadoCoche.fromDb(resultSet.getString("estado")));
            }

        }catch (SQLException e){
            System.out.println("Error al verificar el coche.");
            e.printStackTrace();
        }

        return coche;

    }

}