/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JDialog.java to edit this template
 */
package GUI.Dialog;

import BUS.PhienBanSanPhamBUS;
import BUS.SanPhamBUS;
import DAO.CTSanPhamDAO;
import DTO.CTSanPhamDTO;
import DTO.PhienBanSanPhamDTO;
import DTO.SanPhamDTO;
import config.Constants;
import helper.Calculator;
import helper.Formatter;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author Duc3m
 */
public class ChonCTSPDialog extends javax.swing.JDialog {

    private int pbspId;
    private ArrayList<CTSanPhamDTO> ctspList;
    private CTSanPhamDAO ctspDAO = new CTSanPhamDAO();
    private DefaultTableModel ctspTableModel;
    private SanPhamBUS spBUS = new SanPhamBUS();
    private PhienBanSanPhamBUS pbspBUS = new PhienBanSanPhamBUS();
    public boolean choosen = false;
    
    public ChonCTSPDialog(java.awt.Frame parent, boolean modal, int pbspId) {
        super(parent, modal);
        this.pbspId = pbspId;
        initComponents();
        initComponentsCustom();
        btnIMEI.addMouseListener(new MouseAdapter(){
            @Override
            public void mousePressed(MouseEvent e) {
                try {
                    choosen = true;
                    ctspTable.getValueAt(ctspTable.getSelectedRow(), 0);
                    dispose();
                } catch (Exception ex) {
                    JOptionPane.showMessageDialog(null, "Chưa chọn IMEI");
                }
            }
        });
    }
    
    public void initComponentsCustom() {
        this.setLocationRelativeTo(null);
        this.ctspList = ctspDAO.selectAvailableByPBSPId(this.pbspId);
        ctspTableModel = (DefaultTableModel) ctspTable.getModel();
        PhienBanSanPhamDTO pbsp = pbspBUS.getObjectById(this.pbspId);
        String sp = spBUS.getNameByID(pbsp.getIdSanPham());
        lblTitle.setText(lblTitle.getText()+ sp + " " + pbsp.getRam() + "GB " + pbsp.getRom() + "GB");
        loadSPToTable();
    }
    
    public void loadSPToTable() {
        for(CTSanPhamDTO i : this.ctspList) {
            if(i.getTrangThai() == 1)
            ctspTableModel.addRow(new Object[]{
                i.getImei(),
                Formatter.FormatVND(Calculator.calculatePrice(i.getGiaNhap()))
            });
        }
    }
    
    public String getSelectedIMEI() {
        try {
            return (String) ctspTable.getValueAt(ctspTable.getSelectedRow(), 0);
        }
        catch (Exception e){
            return "";
        }
    }

    
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        ctspTable = new javax.swing.JTable();
        lblTitle = new javax.swing.JLabel();
        btnIMEI = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setBackground(new java.awt.Color(255, 255, 255));

        jPanel1.setBackground(new java.awt.Color(255, 255, 255));

        ctspTable.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "IMEI", "Giá bán"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        jScrollPane1.setViewportView(ctspTable);
        if (ctspTable.getColumnModel().getColumnCount() > 0) {
            ctspTable.getColumnModel().getColumn(0).setResizable(false);
            ctspTable.getColumnModel().getColumn(1).setResizable(false);
        }

        lblTitle.setFont(new java.awt.Font("Segoe UI", 1, 16)); // NOI18N
        lblTitle.setText("Chọn IMEI cho ");

        btnIMEI.setBackground(new java.awt.Color(102, 204, 255));
        btnIMEI.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        btnIMEI.setForeground(new java.awt.Color(255, 255, 255));
        btnIMEI.setText("Chọn IMEI này");
        btnIMEI.setBorder(null);
        btnIMEI.setPreferredSize(new java.awt.Dimension(105, 40));

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 512, Short.MAX_VALUE)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(lblTitle, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(176, 176, 176)
                .addComponent(btnIMEI, javax.swing.GroupLayout.PREFERRED_SIZE, 160, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addComponent(lblTitle, javax.swing.GroupLayout.PREFERRED_SIZE, 34, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 300, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(btnIMEI, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        getContentPane().add(jPanel1, java.awt.BorderLayout.CENTER);

        pack();
    }// </editor-fold>//GEN-END:initComponents


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnIMEI;
    private javax.swing.JTable ctspTable;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JLabel lblTitle;
    // End of variables declaration//GEN-END:variables
}
