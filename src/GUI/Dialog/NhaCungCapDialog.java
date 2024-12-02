/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JDialog.java to edit this template
 */
package GUI.Dialog;

import BUS.NhaCungCapBUS;
import DAO.NhaCungCapDAO;
import DTO.NhaCungCapDTO;
import GUI.Panel.NhaCungCap;
import helper.Validator;
import javax.swing.JOptionPane;

/**
 *
 * @author DVN
 */
public class NhaCungCapDialog extends javax.swing.JDialog {
    private NhaCungCapDAO nccDAO = new NhaCungCapDAO();
    private NhaCungCapBUS nccBUS = new NhaCungCapBUS();
    private NhaCungCapDTO nhaCungCap;
    private NhaCungCapDTO newNhaCungCap;
    private String mode;
    private NhaCungCap nccPanel;
    /**
     * Creates new form NhaCungCap
     */
    public NhaCungCapDialog(java.awt.Frame parent, boolean modal, NhaCungCap nccPanel, NhaCungCapDTO nhaCungCap, String mode) {
        super(parent, modal);
        this.nccPanel = nccPanel;
        this.nhaCungCap = nhaCungCap;
        this.mode = mode;
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
    
    public void initDetailMode() {
        header.setText("Chi tiết nhà cung cấp");
        addNCCBtn.setVisible(false);
        txtTen.setText(this.nhaCungCap.getTen());
        txtTen.setFocusable(false);
        txtDiaChi.setText(this.nhaCungCap.getDiaChi());
        txtDiaChi.setFocusable(false);
        txtSoDienThoai.setText(this.nhaCungCap.getSoDienThoai());
        txtSoDienThoai.setFocusable(false);
        txtEmail.setText(this.nhaCungCap.getEmail());
        txtEmail.setFocusable(false);
    }
    
    public void initAddMode() {
        header.setText("Thêm nhà cung cấp");
        addNCCBtn.setText("Thêm nhà cung cấp");
    }
    
    public void initEditMode() {
        header.setText("Sửa thông tin nhà cung cấp");
        addNCCBtn.setText("Lưu thông tin");
        txtTen.setText(this.nhaCungCap.getTen());
        txtDiaChi.setText(this.nhaCungCap.getDiaChi());
        txtSoDienThoai.setText(this.nhaCungCap.getSoDienThoai());
        txtEmail.setText(this.nhaCungCap.getEmail());
    }
    
    public boolean ValidateInput(){
        if(Validator.isEmpty(txtTen.getText())){
            JOptionPane.showMessageDialog(this, "Bạn chưa nhập tên nhà cung cấp");
            return false;
        }
        if(Validator.isEmpty(txtDiaChi.getText())){
            JOptionPane.showMessageDialog(this, "Bạn chưa nhập địa chỉ nhà cung cấp");
            return false;
        }
        if(Validator.isEmpty(txtSoDienThoai.getText())){
            JOptionPane.showMessageDialog(this, "Bạn chưa nhập số điện thoại nhà cung cấp");
            return false;
        }
        if(Validator.isEmpty(txtEmail.getText())){
            JOptionPane.showMessageDialog(this, "Bạn chưa nhập Email nhà cung cấp");
            return false;
        }
        if(!Validator.isName(txtTen.getText())) {
            JOptionPane.showMessageDialog(this, "Vui lòng nhập tên hợp lệ");
            return false;
        }
        if(!Validator.isName(txtDiaChi.getText())) {
            JOptionPane.showMessageDialog(this, "Vui lòng nhập địa chỉ hợp lệ");
            return false;
        }
        if(!Validator.isPhoneNumber(txtSoDienThoai.getText())){
            JOptionPane.showMessageDialog(this, "Số điện thoại phải là số bắt đầu bằng 0 và có độ dài 10 chữ số");
            txtSoDienThoai.requestFocus();
            return false;
        }
        if(!Validator.isEmail(txtEmail.getText())){
            JOptionPane.showMessageDialog(this, "Vui lòng nhập đúng định dạng email của nhà cung cấp");
            txtEmail.requestFocus();
            return false;
        }
        return true;
    }
    
    public boolean ValidateDuplication(int id){
        for(NhaCungCapDTO i : nccBUS.getAll()){
            if(i.getId() == id) continue;
            if(i.getTen().equals(txtTen.getText()) || i.getDiaChi().equals(txtDiaChi.getText()) || i.getSoDienThoai().equals(txtSoDienThoai.getText()) || i.getEmail().equals(txtEmail.getText())){
                JOptionPane.showMessageDialog(this, "Nhà cung cấp đã tồn tại!");
                return false;
            }
        }
        return true;
    }
    
    public NhaCungCapDTO getNewNCC(){
        String ten = txtTen.getText();
        String diaChi = txtDiaChi.getText();
        String sdt = txtSoDienThoai.getText();
        String email = txtEmail.getText();
        return new NhaCungCapDTO(nccDAO.getAuroIncrement(), ten, diaChi, sdt, email, 1);
    }
    
    public void setEditedNCC(){
        nhaCungCap.setTen(txtTen.getText());
        nhaCungCap.setDiaChi(txtDiaChi.getText());
        nhaCungCap.setSoDienThoai(txtSoDienThoai.getText());
        nhaCungCap.setEmail(txtEmail.getText());
    }
    
    public void addEvent(){
        if(!ValidateInput()){
            return;
        }
        if(!ValidateDuplication(-1)){
            return;
        }
        newNhaCungCap = getNewNCC();
        if(nccPanel.nccBUS.add(newNhaCungCap)){
            JOptionPane.showMessageDialog(this, "Thêm nhà cung cấp thành công!");
            nccPanel.loadDataToTable(nccPanel.nhaCungCapList);
            dispose();
        }
    }
    
    public void editEvent(){
        if(!ValidateInput()) return;
        if(!ValidateDuplication(nhaCungCap.getId())) return;
        setEditedNCC();
        if(nccPanel.nccBUS.update(nhaCungCap)){
            JOptionPane.showMessageDialog(this, "Sửa thông tin nhà cung cấp thành công!");
            nccPanel.loadDataToTable(nccPanel.nhaCungCapList);
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
        txtTen = new javax.swing.JTextField();
        jLabel2 = new javax.swing.JLabel();
        txtDiaChi = new javax.swing.JTextField();
        jLabel7 = new javax.swing.JLabel();
        txtSoDienThoai = new javax.swing.JTextField();
        jLabel8 = new javax.swing.JLabel();
        txtEmail = new javax.swing.JTextField();
        jLabel9 = new javax.swing.JLabel();
        cancelBtn = new javax.swing.JButton();
        addNCCBtn = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);

        jPanel1.setBackground(new java.awt.Color(88, 175, 232));
        jPanel1.setPreferredSize(new java.awt.Dimension(600, 50));

        header.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        header.setForeground(new java.awt.Color(255, 255, 255));
        header.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        header.setText("Nhà cung cấp Dialog");

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

        jPanel2.setBackground(new java.awt.Color(255, 255, 255));

        jLabel2.setText("Tên");

        jLabel7.setText("Địa chỉ");

        jLabel8.setText("Số điện thoại");

        jLabel9.setText("Email");

        cancelBtn.setBackground(new java.awt.Color(255, 107, 107));
        cancelBtn.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        cancelBtn.setForeground(new java.awt.Color(255, 255, 255));
        cancelBtn.setText("Hủy");
        cancelBtn.setBorderPainted(false);
        cancelBtn.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        cancelBtn.setPreferredSize(new java.awt.Dimension(164, 27));
        cancelBtn.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                cancelBtnMousePressed(evt);
            }
        });

        addNCCBtn.setBackground(new java.awt.Color(78, 205, 196));
        addNCCBtn.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        addNCCBtn.setForeground(new java.awt.Color(255, 255, 255));
        addNCCBtn.setText("Thêm nhà cung cấp");
        addNCCBtn.setBorderPainted(false);
        addNCCBtn.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        addNCCBtn.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                addNCCBtnMousePressed(evt);
            }
        });

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGap(60, 60, 60)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                        .addComponent(jLabel9, javax.swing.GroupLayout.PREFERRED_SIZE, 418, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(jLabel8, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jLabel7, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jLabel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addComponent(txtTen)
                    .addComponent(txtDiaChi)
                    .addComponent(txtSoDienThoai)
                    .addComponent(txtEmail)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addComponent(addNCCBtn)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 179, Short.MAX_VALUE)
                        .addComponent(cancelBtn, javax.swing.GroupLayout.PREFERRED_SIZE, 140, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(60, 60, 60))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap(33, Short.MAX_VALUE)
                .addComponent(jLabel2)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(txtTen, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jLabel7)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(txtDiaChi, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jLabel8)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(txtSoDienThoai, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jLabel9)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(txtEmail, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(32, 32, 32)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(cancelBtn, javax.swing.GroupLayout.PREFERRED_SIZE, 45, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(addNCCBtn, javax.swing.GroupLayout.PREFERRED_SIZE, 45, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap())
        );

        getContentPane().add(jPanel2, java.awt.BorderLayout.CENTER);

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void addNCCBtnMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_addNCCBtnMousePressed
        // TODO add your handling code here:
        if(mode.equals("add"))
            addEvent();
        if(mode.equals("edit"))
            editEvent();
    }//GEN-LAST:event_addNCCBtnMousePressed

    private void cancelBtnMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_cancelBtnMousePressed
        // TODO add your handling code here:
        dispose();
    }//GEN-LAST:event_cancelBtnMousePressed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton addNCCBtn;
    private javax.swing.JButton cancelBtn;
    private javax.swing.JLabel header;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JTextField txtDiaChi;
    private javax.swing.JTextField txtEmail;
    private javax.swing.JTextField txtSoDienThoai;
    private javax.swing.JTextField txtTen;
    // End of variables declaration//GEN-END:variables
}
