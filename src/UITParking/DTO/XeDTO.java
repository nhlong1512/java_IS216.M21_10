/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package UITParking.DTO;

/**
 *
 * @author ADMIN
 */
public class XeDTO {
    private String strMaXe, strBienSoXe, strTenLoaiXe;

    public XeDTO() {
    }

    public XeDTO(String strMaXe, String strBienSoXe, String strTenLoaiXe) {
        this.strMaXe = strMaXe;
        this.strBienSoXe = strBienSoXe;
        this.strTenLoaiXe = strTenLoaiXe;
    }

    @Override
    public String toString() {
        return "XeDTO{" + "strMaXe=" + strMaXe + ", strBienSoXe=" + strBienSoXe + ", strTenLoaiXe=" + strTenLoaiXe + '}';
    }

    public String getStrMaXe() {
        return strMaXe;
    }

    public void setStrMaXe(String strMaXe) {
        this.strMaXe = strMaXe;
    }

    public String getStrBienSoXe() {
        return strBienSoXe;
    }

    public void setStrBienSoXe(String strBienSoXe) {
        this.strBienSoXe = strBienSoXe;
    }

    public String getStrTenLoaiXe() {
        return strTenLoaiXe;
    }

    public void setStrTenLoaiXe(String strTenLoaiXe) {
        this.strTenLoaiXe = strTenLoaiXe;
    }
    
    
}
