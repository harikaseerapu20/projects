import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.PreparedStatement;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/postJob")
public class JobPostServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String title = request.getParameter("job_title");
        String desc = request.getParameter("job_desc");
        String location = request.getParameter("location");
        String experience = request.getParameter("experience");
        
        try (Connection conn = DatabaseConnection.getConnection()) {
            String query = "INSERT INTO jobs (title, description, location, experience) VALUES (?, ?, ?, ?)";
            PreparedStatement stmt = conn.prepareStatement(query);
            stmt.setString(1, title);
            stmt.setString(2, desc);
            stmt.setString(3, location);
            stmt.setString(4, experience);
            int rowsInserted = stmt.executeUpdate();
            
            PrintWriter out = response.getWriter();
            if (rowsInserted > 0) {
                out.println("Job posted successfully!");
            } else {
                out.println("Error posting job.");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}