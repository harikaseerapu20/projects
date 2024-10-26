import java.sql.Connection;
import java.sql.DriverManager;

public class DatabaseConnection {
    public static Connection getConnection() throws Exception {
        String url = "jdbc:mysql://localhost:3306/your_database"; // Update with your database URL
        String user = "your_username"; // Update with your database username
        String password = "your_password"; // Update with your database password