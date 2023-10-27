package Database;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DatabaseConnection {
    public static Connection getConnection() {
        Connection connection = null;
        String url = "jdbc:mysql://localhost:3306/MyDatabase"; // Update with your database URL
        String user = "your_username"; // Replace with your MySQL username
        String password = "your_password"; // Replace with your MySQL password

        try {
            connection = DriverManager.getConnection(url, user, password);
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return connection;
    }
}

