/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package DAO;

import DTO.SanPhamDTO;
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
public class SanPhamDAO {
    
    public static SanPhamDAO getInstance() {
        return new SanPhamDAO();
    }
    
    public ArrayList<SanPhamDTO> selectAll() {
        ArrayList<SanPhamDTO> result = new ArrayList<SanPhamDTO>();
        try {
            Connection conn = (Connection) DBConnector.getConnection();
            String query = "SELECT * FROM sanpham WHERE trangThai=1";
            PreparedStatement pst = (PreparedStatement) conn.prepareStatement(query);
            ResultSet rs = (ResultSet) pst.executeQuery();
            while(rs.next()){
                int id = rs.getInt("id");
                String ten = rs.getString("ten");
                String hinhAnh = rs.getString("hinhAnh");
                float kichThuocMan = rs.getFloat("kichThuocMan");
                String cameraSau = rs.getString("cameraSau");
                String cameraTruoc = rs.getString("cameraTruoc");
                String chipXuLy = rs.getString("chipXuLy");
                String heDieuHanh = rs.getString("heDieuHanh");
                int dungLuongPin = rs.getInt("dungLuongPin");
                int thuongHieu_id = rs.getInt("thuongHieu_id");
                int trangThai = rs.getInt("trangThai");
                SanPhamDTO sp = new SanPhamDTO(id, ten, hinhAnh, kichThuocMan, cameraSau, cameraTruoc, chipXuLy, heDieuHanh, dungLuongPin, thuongHieu_id, trangThai);
                result.add(sp);
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
            String sql = "SELECT `AUTO_INCREMENT` FROM  INFORMATION_SCHEMA.TABLES WHERE TABLE_SCHEMA = 'cuahangdienthoai' AND TABLE_NAME = 'sanpham'";
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
    
    public int insert(SanPhamDTO sp) {
        int result = 0;
        try {
            Connection conn = (Connection) DBConnector.getConnection();
            String sql = "INSERT INTO `sanpham`(`ten`, `hinhAnh`, `kichThuocMan`, `cameraSau`, `cameraTruoc`, `chipXuLy`, `heDieuHanh`, `dungLuongPin`, `thuongHieu_id`) VALUES (?,?,?,?,?,?,?,?,?)";
            PreparedStatement pst = (PreparedStatement) conn.prepareStatement(sql);
            pst.setString(1, sp.getTen());
            pst.setString(2, sp.getHinhAnh());
            pst.setFloat(3, sp.getKichThuocMan());
            pst.setString(4, sp.getCameraSau());
            pst.setString(5, sp.getCameraTruoc());
            pst.setString(6, sp.getChipXuLy());
            pst.setString(7, sp.getHeDieuHanh());
            pst.setInt(8, sp.getDungLuongPin());
            pst.setInt(9, sp.getIdThuongHieu());
            result = pst.executeUpdate();
            DBConnector.closeConnection(conn);
        } catch (SQLException ex) {
            Logger.getLogger(PhienBanSanPhamDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return result;
    }
    
    public int update(SanPhamDTO sp) {
        int result = 0;
        try {
            Connection conn = (Connection) DBConnector.getConnection();
            String query = "UPDATE `sanpham` SET `ten`=?,`hinhAnh`=?,`kichThuocMan`=?,`cameraSau`=?,`cameraTruoc`=?,`chipXuLy`=?,`heDieuHanh`=?,`dungLuongPin`=?,`thuongHieu_id`=? WHERE `id`=?";
            PreparedStatement pst = (PreparedStatement) conn.prepareStatement(query);
            pst.setString(1, sp.getTen());
            pst.setString(2, sp.getHinhAnh());
            pst.setFloat(3, sp.getKichThuocMan());
            pst.setString(4, sp.getCameraSau());
            pst.setString(5, sp.getCameraTruoc());
            pst.setString(6, sp.getChipXuLy());
            pst.setString(7, sp.getHeDieuHanh());
            pst.setInt(8, sp.getDungLuongPin());
            pst.setInt(9, sp.getIdThuongHieu());
            pst.setInt(10, sp.getId());
            result = pst.executeUpdate();
            DBConnector.closeConnection(conn);
        } catch (SQLException ex) {
            Logger.getLogger(SanPhamDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return result;
    }
    
    public int delete(int id) {
        int result = 0;
        try {
            Connection con = (Connection) DBConnector.getConnection();
            String sql = "UPDATE `sanpham` SET `trangThai`=0 WHERE id = ?";
            PreparedStatement pst = (PreparedStatement) con.prepareStatement(sql);
            pst.setInt(1, id);
            result = pst.executeUpdate();
            DBConnector.closeConnection(con);
        } catch (SQLException ex) {
            Logger.getLogger(SanPhamDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return result;
    }
    
}
