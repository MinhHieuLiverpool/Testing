/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package DAO;

import DTO.CTBaoHanhDTO;
import config.DBConnector;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

/**
 *
 * @author Windows
 */
public class CTBaoHanhDAO {
        
    public static CTBaoHanhDAO getInstance() {
        return new CTBaoHanhDAO();
    }
    
    public ArrayList<CTBaoHanhDTO> selectAll() {
        ArrayList<CTBaoHanhDTO> result = new ArrayList<>();
        try {
            Connection conn = (Connection) DBConnector.getConnection();
            String query = "SELECT * FROM ctbaohanh";
            PreparedStatement pst = (PreparedStatement) conn.prepareStatement(query);
            ResultSet rs = (ResultSet) pst.executeQuery();
            while(rs.next()){
                int id = rs.getInt("id");
                int baoHanhId = rs.getInt("baoHanh_id");
                int hoaDonId = rs.getInt("hoaDon_id");
                String imeiSp = rs.getString("imei_sanPham");
                java.sql.Timestamp ngayKetThuc = rs.getTimestamp("ngayKetThuc");
                CTBaoHanhDTO ctbh = new CTBaoHanhDTO(id, baoHanhId, hoaDonId, imeiSp, ngayKetThuc);
                result.add(ctbh);
            }
        } catch (Exception e) {
            System.out.println(e);
        }
        return result;
    }
    
    public ArrayList<CTBaoHanhDTO> selectAllByHoaDonID(int hdId) {
        ArrayList<CTBaoHanhDTO> result = new ArrayList<>();
        try {
            Connection conn = (Connection) DBConnector.getConnection();
            String query = "SELECT * FROM ctbaohanh WHERE hoaDon_id="+hdId;
            PreparedStatement pst = (PreparedStatement) conn.prepareStatement(query);
            ResultSet rs = (ResultSet) pst.executeQuery();
            while(rs.next()){
                int id = rs.getInt("id");
                int baoHanhId = rs.getInt("baoHanh_id");
                int hoaDonId = rs.getInt("hoaDon_id");
                String imeiSp = rs.getString("imei_sanPham");
                java.sql.Timestamp ngayKetThuc = rs.getTimestamp("ngayKetThuc");
                CTBaoHanhDTO ctbh = new CTBaoHanhDTO(id, baoHanhId, hoaDonId, imeiSp, ngayKetThuc);
                result.add(ctbh);
            }
        } catch (Exception e) {
            System.out.println(e);
        }
        return result;
    }
    
    public int insert(ArrayList<CTBaoHanhDTO> ctbhList) {
        int result = 0;
        for (int i = 0; i < ctbhList.size(); i++) {
            try {
                Connection con = (Connection) DBConnector.getConnection();
                String sql = "INSERT INTO `ctbaohanh`(`id`, `baoHanh_id`, `hoaDon_id`, `imei_sanPham`, `ngayKetThuc`) VALUES (?,?,?,?,?)";
                PreparedStatement pst = (PreparedStatement) con.prepareStatement(sql);
                pst.setInt(1, ctbhList.get(i).getId());
                pst.setInt(2, ctbhList.get(i).getIdBaoHanh());
                pst.setInt(3, ctbhList.get(i).getIdHoaDon());
                pst.setString(4, ctbhList.get(i).getImeiSanPham());
                pst.setTimestamp(5, ctbhList.get(i).getNgayKetThuc());
                result = pst.executeUpdate();
                DBConnector.closeConnection(con);
            } catch (SQLException ex) {
                Logger.getLogger(CTPhieuNhapDAO.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        return result;
    }
    
    public int deleteByHDId(int hdId) {
        int result = 0;
        try {
            Connection con = (Connection) DBConnector.getConnection();
            String sql = "DELETE FROM `ctbaohanh` WHERE hoaDon_id = ?";
            PreparedStatement pst = (PreparedStatement) con.prepareStatement(sql);
            pst.setInt(1, hdId);
            result = pst.executeUpdate();
            DBConnector.closeConnection(con);
        } catch (SQLException ex) {
            System.out.println(ex);
        }
        return result;
    }
    
    public int getAutoIncrement() {
        int result = -1;
        try {
            Connection con = (Connection) DBConnector.getConnection();
            String sql = "SELECT `AUTO_INCREMENT` FROM  INFORMATION_SCHEMA.TABLES WHERE TABLE_SCHEMA = 'cuahangdienthoai' AND TABLE_NAME = 'ctbaohanh'";
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
