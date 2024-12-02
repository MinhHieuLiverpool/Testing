/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package BUS;

import DAO.PhienBanSanPhamDAO;
import DAO.SanPhamDAO;
import DTO.PhienBanSanPhamDTO;
import DTO.SanPhamDTO;
import DTO.ThuongHieuDTO;
import java.util.ArrayList;

/**
 *
 * @author Admin
 */
public class SanPhamBUS {

    public SanPhamDAO spDAO = new SanPhamDAO();
    private PhienBanSanPhamDAO pbspDAO = new PhienBanSanPhamDAO();
    public ArrayList<SanPhamDTO> sanPhamList = new ArrayList<>();
    public ThuongHieuBUS thBUS = new ThuongHieuBUS();
    public ArrayList<ThuongHieuDTO> thList = thBUS.getAll();

    public SanPhamBUS() {
        sanPhamList = spDAO.selectAll();
    }

    public ArrayList<SanPhamDTO> getAll() {
        return spDAO.selectAll();
    }

    public int getLength() {
        return this.sanPhamList.size();
    }
    
    public int getIndexById(int id) {
        for(int i=0; i<this.sanPhamList.size(); i++) {
            if(this.sanPhamList.get(i).getId() == id)
                return i;
        }
        return -1;
    }
    
    public String getNameByID(int id) {
        return sanPhamList.get(getIndexById(id)).getTen();
    }

    public boolean addNewSP(SanPhamDTO sp) {
        if (spDAO.insert(sp) != 0) {
            sanPhamList.add(sp);
            return true;
        }
        return false;
    }

    public boolean addNewSPWithPBSP(SanPhamDTO sp, PhienBanSanPhamDTO pbsp) {
        if (spDAO.insert(sp) != 0) {
            sanPhamList.add(sp);
            pbspDAO.insert(pbsp);
            return true;
        }
        return false;
    }

    public boolean addNewSPWithPBSPList(SanPhamDTO sp, ArrayList<PhienBanSanPhamDTO> pbsplist) {
        if (spDAO.insert(sp) != 0) {
            sanPhamList.add(sp);
            pbspDAO.insert(pbsplist);
            return true;
        }
        return false;
    }
    
    public boolean update(SanPhamDTO sp) {
        if(spDAO.update(sp) != 0) {
            sanPhamList.set(getIndexById(sp.getId()), sp);
            return true;
        }
        return false;
    }
    
    public boolean updateWithPBSPList(SanPhamDTO sp, ArrayList<PhienBanSanPhamDTO> pbspList) {
        if(spDAO.update(sp) != 0) {
            sanPhamList.set(getIndexById(sp.getId()), sp);
            pbspDAO.update(pbspList);
            return true;
        }
        return false;
    }

    public boolean delete(SanPhamDTO sp) {
        if (spDAO.delete(sp.getId()) != 0) {
            sanPhamList.remove(sp);
            return true;
        }
        return false;
    }

    public ArrayList<SanPhamDTO> search(String text) {
        ArrayList<SanPhamDTO> result = new ArrayList<SanPhamDTO>();
        text = text.toLowerCase();
        for (SanPhamDTO i : sanPhamList) {
            if (Integer.toString(i.getId()).toLowerCase().contains(text) || i.getTen().toLowerCase().contains(text) || Float.toString(i.getKichThuocMan()).toLowerCase().contains(text) || i.getCameraSau().toLowerCase().contains(text) || i.getCameraTruoc().toLowerCase().contains(text) || i.getChipXuLy().toLowerCase().contains(text) || i.getHeDieuHanh().toLowerCase().contains(text) || Integer.toString(i.getDungLuongPin()).toLowerCase().contains(text)) {
                result.add(i);
            }
        }
        return result;
    }

    public ArrayList<SanPhamDTO> search(String text, String type) {
        ArrayList<SanPhamDTO> result = new ArrayList<SanPhamDTO>();
        text = text.toLowerCase();
        switch (type) {
            case "Tất cả":
                for (SanPhamDTO i : sanPhamList) {
                    if (Integer.toString(i.getId()).toLowerCase().contains(text) || i.getTen().toLowerCase().contains(text) || Float.toString(i.getKichThuocMan()).toLowerCase().contains(text) || i.getCameraSau().toLowerCase().contains(text) || i.getCameraTruoc().toLowerCase().contains(text) || i.getChipXuLy().toLowerCase().contains(text) || i.getHeDieuHanh().toLowerCase().contains(text) || Integer.toString(i.getDungLuongPin()).toLowerCase().contains(text)) {
                        result.add(i);
                    }
                }
                break;
            case "Mã":
                for (SanPhamDTO i : sanPhamList) {
                    if (Integer.toString(i.getId()).toLowerCase().contains(text)) {
                        result.add(i);
                    }
                }
                break;
            case "Tên":
                for (SanPhamDTO i : sanPhamList) {
                    if (i.getTen().toLowerCase().contains(text)) {
                        result.add(i);
                    }
                }
                break;
            case "Kích thước màn hình":
                for (SanPhamDTO i : sanPhamList) {
                    if (Float.toString(i.getKichThuocMan()).toLowerCase().contains(text)) {
                        result.add(i);
                    }
                }
                break;
            case "Camera sau":
                for (SanPhamDTO i : sanPhamList) {
                    if (i.getCameraSau().toLowerCase().contains(text)) {
                        result.add(i);
                    }
                }
                break;
            case "Camera trước":
                for (SanPhamDTO i : sanPhamList) {
                    if (i.getCameraTruoc().toLowerCase().contains(text)) {
                        result.add(i);
                    }
                }
                break;
            case "Chip xử lý":
                for (SanPhamDTO i : sanPhamList) {
                    if (i.getChipXuLy().toLowerCase().contains(text)) {
                        result.add(i);
                    }
                }
                break;
            case "Hệ điều hành":
                for (SanPhamDTO i : sanPhamList) {
                    if (i.getHeDieuHanh().toLowerCase().contains(text)) {
                        result.add(i);
                    }
                }
                break;
            case "Dung lượng pin":
                for (SanPhamDTO i : sanPhamList) {
                    if (Integer.toString(i.getDungLuongPin()).toLowerCase().contains(text)) {
                        result.add(i);
                    }
                }
                break;
            case "Thương hiệu":
                for (SanPhamDTO i : sanPhamList) {
                    if (thBUS.getNameByID(i.getIdThuongHieu()).toLowerCase().contains(text)) {
                        result.add(i);
                    }
                }
                break;
            default:
                throw new AssertionError();
        }
        return result;
    }

}
