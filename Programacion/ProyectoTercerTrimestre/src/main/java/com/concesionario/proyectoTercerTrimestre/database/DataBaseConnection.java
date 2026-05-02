package com.concesionario.proyectoTercerTrimestre.database;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DataBaseConnection {

    private static Connection connection;
    private static String url =  "jdbc:mysql://localhost:3306/crm_coches";
    private static String user = "root";
    private static String password = "1234";

    private DataBaseConnection() {};

    public static Connection getConnection() throws SQLException {

        if (connection == null) {

            connection = DriverManager.getConnection(url, user, password);

        }

        return connection;

    }

}
