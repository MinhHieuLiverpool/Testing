/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package BUS;

import DAO.ChucNangDAO;
import DTO.ChucNangDTO;
import java.util.ArrayList;

/**
 *
 * @author Admin
 */
public class ChucNangBUS {
    
    private final ChucNangDAO cnDAO = new ChucNangDAO();
    private ArrayList<ChucNangDTO> cnList = new ArrayList<>();
    
    public ChucNangBUS() {
        this.cnList = cnDAO.selectAll();
    }
    
    public ArrayList<ChucNangDTO> getAll() {
        return this.cnList;
    }
    
    public int getIndexByID(int id) {
        for(int i=0; i<cnList.size(); i++) {
            if(cnList.get(i).getId() == id)
                return i;
        }
        return -1;
    }
    
    public String getNameByID(int id) {
        return cnList.get(getIndexByID(id)).getTen();
    }
    
}
