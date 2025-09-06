/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dal;

import Model.SongType;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author thuhu
 */
public class SongTypeDAO extends DBContext {
    
    public List<SongType> getAll(){
        List<SongType> list = new ArrayList<>();
        String sql="SELECT [id]\n"
                + "      ,[type_name]\n"
                + "      ,[description]\n"
                + "  FROM [dbo].[Song_types]";
        try{
            PreparedStatement st = connection.prepareStatement(sql);
            ResultSet rs = st.executeQuery();
            while(rs.next()){
                SongType s = new SongType();
                s.setId(rs.getInt("id"));
                s.setName(rs.getString("type_name"));
                s.setDescription(rs.getString("description"));
                list.add(s);
            }
        }catch(SQLException e){
            System.out.println(e);
        }
        return list;
    }
    
    public SongType getSongTypeById(int id) {
        String sql = "SELECT [id]\n"
                + "      ,[type_name]\n"
                + "      ,[description]\n"
                + "  FROM [dbo].[Song_types] where id = ?";
        try{
            PreparedStatement st = connection.prepareStatement(sql);
            st.setInt(1, id);
            ResultSet rs = st.executeQuery();
            if(rs.next()){
                SongType s = new SongType();
                s.setId(rs.getInt("id"));
                s.setName(rs.getString("type_name"));
                s.setDescription(rs.getString("description"));
                return s;
            }
        }catch(SQLException e){
            System.out.println(e);
        }
        return null;
    }

}
