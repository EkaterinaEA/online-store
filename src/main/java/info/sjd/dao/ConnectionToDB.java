package info.sjd.dao;

import java.sql.*;

public class ConnectionToDB {

    private static final String URL = "jdbc:postgresql://localhost:5432/prom.ua";
    private static final String USER = "postgres";
    private static final String PASSWORD = "89520290819";

    public static Connection getConnection() {
        try {
            return DriverManager.getConnection(URL, USER, PASSWORD);
        } catch (SQLException e) {
            e.printStackTrace();
        } return null;
    }
}