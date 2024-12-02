/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package DAO;

import DTO.QuyenDTO;
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
public class QuyenDAO {
    
    public static QuyenDAO getInstance() {
        return new QuyenDAO();
    }
    
    public ArrayList<QuyenDTO> selectAll() {
        ArrayList<QuyenDTO> result = new ArrayList<QuyenDTO>();
        try {
            Connection conn = (Connection) DBConnector.getConnection();
            String query = "SELECT * FROM quyen WHERE trangThai=1";
            PreparedStatement pst = (PreparedStatement) conn.prepareStatement(query);
            ResultSet rs = (ResultSet) pst.executeQuery();
            while(rs.next()){
                int id = rs.getInt("id");
                String ten = rs.getString("ten");
                int trangThai = rs.getInt("trangThai");
                QuyenDTO quyen = new QuyenDTO(id, ten, trangThai);
                result.add(quyen);
            }
            DBConnector.closeConnection(conn);
        } catch (Exception e) {
            System.out.println(e);
        }
        return result;
    }
    
    public int getAutoIncrement(){
        int result = -1;
        try {
            Connection con = (Connection) DBConnector.getConnection();
            String sql = "SELECT `AUTO_INCREMENT` FROM  INFORMATION_SCHEMA.TABLES WHERE TABLE_SCHEMA = 'cuahangdienthoai' AND TABLE_NAME = 'quyen'";
            PreparedStatement pst = (PreparedStatement) con.prepareStatement(sql);
            ResultSet rs = pst.executeQuery(sql);
            if (!rs.isBeforeFirst()) {
                System.out.println("No data");
            } else {
                while (rs.next()) {
                    result = rs.getInt("AUTO_INCREMENT");
                }
            }
        } catch (SQLException ex) {
            System.out.println(ex);
        }
        return result;
    }
    
    public int insert(QuyenDTO q) {
        int result = 0;
        try {
            Connection conn = (Connection) DBConnector.getConnection();
            String query = "INSERT INTO `quyen`(`id`, `ten`, `trangThai`) VALUES (?,?,?)";
            PreparedStatement pst = (PreparedStatement) conn.prepareStatement(query);
            pst.setInt(1, q.getId());
            pst.setString(2, q.getTen());
            pst.setInt(3, q.getTrangThai());
            result = pst.executeUpdate();
            DBConnector.closeConnection(conn);
        } catch (SQLException e) {
            System.out.println(e);
        }
        return result;
    }
    
    public int update(QuyenDTO q) {
        int result = 0;
        try {
            Connection conn = (Connection) DBConnector.getConnection();
            String query = "UPDATE `quyen` SET `ten`=? WHERE `id`=?";
            PreparedStatement pst = (PreparedStatement) conn.prepareStatement(query);
            pst.setString(1, q.getTen());
            pst.setInt(2, q.getId());
            result = pst.executeUpdate();
            DBConnector.closeConnection(conn);
        } catch (SQLException e) {
            System.out.println(e);
        }
        return result;
    }
    
    public int delete(int id) {
        int result = 0;
        try {
            Connection conn = (Connection) DBConnector.getConnection();
            String query = "UPDATE `quyen` SET `trangThai`=0 WHERE `id`=?";
            PreparedStatement pst = (PreparedStatement) conn.prepareStatement(query);
            pst.setInt(1, id);
            result = pst.executeUpdate();
            DBConnector.closeConnection(conn);
        } catch (SQLException e) {
            System.out.println(e);
        }
        return result;
    }
    
}
