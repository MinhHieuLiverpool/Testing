/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package DTO;

/**
 *
 * @author Admin
 */
public class PhienBanSanPhamDTO {
    private int id;
    private int idSanPham;
    private int ram;
    private int rom;
    private String mau;
    private int soLuong;
    private int trangThai;

    public PhienBanSanPhamDTO(int id, int idSanPham, int ram, int rom, String mau, int soLuong, int trangThai) {
        this.id = id;
        this.idSanPham = idSanPham;
        this.ram = ram;
        this.rom = rom;
        this.mau = mau;
        this.soLuong = soLuong;
        this.trangThai = trangThai;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getIdSanPham() {
        return idSanPham;
    }

    public void setIdSanPham(int idSanPham) {
        this.idSanPham = idSanPham;
    }

    public int getRam() {
        return ram;
    }

    public void setRam(int ram) {
        this.ram = ram;
    }

    public int getRom() {
        return rom;
    }

    public void setRom(int rom) {
        this.rom = rom;
    }

    public String getMau() {
        return mau;
    }

    public void setMau(String mau) {
        this.mau = mau;
    }

    public int getSoLuong() {
        return soLuong;
    }

    public void setSoLuong(int soLuong) {
        this.soLuong = soLuong;
    }

    public int getTrangThai() {
        return trangThai;
    }

    public void setTrangThai(int trangThai) {
        this.trangThai = trangThai;
    }

    @Override
    public String toString() {
        return "PhienBanSanPhamDTO{" + "id=" + id + ", idSanPham=" + idSanPham + ", ram=" + ram + ", rom=" + rom + ", mau=" + mau + ", soLuong=" + soLuong + ", trangThai=" + trangThai + '}';
    }
    
}
