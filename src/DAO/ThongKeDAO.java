/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package DAO;

import DTO.ThongKe.TKDoanhThuDTO;
import java.sql.Connection;
import config.DBConnector;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.sql.Timestamp;
import java.util.Date;

/**
 *
 * @author Admin
 */
public class ThongKeDAO {
    
    public ArrayList<TKDoanhThuDTO> thongKeDoanhThuNam(int yearStart, int yearEnd) {
        ArrayList<TKDoanhThuDTO> result = new ArrayList<>();
        for(int i=yearStart ; i<=yearEnd; i++) {
            try {
                Connection conn = (Connection) DBConnector.getConnection();
                String sql = """
                             WITH
                             bangdoanhthu as
                             (
                                    SELECT COALESCE(SUM(hd.tongTien), 0) as doanhthu
                                    from hoadon as hd
                                    WHERE YEAR(hd.ngayXuat)=?
                             ),
                             bangchiphi as (
                                    SELECT COALESCE(SUM(pn.tongTien), 0) as chiphi
                                    from phieunhap as pn
                                    WHERE YEAR(pn.ngayNhap)=?
                             )
                             SELECT doanhthu, chiphi from bangdoanhthu, bangchiphi
                             """;
                PreparedStatement pst = (PreparedStatement) conn.prepareStatement(sql);
                pst.setInt(1, i);
                pst.setInt(2, i);
                ResultSet rs = (ResultSet) pst.executeQuery();
                while(rs.next()) {
                    Timestamp ts = new Timestamp(i - 1900, 0, 1, 0, 0, 0, 0);
                    long doanhThu = rs.getLong("doanhthu");
                    long chiPhi = rs.getLong("chiphi");
                    long loiNhuan = doanhThu - chiPhi;
                    TKDoanhThuDTO tkdt = new TKDoanhThuDTO(ts, doanhThu, chiPhi, loiNhuan);
                    result.add(tkdt);
                }
            } catch (Exception e) {
//                System.out.println(e);
            }
        }
        return result;
    }
    
    public ArrayList<TKDoanhThuDTO> thongKeDoanhThuThang(int year) {
        ArrayList<TKDoanhThuDTO> result = new ArrayList<>();
        for(int i=1 ; i<=12; i++) {
            try {
                Connection conn = (Connection) DBConnector.getConnection();
                String sql = """
                             WITH
                             bangdoanhthu as
                             (
                                    SELECT COALESCE(SUM(hd.tongTien), 0) as doanhthu
                                    from hoadon as hd
                                    WHERE YEAR(hd.ngayXuat)=? AND MONTH(hd.ngayXuat)=?
                             ),
                             bangchiphi as (
                                    SELECT COALESCE(SUM(pn.tongTien), 0) as chiphi
                                    from phieunhap as pn
                                    WHERE YEAR(pn.ngayNhap)=? AND MONTH(pn.ngayNhap)=?
                             )
                             SELECT doanhthu, chiphi from bangdoanhthu, bangchiphi
                             """;
                PreparedStatement pst = (PreparedStatement) conn.prepareStatement(sql);
                pst.setInt(1, year);
                pst.setInt(2, i);
                pst.setInt(3, year);
                pst.setInt(4, i);
                ResultSet rs = (ResultSet) pst.executeQuery();
                while(rs.next()) {
                    Timestamp ts = new Timestamp(year - 1900, i-1, 1, 0, 0, 0, 0);
                    long doanhThu = rs.getLong("doanhthu");
                    long chiPhi = rs.getLong("chiphi");
                    long loiNhuan = doanhThu - chiPhi;
                    TKDoanhThuDTO tkdt = new TKDoanhThuDTO(ts, doanhThu, chiPhi, loiNhuan);
                    result.add(tkdt);
                }
            } catch (Exception e) {
//                System.out.println(e);
            }
        }
        return result;
    }
    
    public ArrayList<TKDoanhThuDTO> thongKeDoanhThuNgay(int year, int month) {
        ArrayList<TKDoanhThuDTO> result = new ArrayList<>();
        int dateStart = 0, dateEnd = 0;
        if(month == 1 || month == 3 || month == 5 || month == 7 || month == 8 || month == 10 || month == 12) {
            dateStart = 1;
            dateEnd = 31;
        }
        if(month == 2) {
            if(year%4 == 0 && year%100 != 0) {
                dateStart = 1;
                dateEnd = 29;
            } else {
                dateStart = 1;
                dateEnd = 28;
            }
        }
        if(month == 4 || month == 6 || month == 9 || month == 11) {
            dateStart = 1;
            dateEnd = 30;
        }
        for(int i=dateStart ; i<=dateEnd; i++) {
            try {
                Connection conn = (Connection) DBConnector.getConnection();
                String sql = """
                             WITH
                             bangdoanhthu as
                             (
                                    SELECT COALESCE(SUM(hd.tongTien), 0) as doanhthu
                                    from hoadon as hd
                                    WHERE YEAR(hd.ngayXuat)=? AND MONTH(hd.ngayXuat)=? AND DAY(hd.ngayXuat)=?
                             ),
                             bangchiphi as (
                                    SELECT COALESCE(SUM(pn.tongTien), 0) as chiphi
                                    from phieunhap as pn
                                    WHERE YEAR(pn.ngayNhap)=? AND MONTH(pn.ngayNhap)=? AND DAY(pn.ngayNhap)=?
                             )
                             SELECT doanhthu, chiphi from bangdoanhthu, bangchiphi
                             """;
                PreparedStatement pst = (PreparedStatement) conn.prepareStatement(sql);
                pst.setInt(1, year);
                pst.setInt(2, month);
                pst.setInt(3, i);
                pst.setInt(4, year);
                pst.setInt(5, month);
                pst.setInt(6, i);
                ResultSet rs = (ResultSet) pst.executeQuery();
                while(rs.next()) {
                    Timestamp ts = new Timestamp(year - 1900, month-1, i, 0, 0, 0, 0);
                    long doanhThu = rs.getLong("doanhthu");
                    long chiPhi = rs.getLong("chiphi");
                    long loiNhuan = doanhThu - chiPhi;
                    TKDoanhThuDTO tkdt = new TKDoanhThuDTO(ts, doanhThu, chiPhi, loiNhuan);
                    result.add(tkdt);
                }
            } catch (Exception e) {
//                System.out.println(e);
            }
        }
        return result;
    }
    
}
