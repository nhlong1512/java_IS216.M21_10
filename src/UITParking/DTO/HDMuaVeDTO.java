/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package UITParking.DTO;

/**
 *
 * @author ADMIN
 */
public class HDMuaVeDTO {
    private String strMaHD, strMaKH, strNgayHD;
    private long longTongTriGia;

    public HDMuaVeDTO() {
    }

    public HDMuaVeDTO(String strMaHD, String strMaKH, String strNgayHD, long longTongTriGia) {
        this.strMaHD = strMaHD;
        this.strMaKH = strMaKH;
        this.strNgayHD = strNgayHD;
        this.longTongTriGia = longTongTriGia;
    }

    @Override
    public String toString() {
        return "HDMuaVeDTO{" + "strMaHD=" + strMaHD + ", strMaKH=" + strMaKH + ", strNgayHD=" + strNgayHD + ", longTongTriGia=" + longTongTriGia + '}';
    }

    public String getStrMaHD() {
        return strMaHD;
    }

    public void setStrMaHD(String strMaHD) {
        this.strMaHD = strMaHD;
    }

    public String getStrMaKH() {
        return strMaKH;
    }

    public void setStrMaKH(String strMaKH) {
        this.strMaKH = strMaKH;
    }

    public String getStrNgayHD() {
        return strNgayHD;
    }

    public void setStrNgayHD(String strNgayHD) {
        this.strNgayHD = strNgayHD;
    }

    public long getLongTongTriGia() {
        return longTongTriGia;
    }

    public void setLongTongTriGia(long longTongTriGia) {
        this.longTongTriGia = longTongTriGia;
    }
    
    
}
