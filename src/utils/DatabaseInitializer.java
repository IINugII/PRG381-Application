package utils;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

// This class sets up the PostgreSQL database and the "users" table,
// including inserting some sample user records if the table is empty.
public class DatabaseInitializer {

    // Default database to connect to first (used to create the target database if needed)
    private static final String DEFAULT_DB = "postgres";

    // Target database name, user credentials, host and port are fetched from a configuration file
    private static final String TARGET_DB = DatabaseConfig.get("db.name");
    private static final String USER = DatabaseConfig.get("db.user");
    private static final String PASSWORD = DatabaseConfig.get("db.password");
    private static final String HOST = DatabaseConfig.get("db.host");
    private static final int PORT = Integer.parseInt(DatabaseConfig.get("db.port"));

    // Main method to initialize the database setup
    public static void initialize() {
        // Step 0: Load the PostgreSQL JDBC driver
        try {
            Class.forName("org.postgresql.Driver");
        } catch (ClassNotFoundException e) {
            // If the driver is not found, print the error and exit early
            e.printStackTrace();
            return;
        }

        // Step 1: Connect to the default "postgres" database and attempt to create the target database
        try (Connection conn = DriverManager.getConnection(
                "jdbc:postgresql://" + HOST + ":" + PORT + "/" + DEFAULT_DB,
                USER,
                PASSWORD);
             Statement stmt = conn.createStatement()) {

            // Attempt to create the new database with the specified owner
            stmt.executeUpdate("CREATE DATABASE " + TARGET_DB + " WITH OWNER = " + USER + ";");

        } catch (SQLException e) {
            // Ignore "already exists" errors; log other SQL exceptions
            if (!e.getMessage().contains("already exists")) {
                e.printStackTrace();
            }
        }

        // Step 2: Connect to the newly created (or pre-existing) target database
        // Create a "users" table if it doesn't exist and populate it with sample data
        try (Connection conn = DriverManager.getConnection(
                "jdbc:postgresql://" + HOST + ":" + PORT + "/" + TARGET_DB,
                USER,
                PASSWORD);
             Statement stmt = conn.createStatement()) {

            // Define the SQL command to create the "users" table
            String createTable = """
                CREATE TABLE IF NOT EXISTS users (
                    student_number VARCHAR(20) PRIMARY KEY,
                    name VARCHAR(50) NOT NULL,
                    surname VARCHAR(50) NOT NULL,
                    email VARCHAR(100) UNIQUE NOT NULL,
                    phone VARCHAR(15) NOT NULL,
                    password TEXT NOT NULL
                );
            """;

            // Execute the create table statement
            stmt.executeUpdate(createTable);

            // Check how many records currently exist in the "users" table
            var rs = stmt.executeQuery("SELECT COUNT(*) FROM users");
            rs.next();
            int count = rs.getInt(1);

            // If no users exist, insert some sample data to populate the table
            if (count == 0) {
                String insertUsers = """
                    INSERT INTO users (student_number, name, surname, email, phone, password) VALUES
                    ('600101', 'Alice', 'Smith', '600101@student.belgiumcampus.ac.za', '0812345678', 'password1'),
                    ('600102', 'Bob', 'Johnson', '600102@student.belgiumcampus.ac.za', '0723456789', 'password2'),
                    ('600103', 'Carol', 'Williams', '600103@student.belgiumcampus.ac.za', '0834567890', 'password3'),
                    ('600104', 'David', 'Brown', '600104@student.belgiumcampus.ac.za', '0845678901', 'password4'),
                    ('600105', 'Emily', 'Jones', '600105@student.belgiumcampus.ac.za', '0746789012', 'password5'),
                    ('600106', 'Frank', 'Taylor', '600106@student.belgiumcampus.ac.za', '0657890123', 'password6'),
                    ('600107', 'Grace', 'Anderson', '600107@student.belgiumcampus.ac.za', '0768901234', 'password7');
                """;
                stmt.executeUpdate(insertUsers); // Add dummy users
            }

        } catch (SQLException e) {
            // Print any errors that occur during table creation or data insertion
            e.printStackTrace();
        }
    }
}
