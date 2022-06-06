/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package UITParking.DAO;

import UITParking.DTO.NguoiDungDTO;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;

/**
 *
 * @author Nguyen Huu Long
 */
public class NguoiDungDAO {

    SQLConnectUnit connect;
    public static SQLConnection connection = new SQLConnection("hr", "hr", "orcl");
    ;
    public static PreparedStatement pst = null;

    /**
     * Lấy thông tin từ Database
     */
    public ArrayList<NguoiDungDTO> docDB(String condition, String orderBy) throws Exception {
        // kết nối CSDL
        connect = new SQLConnectUnit();

        ResultSet result = this.connect.Select("NGUOIDUNG", condition, orderBy);
        ArrayList<NguoiDungDTO> nguoidungs = new ArrayList<>();
        while (result.next()) {
            NguoiDungDTO nguoidung = new NguoiDungDTO();
            nguoidung.setStrMaND(result.getString("MaND"));
            nguoidung.setStrEmail(result.getString("Email"));
            nguoidung.setStrMatKhau(result.getString("MatKhau"));
            nguoidung.setStrHoTen(result.getString("HoTen"));
            nguoidung.setStrGioiTinh(result.getString("GioiTinh"));
            nguoidung.setDateNgSinh(result.getDate("NgSinh"));
            nguoidung.setStrDiaChi(result.getString("DiaChi"));
            nguoidung.setStrQueQuan(result.getString("QueQuan"));
            nguoidung.setStrSDT(result.getString("SDT"));
            nguoidung.setStrVaiTro(result.getString("VaiTro"));
            nguoidungs.add(nguoidung);
        }
        connect.Close();
        return nguoidungs;
    }

    public ArrayList<NguoiDungDTO> docDB(String condition) throws Exception {
        return docDB(condition, null);
    }

    public ArrayList<NguoiDungDTO> docDB() throws Exception {
        return docDB(null);
    }

    public NguoiDungDTO findById(String MaND) throws Exception {
        String sql = "Select * from NGUOIDUNG where MaND = ?";
        try {
            pst = this.connection.getConnect().prepareStatement(sql);

            pst.setString(1, MaND);
            ResultSet rs = pst.executeQuery();
            if (rs.next()) {
                NguoiDungDTO nd = new NguoiDungDTO();
                nd.setStrMaND(rs.getString("MaND"));
                nd.setStrHoTen(rs.getString("HoTen"));
                nd.setStrEmail(rs.getString("Email"));
                nd.setStrGioiTinh(rs.getString("GioiTinh"));
                nd.setStrDiaChi(rs.getString("DiaChi"));
                nd.setStrQueQuan(rs.getString("QueQuan"));
                nd.setStrSDT(rs.getString("SDT"));
                nd.setStrVaiTro(rs.getString("VaiTro"));
                nd.setStrMatKhau(rs.getString("MatKhau"));
                nd.setDateNgSinh(rs.getDate("NgSinh"));
                return nd;
            }
            return null;
        } catch (SQLException ex) {
            throw new ArithmeticException(ex.getMessage());
        }
    }

    /**
     * Tạo thêm 1 người dùng dựa theo đã có thông tin trước
     *
     * @return true nếu thành công
     */
    public Boolean them(NguoiDungDTO nd) throws Exception {
        String sql = "INSERT INTO NGUOIDUNG (MaND, Email, MatKhau, HoTen, GIOITINH, NGSINH, DIACHI, QUEQUAN, SDT, VAITRO) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
        try {
            pst = this.connection.getConnect().prepareStatement(sql);

            pst.setString(1, nd.getStrMaND());
            pst.setString(2, nd.getStrEmail());
            pst.setString(3, nd.getStrMatKhau());
            pst.setString(4, nd.getStrHoTen());
            pst.setString(5, nd.getStrGioiTinh());
            if (nd.getDateNgSinh() != null) {
                pst.setDate(6, new java.sql.Date(nd.getDateNgSinh().getTime()));

            } else {
                pst.setDate(6, null);
            }
            pst.setString(7, nd.getStrDiaChi());
            pst.setString(8, nd.getStrQueQuan());
            pst.setString(9, nd.getStrSDT());
            pst.setString(10, nd.getStrVaiTro());

            return pst.executeUpdate() > 0;
        } catch (SQLException ex) {
            throw new ArithmeticException(ex.getMessage());
        }
    }

    public Boolean themManagement(NguoiDungDTO nd) throws Exception {
        String sql = "INSERT INTO NGUOIDUNG (MaND, Email, HoTen, GIOITINH, DIACHI, QUEQUAN, SDT, VAITRO) VALUES (?, ?, ?, ?, ?, ?, ?, ?)";
        try {
            pst = this.connection.getConnect().prepareStatement(sql);

            pst.setString(1, nd.getStrMaND());
            pst.setString(2, nd.getStrEmail());
            pst.setString(3, nd.getStrHoTen());
            pst.setString(4, nd.getStrGioiTinh());
            pst.setString(5, nd.getStrDiaChi());
            pst.setString(6, nd.getStrQueQuan());
            pst.setString(7, nd.getStrSDT());
            pst.setString(8, nd.getStrVaiTro());

            return pst.executeUpdate() > 0;
        } catch (SQLException ex) {
            throw new ArithmeticException(ex.getMessage());
        }
    }

    /**
     * @param nd chuyền vào dữ liệu người dùng để xóa
     * @return true nếu thành công
     */
    public Boolean xoa(NguoiDungDTO nd) throws Exception {
        String sql = "DELETE FROM NGUOIDUNG WHERE MaND = ?";
        try {
            pst = this.connection.getConnect().prepareStatement(sql);

            pst.setString(1, nd.getStrMaND());

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
    public Boolean sua(NguoiDungDTO nd) throws Exception {
        String sql = "UPDATE NGUOIDUNG SET Email = ?, MatKhau = ?, HoTen = ?, "
                + "GIOITINH = ?, NGSINH = ?, DIACHI = ?, QUEQUAN = ?, SDT = ?, "
                + "VAITRO = ? WHERE MAND = ?";
        try {
            pst = this.connection.getConnect().prepareStatement(sql);

            pst.setString(10, nd.getStrMaND());
            pst.setString(1, nd.getStrEmail());
            pst.setString(2, nd.getStrMatKhau());
            pst.setString(3, nd.getStrHoTen());
            pst.setString(4, nd.getStrGioiTinh());
            if (nd.getDateNgSinh() != null) {
                pst.setDate(5, new java.sql.Date(nd.getDateNgSinh().getTime()));

            } else {
                pst.setDate(5, null);
            }
            pst.setString(6, nd.getStrDiaChi());
            pst.setString(7, nd.getStrQueQuan());
            pst.setString(8, nd.getStrSDT());
            pst.setString(9, nd.getStrVaiTro());

            return pst.executeUpdate() > 0;
        } catch (SQLException ex) {
            throw new ArithmeticException(ex.getMessage());
        }
    }

    public Boolean suaManagement(NguoiDungDTO nd) throws Exception {
        String sql = "UPDATE NGUOIDUNG SET Email = ?, HoTen = ?, "
                + "GIOITINH = ?, DIACHI = ?, QUEQUAN = ?, SDT = ?, "
                + "VAITRO = ?, NGAYSINH = ? WHERE MAND = ?";
        try {
            pst = this.connection.getConnect().prepareStatement(sql);

            pst.setString(9, nd.getStrMaND());
            pst.setString(1, nd.getStrEmail());
            pst.setString(2, nd.getStrHoTen());
            pst.setString(3, nd.getStrGioiTinh());
            pst.setString(4, nd.getStrDiaChi());
            pst.setString(5, nd.getStrQueQuan());
            pst.setString(6, nd.getStrSDT());
            pst.setString(7, nd.getStrVaiTro());
            pst.setDate(8, new java.sql.Date(nd.getDateNgSinh().getTime()));

            return pst.executeUpdate() > 0;
        } catch (SQLException ex) {
            throw new ArithmeticException(ex.getMessage());
        }
    }

    public Boolean suaKhongCoNgaySinh(NguoiDungDTO nd) throws Exception {
        String sql = "UPDATE NGUOIDUNG SET Email = ?, MatKhau = ?, HoTen = ?, "
                + "GIOITINH = ?, DIACHI = ?, QUEQUAN = ?, SDT = ?, "
                + "VAITRO = ? WHERE MAND = ?";
        try {
            pst = this.connection.getConnect().prepareStatement(sql);

            pst.setString(9, nd.getStrMaND());
            pst.setString(1, nd.getStrEmail());
            pst.setString(2, nd.getStrMatKhau());
            pst.setString(3, nd.getStrHoTen());
            pst.setString(4, nd.getStrGioiTinh());
            pst.setString(5, nd.getStrDiaChi());
            pst.setString(6, nd.getStrQueQuan());
            pst.setString(7, nd.getStrSDT());
            pst.setString(8, nd.getStrVaiTro());

            return pst.executeUpdate() > 0;
        } catch (SQLException ex) {
            throw new ArithmeticException(ex.getMessage());
        }
    }
}
