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

        try (Connection connection = DataBaseConnection.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(sql)) {

            preparedStatement.setString(1, coche.getMarca());
            preparedStatement.setString(2, coche.getModelo());
            preparedStatement.setString(3, coche.getVersion());
            preparedStatement.setString(4, coche.getMatricula());
            preparedStatement.setString(5, coche.getBastidor());
            preparedStatement.setInt(6, coche.getAnio());
            preparedStatement.setInt(7, coche.getKilometros());

            if (coche.getCombustible() != null) {
                preparedStatement.setString(8, coche.getCombustible().getValorDb());
            } else {
                preparedStatement.setString(8, null);
            }

            if (coche.getCambio() != null) {
                preparedStatement.setString(9, coche.getCambio().getValorDb());
            } else {
                preparedStatement.setString(9, null);
            }

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
    public boolean eliminarCoche(int id) {
        String sql = "DELETE FROM coches WHERE id = ?";

        try (Connection connection = DataBaseConnection.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(sql)) {

            preparedStatement.setInt(1, id);

            int filasAfectadas = preparedStatement.executeUpdate();

            return filasAfectadas > 0;

        } catch (SQLException e) {
            System.out.println("Error al eliminar el coche.");
            e.printStackTrace();
            return false;
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

        try (Connection connection = DataBaseConnection.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(sql);
             ResultSet resultSet = preparedStatement.executeQuery()) {

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
        String sql = """
            UPDATE coches
            SET marca = ?, modelo = ?, version = ?, matricula = ?, bastidor = ?,
                anio = ?, kilometros = ?, combustible = ?, cambio = ?, color = ?,
                precio = ?, estado = ?
            WHERE id = ?
            """;

        try (Connection connection = DataBaseConnection.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(sql)) {

            preparedStatement.setString(1, coche.getMarca());
            preparedStatement.setString(2, coche.getModelo());
            preparedStatement.setString(3, coche.getVersion());
            preparedStatement.setString(4, coche.getMatricula());
            preparedStatement.setString(5, coche.getBastidor());
            preparedStatement.setInt(6, coche.getAnio());
            preparedStatement.setInt(7, coche.getKilometros());

            if (coche.getCombustible() != null) {
                preparedStatement.setString(8, coche.getCombustible().getValorDb());
            } else {
                preparedStatement.setString(8, null);
            }

            if (coche.getCambio() != null) {
                preparedStatement.setString(9, coche.getCambio().getValorDb());
            } else {
                preparedStatement.setString(9, null);
            }

            preparedStatement.setString(10, coche.getColor());
            preparedStatement.setDouble(11, coche.getPrecio());
            preparedStatement.setString(12, coche.getEstado().getValorDb());
            preparedStatement.setInt(13, id);

            preparedStatement.executeUpdate();

        } catch (SQLException e) {
            System.out.println("Error al modificar el coche.");
            e.printStackTrace();
        }
    }

    @Override
    public Coche buscarCoche(int id) {
        String sql = "SELECT * FROM coches WHERE id = ?";

        try (Connection connection = DataBaseConnection.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(sql)) {

            preparedStatement.setInt(1, id);

            ResultSet resultSet = preparedStatement.executeQuery();

            if (resultSet.next()) {
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

                return coche;
            }

        } catch (SQLException e) {
            System.out.println("Error al verificar el coche.");
            e.printStackTrace();
        }

        return null;
    }

}