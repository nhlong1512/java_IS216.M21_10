/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package UITParking.DTO;

/**
 *
 * @author ADMIN
 */
public class CTHDMuaVeDTO {
    private String strMaHD, strMaLoaiVe;
    private long longSoLuongVe;

    public CTHDMuaVeDTO() {
    }

    public CTHDMuaVeDTO(String strMaHD, String strMaLoaiVe, long longSoLuongVe) {
        this.strMaHD = strMaHD;
        this.strMaLoaiVe = strMaLoaiVe;
        this.longSoLuongVe = longSoLuongVe;
    }

    @Override
    public String toString() {
        return "CTHDMuaVeDTO{" + "strMaHD=" + strMaHD + ", strMaLoaiVe=" + strMaLoaiVe + ", longSoLuongVe=" + longSoLuongVe + '}';
    }

    public String getStrMaHD() {
        return strMaHD;
    }

    public void setStrMaHD(String strMaHD) {
        this.strMaHD = strMaHD;
    }

    public String getStrMaLoaiVe() {
        return strMaLoaiVe;
    }

    public void setStrMaLoaiVe(String strMaLoaiVe) {
        this.strMaLoaiVe = strMaLoaiVe;
    }

    public long getLongSoLuongVe() {
        return longSoLuongVe;
    }

    public void setLongSoLuongVe(long longSoLuongVe) {
        this.longSoLuongVe = longSoLuongVe;
    }
    
    
}
