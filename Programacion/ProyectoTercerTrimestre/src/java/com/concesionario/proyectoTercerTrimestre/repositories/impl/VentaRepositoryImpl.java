package com.concesionario.proyectoTercerTrimestre.repositories.impl;

import com.concesionario.proyectoTercerTrimestre.database.DataBaseConnection;
import com.concesionario.proyectoTercerTrimestre.entities.Venta;
import com.concesionario.proyectoTercerTrimestre.repositories.VentaRepository;
import com.concesionario.proyectoTercerTrimestre.entities.EstadoVenta;


import java.sql.*;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import java.util.ArrayList;
import java.util.List;

public class VentaRepositoryImpl implements VentaRepository {

    @Override
    public void crearVenta(Venta venta) {
        String sql = "INSERT INTO ventas (cliente_id, usuario_id, fecha, estado, total) " +
                "VALUES (?, ?, ?, ?, ?)";

        try {
            Connection connection = DataBaseConnection.getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement(sql);

            preparedStatement.setInt(1, venta.getClienteId());
            preparedStatement.setInt(2, venta.getUsuarioId());
            preparedStatement.setDate(3, Date.valueOf(venta.getFecha()));
            preparedStatement.setString(4, venta.getEstado().getValorDb());
            preparedStatement.setDouble(5, venta.getTotal());

            preparedStatement.executeUpdate();

        } catch (SQLException e) {
            System.out.println("Error al crear la venta.");
            e.printStackTrace();
        }
    }

    @Override
    public boolean eliminarVenta(int id) {
        String sql = "DELETE FROM ventas WHERE id = ?";

        try (Connection connection = DataBaseConnection.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(sql)) {

            preparedStatement.setInt(1, id);

            int filasAfectadas = preparedStatement.executeUpdate();

            return filasAfectadas > 0;

        } catch (SQLException e) {
            System.out.println("Error al eliminar la venta.");
            e.printStackTrace();
            return false;
        }
    }

    @Override
    public List<Venta> listarVentas() {
        List<Venta> ventas = new ArrayList<>();

        String sql = "SELECT id, cliente_id, usuario_id, fecha, estado, total " +
                "FROM ventas " +
                "ORDER BY id";

        try {
            Connection connection = DataBaseConnection.getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            ResultSet resultSet = preparedStatement.executeQuery();

            while (resultSet.next()) {
                Venta venta = new Venta();

                venta.setId(resultSet.getInt("id"));
                venta.setClienteId(resultSet.getInt("cliente_id"));
                venta.setUsuarioId(resultSet.getInt("usuario_id"));

                Date fecha = resultSet.getDate("fecha");
                if (fecha != null) {
                    venta.setFecha(fecha.toLocalDate());
                }

                venta.setEstado(EstadoVenta.fromDb(resultSet.getString("estado")));
                venta.setTotal(resultSet.getDouble("total"));

                ventas.add(venta);
            }

        } catch (SQLException e) {
            System.out.println("Error al listar las ventas.");
            e.printStackTrace();
        }

        return ventas;
    }


    @Override
    public void modificarVenta(int id, Venta venta){

        String sql = "UPDATE ventas SET cliente_id = ? WHERE id = ?";
        ResultSet resultSet = null;

        try (Connection connection = DataBaseConnection.getConnection()) {

            if (venta.getClienteId() != -1) {

                PreparedStatement preparedStatementComprobacion = connection.prepareStatement("SELECT * FROM clientes WHERE id = ?");
                preparedStatementComprobacion.setInt(1, venta.getClienteId());
                resultSet = preparedStatementComprobacion.executeQuery();

                if(!resultSet.next()) {
                    System.out.println("El id del cliente no es valida");
                    return;
                }else {
                    PreparedStatement preparedStatement = connection.prepareStatement(sql);
                    preparedStatement.setInt(1, venta.getClienteId());
                    preparedStatement.setInt(2, id);
                    preparedStatement.executeUpdate();
                }


            }

            sql = "UPDATE ventas SET usuario_id = ? WHERE id = ?";

            if (venta.getUsuarioId() != -1) {

                PreparedStatement preparedStatementComprobacion = connection.prepareStatement("SELECT * FROM usuarios WHERE id = ?");
                preparedStatementComprobacion.setInt(1, venta.getUsuarioId());
                resultSet = preparedStatementComprobacion.executeQuery();

                if (!resultSet.next()) {
                    System.out.println("El id del usuario no es valido");
                    return;
                }else{
                    PreparedStatement preparedStatement = connection.prepareStatement(sql);
                    preparedStatement.setInt(1, venta.getUsuarioId());
                    preparedStatement.setInt(2, id);
                    preparedStatement.executeUpdate();
                }

            }

            sql = "UPDATE ventas SET fecha = ? WHERE id = ?";

            if (venta.getFecha() != null){

                PreparedStatement preparedStatement = connection.prepareStatement(sql);
                preparedStatement.setDate(1, Date.valueOf(venta.getFecha()));
                preparedStatement.setInt(2, id);

            }

            sql = "UPDATE ventas SET estado = ? WHERE id = ?";

            if (venta.getEstado() != null){

                PreparedStatement preparedStatement = connection.prepareStatement(sql);
                preparedStatement.setString(1, venta.getEstado().getValorDb());
                preparedStatement.setInt(2, id);
                preparedStatement.executeUpdate();

            }

            sql = "UPDATE ventas SET total = ? WHERE id = ?";

            if (venta.getTotal() != -1){

                PreparedStatement preparedStatement = connection.prepareStatement(sql);
                preparedStatement.setDouble(1, venta.getTotal());
                preparedStatement.setInt(2, id);
                preparedStatement.executeUpdate();

            }

        }catch (SQLException e){
            System.out.println("Error al modificar la venta.");
            e.printStackTrace();
        }
    }

    public Venta buscarVenta(int id){

        Venta venta = new Venta();
        String sql = "SELECT * FROM ventas WHERE id = ?";

        try (Connection connection = DataBaseConnection.getConnection()){

            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setInt(1, id);
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                venta.setId(resultSet.getInt("id"));
                venta.setClienteId(resultSet.getInt("cliente_id"));
                venta.setUsuarioId(resultSet.getInt("usuario_id"));

                Date fecha = resultSet.getDate("fecha");
                if (fecha != null) {
                    venta.setFecha(fecha.toLocalDate());
                }

                venta.setEstado(EstadoVenta.fromDb(resultSet.getString("estado")));
                venta.setTotal(resultSet.getDouble("total"));
            }

        }catch (SQLException e){
            System.out.println("Error al buscar la venta.");
            e.printStackTrace();
        }

        return venta;

    }
}