/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package UITParking.DTO;

/**
 *
 * @author ADMIN
 */
public class LoaiVeDTO {
    private String strMaLoaiVe, strTenLoaiVe;
    private long longGiaVe;

    public LoaiVeDTO() {
    }

    public LoaiVeDTO(String strMaLoaiVe, String strTenLoaiVe, long longGiaVe) {
        this.strMaLoaiVe = strMaLoaiVe;
        this.strTenLoaiVe = strTenLoaiVe;
        this.longGiaVe = longGiaVe;
    }

    @Override
    public String toString() {
        return "LoaiVeDTO{" + "strMaLoaiVe=" + strMaLoaiVe + ", strTenLoaiVe=" + strTenLoaiVe + ", longGiaVe=" + longGiaVe + '}';
    }

    public String getStrMaLoaiVe() {
        return strMaLoaiVe;
    }

    public void setStrMaLoaiVe(String strMaLoaiVe) {
        this.strMaLoaiVe = strMaLoaiVe;
    }

    public String getStrTenLoaiVe() {
        return strTenLoaiVe;
    }

    public void setStrTenLoaiVe(String strTenLoaiVe) {
        this.strTenLoaiVe = strTenLoaiVe;
    }

    public long getLongGiaVe() {
        return longGiaVe;
    }

    public void setLongGiaVe(long longGiaVe) {
        this.longGiaVe = longGiaVe;
    }
    
    
}
