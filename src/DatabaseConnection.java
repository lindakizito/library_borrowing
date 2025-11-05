//These imports provide classes needed to connect to a database
import java.sql.Connection; //Represents the link to the database
import java.sql.DriverManager; //Responsible for creating DB connections
import java.sql.SQLException; //Handles SQL-related errors

public class DatabaseConnection {

    // Update these with your MySQL setup
    private static final String URL = "jdbc:mysql://localhost:3306/library";
    private static final String USER = "root";       // MySQL username
    private static final String PASSWORD = "";

    // Method to get connection
    public static Connection getConnection() throws SQLException {
        return DriverManager.getConnection(URL, USER, PASSWORD);
    }
}
