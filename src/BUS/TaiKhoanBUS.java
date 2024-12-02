/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package BUS;

import DAO.TaiKhoanDAO;
import DTO.TaiKhoanDTO;
import java.util.ArrayList;

/**
 *
 * @author Admin
 */
public class TaiKhoanBUS {
    private NhanVienBUS nvBUS = new NhanVienBUS();
    private QuyenBUS qBUS = new QuyenBUS();
    public TaiKhoanDAO tkDAO = new TaiKhoanDAO();
    private ArrayList<TaiKhoanDTO> tkList = new ArrayList<>();
    
    public TaiKhoanBUS() {
        tkList = tkDAO.selectAll();
    }
    
    public ArrayList<TaiKhoanDTO> getAll() {
        return this.tkList;
    }
    
    public int getIndexByID(int id){
        for(int i = 0; i < tkList.size(); i++){
            if(tkList.get(i).getId() == id)
                return i;
        }
        return -1;
    }
    
    public boolean add(TaiKhoanDTO newTK){
        if(tkDAO.insert(newTK) != 0){
            tkList.add(newTK);
            return true;
        }
        return false;
    }
    
    public boolean update(TaiKhoanDTO tk) {
    // Validation checks
    if (tk.getTenTaiKhoan() == null || tk.getTenTaiKhoan().trim().isEmpty()) {
        System.out.println("Error: Username cannot be empty.");
        return false;
    }
    if (tk.getMatKhau() == null || tk.getMatKhau().trim().isEmpty()) {
        System.out.println("Error: Password cannot be empty.");
        return false;
    }
    if (tk.getTenTaiKhoan().length() > 25) {
        System.out.println("Error: Username cannot exceed 25 characters.");
        return false;
    }
    if (tk.getMatKhau().length() > 25) {
        System.out.println("Error: Password cannot exceed 25 characters.");
        return false;
    }
    
    for (TaiKhoanDTO existingTk : tkList) {
        if (existingTk.getTenTaiKhoan().equalsIgnoreCase(tk.getTenTaiKhoan()) && existingTk.getId() != tk.getId()) {
            System.out.println("Error: Username already exists in the system.");
            return false;
        }
    }
    
//    if (tk.getIdQuyen() == 1) {
//        System.out.println("Không thể thay đổi quyền của quản lí.");
//        return false;
//    }
    // Update logic
    if (tkDAO.update(tk) != 0) {
        tkList.set(getIndexByID(tk.getId()), tk);
        return true;
    }
    return false;
}

    
    public boolean ban(TaiKhoanDTO tk) {
        if(tkDAO.ban(tk.getId()) != 0) {
            tkList.get(getIndexByID(tk.getId())).setTrangThai(2);
            tkList.set(getIndexByID(tk.getId()), tk);
            return true;
        }
        return false;
    }
    
    public boolean unban(TaiKhoanDTO tk) {
        if(tkDAO.unban(tk.getId()) != 0) {
            tkList.get(getIndexByID(tk.getId())).setTrangThai(1);
            tkList.set(getIndexByID(tk.getId()), tk);
            return true;
        }
        return false;
    }
    
    public boolean delete(TaiKhoanDTO tk){
        if(tkDAO.delete(tk.getId()) != 0){
            tkList.remove(tk);
            return true;
        }
        return false;
    }
    
    public ArrayList<TaiKhoanDTO> search(String text){
        ArrayList<TaiKhoanDTO> result = new ArrayList<>();
        text = text.toLowerCase();
        for(TaiKhoanDTO i : tkList){
            if(Integer.toString(i.getId()).toLowerCase().contains(text) || i.getTenTaiKhoan().contains(text) || i.getMatKhau().contains(text) || nvBUS.getNameByID(i.getIdNhanVien()).contains(text) || qBUS.getNameById(i.getIdQuyen()).contains(text))
                result.add(i);
        }
        return result;
    }
    
    public ArrayList<TaiKhoanDTO> search(String text, String type){
        ArrayList<TaiKhoanDTO> result = new ArrayList<>();
        text = text.toLowerCase();
        switch (type) {
            case "Tất cả":
                for(TaiKhoanDTO i : tkList){
                    if(Integer.toString(i.getId()).toLowerCase().contains(text) || i.getTenTaiKhoan().contains(text) || nvBUS.getNameByID(i.getIdNhanVien()).toLowerCase().contains(text) || qBUS.getNameById(i.getIdQuyen()).toLowerCase().contains(text))
                        result.add(i);
                }
                break;
            case "Mã":
                for(TaiKhoanDTO i : tkList){
                    if(Integer.toString(i.getId()).toLowerCase().contains(text))
                        result.add(i);
                }
                break;
            case "Tên nhân viên":
                for(TaiKhoanDTO i : tkList){
//                    String test = nvBUS.getNameByID(i.getIdNhanVien());
//                    System.out.println(test);
//                    System.out.println(text);
                    if(nvBUS.getNameByID(i.getIdNhanVien()).toLowerCase().contains(text))
                        result.add(i);
                }
                break;
            case "Quyền":
                for(TaiKhoanDTO i : tkList){
                    if(qBUS.getNameById(i.getIdQuyen()).toLowerCase().contains(text))
                        result.add(i);
                }
                break;
            case "Tên tài khoản":
                for(TaiKhoanDTO i : tkList){
                    if(i.getTenTaiKhoan().contains(text))
                        result.add(i);
                }
                break;
            default:
                throw new AssertionError();
        }
        return result;
    }
}
