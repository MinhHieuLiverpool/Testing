/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package BUS;

import DAO.CTHoaDonDAO;
import DTO.CTHoaDonDTO;
import java.util.ArrayList;
/**
 *
 * @author Windows
 */
public class CTHoaDonBUS {
    private CTHoaDonDAO cthdDAO = new CTHoaDonDAO();
    private ArrayList<CTHoaDonDTO> cthdList = new ArrayList<>();
    
    public CTHoaDonBUS() {
        this.cthdList = cthdDAO.selectAll();
    }
    
    public ArrayList<CTHoaDonDTO> getAll() {
        return this.cthdList;
    }
    
    public ArrayList<CTHoaDonDTO> getAllByID(int hdId) {
        return cthdDAO.selectAllByID(hdId);
    }
    
}
