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
                lv.getStrTenLoaiVe(), ve.getStrMaKH(),
                (ve.getDateNgayKichHoat() != null ? formatDate(ve.getDateNgayKichHoat()) : ve.getDateNgayKichHoat()),
                (ve.getDateNgayHetHan() != null ? formatDate(ve.getDateNgayHetHan()) : ve.getDateNgayHetHan()), 
                ve.getStrTrangThai()});
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
                lv.getStrTenLoaiVe(), ve.getStrMaKH(),
                (ve.getDateNgayKichHoat() != null ? formatDate(ve.getDateNgayKichHoat()) : ve.getDateNgayKichHoat()),
                (ve.getDateNgayHetHan() != null ? formatDate(ve.getDateNgayHetHan()) : ve.getDateNgayHetHan()), ve.getStrTrangThai()});
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

        setBackground(new java.awt.Color(255, 255, 255));

        jPanel1.setBackground(new java.awt.Color(255, 255, 255));
        jPanel1.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel2.setText("Mã vé");
        jPanel1.add(jLabel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 20, 64, 22));

        txtMaVe.addCaretListener(new javax.swing.event.CaretListener() {
            public void caretUpdate(javax.swing.event.CaretEvent evt) {
                txtMaVeCaretUpdate(evt);
            }
        });
        jPanel1.add(txtMaVe, new org.netbeans.lib.awtextra.AbsoluteConstraints(110, 20, 190, -1));

        jLabel3.setText("Mã khách hàng");
        jPanel1.add(jLabel3, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 60, 90, 20));
        jPanel1.add(txtMaKH, new org.netbeans.lib.awtextra.AbsoluteConstraints(110, 60, 190, -1));

        jLabel7.setText("Tên loại vé");
        jPanel1.add(jLabel7, new org.netbeans.lib.awtextra.AbsoluteConstraints(330, 20, 82, 20));

        jLabel8.setText("Ngày kích hoạt");
        jPanel1.add(jLabel8, new org.netbeans.lib.awtextra.AbsoluteConstraints(330, 60, 87, 19));

        txtTrangThai.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtTrangThaiActionPerformed(evt);
            }
        });
        jPanel1.add(txtTrangThai, new org.netbeans.lib.awtextra.AbsoluteConstraints(110, 110, 190, -1));

        jLabel9.setText("Trạng thái");
        jPanel1.add(jLabel9, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 110, 66, 21));

        btnNhapMoi.setText("Nhập mới");
        btnNhapMoi.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                btnNhapMoiMouseClicked(evt);
            }
        });
        jPanel1.add(btnNhapMoi, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 170, -1, -1));

        btnLuu.setText("Lưu");
        btnLuu.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                btnLuuMouseClicked(evt);
            }
        });
        jPanel1.add(btnLuu, new org.netbeans.lib.awtextra.AbsoluteConstraints(130, 170, 57, -1));

        btnCapNhat.setText("Cập nhật");
        btnCapNhat.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                btnCapNhatMouseClicked(evt);
            }
        });
        jPanel1.add(btnCapNhat, new org.netbeans.lib.awtextra.AbsoluteConstraints(220, 170, -1, -1));

        btnXoa.setText("Xóa");
        btnXoa.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                btnXoaMouseClicked(evt);
            }
        });
        jPanel1.add(btnXoa, new org.netbeans.lib.awtextra.AbsoluteConstraints(330, 170, -1, -1));

        btnTimKiem.setText("Tìm kiếm");
        btnTimKiem.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                btnTimKiemMouseClicked(evt);
            }
        });
        jPanel1.add(btnTimKiem, new org.netbeans.lib.awtextra.AbsoluteConstraints(600, 170, -1, -1));
        jPanel1.add(txtTimKiem, new org.netbeans.lib.awtextra.AbsoluteConstraints(700, 170, 144, -1));

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

        jPanel1.add(jScrollPane1, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 220, 926, 247));
        jPanel1.add(jdcNgayKichHoat, new org.netbeans.lib.awtextra.AbsoluteConstraints(420, 60, 200, -1));

        jLabel11.setText("Ngày hết hạn");
        jPanel1.add(jLabel11, new org.netbeans.lib.awtextra.AbsoluteConstraints(650, 60, -1, 19));

        jLabel13.setText("Mã Loại Vé");
        jPanel1.add(jLabel13, new org.netbeans.lib.awtextra.AbsoluteConstraints(655, 19, 70, 20));
        jPanel1.add(txtMaLoaiVe, new org.netbeans.lib.awtextra.AbsoluteConstraints(737, 20, 180, -1));
        jPanel1.add(jdcNgayHetHan, new org.netbeans.lib.awtextra.AbsoluteConstraints(739, 60, 180, -1));

        cbbTenLoaiVe.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Vé lượt xe máy", "Vé lượt xe đạp", "Vé tuần", "Vé tháng" }));
        cbbTenLoaiVe.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                cbbTenLoaiVeItemStateChanged(evt);
            }
        });
        jPanel1.add(cbbTenLoaiVe, new org.netbeans.lib.awtextra.AbsoluteConstraints(420, 20, 200, -1));

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
        resetRender();
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

    private void txtMaVeCaretUpdate(javax.swing.event.CaretEvent evt) {//GEN-FIRST:event_txtMaVeCaretUpdate
        // TODO add your handling code here:
    }//GEN-LAST:event_txtMaVeCaretUpdate


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
