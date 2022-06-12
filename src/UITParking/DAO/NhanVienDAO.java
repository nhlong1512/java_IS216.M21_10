/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package UITParking.DAO;

import UITParking.DTO.LoaiVeDTO;
import UITParking.DTO.NhanVienDTO;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

/**
 *
 * @author ADMIN
 */
public class NhanVienDAO {
    SQLConnectUnit connect;
    public static SQLConnection connection = new SQLConnection("UITParking", "uitparking", "orcl");;
    public static PreparedStatement pst = null;

    /**
     * Lấy thông tin từ Database
     */
    public ArrayList<NhanVienDTO> docDB(String condition, String orderBy) throws Exception {
        // kết nối CSDL
        connect = new SQLConnectUnit();

        ResultSet result = this.connect.Select("NHANVIEN", condition, orderBy);
        ArrayList<NhanVienDTO> nvs = new ArrayList<>();
        while (result.next()) {
            
            NhanVienDTO nv = new NhanVienDTO();
            nv.setStrMaNV(result.getString("MaNV"));
            nv.setStrViTriNhanVien(result.getString("ViTriNhanVien"));
            nvs.add(nv);
        }
        connect.Close();
        return nvs;
    }

    public ArrayList<NhanVienDTO> docDB(String condition) throws Exception {
        return docDB(condition, null);
    }

    public ArrayList<NhanVienDTO> docDB() throws Exception {
        return docDB(null);
    }

    /**
     * Tạo thêm 1 vé dựa theo đã có thông tin trước
     *
     * @return true nếu thành công
     */
    public Boolean them(NhanVienDTO nv) throws Exception {
        String sql = "INSERT INTO NHANVIEN (MaNV, ViTriNhanVien)"
                + " VALUES (?, ?)";
        try {
            pst = this.connection.getConnect().prepareStatement(sql);

            pst.setString(1, nv.getStrMaNV());
            pst.setString(2, nv.getStrViTriNhanVien());
            return pst.executeUpdate() > 0;
        } catch (SQLException ex) {
            throw new ArithmeticException(ex.getMessage());
        }
    }
    
    /** 
     * @param ve chuyền vào dữ liệu vé để xóa
     * @return true nếu thành công
     */
    public Boolean xoa(NhanVienDTO nv) throws Exception {
        String sql = "DELETE FROM NHANVIEN WHERE MaNV = ?";
        try {
            pst = this.connection.getConnect().prepareStatement(sql);

            pst.setString(1, nv.getStrMaNV());

            return pst.executeUpdate() > 0;
        } catch (SQLException ex) {
            throw new ArithmeticException(ex.getMessage());
        }
    }
    
    /**
     * @param ve truyền vào dữ liệu vé mới
     * @return true nếu thành công
     */
    public Boolean sua(NhanVienDTO nv) throws Exception {
        String sql = "UPDATE NHANVIEN SET ViTriNhanVien = ? WHERE MaNV = ?";
        try {
            pst = this.connection.getConnect().prepareStatement(sql);

            pst.setString(2, nv.getStrMaNV());
            pst.setString(1, nv.getStrViTriNhanVien());
            
            return pst.executeUpdate() > 0;
        } catch (SQLException ex) {
            throw new ArithmeticException(ex.getMessage());
        }
    }
}
