/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JPanel.java to edit this template
 */
package UITParking.TEST2;

import UITParking.BUS.CTHDMuaVeBUS;
import UITParking.BUS.HDMuaVeBUS;
import UITParking.BUS.KhachHangBUS;
import UITParking.BUS.LoaiVeBUS;
import UITParking.BUS.NguoiDungBUS;
import UITParking.BUS.VeBUS;
import UITParking.BUS.XeBUS;
import UITParking.DTO.CTHDMuaVeDTO;
import UITParking.DTO.HDMuaVeDTO;
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
public class QLHDJPanel extends javax.swing.JPanel {

    /**
     * Creates new form QLHDJPanel
     */
    NguoiDungBUS nguoidungtbl = new NguoiDungBUS();
    ArrayList<NguoiDungDTO> list_ND = nguoidungtbl.getList_ND();
    KhachHangBUS khachhangtbl = new KhachHangBUS();
    ArrayList<KhachHangDTO> list_KH = khachhangtbl.getList_KH();
    LoaiVeBUS loaivetbl = new LoaiVeBUS();
    ArrayList<LoaiVeDTO> list_LV = loaivetbl.getList_LV();
    VeBUS vetbl = new VeBUS();
    ArrayList<VeDTO> list_Ve = vetbl.getList_Ve();
    HDMuaVeBUS hoadontbl = new HDMuaVeBUS();
    ArrayList<HDMuaVeDTO> list_HD = hoadontbl.getList_HD();
    CTHDMuaVeBUS cthdtbl = new CTHDMuaVeBUS();
    ArrayList<CTHDMuaVeDTO> list_CTHD = cthdtbl.getlist_CTHD();

    private DefaultTableModel model1;
    private String[] columnHeaders1 = new String[]{"STT", "Mã Hóa Đơn", "Mã Khách Hàng",
        "Ngày Hóa Đơn", "Tổng Trị Giá"};

    private DefaultTableModel model2;
    private String[] columnHeaders2 = new String[]{"STT", "Mã Hóa Đơn", "Mã Loại Vé",
        "Số Lượng Vé"};

    private TableRowSorter<TableModel> rowSorter = null;
    public static String tempMaHD;

    public QLHDJPanel() throws Exception {
        initComponents();
        initTable1();
        hoTroTimKiem1();
        initTable2();
        hoTroTimKiem2();
        btnXoaHoaDon.setEnabled(false);
        btnXoaChiTiet.setEnabled(false);
        btnCapNhatHoaDon.setEnabled(false);
        btnCapNhatChiTiet.setEnabled(false);
        btnLuuHoaDon.setEnabled(false);
        btnLuuChiTiet.setEnabled(false);
    }

    public void resetRender1() {
        txtMaHDMuaVe.setText("");
        txtMaKHMuaVe.setText("");
        txtTongTriGiaMuaVe.setText("");
        jdcNgayHDMuaVe.setDate(null);
    }

    public void resetRender2() {
        txtMaHDChiTiet.setText("");
        txtMaLoaiVeChiTiet.setText("");
        txtSLVeChiTiet.setText("");
    }

    public void initTable1() throws Exception {
        model1 = new DefaultTableModel();
        model1.setColumnIdentifiers(columnHeaders1);
        int index = 1;
        for (HDMuaVeDTO hd : list_HD) {

            //Cập nhật bảng
            model1.addRow(new Object[]{index, hd.getStrMaHD(), hd.getStrMaKH(),
                hd.getDateNgayHD(), hd.getLongTongTriGia()});
            index++;
        }

        tblHoaDonMuaVe.setModel(model1);

    }

    public void initTable2() throws Exception {
        model2 = new DefaultTableModel();
        model2.setColumnIdentifiers(columnHeaders2);
        int index = 1;
        for (CTHDMuaVeDTO cthd : list_CTHD) {

            //Cập nhật bảng
            model2.addRow(new Object[]{index, cthd.getStrMaHD(), cthd.getStrMaLoaiVe(),
                cthd.getLongSoLuongVe()});
            index++;
        }

        tblChiTietHoaDon.setModel(model2);

    }

    public void hoTroTimKiem1() {

        rowSorter = new TableRowSorter<>(tblHoaDonMuaVe.getModel());
        tblHoaDonMuaVe.setRowSorter(rowSorter);
        txtTimKiemHoaDon.getDocument().addDocumentListener(new DocumentListener() {
            @Override
            public void insertUpdate(DocumentEvent e) {
                String text = txtTimKiemHoaDon.getText();
                if (text.trim().length() == 0) {
                    rowSorter.setRowFilter(null);
                } else {
                    rowSorter.setRowFilter(RowFilter.regexFilter("(?i)" + text));
                }
            }

            @Override
            public void removeUpdate(DocumentEvent e) {
                String text = txtTimKiemHoaDon.getText();
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

    public void hoTroTimKiem2() {

        rowSorter = new TableRowSorter<>(tblChiTietHoaDon.getModel());
        tblChiTietHoaDon.setRowSorter(rowSorter);
        txtTimKiemChiTiet.getDocument().addDocumentListener(new DocumentListener() {
            @Override
            public void insertUpdate(DocumentEvent e) {
                String text = txtTimKiemChiTiet.getText();
                if (text.trim().length() == 0) {
                    rowSorter.setRowFilter(null);
                } else {
                    rowSorter.setRowFilter(RowFilter.regexFilter("(?i)" + text));
                }
            }

            @Override
            public void removeUpdate(DocumentEvent e) {
                String text = txtTimKiemChiTiet.getText();
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
    public void capNhatLaiTable1() {
        model1.setRowCount(0);
        int index = 1;
        for (HDMuaVeDTO hd : list_HD) {

            //Cập nhật bảng
            model1.addRow(new Object[]{index, hd.getStrMaHD(), hd.getStrMaKH(),
                hd.getDateNgayHD(), hd.getLongTongTriGia()});
            index++;
        }
        model1.fireTableDataChanged();
    }

    public void capNhatLaiTable2() {
        model2.setRowCount(0);
        int index = 1;
        for (CTHDMuaVeDTO cthd : list_CTHD) {

            //Cập nhật bảng
            model1.addRow(new Object[]{index, cthd.getStrMaHD(), cthd.getStrMaLoaiVe(),
                cthd.getLongSoLuongVe()});
            index++;
        }
        model2.fireTableDataChanged();
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jLabel5 = new javax.swing.JLabel();
        txtMaVe3 = new javax.swing.JTextField();
        jPanel2 = new javax.swing.JPanel();
        jPanel3 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        txtMaHDMuaVe = new javax.swing.JTextField();
        jLabel3 = new javax.swing.JLabel();
        txtMaKHMuaVe = new javax.swing.JTextField();
        jLabel4 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        txtTongTriGiaMuaVe = new javax.swing.JTextField();
        jdcNgayHDMuaVe = new com.toedter.calendar.JDateChooser();
        btnNhapMoiHoaDon = new javax.swing.JButton();
        btnLuuHoaDon = new javax.swing.JButton();
        btnCapNhatHoaDon = new javax.swing.JButton();
        btnXoaHoaDon = new javax.swing.JButton();
        btnTimKiemHoaDon = new javax.swing.JButton();
        txtTimKiemHoaDon = new javax.swing.JTextField();
        jScrollPane1 = new javax.swing.JScrollPane();
        tblHoaDonMuaVe = new javax.swing.JTable();
        jPanel5 = new javax.swing.JPanel();
        jLabel7 = new javax.swing.JLabel();
        jLabel8 = new javax.swing.JLabel();
        txtMaHDChiTiet = new javax.swing.JTextField();
        jLabel9 = new javax.swing.JLabel();
        txtMaLoaiVeChiTiet = new javax.swing.JTextField();
        jLabel11 = new javax.swing.JLabel();
        txtSLVeChiTiet = new javax.swing.JTextField();
        btnNhapMoiChiTiet = new javax.swing.JButton();
        btnLuuChiTiet = new javax.swing.JButton();
        btnCapNhatChiTiet = new javax.swing.JButton();
        btnXoaChiTiet = new javax.swing.JButton();
        btnTimKiemChiTiet = new javax.swing.JButton();
        txtTimKiemChiTiet = new javax.swing.JTextField();
        jScrollPane2 = new javax.swing.JScrollPane();
        tblChiTietHoaDon = new javax.swing.JTable();

        jLabel5.setText("Mã Khách Hàng");

        txtMaVe3.addCaretListener(new javax.swing.event.CaretListener() {
            public void caretUpdate(javax.swing.event.CaretEvent evt) {
                txtMaVe3CaretUpdate(evt);
            }
        });

        jPanel3.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        jPanel3.setPreferredSize(new java.awt.Dimension(596, 511));

        jLabel1.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel1.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel1.setText("HÓA ĐƠN MUA VÉ");

        jLabel2.setText("Mã Hóa Đơn");

        txtMaHDMuaVe.addCaretListener(new javax.swing.event.CaretListener() {
            public void caretUpdate(javax.swing.event.CaretEvent evt) {
                txtMaHDMuaVeCaretUpdate(evt);
            }
        });

        jLabel3.setText("Mã Khách Hàng");

        txtMaKHMuaVe.addCaretListener(new javax.swing.event.CaretListener() {
            public void caretUpdate(javax.swing.event.CaretEvent evt) {
                txtMaKHMuaVeCaretUpdate(evt);
            }
        });

        jLabel4.setText("Ngày Hóa Đơn");

        jLabel6.setText("Tổng Trị Giá");

        txtTongTriGiaMuaVe.addCaretListener(new javax.swing.event.CaretListener() {
            public void caretUpdate(javax.swing.event.CaretEvent evt) {
                txtTongTriGiaMuaVeCaretUpdate(evt);
            }
        });

        btnNhapMoiHoaDon.setText("Nhập Mới");
        btnNhapMoiHoaDon.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                btnNhapMoiHoaDonMouseClicked(evt);
            }
        });

        btnLuuHoaDon.setText("Lưu");
        btnLuuHoaDon.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                btnLuuHoaDonMouseClicked(evt);
            }
        });

        btnCapNhatHoaDon.setText("Cập Nhật");
        btnCapNhatHoaDon.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                btnCapNhatHoaDonMouseClicked(evt);
            }
        });

        btnXoaHoaDon.setText("Xóa");
        btnXoaHoaDon.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                btnXoaHoaDonMouseClicked(evt);
            }
        });

        btnTimKiemHoaDon.setText("Tìm Kiếm");
        btnTimKiemHoaDon.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                btnTimKiemHoaDonMouseClicked(evt);
            }
        });

        tblHoaDonMuaVe.setModel(new javax.swing.table.DefaultTableModel(
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
        tblHoaDonMuaVe.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                tblHoaDonMuaVeMousePressed(evt);
            }
        });
        jScrollPane1.setViewportView(tblHoaDonMuaVe);

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel3Layout.createSequentialGroup()
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(jScrollPane1)
                            .addGroup(jPanel3Layout.createSequentialGroup()
                                .addGap(0, 23, Short.MAX_VALUE)
                                .addComponent(btnNhapMoiHoaDon)
                                .addGap(18, 18, 18)
                                .addComponent(btnLuuHoaDon, javax.swing.GroupLayout.PREFERRED_SIZE, 57, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(btnCapNhatHoaDon)
                                .addGap(18, 18, 18)
                                .addComponent(btnXoaHoaDon)
                                .addGap(18, 18, 18)
                                .addComponent(btnTimKiemHoaDon)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(txtTimKiemHoaDon, javax.swing.GroupLayout.PREFERRED_SIZE, 144, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addGap(23, 23, 23))
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel3Layout.createSequentialGroup()
                                .addComponent(jLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, 82, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(txtMaKHMuaVe, javax.swing.GroupLayout.PREFERRED_SIZE, 187, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                .addComponent(txtMaHDMuaVe, javax.swing.GroupLayout.PREFERRED_SIZE, 187, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanel3Layout.createSequentialGroup()
                                    .addComponent(jLabel4, javax.swing.GroupLayout.PREFERRED_SIZE, 82, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                    .addComponent(jdcNgayHDMuaVe, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                                .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanel3Layout.createSequentialGroup()
                                    .addComponent(jLabel6, javax.swing.GroupLayout.PREFERRED_SIZE, 82, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                    .addComponent(txtTongTriGiaMuaVe, javax.swing.GroupLayout.PREFERRED_SIZE, 187, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanel3Layout.createSequentialGroup()
                                    .addComponent(jLabel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addGap(197, 197, 197))))
                        .addGap(0, 0, Short.MAX_VALUE))))
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 47, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addGap(28, 28, 28)
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, 22, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(txtMaKHMuaVe, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(txtMaHDMuaVe, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 22, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jLabel4, javax.swing.GroupLayout.PREFERRED_SIZE, 22, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jdcNgayHDMuaVe, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel6, javax.swing.GroupLayout.PREFERRED_SIZE, 22, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtTongTriGiaMuaVe, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(btnTimKiemHoaDon)
                        .addComponent(txtTimKiemHoaDon, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(btnNhapMoiHoaDon)
                        .addComponent(btnLuuHoaDon)
                        .addComponent(btnCapNhatHoaDon)
                        .addComponent(btnXoaHoaDon)))
                .addGap(41, 41, 41)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 231, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jPanel5.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        jPanel5.setPreferredSize(new java.awt.Dimension(596, 253));

        jLabel7.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel7.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel7.setText("CHI TIẾT HÓA ĐƠN MUA VÉ");

        jLabel8.setText("Mã Hóa Đơn");

        txtMaHDChiTiet.addCaretListener(new javax.swing.event.CaretListener() {
            public void caretUpdate(javax.swing.event.CaretEvent evt) {
                txtMaHDChiTietCaretUpdate(evt);
            }
        });

        jLabel9.setText("Mã Loại Vé");

        txtMaLoaiVeChiTiet.addCaretListener(new javax.swing.event.CaretListener() {
            public void caretUpdate(javax.swing.event.CaretEvent evt) {
                txtMaLoaiVeChiTietCaretUpdate(evt);
            }
        });

        jLabel11.setText("Số Lượng Vé");

        txtSLVeChiTiet.addCaretListener(new javax.swing.event.CaretListener() {
            public void caretUpdate(javax.swing.event.CaretEvent evt) {
                txtSLVeChiTietCaretUpdate(evt);
            }
        });

        btnNhapMoiChiTiet.setText("Nhập Mới");
        btnNhapMoiChiTiet.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                btnNhapMoiChiTietMouseClicked(evt);
            }
        });

        btnLuuChiTiet.setText("Lưu");
        btnLuuChiTiet.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                btnLuuChiTietMouseClicked(evt);
            }
        });

        btnCapNhatChiTiet.setText("Cập Nhật");
        btnCapNhatChiTiet.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                btnCapNhatChiTietMouseClicked(evt);
            }
        });

        btnXoaChiTiet.setText("Xóa");
        btnXoaChiTiet.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                btnXoaChiTietMouseClicked(evt);
            }
        });

        btnTimKiemChiTiet.setText("Tìm Kiếm");
        btnTimKiemChiTiet.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                btnTimKiemChiTietMouseClicked(evt);
            }
        });

        tblChiTietHoaDon.setModel(new javax.swing.table.DefaultTableModel(
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
        tblChiTietHoaDon.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                tblChiTietHoaDonMousePressed(evt);
            }
        });
        jScrollPane2.setViewportView(tblChiTietHoaDon);

        javax.swing.GroupLayout jPanel5Layout = new javax.swing.GroupLayout(jPanel5);
        jPanel5.setLayout(jPanel5Layout);
        jPanel5Layout.setHorizontalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel5Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel5Layout.createSequentialGroup()
                        .addComponent(btnNhapMoiChiTiet)
                        .addGap(18, 18, 18)
                        .addComponent(btnLuuChiTiet, javax.swing.GroupLayout.PREFERRED_SIZE, 57, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(btnCapNhatChiTiet)
                        .addGap(18, 18, 18)
                        .addComponent(btnXoaChiTiet)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(btnTimKiemChiTiet)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(txtTimKiemChiTiet, javax.swing.GroupLayout.PREFERRED_SIZE, 144, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18))
                    .addGroup(jPanel5Layout.createSequentialGroup()
                        .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel7, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jScrollPane2, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 572, Short.MAX_VALUE))
                        .addContainerGap())
                    .addGroup(jPanel5Layout.createSequentialGroup()
                        .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel5Layout.createSequentialGroup()
                                .addComponent(jLabel11, javax.swing.GroupLayout.PREFERRED_SIZE, 82, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(txtSLVeChiTiet, javax.swing.GroupLayout.PREFERRED_SIZE, 187, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(jPanel5Layout.createSequentialGroup()
                                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                    .addComponent(jLabel8, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(jLabel9, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, 82, Short.MAX_VALUE))
                                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(jPanel5Layout.createSequentialGroup()
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                        .addComponent(txtMaLoaiVeChiTiet, javax.swing.GroupLayout.PREFERRED_SIZE, 187, javax.swing.GroupLayout.PREFERRED_SIZE))
                                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel5Layout.createSequentialGroup()
                                        .addGap(10, 10, 10)
                                        .addComponent(txtMaHDChiTiet, javax.swing.GroupLayout.PREFERRED_SIZE, 187, javax.swing.GroupLayout.PREFERRED_SIZE)))))
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))))
        );
        jPanel5Layout.setVerticalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel5Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel7, javax.swing.GroupLayout.PREFERRED_SIZE, 47, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel5Layout.createSequentialGroup()
                        .addGap(28, 28, 28)
                        .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel9, javax.swing.GroupLayout.PREFERRED_SIZE, 22, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(txtMaLoaiVeChiTiet, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(txtMaHDChiTiet, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(jLabel8, javax.swing.GroupLayout.PREFERRED_SIZE, 22, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel11, javax.swing.GroupLayout.PREFERRED_SIZE, 22, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtSLVeChiTiet, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(btnTimKiemChiTiet)
                        .addComponent(txtTimKiemChiTiet, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(btnNhapMoiChiTiet)
                        .addComponent(btnLuuChiTiet)
                        .addComponent(btnCapNhatChiTiet)
                        .addComponent(btnXoaChiTiet)))
                .addGap(41, 41, 41)
                .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 264, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(36, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel3, javax.swing.GroupLayout.DEFAULT_SIZE, 607, Short.MAX_VALUE)
                .addGap(5, 5, 5)
                .addComponent(jPanel5, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jPanel5, javax.swing.GroupLayout.DEFAULT_SIZE, 511, Short.MAX_VALUE)
                    .addComponent(jPanel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
    }// </editor-fold>//GEN-END:initComponents

    private void txtMaVe3CaretUpdate(javax.swing.event.CaretEvent evt) {//GEN-FIRST:event_txtMaVe3CaretUpdate
        // TODO add your handling code here:
    }//GEN-LAST:event_txtMaVe3CaretUpdate

    private void btnTimKiemHoaDonMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnTimKiemHoaDonMouseClicked

    }//GEN-LAST:event_btnTimKiemHoaDonMouseClicked

    //Hóa đơn đã tạo thì không được xóa
    private void btnXoaHoaDonMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnXoaHoaDonMouseClicked
//        
    }//GEN-LAST:event_btnXoaHoaDonMouseClicked

    private void btnCapNhatHoaDonMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnCapNhatHoaDonMouseClicked

    }//GEN-LAST:event_btnCapNhatHoaDonMouseClicked

    private void btnLuuHoaDonMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnLuuHoaDonMouseClicked

    }//GEN-LAST:event_btnLuuHoaDonMouseClicked

    private void txtTongTriGiaMuaVeCaretUpdate(javax.swing.event.CaretEvent evt) {//GEN-FIRST:event_txtTongTriGiaMuaVeCaretUpdate
        // TODO add your handling code here:
    }//GEN-LAST:event_txtTongTriGiaMuaVeCaretUpdate

    private void txtMaKHMuaVeCaretUpdate(javax.swing.event.CaretEvent evt) {//GEN-FIRST:event_txtMaKHMuaVeCaretUpdate
        // TODO add your handling code here:
    }//GEN-LAST:event_txtMaKHMuaVeCaretUpdate

    private void txtMaHDMuaVeCaretUpdate(javax.swing.event.CaretEvent evt) {//GEN-FIRST:event_txtMaHDMuaVeCaretUpdate
        // TODO add your handling code here:
    }//GEN-LAST:event_txtMaHDMuaVeCaretUpdate

    private void txtMaHDChiTietCaretUpdate(javax.swing.event.CaretEvent evt) {//GEN-FIRST:event_txtMaHDChiTietCaretUpdate
        // TODO add your handling code here:
    }//GEN-LAST:event_txtMaHDChiTietCaretUpdate

    private void txtMaLoaiVeChiTietCaretUpdate(javax.swing.event.CaretEvent evt) {//GEN-FIRST:event_txtMaLoaiVeChiTietCaretUpdate
        // TODO add your handling code here:
    }//GEN-LAST:event_txtMaLoaiVeChiTietCaretUpdate

    private void txtSLVeChiTietCaretUpdate(javax.swing.event.CaretEvent evt) {//GEN-FIRST:event_txtSLVeChiTietCaretUpdate
        // TODO add your handling code here:
    }//GEN-LAST:event_txtSLVeChiTietCaretUpdate

    private void btnNhapMoiChiTietMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnNhapMoiChiTietMouseClicked
        // TODO add your handling code here:
    }//GEN-LAST:event_btnNhapMoiChiTietMouseClicked

    private void btnLuuChiTietMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnLuuChiTietMouseClicked
        // TODO add your handling code here:
    }//GEN-LAST:event_btnLuuChiTietMouseClicked

    private void btnCapNhatChiTietMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnCapNhatChiTietMouseClicked
        // TODO add your handling code here:
    }//GEN-LAST:event_btnCapNhatChiTietMouseClicked

    private void btnXoaChiTietMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnXoaChiTietMouseClicked
        // TODO add your handling code here:
    }//GEN-LAST:event_btnXoaChiTietMouseClicked

    private void btnTimKiemChiTietMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnTimKiemChiTietMouseClicked
        // TODO add your handling code here:
    }//GEN-LAST:event_btnTimKiemChiTietMouseClicked

    private void btnNhapMoiHoaDonMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnNhapMoiHoaDonMouseClicked
        // TODO add your handling code here:
    }//GEN-LAST:event_btnNhapMoiHoaDonMouseClicked

    private void tblHoaDonMuaVeMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tblHoaDonMuaVeMousePressed
        // TODO add your handling code here:
        //Khi đổi pressed thì sẽ reset các trường của chi tiết hóa đơn
        //Gọi hàm resetRender2()
        resetRender1();
        resetRender2();
        
        //
        int selectedRow = tblHoaDonMuaVe.getSelectedRow();
        if (selectedRow >= 0) {

            HDMuaVeDTO hd = list_HD.get(selectedRow);

            txtMaHDMuaVe.setText(hd.getStrMaHD());
            txtMaKHMuaVe.setText(hd.getStrMaKH());

            if (hd.getDateNgayHD() != null) {
                jdcNgayHDMuaVe.setDate(hd.getDateNgayHD());
            }
            txtTongTriGiaMuaVe.setText(String.valueOf(hd.getLongTongTriGia()));
            //Bấm vào hóa đơn, hiện ra chi tiết hóa đơn đối với loại vé đó.
            tempMaHD = hd.getStrMaHD();
            txtTimKiemChiTiet.setText(tempMaHD);
        }
    }//GEN-LAST:event_tblHoaDonMuaVeMousePressed

    private void tblChiTietHoaDonMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tblChiTietHoaDonMousePressed
        // TODO add your handling code here:
        resetRender2();
        int selectedRow = tblChiTietHoaDon.getSelectedRow();
        if (selectedRow >= 0) {

            CTHDMuaVeDTO cthd = list_CTHD.get(selectedRow);

            txtMaHDChiTiet.setText(cthd.getStrMaHD());
            txtMaLoaiVeChiTiet.setText(cthd.getStrMaLoaiVe());
            txtSLVeChiTiet.setText(String.valueOf(cthd.getLongSoLuongVe()));
        }
    }//GEN-LAST:event_tblChiTietHoaDonMousePressed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnCapNhatChiTiet;
    private javax.swing.JButton btnCapNhatHoaDon;
    private javax.swing.JButton btnLuuChiTiet;
    private javax.swing.JButton btnLuuHoaDon;
    private javax.swing.JButton btnNhapMoiChiTiet;
    private javax.swing.JButton btnNhapMoiHoaDon;
    private javax.swing.JButton btnTimKiemChiTiet;
    private javax.swing.JButton btnTimKiemHoaDon;
    private javax.swing.JButton btnXoaChiTiet;
    private javax.swing.JButton btnXoaHoaDon;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel5;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private com.toedter.calendar.JDateChooser jdcNgayHDMuaVe;
    private javax.swing.JTable tblChiTietHoaDon;
    private javax.swing.JTable tblHoaDonMuaVe;
    private javax.swing.JTextField txtMaHDChiTiet;
    private javax.swing.JTextField txtMaHDMuaVe;
    private javax.swing.JTextField txtMaKHMuaVe;
    private javax.swing.JTextField txtMaLoaiVeChiTiet;
    private javax.swing.JTextField txtMaVe3;
    private javax.swing.JTextField txtSLVeChiTiet;
    private javax.swing.JTextField txtTimKiemChiTiet;
    private javax.swing.JTextField txtTimKiemHoaDon;
    private javax.swing.JTextField txtTongTriGiaMuaVe;
    // End of variables declaration//GEN-END:variables
}
