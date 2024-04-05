package team3.remindernote.connect;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Connect {
    private static Connection connection = null;

    public static Connection getConnect() {
        if (connection != null) {
            return connection;
        }
        
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

        return connection;
    }

    public static void closeConnection() {
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
}