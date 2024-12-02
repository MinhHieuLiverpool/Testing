/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package BUS;

import DAO.CTSanPhamDAO;
import DTO.CTSanPhamDTO;
import java.util.ArrayList;

/**
 *
 * @author Admin
 */
public class CTSanPhamBUS {
    
    private final CTSanPhamDAO ctspDAO = new CTSanPhamDAO();
    private ArrayList<CTSanPhamDTO> chiTietSPList = new ArrayList<CTSanPhamDTO>();
    
    public CTSanPhamBUS() {
        chiTietSPList = ctspDAO.getAll();
    }
    
    public ArrayList<CTSanPhamDTO> getAll() {
        chiTietSPList = ctspDAO.getAll();
        return this.chiTietSPList;
    }
    
    public ArrayList<CTSanPhamDTO> getAllByHDId(int pnId) {
        chiTietSPList = ctspDAO.getAll();
        ArrayList<CTSanPhamDTO> result = new ArrayList<>();
        for(CTSanPhamDTO i : chiTietSPList){
            if(i.getIdPhieuNhap()== pnId)
                result.add(i);
        }
        return result;
    }
    
    public boolean checkExisted(String imei){
        for(CTSanPhamDTO i : chiTietSPList){
            if(i.getImei().equals(imei))
                return true;
        }
        
        return false;
    }
    
    public CTSanPhamDTO getSanPhamByImei(String imei){
        for(CTSanPhamDTO i : chiTietSPList){
            if(i.getImei().equals(imei))
                return i;
        }
        return null;
    }
}
