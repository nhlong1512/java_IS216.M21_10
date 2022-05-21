/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package UITParking.DTO;

/**
 *
 * @author ADMIN
 */
public class VeDTO {
    private String strMaVe, strMaLoaiVe, strMaKH, strNgayKichHoat, strNgayHetHan, strTrangThai;

    public VeDTO() {
    }

    public VeDTO(String strMaVe, String strMaLoaiVe, String strMaKH, String strNgayKichHoat, String strNgayHetHan, String strTrangThai) {
        this.strMaVe = strMaVe;
        this.strMaLoaiVe = strMaLoaiVe;
        this.strMaKH = strMaKH;
        this.strNgayKichHoat = strNgayKichHoat;
        this.strNgayHetHan = strNgayHetHan;
        this.strTrangThai = strTrangThai;
    }

    @Override
    public String toString() {
        return "VeDTO{" + "strMaVe=" + strMaVe + ", strMaLoaiVe=" + strMaLoaiVe + ", strMaKH=" + strMaKH + ", strNgayKichHoat=" + strNgayKichHoat + ", strNgayHetHan=" + strNgayHetHan + ", strTrangThai=" + strTrangThai + '}';
    }

    public String getStrMaVe() {
        return strMaVe;
    }

    public void setStrMaVe(String strMaVe) {
        this.strMaVe = strMaVe;
    }

    public String getStrMaLoaiVe() {
        return strMaLoaiVe;
    }

    public void setStrMaLoaiVe(String strMaLoaiVe) {
        this.strMaLoaiVe = strMaLoaiVe;
    }

    public String getStrMaKH() {
        return strMaKH;
    }

    public void setStrMaKH(String strMaKH) {
        this.strMaKH = strMaKH;
    }

    public String getStrNgayKichHoat() {
        return strNgayKichHoat;
    }

    public void setStrNgayKichHoat(String strNgayKichHoat) {
        this.strNgayKichHoat = strNgayKichHoat;
    }

    public String getStrNgayHetHan() {
        return strNgayHetHan;
    }

    public void setStrNgayHetHan(String strNgayHetHan) {
        this.strNgayHetHan = strNgayHetHan;
    }

    public String getStrTrangThai() {
        return strTrangThai;
    }

    public void setStrTrangThai(String strTrangThai) {
        this.strTrangThai = strTrangThai;
    }
    
    
}
