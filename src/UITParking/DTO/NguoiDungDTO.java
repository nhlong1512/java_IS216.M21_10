/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package UITParking.DTO;

import java.util.Date;

/**
 *
 * @author Tran Thi Ngoc Anh
 */
public class NguoiDungDTO {
    private String strMaND, strEmail, strMatKhau, strHoTen, strGioiTinh,
            strDiaChi, strQueQuan, strSDT, strVaiTro;
    private Date dateNgSinh;
    
    public NguoiDungDTO() {
    }

    public NguoiDungDTO(String strMaND, String strEmail, String strMatKhau, String strHoTen, String strGioiTinh, String strDiaChi, String strQueQuan, String strSDT, String strVaiTro, Date dateNgSinh) {
        this.strMaND = strMaND;
        this.strEmail = strEmail;
        this.strMatKhau = strMatKhau;
        this.strHoTen = strHoTen;
        this.strGioiTinh = strGioiTinh;
        this.strDiaChi = strDiaChi;
        this.strQueQuan = strQueQuan;
        this.strSDT = strSDT;
        this.strVaiTro = strVaiTro;
        this.dateNgSinh = dateNgSinh;
    }

    public String getStrMaND() {
        return strMaND;
    }

    public void setStrMaND(String strMaND) {
        this.strMaND = strMaND;
    }

    public String getStrEmail() {
        return strEmail;
    }

    public void setStrEmail(String strEmail) {
        this.strEmail = strEmail;
    }

    public String getStrMatKhau() {
        return strMatKhau;
    }

    public void setStrMatKhau(String strMatKhau) {
        this.strMatKhau = strMatKhau;
    }

    public String getStrHoTen() {
        return strHoTen;
    }

    public void setStrHoTen(String strHoTen) {
        this.strHoTen = strHoTen;
    }

    public String getStrGioiTinh() {
        return strGioiTinh;
    }

    public void setStrGioiTinh(String strGioiTinh) {
        this.strGioiTinh = strGioiTinh;
    }

    public String getStrDiaChi() {
        return strDiaChi;
    }

    public void setStrDiaChi(String strDiaChi) {
        this.strDiaChi = strDiaChi;
    }

    public String getStrQueQuan() {
        return strQueQuan;
    }

    public void setStrQueQuan(String strQueQuan) {
        this.strQueQuan = strQueQuan;
    }

    public String getStrSDT() {
        return strSDT;
    }

    public void setStrSDT(String strSDT) {
        this.strSDT = strSDT;
    }

    public String getStrVaiTro() {
        return strVaiTro;
    }

    public void setStrVaiTro(String strVaiTro) {
        this.strVaiTro = strVaiTro;
    }

    public Date getDateNgSinh() {
        return dateNgSinh;
    }

    public void setDateNgSinh(Date dateNgSinh) {
        this.dateNgSinh = dateNgSinh;
    }

    @Override
    public String toString() {
        return "NguoiDungDTO{" + "strMaND=" + strMaND + ", strEmail=" + strEmail + ", strMatKhau=" + strMatKhau + ", strHoTen=" + strHoTen + ", strGioiTinh=" + strGioiTinh + ", strDiaChi=" + strDiaChi + ", strQueQuan=" + strQueQuan + ", strSDT=" + strSDT + ", strVaiTro=" + strVaiTro + ", dateNgSinh=" + dateNgSinh + '}';
    }

    
}
