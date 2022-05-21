/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package UITParking.DTO;

/**
 *
 * @author Tran Thi Ngoc Anh
 */
public class KhachHangDTO {
    private String strMaKH, strMaXe;
    private long longSoDu;

    public KhachHangDTO() {
    }

    public KhachHangDTO(String strMaKH, String strMaXe, long longSoDu) {
        this.strMaKH = strMaKH;
        this.strMaXe = strMaXe;
        this.longSoDu = longSoDu;
    }

    @Override
    public String toString() {
        return "KhachHangDTO{" + "strMaKH=" + strMaKH + ", strMaXe=" + strMaXe + ", longSoDu=" + longSoDu + '}';
    }
    

    public String getStrMaKH() {
        return strMaKH;
    }

    public void setStrMaKH(String strMaKH) {
        this.strMaKH = strMaKH;
    }

    public String getStrMaXe() {
        return strMaXe;
    }

    public void setStrMaXe(String strMaXe) {
        this.strMaXe = strMaXe;
    }

    public long getLongSoDu() {
        return longSoDu;
    }

    public void setLongSoDu(long longSoDu) {
        this.longSoDu = longSoDu;
    }

    
    
}
