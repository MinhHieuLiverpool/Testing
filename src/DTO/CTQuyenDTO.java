/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package DTO;

/**
 *
 * @author DVN
 */
public class CTQuyenDTO {
    private int idQuyen;
    private int idChucNang;
    private String hanhDong;

    public CTQuyenDTO(int idQuyen, int idChucNang, String hanhDong) {
        this.idQuyen = idQuyen;
        this.idChucNang = idChucNang;
        this.hanhDong = hanhDong;
    }

    public int getIdQuyen() {
        return idQuyen;
    }

    public void setIdQuyen(int idQuyen) {
        this.idQuyen = idQuyen;
    }

    public int getIdChucNang() {
        return idChucNang;
    }

    public void setIdChucNang(int idChucNang) {
        this.idChucNang = idChucNang;
    }

    public String getHanhDong() {
        return hanhDong;
    }

    public void setHanhDong(String hanhDong) {
        this.hanhDong = hanhDong;
    }

    @Override
    public String toString() {
        return "CTQuyenDTO{" + "idQuyen=" + idQuyen + ", idChucNang=" + idChucNang + ", hanhDong=" + hanhDong + '}';
    }
    
}
