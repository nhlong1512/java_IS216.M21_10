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
public class CTRaVaoDTO {
    String strMaCTRaVao;
    Date dateThoiGianVao, dateThoiGianRa;
    String strMaNV, strMaKH, strMaXe, strMaTheKVL;

    public CTRaVaoDTO() {
    }
    
    

    public CTRaVaoDTO(String strMaCTRaVao, Date dateThoiGianVao, Date dateThoiGianRa, String strMaNV, String strMaKH, String strMaXe, String strMaTheKVL) {
        this.strMaCTRaVao = strMaCTRaVao;
        this.dateThoiGianVao = dateThoiGianVao;
        this.dateThoiGianRa = dateThoiGianRa;
        this.strMaNV = strMaNV;
        this.strMaKH = strMaKH;
        this.strMaXe = strMaXe;
        this.strMaTheKVL = strMaTheKVL;
    }

    @Override
    public String toString() {
        return "CTRaVaoDTO{" + "strMaCTRaVao=" + strMaCTRaVao + ", dateThoiGianVao=" + dateThoiGianVao + ", dateThoiGianRa=" + dateThoiGianRa + ", strMaNV=" + strMaNV + ", strMaKH=" + strMaKH + ", strMaXe=" + strMaXe + ", strMaTheKVL=" + strMaTheKVL + '}';
    }

    public String getStrMaCTRaVao() {
        return strMaCTRaVao;
    }

    public void setStrMaCTRaVao(String strMaCTRaVao) {
        this.strMaCTRaVao = strMaCTRaVao;
    }

    public Date getDateThoiGianVao() {
        return dateThoiGianVao;
    }

    public void setDateThoiGianVao(Date dateThoiGianVao) {
        this.dateThoiGianVao = dateThoiGianVao;
    }

    public Date getDateThoiGianRa() {
        return dateThoiGianRa;
    }

    public void setDateThoiGianRa(Date dateThoiGianRa) {
        this.dateThoiGianRa = dateThoiGianRa;
    }

    public String getStrMaNV() {
        return strMaNV;
    }

    public void setStrMaNV(String strMaNV) {
        this.strMaNV = strMaNV;
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

    public String getStrMaTheKVL() {
        return strMaTheKVL;
    }

    public void setStrMaTheKVL(String strMaTheKVL) {
        this.strMaTheKVL = strMaTheKVL;
    }
    
}
