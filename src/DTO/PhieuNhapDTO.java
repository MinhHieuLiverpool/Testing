/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package DTO;

import java.sql.Timestamp;

/**
 *
 * @author DVN
 */
public class PhieuNhapDTO {
    private int id;
    private int idNhaCungCap;
    private int idNhanVien;
    private Timestamp ngayNhap;
    private long tongTien;
    
    public PhieuNhapDTO(int id, int idNhaCungCap, int idNhanVien, Timestamp ngayNhap, long tongTien) {
        this.id = id;
        this.idNhaCungCap = idNhaCungCap;
        this.idNhanVien = idNhanVien;
        this.ngayNhap = ngayNhap;
        this.tongTien = tongTien;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getIdNhaCungCap() {
        return idNhaCungCap;
    }

    public void setIdNhaCungCap(int idNhaCungCap) {
        this.idNhaCungCap = idNhaCungCap;
    }

    public int getIdNhanVien() {
        return idNhanVien;
    }

    public void setIdNhanVien(int idNhanVien) {
        this.idNhanVien = idNhanVien;
    }

    public Timestamp getNgayNhap() {
        return ngayNhap;
    }

    public void setNgayNhap(Timestamp ngayNhap) {
        this.ngayNhap = ngayNhap;
    }

    public long getTongTien() {
        return tongTien;
    }

    public void setTongTien(long tongTien) {
        this.tongTien = tongTien;
    } 

    @Override
    public String toString() {
        return "PhieuNhapDTO{" + "id=" + id + ", idNhaCungCap=" + idNhaCungCap + ", idNhanVien=" + idNhanVien + ", ngayNhap=" + ngayNhap + ", tongTien=" + tongTien + '}';
    }
    
    
}
