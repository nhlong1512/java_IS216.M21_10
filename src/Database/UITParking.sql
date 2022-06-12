--drop table nguoidung cascade CONSTRAINTS;
--drop table xe cascade CONSTRAINTS;
--drop table nhanvien cascade CONSTRAINTS;
--drop table khachhang cascade CONSTRAINTS;
--drop table c_ve cascade CONSTRAINTS;
--drop table loaive cascade CONSTRAINTS;
--drop table chitietravao cascade CONSTRAINTS;
--drop table khachvanglai cascade CONSTRAINTS;
--drop table chitiethoadonmuave cascade CONSTRAINTS;
--drop table hoadonmuave cascade CONSTRAINTS;
--

-- Tao bang NguoiDung
create table NguoiDung (
    MaND varchar2(5),
    Email varchar2(32) not null,
    MatKhau varchar2(64) not null,
    HoTen varchar2(50),
    GioiTinh varchar2(3),
    NgSinh Date,
    DiaChi varchar2(256),
    QueQuan varchar2(256),
    SDT varchar2(10),
    VaiTro varchar2(10),

    CONSTRAINT PK_MaND PRIMARY KEY (MaND),   
   
    CONSTRAINT check_GioiTinh CHECK (GioiTinh = 'Nam' or GioiTinh = 'Nu'),
    CONSTRAINT check_VaiTro CHECK (VaiTro ='Khach hang' or VaiTro = 'Nhan vien')
);


-- Tao bang Xe
create table Xe (
    MaXe varchar2(5),
    BienSoXe varchar2(30),
    TenLoaiXe Varchar2(20),
    
    CONSTRAINT PK_MaXe PRIMARY KEY (MaXe),
    CONSTRAINT check_TenLoaiXe CHECK (TenLoaiXe in ('Xe dap', 'Xe may'))
);

-- Tao bang NhanVien
create table NhanVien  (
    MaNV varchar2(5),
    ViTriNhanVien varchar2(30),
    
    CONSTRAINT PK_MaNV PRIMARY KEY (MaNV),
    CONSTRAINT check_ViTriNhanVien CHECK (ViTriNhanVien in ('Quan ly', 'Bao ve'))
);


-- Tao bang KhachHang
create table KhachHang (
    MaKH varchar2(5),
    MaXe varchar2(5),
    SoDu Number,
    
    CONSTRAINT PK_MaKH PRIMARY KEY (MAKH)
);


-- Tao bang LoaiVe
create table LoaiVe(
    MaLoaiVe varchar2(5),
    TenLoaiVe varchar2(30),
    GiaVe number,
    
    CONSTRAINT PK_MaLoaiVe PRIMARY KEY (MaLoaiVe)
);



-- Tao bang Ve
create table c_Ve(
    MaVe varchar2(5),
    MaLoaiVe varchar2(5),
    MaKH varchar2(5),
    NgayKichHoat Date,
    NgayHetHan Date,
    TrangThai Varchar(30),
    
    CONSTRAINT PK_MaVe PRIMARY KEY (MaVe),
    CONSTRAINT check_TrangThai CHECK (TrangThai in ('Chua kich hoat', 'Dang su dung', 'Da het han'))
);



-- Tao bang KhachVangLai
create table KhachVangLai(
    MaTheKVL varchar2(5),
    MaXe varchar2(5),
    
    CONSTRAINT PK_MaTheKVL PRIMARY KEY (MaTheKVL)
);

-- Tao bang ChiTietRaVao
create table ChiTietRaVao(
    MaCTRaVao varchar2(5),
    ThoiGianVao Date,
    ThoiGianRa Date,
    MaKH varchar2(5),
    MaXe varchar2(5),
    MaTheKVL varchar2(5),
    
    CONSTRAINT PK_MaCTRaVao PRIMARY KEY (MaCTRaVao)
);


-- Tao bang HoaDonMuaVe
create table HoaDonMuaVe(
    MaHD varchar2(5),
    MaKH Varchar2(5),
    NgayHD Date,
    TongTriGia Number,
    
    CONSTRAINT PK_MaHD PRIMARY KEY (MaHD)
);

-- Tao bang ChiTietHoaDonMuaVe
create table ChiTietHoaDonMuaVe(
    MaHD varchar2(5),
    MaLoaiVe varchar2(5),
    SoLuongVe Number,
    
    CONSTRAINT PK_CTHD PRIMARY KEY (MaHD, MaLoaiVe)
);




-- Tao khoa ngoai

-- FK CUA NhanVien
ALTER TABLE NhanVien ADD CONSTRAINT FK_NhanVien_MaNV FOREIGN KEY(MaNV) REFERENCES NguoiDung(MaND);

-- FK CUA KhachHang
ALTER TABLE KhachHang ADD CONSTRAINT FK_KhachHang_MaKH FOREIGN KEY(MaKH) REFERENCES NguoiDung(MaND);
ALTER TABLE KhachHang ADD CONSTRAINT FK_KhachHang_MaXe FOREIGN KEY(MaXe) REFERENCES Xe(MaXe);

-- FK CUA c_Ve 
ALTER TABLE c_Ve ADD CONSTRAINT FK_c_Ve_MaLoaiVe FOREIGN KEY(MaLoaiVe) REFERENCES LoaiVe(MaLoaiVe);
ALTER TABLE c_Ve ADD CONSTRAINT FK_c_Ve_MaKH FOREIGN KEY(MaKH) REFERENCES KhachHang(MaKH);

-- FK CUA KhachVangLai
ALTER TABLE KhachVangLai ADD CONSTRAINT FK_KhachVL_MaXe FOREIGN KEY(MaXe) REFERENCES Xe(MaXe);

-- FK CUA ChiTietRaVao
ALTER TABLE ChiTietRaVao ADD CONSTRAINT FK_CTRaVao_MaKH FOREIGN KEY(MaKH) REFERENCES KhachHang(MaKH);
ALTER TABLE ChiTietRaVao ADD CONSTRAINT FK_CTRaVao_MaTheKVL FOREIGN KEY(MaTheKVL) REFERENCES KhachVangLai(MaTheKVL);
ALTER TABLE ChiTietRaVao ADD CONSTRAINT FK_CTRaVao_MaXe FOREIGN KEY(MaXe) REFERENCES Xe(MaXe);

-- FK CUA ChiTietHoaDonMuaVe
ALTER TABLE ChiTietHoaDonMuaVe ADD CONSTRAINT FK_CTHD_MaHD FOREIGN KEY(MaHD) REFERENCES HoaDonMuave(MaHD);
ALTER TABLE ChiTietHoaDonMuaVe ADD CONSTRAINT FK_CTHD_MaLoaiVe FOREIGN KEY(MaLoaive) REFERENCES LoaiVe(MaLoaiVe);

-- FK CUA HoaDonMuaVe
ALTER TABLE HoaDonMuaVe ADD CONSTRAINT FK_HoaDonMuaVe_MaKH FOREIGN KEY(MaKH) REFERENCES KhachHang(MaKH);

--Them du lieu
alter session set nls_date_format = 'yyyy-mm-dd hh24:mi:ss';

-- IMPORT du lieu tu excel







--Trigger

--Check_SoDu_trigger
create or replace trigger Check_SoDu_trigger
before insert or update on KhachHang 
for each row
begin
    if (:new.SoDu < 0) then
        raise_application_error(-20001, 'So du phai lon hon hoac bang không 0');
    end if;
end;



-- Check_GiaVe_trigger
create or replace trigger Check_GiaVe_trigger
before insert or update on LoaiVe
for each row
begin
    if (:new.GiaVe < 0) then
        raise_application_error(-20002, 'Gia ve phai lon hon hoac bang không 0');
    end if;
end;


--NgayHetHan_trigger
create or replace trigger Check_NgayHetHan_trigger 
before insert or update on c_Ve 
for each row
begin
    if (:new.NgayHetHan < :new.NgayKichHoat) then
        raise_application_error(-20003, 'Ngay het han phai lon hon hoac bang ngay kich hoat');
    end if;
end;


--Check_ThoiGianVaoRa_trigger
create or replace trigger Check_ThoiGianVaoRa_trigger
before insert or update on ChiTietRaVao
for each row
begin
    if ( to_char(:new.ThoiGianRa,'dd-mm-yyyy') = to_char(:new.ThoiGianVao,'dd-mm-yyyy') and to_char(:new.ThoiGianRa,'HH24') = to_char(:new.ThoiGianVao,'HH24') and  to_char(:new.ThoiGianRa,'MI') = to_char(:new.ThoiGianVao,'MI') AND to_char(:new.ThoiGianRa,'SS') - to_char(:new.ThoiGianVao,'SS') < 30 ) then
        raise_application_error(-20004, 'Thoi gian ra phai lon hon thoi gian vao it nhat 30s');
    end if;
end;

-- Check_SoLuongVe_trigger
create trigger Check_SoLuongVe_trigger
before insert or update on ChiTietHoaDonMuaVe
for each row
begin
    if (:new.SoLuongVe < 0) then
        raise_application_error(-20004, 'So luong ve phai lon hon hoac bang không 0');
    end if;
end;


--Procedure
--CAP NHAT GIA VE
create or replace procedure CapNhat_GiaVe(i_MaLoaiVe LoaiVe.MaLoaiVe%type, i_GiaVe LoaiVe.GiaVe%type)
is
PRAGMA AUTONOMOUS_TRANSACTION;
    l_MaLoaiVe varchar2(5);
begin
    select MaLoaiVe into l_MaLoaiVe
    from LoaiVe
    where MaLoaiVe = i_MaLoaiVe;
    if l_MaLoaiVe is null then
        dbms_output.put_line('MaLoaiVe khong ton tai');
    else
        update LoaiVe
        set GiaVe = i_GiaVe
        where MaLoaiVe = i_MaLoaiVe;
    end if;
    exception when no_Data_found then
    dbms_output.put_line('MaLoaiVe khong ton tai');
end;

--CAP NHAT LOAI VE
create or replace procedure CapNhat_LoaiVe(i_MaLoaiVe LoaiVe.MaLoaiVe%type, i_TenLoaiVe LoaiVe.TenLoaiVe%type, i_GiaVe LoaiVe.GiaVe%type)
is
    l_MaLoaiVe varchar2(5);
begin
    select MaLoaiVe into l_MaLoaiVe
    from LoaiVe
    where MaLoaiVe = i_MaLoaiVe;
    if l_MaLoaiVe is null then
        dbms_output.put_line('MaLoaiVe khong ton tai');
    else
        update LoaiVe
        set TenLoaiVe = i_TenLoaiVe, GiaVe = i_GiaVe
        where MaLoaiVe = i_MaLoaiVe;
    end if;
    exception when no_Data_found then
    dbms_output.put_line('MaLoaiVe khong ton tai');
end;

-- CAP NHAT NGUOI DUNG
create or replace procedure CapNhat_NguoiDung(i_MaND NguoiDung.MaND%type, i_Email NguoiDung.Email%type, i_MatKhau NguoiDung.MatKhau%type, i_HoTen NguoiDung.HoTen%type, i_GioiTinh NguoiDung.GioiTinh%type, i_NgSinh NguoiDung.NgSinh%type, i_DiaChi NguoiDung.DiaChi%type, i_QueQuan NguoiDung.QueQuan%type, i_SDT NguoiDung.SDT%type)
is
    l_MaND varchar2(5);
begin
    select MaND into l_MaND
    from NguoiDung
    where MaND = i_MaND;
    if l_MaND is null then
        dbms_output.put_line('MaND khong ton tai');
    else
        update NguoiDung
        set Email = i_Email, MatKhau = i_MatKhau, HoTen = i_HoTen, GioiTinh = i_GioiTinh, NgSinh = i_NgSinh, DiaChi = i_DiaChi, QueQuan = i_QueQuan, SDT = i_SDT
        where MaND = i_MaND;
    end if;
    exception when no_Data_found then
    dbms_output.put_line('MaND khong ton tai');
end;

-- CAP NHAT NHAN VIEN
create or replace procedure CapNhat_NhanVien(i_MaNV NhanVien.Manv%type, i_ViTriNhanVien NhanVien.ViTriNhanVien%type)
is
    l_MaNV varchar2(5);
begin
    select Manv into l_MaNV
    from NhanVien
    where Manv = i_MaNV;
    if l_MaNV is null then
        dbms_output.put_line('MaNV khong ton tai');
    else
        update NhanVien
        set ViTriNhanVien = i_ViTriNhanVien
        where Manv = i_MaNV;
    end if;
    exception when no_Data_found then
    dbms_output.put_line('MaNV khong ton tai');
end;

--CAP NHAT XE
create or replace procedure CapNhat_Xe(i_MaXe Xe.MaXe%type, i_BienSoXe Xe.BienSoXe%type, i_TenLoaiXe Xe.TenLoaiXe%type)
is
    l_MaXe varchar2(5);
begin
    select MaXe into l_MaXe
    from Xe
    where MaXe = i_MaXe;
    if l_MaXe is null then
        dbms_output.put_line('MaXe khong ton tai');
    else
        update Xe
        set BienSoXe = i_BienSoXe, TenLoaiXe = i_TenLoaiXe
        where MaXe = i_MaXe;
    end if;
    exception when no_Data_found then
    dbms_output.put_line('MaXe khong ton tai');
end;

-- KIEM TRA VE HET HAN
create or replace procedure check_Ve_HetHan
as
    l_NgayHetHan date;

    Cursor List_id is
    select * 
    from C_Ve;

begin
    for l_Ve in list_id loop

        select NgayHetHan into l_NgayHetHan
        from c_Ve
        where MaVe =l_Ve.MaVe;
        if to_char(sysdate,'dd/mm/yyyy') > to_char(l_NgayHetHan,'dd/mm/yyyy') then
            update c_Ve
            set TrangThai = 'Da het han'
             where MaVe =l_Ve.MaVe;
        end if;
    end loop;
end;

--NAP TIEN
create or replace procedure NapTien(i_MaKH KhachHang.MaKH%type, i_SoDu KhachHang.SoDu%type)
is
    l_MaKH varchar2(5);
begin
    select MaKH into l_MaKH
    from KhachHang
    where MaKH = i_MaKH;
    if l_MaKH is null then
        dbms_output.put_line('MaKH khong ton tai');
    else
        update KhachHang
        set SoDu = SoDu + i_SoDu
        where MaKH = i_MaKH;
    end if;
    exception when no_Data_found then
    dbms_output.put_line('MaKH khong ton tai');
end;

-- THEM KHACH HANG
create or replace procedure Them_KhachHang(i_MaKH KhachHang.MaKH%type, i_MaXe KhachHang.MaXe%type)
is
    l_MaKH varchar2(5);
begin
    insert into KhachHang values(i_MaKH, i_MaXe, 0);
    exception when no_Data_found then
    dbms_output.put_line('Them khong thanh cong');
end;

--THEM LOAI VE
create or replace procedure Them_LoaiVe(i_MaLoaiVe LoaiVe.MaLoaiVe%type, i_TenLoaiVe LoaiVe.TenLoaiVe%type, i_GiaVe LoaiVe.GiaVe%type)
is
    l_MaLoaiVe varchar2(5); 
begin
    insert into LoaiVe values(i_MaLoaiVe, i_TenLoaiVe, i_GiaVe);
    exception when no_Data_found then
    dbms_output.put_line('Them khong thanh cong');
end;

-- THEM NGUOI DUNG
create or replace procedure Them_NguoiDung(i_Id NguoiDung.MaND%type, i_Email NguoiDung.Email%type, i_MatKhau NguoiDung.MatKhau%type, i_HoTen NguoiDung.HoTen%type, i_VaiTro NguoiDung.VaiTro%type)
is
l_id varchar2(5);
l_numid number;
    l_maMaxND varchar2(5);
begin
        insert into NguoiDung values(i_Id, i_Email,i_MatKhau,i_HoTen,'','','','','', i_VaiTro);
    exception 
    when no_Data_found then
    dbms_output.put_line('Them khong thanh cong');
end;

--THEM NHAN VIEN 
create or replace procedure Them_NhanVien(i_MaNV NhanVien.Manv%type, i_ViTriNhanVien NhanVien.ViTriNhanVien%type)
is
    l_MaNV varchar2(5);
begin
    insert into NhanVien values(i_MaNV, i_ViTriNhanVien);
    exception when no_Data_found then
    dbms_output.put_line('Them khong thanh cong');
end;

--THEM XE
create or replace procedure Them_Xe(i_MaXe Xe.MaXe%type, i_BienSoXe Xe.BienSoXe%type, i_TenLoaiXe Xe.TenLoaiXe%type)
is
    l_MaXe varchar2(5);
begin
    insert into Xe values(i_MaXe, i_BienSoXe, i_TenLoaiXe);
    exception when no_Data_found then
    dbms_output.put_line('Them khong thanh cong');
end;

--XOA LOAI VE
create or replace procedure Xoa_LoaiVe(i_MaLoaiVe LoaiVe.MaLoaiVe%type)
is
    l_MaLoaiVe varchar2(5);
begin
    select MaLoaiVe into l_MaLoaiVe
    from LoaiVe
    where MaLoaiVe = i_MaLoaiVe;
    if l_MaLoaiVe is null then
        dbms_output.put_line('MaLoaiVe khong ton tai');
    else
        delete from LoaiVe
        where MaLoaiVe = i_MaLoaiVe;
    end if;
    exception when no_Data_found then
    dbms_output.put_line('MaLoaiVe khong ton tai');
end;

-- XOA NGUOI DUNG
create or replace procedure Xoa_NguoiDung(i_MaND NguoiDung.MaND%type)   
is
    l_MaND varchar2(5);
begin
    select MaND into l_MaND
    from NguoiDung
    where MaND = i_MaND;
    if l_MaND is null then
        dbms_output.put_line('MaND khong ton tai');
    else
        delete from NguoiDung
        where MaND = i_MaND;
    end if;
    exception when no_Data_found then
    dbms_output.put_line('MaND khong ton tai');
end;

-- XOA XE

create or replace procedure Xoa_Xe(i_MaXe Xe.MaXe%type)
is
    l_MaXe varchar2(5);
begin
    select MaXe into l_MaXe
    from Xe
    where MaXe = i_MaXe;
    if l_MaXe is null then
        dbms_output.put_line('MaXe khong ton tai');
    else
        delete from Xe
        where MaXe = i_MaXe;
    end if;
    exception when no_Data_found then
    dbms_output.put_line('MaXe khong ton tai');
end;




-- FUNCTION
-- kiem tra khach hang co dang gui xe hay khong
create or replace function check_MaKH_dangguixe(i_MaKH Khachhang.MaKH%type)
return number is l_MaKH varchar2(5);

begin 
    select KH.MaKH into l_MaKH
    from KhachHang KH, ChiTietRaVao CTRV
    where KH.MaKH = CTRV.MaKH
        and KH.MaKH = i_MaKH
        and ThoiGianVao is not null;
    if l_MaKH is null then
        return 0;
    else 
        return 1;
    end if;
    exception when no_data_found then
        DBMS_output.put_line('MaKH khong ton tai!');
        return null;
end;

-- tinh doanh thu theo ngay
create or replace function DoanhThu_TheoNgay(p_NgayHD Date )
return number
is DoanhThu HoaDonMuaVe.TongTrigia%type :=0;
begin
    select sum(TongTriGia) into DoanhThu
    from HoaDonMuaVe HD join ChiTietHoaDonMuaVe CTHD on HD.MaHD = CTHD.MaHD
    where HD.NgayHD = p_NgayHD;
    if DoanhThu > 0 then 
        return DoanhThu;
    end if;
    exception when no_data_found then
        dbms_output.put_line('Khong co ngay phu hop!');
        return null;
end;

-- So luong xe ra theo ngay
create or replace function SoLuongXeRa_TheoNgay(p_NgayHD Date)
return number
as SoLuongXeRa number;
   begin
    select count(MaXe) into SoLuongXeRa
    from ChiTietRaVao CTRV 
    where CTRV.ThoiGianRa = p_NgayHD;
    return SoLuongXeRa;
    exception when no_Data_found then
    dbms_output.put_line('Thong tin nhap khong hop le!');
    return null;
end;

--so luong xe vao theo ngay
create or replace function SoLuongXeVao_TheoNgay(p_NgayHD Date)
return number
as SoLuongXeVao number;
begin
    select count(MaXe) into SoLuongXeVao
    from ChiTietRaVao CTRV 
    where CTRV.ThoiGianVao = p_NgayHD;
    return SoLuongXeVao;
    exception when no_Data_found then
    dbms_output.put_line('Thong tin nhap khong hop le!');
    return null;
end;


