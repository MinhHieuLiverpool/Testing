/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JPanel.java to edit this template
 */
package GUI.Panel;

import DAO.CTHoaDonDAO;
import DAO.KhachHangDAO;
import DAO.NhaCungCapDAO;
import DAO.NhanVienDAO;
import GUI.Component.OverviewCard;

/**
 *
 * @author Admin
 */
public class ThongKeTongQuan extends javax.swing.JPanel {

    public NhanVienDAO nvDAO = new NhanVienDAO();
    public KhachHangDAO khDAO = new KhachHangDAO();
    public CTHoaDonDAO cthdDAO = new CTHoaDonDAO();
    public NhaCungCapDAO nccDAO = new NhaCungCapDAO();

    OverviewCard ovKH;
    OverviewCard ovNV;
    OverviewCard ovSP;
    OverviewCard ovNCC;
    
    public ThongKeTongQuan() {
        initComponents();
        initComponentsCustom();
    }
    
    public void initComponentsCustom() {
        ovKH = new OverviewCard("customer.png", "Tổng số khách hàng", Integer.toString(khDAO.selectAll().size()));
        ovNV = new OverviewCard("employee.png", "Tổng số nhân viên", Integer.toString(nvDAO.selectAll().size()));
        ovSP = new OverviewCard("phone.png", "Tổng số sản phẩm đã bán", Integer.toString(cthdDAO.selectAll().size()));
        ovNCC = new OverviewCard("warehouse.png", "Tổng số nhà cung cấp", Integer.toString(nccDAO.selectAll().size()));
        ovKH.setBounds(64, 80, 400, 200);
        add(ovKH);
        ovNV.setBounds(561, 80, 400, 200);
        add(ovNV);
        ovSP.setBounds(64, 330, 400, 200);
        add(ovSP);
        ovNCC.setBounds(561, 330, 400, 200);
        add(ovNCC);
    }
    
    public void updateContent() {
        ovKH.setContent(Integer.toString(khDAO.selectAll().size()));
        ovNV.setContent(Integer.toString(nvDAO.selectAll().size()));
        ovSP.setContent(Integer.toString(cthdDAO.selectAll().size()));
        ovNCC.setContent(Integer.toString(nccDAO.selectAll().size()));
    }
    
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        setBackground(new java.awt.Color(255, 255, 255));
        setPreferredSize(new java.awt.Dimension(1025, 690));

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 1025, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 690, Short.MAX_VALUE)
        );
    }// </editor-fold>//GEN-END:initComponents


    // Variables declaration - do not modify//GEN-BEGIN:variables
    // End of variables declaration//GEN-END:variables
}
