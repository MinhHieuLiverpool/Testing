/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JPanel.java to edit this template
 */
package GUI.Panel;

import BUS.CTSanPhamBUS;
import BUS.ChucNangBUS;
import GUI.Component.ToolBarButton;
import GUI.Main;
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
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;
import GUI.Dialog.HoaDonDialog;
import BUS.HoaDonBUS;
import BUS.KhachHangBUS;
import BUS.KhuyenMaiBUS;
import BUS.NhanVienBUS;
import BUS.QuyenBUS;
import DAO.CTBaoHanhDAO;
import DAO.CTHoaDonDAO;
import DAO.CTSanPhamDAO;
import DAO.PhienBanSanPhamDAO;
import DTO.CTQuyenDTO;
import DTO.CTSanPhamDTO;
import DTO.ChucNangDTO;
import DTO.HoaDonDTO;
import DTO.TaiKhoanDTO;
import GUI.Component.SearchBar1;
import java.util.ArrayList;
import helper.Formatter;
import helper.JTableExporter;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.io.IOException;
import java.util.Date;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;

/**
 *
 * @author Admin
 */
public class HoaDon extends javax.swing.JPanel implements ActionListener {

    private DefaultTableModel tableModel;
    public HoaDonBUS hdBUS = new HoaDonBUS();
    public KhachHangBUS khBUS = new KhachHangBUS();
    public NhanVienBUS nvBUS = new NhanVienBUS();
    public KhuyenMaiBUS kmBUS = new KhuyenMaiBUS();
    public CTHoaDonDAO cthdDAO = new CTHoaDonDAO();
    public CTSanPhamDAO ctspDAO = new CTSanPhamDAO();
    public CTSanPhamBUS ctspBUS = new CTSanPhamBUS();
    public CTBaoHanhDAO ctbhDAO = new CTBaoHanhDAO();
    public PhienBanSanPhamDAO pbspDAO = new PhienBanSanPhamDAO();
    public ArrayList<HoaDonDTO> hoaDonList = hdBUS.getAll();
    public Main main;
    public SearchBar1 searchBar;
    ToolBarButton chiTietBtn = new ToolBarButton("Chi tiết", "toolBar_detail.svg", "detail");
    ToolBarButton themBtn = new ToolBarButton("Thêm", "toolBar_add.svg", "add");
    ToolBarButton xoaBtn = new ToolBarButton("Hủy", "toolBar_delete.svg", "delete");
    public ToolBarButton exportBtn = new ToolBarButton("Xuất excel", "toolBar_export.svg", "export");
    
    private TaiKhoanDTO taiKhoan;
    
    public QuyenBUS qBUS = new QuyenBUS();
    public ArrayList<CTQuyenDTO> ctqList;
    public ChucNangBUS cnBUS = new ChucNangBUS();
    public ArrayList<ChucNangDTO> cnList = cnBUS.getAll();
    

    /**
     * Creates new form PhieuXuat
     */
    public HoaDon(Main main, TaiKhoanDTO taiKhoan) {
        this.main = main;
        this.taiKhoan = taiKhoan;
        ctqList = qBUS.getCTQListById(taiKhoan.getIdQuyen());
        initComponents();
        initComponentsCustom();
        loadDataToTable(hoaDonList);
    }

    public void initComponentsCustom() {
        searchBar = new SearchBar1(new String[]{"Tất cả", "Mã", "Khách hàng", "Nhân viên", "Khuyến mãi", "Tổng tiền", "Ngày xuất"});
        searchBar.txtSearch.addKeyListener(new KeyAdapter() {
            @Override
            public void keyReleased(KeyEvent e) {
                searchEvent();
            }
        });
        searchBar.lamMoiBtn.addMouseListener(new MouseAdapter() {
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
                    } else if (selectedItem.equals("Ngày xuất")) {
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
        if(qBUS.checkQuyen(ctqList, 3, "add"))
            toolBar.add(themBtn);
        if(qBUS.checkQuyen(ctqList, 3, "delete"))
            toolBar.add(xoaBtn);
        chiTietBtn.addActionListener(this);
        themBtn.addActionListener(this);
        xoaBtn.addActionListener(this);
        toolBar.add(exportBtn);
        exportBtn.addActionListener(this);
        hdTable.getTableHeader().setFont(new Font("Segoe UI", Font.BOLD, 12));
        tableModel = (DefaultTableModel) hdTable.getModel();
        xoaBtn.setVisible(false);
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

            loadDataToTable(hdBUS.searchByTotalAmount(giaTriTu, giaTriDen));
        } catch (NumberFormatException ex) {
            // Xử lý ngoại lệ nếu giatritu hoặc giatriden không phải là số nguyên
//            System.out.println("Giá trị nhập không hợp lệ: " + ex.getMessage());
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
//            System.out.println("Ngày bắt đầu phải trước ngày kết thúc.");
            return;
        }

        loadDataToTable(hdBUS.searchByDateRange(startDate, endDate));
    }

    public void loadDataToTable(ArrayList<HoaDonDTO> hdList) {
        tableModel.setRowCount(0);
        for (HoaDonDTO i : hdList) {
            tableModel.addRow(new Object[]{
                i.getId(),
                khBUS.getNameById(i.getIdKhachHang()),
                nvBUS.getNameByID(i.getIdNhanVien()),
                kmBUS.getObjectById(i.getIdKhuyenMai()).getTen(),
                Formatter.FormatVND(i.getTongTien()),
                Formatter.FormatDateTime(i.getNgayXuat())
            });
        }
    }
    
    public void reloadEvent() {
        searchBar.txtSearch.setText("");
        loadDataToTable(hoaDonList);
    }
    
    public void searchEvent(){
        String searchText = searchBar.txtSearch.getText();
        loadDataToTable(hdBUS.search(searchText,(String) searchBar.cbxType.getSelectedItem()));
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
        hdTable = new javax.swing.JTable();

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

        hdTable.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Mã", "Khách hàng", "Nhân viên", "Khuyến mãi", "Tổng tiền", "Ngày xuất"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        hdTable.setRowHeight(32);
        hdTable.setSelectionBackground(new java.awt.Color(190, 215, 220));
        hdTable.setSelectionForeground(new java.awt.Color(0, 0, 0));
        hdTable.setShowGrid(true);
        hdTable.getTableHeader().setResizingAllowed(false);
        hdTable.getTableHeader().setReorderingAllowed(false);
        jScrollPane1.setViewportView(hdTable);
        if (hdTable.getColumnModel().getColumnCount() > 0) {
            hdTable.getColumnModel().getColumn(0).setResizable(false);
            hdTable.getColumnModel().getColumn(1).setResizable(false);
            hdTable.getColumnModel().getColumn(2).setResizable(false);
            hdTable.getColumnModel().getColumn(3).setResizable(false);
            hdTable.getColumnModel().getColumn(4).setResizable(false);
            hdTable.getColumnModel().getColumn(5).setResizable(false);
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

    public int getSelectedRow() {
        int index = hdTable.getSelectedRow();
        if (index == -1) {
            JOptionPane.showMessageDialog(main, "Bạn chưa chọn hóa đơn nào", "Lỗi", JOptionPane.ERROR_MESSAGE);
        }
        return index;
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JTable hdTable;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JToolBar toolBar;
    private javax.swing.JPanel topPanel;
    // End of variables declaration//GEN-END:variables

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == chiTietBtn) {
            int index = getSelectedRow();
            if (index != -1) {
                HoaDonDialog hdDialog = new HoaDonDialog(main, true, this, hoaDonList.get(index), main.getCurrentUser(), "detail");
                hdDialog.setVisible(true);
            }
        }
        if (e.getSource() == themBtn) {
            HoaDonDialog hdDialog = new HoaDonDialog(main, true, this, null, main.getCurrentUser(), "add");
            hdDialog.setVisible(true);
            loadDataToTable(hoaDonList);
        }
        if (e.getSource() == xoaBtn) {
            int index = getSelectedRow();
            if (index != -1) {
                if(JOptionPane.showConfirmDialog(main, "Hóa đơn bị hủy sẽ không thể phục hồi, bạn có chắc chắn muốn hủy hóa đơn không?", "", JOptionPane.YES_NO_OPTION) == 0) {
                    int hdId = (int) hdTable.getValueAt(index, 0);
                    HoaDonDTO hd = hdBUS.getByID(hdId);
                    ArrayList<CTSanPhamDTO> ctspList = ctspBUS.getAll();          
                    for(CTSanPhamDTO i : ctspList) {
                        pbspDAO.tangSoLuong(i.getIdPBSanPham(), 1);
                    }
                    if(hdBUS.delete(hd)){
                        JOptionPane.showMessageDialog(main, "Hủy hóa đơn thành công");
                        loadDataToTable(hoaDonList);
                    }
                }
            }
        }
        
        if(e.getSource() == exportBtn) {
            try {
                JTableExporter.exportJTableToExcel(hdTable);
            } catch (IOException ex) {
                System.out.println(ex);
            }
        }
        
    }

}
