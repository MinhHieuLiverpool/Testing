/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JPanel.java to edit this template
 */
package GUI.Panel;

import BUS.ChucNangBUS;
import BUS.PhienBanSanPhamBUS;
import BUS.QuyenBUS;
import BUS.SanPhamBUS;
import BUS.ThuongHieuBUS;
import DTO.CTQuyenDTO;
import DTO.ChucNangDTO;
import DTO.SanPhamDTO;
import DTO.TaiKhoanDTO;
import GUI.Component.SearchBar;
import GUI.Component.ToolBarButton;
import GUI.Dialog.SanPhamDialog;
import GUI.Dialog.ThemSuaSanPhamDialog;
import GUI.Main;
import com.formdev.flatlaf.extras.FlatSVGIcon;
import helper.JTableExporter;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Cursor;
import java.awt.Font;
import java.awt.Image;
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
import javax.swing.ImageIcon;
import javax.swing.JDialog;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author Admin
 */
public class SanPham extends javax.swing.JPanel implements ActionListener {
    
    public SanPhamBUS spBUS = new SanPhamBUS();
    public ThuongHieuBUS thBUS = new ThuongHieuBUS();
    public PhienBanSanPhamBUS pbspBUS = new PhienBanSanPhamBUS();
    public ArrayList<SanPhamDTO> sanPhamList = spBUS.getAll();
    private Main main;
    private TaiKhoanDTO taiKhoan;
    
    public QuyenBUS qBUS = new QuyenBUS();
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
    /**
     * Creates new form SanPham
     */
    public SanPham(Main main, TaiKhoanDTO taiKhoan) {
        this.main = main;
        this.taiKhoan = taiKhoan;
        ctqList = qBUS.getCTQListById(taiKhoan.getIdQuyen());
        initComponents();
        initComponentsCustom();
        loadDataToTable(sanPhamList);
    }
    
    public void initComponentsCustom() {
        searchBar = new SearchBar(new String[]{"Tất cả", "Mã", "Tên", "Kích thước màn hình", "Camera sau", "Camera trước", "Chip xử lý", "Hệ điều hành", "Dung lượng pin", "Thương hiệu"});
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
        if(qBUS.checkQuyen(ctqList, 1, "add"))
            toolBar.add(themBtn);
        if(qBUS.checkQuyen(ctqList, 1, "edit"))
            toolBar.add(suaBtn);
//        if(qBUS.checkQuyen(ctqList, 1, "delete"))
//            toolBar.add(xoaBtn);
        toolBar.add(exportBtn);
        chiTietBtn.addActionListener(this);
        themBtn.addActionListener(this);
        suaBtn.addActionListener(this);
        xoaBtn.addActionListener(this);
        exportBtn.addActionListener(this);
        spTable.getTableHeader().setFont(new Font("Segoe UI", Font.BOLD, 12));
        tableModel = (DefaultTableModel) spTable.getModel(); 
    }
    
    public void loadDataToTable(ArrayList<SanPhamDTO> spList) {
        tableModel.setRowCount(0);
        for(SanPhamDTO i : spList) {
            tableModel.addRow(new Object[]{i.getId(), i.getTen(), i.getKichThuocMan()+"\"", i.getCameraSau(), i.getCameraTruoc(), i.getChipXuLy(), i.getHeDieuHanh(), i.getDungLuongPin()+" mAh", thBUS.getNameByID(i.getIdThuongHieu())});
        }
    }
        
    private void searchEvent() {                                      
        String searchText = searchBar.txtSearch.getText();
        loadDataToTable(spBUS.search(searchText, (String) searchBar.cbxType.getSelectedItem()));
    }
    
    public void reloadEvent() {
        searchBar.txtSearch.setText("");
        loadDataToTable(sanPhamList);
    }
    
    public int getSelectedRow() {
        int index = spTable.getSelectedRow();
        if (index == -1) {
            JOptionPane.showMessageDialog(main, "Bạn chưa chọn sản phẩm nào", "Lỗi", JOptionPane.ERROR_MESSAGE);
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
        jPanel2 = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        spTable = new javax.swing.JTable();

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

        jPanel2.setPreferredSize(new java.awt.Dimension(1030, 620));

        spTable.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Mã", "Tên", "Màn hình", "Camera sau", "Camera trước", "Chip xử lý", "Hệ điều hành", "Pin", "Thương hiệu"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false, false, false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        spTable.setRowHeight(32);
        spTable.setSelectionBackground(new java.awt.Color(190, 215, 220));
        spTable.setSelectionForeground(new java.awt.Color(0, 0, 0));
        spTable.setSelectionMode(javax.swing.ListSelectionModel.SINGLE_SELECTION);
        spTable.setShowGrid(true);
        spTable.getTableHeader().setResizingAllowed(false);
        spTable.getTableHeader().setReorderingAllowed(false);
        jScrollPane1.setViewportView(spTable);
        if (spTable.getColumnModel().getColumnCount() > 0) {
            spTable.getColumnModel().getColumn(0).setResizable(false);
            spTable.getColumnModel().getColumn(0).setPreferredWidth(5);
            spTable.getColumnModel().getColumn(1).setResizable(false);
            spTable.getColumnModel().getColumn(1).setPreferredWidth(120);
            spTable.getColumnModel().getColumn(2).setResizable(false);
            spTable.getColumnModel().getColumn(2).setPreferredWidth(40);
            spTable.getColumnModel().getColumn(3).setResizable(false);
            spTable.getColumnModel().getColumn(4).setResizable(false);
            spTable.getColumnModel().getColumn(4).setPreferredWidth(40);
            spTable.getColumnModel().getColumn(5).setResizable(false);
            spTable.getColumnModel().getColumn(6).setResizable(false);
            spTable.getColumnModel().getColumn(6).setPreferredWidth(40);
            spTable.getColumnModel().getColumn(7).setResizable(false);
            spTable.getColumnModel().getColumn(7).setPreferredWidth(40);
            spTable.getColumnModel().getColumn(8).setResizable(false);
            spTable.getColumnModel().getColumn(8).setPreferredWidth(50);
        }

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 933, Short.MAX_VALUE)
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 444, Short.MAX_VALUE)
        );

        add(jPanel2, java.awt.BorderLayout.CENTER);
    }// </editor-fold>//GEN-END:initComponents
    

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JPanel jPanel2;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTable spTable;
    private javax.swing.JToolBar toolBar;
    private javax.swing.JPanel topPanel;
    // End of variables declaration//GEN-END:variables

    @Override
    public void actionPerformed(ActionEvent e) {
        if(e.getSource() == chiTietBtn) {
            int index = getSelectedRow();
            if (index != -1) {
                SanPhamDialog spDialog = new SanPhamDialog(main, true, "Chi tiết sản phẩm", sanPhamList.get(index));
                spDialog.setVisible(true);
                loadDataToTable(sanPhamList);
            }
        }
        
        if(e.getSource() == themBtn) {
            ThemSuaSanPhamDialog addSpDialog = new ThemSuaSanPhamDialog(main, true, "Thêm sản phẩm", this, "add", null, null);
            addSpDialog.setVisible(true);
            sanPhamList = spBUS.getAll();
            loadDataToTable(sanPhamList);
        }
        
        if(e.getSource() == xoaBtn) {
            int index = getSelectedRow();
            if (index != -1) {
                if (JOptionPane.showConfirmDialog(main, "Bạn có chắc muốn xóa sản phẩm này không?", "", JOptionPane.YES_NO_OPTION) == 0) {
                    spBUS.delete(sanPhamList.get(index));
                }
                loadDataToTable(sanPhamList);
            }
            
        }
        
        if(e.getSource() == suaBtn) {
            int index = getSelectedRow();
            if(index != -1) {
                ThemSuaSanPhamDialog editSpDialog = new ThemSuaSanPhamDialog(main, true, "Thêm sản phẩm", this, "edit", sanPhamList.get(index), pbspBUS.getAllPBSPBySPId(sanPhamList.get(index).getId()));
                editSpDialog.setVisible(true);
                sanPhamList = spBUS.getAll();
                loadDataToTable(sanPhamList);
            }
        }
        
        if(e.getSource() == exportBtn) {
            try {
                JTableExporter.exportJTableToExcel(spTable);
            } catch (IOException ex) {
                System.out.println(ex);
            }
        }
        
    }
    
}
