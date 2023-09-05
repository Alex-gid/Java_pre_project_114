package jm.task.core.jdbc.util;

import java.sql.*;


public class Util {
    private final String URL = "jdbc:mysql://localhost:3306/bd_kata2114";
    private final String NAME = "root";
    private final String PASSWORD = "RootPassword";


    public Connection getConnection() {
        Connection connection = null;

        try {
            connection = DriverManager.getConnection(URL, NAME, PASSWORD);


        } catch (SQLException e) {
            e.printStackTrace();
            System.out.println("Connected FALSE");
        }
        return connection;
    }
}