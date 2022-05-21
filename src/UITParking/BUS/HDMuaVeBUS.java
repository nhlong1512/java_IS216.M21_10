/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package UITParking.BUS;

import UITParking.DAO.HDMuaVeDAO;
import UITParking.DTO.HDMuaVeDTO;
import java.util.ArrayList;

/**
 *
 * @author ADMIN
 */
public class HDMuaVeBUS {

    private ArrayList<HDMuaVeDTO> list_HD;
    /**
     * Xử lý các lệnh trong SQL
     */
    private HDMuaVeDAO hdDAO;

    public HDMuaVeBUS() throws Exception {
        list_HD = new ArrayList<>();
        hdDAO = new HDMuaVeDAO();
        list_HD = hdDAO.docDB();
    }

    public int getNumbHD() {
        return list_HD.size();
    }
    
    public String getMaxMaHD() throws Exception{
        hdDAO = new HDMuaVeDAO();
        return hdDAO.getMaxMaHD();
    }

    public ArrayList<HDMuaVeDTO> getList_HD() {
        return list_HD;
    }

    public void setList_HD(ArrayList<HDMuaVeDTO> list_HD) {
        this.list_HD = list_HD;
    }

    public HDMuaVeDTO getInfor(String strMaHD) {
        for (HDMuaVeDTO hd : list_HD) {
            if (hd.getStrMaKH().equals(strMaHD)) {
                return hd;
            }
        }

        return null;
    }

    /**
     * thêm 1 người dùng vào danh sách và database
     *
     * @return true nếu thành công
     */
    public Boolean them(HDMuaVeDTO hd) throws Exception {
        if (hdDAO.them(hd)) {
            list_HD.add(hd);
        }
        return false;
    }

    /**
     * xóa 1 người dùng khỏi danh sách và database
     *
     * @return true nếu thành công
     */
    public Boolean xoa(HDMuaVeDTO hd) throws Exception {
        if (hdDAO.xoa(hd)) {

            // duyệt từng phẩn tử
            for (HDMuaVeDTO taikhoan : list_HD) {
                if (taikhoan.getStrMaHD().equals(hd.getStrMaHD())) {
                    list_HD.remove(taikhoan);
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
    public Boolean sua(HDMuaVeDTO hd) throws Exception {
        hdDAO.sua(hd);

        // duyệt từng phẩn tử
        for (HDMuaVeDTO taikhoan : list_HD) {
            if (taikhoan.getStrMaHD().equals(hd.getStrMaHD())) {
                taikhoan.setStrMaKH(hd.getStrMaKH());
                taikhoan.setDateNgayHD(new java.sql.Date(hd.getDateNgayHD().getTime()));
                taikhoan.setLongTongTriGia(hd.getLongTongTriGia());
                return true;
            }

        }

        return false;
    }
}
