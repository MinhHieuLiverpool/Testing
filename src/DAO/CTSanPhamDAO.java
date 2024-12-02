/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package DAO;

import DTO.CTSanPhamDTO;
import DAO.PhienBanSanPhamDAO;
import java.sql.Connection;
import config.DBConnector;
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
public class CTSanPhamDAO {
    
    public static CTSanPhamDAO getInstance() {
        return new CTSanPhamDAO();
    }
    
    public ArrayList<CTSanPhamDTO> getAll() {
        ArrayList<CTSanPhamDTO> result = new ArrayList<>();
        try {
            Connection conn = (Connection) DBConnector.getConnection();
            String query = "SELECT * FROM ctsanpham";
            PreparedStatement pst = (PreparedStatement) conn.prepareStatement(query);
            ResultSet rs = (ResultSet) pst.executeQuery();
            while(rs.next()){
                String imei = rs.getString("imei");
                int idSanPham = rs.getInt("sanPham_id");
                int idPBSanPham = rs.getInt("pbSanPham_id");
                int idPhieuNhap = rs.getInt("phieuNhap_id");
                int giaNhap = rs.getInt("giaNhap");
                int trangThai = rs.getInt("trangThai");
                CTSanPhamDTO ctsp = new CTSanPhamDTO(imei, idSanPham, idPBSanPham, idPhieuNhap, giaNhap, trangThai);
                result.add(ctsp);
            }
            DBConnector.closeConnection(conn);
        } catch (Exception e) {
            System.out.println(e);
        }
        return result;
    }
    
    public int insert(ArrayList<CTSanPhamDTO> ctspList) {
        int result = 0;
        for (int i = 0; i < ctspList.size(); i++) {
            try {
                Connection con = (Connection) DBConnector.getConnection();
                String sql = "INSERT INTO `ctsanpham`(`imei`, `sanPham_id`, `pbSanPham_id`, `phieuNhap_id`, `giaNhap`) VALUES (?,?,?,?,?)";
                PreparedStatement pst = (PreparedStatement) con.prepareStatement(sql);
                pst.setString(1, ctspList.get(i).getImei());
                pst.setInt(2, ctspList.get(i).getIdSanPham());
                pst.setInt(3, ctspList.get(i).getIdPBSanPham());
                pst.setInt(4, ctspList.get(i).getIdPhieuNhap());
                pst.setLong(5, ctspList.get(i).getGiaNhap());
                result = pst.executeUpdate();
//                PhienBanSanPhamDAO.getInstance().giamSoLuong(ctspList.get(i).getIdPBSanPham(), 1);
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
            String sql = "DELETE FROM `ctsanpham` WHERE phieuNhap_id = ?";
            PreparedStatement pst = (PreparedStatement) con.prepareStatement(sql);
            pst.setInt(1, hdId);
            result = pst.executeUpdate();
            DBConnector.closeConnection(con);
        } catch (SQLException ex) {
            System.out.println(ex);
        }
        return result;
    }
    
    public ArrayList<CTSanPhamDTO> selectAllByID(int hdId) {
        ArrayList<CTSanPhamDTO> result = new ArrayList<>();
        try {
            Connection conn = (Connection) DBConnector.getConnection();
            String query = "SELECT * FROM ctsanpham WHERE phieuNhap_id="+hdId;
            PreparedStatement pst = (PreparedStatement) conn.prepareStatement(query);
            ResultSet rs = (ResultSet) pst.executeQuery();
            while(rs.next()){
                String imei = rs.getString("imei");
                int idSanPham = rs.getInt("sanPham_id");
                int idPBSanPham = rs.getInt("pbSanPham_id");
                int idPhieuNhap = rs.getInt("phieuNhap_id");
                int giaNhap = rs.getInt("giaNhap");
                int trangThai = rs.getInt("trangThai");
                CTSanPhamDTO ctsp = new CTSanPhamDTO(imei, idSanPham, idPBSanPham, idPhieuNhap, giaNhap, trangThai);
                result.add(ctsp);
            }
        } catch (Exception e) {
            System.out.println(e);
        }
        return result;
    }
    
    public ArrayList<CTSanPhamDTO> selectAvailableByPBSPId(int pbspId) {
        ArrayList<CTSanPhamDTO> result = new ArrayList<>();
        try {
            Connection conn = (Connection) DBConnector.getConnection();
            String query = "SELECT * FROM ctsanpham WHERE pbSanPham_id=" + pbspId;
            PreparedStatement pst = (PreparedStatement) conn.prepareStatement(query);
            ResultSet rs = (ResultSet) pst.executeQuery();
            while(rs.next()){
                String imei = rs.getString("imei");
                int idSanPham = rs.getInt("sanPham_id");
                int idPBSanPham = rs.getInt("pbSanPham_id");
                int idPhieuNhap = rs.getInt("phieuNhap_id");
                int giaNhap = rs.getInt("giaNhap");
                int trangThai = rs.getInt("trangThai");
                CTSanPhamDTO ctsp = new CTSanPhamDTO(imei, idSanPham, idPBSanPham, idPhieuNhap, giaNhap, trangThai);
                result.add(ctsp);
            }
        } catch (Exception e) {
            System.out.println(e);
        }
        return result;
    }
    
    public int setTrangThaiTo0(ArrayList<CTSanPhamDTO> ctspList) {
        int result = 0;
        for (CTSanPhamDTO i : ctspList) {
            try {
                Connection conn = (Connection) DBConnector.getConnection();
                String query = "UPDATE `ctsanpham` SET `trangThai`=0 WHERE imei LIKE ?";
                PreparedStatement pst = (PreparedStatement) conn.prepareStatement(query);
                pst.setString(1, i.getImei());
                result = pst.executeUpdate();
                String query2 = "UPDATE `pbsanpham` SET `soLuong`=soLuong-1 WHERE id = " + i.getIdPBSanPham();
                PreparedStatement pst2 = (PreparedStatement) conn.prepareStatement(query2);
                pst.setString(1, i.getImei());
                result = pst2.executeUpdate();
                DBConnector.closeConnection(conn);
            } catch (SQLException ex) {
                Logger.getLogger(SanPhamDAO.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        return result;
    }
    
}
