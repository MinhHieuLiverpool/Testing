/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package DTO;

import java.util.Date;

/**
 *
 * @author Admin
 */
public class CTBaoHanhDTO {
    private int id;
    private int idBaoHanh;
    private int idHoaDon;
    private String imeiSanPham;
    private java.sql.Timestamp ngayKetThuc;

    public CTBaoHanhDTO(int id, int idBaoHanh, int idHoaDon, String imeiSanPham, java.sql.Timestamp ngayKetThuc) {
        this.id = id;
        this.idBaoHanh = idBaoHanh;
        this.idHoaDon = idHoaDon;
        this.imeiSanPham = imeiSanPham;
        this.ngayKetThuc = ngayKetThuc;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getIdBaoHanh() {
        return idBaoHanh;
    }

    public void setIdBaoHanh(int idBaoHanh) {
        this.idBaoHanh = idBaoHanh;
    }

    public int getIdHoaDon() {
        return idHoaDon;
    }

    public void setIdHoaDon(int idHoaDon) {
        this.idHoaDon = idHoaDon;
    }

    public String getImeiSanPham() {
        return imeiSanPham;
    }

    public void setImeiSanPham(String imeiSanPham) {
        this.imeiSanPham = imeiSanPham;
    }

    public java.sql.Timestamp getNgayKetThuc() {
        return ngayKetThuc;
    }

    public void setNgayKetThuc(java.sql.Timestamp ngayKetThuc) {
        this.ngayKetThuc = ngayKetThuc;
    }
    
    
}
