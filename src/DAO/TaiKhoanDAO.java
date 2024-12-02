/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package DAO;

import DTO.TaiKhoanDTO;
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
public class TaiKhoanDAO {
    
    public static TaiKhoanDAO getInstance() {
        return new TaiKhoanDAO();
    }
    
    public ArrayList<TaiKhoanDTO> selectAll(){
        ArrayList<TaiKhoanDTO> result = new ArrayList<>();
        try {
            Connection conn = (Connection) DBConnector.getConnection();
            String query = "SELECT * FROM taikhoan";
            PreparedStatement pst = (PreparedStatement) conn.prepareStatement(query);
            ResultSet rs = (ResultSet) pst.executeQuery();
            while(rs.next()){
                int id = rs.getInt("id");
                int idNhanVien = rs.getInt("nhanVien_id");
                int idQuyen = rs.getInt("quyen_id");
                String tenTaiKhoan = rs.getString("tenTaiKhoan");
                String matKhau = rs.getString("matKhau");
                int trangThai = rs.getInt("trangThai");
                TaiKhoanDTO tk = new TaiKhoanDTO(id, idNhanVien, idQuyen, tenTaiKhoan, matKhau, trangThai);
                result.add(tk);
            }
            DBConnector.closeConnection(conn);
        } catch (Exception e) {
            System.out.println(e);
        }
        return result;
    }
    
    public TaiKhoanDTO selectByUserName(String t) {
        TaiKhoanDTO result = null;
        try {
            Connection conn = (Connection) DBConnector.getConnection();
            String query = "SELECT * FROM taikhoan WHERE tenTaiKhoan=?";
            PreparedStatement pst = (PreparedStatement) conn.prepareStatement(query);
            pst.setString(1, t);
            ResultSet rs = (ResultSet) pst.executeQuery();
            while(rs.next()){
                int id = rs.getInt("id");
                int idNhanVien = rs.getInt("nhanVien_id");
                int idQuyen = rs.getInt("quyen_id");
                String tenTaiKhoan = rs.getString("tenTaiKhoan");
                String matKhau = rs.getString("matKhau");
                int trangThai = rs.getInt("trangThai");
                TaiKhoanDTO tk = new TaiKhoanDTO(id, idNhanVien, idQuyen, tenTaiKhoan, matKhau, trangThai);
                result = tk;
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
            Connection conn = (Connection) DBConnector.getConnection();
            String sql = "SELECT `AUTO_INCREMENT` FROM  INFORMATION_SCHEMA.TABLES WHERE TABLE_SCHEMA = 'cuahangdienthoai' AND TABLE_NAME = 'taikhoan'";
            PreparedStatement pst = (PreparedStatement) conn.prepareStatement(sql);
            ResultSet rs = pst.executeQuery();
            if(!rs.isBeforeFirst()){
                System.out.println("No data");
            } else {
                while(rs.next()){
                    result = rs.getInt("AUTO_INCREMENT");
                }
            }
        } catch (SQLException e) {
            System.out.println(e);
        }
        return result;
    }
    
    public int insert(TaiKhoanDTO tk){
        int result = 0;
        try {
            Connection conn = (Connection) DBConnector.getConnection();
            String query = "INSERT INTO `taikhoan` (`nhanVien_id`, `quyen_id`, `tenTaiKhoan`, `matKhau`) VALUES (?,?,?,?)";
            PreparedStatement pst = (PreparedStatement) conn.prepareStatement(query);
            pst.setInt(1, tk.getIdNhanVien());
            pst.setInt(2, tk.getIdQuyen());
            pst.setString(3, tk.getTenTaiKhoan());
            pst.setString(4, tk.getMatKhau());
            result = pst.executeUpdate();
            DBConnector.closeConnection(conn);
        } catch (Exception e) {
            System.out.println(e);
        }
        return result;
    }
    
    public int update(TaiKhoanDTO tk){
        int result = 0;
        try {
            Connection conn = (Connection) DBConnector.getConnection();
            String query = "UPDATE `taikhoan` SET `nhanVien_id`=?, `quyen_id`=?, `tenTaiKhoan`=?, `matKhau`=? WHERE `id`=?";
            PreparedStatement pst = (PreparedStatement) conn.prepareStatement(query);
            pst.setInt(1, tk.getIdNhanVien());
            pst.setInt(2, tk.getIdQuyen());
            pst.setString(3, tk.getTenTaiKhoan());
            pst.setString(4, tk.getMatKhau());
            pst.setInt(5, tk.getId());
            result = pst.executeUpdate();
            DBConnector.closeConnection(conn);
        } catch (Exception e) {
            System.out.println(e);
        }
        return result;
    }
    
    public int delete(int id){
        int result = 0;
        try {
            Connection conn = (Connection) DBConnector.getConnection();
            String query = "UPDATE `taikhoan` SET `trangThai`=0 WHERE `id`=?";
            PreparedStatement pst = (PreparedStatement) conn.prepareStatement(query);
            pst.setInt(1, id);
            result = pst.executeUpdate();
            DBConnector.closeConnection(conn);
        } catch (Exception e) {
            System.out.println(e);
        }
        return result;
    }
    
    public int ban(int id){
        int result = 0;
        try {
            Connection conn = (Connection) DBConnector.getConnection();
            String query = "UPDATE `taikhoan` SET `trangThai`=2 WHERE `id`=?";
            PreparedStatement pst = (PreparedStatement) conn.prepareStatement(query);
            pst.setInt(1, id);
            result = pst.executeUpdate();
            DBConnector.closeConnection(conn);
        } catch (Exception e) {
            System.out.println(e);
        }
        return result;
    }
    
    public int unban(int id){
        int result = 0;
        try {
            Connection conn = (Connection) DBConnector.getConnection();
            String query = "UPDATE `taikhoan` SET `trangThai`=1 WHERE `id`=?";
            PreparedStatement pst = (PreparedStatement) conn.prepareStatement(query);
            pst.setInt(1, id);
            result = pst.executeUpdate();
            DBConnector.closeConnection(conn);
        } catch (Exception e) {
            System.out.println(e);
        }
        return result;
    }
    
}
