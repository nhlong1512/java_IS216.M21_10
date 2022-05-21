/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package UITParking.BUS;

import UITParking.DAO.CTHDMuaVeDAO;
import UITParking.DAO.HDMuaVeDAO;
import UITParking.DTO.CTHDMuaVeDTO;
import UITParking.DTO.HDMuaVeDTO;
import java.util.ArrayList;

/**
 *
 * @author ADMIN
 */
public class CTHDMuaVeBUS {

    private ArrayList<CTHDMuaVeDTO> list_CTHD;
    /**
     * Xử lý các lệnh trong SQL
     */
    private CTHDMuaVeDAO cthdDAO;

    public CTHDMuaVeBUS() throws Exception {
        list_CTHD = new ArrayList<>();
        cthdDAO = new CTHDMuaVeDAO();
        list_CTHD = cthdDAO.docDB();
    }

    public int getNumbCTHD() {
        return list_CTHD.size();
    }

    public ArrayList<CTHDMuaVeDTO> getlist_CTHD() {
        return list_CTHD;
    }

    public void setlist_CTHD(ArrayList<CTHDMuaVeDTO> list_CTHD) {
        this.list_CTHD = list_CTHD;
    }

    public CTHDMuaVeDTO getInfor(String strMaHD, String strMaLoaiVe) {
        for (CTHDMuaVeDTO cthd : list_CTHD) {
            if (cthd.getStrMaHD().equals(strMaHD) && cthd.getStrMaLoaiVe().equals(strMaLoaiVe)) {
                return cthd;
            }
        }

        return null;
    }

    /**
     * thêm 1 người dùng vào danh sách và database
     *
     * @return true nếu thành công
     */
    public Boolean them(CTHDMuaVeDTO cthd) throws Exception {
        if (cthdDAO.them(cthd)) {
            list_CTHD.add(cthd);
        }
        return false;
    }

    /**
     * xóa 1 người dùng khỏi danh sách và database
     *
     * @return true nếu thành công
     */
    public Boolean xoa(CTHDMuaVeDTO cthd) throws Exception {
        if (cthdDAO.xoa(cthd)) {

            // duyệt từng phẩn tử
            for (CTHDMuaVeDTO taikhoan : list_CTHD) {
                if (taikhoan.getStrMaHD().equals(cthd.getStrMaHD())
                        && taikhoan.getStrMaLoaiVe().equals(cthd.getStrMaLoaiVe())) {
                    list_CTHD.remove(taikhoan);
                    break;
                }
            }
        }

        return false;
    }

    /**
     */
    public Boolean sua(CTHDMuaVeDTO cthd) throws Exception {
        cthdDAO.sua(cthd);

        // duyệt từng phẩn tử
        for (CTHDMuaVeDTO taikhoan : list_CTHD) {
            if (taikhoan.getStrMaHD().equals(cthd.getStrMaHD())
                    && taikhoan.getStrMaLoaiVe().equals(cthd.getStrMaLoaiVe())) {
                taikhoan.setLongSoLuongVe(cthd.getLongSoLuongVe());
                return true;
            }

        }

        return false;
    }
}
