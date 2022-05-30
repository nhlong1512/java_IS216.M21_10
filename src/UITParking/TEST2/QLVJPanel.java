/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JPanel.java to edit this template
 */
package UITParking.TEST2;

import UITParking.BUS.KhachHangBUS;
import UITParking.BUS.LoaiVeBUS;
import UITParking.BUS.NguoiDungBUS;
import UITParking.BUS.VeBUS;
import UITParking.BUS.XeBUS;
import UITParking.DTO.KhachHangDTO;
import UITParking.DTO.LoaiVeDTO;
import UITParking.DTO.NguoiDungDTO;
import UITParking.DTO.VeDTO;
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
public class QLVJPanel extends javax.swing.JPanel {

    /**
     * Creates new form QLVJPanel
     */
    NguoiDungBUS nguoidungtbl = new NguoiDungBUS();
    ArrayList<NguoiDungDTO> list_ND = nguoidungtbl.getList_ND();
    KhachHangBUS khachhangtbl = new KhachHangBUS();
    ArrayList<KhachHangDTO> list_KH = khachhangtbl.getList_KH();
    LoaiVeBUS loaivetbl = new LoaiVeBUS();
    ArrayList<LoaiVeDTO> list_LV = loaivetbl.getList_LV();
    VeBUS vetbl = new VeBUS();
    ArrayList<VeDTO> list_Ve = vetbl.getList_Ve();

    private DefaultTableModel model;
    private String[] columnHeaders = new String[]{"STT", "Mã Vé", "Mã Loại Vé",
        "Tên Loại Vé", "Mã khách hàng", "Ngày Kích Hoạt", "Ngày Hết Hạn", "Trạng Thái"};

    private TableRowSorter<TableModel> rowSorter = null;

    public QLVJPanel() throws Exception {
        initComponents();
        initTable();
        hoTroTimKiem();
//        btnCapNhat.setEnabled(false);
//        btnXoa.setEnabled(false);
//        btnLuu.setEnabled(false);
        txtMaLoaiVe.setEditable(false);
    }

    public void resetRender() {
        txtMaVe.setText("");
        txtMaLoaiVe.setText("");
        txtMaKH.setText("");
        txtTrangThai.setText("");
        txtMaLoaiVe.setText("");
        jdcNgayKichHoat.setDate(null);
        jdcNgayHetHan.setDate(null);

    }

    public void updateRender() {
        //Reset txtMaLoaiVe theo tên loại vé
        if (cbbTenLoaiVe.getSelectedItem().toString().equals("Vé lượt xe máy")) {
            txtMaLoaiVe.setText("LVE01");
            jdcNgayHetHan.setDate(null);
            jdcNgayKichHoat.setDate(null);
            txtTrangThai.setText("Chưa kích hoạt");

        }
        if (cbbTenLoaiVe.getSelectedItem().toString().equals("Vé lượt xe đạp")) {
            txtMaLoaiVe.setText("LVE02");
            jdcNgayHetHan.setDate(null);
            jdcNgayKichHoat.setDate(null);
            txtTrangThai.setText("Chưa kích hoạt");
        }
        if (cbbTenLoaiVe.getSelectedItem().toString().equals("Vé tuần")) {
            txtMaLoaiVe.setText("LVE03");
        }
        if (cbbTenLoaiVe.getSelectedItem().toString().equals("Vé tháng")) {
            txtMaLoaiVe.setText("LVE04");
        }
    }

    public void initTable() throws Exception {
        model = new DefaultTableModel();
        model.setColumnIdentifiers(columnHeaders);
        int index = 1;
        for (VeDTO ve : list_Ve) {
            //Lấy ra xe và biển số xe của khách hàng

            LoaiVeDTO lv = loaivetbl.getInfor(ve.getStrMaLoaiVe());
            //Cập nhật bảng
            model.addRow(new Object[]{index, ve.getStrMaVe(), ve.getStrMaLoaiVe(),
                lv.getStrTenLoaiVe(), ve.getStrMaKH(), ve.getDateNgayKichHoat(),
                ve.getDateNgayHetHan(), ve.getStrTrangThai()});
            index++;
        }

        tblVe.setModel(model);

    }

    public void hoTroTimKiem() {

        rowSorter = new TableRowSorter<>(tblVe.getModel());
        tblVe.setRowSorter(rowSorter);
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
        for (VeDTO ve : list_Ve) {
            //Lấy ra xe và biển số xe của khách hàng

            LoaiVeDTO lv = loaivetbl.getInfor(ve.getStrMaLoaiVe());
            //Cập nhật bảng
            model.addRow(new Object[]{index, ve.getStrMaVe(), ve.getStrMaLoaiVe(),
                lv.getStrTenLoaiVe(), ve.getStrMaKH(), ve.getDateNgayKichHoat(),
                ve.getDateNgayHetHan(), ve.getStrTrangThai()});
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

        jPanel1 = new javax.swing.JPanel();
        jLabel2 = new javax.swing.JLabel();
        txtMaVe = new javax.swing.JTextField();
        jLabel3 = new javax.swing.JLabel();
        txtMaKH = new javax.swing.JTextField();
        jLabel7 = new javax.swing.JLabel();
        jLabel8 = new javax.swing.JLabel();
        txtTrangThai = new javax.swing.JTextField();
        jLabel9 = new javax.swing.JLabel();
        btnNhapMoi = new javax.swing.JButton();
        btnLuu = new javax.swing.JButton();
        btnCapNhat = new javax.swing.JButton();
        btnXoa = new javax.swing.JButton();
        btnTimKiem = new javax.swing.JButton();
        txtTimKiem = new javax.swing.JTextField();
        jScrollPane1 = new javax.swing.JScrollPane();
        tblVe = new javax.swing.JTable();
        jdcNgayKichHoat = new com.toedter.calendar.JDateChooser();
        jLabel11 = new javax.swing.JLabel();
        jLabel13 = new javax.swing.JLabel();
        txtMaLoaiVe = new javax.swing.JTextField();
        jdcNgayHetHan = new com.toedter.calendar.JDateChooser();
        cbbTenLoaiVe = new javax.swing.JComboBox<>();

        jLabel2.setText("Mã Vé");

        jLabel3.setText("Mã KH");

        jLabel7.setText("Tên Loại Vé");

        jLabel8.setText("Ngày Kích Hoạt");

        txtTrangThai.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtTrangThaiActionPerformed(evt);
            }
        });

        jLabel9.setText("Trạng Thái");

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

        tblVe.setModel(new javax.swing.table.DefaultTableModel(
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
        tblVe.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                tblVeMousePressed(evt);
            }
        });
        jScrollPane1.setViewportView(tblVe);

        jLabel11.setText("Ngày Hết Hạn");

        jLabel13.setText("Mã Loại Vé");

        cbbTenLoaiVe.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Vé lượt xe máy", "Vé lượt xe đạp", "Vé tuần", "Vé tháng" }));
        cbbTenLoaiVe.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                cbbTenLoaiVeItemStateChanged(evt);
            }
        });

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jScrollPane1, javax.swing.GroupLayout.Alignment.TRAILING)
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
                                .addGap(0, 0, Short.MAX_VALUE)))
                        .addContainerGap())
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(jLabel9, javax.swing.GroupLayout.PREFERRED_SIZE, 66, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(txtTrangThai, javax.swing.GroupLayout.PREFERRED_SIZE, 185, javax.swing.GroupLayout.PREFERRED_SIZE))))
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(10, 10, 10)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 64, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(txtMaVe))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(jLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, 55, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(txtMaKH, javax.swing.GroupLayout.PREFERRED_SIZE, 188, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(38, 38, 38)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(jLabel7, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(cbbTenLoaiVe, javax.swing.GroupLayout.PREFERRED_SIZE, 217, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(jLabel8, javax.swing.GroupLayout.PREFERRED_SIZE, 87, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jdcNgayKichHoat, javax.swing.GroupLayout.PREFERRED_SIZE, 218, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 35, Short.MAX_VALUE)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                    .addComponent(jLabel11, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jLabel13, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.PREFERRED_SIZE, 70, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(txtMaLoaiVe, javax.swing.GroupLayout.PREFERRED_SIZE, 175, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jdcNgayHetHan, javax.swing.GroupLayout.PREFERRED_SIZE, 179, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(17, 17, 17))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(20, 20, 20)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                .addComponent(jLabel7, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(cbbTenLoaiVe, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 22, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(txtMaVe, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGap(0, 0, Short.MAX_VALUE)))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                .addComponent(jLabel3)
                                .addComponent(txtMaKH, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addComponent(jdcNgayKichHoat, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel8, javax.swing.GroupLayout.PREFERRED_SIZE, 19, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel13)
                            .addComponent(txtMaLoaiVe, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel11, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 19, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jdcNgayHetHan, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel9, javax.swing.GroupLayout.PREFERRED_SIZE, 21, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtTrangThai))
                .addGap(22, 22, 22)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnNhapMoi)
                    .addComponent(btnLuu)
                    .addComponent(btnCapNhat)
                    .addComponent(btnXoa)
                    .addComponent(btnTimKiem)
                    .addComponent(txtTimKiem, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 247, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(119, 119, 119))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
        );
    }// </editor-fold>//GEN-END:initComponents

    private void tblVeMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tblVeMousePressed
        // TODO add your handling code here:
        int selectedRow = tblVe.getSelectedRow();
        if (selectedRow >= 0) {

            VeDTO ve = list_Ve.get(selectedRow);
            LoaiVeDTO lv = loaivetbl.getInfor(ve.getStrMaLoaiVe());

            txtMaVe.setText(ve.getStrMaVe());
            txtMaKH.setText(ve.getStrMaKH());
            txtMaLoaiVe.setText(ve.getStrMaLoaiVe());
            txtTrangThai.setText(ve.getStrTrangThai());
            cbbTenLoaiVe.setSelectedItem(lv.getStrTenLoaiVe());

            if (ve.getDateNgayKichHoat() != null) {
                jdcNgayKichHoat.setDate(ve.getDateNgayKichHoat());
            }
            if (ve.getDateNgayHetHan() != null) {
                jdcNgayHetHan.setDate(ve.getDateNgayHetHan());
            }
        }
    }//GEN-LAST:event_tblVeMousePressed

    private void btnTimKiemMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnTimKiemMouseClicked
        // TODO add your handling code here:
//        if (txtMaVe.getText().equals("")) {
//            JOptionPane.showMessageDialog(this, "Mã khách hàng phải được nhập để tìm kiếm");
//            return;
//        }
//
//        try {
//            NguoiDungBUS nguoidungtbl = new NguoiDungBUS();
//            NguoiDungDTO nd = nguoidungtbl.getInfor(txtMaVe.getText());
//
//            if (nd != null) {
//                txtMaVe.setText(nd.getStrMaND());
//                txtMaKH.setText(nd.getStrEmail());
//                txtMaLoaiVe.setText(nd.getStrHoTen());
//                txtDiaChi.setText(nd.getStrDiaChi());
//                txtQueQuan.setText(nd.getStrQueQuan());
//                txtTrangThai.setText(nd.getStrSDT());
//                rdbNam.setSelected(nd.getStrGioiTinh().equals("Nam"));
//                rdbNu.setSelected(nd.getStrGioiTinh().equals("Nu"));
//            } else {
//                JOptionPane.showMessageDialog(this, "Khách hàng không tìm thấy");
//            }
//
//        } catch (Exception e) {
//            JOptionPane.showMessageDialog(this, "Error: " + e.getMessage());
//            e.printStackTrace();
//        }
    }//GEN-LAST:event_btnTimKiemMouseClicked

    private void btnXoaMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnXoaMouseClicked
        // TODO add your handling code here:
        StringBuilder sb = new StringBuilder();
        if (txtMaVe.getText().equals("")) {
            sb.append("Mã vé không được để trống.");
            txtMaVe.setBackground(Color.red);
        } else {
            txtMaVe.setBackground(Color.white);
        }
        if (sb.length() > 0) {
            JOptionPane.showMessageDialog(this, sb);
            return;
        }

        if (JOptionPane.showConfirmDialog(this, "Bạn có muốn xóa không?") == JOptionPane.NO_OPTION) {
            return;
        }
        try {
            VeDTO ve = vetbl.getInfor(txtMaVe.getText());
            vetbl.xoa(ve);

            JOptionPane.showMessageDialog(this, "Vé đã xóa khỏi CSDL");

            //Reset lại render
            resetRender();

            //Cập nhật lại bảng
            capNhatLaiTable();

        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, "Error" + e.getMessage());
            e.printStackTrace();
        }
    }//GEN-LAST:event_btnXoaMouseClicked

    private void btnCapNhatMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnCapNhatMouseClicked
        // TODO add your handling code here:
        StringBuilder sb = new StringBuilder();
        if (txtMaVe.getText().equals("")) {
            sb.append("Mã vé không được để trống.");
            txtMaVe.setBackground(Color.red);
        } else {
            txtMaVe.setBackground(Color.white);
        }
        if (vetbl.getInfor(txtMaVe.getText()) == null) {
            sb.append("Mã vé không tồn tại.");
        }
        if (khachhangtbl.getInfor(txtMaKH.getText()) == null) {
            sb.append("Mã khách hàng không tồn tại.");
        }
        if (loaivetbl.getInfor(txtMaLoaiVe.getText()) == null) {
            sb.append("Mã loại vé không tồn tại.");
        }
        if (sb.length() > 0) {
            JOptionPane.showMessageDialog(this, sb);
            return;
        }
        try {
            VeDTO ve = new VeDTO();
            LoaiVeDTO lv = new LoaiVeDTO();
            ve.setStrMaVe(txtMaVe.getText());
            ve.setStrMaKH(txtMaKH.getText());

            ve.setStrTrangThai(txtTrangThai.getText());
            lv.setStrTenLoaiVe(cbbTenLoaiVe.getSelectedItem().toString());

            updateRender();

            System.out.println(jdcNgayKichHoat.getDate());
            System.out.println(jdcNgayHetHan.getDate());
            ve.setStrMaLoaiVe(txtMaLoaiVe.getText());
            lv.setStrMaLoaiVe(txtMaLoaiVe.getText());

            if (jdcNgayKichHoat.getDate() != null) {
                ve.setDateNgayKichHoat(new java.sql.Date(jdcNgayKichHoat.getDate().getTime()));
            } else {
                ve.setDateNgayKichHoat(null);
            }

            if (jdcNgayHetHan.getDate() != null) {
                ve.setDateNgayHetHan(new java.sql.Date(jdcNgayHetHan.getDate().getTime()));
            } else {
                ve.setDateNgayHetHan(null);
            }
            vetbl.sua(ve);

            //Cập nhật lại Table
            capNhatLaiTable();

            JOptionPane.showMessageDialog(this, "Vé đã được cập nhật vào CSDL");

        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, "Error" + e.getMessage());
            e.printStackTrace();
        }
    }//GEN-LAST:event_btnCapNhatMouseClicked

    private void btnLuuMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnLuuMouseClicked
        StringBuilder sb = new StringBuilder();
        if (txtMaVe.getText().equals("")) {
            sb.append("Mã vé không được để trống.");
            txtMaVe.setBackground(Color.red);
        } else {
            txtMaVe.setBackground(Color.white);
        }
        if (vetbl.getInfor(txtMaVe.getText()) != null) {
            sb.append("Mã vé đã tồn tại.");
        }

        if (sb.length() > 0) {
            JOptionPane.showMessageDialog(this, sb);
            return;
        }
        try {
            VeDTO ve = new VeDTO();
            LoaiVeDTO lv = new LoaiVeDTO();
            ve.setStrMaVe(txtMaVe.getText());
            ve.setStrMaKH(txtMaKH.getText());
            updateRender();
            ve.setStrMaLoaiVe(txtMaLoaiVe.getText());
            ve.setStrTrangThai(txtTrangThai.getText());

            if (jdcNgayKichHoat.getDate() != null) {
                ve.setDateNgayKichHoat(new java.sql.Date(jdcNgayKichHoat.getDate().getTime()));
            } else {
                ve.setDateNgayKichHoat(null);
            }

            if (jdcNgayHetHan.getDate() != null) {
                ve.setDateNgayHetHan(new java.sql.Date(jdcNgayHetHan.getDate().getTime()));
            } else {
                ve.setDateNgayHetHan(null);
            }

            vetbl.them(ve);

            //Cập nhật lại Table
            capNhatLaiTable();
            JOptionPane.showMessageDialog(this, "Khách hàng mới đã được thêm vào CSDL");

        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, "Error" + e.getMessage());
            e.printStackTrace();
        }
    }//GEN-LAST:event_btnLuuMouseClicked

    private void btnNhapMoiMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnNhapMoiMouseClicked
        resetRender();
    }//GEN-LAST:event_btnNhapMoiMouseClicked

    private void txtTrangThaiActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtTrangThaiActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtTrangThaiActionPerformed

    private void cbbTenLoaiVeItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_cbbTenLoaiVeItemStateChanged
        // TODO add your handling code here:
        updateRender();
    }//GEN-LAST:event_cbbTenLoaiVeItemStateChanged


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnCapNhat;
    private javax.swing.JButton btnLuu;
    private javax.swing.JButton btnNhapMoi;
    private javax.swing.JButton btnTimKiem;
    private javax.swing.JButton btnXoa;
    private javax.swing.JComboBox<String> cbbTenLoaiVe;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JScrollPane jScrollPane1;
    private com.toedter.calendar.JDateChooser jdcNgayHetHan;
    private com.toedter.calendar.JDateChooser jdcNgayKichHoat;
    private javax.swing.JTable tblVe;
    private javax.swing.JTextField txtMaKH;
    private javax.swing.JTextField txtMaLoaiVe;
    private javax.swing.JTextField txtMaVe;
    private javax.swing.JTextField txtTimKiem;
    private javax.swing.JTextField txtTrangThai;
    // End of variables declaration//GEN-END:variables
}
