/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package UITParking.DAO;

import UITParking.DTO.LoaiVeDTO;
import UITParking.DTO.VeDTO;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

/**
 *
 * @author ADMIN
 */
public class LoaiVeDAO {
    SQLConnectUnit connect;
    public static SQLConnection connection = new SQLConnection("hr", "hr", "orcl");;
    public static PreparedStatement pst = null;

    /**
     * Lấy thông tin từ Database
     */
    public ArrayList<LoaiVeDTO> docDB(String condition, String orderBy) throws Exception {
        // kết nối CSDL
        connect = new SQLConnectUnit();

        ResultSet result = this.connect.Select("LOAIVE", condition, orderBy);
        ArrayList<LoaiVeDTO> lvs = new ArrayList<>();
        while (result.next()) {
            
            LoaiVeDTO lv = new LoaiVeDTO();
            lv.setStrMaLoaiVe(result.getString("MaLoaiVe"));
            lv.setStrTenLoaiVe(result.getString("TenLoaiVe"));
            lv.setLongGiaVe(result.getLong("GiaVe"));
            lvs.add(lv);
        }
        connect.Close();
        return lvs;
    }

    public ArrayList<LoaiVeDTO> docDB(String condition) throws Exception {
        return docDB(condition, null);
    }

    public ArrayList<LoaiVeDTO> docDB() throws Exception {
        return docDB(null);
    }

    /**
     * Tạo thêm 1 vé dựa theo đã có thông tin trước
     *
     * @return true nếu thành công
     */
    public Boolean them(LoaiVeDTO lv) throws Exception {
        String sql = "INSERT INTO LOAIVE (MaLoaiVe, TenLoaiVe, GiaVe)"
                + " VALUES (?, ?, ?)";
        try {
            pst = this.connection.getConnect().prepareStatement(sql);

            pst.setString(1, lv.getStrMaLoaiVe());
            pst.setString(2, lv.getStrTenLoaiVe());
            pst.setLong(3, lv.getLongGiaVe());
            return pst.executeUpdate() > 0;
        } catch (SQLException ex) {
            throw new ArithmeticException(ex.getMessage());
        }
    }
    
    /** 
     * @param ve chuyền vào dữ liệu vé để xóa
     * @return true nếu thành công
     */
    public Boolean xoa(LoaiVeDTO lv) throws Exception {
        String sql = "DELETE FROM LOAIVE WHERE MaLoaiVe = ?";
        try {
            pst = this.connection.getConnect().prepareStatement(sql);

            pst.setString(1, lv.getStrMaLoaiVe());

            return pst.executeUpdate() > 0;
        } catch (SQLException ex) {
            throw new ArithmeticException(ex.getMessage());
        }
    }
    
    /**
     * @param ve truyền vào dữ liệu vé mới
     * @return true nếu thành công
     */
    public Boolean sua(LoaiVeDTO lv) throws Exception {
        String sql = "UPDATE LOAIVE SET TenLoaiVe = ?, GiaVe = ? WHERE MaLoaiVe = ?";
        try {
            pst = this.connection.getConnect().prepareStatement(sql);

            pst.setString(3, lv.getStrMaLoaiVe());
            pst.setString(1, lv.getStrTenLoaiVe());
            pst.setLong(2, lv.getLongGiaVe());
            
            return pst.executeUpdate() > 0;
        } catch (SQLException ex) {
            throw new ArithmeticException(ex.getMessage());
        }
    }
}
