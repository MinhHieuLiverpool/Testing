/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package DTO.ThongKe;
import helper.Formatter;
import java.sql.Timestamp;

import java.util.Date;

/**
 *
 * @author Admin
 */
public class TKDoanhThuDTO {

    private Timestamp date;
    private long doanhThu;
    private long chiPhi;
    private long loiNhuan;

    public TKDoanhThuDTO(Timestamp date, long doanhThu, long chiPhi, long loiNhuan) {
        this.date = date;
        this.doanhThu = doanhThu;
        this.chiPhi = chiPhi;
        this.loiNhuan = loiNhuan;
    }

    public Timestamp getDate() {
        return date;
    }

    public void setDate(Timestamp date) {
        this.date = date;
    }

    public long getDoanhThu() {
        return doanhThu;
    }

    public void setDoanhThu(long doanhThu) {
        this.doanhThu = doanhThu;
    }

    public long getChiPhi() {
        return chiPhi;
    }

    public void setChiPhi(long chiPhi) {
        this.chiPhi = chiPhi;
    }

    public long getLoiNhuan() {
        return loiNhuan;
    }

    public void setLoiNhuan(long loiNhuan) {
        this.loiNhuan = loiNhuan;
    }

    @Override
    public String toString() {
        return "TKDoanhThuDTO{" + "date=" + Formatter.FormatDateTime(date) + ", doanhThu=" + doanhThu + ", chiPhi=" + chiPhi + ", loiNhuan=" + loiNhuan + '}';
    }
    
    
}
