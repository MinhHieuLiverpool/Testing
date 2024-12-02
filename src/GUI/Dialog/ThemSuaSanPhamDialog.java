/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JDialog.java to edit this template
 */
package GUI.Dialog;

import BUS.PhienBanSanPhamBUS;
import BUS.SanPhamBUS;
import BUS.ThuongHieuBUS;
import DAO.PhienBanSanPhamDAO;
import DTO.NhanVienDTO;
import DTO.PhienBanSanPhamDTO;
import DTO.SanPhamDTO;
import DTO.ThuongHieuDTO;
import GUI.Panel.SanPham;
import helper.Validator;
import java.awt.CardLayout;
import java.awt.FileDialog;
import java.awt.Frame;
import java.awt.Image;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Locale;
import java.util.Random;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.imageio.ImageIO;
import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JComboBox;
import javax.swing.JFileChooser;
import javax.swing.JOptionPane;
import javax.swing.filechooser.FileNameExtensionFilter;
import javax.swing.filechooser.FileSystemView;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author DVN
 */
public class ThemSuaSanPhamDialog extends javax.swing.JDialog {

    private CardLayout cardLayout;
    private DefaultTableModel pbspModel;
    private String imageURL;
    private SanPhamBUS spBUS = new SanPhamBUS();
    private PhienBanSanPhamBUS pbspBUS = new PhienBanSanPhamBUS();
    private ThuongHieuBUS thBUS = new ThuongHieuBUS();
    private ArrayList<ThuongHieuDTO> thList = thBUS.getAll();
    private ArrayList<SanPhamDTO> spList = spBUS.getAll();
    private SanPhamDTO newSP;
    private ArrayList<PhienBanSanPhamDTO> newPBSPList = new ArrayList<>();
    private ArrayList<PhienBanSanPhamDTO> pbspEditList;
    private SanPhamDTO spEdit;
    private SanPham panelSanPham;
    private int newSPID;
    private int newPBSPID;
    private String mode;
    private boolean newPBAdded = false;
    private boolean imageChanged = false;
    private NhanVienDTO nhanvien;

    /**
     * Creates new form ThemSanPhamDialog
     */
    public ThemSuaSanPhamDialog(java.awt.Frame parent, boolean modal) {
        super(parent, modal);
        initComponents();
        this.setLocationRelativeTo(null);
    }

    public ThemSuaSanPhamDialog(java.awt.Frame parent, boolean modal, String title, SanPham panelSanPham) {
        super(parent, modal);
        this.setTitle(title);
        this.panelSanPham = panelSanPham;
        initComponents();
        initComponentsCustom();
        this.setLocationRelativeTo(null);
    }

    public ThemSuaSanPhamDialog(java.awt.Frame parent, boolean modal, String title, SanPham panelSanPham, String mode, SanPhamDTO spEdit, ArrayList<PhienBanSanPhamDTO> pbspEditList) {
        super(parent, modal);
        this.setTitle(title);
        this.panelSanPham = panelSanPham;
        this.mode = mode;
        this.spEdit = spEdit;
        this.pbspEditList = pbspEditList;
        initComponents();
        initComponentsCustom();
        this.setLocationRelativeTo(null);
    }

    public void initComponentsCustom() {
        newSPID = spBUS.spDAO.getAutoIncrement();
        newPBSPID = PhienBanSanPhamDAO.getInstance().getAutoIncrement();
        cardLayout = (CardLayout) contentPanel.getLayout();
        pbspModel = (DefaultTableModel) pbspTable.getModel();
        pbspModel.setRowCount(0);
        imgUploadBtn.addMouseListener(new MouseAdapter() {

            public void mousePressed(MouseEvent e) {
                JFileChooser jfc;
                jfc = new JFileChooser(FileSystemView.getFileSystemView().getHomeDirectory());
                jfc.setAcceptAllFileFilterUsed(false);
                FileNameExtensionFilter filter = new FileNameExtensionFilter("PNG and GIF images", "png", "gif", "jpg", "jpeg");
                jfc.addChoosableFileFilter(filter);
                int returnValue = jfc.showOpenDialog(null);
                if (returnValue == JFileChooser.APPROVE_OPTION) {
                    imageURL = (String) jfc.getSelectedFile().getPath();
                    File file = jfc.getSelectedFile();
                    ImageIcon imageIcon = new ImageIcon(new javax.swing.ImageIcon(String.valueOf(jfc.getSelectedFile())).getImage().getScaledInstance(300, 300, Image.SCALE_SMOOTH));
                    BufferedImage b;
                    try {
                        b = ImageIO.read(file);
                        hinhAnhSP.setIcon(imageIcon);
                    } catch (IOException ex) {
                        Logger.getLogger(ThemSuaSanPhamDialog.class.getName()).log(Level.SEVERE, null, ex);
                    }
                }
                if (mode.equals("edit")) {
                    imageChanged = true;
                }
            }

        });

        if (mode.equals("edit")) {
            imageURL = "/image/product/" + this.spEdit.getHinhAnh();
            hinhAnhSP.setIcon(new ImageIcon(new javax.swing.ImageIcon(getClass().getResource("/image/product/" + this.spEdit.getHinhAnh())).getImage().getScaledInstance(300, 300, Image.SCALE_SMOOTH)));
            txtTenSP.setText(this.spEdit.getTen());
            cbxThuongHieu.setSelectedItem(thBUS.getNameByID(this.spEdit.getIdThuongHieu()));
            txtKTMH.setText(Float.toString(this.spEdit.getKichThuocMan()));
            txtCamTruoc.setText(this.spEdit.getCameraTruoc());
            txtCamSau.setText(this.spEdit.getCameraSau());
            txtDLPin.setText(Integer.toString(this.spEdit.getDungLuongPin()));
            txtChipXL.setText(this.spEdit.getChipXuLy());
            txtHDH.setText(this.spEdit.getHeDieuHanh());
            loadPBSPListToTable(this.pbspEditList);
            txtTenSP.setEnabled(false);
            cbxThuongHieu.setEnabled(false);
            themSPBtn.setText("Lưu thông tin");
            themPBSPBtn.setText("Sửa phiên bản sản phẩm");
            jLabel1.setText("Sửa thông tin sản phẩm");
        }
    }

    public void loadPBSPListToTable(ArrayList<PhienBanSanPhamDTO> pbspList) {
        pbspModel.setRowCount(0);
        for (int i = 0; i < pbspList.size(); i++) {
            if (pbspList.get(i).getTrangThai() == 1) {
                pbspModel.addRow(new Object[]{i + 1, pbspList.get(i).getRam()+"GB", pbspList.get(i).getRom()+"GB", pbspList.get(i).getMau()});
            }
        }
    }

    public void emptyPBSPInput() {
        txtRam.setText("");
        txtRom.setText("");
        txtMau.setText("");
    }

    public String addImage(String imageURL) {
        Random randomGenerator = new Random();
        int rand = randomGenerator.nextInt(1000);
        File sourceFile = new File(imageURL);
        String destPath = "./src/image/product";
        File destFolder = new File(destPath);
        String newName = rand + sourceFile.getName();
        try {
            Path dest = Paths.get(destFolder.getPath(), newName);
            Files.copy(sourceFile.toPath(), dest);
            if (mode.equals("edit")) {
                Path oldDest = Paths.get(destPath, this.spEdit.getHinhAnh());
                Files.delete(oldDest);
            }
        } catch (IOException e) {
        }
        return newName;
    }

    public SanPhamDTO getNewSP() {
        String ten = txtTenSP.getText();
        String hinhAnh = addImage(imageURL);
        float kichThuocMan = Float.parseFloat(txtKTMH.getText());
        String camSau = txtCamSau.getText();
        String camTruoc = txtCamTruoc.getText();
        String chipXuLy = txtChipXL.getText();
        String heDieuHanh = txtHDH.getText();
        int dungLuongPin = Integer.parseInt(txtDLPin.getText());
        int idThuongHieu = thBUS.getByIndex(cbxThuongHieu.getSelectedIndex()).getId();
        return new SanPhamDTO(newSPID, ten, hinhAnh, kichThuocMan, camSau, camTruoc, chipXuLy, heDieuHanh, dungLuongPin, idThuongHieu, 1);
    }

    public PhienBanSanPhamDTO getNewPBSP() {
        int ram = Integer.parseInt(txtRam.getText());
        int rom = Integer.parseInt(txtRom.getText());
        String mau = txtMau.getText();
        newPBSPID++;
        if(mode.equals("edit")) {
            return new PhienBanSanPhamDTO(newPBSPID, spEdit.getId(), ram, rom, mau, 0, 1);
        }
        return new PhienBanSanPhamDTO(newPBSPID, newSPID, ram, rom, mau, 0, 1);
    }

    public void setEditedSP() {
        this.spEdit.setTen(txtTenSP.getText());
        if (imageChanged) {
            this.spEdit.setHinhAnh(addImage(imageURL));
        }
        this.spEdit.setKichThuocMan(Float.parseFloat(txtKTMH.getText()));
        this.spEdit.setCameraSau(txtCamSau.getText());
        this.spEdit.setCameraTruoc(txtCamTruoc.getText());
        this.spEdit.setChipXuLy(txtChipXL.getText());
        this.spEdit.setHeDieuHanh(txtHDH.getText());
        this.spEdit.setDungLuongPin(Integer.parseInt(txtDLPin.getText()));
        this.spEdit.setIdThuongHieu(thBUS.getByIndex(cbxThuongHieu.getSelectedIndex()).getId());
    }

    public boolean ValidateSPInput() {
        if (Validator.isEmpty(txtTenSP.getText())) {
            JOptionPane.showMessageDialog(this, "Bạn chưa nhập tên sản phẩm");
            return false;
        }
        if (Validator.isEmpty(txtKTMH.getText())) {
            JOptionPane.showMessageDialog(this, "Bạn chưa nhập kích thước màn hình");
            return false;
        }
        if (Validator.isEmpty(txtCamTruoc.getText())) {
            JOptionPane.showMessageDialog(this, "Bạn chưa nhập thông số camera trước");
            return false;
        }
        if (Validator.isEmpty(txtCamSau.getText())) {
            JOptionPane.showMessageDialog(this, "Bạn chưa nhập thông số camera sau");
            return false;
        }
        if (Validator.isEmpty(txtDLPin.getText())) {
            JOptionPane.showMessageDialog(this, "Bạn chưa nhập dung lượng pin");
            return false;
        }
        if (Validator.isEmpty(txtChipXL.getText())) {
            JOptionPane.showMessageDialog(this, "Bạn chưa nhập chip xử lý");
            return false;
        }
        if (Validator.isEmpty(txtHDH.getText())) {
            JOptionPane.showMessageDialog(this, "Bạn chưa nhập hệ điều hành");
            return false;
        }
        if (!Validator.isFloat(txtKTMH.getText())) {
            JOptionPane.showMessageDialog(this, "Kích thước màn hình phải là số dương");
            return false;
        } else if (Float.parseFloat(txtKTMH.getText()) <= 1.99 || Float.parseFloat(txtKTMH.getText()) > 10.0) {
            JOptionPane.showMessageDialog(this, "Kích thước màn hình phải từ 2 đến 10");
            return false;
        }
        if (!Validator.isInteger(txtDLPin.getText())) {
            JOptionPane.showMessageDialog(this, "Dung lượng pin phải là số nguyên dương");
            return false;
        } else if (Integer.parseInt(txtDLPin.getText()) <= 0) {
            JOptionPane.showMessageDialog(this, "Dung lượng pin phải lớn hơn 0");
            return false;
        }
        if (imageURL == null) {
            JOptionPane.showMessageDialog(this, "Bạn chưa chọn hình ảnh sản phẩm");
            return false;
        }
        return true;
    }

    public boolean ValidatePBSPInput() {
        if (Validator.isEmpty(txtRam.getText())) {
            JOptionPane.showMessageDialog(this, "Bạn chưa nhập dung lượng ram");
            return false;
        }
        if (Validator.isEmpty(txtRom.getText())) {
            JOptionPane.showMessageDialog(this, "Bạn chưa nhập dung lượng rom");
            return false;
        }
        if (Validator.isEmpty(txtMau.getText())) {
            JOptionPane.showMessageDialog(this, "Bạn chưa nhập màu");
            return false;
        }
        if (!Validator.isInteger(txtRam.getText())) {
            JOptionPane.showMessageDialog(this, "Dung lượng ram phải là số nguyên dương");
            return false;
        } else if(Integer.parseInt(txtRam.getText()) <= 0) {
            JOptionPane.showMessageDialog(this, "Dung lượng ram phải lớn hơn 0");
            return false;
        }
        if (!Validator.isInteger(txtRom.getText())) {
            JOptionPane.showMessageDialog(this, "Dung lượng rom phải là số nguyên dương");
            return false;
        } else if(Integer.parseInt(txtRom.getText()) <= 0) {
            JOptionPane.showMessageDialog(this, "Dung lượng rom phải lớn hơn 0");
            return false;            
        }
        return true;
    }

    public boolean ValidateSPDuplication() {
        for (SanPhamDTO i : spList) {
            if (i.getTen().equals(txtTenSP.getText())) {
                JOptionPane.showMessageDialog(this, "Sản phẩm đã tồn tại !");
                return false;
            }
        }
        return true;
    }

    public boolean ValidatePBSPDuplication() {
        for (PhienBanSanPhamDTO i : this.newPBSPList) {
            if (i.getRam() == Integer.parseInt(txtRam.getText()) && i.getRom() == Integer.parseInt(txtRom.getText()) && i.getMau().equals(txtMau.getText())) {
                JOptionPane.showMessageDialog(this, "Phiên bản này đã tồn tại !");
                return false;
            }
        }
        return true;
    }

    public void addSPEvent() {
        this.newSP = getNewSP();
        if (spBUS.addNewSPWithPBSPList(newSP, this.newPBSPList)) {
            JOptionPane.showMessageDialog(this, "Thêm sản phẩm thành công !");
            dispose();
        }
    }

    public void editSPEvent() {
        setEditedSP();
        if(newPBAdded == true)
            pbspBUS.addNewPBSPList(newPBSPList);
        if(panelSanPham.spBUS.updateWithPBSPList(spEdit, pbspEditList)) {
            JOptionPane.showMessageDialog(this, "Sửa sản phẩm thành công !");
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
        jLabel1 = new javax.swing.JLabel();
        contentPanel = new javax.swing.JPanel();
        card1 = new javax.swing.JPanel();
        hinhAnhSP = new javax.swing.JLabel();
        imgUploadBtn = new javax.swing.JButton();
        jLabel2 = new javax.swing.JLabel();
        txtTenSP = new javax.swing.JTextField();
        jLabel3 = new javax.swing.JLabel();
        txtKTMH = new javax.swing.JTextField();
        jLabel4 = new javax.swing.JLabel();
        txtChipXL = new javax.swing.JTextField();
        jLabel12 = new javax.swing.JLabel();
        txtCamTruoc = new javax.swing.JTextField();
        jLabel13 = new javax.swing.JLabel();
        txtCamSau = new javax.swing.JTextField();
        jLabel14 = new javax.swing.JLabel();
        txtHDH = new javax.swing.JTextField();
        jLabel16 = new javax.swing.JLabel();
        txtDLPin = new javax.swing.JTextField();
        jLabel17 = new javax.swing.JLabel();
        themPBSPBtn = new javax.swing.JButton();
        cbxThuongHieu = new javax.swing.JComboBox(thBUS.getStringList());
        themSPBtn = new javax.swing.JButton();
        card2 = new javax.swing.JPanel();
        jPanel1 = new javax.swing.JPanel();
        jLabel15 = new javax.swing.JLabel();
        txtRam = new javax.swing.JTextField();
        jLabel18 = new javax.swing.JLabel();
        txtRom = new javax.swing.JTextField();
        jLabel6 = new javax.swing.JLabel();
        txtMau = new javax.swing.JTextField();
        jScrollPane1 = new javax.swing.JScrollPane();
        pbspTable = new javax.swing.JTable();
        thempbBtn = new javax.swing.JButton();
        suapbBtn = new javax.swing.JButton();
        xoapbBtn = new javax.swing.JButton();
        quayLaiBtn = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);

        headerPanel.setBackground(new java.awt.Color(88, 175, 232));
        headerPanel.setPreferredSize(new java.awt.Dimension(1000, 100));

        jLabel1.setFont(new java.awt.Font("Segoe UI", 1, 16)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(255, 255, 255));
        jLabel1.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel1.setText("THÊM SẢN PHẨM");
        jLabel1.setMaximumSize(new java.awt.Dimension(300, 50));
        jLabel1.setMinimumSize(new java.awt.Dimension(300, 50));
        jLabel1.setPreferredSize(new java.awt.Dimension(300, 50));

        javax.swing.GroupLayout headerPanelLayout = new javax.swing.GroupLayout(headerPanel);
        headerPanel.setLayout(headerPanelLayout);
        headerPanelLayout.setHorizontalGroup(
            headerPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jLabel1, javax.swing.GroupLayout.DEFAULT_SIZE, 1000, Short.MAX_VALUE)
        );
        headerPanelLayout.setVerticalGroup(
            headerPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, headerPanelLayout.createSequentialGroup()
                .addContainerGap(25, Short.MAX_VALUE)
                .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(25, 25, 25))
        );

        getContentPane().add(headerPanel, java.awt.BorderLayout.PAGE_START);

        contentPanel.setBackground(new java.awt.Color(255, 255, 255));
        contentPanel.setPreferredSize(new java.awt.Dimension(1000, 380));
        contentPanel.setLayout(new java.awt.CardLayout());

        card1.setBackground(new java.awt.Color(255, 255, 255));
        card1.setPreferredSize(new java.awt.Dimension(1000, 380));

        hinhAnhSP.setPreferredSize(new java.awt.Dimension(300, 300));

        imgUploadBtn.setBackground(new java.awt.Color(102, 204, 255));
        imgUploadBtn.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        imgUploadBtn.setForeground(new java.awt.Color(255, 255, 255));
        imgUploadBtn.setText("Chọn ảnh");
        imgUploadBtn.setBorder(null);
        imgUploadBtn.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));

        jLabel2.setText("Tên sản phẩm");
        jLabel2.setPreferredSize(new java.awt.Dimension(80, 20));

        jLabel3.setText("Kích thước màn hình");
        jLabel3.setPreferredSize(new java.awt.Dimension(80, 20));

        jLabel4.setText("Chip xử lí");
        jLabel4.setPreferredSize(new java.awt.Dimension(80, 20));

        jLabel12.setText("Camera trước");
        jLabel12.setPreferredSize(new java.awt.Dimension(80, 20));

        jLabel13.setText("Camera sau");
        jLabel13.setPreferredSize(new java.awt.Dimension(80, 20));

        jLabel14.setText("Hệ điều hành");
        jLabel14.setPreferredSize(new java.awt.Dimension(80, 20));

        jLabel16.setText("Dung lượng pin");
        jLabel16.setPreferredSize(new java.awt.Dimension(80, 20));

        jLabel17.setText("Thương hiệu");
        jLabel17.setPreferredSize(new java.awt.Dimension(80, 20));

        themPBSPBtn.setBackground(new java.awt.Color(247, 179, 43));
        themPBSPBtn.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        themPBSPBtn.setForeground(new java.awt.Color(255, 255, 255));
        themPBSPBtn.setText("Thêm phiên bản sản phẩm");
        themPBSPBtn.setBorder(null);
        themPBSPBtn.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        themPBSPBtn.setFocusPainted(false);
        themPBSPBtn.setPreferredSize(new java.awt.Dimension(180, 50));
        themPBSPBtn.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                themPBSPBtnMousePressed(evt);
            }
        });

        themSPBtn.setBackground(new java.awt.Color(61, 220, 151));
        themSPBtn.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        themSPBtn.setForeground(new java.awt.Color(255, 255, 255));
        themSPBtn.setText("Thêm sản phẩm");
        themSPBtn.setBorder(null);
        themSPBtn.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        themSPBtn.setPreferredSize(new java.awt.Dimension(180, 50));
        themSPBtn.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                themSPBtnMousePressed(evt);
            }
        });

        javax.swing.GroupLayout card1Layout = new javax.swing.GroupLayout(card1);
        card1.setLayout(card1Layout);
        card1Layout.setHorizontalGroup(
            card1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(card1Layout.createSequentialGroup()
                .addGroup(card1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(card1Layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(hinhAnhSP, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(card1Layout.createSequentialGroup()
                        .addGap(103, 103, 103)
                        .addComponent(imgUploadBtn, javax.swing.GroupLayout.PREFERRED_SIZE, 115, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGroup(card1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(card1Layout.createSequentialGroup()
                        .addGap(18, 18, 18)
                        .addGroup(card1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(card1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                .addComponent(txtCamTruoc, javax.swing.GroupLayout.PREFERRED_SIZE, 200, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(jLabel12, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(card1Layout.createSequentialGroup()
                                .addGroup(card1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addComponent(jLabel16, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(txtTenSP)
                                    .addComponent(txtDLPin, javax.swing.GroupLayout.PREFERRED_SIZE, 200, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGap(23, 23, 23)
                                .addGroup(card1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(txtCamSau)
                                    .addComponent(txtChipXL, javax.swing.GroupLayout.Alignment.TRAILING)
                                    .addGroup(card1Layout.createSequentialGroup()
                                        .addGroup(card1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addComponent(jLabel4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                            .addComponent(jLabel17, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                            .addComponent(jLabel13, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                                        .addGap(0, 120, Short.MAX_VALUE))
                                    .addComponent(cbxThuongHieu, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))))
                        .addGap(18, 18, 18)
                        .addGroup(card1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(jLabel14, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, 120, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(txtKTMH)
                            .addComponent(txtHDH, javax.swing.GroupLayout.PREFERRED_SIZE, 180, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(49, 49, 49))
                    .addGroup(card1Layout.createSequentialGroup()
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(themSPBtn, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(174, 174, 174)
                        .addComponent(themPBSPBtn, javax.swing.GroupLayout.PREFERRED_SIZE, 200, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addContainerGap())))
        );
        card1Layout.setVerticalGroup(
            card1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(card1Layout.createSequentialGroup()
                .addGap(12, 12, 12)
                .addGroup(card1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(hinhAnhSP, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(card1Layout.createSequentialGroup()
                        .addGroup(card1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(card1Layout.createSequentialGroup()
                                .addComponent(jLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(txtKTMH, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(card1Layout.createSequentialGroup()
                                .addGroup(card1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jLabel17, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(card1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addComponent(txtTenSP, javax.swing.GroupLayout.DEFAULT_SIZE, 40, Short.MAX_VALUE)
                                    .addComponent(cbxThuongHieu))))
                        .addGap(18, 18, 18)
                        .addGroup(card1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addGroup(card1Layout.createSequentialGroup()
                                .addComponent(jLabel12, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(txtCamTruoc, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(card1Layout.createSequentialGroup()
                                .addComponent(jLabel13, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(txtCamSau, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addGap(18, 18, 18)
                        .addGroup(card1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, card1Layout.createSequentialGroup()
                                .addComponent(jLabel16, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(txtDLPin, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, card1Layout.createSequentialGroup()
                                .addComponent(jLabel14, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(txtHDH, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(card1Layout.createSequentialGroup()
                                .addComponent(jLabel4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(txtChipXL, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)))))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(card1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(card1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(themPBSPBtn, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(themSPBtn, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(imgUploadBtn, javax.swing.GroupLayout.PREFERRED_SIZE, 52, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        contentPanel.add(card1, "card1");

        card2.setBackground(new java.awt.Color(255, 255, 255));
        card2.setLayout(new java.awt.BorderLayout());

        jPanel1.setBackground(new java.awt.Color(255, 255, 255));
        jPanel1.setPreferredSize(new java.awt.Dimension(1000, 320));

        jLabel15.setText("Dung lượng RAM");
        jLabel15.setPreferredSize(new java.awt.Dimension(80, 20));

        jLabel18.setText("Dung lượng ROM");
        jLabel18.setPreferredSize(new java.awt.Dimension(80, 20));

        jLabel6.setText("Màu");
        jLabel6.setPreferredSize(new java.awt.Dimension(80, 20));

        jScrollPane1.setBackground(new java.awt.Color(255, 255, 255));
        jScrollPane1.setPreferredSize(new java.awt.Dimension(800, 0));

        pbspTable.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "STT", "Dung lượng RAM", "Dung lượng ROM", "Màu"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        pbspTable.setFocusable(false);
        pbspTable.setRowHeight(32);
        pbspTable.setSelectionBackground(new java.awt.Color(190, 215, 220));
        pbspTable.setShowGrid(true);
        pbspTable.getTableHeader().setResizingAllowed(false);
        pbspTable.getTableHeader().setReorderingAllowed(false);
        pbspTable.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                pbspTableMousePressed(evt);
            }
        });
        jScrollPane1.setViewportView(pbspTable);
        if (pbspTable.getColumnModel().getColumnCount() > 0) {
            pbspTable.getColumnModel().getColumn(0).setResizable(false);
            pbspTable.getColumnModel().getColumn(0).setPreferredWidth(60);
            pbspTable.getColumnModel().getColumn(1).setResizable(false);
            pbspTable.getColumnModel().getColumn(1).setPreferredWidth(150);
            pbspTable.getColumnModel().getColumn(2).setResizable(false);
            pbspTable.getColumnModel().getColumn(2).setPreferredWidth(150);
            pbspTable.getColumnModel().getColumn(3).setResizable(false);
            pbspTable.getColumnModel().getColumn(3).setPreferredWidth(150);
        }

        thempbBtn.setBackground(new java.awt.Color(78, 205, 196));
        thempbBtn.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        thempbBtn.setForeground(new java.awt.Color(255, 255, 255));
        thempbBtn.setText("Thêm phiên bản");
        thempbBtn.setBorder(null);
        thempbBtn.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        thempbBtn.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                thempbBtnMousePressed(evt);
            }
        });

        suapbBtn.setBackground(new java.awt.Color(255, 230, 109));
        suapbBtn.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        suapbBtn.setForeground(new java.awt.Color(255, 255, 255));
        suapbBtn.setText("Sửa phiên bản");
        suapbBtn.setBorder(null);
        suapbBtn.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        suapbBtn.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                suapbBtnMousePressed(evt);
            }
        });

        xoapbBtn.setBackground(new java.awt.Color(255, 107, 107));
        xoapbBtn.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        xoapbBtn.setForeground(new java.awt.Color(255, 255, 255));
        xoapbBtn.setText("Xóa phiên bản");
        xoapbBtn.setBorder(null);
        xoapbBtn.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        xoapbBtn.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                xoapbBtnMousePressed(evt);
            }
        });

        quayLaiBtn.setBackground(new java.awt.Color(26, 83, 92));
        quayLaiBtn.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        quayLaiBtn.setForeground(new java.awt.Color(255, 255, 255));
        quayLaiBtn.setText("Quay lại");
        quayLaiBtn.setBorder(null);
        quayLaiBtn.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        quayLaiBtn.setPreferredSize(new java.awt.Dimension(120, 50));
        quayLaiBtn.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                quayLaiBtnMousePressed(evt);
            }
        });

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(61, 61, 61)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel15, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(txtRam, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel18, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(txtRom, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel6, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(txtMau, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(10, 10, 10)
                        .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 825, Short.MAX_VALUE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(thempbBtn, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(suapbBtn, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(xoapbBtn, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                                .addGap(0, 26, Short.MAX_VALUE)
                                .addComponent(quayLaiBtn, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))))
                .addContainerGap())
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(12, 12, 12)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(txtRam, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addComponent(jLabel18, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGroup(jPanel1Layout.createSequentialGroup()
                            .addGap(27, 27, 27)
                            .addComponent(txtRom, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addComponent(txtMau, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(jLabel15, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel6, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(47, 47, 47)))
                .addGap(8, 8, 8)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(thempbBtn, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(suapbBtn, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(xoapbBtn, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 101, Short.MAX_VALUE)
                        .addComponent(quayLaiBtn, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addGap(8, 8, 8))
        );

        card2.add(jPanel1, java.awt.BorderLayout.CENTER);

        contentPanel.add(card2, "card2");

        getContentPane().add(contentPanel, java.awt.BorderLayout.CENTER);

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void themPBSPBtnMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_themPBSPBtnMousePressed
        if (!ValidateSPInput()) {
            return;
        }
        if (mode.equals("add")) {
            if (!ValidateSPDuplication()) {
                return;
            }
        }
        cardLayout.show(contentPanel, "card2");
        jLabel1.setText("Thêm phiên bản sản phẩm cho: " + txtTenSP.getText());
    }//GEN-LAST:event_themPBSPBtnMousePressed

    private void quayLaiBtnMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_quayLaiBtnMousePressed
        cardLayout.show(contentPanel, "card1");
        if (mode.equals("add")) {
            jLabel1.setText("Thêm sản phẩm");
        }
        if (mode.equals("edit"))
            jLabel1.setText("Sửa thông tin sản phẩm");
    }//GEN-LAST:event_quayLaiBtnMousePressed

    private void themSPBtnMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_themSPBtnMousePressed
        if(!ValidateSPInput()) {
            return;
        }
        if (pbspModel.getRowCount() == 0) {
            JOptionPane.showMessageDialog(this, "Bạn phải thêm ít nhất 1 phiên bản");
            return;
        }
        if (mode.equals("add"))
            addSPEvent();
        if (mode.equals("edit"))
            editSPEvent();
    }//GEN-LAST:event_themSPBtnMousePressed

    private void thempbBtnMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_thempbBtnMousePressed
        if (!ValidatePBSPInput()) {
            return;
        }
        if (!ValidatePBSPDuplication()) {
            return;
        }
        if (mode.equals("add")) {
            this.newPBSPList.add(getNewPBSP());
            loadPBSPListToTable(this.newPBSPList);
        }
        if (mode.equals("edit")) {
            this.pbspEditList.add(getNewPBSP());
            loadPBSPListToTable(this.pbspEditList);
            this.newPBAdded = true;
            this.newPBSPList.add(getNewPBSP());
        }
        emptyPBSPInput();
    }//GEN-LAST:event_thempbBtnMousePressed

    private void suapbBtnMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_suapbBtnMousePressed
        int index = pbspTable.getSelectedRow();
        if (index == -1) {
            JOptionPane.showMessageDialog(this, "Bạn chưa chọn phiên bản để sửa");
            return;
        } else {
            if (!ValidatePBSPInput())
                return;
            if (mode.equals("add")) {
                this.newPBSPList.get(index).setRam(Integer.parseInt(txtRam.getText()));
                this.newPBSPList.get(index).setRom(Integer.parseInt(txtRom.getText()));
                this.newPBSPList.get(index).setMau(txtMau.getText());
                loadPBSPListToTable(this.newPBSPList);
            }
            if (mode.equals("edit")) {
                    this.pbspEditList.get(index).setRam(Integer.parseInt(txtRam.getText()));
                    this.pbspEditList.get(index).setRom(Integer.parseInt(txtRom.getText()));
                    this.pbspEditList.get(index).setMau(txtMau.getText());
                    loadPBSPListToTable(this.pbspEditList);
                }
            emptyPBSPInput();
        }
    }//GEN-LAST:event_suapbBtnMousePressed

    private void pbspTableMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_pbspTableMousePressed
        int index = pbspTable.getSelectedRow();
        if (index != -1) {
            if (mode.equals("add")) {
                txtRam.setText(Integer.toString(this.newPBSPList.get(index).getRam()));
                txtRom.setText(Integer.toString(this.newPBSPList.get(index).getRom()));
                txtMau.setText(this.newPBSPList.get(index).getMau());
            }
            if (mode.equals("edit")) {
                txtRam.setText(Integer.toString(this.pbspEditList.get(index).getRam()));
                txtRom.setText(Integer.toString(this.pbspEditList.get(index).getRom()));
                txtMau.setText(this.pbspEditList.get(index).getMau());
            }
        }
    }//GEN-LAST:event_pbspTableMousePressed

    private void xoapbBtnMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_xoapbBtnMousePressed
        int index = pbspTable.getSelectedRow();
        if (index == -1) {
            JOptionPane.showMessageDialog(this, "Bạn chưa chọn phiên bản để xóa");
            return;
        } else {
            if (JOptionPane.showConfirmDialog(this, "Bạn có chắc muốn xóa phiên bản này không?", "", JOptionPane.YES_NO_OPTION) == 0) {
                if (mode.equals("add")) {
                    this.newPBSPList.remove(index);
                    loadPBSPListToTable(this.newPBSPList);
                }
                if (mode.equals("edit")) {
                    this.pbspEditList.get(index).setTrangThai(0);
                    loadPBSPListToTable(this.pbspEditList);
                }
                emptyPBSPInput();
            }
        }
    }//GEN-LAST:event_xoapbBtnMousePressed

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JPanel card1;
    private javax.swing.JPanel card2;
    private javax.swing.JComboBox cbxThuongHieu;
    private javax.swing.JPanel contentPanel;
    private javax.swing.JPanel headerPanel;
    public javax.swing.JLabel hinhAnhSP;
    private javax.swing.JButton imgUploadBtn;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel14;
    private javax.swing.JLabel jLabel15;
    private javax.swing.JLabel jLabel16;
    private javax.swing.JLabel jLabel17;
    private javax.swing.JLabel jLabel18;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTable pbspTable;
    private javax.swing.JButton quayLaiBtn;
    private javax.swing.JButton suapbBtn;
    private javax.swing.JButton themPBSPBtn;
    private javax.swing.JButton themSPBtn;
    private javax.swing.JButton thempbBtn;
    private javax.swing.JTextField txtCamSau;
    private javax.swing.JTextField txtCamTruoc;
    private javax.swing.JTextField txtChipXL;
    private javax.swing.JTextField txtDLPin;
    private javax.swing.JTextField txtHDH;
    private javax.swing.JTextField txtKTMH;
    private javax.swing.JTextField txtMau;
    private javax.swing.JTextField txtRam;
    private javax.swing.JTextField txtRom;
    private javax.swing.JTextField txtTenSP;
    private javax.swing.JButton xoapbBtn;
    // End of variables declaration//GEN-END:variables
}
