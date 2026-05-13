package com.concesionario.proyectoTercerTrimestre.repositories.impl;

import com.concesionario.proyectoTercerTrimestre.database.DataBaseConnection;
import com.concesionario.proyectoTercerTrimestre.entities.InteraccionCliente;
import com.concesionario.proyectoTercerTrimestre.entities.TipoInteraccion;
import com.concesionario.proyectoTercerTrimestre.repositories.InteraccionClienteRepository;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

public class InteraccionClienteRepositoryImpl implements InteraccionClienteRepository {

    @Override
    public void crearInteraccionCliente(InteraccionCliente interaccionCliente) {
        String sql = "INSERT INTO interacciones_cliente " +
                "(cliente_id, usuario_id, tipo, fecha, asunto, descripcion, resultado, proxima_accion, fecha_proxima) " +
                "VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?)";

        try (Connection connection = DataBaseConnection.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(sql)) {

            preparedStatement.setInt(1, interaccionCliente.getClienteId());
            preparedStatement.setInt(2, interaccionCliente.getUsuarioId());
            preparedStatement.setString(3, interaccionCliente.getTipo().getValorDb());
            preparedStatement.setTimestamp(4, Timestamp.valueOf(interaccionCliente.getFecha()));
            preparedStatement.setString(5, interaccionCliente.getAsunto());
            preparedStatement.setString(6, interaccionCliente.getDescripcion());
            preparedStatement.setString(7, interaccionCliente.getResultado());
            preparedStatement.setString(8, interaccionCliente.getProximaAccion());

            if (interaccionCliente.getFechaProxima() != null) {
                preparedStatement.setTimestamp(9, Timestamp.valueOf(interaccionCliente.getFechaProxima()));
            } else {
                preparedStatement.setTimestamp(9, null);
            }

            preparedStatement.executeUpdate();

        } catch (SQLException e) {
            System.out.println("Error al crear la interaccion con el cliente.");
            e.printStackTrace();
        }
    }

    @Override
    public boolean eliminarInteraccionCliente(int id) {
        String sql = "DELETE FROM interacciones_cliente WHERE id = ?";

        try (Connection connection = DataBaseConnection.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(sql)) {

            preparedStatement.setInt(1, id);

            int filasAfectadas = preparedStatement.executeUpdate();

            return filasAfectadas > 0;

        } catch (SQLException e) {
            System.out.println("Error al eliminar la interaccion con el cliente.");
            e.printStackTrace();
            return false;
        }
    }

    @Override
    public List<InteraccionCliente> listarInteraccionesCliente() {
        List<InteraccionCliente> interacciones = new ArrayList<>();

        String sql = "SELECT id, cliente_id, usuario_id, tipo, fecha, asunto, descripcion, resultado, proxima_accion, fecha_proxima " +
                "FROM interacciones_cliente " +
                "ORDER BY id";

        try (Connection connection = DataBaseConnection.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(sql);
             ResultSet resultSet = preparedStatement.executeQuery()) {

            while (resultSet.next()) {
                InteraccionCliente interaccionCliente = new InteraccionCliente();

                interaccionCliente.setId(resultSet.getInt("id"));
                interaccionCliente.setClienteId(resultSet.getInt("cliente_id"));
                interaccionCliente.setUsuarioId(resultSet.getInt("usuario_id"));
                interaccionCliente.setTipo(TipoInteraccion.fromDb(resultSet.getString("tipo")));

                Timestamp fecha = resultSet.getTimestamp("fecha");
                if (fecha != null) {
                    interaccionCliente.setFecha(fecha.toLocalDateTime());
                }

                interaccionCliente.setAsunto(resultSet.getString("asunto"));
                interaccionCliente.setDescripcion(resultSet.getString("descripcion"));
                interaccionCliente.setResultado(resultSet.getString("resultado"));
                interaccionCliente.setProximaAccion(resultSet.getString("proxima_accion"));

                Timestamp fechaProxima = resultSet.getTimestamp("fecha_proxima");
                if (fechaProxima != null) {
                    interaccionCliente.setFechaProxima(fechaProxima.toLocalDateTime());
                }

                interacciones.add(interaccionCliente);
            }

        } catch (SQLException e) {
            System.out.println("Error al listar las interacciones con clientes.");
            e.printStackTrace();
        }

        return interacciones;
    }

    @Override
    public void modificarInteraccionCliente(int id, InteraccionCliente interaccionCliente) {

        try (Connection connection = DataBaseConnection.getConnection()) {

            if (interaccionCliente.getClienteId() != -1) {
                String sqlComprobacion = "SELECT * FROM clientes WHERE id = ?";

                try (PreparedStatement preparedStatementComprobacion = connection.prepareStatement(sqlComprobacion)) {
                    preparedStatementComprobacion.setInt(1, interaccionCliente.getClienteId());

                    try (ResultSet resultSet = preparedStatementComprobacion.executeQuery()) {
                        if (!resultSet.next()) {
                            System.out.println("El id del cliente no es valido");
                            return;
                        }
                    }
                }

                String sql = "UPDATE interacciones_cliente SET cliente_id = ? WHERE id = ?";

                try (PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
                    preparedStatement.setInt(1, interaccionCliente.getClienteId());
                    preparedStatement.setInt(2, id);
                    preparedStatement.executeUpdate();
                }
            }

            if (interaccionCliente.getUsuarioId() != -1) {
                String sqlComprobacion = "SELECT * FROM usuarios WHERE id = ?";

                try (PreparedStatement preparedStatementComprobacion = connection.prepareStatement(sqlComprobacion)) {
                    preparedStatementComprobacion.setInt(1, interaccionCliente.getUsuarioId());

                    try (ResultSet resultSet = preparedStatementComprobacion.executeQuery()) {
                        if (!resultSet.next()) {
                            System.out.println("El id del usuario no es valido");
                            return;
                        }
                    }
                }

                String sql = "UPDATE interacciones_cliente SET usuario_id = ? WHERE id = ?";

                try (PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
                    preparedStatement.setInt(1, interaccionCliente.getUsuarioId());
                    preparedStatement.setInt(2, id);
                    preparedStatement.executeUpdate();
                }
            }

            if (interaccionCliente.getTipo() != null) {
                String sql = "UPDATE interacciones_cliente SET tipo = ? WHERE id = ?";

                try (PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
                    preparedStatement.setString(1, interaccionCliente.getTipo().getValorDb());
                    preparedStatement.setInt(2, id);
                    preparedStatement.executeUpdate();
                }
            }

            if (interaccionCliente.getFecha() != null) {
                String sql = "UPDATE interacciones_cliente SET fecha = ? WHERE id = ?";

                try (PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
                    preparedStatement.setTimestamp(1, Timestamp.valueOf(interaccionCliente.getFecha()));
                    preparedStatement.setInt(2, id);
                    preparedStatement.executeUpdate();
                }
            }

            if (interaccionCliente.getAsunto() != null) {
                String sql = "UPDATE interacciones_cliente SET asunto = ? WHERE id = ?";

                try (PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
                    preparedStatement.setString(1, interaccionCliente.getAsunto());
                    preparedStatement.setInt(2, id);
                    preparedStatement.executeUpdate();
                }
            }

            if (interaccionCliente.getDescripcion() != null) {
                String sql = "UPDATE interacciones_cliente SET descripcion = ? WHERE id = ?";

                try (PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
                    preparedStatement.setString(1, interaccionCliente.getDescripcion());
                    preparedStatement.setInt(2, id);
                    preparedStatement.executeUpdate();
                }
            }

            if (interaccionCliente.getResultado() != null) {
                String sql = "UPDATE interacciones_cliente SET resultado = ? WHERE id = ?";

                try (PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
                    preparedStatement.setString(1, interaccionCliente.getResultado());
                    preparedStatement.setInt(2, id);
                    preparedStatement.executeUpdate();
                }
            }

            if (interaccionCliente.getProximaAccion() != null) {
                String sql = "UPDATE interacciones_cliente SET proxima_accion = ? WHERE id = ?";

                try (PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
                    preparedStatement.setString(1, interaccionCliente.getProximaAccion());
                    preparedStatement.setInt(2, id);
                    preparedStatement.executeUpdate();
                }
            }

            if (interaccionCliente.getFechaProxima() != null) {
                String sql = "UPDATE interacciones_cliente SET fecha_proxima = ? WHERE id = ?";

                try (PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
                    preparedStatement.setTimestamp(1, Timestamp.valueOf(interaccionCliente.getFechaProxima()));
                    preparedStatement.setInt(2, id);
                    preparedStatement.executeUpdate();
                }
            }

        } catch (SQLException e) {
            System.out.println("Error al modificar la interaccion con cliente.");
            e.printStackTrace();
        }
    }

    @Override
    public InteraccionCliente bucarInteraccionCliente(int id) {

        InteraccionCliente interaccionCliente = new InteraccionCliente();

        String sql = "SELECT * FROM interacciones_cliente WHERE id = ?";

        try (Connection connection = DataBaseConnection.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(sql)) {

            preparedStatement.setInt(1, id);

            try (ResultSet resultSet = preparedStatement.executeQuery()) {
                while (resultSet.next()) {
                    interaccionCliente.setId(resultSet.getInt("id"));
                    interaccionCliente.setClienteId(resultSet.getInt("cliente_id"));
                    interaccionCliente.setUsuarioId(resultSet.getInt("usuario_id"));
                    interaccionCliente.setTipo(TipoInteraccion.fromDb(resultSet.getString("tipo")));

                    Timestamp fecha = resultSet.getTimestamp("fecha");
                    if (fecha != null) {
                        interaccionCliente.setFecha(fecha.toLocalDateTime());
                    }

                    interaccionCliente.setAsunto(resultSet.getString("asunto"));
                    interaccionCliente.setDescripcion(resultSet.getString("descripcion"));
                    interaccionCliente.setResultado(resultSet.getString("resultado"));
                    interaccionCliente.setProximaAccion(resultSet.getString("proxima_accion"));

                    Timestamp fechaProxima = resultSet.getTimestamp("fecha_proxima");
                    if (fechaProxima != null) {
                        interaccionCliente.setFechaProxima(fechaProxima.toLocalDateTime());
                    }
                }
            }

        } catch (SQLException e) {
            System.out.println("Error al buscar la interaccion con cliente.");
            e.printStackTrace();
        }

        return interaccionCliente;
    }
}