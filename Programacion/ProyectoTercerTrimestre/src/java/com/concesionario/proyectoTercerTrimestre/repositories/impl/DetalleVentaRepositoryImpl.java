package com.concesionario.proyectoTercerTrimestre.repositories.impl;

import com.concesionario.proyectoTercerTrimestre.database.DataBaseConnection;
import com.concesionario.proyectoTercerTrimestre.entities.DetalleVenta;
import com.concesionario.proyectoTercerTrimestre.repositories.DetalleVentaRepository;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class DetalleVentaRepositoryImpl implements DetalleVentaRepository {

    @Override
    public void crearDetalleVenta(DetalleVenta detalleVenta) {

        String sql = "INSERT INTO detalle_venta (venta_id, coche_id, cantidad) " +
                "VALUES (?, ?, ?)";

        try {
            Connection connection = DataBaseConnection.getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement(sql);

            preparedStatement.setInt(1, detalleVenta.getVentaId());
            preparedStatement.setInt(2, detalleVenta.getCocheId());
            preparedStatement.setInt(3, detalleVenta.getCantidad());


            preparedStatement.executeUpdate();

        } catch (SQLException e) {
            System.out.println("Error al crear el detalle de venta.");
            e.printStackTrace();
        }
    }

    @Override
    public void eliminarDetalleVenta(int id) {
        String sql = "DELETE FROM detalle_venta WHERE id = ?";

        try {
            Connection connection = DataBaseConnection.getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement(sql);

            preparedStatement.setInt(1, id);
            preparedStatement.executeUpdate();

        } catch (SQLException e) {
            System.out.println("Error al eliminar el detalle de venta.");
            e.printStackTrace();
        }
    }

    @Override
    public List<DetalleVenta> listarDetallesVenta() {
        List<DetalleVenta> detallesVenta = new ArrayList<>();

        String sql = "SELECT id, venta_id, coche_id, cantidad " +
                "FROM detalle_venta " +
                "ORDER BY id";

        try {
            Connection connection = DataBaseConnection.getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            ResultSet resultSet = preparedStatement.executeQuery();

            while (resultSet.next()) {
                DetalleVenta detalleVenta = new DetalleVenta();

                detalleVenta.setId(resultSet.getInt("id"));
                detalleVenta.setVentaId(resultSet.getInt("venta_id"));
                detalleVenta.setCocheId(resultSet.getInt("coche_id"));
                detalleVenta.setCantidad(resultSet.getInt("cantidad"));


                detallesVenta.add(detalleVenta);
            }

        } catch (SQLException e) {
            System.out.println("Error al listar los detalles de venta.");
            e.printStackTrace();
        }

        return detallesVenta;
    }


    @Override
    public void modificarDetalleVenta(int id, DetalleVenta detalleVenta) {

        String sql = "UPDATE detalle_venta SET venta_id = ? WHERE id = ?";
        ResultSet resultSet = null;

        try (Connection connection = DataBaseConnection.getConnection()) {

            if (detalleVenta.getVentaId() != -1) {

                PreparedStatement preparedStatementComprobacion =
                        connection.prepareStatement("SELECT * FROM ventas WHERE id = ?");
                preparedStatementComprobacion.setInt(1, detalleVenta.getVentaId());
                resultSet = preparedStatementComprobacion.executeQuery();

                if (!resultSet.next()) {
                    System.out.println("El id de la venta no es valido");
                    return;
                } else {
                    PreparedStatement preparedStatement = connection.prepareStatement(sql);
                    preparedStatement.setInt(1, detalleVenta.getVentaId());
                    preparedStatement.setInt(2, id);
                    preparedStatement.executeUpdate();
                }
            }

            sql = "UPDATE detalle_venta SET coche_id = ? WHERE id = ?";

            if (detalleVenta.getCocheId() != -1) {

                PreparedStatement preparedStatementComprobacion =
                        connection.prepareStatement("SELECT * FROM coches WHERE id = ?");
                preparedStatementComprobacion.setInt(1, detalleVenta.getCocheId());
                resultSet = preparedStatementComprobacion.executeQuery();

                if (!resultSet.next()) {
                    System.out.println("El id del coche no es valido");
                    return;
                } else {
                    PreparedStatement preparedStatement = connection.prepareStatement(sql);
                    preparedStatement.setInt(1, detalleVenta.getCocheId());
                    preparedStatement.setInt(2, id);
                    preparedStatement.executeUpdate();
                }
            }

            sql = "UPDATE detalle_venta SET cantidad = ? WHERE id = ?";

            if (detalleVenta.getCantidad() != -1) {

                PreparedStatement preparedStatement = connection.prepareStatement(sql);
                preparedStatement.setInt(1, detalleVenta.getCantidad());
                preparedStatement.setInt(2, id);
                preparedStatement.executeUpdate();
            }

        } catch (SQLException e) {
            System.out.println("Error al modificar el detalle de venta.");
            e.printStackTrace();
        }
    }

    @Override
    public DetalleVenta buscarDetalleVenta(int id){

        DetalleVenta detalleVenta = new DetalleVenta();
        String sql = "SELECT * FROM detalle_venta WHERE id = ?";

        try (Connection connection = DataBaseConnection.getConnection()){

            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setInt(1, id);
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                DetalleVenta detalleVenta1 = new DetalleVenta();
                detalleVenta1.setId(resultSet.getInt("id"));
                detalleVenta1.setVentaId(resultSet.getInt("venta_id"));
                detalleVenta1.setCocheId(resultSet.getInt("coche_id"));
                detalleVenta1.setCantidad(resultSet.getInt("cantidad"));
            }

        }catch (SQLException e){
            System.out.println("Error al verificar el detalle de venta.");
            e.printStackTrace();
        }

        return detalleVenta;

    }

    @Override
    public boolean existeVenta(int ventaId) {
        String sql = "SELECT COUNT(*) FROM ventas WHERE id = ?";

        try (Connection connection = DataBaseConnection.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(sql)) {

            preparedStatement.setInt(1, ventaId);

            try (ResultSet resultSet = preparedStatement.executeQuery()) {
                if (resultSet.next()) {
                    return resultSet.getInt(1) > 0;
                }
            }

        } catch (SQLException e) {
            System.out.println("Error al comprobar si existe la venta.");
            e.printStackTrace();
        }

        return false;
    }

    @Override
    public boolean existeCoche(int cocheId) {
        String sql = "SELECT COUNT(*) FROM coches WHERE id = ?";

        try (Connection connection = DataBaseConnection.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(sql)) {

            preparedStatement.setInt(1, cocheId);

            try (ResultSet resultSet = preparedStatement.executeQuery()) {
                if (resultSet.next()) {
                    return resultSet.getInt(1) > 0;
                }
            }

        } catch (SQLException e) {
            System.out.println("Error al comprobar si existe el coche.");
            e.printStackTrace();
        }

        return false;
    }

    @Override
    public boolean existeDetalleVentaConVentaYCoche(int ventaId, int cocheId) {
        String sql = "SELECT COUNT(*) FROM detalle_venta WHERE venta_id = ? AND coche_id = ?";

        try (Connection connection = DataBaseConnection.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(sql)) {

            preparedStatement.setInt(1, ventaId);
            preparedStatement.setInt(2, cocheId);

            try (ResultSet resultSet = preparedStatement.executeQuery()) {
                if (resultSet.next()) {
                    return resultSet.getInt(1) > 0;
                }
            }

        } catch (SQLException e) {
            System.out.println("Error al comprobar si ya existe el detalle de venta.");
            e.printStackTrace();
        }

        return true;
    }

    @Override
    public boolean existeDetalleVentaConCoche(int cocheId) {
        String sql = "SELECT COUNT(*) FROM detalle_venta WHERE coche_id = ?";

        try (Connection connection = DataBaseConnection.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(sql)) {

            preparedStatement.setInt(1, cocheId);

            try (ResultSet resultSet = preparedStatement.executeQuery()) {
                if (resultSet.next()) {
                    return resultSet.getInt(1) > 0;
                }
            }

        } catch (SQLException e) {
            System.out.println("Error al comprobar si el coche ya esta en un detalle de venta.");
            e.printStackTrace();
        }

        return true;
    }

}