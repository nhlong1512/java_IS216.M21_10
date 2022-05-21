/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package UITParking.DAO;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLDataException;
import java.sql.SQLException;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.util.HashMap;
import java.sql.Connection;

/**
 *
 * @author Nguyen Huu Long
 */
public class SQLConnectUnit {

    //biến kết nối cơ bản
    private SQLConnection connect;
    PreparedStatement ps = null;

    // hàm khởi tạo kết nối mặc định
    public SQLConnectUnit() {
        connect = new SQLConnection("hr", "hr", "orcl");
    }

    // hàm khởi tạo cơ bản
    public SQLConnectUnit(String Username, String Password, String Sid) {
        connect = new SQLConnection(Username, Password, Sid);
    }

    // Hàm hỗ trợ Select CSDL
    /**
     * Select * From Table Where Condition Order by OderBy
     *
     * @throws Exception
     */
    public ResultSet Select(String TableName, String Condition, String OrderBy) throws Exception {
        // khai báo biến StringBuilder để tạo chuỗi Select
        StringBuilder query = new StringBuilder("SELECT * FROM " + TableName);
        // Đưa câu lệnh điều kiện vaò câu query
        this.AddCondition(query, Condition);
        // Đưa câu lệnh Order vào query
        this.AddOrderBy(query, OrderBy);
        // chèn ký tự ; vào cuồi các câu lệnh
//        query.append(";");
        // thực thi câu lệnh query và trả kết quả
        return this.connect.getConnect().prepareStatement(query.toString()).executeQuery();
    }

    // Hàm over load Select giảm OrderBy parameter
    /**
     * Select * From Table Where Condition
     *
     * @throws Exception
     */
    public ResultSet Select(String TableName, String Condition) throws Exception {
        return this.Select(TableName, Condition, null);
    }

    // Hàm over load Select giảm Condition  parameter
    /**
     * Select * From Table
     *
     * @throws Exception
     */
    public ResultSet Select(String TableName) throws Exception {
        return this.Select(TableName, null, null);
    }

    //Hàm thêm điều kiện vào query
    private void AddCondition(StringBuilder query, String Condition) {
        // kiểm tra nếu condition khác null
        if (Condition != null) {
            query.append(" WHERE " + Condition);
        }
    }

    // Hàm thêm OrderBy vào query
    private void AddOrderBy(StringBuilder query, String OrderBy) {
        // Kiểm tra OrderBy Khác null
        if (OrderBy != null) {
            query.append(" ORDER BY " + OrderBy);
        }
    }
    public static int getColumnCount(ResultSet result) throws SQLException {
        return result.getMetaData().getColumnCount();
    }

    // hàm lấy tên cột trong  result  select từ CSDL
    public static String[] getColumnName(ResultSet result) throws SQLException {
        // lấy resultsetMetaDate từ Result
        ResultSetMetaData rsMetaData = (ResultSetMetaData) result.getMetaData();
        // lấy số lượng cột trong Result
        int ColumnCount = rsMetaData.getColumnCount();
        // khai báo danh sách các cột
        String[] list = new String[ColumnCount];
        for (int i = 0; i < ColumnCount; i++) {
            list[i] = rsMetaData.getColumnName(i);
        }
        return list;
    }

//     hàm đóng kết nối 
    public void Close() throws Exception {
        this.connect.Close();
    }
}
