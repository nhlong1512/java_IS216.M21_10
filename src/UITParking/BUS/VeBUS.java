/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package UITParking.BUS;

import UITParking.DAO.HDMuaVeDAO;
import UITParking.DAO.VeDAO;
import UITParking.DTO.VeDTO;
import java.util.ArrayList;

/**
 *
 * @author ADMIN
 */
public class VeBUS {
    private ArrayList<VeDTO> list_Ve;
    /**
     * Xử lý các lệnh trong SQL
     */
    private VeDAO veDAO;

    public VeBUS() throws Exception {
        list_Ve = new ArrayList<>();
        veDAO = new VeDAO();
        list_Ve = veDAO.docDB();
    }

    public int getNumbVe() {
        return list_Ve.size();
    }
    
    public String getMaxMaVe() throws Exception{
        veDAO = new VeDAO();
        return veDAO.getMaxMaVe();
    }

    public ArrayList<VeDTO> getList_Ve() {
        return list_Ve;
    }

    public void setList_Ve(ArrayList<VeDTO> list_Ve) {
        this.list_Ve = list_Ve;
    }

    public VeDTO getInfor(String strMaVe) {
        for (VeDTO ve : list_Ve) {
            if (ve.getStrMaVe().equals(strMaVe)) {
                return ve;
            }
        }

        return null;
    }

    /**
     * thêm 1 người dùng vào danh sách và database
     *
     * @return true nếu thành công
     */
    public Boolean them(VeDTO ve) throws Exception {
        if (veDAO.them(ve)) {
            list_Ve.add(ve);
        }
        return false;
    }

    /**
     * xóa 1 người dùng khỏi danh sách và database
     *
     * @return true nếu thành công
     */
    public Boolean xoa(VeDTO ve) throws Exception {
        if (veDAO.xoa(ve)) {

            // duyệt từng phẩn tử
            for (VeDTO taikhoan : list_Ve) {
                if (taikhoan.getStrMaKH().equals(ve.getStrMaKH())) {
                    list_Ve.remove(taikhoan);
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
    public Boolean sua(VeDTO ve) throws Exception {
        veDAO.sua(ve);

        // duyệt từng phẩn tử
        for (VeDTO taikhoan : list_Ve) {
            if (taikhoan.getStrMaVe().equals(ve.getStrMaVe())) {
                taikhoan.setStrMaLoaiVe(ve.getStrMaLoaiVe());
                taikhoan.setStrMaKH(ve.getStrMaKH());
                taikhoan.setStrNgayKichHoat(ve.getStrNgayKichHoat());
                taikhoan.setStrNgayHetHan(ve.getStrNgayHetHan());
                taikhoan.setStrTrangThai(ve.getStrTrangThai());
                return true;
            }

        }

        return false;
    }
}
