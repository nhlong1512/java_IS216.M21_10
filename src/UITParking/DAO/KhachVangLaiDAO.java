/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package UITParking.DAO;

import UITParking.DTO.KhachVangLaiDTO;
import UITParking.DTO.NhanVienDTO;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

/**
 *
 * @author ADMIN
 */
public class KhachVangLaiDAO {
    SQLConnectUnit connect;
    public static SQLConnection connection = new SQLConnection("hr", "hr", "orcl");;
    public static PreparedStatement pst = null;

    /**
     * Lấy thông tin từ Database
     */
    public ArrayList<KhachVangLaiDTO> docDB(String condition, String orderBy) throws Exception {
        // kết nối CSDL
        connect = new SQLConnectUnit();

        ResultSet result = this.connect.Select("KHACHVANGLAI", condition, orderBy);
        ArrayList<KhachVangLaiDTO> kvls = new ArrayList<>();
        while (result.next()) {
            
            KhachVangLaiDTO kvl = new KhachVangLaiDTO();
            kvl.setStrMaTheKVL(result.getString("MaTheKVL"));
            kvl.setStrMaXe(result.getString("MaXe"));
            kvls.add(kvl);
        }
        connect.Close();
        return kvls;
    }

    public ArrayList<KhachVangLaiDTO> docDB(String condition) throws Exception {
        return docDB(condition, null);
    }

    public ArrayList<KhachVangLaiDTO> docDB() throws Exception {
        return docDB(null);
    }

    /**
     * Tạo thêm 1 vé dựa theo đã có thông tin trước
     *
     * @return true nếu thành công
     */
    public Boolean them(KhachVangLaiDTO kvl) throws Exception {
        String sql = "INSERT INTO KHACHVANGLAI (MaTheKVL, MaXe)"
                + " VALUES (?, ?)";
        try {
            pst = this.connection.getConnect().prepareStatement(sql);

            pst.setString(1, kvl.getStrMaTheKVL());
            pst.setString(2, kvl.getStrMaXe());
            return pst.executeUpdate() > 0;
        } catch (SQLException ex) {
            throw new ArithmeticException(ex.getMessage());
        }
    }
    
    /** 
     * @param ve chuyền vào dữ liệu vé để xóa
     * @return true nếu thành công
     */
    public Boolean xoa(KhachVangLaiDTO kvl) throws Exception {
        String sql = "DELETE FROM KHACHVANGLAI WHERE MaTheKVL = ?";
        try {
            pst = this.connection.getConnect().prepareStatement(sql);

            pst.setString(1, kvl.getStrMaTheKVL());

            return pst.executeUpdate() > 0;
        } catch (SQLException ex) {
            throw new ArithmeticException(ex.getMessage());
        }
    }
    
    /**
     * @param ve truyền vào dữ liệu vé mới
     * @return true nếu thành công
     */
    public Boolean sua(KhachVangLaiDTO kvl) throws Exception {
        String sql = "UPDATE KHACHVANGLAI SET MaXe = ? WHERE MaTheKVL = ?";
        try {
            pst = this.connection.getConnect().prepareStatement(sql);

            pst.setString(2, kvl.getStrMaTheKVL());
            pst.setString(1, kvl.getStrMaXe());
            
            
            return pst.executeUpdate() > 0;
        } catch (SQLException ex) {
            throw new ArithmeticException(ex.getMessage());
        }
    }
}
