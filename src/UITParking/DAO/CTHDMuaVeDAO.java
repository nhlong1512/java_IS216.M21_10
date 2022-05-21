/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package UITParking.DAO;

import UITParking.DTO.CTHDMuaVeDTO;
import UITParking.DTO.HDMuaVeDTO;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

/**
 *
 * @author ADMIN
 */
public class CTHDMuaVeDAO {

    SQLConnectUnit connect;
    public static SQLConnection connection = new SQLConnection("hr", "hr", "orcl");
    public static PreparedStatement pst = null;

    /**
     * Lấy thông tin từ Database
     */
    public ArrayList<CTHDMuaVeDTO> docDB(String condition, String orderBy) throws Exception {
        // kết nối CSDL
        connect = new SQLConnectUnit();

        ResultSet result = this.connect.Select("CHITIETHOADONMUAVE", condition, orderBy);
        ArrayList<CTHDMuaVeDTO> CTHDMuaVes = new ArrayList<>();
        while (result.next()) {

            CTHDMuaVeDTO CTHDMuaVe = new CTHDMuaVeDTO();
            CTHDMuaVe.setStrMaHD(result.getString("MaHD"));
            CTHDMuaVe.setStrMaLoaiVe(result.getString("MaLoaiVe"));
            CTHDMuaVe.setLongSoLuongVe(result.getLong("SoLuongVe"));
            CTHDMuaVes.add(CTHDMuaVe);
        }
        connect.Close();
        return CTHDMuaVes;
    }

    public ArrayList<CTHDMuaVeDTO> docDB(String condition) throws Exception {
        return docDB(condition, null);
    }

    public ArrayList<CTHDMuaVeDTO> docDB() throws Exception {
        return docDB(null);
    }

    /**
     * Tạo thêm 1 người dùng dựa theo đã có thông tin trước
     *
     * @return true nếu thành công
     */
    public Boolean them(CTHDMuaVeDTO CTHDMuaVe) throws Exception {
        String sql = "INSERT INTO CHITIETHOADONMUAVE (MaHD, MaLoaiVe, SoLuongVe) VALUES (?, ?, ?)";
        try {
            pst = this.connection.getConnect().prepareStatement(sql);

            pst.setString(1, CTHDMuaVe.getStrMaHD());
            pst.setString(2, CTHDMuaVe.getStrMaLoaiVe());
            pst.setLong(3, CTHDMuaVe.getLongSoLuongVe());

            return pst.executeUpdate() > 0;
        } catch (SQLException ex) {
            throw new ArithmeticException(ex.getMessage());
        }
    }

    /**
     * @param nd chuyền vào dữ liệu người dùng để xóa
     * @return true nếu thành công
     */
    public Boolean xoa(CTHDMuaVeDTO CTHDMuaVe) throws Exception {
        String sql = "DELETE FROM CHITIETHOADONMUAVE WHERE MaHD = ? AND MaLoaiVe = ?";
        try {
            pst = this.connection.getConnect().prepareStatement(sql);

            pst.setString(1, CTHDMuaVe.getStrMaHD());
            pst.setString(2, CTHDMuaVe.getStrMaLoaiVe());

            return pst.executeUpdate() > 0;
        } catch (SQLException ex) {
            throw new ArithmeticException(ex.getMessage());
        }
    }

    /**
     * @param nd truyền vào dữ liệu người dùng mới Sửa thông tin đăng nhập hoặc
     * là cấp bậc của 1 người dùng
     * @return true nếu thành công
     */
    public Boolean sua(CTHDMuaVeDTO CTHDMuaVe) throws Exception {
        String sql = "UPDATE HOADONMUAVE SET SoLuongVe = ? WHERE MAHD = ? AND MaLoaiVe = ?";
        try {
            pst = this.connection.getConnect().prepareStatement(sql);

            pst.setString(2, CTHDMuaVe.getStrMaHD());
            pst.setString(3, CTHDMuaVe.getStrMaLoaiVe());
            pst.setLong(1, CTHDMuaVe.getLongSoLuongVe());

            return pst.executeUpdate() > 0;
        } catch (SQLException ex) {
            throw new ArithmeticException(ex.getMessage());
        }
    }
}
