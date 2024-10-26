import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.mindrot.jbcrypt.BCrypt; 

@WebServlet("/register")
public class RegistrationServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String username = request.getParameter("username");
        String email = request.getParameter("email");
        String password = request.getParameter("password");
        String hashedPassword = BCrypt.hashpw(password, BCrypt.gensalt()); // Secure password hash

        String jdbcURL = "jdbc:mysql://localhost:3306/your_database"; 
        String dbUser  = "your_username"; 
        String dbPassword = "your_password"; 

        response.setContentType("text/html");
        PrintWriter out = response.getWriter();

        try (Connection conn = DriverManager.getConnection(jdbcURL, dbUser , dbPassword)) {
            String query = "INSERT INTO users (username, email, password) VALUES (?, ?, ?)";
            try (PreparedStatement statement = conn.prepareStatement(query)) {
                statement.setString(1, username);
                statement.setString(2, email);
                statement.setString(3, hashedPassword);

                int rowsInserted = statement.executeUpdate();
                if (rowsInserted > 0) {
                    out.println("Registration successful!");
                }
            }
        } catch (SQLException e) {
            out.println("Error: " + e.getMessage());
        }
    }
}
