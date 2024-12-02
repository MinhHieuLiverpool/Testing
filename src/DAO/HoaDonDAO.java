/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package DAO;

import DTO.HoaDonDTO;
import config.DBConnector;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
/**
 *
 * @author Windows
 */
public class HoaDonDAO {
    
    public static HoaDonDAO getIntance(){
        return new HoaDonDAO();
    }
    
    public ArrayList<HoaDonDTO> selectAll(){
        ArrayList<HoaDonDTO> result = new ArrayList<>();
        try {
            Connection conn = (Connection) DBConnector.getConnection();
            String query = "SELECT * FROM hoadon";
            PreparedStatement pst = (PreparedStatement) conn.prepareStatement(query);
            ResultSet rs = (ResultSet) pst.executeQuery();
            while(rs.next()){
                int id = rs.getInt("id");
                int khachHangId = rs.getInt("khacHang_id");
                int nhanVienId = rs.getInt("nhanvien_id");
                int khuyenMaiId = rs.getInt("khuyenMai_id");
                Timestamp ngayXuat = rs.getTimestamp("ngayXuat");
                long tongTien = rs.getLong("tongTien");
                HoaDonDTO hd = new HoaDonDTO(id,khachHangId,nhanVienId,ngayXuat,tongTien,khuyenMaiId);
                result.add(hd);
            }
            
        } catch (Exception e) {
            System.out.println(e);
        }
        return result;
    }
    
    public int insert(HoaDonDTO hd){
        int result = 0;
        try {
            Connection conn = (Connection) DBConnector.getConnection();
            String query = "INSERT INTO hoadon (id,khacHang_id,nhanVien_id,khuyenMai_id,ngayXuat,tongTien) VALUES (?,?,?,?,?,?)";
            PreparedStatement pst = (PreparedStatement) conn.prepareStatement(query);
            pst.setInt(1, hd.getId());
            pst.setInt(2, hd.getIdKhachHang());
            pst.setInt(3, hd.getIdNhanVien());
            int kmid = hd.getIdKhuyenMai();
            if(kmid == -1){
                pst.setString(4, null);
            }else{
            pst.setInt(4, hd.getIdKhuyenMai());
            }
            pst.setTimestamp(5, hd.getNgayXuat());
            pst.setLong(6, hd.getTongTien());
            result = pst.executeUpdate();
            System.out.println(pst);
            DBConnector.closeConnection(conn);
        } catch (Exception e) {
            System.out.println(e);
        }
        return result;
    }
    
    public int delete(int id) {
        int result = 0;
        try {
            Connection con = (Connection) DBConnector.getConnection();
            String sql = "DELETE FROM `hoadon` WHERE id = ?";
            PreparedStatement pst = (PreparedStatement) con.prepareStatement(sql);
            pst.setInt(1, id);
            result = pst.executeUpdate();
            DBConnector.closeConnection(con);
        } catch (SQLException ex) {
            System.out.println(ex);
        }
        return result;
    }
        
    public int getAutoIncrement(){
        int result = -1;
        try {
            Connection con = (Connection) DBConnector.getConnection();
            String sql = "SELECT `AUTO_INCREMENT` FROM  INFORMATION_SCHEMA.TABLES WHERE TABLE_SCHEMA = 'cuahangdienthoai' AND TABLE_NAME = 'hoadon'";
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
