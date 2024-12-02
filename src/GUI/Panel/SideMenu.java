/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JPanel.java to edit this template
 */
package GUI.Panel;

import BUS.ChucNangBUS;
import BUS.QuyenBUS;
import DAO.NhanVienDAO;
import DTO.CTQuyenDTO;
import DTO.ChucNangDTO;
import DTO.NhanVienDTO;
import DTO.TaiKhoanDTO;
import GUI.Component.SideMenuItem;
import GUI.Dialog.DoiTaiKhoanDialog;
import GUI.Login;
import GUI.Main;
import com.formdev.flatlaf.extras.FlatSVGIcon;
import java.awt.Color;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;
import javax.swing.JOptionPane;

/**
 *
 * @author Admin
 */
public class SideMenu extends javax.swing.JPanel {

    Main main;
    TaiKhoanDTO taiKhoan;
    String[][] menuSt = {
        {"Trang chủ", "home", "sideMenu_home.svg"},
        {"Sản phẩm", "sanPham", "sideMenu_sanPham.svg"},
        {"Phiếu nhập", "phieuNhap", "sideMenu_phieuNhap.svg"},
        {"Hóa đơn", "hoaDon", "sideMenu_hoaDon.svg"},
        {"Khách hàng", "khachHang", "sideMenu_khachHang.svg"},
        {"Nhân viên", "nhanVien", "sideMenu_nhanVien.svg"},
        {"Nhà cung cấp", "nhaCungCap", "sideMenu_nhaCungCap.svg"},
        {"Phân quyền", "phanQuyen", "sideMenu_phanQuyen.svg"},
        {"Thống kê", "thongKe", "sideMenu_thongKe.svg"},
        {"Tài khoản", "taiKhoan", "sideMenu_taiKhoan.svg"},
        {"Thêm", "them", "sideMenu_them.svg"}
    };
    public SideMenuItem menuItems[];
    Color itemBgColor = new Color(255, 255, 255);
    Color itemFontColor = new Color(0, 0, 0);
    Color selectedItemBgColor = new Color(190, 215, 220);
    Color selectedItemFontColor = new Color(0, 0, 0);
    
    public QuyenBUS qBUS = new QuyenBUS();
    public ArrayList<CTQuyenDTO> ctqList;
    public ChucNangBUS cnBUS = new ChucNangBUS();
    public ArrayList<ChucNangDTO> cnList = cnBUS.getAll();
    
    /**
     * Creates new form MenuBar
     */
    public SideMenu() {
        initComponents();
    }

    public SideMenu(Main main, TaiKhoanDTO taiKhoan) {
        initComponents();
        userIcon.setIcon(new FlatSVGIcon("./image/icon/sideMenu_user.svg"));
        this.main = main;
        this.taiKhoan = taiKhoan;
        ctqList = qBUS.getCTQListById(this.taiKhoan.getIdQuyen());
        menuItems = new SideMenuItem[menuSt.length];
        for(int i=0; i<menuSt.length; i++) {
            menuItems[i] = new SideMenuItem(main, menuSt[i][0], menuSt[i][1], menuSt[i][2]);
            menuItems[i].addMouseListener(new MouseAdapter() {
                @Override
                public void mousePressed(MouseEvent evt) {
                    selectingMenuItem(evt);
                }
            });
            if(i == 0) {
                centerPanel.add(menuItems[i]);
                continue;
            }
            if(i==10)
                continue;
            if(qBUS.checkChucNang(ctqList, cnList.get(i-1).getId()))
            centerPanel.add(menuItems[i]);
        }
        menuItems[0].isSelected = true;
        menuItems[0].setBackground(selectedItemBgColor);
        menuItems[0].text.setForeground(selectedItemFontColor);
        
        FlatSVGIcon dangXuatBG = new FlatSVGIcon("./image/icon/sideMenu_logout.svg");
        FlatSVGIcon dangXuatHoverBG = new FlatSVGIcon("./image/icon/sideMenu_logouthover.svg");
        dangXuatBtn.setIcon(dangXuatBG);
        dangXuatBtn.addMouseListener(new MouseAdapter(){
            @Override
            public void mouseEntered(MouseEvent e) {
                dangXuatBtn.setIcon(dangXuatHoverBG);
                dangXuatBtn.setBackground(new Color(240, 40, 37));
                dangXuatBtn.setForeground(new Color(255, 255, 255));
            }
            public void mouseExited(MouseEvent e) {
                dangXuatBtn.setIcon(dangXuatBG);
                dangXuatBtn.setBackground(new Color(255, 255, 255));
                dangXuatBtn.setForeground(new Color(0, 0, 0));
            }
            public void mousePressed(MouseEvent e) {
                int confirm = JOptionPane.showConfirmDialog(main, "Bạn có chắc muốn đăng xuất không?", "Đăng xuất", JOptionPane.YES_NO_OPTION);
                if(confirm == 0) {
                    main.dispose();
                    new Login().setVisible(true);
                }
            }
        });
        
        NhanVienDTO currentNhanVien = NhanVienDAO.getInstance().selectByAccountId(Integer.toString(taiKhoan.getId()));
        userName.setText(currentNhanVien.getHo()+" "+currentNhanVien.getTen());
        userRole.setText(qBUS.getNameById(taiKhoan.getIdQuyen()));
    }
        
    public void selectingMenuItem(MouseEvent evt) {
        for (int i = 0; i < menuSt.length; i++) {
            if (evt.getSource() == menuItems[i]) {
                menuItems[i].isSelected = true;
                menuItems[i].setBackground(selectedItemBgColor);
                menuItems[i].text.setForeground(selectedItemFontColor);
            } else {
                menuItems[i].isSelected = false;
                menuItems[i].setBackground(itemBgColor);
                menuItems[i].text.setForeground(itemFontColor);
            }
        }
    }

    public void updateTaiKhoanEvent() {
        DoiTaiKhoanDialog dtkDialog = new DoiTaiKhoanDialog(main, true, taiKhoan);
        dtkDialog.setVisible(true);
    }
    
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        topPanel = new javax.swing.JPanel();
        userIcon = new javax.swing.JLabel();
        userName = new javax.swing.JLabel();
        userRole = new javax.swing.JLabel();
        centerPanel = new javax.swing.JPanel();
        bottomPanel = new javax.swing.JPanel();
        dangXuatBtn = new javax.swing.JButton();

        setBackground(new java.awt.Color(255, 255, 255));
        setBorder(javax.swing.BorderFactory.createMatteBorder(0, 0, 0, 1, new java.awt.Color(0, 0, 0)));
        setPreferredSize(new java.awt.Dimension(250, 720));
        setLayout(new java.awt.BorderLayout());

        topPanel.setBackground(new java.awt.Color(251, 168, 52));
        topPanel.setBorder(javax.swing.BorderFactory.createMatteBorder(0, 0, 1, 0, new java.awt.Color(0, 0, 0)));
        topPanel.setForeground(new java.awt.Color(255, 255, 255));
        topPanel.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        topPanel.setPreferredSize(new java.awt.Dimension(250, 70));
        topPanel.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                topPanelMousePressed(evt);
            }
        });

        userIcon.setPreferredSize(new java.awt.Dimension(44, 44));
        userIcon.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                userIconMousePressed(evt);
            }
        });

        userName.setFont(new java.awt.Font("Segoe UI", 1, 16)); // NOI18N
        userName.setForeground(new java.awt.Color(255, 255, 255));
        userName.setText("Tên nhân viên");
        userName.setVerticalAlignment(javax.swing.SwingConstants.BOTTOM);
        userName.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                userNameMousePressed(evt);
            }
        });

        userRole.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        userRole.setForeground(new java.awt.Color(255, 255, 255));
        userRole.setText("Chức vụ");
        userRole.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                userRoleMousePressed(evt);
            }
        });

        javax.swing.GroupLayout topPanelLayout = new javax.swing.GroupLayout(topPanel);
        topPanel.setLayout(topPanelLayout);
        topPanelLayout.setHorizontalGroup(
            topPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(topPanelLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(userIcon, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, 0)
                .addGroup(topPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(userName)
                    .addComponent(userRole))
                .addContainerGap(85, Short.MAX_VALUE))
        );
        topPanelLayout.setVerticalGroup(
            topPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(topPanelLayout.createSequentialGroup()
                .addGap(16, 16, 16)
                .addGroup(topPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(userIcon, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(topPanelLayout.createSequentialGroup()
                        .addGap(21, 21, 21)
                        .addComponent(userRole))
                    .addComponent(userName))
                .addContainerGap())
        );

        add(topPanel, java.awt.BorderLayout.PAGE_START);

        centerPanel.setBackground(new java.awt.Color(255, 255, 255));
        centerPanel.setLayout(new java.awt.FlowLayout(java.awt.FlowLayout.CENTER, 0, 0));
        add(centerPanel, java.awt.BorderLayout.CENTER);

        bottomPanel.setBackground(new java.awt.Color(255, 255, 255));
        bottomPanel.setBorder(javax.swing.BorderFactory.createMatteBorder(1, 0, 0, 0, new java.awt.Color(0, 0, 0)));
        bottomPanel.setPreferredSize(new java.awt.Dimension(250, 60));

        dangXuatBtn.setBackground(new java.awt.Color(255, 255, 255));
        dangXuatBtn.setFont(new java.awt.Font("Segoe UI", 1, 16)); // NOI18N
        dangXuatBtn.setText("Đăng xuất");
        dangXuatBtn.setAlignmentY(0.0F);
        dangXuatBtn.setBorder(null);
        dangXuatBtn.setMargin(new java.awt.Insets(2, 0, 2, 14));
        dangXuatBtn.setPreferredSize(new java.awt.Dimension(250, 60));

        javax.swing.GroupLayout bottomPanelLayout = new javax.swing.GroupLayout(bottomPanel);
        bottomPanel.setLayout(bottomPanelLayout);
        bottomPanelLayout.setHorizontalGroup(
            bottomPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(dangXuatBtn, javax.swing.GroupLayout.DEFAULT_SIZE, 249, Short.MAX_VALUE)
        );
        bottomPanelLayout.setVerticalGroup(
            bottomPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(dangXuatBtn, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 59, Short.MAX_VALUE)
        );

        add(bottomPanel, java.awt.BorderLayout.PAGE_END);
    }// </editor-fold>//GEN-END:initComponents

    private void topPanelMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_topPanelMousePressed
        updateTaiKhoanEvent();
    }//GEN-LAST:event_topPanelMousePressed

    private void userIconMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_userIconMousePressed
        updateTaiKhoanEvent();
    }//GEN-LAST:event_userIconMousePressed

    private void userNameMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_userNameMousePressed
        updateTaiKhoanEvent();
    }//GEN-LAST:event_userNameMousePressed

    private void userRoleMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_userRoleMousePressed
        updateTaiKhoanEvent();
    }//GEN-LAST:event_userRoleMousePressed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JPanel bottomPanel;
    private javax.swing.JPanel centerPanel;
    private javax.swing.JButton dangXuatBtn;
    private javax.swing.JPanel topPanel;
    private javax.swing.JLabel userIcon;
    public javax.swing.JLabel userName;
    public javax.swing.JLabel userRole;
    // End of variables declaration//GEN-END:variables
}
