/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package DAO;

import DTO.PhienBanSanPhamDTO;
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
public class PhienBanSanPhamDAO {
    
    public static PhienBanSanPhamDAO getInstance() {
        return new PhienBanSanPhamDAO();
    }
    
    public ArrayList<PhienBanSanPhamDTO> selectAll() {
        ArrayList<PhienBanSanPhamDTO> result = new ArrayList<>();
        try {
            Connection conn = (Connection) DBConnector.getConnection();
            String query = "SELECT * FROM pbsanpham WHERE trangThai=1";
            PreparedStatement pst = (PreparedStatement) conn.prepareStatement(query);
            ResultSet rs = (ResultSet) pst.executeQuery();
            while(rs.next()){
                int id = rs.getInt("id");
                int sanPham_id = rs.getInt("sanPham_id");
                int ram = rs.getInt("ram");
                int rom = rs.getInt("rom");
                String mau = rs.getString("mau");
                int soLuong = rs.getInt("soLuong");
                int trangThai = rs.getInt("trangThai");
                PhienBanSanPhamDTO sp = new PhienBanSanPhamDTO(id, sanPham_id, ram, rom, mau, soLuong, trangThai);
                result.add(sp);
            }
            DBConnector.closeConnection(conn);
        } catch (SQLException e) {
            System.out.println(e);
        }
        return result;
    }
    
    public ArrayList<PhienBanSanPhamDTO> selectAllByID(int spId) {
        ArrayList<PhienBanSanPhamDTO> result = new ArrayList<>();
        try {
            Connection conn = (Connection) DBConnector.getConnection();
            String query = "SELECT * FROM pbsanpham WHERE trangThai=1 AND sanPham_id="+spId;
            PreparedStatement pst = (PreparedStatement) conn.prepareStatement(query);
            ResultSet rs = (ResultSet) pst.executeQuery();
            while(rs.next()){
                int id = rs.getInt("id");
                int sanPham_id = rs.getInt("sanPham_id");
                int ram = rs.getInt("ram");
                int rom = rs.getInt("rom");
                String mau = rs.getString("mau");
                int soLuong = rs.getInt("soLuong");
                int trangThai = rs.getInt("trangThai");
                PhienBanSanPhamDTO sp = new PhienBanSanPhamDTO(id, sanPham_id, ram, rom, mau, soLuong, trangThai);
                result.add(sp);
            }
            DBConnector.closeConnection(conn);
        } catch (SQLException e) {
            System.out.println(e);
        }
        return result;
    }
    
    public PhienBanSanPhamDTO selectById(int pbspId) {
        PhienBanSanPhamDTO pbsp = null;
        try {
            Connection con = (Connection) DBConnector.getConnection();
            String sql = "SELECT * FROM pbsanpham WHERE id = ?";
            PreparedStatement pst = (PreparedStatement) con.prepareStatement(sql);
            pst.setInt(1, pbspId);
            ResultSet rs = (ResultSet) pst.executeQuery();
            while (rs.next()) {
                int id = rs.getInt("id");
                int idSanPham = rs.getInt("sanPham_id");
                int ram = rs.getInt("ram");
                int rom = rs.getInt("rom");
                String mau = rs.getString("mau");
                int soLuong = rs.getInt("soLuong");
                int trangThai = rs.getInt("trangThai");
                pbsp = new PhienBanSanPhamDTO(id, idSanPham, ram, rom, mau, soLuong, trangThai);
            }
            DBConnector.closeConnection(con);
        } catch (SQLException e) {
        }
        return pbsp;
    }
    
    public int getAutoIncrement() {
        int result = -1;
        try {
            Connection con = (Connection) DBConnector.getConnection();
            String sql = "SELECT `AUTO_INCREMENT` FROM  INFORMATION_SCHEMA.TABLES WHERE TABLE_SCHEMA = 'cuahangdienthoai' AND TABLE_NAME = 'pbsanpham'";
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
    
    public int insert(PhienBanSanPhamDTO pbsp) {
        int result = 0;
        try {
            Connection conn = (Connection) DBConnector.getConnection();
            String sql = "INSERT INTO `pbsanpham`(`sanPham_id`, `ram`, `rom`, `mau`) VALUES (?,?,?,?)";
            PreparedStatement pst = (PreparedStatement) conn.prepareStatement(sql);
            pst.setInt(1, pbsp.getIdSanPham());
            pst.setInt(2, pbsp.getRam());
            pst.setInt(3, pbsp.getRom());
            pst.setString(4, pbsp.getMau());
            result = pst.executeUpdate();
            DBConnector.closeConnection(conn);
        } catch (SQLException ex) {
            Logger.getLogger(PhienBanSanPhamDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return result;
    }
    
    public int insert(ArrayList<PhienBanSanPhamDTO> pbspList) {
        int result = 0;
        for(PhienBanSanPhamDTO i : pbspList) {
            try {
                Connection conn = (Connection) DBConnector.getConnection();
                String sql = "INSERT INTO `pbsanpham`(`sanPham_id`, `ram`, `rom`, `mau`) VALUES (?,?,?,?)";
                PreparedStatement pst = (PreparedStatement) conn.prepareStatement(sql);
                pst.setInt(1, i.getIdSanPham());
                pst.setInt(2, i.getRam());
                pst.setInt(3, i.getRom());
                pst.setString(4, i.getMau());
                result = pst.executeUpdate();
                DBConnector.closeConnection(conn);
            } catch (SQLException ex) {
                Logger.getLogger(PhienBanSanPhamDAO.class.getName()).log(Level.SEVERE, null, ex);
            }
        }    
        return result;
    }
    
    public int update(ArrayList<PhienBanSanPhamDTO> pbspList) {
        int result = 0;
        for (PhienBanSanPhamDTO i : pbspList) {
            try {
                Connection conn = (Connection) DBConnector.getConnection();
                String query = "UPDATE `pbsanpham` SET `ram`=?,`rom`=?,`mau`=?,`trangThai`=? WHERE id=?";
                PreparedStatement pst = (PreparedStatement) conn.prepareStatement(query);
                pst.setInt(1, i.getRam());
                pst.setInt(2, i.getRom());
                pst.setString(3, i.getMau());
                pst.setInt(4, i.getTrangThai());
                pst.setInt(5, i.getId());
                result = pst.executeUpdate();
                DBConnector.closeConnection(conn);
            } catch (SQLException ex) {
                Logger.getLogger(SanPhamDAO.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        return result;
    }
    
    public int delete(int id) {
        int result = 0;
        try {
            Connection con = (Connection) DBConnector.getConnection();
            String sql = "UPDATE `pbsanpham` SET `trangThai`=0 WHERE id = ?";
            PreparedStatement pst = (PreparedStatement) con.prepareStatement(sql);
            pst.setInt(1, id);
            result = pst.executeUpdate();
            DBConnector.closeConnection(con);
        } catch (SQLException ex) {
            Logger.getLogger(SanPhamDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return result;
    }
    
    public int tangSoLuong(int id, int soLuong) {
        PhienBanSanPhamDTO pbsp = this.selectById(id);
        int result = 0;
        int newSoLuong = pbsp.getSoLuong()+ soLuong;
        try {
            Connection con = (Connection) DBConnector.getConnection();
            String sql = "UPDATE `pbsanpham` SET `soLuong`=? WHERE id = ?";
            PreparedStatement pst = (PreparedStatement) con.prepareStatement(sql);
            pst.setInt(1, newSoLuong);
            pst.setInt(2, pbsp.getId());
            result = pst.executeUpdate();
            DBConnector.closeConnection(con);
        } catch (SQLException ex) {
            Logger.getLogger(PhienBanSanPhamDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return result;
    }
    
    public int giamSoLuong(int id, int soLuong){
        PhienBanSanPhamDTO pbsp = this.selectById(id);
        int result = 0;
        int newSoLuong = pbsp.getSoLuong()- soLuong;
        try {
            Connection con = (Connection) DBConnector.getConnection();
            String sql = "UPDATE `pbsanpham` SET `soLuong`=? WHERE id = ?";
            PreparedStatement pst = (PreparedStatement) con.prepareStatement(sql);
            pst.setInt(1, newSoLuong);
            pst.setInt(2, pbsp.getId());
            result = pst.executeUpdate();
            DBConnector.closeConnection(con);
        } catch (SQLException ex) {
            Logger.getLogger(PhienBanSanPhamDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return result;
    }
}
