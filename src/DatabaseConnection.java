import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DatabaseConnection {

    // Update these with your MySQL setup
    private static final String URL = "jdbc:mysql://localhost:3306/library";
    private static final String USER = "root";       // MySQL username
    private static final String PASSWORD = "";       // (blank if none)

    // Method to get connection
    public static Connection getConnection() throws SQLException {
        return DriverManager.getConnection(URL, USER, PASSWORD);
    }
}
