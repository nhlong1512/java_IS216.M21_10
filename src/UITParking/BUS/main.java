/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package UITParking.BUS;

import UITParking.DTO.NguoiDungDTO;
import UITParking.DAO.NguoiDungDAO;
import UITParking.DTO.CTHDMuaVeDTO;
import UITParking.DTO.HDMuaVeDTO;
import UITParking.DTO.KhachHangDTO;
import UITParking.DTO.KhachVangLaiDTO;
import UITParking.DTO.LoaiVeDTO;
import UITParking.DTO.NhanVienDTO;
import UITParking.DTO.VeDTO;
import UITParking.DTO.XeDTO;
import static UITParking.GUI.InitPublic.getDateThoiGianThuc;
import static UITParking.GUI.InitPublic.getThoiGianThuc;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;

/**
 *
 * @author Nguyen Huu Long
 */
public class main {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) throws Exception {
        // TODO code application logic here
//        NguoiDungDTO nd022 = new NguoiDungDTO("ND022", "huulong22@gmail.com", "longlong", "Nguyen Huu Long", "Nam", "19-MAR-02", "28 Nguyen Thi Ly phuong 2", "TP. Quang Tri", "0775504619", "Khach hang");
//        NguoiDungBUS nguoidungtbl = new NguoiDungBUS();
//        System.out.println(nguoidungtbl.getNumbND());
//        nguoidungtbl.them(nd022);
//        System.out.println(nguoidungtbl.getInfor("ND015"));
//        NguoiDungDTO nd015 = new NguoiDungDTO("ND015", "20521083@gmail.uit.edu.vn", "ttna0000", "Tran Thi Ngoc Anh", "Nu", "19-MAR-02", "28 Nguyen Thi Ly, phuong 2", "TP. Quang Tri", "0985766322", "Khach hang");
//        nguoidungtbl.xoa(nd015);
//        System.out.println(nguoidungtbl.getNumbND());
//        ArrayList<NguoiDungDTO> list_ND = nguoidungtbl.getList_ND();
//        for (NguoiDungDTO nd : list_ND) {
//            System.out.println(nd);
//        }
//        list_ND.add(nd019);
//        for (NguoiDungDTO nd : list_ND) {
//            System.out.println("New----" + nd);
//        }

//        nguoidungtbl.xoa(nd022);
//        for (NguoiDungDTO nd : list_ND) {
//            System.out.println("New----" + nd);
//        }
//        
//        nd022 = new NguoiDungDTO("ND022", "huulong232@gmail.com", "longrong", "Nguyen Huu Lodfsdfsdfdsg", "Nam", "19-MAR-02", "28 Nguyen Thi Ly phuong 2", "TP. Quang Tri", "0775504619", "Khach hang");
//        nguoidungtbl.sua(nd022);
//        for (NguoiDungDTO nd : list_ND) {
//            System.out.println("New----" + nd);
//        } 
//          NguoiDungDTO nd = nguoidungtbl.getInfor("ND015"); 
//          System.out.println(nd.getStrHoTen());
//        KhachHangBUS khachhangtbl = new KhachHangBUS();
//        System.out.println(khachhangtbl.getNumbKH());
//
//        VeBUS vetbl = new VeBUS();
//        System.out.println(vetbl.getNumbVe());
//
//        ArrayList<VeDTO> list_Ve = vetbl.getList_Ve();
//        for (VeDTO ve : list_Ve) {
//            System.out.println("New----" + ve);
//        }
//
//        LoaiVeBUS loaivetbl = new LoaiVeBUS();
//        System.out.println(loaivetbl.getNumbLV());
//
//        ArrayList<LoaiVeDTO> list_LV = loaivetbl.getList_LV();
//        for (LoaiVeDTO ve : list_LV) {
//            System.out.println("New----" + ve);
//        }
//
//        HDMuaVeBUS hdmuavetbl = new HDMuaVeBUS();
//        System.out.println(hdmuavetbl.getNumbHD());
//        ArrayList<HDMuaVeDTO> list_HD = hdmuavetbl.getList_HD();
//        for (HDMuaVeDTO ve : list_HD) {
//            System.out.println("New----" + ve);
//        }
//
//        CTHDMuaVeBUS cthdmuavetbl = new CTHDMuaVeBUS();
//        System.out.println(cthdmuavetbl.getNumbCTHD());
//        ArrayList<CTHDMuaVeDTO> list_CTHD = cthdmuavetbl.getlist_CTHD();
//        for (CTHDMuaVeDTO ve : list_CTHD) {
//            System.out.println("New----" + ve);
//        }
//
//        NhanVienBUS nhanvientbl = new NhanVienBUS();
//        System.out.println(nhanvientbl.getNumbNV());
//        ArrayList<NhanVienDTO> list_NV = nhanvientbl.getlist_NV();
//        for (NhanVienDTO ve : list_NV) {
//            System.out.println("New----" + ve);
//        }
//        
//        KhachVangLaiBUS khachvanglaitbl = new KhachVangLaiBUS();
//        System.out.println(khachvanglaitbl.getNumbKVL());
//        ArrayList<KhachVangLaiDTO> list_KVL = khachvanglaitbl.getlist_KVL();
//        for (KhachVangLaiDTO ve : list_KVL) {
//            System.out.println("New----" + ve);
//        }
//
//        XeBUS xetbl = new XeBUS();
//        System.out.println(xetbl.getNumbXE());
//        ArrayList<XeDTO> list_XE = xetbl.getlist_XE();
//        for (XeDTO ve : list_XE) {
//            System.out.println("New----" + ve);
//        }
//        
//        
//        System.out.println("Mã hóa đơn tiếp theo là " + hdmuavetbl.getMaxMaHD());
//        
//        
////        HDMuaVeDTO hd = new HDMuaVeDTO(hdmuavetbl.getMaxMaHD(), "ND014", getDateThoiGianThuc(), 50000);
////        hdmuavetbl.them(hd);
//        System.out.println("Thoi gian thuc la: " + getThoiGianThuc());
//        for (HDMuaVeDTO ve : list_HD) {
//            System.out.println("Sau khi them----" + ve);
//        }
//        
//        CTHDMuaVeDTO cthd = new CTHDMuaVeDTO("HD015", "LVE02", 5);
//        cthdmuavetbl.them(cthd);
//        for (CTHDMuaVeDTO ve : list_CTHD) {
//            System.out.println("Sau khi them----" + ve);
//        }
//        
//        System.out.println("Mã vé tiếp theo là " + vetbl.getMaxMaVe());
        
        KhachHangBUS list_KH = new KhachHangBUS();
        ArrayList<KhachHangDTO> listItem = list_KH.getList_KH();
        for(KhachHangDTO t : listItem){
            System.out.println(t);
        }
        System.out.println(list_KH.getNumbKH());
    }
}
