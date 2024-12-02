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
import java.util.ArrayList;
import DTO.CTHoaDonDTO;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Windows
 */
public class CTHoaDonDAO {

    public static CTHoaDonDAO getInstance() {
        return new CTHoaDonDAO();
    }

    public ArrayList<CTHoaDonDTO> selectAll() {
        ArrayList<CTHoaDonDTO> result = new ArrayList<>();
        try {
            Connection conn = (Connection) DBConnector.getConnection();
            String query = "SELECT * FROM cthoadon";
            PreparedStatement pst = (PreparedStatement) conn.prepareStatement(query);
            ResultSet rs = (ResultSet) pst.executeQuery();
            while (rs.next()) {
                int idHoaDon = rs.getInt("hoaDon_id");
                String imei = rs.getString("imei");
                int soLuong = rs.getInt("soLuong");
                long giaBanRa = rs.getLong("giaBanRa");
                long tongTien = rs.getLong("tongTien");
                CTHoaDonDTO ctpn = new CTHoaDonDTO(idHoaDon, imei, soLuong, giaBanRa, tongTien);
                result.add(ctpn);
            }
        } catch (Exception e) {
            System.out.println(e);
        }
        return result;
    }

    public ArrayList<CTHoaDonDTO> selectAllByID(int hdId) {
        ArrayList<CTHoaDonDTO> result = new ArrayList<>();
        try {
            Connection conn = (Connection) DBConnector.getConnection();
            String query = "SELECT * FROM cthoadon WHERE hoaDon_id=" + hdId;
            PreparedStatement pst = (PreparedStatement) conn.prepareStatement(query);
            ResultSet rs = (ResultSet) pst.executeQuery();
            while (rs.next()) {
                int idHoaDon = rs.getInt("hoaDon_id");
                String imei = rs.getString("imei");
                int soLuong = rs.getInt("soLuong");
                long giaBanRa = rs.getLong("giaBanRa");
                long tongTien = rs.getLong("tongTien");
                CTHoaDonDTO ctpn = new CTHoaDonDTO(idHoaDon, imei, soLuong, giaBanRa, tongTien);
                result.add(ctpn);
            }
        } catch (Exception e) {
            System.out.println(e);
        }
        return result;
    }

    public int insert(ArrayList<CTHoaDonDTO> cthdList) {
        int result = 0;
        for (int i = 0; i < cthdList.size(); i++) {
            try {
                Connection con = (Connection) DBConnector.getConnection();
                String sql = "INSERT INTO `cthoadon`(`hoaDon_id`, `imei`, `soLuong`, `giaBanRa`, `tongTien`) VALUES (?,?,?,?,?)";
                PreparedStatement pst = (PreparedStatement) con.prepareStatement(sql);
                pst.setInt(1, cthdList.get(i).getIdHoaDon());
                pst.setString(2, cthdList.get(i).getImeiSanPham());
                pst.setInt(3, cthdList.get(i).getSoLuong());
                pst.setLong(4, cthdList.get(i).getGiaBanRa());
                pst.setLong(5, cthdList.get(i).getTongTien());
                result = pst.executeUpdate();
                DBConnector.closeConnection(con);
            } catch (SQLException ex) {
                Logger.getLogger(CTPhieuNhapDAO.class.getName()).log(Level.SEVERE, null, ex);
            }
//            PhienBanSanPhamDAO.getInstance().tangSoLuong(ctpnList.get(i).getIdPBSanPham(), ctpnList.get(i).getSoLuong());
        }
        return result;
    }
    
    public int deleteByHDId(int hdId) {
        int result = 0;
        try {
            Connection con = (Connection) DBConnector.getConnection();
            String sql = "DELETE FROM `cthoadon` WHERE hoaDon_id = ?";
            PreparedStatement pst = (PreparedStatement) con.prepareStatement(sql);
            pst.setInt(1, hdId);
            result = pst.executeUpdate();
            DBConnector.closeConnection(con);
        } catch (SQLException ex) {
            System.out.println(ex);
        }
        return result;
    }
}
