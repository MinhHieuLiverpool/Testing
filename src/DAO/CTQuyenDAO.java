/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package DAO;

import DTO.CTQuyenDTO;
import config.DBConnector;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Admin
 */
public class CTQuyenDAO {
    
    public static CTQuyenDAO getInstance() {
        return new CTQuyenDAO();
    }
    
    public ArrayList<CTQuyenDTO> selectAll() {
        ArrayList<CTQuyenDTO> result = new ArrayList<>();
        try {
            Connection conn = (Connection) DBConnector.getConnection();
            String query = "SELECT * FROM ctquyen";
            PreparedStatement pst = (PreparedStatement) conn.prepareStatement(query);
            ResultSet rs = (ResultSet) pst.executeQuery();
            while(rs.next()){
                int idQuyen = rs.getInt("quyen_id");
                int idChucNang = rs.getInt("chucNang_id");
                String hanhDong =  rs.getString("hanhDong");
                CTQuyenDTO quyen = new CTQuyenDTO(idQuyen, idChucNang, hanhDong);
                result.add(quyen);
            }
            DBConnector.closeConnection(conn);
        } catch (Exception e) {
            System.out.println(e);
        }
        return result;
    }
    
    public ArrayList<CTQuyenDTO> selectAll(int qId) {
        ArrayList<CTQuyenDTO> result = new ArrayList<>();
        try {
            Connection con = (Connection) DBConnector.getConnection();
            String sql = "SELECT * FROM ctquyen WHERE quyen_id = ?";
            PreparedStatement pst = (PreparedStatement) con.prepareStatement(sql);
            pst.setInt(1, qId);
            ResultSet rs = (ResultSet) pst.executeQuery();
            while (rs.next()) {
                int idQuyen = rs.getInt("quyen_id");
                int idChucNang = rs.getInt("chucNang_id");
                String hanhDong = rs.getString("hanhDong");
                CTQuyenDTO quyen = new CTQuyenDTO(idQuyen, idChucNang, hanhDong);
                result.add(quyen);
            }
            DBConnector.closeConnection(con);
        } catch (SQLException e) {
            System.out.println(e);
        }
        return result;
    }
    
    public int insert(ArrayList<CTQuyenDTO> ctqList) {
        int result = 0;
        for (CTQuyenDTO i : ctqList) {
            try {
                Connection con = (Connection) DBConnector.getConnection();
                String sql = "INSERT INTO `ctquyen`(`quyen_id`,`chucNang_id`,`hanhDong`) VALUES (?,?,?)";
                PreparedStatement pst = (PreparedStatement) con.prepareStatement(sql);
                pst.setInt(1, i.getIdQuyen());
                pst.setInt(2, i.getIdChucNang());
                pst.setString(3, i.getHanhDong());
                result = pst.executeUpdate();
                DBConnector.closeConnection(con);
            } catch (SQLException ex) {
                System.out.println(ex);
            }
        }
        return result;
    }
    
    public int delete(int qId) {
        int result = 0;
        try {
            Connection con = (Connection) DBConnector.getConnection();
            String sql = "DELETE FROM ctquyen WHERE quyen_id = ?";
            PreparedStatement pst = con.prepareStatement(sql);
            pst.setInt(1, qId);
            result = pst.executeUpdate();
            DBConnector.closeConnection(con);
        } catch (SQLException ex) {
            System.out.println(ex);
        }
        return result;
    }
    
    public int update(ArrayList<CTQuyenDTO> ctqList, int qId) {
        int result = this.delete(qId);
        if(result >= 0) result = this.insert(ctqList);
        return result;
    }
    
}
