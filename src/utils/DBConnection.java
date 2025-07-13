package utils;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

// This utility class provides a reusable method to establish a connection to the PostgreSQL database,
// using configuration properties loaded from the db.properties file via DatabaseConfig.
public class DBConnection {

    // Load connection settings from the configuration file
    private static final String HOST = DatabaseConfig.get("db.host");
    private static final int PORT = Integer.parseInt(DatabaseConfig.get("db.port"));
    private static final String DB_NAME = DatabaseConfig.get("db.name");
    private static final String USER = DatabaseConfig.get("db.user");
    private static final String PASSWORD = DatabaseConfig.get("db.password");

    // Construct the JDBC URL from individual components
    private static final String URL = "jdbc:postgresql://" + HOST + ":" + PORT + "/" + DB_NAME;

    /**
     * Establishes and returns a connection to the configured PostgreSQL database.
     * @return a Connection object that can be used to interact with the database
     * @throws SQLException if the connection fails
     */
    public static Connection getConnection() throws SQLException {
        return DriverManager.getConnection(URL, USER, PASSWORD);
    }
}
