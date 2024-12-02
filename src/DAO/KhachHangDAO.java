/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package DAO;

import DTO.KhachHangDTO;
import config.DBConnector;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.ArrayList;

/**
 *
 * @author Admin
 */
public class KhachHangDAO {
    
    public static KhachHangDAO getInstance() {
        return new KhachHangDAO();
    }
    
    public ArrayList<KhachHangDTO> selectAll() {
        ArrayList<KhachHangDTO> result = new ArrayList<KhachHangDTO>();
        try {
            Connection conn = (Connection) DBConnector.getConnection();
            String query = "SELECT * FROM khachhang WHERE trangThai=1";
            PreparedStatement pst = (PreparedStatement) conn.prepareStatement(query);
            ResultSet rs = (ResultSet) pst.executeQuery();
            while(rs.next()){
                int id = rs.getInt("id");
                String ho = rs.getString("ho");
                String ten = rs.getString("ten");
                String sdt = rs.getString("soDienThoai");
                Timestamp ngayThamGia = rs.getTimestamp("ngayThamGia");
                int trangThai = rs.getInt("trangThai");
                KhachHangDTO kh = new KhachHangDTO(id, ho, ten, sdt, ngayThamGia, trangThai);
                result.add(kh);
            }
            DBConnector.closeConnection(conn);
        } catch (Exception e) {
            System.out.println(e);
        }
        return result;
    }
    
    public int getAutoIncrement() {
        int result = -1;
        try {
            Connection con = (Connection) DBConnector.getConnection();
            String sql = "SELECT `AUTO_INCREMENT` FROM  INFORMATION_SCHEMA.TABLES WHERE TABLE_SCHEMA = 'cuahangdienthoai' AND TABLE_NAME = 'khachhang'";
            PreparedStatement pst = (PreparedStatement) con.prepareStatement(sql);
            ResultSet rs = pst.executeQuery(sql);
            if (!rs.isBeforeFirst()) {
                System.out.println("No data");
            } else {
                while (rs.next()) {
                    result = rs.getInt("AUTO_INCREMENT");
                }
            }
        } catch (SQLException e) {
            System.out.println(e);
        }
        return result;
    }
    
    public int insert(KhachHangDTO kh){
       int result = 0;
       try{
           Connection conn = (Connection)DBConnector.getConnection();
           String query = "INSERT INTO `khachhang`(`ho`,`ten`,`soDienThoai`,`ngayThamGia`) VALUES (?,?,?,?)";
           PreparedStatement pst = (PreparedStatement) conn.prepareStatement(query);
           pst.setString(1, kh.getHo());
           pst.setString(2, kh.getTen());
           pst.setString(3, kh.getSoDienThoai());
           pst.setTimestamp(4, kh.getNgayThamGia());
           result = pst.executeUpdate();
           DBConnector.closeConnection(conn);
       }catch(Exception e){
           System.err.println(e);
       }
       return result;
    }
    
    public int update (KhachHangDTO kh){
        int result =0;
        try{
            Connection conn = (Connection) DBConnector.getConnection();
            String query = "UPDATE `khachhang` SET `ho`=?, `ten`=?,`soDienThoai`=?,`ngayThamGia`=? where `id`=?"; 
            PreparedStatement pst = (PreparedStatement) conn.prepareStatement(query);
            pst.setString(1, kh.getHo());
            pst.setString(2, kh.getTen());
            pst.setString(3,kh.getSoDienThoai());
            pst.setTimestamp(4,kh.getNgayThamGia());
            pst.setInt(5, kh.getId());
            result=pst.executeUpdate();
            DBConnector.closeConnection(conn);
        }catch (SQLException e){
            System.out.println(e);
        }
        return result;
    }
    
    public int delete(int id){
        int result =0;
        try{
            Connection conn = (Connection) DBConnector.getConnection();
            String query = "UPDATE `khachhang` SET `trangThai`=0 WHERE `id`=?";
            PreparedStatement pst = (PreparedStatement) conn.prepareCall(query);
            pst.setInt(1,id);
            result = pst.executeUpdate();
            DBConnector.closeConnection(conn);
        }catch(Exception e){
            System.err.println(e);
        }
        return result;
    }
}
