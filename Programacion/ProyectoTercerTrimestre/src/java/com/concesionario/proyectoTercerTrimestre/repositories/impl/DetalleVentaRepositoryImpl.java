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
    public boolean eliminarDetalleVenta(int id) {
        String sql = "DELETE FROM detalle_venta WHERE id = ?";

        try (Connection connection = DataBaseConnection.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(sql)) {

            preparedStatement.setInt(1, id);

            int filasAfectadas = preparedStatement.executeUpdate();

            return filasAfectadas > 0;

        } catch (SQLException e) {
            System.out.println("Error al eliminar el detalle de venta.");
            e.printStackTrace();
            return false;
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
        String sql = """
            UPDATE detalle_venta
            SET venta_id = ?, coche_id = ?, cantidad = ?
            WHERE id = ?
            """;

        try (Connection connection = DataBaseConnection.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(sql)) {

            preparedStatement.setInt(1, detalleVenta.getVentaId());
            preparedStatement.setInt(2, detalleVenta.getCocheId());
            preparedStatement.setInt(3, detalleVenta.getCantidad());
            preparedStatement.setInt(4, id);

            preparedStatement.executeUpdate();

        } catch (SQLException e) {
            System.out.println("Error al modificar el detalle de venta.");
            e.printStackTrace();
        }
    }

    @Override
    public DetalleVenta buscarDetalleVenta(int id) {
        String sql = "SELECT * FROM detalle_venta WHERE id = ?";

        try (Connection connection = DataBaseConnection.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(sql)) {

            preparedStatement.setInt(1, id);

            ResultSet resultSet = preparedStatement.executeQuery();

            if (resultSet.next()) {
                DetalleVenta detalleVenta = new DetalleVenta();

                detalleVenta.setId(resultSet.getInt("id"));
                detalleVenta.setVentaId(resultSet.getInt("venta_id"));
                detalleVenta.setCocheId(resultSet.getInt("coche_id"));
                detalleVenta.setCantidad(resultSet.getInt("cantidad"));

                return detalleVenta;
            }

        } catch (SQLException e) {
            System.out.println("Error al verificar el detalle de venta.");
            e.printStackTrace();
        }

        return null;
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

    @Override
    public boolean existeDetalleVentaConVentaYCocheExcluyendoId(int ventaId, int cocheId, int idDetalleVenta) {
        String sql = """
            SELECT COUNT(*) 
            FROM detalle_venta 
            WHERE venta_id = ? 
            AND coche_id = ? 
            AND id <> ?
            """;

        try (Connection connection = DataBaseConnection.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(sql)) {

            preparedStatement.setInt(1, ventaId);
            preparedStatement.setInt(2, cocheId);
            preparedStatement.setInt(3, idDetalleVenta);

            try (ResultSet resultSet = preparedStatement.executeQuery()) {
                if (resultSet.next()) {
                    return resultSet.getInt(1) > 0;
                }
            }

        } catch (SQLException e) {
            System.out.println("Error al comprobar si ya existe el detalle de venta.");
            e.printStackTrace();
        }

        return false;
    }

    @Override
    public boolean existeDetalleVentaConCocheExcluyendoId(int cocheId, int idDetalleVenta) {
        String sql = """
            SELECT COUNT(*) 
            FROM detalle_venta 
            WHERE coche_id = ? 
            AND id <> ?
            """;

        try (Connection connection = DataBaseConnection.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(sql)) {

            preparedStatement.setInt(1, cocheId);
            preparedStatement.setInt(2, idDetalleVenta);

            try (ResultSet resultSet = preparedStatement.executeQuery()) {
                if (resultSet.next()) {
                    return resultSet.getInt(1) > 0;
                }
            }

        } catch (SQLException e) {
            System.out.println("Error al comprobar si el coche ya esta en otro detalle de venta.");
            e.printStackTrace();
        }

        return false;
    }

}