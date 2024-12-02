/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JPanel.java to edit this template
 */
package GUI.Panel;

/**
 *
 * @author Admin
 */
public class ThongKe extends javax.swing.JPanel {
    
    ThongKeTongQuan tktq = new ThongKeTongQuan();
    ThongKeDoanhThu tkdt = new ThongKeDoanhThu();
    
    public ThongKe() {
        initComponents();
        initComponentsCustom();
    }

    public void initComponentsCustom() {
        jTabbedPane1.addTab("Tổng quan", tktq);
        jTabbedPane1.addTab("Doanh thu", tkdt);
    }
    
    public void updateContent() {
        tktq.updateContent();
        tkdt.updateContent();
    }
    
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jTabbedPane1 = new javax.swing.JTabbedPane();

        setBackground(new java.awt.Color(255, 255, 255));

        jTabbedPane1.setPreferredSize(new java.awt.Dimension(1030, 720));

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jTabbedPane1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jTabbedPane1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
    }// </editor-fold>//GEN-END:initComponents


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JTabbedPane jTabbedPane1;
    // End of variables declaration//GEN-END:variables
}
