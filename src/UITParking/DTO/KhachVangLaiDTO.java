/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package UITParking.DTO;

/**
 *
 * @author ADMIN
 */
public class KhachVangLaiDTO {
    private String strMaTheKVL, strMaXe;

    public KhachVangLaiDTO() {
    }

    public KhachVangLaiDTO(String strMaTheKVL, String strMaXe) {
        this.strMaTheKVL = strMaTheKVL;
        this.strMaXe = strMaXe;
    }

    @Override
    public String toString() {
        return "KhachVangLaiDTO{" + "strMaTheKVL=" + strMaTheKVL + ", strMaXe=" + strMaXe + '}';
    }

    public String getStrMaTheKVL() {
        return strMaTheKVL;
    }

    public void setStrMaTheKVL(String strMaTheKVL) {
        this.strMaTheKVL = strMaTheKVL;
    }

    public String getStrMaXe() {
        return strMaXe;
    }

    public void setStrMaXe(String strMaXe) {
        this.strMaXe = strMaXe;
    }
    
}
