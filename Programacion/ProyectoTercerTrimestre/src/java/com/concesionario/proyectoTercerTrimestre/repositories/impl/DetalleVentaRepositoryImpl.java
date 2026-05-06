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
        String sql = "INSERT INTO detalle_venta (venta_id, coche_id, cantidad, precio_unitario) " +
                "VALUES (?, ?, ?, ?)";

        try {
            Connection connection = DataBaseConnection.getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement(sql);

            preparedStatement.setInt(1, detalleVenta.getVentaId());
            preparedStatement.setInt(2, detalleVenta.getCocheId());
            preparedStatement.setInt(3, detalleVenta.getCantidad());
            preparedStatement.setDouble(4, detalleVenta.getPrecioUnitario());

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

        String sql = "SELECT id, venta_id, coche_id, cantidad, precio_unitario " +
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
                detalleVenta.setPrecioUnitario(resultSet.getDouble("precio_unitario"));

                detallesVenta.add(detalleVenta);
            }

        } catch (SQLException e) {
            System.out.println("Error al listar los detalles de venta.");
            e.printStackTrace();
        }

        return detallesVenta;
    }
}