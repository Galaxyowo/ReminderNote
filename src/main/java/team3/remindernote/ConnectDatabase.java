package team3.remindernote;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

public class ConnectDatabase {
    private Connection connection;

    public void connect() {
        String jdbcUrl = "jdbc:mysql://localhost:3306/remindernotedb"; 
        String username = "root"; 
        String password = ""; 

        try {
            connection = DriverManager.getConnection(jdbcUrl, username, password);
            System.out.println("Database Connected");
        } catch (SQLException e) {
            System.out.println("Database Not Connected");
            e.printStackTrace();
        }
    }

    public void checkConnection() {
        try {
            if (connection != null && !connection.isClosed()) {
                System.out.println("Connection is active");
            } else {
                System.out.println("Connection is closed");
            }
        } catch (SQLException e) {
            System.out.println("Failed to check connection");
            e.printStackTrace();
        }
    }

    public Connection getConnection() {
        return connection;
    }

    public void setConnection(Connection connection) {
        this.connection = connection;
    }

     public void closeConnection() {
        try {
            if (connection != null && !connection.isClosed()) {
                connection.close();
                System.out.println("Connection closed successfully.");
            }
        } catch (SQLException e) {
            System.out.println("Failed to close connection.");
            e.printStackTrace();
        }
    }
     
    public static void main(String[] args) {
    }
}