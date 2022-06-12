/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package UITParking.DAO;

import static UITParking.DAO.HDMuaVeDAO.pst;
import static UITParking.DAO.NguoiDungDAO.pst;
import UITParking.DTO.VeDTO;
import static UITParking.GUI.InitPublic.getID;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

/**
 *
 * @author ADMIN
 */
public class VeDAO {

    SQLConnectUnit connect;
    public static SQLConnection connection = new SQLConnection("UITParking", "uitparking", "orcl");
    public static PreparedStatement pst = null;

    /**
     * Lấy thông tin từ Database
     */
    public ArrayList<VeDTO> docDB(String condition, String orderBy) throws Exception {
        // kết nối CSDL
        connect = new SQLConnectUnit();

        ResultSet result = this.connect.Select("c_VE", condition, orderBy);
        ArrayList<VeDTO> ves = new ArrayList<>();
        while (result.next()) {

            VeDTO ve = new VeDTO();
            ve.setStrMaVe(result.getString("MaVe"));
            ve.setStrMaLoaiVe(result.getString("MaLoaiVe"));
            ve.setStrMaKH(result.getString("MaKH"));
            ve.setDateNgayKichHoat(result.getDate("NgayKichHoat"));
            ve.setDateNgayHetHan(result.getDate("NgayHetHan"));
            ve.setStrTrangThai(result.getString("TrangThai"));
            ves.add(ve);
        }
        connect.Close();
        return ves;
    }

    public ArrayList<VeDTO> docDB(String condition) throws Exception {
        return docDB(condition, null);
    }

    public ArrayList<VeDTO> docDB() throws Exception {
        return docDB(null);
    }

    /**
     * Tạo thêm 1 vé dựa theo đã có thông tin trước
     *
     * @return true nếu thành công
     */
    public Boolean them(VeDTO ve) throws Exception {
        String sql = "INSERT INTO c_Ve (MaVe, MaLoaiVe, MaKH, NgayKichHoat, NgayHetHan, TrangThai)"
                + " VALUES (?, ?, ?, ?, ?, ?)";
        try {
            pst = this.connection.getConnect().prepareStatement(sql);

            pst.setString(1, ve.getStrMaVe());
            pst.setString(2, ve.getStrMaLoaiVe());
            pst.setString(3, ve.getStrMaKH());
            if (ve.getDateNgayKichHoat() != null) {
                pst.setDate(4, new java.sql.Date(ve.getDateNgayKichHoat().getTime()));
            }else{
                pst.setDate(4, null);
            }
            if (ve.getDateNgayHetHan() != null) {
                pst.setDate(5, new java.sql.Date(ve.getDateNgayHetHan().getTime()));
            }else{
                pst.setDate(5, null);
            }
            pst.setString(6, ve.getStrTrangThai());

            return pst.executeUpdate() > 0;
        } catch (SQLException ex) {
            throw new ArithmeticException(ex.getMessage());
        }
    }

    /**
     * @param ve chuyền vào dữ liệu vé để xóa
     * @return true nếu thành công
     */
    public Boolean xoa(VeDTO ve) throws Exception {
        String sql = "DELETE FROM c_Ve WHERE MaVe = ?";
        try {
            pst = this.connection.getConnect().prepareStatement(sql);

            pst.setString(1, ve.getStrMaVe());

            return pst.executeUpdate() > 0;
        } catch (SQLException ex) {
            throw new ArithmeticException(ex.getMessage());
        }
    }

    /**
     * @param ve truyền vào dữ liệu vé mới
     * @return true nếu thành công
     */
    public Boolean sua(VeDTO ve) throws Exception {
        String sql = "UPDATE c_Ve SET MaLoaiVe = ?, MaKH = ?, NgayKichHoat = ?,"
                + "NgayHetHan = ?, TrangThai = ? WHERE MaVe = ?";
        try {
            pst = this.connection.getConnect().prepareStatement(sql);

            pst.setString(6, ve.getStrMaVe());
            pst.setString(1, ve.getStrMaLoaiVe());
            pst.setString(2, ve.getStrMaKH());
            if (ve.getDateNgayKichHoat() != null) {
                pst.setDate(3, new java.sql.Date(ve.getDateNgayKichHoat().getTime()));
            }else{
                pst.setDate(3, null);
            }
            if (ve.getDateNgayHetHan() != null) {
                pst.setDate(4, new java.sql.Date(ve.getDateNgayHetHan().getTime()));
            }else{
                pst.setDate(4, null);
            }
            
            pst.setString(5, ve.getStrTrangThai());

            return pst.executeUpdate() > 0;
        } catch (SQLException ex) {
            throw new ArithmeticException(ex.getMessage());
        }
    }
    

    public String getMaxMaVe() throws Exception {
        String sql = "Select Max(MaVe) as MaxVe from C_VE";
        pst = this.connection.getConnect().prepareStatement(sql);
        ResultSet rs = pst.executeQuery();
        String id = "";
        if (rs.next()) {
            String maxhd = rs.getString("MaxVe");
            id = getID(maxhd);
        }
        return "VE" + id;
    }
}
