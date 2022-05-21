/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package UITParking.GUI;

import UITParking.GUI.Homepage;
import UITParking.BUS.NguoiDungBUS;
import UITParking.DAO.NguoiDungDAO;
import UITParking.DTO.NguoiDungDTO;
import static UITParking.GUI.InitPublic.getID;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import javax.swing.JOptionPane;
import java.awt.Color;
import java.util.ArrayList;
import UITParking.GUI.login;
import java.awt.Toolkit;

/**
 *
 * @author @author Pham Hoang Ngoc Anh
 */
public class signup extends javax.swing.JFrame {

    Connection conn = null;
    PreparedStatement ps = null, ps1 = null, ps2 = null;
    ResultSet rs = null, rs1 = null, rs2 = null;

    /**
     * Creates new form signup
     */
    public signup() {
        initComponents();
        setIconImage();
    }

    

    public class EmailExample {

        private Pattern pattern, pattern1, pattern2;
        private Matcher matcher, matcher1, matcher2;

        private static final String EMAIL_REGEX = "^[A-Za-z0-9]+[A-Za-z0-9]*@[A-Za-z0-9]+(\\.[A-Za-z0-9]+)$";
        private static final String EMAIL_REGEX1 = "^[A-Za-z0-9]+[A-Za-z0-9]*@[A-Za-z0-9]+(\\.[A-Za-z0-9]+)+(\\.[A-Za-z0-9]+)$";
        private static final String EMAIL_REGEX2 = "^[A-Za-z0-9]+[A-Za-z0-9]*@[A-Za-z0-9]+(\\.[A-Za-z0-9]+)+(\\.[A-Za-z0-9]+)+(\\.[A-Za-z0-9]+)$";

        public EmailExample() {
            pattern = Pattern.compile(EMAIL_REGEX);
            pattern1 = Pattern.compile(EMAIL_REGEX1);
            pattern2 = Pattern.compile(EMAIL_REGEX2);
        }

        public boolean validate(String regex) {
            matcher = pattern.matcher(regex);
            matcher1 = pattern1.matcher(regex);
            matcher2 = pattern2.matcher(regex);
            return matcher.matches() || matcher1.matches() || matcher2.matches();
        }
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
        jLabel9 = new javax.swing.JLabel();
        panelRound1 = new Admin.PanelRound();
        jLabel1 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        txtEmailSignUp = new javax.swing.JTextField();
        jLabel5 = new javax.swing.JLabel();
        txtFirstNameSignUp = new javax.swing.JTextField();
        jLabel7 = new javax.swing.JLabel();
        txtLastNameSignUp = new javax.swing.JTextField();
        jLabel8 = new javax.swing.JLabel();
        txtPasswordSignUp = new javax.swing.JPasswordField();
        jLabel6 = new javax.swing.JLabel();
        txtConfirmPasswordSignUp = new javax.swing.JPasswordField();
        btnSignUp = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("UIT Parking");
        setBackground(new java.awt.Color(255, 255, 255));

        jPanel1.setBackground(new java.awt.Color(12, 33, 250));
        jPanel1.setBorder(javax.swing.BorderFactory.createEtchedBorder(javax.swing.border.EtchedBorder.RAISED, new java.awt.Color(153, 153, 153), java.awt.Color.lightGray));

        jLabel9.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icon/icons8_Close_20px.png"))); // NOI18N
        jLabel9.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        jLabel9.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jLabel9MouseClicked(evt);
            }
        });

        panelRound1.setBackground(new java.awt.Color(255, 255, 255));
        panelRound1.setRoundBottomLeft(50);
        panelRound1.setRoundBottomRight(50);
        panelRound1.setRoundTopLeft(50);
        panelRound1.setRoundTopRight(50);
        panelRound1.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icon/imgonline-com-ua-resize-qKADAloMJwEOW.png"))); // NOI18N
        panelRound1.add(jLabel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 40, 380, -1));

        jLabel3.setFont(new java.awt.Font("Segoe UI Historic", 1, 25)); // NOI18N
        jLabel3.setForeground(new java.awt.Color(0, 51, 204));
        jLabel3.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel3.setText("Welcome");
        panelRound1.add(jLabel3, new org.netbeans.lib.awtextra.AbsoluteConstraints(680, 20, 192, -1));

        jLabel2.setFont(new java.awt.Font("Segoe UI Historic", 0, 14)); // NOI18N
        jLabel2.setForeground(new java.awt.Color(0, 51, 204));
        jLabel2.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel2.setText("Please sign up to continue.");
        panelRound1.add(jLabel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(640, 60, 280, -1));

        jLabel4.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jLabel4.setText("Username");
        panelRound1.add(jLabel4, new org.netbeans.lib.awtextra.AbsoluteConstraints(480, 100, 80, 20));

        txtEmailSignUp.setForeground(new java.awt.Color(153, 153, 153));
        txtEmailSignUp.setText("Enter your email");
        txtEmailSignUp.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(0, 0, 0), 1, true));
        txtEmailSignUp.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                txtEmailSignUpFocusGained(evt);
            }
            public void focusLost(java.awt.event.FocusEvent evt) {
                txtEmailSignUpFocusLost(evt);
            }
        });
        txtEmailSignUp.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtEmailSignUpActionPerformed(evt);
            }
        });
        panelRound1.add(txtEmailSignUp, new org.netbeans.lib.awtextra.AbsoluteConstraints(640, 100, 290, 32));

        jLabel5.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jLabel5.setText("FirstName");
        panelRound1.add(jLabel5, new org.netbeans.lib.awtextra.AbsoluteConstraints(480, 160, 93, -1));

        txtFirstNameSignUp.setForeground(new java.awt.Color(153, 153, 153));
        txtFirstNameSignUp.setText("Enter your firstname");
        txtFirstNameSignUp.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(0, 0, 0), 1, true));
        txtFirstNameSignUp.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                txtFirstNameSignUpFocusGained(evt);
            }
            public void focusLost(java.awt.event.FocusEvent evt) {
                txtFirstNameSignUpFocusLost(evt);
            }
        });
        txtFirstNameSignUp.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtFirstNameSignUpActionPerformed(evt);
            }
        });
        panelRound1.add(txtFirstNameSignUp, new org.netbeans.lib.awtextra.AbsoluteConstraints(640, 160, 290, 32));

        jLabel7.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jLabel7.setText("LastName");
        panelRound1.add(jLabel7, new org.netbeans.lib.awtextra.AbsoluteConstraints(480, 220, 90, -1));

        txtLastNameSignUp.setForeground(new java.awt.Color(153, 153, 153));
        txtLastNameSignUp.setText("Enter your lastname");
        txtLastNameSignUp.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(0, 0, 0), 1, true));
        txtLastNameSignUp.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                txtLastNameSignUpFocusGained(evt);
            }
            public void focusLost(java.awt.event.FocusEvent evt) {
                txtLastNameSignUpFocusLost(evt);
            }
        });
        txtLastNameSignUp.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtLastNameSignUpActionPerformed(evt);
            }
        });
        panelRound1.add(txtLastNameSignUp, new org.netbeans.lib.awtextra.AbsoluteConstraints(640, 220, 290, 32));

        jLabel8.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jLabel8.setText("Password");
        panelRound1.add(jLabel8, new org.netbeans.lib.awtextra.AbsoluteConstraints(480, 280, 82, -1));

        txtPasswordSignUp.setForeground(new java.awt.Color(153, 153, 153));
        txtPasswordSignUp.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(0, 0, 0), 1, true));
        txtPasswordSignUp.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtPasswordSignUpActionPerformed(evt);
            }
        });
        panelRound1.add(txtPasswordSignUp, new org.netbeans.lib.awtextra.AbsoluteConstraints(640, 280, 290, 31));

        jLabel6.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jLabel6.setText("Confirm Password");
        panelRound1.add(jLabel6, new org.netbeans.lib.awtextra.AbsoluteConstraints(480, 340, 130, -1));

        txtConfirmPasswordSignUp.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        panelRound1.add(txtConfirmPasswordSignUp, new org.netbeans.lib.awtextra.AbsoluteConstraints(640, 340, 290, 31));

        btnSignUp.setBackground(new java.awt.Color(12, 33, 250));
        btnSignUp.setFont(new java.awt.Font("Segoe UI Emoji", 1, 14)); // NOI18N
        btnSignUp.setForeground(new java.awt.Color(255, 255, 255));
        btnSignUp.setText("SIGN UP");
        btnSignUp.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));
        btnSignUp.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                btnSignUpMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                btnSignUpMouseExited(evt);
            }
        });
        btnSignUp.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnSignUpActionPerformed(evt);
            }
        });
        panelRound1.add(btnSignUp, new org.netbeans.lib.awtextra.AbsoluteConstraints(680, 410, 230, 38));

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(48, 48, 48)
                .addComponent(panelRound1, javax.swing.GroupLayout.PREFERRED_SIZE, 1023, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 24, Short.MAX_VALUE)
                .addComponent(jLabel9)
                .addContainerGap())
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel9)
                .addGap(9, 9, 9)
                .addComponent(panelRound1, javax.swing.GroupLayout.PREFERRED_SIZE, 480, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(35, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void txtFirstNameSignUpActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtFirstNameSignUpActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtFirstNameSignUpActionPerformed

    private void txtEmailSignUpActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtEmailSignUpActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtEmailSignUpActionPerformed

    private void btnSignUpActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSignUpActionPerformed
        // TODO add your handling code here:
        try {
            String a = "";
            conn = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:orcl", "hr", "hr");
            
            //Thực thi insert dữ liệu người dùng từ form đăng ký
            String sql = "INSERT INTO NGUOIDUNG (MaND, Email, MatKhau, HoTen, VaiTro) VALUES (?, ?, ?, ?, ?)";
            ps = conn.prepareStatement(sql);
            
            //Lấy ra người dùng có mã max để từ đó chèn người tiếp theo vào
            String maxMaND = "Select Max(MaND) as MaxND from NguoiDung";
            ps1 = conn.prepareStatement(maxMaND);
            rs1 = ps1.executeQuery();

            //Thực thi checkEmailExist - Kiểm tra email đã tồn tại trong database hay chưa.
            String checkEmailExist = "Select count(MaND) as countEmail from NguoiDung where Email = ?";
            ps2 = conn.prepareStatement(checkEmailExist);
            ps2.setString(1, txtEmailSignUp.getText());
            rs2 = ps2.executeQuery();

            StringBuilder sb = new StringBuilder();
            //Kiểm tra tính hợp lệ của email khi đăng ký
            EmailExample emailExample = new EmailExample();
            boolean isvalid = emailExample.validate(txtEmailSignUp.getText());
            //Kiểm tra thông tin không được bỏ trống.
            if (txtEmailSignUp.getText().equals("")) {
                sb.append("Please Enter Your User Name!");
            } else if (txtFirstNameSignUp.getText().equals("")) {
                sb.append("Please Enter Your FirstName!");
            } else if (txtLastNameSignUp.getText().equals("")) {
                sb.append("Please Enter Your LastName!");
            } else if (new String(txtConfirmPasswordSignUp.getPassword()).equals("")) {
                sb.append("Please Enter Your Password!");
            } //Kiểm tra nếu mật khẩu bé hơn 6 kí tự thì thông báo 
            //Mật khẩu phải ít nhất 6 kí tự
            else if (new String(txtConfirmPasswordSignUp.getPassword()).length() < 6) {
                sb.append("Password must be at least 6 characters.");
            } else if (new String(txtPasswordSignUp.getPassword()).equals("")) {
                sb.append("Please Enter Your Confirm Password!");
            } //Kiểm tra xem mật khẩu confirm có trùng khớp với mật khẩu ban đầu.
            //Nếu mật khẩu không trùng khớp, thông báo ra người dùng.
            else if (!new String(txtConfirmPasswordSignUp.getPassword()).equals(new String(txtPasswordSignUp.getPassword()))) {
                sb.append("Invalid Confirm Password");
            } //Nếu email không hợp lệ thông báo ra người dùng.
            else if (!isvalid) {
                sb.append("Invalid Email");
            } else if (rs2.next()) {
                if (rs2.getInt("countEmail") > 0) {
                    sb.append("Email Already Exists");
                }
            }

            //Nếu độ dài sb lớn hơn 0 - có lỗi thì hiện ra thông báo lỗi
            if (sb.length() > 0) {
                JOptionPane.showMessageDialog(this, sb);
                return;
            }

            String id = "";
            if (rs1.next()) {
                String maxnd = rs1.getString("MaxND");
                id = getID(maxnd);
            }
            ps.setString(1, "ND" + id + "");

            ps.setString(2, txtEmailSignUp.getText());

            ps.setString(3, new String(txtConfirmPasswordSignUp.getPassword()));

            ps.setString(4, (txtLastNameSignUp.getText() + " " + txtFirstNameSignUp.getText()));

            ps.setString(5, "Khach hang");

            //Thực thi thêm người dùng vào bảng người dùng
            rs = ps.executeQuery();
            
            //Thực thi thêm người dùng vai trò khách hàng vào bảng khách hàng
            sql = "INSERT INTO KHACHHANG (MaKH, SoDu) VALUES (?, ?)";
            ps = conn.prepareStatement(sql);
            ps.setString(1, "ND" + id + "");
            ps.setInt(2, 0);
            rs = ps.executeQuery();
            JOptionPane.showMessageDialog(this, "Sign Up Successfully");
//            Homepage _homepageCustomer = new Homepage();
//            _homepageCustomer.show();
            login _login = new login();
            _login.show();
            dispose();

        } catch (Exception ex) {
            JOptionPane.showMessageDialog(null, ex);
        }
    }//GEN-LAST:event_btnSignUpActionPerformed

    private void jLabel9MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel9MouseClicked
        System.exit(0);
    }//GEN-LAST:event_jLabel9MouseClicked


    private void txtEmailSignUpFocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_txtEmailSignUpFocusGained
        if (txtEmailSignUp.getText().equals("Enter your email")) {
            txtEmailSignUp.setText("");
            txtEmailSignUp.setForeground(new Color(153, 153, 153));
        }
    }//GEN-LAST:event_txtEmailSignUpFocusGained

    private void txtEmailSignUpFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_txtEmailSignUpFocusLost
        if (txtEmailSignUp.getText().equals("")) {
            txtEmailSignUp.setText("Enter your email");
            txtEmailSignUp.setForeground(new Color(153, 153, 153));
        }
    }//GEN-LAST:event_txtEmailSignUpFocusLost

    private void txtFirstNameSignUpFocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_txtFirstNameSignUpFocusGained
        if (txtFirstNameSignUp.getText().equals("Enter your firstname")) {
            txtFirstNameSignUp.setText("");
            txtFirstNameSignUp.setForeground(new Color(153, 153, 153));
        }
    }//GEN-LAST:event_txtFirstNameSignUpFocusGained

    private void txtFirstNameSignUpFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_txtFirstNameSignUpFocusLost
        // TODO add your handling code here:
        if (txtFirstNameSignUp.getText().equals("")) {
            txtFirstNameSignUp.setText("Enter your firstname");
            txtFirstNameSignUp.setForeground(new Color(153, 153, 153));
        }
    }//GEN-LAST:event_txtFirstNameSignUpFocusLost

    private void txtLastNameSignUpFocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_txtLastNameSignUpFocusGained
//        // TODO add your handling code here:
        if (txtLastNameSignUp.getText().equals("Enter your lastname")) {
            txtLastNameSignUp.setText("");
            txtLastNameSignUp.setForeground(new Color(153, 153, 153));
        }
    }//GEN-LAST:event_txtLastNameSignUpFocusGained


    private void txtLastNameSignUpFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_txtLastNameSignUpFocusLost
        // TODO add your handling code here:
        if (txtLastNameSignUp.getText().equals("")) {
            txtLastNameSignUp.setText("Enter your lastname");
            txtLastNameSignUp.setForeground(new Color(153, 153, 153));
        }
    }//GEN-LAST:event_txtLastNameSignUpFocusLost

    private void txtPasswordSignUpActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtPasswordSignUpActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtPasswordSignUpActionPerformed

    private void txtLastNameSignUpActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtLastNameSignUpActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtLastNameSignUpActionPerformed

    private void btnSignUpMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnSignUpMouseEntered
       btnSignUp.setBackground(new Color(80,60,244));
    }//GEN-LAST:event_btnSignUpMouseEntered

    private void btnSignUpMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnSignUpMouseExited
        btnSignUp.setBackground(new Color(12, 33, 250));
    }//GEN-LAST:event_btnSignUpMouseExited

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
            java.util.logging.Logger.getLogger(signup.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(signup.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(signup.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(signup.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new signup().setVisible(true);
            }
        });
    }


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnSignUp;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel1;
    private Admin.PanelRound panelRound1;
    private javax.swing.JPasswordField txtConfirmPasswordSignUp;
    private javax.swing.JTextField txtEmailSignUp;
    private javax.swing.JTextField txtFirstNameSignUp;
    private javax.swing.JTextField txtLastNameSignUp;
    private javax.swing.JPasswordField txtPasswordSignUp;
    // End of variables declaration//GEN-END:variables
private void setIconImage() {
       setIconImage(Toolkit.getDefaultToolkit().getImage(getClass().getResource("/icon/parking.png")));

    }
}
