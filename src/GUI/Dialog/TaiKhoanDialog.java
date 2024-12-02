/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JDialog.java to edit this template
 */
package GUI.Dialog;

import BUS.NhanVienBUS;
import BUS.QuyenBUS;
import BUS.TaiKhoanBUS;
import DAO.TaiKhoanDAO;
import DTO.NhanVienDTO;
import DTO.QuyenDTO;
import DTO.TaiKhoanDTO;
import GUI.Panel.TaiKhoan;
import helper.Validator;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.ArrayList;
import javax.swing.JOptionPane;

/**
 *
 * @author DVN
 */
public class TaiKhoanDialog extends javax.swing.JDialog {
    
    private NhanVienBUS nvBUS = new NhanVienBUS();
    private NhanVienDTO nhanVien;
    private QuyenBUS qBUS = new QuyenBUS();
    private ArrayList<QuyenDTO> quyenList = qBUS.getAll();
    private QuyenDTO quyen;
    private TaiKhoanDAO tkDAO = new TaiKhoanDAO();
    private TaiKhoanBUS tkBUS = new TaiKhoanBUS();
    private TaiKhoanDTO taiKhoan;
    private TaiKhoanDTO newTaiKhoan;
    private String mode;
    private TaiKhoan tkPanel;
    /**
     * Creates new form TaiKhoanDialog
     */
    public TaiKhoanDialog(java.awt.Frame parent, boolean modal, TaiKhoan tkPanel, TaiKhoanDTO taiKhoan, NhanVienDTO nhanVien, String mode) {
        super(parent, modal);
        this.tkPanel = tkPanel;
        this.taiKhoan = taiKhoan;
        this.mode = mode;
        this.nhanVien = nhanVien;
        initComponents();
        initComponentsCustom();
    }
    
    public void initComponentsCustom(){
        this.setLocationRelativeTo(null);
        if(mode.equals("detail"))
            initDetailMode();
        if(mode.equals("add"))
            initAddMode();
        if(mode.equals("edit"))
            initEditMode();
    }
    
    public void initDetailMode(){
        header.setText("Chi tiết tài khoản");
        addTaiKhoanBtn.setVisible(false);
        txtTenTaiKhoan.setText(this.taiKhoan.getTenTaiKhoan());
        txtTenTaiKhoan.setFocusable(false);
        txtMatKhau.setText(this.taiKhoan.getMatKhau());
        txtMatKhau.setFocusable(false);
        txtTenNhanVien.setText(this.nvBUS.getNameByID(this.taiKhoan.getIdNhanVien()));
        txtTenNhanVien.setFocusable(false);
//        txtQuyen.setText(this.qBUS.getNameById(this.taiKhoan.getIdQuyen()));
//        txtQuyen.setFocusable(false);
        cbxQuyen.removeAllItems();
        cbxQuyen.addItem(qBUS.getNameById(taiKhoan.getIdQuyen()));
        cbxQuyen.setFocusable(false);
        chonNVBtn.setVisible(false);
    }
    
    public void initAddMode(){
        header.setText("Thêm tài khoản mới");
        addTaiKhoanBtn.setText("Thêm tài khoản");
    }
    
    public void initEditMode(){
        header.setText("Sửa thông tin tài khoản");
        addTaiKhoanBtn.setText("Lưu thông tin");
        txtTenTaiKhoan.setText(this.taiKhoan.getTenTaiKhoan());
        txtMatKhau.setText(this.taiKhoan.getMatKhau());
        txtTenNhanVien.setText(this.nvBUS.getNameByID(this.taiKhoan.getIdNhanVien()));
        cbxQuyen.setSelectedIndex(qBUS.getIndexById(this.taiKhoan.getIdQuyen()));
    }
    
    public boolean ValidateInput(){
        if(Validator.isEmpty(txtTenTaiKhoan.getText())){
            JOptionPane.showMessageDialog(this, "Ban chưa nhập tên tài khoản");
            return false;
        }
        if(!Validator.isWord(txtTenTaiKhoan.getText())) {
            JOptionPane.showMessageDialog(this, "Tên tài khoản không được chứa kí tự đặc biệt");
            return false;
        }
        if(Validator.isEmpty(txtMatKhau.getText())){
            JOptionPane.showMessageDialog(this, "Ban chưa nhập mật khẩu tài khoản");
            return false;
        }
        if(Validator.isEmpty(txtTenNhanVien.getText())){
            JOptionPane.showMessageDialog(this, "Ban chưa chọn nhân viên");
            return false;
        }
        return true;
    }
    
    public boolean ValidateDuplication(){
        for(TaiKhoanDTO i : tkBUS.getAll()){
            if(i.getTenTaiKhoan().equals(txtTenTaiKhoan.getText())){
                JOptionPane.showMessageDialog(this, "Tên tài khoản đã tồn tại!");
                return false;
            }
        }
        return true;
    }
 
    public TaiKhoanDTO getNewTK(){
        String tenTk = txtTenTaiKhoan.getText();
        String matKhau = txtMatKhau.getText();
        int idQuyen = qBUS.getByIndex(cbxQuyen.getSelectedIndex()).getId();
        return new TaiKhoanDTO(tkDAO.getAutoIncrement(), this.nhanVien.getId(), idQuyen, tenTk, matKhau, 1);
    }
    
    public void setEditedTK(){
        taiKhoan.setTenTaiKhoan(txtTenTaiKhoan.getText());
        taiKhoan.setMatKhau(txtMatKhau.getText());
        taiKhoan.setIdNhanVien(this.nhanVien.getId());
        taiKhoan.setIdQuyen(qBUS.getByIndex(cbxQuyen.getSelectedIndex()).getId());
    }
    
    public void addEvent(){
        if(!ValidateInput())
            return;
        if(!ValidateDuplication())
            return;
        newTaiKhoan = getNewTK();
        if(tkPanel.tkBUS.add(newTaiKhoan)){
            JOptionPane.showMessageDialog(this, "Thêm tài khoản thành công!");
            tkPanel.loadDataToTable(tkPanel.tkList);
            dispose();
        }
    }
    
    public void editEvent(){
        if(!ValidateInput())
            return;
        if(!ValidateDuplication())
            return;
        setEditedTK();
        if(tkPanel.tkBUS.update(taiKhoan)){
            JOptionPane.showMessageDialog(this, "Sửa thông tin tài khoản thành công!");
            tkPanel.loadDataToTable(tkPanel.tkList);
            dispose();
        }
    }
        
    
    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        header = new javax.swing.JLabel();
        jPanel2 = new javax.swing.JPanel();
        txtTenTaiKhoan = new javax.swing.JTextField();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        txtTenNhanVien = new javax.swing.JTextField();
        jLabel4 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        cancelBtn = new javax.swing.JButton();
        addTaiKhoanBtn = new javax.swing.JButton();
        chonNVBtn = new javax.swing.JButton();
        cbxQuyen = new javax.swing.JComboBox(qBUS.getStringList());
        txtMatKhau = new javax.swing.JPasswordField();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);

        jPanel1.setBackground(new java.awt.Color(88, 175, 232));
        jPanel1.setPreferredSize(new java.awt.Dimension(600, 50));

        header.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        header.setForeground(new java.awt.Color(255, 255, 255));
        header.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        header.setText("Tài Khoản Dialog");

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(header, javax.swing.GroupLayout.DEFAULT_SIZE, 600, Short.MAX_VALUE)
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(header, javax.swing.GroupLayout.DEFAULT_SIZE, 50, Short.MAX_VALUE)
        );

        getContentPane().add(jPanel1, java.awt.BorderLayout.PAGE_START);

        jLabel2.setText("Tên tài khoản");

        jLabel3.setText("Mật khẩu");

        txtTenNhanVien.setFocusable(false);

        jLabel4.setText("Nhân viên");

        jLabel5.setText("Quyền");

        cancelBtn.setBackground(new java.awt.Color(255, 107, 107));
        cancelBtn.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        cancelBtn.setForeground(new java.awt.Color(255, 255, 255));
        cancelBtn.setText("Hủy");
        cancelBtn.setBorder(null);
        cancelBtn.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                cancelBtnMousePressed(evt);
            }
        });

        addTaiKhoanBtn.setBackground(new java.awt.Color(78, 205, 196));
        addTaiKhoanBtn.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        addTaiKhoanBtn.setForeground(new java.awt.Color(255, 255, 255));
        addTaiKhoanBtn.setText("Thêm tài khoản");
        addTaiKhoanBtn.setBorder(null);
        addTaiKhoanBtn.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                addTaiKhoanBtnMousePressed(evt);
            }
        });

        chonNVBtn.setText("jButton1");
        chonNVBtn.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                chonNVBtnMousePressed(evt);
            }
        });

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGap(91, 91, 91)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(txtMatKhau, javax.swing.GroupLayout.PREFERRED_SIZE, 418, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(cbxQuyen, javax.swing.GroupLayout.PREFERRED_SIZE, 418, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                        .addGroup(jPanel2Layout.createSequentialGroup()
                            .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                .addComponent(jLabel4, javax.swing.GroupLayout.PREFERRED_SIZE, 418, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(jLabel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(txtTenTaiKhoan)
                                .addComponent(jLabel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addGroup(jPanel2Layout.createSequentialGroup()
                                    .addComponent(txtTenNhanVien, javax.swing.GroupLayout.PREFERRED_SIZE, 370, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                    .addComponent(chonNVBtn, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)))
                            .addGap(36, 36, 36))
                        .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                            .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanel2Layout.createSequentialGroup()
                                .addComponent(addTaiKhoanBtn, javax.swing.GroupLayout.PREFERRED_SIZE, 140, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(cancelBtn, javax.swing.GroupLayout.PREFERRED_SIZE, 140, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addComponent(jLabel5, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, 418, Short.MAX_VALUE))))
                .addGap(55, 55, 55))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGap(23, 23, 23)
                .addComponent(jLabel2)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(txtTenTaiKhoan, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jLabel3)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(txtMatKhau, javax.swing.GroupLayout.PREFERRED_SIZE, 46, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel4)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(txtTenNhanVien, javax.swing.GroupLayout.DEFAULT_SIZE, 40, Short.MAX_VALUE)
                    .addComponent(chonNVBtn, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jLabel5)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(cbxQuyen, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 33, Short.MAX_VALUE)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(cancelBtn, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(addTaiKhoanBtn, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap())
        );

        getContentPane().add(jPanel2, java.awt.BorderLayout.CENTER);

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void cancelBtnMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_cancelBtnMousePressed
        // TODO add your handling code here:
        dispose();
    }//GEN-LAST:event_cancelBtnMousePressed

    private void addTaiKhoanBtnMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_addTaiKhoanBtnMousePressed
        // TODO add your handling code here:
        if(mode.equals("add"))
            addEvent();
        if(mode.equals("edit"))
            editEvent();
    }//GEN-LAST:event_addTaiKhoanBtnMousePressed

    private void chonNVBtnMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_chonNVBtnMousePressed
        ChonNhanVienDialog cnvDialog = new ChonNhanVienDialog(null, true);
        cnvDialog.setVisible(true);
        try {
            if(cnvDialog.choosen == false) return;
            int idNv = cnvDialog.getSelectedId();
            this.nhanVien = nvBUS.getObjectById(idNv);
            txtTenNhanVien.setText(this.nhanVien.getHo()+" "+this.nhanVien.getTen());
        } catch (Exception e) {
//          System.out.println(e);  
        }
    }//GEN-LAST:event_chonNVBtnMousePressed

    

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton addTaiKhoanBtn;
    private javax.swing.JButton cancelBtn;
    private javax.swing.JComboBox<String> cbxQuyen;
    private javax.swing.JButton chonNVBtn;
    private javax.swing.JLabel header;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPasswordField txtMatKhau;
    private javax.swing.JTextField txtTenNhanVien;
    private javax.swing.JTextField txtTenTaiKhoan;
    // End of variables declaration//GEN-END:variables
}
