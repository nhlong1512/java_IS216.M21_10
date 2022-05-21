package UITParking.DAO;


import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLDataException;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.TimeZone;
import java.sql.Connection;

/**
 *
 * @author Nguyen Huu Long
 */
public class SQLConnection {
    String Username = "";
    String Password = "";
    String Sid = "";
    
    Connection connect = null;
    Statement statement = null;
    ResultSet resultSet = null;

    public SQLConnection(String Username, String Password, String Sid) {
        this.Username = Username;
        this.Password = Password;
        this.Sid = Sid;
    }
    
    
    protected void driveTest () throws Exception {
        try {
            Class.forName("oracle.jdbc.driver.OracleDriver");
        }
        catch (ClassNotFoundException e) {
            throw new Exception("SQl Developer not found driveTest");
        }
    }
    
    protected Connection getConnect() throws Exception {
//        nếu connection null thì khởi tạo mới
        if (this.connect == null) {
            driveTest();
            
//            Tạo url để kết nối tới Database
            String url = "jdbc:oracle:thin:@localhost:1521:" + this.Sid;
            try{
//                tạo connet thông qua url
                this.connect = DriverManager.getConnection(url, this.Username, this.Password);
            }        
            catch (SQLException e) {
                throw new Exception("không thể kết nối tới Database" + url +e.getMessage());
            }
        }
        
        return this.connect;
    }
    
    //hàm đóng kết nối
    public void Close() throws Exception {
        if (this.resultSet != null && this.resultSet.isClosed()) {
            this.resultSet.close();
            this.resultSet = null;
        }
        if (this.statement != null && this.statement.isClosed()) {
            this.statement.close();
            this.statement = null;
        }
        if (this.connect != null && this.connect.isClosed()) {
            this.connect.close();
            this.connect = null;
        }
    }

    Object prepareStatement(String toString) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }
}