/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package UITParking.DTO;

import java.util.Date;

/**
 *
 * @author ADMIN
 */
public class HDMuaVeDTO {
    private String strMaHD, strMaKH;
    private Date dateNgayHD;
    private long longTongTriGia;

    public HDMuaVeDTO() {
    }

    public HDMuaVeDTO(String strMaHD, String strMaKH, Date dateNgayHD, long longTongTriGia) {
        this.strMaHD = strMaHD;
        this.strMaKH = strMaKH;
        this.dateNgayHD = dateNgayHD;
        this.longTongTriGia = longTongTriGia;
    }

    @Override
    public String toString() {
        return "HDMuaVeDTO{" + "strMaHD=" + strMaHD + ", strMaKH=" + strMaKH + ", dateNgayHD=" + dateNgayHD + ", longTongTriGia=" + longTongTriGia + '}';
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

    public Date getDateNgayHD() {
        return dateNgayHD;
    }

    public void setDateNgayHD(Date dateNgayHD) {
        this.dateNgayHD = dateNgayHD;
    }

    public long getLongTongTriGia() {
        return longTongTriGia;
    }

    public void setLongTongTriGia(long longTongTriGia) {
        this.longTongTriGia = longTongTriGia;
    }

}
