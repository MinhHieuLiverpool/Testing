/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JDialog.java to edit this template
 */
package GUI.Dialog;

import BUS.NhanVienBUS;
import DAO.NhanVienDAO;
import DTO.NhanVienDTO;
import GUI.Panel.NhanVien;
import helper.Validator;
import javax.swing.JOptionPane;

/**
 *
 * @author Admin
 */
public class NhanVienDialog extends javax.swing.JDialog {

    private NhanVienDAO nvDAO = new NhanVienDAO();
    private NhanVienBUS nvBUS = new NhanVienBUS();
    private NhanVienDTO nhanVien;
    private NhanVienDTO newNhanVien;
    private String mode;
    private NhanVien nvPanel;
    /**
     * Creates new form NhanVienDialog
     */
    public NhanVienDialog(java.awt.Frame parent, boolean modal, NhanVien nvPanel, NhanVienDTO nhanVien, String mode) {
        super(parent, modal);
        this.nvPanel = nvPanel;
        this.nhanVien = nhanVien;
        this.mode = mode;
        initComponents();
        initComponentsCustom();
    }
    
    public void initComponentsCustom() {
        this.setLocationRelativeTo(null);
        if(mode.equals("detail"))
            initDetailMode();
        if(mode.equals("add"))
            initAddMode();
        if(mode.equals("edit"))
            initEditMode();
    }

    public void initDetailMode() {
        header.setText("Thông tin nhân viên");
        addBtn.setVisible(false);
        txtHo.setText(this.nhanVien.getHo());
        txtHo.setFocusable(false);
        txtTen.setText(this.nhanVien.getTen());
        txtTen.setFocusable(false);
        if(this.nhanVien.getGioiTinh().equals("Nam")) {
            radioNam.setSelected(true);
            radioNu.setVisible(false);
        } else {
            radioNu.setSelected(true);
            radioNam.setVisible(false);
        }
        txtSDT.setText(this.nhanVien.getSoDienThoai());
        txtSDT.setFocusable(false);
        txtEmail.setText(this.nhanVien.getEmail());
        txtEmail.setFocusable(false);
    }
    
    public void initAddMode() {
        header.setText("Thêm nhân viên mới");
        addBtn.setText("Thêm nhân viên");
    }
    
    public void initEditMode() {
        header.setText("Sửa thông tin nhân viên");
        addBtn.setText("Lưu thông tin");
        txtHo.setText(this.nhanVien.getHo());
        txtTen.setText(this.nhanVien.getTen());
        if(this.nhanVien.getGioiTinh().equals("Nam")) {
            radioNam.setSelected(true);
        } else {
            radioNu.setSelected(true);
        }
        txtSDT.setText(this.nhanVien.getSoDienThoai());
        txtEmail.setText(this.nhanVien.getEmail());
    }
    
    public boolean ValidateInput() {
        if(
                Validator.isEmpty(txtHo.getText())
                && Validator.isEmpty(txtTen.getText())
                && Validator.isEmpty(txtSDT.getText())
                && Validator.isEmpty(txtEmail.getText())
        ) {
            JOptionPane.showMessageDialog(this, "Thông tin không được để trống");
            return false;
        }
        if(Validator.isEmpty(txtHo.getText())) {
            JOptionPane.showMessageDialog(this, "Bạn chưa nhập họ của nhân viên");
            return false;
        }
        if(Validator.isEmpty(txtTen.getText())) {
            JOptionPane.showMessageDialog(this, "Bạn chưa nhập tên của nhân viên");
            return false;
        }
        if(radioNam.isSelected()==false && radioNu.isSelected()==false) {
            JOptionPane.showMessageDialog(this, "Bạn chưa chọn giới tính cho nhân viên");
            return false;
        }
        if(Validator.isEmpty(txtSDT.getText())) {
            JOptionPane.showMessageDialog(this, "Bạn chưa nhập số điện thoại của nhân viên");
            return false;
        }
        if(Validator.isEmpty(txtEmail.getText())) {
            JOptionPane.showMessageDialog(this, "Bạn chưa nhập email của nhân viên");
            return false;
        }
        if(!Validator.isName(txtHo.getText())) {
            JOptionPane.showMessageDialog(this, "Vui lòng nhập họ hợp lệ");
            return false;
        }
        if(!Validator.isName(txtTen.getText())) {
            JOptionPane.showMessageDialog(this, "Vui lòng nhập tên hợp lệ");
            return false;
        }
        if(!Validator.isPhoneNumber(txtSDT.getText())) {
            JOptionPane.showMessageDialog(this, "Số điện thoại phải là số bắt đầu bằng 0 và có độ dài 10 chữ số");
            return false;
        }
        if(!Validator.isEmail(txtEmail.getText())) {
            JOptionPane.showMessageDialog(this, "Vui lòng nhập đúng định dạng email");
            return false;
        }
        return true;
    }
    
    public String getSelectedGT() {
        if(radioNam.isSelected())
            return "Nam";
        if(radioNu.isSelected())
            return "Nữ";
        return "";
    }
    
    public boolean ValidateDuplication(int id) {
        for(NhanVienDTO i : nvBUS.getAll()) {
            if(i.getId() == id) continue;
//            if(i.getHo().equals(txtHo.getText()) && i.getTen().equals(txtTen.getText()) && i.getGioiTinh().equals(getSelectedGT())) {
//                JOptionPane.showMessageDialog(this, "Nhân viên đã tồn tại!");
//                return false;
//            }
            if (i.getSoDienThoai().equals(txtSDT.getText())) {
                JOptionPane.showMessageDialog(this, "Số điện thoại này đã tồn tại!");
                return false;
            }
            if (i.getEmail().equals(txtEmail.getText())) {
                JOptionPane.showMessageDialog(this, "Email này đã tồn tại!");
                return false;
            }
        }
        return true;
    }
    
    public NhanVienDTO getNewNV() {
        String ho = txtHo.getText();
        String ten = txtTen.getText();
        String gioiTinh = "";
        if(radioNam.isSelected())
            gioiTinh = radioNam.getText();
        if(radioNu.isSelected())
            gioiTinh = radioNu.getText();
        String sdt = txtSDT.getText();
        String email = txtEmail.getText();
        return new NhanVienDTO(nvDAO.getAutoIncrement(), ho, ten, gioiTinh, sdt, email, 1);
    }
    
    public void setEditedNV() {
        nhanVien.setHo(txtHo.getText());
        nhanVien.setTen(txtTen.getText());
        if(radioNam.isSelected())
            nhanVien.setGioiTinh("Nam");
        if(radioNu.isSelected())
            nhanVien.setGioiTinh("Nữ");
        nhanVien.setSoDienThoai(txtSDT.getText());
        nhanVien.setEmail(txtEmail.getText());
    }
    
    public void addEvent() {
        if(!ValidateInput())
            return;
        if(!ValidateDuplication(-1))
            return;
        newNhanVien = getNewNV();
        if(nvPanel.nvBUS.add(newNhanVien)) {
            JOptionPane.showMessageDialog(this, "Thêm nhân viên mới thành công!");
            nvPanel.nhanVienList = nvDAO.selectAll();
            nvPanel.loadDataToTable(nvPanel.nhanVienList);
            dispose();
        }
    }
    
    public void editEvent() {
        if(!ValidateInput())
            return;
        if(!ValidateDuplication(this.nhanVien.getId()))
            return;
        setEditedNV();
        if(nvPanel.nvBUS.update(nhanVien)) {
            JOptionPane.showMessageDialog(this, "Sửa thông tin nhân viên thành công!");
            nvPanel.loadDataToTable(nvPanel.nhanVienList);
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

        gioiTinhBtnGr = new javax.swing.ButtonGroup();
        jPanel1 = new javax.swing.JPanel();
        header = new javax.swing.JLabel();
        jPanel2 = new javax.swing.JPanel();
        jLabel2 = new javax.swing.JLabel();
        txtHo = new javax.swing.JTextField();
        jLabel3 = new javax.swing.JLabel();
        txtTen = new javax.swing.JTextField();
        jLabel4 = new javax.swing.JLabel();
        radioNam = new javax.swing.JRadioButton();
        radioNu = new javax.swing.JRadioButton();
        jLabel5 = new javax.swing.JLabel();
        txtSDT = new javax.swing.JTextField();
        jLabel6 = new javax.swing.JLabel();
        txtEmail = new javax.swing.JTextField();
        addBtn = new javax.swing.JButton();
        cancelBtn = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);

        jPanel1.setBackground(new java.awt.Color(88, 175, 232));
        jPanel1.setPreferredSize(new java.awt.Dimension(600, 50));

        header.setBackground(new java.awt.Color(255, 255, 255));
        header.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        header.setForeground(new java.awt.Color(255, 255, 255));
        header.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        header.setText("Nhân viên Dialog");
        header.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(header, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 600, Short.MAX_VALUE)
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(header, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 50, Short.MAX_VALUE)
        );

        getContentPane().add(jPanel1, java.awt.BorderLayout.PAGE_START);

        jPanel2.setBackground(new java.awt.Color(255, 255, 255));
        jPanel2.setPreferredSize(new java.awt.Dimension(600, 400));

        jLabel2.setText("Họ");

        txtHo.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtHoActionPerformed(evt);
            }
        });

        jLabel3.setText("Tên");

        jLabel4.setText("Giới tính");

        radioNam.setBackground(new java.awt.Color(255, 255, 255));
        gioiTinhBtnGr.add(radioNam);
        radioNam.setText("Nam");

        radioNu.setBackground(new java.awt.Color(255, 255, 255));
        gioiTinhBtnGr.add(radioNu);
        radioNu.setText("Nữ");

        jLabel5.setText("Số điện thoại");

        jLabel6.setText("Email");

        addBtn.setBackground(new java.awt.Color(78, 205, 196));
        addBtn.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        addBtn.setForeground(new java.awt.Color(255, 255, 255));
        addBtn.setText("Thêm nhân viên");
        addBtn.setBorder(null);
        addBtn.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        addBtn.setPreferredSize(new java.awt.Dimension(120, 40));
        addBtn.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                addBtnMousePressed(evt);
            }
        });
        addBtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                addBtnActionPerformed(evt);
            }
        });

        cancelBtn.setBackground(new java.awt.Color(255, 107, 107));
        cancelBtn.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        cancelBtn.setForeground(new java.awt.Color(255, 255, 255));
        cancelBtn.setText("Hủy");
        cancelBtn.setBorder(null);
        cancelBtn.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        cancelBtn.setPreferredSize(new java.awt.Dimension(120, 40));
        cancelBtn.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                cancelBtnMousePressed(evt);
            }
        });

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGap(91, 91, 91)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addComponent(addBtn, javax.swing.GroupLayout.PREFERRED_SIZE, 140, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(cancelBtn, javax.swing.GroupLayout.PREFERRED_SIZE, 140, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(jLabel4)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addComponent(radioNam)
                        .addGap(18, 18, 18)
                        .addComponent(radioNu))
                    .addComponent(jLabel6)
                    .addComponent(txtSDT)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(txtHo, javax.swing.GroupLayout.PREFERRED_SIZE, 200, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 37, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(18, 18, 18)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(txtTen, javax.swing.GroupLayout.PREFERRED_SIZE, 200, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, 37, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addComponent(jLabel5)
                    .addComponent(txtEmail))
                .addGap(91, 91, 91))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addComponent(jLabel3)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(txtTen, javax.swing.GroupLayout.PREFERRED_SIZE, 39, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addComponent(jLabel2)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(txtHo, javax.swing.GroupLayout.PREFERRED_SIZE, 39, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel4)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(radioNam)
                    .addComponent(radioNu))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel5)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(txtSDT, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel6)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(txtEmail, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 83, Short.MAX_VALUE)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(addBtn, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(cancelBtn, javax.swing.GroupLayout.DEFAULT_SIZE, 42, Short.MAX_VALUE))
                .addContainerGap())
        );

        getContentPane().add(jPanel2, java.awt.BorderLayout.CENTER);

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void addBtnMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_addBtnMousePressed
        if(mode.equals("add"))
            addEvent();
        if(mode.equals("edit"))
            editEvent();
    }//GEN-LAST:event_addBtnMousePressed

    private void cancelBtnMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_cancelBtnMousePressed
        dispose();
    }//GEN-LAST:event_cancelBtnMousePressed

    private void txtHoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtHoActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtHoActionPerformed

    private void addBtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_addBtnActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_addBtnActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton addBtn;
    private javax.swing.JButton cancelBtn;
    private javax.swing.ButtonGroup gioiTinhBtnGr;
    private javax.swing.JLabel header;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JRadioButton radioNam;
    private javax.swing.JRadioButton radioNu;
    private javax.swing.JTextField txtEmail;
    private javax.swing.JTextField txtHo;
    private javax.swing.JTextField txtSDT;
    private javax.swing.JTextField txtTen;
    // End of variables declaration//GEN-END:variables
}
