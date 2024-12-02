/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package DAO;

import DTO.CTPhieuNhapDTO;
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
 * @author Admin
 */
public class CTPhieuNhapDAO {
    
    public static CTPhieuNhapDAO getInstance() {
        return new CTPhieuNhapDAO();
    }
    
    public ArrayList<CTPhieuNhapDTO> selectAll() {
        ArrayList<CTPhieuNhapDTO> result = new ArrayList<CTPhieuNhapDTO>();
        try {
            Connection conn = (Connection) DBConnector.getConnection();
            String query = "SELECT * FROM ctphieunhap";
            PreparedStatement pst = (PreparedStatement) conn.prepareStatement(query);
            ResultSet rs = (ResultSet) pst.executeQuery();
            while(rs.next()){
                int idPhieuNhap = rs.getInt("phieuNhap_id");
                int idPBSP = rs.getInt("pbsanPham_id");
                int soLuong = rs.getInt("soLuong");
                int donGia = rs.getInt("donGia");
                long tongTien = rs.getLong("tongTien");
                CTPhieuNhapDTO ctpn = new CTPhieuNhapDTO(idPhieuNhap, idPBSP, soLuong, donGia, tongTien);
                result.add(ctpn);
            }
        } catch (Exception e) {
            System.out.println(e);
        }
        return result;
    }
    
    public ArrayList<CTPhieuNhapDTO> selectAllByID(int pnId) {
        ArrayList<CTPhieuNhapDTO> result = new ArrayList<CTPhieuNhapDTO>();
        try {
            Connection conn = (Connection) DBConnector.getConnection();
            String query = "SELECT * FROM ctphieunhap WHERE phieuNhap_id="+pnId;
            PreparedStatement pst = (PreparedStatement) conn.prepareStatement(query);
            ResultSet rs = (ResultSet) pst.executeQuery();
            while(rs.next()){
                int idPhieuNhap = rs.getInt("phieuNhap_id");
                int idPBSP = rs.getInt("pbsanPham_id");
                int soLuong = rs.getInt("soLuong");
                int donGia = rs.getInt("donGia");
                long tongTien = rs.getLong("tongTien");
                CTPhieuNhapDTO ctpn = new CTPhieuNhapDTO(idPhieuNhap, idPBSP, soLuong, donGia, tongTien);
                result.add(ctpn);
            }
        } catch (Exception e) {
            System.out.println(e);
        }
        return result;
    }
    
    public int insert(ArrayList<CTPhieuNhapDTO> ctpnList) {
        int result = 0;
        for (int i = 0; i < ctpnList.size(); i++) {
            try {
                Connection con = (Connection) DBConnector.getConnection();
                String sql = "INSERT INTO `ctphieunhap`(`phieuNhap_id`, `pbsanPham_id`, `soLuong`, `donGia`, `tongTien`) VALUES (?,?,?,?,?)";
                PreparedStatement pst = (PreparedStatement) con.prepareStatement(sql);
                pst.setInt(1, ctpnList.get(i).getIdPhieuNhap());
                pst.setInt(2, ctpnList.get(i).getIdPBSanPham());
                pst.setInt(3, ctpnList.get(i).getSoLuong());
                pst.setLong(4, ctpnList.get(i).getDonGia());
                pst.setLong(5, ctpnList.get(i).getTongTien());
                result = pst.executeUpdate();
                DBConnector.closeConnection(con);
            } catch (SQLException ex) {
                Logger.getLogger(CTPhieuNhapDAO.class.getName()).log(Level.SEVERE, null, ex);
            }
            PhienBanSanPhamDAO.getInstance().tangSoLuong(ctpnList.get(i).getIdPBSanPham(), ctpnList.get(i).getSoLuong());
        }
        return result;
    }

    
}
