/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JPanel.java to edit this template
 */
package UITParking.GUI.Admin;

import UITParking.DAO.Connect;
import UITParking.DAO.SQLConnectUnit;
import UITParking.DAO.SQLConnection;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.util.HashMap;
import java.util.Hashtable;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperCompileManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.engine.design.JasperDesign;
import net.sf.jasperreports.engine.xml.JRXmlLoader;
import net.sf.jasperreports.view.JasperViewer;
import org.jfree.data.category.DefaultCategoryDataset;
import org.jfree.chart.plot.CategoryPlot;
import org.jfree.chart.ChartFactory;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.plot.PlotOrientation;
import java.awt.Color;
import org.jfree.chart.ChartFrame;
import org.jfree.chart.ChartPanel;

/**
 *
 * @author ADMIN
 */
public class BCTKJPanel extends javax.swing.JPanel {

    /**
     * Creates new form BCTKJPanel
     */
    public String selectedItemStr = "";

    public BCTKJPanel() {
        initComponents();
        txtMaHD.setEnabled(false);
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
        jLabel1 = new javax.swing.JLabel();
        cbbLoaiThongKe = new javax.swing.JComboBox<>();
        btnTraCuu = new javax.swing.JButton();
        jLabel2 = new javax.swing.JLabel();
        txtMaHD = new javax.swing.JTextField();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        txtNgay = new javax.swing.JTextField();
        txtThang = new javax.swing.JTextField();
        txtNam = new javax.swing.JTextField();

        jPanel1.setBackground(new java.awt.Color(255, 255, 255));

        jLabel1.setText("Loại thống kê");

        cbbLoaiThongKe.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Doanh thu vé theo ngày", "Doanh thu vé theo tháng", "Doanh thu vé theo năm", "Doanh thu vé khách vãng lai theo tháng", "Doanh thu vé khách vãng lai theo năm", "Thống kê số lượng xe ra vào", "Hóa đơn mua vé" }));
        cbbLoaiThongKe.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                cbbLoaiThongKeItemStateChanged(evt);
            }
        });

        btnTraCuu.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        btnTraCuu.setText("Tra cứu");
        btnTraCuu.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                btnTraCuuMouseClicked(evt);
            }
        });
        btnTraCuu.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnTraCuuActionPerformed(evt);
            }
        });

        jLabel2.setText("Mã hóa đơn");

        jLabel3.setText("Ngày");

        jLabel4.setText("Tháng");

        jLabel5.setText("Năm");

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(108, 108, 108)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 84, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 70, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, 70, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGap(109, 109, 109)
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addComponent(cbbLoaiThongKe, 0, 266, Short.MAX_VALUE)
                                    .addComponent(txtMaHD)
                                    .addComponent(txtNgay)))
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel4, javax.swing.GroupLayout.PREFERRED_SIZE, 70, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jLabel5, javax.swing.GroupLayout.PREFERRED_SIZE, 70, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGap(123, 123, 123)
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addComponent(txtThang, javax.swing.GroupLayout.DEFAULT_SIZE, 266, Short.MAX_VALUE)
                                    .addComponent(txtNam)))))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(258, 258, 258)
                        .addComponent(btnTraCuu, javax.swing.GroupLayout.PREFERRED_SIZE, 97, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(633, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(37, 37, 37)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel1)
                    .addComponent(cbbLoaiThongKe, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(20, 20, 20)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 24, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtMaHD, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(20, 20, 20)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, 19, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtNgay, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(20, 20, 20)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel4, javax.swing.GroupLayout.PREFERRED_SIZE, 19, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtThang, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(20, 20, 20)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel5, javax.swing.GroupLayout.PREFERRED_SIZE, 19, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtNam, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(38, 38, 38)
                .addComponent(btnTraCuu, javax.swing.GroupLayout.PREFERRED_SIZE, 34, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(122, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
    }// </editor-fold>//GEN-END:initComponents

    private void btnTraCuuActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnTraCuuActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_btnTraCuuActionPerformed

    /**
     *
     * @param evt Thực hiện tra cứu với tham số là getSelectedItem()
     */
//     * Doanh thu vé theo ngày
//     * Doanh thu vé theo tháng
//     * Doanh thu vé theo năm
//     * Doanh thu vé khách vãng lai theo ngày
//     * Doanh thu vé khách vãng lai theo tháng
//     * Doanh thu vé khách vãng lai theo năm
//     * Thống kê số lượng xe ra vào
//     * Hóa đơn mua vé

    private void btnTraCuuMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnTraCuuMouseClicked
        // TODO add your handling code here:
//        Connection conn = Connect.getConnect();
        if (cbbLoaiThongKe.getSelectedItem().toString().equals("Doanh thu vé theo ngày")) {
            try {
                System.out.println("Load Report.....");
                HashMap<String, Object> map = new HashMap<String, Object>();
                map.put("Ngay", txtNgay.getText());
                map.put("Thang", txtThang.getText());
                map.put("Nam", txtNam.getText());

                System.out.println("Mapped....");
                JasperReport rpt = JasperCompileManager.compileReport("src\\UITParking\\Report\\DT_Ngay.jrxml");
                System.out.println("Report getted!");

                Connection conn = Connect.getConnect();
                System.out.println("Connected to database!");

                try {
                    JasperPrint p = (JasperPrint) JasperFillManager.fillReport(rpt, map, conn);
                    System.out.println("Report mapped!");
                    JasperViewer.viewReport(p, false);
                } catch (Exception e) {
                    System.out.println("BUG IS" + e);
                }

            } catch (Exception ex) {
                System.out.println("Err is " + ex);
            }
        }
        if (cbbLoaiThongKe.getSelectedItem().toString().equals("Doanh thu vé theo tháng")) {
            try {
                System.out.println("Load Report.....");
                HashMap<String, Object> map = new HashMap<String, Object>();
                map.put("Thang", txtThang.getText());
                map.put("Nam", txtNam.getText());

                System.out.println("Mapped....");
                JasperReport rpt = JasperCompileManager.compileReport("src\\UITParking\\Report\\DT_Thang.jrxml");
                System.out.println("Report getted!");

                Connection conn = Connect.getConnect();
                System.out.println("Connected to database!");

                try {
                    JasperPrint p = (JasperPrint) JasperFillManager.fillReport(rpt, map, conn);
                    System.out.println("Report mapped!");
                    JasperViewer.viewReport(p, false);
                } catch (Exception e) {
                    System.out.println("BUG IS" + e);
                }

            } catch (Exception ex) {
                System.out.println("Err is " + ex);
            }
        }
        if (cbbLoaiThongKe.getSelectedItem().toString().equals("Doanh thu vé theo năm")) {
            try {
                System.out.println("Load Report.....");
                HashMap<String, Object> map = new HashMap<String, Object>();
                map.put("Nam", txtNam.getText());
                System.out.println("Long dep trai");
                System.out.println(txtNam.getText());

                System.out.println("Mapped....");
                JasperReport rpt = JasperCompileManager.compileReport("src\\UITParking\\Report\\DT_Nam.jrxml");
                System.out.println("Report getted!");

                Connection conn = Connect.getConnect();
                System.out.println("Connected to database!");

                try {
                    JasperPrint p = (JasperPrint) JasperFillManager.fillReport(rpt, map, conn);
                    System.out.println("Report mapped!");
                    JasperViewer.viewReport(p, false);
                } catch (Exception e) {
                    System.out.println("BUG IS" + e);
                }

            } catch (Exception ex) {
                System.out.println("Err is " + ex);
            }
        }
        if (cbbLoaiThongKe.getSelectedItem().toString().equals("Doanh thu vé khách vãng lai theo năm")) {
            try {
                System.out.println("Load Report.....");
                HashMap<String, Object> map = new HashMap<String, Object>();
                map.put("Nam", txtNam.getText());

                System.out.println("Mapped....");
                JasperReport rpt = JasperCompileManager.compileReport("src\\UITParking\\Report\\DTVL_Nam.jrxml");
                System.out.println("Report getted!");

                Connection conn = Connect.getConnect();
                System.out.println("Connected to database!");

                try {
                    JasperPrint p = (JasperPrint) JasperFillManager.fillReport(rpt, map, conn);
                    System.out.println("Report mapped!");
                    JasperViewer.viewReport(p, false);
                } catch (Exception e) {
                    System.out.println("BUG IS" + e);
                }

            } catch (Exception ex) {
                System.out.println("Err is " + ex);
            }
        }
        if (cbbLoaiThongKe.getSelectedItem().toString().equals("Doanh thu vé khách vãng lai theo tháng")) {
            try {
                System.out.println("Load Report.....");
                HashMap<String, Object> map = new HashMap<String, Object>();
                map.put("Thang", txtThang.getText());
                map.put("Nam", txtNam.getText());

                System.out.println("Mapped....");
                JasperReport rpt = JasperCompileManager.compileReport("src\\UITParking\\Report\\DTVL_Thang.jrxml");
                System.out.println("Report getted!");

                Connection conn = Connect.getConnect();
                System.out.println("Connected to database!");

                try {
                    JasperPrint p = (JasperPrint) JasperFillManager.fillReport(rpt, map, conn);
                    System.out.println("Report mapped!");
                    JasperViewer.viewReport(p, false);
                } catch (Exception e) {
                    System.out.println("BUG IS" + e);
                }

            } catch (Exception ex) {
                System.out.println("Err is " + ex);
            }
        }
        if (cbbLoaiThongKe.getSelectedItem().toString().equals("Thống kê số lượng xe ra vào")) {
            try {
                System.out.println("Load Report.....");
                HashMap<String, Object> map = new HashMap<String, Object>();
                System.out.println("Mapped....");
                JasperReport rpt = JasperCompileManager.compileReport("src\\UITParking\\Report\\SLX_VaoRa.jrxml");
                System.out.println("Report getted!");

                Connection conn = Connect.getConnect();
                System.out.println("Connected to database!");

                try {
                    JasperPrint p = (JasperPrint) JasperFillManager.fillReport(rpt, null, conn);
                    System.out.println("Report mapped!");
                    JasperViewer.viewReport(p, false);
                } catch (Exception e) {
                    System.out.println("BUG IS" + e);
                }

            } catch (Exception ex) {
                System.out.println("Err is " + ex);
            }
        }
        if (cbbLoaiThongKe.getSelectedItem().toString().equals("Hóa đơn mua vé")) {
            try {
                System.out.println("Load Report.....");
                HashMap<String, Object> map = new HashMap<String, Object>();
                map.put("MAHD", txtMaHD.getText());
                System.out.println("Mapped....");
                JasperReport rpt = JasperCompileManager.compileReport("src\\UITParking\\Report\\HoaDon.jrxml");
                System.out.println("Report getted!");

                Connection conn = Connect.getConnect();
                System.out.println("Connected to database!");

                try {
                    JasperPrint p = (JasperPrint) JasperFillManager.fillReport(rpt, map, conn);
                    System.out.println("Report mapped!");
                    JasperViewer.viewReport(p, false);
                } catch (Exception e) {
                    System.out.println("BUG IS" + e);
                }

            } catch (Exception ex) {
                System.out.println("Err is " + ex);
            }
        }
    }//GEN-LAST:event_btnTraCuuMouseClicked

    /**
     *
     * @param evt Handle event thay đổi giá trị item
     */
    private void cbbLoaiThongKeItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_cbbLoaiThongKeItemStateChanged
        // TODO add your handling code here:
        selectedItemStr = cbbLoaiThongKe.getSelectedItem().toString();
        if (cbbLoaiThongKe.getSelectedItem().toString().equals("Doanh thu vé theo ngày")) {
            txtMaHD.setEnabled(false);
            txtNgay.setEnabled(true);
            txtThang.setEnabled(true);
            txtNam.setEnabled(true);
        }
        if (cbbLoaiThongKe.getSelectedItem().toString().equals("Doanh thu vé theo tháng")) {
            txtMaHD.setEnabled(false);
            txtNgay.setEnabled(false);
            txtThang.setEnabled(true);
            txtNam.setEnabled(true);
        }
        if (cbbLoaiThongKe.getSelectedItem().toString().equals("Doanh thu vé theo năm")) {
            txtMaHD.setEnabled(false);
            txtNgay.setEnabled(false);
            txtThang.setEnabled(false);
            txtNam.setEnabled(true);
        }
        if (cbbLoaiThongKe.getSelectedItem().toString().equals("Doanh thu vé khách vãng lai theo năm")) {
            txtMaHD.setEnabled(false);
            txtNgay.setEnabled(false);
            txtThang.setEnabled(false);
            txtNam.setEnabled(true);
        }
        if (cbbLoaiThongKe.getSelectedItem().toString().equals("Doanh thu vé khách vãng lai theo tháng")) {
            txtMaHD.setEnabled(false);
            txtNgay.setEnabled(false);
            txtThang.setEnabled(true);
            txtNam.setEnabled(true);
        }
        if (cbbLoaiThongKe.getSelectedItem().toString().equals("Thống kê số lượng xe ra vào")) {
            txtMaHD.setEnabled(false);
            txtNgay.setEnabled(false);
            txtThang.setEnabled(false);
            txtNam.setEnabled(false);
        }
        if (cbbLoaiThongKe.getSelectedItem().toString().equals("Hóa đơn mua vé")) {
            txtMaHD.setEnabled(true);
            txtNgay.setEnabled(false);
            txtThang.setEnabled(false);
            txtNam.setEnabled(false);
        }
    }//GEN-LAST:event_cbbLoaiThongKeItemStateChanged


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnTraCuu;
    private javax.swing.JComboBox<String> cbbLoaiThongKe;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JTextField txtMaHD;
    private javax.swing.JTextField txtNam;
    private javax.swing.JTextField txtNgay;
    private javax.swing.JTextField txtThang;
    // End of variables declaration//GEN-END:variables
}
