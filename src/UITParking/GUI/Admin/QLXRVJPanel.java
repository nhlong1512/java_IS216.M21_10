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
    private String[] columnHeaders = new String[]{"STT", "M?? CT Ra V??o", "Th???i Gian V??o",
        "Th???i Gian Ra", "M?? Kh??ch H??ng", "M?? Xe", "M?? Th??? KVL"};

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
            //L???y ra xe v?? bi???n s??? xe c???a kh??ch h??ng

//            LoaiVeDTO lv = loaivetbl.getInfor(ve.getStrMaLoaiVe());
            //C???p nh???t b???ng
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

    //H??m c???p nh???t l???i b???ng sau khi th??m x??a s???a
    public void capNhatLaiTable() {
        model.setRowCount(0);
        int index = 1;
        for (CTRaVaoDTO ctrv : list_CTRV) {
            //L???y ra xe v?? bi???n s??? xe c???a kh??ch h??ng

//            LoaiVeDTO lv = loaivetbl.getInfor(ve.getStrMaLoaiVe());
            //C???p nh???t b???ng
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
        btnNhapMoi.setText("Nh???p m???i");
        btnNhapMoi.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                btnNhapMoiMouseClicked(evt);
            }
        });

        btnXeTVVao.setText("Xe kh??ch th??nh vi??n _ V??o");
        btnXeTVVao.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                btnXeTVVaoMouseClicked(evt);
            }
        });

        btnXeTVRa.setText("Xe kh??ch th??nh vi??n _ Ra");
        btnXeTVRa.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                btnXeTVRaMouseClicked(evt);
            }
        });

        btnTimKiem.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icon/icons8_search_24px.png"))); // NOI18N
        btnTimKiem.setText("T??m ki???m");
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
        tblChiTietRaVao.setFillsViewportHeight(true);
        tblChiTietRaVao.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                tblChiTietRaVaoMousePressed(evt);
            }
        });
        jScrollPane1.setViewportView(tblChiTietRaVao);

        jLabel13.setText("M?? kh??ch h??ng");

        jLabel10.setText("Lo???i xe");

        jLabel12.setText("Bi???n s??? xe");

        cbbLoaiXe.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Xe ?????p", "Xe m??y" }));
        cbbLoaiXe.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                cbbLoaiXeItemStateChanged(evt);
            }
        });

        btnXeVLRa.setText("Xe kh??ch v??ng lai _ Ra");
        btnXeVLRa.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                btnXeVLRaMouseClicked(evt);
            }
        });

        btnXeVLVao.setText("Xe kh??ch v??ng lai _ V??o");
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

        btnDemoKHTV.setText("Kh??ch h??ng th??nh vi??n");
        btnDemoKHTV.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                btnDemoKHTVMouseClicked(evt);
            }
        });

        btnDemoKVL.setText("Kh??ch v??ng lai");
        btnDemoKVL.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                btnDemoKVLMouseClicked(evt);
            }
        });

        jLabel14.setText("M?? kh??ch v??ng lai");

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
     * @param evt Khi kh??ch th??nh vi??n ra kh???i b??i, ??i???u duy nh???t c???n l??m l?? C???p
     * nh???t gi??? ra cho b???ng chi ti???t ra v??o.
     */
    private void btnXeTVRaMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnXeTVRaMouseClicked
        ArrayList<VeDTO> list_VeTV = vetbl.getList_VeTV(txtMaKhachHang.getText());
        for (VeDTO ve : list_VeTV) {
            if ((ve.getStrMaLoaiVe().equals("LVE01") || ve.getStrMaLoaiVe().equals("LVE02"))
                    && ve.getStrTrangThai().equals("Dang su dung")) {
                ve.setStrTrangThai("Da het han");
                try {
                    vetbl.sua(ve);
                } catch (Exception ex) {
                    Logger.getLogger(QLXRVJPanel.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        }
        for (CTRaVaoDTO ctrv : list_CTRV) {
            //N???u m?? th??? KVL tr??ng v???i barrier v?? th???i gian ra ??ang l?? null th?? c???p nh???t
            if (ctrv.getStrMaKH() != null) {
                if (ctrv.getStrMaKH().equals(txtMaKhachHang.getText())
                        && ctrv.getDateThoiGianRa() == null) {
                    ctrv.setDateThoiGianRa(sysdate());
                    try {
                        ctrvtbl.sua(ctrv);
                    } catch (Exception ex) {
                        JOptionPane.showMessageDialog(this, "Th???i gian ra ph???i l???n h??n th???i gian v??o ??t nh???t 30s");
                        return;
                    }
                    capNhatLaiTable();
                    JOptionPane.showMessageDialog(this, "Xe ra b??i th??nh c??ng");
                    return;
                }
            }

        }
        JOptionPane.showMessageDialog(this, "Xe ra b??i th???t b???i");
        capNhatLaiTable();
    }//GEN-LAST:event_btnXeTVRaMouseClicked

    /**
     *
     * @param evt Event x??? l?? khi xe th??nh vi??n v??o b??i Xe th??nh vi??n v??o b??i
     * th?? ??? ????y ta m?? ph???ng b???ng c??ch nh???p m?? Kh??ch h??ng v??o ??? ????y khi ki???m tra
     * m?? kh??ch h??ng t???n t???i, Ta c???n x??? l?? ki???m tra xem kh??ch h??ng c?? v?? ch??a
     * V???i kh??ch h??ng c?? v?? tu???n, th??ng ??ang s??? d???ng th?? ??u ti??n s??? d???ng n??. N???u
     * ch??a c?? th?? ??u ti??n s??? d???ng v?? l?????t H??? th???ng s??? t??? k??ch ho???t v?? l?????t,
     * chuy???n tr???ng th??i sang ??ang s??? d???ng. ?????i v???i v?? l?????t th?? tr?????ng th???i gian
     * k??ch ho???t v?? th???i gian h???t h???n kh??ng c???n care. Trong tr?????ng h???p kh??ch
     * h??ng th??nh vi??n kh??ng c??n lo???i v?? n??o th?? s??? hi???n th??? th??ng b??o v?? kh??ch
     * h??ng th??nh vi??n ph???i tr??? ti???n nh?? kh??ch h??ng v??ng lai. Flow v??o, barrier
     * v???n ki???m tra lo???i xe v?? bi???n s??? xe ??? tr??n app demo th?? s??? nh???p tay bi???n
     * s??? xe v?? ch???n l???a lo???i xe
     *
     */
    private void btnXeTVVaoMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnXeTVVaoMouseClicked

        NguoiDungDTO nd = nguoidungtbl.getInfor(txtMaKhachHang.getText());
        KhachHangDTO kh = khachhangtbl.getInfor(txtMaKhachHang.getText());
        XeDTO xe = xetbl.getInfor(kh.getStrMaXe());
        ArrayList<VeDTO> list_VeTV = vetbl.getList_VeTV(txtMaKhachHang.getText());
        //N???u kh??ch h??ng kh??ng c?? v?? n??o s??? th??ng b??o cho kh??ch h??ng
        if (list_VeTV.size() == 0) {
            JOptionPane.showMessageDialog(this, "Xe v??o b??i kh??ng th??nh c??ng, vui l??ng ki???m tra l???i th??ng tin v?? c???a m??nh!");
            capNhatLaiTable();
            return;
        }

        //Ki???m tra n???u c?? v?? tu???n ho???c th??ng ??ang s??? d???ng th?? cho ph??p v??o b??i
        for (VeDTO ve : list_VeTV) {
            if ((ve.getStrMaLoaiVe().equals("LVE03") || ve.getStrMaLoaiVe().equals("LVE04"))
                    && ve.getStrTrangThai().equals("Dang su dung")) {
                capNhatLaiTable();
                JOptionPane.showMessageDialog(this, "Xe v??o b??i th??nh c??ng");
                /**
                 * Th??m th??ng tin xe v??o b???ng xe V?? th??ng tin chi ti???t ra v??o
                 * m???i
                 */
                xe = new XeDTO();
                try {
                    String maXeTemp = xetbl.getMaxMaXe();
                    xe.setStrMaXe(maXeTemp);
                    System.out.println(maXeTemp);
                    if (cbbLoaiXe.getSelectedItem().toString().equals("Xe ?????p")) {
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
         * N???u ki???m tra kh??ng c?? v?? tu???n th??ng ??ang s??? d???ng, s??? chuy???n sang t???
         * s??? d???ng v?? l?????t. N???u ki???m tra cbbLoaiXe l?? xe m??y th?? s??? d???ng v?? l?????t
         * xe m??y N???u ki???m tra cbbLoaiXe l?? xe ?????p th?? s??? d???ng v?? l?????t xe ?????p.
         */
        if (cbbLoaiXe.getSelectedItem().toString().equals("Xe ?????p")) {
            for (VeDTO ve : list_VeTV) {
                /**
                 * N???u cbb l?? xe ?????p th?? s??? ki???m tra xem c?? LVE002 v?? tr???ng th??i
                 * ch??a k??ch ho???t t???n t???i kh??ng N???u c?? th?? k??ch ho???t chuy???n
                 * tr???ng th??i sang ??ang s??? d???ng v?? return.
                 */
                if (ve.getStrMaLoaiVe().equals("LVE02")
                        && ve.getStrTrangThai().equals("Chua kich hoat")) {
                    System.out.println(ve);
                    ve.setStrTrangThai("Dang su dung");
                    try {
                        vetbl.sua(ve);
                    } catch (Exception ex) {
                        Logger.getLogger(QLXRVJPanel.class.getName()).log(Level.SEVERE, null, ex);
                    }
                    JOptionPane.showMessageDialog(this, "Xe v??o b??i th??nh c??ng");
                    /**
                     * Th??m th??ng tin xe v??o b???ng xe V?? th??ng tin chi ti???t ra
                     * v??o m???i
                     */
                    xe = new XeDTO();
                    try {
                        String maXeTemp = xetbl.getMaxMaXe();
                        xe.setStrMaXe(maXeTemp);
                        System.out.println(maXeTemp);
                        if (cbbLoaiXe.getSelectedItem().toString().equals("Xe ?????p")) {
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
                 * N???u cbb l?? xe m??y th?? s??? ki???m tra xem c?? LVE001 v?? tr???ng th??i
                 * ch??a k??ch ho???t t???n t???i kh??ng N???u c?? th?? k??ch ho???t chuy???n
                 * tr???ng th??i sang ??ang s??? d???ng v?? return.
                 */
                if (ve.getStrMaLoaiVe().equals("LVE01")
                        && ve.getStrTrangThai().equals("Chua kich hoat")) {
                    System.out.println(ve);
                    ve.setStrTrangThai("Dang su dung");
                    try {
                        vetbl.sua(ve);
                    } catch (Exception ex) {
                        Logger.getLogger(QLXRVJPanel.class.getName()).log(Level.SEVERE, null, ex);
                    }
                    JOptionPane.showMessageDialog(this, "Xe v??o b??i th??nh c??ng");
                    /**
                     * Th??m th??ng tin xe v??o b???ng xe V?? th??ng tin chi ti???t ra
                     * v??o m???i
                     */
                    xe = new XeDTO();
                    try {
                        String maXeTemp = xetbl.getMaxMaXe();
                        xe.setStrMaXe(maXeTemp);
                        System.out.println(maXeTemp);
                        if (cbbLoaiXe.getSelectedItem().toString().equals("Xe ?????p")) {
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
         * N???u t???t c??? tr?????ng h???p tr??n ?????u kh??ng th???a m??n ?????ng ngh??a vi???c kh??ng
         * c?? lo???i v?? ph?? h???p Th?? s??? th??ng b??o ra m??n h??nh.
         */
        capNhatLaiTable();
        JOptionPane.showMessageDialog(this, "Xe v??o b??i kh??ng th??nh c??ng, vui l??ng ki???m tra l???i th??ng tin v?? c???a m??nh!");

    }//GEN-LAST:event_btnXeTVVaoMouseClicked

    /**
     *
     * @param evt Event click button DemoKVL Khi click v??o th?? 2 button Xe TV
     * v??o v?? Xe TV ra b??? disable 2 button Xe VL v??o v?? Xe VL ra ???????c enable 2
     * tr?????ng lo???i xe bi???n s??? xe ???????c enable tr?????ng m?? kh??ch h??ng disable
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
     * @param evt Event click button DemoKHTV Khi click v??o th?? 2 button Xe VL
     * v??o v?? Xe VL ra b??? disable
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
     * @param evt X??? l?? event Khi xe v??ng lai v??o Khi Xe v??ng lai v??o, x??? ???????c
     * ph??t m???t th??? kh??ch v??ng lai Khi qu???t th???, m?? th??? kh??ch v??ng lai ???????c t???o
     * m???i, m?? xe ???????c t???o m???i ?????ng th???i trong b???ng Xe s??? t???o m???i Xe B???ng chi
     * ti???t ra v??o s??? ???????c t???o, v???i gi??? v??o l?? gi??? hi???n t???i ?????u ti??n c???n th??m
     * xe, v??o b???ng xe, sau ???? th??m kh??ch v??ng lai Cu???i c??ng l?? th??m chi ti???t ra
     * v??o
     */
    private void btnXeVLVaoMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnXeVLVaoMouseClicked
        // TODO add your handling code here:

        //Th??m xe m???i v??o database
        XeDTO xe = new XeDTO();
        try {
            String maXeTemp = xetbl.getMaxMaXe();
            xe.setStrMaXe(maXeTemp);
            System.out.println(maXeTemp);
            if (cbbLoaiXe.getSelectedItem().toString().equals("Xe ?????p")) {
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

        //Th??m kh??ch v??ng lai m???i v??o database
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

        //Th??m chi ti???t ra v??o m???i v??o database
        CTRaVaoDTO ctrv = new CTRaVaoDTO();
        try {
            String maCTRaVaoTemp = ctrvtbl.getMaxMaCTRaVao();
            System.out.println(maCTRaVaoTemp);
            ctrv.setStrMaCTRaVao(maCTRaVaoTemp);
            ctrv.setDateThoiGianVao(sysdate());
            ctrv.setDateThoiGianRa(null);
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
        JOptionPane.showMessageDialog(this, "Xe v??o b??i th??nh c??ng");


    }//GEN-LAST:event_btnXeVLVaoMouseClicked

    /**
     *
     * @param evt Khi Lo???i xe thay ?????i th?? event ??c g???i Khi lo???i xe l?? xe ?????p,
     * tr?????ng bi???n s??? xe b??? disable Ng?????c l???i th?? tr?????ng bi???n s??? xe s??? ???????c
     * enable
     */
    private void cbbLoaiXeItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_cbbLoaiXeItemStateChanged
        // TODO add your handling code here:
        txtBienSoXe.setText("");
        if (cbbLoaiXe.getSelectedItem().toString().equals("Xe ?????p")) {
            txtBienSoXe.setEnabled(false);
        } else {
            txtBienSoXe.setEnabled(true);
        }
    }//GEN-LAST:event_cbbLoaiXeItemStateChanged

    /**
     *
     * @param evt Khi xe v??ng lai ra, s??? t??m row trong database c?? xe ???? v?? m??
     * xe ???? sau ???? c???p nh???t th???i gian ra V?? demo n??n m??nh s??? t??? nh???p m?? th??? KVL
     * Th???c t??? s??? c?? barrier x??? l?? vi???c n??y
     */
    private void btnXeVLRaMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnXeVLRaMouseClicked
        // TODO add your handling code here:
        for (CTRaVaoDTO ctrv : list_CTRV) {
            //N???u m?? th??? KVL tr??ng v???i barrier v?? th???i gian ra ??ang l?? null th?? c???p nh???t
            if (ctrv.getStrMaTheKVL() != null) {
                if (ctrv.getStrMaTheKVL().equals(txtMaTheKVL.getText())
                        && ctrv.getDateThoiGianRa() == null) {
                    ctrv.setDateThoiGianRa(sysdate());
                    try {
                        ctrvtbl.sua(ctrv);
                    } catch (Exception ex) {
                        JOptionPane.showMessageDialog(this, "Th???i gian ra ph???i l???n h??n th???i gian v??o ??t nh???t 30s");
                        return;
                    }
                    JOptionPane.showMessageDialog(this, "Xe ra b??i th??nh c??ng");
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
