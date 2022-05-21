/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package UITParking.BUS;

import UITParking.DAO.LoaiVeDAO;
import UITParking.DTO.LoaiVeDTO;
import java.util.ArrayList;

/**
 *
 * @author ADMIN
 */
public class LoaiVeBUS {

    private ArrayList<LoaiVeDTO> list_LV;
    /**
     * Xử lý các lệnh trong SQL
     */
    private LoaiVeDAO lvDAO;

    public LoaiVeBUS() throws Exception {
        list_LV = new ArrayList<>();
        lvDAO = new LoaiVeDAO();
        list_LV = lvDAO.docDB();
    }

    public int getNumbLV() {
        return list_LV.size();
    }

    public ArrayList<LoaiVeDTO> getList_LV() {
        return list_LV;
    }

    public void setList_LV(ArrayList<LoaiVeDTO> list_LV) {
        this.list_LV = list_LV;
    }

    public LoaiVeDTO getInfor(String strMaLoaiVe) {
        for (LoaiVeDTO lv : list_LV) {
            if (lv.getStrMaLoaiVe().equals(strMaLoaiVe)) {
                return lv;
            }
        }

        return null;
    }

    /**
     * thêm 1 người dùng vào danh sách và database
     *
     * @return true nếu thành công
     */
    public Boolean them(LoaiVeDTO lv) throws Exception {
        if (lvDAO.them(lv)) {
            list_LV.add(lv);
        }
        return false;
    }

    /**
     * xóa 1 người dùng khỏi danh sách và database
     *
     * @return true nếu thành công
     */
    public Boolean xoa(LoaiVeDTO lv) throws Exception {
        if (lvDAO.xoa(lv)) {

            // duyệt từng phẩn tử
            for (LoaiVeDTO taikhoan : list_LV) {
                if (taikhoan.getStrMaLoaiVe().equals(lv.getStrMaLoaiVe())) {
                    list_LV.remove(taikhoan);
                    break;
                }
            }
        }

        return false;
    }

    /**
     * sửa thông tin của 1 khách hàng <br>
     * - Trừ thông tin đăng nhập của khách hàng đó
     *
     * @return true nếu thực hiện thành công
     */
    public Boolean sua(LoaiVeDTO lv) throws Exception {
        lvDAO.sua(lv);

        // duyệt từng phẩn tử
        for (LoaiVeDTO taikhoan : list_LV) {
            if (taikhoan.getStrMaLoaiVe().equals(lv.getStrMaLoaiVe())) {
                taikhoan.setStrTenLoaiVe(lv.getStrTenLoaiVe());
                taikhoan.setLongGiaVe(lv.getLongGiaVe());
                return true;
            }

        }

        return false;
    }
}
