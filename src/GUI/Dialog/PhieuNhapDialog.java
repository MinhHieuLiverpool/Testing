/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JDialog.java to edit this template
 */
package GUI.Dialog;

import BUS.CTPhieuNhapBUS;
import BUS.NhaCungCapBUS;
import BUS.NhanVienBUS;
import BUS.PhienBanSanPhamBUS;
import BUS.PhieuNhapBUS;
import BUS.SanPhamBUS;
import DAO.NhanVienDAO;
import DAO.PhieuNhapDAO;
import DTO.CTPhieuNhapDTO;
import DTO.CTSanPhamDTO;
import DTO.NhanVienDTO;
import DTO.PhienBanSanPhamDTO;
import DTO.PhieuNhapDTO;
import DTO.TaiKhoanDTO;
import GUI.Main;
import GUI.Panel.PhieuNhap;
import helper.Formatter;
import java.awt.Color;
import java.util.ArrayList;
import javax.swing.table.DefaultTableModel;
import java.sql.Timestamp;
import java.util.Date;
import java.util.HashMap;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import javax.swing.JOptionPane;

/**
 *
 * @author Admin
 */
public class PhieuNhapDialog extends javax.swing.JDialog {

    private CTPhieuNhapBUS ctpnBUS = new CTPhieuNhapBUS();
    private NhaCungCapBUS nccBUS = new NhaCungCapBUS();
    private NhanVienBUS nvBUS = new NhanVienBUS();
    private SanPhamBUS spBUS = new SanPhamBUS();
    private PhienBanSanPhamBUS pbspBUS = new PhienBanSanPhamBUS();
    private PhieuNhapDAO pnDAO = new PhieuNhapDAO();
//    private int pbspId;
    private ArrayList<CTPhieuNhapDTO> newCTPNList = new ArrayList<>();
    private PhieuNhapDTO phieuNhap;
    private TaiKhoanDTO currentUser;
    private ArrayList<CTPhieuNhapDTO> ctpnList;
    private PhieuNhapDTO newPhieuNhap;
    private long tongTien = 0;
    private int newPNId;
    private String mode;
    private PhieuNhap pnPanel;
    private Main main;
    private HashMap<Integer, String> imeiList = new HashMap<>();
    
    private ArrayList<CTSanPhamDTO> newCTSPList = new ArrayList<>();
    
    private DefaultTableModel ctpnTableModel;
    /**
     * Creates new form PhieuNhapDialog
     */
    public PhieuNhapDialog(java.awt.Frame parent, boolean modal, PhieuNhap pnPanel, PhieuNhapDTO phieuNhap, TaiKhoanDTO currentUser,String mode) {
        super(parent, modal);
        this.pnPanel = pnPanel;
        this.phieuNhap = phieuNhap;
        this.currentUser = currentUser;
        this.mode = mode;
        this.main = (Main) parent;
        initComponents();
        initComponentsCustom();
    }

    public void initComponentsCustom() {
        this.setLocationRelativeTo(null);
        suaSLBtn.setVisible(false);
        ctpnTableModel = (DefaultTableModel) ctpnTable.getModel();
        if(mode.equals("detail"))
            initDetailMode();
        if(mode.equals("add"))
            initAddMode();
    }
    
    public void initDetailMode() {
        ctpnList = ctpnBUS.getAllByID(phieuNhap.getId());
        txtMa.setText(Integer.toString(phieuNhap.getId()));
        txtMa.setFocusable(false);
        cbxNcc.removeAllItems();
        cbxNcc.addItem(nccBUS.getNameByID(phieuNhap.getIdNhaCungCap()));
        cbxNcc.setFocusable(false);
        txtNv.setText(nvBUS.getNameByID(phieuNhap.getIdNhanVien()));
        txtNv.setFocusable(false);
        txtNn.setText(Formatter.FormatDateTime(phieuNhap.getNgayNhap()));
        txtNn.setFocusable(false);
        this.tongTien = phieuNhap.getTongTien();
        txtTt.setText(Formatter.FormatVND(this.tongTien));
        txtTt.setFocusable(false);
        nhapHangBtn.setVisible(false);
        themSPBtn.setVisible(false);
        suaSLBtn.setVisible(false);
        xoaSPBtn.setVisible(false);
        loadDataToTable(ctpnList);
    }
    
    public void initAddMode() {
        NhanVienDTO currentNhanVien = NhanVienDAO.getInstance().selectByAccountId(Integer.toString(currentUser.getId()));
        newPNId = pnDAO.getAutoIncrement();
        headerLabel.setText("Nhập hàng");
        lblMa.setText("");
        txtMa.setFocusable(false);
        txtMa.setBorder(null);
        txtNv.setText(currentNhanVien.getHo() + " " + currentNhanVien.getTen());
        txtNv.setFocusable(false);
        lblNn.setText("");
        txtNn.setFocusable(false);
        txtNn.setBorder(null);
        txtTt.setText(Formatter.FormatVND(this.tongTien));
        txtTt.setFocusable(false);
    }
    
    public void loadDataToTable(ArrayList<CTPhieuNhapDTO> ctpnList) {
        ctpnTableModel.setRowCount(0);
        for(int i=0; i<ctpnList.size(); i++) {
            PhienBanSanPhamDTO pbspIndex = pbspBUS.getObjectById(ctpnList.get(i).getIdPBSanPham());
            ctpnTableModel.addRow(new Object[]{
                i+1,
                spBUS.getNameByID(pbspIndex.getIdSanPham()),
                pbspIndex.getRam(),
                pbspIndex.getRom(),
                pbspIndex.getMau(),
                ctpnList.get(i).getSoLuong(),
                Formatter.FormatVND(ctpnList.get(i).getDonGia()),
                Formatter.FormatVND(ctpnList.get(i).getTongTien())
            });
        }
    }
    
    public void setTongTien() {
        long sum=0;
        for(CTPhieuNhapDTO i : newCTPNList) {
            sum += (long) i.getTongTien();
        }
        this.tongTien = sum;
    }
    
    public PhieuNhapDTO getNewPN() {
//        new PhieuNhapDTO()
        int nccId = nccBUS.getByIndex(cbxNcc.getSelectedIndex()).getId();
        long now = System.currentTimeMillis();
        Timestamp ngayNhap = new Timestamp(now);
        return new PhieuNhapDTO(this.newPNId, nccId, this.currentUser.getIdNhanVien(), ngayNhap, (long) this.tongTien);
    }
    
    public long getTongTien() {
        setTongTien();
        return this.tongTien;
    }
    
    public int getNumberInput(String target) {
        boolean inputAccepted = false;
        int result = 0;
        while(!inputAccepted) {
            try {
                String input = JOptionPane.showInputDialog("Nhập " + target);
                if(input == null)
                    return 0;
                result = Integer.parseInt(input);
                if (result<=0) {
                    JOptionPane.showMessageDialog(this, target + " phải là số lớn hơn 0");
                    break;
                } else {
                    inputAccepted = true;
                    return result;
                }
            } catch(NumberFormatException e) {
                JOptionPane.showMessageDialog(this, target + " phải là số lớn hơn 0");
                break;
            }    
        }
        return 0;
    }
    
    public String getImei(ArrayList<CTSanPhamDTO> ctsp,int soLuong){
        boolean inputAccepted = false; 
        String result = "";
        while (!inputAccepted) {
            String input = JOptionPane.showInputDialog("Nhập IMEI cho lô sản phẩm này");
            if (input == null) {
                return "";
            }
            Pattern pattern = Pattern.compile("^[1-9]{1}[0-9]{14}$", Pattern.CASE_INSENSITIVE);
            Matcher matcher = pattern.matcher(input);
            boolean matchFound = matcher.find();
            if (!matchFound) {
                JOptionPane.showMessageDialog(this, "Imei phải là số có 15 chữ số và không bắt đầu bằng số 0");
            } else {
                inputAccepted = true;
                //check imei exsited

                for (int i = 0; i < soLuong; i++) {
                    String newimei = String.valueOf(Long.parseLong(input) + i);
                    for (CTSanPhamDTO j : ctsp) {
                        if (j.getImei().equals(newimei)) {
                            JOptionPane.showMessageDialog(this, "Imei '" + newimei + "' đã tồn tại");
                            inputAccepted = false;
                            break;
                        }
                        if (!inputAccepted)
                            break;
                    }
                }
                
                if (inputAccepted)
                    return input;
            }
        }
        
        
        return result;
    }
    
    public int getCTPNIndexByPBSPId(int id) {
        for(int i=0; i<newCTPNList.size(); i++) {
            if(newCTPNList.get(i).getIdPBSanPham() == id)
                return i;
        }
        return -1;
    }
    
    public boolean isExisted(int id) {
        for(CTPhieuNhapDTO i : newCTPNList) {
            if(i.getIdPBSanPham() == id)
                return true;
        }
        return false;
    }
        
    public void reloadEvent() {
        loadDataToTable(newCTPNList);
        txtTt.setText(Formatter.FormatVND(getTongTien()));
    }
    
    public void generateNewCTSPList() {
        for (CTPhieuNhapDTO i : this.newCTPNList) {
            String imei = imeiList.get(i.getIdPBSanPham());
            PhienBanSanPhamDTO pbsp = pbspBUS.getObjectById(i.getIdPBSanPham());
            for (int j = 0; j < i.getSoLuong(); j++) {
                String newimei = String.valueOf(Long.parseLong(imei) + j);
                CTSanPhamDTO newCTSP = new CTSanPhamDTO(newimei, pbsp.getIdSanPham(), pbsp.getId(), this.newPNId, i.getDonGia(), 1);
                this.newCTSPList.add(newCTSP);
            }
        }
    }
    
    public void addPNEvent() {
        newPhieuNhap = getNewPN();
        generateNewCTSPList();
        if (pnPanel.pnBUS.addNewPNWithCTSPList(newPhieuNhap, newCTPNList, newCTSPList)) {
            JOptionPane.showMessageDialog(this, "Nhập hàng thành công !");
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

        headerPanel = new javax.swing.JPanel();
        headerLabel = new javax.swing.JLabel();
        jPanel2 = new javax.swing.JPanel();
        txtMa = new javax.swing.JTextField();
        lblMa = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        txtNv = new javax.swing.JTextField();
        jLabel3 = new javax.swing.JLabel();
        txtNn = new javax.swing.JTextField();
        lblNn = new javax.swing.JLabel();
        txtTt = new javax.swing.JTextField();
        jLabel5 = new javax.swing.JLabel();
        nhapHangBtn = new javax.swing.JButton();
        jButton2 = new javax.swing.JButton();
        jScrollPane1 = new javax.swing.JScrollPane();
        ctpnTable = new javax.swing.JTable();
        themSPBtn = new javax.swing.JButton();
        cbxNcc = new javax.swing.JComboBox(nccBUS.getStringList());
        suaSLBtn = new javax.swing.JButton();
        xoaSPBtn = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setBackground(new java.awt.Color(255, 255, 255));

        headerPanel.setBackground(new java.awt.Color(88, 175, 232));
        headerPanel.setMinimumSize(new java.awt.Dimension(100, 50));
        headerPanel.setPreferredSize(new java.awt.Dimension(700, 50));

        headerLabel.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        headerLabel.setForeground(new java.awt.Color(255, 255, 255));
        headerLabel.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        headerLabel.setText("Chi tiết phiếu nhập");

        javax.swing.GroupLayout headerPanelLayout = new javax.swing.GroupLayout(headerPanel);
        headerPanel.setLayout(headerPanelLayout);
        headerPanelLayout.setHorizontalGroup(
            headerPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(headerLabel, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 950, Short.MAX_VALUE)
        );
        headerPanelLayout.setVerticalGroup(
            headerPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(headerLabel, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 50, Short.MAX_VALUE)
        );

        getContentPane().add(headerPanel, java.awt.BorderLayout.PAGE_START);

        jPanel2.setBackground(new java.awt.Color(255, 255, 255));

        lblMa.setText("Mã");

        jLabel2.setText("Nhà cung cấp");

        jLabel3.setText("Nhân viên nhập");

        lblNn.setText("Ngày nhập");

        jLabel5.setText("Tổng tiền");

        nhapHangBtn.setBackground(new java.awt.Color(61, 220, 151));
        nhapHangBtn.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        nhapHangBtn.setForeground(new java.awt.Color(255, 255, 255));
        nhapHangBtn.setText("Nhập hàng");
        nhapHangBtn.setBorder(null);
        nhapHangBtn.setPreferredSize(new java.awt.Dimension(120, 40));
        nhapHangBtn.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                nhapHangBtnMousePressed(evt);
            }
        });

        jButton2.setBackground(new java.awt.Color(255, 107, 107));
        jButton2.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jButton2.setForeground(new java.awt.Color(255, 255, 255));
        jButton2.setText("Đóng");
        jButton2.setBorder(null);
        jButton2.setPreferredSize(new java.awt.Dimension(120, 40));
        jButton2.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                jButton2MousePressed(evt);
            }
        });

        ctpnTable.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "STT", "Tên sản phẩm", "RAM", "ROM", "Màu", "Số lượng", "Đơn giá", "Tổng tiền"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false, false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        ctpnTable.setFocusable(false);
        ctpnTable.setRowHeight(32);
        ctpnTable.setSelectionBackground(new java.awt.Color(190, 215, 220));
        ctpnTable.setShowGrid(true);
        ctpnTable.getTableHeader().setResizingAllowed(false);
        ctpnTable.getTableHeader().setReorderingAllowed(false);
        jScrollPane1.setViewportView(ctpnTable);
        if (ctpnTable.getColumnModel().getColumnCount() > 0) {
            ctpnTable.getColumnModel().getColumn(0).setResizable(false);
            ctpnTable.getColumnModel().getColumn(0).setPreferredWidth(10);
            ctpnTable.getColumnModel().getColumn(1).setResizable(false);
            ctpnTable.getColumnModel().getColumn(1).setPreferredWidth(150);
            ctpnTable.getColumnModel().getColumn(2).setResizable(false);
            ctpnTable.getColumnModel().getColumn(2).setPreferredWidth(30);
            ctpnTable.getColumnModel().getColumn(3).setResizable(false);
            ctpnTable.getColumnModel().getColumn(3).setPreferredWidth(30);
            ctpnTable.getColumnModel().getColumn(4).setResizable(false);
            ctpnTable.getColumnModel().getColumn(4).setPreferredWidth(30);
            ctpnTable.getColumnModel().getColumn(5).setResizable(false);
            ctpnTable.getColumnModel().getColumn(5).setPreferredWidth(30);
            ctpnTable.getColumnModel().getColumn(6).setResizable(false);
            ctpnTable.getColumnModel().getColumn(7).setResizable(false);
        }

        themSPBtn.setBackground(new java.awt.Color(102, 204, 255));
        themSPBtn.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        themSPBtn.setForeground(new java.awt.Color(255, 255, 255));
        themSPBtn.setText("Thêm lô sản phẩm");
        themSPBtn.setBorder(null);
        themSPBtn.setPreferredSize(new java.awt.Dimension(120, 40));
        themSPBtn.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                themSPBtnMousePressed(evt);
            }
        });

        suaSLBtn.setBackground(new java.awt.Color(255, 230, 109));
        suaSLBtn.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        suaSLBtn.setForeground(new java.awt.Color(255, 255, 255));
        suaSLBtn.setText("Sửa số lượng");
        suaSLBtn.setBorder(null);
        suaSLBtn.setPreferredSize(new java.awt.Dimension(120, 40));
        suaSLBtn.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                suaSLBtnMousePressed(evt);
            }
        });

        xoaSPBtn.setBackground(new java.awt.Color(26, 83, 92));
        xoaSPBtn.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        xoaSPBtn.setForeground(new java.awt.Color(255, 255, 255));
        xoaSPBtn.setText("Xóa lô sản phẩm");
        xoaSPBtn.setBorder(null);
        xoaSPBtn.setPreferredSize(new java.awt.Dimension(120, 40));
        xoaSPBtn.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                xoaSPBtnMousePressed(evt);
            }
        });

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(jScrollPane1))
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGap(36, 36, 36)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(lblMa)
                            .addComponent(txtMa, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel2Layout.createSequentialGroup()
                                .addComponent(jLabel2)
                                .addGap(0, 261, Short.MAX_VALUE))
                            .addComponent(cbxNcc, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel3)
                            .addComponent(txtNv, javax.swing.GroupLayout.PREFERRED_SIZE, 170, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(lblNn)
                            .addComponent(txtNn, javax.swing.GroupLayout.PREFERRED_SIZE, 164, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(txtTt, javax.swing.GroupLayout.PREFERRED_SIZE, 170, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel5)))
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(themSPBtn, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(suaSLBtn, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(xoaSPBtn, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 203, Short.MAX_VALUE)
                        .addComponent(nhapHangBtn, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jButton2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(12, 12, 12)))
                .addContainerGap())
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addComponent(lblNn)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(txtNn))
                    .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                        .addGroup(jPanel2Layout.createSequentialGroup()
                            .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addComponent(lblMa, javax.swing.GroupLayout.Alignment.TRAILING)
                                .addComponent(jLabel2))
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                            .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                .addComponent(txtMa, javax.swing.GroupLayout.DEFAULT_SIZE, 40, Short.MAX_VALUE)
                                .addComponent(cbxNcc)))
                        .addGroup(jPanel2Layout.createSequentialGroup()
                            .addComponent(jLabel5)
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                            .addComponent(txtTt, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addComponent(jLabel3)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(txtNv)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 405, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE, false)
                    .addComponent(themSPBtn, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(suaSLBtn, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(xoaSPBtn, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jButton2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(nhapHangBtn, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap())
        );

        getContentPane().add(jPanel2, java.awt.BorderLayout.CENTER);

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jButton2MousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jButton2MousePressed
        dispose();
    }//GEN-LAST:event_jButton2MousePressed

    private void themSPBtnMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_themSPBtnMousePressed
        String[] option = {"Tạo sản phẩm mới", "Chọn sản phẩm", "Hủy"};
        int selection = JOptionPane.showOptionDialog(null, "Bạn muốn nhập sản phẩm mới hay chọn sản phẩm có sẵn?", "", 0, 3, null, option, option[2]);
        if(selection == 0) {
            ThemSuaSanPhamDialog addSpDialog = new ThemSuaSanPhamDialog(null, true, "Thêm sản phẩm", null, "add", null, null);
            addSpDialog.setVisible(true);
            PhienBanSanPhamBUS newPbspBUS = new PhienBanSanPhamBUS();
            pbspBUS = newPbspBUS;
            SanPhamBUS newspBUS = new SanPhamBUS();
            spBUS = newspBUS;
            main.sanPham.sanPhamList = spBUS.getAll();
            main.sanPham.loadDataToTable(main.sanPham.sanPhamList);
        }
        if(selection == 1) {
            ChonSanPhamDialog dialog = new ChonSanPhamDialog(pnPanel.main, true, null, null, "nhap");
            dialog.setVisible(true);
            try {
                if(dialog.choosen == false) return;
                int pbspId = dialog.getSelectedId();
                if(pbspId == -1)
                    return;
                int soLuong = getNumberInput("Số lượng");
                if(soLuong == 0)
                    return;
                if(isExisted(pbspId)) {
                    CTPhieuNhapDTO duplicatedSP = newCTPNList.get(getCTPNIndexByPBSPId(pbspId));
                    int newSoLuong =  duplicatedSP.getSoLuong() + soLuong;
                    long newTongTien = duplicatedSP.getDonGia() * newSoLuong;
                    duplicatedSP.setSoLuong(newSoLuong);
                    duplicatedSP.setTongTien(newTongTien);
                    newCTPNList.set(getCTPNIndexByPBSPId(pbspId), duplicatedSP);
                    reloadEvent();
                    return;
                }
                int giaNhap = getNumberInput("Giá nhập");
                if (giaNhap == 0)
                    return;
                String imei = getImei(newCTSPList,soLuong);
//                PhienBanSanPhamDTO pbsp = pbspBUS.getObjectById(pbspId);
                this.imeiList.put(pbspId, imei);
//                for(int i=0; i<soLuong; i++) {
//                    String newimei = String.valueOf(Long.parseLong(imei) + i);
//                    CTSanPhamDTO newCTSP = new CTSanPhamDTO(newimei, pbsp.getIdSanPham(), pbspId, this.newPNId, giaNhap, 1);
//                    this.newCTSPList.add(newCTSP);
//                }
                
                this.tongTien += (long) giaNhap*soLuong;
                newCTPNList.add(new CTPhieuNhapDTO(this.newPNId, pbspId, soLuong, giaNhap, (long) giaNhap*soLuong));
                loadDataToTable(newCTPNList);
                txtTt.setText(Formatter.FormatVND(getTongTien()));
            } catch (NumberFormatException e) {
//                System.out.println(e);
//                return;
            }
        }
    }//GEN-LAST:event_themSPBtnMousePressed

    private void xoaSPBtnMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_xoaSPBtnMousePressed
        int index = ctpnTable.getSelectedRow();
        if (index == -1) {
            JOptionPane.showMessageDialog(this, "Bạn chưa chọn sản phẩm để xóa");
            return;
        } else {
            if (JOptionPane.showConfirmDialog(this, "Bạn có chắc muốn xóa sản phẩm này không?", "", JOptionPane.YES_NO_OPTION) == 0) {
                newCTPNList.remove(index);
                reloadEvent();
            }
        }
    }//GEN-LAST:event_xoaSPBtnMousePressed

    private void nhapHangBtnMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_nhapHangBtnMousePressed
        if(ctpnTable.getRowCount() == 0){
            JOptionPane.showMessageDialog(this, "Phải có ít nhất 1 sản phẩm để tạo phiếu nhập!");
            return;
        }
        if(JOptionPane.showConfirmDialog(this, "Phiếu nhập sẽ không thể hủy, bạn có chắc muốn nhập hàng không?", "", JOptionPane.YES_NO_OPTION)== 0)
            addPNEvent();
    }//GEN-LAST:event_nhapHangBtnMousePressed

    private void suaSLBtnMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_suaSLBtnMousePressed
        int index = ctpnTable.getSelectedRow();
        if (index == -1) {
            JOptionPane.showMessageDialog(this, "Bạn chưa chọn sản phẩm để sửa");
            return;
        } else {
            try {
                int soLuong = getNumberInput("Số lượng");
                if(soLuong == 0)
                return;
                newCTPNList.get(index).setSoLuong(soLuong);
                newCTPNList.get(index).setTongTien(soLuong * newCTPNList.get(index).getDonGia());
                reloadEvent();
            } catch (Exception e) {
                return;
            }
        }
    }//GEN-LAST:event_suaSLBtnMousePressed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JComboBox<String> cbxNcc;
    private javax.swing.JTable ctpnTable;
    private javax.swing.JLabel headerLabel;
    private javax.swing.JPanel headerPanel;
    private javax.swing.JButton jButton2;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JLabel lblMa;
    private javax.swing.JLabel lblNn;
    private javax.swing.JButton nhapHangBtn;
    private javax.swing.JButton suaSLBtn;
    private javax.swing.JButton themSPBtn;
    private javax.swing.JTextField txtMa;
    private javax.swing.JTextField txtNn;
    private javax.swing.JTextField txtNv;
    private javax.swing.JTextField txtTt;
    private javax.swing.JButton xoaSPBtn;
    // End of variables declaration//GEN-END:variables
}
