package org.example;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DbConnector {
    public static Connection getConnectionEvent() {

        String url = "jdbc:mysql://localhost:3306/eventDB";
        String user = "root";
        String password = "";

        //Gjøres i en try-catch for at connection skal lukke automatisk

        try {
            System.out.println("Database connection is made " + url);
            return DriverManager.getConnection(url, user, password);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public static Connection getConnectionUni() {

        String url = "jdbc:mysql://localhost:3306/universityDB";
        String user = "root";
        String password = "";

        //Gjøres i en try-catch for at connection skal lukke automatisk

        try {
            System.out.println("Database connection is made " + url);
            return DriverManager.getConnection(url, user, password);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
