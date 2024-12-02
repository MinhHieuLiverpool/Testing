/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package BUS;

import DAO.BaoHanhDAO;
import DTO.BaoHanhDTO;
import java.util.ArrayList;

/**
 *
 * @author Windows
 */
public class BaoHanhBUS {
    private BaoHanhDAO bhDAO = new BaoHanhDAO();
    private ArrayList<BaoHanhDTO> baoHanhList = new ArrayList<>();  
    
    public BaoHanhBUS(){
        baoHanhList = bhDAO.selectAll();
    }
    
    public boolean addNewBh(BaoHanhDTO hd) {
        if(bhDAO.insert(hd) != 0) {
            baoHanhList.add(hd);
            return true;
        }
        return false;
    }
    
    public int getAutoIncrement(){
        return bhDAO.getAutoIncrement();
    }
    
    public String[] getStringList(){
        String[] result = new String[baoHanhList.size()];
        for(int i=0; i<baoHanhList.size(); i++) {
            result[i] = baoHanhList.get(i).getSoThang()+"";
        }
        return result;
    }
    
    public int getIdBySoThang(int soThang){
        int result =-1;
        for(BaoHanhDTO i : baoHanhList){
            if(i.getSoThang() == soThang)
                return i.getId();
        }
        return result;
    }
    
}
