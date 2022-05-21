/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package UITParking.BUS;

import UITParking.DAO.LoaiVeDAO;
import UITParking.DAO.NhanVienDAO;
import UITParking.DTO.LoaiVeDTO;
import UITParking.DTO.NhanVienDTO;
import java.util.ArrayList;

/**
 *
 * @author ADMIN
 */
public class NhanVienBUS {
    private ArrayList<NhanVienDTO> list_NV;
    /**
     * Xử lý các lệnh trong SQL
     */
    private NhanVienDAO nvDAO;

    public NhanVienBUS() throws Exception {
        list_NV = new ArrayList<>();
        nvDAO = new NhanVienDAO();
        list_NV = nvDAO.docDB();
    }

    public int getNumbNV() {
        return list_NV.size();
    }

    public ArrayList<NhanVienDTO> getlist_NV() {
        return list_NV;
    }

    public void setlist_NV(ArrayList<NhanVienDTO> list_NV) {
        this.list_NV = list_NV;
    }

    public NhanVienDTO getInfor(String strMaNV) {
        for (NhanVienDTO nv : list_NV) {
            if (nv.getStrMaNV().equals(strMaNV)) {
                return nv;
            }
        }

        return null;
    }

    /**
     * thêm 1 người dùng vào danh sách và database
     *
     * @return true nếu thành công
     */
    public Boolean them(NhanVienDTO nv) throws Exception {
        if (nvDAO.them(nv)) {
            list_NV.add(nv);
        }
        return false;
    }

    /**
     * xóa 1 người dùng khỏi danh sách và database
     *
     * @return true nếu thành công
     */
    public Boolean xoa(NhanVienDTO nv) throws Exception {
        if (nvDAO.xoa(nv)) {

            // duyệt từng phẩn tử
            for (NhanVienDTO taikhoan : list_NV) {
                if (taikhoan.getStrMaNV().equals(nv.getStrMaNV())) {
                    list_NV.remove(taikhoan);
                    break;
                }
            }
        }

        return false;
    }

    /**
     */
    public Boolean sua(NhanVienDTO nv) throws Exception {
        nvDAO.sua(nv);

        // duyệt từng phẩn tử
        for (NhanVienDTO taikhoan : list_NV) {
            if (taikhoan.getStrMaNV().equals(nv.getStrMaNV())) {
                taikhoan.setStrViTriNhanVien(nv.getStrViTriNhanVien());
                return true;
            }

        }

        return false;
    }
}
