import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/searchJobs")
public class SearchJobsServlet extends HttpServlet {
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String keyword = request.getParameter("keyword");
        
        try (Connection conn = DatabaseConnection.getConnection()) {
            String query = "SELECT * FROM jobs WHERE title LIKE ? OR description LIKE ?";
            PreparedStatement stmt = conn.prepareStatement(query);
            stmt.setString(1, "%" + keyword + "%");
            stmt.setString(2, "%" + keyword + "%");
            ResultSet rs = stmt.executeQuery();
            
            PrintWriter out = response.getWriter();
            while (rs.next()) {
                out.println("<div>" + rs.getString("title") + " - " + rs.getString("location") + "</div>");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}