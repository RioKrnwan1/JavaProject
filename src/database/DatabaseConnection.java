package database;

import java.sql.Connection;
import java.sql.DriverManager;

public class DatabaseConnection {
    private static String url;
    private static String user;
    private static String password;

    public static Connection connect() {
        
        url = "jdbc:mysql://localhost:3306/javafx";
        user = "root";
        password = "";
        Connection connection = null;

        try {
            connection = DriverManager.getConnection(url, user, password);
            System.out.println("Koneksi berhasil bro.");
        } catch (Exception e) {
            System.err.println("Aduhh koneksi gagal: " + e.getMessage());
        }

        return connection;
    }
}
