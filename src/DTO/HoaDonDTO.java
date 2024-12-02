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
public class HoaDonDTO {
    private int id;
    private int idKhachHang;
    private int idNhanVien;
    private Timestamp ngayXuat;
    private long tongTien;
    private int idKhuyenMai;

    public HoaDonDTO(int id, int idKhachHang, int idNhanVien, Timestamp ngayXuat, long tongTien, int idKhuyenMai) {
        this.id = id;
        this.idKhachHang = idKhachHang;
        this.idNhanVien = idNhanVien;
        this.ngayXuat = ngayXuat;
        this.tongTien = tongTien;
        this.idKhuyenMai = idKhuyenMai;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getIdKhachHang() {
        return idKhachHang;
    }

    public void setIdKhachHang(int idKhachHang) {
        this.idKhachHang = idKhachHang;
    }

    public int getIdNhanVien() {
        return idNhanVien;
    }

    public void setIdNhanVien(int idNhanVien) {
        this.idNhanVien = idNhanVien;
    }

    public Timestamp getNgayXuat() {
        return ngayXuat;
    }

    public void setNgayXuat(Timestamp ngayXuat) {
        this.ngayXuat = ngayXuat;
    }

    public long getTongTien() {
        return tongTien;
    }

    public void setTongTien(long tongTien) {
        this.tongTien = tongTien;
    }

    public int getIdKhuyenMai() {
        return idKhuyenMai;
    }

    public void setIdKhuyenMai(int idKhuyenMai) {
        this.idKhuyenMai = idKhuyenMai;
    }

    @Override
    public String toString() {
        return "HoaDonDTO{" + "id=" + id + ", idKhachHang=" + idKhachHang + ", idNhanVien=" + idNhanVien + ", ngayXuat=" + ngayXuat + ", tongTien=" + tongTien + ", idKhuyenMai=" + idKhuyenMai + '}';
    }
    
    
}
