/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JPanel.java to edit this template
 */
package UITParking.TEST2;

import UITParking.BUS.CTRaVaoBUS;
import UITParking.BUS.KhachHangBUS;
import UITParking.BUS.KhachVangLaiBUS;
import UITParking.BUS.LoaiVeBUS;
import UITParking.BUS.NguoiDungBUS;
import UITParking.BUS.VeBUS;
import UITParking.BUS.XeBUS;
import UITParking.DTO.CTRaVaoDTO;
import UITParking.DTO.KhachHangDTO;
import UITParking.DTO.KhachVangLaiDTO;
import UITParking.DTO.LoaiVeDTO;
import UITParking.DTO.NguoiDungDTO;
import UITParking.DTO.VeDTO;
import UITParking.DTO.XeDTO;
import static UITParking.GUI.InitPublic.getDateThoiGianThuc;
import java.awt.Color;
import java.text.ParseException;
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
public class QLXRVJPanel extends javax.swing.JPanel {

    NguoiDungBUS nguoidungtbl = new NguoiDungBUS();
    ArrayList<NguoiDungDTO> list_ND = nguoidungtbl.getList_ND();
    KhachHangBUS khachhangtbl = new KhachHangBUS();
    ArrayList<KhachHangDTO> list_KH = khachhangtbl.getList_KH();
    LoaiVeBUS loaivetbl = new LoaiVeBUS();
    ArrayList<LoaiVeDTO> list_LV = loaivetbl.getList_LV();
    VeBUS vetbl = new VeBUS();
    ArrayList<VeDTO> list_Ve = vetbl.getList_Ve();
    CTRaVaoBUS ctrvtbl = new CTRaVaoBUS();
    ArrayList<CTRaVaoDTO> list_CTRV = ctrvtbl.getList_CTRV();
    XeBUS xetbl = new XeBUS();
    ArrayList<XeDTO> listXe = xetbl.getlist_XE();
    KhachVangLaiBUS kvltbl = new KhachVangLaiBUS();
    ArrayList<KhachVangLaiDTO> listKVL = kvltbl.getlist_KVL();

    private DefaultTableModel model;
    private String[] columnHeaders = new String[]{"STT", "Mã CT Ra Vào", "Thời Gian Vào",
        "Thời Gian Ra", "Mã Khách Hàng", "Mã Xe", "Mã Thẻ KVL"};

    private TableRowSorter<TableModel> rowSorter = null;

    /**
     * Creates new form QLDTJPanel
     */
    public QLXRVJPanel() throws Exception {
        initComponents();
        initTable();
        hoTroTimKiem();
        txtBienSoXe.setEnabled(false);
    }

    public void resetRender() {

    }

    public void updateRender() {
        //Reset txtMaLoaiVe theo tên loại vé
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
        for (CTRaVaoDTO ctrv : list_CTRV) {
            //Lấy ra xe và biển số xe của khách hàng

//            LoaiVeDTO lv = loaivetbl.getInfor(ve.getStrMaLoaiVe());
            //Cập nhật bảng
            model.addRow(new Object[]{index, ctrv.getStrMaCTRaVao(), ctrv.getDateThoiGianVao(),
                ctrv.getDateThoiGianRa(), ctrv.getStrMaKH() == null ? "null" : ctrv.getStrMaKH(), ctrv.getStrMaXe(),
                ctrv.getStrMaTheKVL() == null ? "null" : ctrv.getStrMaTheKVL()});
            index++;
        }

        tblChiTietRaVao.setModel(model);

    }

    public void hoTroTimKiem() {

        rowSorter = new TableRowSorter<>(tblChiTietRaVao.getModel());
        tblChiTietRaVao.setRowSorter(rowSorter);
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
        model.setRowCount(0);
        int index = 1;
        for (CTRaVaoDTO ctrv : list_CTRV) {
            //Lấy ra xe và biển số xe của khách hàng

//            LoaiVeDTO lv = loaivetbl.getInfor(ve.getStrMaLoaiVe());
            //Cập nhật bảng
            model.addRow(new Object[]{index, ctrv.getStrMaCTRaVao(), ctrv.getDateThoiGianVao(),
                ctrv.getDateThoiGianRa(), ctrv.getStrMaKH() == null ? "null" : ctrv.getStrMaKH(), ctrv.getStrMaXe(),
                ctrv.getStrMaTheKVL() == null ? "null" : ctrv.getStrMaTheKVL()});
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
        btnNhapMoi = new javax.swing.JButton();
        btnXeTVVao = new javax.swing.JButton();
        btnXeTVRa = new javax.swing.JButton();
        btnTimKiem = new javax.swing.JButton();
        txtTimKiem = new javax.swing.JTextField();
        jScrollPane1 = new javax.swing.JScrollPane();
        tblChiTietRaVao = new javax.swing.JTable();
        jLabel13 = new javax.swing.JLabel();
        txtMaKhachHang = new javax.swing.JTextField();
        jLabel10 = new javax.swing.JLabel();
        jLabel12 = new javax.swing.JLabel();
        txtBienSoXe = new javax.swing.JTextField();
        cbbLoaiXe = new javax.swing.JComboBox<>();
        btnXeVLRa = new javax.swing.JButton();
        btnXeVLVao = new javax.swing.JButton();
        btnDemoKHTV = new javax.swing.JButton();
        btnDemoKVL = new javax.swing.JButton();
        jLabel14 = new javax.swing.JLabel();
        txtMaTheKVL = new javax.swing.JTextField();

        setBackground(new java.awt.Color(255, 255, 255));

        jPanel1.setBackground(new java.awt.Color(255, 255, 255));
        jPanel1.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        btnNhapMoi.setText("Nhập mới");
        btnNhapMoi.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                btnNhapMoiMouseClicked(evt);
            }
        });
        jPanel1.add(btnNhapMoi, new org.netbeans.lib.awtextra.AbsoluteConstraints(510, 170, -1, -1));

        btnXeTVVao.setText("Xe TV Vào");
        btnXeTVVao.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                btnXeTVVaoMouseClicked(evt);
            }
        });
        jPanel1.add(btnXeTVVao, new org.netbeans.lib.awtextra.AbsoluteConstraints(50, 170, 190, -1));

        btnXeTVRa.setText("Xe TV Ra");
        btnXeTVRa.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                btnXeTVRaMouseClicked(evt);
            }
        });
        jPanel1.add(btnXeTVRa, new org.netbeans.lib.awtextra.AbsoluteConstraints(260, 170, 190, -1));

        btnTimKiem.setText("Tìm kiếm");
        btnTimKiem.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                btnTimKiemMouseClicked(evt);
            }
        });
        jPanel1.add(btnTimKiem, new org.netbeans.lib.awtextra.AbsoluteConstraints(600, 170, -1, -1));
        jPanel1.add(txtTimKiem, new org.netbeans.lib.awtextra.AbsoluteConstraints(700, 170, 144, -1));

        tblChiTietRaVao.setModel(new javax.swing.table.DefaultTableModel(
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
        tblChiTietRaVao.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                tblChiTietRaVaoMousePressed(evt);
            }
        });
        jScrollPane1.setViewportView(tblChiTietRaVao);

        jPanel1.add(jScrollPane1, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 220, 926, 247));

        jLabel13.setText("Mã Khách Hàng");
        jPanel1.add(jLabel13, new org.netbeans.lib.awtextra.AbsoluteConstraints(280, 70, 90, 20));
        jPanel1.add(txtMaKhachHang, new org.netbeans.lib.awtextra.AbsoluteConstraints(360, 70, 140, -1));

        jLabel10.setText("Loại Xe");
        jPanel1.add(jLabel10, new org.netbeans.lib.awtextra.AbsoluteConstraints(280, 20, 70, 20));

        jLabel12.setText("Biển Số Xe");
        jPanel1.add(jLabel12, new org.netbeans.lib.awtextra.AbsoluteConstraints(530, 20, 60, 20));
        jPanel1.add(txtBienSoXe, new org.netbeans.lib.awtextra.AbsoluteConstraints(590, 20, 130, -1));

        cbbLoaiXe.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Xe đạp", "Xe máy" }));
        cbbLoaiXe.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                cbbLoaiXeItemStateChanged(evt);
            }
        });
        jPanel1.add(cbbLoaiXe, new org.netbeans.lib.awtextra.AbsoluteConstraints(360, 20, 140, -1));

        btnXeVLRa.setText("Xe VL Ra");
        btnXeVLRa.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                btnXeVLRaMouseClicked(evt);
            }
        });
        jPanel1.add(btnXeVLRa, new org.netbeans.lib.awtextra.AbsoluteConstraints(260, 140, 190, -1));

        btnXeVLVao.setText("Xe VL Vào");
        btnXeVLVao.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                btnXeVLVaoMouseClicked(evt);
            }
        });
        jPanel1.add(btnXeVLVao, new org.netbeans.lib.awtextra.AbsoluteConstraints(50, 140, 190, -1));

        btnDemoKHTV.setText("Demo KHTV");
        btnDemoKHTV.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                btnDemoKHTVMouseClicked(evt);
            }
        });
        jPanel1.add(btnDemoKHTV, new org.netbeans.lib.awtextra.AbsoluteConstraints(50, 60, 210, 40));

        btnDemoKVL.setText("Demo KVL");
        btnDemoKVL.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                btnDemoKVLMouseClicked(evt);
            }
        });
        jPanel1.add(btnDemoKVL, new org.netbeans.lib.awtextra.AbsoluteConstraints(50, 10, 210, 40));

        jLabel14.setText("Mã KVL");
        jPanel1.add(jLabel14, new org.netbeans.lib.awtextra.AbsoluteConstraints(740, 20, 60, 20));

        txtMaTheKVL.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtMaTheKVLActionPerformed(evt);
            }
        });
        jPanel1.add(txtMaTheKVL, new org.netbeans.lib.awtextra.AbsoluteConstraints(800, 20, 130, -1));

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

    private void tblChiTietRaVaoMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tblChiTietRaVaoMousePressed
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
    }//GEN-LAST:event_tblChiTietRaVaoMousePressed

    private void btnTimKiemMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnTimKiemMouseClicked
        // TODO add your handling code here:
    }//GEN-LAST:event_btnTimKiemMouseClicked

    private void btnXeTVRaMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnXeTVRaMouseClicked
        // TODO add your handling code here:
        //        StringBuilder sb = new StringBuilder();
        //        if (txtMaVe.getText().equals("")) {
        //            sb.append("Mã vé không được để trống.");
        //            txtMaVe.setBackground(Color.red);
        //        } else {
        //            txtMaVe.setBackground(Color.white);
        //        }
        //        if (vetbl.getInfor(txtMaVe.getText()) == null) {
        //            sb.append("Mã vé không tồn tại.");
        //        }
        //        if (khachhangtbl.getInfor(txtMaKH.getText()) == null) {
        //            sb.append("Mã khách hàng không tồn tại.");
        //        }
        //        if (loaivetbl.getInfor(txtMaLoaiVe.getText()) == null) {
        //            sb.append("Mã loại vé không tồn tại.");
        //        }
        //        if (sb.length() > 0) {
        //            JOptionPane.showMessageDialog(this, sb);
        //            return;
        //        }
        //        try {
        //            VeDTO ve = new VeDTO();
        //            LoaiVeDTO lv = new LoaiVeDTO();
        //            ve.setStrMaVe(txtMaVe.getText());
        //            ve.setStrMaKH(txtMaKH.getText());
        //
        //            ve.setStrTrangThai(txtTrangThai.getText());
        //            lv.setStrTenLoaiVe(cbbTenLoaiVe.getSelectedItem().toString());
        //
        //            updateRender();
        //
        //            System.out.println(jdcNgayKichHoat.getDate());
        //            System.out.println(jdcNgayHetHan.getDate());
        //            ve.setStrMaLoaiVe(txtMaLoaiVe.getText());
        //            lv.setStrMaLoaiVe(txtMaLoaiVe.getText());
        //
        //            if (jdcNgayKichHoat.getDate() != null) {
        //                ve.setDateNgayKichHoat(new java.sql.Date(jdcNgayKichHoat.getDate().getTime()));
        //            } else {
        //                ve.setDateNgayKichHoat(null);
        //            }
        //
        //            if (jdcNgayHetHan.getDate() != null) {
        //                ve.setDateNgayHetHan(new java.sql.Date(jdcNgayHetHan.getDate().getTime()));
        //            } else {
        //                ve.setDateNgayHetHan(null);
        //            }
        //            vetbl.sua(ve);
        //
        //            //Cập nhật lại Table
        //            capNhatLaiTable();
        //
        //            JOptionPane.showMessageDialog(this, "Vé đã được cập nhật vào CSDL");
        //
        //        } catch (Exception e) {
        //            JOptionPane.showMessageDialog(this, "Error" + e.getMessage());
        //            e.printStackTrace();
        //        }
    }//GEN-LAST:event_btnXeTVRaMouseClicked

    /**
     * 
     * @param evt 
     * Event xử lý khi xe thành viên vào bãi
     * Xe thành viên vào bãi thì ở đây ta mô phỏng
     * bằng cách nhập mã Khách hàng vào
     * Ở đây khi kiểm tra mã khách hàng tồn tại,
     * Ta cần xử lý kiểm tra xem khách hàng có vé chưa
     * Với khách hàng có vé tuần, tháng đang sử dụng thì ưu tiên
     * sử dụng nó. Nếu chưa có thì ưu tiên sử dụng vé lượt
     * Hệ thống sẽ tự kích hoạt vé lượt, chuyển trạng thái sang 
     * đang sử dụng. Đối với vé lượt thì trường thời gian kích hoạt
     * và thời gian hết hạn không cần care.
     * Trong trường hợp khách hàng thành viên không còn loại vé nào
     * thì sẽ hiển thị thông báo và khách hàng thành viên phải trả 
     * tiền như khách hàng vãng lai
     */
    private void btnXeTVVaoMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnXeTVVaoMouseClicked
        
    }//GEN-LAST:event_btnXeTVVaoMouseClicked

    private void btnNhapMoiMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnNhapMoiMouseClicked
        //        resetRender();
    }//GEN-LAST:event_btnNhapMoiMouseClicked

    /**
     *
     * @param evt Event click button DemoKVL Khi click vào thì 2 button Xe TV
     * vào và Xe TV ra bị disable 2 button Xe VL vào và Xe VL ra được enable 2
     * trường loại xe biển số xe được enable trường mã khách hàng disable
     */
    private void btnDemoKVLMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnDemoKVLMouseClicked
        // TODO add your handling code here:
        btnXeTVVao.setEnabled(false);
        btnXeTVRa.setEnabled(false);
        btnXeVLVao.setEnabled(true);
        btnXeVLRa.setEnabled(true);
        cbbLoaiXe.setEnabled(true);
        txtMaKhachHang.setEnabled(false);

    }//GEN-LAST:event_btnDemoKVLMouseClicked

    /**
     *
     * @param evt Event click button DemoKHTV Khi click vào thì 2 button Xe VL
     * vào và Xe VL ra bị disable
     */
    private void btnDemoKHTVMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnDemoKHTVMouseClicked
        // TODO add your handling code here:
        btnXeVLVao.setEnabled(false);
        btnXeVLRa.setEnabled(false);
        btnXeTVVao.setEnabled(true);
        btnXeTVRa.setEnabled(true);
        cbbLoaiXe.setEnabled(false);
        txtMaKhachHang.setEnabled(true);
    }//GEN-LAST:event_btnDemoKHTVMouseClicked

    /**
     *
     * @param evt Xử lý event Khi xe vãng lai vào Khi Xe vãng lai vào, xẽ được
     * phát một thẻ khách vãng lai Khi quẹt thẻ, mã thẻ khách vãng lai được tạo
     * mới, mã xe được tạo mới Đồng thời trong bảng Xe sẽ tạo mới Xe Bảng chi
     * tiết ra vào sẽ được tạo, với giờ vào là giờ hiện tại Đầu tiên cần thêm
     * xe, vào bảng xe, sau đó thêm khách vãng lai Cuối cùng là thêm chi tiết ra
     * vào
     */
    private void btnXeVLVaoMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnXeVLVaoMouseClicked
        // TODO add your handling code here:

        //Thêm xe mới vào database
        XeDTO xe = new XeDTO();
        try {
            String maXeTemp = xetbl.getMaxMaXe();
            xe.setStrMaXe(maXeTemp);
            System.out.println(maXeTemp);
            if (cbbLoaiXe.getSelectedItem().toString().equals("Xe đạp")) {
                xe.setStrTenLoaiXe("Xe dap");
                xe.setStrBienSoXe(null);
            } else {
                xe.setStrTenLoaiXe("Xe may");
                xe.setStrBienSoXe(txtBienSoXe.getText());
            }
        } catch (Exception ex) {
            Logger.getLogger(QLXRVJPanel.class.getName()).log(Level.SEVERE, null, ex);
        }
        try {
            xetbl.them(xe);
        } catch (Exception ex) {
            Logger.getLogger(QLXRVJPanel.class.getName()).log(Level.SEVERE, null, ex);
        }

        //Thêm khách vãng lai mới vào database
        KhachVangLaiDTO kvl = new KhachVangLaiDTO();
        try {
            String maKVLTemp = kvltbl.getMaxMaKVL();
            System.out.println(maKVLTemp);
            kvl.setStrMaTheKVL(maKVLTemp);
            kvl.setStrMaXe(xe.getStrMaXe());
        } catch (Exception ex) {
            Logger.getLogger(QLXRVJPanel.class.getName()).log(Level.SEVERE, null, ex);
        }
        try {
            kvltbl.them(kvl);
        } catch (Exception ex) {
            Logger.getLogger(QLXRVJPanel.class.getName()).log(Level.SEVERE, null, ex);
        }

        //Thêm chi tiết ra vào mới vào database
        CTRaVaoDTO ctrv = new CTRaVaoDTO();
        try {
            String maCTRaVaoTemp = ctrvtbl.getMaxMaCTRaVao();
            System.out.println(maCTRaVaoTemp);
            ctrv.setStrMaCTRaVao(maCTRaVaoTemp);
            ctrv.setDateThoiGianVao(getDateThoiGianThuc());
            ctrv.setDateThoiGianRa(null);
            ctrv.setStrMaNV("ND011");
            ctrv.setStrMaKH(null);
            ctrv.setStrMaXe(xe.getStrMaXe());
            ctrv.setStrMaTheKVL(kvl.getStrMaTheKVL());
        } catch (Exception ex) {
            Logger.getLogger(QLXRVJPanel.class.getName()).log(Level.SEVERE, null, ex);
        }
        try {
            ctrvtbl.them(ctrv);
        } catch (Exception ex) {
            Logger.getLogger(QLXRVJPanel.class.getName()).log(Level.SEVERE, null, ex);
        }
        JOptionPane.showMessageDialog(this, "Xe vào bãi thành công");


    }//GEN-LAST:event_btnXeVLVaoMouseClicked

    /**
     *
     * @param evt Khi Loại xe thay đổi thì event đc gọi Khi loại xe là xe đạp,
     * trường biển số xe bị disable Ngược lại thì trường biển số xe sẽ được
     * enable
     */
    private void cbbLoaiXeItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_cbbLoaiXeItemStateChanged
        // TODO add your handling code here:
        if (cbbLoaiXe.getSelectedItem().toString().equals("Xe đạp")) {
            txtBienSoXe.setEnabled(false);
        } else {
            txtBienSoXe.setEnabled(true);
        }
    }//GEN-LAST:event_cbbLoaiXeItemStateChanged

    /**
     *
     * @param evt Khi xe vãng lai ra, sẽ tìm row trong database có xe đó và mã
     * xe đó sau đó cập nhật thời gian ra Vì demo nên mình sẽ tự nhập mã thẻ KVL
     * Thực tế sẽ có barrier xử lý việc này
     */
    private void btnXeVLRaMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnXeVLRaMouseClicked
        // TODO add your handling code here:
        for (CTRaVaoDTO ctrv : list_CTRV) {
            //Nếu mã thẻ KVL trùng với barrier và thời gian ra đang là null thì cập nhật
            if (ctrv.getStrMaTheKVL() != null) {
                if (ctrv.getStrMaTheKVL().equals(txtMaTheKVL.getText())
                        && ctrv.getDateThoiGianRa() == null) {
                    try {
                        ctrv.setDateThoiGianRa(getDateThoiGianThuc());
                        try {
                            ctrvtbl.sua(ctrv);
                        } catch (Exception ex) {
                            Logger.getLogger(QLXRVJPanel.class.getName()).log(Level.SEVERE, null, ex);
                        }
                    } catch (ParseException ex) {
                        Logger.getLogger(QLXRVJPanel.class.getName()).log(Level.SEVERE, null, ex);
                    }
                    JOptionPane.showMessageDialog(this, "Xe ra bãi thành công");
                }
            }

        }
    }//GEN-LAST:event_btnXeVLRaMouseClicked

    private void txtMaTheKVLActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtMaTheKVLActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtMaTheKVLActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnDemoKHTV;
    private javax.swing.JButton btnDemoKVL;
    private javax.swing.JButton btnNhapMoi;
    private javax.swing.JButton btnTimKiem;
    private javax.swing.JButton btnXeTVRa;
    private javax.swing.JButton btnXeTVVao;
    private javax.swing.JButton btnXeVLRa;
    private javax.swing.JButton btnXeVLVao;
    private javax.swing.JComboBox<String> cbbLoaiXe;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel14;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTable tblChiTietRaVao;
    private javax.swing.JTextField txtBienSoXe;
    private javax.swing.JTextField txtMaKhachHang;
    private javax.swing.JTextField txtMaTheKVL;
    private javax.swing.JTextField txtTimKiem;
    // End of variables declaration//GEN-END:variables
}