/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JPanel.java to edit this template
 */
package GUI.Panel;

import BUS.HoaDonBUS;
import BUS.PhieuNhapBUS;
import DAO.HoaDonDAO;
import DAO.PhieuNhapDAO;
import DAO.ThongKeDAO;
import DTO.ThongKe.TKDoanhThuDTO;
import GUI.Component.NumericDocumentFilter;
import helper.Formatter;
import java.util.ArrayList;
import java.sql.Timestamp;
import java.time.LocalDate;
import java.time.Month;
import java.time.Year;
import java.util.Date;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;
import javax.swing.text.PlainDocument;

/**
 *
 * @author Admin
 */
public class ThongKeDoanhThu extends javax.swing.JPanel {

    PhieuNhapDAO pnDAO = new PhieuNhapDAO();
    PhieuNhapBUS pnBUS = new PhieuNhapBUS();
    HoaDonDAO hdDAO = new HoaDonDAO();
    HoaDonBUS hdBUS = new HoaDonBUS();
    ThongKeDAO tkDAO = new ThongKeDAO();
    ArrayList<TKDoanhThuDTO> tkdtNamList;
    ArrayList<TKDoanhThuDTO> tkdtThangList;
    ArrayList<TKDoanhThuDTO> tkdtNgayList;
    DefaultTableModel dtNamtblModel;
    DefaultTableModel dtThangtblModel;
    DefaultTableModel dtNgayModel;
    
    public ThongKeDoanhThu() {
        initComponents();
        initComponentsCustom();
    }

    public void initComponentsCustom() {
        lbl_totalEarn.setText(Formatter.FormatVND(hdBUS.getTotalMoney()));
        lbl_totalSpent.setText(Formatter.FormatVND(pnBUS.getTotalMoney()));
        lbl_totalProfit.setText(Formatter.FormatVND(hdBUS.getTotalMoney() - pnBUS.getTotalMoney()));
        
        PlainDocument doc1 = (PlainDocument) txtYearStart.getDocument();
        doc1.setDocumentFilter(new NumericDocumentFilter());
        PlainDocument doc2 = (PlainDocument) txtYearEnd.getDocument();
        doc2.setDocumentFilter(new NumericDocumentFilter());
        PlainDocument doc3 = (PlainDocument) txtYearMonth.getDocument();
        doc3.setDocumentFilter(new NumericDocumentFilter());
        dtNamtblModel = (DefaultTableModel) tbl_dtNam.getModel();
        dtThangtblModel = (DefaultTableModel) tbl_dtThang.getModel();
        dtNgayModel = (DefaultTableModel) tbl_dtNgay.getModel();
        
        tkdtNamList = tkDAO.thongKeDoanhThuNam(2016, 2024);
        loadDTNamToTable(tkdtNamList);
        
        tkdtThangList = tkDAO.thongKeDoanhThuThang(Year.now().getValue());
        loadDTThangToTable(tkdtThangList);
        
        tkdtNgayList = tkDAO.thongKeDoanhThuNgay(Year.now().getValue(), LocalDate.now().getMonthValue());
        loadDTNgayToTable(tkdtNgayList);
    }
    
    public void updateContent() {
        lbl_totalEarn.setText(Formatter.FormatVND(hdBUS.getTotalMoney()));
        lbl_totalSpent.setText(Formatter.FormatVND(pnBUS.getTotalMoney()));
        lbl_totalProfit.setText(Formatter.FormatVND(hdBUS.getTotalMoney() - pnBUS.getTotalMoney()));
        
        tkdtNamList = tkDAO.thongKeDoanhThuNam(2016, 2024);
        loadDTNamToTable(tkdtNamList);
        
        tkdtThangList = tkDAO.thongKeDoanhThuThang(Year.now().getValue());
        loadDTThangToTable(tkdtThangList);
        
        tkdtNgayList = tkDAO.thongKeDoanhThuNgay(Year.now().getValue(), LocalDate.now().getMonthValue());
        loadDTNgayToTable(tkdtNgayList);
    }
    
    public void loadDTNamToTable(ArrayList<TKDoanhThuDTO> tkdtList) {
        dtNamtblModel.setRowCount(0);
        for(TKDoanhThuDTO i : tkdtList) {
            dtNamtblModel.addRow(new Object[]{
                Formatter.getYear(i.getDate()),
                Formatter.FormatVND(i.getDoanhThu()),
                Formatter.FormatVND(i.getChiPhi()),
                Formatter.FormatVND(i.getLoiNhuan())
            });
        }
    }
    
    public void loadDTThangToTable(ArrayList<TKDoanhThuDTO> tkdtList) {
        dtThangtblModel.setRowCount(0);
        for(TKDoanhThuDTO i : tkdtList) {
            dtThangtblModel.addRow(new Object[]{
                Formatter.getMonth(i.getDate()),
                Formatter.FormatVND(i.getDoanhThu()),
                Formatter.FormatVND(i.getChiPhi()),
                Formatter.FormatVND(i.getLoiNhuan())
            });
        }
    }
    
    public void loadDTNgayToTable(ArrayList<TKDoanhThuDTO> tkdtList) {
        dtNgayModel.setRowCount(0);
        for(TKDoanhThuDTO i : tkdtList) {
            dtNgayModel.addRow(new Object[]{
                Formatter.getDate(i.getDate()),
                Formatter.FormatVND(i.getDoanhThu()),
                Formatter.FormatVND(i.getChiPhi()),
                Formatter.FormatVND(i.getLoiNhuan())
            });
        }
    }
    
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jTabbedPane1 = new javax.swing.JTabbedPane();
        pnl_tongDT = new javax.swing.JPanel();
        jPanel2 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        lbl_totalEarn = new javax.swing.JLabel();
        jPanel3 = new javax.swing.JPanel();
        jLabel2 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        lbl_totalSpent = new javax.swing.JLabel();
        jPanel4 = new javax.swing.JPanel();
        jLabel3 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        lbl_totalProfit = new javax.swing.JLabel();
        pnl_dtNam = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        tbl_dtNam = new javax.swing.JTable();
        jLabel7 = new javax.swing.JLabel();
        txtYearStart = new javax.swing.JTextField();
        jLabel8 = new javax.swing.JLabel();
        txtYearEnd = new javax.swing.JTextField();
        btn_tkNam = new javax.swing.JButton();
        pnl_dtThang = new javax.swing.JPanel();
        jLabel9 = new javax.swing.JLabel();
        txtYearMonth = new javax.swing.JTextField();
        btn_tkThang = new javax.swing.JButton();
        jScrollPane2 = new javax.swing.JScrollPane();
        tbl_dtThang = new javax.swing.JTable();
        jPanel1 = new javax.swing.JPanel();
        jLabel10 = new javax.swing.JLabel();
        txtYearDate = new javax.swing.JTextField();
        jLabel11 = new javax.swing.JLabel();
        txtMonthDate = new javax.swing.JTextField();
        btn_tkNgay = new javax.swing.JButton();
        jScrollPane3 = new javax.swing.JScrollPane();
        tbl_dtNgay = new javax.swing.JTable();

        setBackground(new java.awt.Color(255, 255, 255));

        pnl_tongDT.setBackground(new java.awt.Color(255, 255, 255));

        jPanel2.setBackground(new java.awt.Color(169, 210, 213));

        jLabel1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/image/icon/money-bag.png"))); // NOI18N
        jLabel1.setPreferredSize(new java.awt.Dimension(128, 128));

        jLabel4.setFont(new java.awt.Font("Segoe UI", 1, 24)); // NOI18N
        jLabel4.setText("Tổng doanh thu bán ra");

        lbl_totalEarn.setFont(new java.awt.Font("Segoe UI", 1, 48)); // NOI18N
        lbl_totalEarn.setText("1,234,567,891,234,567,891,234đ");

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGap(40, 40, 40)
                .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(lbl_totalEarn, javax.swing.GroupLayout.DEFAULT_SIZE, 743, Short.MAX_VALUE))
                .addContainerGap())
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGap(21, 21, 21)
                        .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(jLabel4, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(lbl_totalEarn, javax.swing.GroupLayout.PREFERRED_SIZE, 86, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(21, 21, 21))
        );

        jPanel3.setBackground(new java.awt.Color(169, 210, 213));

        jLabel2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/image/icon/spending.png"))); // NOI18N

        jLabel5.setFont(new java.awt.Font("Segoe UI", 1, 24)); // NOI18N
        jLabel5.setText("Tổng kinh phí nhập hàng");

        lbl_totalSpent.setFont(new java.awt.Font("Segoe UI", 1, 48)); // NOI18N
        lbl_totalSpent.setText("1,234,567,891,234,567,891,234đ");

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addGap(40, 40, 40)
                .addComponent(jLabel2)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel5, javax.swing.GroupLayout.DEFAULT_SIZE, 743, Short.MAX_VALUE)
                    .addComponent(lbl_totalSpent, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 743, Short.MAX_VALUE))
                .addContainerGap())
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addGap(21, 21, 21)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addComponent(jLabel5, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(lbl_totalSpent, javax.swing.GroupLayout.PREFERRED_SIZE, 86, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(jLabel2))
                .addGap(21, 21, 21))
        );

        jPanel4.setBackground(new java.awt.Color(169, 210, 213));

        jLabel3.setIcon(new javax.swing.ImageIcon(getClass().getResource("/image/icon/profits.png"))); // NOI18N

        jLabel6.setFont(new java.awt.Font("Segoe UI", 1, 24)); // NOI18N
        jLabel6.setText("Tổng lợi nhuận");

        lbl_totalProfit.setFont(new java.awt.Font("Segoe UI", 1, 48)); // NOI18N
        lbl_totalProfit.setText("1,234,567,891,234,567,891,234đ");

        javax.swing.GroupLayout jPanel4Layout = new javax.swing.GroupLayout(jPanel4);
        jPanel4.setLayout(jPanel4Layout);
        jPanel4Layout.setHorizontalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addGap(40, 40, 40)
                .addComponent(jLabel3)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel6, javax.swing.GroupLayout.DEFAULT_SIZE, 743, Short.MAX_VALUE)
                    .addComponent(lbl_totalProfit, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 743, Short.MAX_VALUE))
                .addContainerGap())
        );
        jPanel4Layout.setVerticalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addGap(21, 21, 21)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addGroup(jPanel4Layout.createSequentialGroup()
                        .addComponent(jLabel6, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(lbl_totalProfit, javax.swing.GroupLayout.PREFERRED_SIZE, 86, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(jLabel3))
                .addGap(21, 21, 21))
        );

        javax.swing.GroupLayout pnl_tongDTLayout = new javax.swing.GroupLayout(pnl_tongDT);
        pnl_tongDT.setLayout(pnl_tongDTLayout);
        pnl_tongDTLayout.setHorizontalGroup(
            pnl_tongDTLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, pnl_tongDTLayout.createSequentialGroup()
                .addGap(45, 45, 45)
                .addGroup(pnl_tongDTLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jPanel4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(45, 45, 45))
        );
        pnl_tongDTLayout.setVerticalGroup(
            pnl_tongDTLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnl_tongDTLayout.createSequentialGroup()
                .addGap(32, 32, 32)
                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(jPanel4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(83, Short.MAX_VALUE))
        );

        jTabbedPane1.addTab("Tổng doanh thu", pnl_tongDT);

        pnl_dtNam.setBackground(new java.awt.Color(255, 255, 255));

        tbl_dtNam.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        tbl_dtNam.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Năm", "Doanh thu", "Chi phí", "Lợi nhuận"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        tbl_dtNam.setRowHeight(36);
        tbl_dtNam.setSelectionBackground(new java.awt.Color(190, 215, 220));
        tbl_dtNam.setSelectionForeground(new java.awt.Color(0, 0, 0));
        tbl_dtNam.setShowGrid(true);
        tbl_dtNam.getTableHeader().setResizingAllowed(false);
        tbl_dtNam.getTableHeader().setReorderingAllowed(false);
        jScrollPane1.setViewportView(tbl_dtNam);
        if (tbl_dtNam.getColumnModel().getColumnCount() > 0) {
            tbl_dtNam.getColumnModel().getColumn(0).setResizable(false);
            tbl_dtNam.getColumnModel().getColumn(1).setResizable(false);
            tbl_dtNam.getColumnModel().getColumn(2).setResizable(false);
            tbl_dtNam.getColumnModel().getColumn(3).setResizable(false);
        }

        jLabel7.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jLabel7.setText("Từ năm");

        jLabel8.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jLabel8.setText("Đến năm");

        btn_tkNam.setText("Thống kê");
        btn_tkNam.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                btn_tkNamMousePressed(evt);
            }
        });

        javax.swing.GroupLayout pnl_dtNamLayout = new javax.swing.GroupLayout(pnl_dtNam);
        pnl_dtNam.setLayout(pnl_dtNamLayout);
        pnl_dtNamLayout.setHorizontalGroup(
            pnl_dtNamLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jScrollPane1, javax.swing.GroupLayout.Alignment.TRAILING)
            .addGroup(pnl_dtNamLayout.createSequentialGroup()
                .addGap(328, 328, 328)
                .addComponent(jLabel7)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(txtYearStart, javax.swing.GroupLayout.PREFERRED_SIZE, 80, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel8)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(txtYearEnd, javax.swing.GroupLayout.PREFERRED_SIZE, 80, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(btn_tkNam)
                .addContainerGap(327, Short.MAX_VALUE))
        );
        pnl_dtNamLayout.setVerticalGroup(
            pnl_dtNamLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, pnl_dtNamLayout.createSequentialGroup()
                .addGap(26, 26, 26)
                .addGroup(pnl_dtNamLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel7, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtYearStart, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel8, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtYearEnd, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btn_tkNam, javax.swing.GroupLayout.DEFAULT_SIZE, 36, Short.MAX_VALUE))
                .addGap(18, 18, 18)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 581, javax.swing.GroupLayout.PREFERRED_SIZE))
        );

        jTabbedPane1.addTab("Doanh thu theo năm", pnl_dtNam);

        pnl_dtThang.setBackground(new java.awt.Color(255, 255, 255));

        jLabel9.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jLabel9.setText("Năm");

        btn_tkThang.setText("Thống kê");
        btn_tkThang.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                btn_tkThangMousePressed(evt);
            }
        });

        tbl_dtThang.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        tbl_dtThang.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Tháng", "Doanh thu", "Chi phí", "Lợi nhuận"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        tbl_dtThang.setRowHeight(36);
        tbl_dtThang.setSelectionBackground(new java.awt.Color(190, 215, 220));
        tbl_dtThang.setSelectionForeground(new java.awt.Color(0, 0, 0));
        tbl_dtThang.setShowGrid(true);
        tbl_dtThang.getTableHeader().setResizingAllowed(false);
        tbl_dtThang.getTableHeader().setReorderingAllowed(false);
        jScrollPane2.setViewportView(tbl_dtThang);
        if (tbl_dtThang.getColumnModel().getColumnCount() > 0) {
            tbl_dtThang.getColumnModel().getColumn(0).setResizable(false);
            tbl_dtThang.getColumnModel().getColumn(1).setResizable(false);
            tbl_dtThang.getColumnModel().getColumn(2).setResizable(false);
            tbl_dtThang.getColumnModel().getColumn(3).setResizable(false);
        }

        javax.swing.GroupLayout pnl_dtThangLayout = new javax.swing.GroupLayout(pnl_dtThang);
        pnl_dtThang.setLayout(pnl_dtThangLayout);
        pnl_dtThangLayout.setHorizontalGroup(
            pnl_dtThangLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jScrollPane2)
            .addGroup(pnl_dtThangLayout.createSequentialGroup()
                .addGap(403, 403, 403)
                .addComponent(jLabel9)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(txtYearMonth, javax.swing.GroupLayout.PREFERRED_SIZE, 80, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(btn_tkThang)
                .addContainerGap(410, Short.MAX_VALUE))
        );
        pnl_dtThangLayout.setVerticalGroup(
            pnl_dtThangLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, pnl_dtThangLayout.createSequentialGroup()
                .addContainerGap(26, Short.MAX_VALUE)
                .addGroup(pnl_dtThangLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txtYearMonth, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btn_tkThang, javax.swing.GroupLayout.DEFAULT_SIZE, 36, Short.MAX_VALUE)
                    .addComponent(jLabel9, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 581, javax.swing.GroupLayout.PREFERRED_SIZE))
        );

        jTabbedPane1.addTab("Doanh thu theo tháng", pnl_dtThang);

        jPanel1.setBackground(new java.awt.Color(255, 255, 255));

        jLabel10.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jLabel10.setText("Năm");

        jLabel11.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jLabel11.setText("Tháng");

        btn_tkNgay.setText("Thống kê");
        btn_tkNgay.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                btn_tkNgayMousePressed(evt);
            }
        });

        tbl_dtNgay.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        tbl_dtNgay.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Ngày", "Doanh thu", "Chi phí", "Lợi nhuận"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        tbl_dtNgay.setRowHeight(36);
        tbl_dtNgay.setSelectionBackground(new java.awt.Color(190, 215, 220));
        tbl_dtNgay.setSelectionForeground(new java.awt.Color(0, 0, 0));
        tbl_dtNgay.setShowGrid(true);
        tbl_dtNgay.getTableHeader().setResizingAllowed(false);
        tbl_dtNgay.getTableHeader().setReorderingAllowed(false);
        jScrollPane3.setViewportView(tbl_dtNgay);
        if (tbl_dtNgay.getColumnModel().getColumnCount() > 0) {
            tbl_dtNgay.getColumnModel().getColumn(0).setResizable(false);
            tbl_dtNgay.getColumnModel().getColumn(1).setResizable(false);
            tbl_dtNgay.getColumnModel().getColumn(2).setResizable(false);
            tbl_dtNgay.getColumnModel().getColumn(3).setResizable(false);
        }

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jScrollPane3, javax.swing.GroupLayout.Alignment.TRAILING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(328, 328, 328)
                .addComponent(jLabel10)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(txtYearDate, javax.swing.GroupLayout.PREFERRED_SIZE, 80, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel11)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(txtMonthDate, javax.swing.GroupLayout.PREFERRED_SIZE, 80, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(btn_tkNgay)
                .addContainerGap(362, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addGap(26, 26, 26)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel10, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtYearDate, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel11, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtMonthDate, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btn_tkNgay, javax.swing.GroupLayout.DEFAULT_SIZE, 36, Short.MAX_VALUE))
                .addGap(18, 18, 18)
                .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, 581, javax.swing.GroupLayout.PREFERRED_SIZE))
        );

        jTabbedPane1.addTab("Doanh thu theo ngày", jPanel1);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jTabbedPane1)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jTabbedPane1)
        );

        jTabbedPane1.getAccessibleContext().setAccessibleName("Tổng doanh thu");
    }// </editor-fold>//GEN-END:initComponents

    private void btn_tkNamMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btn_tkNamMousePressed
        if(txtYearStart.getText().equals("") || txtYearEnd.getText().equals("")) {
            JOptionPane.showMessageDialog(null, "Bạn chưa nhập đủ năm bắt đầu và năm kết thúc");
            return;
        }
        if(Integer.parseInt(txtYearStart.getText())<=2015 || Integer.parseInt(txtYearStart.getText()) > Integer.parseInt(txtYearEnd.getText())) {
            JOptionPane.showMessageDialog(null, "Năm bắt đầu phải lớn hơn 2015 và nhỏ hơn hoặc bằng năm kết thúc");
            return;
        }
        if(Integer.parseInt(txtYearEnd.getText()) > Year.now().getValue() || Integer.parseInt(txtYearStart.getText()) > Integer.parseInt(txtYearEnd.getText())) {
            JOptionPane.showMessageDialog(null, "Năm kết thúc phải nhỏ hơn năm hiện tại và lớn hơn hoặc bằng năm bắt đầu");
            return;
        }
        tkdtNamList = tkDAO.thongKeDoanhThuNam(Integer.parseInt(txtYearStart.getText()), Integer.parseInt(txtYearEnd.getText()));
        loadDTNamToTable(tkdtNamList);
    }//GEN-LAST:event_btn_tkNamMousePressed

    private void btn_tkThangMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btn_tkThangMousePressed
        if(txtYearMonth.getText().equals("")) {
            JOptionPane.showMessageDialog(null, "Bạn chưa nhập năm");
            return;
        }
        if(Integer.parseInt(txtYearMonth.getText())<=2015 || Integer.parseInt(txtYearMonth.getText()) > Year.now().getValue()) {
            JOptionPane.showMessageDialog(null, "Năm phải lớn hơn 2015 và nhỏ hơn hoặc bằng năm hiện tại");
            return;
        }
        tkdtThangList = tkDAO.thongKeDoanhThuThang(Integer.parseInt(txtYearMonth.getText()));
        loadDTThangToTable(tkdtThangList);
    }//GEN-LAST:event_btn_tkThangMousePressed

    private void btn_tkNgayMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btn_tkNgayMousePressed
        if(txtYearDate.getText().equals("") || txtMonthDate.getText().equals("")) {
            JOptionPane.showMessageDialog(null, "Bạn chưa nhập đủ năm và tháng");
            return;
        }
        if(Integer.parseInt(txtYearDate.getText())<=2015 || Integer.parseInt(txtYearDate.getText()) > Year.now().getValue()) {
            JOptionPane.showMessageDialog(null, "Năm phải lớn hơn 2015 và nhỏ hơn hoặc bằng năm hiện tại");
            return;
        }
        if(Integer.parseInt(txtMonthDate.getText()) < 1 || Integer.parseInt(txtMonthDate.getText()) > 12) {
            JOptionPane.showMessageDialog(null, "Tháng phải nằm trong khoảng từ 1 đến 12");
            return;
        }
        tkdtNgayList = tkDAO.thongKeDoanhThuNgay(Integer.parseInt(txtYearDate.getText()), Integer.parseInt(txtMonthDate.getText()));
        loadDTNgayToTable(tkdtNgayList);
    }//GEN-LAST:event_btn_tkNgayMousePressed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btn_tkNam;
    private javax.swing.JButton btn_tkNgay;
    private javax.swing.JButton btn_tkThang;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JTabbedPane jTabbedPane1;
    private javax.swing.JLabel lbl_totalEarn;
    private javax.swing.JLabel lbl_totalProfit;
    private javax.swing.JLabel lbl_totalSpent;
    private javax.swing.JPanel pnl_dtNam;
    private javax.swing.JPanel pnl_dtThang;
    private javax.swing.JPanel pnl_tongDT;
    private javax.swing.JTable tbl_dtNam;
    private javax.swing.JTable tbl_dtNgay;
    private javax.swing.JTable tbl_dtThang;
    private javax.swing.JTextField txtMonthDate;
    private javax.swing.JTextField txtYearDate;
    private javax.swing.JTextField txtYearEnd;
    private javax.swing.JTextField txtYearMonth;
    private javax.swing.JTextField txtYearStart;
    // End of variables declaration//GEN-END:variables
}
