/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package UITParking.DAO;

import UITParking.DTO.KhachHangDTO;
import UITParking.DTO.NguoiDungDTO;
import static UITParking.GUI.InitPublic.getID;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

/**
 *
 * @author Nguyen Huu Long
 */
public class KhachHangDAO {
    SQLConnectUnit connect;
    public static SQLConnection connection = new SQLConnection("UITParking", "uitparking", "orcl");
    public static PreparedStatement pst = null;

    /**
     * Lấy thông tin từ Database
     */
    public ArrayList<KhachHangDTO> docDB(String condition, String orderBy) throws Exception {
        // kết nối CSDL
        connect = new SQLConnectUnit();

        ResultSet result = this.connect.Select("KHACHHANG", condition, orderBy);
        ArrayList<KhachHangDTO> khachhangs = new ArrayList<>();
        while (result.next()) {
            
            KhachHangDTO khachhang = new KhachHangDTO();
            khachhang.setStrMaKH(result.getString("MaKH"));
            khachhang.setStrMaXe(result.getString("MaXe"));
            khachhang.setLongSoDu(result.getLong("SoDu"));
            khachhangs.add(khachhang);
        }
        connect.Close();
        return khachhangs;
    }

    public ArrayList<KhachHangDTO> docDB(String condition) throws Exception {
        return docDB(condition, null);
    }

    public ArrayList<KhachHangDTO> docDB() throws Exception {
        return docDB(null);
    }

    /**
     * Tạo thêm 1 người dùng dựa theo đã có thông tin trước
     *
     * @return true nếu thành công
     */
    public Boolean them(KhachHangDTO kh) throws Exception {
        String sql = "INSERT INTO KHACHHANG (MaKH, MaXe, SoDu) VALUES (?, ?, ?)";
        try {
            pst = this.connection.getConnect().prepareStatement(sql);

            pst.setString(1, kh.getStrMaKH());
            pst.setString(2, kh.getStrMaXe());
            pst.setLong(3, kh.getLongSoDu());

            return pst.executeUpdate() > 0;
        } catch (SQLException ex) {
            throw new ArithmeticException(ex.getMessage());
        }
    }
    
    /** 
     * @param nd chuyền vào dữ liệu người dùng để xóa
     * @return true nếu thành công
     */
    public Boolean xoa(KhachHangDTO kh) throws Exception {
        String sql = "DELETE FROM KHACHHANG WHERE MaKH = ?";
        try {
            pst = this.connection.getConnect().prepareStatement(sql);

            pst.setString(1, kh.getStrMaKH());

            return pst.executeUpdate() > 0;
        } catch (SQLException ex) {
            throw new ArithmeticException(ex.getMessage());
        }
    }
    
    /**
     * @param nd truyền vào dữ liệu người dùng mới
     * Sửa thông tin đăng nhập hoặc là cấp bậc của 1 người dùng
     * @return true nếu thành công
     */
    public Boolean sua(KhachHangDTO kh) throws Exception {
        String sql = "UPDATE KHACHHANG SET MaXe = ?, SoDu = ? WHERE MAKH = ?";
        try {
            pst = this.connection.getConnect().prepareStatement(sql);

            pst.setString(3, kh.getStrMaKH());
            pst.setString(1, kh.getStrMaXe());
            pst.setLong(2, kh.getLongSoDu());
            

            return pst.executeUpdate() > 0;
        } catch (SQLException ex) {
            throw new ArithmeticException(ex.getMessage());
        }
    }
    
    public String getMaxMaKH() throws Exception {
        String sql = "Select Max(MaKH) as MaxKhachHang from KHACHHANG";
        pst = this.connection.getConnect().prepareStatement(sql);
        ResultSet rs = pst.executeQuery();
        String id = "";
        if (rs.next()) {
            String maxhd = rs.getString("MaxKhachHang");
            id = getID(maxhd);
        }
        return "ND" + id;
    }
}
