/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JPanel.java to edit this template
 */
package UITParking.TEST2;

import UITParking.BUS.KhachHangBUS;
import UITParking.BUS.NguoiDungBUS;
import UITParking.BUS.XeBUS;
import UITParking.DTO.KhachHangDTO;
import UITParking.DTO.NguoiDungDTO;
import UITParking.DTO.XeDTO;
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
        "Số Dư", "Mật Khẩu"};

    private TableRowSorter<TableModel> rowSorter = null;

    public QLKHJPanel() throws Exception {
        initComponents();
        initTable();
        hoTroTimKiem();
        //Disable chuc nang cua Xoa va Sua khach hang
        btnCapNhat.setEnabled(false);
        btnXoa.setEnabled(false);
    }

    public void resetRender() {
        txtMaKH.setText("");
        txtHoTen.setText("");
        txtEmail.setText("");
        txtDiaChi.setText("");
        txtQueQuan.setText("");
        txtSDT.setText("");
        txtMaXe.setText("");
        txtLoaiXe.setText("");
        txtBienSoXe.setText("");
        txtSoDu.setText("");
        txtMatKhau.setText("");
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
                nd.getDateNgSinh(), nd.getStrGioiTinh(), nd.getStrDiaChi(),
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
                nd.getDateNgSinh(), nd.getStrGioiTinh(), nd.getStrDiaChi(),
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
        txtLoaiXe = new javax.swing.JTextField();
        jLabel11 = new javax.swing.JLabel();
        txtBienSoXe = new javax.swing.JTextField();
        txtSoDu = new javax.swing.JTextField();
        jLabel12 = new javax.swing.JLabel();
        jLabel13 = new javax.swing.JLabel();
        txtMaXe = new javax.swing.JTextField();
        jLabel14 = new javax.swing.JLabel();
        txtMatKhau = new javax.swing.JTextField();

        jLabel2.setText("Mã KH");

        jLabel3.setText("Email");

        jLabel4.setText("Địa Chỉ");

        jLabel5.setText("Quê Quán");

        jLabel6.setText("Giới Tính");

        btnGroupGioiTinh.add(rdbNam);
        rdbNam.setText("Nam");

        btnGroupGioiTinh.add(rdbNu);
        rdbNu.setText("Nữ");

        jLabel7.setText("Họ Tên");

        jLabel8.setText("Ngày Sinh");

        jLabel9.setText("Số ĐT");

        btnNhapMoi.setText("Nhập Mới");
        btnNhapMoi.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                btnNhapMoiMouseClicked(evt);
            }
        });

        btnLuu.setText("Lưu");
        btnLuu.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                btnLuuMouseClicked(evt);
            }
        });

        btnCapNhat.setText("Cập Nhật");
        btnCapNhat.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                btnCapNhatMouseClicked(evt);
            }
        });

        btnXoa.setText("Xóa");
        btnXoa.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                btnXoaMouseClicked(evt);
            }
        });

        btnTimKiem.setText("Tìm Kiếm");
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
        tblKhachHang.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                tblKhachHangMousePressed(evt);
            }
        });
        jScrollPane1.setViewportView(tblKhachHang);

        jLabel10.setText("Loại Xe");

        txtLoaiXe.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtLoaiXeActionPerformed(evt);
            }
        });

        jLabel11.setText("Biển Số Xe");

        jLabel12.setText("Số Dư");

        jLabel13.setText("Mã Xe");

        jLabel14.setText("Mật Khẩu");

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(10, 10, 10)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(jPanel1Layout.createSequentialGroup()
                                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                            .addGroup(jPanel1Layout.createSequentialGroup()
                                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                                    .addGroup(jPanel1Layout.createSequentialGroup()
                                                        .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 64, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                                        .addComponent(txtMaKH))
                                                    .addGroup(jPanel1Layout.createSequentialGroup()
                                                        .addComponent(jLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, 55, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                        .addGap(18, 18, 18)
                                                        .addComponent(txtEmail, javax.swing.GroupLayout.PREFERRED_SIZE, 188, javax.swing.GroupLayout.PREFERRED_SIZE)))
                                                .addGap(67, 67, 67)
                                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                                    .addGroup(jPanel1Layout.createSequentialGroup()
                                                        .addComponent(jLabel7, javax.swing.GroupLayout.PREFERRED_SIZE, 55, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                                        .addComponent(txtHoTen))
                                                    .addGroup(jPanel1Layout.createSequentialGroup()
                                                        .addComponent(jLabel8, javax.swing.GroupLayout.PREFERRED_SIZE, 55, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                        .addGap(7, 7, 7)
                                                        .addComponent(jdcNgaySinh, javax.swing.GroupLayout.PREFERRED_SIZE, 218, javax.swing.GroupLayout.PREFERRED_SIZE))
                                                    .addGroup(jPanel1Layout.createSequentialGroup()
                                                        .addComponent(jLabel9, javax.swing.GroupLayout.PREFERRED_SIZE, 55, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                                        .addComponent(txtSDT, javax.swing.GroupLayout.PREFERRED_SIZE, 218, javax.swing.GroupLayout.PREFERRED_SIZE))))
                                            .addGroup(jPanel1Layout.createSequentialGroup()
                                                .addComponent(btnNhapMoi)
                                                .addGap(18, 18, 18)
                                                .addComponent(btnLuu, javax.swing.GroupLayout.PREFERRED_SIZE, 57, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                                .addComponent(btnCapNhat)
                                                .addGap(18, 18, 18)
                                                .addComponent(btnXoa)
                                                .addGap(41, 41, 41)
                                                .addComponent(btnTimKiem)
                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                                .addComponent(txtTimKiem, javax.swing.GroupLayout.PREFERRED_SIZE, 144, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                .addGap(3, 3, 3)))
                                        .addGap(46, 46, 46))
                                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                                        .addComponent(jLabel5, javax.swing.GroupLayout.PREFERRED_SIZE, 58, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addGap(270, 270, 270)))
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addGroup(jPanel1Layout.createSequentialGroup()
                                        .addComponent(jLabel12, javax.swing.GroupLayout.PREFERRED_SIZE, 52, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addGap(28, 28, 28)
                                        .addComponent(txtSoDu, javax.swing.GroupLayout.PREFERRED_SIZE, 185, javax.swing.GroupLayout.PREFERRED_SIZE))
                                    .addGroup(jPanel1Layout.createSequentialGroup()
                                        .addComponent(jLabel11, javax.swing.GroupLayout.PREFERRED_SIZE, 52, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addGap(28, 28, 28)
                                        .addComponent(txtBienSoXe, javax.swing.GroupLayout.PREFERRED_SIZE, 185, javax.swing.GroupLayout.PREFERRED_SIZE))
                                    .addGroup(jPanel1Layout.createSequentialGroup()
                                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addComponent(jLabel10, javax.swing.GroupLayout.PREFERRED_SIZE, 62, javax.swing.GroupLayout.PREFERRED_SIZE)
                                            .addComponent(jLabel13, javax.swing.GroupLayout.PREFERRED_SIZE, 53, javax.swing.GroupLayout.PREFERRED_SIZE))
                                        .addGap(18, 18, 18)
                                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addComponent(txtMaXe)
                                            .addComponent(txtLoaiXe, javax.swing.GroupLayout.PREFERRED_SIZE, 185, javax.swing.GroupLayout.PREFERRED_SIZE)))))
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addComponent(jLabel6, javax.swing.GroupLayout.PREFERRED_SIZE, 55, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18)
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(txtMatKhau, javax.swing.GroupLayout.PREFERRED_SIZE, 188, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addGroup(jPanel1Layout.createSequentialGroup()
                                        .addComponent(rdbNam)
                                        .addGap(10, 10, 10)
                                        .addComponent(rdbNu))))))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(8, 8, 8)
                        .addComponent(jLabel4, javax.swing.GroupLayout.PREFERRED_SIZE, 55, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(txtDiaChi, javax.swing.GroupLayout.PREFERRED_SIZE, 188, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(135, 135, 135)
                        .addComponent(txtQueQuan, javax.swing.GroupLayout.PREFERRED_SIZE, 214, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(jLabel14, javax.swing.GroupLayout.PREFERRED_SIZE, 45, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(0, 40, Short.MAX_VALUE))
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane1)
                .addContainerGap())
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(20, 20, 20)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel7, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                .addComponent(jLabel13)
                                .addComponent(txtMaXe, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 22, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(txtMaKH, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addComponent(txtHoTen, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(0, 0, Short.MAX_VALUE)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jLabel3)
                        .addComponent(txtEmail, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(jdcNgaySinh, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel8, javax.swing.GroupLayout.PREFERRED_SIZE, 19, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jLabel10, javax.swing.GroupLayout.PREFERRED_SIZE, 19, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(txtLoaiXe, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel4)
                    .addComponent(txtDiaChi, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtQueQuan, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtBienSoXe, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel5)
                    .addComponent(jLabel11, javax.swing.GroupLayout.PREFERRED_SIZE, 27, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(txtSDT)
                        .addComponent(jLabel12, javax.swing.GroupLayout.PREFERRED_SIZE, 27, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(txtSoDu, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jLabel6, javax.swing.GroupLayout.PREFERRED_SIZE, 24, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(rdbNam)
                        .addComponent(rdbNu)
                        .addComponent(jLabel9)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel14)
                    .addComponent(txtMatKhau, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnNhapMoi)
                    .addComponent(btnLuu)
                    .addComponent(btnCapNhat)
                    .addComponent(btnXoa)
                    .addComponent(btnTimKiem)
                    .addComponent(txtTimKiem, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 247, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(100, 100, 100))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, 516, javax.swing.GroupLayout.PREFERRED_SIZE)
        );
    }// </editor-fold>//GEN-END:initComponents

    private void btnNhapMoiMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnNhapMoiMouseClicked
        resetRender();
    }//GEN-LAST:event_btnNhapMoiMouseClicked

    private void btnLuuMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnLuuMouseClicked
        StringBuilder sb = new StringBuilder();
        if (txtMaKH.getText().equals("")) {
            sb.append("Mã khách hàng không được để trống.");
            txtMaKH.setBackground(Color.red);
        } else {
            txtMaKH.setBackground(Color.white);
        }
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
        if (txtMaXe.getText().length() > 5) {
            sb.append("Mã xe tối đa chỉ có 5 kí tự");
        }

        if (sb.length() > 0) {
            JOptionPane.showMessageDialog(this, sb);
            return;
        }
        try {
            NguoiDungDTO nd = new NguoiDungDTO();
            KhachHangDTO kh = new KhachHangDTO();
            XeDTO xe = new XeDTO();
            nd.setStrMaND(txtMaKH.getText());
            nd.setStrEmail(txtEmail.getText());
            nd.setStrHoTen(txtHoTen.getText());
            nd.setStrDiaChi(txtDiaChi.getText());
            nd.setStrQueQuan(txtQueQuan.getText());
            nd.setStrSDT(txtSDT.getText());
            nd.setStrGioiTinh(rdbNam.isSelected() ? "Nam" : "Nu");
            kh.setStrMaKH(txtMaKH.getText());
            kh.setStrMaXe(txtMaXe.getText());
            kh.setLongSoDu(Integer.parseInt(txtSoDu.getText()));
            xe.setStrMaXe(txtMaXe.getText());
            xe.setStrBienSoXe(txtBienSoXe.getText());
            xe.setStrTenLoaiXe(txtLoaiXe.getText());
            if (jdcNgaySinh.getDate() != null) {
                nd.setDateNgSinh(new java.sql.Date(jdcNgaySinh.getDate().getTime()));
            } else {
                nd.setDateNgSinh(null);
            }
            nd.setStrVaiTro("Khach hang");
            nd.setStrMatKhau(txtMatKhau.getText());
            nguoidungtbl.them(nd);
            xetbl.them(xe);
            khachhangtbl.them(kh);

            //Cập nhật lại Table
            capNhatLaiTable();
            JOptionPane.showMessageDialog(this, "Khách hàng mới đã được thêm vào CSDL");

        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, "Error" + e.getMessage());
            e.printStackTrace();
        }
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
                txtLoaiXe.setText(xe.getStrTenLoaiXe());
                txtBienSoXe.setText(xe.getStrBienSoXe());
            }

            txtMatKhau.setText(nd.getStrMatKhau());

            if (nd.getDateNgSinh() != null) {
                jdcNgaySinh.setDate(nd.getDateNgSinh());
            }
        }
    }//GEN-LAST:event_tblKhachHangMousePressed

    private void txtLoaiXeActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtLoaiXeActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtLoaiXeActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnCapNhat;
    private javax.swing.ButtonGroup btnGroupGioiTinh;
    private javax.swing.JButton btnLuu;
    private javax.swing.JButton btnNhapMoi;
    private javax.swing.JButton btnTimKiem;
    private javax.swing.JButton btnXoa;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel14;
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
    private javax.swing.JRadioButton rdbNam;
    private javax.swing.JRadioButton rdbNu;
    private javax.swing.JTable tblKhachHang;
    private javax.swing.JTextField txtBienSoXe;
    private javax.swing.JTextField txtDiaChi;
    private javax.swing.JTextField txtEmail;
    private javax.swing.JTextField txtHoTen;
    private javax.swing.JTextField txtLoaiXe;
    private javax.swing.JTextField txtMaKH;
    private javax.swing.JTextField txtMaXe;
    private javax.swing.JTextField txtMatKhau;
    private javax.swing.JTextField txtQueQuan;
    private javax.swing.JTextField txtSDT;
    private javax.swing.JTextField txtSoDu;
    private javax.swing.JTextField txtTimKiem;
    // End of variables declaration//GEN-END:variables
}
