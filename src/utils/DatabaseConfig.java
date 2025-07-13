package utils;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

// This utility class loads database configuration properties from an external "db.properties" file
public class DatabaseConfig {

    // Static Properties object used to store key-value pairs from the configuration file
    private static Properties props = new Properties();

    // Static initializer block — runs once when the class is first loaded
    static {
        // Attempt to load the "db.properties" file from the classpath
        try (InputStream input = DatabaseConfig.class.getClassLoader().getResourceAsStream("db.properties")) {
            
            // Check if the file was found
            if (input == null) {
                System.out.println("Sorry, unable to find db.properties"); // Warn if missing
            } else {
                props.load(input); // Load key-value pairs into the props object
            }

        } catch (IOException ex) {
            // Handle any I/O error that occurs while loading the file
            ex.printStackTrace();
        }
    }

    // Public method to retrieve a property value by key from the loaded properties
    public static String get(String key) {
        return props.getProperty(key); // Returns null if key isn't found
    }
}
