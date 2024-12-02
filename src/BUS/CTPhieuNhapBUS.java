/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package BUS;

import DAO.CTPhieuNhapDAO;
import DTO.CTPhieuNhapDTO;
import java.util.ArrayList;

/**
 *
 * @author Admin
 */
public class CTPhieuNhapBUS {
    private CTPhieuNhapDAO ctpnDAO = new CTPhieuNhapDAO();
    private ArrayList<CTPhieuNhapDTO> ctpnList = new ArrayList<>();
    
    public CTPhieuNhapBUS() {
        this.ctpnList = ctpnDAO.selectAll();
    }
    
    public ArrayList<CTPhieuNhapDTO> getAll() {
        return this.ctpnList;
    }
    
    public ArrayList<CTPhieuNhapDTO> getAllByID(int pnId) {
        return ctpnDAO.selectAllByID(pnId);
    }
    
    public Object[] getStringListByPbsp(int pbsp){
        ArrayList<String> resultAl = new ArrayList<>();
        Object[] result;
        for(int i=0; i<ctpnList.size(); i++){
            if(ctpnList.get(i).getIdPBSanPham() == pbsp)
                resultAl.add(ctpnList.get(i).getIdPhieuNhap()+"");
        }
        result = resultAl.toArray();
        return result;
    }
}
