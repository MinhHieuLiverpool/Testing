/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package DTO;

/**
 *
 * @author Admin
 */
public class CTSanPhamDTO {
    private String imei;
    private int idSanPham;
    private int idPBSanPham;
    private int idPhieuNhap;
    private long giaNhap;
    private int trangThai;

    public CTSanPhamDTO(String imei, int idSanPham, int idPBSanPham, int idPhieuNhap, long giaNhap, int trangThai) {
        this.imei = imei;
        this.idSanPham = idSanPham;
        this.idPBSanPham = idPBSanPham;
        this.idPhieuNhap = idPhieuNhap;
        this.giaNhap = giaNhap;
        this.trangThai = trangThai;
    }

    public String getImei() {
        return imei;
    }

    public void setImei(String imei) {
        this.imei = imei;
    }

    public int getIdSanPham() {
        return idSanPham;
    }

    public void setIdSanPham(int idSanPham) {
        this.idSanPham = idSanPham;
    }

    public int getIdPBSanPham() {
        return idPBSanPham;
    }

    public void setIdPBSanPham(int idPBSanPham) {
        this.idPBSanPham = idPBSanPham;
    }

    public int getIdPhieuNhap() {
        return idPhieuNhap;
    }

    public void setIdPhieuNhap(int idPhieuNhap) {
        this.idPhieuNhap = idPhieuNhap;
    }

    public long getGiaNhap() {
        return giaNhap;
    }

    public void setGiaNhap(int giaNhap) {
        this.giaNhap = giaNhap;
    }
    
    public int getTrangThai() {
        return trangThai;
    }

    public void setTrangThai(int trangThai) {
        this.trangThai = trangThai;
    }

    @Override
    public String toString() {
        return "CTSanPhamDTO{" + "imei=" + imei + ", idSanPham=" + idSanPham + ", idPBSanPham=" + idPBSanPham + ", idPhieuNhap=" + idPhieuNhap + ", trangThai=" + trangThai + '}';
    }
    
    
}
