import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/jobSearch")
public class JobSearchServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String keyword = request.getParameter("keyword");
        response.setContentType("text/html");
        PrintWriter out = response.getWriter();

        if (keyword != null && !keyword.isEmpty()) {
            String query = "SELECT * FROM jobs WHERE title LIKE ? OR description LIKE ?";
            try {
                // Database connection
                Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/your_database", "username", "password");
                PreparedStatement stmt = conn.prepareStatement(query);
                stmt.setString(1, "%" + keyword + "%");
                stmt.setString(2, "%" + keyword + "%");
                
                ResultSet resultSet = stmt.executeQuery();

                if (resultSet.next()) {
                    do {
                        out.println("<div>");
                        out.println("<h3>" + resultSet.getString("title") + "</h3>");
                        out.println("<p>" + resultSet.getString("description") + "</p>");
                        out.println("<p>Location: " + resultSet.getString("location") + "</p>");
                        out.println("</div>");
                    } while (resultSet.next());
                } else {
                    out.println("No jobs found.");
                }

                // Close resources
                resultSet.close();
                stmt.close();
                conn.close();
            } catch (Exception e) {
                out.println("Error: " + e.getMessage());
            }
        } else {
            out.println("Please provide a keyword.");
        }
    }
}