/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package DTO;

/**
 *
 * @author Admin
 */
public class SanPhamDTO {
    private int id;
    private String ten;
    private String hinhAnh;
    private float kichThuocMan;
    private String cameraSau;
    private String cameraTruoc;
    private String chipXuLy;
    private String heDieuHanh;
    private int dungLuongPin;
    private int idThuongHieu;
    private int trangThai;

    public SanPhamDTO(int id, String ten, String hinhAnh, float kichThuocMan, String cameraSau, String cameraTruoc, String chipXuLy, String heDieuHanh, int dungLuongPin, int idThuongHieu, int trangThai) {
        this.id = id;
        this.ten = ten;
        this.hinhAnh = hinhAnh;
        this.kichThuocMan = kichThuocMan;
        this.cameraSau = cameraSau;
        this.cameraTruoc = cameraTruoc;
        this.chipXuLy = chipXuLy;
        this.heDieuHanh = heDieuHanh;
        this.dungLuongPin = dungLuongPin;
        this.idThuongHieu = idThuongHieu;
        this.trangThai = trangThai;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTen() {
        return ten;
    }

    public void setTen(String ten) {
        this.ten = ten;
    }

    public String getHinhAnh() {
        return hinhAnh;
    }

    public void setHinhAnh(String hinhAnh) {
        this.hinhAnh = hinhAnh;
    }

    public float getKichThuocMan() {
        return kichThuocMan;
    }

    public void setKichThuocMan(float kichThuocMan) {
        this.kichThuocMan = kichThuocMan;
    }

    public String getCameraSau() {
        return cameraSau;
    }

    public void setCameraSau(String cameraSau) {
        this.cameraSau = cameraSau;
    }

    public String getCameraTruoc() {
        return cameraTruoc;
    }

    public void setCameraTruoc(String cameraTruoc) {
        this.cameraTruoc = cameraTruoc;
    }

    public String getChipXuLy() {
        return chipXuLy;
    }

    public void setChipXuLy(String chipXuLy) {
        this.chipXuLy = chipXuLy;
    }

    public String getHeDieuHanh() {
        return heDieuHanh;
    }

    public void setHeDieuHanh(String heDieuHanh) {
        this.heDieuHanh = heDieuHanh;
    }

    public int getDungLuongPin() {
        return dungLuongPin;
    }

    public void setDungLuongPin(int dungLuongPin) {
        this.dungLuongPin = dungLuongPin;
    }

    public int getIdThuongHieu() {
        return idThuongHieu;
    }

    public void setIdThuongHieu(int idThuongHieu) {
        this.idThuongHieu = idThuongHieu;
    }

    public int getTrangThai() {
        return trangThai;
    }

    public void setTrangThai(int trangThai) {
        this.trangThai = trangThai;
    }

        
}
