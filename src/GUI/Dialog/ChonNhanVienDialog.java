/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JDialog.java to edit this template
 */
package GUI.Dialog;

import BUS.NhanVienBUS;
import DAO.NhanVienDAO;
import DTO.NhanVienDTO;
import java.awt.Font;
import java.util.ArrayList;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author Admin
 */
public class ChonNhanVienDialog extends javax.swing.JDialog {

    public NhanVienDAO nvDAO = new NhanVienDAO();
    public ArrayList<NhanVienDTO> nhanVienList = nvDAO.selectAllWithoutAccount();
    private DefaultTableModel tableModel;
    public boolean choosen = false;
    
    public ChonNhanVienDialog(java.awt.Frame parent, boolean modal) {
        super(parent, modal);
        initComponents();
        initComponentsCustom();
    }

    public void initComponentsCustom() {
        setLocationRelativeTo(null);
        setTitle("Chọn nhân viên");
        nvTable.getTableHeader().setFont(new Font("Segoe UI", Font.BOLD, 12));
        tableModel = (DefaultTableModel) nvTable.getModel();
        loadDataToTable(nhanVienList);
    }
    
    public void loadDataToTable(ArrayList<NhanVienDTO> nvList){
        tableModel.setRowCount(0);
        for(NhanVienDTO i : nvList){
            tableModel.addRow(new Object[] {i.getId(), i.getHo(), i.getTen(), i.getGioiTinh(), i.getSoDienThoai(), i.getEmail()});
        }
    }
    
    public int getSelectedId() {
        try {
            return (int) nvTable.getValueAt(nvTable.getSelectedRow(), 0);
        }
        catch (Exception e){
            return -1;
        }
    }
    
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        jButton1 = new javax.swing.JButton();
        jScrollPane1 = new javax.swing.JScrollPane();
        nvTable = new javax.swing.JTable();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);

        jPanel1.setBackground(new java.awt.Color(255, 255, 255));

        jButton1.setBackground(new java.awt.Color(102, 204, 255));
        jButton1.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jButton1.setForeground(new java.awt.Color(255, 255, 255));
        jButton1.setText("Chọn nhân viên");
        jButton1.setBorder(null);
        jButton1.setPreferredSize(new java.awt.Dimension(120, 40));
        jButton1.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                jButton1MousePressed(evt);
            }
        });

        nvTable.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Mã", "Họ", "Tên", "Giới tính", "Số điện thoại", "Email"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        nvTable.setRowHeight(32);
        nvTable.setSelectionBackground(new java.awt.Color(190, 215, 220));
        nvTable.setSelectionForeground(new java.awt.Color(0, 0, 0));
        nvTable.setShowGrid(true);
        nvTable.getTableHeader().setResizingAllowed(false);
        nvTable.getTableHeader().setReorderingAllowed(false);
        jScrollPane1.setViewportView(nvTable);
        if (nvTable.getColumnModel().getColumnCount() > 0) {
            nvTable.getColumnModel().getColumn(0).setResizable(false);
            nvTable.getColumnModel().getColumn(0).setPreferredWidth(40);
            nvTable.getColumnModel().getColumn(1).setResizable(false);
            nvTable.getColumnModel().getColumn(1).setPreferredWidth(200);
            nvTable.getColumnModel().getColumn(2).setResizable(false);
            nvTable.getColumnModel().getColumn(2).setPreferredWidth(150);
            nvTable.getColumnModel().getColumn(3).setResizable(false);
            nvTable.getColumnModel().getColumn(3).setPreferredWidth(100);
            nvTable.getColumnModel().getColumn(4).setResizable(false);
            nvTable.getColumnModel().getColumn(4).setPreferredWidth(150);
            nvTable.getColumnModel().getColumn(5).setResizable(false);
            nvTable.getColumnModel().getColumn(5).setPreferredWidth(200);
        }

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(340, 340, 340)
                .addComponent(jButton1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(340, Short.MAX_VALUE))
            .addComponent(jScrollPane1)
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 530, Short.MAX_VALUE)
                .addGap(18, 18, 18)
                .addComponent(jButton1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(12, 12, 12))
        );

        getContentPane().add(jPanel1, java.awt.BorderLayout.CENTER);

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jButton1MousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jButton1MousePressed
        try {
            nvTable.getValueAt(nvTable.getSelectedRow(), 0);
            choosen = true;
            dispose();
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(null, "Chưa chọn nhân viên");
        }
    }//GEN-LAST:event_jButton1MousePressed

    

    // Variables declaration - do not modify//GEN-BEGIN:variables
    public javax.swing.JButton jButton1;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTable nvTable;
    // End of variables declaration//GEN-END:variables
}
