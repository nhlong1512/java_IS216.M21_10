/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package UITParking.DAO;

import UITParking.DTO.HDMuaVeDTO;
import static UITParking.GUI.InitPublic.getID;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

/**
 *
 * @author ADMIN
 */
public class HDMuaVeDAO {

    SQLConnectUnit connect;
    public static SQLConnection connection = new SQLConnection("hr", "hr", "orcl");
    public static PreparedStatement pst = null;

    /**
     * Lấy thông tin từ Database
     */
    public ArrayList<HDMuaVeDTO> docDB(String condition, String orderBy) throws Exception {
        // kết nối CSDL
        connect = new SQLConnectUnit();

        ResultSet result = this.connect.Select("HOADONMUAVE", condition, orderBy);
        ArrayList<HDMuaVeDTO> HDMuaVes = new ArrayList<>();
        while (result.next()) {

            HDMuaVeDTO HDMuaVe = new HDMuaVeDTO();
            HDMuaVe.setStrMaHD(result.getString("MaHD"));
            HDMuaVe.setStrMaKH(result.getString("MaKH"));
            HDMuaVe.setDateNgayHD(result.getDate("NgayHD"));
            HDMuaVe.setLongTongTriGia(result.getLong("TongTriGia"));
            HDMuaVes.add(HDMuaVe);
        }
        connect.Close();
        return HDMuaVes;
    }

    public ArrayList<HDMuaVeDTO> docDB(String condition) throws Exception {
        return docDB(condition, null);
    }

    public ArrayList<HDMuaVeDTO> docDB() throws Exception {
        return docDB(null);
    }

    /**
     * Tạo thêm 1 người dùng dựa theo đã có thông tin trước
     *
     * @return true nếu thành công
     */
    public Boolean them(HDMuaVeDTO HDMuaVe) throws Exception {
        String sql = "INSERT INTO HOADONMUAVE (MaHD, MaKH, NgayHD, TongTriGia) VALUES (?, ?, ?, ?)";
        try {
            pst = this.connection.getConnect().prepareStatement(sql);

            pst.setString(1, HDMuaVe.getStrMaHD());
            pst.setString(2, HDMuaVe.getStrMaKH());
            pst.setDate(3, new java.sql.Date(HDMuaVe.getDateNgayHD().getTime()));
            pst.setLong(4, HDMuaVe.getLongTongTriGia());

            return pst.executeUpdate() > 0;
        } catch (SQLException ex) {
            throw new ArithmeticException(ex.getMessage());
        }
    }

    /**
     * @param nd chuyền vào dữ liệu người dùng để xóa
     * @return true nếu thành công
     */
    public Boolean xoa(HDMuaVeDTO HDMuaVe) throws Exception {
        String sql = "DELETE FROM HOADONMUAVE WHERE MaHD = ?";
        try {
            pst = this.connection.getConnect().prepareStatement(sql);

            pst.setString(1, HDMuaVe.getStrMaHD());

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
    public Boolean sua(HDMuaVeDTO HDMuaVe) throws Exception {
        String sql = "UPDATE HOADONMUAVE SET MaKH = ?, NgayHD = ?, TongTriGia = ? WHERE MAHD = ?";
        try {
            pst = this.connection.getConnect().prepareStatement(sql);

            pst.setString(4, HDMuaVe.getStrMaHD());
            pst.setString(1, HDMuaVe.getStrMaKH());
            pst.setDate(2, new java.sql.Date(HDMuaVe.getDateNgayHD().getTime()));
            pst.setLong(3, HDMuaVe.getLongTongTriGia());

            return pst.executeUpdate() > 0;
        } catch (SQLException ex) {
            throw new ArithmeticException(ex.getMessage());
        }
    }

    public String getMaxMaHD() throws Exception {
        String sql = "Select Max(MaHD) as MaxHD from HOADONMUAVE";
        pst = this.connection.getConnect().prepareStatement(sql);
        ResultSet rs = pst.executeQuery();
        String id = "";
        if (rs.next()) {
            String maxhd = rs.getString("MaxHD");
            id = getID(maxhd);
        }
        return "HD" + id;
    }
}
