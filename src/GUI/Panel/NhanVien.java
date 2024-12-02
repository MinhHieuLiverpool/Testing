/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JPanel.java to edit this template
 */
package GUI.Panel;

import BUS.ChucNangBUS;
import BUS.NhanVienBUS;
import BUS.QuyenBUS;
import DAO.TaiKhoanDAO;
import DTO.CTQuyenDTO;
import DTO.ChucNangDTO;
import DTO.NhanVienDTO;
import DTO.TaiKhoanDTO;
import GUI.Component.SearchBar;
import GUI.Component.ToolBarButton;
import GUI.Dialog.NhanVienDialog;
import GUI.Main;
import com.formdev.flatlaf.extras.FlatSVGIcon;
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
public class NhanVien extends javax.swing.JPanel implements ActionListener {

    public NhanVienBUS nvBUS = new NhanVienBUS();
    public ArrayList<NhanVienDTO> nhanVienList = nvBUS.getAll();
    private Main main;
    
    private TaiKhoanDTO taiKhoan;
    
    public QuyenBUS qBUS = new QuyenBUS();
    public ArrayList<CTQuyenDTO> ctqList;
    public ChucNangBUS cnBUS = new ChucNangBUS();
    public ArrayList<ChucNangDTO> cnList = cnBUS.getAll();
    
    public DefaultTableModel tableModel;
    public SearchBar searchBar;
    ToolBarButton chiTietBtn = new ToolBarButton("Chi tiết", "toolBar_detail.svg", "detail");
    ToolBarButton themBtn = new ToolBarButton("Thêm", "toolBar_add.svg", "add");
    ToolBarButton suaBtn = new ToolBarButton("Sửa", "toolBar_edit.svg", "edit");
    ToolBarButton xoaBtn = new ToolBarButton("Cho nghỉ việc", "toolBar_delete.svg", "delete");
    public ToolBarButton exportBtn = new ToolBarButton("Xuất excel", "toolBar_export.svg", "export");
    /**
     * Creates new form NhanVien
     */
    public NhanVien(Main main, TaiKhoanDTO taiKhoan) {
        this.main = main;
        this.taiKhoan = taiKhoan;
        ctqList = qBUS.getCTQListById(taiKhoan.getIdQuyen());
        initComponents();
        initComponentsCustom();
        loadDataToTable(this.nhanVienList);
    }
    
    public void initComponentsCustom() {
        searchBar = new SearchBar(new String[]{"Tất cả", "Mã", "Tên", "Giới tính", "Số điện thoại", "Email"});
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
                searchEvent();;
            }
        });
        topPanel.add(searchBar, BorderLayout.CENTER);
        toolBar.add(chiTietBtn);
        if(qBUS.checkQuyen(ctqList, 5, "add"))
            toolBar.add(themBtn);
        if(qBUS.checkQuyen(ctqList, 5, "edit"))
            toolBar.add(suaBtn);
        if(qBUS.checkQuyen(ctqList, 5, "delete"))
            toolBar.add(xoaBtn);
        chiTietBtn.addActionListener(this);
        themBtn.addActionListener(this);
        suaBtn.addActionListener(this);
        xoaBtn.addActionListener(this);
        toolBar.add(exportBtn);
        exportBtn.addActionListener(this);
        nvTable.getTableHeader().setFont(new Font("Segoe UI", Font.BOLD, 12));
        tableModel = (DefaultTableModel) nvTable.getModel();
    }
    
    public void loadDataToTable(ArrayList<NhanVienDTO> nvList){
//        nvList = nvBUS.getAll();
        tableModel.setRowCount(0);
        for(NhanVienDTO i : nvList){
            tableModel.addRow(new Object[] {
                i.getId(),
                i.getHo(),
                i.getTen(),
                i.getGioiTinh(),
                i.getSoDienThoai(),
                i.getEmail(),
                (i.getTrangThai() == 1?"Đang làm việc":"Đã nghỉ việc")});
        }
    }
    
    public int getSelectedRow() {
        int index = nvTable.getSelectedRow();
        if (index == -1) {
            JOptionPane.showMessageDialog(main, "Bạn chưa chọn nhân viên nào", "Lỗi", JOptionPane.ERROR_MESSAGE);
        }
        return index;
    }
    
    public void searchEvent() {
        String searchText = searchBar.txtSearch.getText();
        loadDataToTable(nvBUS.search(searchText, (String) searchBar.cbxType.getSelectedItem()));
    }
    
    public void reloadEvent() {
        searchBar.txtSearch.setText("");
        loadDataToTable(nhanVienList);
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
        nvTable = new javax.swing.JTable();

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

        nvTable.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Mã", "Họ", "Tên", "Giới tính", "Số điện thoại", "Email", "Trạng thái"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false, false, false
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
            nvTable.getColumnModel().getColumn(0).setPreferredWidth(100);
            nvTable.getColumnModel().getColumn(1).setResizable(false);
            nvTable.getColumnModel().getColumn(1).setPreferredWidth(160);
            nvTable.getColumnModel().getColumn(2).setResizable(false);
            nvTable.getColumnModel().getColumn(2).setPreferredWidth(100);
            nvTable.getColumnModel().getColumn(3).setResizable(false);
            nvTable.getColumnModel().getColumn(3).setPreferredWidth(100);
            nvTable.getColumnModel().getColumn(4).setResizable(false);
            nvTable.getColumnModel().getColumn(4).setPreferredWidth(120);
            nvTable.getColumnModel().getColumn(5).setResizable(false);
            nvTable.getColumnModel().getColumn(5).setPreferredWidth(200);
            nvTable.getColumnModel().getColumn(6).setResizable(false);
            nvTable.getColumnModel().getColumn(6).setPreferredWidth(100);
        }

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 1030, Short.MAX_VALUE)
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jScrollPane1, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 418, Short.MAX_VALUE)
        );

        add(jPanel2, java.awt.BorderLayout.CENTER);
    }// </editor-fold>//GEN-END:initComponents


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JPanel jPanel2;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTable nvTable;
    private javax.swing.JToolBar toolBar;
    private javax.swing.JPanel topPanel;
    // End of variables declaration//GEN-END:variables

    @Override
    public void actionPerformed(ActionEvent e) {
        
        if(e.getSource() == chiTietBtn) {            
            int index = getSelectedRow();
            if(index != -1) {
                NhanVienDialog nvDialog = new NhanVienDialog(main, true, this, nhanVienList.get(index), "detail");
                nvDialog.setVisible(true);
                loadDataToTable(nhanVienList);
            }
        }
        if(e.getSource() == themBtn) {
            NhanVienDialog nvDialog = new NhanVienDialog(main, true, this, null, "add");
            nvDialog.setVisible(true);
            loadDataToTable(nhanVienList);
        }
        if(e.getSource() == suaBtn) {            
            int index = getSelectedRow();
            if(index != -1) {
                NhanVienDialog nvDialog = new NhanVienDialog(main, true, this, nhanVienList.get(index), "edit");
                nvDialog.setVisible(true);
                loadDataToTable(nhanVienList);
            }
        }
        if(e.getSource() == xoaBtn) {            
            int index = getSelectedRow();
            int id = Integer.parseInt(nvTable.getValueAt(index, 0).toString());
            if(index != -1) {
                if(JOptionPane.showConfirmDialog(main, "Bạn có chắc muốn cho nhân viên này nghỉ việc không?", "", JOptionPane.YES_NO_OPTION) == 0) {
                    nvBUS.delete(nhanVienList.get(nvBUS.getIndexByID(id)));
                    main.taiKhoan.tkList = TaiKhoanDAO.getInstance().selectAll();
                    main.taiKhoan.loadDataToTable(main.taiKhoan.tkList);
                }
                loadDataToTable(nhanVienList);
                JOptionPane.showMessageDialog(main, "Thôi việc nhân viên thành công");
            }
        }
        
        if(e.getSource() == exportBtn) {
            try {
                JTableExporter.exportJTableToExcel(nvTable);
            } catch (IOException ex) {
                System.out.println(ex);
            }
        }
        
    }
    
}
