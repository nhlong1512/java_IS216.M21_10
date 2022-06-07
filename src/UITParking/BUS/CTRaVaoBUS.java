/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package UITParking.BUS;

import UITParking.DAO.CTRaVaoDAO;
import UITParking.DTO.CTRaVaoDTO;
import java.util.ArrayList;

/**
 *
 * @author ADMIN
 */
public class CTRaVaoBUS {

    private ArrayList<CTRaVaoDTO> list_CTRaVao;
    /**
     * Xử lý các lệnh trong SQL
     */
    private CTRaVaoDAO ctrvDAO;

    public CTRaVaoBUS() throws Exception {
        list_CTRaVao = new ArrayList<>();
        ctrvDAO = new CTRaVaoDAO();
        list_CTRaVao = ctrvDAO.docDB();
    }

    public int getNumbCTRaVao() {
        return list_CTRaVao.size();
    }

    public String getMaxMaCTRaVao() throws Exception {
        ctrvDAO = new CTRaVaoDAO();
        return ctrvDAO.getMaxMaCTRaVao();
    }

    public ArrayList<CTRaVaoDTO> getList_CTRV() {
        return list_CTRaVao;
    }

    public void setList_CTRV(ArrayList<CTRaVaoDTO> list_CTRaVao) {
        this.list_CTRaVao = list_CTRaVao;
    }

    public CTRaVaoDTO getInfor(String strMaCTRaVao) {
        for (CTRaVaoDTO ctrv : list_CTRaVao) {
            if (ctrv.getStrMaCTRaVao().equals(strMaCTRaVao)) {
                return ctrv;
            }
        }

        return null;
    }

    /**
     * thêm 1 người dùng vào danh sách và database
     *
     * @return true nếu thành công
     */
    public Boolean them(CTRaVaoDTO ctrv) throws Exception {
        if (ctrvDAO.them(ctrv)) {
            list_CTRaVao.add(ctrv);
        }
        return false;
    }

    /**
     * xóa 1 người dùng khỏi danh sách và database
     *
     * @return true nếu thành công
     */
    public Boolean xoa(CTRaVaoDTO ctrv) throws Exception {
        if (ctrvDAO.xoa(ctrv)) {

            // duyệt từng phẩn tử
            for (CTRaVaoDTO taikhoan : list_CTRaVao) {
                if (taikhoan.getStrMaCTRaVao().equals(ctrv.getStrMaCTRaVao())) {
                    list_CTRaVao.remove(taikhoan);
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
    public Boolean sua(CTRaVaoDTO ctrv) throws Exception {
        ctrvDAO.sua(ctrv);

        // duyệt từng phẩn tử
        for (CTRaVaoDTO taikhoan : list_CTRaVao) {
            if (taikhoan.getStrMaCTRaVao().equals(ctrv.getStrMaCTRaVao())) {
                if (ctrv.getDateThoiGianVao() != null) {
                    taikhoan.setDateThoiGianVao(new java.sql.Date(ctrv.getDateThoiGianVao().getTime()));

                } else {
                    taikhoan.setDateThoiGianVao(null);
                }
                if (ctrv.getDateThoiGianRa() != null) {
                    taikhoan.setDateThoiGianRa(new java.sql.Date(ctrv.getDateThoiGianRa().getTime()));

                } else {
                    taikhoan.setDateThoiGianRa(null);
                }
                taikhoan.setStrMaNV(ctrv.getStrMaNV());
                taikhoan.setStrMaKH(ctrv.getStrMaKH());
                taikhoan.setStrMaXe(ctrv.getStrMaXe());
                taikhoan.setStrMaTheKVL(ctrv.getStrMaTheKVL());

                return true;
            }

        }

        return false;
    }
    
}
