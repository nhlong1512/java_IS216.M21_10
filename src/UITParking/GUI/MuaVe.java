/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package UITParking.GUI;

import java.awt.Color;
import java.awt.Toolkit;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author ADMIN
 */
public class MuaVe extends javax.swing.JFrame {

    /**
     * Creates new form MuaVe
     */
    public static int slVe3000Dong = 0;
    public static int slVe2000Dong = 0;
    public static int slVe25000Dong = 0;
    public static int slVe95000Dong = 0;
    public static int slVeGioHang = 0;

    public MuaVe() {
        initComponents();
        btnBackMuaVe.setVisible(true);
        setIconImage();
        //set số lượng vé trong icon giỏ hàng
        txtSoLuongVeGioHang.setText(String.valueOf(slVeGioHang));

    }

    public int tangSlVeGioHang() {
        if (slVe2000Dong == 1) {
            slVeGioHang++;
        }
        if (slVe3000Dong == 1) {
            slVeGioHang++;
        }
        if (slVe25000Dong == 1) {
            slVeGioHang++;
        }
        if (slVe95000Dong == 1) {
            slVeGioHang++;
        }
        return slVeGioHang;
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel5 = new javax.swing.JPanel();
        panelRound1 = new Admin.PanelRound();
        jLabel4 = new javax.swing.JLabel();
        jLabel10 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        btnMuaVe3000Dong = new javax.swing.JButton();
        btnBackMuaVe = new javax.swing.JButton();
        panelRound2 = new Admin.PanelRound();
        jLabel11 = new javax.swing.JLabel();
        jLabel12 = new javax.swing.JLabel();
        jLabel13 = new javax.swing.JLabel();
        btnMuaVe2000Dong = new javax.swing.JButton();
        panelRound3 = new Admin.PanelRound();
        jLabel14 = new javax.swing.JLabel();
        jLabel15 = new javax.swing.JLabel();
        jLabel16 = new javax.swing.JLabel();
        btnMuaVe95000Dong = new javax.swing.JButton();
        panelRound4 = new Admin.PanelRound();
        jLabel17 = new javax.swing.JLabel();
        jLabel18 = new javax.swing.JLabel();
        jLabel19 = new javax.swing.JLabel();
        btnMuaVe25000Dong = new javax.swing.JButton();
        txtSoLuongVeGioHang = new javax.swing.JLabel();
        btnXemGioHang = new javax.swing.JButton();
        panelRound5 = new Admin.PanelRound();
        jLabel1 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("UIT Parking");
        getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jPanel5.setBackground(new java.awt.Color(41, 58, 128));
        jPanel5.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        panelRound1.setBackground(new java.awt.Color(255, 255, 255));
        panelRound1.setRoundBottomLeft(50);
        panelRound1.setRoundBottomRight(50);
        panelRound1.setRoundTopLeft(50);
        panelRound1.setRoundTopRight(50);

        jLabel4.setFont(new java.awt.Font("Cooper", 0, 18)); // NOI18N
        jLabel4.setForeground(new java.awt.Color(41, 58, 128));
        jLabel4.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel4.setText("Vé lượt xe máy");

        jLabel10.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icon/imgonline-com-ua-resize-tWkwJvM5UoVj.png"))); // NOI18N

        jLabel5.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        jLabel5.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel5.setText("3.000 đ");

        btnMuaVe3000Dong.setBackground(new java.awt.Color(255, 204, 51));
        btnMuaVe3000Dong.setFont(new java.awt.Font("Cooper", 0, 18)); // NOI18N
        btnMuaVe3000Dong.setText("Mua Vé");
        btnMuaVe3000Dong.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btnMuaVe3000Dong.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                btnMuaVe3000DongMouseClicked(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                btnMuaVe3000DongMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                btnMuaVe3000DongMouseExited(evt);
            }
        });

        javax.swing.GroupLayout panelRound1Layout = new javax.swing.GroupLayout(panelRound1);
        panelRound1.setLayout(panelRound1Layout);
        panelRound1Layout.setHorizontalGroup(
            panelRound1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jLabel10, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, panelRound1Layout.createSequentialGroup()
                .addGap(0, 0, Short.MAX_VALUE)
                .addComponent(btnMuaVe3000Dong, javax.swing.GroupLayout.PREFERRED_SIZE, 104, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(45, 45, 45))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, panelRound1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
            .addComponent(jLabel5, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        panelRound1Layout.setVerticalGroup(
            panelRound1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelRound1Layout.createSequentialGroup()
                .addGap(13, 13, 13)
                .addComponent(jLabel4, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel10, javax.swing.GroupLayout.PREFERRED_SIZE, 97, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel5, javax.swing.GroupLayout.PREFERRED_SIZE, 22, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(btnMuaVe3000Dong, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(7, Short.MAX_VALUE))
        );

        jPanel5.add(panelRound1, new org.netbeans.lib.awtextra.AbsoluteConstraints(60, 90, 200, 210));

        btnBackMuaVe.setBackground(new java.awt.Color(243, 148, 34));
        btnBackMuaVe.setFont(new java.awt.Font("Cooper", 0, 18)); // NOI18N
        btnBackMuaVe.setForeground(new java.awt.Color(255, 255, 255));
        btnBackMuaVe.setText("Trang chủ");
        btnBackMuaVe.setBorderPainted(false);
        btnBackMuaVe.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btnBackMuaVe.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                btnBackMuaVeMouseClicked(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                btnBackMuaVeMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                btnBackMuaVeMouseExited(evt);
            }
        });
        btnBackMuaVe.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnBackMuaVeActionPerformed(evt);
            }
        });
        jPanel5.add(btnBackMuaVe, new org.netbeans.lib.awtextra.AbsoluteConstraints(570, 490, 140, 40));

        panelRound2.setBackground(new java.awt.Color(255, 255, 255));
        panelRound2.setRoundBottomLeft(50);
        panelRound2.setRoundBottomRight(50);
        panelRound2.setRoundTopLeft(50);
        panelRound2.setRoundTopRight(50);

        jLabel11.setFont(new java.awt.Font("Cooper", 0, 18)); // NOI18N
        jLabel11.setForeground(new java.awt.Color(41, 58, 128));
        jLabel11.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel11.setText("Vé lượt xe đạp");

        jLabel12.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icon/vé xe đạp.png"))); // NOI18N

        jLabel13.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        jLabel13.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel13.setText("2.000 đ");

        btnMuaVe2000Dong.setBackground(new java.awt.Color(255, 204, 51));
        btnMuaVe2000Dong.setFont(new java.awt.Font("Cooper", 0, 18)); // NOI18N
        btnMuaVe2000Dong.setText("Mua Vé");
        btnMuaVe2000Dong.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btnMuaVe2000Dong.setPreferredSize(new java.awt.Dimension(101, 23));
        btnMuaVe2000Dong.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                btnMuaVe2000DongMouseClicked(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                btnMuaVe2000DongMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                btnMuaVe2000DongMouseExited(evt);
            }
        });

        javax.swing.GroupLayout panelRound2Layout = new javax.swing.GroupLayout(panelRound2);
        panelRound2.setLayout(panelRound2Layout);
        panelRound2Layout.setHorizontalGroup(
            panelRound2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelRound2Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel11, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
            .addComponent(jLabel12, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, panelRound2Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(btnMuaVe2000Dong, javax.swing.GroupLayout.PREFERRED_SIZE, 104, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(46, 46, 46))
            .addComponent(jLabel13, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        panelRound2Layout.setVerticalGroup(
            panelRound2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelRound2Layout.createSequentialGroup()
                .addGap(13, 13, 13)
                .addComponent(jLabel11, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel12, javax.swing.GroupLayout.PREFERRED_SIZE, 97, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel13, javax.swing.GroupLayout.PREFERRED_SIZE, 22, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(btnMuaVe2000Dong, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(7, Short.MAX_VALUE))
        );

        jPanel5.add(panelRound2, new org.netbeans.lib.awtextra.AbsoluteConstraints(310, 90, 200, 210));

        panelRound3.setBackground(new java.awt.Color(255, 255, 255));
        panelRound3.setRoundBottomLeft(50);
        panelRound3.setRoundBottomRight(50);
        panelRound3.setRoundTopLeft(50);
        panelRound3.setRoundTopRight(50);

        jLabel14.setFont(new java.awt.Font("Cooper", 0, 18)); // NOI18N
        jLabel14.setForeground(new java.awt.Color(41, 58, 128));
        jLabel14.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel14.setText("Vé tháng");

        jLabel15.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icon/vé tháng.png"))); // NOI18N

        jLabel16.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        jLabel16.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel16.setText("95.000 đ");

        btnMuaVe95000Dong.setBackground(new java.awt.Color(255, 204, 51));
        btnMuaVe95000Dong.setFont(new java.awt.Font("Cooper", 0, 18)); // NOI18N
        btnMuaVe95000Dong.setText("Mua Vé");
        btnMuaVe95000Dong.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btnMuaVe95000Dong.setPreferredSize(new java.awt.Dimension(104, 28));
        btnMuaVe95000Dong.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                btnMuaVe95000DongMouseClicked(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                btnMuaVe95000DongMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                btnMuaVe95000DongMouseExited(evt);
            }
        });

        javax.swing.GroupLayout panelRound3Layout = new javax.swing.GroupLayout(panelRound3);
        panelRound3.setLayout(panelRound3Layout);
        panelRound3Layout.setHorizontalGroup(
            panelRound3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelRound3Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel14, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
            .addComponent(jLabel15, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(jLabel16, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, panelRound3Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(btnMuaVe95000Dong, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(46, 46, 46))
        );
        panelRound3Layout.setVerticalGroup(
            panelRound3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelRound3Layout.createSequentialGroup()
                .addGap(13, 13, 13)
                .addComponent(jLabel14, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel15, javax.swing.GroupLayout.PREFERRED_SIZE, 97, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel16, javax.swing.GroupLayout.PREFERRED_SIZE, 22, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(btnMuaVe95000Dong, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(7, Short.MAX_VALUE))
        );

        jPanel5.add(panelRound3, new org.netbeans.lib.awtextra.AbsoluteConstraints(310, 320, -1, 210));

        panelRound4.setBackground(new java.awt.Color(255, 255, 255));
        panelRound4.setRoundBottomLeft(50);
        panelRound4.setRoundBottomRight(50);
        panelRound4.setRoundTopLeft(50);
        panelRound4.setRoundTopRight(50);

        jLabel17.setFont(new java.awt.Font("Cooper", 0, 18)); // NOI18N
        jLabel17.setForeground(new java.awt.Color(41, 58, 128));
        jLabel17.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel17.setText("Vé tuần");

        jLabel18.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icon/vé tuần.png"))); // NOI18N

        jLabel19.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        jLabel19.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel19.setText("25.000 đ");

        btnMuaVe25000Dong.setBackground(new java.awt.Color(255, 204, 51));
        btnMuaVe25000Dong.setFont(new java.awt.Font("Cooper", 0, 18)); // NOI18N
        btnMuaVe25000Dong.setText("Mua Vé");
        btnMuaVe25000Dong.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btnMuaVe25000Dong.setPreferredSize(new java.awt.Dimension(104, 28));
        btnMuaVe25000Dong.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                btnMuaVe25000DongMouseClicked(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                btnMuaVe25000DongMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                btnMuaVe25000DongMouseExited(evt);
            }
        });

        javax.swing.GroupLayout panelRound4Layout = new javax.swing.GroupLayout(panelRound4);
        panelRound4.setLayout(panelRound4Layout);
        panelRound4Layout.setHorizontalGroup(
            panelRound4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelRound4Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel17, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
            .addComponent(jLabel18, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(jLabel19, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, panelRound4Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(btnMuaVe25000Dong, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(45, 45, 45))
        );
        panelRound4Layout.setVerticalGroup(
            panelRound4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelRound4Layout.createSequentialGroup()
                .addGap(14, 14, 14)
                .addComponent(jLabel17, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel18, javax.swing.GroupLayout.PREFERRED_SIZE, 97, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel19, javax.swing.GroupLayout.PREFERRED_SIZE, 22, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(btnMuaVe25000Dong, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jPanel5.add(panelRound4, new org.netbeans.lib.awtextra.AbsoluteConstraints(60, 320, -1, 210));

        txtSoLuongVeGioHang.setFont(new java.awt.Font("Segoe UI Black", 0, 18)); // NOI18N
        txtSoLuongVeGioHang.setText("1");
        jPanel5.add(txtSoLuongVeGioHang, new org.netbeans.lib.awtextra.AbsoluteConstraints(650, 10, 20, 30));

        btnXemGioHang.setBackground(new java.awt.Color(41, 58, 128));
        btnXemGioHang.setFont(new java.awt.Font("Cooper", 0, 18)); // NOI18N
        btnXemGioHang.setForeground(new java.awt.Color(255, 255, 255));
        btnXemGioHang.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icon/icons8_shopping_cart_96px.png"))); // NOI18N
        btnXemGioHang.setBorder(null);
        btnXemGioHang.setContentAreaFilled(false);
        btnXemGioHang.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btnXemGioHang.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                btnXemGioHangMouseClicked(evt);
            }
        });
        jPanel5.add(btnXemGioHang, new org.netbeans.lib.awtextra.AbsoluteConstraints(580, 10, 90, 70));

        panelRound5.setBackground(new java.awt.Color(255, 255, 255));
        panelRound5.setRoundBottomLeft(50);
        panelRound5.setRoundBottomRight(50);
        panelRound5.setRoundTopLeft(50);
        panelRound5.setRoundTopRight(50);

        javax.swing.GroupLayout panelRound5Layout = new javax.swing.GroupLayout(panelRound5);
        panelRound5.setLayout(panelRound5Layout);
        panelRound5Layout.setHorizontalGroup(
            panelRound5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 30, Short.MAX_VALUE)
        );
        panelRound5Layout.setVerticalGroup(
            panelRound5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 30, Short.MAX_VALUE)
        );

        jPanel5.add(panelRound5, new org.netbeans.lib.awtextra.AbsoluteConstraints(640, 10, 30, 30));

        jLabel1.setFont(new java.awt.Font("Cooper", 0, 36)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(243, 148, 34));
        jLabel1.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel1.setText("TICKET SELECTION");
        jPanel5.add(jLabel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 20, 510, 50));

        getContentPane().add(jPanel5, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 750, 570));

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    //Event click button Mua Vé 3000 đồng
    private void btnMuaVe3000DongMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnMuaVe3000DongMouseClicked
        // TODO add your handling code here:
        //Tăng số lượng vé 3000 Đồng lên 1 nếu click vào button mua vé 3000 đồng
        slVe3000Dong = slVe3000Dong + 1;
        if (slVe3000Dong == 1) {
            slVeGioHang++;
            txtSoLuongVeGioHang.setText(String.valueOf(slVeGioHang));
        }
    }//GEN-LAST:event_btnMuaVe3000DongMouseClicked

    //Event click button Mua Vé 2000 đồng
    private void btnMuaVe2000DongMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnMuaVe2000DongMouseClicked
        // TODO add your handling code here:
        //Tăng số lượng vé 2000 Đồng lên 1 nếu click vào button mua vé 2000 đồng
        slVe2000Dong = slVe2000Dong + 1;
        if (slVe2000Dong == 1) {
            slVeGioHang++;
            txtSoLuongVeGioHang.setText(String.valueOf(slVeGioHang));
        }
    }//GEN-LAST:event_btnMuaVe2000DongMouseClicked

    //Event click button Mua Vé 25000 đồng
    private void btnMuaVe25000DongMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnMuaVe25000DongMouseClicked
        // TODO add your handling code here:
        //Tăng số lượng vé 25000 Đồng lên 1 nếu click vào button mua vé 25000 đồng
        slVe25000Dong = slVe25000Dong + 1;
        if (slVe25000Dong == 1) {
            slVeGioHang++;
            txtSoLuongVeGioHang.setText(String.valueOf(slVeGioHang));
        }

    }//GEN-LAST:event_btnMuaVe25000DongMouseClicked

    //Event click button Xem giỏ hàng
    private void btnXemGioHangMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnXemGioHangMouseClicked
        // TODO add your handling code here:
        GioHang _gioHang = null;
        try {
            _gioHang = new GioHang();
        } catch (Exception ex) {
            Logger.getLogger(MuaVe.class.getName()).log(Level.SEVERE, null, ex);
        }
        _gioHang.show();
        dispose();
    }//GEN-LAST:event_btnXemGioHangMouseClicked

    private void btnBackMuaVeActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnBackMuaVeActionPerformed


    }//GEN-LAST:event_btnBackMuaVeActionPerformed

    private void btnMuaVe95000DongMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnMuaVe95000DongMouseClicked
        slVe95000Dong = slVe95000Dong + 1;
        if(slVe95000Dong == 1) {
            slVeGioHang ++;
            txtSoLuongVeGioHang.setText(String.valueOf(slVeGioHang));
        }

    }//GEN-LAST:event_btnMuaVe95000DongMouseClicked

    private void btnBackMuaVeMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnBackMuaVeMouseEntered
        btnBackMuaVe.setBackground(new Color(200, 148, 34));
    }//GEN-LAST:event_btnBackMuaVeMouseEntered

    private void btnBackMuaVeMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnBackMuaVeMouseClicked
        Homepage _homepage = null;
        try {
            _homepage = new Homepage();
        } catch (Exception ex) {
            Logger.getLogger(MuaVe.class.getName()).log(Level.SEVERE, null, ex);
        }
        _homepage.show();
        dispose();
    }//GEN-LAST:event_btnBackMuaVeMouseClicked

    private void btnBackMuaVeMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnBackMuaVeMouseExited
        btnBackMuaVe.setBackground(new Color(243, 148, 34));
    }//GEN-LAST:event_btnBackMuaVeMouseExited

    private void btnMuaVe3000DongMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnMuaVe3000DongMouseEntered
        // TODO add your handling code here:
        btnMuaVe3000Dong.setBackground(new Color(230, 204, 51));
    }//GEN-LAST:event_btnMuaVe3000DongMouseEntered

    private void btnMuaVe3000DongMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnMuaVe3000DongMouseExited
        // TODO add your handling code here:
        btnMuaVe3000Dong.setBackground(new Color(255, 204, 51));
    }//GEN-LAST:event_btnMuaVe3000DongMouseExited

    private void btnMuaVe2000DongMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnMuaVe2000DongMouseEntered
        // TODO add your handling code here:
        btnMuaVe2000Dong.setBackground(new Color(230, 204, 51));

    }//GEN-LAST:event_btnMuaVe2000DongMouseEntered

    private void btnMuaVe2000DongMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnMuaVe2000DongMouseExited
        // TODO add your handling code here:
        btnMuaVe2000Dong.setBackground(new Color(255, 204, 51));

    }//GEN-LAST:event_btnMuaVe2000DongMouseExited

    private void btnMuaVe25000DongMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnMuaVe25000DongMouseEntered
        // TODO add your handling code here:
        btnMuaVe25000Dong.setBackground(new Color(230, 204, 51));

    }//GEN-LAST:event_btnMuaVe25000DongMouseEntered

    private void btnMuaVe25000DongMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnMuaVe25000DongMouseExited
        // TODO add your handling code here:
        btnMuaVe25000Dong.setBackground(new Color(255, 204, 51));

    }//GEN-LAST:event_btnMuaVe25000DongMouseExited

    private void btnMuaVe95000DongMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnMuaVe95000DongMouseEntered
        // TODO add your handling code here:
        btnMuaVe95000Dong.setBackground(new Color(230, 204, 51));
    }//GEN-LAST:event_btnMuaVe95000DongMouseEntered

    private void btnMuaVe95000DongMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnMuaVe95000DongMouseExited
        // TODO add your handling code here:
        btnMuaVe95000Dong.setBackground(new Color(255, 204, 51));

    }//GEN-LAST:event_btnMuaVe95000DongMouseExited

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
            java.util.logging.Logger.getLogger(MuaVe.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(MuaVe.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(MuaVe.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(MuaVe.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new MuaVe().setVisible(true);

            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnBackMuaVe;
    private javax.swing.JButton btnMuaVe2000Dong;
    private javax.swing.JButton btnMuaVe25000Dong;
    private javax.swing.JButton btnMuaVe3000Dong;
    private javax.swing.JButton btnMuaVe95000Dong;
    private javax.swing.JButton btnXemGioHang;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel14;
    private javax.swing.JLabel jLabel15;
    private javax.swing.JLabel jLabel16;
    private javax.swing.JLabel jLabel17;
    private javax.swing.JLabel jLabel18;
    private javax.swing.JLabel jLabel19;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JPanel jPanel5;
    private Admin.PanelRound panelRound1;
    private Admin.PanelRound panelRound2;
    private Admin.PanelRound panelRound3;
    private Admin.PanelRound panelRound4;
    private Admin.PanelRound panelRound5;
    private javax.swing.JLabel txtSoLuongVeGioHang;
    // End of variables declaration//GEN-END:variables

    private void setIconImage() {
        setIconImage(Toolkit.getDefaultToolkit().getImage(getClass().getResource("/icon/parking.png")));

    }
}
