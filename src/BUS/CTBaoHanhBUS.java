/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package BUS;

import DAO.CTBaoHanhDAO;
import DTO.CTBaoHanhDTO;
import java.util.ArrayList;

/**
 *
 * @author Windows
 */
public class CTBaoHanhBUS {
    private final CTBaoHanhDAO ctbhDAO = new CTBaoHanhDAO();
    private ArrayList<CTBaoHanhDTO> ctbhList = new ArrayList<>();
    
    public CTBaoHanhBUS(){
        this.ctbhList = ctbhDAO.selectAll();
    }
    
    public ArrayList<CTBaoHanhDTO> getAll() {
        return this.ctbhList;
    }
    
    public ArrayList<CTBaoHanhDTO> getAllByHoaDonId(int id){
        return ctbhDAO.selectAllByHoaDonID(id);
    }

    
    public int getAutoIncrement(){
        return ctbhDAO.getAutoIncrement();
    }
}
