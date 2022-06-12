/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JPanel.java to edit this template
 */
package UITParking.GUI.Admin;

import UITParking.BUS.KhachHangBUS;
import UITParking.BUS.NguoiDungBUS;
import UITParking.BUS.XeBUS;
import UITParking.DTO.KhachHangDTO;
import UITParking.DTO.NguoiDungDTO;
import UITParking.DTO.XeDTO;
import static UITParking.GUI.InitPublic.formatDate;
import java.awt.Color;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.ButtonGroup;
import javax.swing.JOptionPane;
import javax.swing.RowFilter;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;
import javax.swing.table.TableRowSorter;

/**
 *
 * @author ADMIN
 */
public class QLKHJPanel extends javax.swing.JPanel {

    /**
     * Creates new form QLKHJPanel
     */
    NguoiDungBUS nguoidungtbl = new NguoiDungBUS();
    ArrayList<NguoiDungDTO> list_ND = nguoidungtbl.getList_ND();
    KhachHangBUS khachhangtbl = new KhachHangBUS();
    ArrayList<KhachHangDTO> list_KH = khachhangtbl.getList_KH();
    XeBUS xetbl = new XeBUS();
    ArrayList<XeDTO> list_XE = xetbl.getlist_XE();
    public static String passwordTemp;

    private DefaultTableModel model;
    private String[] columnHeaders = new String[]{"STT", "Mã KH", "Họ Tên", "Email", "Ngày Sinh",
        "Giới Tính", "Địa Chỉ", "Quê Quán", "Số Điện Thoại", "Mã Xe", "Tên Loại Xe", "Biển Số Xe",
        "Số Dư"};

    private TableRowSorter<TableModel> rowSorter = null;

    public QLKHJPanel() throws Exception {
        initComponents();
        initTable();
        hoTroTimKiem();
        //Disable chuc nang cua Xoa va Sua khach hang
        btnCapNhat.setEnabled(false);
        btnXoa.setEnabled(false);
        txtMaKH.setEnabled(false);
        txtMaXe.setEnabled(false);
        txtEmail.setEnabled(false);
        disablePassword();

    }

    public void disablePassword() {
        txtMatKhau.setVisible(false);
        lblMatKhau.setVisible(false);
    }

    public void enablePassword() {
        txtMatKhau.setVisible(true);
        lblMatKhau.setVisible(true);
    }

    public void resetRender() {
        txtMaKH.setText("");
        txtHoTen.setText("");
        txtEmail.setText("");
        txtDiaChi.setText("");
        txtQueQuan.setText("");
        txtSDT.setText("");
        txtMaXe.setText("");
        txtBienSoXe.setText("");
        txtSoDu.setText("");
        //cleat Selection Group
        btnGroupGioiTinh.clearSelection();
        jdcNgaySinh.setDate(null);

    }

    public void initTable() throws Exception {
        model = new DefaultTableModel();
        model.setColumnIdentifiers(columnHeaders);
        int index = 1;
        for (KhachHangDTO kh : list_KH) {
            //Lấy ra xe và biển số xe của khách hàng
            Boolean xeIsExists = false;
            String tenLoaiXe = "", bienSoXe = "";
            NguoiDungDTO nd = nguoidungtbl.getInfor(kh.getStrMaKH());
            if (kh.getStrMaXe() != null) {
                XeDTO xe = xetbl.getInfor(kh.getStrMaXe());
                xeIsExists = true;
                tenLoaiXe = xe.getStrTenLoaiXe();
                bienSoXe = xe.getStrBienSoXe();
            } else {
                xeIsExists = false;
            }
            //Cập nhật bảng
            model.addRow(new Object[]{index, nd.getStrMaND(), nd.getStrHoTen(), nd.getStrEmail(),
                (nd.getDateNgSinh() != null ? formatDate(nd.getDateNgSinh()) : nd.getDateNgSinh()),
                nd.getStrGioiTinh(), nd.getStrDiaChi(),
                nd.getStrQueQuan(), nd.getStrSDT(), kh.getStrMaXe(), tenLoaiXe, bienSoXe, kh.getLongSoDu()});
            index++;
        }

        tblKhachHang.setModel(model);

    }

    public void hoTroTimKiem() {

        rowSorter = new TableRowSorter<>(tblKhachHang.getModel());
        tblKhachHang.setRowSorter(rowSorter);
        txtTimKiem.getDocument().addDocumentListener(new DocumentListener() {
            @Override
            public void insertUpdate(DocumentEvent e) {
                String text = txtTimKiem.getText();
                if (text.trim().length() == 0) {
                    rowSorter.setRowFilter(null);
                } else {
                    rowSorter.setRowFilter(RowFilter.regexFilter("(?i)" + text));
                }
            }

            @Override
            public void removeUpdate(DocumentEvent e) {
                String text = txtTimKiem.getText();
                if (text.trim().length() == 0) {
                    rowSorter.setRowFilter(null);
                } else {
                    rowSorter.setRowFilter(RowFilter.regexFilter("(?i)" + text));
                }
            }

            @Override
            public void changedUpdate(DocumentEvent e) {
            }
        });
    }

    //Hàm cập nhật lại bảng sau khi thêm xóa sửa
    public void capNhatLaiTable() {
        list_ND = nguoidungtbl.getList_ND();
        model.setRowCount(0);
        int index = 1;
        for (KhachHangDTO kh : list_KH) {
            //Lấy ra xe và biển số xe của khách hàng

            NguoiDungDTO nd = nguoidungtbl.getInfor(kh.getStrMaKH());
            XeDTO xe = xetbl.getInfor(kh.getStrMaXe());
            //Cập nhật bảng
            model.addRow(new Object[]{index, nd.getStrMaND(), nd.getStrHoTen(), nd.getStrEmail(),
                (nd.getDateNgSinh() != null ? formatDate(nd.getDateNgSinh()) : nd.getDateNgSinh()),
                nd.getStrGioiTinh(), nd.getStrDiaChi(),
                nd.getStrQueQuan(), nd.getStrSDT(), kh.getStrMaXe(), xe.getStrTenLoaiXe(),
                xe.getStrBienSoXe(), kh.getLongSoDu(), nd.getStrMatKhau()});
            index++;
        }
        model.fireTableDataChanged();
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        btnGroupGioiTinh = new javax.swing.ButtonGroup();
        jPanel1 = new javax.swing.JPanel();
        jLabel2 = new javax.swing.JLabel();
        txtMaKH = new javax.swing.JTextField();
        jLabel3 = new javax.swing.JLabel();
        txtEmail = new javax.swing.JTextField();
        jLabel4 = new javax.swing.JLabel();
        txtDiaChi = new javax.swing.JTextField();
        jLabel5 = new javax.swing.JLabel();
        txtQueQuan = new javax.swing.JTextField();
        jLabel6 = new javax.swing.JLabel();
        rdbNam = new javax.swing.JRadioButton();
        rdbNu = new javax.swing.JRadioButton();
        jLabel7 = new javax.swing.JLabel();
        txtHoTen = new javax.swing.JTextField();
        jLabel8 = new javax.swing.JLabel();
        txtSDT = new javax.swing.JTextField();
        jLabel9 = new javax.swing.JLabel();
        btnNhapMoi = new javax.swing.JButton();
        btnLuu = new javax.swing.JButton();
        btnCapNhat = new javax.swing.JButton();
        btnXoa = new javax.swing.JButton();
        btnTimKiem = new javax.swing.JButton();
        txtTimKiem = new javax.swing.JTextField();
        jScrollPane1 = new javax.swing.JScrollPane();
        tblKhachHang = new javax.swing.JTable();
        jdcNgaySinh = new com.toedter.calendar.JDateChooser();
        jLabel10 = new javax.swing.JLabel();
        jLabel11 = new javax.swing.JLabel();
        txtBienSoXe = new javax.swing.JTextField();
        txtSoDu = new javax.swing.JTextField();
        jLabel12 = new javax.swing.JLabel();
        jLabel13 = new javax.swing.JLabel();
        txtMaXe = new javax.swing.JTextField();
        lblMatKhau = new javax.swing.JLabel();
        txtMatKhau = new javax.swing.JTextField();
        cbbLoaiXe = new javax.swing.JComboBox<>();

        setBackground(new java.awt.Color(255, 255, 255));
        setPreferredSize(new java.awt.Dimension(1200, 497));

        jPanel1.setBackground(new java.awt.Color(255, 255, 255));

        jLabel2.setText("Mã khách hàng");

        txtMaKH.setEditable(false);

        jLabel3.setText("Email");

        jLabel4.setText("Địa chỉ");

        jLabel5.setText("Quê quán");

        jLabel6.setText("Giới tính");

        btnGroupGioiTinh.add(rdbNam);
        rdbNam.setText("Nam");

        btnGroupGioiTinh.add(rdbNu);
        rdbNu.setText("Nữ");

        jLabel7.setText("Họ tên");

        jLabel8.setText("Ngày sinh");

        jLabel9.setText("Số ĐT");

        btnNhapMoi.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icon/icons8_available_updates_24px.png"))); // NOI18N
        btnNhapMoi.setText("Nhập mới");
        btnNhapMoi.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                btnNhapMoiMouseClicked(evt);
            }
        });

        btnLuu.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icon/icons8_new_copy_24px.png"))); // NOI18N
        btnLuu.setText("Lưu");
        btnLuu.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                btnLuuMouseClicked(evt);
            }
        });

        btnCapNhat.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icon/icons8_installing_updates_24px.png"))); // NOI18N
        btnCapNhat.setText("Cập nhật");
        btnCapNhat.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                btnCapNhatMouseClicked(evt);
            }
        });

        btnXoa.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icon/icons8_waste_24px.png"))); // NOI18N
        btnXoa.setText("Xóa");
        btnXoa.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                btnXoaMouseClicked(evt);
            }
        });

        btnTimKiem.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icon/icons8_search_24px.png"))); // NOI18N
        btnTimKiem.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                btnTimKiemMouseClicked(evt);
            }
        });

        tblKhachHang.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "Title 1", "Title 2", "Title 3", "Title 4"
            }
        ));
        tblKhachHang.setFillsViewportHeight(true);
        tblKhachHang.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                tblKhachHangMousePressed(evt);
            }
        });
        jScrollPane1.setViewportView(tblKhachHang);

        jLabel10.setText("Loại xe");
        jLabel10.setToolTipText("");

        jLabel11.setText("Biển số xe");

        jLabel12.setText("Số dư");

        jLabel13.setText("Mã xe");

        txtMaXe.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtMaXeActionPerformed(evt);
            }
        });

        lblMatKhau.setText("Mật khẩu");

        cbbLoaiXe.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Xe đạp", "Xe máy" }));
        cbbLoaiXe.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                cbbLoaiXeItemStateChanged(evt);
            }
        });

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(378, 378, 378)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel5, javax.swing.GroupLayout.PREFERRED_SIZE, 58, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                .addComponent(jLabel8, javax.swing.GroupLayout.PREFERRED_SIZE, 55, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(jLabel7, javax.swing.GroupLayout.PREFERRED_SIZE, 55, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addGap(51, 51, 51)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(txtHoTen, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 220, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jdcNgaySinh, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 220, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(txtQueQuan, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 220, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(txtSDT, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 220, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(22, 22, 22)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel2)
                            .addComponent(jLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, 51, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel4, javax.swing.GroupLayout.PREFERRED_SIZE, 51, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel6, javax.swing.GroupLayout.PREFERRED_SIZE, 55, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(lblMatKhau, javax.swing.GroupLayout.PREFERRED_SIZE, 60, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(18, 18, 18)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(txtMatKhau, javax.swing.GroupLayout.PREFERRED_SIZE, 197, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(txtMaKH, javax.swing.GroupLayout.PREFERRED_SIZE, 200, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(txtDiaChi, javax.swing.GroupLayout.PREFERRED_SIZE, 197, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(txtEmail, javax.swing.GroupLayout.PREFERRED_SIZE, 197, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addComponent(rdbNam)
                                .addGap(2, 2, 2)
                                .addComponent(rdbNu))))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(378, 378, 378)
                        .addComponent(jLabel9, javax.swing.GroupLayout.PREFERRED_SIZE, 55, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 86, Short.MAX_VALUE)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(jLabel12, javax.swing.GroupLayout.PREFERRED_SIZE, 52, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(43, 43, 43)
                        .addComponent(txtSoDu, javax.swing.GroupLayout.PREFERRED_SIZE, 190, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                        .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                            .addComponent(jLabel10, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(cbbLoaiXe, javax.swing.GroupLayout.PREFERRED_SIZE, 190, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                            .addComponent(jLabel13, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGap(45, 45, 45)
                            .addComponent(txtMaXe, javax.swing.GroupLayout.PREFERRED_SIZE, 190, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGroup(jPanel1Layout.createSequentialGroup()
                            .addComponent(jLabel11, javax.swing.GroupLayout.PREFERRED_SIZE, 60, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(txtBienSoXe, javax.swing.GroupLayout.PREFERRED_SIZE, 190, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addGap(122, 122, 122))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addGap(252, 252, 252)
                .addComponent(btnNhapMoi)
                .addGap(26, 26, 26)
                .addComponent(btnLuu, javax.swing.GroupLayout.PREFERRED_SIZE, 80, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(32, 32, 32)
                .addComponent(btnCapNhat)
                .addGap(18, 18, 18)
                .addComponent(btnXoa)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(btnTimKiem)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(txtTimKiem, javax.swing.GroupLayout.PREFERRED_SIZE, 191, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(103, 103, 103))
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane1)
                .addContainerGap())
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(19, 19, 19)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(txtMaXe, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel13, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(18, 18, 18)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(cbbLoaiXe, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel10, javax.swing.GroupLayout.PREFERRED_SIZE, 19, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(18, 18, Short.MAX_VALUE)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(txtBienSoXe, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel11, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel12, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(txtSoDu, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(64, 64, 64))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 22, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(txtMaKH, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                .addComponent(jLabel7, javax.swing.GroupLayout.PREFERRED_SIZE, 22, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(txtHoTen, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addGap(18, 18, 18)
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(jPanel1Layout.createSequentialGroup()
                                        .addComponent(jLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addGap(19, 19, 19)
                                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                            .addComponent(jLabel4, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                                            .addComponent(txtDiaChi, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                                    .addGroup(jPanel1Layout.createSequentialGroup()
                                        .addComponent(jLabel8, javax.swing.GroupLayout.PREFERRED_SIZE, 19, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addGap(18, 18, 18)
                                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                            .addComponent(jLabel5, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                                            .addComponent(txtQueQuan, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                                    .addComponent(txtEmail, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addGap(16, 16, 16)
                                .addComponent(jdcNgaySinh, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                .addComponent(rdbNam)
                                .addComponent(jLabel6, javax.swing.GroupLayout.PREFERRED_SIZE, 24, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(rdbNu))
                            .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                .addComponent(jLabel9, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(txtSDT, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addGap(18, 18, 18)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(lblMatKhau, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(txtMatKhau, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(btnTimKiem, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(btnNhapMoi, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(btnLuu, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(btnCapNhat, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(btnXoa, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(txtTimKiem, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 200, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(58, 58, 58))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, 492, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 5, Short.MAX_VALUE))
        );
    }// </editor-fold>//GEN-END:initComponents

    private void btnNhapMoiMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnNhapMoiMouseClicked
        resetRender();
        enablePassword();
        txtEmail.setEnabled(true);
    }//GEN-LAST:event_btnNhapMoiMouseClicked

    private void btnLuuMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnLuuMouseClicked
        StringBuilder sb = new StringBuilder();
        if (nguoidungtbl.getInfor(txtMaKH.getText()) != null) {
            sb.append("Mã khách hàng đã tồn tại.");
        }
        if (khachhangtbl.getInfor(txtMaKH.getText()) != null) {
            sb.append("Mã khách hàng đã tồn tại.");
        }
        if (nguoidungtbl.getInforEmail(txtEmail.getText()) != null) {
            sb.append("Email khách hàng đã tồn tại");
        }

        if (xetbl.getInfor(txtMaXe.getText()) != null) {
            sb.append("Mã xe đã tồn tại");
        }

        if (sb.length() > 0) {
            JOptionPane.showMessageDialog(this, sb);
            return;
        }
        try {
            NguoiDungDTO nd = new NguoiDungDTO();
            KhachHangDTO kh = new KhachHangDTO();
            XeDTO xe = new XeDTO();
//            nd.setStrMaND(txtMaKH.getText());
            String tempMaKH = nguoidungtbl.getMaxMaND();
            System.out.println(tempMaKH);
            nd.setStrMaND(tempMaKH);
            String tempMaXe = xetbl.getMaxMaXe();
            System.out.println(tempMaXe);
            nd.setStrEmail(txtEmail.getText());
            nd.setStrHoTen(txtHoTen.getText());
            nd.setStrDiaChi(txtDiaChi.getText());
            nd.setStrQueQuan(txtQueQuan.getText());
            nd.setStrSDT(txtSDT.getText());
            nd.setStrGioiTinh(rdbNam.isSelected() ? "Nam" : "Nu");
            kh.setStrMaKH(tempMaKH);
            kh.setStrMaXe(tempMaXe);
            kh.setLongSoDu(Integer.parseInt(txtSoDu.getText()));
            xe.setStrMaXe(tempMaXe);
            if (cbbLoaiXe.getSelectedItem().toString().equals("Xe đạp")) {
                xe.setStrTenLoaiXe("Xe dap");
                xe.setStrBienSoXe(null);
            } else {
                xe.setStrTenLoaiXe("Xe may");
                xe.setStrBienSoXe(txtBienSoXe.getText());
            }
            if (jdcNgaySinh.getDate() != null) {
                nd.setDateNgSinh(new java.sql.Date(jdcNgaySinh.getDate().getTime()));
            } else {
                nd.setDateNgSinh(null);
            }
            nd.setStrVaiTro("Khach hang");
            nd.setStrMatKhau(txtMatKhau.getText());
            xetbl.them(xe);
            nguoidungtbl.them(nd);
            khachhangtbl.them(kh);

            //Cập nhật lại Table
            capNhatLaiTable();
            JOptionPane.showMessageDialog(this, "Khách hàng mới đã được thêm vào CSDL");

        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, "Error" + e.getMessage());
            e.printStackTrace();
        }
        /**
         * Sau khi lưu thì disablePassword Sau khi lưu thì email set về dạng
         * setEnable(false)
         */
        disablePassword();
        txtEmail.setEnabled(false);
    }//GEN-LAST:event_btnLuuMouseClicked

    private void btnCapNhatMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnCapNhatMouseClicked
        // TODO add your handling code here:
//        StringBuilder sb = new StringBuilder();
//        if (txtMaKH.getText().equals("")) {
//            sb.append("Mã khách hàng không được để trống.");
//            txtMaKH.setBackground(Color.red);
//        } else {
//            txtMaKH.setBackground(Color.white);
//        }
//        if (nguoidungtbl.getInfor(txtMaKH.getText()) == null) {
//            sb.append("Mã khách hàng không tồn tại.");
//        }
//        if (khachhangtbl.getInfor(txtMaKH.getText()) == null) {
//            sb.append("Mã khách hàng không tồn tại.");
//        }
//        if (xetbl.getInfor(txtMaXe.getText()) == null) {
//            sb.append("Mã xe không tồn tại.");
//        }
//        if (sb.length() > 0) {
//            JOptionPane.showMessageDialog(this, sb);
//            return;
//        }
//        try {
//            NguoiDungDTO nd = new NguoiDungDTO();
//            KhachHangDTO kh = new KhachHangDTO();
//            XeDTO xe = new XeDTO();
//            nd.setStrMaND(txtMaKH.getText());
//            nd.setStrEmail(txtEmail.getText());
//            nd.setStrHoTen(txtHoTen.getText());
//            nd.setStrDiaChi(txtDiaChi.getText());
//            nd.setStrQueQuan(txtQueQuan.getText());
//            nd.setStrSDT(txtSDT.getText());
//            nd.setStrGioiTinh(rdbNam.isSelected() ? "Nam" : "Nu");
//            kh.setStrMaKH(txtMaKH.getText());
//            kh.setStrMaXe(txtMaXe.getText());
//            kh.setLongSoDu(Integer.parseInt(txtSoDu.getText()));
//            xe.setStrMaXe(txtMaXe.getText());
//            xe.setStrBienSoXe(txtBienSoXe.getText());
//            xe.setStrTenLoaiXe(txtLoaiXe.getText());
//            if (jdcNgaySinh.getDate() != null) {
//                nd.setDateNgSinh(new java.sql.Date(jdcNgaySinh.getDate().getTime()));
//            }else{
//                nd.setDateNgSinh(null);
//            }
//            nd.setStrMatKhau(txtMatKhau.getText());
//            nd.setStrVaiTro("Khach hang");
//            nguoidungtbl.sua(nd);
//            xetbl.sua(xe);
//            khachhangtbl.sua(kh);
//            
//            //Cập nhật lại Table
//            capNhatLaiTable();
//
//            JOptionPane.showMessageDialog(this, "Khách hàng đã được cập nhật vào CSDL");
//
//        } catch (Exception e) {
//            JOptionPane.showMessageDialog(this, "Error" + e.getMessage());
//            e.printStackTrace();
//        }
    }//GEN-LAST:event_btnCapNhatMouseClicked

    private void btnXoaMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnXoaMouseClicked
        // TODO add your handling code here:
//        StringBuilder sb = new StringBuilder();
//        if (txtMaKH.getText().equals("")) {
//            sb.append("Mã khách hàng không được để trống.");
//            txtMaKH.setBackground(Color.red);
//        } else {
//            txtMaKH.setBackground(Color.white);
//        }
//        if (sb.length() > 0) {
//            JOptionPane.showMessageDialog(this, sb);
//            return;
//        }
//
//        if (JOptionPane.showConfirmDialog(this, "Bạn có muốn xóa không?") == JOptionPane.NO_OPTION) {
//            return;
//        }
//        try {
//            NguoiDungDTO nd = nguoidungtbl.getInfor(txtMaKH.getText());
//            KhachHangDTO kh = khachhangtbl.getInfor(txtMaKH.getText());
//            XeDTO xe = xetbl.getInfor(txtMaXe.getText());
//            nguoidungtbl.xoa(nd);
//            khachhangtbl.xoa(kh);
//            xetbl.xoa(xe);
//
//            JOptionPane.showMessageDialog(this, "Khách hàng đã xóa khỏi CSDL");
//
//            //Reset lại render
//            resetRender();
//
//            //Cập nhật lại bảng
//            capNhatLaiTable();
//
//        } catch (Exception e) {
//            JOptionPane.showMessageDialog(this, "Error" + e.getMessage());
//            e.printStackTrace();
//        }
    }//GEN-LAST:event_btnXoaMouseClicked

    private void btnTimKiemMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnTimKiemMouseClicked
        // TODO add your handling code here:
        if (txtMaKH.getText().equals("")) {
            JOptionPane.showMessageDialog(this, "Mã khách hàng phải được nhập để tìm kiếm");
            return;
        }

        try {
            NguoiDungBUS nguoidungtbl = new NguoiDungBUS();
            NguoiDungDTO nd = nguoidungtbl.getInfor(txtMaKH.getText());

            if (nd != null) {
                txtMaKH.setText(nd.getStrMaND());
                txtEmail.setText(nd.getStrEmail());
                txtHoTen.setText(nd.getStrHoTen());
                txtDiaChi.setText(nd.getStrDiaChi());
                txtQueQuan.setText(nd.getStrQueQuan());
                txtSDT.setText(nd.getStrSDT());
                rdbNam.setSelected(nd.getStrGioiTinh().equals("Nam"));
                rdbNu.setSelected(nd.getStrGioiTinh().equals("Nu"));
            } else {
                JOptionPane.showMessageDialog(this, "Khách hàng không tìm thấy");
            }

        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, "Error: " + e.getMessage());
            e.printStackTrace();
        }
    }//GEN-LAST:event_btnTimKiemMouseClicked

    private void tblKhachHangMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tblKhachHangMousePressed
        // TODO add your handling code here:
        resetRender();
        disablePassword();
        int selectedRow = tblKhachHang.getSelectedRow();
        if (selectedRow >= 0) {

            KhachHangDTO kh = list_KH.get(selectedRow);
            NguoiDungDTO nd = nguoidungtbl.getInfor(kh.getStrMaKH());

            txtMaKH.setText(nd.getStrMaND());
            txtEmail.setText(nd.getStrEmail());
            txtHoTen.setText(nd.getStrHoTen());
            txtDiaChi.setText(nd.getStrDiaChi());
            txtQueQuan.setText(nd.getStrQueQuan());
            txtSDT.setText(nd.getStrSDT());
            rdbNam.setSelected(nd.getStrGioiTinh().equals("Nam"));
            rdbNu.setSelected(nd.getStrGioiTinh().equals("Nu"));
            txtMaXe.setText(kh.getStrMaXe());
            txtSoDu.setText(String.valueOf(kh.getLongSoDu()));
            if (kh.getStrMaXe() != null) {
                XeDTO xe = xetbl.getInfor(kh.getStrMaXe());
                if (xe.getStrTenLoaiXe().equals("Xe dap")) {
                    cbbLoaiXe.setSelectedItem("Xe đạp");
                }
                if (xe.getStrTenLoaiXe().equals("Xe may")) {
                    cbbLoaiXe.setSelectedItem("Xe máy");
                }
                txtBienSoXe.setText(xe.getStrBienSoXe());
            }

            if (nd.getDateNgSinh() != null) {
                jdcNgaySinh.setDate(nd.getDateNgSinh());
            }
        }
    }//GEN-LAST:event_tblKhachHangMousePressed

    private void txtMaXeActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtMaXeActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtMaXeActionPerformed

    private void cbbLoaiXeItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_cbbLoaiXeItemStateChanged
        // TODO add your handling code here:
        if (cbbLoaiXe.getSelectedItem().toString().equals("Xe đạp")) {
            txtBienSoXe.setEnabled(false);
        } else {
            txtBienSoXe.setEnabled(true);
        }
    }//GEN-LAST:event_cbbLoaiXeItemStateChanged


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnCapNhat;
    private javax.swing.ButtonGroup btnGroupGioiTinh;
    private javax.swing.JButton btnLuu;
    private javax.swing.JButton btnNhapMoi;
    private javax.swing.JButton btnTimKiem;
    private javax.swing.JButton btnXoa;
    private javax.swing.JComboBox<String> cbbLoaiXe;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JScrollPane jScrollPane1;
    private com.toedter.calendar.JDateChooser jdcNgaySinh;
    private javax.swing.JLabel lblMatKhau;
    private javax.swing.JRadioButton rdbNam;
    private javax.swing.JRadioButton rdbNu;
    private javax.swing.JTable tblKhachHang;
    private javax.swing.JTextField txtBienSoXe;
    private javax.swing.JTextField txtDiaChi;
    private javax.swing.JTextField txtEmail;
    private javax.swing.JTextField txtHoTen;
    private javax.swing.JTextField txtMaKH;
    private javax.swing.JTextField txtMaXe;
    private javax.swing.JTextField txtMatKhau;
    private javax.swing.JTextField txtQueQuan;
    private javax.swing.JTextField txtSDT;
    private javax.swing.JTextField txtSoDu;
    private javax.swing.JTextField txtTimKiem;
    // End of variables declaration//GEN-END:variables
}
