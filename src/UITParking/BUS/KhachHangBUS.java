/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package UITParking.BUS;

import UITParking.DAO.KhachHangDAO;
import UITParking.DAO.NguoiDungDAO;
import UITParking.DTO.KhachHangDTO;
import UITParking.DTO.NguoiDungDTO;
import java.util.ArrayList;

/**
 *
 * @author Tran Thi Ngoc Anh
 */
public class KhachHangBUS {
    private ArrayList<KhachHangDTO> list_KH;
    /**
     * Xử lý các lệnh trong SQL
     */
    private KhachHangDAO khDAO;

    public KhachHangBUS() throws Exception {
        list_KH = new ArrayList<>();
        khDAO = new KhachHangDAO();
        list_KH = khDAO.docDB();
    }

    public int getNumbKH() {
        return list_KH.size();
    }

    public ArrayList<KhachHangDTO> getList_KH() {
        return list_KH;
    }

    public void setList_KH(ArrayList<KhachHangDTO> list_KH) {
        this.list_KH = list_KH;
    }

    public KhachHangDTO getInfor(String strMaKH) {
        for (KhachHangDTO kh : list_KH) {
            if (kh.getStrMaKH().equals(strMaKH)) {
                return kh;
            }
        }

        return null;
    }

    /**
     * thêm 1 người dùng vào danh sách và database
     *
     * @return true nếu thành công
     */
    public Boolean them(KhachHangDTO kh) throws Exception {
        if (khDAO.them(kh)) {
            list_KH.add(kh);
        }
        return false;
    }

    /**
     * xóa 1 người dùng khỏi danh sách và database
     *
     * @return true nếu thành công
     */
    public Boolean xoa(KhachHangDTO kh) throws Exception {
        if (khDAO.xoa(kh)) {

            // duyệt từng phẩn tử
            for (KhachHangDTO taikhoan : list_KH) {
                if (taikhoan.getStrMaKH().equals(kh.getStrMaKH())) {
                    list_KH.remove(taikhoan);
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
    public Boolean sua(KhachHangDTO kh) throws Exception {
        khDAO.sua(kh);

        // duyệt từng phẩn tử
        for (KhachHangDTO taikhoan : list_KH) {
            if (taikhoan.getStrMaKH().equals(kh.getStrMaKH())) {
                taikhoan.setStrMaXe(kh.getStrMaXe());
                taikhoan.setLongSoDu(kh.getLongSoDu());
                return true;
            }

        }

        return false;
    }
}
