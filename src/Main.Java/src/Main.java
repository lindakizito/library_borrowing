import java.sql.Connection;

public class Main {
    public static void main(String[] args) {
        try {
            Connection conn = DatabaseConnection.getConnection();
            System.out.println("✅ Connection successful!");
            conn.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
