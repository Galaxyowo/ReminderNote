package team3.remindernote;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConnectDatabase {
    private Connection connection = null;

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