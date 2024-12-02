/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package BUS;

import DAO.CTQuyenDAO;
import DAO.QuyenDAO;
import DTO.CTQuyenDTO;
import DTO.QuyenDTO;
import java.util.ArrayList;

/**
 *
 * @author Admin
 */
public class QuyenBUS {
    public QuyenDAO qDAO = new QuyenDAO();
    public CTQuyenDAO ctqDAO = new CTQuyenDAO();
    public ArrayList<QuyenDTO> quyenList = new ArrayList<>();
    
    public QuyenBUS() {
        quyenList = qDAO.selectAll();
    }
    
    public ArrayList<QuyenDTO> getAll() {
        return this.quyenList;
    }
    
    public QuyenDTO getByIndex(int index) {
        return this.quyenList.get(index);
    }
    
    public QuyenDTO getObjectById(int id) {
        for(QuyenDTO i : quyenList) {
            if(i.getId() == id)
                return i;
        }
        return null;
    }
    
    public int getIndexById(int id) {
        for(int i=0; i<quyenList.size(); i++) {
            if(quyenList.get(i).getId() == id)
                return i;
        }
        return -1;
    }
    
    public String getNameById(int id) {
        return quyenList.get(getIndexById(id)).getTen();
    }
    
    public String[] getStringList() {
        String[] result = new String[quyenList.size()];
        for(int i=0; i<quyenList.size(); i++) {
            result[i] = quyenList.get(i).getTen();
        }
        return result;
    }
    
    public ArrayList<CTQuyenDTO> getCTQListById(int id) {
        return ctqDAO.selectAll(id);
    }
    
    public boolean checkChucNang(ArrayList<CTQuyenDTO> ctqList, int idCN) {
        for(CTQuyenDTO i : ctqList)
            if(i.getIdChucNang() == idCN)
                return true;
        return false;
    }
    
    public boolean checkQuyen(ArrayList<CTQuyenDTO> ctqList, int idCN, String hanhDong) {
        for(CTQuyenDTO i : ctqList)
            if(i.getIdChucNang() == idCN)
                if(i.getHanhDong().equals(hanhDong))
                    return true;
        return false;
    }
    
    public boolean add(QuyenDTO q, ArrayList<CTQuyenDTO> ctqList) {
        if(qDAO.insert(q) != 0) {
            ctqDAO.insert(ctqList);
            quyenList.add(q);
            return true;
        }
        return false;
    }
    
    public boolean update(QuyenDTO q, ArrayList<CTQuyenDTO> ctqList) {
        if(qDAO.update(q) != 0) {
            ctqDAO.update(ctqList, q.getId());
            quyenList.set(getIndexById(q.getId()), q);
            return true;
        }
        return false;
    }
    
    public boolean delete(QuyenDTO q) {
        if(ctqDAO.delete(q.getId()) >= 0) {
            qDAO.delete(q.getId());
            quyenList.remove(q);
            return true;
        }
        return false;
    }
    
    public ArrayList<QuyenDTO> search(String text, String type){
        ArrayList<QuyenDTO> result = new ArrayList<>();
        text = text.toLowerCase();
        switch (type) {
            case "Tất cả":
                for(QuyenDTO i : quyenList){
                    if(Integer.toString(i.getId()).toLowerCase().contains(text) || i.getTen().toLowerCase().contains(text))
                        result.add(i);
                }
                break;
            case "Mã":
                for(QuyenDTO i : quyenList){
                    if(Integer.toString(i.getId()).toLowerCase().contains(text))
                        result.add(i);
                }
                break;
            case "Tên quyền":
                for(QuyenDTO i : quyenList){
                    if(i.getTen().toLowerCase().contains(text))
                        result.add(i);
                }
                break;
            default:
                throw new AssertionError();
        }
        return result;
    }
    
}
