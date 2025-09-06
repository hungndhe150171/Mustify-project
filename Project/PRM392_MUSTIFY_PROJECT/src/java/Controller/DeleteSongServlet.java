package Controller;

import dal.SongDAO;
import java.io.IOException;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@WebServlet(name = "DeleteSongServlet", urlPatterns = {"/deleteSong"})
public class DeleteSongServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
    throws ServletException, IOException {
        try {
            String idParam = request.getParameter("id");
            if (idParam == null || idParam.isEmpty()) {
                response.sendRedirect("listsong?error=Invalid ID");
                return;
            }

            int id = Integer.parseInt(idParam);
            SongDAO sdb = new SongDAO();
            
            boolean isDeleted = sdb.deleteSong(id);
            if (isDeleted) {
                System.out.println("Deleted song ID: " + id);
                response.sendRedirect("listsong?success=Song deleted");
            } else {
                response.sendRedirect("listsong?error=Song not found or could not be deleted");
            }
        } catch (NumberFormatException e) {
            response.sendRedirect("listsong?error=Invalid song ID format");
        } catch (Exception e) {
            e.printStackTrace();
            response.sendRedirect("listsong?error=An error occurred");
        }
    }

    @Override
    public String getServletInfo() {
        return "Servlet to delete a song";
    }
}
