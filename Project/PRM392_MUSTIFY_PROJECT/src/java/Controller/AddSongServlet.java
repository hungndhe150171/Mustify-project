/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package Controller;

import Model.Song;
import Model.SongType;
import Utils.Utils;

import dal.SongDAO;
import dal.SongTypeDAO;
import java.io.IOException;
import java.io.PrintWriter;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.MultipartConfig;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.Part;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author thuhu
 */
@MultipartConfig()
public class AddSongServlet extends HttpServlet {

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        try (PrintWriter out = response.getWriter()) {
            /* TODO output your page here. You may use following sample code. */
            out.println("<!DOCTYPE html>");
            out.println("<html>");
            out.println("<head>");
            out.println("<title>Servlet AddSongServlet</title>");
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet AddSongServlet at " + request.getContextPath() + "</h1>");
            out.println("</body>");
            out.println("</html>");
        }
    }

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /**
     * Handles the HTTP <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        List<SongType> listST = new ArrayList<>();
        SongTypeDAO stdb = new SongTypeDAO();
        listST = stdb.getAll();

        request.setAttribute("listST", listST);
        request.getRequestDispatcher("addSong.jsp").forward(request, response);
    }

    /**
     * Handles the HTTP <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String title = request.getParameter("title");
        String artist = request.getParameter("artist");
        String album = request.getParameter("album");
        System.out.println(title + " " + artist);
        int type = Integer.parseInt(request.getParameter("typeSong"));
        String imageFile = "images/Music image/192x192px.png";
        String musicFile = "images/Music image/192x192px.png";
        int duration = 0;
        try {
            Part musicFilePart = request.getPart("musicFile");
            Part musicImageFilePart = request.getPart("musicImg");

            System.out.println("music file part: " + musicFilePart);
            System.out.println("music image file part: " + musicImageFilePart);

            String musicFileUrl = Paths.get(musicFilePart.getSubmittedFileName()).getFileName().toString();
            String musicImageFileUrl = Paths.get(musicImageFilePart.getSubmittedFileName()).getFileName().toString();
            imageFile = musicImageFileUrl;
            musicFile = musicFileUrl;

            //duration
            Utils u = new Utils();
            duration = u.MP3DurationCalculator("D:/0_FU_learning/SP2025/PRM392/MusicApplicationTemplate/MusicApplicationAndroid/List Music/" + musicFile);

        } catch (Exception e) {
            System.out.println(e);
        }
        SongDAO sdb = new SongDAO();
        Song s = new Song();
        s.setTitle(title);
        s.setArtist(artist);
        s.setAlbum(album);

        SongType st = new SongType();
        st.setId(type);
        s.setType(st);
        s.setFile_path(musicFile);
        s.setImage(imageFile);
        s.setDuration(duration);
        if (sdb.getSongByName(title) == null) {
            System.out.println(sdb.addSong(s));
            request.setAttribute("msg", "Add successed!");
        } else {
            request.setAttribute("msg", "Song is already exist!");
        }

        request.getRequestDispatcher("addSong.jsp").forward(request, response);
    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
