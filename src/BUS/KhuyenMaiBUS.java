/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package BUS;

import DAO.KhuyenMaiDAO;
import DTO.KhuyenMaiDTO;
import java.util.ArrayList;

/**
 *
 * @author Admin
 */
public class KhuyenMaiBUS {
    
    private KhuyenMaiDAO kmDAO = new KhuyenMaiDAO();
    private ArrayList<KhuyenMaiDTO> kmList;
    
    public KhuyenMaiBUS() {
        kmList = kmDAO.selectAll();
    }
    
    public ArrayList<KhuyenMaiDTO> getAll() {
        return this.kmList;
    }
    
    public KhuyenMaiDTO getObjectById(int id) {
        for(KhuyenMaiDTO i : kmList)
            if(i.getId() == id)
                return i;
        return null;
    }
    
    public String[] getStringList() {
        String[] result = new String[kmList.size()];
        for(int i=0; i<kmList.size(); i++) {
            result[i] = kmList.get(i).getTen();
        }
        return result;
    }
    
}
