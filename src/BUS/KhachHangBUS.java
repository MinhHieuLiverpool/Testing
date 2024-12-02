/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package BUS;

import DAO.KhachHangDAO;
import DTO.KhachHangDTO;
import java.util.ArrayList;

/**
 *
 * @author Admin
 */
public class KhachHangBUS {
    private final KhachHangDAO khDAO = new KhachHangDAO();
    public ArrayList<KhachHangDTO> khachHangList = new ArrayList<KhachHangDTO>();

    public KhachHangBUS() {
        khachHangList = khDAO.selectAll();
    }

    public ArrayList<KhachHangDTO> getAll() {
        return this.khachHangList;
    }

    public int getIndexByID(int id) {
        for (int i = 0; i < khachHangList.size(); i++)
            if (khachHangList.get(i).getId() == id)
                return i;
        return -1;
    }

    public String getNameByID(int id) {
        return khachHangList.get(getIndexByID(id)).getHo() + " " + khachHangList.get(getIndexByID(id)).getTen();
    }

    public boolean add(KhachHangDTO newKH) {
        if (khDAO.insert(newKH) != 0) {
            khachHangList.add(newKH);
            return true;
        }
        return false;
    }

    public boolean update(KhachHangDTO kh) {
        if (khDAO.update(kh) != 0) {
            khachHangList.set(getIndexByID(kh.getId()), kh);
            return true;
        }
        return false;
    }

    public boolean delete(KhachHangDTO kh) {
        if (khDAO.delete(kh.getId()) != 0) {
            khachHangList.remove(kh);
            return true;
        }
        return false;
    }

    public ArrayList<KhachHangDTO> search(String text) {
        ArrayList<KhachHangDTO> result = new ArrayList<KhachHangDTO>();
        text = text.toLowerCase();
        for (KhachHangDTO i : this.khachHangList) {
            if (Integer.toString(i.getId()).toLowerCase().contains(text) || i.getHo().toLowerCase().contains(text)
                    || i.getTen().toLowerCase().contains(text) || i.getSoDienThoai().toLowerCase().contains(text)) {
                result.add(i);
            }
        }
        return result;
    }

    public ArrayList<KhachHangDTO> search(String text, String type) {
        ArrayList<KhachHangDTO> result = new ArrayList<>();
        text = text.toLowerCase();
        switch (type) {
            case "Tất cả":
                for (KhachHangDTO i : khachHangList) {
                    if (Integer.toString(i.getId()).toLowerCase().contains(text)
                            || i.getHo().toLowerCase().contains(text) || i.getTen().toLowerCase().contains(text)
                            || i.getSoDienThoai().toLowerCase().contains(text)) {
                        result.add(i);
                    }
                }
                break;
            case "Mã":
                for (KhachHangDTO i : khachHangList) {
                    if (Integer.toString(i.getId()).toLowerCase().contains(text)) {
                        result.add(i);
                    }
                }
                break;
            case "Họ":
                for (KhachHangDTO i : khachHangList) {
                    if (i.getHo().toLowerCase().contains(text)) {
                        result.add(i);
                    }
                }
                break;
            case "Tên":
                for (KhachHangDTO i : khachHangList) {
                    if (i.getTen().toLowerCase().contains(text)) {
                        result.add(i);
                    }
                }
                break;
            case "Số điện thoại":
                for (KhachHangDTO i : khachHangList) {
                    if (i.getSoDienThoai().toLowerCase().contains(text)) {
                        result.add(i);
                    }
                }
                break;
            default:
                throw new AssertionError();

        }
        return result;
    }

    public String[] getStringList() {
        String[] result = new String[khachHangList.size()];
        for (int i = 0; i < khachHangList.size(); i++) {
            result[i] = khachHangList.get(i).getHo() + " " + khachHangList.get(i).getTen();
        }
        return result;
    }

    public KhachHangDTO getByIndex(int index) {
        return this.khachHangList.get(index);
    }

    public KhachHangDTO getObjectById(int id) {
        for (KhachHangDTO i : khachHangList)
            if (i.getId() == id)
                return i;
        return null;
    }

    public String getNameById(int id) {
        int index = this.getIndexByID(id);
        return khachHangList.get(index).getHo() + " " + khachHangList.get(index).getTen();
    }
}
