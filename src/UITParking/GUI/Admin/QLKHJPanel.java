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

        jPanel1.setBackground(new java.awt.Color(255, 255, 255));
        jPanel1.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel2.setText("Mã khách hàng");
        jPanel1.add(jLabel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 20, -1, 22));

        txtMaKH.setEditable(false);
        jPanel1.add(txtMaKH, new org.netbeans.lib.awtextra.AbsoluteConstraints(97, 20, 200, -1));

        jLabel3.setText("Email");
        jPanel1.add(jLabel3, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 60, 55, 20));
        jPanel1.add(txtEmail, new org.netbeans.lib.awtextra.AbsoluteConstraints(100, 60, 200, -1));

        jLabel4.setText("Địa chỉ");
        jPanel1.add(jLabel4, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 100, 51, 20));
        jPanel1.add(txtDiaChi, new org.netbeans.lib.awtextra.AbsoluteConstraints(100, 100, 200, -1));

        jLabel5.setText("Quê quán");
        jPanel1.add(jLabel5, new org.netbeans.lib.awtextra.AbsoluteConstraints(350, 100, 58, 20));
        jPanel1.add(txtQueQuan, new org.netbeans.lib.awtextra.AbsoluteConstraints(420, 100, 220, -1));

        jLabel6.setText("Giới tính");
        jPanel1.add(jLabel6, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 140, 55, 24));

        btnGroupGioiTinh.add(rdbNam);
        rdbNam.setText("Nam");
        jPanel1.add(rdbNam, new org.netbeans.lib.awtextra.AbsoluteConstraints(100, 140, -1, 20));

        btnGroupGioiTinh.add(rdbNu);
        rdbNu.setText("Nữ");
        jPanel1.add(rdbNu, new org.netbeans.lib.awtextra.AbsoluteConstraints(150, 140, -1, 20));

        jLabel7.setText("Họ tên");
        jPanel1.add(jLabel7, new org.netbeans.lib.awtextra.AbsoluteConstraints(349, 20, 55, 22));
        jPanel1.add(txtHoTen, new org.netbeans.lib.awtextra.AbsoluteConstraints(420, 20, 219, -1));

        jLabel8.setText("Ngày sinh");
        jPanel1.add(jLabel8, new org.netbeans.lib.awtextra.AbsoluteConstraints(350, 60, 55, 19));
        jPanel1.add(txtSDT, new org.netbeans.lib.awtextra.AbsoluteConstraints(420, 140, 220, -1));

        jLabel9.setText("Số ĐT");
        jPanel1.add(jLabel9, new org.netbeans.lib.awtextra.AbsoluteConstraints(350, 140, 55, 20));

        btnNhapMoi.setText("Nhập mới");
        btnNhapMoi.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                btnNhapMoiMouseClicked(evt);
            }
        });
        jPanel1.add(btnNhapMoi, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 210, -1, -1));

        btnLuu.setText("Lưu");
        btnLuu.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                btnLuuMouseClicked(evt);
            }
        });
        jPanel1.add(btnLuu, new org.netbeans.lib.awtextra.AbsoluteConstraints(130, 210, 57, -1));

        btnCapNhat.setText("Cập nhật");
        btnCapNhat.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                btnCapNhatMouseClicked(evt);
            }
        });
        jPanel1.add(btnCapNhat, new org.netbeans.lib.awtextra.AbsoluteConstraints(210, 210, -1, -1));

        btnXoa.setText("Xóa");
        btnXoa.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                btnXoaMouseClicked(evt);
            }
        });
        jPanel1.add(btnXoa, new org.netbeans.lib.awtextra.AbsoluteConstraints(320, 210, -1, -1));

        btnTimKiem.setText("Tìm kiếm");
        btnTimKiem.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                btnTimKiemMouseClicked(evt);
            }
        });
        jPanel1.add(btnTimKiem, new org.netbeans.lib.awtextra.AbsoluteConstraints(660, 210, -1, -1));
        jPanel1.add(txtTimKiem, new org.netbeans.lib.awtextra.AbsoluteConstraints(760, 210, 144, -1));

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

        jPanel1.add(jScrollPane1, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 250, 1110, 240));
        jPanel1.add(jdcNgaySinh, new org.netbeans.lib.awtextra.AbsoluteConstraints(420, 60, 220, -1));

        jLabel10.setText("Loại xe");
        jLabel10.setToolTipText("");
        jPanel1.add(jLabel10, new org.netbeans.lib.awtextra.AbsoluteConstraints(690, 60, 50, 19));

        jLabel11.setText("Biển số xe");
        jPanel1.add(jLabel11, new org.netbeans.lib.awtextra.AbsoluteConstraints(690, 100, 60, 20));
        jPanel1.add(txtBienSoXe, new org.netbeans.lib.awtextra.AbsoluteConstraints(770, 100, 190, -1));
        jPanel1.add(txtSoDu, new org.netbeans.lib.awtextra.AbsoluteConstraints(770, 140, 190, -1));

        jLabel12.setText("Số dư");
        jPanel1.add(jLabel12, new org.netbeans.lib.awtextra.AbsoluteConstraints(690, 140, 52, 20));

        jLabel13.setText("Mã xe");
        jPanel1.add(jLabel13, new org.netbeans.lib.awtextra.AbsoluteConstraints(690, 20, 50, 20));

        txtMaXe.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtMaXeActionPerformed(evt);
            }
        });
        jPanel1.add(txtMaXe, new org.netbeans.lib.awtextra.AbsoluteConstraints(770, 20, 190, -1));

        lblMatKhau.setText("Mật khẩu");
        jPanel1.add(lblMatKhau, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 170, 60, 20));
        jPanel1.add(txtMatKhau, new org.netbeans.lib.awtextra.AbsoluteConstraints(100, 170, 188, -1));

        cbbLoaiXe.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Xe đạp", "Xe máy" }));
        cbbLoaiXe.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                cbbLoaiXeItemStateChanged(evt);
            }
        });
        jPanel1.add(cbbLoaiXe, new org.netbeans.lib.awtextra.AbsoluteConstraints(770, 50, 190, 30));

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, 1140, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
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
