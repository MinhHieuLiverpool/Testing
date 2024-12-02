/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package DAO;

import DTO.KhuyenMaiDTO;
import config.DBConnector;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

/**
 *
 * @author Admin
 */
public class KhuyenMaiDAO {
    
    public static KhuyenMaiDAO getInstance() {
        return new KhuyenMaiDAO();
    }
    
    public ArrayList<KhuyenMaiDTO> selectAll() {
        ArrayList<KhuyenMaiDTO> result = new ArrayList<KhuyenMaiDTO>();
        try {
            Connection conn = (Connection) DBConnector.getConnection();
            String query = "SELECT * FROM khuyenmai WHERE `trangThai`=1";
            PreparedStatement pst = (PreparedStatement) conn.prepareStatement(query);
            ResultSet rs = (ResultSet) pst.executeQuery();
            while(rs.next()){
                int id = rs.getInt("id");
                String ten = rs.getString("ten");
                float tiLe = rs.getFloat("tiLe");
                int idSanPham = rs.getInt("sanPham_id");
                int dieuKien = rs.getInt("dieuKien");
                int trangThai = rs.getInt("trangThai");
                KhuyenMaiDTO th = new KhuyenMaiDTO(id, ten, tiLe, idSanPham, dieuKien, trangThai);
                result.add(th);
            }
            DBConnector.closeConnection(conn);
        } catch (Exception e) {
            System.out.println(e);
        }
        return result;
    }
    
}
