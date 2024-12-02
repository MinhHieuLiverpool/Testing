/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package BUS;

import DAO.CTPhieuNhapDAO;
import DAO.CTSanPhamDAO;
import DAO.PhienBanSanPhamDAO;
import DAO.PhieuNhapDAO;
import DTO.CTPhieuNhapDTO;
import DTO.CTSanPhamDTO;
import DTO.NhaCungCapDTO;
import DTO.NhanVienDTO;
import DTO.PhieuNhapDTO;
import helper.Formatter;
import java.util.ArrayList;
import java.util.Date;

/**
 *
 * @author Admin
 */
public class PhieuNhapBUS {
    private PhieuNhapDAO pnDAO = new PhieuNhapDAO();
    private CTPhieuNhapDAO ctpnDAO = new CTPhieuNhapDAO();
    private CTSanPhamDAO ctspDAO = new CTSanPhamDAO();
    private PhienBanSanPhamDAO pbspDAO = new PhienBanSanPhamDAO();
    private NhanVienBUS nvBUS = new NhanVienBUS();
    private NhaCungCapBUS nccBUS = new NhaCungCapBUS();
    public ArrayList<PhieuNhapDTO> phieuNhapList = new ArrayList<>();
    
    public PhieuNhapBUS() {
        this.phieuNhapList = pnDAO.selectAll();
    }
    
    public ArrayList<PhieuNhapDTO> getAll(){ 
        return this.phieuNhapList;
    }
    
    public long getTotalMoney() {
        long result = 0;
        for(PhieuNhapDTO i : pnDAO.selectAll()) {
            result += i.getTongTien();
        }
        return result;
    }
    
    public boolean addNewPN(PhieuNhapDTO pn) {
        if(pnDAO.insert(pn) != 0) {
            phieuNhapList.add(pn);
            return true;
        }
        return false;
    }
    
    public boolean addNewPNWithCTSPList(PhieuNhapDTO pn, ArrayList<CTPhieuNhapDTO> ctpnList, ArrayList<CTSanPhamDTO> ctspList) {
        if(pnDAO.insert(pn) != 0) {
            phieuNhapList.add(pn);
            ctpnDAO.insert(ctpnList);
            ctspDAO.insert(ctspList);
            return true;
        }
        return false;
    }
    
    public ArrayList<PhieuNhapDTO> search(String text, String type){
        ArrayList<PhieuNhapDTO> result = new ArrayList<>();
        text = text.toLowerCase();
        switch(type){
            case "Tất cả":
                for(PhieuNhapDTO i : phieuNhapList) {
                    if(Integer.toString(i.getId()).toLowerCase().contains(text) || nccBUS.getNameByID(i.getIdNhaCungCap()).toLowerCase().contains(text) || nvBUS.getNameByID(i.getIdNhanVien()).toLowerCase().contains(text) || Formatter.FormatDateTime(i.getNgayNhap()).contains(text) || Formatter.FormatVND(i.getTongTien()).contains(text))
                        result.add(i);
                }
                break;
            case "Mã":
                for(PhieuNhapDTO i : phieuNhapList){
                    if(Integer.toString(i.getId()).toLowerCase().contains(text))
                        result.add(i);
                }
                break;
            case "Nhà cung cấp":
                for(PhieuNhapDTO i : phieuNhapList){
                    if(nccBUS.getNameByID(i.getIdNhaCungCap()).toLowerCase().contains(text))
                        result.add(i);
                }
                break;
            case "Nhân viên nhập":
                for(PhieuNhapDTO i : phieuNhapList){
                    if(nvBUS.getNameByID(i.getIdNhanVien()).toLowerCase().contains(text))
                        result.add(i);
                }
                break;
            case "Ngày nhập":
                for(PhieuNhapDTO i : phieuNhapList){
                    if(Formatter.FormatDateTime(i.getNgayNhap()).contains(text))
                        result.add(i);
                }
                break;
            case "Tổng tiền":
                for(PhieuNhapDTO i : phieuNhapList){
                    if(Formatter.FormatVND(i.getTongTien()).contains(text))
                        result.add(i);
                }
                break;
             default:
                    throw new AssertionError();
        }
        return result;
    }
    
    public ArrayList<PhieuNhapDTO> searchByTotalAmount(int start, int end) {
        ArrayList<PhieuNhapDTO> result = new ArrayList<>();
    
        for (PhieuNhapDTO phieuNhap : phieuNhapList) {
            if (phieuNhap.getTongTien() >= start && phieuNhap.getTongTien() <= end)
                result.add(phieuNhap);
        }
    
        return result;
    }

    public ArrayList<PhieuNhapDTO> searchByDateRange(Date startDate, Date endDate) {
        ArrayList<PhieuNhapDTO> result = new ArrayList<>();
        
        Date maxDate = new Date();
    
        if (startDate == null)
            startDate = new Date(Long.MIN_VALUE);
    
        if (endDate == null)
            endDate = maxDate;
    
        for (PhieuNhapDTO phieuNhap : phieuNhapList) {
            System.out.print("Tìm từ ngày đến ngày: ");
            Date ngayXuat = phieuNhap.getNgayNhap(); 
            if (ngayXuat.compareTo(startDate) >= 0 && ngayXuat.compareTo(endDate) <= 0)
                result.add(phieuNhap);
        }
    
        return result;
    }
    
}
