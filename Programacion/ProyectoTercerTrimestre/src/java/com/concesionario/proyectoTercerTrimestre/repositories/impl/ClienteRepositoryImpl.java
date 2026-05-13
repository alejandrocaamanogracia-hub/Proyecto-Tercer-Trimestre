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
    public void crearCliente(Cliente cliente) {
        String sql = "INSERT INTO clientes (nombre, email, telefono, direccion) VALUES (?, ?, ?, ?)";

        try {
            Connection connection = DataBaseConnection.getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement(sql);

            preparedStatement.setString(1, cliente.getNombre());
            preparedStatement.setString(2, cliente.getEmail());
            preparedStatement.setString(3, cliente.getTelefono());
            preparedStatement.setString(4, cliente.getDireccion());

            preparedStatement.executeUpdate();

        } catch (SQLException e) {
            System.out.println("Error al crear el cliente.");
            e.printStackTrace();
        }
    }

    @Override
    public boolean eliminarCliente(int id) {
        String sql = "DELETE FROM clientes WHERE id = ?";

        try (Connection connection = DataBaseConnection.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(sql)) {

            preparedStatement.setInt(1, id);

            int filasAfectadas = preparedStatement.executeUpdate();

            return filasAfectadas > 0;

        } catch (SQLException e) {
            System.out.println("Error al eliminar el cliente.");
            e.printStackTrace();
            return false;
        }
    }

    @Override
    public List<Cliente> listarClientes() {
        List<Cliente> clientes = new ArrayList<>();

        String sql = "SELECT id, nombre, email, telefono, direccion FROM clientes ORDER BY id";

        try {
            Connection connection = DataBaseConnection.getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            ResultSet resultSet = preparedStatement.executeQuery();

            while (resultSet.next()) {

                Cliente cliente = new Cliente();

                cliente.setId(resultSet.getInt("id"));
                cliente.setNombre(resultSet.getString("nombre"));
                cliente.setEmail(resultSet.getString("email"));
                cliente.setTelefono(resultSet.getString("telefono"));
                cliente.setDireccion(resultSet.getString("direccion"));

                clientes.add(cliente);
            }

        } catch (SQLException e) {
            System.out.println("Error al listar los clientes.");
            e.printStackTrace();
        }

        return clientes;
    }


    @Override
    public void modificarCliente(int id,  Cliente cliente) {

        String sql = "UPDATE clientes SET nombre = ? WHERE id = ?";

        try (Connection connection = DataBaseConnection.getConnection()){

            if (cliente.getNombre() != null) {

                PreparedStatement preparedStatement = connection.prepareStatement(sql);
                preparedStatement.setString(1, cliente.getNombre());
                preparedStatement.setInt(2, id);
                preparedStatement.executeUpdate();

            }

            sql = "UPDATE clientes SET email = ? WHERE id = ?";

            if (cliente.getEmail() != null) {

                PreparedStatement preparedStatement = connection.prepareStatement(sql);
                preparedStatement.setString(1, cliente.getEmail());
                preparedStatement.setInt(2, id);
                preparedStatement.executeUpdate();

            }

            sql = "UPDATE clientes SET telefono = ? WHERE id = ?";

            if (cliente.getTelefono() != null) {

                PreparedStatement preparedStatement = connection.prepareStatement(sql);
                preparedStatement.setString(1, cliente.getTelefono());
                preparedStatement.setInt(2, id);
                preparedStatement.executeUpdate();

            }

            sql = "UPDATE clientes SET direccion = ? WHERE id = ?";

            if (cliente.getDireccion() != null) {

                PreparedStatement preparedStatement = connection.prepareStatement(sql);
                preparedStatement.setString(1, cliente.getDireccion());
                preparedStatement.setInt(2, id);
                preparedStatement.executeUpdate();

            }

        }catch (SQLException e){
            System.out.println("Error al modificar el cliente.");
            e.printStackTrace();
        }

    }

    @Override
    public Cliente buscarCliente(int id){

        Cliente cliente = new Cliente();

        String sql = "SELECT * FROM clientes WHERE id = ?";

        try (Connection connection = DataBaseConnection.getConnection()){

            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setInt(1, id);
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                cliente.setId(resultSet.getInt("id"));
                cliente.setNombre(resultSet.getString("nombre"));
                cliente.setEmail(resultSet.getString("email"));
                cliente.setTelefono(resultSet.getString("telefono"));
                cliente.setDireccion(resultSet.getString("direccion"));
            }

        }catch (SQLException e){
            System.out.println("Error al ver el cliente.");
            e.printStackTrace();
        }

        return cliente;

    }

}