/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package DAO;

import DTO.NhanVienDTO;
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
 * @author DVN
 */
public class NhanVienDAO {
    
    public static NhanVienDAO getInstance() {
        return new NhanVienDAO();
    }
    
    public ArrayList<NhanVienDTO> selectAll() {
        ArrayList<NhanVienDTO> result = new ArrayList<NhanVienDTO>();
        try {
            Connection conn = (Connection) DBConnector.getConnection();
            String query = "SELECT * FROM nhanvien";
            PreparedStatement pst = (PreparedStatement) conn.prepareStatement(query);
            ResultSet rs = (ResultSet) pst.executeQuery();
            while(rs.next()){
                int id = rs.getInt("id");
                String ho = rs.getString("ho");
                String ten = rs.getNString("ten");
                String gioiTinh = rs.getString("gioitinh");
                String sdt = rs.getString("soDienThoai");
                String email = rs.getString("email");
                int trangThai = rs.getInt("trangThai");
                NhanVienDTO nv = new NhanVienDTO(id, ho, ten, gioiTinh, sdt, email, trangThai);
                result.add(nv);
            }
            DBConnector.closeConnection(conn);
        } catch (Exception e) {
            System.out.println(e);
        }
        return result;
    }
    
    public ArrayList<NhanVienDTO> selectAllWithoutAccount() {
        ArrayList<NhanVienDTO> result = new ArrayList<NhanVienDTO>();
        try {
            Connection conn = (Connection) DBConnector.getConnection();
                String query = "SELECT * FROM nhanvien WHERE nhanvien.id NOT IN ( SELECT taikhoan.nhanVien_id FROM taikhoan)";
            PreparedStatement pst = (PreparedStatement) conn.prepareStatement(query);
            ResultSet rs = (ResultSet) pst.executeQuery();
            while(rs.next()){
                int id = rs.getInt("id");
                String ho = rs.getString("ho");
                String ten = rs.getNString("ten");
                String gioiTinh = rs.getString("gioitinh");
                String sdt = rs.getString("soDienThoai");
                String email = rs.getString("email");
                int trangThai = rs.getInt("trangThai");
                NhanVienDTO nv = new NhanVienDTO(id, ho, ten, gioiTinh, sdt, email, trangThai);
                result.add(nv);
            }
            DBConnector.closeConnection(conn);
        } catch (Exception e) {
            System.out.println(e);
        }
        return result;
    }
    
    public NhanVienDTO selectByAccountId(String t) {
        NhanVienDTO nv = null;
        try{
            Connection con = DBConnector.getConnection();
            String query = "SELECT * FROM taikhoan tk join nhanvien nv on tk.nhanVien_id=nv.id where tk.id=?";
            PreparedStatement pst = con.prepareStatement(query);
            pst.setString(1,t);
            ResultSet rs = pst.executeQuery();
            while (rs.next()) {
                int id = rs.getInt("id");
                String ho = rs.getString("ho");
                String ten = rs.getNString("ten");
                String gioiTinh = rs.getString("gioitinh");
                String sdt = rs.getString("soDienThoai");
                String email = rs.getString("email");
                int trangThai = rs.getInt("trangThai");
                nv = new NhanVienDTO(id, ho, ten, gioiTinh, sdt, email, trangThai);
            }
            DBConnector.closeConnection(con);
        } catch (Exception e) {
            // TODO: handle exception  
            System.out.println(e);
        }
        return nv;
    }
    
    public int getAutoIncrement() {
        int result = -1;
        try {
            Connection con = (Connection) DBConnector.getConnection();
            String sql = "SELECT `AUTO_INCREMENT` FROM  INFORMATION_SCHEMA.TABLES WHERE TABLE_SCHEMA = 'cuahangdienthoai' AND TABLE_NAME = 'nhanvien'";
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
    
    public int insert(NhanVienDTO nv) {
        int result = 0;
        try {
            Connection conn = (Connection) DBConnector.getConnection();
            String query = "INSERT INTO `nhanvien`(`ho`, `ten`, `gioiTinh`, `soDienThoai`, `email`) VALUES (?,?,?,?,?)";
            PreparedStatement pst = (PreparedStatement) conn.prepareStatement(query);
            pst.setString(1, nv.getHo());
            pst.setString(2, nv.getTen());
            pst.setString(3, nv.getGioiTinh());
            pst.setString(4, nv.getSoDienThoai());
            pst.setString(5, nv.getEmail());
            result = pst.executeUpdate();
            DBConnector.closeConnection(conn);
        } catch (SQLException e) {
            System.out.println(e);
        }
        return result;
    }
    
    public int update(NhanVienDTO nv) {
        int result = 0;
        try {
            Connection conn = (Connection) DBConnector.getConnection();
            String query = "UPDATE `nhanvien` SET `ho`=?,`ten`=?,`gioiTinh`=?,`soDienThoai`=?,`email`=? WHERE `id`=?";
            PreparedStatement pst = (PreparedStatement) conn.prepareStatement(query);
            pst.setString(1, nv.getHo());
            pst.setString(2, nv.getTen());
            pst.setString(3, nv.getGioiTinh());
            pst.setString(4, nv.getSoDienThoai());
            pst.setString(5, nv.getEmail());
            pst.setInt(6, nv.getId());
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
            String query = "UPDATE `nhanvien` nv, `taikhoan` tk SET nv.`trangThai`=0, tk.trangThai=2 WHERE nv.`id`= ? and tk.`nhanVien_id`=?";
            PreparedStatement pst = (PreparedStatement) conn.prepareStatement(query);
            pst.setInt(1, id);
            pst.setInt(2, id);
            result = pst.executeUpdate();
            DBConnector.closeConnection(conn);
        } catch (SQLException e) {
            System.out.println(e);
        }
        return result;
    }
    
}