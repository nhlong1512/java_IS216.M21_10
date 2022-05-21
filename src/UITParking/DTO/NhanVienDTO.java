/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package UITParking.DTO;

import java.util.Objects;

/**
 *
 * @author ADMIN
 */
public class NhanVienDTO {
    private String strMaNV, strViTriNhanVien;

    public NhanVienDTO() {
    }

    public NhanVienDTO(String strMaNV, String strViTriNhanVien) {
        this.strMaNV = strMaNV;
        this.strViTriNhanVien = strViTriNhanVien;
    }

    @Override
    public String toString() {
        return "NhanVienDTO{" + "strMaNV=" + strMaNV + ", strViTriNhanVien=" + strViTriNhanVien + '}';
    }

    public String getStrMaNV() {
        return strMaNV;
    }

    public void setStrMaNV(String strMaNV) {
        this.strMaNV = strMaNV;
    }

    public String getStrViTriNhanVien() {
        return strViTriNhanVien;
    }

    public void setStrViTriNhanVien(String strViTriNhanVien) {
        this.strViTriNhanVien = strViTriNhanVien;
    }

}
