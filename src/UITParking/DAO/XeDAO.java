/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package UITParking.DAO;

import UITParking.DTO.NhanVienDTO;
import UITParking.DTO.XeDTO;
import static UITParking.GUI.InitPublic.getID;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

/**
 *
 * @author ADMIN
 */
public class XeDAO {
    SQLConnectUnit connect;
    public static SQLConnection connection = new SQLConnection("hr", "hr", "orcl");;
    public static PreparedStatement pst = null;

    /**
     * Lấy thông tin từ Database
     */
    public ArrayList<XeDTO> docDB(String condition, String orderBy) throws Exception {
        // kết nối CSDL
        connect = new SQLConnectUnit();

        ResultSet result = this.connect.Select("XE", condition, orderBy);
        ArrayList<XeDTO> xes = new ArrayList<>();
        while (result.next()) {
            
            XeDTO xe = new XeDTO();
            xe.setStrMaXe(result.getString("MaXe"));
            xe.setStrBienSoXe(result.getString("BienSoXe"));
            xe.setStrTenLoaiXe(result.getString("TenLoaiXe"));
            xes.add(xe);
        }
        connect.Close();
        return xes;
    }

    public ArrayList<XeDTO> docDB(String condition) throws Exception {
        return docDB(condition, null);
    }

    public ArrayList<XeDTO> docDB() throws Exception {
        return docDB(null);
    }

    /**
     */
    public Boolean them(XeDTO xe) throws Exception {
        String sql = "INSERT INTO XE (MaXe, BienSoXe, TenLoaiXe)"
                + " VALUES (?, ?, ?)";
        try {
            pst = this.connection.getConnect().prepareStatement(sql);

            pst.setString(1, xe.getStrMaXe());
            pst.setString(2, xe.getStrBienSoXe());
            pst.setString(3, xe.getStrTenLoaiXe());
            return pst.executeUpdate() > 0;
        } catch (SQLException ex) {
            throw new ArithmeticException(ex.getMessage());
        }
    }
    
    /** 
     * @param ve chuyền vào dữ liệu vé để xóa
     * @return true nếu thành công
     */
    public Boolean xoa(XeDTO xe) throws Exception {
        String sql = "DELETE FROM XE WHERE MaXe = ?";
        try {
            pst = this.connection.getConnect().prepareStatement(sql);

            pst.setString(1, xe.getStrMaXe());

            return pst.executeUpdate() > 0;
        } catch (SQLException ex) {
            throw new ArithmeticException(ex.getMessage());
        }
    }
    
    /**
     */
    public Boolean sua(XeDTO xe) throws Exception {
        String sql = "UPDATE XE SET BienSoXe = ?, TenLoaiXe = ? WHERE MaXe = ?";
        try {
            pst = this.connection.getConnect().prepareStatement(sql);

            pst.setString(3, xe.getStrMaXe());
            pst.setString(1, xe.getStrBienSoXe());
            pst.setString(2, xe.getStrTenLoaiXe());
            
            return pst.executeUpdate() > 0;
        } catch (SQLException ex) {
            throw new ArithmeticException(ex.getMessage());
        }
    }
    
    public String getMaxMaXe() throws Exception {
        String sql = "Select Max(MaXe) as MaxXe from XE";
        pst = this.connection.getConnect().prepareStatement(sql);
        ResultSet rs = pst.executeQuery();
        String id = "";
        if (rs.next()) {
            String maxhd = rs.getString("MaxXe");
            id = getID(maxhd);
        }
        return "XE" + id;
    }
}
