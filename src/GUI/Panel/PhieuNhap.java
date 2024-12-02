/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JPanel.java to edit this template
 */
package GUI.Panel;

import BUS.ChucNangBUS;
import BUS.NhaCungCapBUS;
import BUS.NhanVienBUS;
import BUS.PhieuNhapBUS;
import BUS.QuyenBUS;
import DTO.CTQuyenDTO;
import DTO.ChucNangDTO;
import DTO.PhieuNhapDTO;
import DTO.TaiKhoanDTO;
import GUI.Component.SearchBar;
import GUI.Component.SearchBar1;
import GUI.Component.ToolBarButton;
import GUI.Dialog.PhieuNhapDialog;
import GUI.Main;
import com.formdev.flatlaf.extras.FlatSVGIcon;
import helper.Formatter;
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
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.Locale;
import javax.swing.JOptionPane;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author Admin
 */
public class PhieuNhap extends javax.swing.JPanel implements ActionListener{

    public PhieuNhapBUS pnBUS = new PhieuNhapBUS();
    public NhaCungCapBUS nccBUS = new NhaCungCapBUS();
    public NhanVienBUS nvBUS = new NhanVienBUS();
    public ArrayList<PhieuNhapDTO> phieuNhapList = pnBUS.getAll();
    public Main main;
    
    private TaiKhoanDTO taiKhoan;
    
    public QuyenBUS qBUS = new QuyenBUS();
    public ArrayList<CTQuyenDTO> ctqList;
    public ChucNangBUS cnBUS = new ChucNangBUS();
    public ArrayList<ChucNangDTO> cnList = cnBUS.getAll();
    
    private DefaultTableModel tableModel;
    public SearchBar1 searchBar;
    ToolBarButton chiTietBtn = new ToolBarButton("Chi tiết", "toolBar_detail.svg", "detail");
    ToolBarButton themBtn = new ToolBarButton("Nhập hàng", "toolBar_add.svg", "add");
    public ToolBarButton exportBtn = new ToolBarButton("Xuất excel", "toolBar_export.svg", "export");
    
    /**
     * Creates new form PhieuNhap
     */
    public PhieuNhap(Main main, TaiKhoanDTO taiKhoan) {
        this.main = main;
        this.taiKhoan = taiKhoan;
        ctqList = qBUS.getCTQListById(taiKhoan.getIdQuyen());
        initComponents();
        initComponentsCustom();
        loadDataToTable(this.phieuNhapList);
    }
    
    public void initComponentsCustom() {
        searchBar = new SearchBar1(new String[]{"Tất cả", "Mã", "Nhà cung cấp", "Nhân viên nhập", "Ngày nhập", "Tổng tiền"});
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
            @Override
            public void itemStateChanged(ItemEvent e) {
                if (e.getStateChange() == ItemEvent.SELECTED) {
                    String selectedItem = (String) searchBar.cbxType.getSelectedItem();

                    if (selectedItem.equals("Tổng tiền")) {
                        // Hiển thị hai TextField "giatritu" và "giatriden"
                        searchBar.searchTongTien.giatritu().setEnabled(true);
                        searchBar.searchTongTien.giatriden().setEnabled(true);
                    } else if (selectedItem.equals("Ngày nhập")) {
                        searchBar.searchDaytoDay.giatritu().setEnabled(true);
                        searchBar.searchDaytoDay.giatriden().setEnabled(true);
                    } else {
                        // Gọi hàm tìm kiếm khác khi loại tìm kiếm không phải là "Tổng tiền"
                        searchEvent();
                    }
                }
            }
        });
        searchBar.searchTongTien.giatritu().getDocument().addDocumentListener(new DocumentListener() {
            @Override
            public void insertUpdate(DocumentEvent e) {
                performSearchByTotalAmount();
            }

            @Override
            public void removeUpdate(DocumentEvent e) {
                performSearchByTotalAmount();
            }

            @Override
            public void changedUpdate(DocumentEvent e) {
                performSearchByTotalAmount();
            }
        });

        searchBar.searchTongTien.giatriden().getDocument().addDocumentListener(new DocumentListener() {
            @Override
            public void insertUpdate(DocumentEvent e) {
                performSearchByTotalAmount();
            }

            @Override
            public void removeUpdate(DocumentEvent e) {
                performSearchByTotalAmount();
            }

            @Override
            public void changedUpdate(DocumentEvent e) {
                performSearchByTotalAmount();
            }
        });

        searchBar.searchDaytoDay.giatritu().addPropertyChangeListener("date", new PropertyChangeListener() {
            @Override
            public void propertyChange(PropertyChangeEvent evt) {
                Date startDate = (Date) searchBar.searchDaytoDay.giatritu().getDate();
                Date endDate = (Date) searchBar.searchDaytoDay.giatriden().getDate();
                performSearchByDateRange(startDate, endDate);
            }
        });

        searchBar.searchDaytoDay.giatriden().addPropertyChangeListener("date", new PropertyChangeListener() {
            @Override
            public void propertyChange(PropertyChangeEvent evt) {
                Date startDate = (Date) searchBar.searchDaytoDay.giatritu().getDate();
                Date endDate = (Date) searchBar.searchDaytoDay.giatriden().getDate();
                performSearchByDateRange(startDate, endDate);
            }
        });
        // làm mới calendar
        searchBar.lamMoiBtn.addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent e) {
                // dặt null
                searchBar.searchDaytoDay.giatritu().setDate(null);
                searchBar.searchDaytoDay.giatriden().setDate(null);

                reloadEvent();
            }
        });
        
        topPanel.add(searchBar, BorderLayout.CENTER);
        toolBar.add(chiTietBtn);
        if(qBUS.checkQuyen(ctqList, 2, "add"))
            toolBar.add(themBtn);
        toolBar.add(exportBtn);
        chiTietBtn.addActionListener(this);
        themBtn.addActionListener(this);
        exportBtn.addActionListener(this);
        pnTable.getTableHeader().setFont(new Font("Segoe UI", Font.BOLD, 12));
        tableModel = (DefaultTableModel) pnTable.getModel();
    }
    
    private void performSearchByTotalAmount() {
        String giatritu = searchBar.searchTongTien.giatritu().getText();
        String giatriden = searchBar.searchTongTien.giatriden().getText();

        int giaTriTu = 0;
        int giaTriDen = Integer.MAX_VALUE;

        try {
            if (!giatritu.isEmpty()) {
                giaTriTu = Integer.parseInt(giatritu);
            }
            if (!giatriden.isEmpty()) {
                giaTriDen = Integer.parseInt(giatriden);
            }

            loadDataToTable(pnBUS.searchByTotalAmount(giaTriTu, giaTriDen));
        } catch (NumberFormatException ex) {
            // Xử lý ngoại lệ nếu giatritu hoặc giatriden không phải là số nguyên
            System.out.println("Giá trị nhập không hợp lệ: " + ex.getMessage());
        }
    }

    private void performSearchByDateRange(Date startDate, Date endDate) {
        if (startDate == null) {
            startDate = new Date(Long.MIN_VALUE);
        }

        if (endDate == null) {
            endDate = new Date();
        }

        if (startDate.after(endDate)) {
            System.out.println("Ngày bắt đầu phải trước ngày kết thúc.");
            return;
        }

        loadDataToTable(pnBUS.searchByDateRange(startDate, endDate));
    }
    
    public void loadDataToTable(ArrayList<PhieuNhapDTO> pnList) {
        tableModel.setRowCount(0);
        for(PhieuNhapDTO i : pnList){
            tableModel.addRow(new Object[] {
                i.getId(), 
                nccBUS.getNameByID(i.getIdNhaCungCap()), 
                nvBUS.getNameByID(i.getIdNhanVien()), 
                Formatter.FormatDateTime(i.getNgayNhap()), 
                Formatter.FormatVND(i.getTongTien())});
        }
    }
    
    public void searchEvent() {
        String searchText = searchBar.txtSearch.getText();
        loadDataToTable(pnBUS.search(searchText,(String) searchBar.cbxType.getSelectedItem()));
    }
    
    public void reloadEvent() {                                       
        searchBar.txtSearch.setText("");
        loadDataToTable(phieuNhapList);
    }
    
    public int getSelectedRow() {
        int index = pnTable.getSelectedRow();
        if (index == -1) {
            JOptionPane.showMessageDialog(main, "Bạn chưa chọn phiếu nhập nào", "Lỗi", JOptionPane.ERROR_MESSAGE);
        }
        return index;
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        topPanel = new javax.swing.JPanel();
        toolBar = new javax.swing.JToolBar();
        jPanel1 = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        pnTable = new javax.swing.JTable();

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

        pnTable.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Mã", "Nhà cung cấp", "Nhân viên nhập", "Ngày và giờ nhập", "Tổng tiền"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        pnTable.setFocusable(false);
        pnTable.setRowHeight(32);
        pnTable.setSelectionBackground(new java.awt.Color(190, 215, 220));
        pnTable.setShowGrid(true);
        pnTable.getTableHeader().setResizingAllowed(false);
        pnTable.getTableHeader().setReorderingAllowed(false);
        jScrollPane1.setViewportView(pnTable);
        if (pnTable.getColumnModel().getColumnCount() > 0) {
            pnTable.getColumnModel().getColumn(0).setResizable(false);
            pnTable.getColumnModel().getColumn(0).setPreferredWidth(50);
            pnTable.getColumnModel().getColumn(1).setResizable(false);
            pnTable.getColumnModel().getColumn(1).setPreferredWidth(300);
            pnTable.getColumnModel().getColumn(2).setResizable(false);
            pnTable.getColumnModel().getColumn(2).setPreferredWidth(150);
            pnTable.getColumnModel().getColumn(3).setResizable(false);
            pnTable.getColumnModel().getColumn(3).setPreferredWidth(150);
            pnTable.getColumnModel().getColumn(4).setResizable(false);
            pnTable.getColumnModel().getColumn(4).setPreferredWidth(150);
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
    private javax.swing.JTable pnTable;
    private javax.swing.JToolBar toolBar;
    private javax.swing.JPanel topPanel;
    // End of variables declaration//GEN-END:variables

    @Override
    public void actionPerformed(ActionEvent e) {
        if(e.getSource() == chiTietBtn) {            
            int index = getSelectedRow();
            if(index != -1) {
                PhieuNhapDialog pnDialog = new PhieuNhapDialog(main, true, this, phieuNhapList.get(index), main.getCurrentUser(), "detail");
                pnDialog.setVisible(true);
                loadDataToTable(phieuNhapList);
            }
        }
        if(e.getSource() == themBtn) {
            PhieuNhapDialog pnDialog = new PhieuNhapDialog(main, true, this, null, main.getCurrentUser(), "add");
                pnDialog.setVisible(true);
                loadDataToTable(phieuNhapList);
        }
        
        if(e.getSource() == exportBtn) {
            try {
                JTableExporter.exportJTableToExcel(pnTable);
            } catch (IOException ex) {
                System.out.println(ex);
            }
        }
        
    }

}
