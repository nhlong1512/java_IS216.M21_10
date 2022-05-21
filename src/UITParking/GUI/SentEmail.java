/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package UITParking.GUI;

import java.awt.*;
import UITParking.GUI.OTPform;
import javax.swing.JOptionPane;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import javax.swing.JOptionPane;
import javax.mail.*;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import java.util.Properties;

/**
 *
 * @author @author Pham Hoang Ngoc Anh
 */
public class SentEmail extends javax.swing.JFrame {

    Connection conn = null;
    PreparedStatement ps = null;
    ResultSet rs = null;

    /**
     * Creates new form SentEmail
     */
    public SentEmail() {
        initComponents();
        setIconImage();

    }

    public static int randomOTP() {
        double randomDouble = Math.random();
        randomDouble = randomDouble * 1000000 + 1;
        int randomInt = (int) randomDouble;
        return randomInt;
    }
    public static int pRandomOTP;
    public static String pEmailSentEmail;

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
        jLabel2 = new javax.swing.JLabel();
        txtEmailModified = new javax.swing.JTextField();
        jLabel3 = new javax.swing.JLabel();
        btnGetOTP = new javax.swing.JButton();
        btnBackFormEmail = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("UIT Parking");

        jPanel1.setBackground(new java.awt.Color(255, 255, 255));

        jLabel1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icon/a.png"))); // NOI18N

        jLabel2.setFont(new java.awt.Font("Segoe UI Historic", 1, 25)); // NOI18N
        jLabel2.setForeground(new java.awt.Color(0, 51, 204));
        jLabel2.setText("OTP Verification");

        txtEmailModified.setForeground(new java.awt.Color(153, 153, 153));
        txtEmailModified.setText(" abc@gmail.com");
        txtEmailModified.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(0, 0, 0), 1, true));
        txtEmailModified.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                txtEmailModifiedFocusGained(evt);
            }
            public void focusLost(java.awt.event.FocusEvent evt) {
                txtEmailModifiedFocusLost(evt);
            }
        });
        txtEmailModified.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtEmailModifiedActionPerformed(evt);
            }
        });

        jLabel3.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jLabel3.setForeground(new java.awt.Color(0, 51, 204));
        jLabel3.setText("We will send you OTP to this email");

        btnGetOTP.setBackground(new java.awt.Color(12, 33, 250));
        btnGetOTP.setFont(new java.awt.Font("Segoe UI Emoji", 1, 14)); // NOI18N
        btnGetOTP.setForeground(new java.awt.Color(255, 255, 255));
        btnGetOTP.setText("Get OTP");
        btnGetOTP.setBorder(new javax.swing.border.SoftBevelBorder(javax.swing.border.BevelBorder.RAISED));
        btnGetOTP.setBorderPainted(false);
        btnGetOTP.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                btnGetOTPMouseClicked(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                btnGetOTPMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                btnGetOTPMouseExited(evt);
            }
        });
        btnGetOTP.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnGetOTPActionPerformed(evt);
            }
        });

        btnBackFormEmail.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icon/back-button.png"))); // NOI18N
        btnBackFormEmail.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btnBackFormEmail.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                btnBackFormEmailMouseClicked(evt);
            }
        });

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(88, 88, 88)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel2)
                            .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 208, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel3)))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(116, 116, 116)
                        .addComponent(btnGetOTP, javax.swing.GroupLayout.PREFERRED_SIZE, 133, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addGap(0, 61, Short.MAX_VALUE)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                        .addComponent(txtEmailModified, javax.swing.GroupLayout.PREFERRED_SIZE, 265, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(50, 50, 50))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                        .addComponent(btnBackFormEmail)
                        .addGap(14, 14, 14))))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(btnBackFormEmail)
                .addGap(2, 2, 2)
                .addComponent(jLabel1)
                .addGap(18, 18, 18)
                .addComponent(jLabel2)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jLabel3)
                .addGap(18, 18, 18)
                .addComponent(txtEmailModified, javax.swing.GroupLayout.PREFERRED_SIZE, 41, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(btnGetOTP, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(30, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void txtEmailModifiedFocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_txtEmailModifiedFocusGained
        // TODO add your handling code here:
        if (txtEmailModified.getText().equals(" abc@gmail.com")) {
            txtEmailModified.setText("");
            txtEmailModified.setForeground(new Color(153, 153, 153));
        }
    }//GEN-LAST:event_txtEmailModifiedFocusGained

    private void txtEmailModifiedFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_txtEmailModifiedFocusLost
        // TODO add your handling code here:
        if (txtEmailModified.getText().equals("")) {
            txtEmailModified.setText(" abc@gmail.com");
            txtEmailModified.setForeground(new Color(153, 153, 153));
        }
    }//GEN-LAST:event_txtEmailModifiedFocusLost

    private void txtEmailModifiedActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtEmailModifiedActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtEmailModifiedActionPerformed

    private void btnBackFormEmailMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnBackFormEmailMouseClicked
        login _login = new login();
        _login.show();
        dispose();
    }//GEN-LAST:event_btnBackFormEmailMouseClicked

    private void btnGetOTPActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnGetOTPActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_btnGetOTPActionPerformed

    private void btnGetOTPMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnGetOTPMouseClicked
        // TODO add your handling code here:
        try {
            conn = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:orcl", "hr", "hr");
            String sql = "SELECT * FROM NGUOIDUNG WHERE Email = ?";
            ps = conn.prepareStatement(sql);
            ps.setString(1, txtEmailModified.getText());
            rs = ps.executeQuery();
            if (rs.next()) {
                JOptionPane.showMessageDialog(null, "Email chinh xac, vui kiem tra gmail");

                pRandomOTP = randomOTP();
                pEmailSentEmail = txtEmailModified.getText();
                final String username = "uitparking@gmail.com";
                final String password = "UITParking2052~";

                Properties prop = new Properties();
                prop.put("mail.smtp.host", "smtp.gmail.com");
                prop.put("mail.smtp.port", "587");
                prop.put("mail.smtp.auth", "true");
                prop.put("mail.smtp.starttls.enable", "true"); //TLS
                prop.put("mail.smtp.starttls.required", "true"); //
                prop.put("mail.smtp.ssl.protocols", "TLSv1.2"); //

                Session session = Session.getInstance(prop,
                        new javax.mail.Authenticator() {
                    protected PasswordAuthentication getPasswordAuthentication() {
                        return new PasswordAuthentication(username, password);
                    }
                });

                try {

                    Message message = new MimeMessage(session);
                    message.setFrom(new InternetAddress(txtEmailModified.getText()));
                    message.setRecipients(
                            Message.RecipientType.TO,
                            InternetAddress.parse(txtEmailModified.getText())
                    );
                    message.setSubject("VERIFY FORGET PASSWORD");
                    message.setText("OTP To Reset Password: " + String.valueOf(pRandomOTP));

                    Transport.send(message);

                    System.out.println("Done");

                } catch (MessagingException e) {
                    e.printStackTrace();
                }

                OTPform _OTPform = new OTPform();
                _OTPform.show();
                dispose();

            } else {
                JOptionPane.showMessageDialog(null, "Email Address Does Not Exist!");
                txtEmailModified.setText("");
            }

        } catch (Exception ex) {
            JOptionPane.showMessageDialog(null, ex);
        }
    }//GEN-LAST:event_btnGetOTPMouseClicked

    private void btnGetOTPMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnGetOTPMouseEntered
        btnGetOTP.setBackground(new Color(80, 60, 244));
    }//GEN-LAST:event_btnGetOTPMouseEntered

    private void btnGetOTPMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnGetOTPMouseExited
        btnGetOTP.setBackground(new Color(12, 33, 250));
    }//GEN-LAST:event_btnGetOTPMouseExited

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
            java.util.logging.Logger.getLogger(SentEmail.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(SentEmail.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(SentEmail.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(SentEmail.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
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
            java.util.logging.Logger.getLogger(SentEmail.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(SentEmail.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(SentEmail.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(SentEmail.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new SentEmail().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel btnBackFormEmail;
    private javax.swing.JButton btnGetOTP;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JTextField txtEmailModified;
    // End of variables declaration//GEN-END:variables

    private void setIconImage() {
        setIconImage(Toolkit.getDefaultToolkit().getImage(getClass().getResource("/icon/parking.png")));

    }
}
