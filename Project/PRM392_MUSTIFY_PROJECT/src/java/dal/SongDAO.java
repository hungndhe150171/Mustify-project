/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dal;

import Model.Song;
import Model.SongType;
import java.util.ArrayList;
import java.util.List;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 *
 * @author thuhu
 */
public class SongDAO extends DBContext {

    public List<Song> getAllsong() {
        List<Song> list = new ArrayList<>();
        String sql = "SELECT  [song_id]\n"
                + "      ,[title]\n"
                + "      ,[type_id]\n"
                + "      ,[artist]\n"
                + "      ,[album]\n"
                + "      ,[duration]\n"
                + "      ,[file_path]\n"
                + "      ,[created_date]\n"
                + "      ,[image]\n"
                + "  FROM [MUSTIFY].[dbo].[Song] order by song_id DESC";
        try {
            PreparedStatement st = connection.prepareStatement(sql);
            ResultSet rs = st.executeQuery();
            while (rs.next()) {
                Song s = new Song();
                s.setSong_id(rs.getInt("song_id"));
                s.setTitle(rs.getString("title"));

                SongTypeDAO stdb = new SongTypeDAO();
                SongType stype = stdb.getSongTypeById(rs.getInt("type_id"));
                s.setType(stype);
                s.setArtist(rs.getString("artist"));
                s.setAlbum(rs.getString("album"));
                s.setDuration(rs.getInt("duration"));
                s.setFile_path(rs.getString("file_path"));
                s.setImage(rs.getString("image"));
                s.setCreated_date(rs.getDate("created_date"));
                list.add(s);
            }
        } catch (SQLException e) {
            System.out.println(e);
        }
        return list;
    }
    
    public boolean deleteSong(int id){
        String sql = "Delete from Song where song_id = ?";
        try{
            PreparedStatement st = connection.prepareStatement(sql);
            st.setInt(1, id);
            st.executeUpdate();
            return true;
        }catch(SQLException e){
            e.printStackTrace();
        }
        return false;
    }

    public boolean addSong(Song s) {
        String sql = "INSERT INTO [dbo].[Song]\n"
                + "           ([title]\n"
                + "           ,[type_id]\n"
                + "           ,[artist]\n"
                + "           ,[album]\n"
                + "           ,[duration]\n"
                + "           ,[file_path]\n"
                + "           ,[image])\n"
                + "     VALUES\n"
                + "           (?,?,?,?,?,?,?)";
        try {
            PreparedStatement st = connection.prepareStatement(sql);
            st.setString(1, s.getTitle());
            st.setInt(2, s.getType().getId());
            st.setString(3, s.getArtist());
            st.setString(4, s.getAlbum());
            st.setInt(5, s.getDuration());
            st.setString(6, s.getFile_path());
            st.setString(7, s.getImage());
            st.executeUpdate();
            return true;
        } catch (SQLException e) {
            System.out.println(e);
        }
        return false;
    }

    public Song getSongByName(String name) {
        String sql = "SELECT  [song_id]\n"
                + "      ,[title]\n"
                + "      ,[type_id]\n"
                + "      ,[artist]\n"
                + "      ,[album]\n"
                + "      ,[duration]\n"
                + "      ,[file_path]\n"
                + "      ,[created_date]\n"
                + "      ,[image]\n"
                + "  FROM [MUSTIFY].[dbo].[Song]\n"
                + "  where title = ?";
        try{
            PreparedStatement st = connection.prepareStatement(sql);
            st.setString(1, name);
            ResultSet rs = st.executeQuery();
            if(rs.next()){
                Song s = new Song();
                s.setSong_id(rs.getInt("song_id"));
                s.setTitle(rs.getString("title"));

                SongTypeDAO stdb = new SongTypeDAO();
                SongType stype = stdb.getSongTypeById(rs.getInt("type_id"));
                s.setType(stype);
                s.setArtist(rs.getString("artist"));
                s.setAlbum(rs.getString("album"));
                s.setDuration(rs.getInt("duration"));
                s.setFile_path(rs.getString("file_path"));
                s.setImage(rs.getString("image"));
                s.setCreated_date(rs.getDate("created_date"));
                return s;
            }
        }catch(SQLException e){
            System.out.println(e);
        }
        return null;
    }

    public static void main(String[] args) {
        SongDAO sdb = new SongDAO();
        List<Song> list = new ArrayList<>();
        System.out.println(sdb.getSongByName("mất kết nối").getTitle());
    }
}
