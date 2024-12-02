/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package DTO;

/**
 *
 * @author DVN
 */
public class CTPhieuNhapDTO {
    private int idPhieuNhap;
    private int idPBSanPham;
    private int soLuong;
    private long donGia;
    private long tongTien;

    public CTPhieuNhapDTO(int idPhieuNhap, int idPBSanPham, int soLuong, long donGia, long tongTien) {
        this.idPhieuNhap = idPhieuNhap;
        this.idPBSanPham = idPBSanPham;
        this.soLuong = soLuong;
        this.donGia = donGia;
        this.tongTien = tongTien;
    }

    public int getIdPhieuNhap() {
        return idPhieuNhap;
    }

    public void setIdPhieuNhap(int idPhieuNhap) {
        this.idPhieuNhap = idPhieuNhap;
    }

    public int getIdPBSanPham() {
        return idPBSanPham;
    }

    public void setIdPBSanPham(int idPBSanPham) {
        this.idPBSanPham = idPBSanPham;
    }

    public int getSoLuong() {
        return soLuong;
    }

    public void setSoLuong(int soLuong) {
        this.soLuong = soLuong;
    }

    public long getDonGia() {
        return donGia;
    }

    public void setDonGia(int donGia) {
        this.donGia = donGia;
    }

    public long getTongTien() {
        return tongTien;
    }

    public void setTongTien(long tongTien) {
        this.tongTien = tongTien;
    }

    @Override
    public String toString() {
        return "CTPhieuNhapDTO{" + "idPhieuNhap=" + idPhieuNhap + ", idPBSanPham=" + idPBSanPham + ", soLuong=" + soLuong + ", donGia=" + donGia + ", tongTien=" + tongTien + '}';
    }

    
}
