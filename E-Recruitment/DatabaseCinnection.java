import java.sql.Connection;
import java.sql.DriverManager;

public class DatabaseConnection {
    public static Connection getConnection() throws Exception {
        String url = "jdbc:mysql://localhost:3306/your_database"; /
        String user = "your_username"; // 
        String password = "your_password"; 