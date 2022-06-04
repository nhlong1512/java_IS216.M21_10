/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package UITParking.GUI;

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
import static UITParking.GUI.login.pMaND;
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
public class ThongTinVe extends javax.swing.JFrame {

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

    /**
     * Creates new form ThongTinVe
     */
    public ThongTinVe() throws Exception {
        initComponents();
        initTable();
        hoTroTimKiem();
        setLocationRelativeTo(null);
        txtMaLoaiVe.setEditable(false);
    }

    public void resetRender() {
//        txtMaVe.setText("");
//        txtMaLoaiVe.setText("");
//        txtMaKH.setText("");
//        txtTrangThai.setText("");
//        txtMaLoaiVe.setText("");
//        jdcNgayKichHoat.setDate(null);
//        jdcNgayHetHan.setDate(null);

    }

    public void updateRender() {
//        //Reset txtMaLoaiVe theo tên loại vé
//        if (cbbTenLoaiVe.getSelectedItem().toString().equals("Vé lượt xe máy")) {
//            txtMaLoaiVe.setText("LVE01");
//            jdcNgayHetHan.setDate(null);
//            jdcNgayKichHoat.setDate(null);
//            txtTrangThai.setText("Chưa kích hoạt");
//
//        }
//        if (cbbTenLoaiVe.getSelectedItem().toString().equals("Vé lượt xe đạp")) {
//            txtMaLoaiVe.setText("LVE02");
//            jdcNgayHetHan.setDate(null);
//            jdcNgayKichHoat.setDate(null);
//            txtTrangThai.setText("Chưa kích hoạt");
//        }
//        if (cbbTenLoaiVe.getSelectedItem().toString().equals("Vé tuần")) {
//            txtMaLoaiVe.setText("LVE03");
//        }
//        if (cbbTenLoaiVe.getSelectedItem().toString().equals("Vé tháng")) {
//            txtMaLoaiVe.setText("LVE04");
//        }
    }

    public void initTable() throws Exception {
        model = new DefaultTableModel();
        model.setColumnIdentifiers(columnHeaders);
        int index = 1;
        for (VeDTO ve : list_Ve) {
            //Lấy ra xe và biển số xe của khách hàng
            if (ve.getStrMaKH().equals(pMaND)) {
                LoaiVeDTO lv = loaivetbl.getInfor(ve.getStrMaLoaiVe());
                model.addRow(new Object[]{index, ve.getStrMaVe(), ve.getStrMaLoaiVe(),
                    lv.getStrTenLoaiVe(), ve.getStrMaKH(), ve.getDateNgayKichHoat(),
                    ve.getDateNgayHetHan(), ve.getStrTrangThai()});
                index++;
            }

            //Cập nhật bảng
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
//        list_ND = nguoidungtbl.getList_ND();
//        model.setRowCount(0);
//        int index = 1;
//        for (VeDTO ve : list_Ve) {
//            //Lấy ra xe và biển số xe của khách hàng
//
//            LoaiVeDTO lv = loaivetbl.getInfor(ve.getStrMaLoaiVe());
//            //Cập nhật bảng
//            model.addRow(new Object[]{index, ve.getStrMaVe(), ve.getStrMaLoaiVe(),
//                lv.getStrTenLoaiVe(), ve.getStrMaKH(), ve.getDateNgayKichHoat(),
//                ve.getDateNgayHetHan(), ve.getStrTrangThai()});
//            index++;
//        }
//        model.fireTableDataChanged();
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
        btnBack = new javax.swing.JButton();
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
        btnKichHoat = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

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

        btnBack.setText("Back");
        btnBack.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                btnBackMouseClicked(evt);
            }
        });
        jPanel1.add(btnBack, new org.netbeans.lib.awtextra.AbsoluteConstraints(220, 170, -1, -1));

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

        btnKichHoat.setText("Kích Hoạt");
        btnKichHoat.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                btnKichHoatMouseClicked(evt);
            }
        });
        jPanel1.add(btnKichHoat, new org.netbeans.lib.awtextra.AbsoluteConstraints(420, 170, -1, -1));

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void txtMaVeCaretUpdate(javax.swing.event.CaretEvent evt) {//GEN-FIRST:event_txtMaVeCaretUpdate
        // TODO add your handling code here:
    }//GEN-LAST:event_txtMaVeCaretUpdate

    private void txtTrangThaiActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtTrangThaiActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtTrangThaiActionPerformed

    private void btnBackMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnBackMouseClicked
        Homepage _homepage = null;
        try {
            _homepage = new Homepage();
        } catch (Exception ex) {
            Logger.getLogger(ThongTinVe.class.getName()).log(Level.SEVERE, null, ex);
        }
        _homepage.show();
        dispose();
    }//GEN-LAST:event_btnBackMouseClicked

    private void btnTimKiemMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnTimKiemMouseClicked
        // TODO add your handling code here:
    }//GEN-LAST:event_btnTimKiemMouseClicked

    private void tblVeMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tblVeMousePressed
        // TODO add your handling code here:
//        resetRender();
//        int selectedRow = tblVe.getSelectedRow();
//        if (selectedRow >= 0) {
//
//            VeDTO ve = list_Ve.get(selectedRow);
//            LoaiVeDTO lv = loaivetbl.getInfor(ve.getStrMaLoaiVe());
//
//            txtMaVe.setText(ve.getStrMaVe());
//            txtMaKH.setText(ve.getStrMaKH());
//            txtMaLoaiVe.setText(ve.getStrMaLoaiVe());
//            txtTrangThai.setText(ve.getStrTrangThai());
//            cbbTenLoaiVe.setSelectedItem(lv.getStrTenLoaiVe());
//
//            if (ve.getDateNgayKichHoat() != null) {
//                jdcNgayKichHoat.setDate(ve.getDateNgayKichHoat());
//            }
//            if (ve.getDateNgayHetHan() != null) {
//                jdcNgayHetHan.setDate(ve.getDateNgayHetHan());
//            }
//        }
    }//GEN-LAST:event_tblVeMousePressed

    private void cbbTenLoaiVeItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_cbbTenLoaiVeItemStateChanged
        // TODO add your handling code here:
        updateRender();
    }//GEN-LAST:event_cbbTenLoaiVeItemStateChanged

    private void btnKichHoatMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnKichHoatMouseClicked
        // TODO add your handling code here:
    }//GEN-LAST:event_btnKichHoatMouseClicked

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(ThongTinVe.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(ThongTinVe.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(ThongTinVe.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(ThongTinVe.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                try {
                    new ThongTinVe().setVisible(true);
                } catch (Exception ex) {
                    Logger.getLogger(ThongTinVe.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnBack;
    private javax.swing.JButton btnKichHoat;
    private javax.swing.JButton btnTimKiem;
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
