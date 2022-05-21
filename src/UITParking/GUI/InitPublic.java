/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package UITParking.GUI;

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
    
}
