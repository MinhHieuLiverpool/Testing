/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package DAO;

import DTO.PhieuNhapDTO;
import config.DBConnector;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.sql.Timestamp;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Admin
 */
public class PhieuNhapDAO {
    
    public static PhieuNhapDAO getInstance() {
        return new PhieuNhapDAO();
    }
    
    public ArrayList<PhieuNhapDTO> selectAll() {
        ArrayList<PhieuNhapDTO> result = new ArrayList<>();
        try {
            Connection conn = (Connection) DBConnector.getConnection();
            String query = "SELECT * FROM phieunhap";
            PreparedStatement pst = (PreparedStatement) conn.prepareStatement(query);
            ResultSet rs = (ResultSet) pst.executeQuery();
            while(rs.next()){
                int id = rs.getInt("id");
                int idNhaCungCap = rs.getInt("nhaCungCap_id");
                int idNhanVien = rs.getInt("nhanVien_id");
                Timestamp ngayNhap = rs.getTimestamp("ngayNhap");
                long tongTien = rs.getLong("tongTien");
                PhieuNhapDTO pn = new PhieuNhapDTO(id, idNhaCungCap, idNhanVien, ngayNhap, tongTien);
                result.add(pn);
            }
        } catch (Exception e) {
            System.out.println(e);
        }
        return result;
    }
    
    public int insert(PhieuNhapDTO pn) {
        int result = 0;
        try {
            Connection conn = (Connection) DBConnector.getConnection();
            String query = "INSERT INTO `phieunhap`(`id`, `nhaCungCap_id`, `nhanVien_id`, `ngayNhap`, `tongTien`) VALUES (?,?,?,?,?)";
            PreparedStatement pst = (PreparedStatement) conn.prepareStatement(query);
            pst.setInt(1, pn.getId());
            pst.setInt(2, pn.getIdNhaCungCap());
            pst.setInt(3, pn.getIdNhanVien());
            pst.setTimestamp(4, pn.getNgayNhap());
            pst.setLong(5, pn.getTongTien());
            result = pst.executeUpdate();
            DBConnector.closeConnection(conn);
        } catch (SQLException e) {
            System.out.println(e);
        }
        return result;
    }
    
    public int getAutoIncrement() {
        int result = -1;
        try {
            Connection con = (Connection) DBConnector.getConnection();
            String sql = "SELECT `AUTO_INCREMENT` FROM  INFORMATION_SCHEMA.TABLES WHERE TABLE_SCHEMA = 'cuahangdienthoai' AND TABLE_NAME = 'phieunhap'";
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
            Logger.getLogger(SanPhamDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return result;
    }
    
}
