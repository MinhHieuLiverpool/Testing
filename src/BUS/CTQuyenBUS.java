/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package BUS;

import DAO.CTQuyenDAO;
import DTO.CTQuyenDTO;
import java.util.ArrayList;

/**
 *
 * @author Admin
 */
public class CTQuyenBUS {
    
    private final CTQuyenDAO ctqDAO = new CTQuyenDAO();
    private ArrayList<CTQuyenDTO> ctqList;
    
    public CTQuyenBUS() {
        this.ctqList = ctqDAO.selectAll();
    }
    
    public ArrayList<CTQuyenDTO> getAll() {
        return this.ctqList;
    }
    
    public ArrayList<CTQuyenDTO> getAllByQuyenId(int idQuyen) {
        ArrayList<CTQuyenDTO> result = new ArrayList<>();
        for(CTQuyenDTO i : this.ctqList) {
            if(i.getIdQuyen() == idQuyen)
                result.add(i);
        }
        return result;
    }
     
}
