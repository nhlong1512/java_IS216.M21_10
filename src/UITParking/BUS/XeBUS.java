/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package UITParking.BUS;

import UITParking.DAO.LoaiVeDAO;
import UITParking.DAO.XeDAO;
import UITParking.DTO.LoaiVeDTO;
import UITParking.DTO.XeDTO;
import java.util.ArrayList;

/**
 *
 * @author ADMIN
 */
public class XeBUS {

    private ArrayList<XeDTO> list_XE;
    /**
     * Xử lý các lệnh trong SQL
     */
    private XeDAO xeDAO;

    public XeBUS() throws Exception {
        list_XE = new ArrayList<>();
        xeDAO = new XeDAO();
        list_XE = xeDAO.docDB();
    }

    public int getNumbXE() {
        return list_XE.size();
    }

    public ArrayList<XeDTO> getlist_XE() {
        return list_XE;
    }

    public void setlist_XE(ArrayList<XeDTO> list_XE) {
        this.list_XE = list_XE;
    }

    public XeDTO getInfor(String strMaXe) {
        for (XeDTO xe : list_XE) {
            if (xe.getStrMaXe() != null) {
                if (xe.getStrMaXe().equals(strMaXe)) {
                    return xe;
                }
            }

        }

        return null;
    }

    public XeDTO getInforBienSoXe(String strBienSoXe) {
        for (XeDTO xe : list_XE) {
            if (xe.getStrBienSoXe().equals(strBienSoXe)) {
                return xe;
            }
        }
        return null;
    }

    /**
     * thêm 1 người dùng vào danh sách và database
     *
     * @return true nếu thành công
     */
    public Boolean them(XeDTO xe) throws Exception {
        if (xeDAO.them(xe)) {
            list_XE.add(xe);
        }
        return false;
    }

    /**
     * xóa 1 người dùng khỏi danh sách và database
     *
     * @return true nếu thành công
     */
    public Boolean xoa(XeDTO xe) throws Exception {
        if (xeDAO.xoa(xe)) {

            // duyệt từng phẩn tử
            for (XeDTO taikhoan : list_XE) {
                if (taikhoan.getStrMaXe().equals(xe.getStrMaXe())) {
                    list_XE.remove(taikhoan);
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
    public Boolean sua(XeDTO xe) throws Exception {
        xeDAO.sua(xe);

        // duyệt từng phẩn tử
        for (XeDTO taikhoan : list_XE) {
            if (taikhoan.getStrMaXe().equals(xe.getStrMaXe())) {
                taikhoan.setStrBienSoXe(xe.getStrBienSoXe());
                taikhoan.setStrTenLoaiXe(xe.getStrTenLoaiXe());
                return true;
            }

        }

        return false;
    }
}
