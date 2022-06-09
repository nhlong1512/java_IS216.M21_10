/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JPanel.java to edit this template
 */
package UITParking.GUI.Admin;

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
import static UITParking.GUI.InitPublic.formatDate;
import static UITParking.GUI.InitPublic.getDateThoiGianThuc;
import static UITParking.GUI.InitPublic.sysdate;
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
        txtMaKhachHang.setEnabled(false);
        txtMaTheKVL.setEnabled(false);
    }

    public void resetRender() {

    }

    public void updateRender() {

    }

    public void initTable() throws Exception {
        model = new DefaultTableModel();
        model.setColumnIdentifiers(columnHeaders);
        int index = 1;
        for (CTRaVaoDTO ctrv : list_CTRV) {
            //Lấy ra xe và biển số xe của khách hàng

//            LoaiVeDTO lv = loaivetbl.getInfor(ve.getStrMaLoaiVe());
            //Cập nhật bảng
            model.addRow(new Object[]{index, ctrv.getStrMaCTRaVao(), 
                (ctrv.getDateThoiGianVao() != null ? formatDate(ctrv.getDateThoiGianVao()) : ctrv.getDateThoiGianVao()),
                (ctrv.getDateThoiGianRa() != null ? formatDate(ctrv.getDateThoiGianRa()) : ctrv.getDateThoiGianRa()), ctrv.getStrMaKH() == null ? "null" : ctrv.getStrMaKH(), ctrv.getStrMaXe(),
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
            model.addRow(new Object[]{index, ctrv.getStrMaCTRaVao(), 
                (ctrv.getDateThoiGianVao() != null ? formatDate(ctrv.getDateThoiGianVao()) : ctrv.getDateThoiGianVao()),
                (ctrv.getDateThoiGianRa() != null ? formatDate(ctrv.getDateThoiGianRa()) : ctrv.getDateThoiGianRa()), ctrv.getStrMaKH() == null ? "null" : ctrv.getStrMaKH(), ctrv.getStrMaXe(),
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
        setPreferredSize(new java.awt.Dimension(1200, 467));

        jPanel1.setBackground(new java.awt.Color(255, 255, 255));

        btnNhapMoi.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icon/icons8_available_updates_24px.png"))); // NOI18N
        btnNhapMoi.setText("Nhập mới");
        btnNhapMoi.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                btnNhapMoiMouseClicked(evt);
            }
        });

        btnXeTVVao.setText("Xe khách thành viên _ Vào");
        btnXeTVVao.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                btnXeTVVaoMouseClicked(evt);
            }
        });

        btnXeTVRa.setText("Xe khàch thành viên _ Ra");
        btnXeTVRa.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                btnXeTVRaMouseClicked(evt);
            }
        });

        btnTimKiem.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icon/icons8_search_24px.png"))); // NOI18N
        btnTimKiem.setText("Tìm kiếm");
        btnTimKiem.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                btnTimKiemMouseClicked(evt);
            }
        });

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

        jLabel13.setText("Mã khách hàng");

        jLabel10.setText("Loại xe");

        jLabel12.setText("Biển số xe");

        cbbLoaiXe.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Xe đạp", "Xe máy" }));
        cbbLoaiXe.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                cbbLoaiXeItemStateChanged(evt);
            }
        });

        btnXeVLRa.setText("Xe khách vãng lai _ Ra");
        btnXeVLRa.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                btnXeVLRaMouseClicked(evt);
            }
        });

        btnXeVLVao.setText("Xe khách vãng lai _ Vào");
        btnXeVLVao.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                btnXeVLVaoMouseClicked(evt);
            }
        });
        btnXeVLVao.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnXeVLVaoActionPerformed(evt);
            }
        });

        btnDemoKHTV.setText("Khách hàng thành viên");
        btnDemoKHTV.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                btnDemoKHTVMouseClicked(evt);
            }
        });

        btnDemoKVL.setText("Khách vãng lai");
        btnDemoKVL.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                btnDemoKVLMouseClicked(evt);
            }
        });

        jLabel14.setText("Mã khách vãng lai");

        txtMaTheKVL.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtMaTheKVLActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(50, 50, 50)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(btnDemoKVL, javax.swing.GroupLayout.PREFERRED_SIZE, 210, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(70, 70, 70)
                        .addComponent(jLabel14, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(30, 30, 30)
                        .addComponent(txtMaTheKVL, javax.swing.GroupLayout.PREFERRED_SIZE, 130, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(70, 70, 70)
                        .addComponent(jLabel10, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(30, 30, 30)
                        .addComponent(cbbLoaiXe, javax.swing.GroupLayout.PREFERRED_SIZE, 140, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(btnDemoKHTV, javax.swing.GroupLayout.PREFERRED_SIZE, 210, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(70, 70, 70)
                        .addComponent(jLabel13, javax.swing.GroupLayout.PREFERRED_SIZE, 90, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(40, 40, 40)
                        .addComponent(txtMaKhachHang, javax.swing.GroupLayout.PREFERRED_SIZE, 130, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(70, 70, 70)
                        .addComponent(jLabel12, javax.swing.GroupLayout.PREFERRED_SIZE, 60, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(10, 10, 10)
                        .addComponent(txtBienSoXe, javax.swing.GroupLayout.PREFERRED_SIZE, 140, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(btnXeVLVao, javax.swing.GroupLayout.PREFERRED_SIZE, 210, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(btnXeTVVao, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 210, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addGap(20, 20, 20)
                                .addComponent(btnXeVLRa, javax.swing.GroupLayout.PREFERRED_SIZE, 210, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addGap(18, 18, 18)
                                .addComponent(btnXeTVRa, javax.swing.GroupLayout.PREFERRED_SIZE, 210, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addGap(80, 80, 80)
                        .addComponent(btnNhapMoi, javax.swing.GroupLayout.PREFERRED_SIZE, 120, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(20, 20, 20)
                        .addComponent(btnTimKiem, javax.swing.GroupLayout.PREFERRED_SIZE, 110, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 0, 0)
                        .addComponent(txtTimKiem, javax.swing.GroupLayout.PREFERRED_SIZE, 170, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane1)
                .addContainerGap())
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(10, 10, 10)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(btnDemoKVL, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(10, 10, 10)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel14, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(txtMaTheKVL, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel10, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(cbbLoaiXe, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addGap(10, 10, 10)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(btnDemoKHTV, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(10, 10, 10)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel13, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(txtMaKhachHang, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel12, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(txtBienSoXe, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addGap(40, 40, 40)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(btnXeVLVao)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(btnXeVLRa)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(btnXeTVRa)
                            .addComponent(btnXeTVVao)))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(20, 20, 20)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(btnNhapMoi, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(btnTimKiem, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(txtTimKiem, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addGap(25, 25, 25)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 247, javax.swing.GroupLayout.PREFERRED_SIZE))
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

    private void tblChiTietRaVaoMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tblChiTietRaVaoMousePressed

    }//GEN-LAST:event_tblChiTietRaVaoMousePressed

    private void btnTimKiemMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnTimKiemMouseClicked
        // TODO add your handling code here:
    }//GEN-LAST:event_btnTimKiemMouseClicked

    /**
     *
     * @param evt Khi khách thành viên ra khỏi bãi, điều duy nhất cần làm là Cập
     * nhật giờ ra cho bảng chi tiết ra vào.
     */
    private void btnXeTVRaMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnXeTVRaMouseClicked
        ArrayList<VeDTO> list_VeTV = vetbl.getList_VeTV(txtMaKhachHang.getText());
        for (VeDTO ve : list_VeTV) {
            if ((ve.getStrMaLoaiVe().equals("LVE01") || ve.getStrMaLoaiVe().equals("LVE02"))
                    && ve.getStrTrangThai().equals("Đang sử dụng")) {
                ve.setStrTrangThai("Đã hết hạn");
                try {
                    vetbl.sua(ve);
                } catch (Exception ex) {
                    Logger.getLogger(QLXRVJPanel.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        }
        for (CTRaVaoDTO ctrv : list_CTRV) {
            //Nếu mã thẻ KVL trùng với barrier và thời gian ra đang là null thì cập nhật
            if (ctrv.getStrMaKH() != null) {
                if (ctrv.getStrMaKH().equals(txtMaKhachHang.getText())
                        && ctrv.getDateThoiGianRa() == null) {
                    ctrv.setDateThoiGianRa(sysdate());
                    try {
                        ctrvtbl.sua(ctrv);
                    } catch (Exception ex) {
                        Logger.getLogger(QLXRVJPanel.class.getName()).log(Level.SEVERE, null, ex);
                    }
                    capNhatLaiTable();
                    JOptionPane.showMessageDialog(this, "Xe ra bãi thành công");
                    return;
                }
            }

        }
        JOptionPane.showMessageDialog(this, "Xe ra bãi thất bại");
        capNhatLaiTable();
    }//GEN-LAST:event_btnXeTVRaMouseClicked

    /**
     *
     * @param evt Event xử lý khi xe thành viên vào bãi Xe thành viên vào bãi
     * thì ở đây ta mô phỏng bằng cách nhập mã Khách hàng vào Ở đây khi kiểm tra
     * mã khách hàng tồn tại, Ta cần xử lý kiểm tra xem khách hàng có vé chưa
     * Với khách hàng có vé tuần, tháng đang sử dụng thì ưu tiên sử dụng nó. Nếu
     * chưa có thì ưu tiên sử dụng vé lượt Hệ thống sẽ tự kích hoạt vé lượt,
     * chuyển trạng thái sang đang sử dụng. Đối với vé lượt thì trường thời gian
     * kích hoạt và thời gian hết hạn không cần care. Trong trường hợp khách
     * hàng thành viên không còn loại vé nào thì sẽ hiển thị thông báo và khách
     * hàng thành viên phải trả tiền như khách hàng vãng lai. Flow vào, barrier
     * vẫn kiểm tra loại xe và biển số xe Ở trên app demo thì sẽ nhập tay biển
     * số xe và chọn lựa loại xe
     *
     */
    private void btnXeTVVaoMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnXeTVVaoMouseClicked

        NguoiDungDTO nd = nguoidungtbl.getInfor(txtMaKhachHang.getText());
        KhachHangDTO kh = khachhangtbl.getInfor(txtMaKhachHang.getText());
        XeDTO xe = xetbl.getInfor(kh.getStrMaXe());
        ArrayList<VeDTO> list_VeTV = vetbl.getList_VeTV(txtMaKhachHang.getText());
        //Nếu khách hàng không có vé nào sẽ thông báo cho khách hàng
        if (list_VeTV.size() == 0) {
            JOptionPane.showMessageDialog(this, "Xe vào bãi không thành công, vui lòng kiểm tra lại thông tin vé của mình!");
            capNhatLaiTable();
            return;
        }

        //Kiểm tra nếu có vé tuần hoặc tháng đang sử dụng thì cho phép vào bãi
        for (VeDTO ve : list_VeTV) {
            if ((ve.getStrMaLoaiVe().equals("LVE03") || ve.getStrMaLoaiVe().equals("LVE04"))
                    && ve.getStrTrangThai().equals("Đang sử dụng")) {
                capNhatLaiTable();
                JOptionPane.showMessageDialog(this, "Xe vào bãi thành công");
                /**
                 * Thêm thông tin xe vào bảng xe Và thông tin chi tiết ra vào
                 * mới
                 */
                xe = new XeDTO();
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
                CTRaVaoDTO ctrv = new CTRaVaoDTO();
                try {
                    String maCTRaVaoTemp = ctrvtbl.getMaxMaCTRaVao();
                    System.out.println(maCTRaVaoTemp);
                    ctrv.setStrMaCTRaVao(maCTRaVaoTemp);
                    ctrv.setDateThoiGianVao(sysdate());
                    ctrv.setDateThoiGianRa(null);
                    ctrv.setStrMaNV("ND011");
                    ctrv.setStrMaKH(txtMaKhachHang.getText());
                    ctrv.setStrMaXe(xe.getStrMaXe());
                    ctrv.setStrMaTheKVL(null);
                } catch (Exception ex) {
                    Logger.getLogger(QLXRVJPanel.class.getName()).log(Level.SEVERE, null, ex);
                }
                try {
                    ctrvtbl.them(ctrv);
                } catch (Exception ex) {
                    Logger.getLogger(QLXRVJPanel.class.getName()).log(Level.SEVERE, null, ex);
                }
                capNhatLaiTable();
                return;
            }
        }

        /**
         * Nếu kiếm tra không có vé tuần tháng đang sử dụng, sẽ chuyển sang tự
         * sử dụng vé lượt. Nếu kiểm tra cbbLoaiXe là xe máy thì sử dụng vé lượt
         * xe máy Nếu kiểm tra cbbLoaiXe là xe đạp thì sử dụng vé lượt xe đạp.
         */
        if (cbbLoaiXe.getSelectedItem().toString().equals("Xe đạp")) {
            for (VeDTO ve : list_VeTV) {
                /**
                 * Nếu cbb là xe đạp thì sẽ kiểm tra xem có LVE002 và trạng thái
                 * chưa kích hoạt tồn tại không Nếu có thì kích hoạt chuyển
                 * trạng thái sang đang sử dụng và return.
                 */
                if (ve.getStrMaLoaiVe().equals("LVE02")
                        && ve.getStrTrangThai().equals("Chưa kích hoạt")) {
                    System.out.println(ve);
                    ve.setStrTrangThai("Đang sử dụng");
                    try {
                        vetbl.sua(ve);
                    } catch (Exception ex) {
                        Logger.getLogger(QLXRVJPanel.class.getName()).log(Level.SEVERE, null, ex);
                    }
                    JOptionPane.showMessageDialog(this, "Xe vào bãi thành công");
                    /**
                     * Thêm thông tin xe vào bảng xe Và thông tin chi tiết ra
                     * vào mới
                     */
                    xe = new XeDTO();
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
                    CTRaVaoDTO ctrv = new CTRaVaoDTO();
                    try {
                        String maCTRaVaoTemp = ctrvtbl.getMaxMaCTRaVao();
                        System.out.println(maCTRaVaoTemp);
                        ctrv.setStrMaCTRaVao(maCTRaVaoTemp);
                        ctrv.setDateThoiGianVao(sysdate());
                        ctrv.setDateThoiGianRa(null);
                        ctrv.setStrMaNV("ND011");
                        ctrv.setStrMaKH(txtMaKhachHang.getText());
                        ctrv.setStrMaXe(xe.getStrMaXe());
                        ctrv.setStrMaTheKVL(null);
                    } catch (Exception ex) {
                        Logger.getLogger(QLXRVJPanel.class.getName()).log(Level.SEVERE, null, ex);
                    }
                    try {
                        ctrvtbl.them(ctrv);
                    } catch (Exception ex) {
                        Logger.getLogger(QLXRVJPanel.class.getName()).log(Level.SEVERE, null, ex);
                    }
                    capNhatLaiTable();
                    return;
                }
            }
        } else {
            for (VeDTO ve : list_VeTV) {
                /**
                 * Nếu cbb là xe máy thì sẽ kiểm tra xem có LVE001 và trạng thái
                 * chưa kích hoạt tồn tại không Nếu có thì kích hoạt chuyển
                 * trạng thái sang đang sử dụng và return.
                 */
                if (ve.getStrMaLoaiVe().equals("LVE01")
                        && ve.getStrTrangThai().equals("Chưa kích hoạt")) {
                    System.out.println(ve);
                    ve.setStrTrangThai("Đang sử dụng");
                    try {
                        vetbl.sua(ve);
                    } catch (Exception ex) {
                        Logger.getLogger(QLXRVJPanel.class.getName()).log(Level.SEVERE, null, ex);
                    }
                    JOptionPane.showMessageDialog(this, "Xe vào bãi thành công");
                    /**
                     * Thêm thông tin xe vào bảng xe Và thông tin chi tiết ra
                     * vào mới
                     */
                    xe = new XeDTO();
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
                    CTRaVaoDTO ctrv = new CTRaVaoDTO();
                    try {
                        String maCTRaVaoTemp = ctrvtbl.getMaxMaCTRaVao();
                        System.out.println(maCTRaVaoTemp);
                        ctrv.setStrMaCTRaVao(maCTRaVaoTemp);
                        ctrv.setDateThoiGianVao(sysdate());
                        ctrv.setDateThoiGianRa(null);
                        ctrv.setStrMaNV("ND011");
                        ctrv.setStrMaKH(txtMaKhachHang.getText());
                        ctrv.setStrMaXe(xe.getStrMaXe());
                        ctrv.setStrMaTheKVL(null);
                    } catch (Exception ex) {
                        Logger.getLogger(QLXRVJPanel.class.getName()).log(Level.SEVERE, null, ex);
                    }
                    try {
                        ctrvtbl.them(ctrv);
                    } catch (Exception ex) {
                        Logger.getLogger(QLXRVJPanel.class.getName()).log(Level.SEVERE, null, ex);
                    }
                    capNhatLaiTable();
                    return;
                }
            }
        }

        /**
         * Nếu tất cả trường hợp trên đều không thỏa mãn Đồng nghĩa việc không
         * có loại vé phù hợp Thì sẽ thông báo ra màn hình.
         */
        capNhatLaiTable();
        JOptionPane.showMessageDialog(this, "Xe vào bãi không thành công, vui lòng kiểm tra lại thông tin vé của mình!");

    }//GEN-LAST:event_btnXeTVVaoMouseClicked

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
        txtMaKhachHang.setEnabled(false);
        txtMaTheKVL.setEnabled(true);

        System.out.println(sysdate());

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
        txtMaKhachHang.setEnabled(true);
        txtMaTheKVL.setEnabled(false);
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
            ctrv.setDateThoiGianVao(sysdate());
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
        capNhatLaiTable();
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
                    ctrv.setDateThoiGianRa(sysdate());
                    try {
                        ctrvtbl.sua(ctrv);
                    } catch (Exception ex) {
                        Logger.getLogger(QLXRVJPanel.class.getName()).log(Level.SEVERE, null, ex);
                    }
                    JOptionPane.showMessageDialog(this, "Xe ra bãi thành công");
                }
            }

        }
        capNhatLaiTable();
    }//GEN-LAST:event_btnXeVLRaMouseClicked

    private void txtMaTheKVLActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtMaTheKVLActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtMaTheKVLActionPerformed

    private void btnXeVLVaoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnXeVLVaoActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_btnXeVLVaoActionPerformed

    private void btnNhapMoiMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnNhapMoiMouseClicked
        //        resetRender();
    }//GEN-LAST:event_btnNhapMoiMouseClicked


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
