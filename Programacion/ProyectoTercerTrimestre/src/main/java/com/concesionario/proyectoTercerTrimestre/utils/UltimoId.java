package com.concesionario.proyectoTercerTrimestre.utils;

import com.concesionario.proyectoTercerTrimestre.database.DataBaseConnection;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class UltimoId {

    public static int getUltimoId(String tabla) {

        int id = 0;

        String query = "SELECT MAX(id) AS max_id FROM " + tabla;

        try (Connection connection = DataBaseConnection.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(query);
             ResultSet resultSet = preparedStatement.executeQuery()) {

            if (resultSet.next()) {
                id = resultSet.getInt("max_id");
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return id;
    }

}
