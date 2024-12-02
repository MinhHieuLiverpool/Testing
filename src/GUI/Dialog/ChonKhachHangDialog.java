/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JDialog.java to edit this template
 */
package GUI.Dialog;

import BUS.KhachHangBUS;
import DTO.KhachHangDTO;
import helper.Formatter;
import java.awt.Font;
import java.util.ArrayList;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author Admin
 */
public class ChonKhachHangDialog extends javax.swing.JDialog {

    private final KhachHangBUS khBUS = new KhachHangBUS();
    private ArrayList<KhachHangDTO> khachHangList = khBUS.getAll();
    private DefaultTableModel tableModel;
    public boolean choosen = false;
    
    
    public ChonKhachHangDialog(java.awt.Frame parent, boolean modal) {
        super(parent, modal);
        initComponents();
        initComponentsCustom();
    }
    
    public void initComponentsCustom() {
        setLocationRelativeTo(null);
        setTitle("Chọn khách hàng");
        khTable.getTableHeader().setFont(new Font("Segoe UI", Font.BOLD, 12));
        tableModel = (DefaultTableModel) khTable.getModel();
        loadDataToTable(khachHangList);
    }

    public void loadDataToTable(ArrayList<KhachHangDTO> khList) {
        tableModel.setRowCount(0);
        for(KhachHangDTO i : khList) {
            tableModel.addRow(new Object[] {
                i.getId(), 
                i.getHo(), 
                i.getTen(), 
                i.getSoDienThoai(), 
                Formatter.FormatDateTime(i.getNgayThamGia()), 
            });
        }
    }
    
    public int getSelectedId() {
        try {
            return (int) khTable.getValueAt(khTable.getSelectedRow(), 0);
        }
        catch (Exception e){
            return -1;
        }
    }
    
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        khTable = new javax.swing.JTable();
        jButton1 = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);

        jPanel1.setBackground(new java.awt.Color(255, 255, 255));

        khTable.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Mã", "Họ", "Tên", "Số điện thoại", "Ngày tham gia"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        khTable.setRowHeight(32);
        khTable.setSelectionBackground(new java.awt.Color(190, 215, 220));
        khTable.setSelectionForeground(new java.awt.Color(0, 0, 0));
        khTable.setShowGrid(true);
        khTable.getTableHeader().setResizingAllowed(false);
        khTable.getTableHeader().setReorderingAllowed(false);
        jScrollPane1.setViewportView(khTable);
        if (khTable.getColumnModel().getColumnCount() > 0) {
            khTable.getColumnModel().getColumn(0).setResizable(false);
            khTable.getColumnModel().getColumn(1).setResizable(false);
            khTable.getColumnModel().getColumn(2).setResizable(false);
            khTable.getColumnModel().getColumn(3).setResizable(false);
            khTable.getColumnModel().getColumn(4).setResizable(false);
        }

        jButton1.setBackground(new java.awt.Color(102, 204, 255));
        jButton1.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jButton1.setForeground(new java.awt.Color(255, 255, 255));
        jButton1.setText("Chọn khách hàng");
        jButton1.setBorder(null);
        jButton1.setPreferredSize(new java.awt.Dimension(140, 40));
        jButton1.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                jButton1MousePressed(evt);
            }
        });

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jScrollPane1)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(340, 340, 340)
                .addComponent(jButton1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(340, 340, 340))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 550, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jButton1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jButton1MousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jButton1MousePressed
        try {
            choosen = true;
            khTable.getValueAt(khTable.getSelectedRow(), 0);
            dispose();
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(null, "Chưa chọn khách hàng");
        }
    }//GEN-LAST:event_jButton1MousePressed

   

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jButton1;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTable khTable;
    // End of variables declaration//GEN-END:variables
}
