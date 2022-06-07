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
import static UITParking.GUI.InitPublic.formatDate;
import static UITParking.GUI.InitPublic.getConvertStringToLocalDate;
import static UITParking.GUI.InitPublic.getDateThoiGianThuc;
import static UITParking.GUI.InitPublic.getDateThoiGianVeThang;
import static UITParking.GUI.InitPublic.getDateThoiGianVeTuan;
import static UITParking.GUI.InitPublic.sysdate;
import static UITParking.GUI.login.pMaND;
import java.awt.Color;
import java.awt.Toolkit;
import java.text.ParseException;
import java.time.Instant;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.Date;
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

    //Set biến loại vé hiện tại để lưu loại vé được pressed
    public static String loaiVeHienTai = "";
    //Set biến mã vé hiện tại lưu mã vé được pressed
    public static String maVeHienTai = "";
    //Set biến lưu trạng thái vé hiện tại, nếu là vé đang sử dụng của vé tuần
    //Vé tháng thì sẽ không cho phép kích hoạt
    public static String trangThaiVeHienTai = "";

    public static LocalDate d1 = LocalDate.now();
    private DefaultTableModel model;
    private String[] columnHeaders = new String[]{"STT", "Mã Vé",
        "Tên Loại Vé", "Ngày Kích Hoạt", "Ngày Hết Hạn", "Trạng Thái"};

    private TableRowSorter<TableModel> rowSorter = null;

    /**
     * Creates new form ThongTinVe
     */
    public ThongTinVe() throws Exception {
        initComponents();
        initTable();
        hoTroTimKiem();
        setLocationRelativeTo(null);
//        txtMaLoaiVe.setEditable(false);
        capNhatTrangThai();
        setIconImage();
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

    /**
     * Sẽ cập nhật trạng thái vé nếu sysdate lớn hơn ngày hết hạn thì sẽ chuyển
     * trạng thái sang đã hết hạn
     */
    public void capNhatTrangThai() throws Exception {

        for (VeDTO ve : list_Ve) {
            if (ve.getDateNgayHetHan() != null) {
                System.out.println(ve.getStrMaVe());
                
                String stringDate = (new java.sql.Date(ve.getDateNgayHetHan().getTime())).toString();
                Boolean isHetHan = d1.isAfter(getConvertStringToLocalDate(stringDate));
                System.out.println((new java.sql.Date(ve.getDateNgayHetHan().getTime())).toString());
                System.out.println(getConvertStringToLocalDate(stringDate));
                System.out.println(isHetHan);
                
                /**
                 * 
                 * Nếu isHetHan true thì cập nhật trạng thái đã hết hạn
                 * Sau đó sẽ cập nhật lại table
                 */ 
                if(isHetHan){
                    ve.setStrTrangThai("Đã hết hạn");
                    vetbl.sua(ve);
                    capNhatLaiTable();
                }
                

            }
        }
    }

    /**
     * Update render sẽ cập nhật lại trạng thái vé mỗi lần render ra màn hình
     */
    public void updateRender() {
    }

    public void initTable() throws Exception {
        model = new DefaultTableModel();
        model.setColumnIdentifiers(columnHeaders);
        int index = 1;
        for (VeDTO ve : list_Ve) {
            //Lấy ra xe và biển số xe của khách hàng
            if (ve.getStrMaKH().equals(pMaND)) {
                LoaiVeDTO lv = loaivetbl.getInfor(ve.getStrMaLoaiVe());
                model.addRow(new Object[]{index, ve.getStrMaVe(), lv.getStrTenLoaiVe(), 
                    (ve.getDateNgayKichHoat() != null ? formatDate(ve.getDateNgayKichHoat()) : ve.getDateNgayKichHoat()),
                (ve.getDateNgayHetHan() != null ? formatDate(ve.getDateNgayHetHan()) : ve.getDateNgayHetHan()), ve.getStrTrangThai()});
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
        model.setRowCount(0);
        int index = 1;
        for (VeDTO ve : list_Ve) {
            //Lấy ra xe và biển số xe của khách hàng
            if (ve.getStrMaKH().equals(pMaND)) {
                LoaiVeDTO lv = loaivetbl.getInfor(ve.getStrMaLoaiVe());
                model.addRow(new Object[]{index, ve.getStrMaVe(), lv.getStrTenLoaiVe(), 
                    (ve.getDateNgayKichHoat() != null ? formatDate(ve.getDateNgayKichHoat()) : ve.getDateNgayKichHoat()),
                (ve.getDateNgayHetHan() != null ? formatDate(ve.getDateNgayHetHan()) : ve.getDateNgayHetHan()), ve.getStrTrangThai()});
                index++;
            }

            //Cập nhật bảng
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
        txtTimKiem = new javax.swing.JTextField();
        btnTimKiem = new javax.swing.JButton();
        btnBack = new javax.swing.JButton();
        btnKichHoat = new javax.swing.JButton();
        jLabel2 = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        tblVe = new javax.swing.JTable();
        jLabel1 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("UIT Parking");

        jPanel1.setBackground(new java.awt.Color(255, 255, 255));
        jPanel1.setForeground(new java.awt.Color(107, 122, 161));
        jPanel1.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        txtTimKiem.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtTimKiemActionPerformed(evt);
            }
        });
        jPanel1.add(txtTimKiem, new org.netbeans.lib.awtextra.AbsoluteConstraints(50, 130, 210, 30));

        btnTimKiem.setBackground(new java.awt.Color(52, 79, 115));
        btnTimKiem.setFont(new java.awt.Font("Cooper", 0, 14)); // NOI18N
        btnTimKiem.setForeground(new java.awt.Color(255, 255, 255));
        btnTimKiem.setText("Tìm kiếm");
        btnTimKiem.setBorder(null);
        btnTimKiem.setBorderPainted(false);
        btnTimKiem.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                btnTimKiemMouseClicked(evt);
            }
        });
        jPanel1.add(btnTimKiem, new org.netbeans.lib.awtextra.AbsoluteConstraints(260, 130, 90, 30));

        btnBack.setBackground(new java.awt.Color(52, 79, 115));
        btnBack.setFont(new java.awt.Font("Cooper", 0, 14)); // NOI18N
        btnBack.setForeground(new java.awt.Color(255, 255, 255));
        btnBack.setText("Quay lại");
        btnBack.setBorder(null);
        btnBack.setBorderPainted(false);
        btnBack.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                btnBackMouseClicked(evt);
            }
        });
        btnBack.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnBackActionPerformed(evt);
            }
        });
        jPanel1.add(btnBack, new org.netbeans.lib.awtextra.AbsoluteConstraints(50, 90, 80, 30));

        btnKichHoat.setBackground(new java.awt.Color(52, 79, 115));
        btnKichHoat.setFont(new java.awt.Font("Cooper", 0, 14)); // NOI18N
        btnKichHoat.setForeground(new java.awt.Color(255, 255, 255));
        btnKichHoat.setText("Kích Hoạt");
        btnKichHoat.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                btnKichHoatMouseClicked(evt);
            }
        });
        jPanel1.add(btnKichHoat, new org.netbeans.lib.awtextra.AbsoluteConstraints(140, 90, -1, 30));

        jLabel2.setFont(new java.awt.Font("Cooper", 0, 36)); // NOI18N
        jLabel2.setForeground(new java.awt.Color(52, 79, 115));
        jLabel2.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel2.setText("THÔNG TIN VÉ");
        jPanel1.add(jLabel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 20, 870, 60));

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
        tblVe.setFillsViewportHeight(true);
        tblVe.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                tblVeMousePressed(evt);
            }
        });
        jScrollPane1.setViewportView(tblVe);

        jPanel1.add(jScrollPane1, new org.netbeans.lib.awtextra.AbsoluteConstraints(50, 200, 790, 180));

        jLabel1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icon/wepik--202255-22401.png"))); // NOI18N
        jPanel1.add(jLabel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 880, 420));

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, 874, javax.swing.GroupLayout.PREFERRED_SIZE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
        );

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

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

    private void btnBackActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnBackActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_btnBackActionPerformed

    private void txtTimKiemActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtTimKiemActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtTimKiemActionPerformed

    /**
     *
     * Xử lý event click button kích hoạt, ngày kích hoạt sẽ được cập nhật ngay
     * tại thời gian hiện tại.
     *
     */
    private void btnKichHoatMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnKichHoatMouseClicked
        /**
        * Nếu loại vé hiện tại là loại vé tuần hoặc vé tháng và trạng thái của
        * vé là chưa kích hoạt thì cho phép bắt sự kiện click button kích hoạt
        * ngược lại thì không làm gì cả.
        */
        if ((loaiVeHienTai.equals("Ve tuan") || loaiVeHienTai.equals("Ve thang"))
            && trangThaiVeHienTai.equals("Chưa kích hoạt")) {
            System.out.println("Long dep trai");
            try {
                System.out.println(sysdate());
                System.out.println(getDateThoiGianVeTuan());
                System.out.println(getDateThoiGianVeThang());

            } catch (ParseException ex) {
                Logger.getLogger(ThongTinVe.class.getName()).log(Level.SEVERE, null, ex);
            }

            /**
            * Cập nhật lại thời gian thực cho trường ngày kích hoạt của bảng
            * C_VE, ngày hết hạn sẽ được cộng dựa vào loại vé, Trạng thái sẽ
            * được chuyển thành đang sử dụng
            */
            VeDTO ve = new VeDTO();
            ve = vetbl.getInfor(maVeHienTai);
            System.out.println(ve);
            try {
                ve.setDateNgayKichHoat(sysdate());
                ve.setStrTrangThai("Đang sử dụng");
                if (ve.getStrMaLoaiVe().equals("LVE03")) {
                    ve.setDateNgayHetHan(getDateThoiGianVeTuan());
                }
                if (ve.getStrMaLoaiVe().equals("LVE04")) {
                    ve.setDateNgayHetHan(getDateThoiGianVeThang());
                }
                try {
                    vetbl.sua(ve);
                } catch (Exception ex) {
                    Logger.getLogger(ThongTinVe.class.getName()).log(Level.SEVERE, null, ex);
                }
            } catch (ParseException ex) {
                Logger.getLogger(ThongTinVe.class.getName()).log(Level.SEVERE, null, ex);
            }

            //Sau khi hoàn thành kích hoạt, thì update Table
            capNhatLaiTable();

        }
    }//GEN-LAST:event_btnKichHoatMouseClicked

    private void tblVeMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tblVeMousePressed
        // TODO add your handling code here:
        int selectedRow = tblVe.getSelectedRow();
        if (selectedRow >= 0) {

            VeDTO ve = list_Ve.get(selectedRow);
            LoaiVeDTO lv = loaivetbl.getInfor(ve.getStrMaLoaiVe());

            System.out.println(lv.getStrTenLoaiVe());
            System.out.println(ve.getDateNgayHetHan());
            System.out.println(ve.getDateNgayHetHan());
            System.out.println(ve.getStrTrangThai());

            /**
            * Lấy dữ liệu của mã vé getValueAt trong table sẽ có dạng tham số
            * đầu là index dòng trong table muốn lấy tham số thứ 2 là index cột
            * muốn lấy Cách lấy này như tọa độ, nó sẽ tham chiếu đến trục hoành
            * và trục tung của bảng.
            */
            System.out.println("Dữ liệu table:" + tblVe.getValueAt(selectedRow, 1));
            System.out.println("Dữ liệu table:" + tblVe.getValueAt(selectedRow, 2));

            /**
            *
            * Nếu tên loại vé là vé lượt xe máy hoặc vé lượt xe đạp thì sẽ
            * disable button kích hoạt
            */
            if (tblVe.getValueAt(selectedRow, 2).equals("Ve luot xe may")
                || tblVe.getValueAt(selectedRow, 2).equals("Ve luot xe dap")) {
                btnKichHoat.setEnabled(false);
                loaiVeHienTai = (String) tblVe.getValueAt(selectedRow, 2);
                maVeHienTai = (String) tblVe.getValueAt(selectedRow, 1);
            }
            if (tblVe.getValueAt(selectedRow, 2).equals("Ve tuan")
                || tblVe.getValueAt(selectedRow, 2).equals("Ve thang")) {
                btnKichHoat.setEnabled(true);
                loaiVeHienTai = (String) tblVe.getValueAt(selectedRow, 2);
                maVeHienTai = (String) tblVe.getValueAt(selectedRow, 1);
                trangThaiVeHienTai = (String) tblVe.getValueAt(selectedRow, 5);
                /**
                * Nếu trạng thái đang sử dụng hoặc đã hết hạn thì disable
                * button kích hoạt
                */
                if (!tblVe.getValueAt(selectedRow, 5).equals("Chưa kích hoạt")) {
                    btnKichHoat.setEnabled(false);
                }
            }

        }
    }//GEN-LAST:event_tblVeMousePressed

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
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTable tblVe;
    private javax.swing.JTextField txtTimKiem;
    // End of variables declaration//GEN-END:variables

    private void setIconImage() {
       setIconImage(Toolkit.getDefaultToolkit().getImage(getClass().getResource("/icon/parking.png")));

    }

    
}
