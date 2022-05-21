/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package UITParking.BUS;

import UITParking.DAO.KhachVangLaiDAO;
import UITParking.DAO.NhanVienDAO;
import UITParking.DTO.KhachVangLaiDTO;
import UITParking.DTO.NhanVienDTO;
import java.util.ArrayList;

/**
 *
 * @author ADMIN
 */
public class KhachVangLaiBUS {
    private ArrayList<KhachVangLaiDTO> list_KVL;
    /**
     * Xử lý các lệnh trong SQL
     */
    private KhachVangLaiDAO kvlDAO;

    public KhachVangLaiBUS() throws Exception {
        list_KVL = new ArrayList<>();
        kvlDAO = new KhachVangLaiDAO();
        list_KVL = kvlDAO.docDB();
    }

    public int getNumbKVL() {
        return list_KVL.size();
    }

    public ArrayList<KhachVangLaiDTO> getlist_KVL() {
        return list_KVL;
    }

    public void setlist_KVL(ArrayList<KhachVangLaiDTO> list_KVL) {
        this.list_KVL = list_KVL;
    }

    public KhachVangLaiDTO getInfor(String strMaTheKVL) {
        for (KhachVangLaiDTO kvl : list_KVL) {
            if (kvl.getStrMaTheKVL().equals(strMaTheKVL)) {
                return kvl;
            }
        }

        return null;
    }

    /**
     * thêm 1 người dùng vào danh sách và database
     *
     * @return true nếu thành công
     */
    public Boolean them(KhachVangLaiDTO kvl) throws Exception {
        if (kvlDAO.them(kvl)) {
            list_KVL.add(kvl);
        }
        return false;
    }

    /**
     * xóa 1 người dùng khỏi danh sách và database
     *
     * @return true nếu thành công
     */
    public Boolean xoa(KhachVangLaiDTO kvl) throws Exception {
        if (kvlDAO.xoa(kvl)) {

            // duyệt từng phẩn tử
            for (KhachVangLaiDTO taikhoan : list_KVL) {
                if (taikhoan.getStrMaTheKVL().equals(kvl.getStrMaTheKVL())) {
                    list_KVL.remove(taikhoan);
                    break;
                }
            }
        }

        return false;
    }

    /**
     */
    public Boolean sua(KhachVangLaiDTO kvl) throws Exception {
        kvlDAO.sua(kvl);

        // duyệt từng phẩn tử
        for (KhachVangLaiDTO taikhoan : list_KVL) {
            if (taikhoan.getStrMaTheKVL().equals(kvl.getStrMaTheKVL())) {
                taikhoan.setStrMaXe(kvl.getStrMaXe());
                return true;
            }

        }

        return false;
    }
}
