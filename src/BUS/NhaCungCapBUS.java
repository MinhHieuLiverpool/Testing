/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package BUS;

import DAO.NhaCungCapDAO;
import DTO.NhaCungCapDTO;
import java.util.ArrayList;

/**
 *
 * @author Admin
 */
public class NhaCungCapBUS {
    private final NhaCungCapDAO nccDAO = new NhaCungCapDAO();
    private ArrayList<NhaCungCapDTO> nhaCungCapList = new ArrayList<NhaCungCapDTO>();

    public NhaCungCapBUS() {
        nhaCungCapList = nccDAO.selectAll();
    }

    public ArrayList<NhaCungCapDTO> getAll() {
        return this.nhaCungCapList;
    }

    public NhaCungCapDTO getByIndex(int index) {
        return this.nhaCungCapList.get(index);
    }

    public int getIndexByID(int id) {
        for (int i = 0; i < nhaCungCapList.size(); i++) {
            if (nhaCungCapList.get(i).getId() == id)
                return i;
        }
        return -1;
    }

    public String getNameByID(int id) {
        return nhaCungCapList.get(getIndexByID(id)).getTen();
    }
    
    public String[] getStringList() {
        String[] result = new String[nhaCungCapList.size()];
        for(int i=0; i<nhaCungCapList.size(); i++) {
            result[i] = nhaCungCapList.get(i).getTen();
        }
        return result;
    }

    public ArrayList<NhaCungCapDTO> search(String text) {
        ArrayList<NhaCungCapDTO> result = new ArrayList<>();
        text = text.toLowerCase();
        for (NhaCungCapDTO i : nhaCungCapList) {
            if (Integer.toString(i.getId()).toLowerCase().contains(text) || i.getTen().toLowerCase().contains(text)
                    || i.getDiaChi().toLowerCase().contains(text) || i.getSoDienThoai().toLowerCase().contains(text)
                    || i.getEmail().toLowerCase().contains(text))
                result.add(i);
        }
        return result;
    }

//    public boolean add(NhaCungCapDTO newNCC) {
//        if (nccDAO.insert(newNCC) != 0) {
//            nhaCungCapList.add(newNCC);
//            return true;
//        }
//        return false;
//    }
//<----Hieu test---->
    
    public boolean add(NhaCungCapDTO newNCC) {
    // Kiểm tra tính hợp lệ của thông tin nhà cung cấp
    if (newNCC.getTen() == null || newNCC.getTen().trim().isEmpty()) {
        System.out.println("Tên nhà cung cấp không được để trống!");
        return false;
    }
    if (newNCC.getTen().trim().length() < 5 || newNCC.getTen().trim().length() > 50) {
        System.out.println("Tên nhà cung cấp phải có độ dài từ 5 đến 50 ký tự!");
        return false;
    }
    if (newNCC.getDiaChi() == null || newNCC.getDiaChi().trim().isEmpty()) {
        System.out.println("Địa chỉ nhà cung cấp không được để trống!");
        return false;
    }
    if (newNCC.getDiaChi().trim().length() < 5 || newNCC.getDiaChi().trim().length() > 50) {
        System.out.println("Địa chỉ nhà cung cấp phải có độ dài từ 5 đến 50 ký tự!");
        return false;
    }
    if (newNCC.getSoDienThoai() == null || newNCC.getSoDienThoai().trim().isEmpty()) {
        System.out.println("Số điện thoại nhà cung cấp không được để trống!");
        return false;
    }
    if (newNCC.getEmail() == null || newNCC.getEmail().trim().isEmpty()) {
        System.out.println("Email nhà cung cấp không được để trống!");
        return false;
    }

    // Kiểm tra định dạng số điện thoại: Bắt đầu bằng '0' và chứa 10 số
    if (!newNCC.getSoDienThoai().matches("^0\\d{9}$")) {
        System.out.println("Số điện thoại không hợp lệ! Phải bắt đầu bằng số 0 và chứa đúng 10 chữ số.");
        return false;
    }

    // Kiểm tra định dạng email
    if (!newNCC.getEmail().matches("^[a-zA-Z0-9._%+-]+@[a-zA-Z0-9.-]+\\.[a-zA-Z]{2,6}$")) {
        System.out.println("Email không đúng định dạng!");
        return false;
    }

    // Tiến hành thêm nếu hợp lệ
    if (nccDAO.insert(newNCC) != 0) {
        nhaCungCapList.add(newNCC);
        System.out.println("Thêm nhà cung cấp thành công");
        return true;
    }
    return false;
}


//    <----- Hieu test ----->
    
    public boolean update(NhaCungCapDTO ncc) {
        if (nccDAO.update(ncc) != 0) {
            nhaCungCapList.set(getIndexByID(ncc.getId()), ncc);
            return true;
        }
        return false;
    }

    public boolean delete(NhaCungCapDTO ncc) {
        if (nccDAO.delete(ncc.getId()) != 0) {
            nhaCungCapList.remove(ncc);
            return true;
        }
        return false;
    }

    public ArrayList<NhaCungCapDTO> search(String text, String type) {
        ArrayList<NhaCungCapDTO> result = new ArrayList<NhaCungCapDTO>();
        text = text.toLowerCase();
        switch (type) {
            case "Tất cả":
                for (NhaCungCapDTO i : nhaCungCapList) {
                    if (Integer.toString(i.getId()).toLowerCase().contains(text)
                            || i.getTen().toLowerCase().contains(text) || i.getDiaChi().toLowerCase().contains(text)
                            || i.getSoDienThoai().toLowerCase().contains(text)
                            || i.getEmail().toLowerCase().contains(text)) {
                        result.add(i);
                    }
                }
                break;
            case "Mã":
                for (NhaCungCapDTO i : nhaCungCapList) {
                    if (Integer.toString(i.getId()).toLowerCase().contains(text)) {
                        result.add(i);
                    }
                }
                break;
            case "Tên":
                for (NhaCungCapDTO i : nhaCungCapList) {
                    if (i.getTen().toLowerCase().contains(text)) {
                        result.add(i);
                    }
                }
                break;
            case "Địa chỉ":
                for (NhaCungCapDTO i : nhaCungCapList) {
                    if (i.getDiaChi().toLowerCase().contains(text)) {
                        result.add(i);
                    }
                }
                break;
            case "Số điện thoại":
                for (NhaCungCapDTO i : nhaCungCapList) {
                    if (i.getSoDienThoai().toLowerCase().contains(text)) {
                        result.add(i);
                    }
                }
                break;
            case "Email":
                for (NhaCungCapDTO i : nhaCungCapList) {
                    if (i.getEmail().toLowerCase().contains(text)) {
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
