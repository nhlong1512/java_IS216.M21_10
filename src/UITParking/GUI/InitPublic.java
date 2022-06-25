/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package UITParking.GUI;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.Instant;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.util.Calendar;
import java.util.Date;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 *
 * @author ADMIN
 */
public class InitPublic {

    public static java.util.Date sysdate() {
        return new java.util.Date();
    }

    public static String getID(String id) {
        id = id.replaceAll("\\D+", "");
        int id_num = Integer.parseInt(id);
        id_num++;
        String id_format = String.format("%03d", id_num);
        return id_format;
    }

    public static String getThoiGianThuc() throws ParseException {
        SimpleDateFormat DateFormat = new SimpleDateFormat("yyyy-MM-dd");
        LocalDate today = LocalDate.now();
        String strToday = today.toString();
        return strToday;
    }

    public static Date getDateThoiGianThuc() throws ParseException {
        SimpleDateFormat DateFormat = new SimpleDateFormat("yyyy-MM-dd");
        LocalDate today = LocalDate.now();
        String strToday = today.toString();
        Date dateToday = DateFormat.parse(strToday);
        return dateToday;
    }

    public static Date getDateThoiGianVeTuan() throws ParseException {
        Date dt = new Date();
        System.out.println("Today:    " + dt);
        Calendar c = Calendar.getInstance();
        c.setTime(dt);
        c.add(Calendar.DATE, 7);
        dt = c.getTime();
        return dt;
    }

    public static Date getDateThoiGianVeThang() throws ParseException {
        Date dt = new Date();
        Calendar c = Calendar.getInstance();
        c.setTime(dt);
        c.add(Calendar.DATE, 30);
        dt = c.getTime();
        return dt;
    }

    public static Date getConvertYYYYMMDD(String data) throws ParseException {
        SimpleDateFormat DateFormat = new SimpleDateFormat("yyyy-MM-dd");
        Date rs = DateFormat.parse(data);
        return rs;
    }

    public static String getStringConvertYYYYMMDD(String data) throws ParseException {
        SimpleDateFormat DateFormat = new SimpleDateFormat("yyyy-MM-dd");
        Date rs = DateFormat.parse(data);
        return rs.toString();

    }

    public static String formatDate(Date date) {
        SimpleDateFormat sdfDate = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");//dd/MM/yyyy

        String strDate = sdfDate.format(date);
        return strDate;
    }

//    public static Date getDateTimeThoiGianThuc() {
//        java.util.Date utilDate = new java.util.Date();
//        LocalDateTime dateValue = utilDate;
//        String dateFormat = "yyyy-MM-dd'T'HH:mm:ss";
//        DateTimeFormatter dtf1 = DateTimeFormatter.ofPattern(dateFormat);
//        SimpleDateFormat sdf1 = new SimpleDateFormat(dateFormat);
//        try {
//            utilDate = sdf1.parse(dateValue.format(dtf1));
//        } catch (ParseException e) {
//            utilDate = null; // handle the exception
//        }
//        java.sql.Date sqlDate = new java.sql.Date(utilDate.getTime());
//    }
    public static LocalDate getConvertStringToLocalDate(String data) {
        LocalDate localDate = LocalDate.parse(data, DateTimeFormatter.ISO_LOCAL_DATE);
        return localDate;
    }

    public LocalDate convertDatetoLocalDate(Date now) {
        Instant instant = Instant.ofEpochMilli(now.getTime());
        LocalDate localDate = LocalDateTime.ofInstant(instant, ZoneId.systemDefault()).toLocalDate();
        return localDate;
    }

    /**
     *
     * @param passwordToHash
     * @return Hàm thực thi băm mật khẩu và trả về chuỗi hex gồm 32 kí tự
     */
    public static String getHashPassword(String passwordToHash) {
        String generatedPassword = null;

        try {
            // Create MessageDigest instance for MD5
            MessageDigest md = MessageDigest.getInstance("MD5");

            // Add password bytes to digest
            md.update(passwordToHash.getBytes());

            // Get the hash's bytes
            byte[] bytes = md.digest();

            // This bytes[] has bytes in decimal format. Convert it to hexadecimal format
            StringBuilder sb = new StringBuilder();
            for (int i = 0; i < bytes.length; i++) {
                sb.append(Integer.toString((bytes[i] & 0xff) + 0x100, 16).substring(1));
            }

            // Get complete hashed password in hex format
            generatedPassword = sb.toString();
            System.out.println(generatedPassword.length());
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }
        return generatedPassword;
    }

//    void checkNgaySinh() {
//        Calendar c1 = Calendar.getInstance();
//        c1.set(Calendar.MONTH, 5);
//
//        // set Date
//        c1.set(Calendar.DATE, 26);
//
//        // set Year
//        c1.set(Calendar.YEAR, 2005);
//
//        Date dateToday = c1.getTime();
//        if (jdcNgaySinh.getDate().getTime() < dateToday.getTime()) {
//            sb.append("Người dùng chưa đủ 17 tuổi");
//        }
//    }

}
