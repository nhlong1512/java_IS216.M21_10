/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package UITParking.GUI;

import UITParking.DTO.KhachHangDTO;
import java.awt.List;
import java.util.ArrayList;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author ADMIN
 */
public class ClassTableModel {
    public DefaultTableModel setTableKhachHang(ArrayList<KhachHangDTO> listItem, String[] listColumn) {
        int columns = listColumn.length;
        DefaultTableModel dtm = new DefaultTableModel() {
            @Override
            public boolean isCellEditable(int rowIndex, int colIndex) {
                return false;
            }

            @Override
            public Class<?> getColumnClass(int columnIndex) {
                return columnIndex == 7 ? Boolean.class : String.class;
            }
        };
        dtm.setColumnIdentifiers(listColumn);
        Object[] obj;
        int num = listItem.size();
        KhachHangDTO kh = null;
        for (int i = 0; i < num; i++) {
            kh = listItem.get(i);
            obj = new Object[columns];
            obj[0] = kh.getStrMaKH();
            obj[1] = (i + 1);
            obj[2] = kh.getStrMaXe();
            obj[3] = kh.getLongSoDu();
            dtm.addRow(obj);
        }
        return dtm;
    }

}
