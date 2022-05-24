/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package UITParking.GUI;

import UITParking.BUS.CTHDMuaVeBUS;
import UITParking.BUS.HDMuaVeBUS;
import UITParking.BUS.KhachHangBUS;
import UITParking.BUS.VeBUS;
import UITParking.DTO.CTHDMuaVeDTO;
import UITParking.DTO.HDMuaVeDTO;
import UITParking.DTO.KhachHangDTO;
import UITParking.DTO.VeDTO;
import static UITParking.GUI.InitPublic.getDateThoiGianThuc;
import static UITParking.GUI.InitPublic.getThoiGianThuc;
import static UITParking.GUI.MuaVe.slVe3000Dong;
import static UITParking.GUI.MuaVe.slVe2000Dong;
import static UITParking.GUI.MuaVe.slVe25000Dong;
import static UITParking.GUI.MuaVe.slVe95000Dong;
import static UITParking.GUI.MuaVe.slVeGioHang;
import static UITParking.GUI.NapTien.tempTien;
import static UITParking.GUI.login.pMaND;
import java.awt.Color;
import static java.awt.GridBagConstraints.BOTH;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import java.awt.Toolkit;
import javax.swing.JFrame;

/**
 *
 * @author ADMIN
 */
public class GioHang extends javax.swing.JFrame {

    /**
     * Creates new form GioHang
     */
    public static int pTongTienThanhToan = 0;
    public static long tempTienGioHang = 0;
    KhachHangBUS khachhangtbl = new KhachHangBUS();
    KhachHangDTO kh = khachhangtbl.getInfor(pMaND);
    HDMuaVeBUS hdmuavetbl = new HDMuaVeBUS();
    CTHDMuaVeBUS cthdmuavetbl = new CTHDMuaVeBUS();
    VeBUS vetbl = new VeBUS();

    public GioHang() throws Exception {
        initComponents();
        setIconImage();
        KhachHangBUS khachhangtbl = new KhachHangBUS();
        KhachHangDTO kh = khachhangtbl.getInfor(pMaND);
        tempTienGioHang = kh.getLongSoDu();

        System.out.println("So tien" + tempTienGioHang);

        //Render giao diện giỏ hàng 
        renderGiaoDienGioHang();
        //Set số lượng ban đầu khi chọn mua vé
        setSLVeText();
        //Set tổng tiền dựa vào số lượng vé và đơn giá vé
        setTongTienThanhToan();
        //Set tổng tiền từng loại vé
        setTongTienText();
        this.setExtendedState(JFrame.MAXIMIZED_BOTH);

    }

    //Hàm render giao diện giỏ hàng dựa vào số lượng vé của từng loại
    public void renderGiaoDienGioHang() {
        if (slVe2000Dong <= 0 && slVe3000Dong <= 0
                && slVe25000Dong <= 0 && slVe95000Dong <= 0) {
//            empty.setVisible(true);
//            panelTitle.setVisible(false);
//            panelThanhToan.setVisible(false);
            PanelEmpty.setVisible(true);
            PanelGioHang.setVisible(false);
        } else {
//            empty.setVisible(false);
            PanelEmpty.setVisible(false);

//            PanelEmpty.setVisible(true);
        }
        if (slVe2000Dong <= 0) {
            panelChiTietVe2000Dong.setVisible(false);
        }
        if (slVe3000Dong <= 0) {
            panelChiTietVe3000Dong.setVisible(false);
        }
        if (slVe25000Dong <= 0) {
            panelChiTietVe25000Dong.setVisible(false);
        }
        if (slVe95000Dong <= 0) {
            panelChiTietVe95000Dong.setVisible(false);
        }
    }

    public void setTongTienText() {
        txtTongCongVe3000Dong.setText(String.valueOf(slVe3000Dong * 3000));
        txtTongCongVe2000Dong.setText(String.valueOf(slVe2000Dong * 2000));
        txtTongCongVe25000Dong.setText(String.valueOf(slVe25000Dong * 25000));
        txtTongCongVe95000Dong.setText(String.valueOf(slVe95000Dong * 95000));

    }

    //Hàm set số lượng ban đầu khi chọn mua vé
    public void setSLVeText() {
        txtSLVe2000Dong.setText(String.valueOf(slVe2000Dong));
        txtSLVe3000Dong.setText(String.valueOf(slVe3000Dong));
        txtSLVe25000Dong.setText(String.valueOf(slVe25000Dong));
        txtSLVe95000Dong.setText(String.valueOf(slVe95000Dong));
    }

    //Hàm set số lượng vé về 0.
    public void setSLVe0() {
        slVe2000Dong = 0;
        slVe25000Dong = 0;
        slVe3000Dong = 0;
        slVe95000Dong = 0;
    }

    //Hàm xóa các loại vé có trong giao diện
    public void xoaSLVe() {
        panelChiTietVe2000Dong.setVisible(false);
        panelChiTietVe25000Dong.setVisible(false);
        panelChiTietVe3000Dong.setVisible(false);
        panelChiTietVe95000Dong.setVisible(false);
        PanelEmpty.setVisible(true);
        PanelGioHang.setVisible(false);
    }

    //Hàm tính tổng tiền thanh toán
    public void setTongTienThanhToan() {
        pTongTienThanhToan = slVe2000Dong * 2000
                + slVe3000Dong * 3000
                + slVe25000Dong * 25000
                + slVe95000Dong * 95000;
        txtTongTienThanhToan.setText(String.valueOf(pTongTienThanhToan) + "đ");
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        PanelGioHang = new javax.swing.JPanel();
        panelTitle = new javax.swing.JPanel();
        jLabel12 = new javax.swing.JLabel();
        jLabel13 = new javax.swing.JLabel();
        jLabel14 = new javax.swing.JLabel();
        jLabel15 = new javax.swing.JLabel();
        btnBackGioHang = new javax.swing.JButton();
        panelChiTietVe3000Dong = new Admin.PanelRound();
        jLabel2 = new javax.swing.JLabel();
        txtSLVe3000Dong = new javax.swing.JButton();
        btnTruSLVe3000Dong = new javax.swing.JButton();
        btnCongSLVe3000Dong = new javax.swing.JButton();
        txtTongCongVe3000Dong = new javax.swing.JLabel();
        jLabel17 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        btnXoaVe3000Dong = new javax.swing.JButton();
        panelChiTietVe2000Dong = new Admin.PanelRound();
        jLabel18 = new javax.swing.JLabel();
        txtTongCongVe2000Dong = new javax.swing.JLabel();
        jLabel20 = new javax.swing.JLabel();
        jLabel21 = new javax.swing.JLabel();
        btnTruSLVe2000Dong = new javax.swing.JButton();
        txtSLVe2000Dong = new javax.swing.JButton();
        btnCongSLVe2000Dong = new javax.swing.JButton();
        btnXoaVe2000Dong = new javax.swing.JButton();
        panelChiTietVe25000Dong = new Admin.PanelRound();
        jLabel22 = new javax.swing.JLabel();
        txtTongCongVe25000Dong = new javax.swing.JLabel();
        jLabel24 = new javax.swing.JLabel();
        jLabel25 = new javax.swing.JLabel();
        btnTruSLVe25000Dong = new javax.swing.JButton();
        btnCongSLVe25000Dong = new javax.swing.JButton();
        txtSLVe25000Dong = new javax.swing.JButton();
        btnXoaVe25000Dong = new javax.swing.JButton();
        panelThanhToan = new Admin.PanelRound();
        jLabel4 = new javax.swing.JLabel();
        btnThanhToanGioHang = new javax.swing.JButton();
        txtTongTienThanhToan = new javax.swing.JLabel();
        panelTiTle = new Admin.PanelRound();
        Title = new javax.swing.JLabel();
        panelChiTietVe95000Dong = new Admin.PanelRound();
        jLabel30 = new javax.swing.JLabel();
        txtTongCongVe95000Dong = new javax.swing.JLabel();
        jLabel32 = new javax.swing.JLabel();
        jLabel33 = new javax.swing.JLabel();
        btnTruSLVe95000Dong = new javax.swing.JButton();
        btnCongSLVe95000Dong = new javax.swing.JButton();
        txtSLVe95000Dong = new javax.swing.JButton();
        btnXoaVe95000Dong = new javax.swing.JButton();
        PanelEmpty = new javax.swing.JPanel();
        empty = new javax.swing.JLabel();
        labelgiohangtrong = new javax.swing.JLabel();
        btnBackGioHangTrong = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("UIT Parking");
        getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        PanelGioHang.setBackground(new java.awt.Color(41, 58, 128));
        PanelGioHang.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        panelTitle.setBackground(new java.awt.Color(255, 255, 255));
        panelTitle.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));

        jLabel12.setFont(new java.awt.Font("Cooper", 0, 18)); // NOI18N
        jLabel12.setForeground(new java.awt.Color(41, 58, 128));
        jLabel12.setText("Tên Vé");

        jLabel13.setFont(new java.awt.Font("Cooper", 0, 18)); // NOI18N
        jLabel13.setForeground(new java.awt.Color(41, 58, 128));
        jLabel13.setText("Số Lượng Vé");

        jLabel14.setFont(new java.awt.Font("Cooper", 0, 18)); // NOI18N
        jLabel14.setForeground(new java.awt.Color(41, 58, 128));
        jLabel14.setText("Giá");

        jLabel15.setFont(new java.awt.Font("Cooper", 0, 18)); // NOI18N
        jLabel15.setForeground(new java.awt.Color(41, 58, 128));
        jLabel15.setText("Tổng cộng");

        javax.swing.GroupLayout panelTitleLayout = new javax.swing.GroupLayout(panelTitle);
        panelTitle.setLayout(panelTitleLayout);
        panelTitleLayout.setHorizontalGroup(
            panelTitleLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelTitleLayout.createSequentialGroup()
                .addGap(31, 31, 31)
                .addComponent(jLabel12, javax.swing.GroupLayout.PREFERRED_SIZE, 103, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 313, Short.MAX_VALUE)
                .addComponent(jLabel13)
                .addGap(81, 81, 81)
                .addComponent(jLabel14)
                .addGap(69, 69, 69)
                .addComponent(jLabel15)
                .addGap(98, 98, 98))
        );
        panelTitleLayout.setVerticalGroup(
            panelTitleLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, panelTitleLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(panelTitleLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel12, javax.swing.GroupLayout.DEFAULT_SIZE, 29, Short.MAX_VALUE)
                    .addComponent(jLabel13, javax.swing.GroupLayout.PREFERRED_SIZE, 21, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel14, javax.swing.GroupLayout.PREFERRED_SIZE, 21, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel15, javax.swing.GroupLayout.PREFERRED_SIZE, 21, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap())
        );

        PanelGioHang.add(panelTitle, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 90, 950, -1));

        btnBackGioHang.setBackground(new java.awt.Color(243, 148, 34));
        btnBackGioHang.setFont(new java.awt.Font("Cooper", 0, 18)); // NOI18N
        btnBackGioHang.setForeground(new java.awt.Color(255, 255, 255));
        btnBackGioHang.setText("Quay lại");
        btnBackGioHang.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btnBackGioHang.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                btnBackGioHangMouseClicked(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                btnBackGioHangMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                btnBackGioHangMouseExited(evt);
            }
        });
        PanelGioHang.add(btnBackGioHang, new org.netbeans.lib.awtextra.AbsoluteConstraints(1070, 600, 120, 40));

        panelChiTietVe3000Dong.setBackground(new java.awt.Color(255, 255, 255));
        panelChiTietVe3000Dong.setRoundBottomLeft(25);
        panelChiTietVe3000Dong.setRoundBottomRight(25);
        panelChiTietVe3000Dong.setRoundTopLeft(25);
        panelChiTietVe3000Dong.setRoundTopRight(25);

        jLabel2.setFont(new java.awt.Font("Cooper", 0, 18)); // NOI18N
        jLabel2.setForeground(new java.awt.Color(41, 58, 128));
        jLabel2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icon/imgonline-com-ua-resize-tWkwJvM5UoVj.png"))); // NOI18N
        jLabel2.setText("Vé Lượt Xe Máy");

        txtSLVe3000Dong.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        txtSLVe3000Dong.setText("SL Vé");

        btnTruSLVe3000Dong.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        btnTruSLVe3000Dong.setText("-");
        btnTruSLVe3000Dong.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btnTruSLVe3000Dong.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                btnTruSLVe3000DongMouseClicked(evt);
            }
        });

        btnCongSLVe3000Dong.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        btnCongSLVe3000Dong.setText("+");
        btnCongSLVe3000Dong.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btnCongSLVe3000Dong.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                btnCongSLVe3000DongMouseClicked(evt);
            }
        });

        txtTongCongVe3000Dong.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        txtTongCongVe3000Dong.setText("Tổng cộng");

        jLabel17.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel17.setText("3.000đ");

        jLabel3.setFont(new java.awt.Font("Cooper", 0, 18)); // NOI18N
        jLabel3.setText("Vé lượt xe máy");

        btnXoaVe3000Dong.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        btnXoaVe3000Dong.setText("Xóa");
        btnXoaVe3000Dong.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btnXoaVe3000Dong.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnXoaVe3000DongActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout panelChiTietVe3000DongLayout = new javax.swing.GroupLayout(panelChiTietVe3000Dong);
        panelChiTietVe3000Dong.setLayout(panelChiTietVe3000DongLayout);
        panelChiTietVe3000DongLayout.setHorizontalGroup(
            panelChiTietVe3000DongLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelChiTietVe3000DongLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 191, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(jLabel3)
                .addGap(76, 76, 76)
                .addComponent(btnTruSLVe3000Dong)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(txtSLVe3000Dong)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(btnCongSLVe3000Dong)
                .addGap(75, 75, 75)
                .addComponent(jLabel17)
                .addGap(70, 70, 70)
                .addComponent(txtTongCongVe3000Dong)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 28, Short.MAX_VALUE)
                .addComponent(btnXoaVe3000Dong)
                .addGap(17, 17, 17))
        );
        panelChiTietVe3000DongLayout.setVerticalGroup(
            panelChiTietVe3000DongLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelChiTietVe3000DongLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(panelChiTietVe3000DongLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, panelChiTietVe3000DongLayout.createSequentialGroup()
                        .addGroup(panelChiTietVe3000DongLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(txtSLVe3000Dong)
                            .addComponent(btnTruSLVe3000Dong)
                            .addComponent(btnCongSLVe3000Dong)
                            .addComponent(txtTongCongVe3000Dong, javax.swing.GroupLayout.PREFERRED_SIZE, 21, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel17, javax.swing.GroupLayout.PREFERRED_SIZE, 21, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel3)
                            .addComponent(btnXoaVe3000Dong))
                        .addGap(50, 50, 50))
                    .addGroup(panelChiTietVe3000DongLayout.createSequentialGroup()
                        .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 108, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(6, 6, 6))))
        );

        PanelGioHang.add(panelChiTietVe3000Dong, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 140, 950, 120));

        panelChiTietVe2000Dong.setBackground(new java.awt.Color(255, 255, 255));
        panelChiTietVe2000Dong.setRoundBottomLeft(25);
        panelChiTietVe2000Dong.setRoundBottomRight(25);
        panelChiTietVe2000Dong.setRoundTopLeft(25);
        panelChiTietVe2000Dong.setRoundTopRight(25);

        jLabel18.setFont(new java.awt.Font("Cooper", 0, 18)); // NOI18N
        jLabel18.setForeground(new java.awt.Color(41, 58, 128));
        jLabel18.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icon/vé xe đạp.png"))); // NOI18N
        jLabel18.setText("Vé Lượt Xe Máy");

        txtTongCongVe2000Dong.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        txtTongCongVe2000Dong.setText("Tổng cộng");

        jLabel20.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel20.setText("2.000đ");

        jLabel21.setFont(new java.awt.Font("Cooper", 0, 18)); // NOI18N
        jLabel21.setText("Vé lượt xe đạp");

        btnTruSLVe2000Dong.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        btnTruSLVe2000Dong.setText("-");
        btnTruSLVe2000Dong.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btnTruSLVe2000Dong.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                btnTruSLVe2000DongMouseClicked(evt);
            }
        });

        txtSLVe2000Dong.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        txtSLVe2000Dong.setText("SL Vé");

        btnCongSLVe2000Dong.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        btnCongSLVe2000Dong.setText("+");
        btnCongSLVe2000Dong.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btnCongSLVe2000Dong.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                btnCongSLVe2000DongMouseClicked(evt);
            }
        });

        btnXoaVe2000Dong.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        btnXoaVe2000Dong.setText("Xóa");
        btnXoaVe2000Dong.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btnXoaVe2000Dong.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                btnXoaVe2000DongMouseClicked(evt);
            }
        });
        btnXoaVe2000Dong.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnXoaVe2000DongActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout panelChiTietVe2000DongLayout = new javax.swing.GroupLayout(panelChiTietVe2000Dong);
        panelChiTietVe2000Dong.setLayout(panelChiTietVe2000DongLayout);
        panelChiTietVe2000DongLayout.setHorizontalGroup(
            panelChiTietVe2000DongLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelChiTietVe2000DongLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel18, javax.swing.GroupLayout.PREFERRED_SIZE, 191, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(jLabel21)
                .addGap(81, 81, 81)
                .addComponent(btnTruSLVe2000Dong)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(txtSLVe2000Dong)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(btnCongSLVe2000Dong)
                .addGap(77, 77, 77)
                .addComponent(jLabel20)
                .addGap(69, 69, 69)
                .addComponent(txtTongCongVe2000Dong)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 27, Short.MAX_VALUE)
                .addComponent(btnXoaVe2000Dong)
                .addGap(17, 17, 17))
        );
        panelChiTietVe2000DongLayout.setVerticalGroup(
            panelChiTietVe2000DongLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelChiTietVe2000DongLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(panelChiTietVe2000DongLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel18, javax.swing.GroupLayout.PREFERRED_SIZE, 108, Short.MAX_VALUE)
                    .addComponent(txtTongCongVe2000Dong, javax.swing.GroupLayout.PREFERRED_SIZE, 21, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel20, javax.swing.GroupLayout.PREFERRED_SIZE, 21, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel21)
                    .addComponent(btnTruSLVe2000Dong)
                    .addComponent(txtSLVe2000Dong)
                    .addComponent(btnCongSLVe2000Dong)
                    .addComponent(btnXoaVe2000Dong))
                .addContainerGap())
        );

        PanelGioHang.add(panelChiTietVe2000Dong, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 270, 950, 120));

        panelChiTietVe25000Dong.setBackground(new java.awt.Color(255, 255, 255));
        panelChiTietVe25000Dong.setRoundBottomLeft(25);
        panelChiTietVe25000Dong.setRoundBottomRight(25);
        panelChiTietVe25000Dong.setRoundTopLeft(25);
        panelChiTietVe25000Dong.setRoundTopRight(25);

        jLabel22.setFont(new java.awt.Font("Cooper", 0, 18)); // NOI18N
        jLabel22.setForeground(new java.awt.Color(41, 58, 128));
        jLabel22.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icon/vé tuần.png"))); // NOI18N
        jLabel22.setText("Vé Lượt Xe Máy");

        txtTongCongVe25000Dong.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        txtTongCongVe25000Dong.setText("Tổng cộng");

        jLabel24.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel24.setText("25.000đ");

        jLabel25.setFont(new java.awt.Font("Cooper", 0, 18)); // NOI18N
        jLabel25.setText("Vé tuần");

        btnTruSLVe25000Dong.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        btnTruSLVe25000Dong.setText("-");
        btnTruSLVe25000Dong.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btnTruSLVe25000Dong.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                btnTruSLVe25000DongMouseClicked(evt);
            }
        });

        btnCongSLVe25000Dong.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        btnCongSLVe25000Dong.setText("+");
        btnCongSLVe25000Dong.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btnCongSLVe25000Dong.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                btnCongSLVe25000DongMouseClicked(evt);
            }
        });

        txtSLVe25000Dong.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        txtSLVe25000Dong.setText("SL Vé");

        btnXoaVe25000Dong.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        btnXoaVe25000Dong.setText("Xóa");
        btnXoaVe25000Dong.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btnXoaVe25000Dong.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                btnXoaVe25000DongMouseClicked(evt);
            }
        });
        btnXoaVe25000Dong.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnXoaVe25000DongActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout panelChiTietVe25000DongLayout = new javax.swing.GroupLayout(panelChiTietVe25000Dong);
        panelChiTietVe25000Dong.setLayout(panelChiTietVe25000DongLayout);
        panelChiTietVe25000DongLayout.setHorizontalGroup(
            panelChiTietVe25000DongLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelChiTietVe25000DongLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel22, javax.swing.GroupLayout.PREFERRED_SIZE, 191, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(jLabel25, javax.swing.GroupLayout.PREFERRED_SIZE, 146, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(76, 76, 76)
                .addComponent(btnTruSLVe25000Dong)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(txtSLVe25000Dong)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(btnCongSLVe25000Dong)
                .addGap(73, 73, 73)
                .addComponent(jLabel24)
                .addGap(64, 64, 64)
                .addComponent(txtTongCongVe25000Dong)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 27, Short.MAX_VALUE)
                .addComponent(btnXoaVe25000Dong)
                .addGap(17, 17, 17))
        );
        panelChiTietVe25000DongLayout.setVerticalGroup(
            panelChiTietVe25000DongLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelChiTietVe25000DongLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(panelChiTietVe25000DongLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel22, javax.swing.GroupLayout.PREFERRED_SIZE, 108, Short.MAX_VALUE)
                    .addComponent(txtTongCongVe25000Dong, javax.swing.GroupLayout.PREFERRED_SIZE, 21, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel24, javax.swing.GroupLayout.PREFERRED_SIZE, 21, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel25, javax.swing.GroupLayout.PREFERRED_SIZE, 27, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnTruSLVe25000Dong)
                    .addComponent(btnCongSLVe25000Dong)
                    .addComponent(txtSLVe25000Dong)
                    .addComponent(btnXoaVe25000Dong))
                .addContainerGap())
        );

        PanelGioHang.add(panelChiTietVe25000Dong, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 400, 950, 120));

        panelThanhToan.setBackground(new java.awt.Color(255, 255, 255));
        panelThanhToan.setRoundBottomLeft(25);
        panelThanhToan.setRoundBottomRight(25);
        panelThanhToan.setRoundTopLeft(25);
        panelThanhToan.setRoundTopRight(25);

        jLabel4.setFont(new java.awt.Font("Cooper", 0, 18)); // NOI18N
        jLabel4.setForeground(new java.awt.Color(41, 58, 128));
        jLabel4.setText("Thành Tiền");

        btnThanhToanGioHang.setBackground(new java.awt.Color(255, 204, 51));
        btnThanhToanGioHang.setFont(new java.awt.Font("Cooper", 0, 18)); // NOI18N
        btnThanhToanGioHang.setText("Thanh Toán");
        btnThanhToanGioHang.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btnThanhToanGioHang.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                btnThanhToanGioHangMouseClicked(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                btnThanhToanGioHangMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                btnThanhToanGioHangMouseExited(evt);
            }
        });

        txtTongTienThanhToan.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        txtTongTienThanhToan.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        txtTongTienThanhToan.setText("0 đ");

        javax.swing.GroupLayout panelThanhToanLayout = new javax.swing.GroupLayout(panelThanhToan);
        panelThanhToan.setLayout(panelThanhToanLayout);
        panelThanhToanLayout.setHorizontalGroup(
            panelThanhToanLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelThanhToanLayout.createSequentialGroup()
                .addGap(14, 14, 14)
                .addComponent(jLabel4, javax.swing.GroupLayout.PREFERRED_SIZE, 119, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 9, Short.MAX_VALUE)
                .addComponent(txtTongTienThanhToan, javax.swing.GroupLayout.PREFERRED_SIZE, 104, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(24, 24, 24))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, panelThanhToanLayout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(btnThanhToanGioHang, javax.swing.GroupLayout.PREFERRED_SIZE, 152, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(51, 51, 51))
        );
        panelThanhToanLayout.setVerticalGroup(
            panelThanhToanLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelThanhToanLayout.createSequentialGroup()
                .addGap(41, 41, 41)
                .addGroup(panelThanhToanLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txtTongTienThanhToan, javax.swing.GroupLayout.PREFERRED_SIZE, 27, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel4, javax.swing.GroupLayout.PREFERRED_SIZE, 27, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 45, Short.MAX_VALUE)
                .addComponent(btnThanhToanGioHang, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(24, 24, 24))
        );

        PanelGioHang.add(panelThanhToan, new org.netbeans.lib.awtextra.AbsoluteConstraints(990, 90, 270, 170));

        panelTiTle.setBackground(new java.awt.Color(1, 0, 56));
        panelTiTle.setRoundBottomLeft(25);
        panelTiTle.setRoundBottomRight(25);
        panelTiTle.setRoundTopLeft(25);
        panelTiTle.setRoundTopRight(25);

        Title.setFont(new java.awt.Font("Cooper", 0, 48)); // NOI18N
        Title.setForeground(new java.awt.Color(243, 148, 34));
        Title.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        Title.setText("MY CART");

        javax.swing.GroupLayout panelTiTleLayout = new javax.swing.GroupLayout(panelTiTle);
        panelTiTle.setLayout(panelTiTleLayout);
        panelTiTleLayout.setHorizontalGroup(
            panelTiTleLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, panelTiTleLayout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(Title, javax.swing.GroupLayout.PREFERRED_SIZE, 298, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );
        panelTiTleLayout.setVerticalGroup(
            panelTiTleLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, panelTiTleLayout.createSequentialGroup()
                .addGap(0, 8, Short.MAX_VALUE)
                .addComponent(Title, javax.swing.GroupLayout.PREFERRED_SIZE, 62, javax.swing.GroupLayout.PREFERRED_SIZE))
        );

        PanelGioHang.add(panelTiTle, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 10, 310, 70));

        panelChiTietVe95000Dong.setBackground(new java.awt.Color(255, 255, 255));
        panelChiTietVe95000Dong.setRoundBottomLeft(25);
        panelChiTietVe95000Dong.setRoundBottomRight(25);
        panelChiTietVe95000Dong.setRoundTopLeft(25);
        panelChiTietVe95000Dong.setRoundTopRight(25);

        jLabel30.setFont(new java.awt.Font("Cooper", 0, 18)); // NOI18N
        jLabel30.setForeground(new java.awt.Color(41, 58, 128));
        jLabel30.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icon/vé tháng.png"))); // NOI18N
        jLabel30.setText("Vé Lượt Xe Máy");

        txtTongCongVe95000Dong.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        txtTongCongVe95000Dong.setText("Tổng cộng");

        jLabel32.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel32.setText("95.000đ");

        jLabel33.setFont(new java.awt.Font("Cooper", 0, 18)); // NOI18N
        jLabel33.setText("Vé tháng");

        btnTruSLVe95000Dong.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        btnTruSLVe95000Dong.setText("-");
        btnTruSLVe95000Dong.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btnTruSLVe95000Dong.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                btnTruSLVe95000DongMouseClicked(evt);
            }
        });

        btnCongSLVe95000Dong.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        btnCongSLVe95000Dong.setText("+");
        btnCongSLVe95000Dong.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btnCongSLVe95000Dong.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                btnCongSLVe95000DongMouseClicked(evt);
            }
        });

        txtSLVe95000Dong.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        txtSLVe95000Dong.setText("SL Vé");

        btnXoaVe95000Dong.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        btnXoaVe95000Dong.setText("Xóa");
        btnXoaVe95000Dong.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btnXoaVe95000Dong.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                btnXoaVe95000DongMouseClicked(evt);
            }
        });
        btnXoaVe95000Dong.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnXoaVe95000DongActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout panelChiTietVe95000DongLayout = new javax.swing.GroupLayout(panelChiTietVe95000Dong);
        panelChiTietVe95000Dong.setLayout(panelChiTietVe95000DongLayout);
        panelChiTietVe95000DongLayout.setHorizontalGroup(
            panelChiTietVe95000DongLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelChiTietVe95000DongLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel30, javax.swing.GroupLayout.PREFERRED_SIZE, 191, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(jLabel33, javax.swing.GroupLayout.PREFERRED_SIZE, 146, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(76, 76, 76)
                .addComponent(btnTruSLVe95000Dong)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(txtSLVe95000Dong)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(btnCongSLVe95000Dong)
                .addGap(73, 73, 73)
                .addComponent(jLabel32)
                .addGap(64, 64, 64)
                .addComponent(txtTongCongVe95000Dong)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 27, Short.MAX_VALUE)
                .addComponent(btnXoaVe95000Dong)
                .addGap(17, 17, 17))
        );
        panelChiTietVe95000DongLayout.setVerticalGroup(
            panelChiTietVe95000DongLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelChiTietVe95000DongLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(panelChiTietVe95000DongLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel30, javax.swing.GroupLayout.PREFERRED_SIZE, 108, Short.MAX_VALUE)
                    .addComponent(txtTongCongVe95000Dong, javax.swing.GroupLayout.PREFERRED_SIZE, 21, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel32, javax.swing.GroupLayout.PREFERRED_SIZE, 21, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel33, javax.swing.GroupLayout.PREFERRED_SIZE, 27, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnTruSLVe95000Dong)
                    .addComponent(btnCongSLVe95000Dong)
                    .addComponent(txtSLVe95000Dong)
                    .addComponent(btnXoaVe95000Dong))
                .addContainerGap())
        );

        PanelGioHang.add(panelChiTietVe95000Dong, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 530, 950, -1));

        getContentPane().add(PanelGioHang, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 1370, 690));

        PanelEmpty.setBackground(new java.awt.Color(41, 58, 128));
        PanelEmpty.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        empty.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icon/bgnoticket.png"))); // NOI18N
        PanelEmpty.add(empty, new org.netbeans.lib.awtextra.AbsoluteConstraints(320, 60, 556, 394));

        labelgiohangtrong.setFont(new java.awt.Font("Segoe UI", 1, 48)); // NOI18N
        labelgiohangtrong.setForeground(new java.awt.Color(255, 255, 255));
        labelgiohangtrong.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        labelgiohangtrong.setText("Giỏ hàng trống");
        PanelEmpty.add(labelgiohangtrong, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 450, 1290, 60));

        btnBackGioHangTrong.setBackground(new java.awt.Color(243, 148, 34));
        btnBackGioHangTrong.setFont(new java.awt.Font("Cooper", 0, 18)); // NOI18N
        btnBackGioHangTrong.setForeground(new java.awt.Color(255, 255, 255));
        btnBackGioHangTrong.setText("Thêm vé");
        btnBackGioHangTrong.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                btnBackGioHangTrongMouseClicked(evt);
            }
        });
        PanelEmpty.add(btnBackGioHangTrong, new org.netbeans.lib.awtextra.AbsoluteConstraints(590, 530, 120, 40));

        getContentPane().add(PanelEmpty, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 1290, 730));

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    //Event click button back ở trang giỏ hàng.
    private void btnBackGioHangMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnBackGioHangMouseClicked
        // TODO add your handling code here:
        MuaVe _muaVe = new MuaVe();
        _muaVe.show();
        dispose();
    }//GEN-LAST:event_btnBackGioHangMouseClicked

    private void btnBackGioHangTrongMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnBackGioHangTrongMouseClicked
        MuaVe _muave = new MuaVe();
        _muave.show();
        dispose();
    }//GEN-LAST:event_btnBackGioHangTrongMouseClicked

    //Evenet click button cộng số lượng vé cho vé 3000 đồng
    private void btnCongSLVe3000DongMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnCongSLVe3000DongMouseClicked
        // TODO add your handling code here:
        slVe3000Dong = slVe3000Dong + 1;
        txtSLVe3000Dong.setText(String.valueOf(slVe3000Dong));
        setTongTienThanhToan();
        setTongTienText();
    }//GEN-LAST:event_btnCongSLVe3000DongMouseClicked

    //Evenet click button trừ số lượng vé cho vé 3000 đồng
    //Nếu số lượng vé là 1 thì phương thức này sẽ không được thực hiện
    private void btnTruSLVe3000DongMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnTruSLVe3000DongMouseClicked
        // TODO add your handling code here:
        if (slVe3000Dong > 1) {
            slVe3000Dong = slVe3000Dong - 1;
            txtSLVe3000Dong.setText(String.valueOf(slVe3000Dong));
            setTongTienThanhToan();
            setTongTienText();

        }
    }//GEN-LAST:event_btnTruSLVe3000DongMouseClicked

    private void btnCongSLVe2000DongMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnCongSLVe2000DongMouseClicked
        // TODO add your handling code here:
        slVe2000Dong = slVe2000Dong + 1;
        txtSLVe2000Dong.setText(String.valueOf(slVe2000Dong));
        setTongTienThanhToan();
        setTongTienText();

    }//GEN-LAST:event_btnCongSLVe2000DongMouseClicked

    private void btnTruSLVe2000DongMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnTruSLVe2000DongMouseClicked
        // TODO add your handling code here:
        if (slVe2000Dong > 1) {
            slVe2000Dong = slVe2000Dong - 1;
            txtSLVe2000Dong.setText(String.valueOf(slVe2000Dong));
            setTongTienThanhToan();
            setTongTienText();

        }
    }//GEN-LAST:event_btnTruSLVe2000DongMouseClicked

    private void btnCongSLVe25000DongMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnCongSLVe25000DongMouseClicked
        // TODO add your handling code here:
        slVe25000Dong = slVe25000Dong + 1;
        txtSLVe25000Dong.setText(String.valueOf(slVe25000Dong));
        setTongTienThanhToan();
        setTongTienText();

    }//GEN-LAST:event_btnCongSLVe25000DongMouseClicked

    private void btnTruSLVe25000DongMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnTruSLVe25000DongMouseClicked
        // TODO add your handling code here:
        if (slVe25000Dong > 1) {
            slVe25000Dong = slVe25000Dong - 1;
            txtSLVe25000Dong.setText(String.valueOf(slVe25000Dong));
            setTongTienThanhToan();
            setTongTienText();

        }
    }//GEN-LAST:event_btnTruSLVe25000DongMouseClicked

    //Event click button thanh toán giỏ hàng
    private void btnThanhToanGioHangMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnThanhToanGioHangMouseClicked
        // TODO add your handling code here:
        // TODO add your handling code here:
        System.out.println("Tong tien thanh toan: " + pTongTienThanhToan);
        System.out.println("Tong tien trong vi nguoi dung: " + tempTienGioHang);
        //Nếu tiền thanh toán bé hơn hoặc bằng số tiền mà người dùng có thì cho phép thanh toán
        if (pTongTienThanhToan <= tempTienGioHang) {

            tempTienGioHang = tempTienGioHang - pTongTienThanhToan;

//            System.out.println("Tong tien thanh toan con lai: " + pTongTienThanhToan);
//            System.out.println("Temp Tien Gio Hang con lai: " + tempTienGioHang);
//            System.out.println("Thanh toan thanh cong");
            kh.setLongSoDu(tempTienGioHang);
            try {
                //Cập nhật dữ liệu
                khachhangtbl.sua(kh);
                //Hiện ra thông báo thanh toán thành công.
                JOptionPane.showMessageDialog(null, "Thanh toán thành công, "
                        + "số dư còn lại của bạn là: " + kh.getLongSoDu() + "đ");

                /*
                 * Tạo hóa đơn mới cho dữ liệu vừa được thêm vào
                 * Hóa đơn thêm vào gồm các trường Mã hóa đơn, tăng tự động, 
                  Mã khách hàng, ngày hóa đơn(Ngày hiện tại, sysdate) tổng trị giá của hóa đơn
                 * 
                 */
                System.out.println("Tổng số tiền cần thanh toán " + pTongTienThanhToan);
                System.out.println("Số lượng vé của LVE01 - Vé lượt xe máy - 3000đ là: " + slVe3000Dong);
                System.out.println("Số lượng vé của LVE02 - Vé lượt xe đạp - 2000đ là: " + slVe2000Dong);
                System.out.println("Số lượng vé của LVE03 - Vé tuần - 25000đ là: " + slVe25000Dong);
                System.out.println("Số lượng vé của LVE01 - Vé tháng - 95000đ là: " + slVe95000Dong);
                System.out.println("Mã khách hàng hiện tại là: " + kh.getStrMaKH());

                //Tạo mã hóa đơn mới cho bảng HOADONMUAVE
                //Lấy ra người dùng có mã max để từ đó chèn người tiếp theo vào
                String maHD = hdmuavetbl.getMaxMaHD();
                HDMuaVeDTO hd = new HDMuaVeDTO(maHD, kh.getStrMaKH(), getDateThoiGianThuc(), pTongTienThanhToan);
                hdmuavetbl.them(hd);

                /**
                 * Tạo CTHD mới Nếu số lượng của loại vé > 0 thì tạo CTHD mới
                 */
                if (slVe2000Dong > 0) {
                    CTHDMuaVeDTO cthd = new CTHDMuaVeDTO(maHD, "LVE02", slVe2000Dong);
                    cthdmuavetbl.them(cthd);
                    for (int i = 0; i < slVe2000Dong; i++) {
                        String maVe = vetbl.getMaxMaVe();
                        VeDTO ve = new VeDTO(maVe, "LVE02", kh.getStrMaKH(), "", "", "Chưa kích hoạt");
                        vetbl.them(ve);
                    }
                }
                if (slVe3000Dong > 0) {
                    CTHDMuaVeDTO cthd = new CTHDMuaVeDTO(maHD, "LVE01", slVe3000Dong);
                    cthdmuavetbl.them(cthd);
                    for (int i = 0; i < slVe3000Dong; i++) {
                        String maVe = vetbl.getMaxMaVe();
                        VeDTO ve = new VeDTO(maVe, "LVE01", kh.getStrMaKH(), "", "", "Chưa kích hoạt");
                        vetbl.them(ve);
                    }
                }
                if (slVe25000Dong > 0) {
                    CTHDMuaVeDTO cthd = new CTHDMuaVeDTO(maHD, "LVE03", slVe25000Dong);
                    cthdmuavetbl.them(cthd);
                    for (int i = 0; i < slVe25000Dong; i++) {
                        String maVe = vetbl.getMaxMaVe();
                        VeDTO ve = new VeDTO(maVe, "LVE03", kh.getStrMaKH(), "", "", "Chưa kích hoạt");
                        vetbl.them(ve);
                    }
                }
                if (slVe95000Dong > 0) {
                    CTHDMuaVeDTO cthd = new CTHDMuaVeDTO(maHD, "LVE04", slVe95000Dong);
                    cthdmuavetbl.them(cthd);
                    for (int i = 0; i < slVe95000Dong; i++) {
                        String maVe = vetbl.getMaxMaVe();
                        VeDTO ve = new VeDTO(maVe, "LVE04", kh.getStrMaKH(), "", "", "Chưa kích hoạt");
                        vetbl.them(ve);
                    }
                }

                /**
                 * Cập nhật tổng tiền về 0 Cập nhật số lượng vé, xóa các loại vé
                 * còn trong giỏ hàng
                 */
                //Cập nhật lại tổng tiền thanh toán về 0.
                pTongTienThanhToan = 0;
                txtTongTienThanhToan.setText(String.valueOf(pTongTienThanhToan) + "đ");
                //Xóa các loại vé trong giỏ hàng.
                xoaSLVe();
                //Set số lượng các loại vé về 0
                setSLVe0();

            } catch (Exception ex) {
                Logger.getLogger(GioHang.class.getName()).log(Level.SEVERE, null, ex);
            }
        } else {//Ngược lại thì thông báo cho người dùng việc thanh toán thất bại.
            JOptionPane.showMessageDialog(null, "Số dư của khách hàng là: " + kh.getLongSoDu()
                    + "đ, không đủ để thanh toán. Vui lòng nạp tiền thêm!!!");
        }
    }//GEN-LAST:event_btnThanhToanGioHangMouseClicked

    //Evenet click button trừ số lượng vé cho vé 2000 đồng
    //Nếu số lượng vé là 1 thì phương thức này sẽ không được thực hiện
    //Event click button xóa loại vé 2000 đồng ra khỏi giỏ hàng
    private void btnXoaVe2000DongMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnXoaVe2000DongMouseClicked
        // TODO add your handling code here:
        panelChiTietVe2000Dong.setVisible(false);
        slVe2000Dong = 0;
        setTongTienThanhToan();
        slVeGioHang--;
        setTongTienText();

    }//GEN-LAST:event_btnXoaVe2000DongMouseClicked

    //Event click button xóa loại vé 25000 đồng ra khỏi giỏ hàng
    private void btnXoaVe25000DongMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnXoaVe25000DongMouseClicked
        // TODO add your handling code here:
        panelChiTietVe25000Dong.setVisible(false);
        slVe25000Dong = 0;
        setTongTienThanhToan();
        slVeGioHang--;
        setTongTienText();

    }//GEN-LAST:event_btnXoaVe25000DongMouseClicked

    //Event click button xóa loại vé 95000 đồng ra khỏi giỏ hàng
    private void btnXoaVe95000DongMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnXoaVe95000DongMouseClicked
        // TODO add your handling code here:
        panelChiTietVe95000Dong.setVisible(false);
        slVe95000Dong = 0;
        setTongTienThanhToan();
        slVeGioHang--;
    }//GEN-LAST:event_btnXoaVe95000DongMouseClicked

    private void btnXoaVe2000DongActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnXoaVe2000DongActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_btnXoaVe2000DongActionPerformed

    private void btnXoaVe3000DongActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnXoaVe3000DongActionPerformed
        // TODO add your handling code here:
        panelChiTietVe3000Dong.setVisible(false);
        slVe3000Dong = 0;
        setTongTienThanhToan();
        slVeGioHang--;
        setTongTienText();

    }//GEN-LAST:event_btnXoaVe3000DongActionPerformed

    private void btnXoaVe25000DongActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnXoaVe25000DongActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_btnXoaVe25000DongActionPerformed

    private void btnTruSLVe95000DongMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnTruSLVe95000DongMouseClicked
        // TODO add your handling code here:
        if (slVe95000Dong > 1) {
            slVe95000Dong = slVe95000Dong - 1;
            txtSLVe95000Dong.setText(String.valueOf(slVe95000Dong));
            setTongTienThanhToan();
            setTongTienText();

        }
    }//GEN-LAST:event_btnTruSLVe95000DongMouseClicked

    private void btnCongSLVe95000DongMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnCongSLVe95000DongMouseClicked
        // TODO add your handling code here:
        slVe95000Dong = slVe95000Dong + 1;
        txtSLVe95000Dong.setText(String.valueOf(slVe95000Dong));
        setTongTienThanhToan();
        setTongTienText();
    }//GEN-LAST:event_btnCongSLVe95000DongMouseClicked

    private void btnXoaVe95000DongActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnXoaVe95000DongActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_btnXoaVe95000DongActionPerformed

    private void btnThanhToanGioHangMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnThanhToanGioHangMouseEntered
        btnThanhToanGioHang.setBackground(new Color(200, 148, 34));

    }//GEN-LAST:event_btnThanhToanGioHangMouseEntered

    private void btnThanhToanGioHangMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnThanhToanGioHangMouseExited
        btnThanhToanGioHang.setBackground(new Color(243, 148, 34));
    }//GEN-LAST:event_btnThanhToanGioHangMouseExited

    private void btnBackGioHangMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnBackGioHangMouseEntered
        btnBackGioHang.setBackground(new Color(200, 148, 34));

    }//GEN-LAST:event_btnBackGioHangMouseEntered

    private void btnBackGioHangMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnBackGioHangMouseExited
        btnBackGioHang.setBackground(new Color(243, 148, 34));

    }//GEN-LAST:event_btnBackGioHangMouseExited

    private void btnXoaVe3000DongMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnXoa3000DongMouseClicked
        panelChiTietVe3000Dong.setVisible(false);
        slVe3000Dong = 0;
        setTongTienThanhToan();
        setTongTienText();

    }//GEN-LAST:event_btnXoa3000DongMouseClicked

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
            java.util.logging.Logger.getLogger(GioHang.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(GioHang.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(GioHang.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(GioHang.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(GioHang.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(GioHang.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(GioHang.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(GioHang.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                try {
                    new GioHang().setVisible(true);
                } catch (Exception ex) {
                    Logger.getLogger(GioHang.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JPanel PanelEmpty;
    private javax.swing.JPanel PanelGioHang;
    private javax.swing.JLabel Title;
    private javax.swing.JButton btnBackGioHang;
    private javax.swing.JButton btnBackGioHangTrong;
    private javax.swing.JButton btnCongSLVe2000Dong;
    private javax.swing.JButton btnCongSLVe25000Dong;
    private javax.swing.JButton btnCongSLVe3000Dong;
    private javax.swing.JButton btnCongSLVe95000Dong;
    private javax.swing.JButton btnThanhToanGioHang;
    private javax.swing.JButton btnTruSLVe2000Dong;
    private javax.swing.JButton btnTruSLVe25000Dong;
    private javax.swing.JButton btnTruSLVe3000Dong;
    private javax.swing.JButton btnTruSLVe95000Dong;
    private javax.swing.JButton btnXoaVe2000Dong;
    private javax.swing.JButton btnXoaVe25000Dong;
    private javax.swing.JButton btnXoaVe3000Dong;
    private javax.swing.JButton btnXoaVe95000Dong;
    private javax.swing.JLabel empty;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel14;
    private javax.swing.JLabel jLabel15;
    private javax.swing.JLabel jLabel17;
    private javax.swing.JLabel jLabel18;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel20;
    private javax.swing.JLabel jLabel21;
    private javax.swing.JLabel jLabel22;
    private javax.swing.JLabel jLabel24;
    private javax.swing.JLabel jLabel25;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel30;
    private javax.swing.JLabel jLabel32;
    private javax.swing.JLabel jLabel33;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel labelgiohangtrong;
    private Admin.PanelRound panelChiTietVe2000Dong;
    private Admin.PanelRound panelChiTietVe25000Dong;
    private Admin.PanelRound panelChiTietVe3000Dong;
    private Admin.PanelRound panelChiTietVe95000Dong;
    private Admin.PanelRound panelThanhToan;
    private Admin.PanelRound panelTiTle;
    private javax.swing.JPanel panelTitle;
    private javax.swing.JButton txtSLVe2000Dong;
    private javax.swing.JButton txtSLVe25000Dong;
    private javax.swing.JButton txtSLVe3000Dong;
    private javax.swing.JButton txtSLVe95000Dong;
    private javax.swing.JLabel txtTongCongVe2000Dong;
    private javax.swing.JLabel txtTongCongVe25000Dong;
    private javax.swing.JLabel txtTongCongVe3000Dong;
    private javax.swing.JLabel txtTongCongVe95000Dong;
    private javax.swing.JLabel txtTongTienThanhToan;
    // End of variables declaration//GEN-END:variables

    private void setIconImage() {
        setIconImage(Toolkit.getDefaultToolkit().getImage(getClass().getResource("/icon/parking.png")));
    }
}
