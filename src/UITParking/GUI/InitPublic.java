/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package UITParking.GUI;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.Date;

/**
 *
 * @author ADMIN
 */
public class InitPublic {
    public static String getID(String id) {
        id = id.replaceAll("\\D+", "");
        int id_num = Integer.parseInt(id);
        id_num++;
        String id_format = String.format("%03d", id_num);
        return id_format;
    }
    
    public static String getThoiGianThuc() throws ParseException{
        SimpleDateFormat DateFormat = new SimpleDateFormat("yyyy-MM-dd");
        LocalDate today = LocalDate.now();
        String strToday = today.toString();
        return strToday;
//        Date ngayBDDate = DateFormat.parse(ngayBD.getText());
//            Date ngayKTDate = DateFormat.parse(ngayKT.getText());
//            GiaiDau gd = new GiaiDau();
//            countResult = gd.themGiaiDau(maGiaiText, tenGiaiText, ngayBDDate, ngayKTDate);
    }
    
    public static Date getDateThoiGianThuc() throws ParseException{
        SimpleDateFormat DateFormat = new SimpleDateFormat("yyyy-MM-dd");
        LocalDate today = LocalDate.now();
        String strToday = today.toString();
        Date dateToday = DateFormat.parse(strToday);
        return dateToday;
//        Date ngayBDDate = DateFormat.parse(ngayBD.getText());
//            Date ngayKTDate = DateFormat.parse(ngayKT.getText());
//            GiaiDau gd = new GiaiDau();
//            countResult = gd.themGiaiDau(maGiaiText, tenGiaiText, ngayBDDate, ngayKTDate);
    }
}
