/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JPanel.java to edit this template
 */
package GUI.Component;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 *
 * @author Admin
 */
public class SearchTongTien extends javax.swing.JPanel {

    public SearchTongTien() {
        initComponents();
    }

    @SuppressWarnings("unchecked")
// <editor-fold defaultstate="collapsed" desc="Generated Code">                          
    private void initComponents() {

        jLabel1 = new javax.swing.JLabel();
        jTextField1 = new javax.swing.JTextField();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jTextField2 = new javax.swing.JTextField();

        setBackground(new java.awt.Color(255, 255, 255));
        setBorder(javax.swing.BorderFactory.createMatteBorder(0, 0, 0, 1, new java.awt.Color(221, 221, 221)));
        setPreferredSize(new java.awt.Dimension(630, 100));

        jLabel1.setText("Tổng Tiền");
        jLabel2.setText("Từ");
        jLabel3.setText("Đến");

        // Thêm KeyListener vào cả jTextField1 và jTextField2
        jTextField1.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                validateAndFilter(); // Gọi hàm validateAndFilter khi người dùng nhập
            }
        });

        jTextField2.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                validateAndFilter(); // Gọi hàm validateAndFilter khi người dùng nhập
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
                layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(layout.createSequentialGroup()
                                .addGap(20, 20, 20)
                                .addComponent(jLabel1)
                                .addGap(62, 62, 62)
                                .addComponent(jLabel2)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(jTextField1, javax.swing.GroupLayout.PREFERRED_SIZE, 135, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(31, 31, 31)
                                .addComponent(jLabel3)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jTextField2, javax.swing.GroupLayout.PREFERRED_SIZE, 125, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addContainerGap(74, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
                layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(layout.createSequentialGroup()
                                .addContainerGap()
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                        .addComponent(jLabel1)
                                        .addComponent(jTextField1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addComponent(jLabel2)
                                        .addComponent(jLabel3)
                                        .addComponent(jTextField2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addContainerGap(11, Short.MAX_VALUE))
        );
    }

    public JTextField tu;

    public JTextField giatritu() {
        tu = jTextField1;
        return tu;
    }
    public JTextField den;

    public JTextField giatriden() {
        den = jTextField2;
        return den;
    }

    private void validateAndFilter() {
        String startAmount = jTextField1.getText();
        String endAmount = jTextField2.getText();

//        System.out.println("click textfiled từ: " + startAmount);
//        System.out.println("click textfeild nhập: " + endAmount);
        if (startAmount.isEmpty() || endAmount.isEmpty()) {
            return;
        }

        try {
            double start = Double.parseDouble(startAmount);
            double end = Double.parseDouble(endAmount);
            if (start > end) {
                JOptionPane.showMessageDialog(null, "Số bắt đầu không thể lớn hơn số kết thúc.", "Lỗi nhập liệu", JOptionPane.ERROR_MESSAGE);
                return;
            }

            // Execute search logic here
//            System.out.println("Đang tìm kiếm số tiền trong khoảng: " + start + " và " + end);
            // TODO: Add your search implementation here
        } catch (NumberFormatException e) {

            if (!startAmount.isEmpty() && !endAmount.isEmpty()) {
                JOptionPane.showMessageDialog(null, "Vui lòng nhập số hợp lệ.", "Lỗi nhập liệu", JOptionPane.ERROR_MESSAGE);
            }
        }
    }

    /**
     *
     */

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JTextField jTextField1;
    private javax.swing.JTextField jTextField2;
    // End of variables declaration//GEN-END:variables
}
