/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */

package Controller;

import Model.Song;
import dal.SongDAO;
import com.google.gson.Gson;
import java.io.IOException;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.PrintWriter;

/**
 *
 * @author thuhu
 */
public class CheckSongExistServlet extends HttpServlet {
 
 
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
    throws ServletException, IOException {
       String songName =request.getParameter("songName");
        
        SongDAO sdb = new SongDAO();
        Song s = sdb.getSongByName(songName);
        boolean sn = (s != null);
        System.out.println("Song name: "+s.getTitle());
        String json = new Gson().toJson(sn);

        response.setContentType("application/json");
        response.getWriter().write(json);
    } 

  
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
