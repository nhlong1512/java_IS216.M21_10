/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package UITParking.DAO;

import UITParking.DTO.CTRaVaoDTO;
import static UITParking.GUI.InitPublic.getID;
import java.sql.PreparedStatement;
import java.util.ArrayList;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 *
 * @author ADMIN
 */
public class CTRaVaoDAO {

    SQLConnectUnit connect;
    public static SQLConnection connection = new SQLConnection("hr", "hr", "orcl");
    public static PreparedStatement pst = null;

    public ArrayList<CTRaVaoDTO> docDB(String condition, String orderBy) throws Exception {
        // kết nối CSDL
        connect = new SQLConnectUnit();

        ResultSet result = this.connect.Select("CHITIETRAVAO", condition, orderBy);
        ArrayList<CTRaVaoDTO> CTRaVaos = new ArrayList<>();
        while (result.next()) {

            CTRaVaoDTO CTRaVao = new CTRaVaoDTO();
            CTRaVao.setStrMaCTRaVao(result.getString("MaCTRaVao"));
            CTRaVao.setDateThoiGianVao(result.getDate("ThoiGianVao"));
            CTRaVao.setDateThoiGianRa(result.getDate("ThoiGianRa"));
            CTRaVao.setStrMaNV(result.getString("MaNV"));
            CTRaVao.setStrMaKH(result.getString("MaKH"));
            CTRaVao.setStrMaXe(result.getString("MaXe"));
            CTRaVao.setStrMaTheKVL(result.getString("MaTheKVL"));
            CTRaVaos.add(CTRaVao);
        }
        connect.Close();
        return CTRaVaos;
    }

    public ArrayList<CTRaVaoDTO> docDB(String condition) throws Exception {
        return docDB(condition, null);
    }

    public ArrayList<CTRaVaoDTO> docDB() throws Exception {
        return docDB(null);
    }

    /**
     * Tạo thêm 1 người dùng dựa theo đã có thông tin trước
     *
     * @return true nếu thành công
     */
    public Boolean them(CTRaVaoDTO CTRaVao) throws Exception {
        String sql = "INSERT INTO CHITIETRAVAO (MaCTRaVao, ThoiGianVao, ThoiGianRa, MaNV, MaKH, MaXe, MaTheKVL) VALUES (?, ?, ?, ?, ?, ?, ?)";
        try {
            pst = this.connection.getConnect().prepareStatement(sql);

            pst.setString(1, CTRaVao.getStrMaCTRaVao());
            if (CTRaVao.getDateThoiGianVao() != null) {
                pst.setDate(2, new java.sql.Date(CTRaVao.getDateThoiGianVao().getTime()));
            } else {
                pst.setDate(2, null);
            }
            if (CTRaVao.getDateThoiGianRa() != null) {
                pst.setDate(3, new java.sql.Date(CTRaVao.getDateThoiGianRa().getTime()));

            } else {
                pst.setDate(3, null);
            }
            pst.setString(4, CTRaVao.getStrMaNV());
            pst.setString(5, CTRaVao.getStrMaKH());
            pst.setString(6, CTRaVao.getStrMaXe());
            pst.setString(7, CTRaVao.getStrMaTheKVL());
            return pst.executeUpdate() > 0;
        } catch (SQLException ex) {
            throw new ArithmeticException(ex.getMessage());
        }
    }

    /**
     * @param nd chuyền vào dữ liệu người dùng để xóa
     * @return true nếu thành công
     */
    public Boolean xoa(CTRaVaoDTO CTRaVao) throws Exception {
        String sql = "DELETE FROM CHITIETRAVAO WHERE MaCTRaVao = ?";
        try {
            pst = this.connection.getConnect().prepareStatement(sql);

            pst.setString(1, CTRaVao.getStrMaCTRaVao());

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
    public Boolean sua(CTRaVaoDTO CTRaVao) throws Exception {
        String sql = "UPDATE CHITIETRAVAO SET ThoiGianVao = ?, ThoiGianRa = ?, MaNV = ?, MaKH = ?, MaXe = ?, MaTheKVL = ? WHERE MaCTRaVao = ?";
        try {
            pst = this.connection.getConnect().prepareStatement(sql);

            pst.setString(7, CTRaVao.getStrMaCTRaVao());
            if (CTRaVao.getDateThoiGianVao() != null) {
                pst.setDate(1, new java.sql.Date(CTRaVao.getDateThoiGianVao().getTime()));
            } else {
                pst.setDate(1, null);
            }
            if (CTRaVao.getDateThoiGianRa() != null) {
                pst.setDate(2, new java.sql.Date(CTRaVao.getDateThoiGianRa().getTime()));

            } else {
                pst.setDate(2, null);
            }
            pst.setString(3, CTRaVao.getStrMaNV());
            pst.setString(4, CTRaVao.getStrMaKH());
            pst.setString(5, CTRaVao.getStrMaXe());
            pst.setString(6, CTRaVao.getStrMaTheKVL());

            return pst.executeUpdate() > 0;
        } catch (SQLException ex) {
            throw new ArithmeticException(ex.getMessage());
        }
    }
    
    

    public String getMaxMaCTRaVao() throws Exception {
        String sql = "Select Max(MaCTRaVao) as MaxCTRaVao from CHITIETRAVAO";
        pst = this.connection.getConnect().prepareStatement(sql);
        ResultSet rs = pst.executeQuery();
        String id = "";
        if (rs.next()) {
            String maxctravao = rs.getString("MaxCTRaVao");
            id = getID(maxctravao);
        }
        return "DT" + id;
    }
}
