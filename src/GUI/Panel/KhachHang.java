/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JPanel.java to edit this template
 */
package GUI.Panel;

import GUI.Dialog.KhachHangDialog;
import BUS.ChucNangBUS;
import BUS.KhachHangBUS;
import BUS.QuyenBUS;
import DTO.CTQuyenDTO;
import DTO.ChucNangDTO;
import DTO.KhachHangDTO;
import DTO.TaiKhoanDTO;
import GUI.Component.SearchBar;
import GUI.Component.ToolBarButton;
import GUI.Main;
import com.formdev.flatlaf.extras.FlatSVGIcon;
import helper.Formatter;
import helper.JTableExporter;
import java.awt.BorderLayout;
import java.awt.Color;
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
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.border.Border;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author Admin
 */
public class KhachHang extends javax.swing.JPanel implements ActionListener {

    public KhachHangBUS khBUS = new KhachHangBUS();
    public ArrayList<KhachHangDTO> khachHangList = khBUS.getAll();
    private Main main;

    private TaiKhoanDTO taiKhoan;

    public QuyenBUS qBUS = new QuyenBUS();
    public ArrayList<CTQuyenDTO> ctqList;
    public ChucNangBUS cnBUS = new ChucNangBUS();
    public ArrayList<ChucNangDTO> cnList = cnBUS.getAll();

    private DefaultTableModel tableModel;
    public SearchBar searchBar;
    ToolBarButton chiTietBtn = new ToolBarButton("Chi tiết", "toolBar_detail.svg", "detail");
    ToolBarButton themBtn = new ToolBarButton("Thêm", "toolBar_add.svg", "add");
    ToolBarButton suaBtn = new ToolBarButton("Sửa", "toolBar_edit.svg", "edit");
    ToolBarButton xoaBtn = new ToolBarButton("Xóa", "toolBar_delete.svg", "delete");
    public ToolBarButton exportBtn = new ToolBarButton("Xuất excel", "toolBar_export.svg", "export");

    /**
     * Creates new form KhachHang
     */
    public KhachHang(Main main, TaiKhoanDTO taiKhoan) {
        this.main = main;
        this.taiKhoan = taiKhoan;
        ctqList = qBUS.getCTQListById(taiKhoan.getIdQuyen());
        initComponents();
        initComponentsCustom();
        this.main = main;
        loadDataToTable(this.khachHangList);
    }

    public void initComponentsCustom() {
        searchBar = new SearchBar(new String[] { "Tất cả", "Mã", "Tên", "Số điện thoại" });
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
            public void itemStateChanged(ItemEvent e) {
                searchEvent();
            }
        });
        topPanel.add(searchBar, BorderLayout.CENTER);
        toolBar.add(chiTietBtn);
        if (qBUS.checkQuyen(ctqList, 4, "add"))
            toolBar.add(themBtn);
        if (qBUS.checkQuyen(ctqList, 4, "edit"))
            toolBar.add(suaBtn);
//        if (qBUS.checkQuyen(ctqList, 4, "delete"))
//            toolBar.add(xoaBtn);
        chiTietBtn.addActionListener(this);
        themBtn.addActionListener(this);
        suaBtn.addActionListener(this);
        xoaBtn.addActionListener(this);
        toolBar.add(exportBtn);
        exportBtn.addActionListener(this);
        khTable.getTableHeader().setFont(new Font("Segoe UI", Font.BOLD, 12));
        tableModel = (DefaultTableModel) khTable.getModel();
    }

    public void loadDataToTable(ArrayList<KhachHangDTO> khList) {
        tableModel.setRowCount(0);
        for (KhachHangDTO i : khList) {
            tableModel.addRow(new Object[] {
                    i.getId(),
                    i.getHo(),
                    i.getTen(),
                    i.getSoDienThoai(),
                    Formatter.FormatDateTime(i.getNgayThamGia()),
            });
        }
    }

    public int getSelectedRow() {
        int index = khTable.getSelectedRow();
        if (index == -1) {
            JOptionPane.showMessageDialog(main, "Bạn chưa chọn khách hàng nào", "Lỗi", JOptionPane.ERROR_MESSAGE);
        }
        return index;
    }

    public void searchEvent() {
        String searchText = searchBar.txtSearch.getText();
        loadDataToTable(khBUS.search(searchText, (String) searchBar.cbxType.getSelectedItem()));
    }

    public void reloadEvent() {
        searchBar.txtSearch.setText("");
        loadDataToTable(khachHangList);
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated
    // Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        topPanel = new javax.swing.JPanel();
        toolBar = new javax.swing.JToolBar();
        mainPanel = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        khTable = new javax.swing.JTable();

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

        khTable.setModel(new javax.swing.table.DefaultTableModel(
                new Object[][] {

                },
                new String[] {
                        "Mã", "Họ", "Tên", "Số điện thoại", "Ngày tham gia"
                }) {
            boolean[] canEdit = new boolean[] {
                    false, false, false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit[columnIndex];
            }
        });
        khTable.setToolTipText("");
        khTable.setRowHeight(32);
        khTable.setSelectionBackground(new java.awt.Color(190, 215, 220));
        khTable.setSelectionForeground(new java.awt.Color(0, 0, 0));
        khTable.setShowGrid(true);
        khTable.getTableHeader().setReorderingAllowed(false);
        jScrollPane1.setViewportView(khTable);
        if (khTable.getColumnModel().getColumnCount() > 0) {
            khTable.getColumnModel().getColumn(0).setResizable(false);
            khTable.getColumnModel().getColumn(0).setPreferredWidth(100);
            khTable.getColumnModel().getColumn(1).setResizable(false);
            khTable.getColumnModel().getColumn(1).setPreferredWidth(300);
            khTable.getColumnModel().getColumn(2).setResizable(false);
            khTable.getColumnModel().getColumn(2).setPreferredWidth(200);
            khTable.getColumnModel().getColumn(3).setResizable(false);
            khTable.getColumnModel().getColumn(3).setPreferredWidth(300);
            khTable.getColumnModel().getColumn(4).setResizable(false);
            khTable.getColumnModel().getColumn(4).setPreferredWidth(230);
        }

        javax.swing.GroupLayout mainPanelLayout = new javax.swing.GroupLayout(mainPanel);
        mainPanel.setLayout(mainPanelLayout);
        mainPanelLayout.setHorizontalGroup(
                mainPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 1030, Short.MAX_VALUE));
        mainPanelLayout.setVerticalGroup(
                mainPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 620, Short.MAX_VALUE));

        add(mainPanel, java.awt.BorderLayout.CENTER);
    }// </editor-fold>//GEN-END:initComponents

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTable khTable;
    private javax.swing.JPanel mainPanel;
    private javax.swing.JToolBar toolBar;
    private javax.swing.JPanel topPanel;
    // End of variables declaration//GEN-END:variables

    @Override
    public void actionPerformed(ActionEvent e) {

        if (e.getSource() == chiTietBtn) {
            int index = getSelectedRow();
            if (index != -1) {
                KhachHangDialog khDialog = new KhachHangDialog(main, true, this, khachHangList.get(index), "detail");
                khDialog.setVisible(true);
                loadDataToTable(khachHangList);
            }
        }
        if (e.getSource() == themBtn) {
            KhachHangDialog khDialog = new KhachHangDialog(main, true, this, null, "add");
            khDialog.setVisible(true);
            loadDataToTable(khachHangList);
        }
        if (e.getSource() == suaBtn) {
            int index = getSelectedRow();
            if (index != -1) {
                KhachHangDialog khDialog = new KhachHangDialog(main, true, this, khachHangList.get(index), "edit");
                khDialog.setVisible(true);
                loadDataToTable(khachHangList);
            }
        }
        if (e.getSource() == xoaBtn) {
            int index = getSelectedRow();
            if (index != -1) {
                if (JOptionPane.showConfirmDialog(main, "Bạn có chắc muốn xóa khách hàng này không?", "", JOptionPane.YES_NO_OPTION) == 0)
                    if(khBUS.delete(khachHangList.get(index)))
                        JOptionPane.showMessageDialog(main, "Xóa khách hàng thành công");
                loadDataToTable(khachHangList);
            }
        }

        if (e.getSource() == exportBtn) {
            try {
                JTableExporter.exportJTableToExcel(khTable);
            } catch (IOException ex) {
                System.out.println(ex);
            }
        }

    }

}
