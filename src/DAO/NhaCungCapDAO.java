/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package DAO;

import DTO.NhaCungCapDTO;
import config.DBConnector;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

/**
 *
 * @author Admin
 */
public class NhaCungCapDAO {
    
    public static NhaCungCapDAO getInstance() {
        return new NhaCungCapDAO();
    }
    
    public ArrayList<NhaCungCapDTO> selectAll() {
        ArrayList<NhaCungCapDTO> result = new ArrayList<NhaCungCapDTO>();
        try {
            Connection conn = (Connection) DBConnector.getConnection();
            String query = "SELECT * FROM nhacungcap WHERE trangThai=1";
            PreparedStatement pst = (PreparedStatement) conn.prepareStatement(query);
            ResultSet rs = (ResultSet) pst.executeQuery();
            while(rs.next()){
                int id = rs.getInt("id");
                String ten = rs.getNString("ten");
                String diaChi = rs.getString("diaChi");
                String soDienThoai = rs.getString("soDienThoai");
                String email = rs.getString("email");
                int trangThai = rs.getInt("trangThai");
                NhaCungCapDTO nv = new NhaCungCapDTO(id, ten, diaChi, soDienThoai, email, trangThai);
                result.add(nv);
            }
            DBConnector.closeConnection(conn);
        } catch (Exception e) {
            System.out.println(e);
        }
        return result;
    }
    
    public int getAuroIncrement(){
        int result = -1;
        try {
            Connection conn = (Connection) DBConnector.getConnection();
            String sql = "SELECT `AUTO_INCREMENT` FROM INFORMATION_SCHEMA.TABLES WHERE TABLE_SCHEMA = 'cuahangdienthoai' AND TABLE_NAME = 'nhacungcap'";
            PreparedStatement pst = (PreparedStatement) conn.prepareStatement(sql);
            ResultSet rs = pst.executeQuery(sql);
            if(!rs.isBeforeFirst()) {
                System.out.println("No data");
            } else {
                while (rs.next()){
                    result = rs.getInt("AUTO_INCREMENT");
                }
            }
        } catch (Exception e) {
            System.out.println(e);
        }
        return result;
    }
    
    public int insert(NhaCungCapDTO ncc){
        int result = 0;
        try {
            Connection conn = (Connection) DBConnector.getConnection();
            String query = "INSERT INTO `nhacungcap`(`ten`,`diaChi`,`soDienThoai`,`email`) VALUES (?,?,?,?)";
            PreparedStatement pst = (PreparedStatement) conn.prepareStatement(query);
            pst.setString(1, ncc.getTen());
            pst.setString(2, ncc.getDiaChi());
            pst.setString(3, ncc.getSoDienThoai());
            pst.setString(4, ncc.getEmail());
            result = pst.executeUpdate();
            DBConnector.closeConnection(conn);
        } catch (SQLException e){
            System.out.println(e);
        }
        return result;
    }
    
//    <---- Hiếu test --->
//    public int insert(NhaCungCapDTO ncc) {
//    int result = 0;
//    try {
//        // Kiểm tra các trường không được rỗng
//        if (ncc.getTen() == null || ncc.getTen().trim().isEmpty() ||
//            ncc.getDiaChi() == null || ncc.getDiaChi().trim().isEmpty() ||
//            ncc.getSoDienThoai() == null || ncc.getSoDienThoai().trim().isEmpty() ||
//            ncc.getEmail() == null || ncc.getEmail().trim().isEmpty()) {
//            throw new IllegalArgumentException("Các trường không được để trống!");
//        }
//
//        Connection conn = (Connection) DBConnector.getConnection();
//        String query = "INSERT INTO `nhacungcap`(`ten`, `diaChi`, `soDienThoai`, `email`) VALUES (?,?,?,?)";
//        PreparedStatement pst = (PreparedStatement) conn.prepareStatement(query);
//        pst.setString(1, ncc.getTen());
//        pst.setString(2, ncc.getDiaChi());
//        pst.setString(3, ncc.getSoDienThoai());
//        pst.setString(4, ncc.getEmail());
//        result = pst.executeUpdate();
//        DBConnector.closeConnection(conn);
//    } catch (IllegalArgumentException e) {
//        System.out.println("Lỗi: " + e.getMessage());
//    } catch (SQLException e) {
//        System.out.println(e);
//    }
//    return result;
//}
////    <---- Hiếu test --->
    public int update(NhaCungCapDTO ncc){
        int result = 0;
        try {
            Connection conn = (Connection) DBConnector.getConnection();
            String query = "UPDATE `nhacungcap` SET `ten`=?, `diaChi`=?, `soDienThoai`=?, `email`=? WHERE `id`=?";
            PreparedStatement pst = (PreparedStatement) conn.prepareStatement(query);
            pst.setString(1, ncc.getTen());
            pst.setString(2, ncc.getDiaChi());
            pst.setString(3, ncc.getSoDienThoai());
            pst.setString(4, ncc.getEmail());
            pst.setInt(5, ncc.getId());
            result = pst.executeUpdate();
            DBConnector.closeConnection(conn);
        } catch (SQLException e) {
            System.out.println(e);
        }
        return result;
    }
    
    public int delete(int id){
        int result = 0;
        try {
            Connection conn = (Connection) DBConnector.getConnection();
            String query = "UPDATE `nhacungcap` SET `trangthai`=0 WHERE `id`=?";
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
