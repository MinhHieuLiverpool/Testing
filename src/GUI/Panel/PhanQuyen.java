/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JPanel.java to edit this template
 */
package GUI.Panel;

import BUS.CTQuyenBUS;
import BUS.ChucNangBUS;
import BUS.QuyenBUS;
import DAO.QuyenDAO;
import DTO.CTQuyenDTO;
import DTO.ChucNangDTO;
import DTO.QuyenDTO;
import DTO.TaiKhoanDTO;
import GUI.Component.SearchBar;
import GUI.Component.ToolBarButton;
import GUI.Dialog.QuyenDialog;
import GUI.Main;
import helper.JTableExporter;
import java.awt.BorderLayout;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.IOException;
import java.util.ArrayList;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author Admin
 */
public class PhanQuyen extends javax.swing.JPanel implements ActionListener {

    public QuyenBUS qBUS = new QuyenBUS();
    public CTQuyenBUS ctqBUS = new CTQuyenBUS();
    public ArrayList<QuyenDTO> qList = qBUS.getAll();
    public QuyenDAO qDAO = new QuyenDAO();
    public Main main;
    
    private TaiKhoanDTO taiKhoan;
    
    public ArrayList<CTQuyenDTO> ctqList;
    public ChucNangBUS cnBUS = new ChucNangBUS();
    public ArrayList<ChucNangDTO> cnList = cnBUS.getAll();
    
    private DefaultTableModel tableModel;
    public SearchBar searchBar;
    public ToolBarButton chiTietBtn = new ToolBarButton("Chi tiết", "toolBar_detail.svg", "detail");
    public ToolBarButton themBtn = new ToolBarButton("Thêm", "toolBar_add.svg", "add");
    public ToolBarButton suaBtn = new ToolBarButton("Sửa", "toolBar_edit.svg", "edit");
    public ToolBarButton xoaBtn = new ToolBarButton("Xóa", "toolBar_delete.svg", "delete");
    public ToolBarButton exportBtn = new ToolBarButton("Xuất excel", "toolBar_export.svg", "export");
    
    public PhanQuyen(Main main, TaiKhoanDTO taiKhoan) {
        this.main = main;
        this.taiKhoan = taiKhoan;
        ctqList = qBUS.getCTQListById(taiKhoan.getIdQuyen());
        initComponents();
        initComponentsCustom();
        loadDataToTable(this.qList);
    }
    
    public void initComponentsCustom() {
        searchBar = new SearchBar(new String[]{"Tất cả", "Mã", "Tên quyền"});
        searchBar.txtSearch.addKeyListener(new KeyAdapter(){
            @Override
            public void keyReleased(KeyEvent e) {
                searchEvent();
            }
        });
        searchBar.lamMoiBtn.addMouseListener(new MouseAdapter(){
            @Override
            public void mousePressed(MouseEvent e) {
                reloadEvent();
            }
        });
        searchBar.cbxType.addItemListener(new ItemListener() {
            public void itemStateChanged(ItemEvent e) {
                searchEvent();
            }
        });
        topPanel.add(searchBar, BorderLayout.CENTER);
        toolBar.add(chiTietBtn);
        if(qBUS.checkQuyen(ctqList, 7, "add"))
            toolBar.add(themBtn);
        if(qBUS.checkQuyen(ctqList, 7, "edit"))
            toolBar.add(suaBtn);
//        if(qBUS.checkQuyen(ctqList, 7, "delete"))
//            toolBar.add(xoaBtn);
        chiTietBtn.addActionListener(this);
        themBtn.addActionListener(this);
        suaBtn.addActionListener(this);
        xoaBtn.addActionListener(this);
//        toolBar.add(exportBtn);
        exportBtn.addActionListener(this);
        quyenTable.getTableHeader().setFont(new Font("Segoe UI", Font.BOLD, 12));
        tableModel = (DefaultTableModel) quyenTable.getModel(); 
    }

    public void loadDataToTable(ArrayList<QuyenDTO> qList) {
        tableModel.setRowCount(0);
        for(QuyenDTO i : qList) {
            tableModel.addRow(new Object[]{
                i.getId(),
                i.getTen()
            });
        }
    }
    
    public int getSelectedRow(){
        int index = quyenTable.getSelectedRow();
        if(index == -1){
            JOptionPane.showMessageDialog(main, "Bạn chưa chọn quyền nào", "Lỗi", JOptionPane.ERROR_MESSAGE);
        }
        return index;
    }
    
    public void reloadEvent() {
        searchBar.txtSearch.setText("");
        loadDataToTable(qList);
    }
    
    public void searchEvent() {
        String searchText = searchBar.txtSearch.getText();
        loadDataToTable(qBUS.search(searchText,(String) searchBar.cbxType.getSelectedItem()));
    }
    
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        topPanel = new javax.swing.JPanel();
        toolBar = new javax.swing.JToolBar();
        jPanel1 = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        quyenTable = new javax.swing.JTable();

        setBackground(new java.awt.Color(255, 255, 255));
        setPreferredSize(new java.awt.Dimension(1030, 720));
        setLayout(new java.awt.BorderLayout());

        topPanel.setBackground(new java.awt.Color(255, 255, 255));
        topPanel.setBorder(javax.swing.BorderFactory.createMatteBorder(1, 0, 1, 0, new java.awt.Color(221, 221, 221)));
        topPanel.setPreferredSize(new java.awt.Dimension(1030, 100));
        topPanel.setLayout(new java.awt.BorderLayout());

        toolBar.setBackground(new java.awt.Color(255, 255, 255));
        toolBar.setRollover(true);
        toolBar.setPreferredSize(new java.awt.Dimension(400, 100));
        topPanel.add(toolBar, java.awt.BorderLayout.LINE_END);

        add(topPanel, java.awt.BorderLayout.PAGE_START);

        quyenTable.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Mã", "Tên quyền"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        quyenTable.setRowHeight(32);
        quyenTable.setSelectionBackground(new java.awt.Color(190, 215, 220));
        quyenTable.setSelectionForeground(new java.awt.Color(0, 0, 0));
        quyenTable.setShowGrid(true);
        quyenTable.getTableHeader().setResizingAllowed(false);
        quyenTable.getTableHeader().setReorderingAllowed(false);
        jScrollPane1.setViewportView(quyenTable);
        if (quyenTable.getColumnModel().getColumnCount() > 0) {
            quyenTable.getColumnModel().getColumn(0).setResizable(false);
            quyenTable.getColumnModel().getColumn(1).setResizable(false);
        }

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 1030, Short.MAX_VALUE)
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 620, Short.MAX_VALUE)
        );

        add(jPanel1, java.awt.BorderLayout.CENTER);
    }// </editor-fold>//GEN-END:initComponents


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JPanel jPanel1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTable quyenTable;
    private javax.swing.JToolBar toolBar;
    private javax.swing.JPanel topPanel;
    // End of variables declaration//GEN-END:variables

     @Override
    public void actionPerformed(ActionEvent e) {
        if(e.getSource() == chiTietBtn) {
            int index = getSelectedRow();
            
            if(index != -1) {
                int id = (int) quyenTable.getValueAt(index, 0);
                QuyenDialog qDialog = new QuyenDialog(main, true, qBUS.getObjectById(id), qBUS.getCTQListById(id), "detail");
                qDialog.setVisible(true);
                loadDataToTable(qList);
            }
            
        }
        
        if(e.getSource() == themBtn) {
            QuyenDialog qDialog = new QuyenDialog(main, true, null, null, "add");
            qDialog.setVisible(true);
            qList = qDAO.selectAll();
            qBUS.quyenList = qDAO.selectAll();
            loadDataToTable(qList);
        }
        
        if(e.getSource() == xoaBtn) {
            int index = getSelectedRow();
            if(index != -1) {
                int id = (int) quyenTable.getValueAt(index, 0);
                if(id == 1) {
                    JOptionPane.showMessageDialog(main, "Bạn không thể xóa quyền của Quản lý");
                    return;
                }
                if (JOptionPane.showConfirmDialog(main, "Bạn có chắc muốn xóa quyền này không?", "", JOptionPane.YES_NO_OPTION) == 0) {
                    if(qBUS.delete(qBUS.getObjectById(id)) == true) {
                        JOptionPane.showMessageDialog(main, "Xóa quyền thành công");
                        loadDataToTable(qList);
                    }
                }
            }
        }
        if(e.getSource() == suaBtn) {
            int index = getSelectedRow();
            if(index != -1) {
                int id = (int) quyenTable.getValueAt(index, 0);
                if(id == 1) {
                    JOptionPane.showMessageDialog(main, "Bạn không thể sửa quyền của Quản lý");
                    return;
                }
                QuyenDialog qDialog = new QuyenDialog(main, true, qBUS.getObjectById(id), qBUS.getCTQListById(id), "edit");
                qDialog.setVisible(true);
                loadDataToTable(qList);
            }
        }
        
        if(e.getSource() == exportBtn) {
            try {
                JTableExporter.exportJTableToExcel(quyenTable);
            } catch (IOException ex) {
                System.out.println(ex);
            }
        }
        
    }
    
}
