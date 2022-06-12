/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package UITParking.DAO;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import javax.swing.JOptionPane;

/**
 *
 * @author ADMIN
 */
public class Connect {
    private  static Connection conn=null;
    
    public static Connection getConnect(){
        final String url = "jdbc:oracle:thin:@localhost:1521:orcl";
        final String username = "UITParking";
        final String password = "uitparking";
        try {
            Class.forName("oracle.jdbc.driver.OracleDriver");
            conn = DriverManager.getConnection(url, username, password);
            //JOptionPane.showMessageDialog(null,"Ket noi co so du lieu thanh cong ");
            
        } catch (ClassNotFoundException | SQLException e) {
            JOptionPane.showMessageDialog(null,"Ket noi co so du lieu khong thanh cong ");
        }
        return conn;
    }
}
