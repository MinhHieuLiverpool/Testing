/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package BUS;

import DAO.PhienBanSanPhamDAO;
import DTO.PhienBanSanPhamDTO;
import java.util.ArrayList;

/**
 *
 * @author Admin
 */
public class PhienBanSanPhamBUS {
    public PhienBanSanPhamDAO pbspDAO = new PhienBanSanPhamDAO();
    private ArrayList<PhienBanSanPhamDTO> pbSPList = new ArrayList<PhienBanSanPhamDTO>();
    
    public PhienBanSanPhamBUS() {
        pbSPList = pbspDAO.selectAll();
    }
    
    public int getLength() {
        return this.pbSPList.size();
    }
    
    public boolean addNewPBSP(PhienBanSanPhamDTO pbsp) {
        if(pbspDAO.insert(pbsp) != 0) {
            pbSPList.add(pbsp);
            return true;
        }
        return false;
    }
    
    public boolean addNewPBSPList(ArrayList<PhienBanSanPhamDTO> pbspList) {
        if(pbspDAO.insert(pbspList) != 0) {
            for(PhienBanSanPhamDTO i : pbspList)
                pbSPList.add(i);
            return true;
        }
        return false;
    }
    
    public int getIndexByID(int id) {
        for(int i=0; i<pbSPList.size(); i++) {
            if(pbSPList.get(i).getId() == id){
                return i;
            }
                
        }
        return -1;
    }
    
    public PhienBanSanPhamDTO getObjectById(int id) {
        return pbSPList.get(getIndexByID(id));
    }
    
    public boolean delete(PhienBanSanPhamDTO pbsp) {
        if(pbspDAO.delete(pbsp.getId()) != 0) {
            pbSPList.remove(pbsp);
            return true;
        }
        return false;
    }
    
    public ArrayList<PhienBanSanPhamDTO> getAll() {
        return this.pbSPList;
    }
    
    public ArrayList<PhienBanSanPhamDTO> getAllPBSPBySPId(int spId) {
        return pbspDAO.selectAllByID(spId);
    }
}
