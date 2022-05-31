drop table nguoidung cascade CONSTRAINTS;
drop table xe cascade CONSTRAINTS;
drop table nhanvien cascade CONSTRAINTS;
drop table khachhang cascade CONSTRAINTS;
drop table c_ve cascade CONSTRAINTS;
drop table loaive cascade CONSTRAINTS;
drop table chitietravao cascade CONSTRAINTS;
drop table khachvanglai cascade CONSTRAINTS;
drop table chitiethoadonmuave cascade CONSTRAINTS;
drop table hoadonmuave cascade CONSTRAINTS;


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

    CONSTRAINT PK_MaND PRIMARY KEY (MaND)
--   
--    CONSTRAINT check_GioiTinh CHECK (GioiTinh = 'Nam' or GioiTinh = 'Nu'),
--    CONSTRAINT check_VaiTro CHECK (VaiTro ='Khach hang' or VaiTro = 'Nhan vien')
);


-- Tao bang Xe
create table Xe (
    MaXe varchar2(5),
    BienSoXe varchar2(30),
    TenLoaiXe Varchar2(20),
    
    CONSTRAINT PK_MaXe PRIMARY KEY (MaXe)      
);

-- Tao bang NhanVien
create table NhanVien  (
    MaNV varchar2(5),
    ViTriNhanVien varchar2(30),
    
    CONSTRAINT PK_MaNV PRIMARY KEY (MaNV)
--    CONSTRAINT check_ViTriNhanVien CHECK (ViTriNhanVien in ('Quan ly', 'Bao ve'))
);


-- Tao bang KhachHang
create table KhachHang (
    MaKH varchar2(5),
    MaXe varchar2(5),
    SoDu Number,
    
    CONSTRAINT PK_MaKH PRIMARY KEY (MAKH)
);
--So du phai lon hon hoac bang kh�ng 0
create or replace trigger SoDu_trigger
before insert or update on KhachHang 
for each row
begin
    if (:new.SoDu < 0) then
        raise_application_error(-20001, 'So du phai lon hon hoac bang kh�ng 0');
    end if;
end;
--da chay

-- Tao bang LoaiVe
create table LoaiVe(
    MaLoaiVe varchar2(5),
    TenLoaiVe varchar2(30),
    GiaVe number,
    
    CONSTRAINT PK_MaLoaiVe PRIMARY KEY (MaLoaiVe)
);


--Gia ve phai lon hon hoac bang kh�ng 0
create or replace trigger GiaVe_trigger
before insert or update on LoaiVe
for each row
begin
    if (:new.GiaVe < 0) then
        raise_application_error(-20002, 'Gia ve phai lon hon hoac bang kh�ng 0');
    end if;
end;
-- da chay
-- Tao bang Ve
create table c_Ve(
    MaVe varchar2(5),
    MaLoaiVe varchar2(5),
    MaKH varchar2(5),
    NgayKichHoat Date,
    NgayHetHan Date,
    TrangThai Varchar(30),
    
    CONSTRAINT PK_MaVe PRIMARY KEY (MaVe) 
);

--Ngay het han phai lon hon hoac bang ngay kich hoat
create or replace trigger NgayHetHan_trigger 
before insert or update on c_Ve 
for each row
begin
    if (:new.NgayHetHan < :new.NgayKichHoat) then
        raise_application_error(-20003, 'Ngay het han phai lon hon hoac bang ngay kich hoat');
    end if;
end;

drop trigger NgayHetHan_trigger;
--da chay

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
    MaNV varchar2(5),
    MaKH varchar2(5),
    MaXe varchar2(5),
    MaTheKVL varchar2(5),
    
    CONSTRAINT PK_MaCTRaVao PRIMARY KEY (MaCTRaVao)
);
-- thoi gian xe vao phai lon hon thoi gian xe ra toi thieu 30 giay
create or replace trigger ThoiGianVaoRa_trigger
before insert or update on ChiTietRaVao
for each row
begin
    if ( to_char(:new.ThoiGianRa,'dd-mm-yyyy') = to_char(:new.ThoiGianVao,'dd-mm-yyyy') and to_char(:new.ThoiGianRa,'HH24') = to_char(:new.ThoiGianVao,'HH24') and  to_char(:new.ThoiGianRa,'MI') = to_char(:new.ThoiGianVao,'MI') AND to_char(:new.ThoiGianRa,'SS') - to_char(:new.ThoiGianVao,'SS') < 30 ) then
        raise_application_error(-20004, 'Thoi gian ra phai lon hon thoi gian vao it nhat 30s');
    end if;
end;
--da chay
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
-- create trigger for insert or update to check SoLuongVe bigger than 0
create trigger SoLuongVe_trigger
before insert or update on ChiTietHoaDonMuaVe
for each row
begin
    if (:new.SoLuongVe < 0) then
        raise_application_error(-20004, 'So luong ve phai lon hon hoac bang kh�ng 0');
    end if;
end;
--da chay



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
ALTER TABLE ChiTietRaVao ADD CONSTRAINT FK_CTRaVao_MaNV FOREIGN KEY(MaNV) REFERENCES NhanVien(MaNV);
ALTER TABLE ChiTietRaVao ADD CONSTRAINT FK_CTRaVao_MaKH FOREIGN KEY(MaKH) REFERENCES KhachHang(MaKH);
ALTER TABLE ChiTietRaVao ADD CONSTRAINT FK_CTRaVao_MaTheKVL FOREIGN KEY(MaTheKVL) REFERENCES KhachVangLai(MaTheKVL);
ALTER TABLE ChiTietRaVao ADD CONSTRAINT FK_CTRaVao_MaXe FOREIGN KEY(MaXe) REFERENCES Xe(MaXe);

-- FK CUA ChiTietHoaDonMuaVe
ALTER TABLE ChiTietHoaDonMuaVe ADD CONSTRAINT FK_CTHD_MaHD FOREIGN KEY(MaHD) REFERENCES HoaDonMuave(MaHD);
ALTER TABLE ChiTietHoaDonMuaVe ADD CONSTRAINT FK_CTHD_MaLoaiVe FOREIGN KEY(MaLoaive) REFERENCES LoaiVe(MaLoaiVe);

-- FK CUA HoaDonMuaVe
ALTER TABLE HoaDonMuaVe ADD CONSTRAINT FK_HoaDonMuaVe_MaKH FOREIGN KEY(MaKH) REFERENCES KhachHang(MaKH);

--Them du lieu
alter session set nls_date_format = 'dd/MON/yyyy hh24:mi:ss';

-- INSERT NguoiDung TABLE

INSERT INTO NguoiDung VALUES('ND001','20521122@gm.uit.edu.vn', 'nguyenvana123',
'Nguyen Van A', 'Nam',TO_DATE('31/03/2002', 'dd/mm/yyyy'),
'123 Quang Trung, p.10, Q.Go Vap', 'TP.HCM', '0123456789','Khach hang');

INSERT INTO NguoiDung VALUES('ND002','20520945@gm.uit.edu.vn', 'trantiendung012',
'Tran Tien Dung', 'Nam',TO_DATE('1/05/2002', 'dd/mm/yyyy'),
'966 Le Van Sy, p.3', 'TP. Ho Chi Minh', '0983563214', 'Khach hang');



INSERT INTO NguoiDung VALUES ('ND003','21520089@gm.uit.edu.vn', 'ntnga11',
'Nguyen Thi Nga', 'Nu', TO_DATE('6/07/2003', 'dd/mm/yyyy'),
'202 Nguyen Van Troi, p.6','TP. Da Lat', '0988746532','Khach hang');

INSERT INTO NguoiDung VALUES ('ND004','20520089@gm.uit.edu.vn', 'vnu000',
'Tran The Son', 'Nam', TO_DATE('10/03/2002', 'dd/mm/yyyy'),
'43 Nguyen Van Linh, Phuoc Hiep, Ba Ria','TP. Ba Ria - Vung Tau', '0566744325','Khach hang');

INSERT INTO NguoiDung VALUES ('ND005','20526793@gm.uit.edu.vn', 'pro178',
'Nguyen Thi Thanh Truc', 'Nu', TO_DATE('06/09/2002', 'dd/mm/yyyy'),
'983 Tran Phu, phuong 2','TP. Bao Loc', '0963412776','Khach hang');

INSERT INTO NguoiDung VALUES ('ND006','20520881@gm.uit.edu.vn', 'phna1111',
'Pham Hoang Ngoc Anh', 'Nu', TO_DATE('04/06/2002', 'dd/mm/yyyy'),
'993 Nguyen Kiem, phuong 3, Q.Go Vap','TP. Ho Chi Minh', '0939508991','Khach hang');

INSERT INTO NguoiDung VALUES ('ND007','20520811@gm.uit.edu.vn', 'nhoklokchok000',
'Tran Trong Tin', 'Nam', TO_DATE('11/01/2001', 'dd/mm/yyyy'),
'456 Nguyen Trai, phuong 6','TP. Ben Tre', '0377895602','Khach hang');

INSERT INTO NguoiDung VALUES ('ND008','20521982@gm.uit.edu.vn', 'twg90',
'Le Nu Thu Giang', 'Nu', TO_DATE('13/10/2002', 'dd/mm/yyyy'),
'42 Ky Con, phuong 2','TP. Bao Loc', '0976832461','Khach hang');

INSERT INTO NguoiDung VALUES ('ND009','20539872@gm.uit.edu.vn', 'bhik901',
'Pham Thi Thuy Uyen', 'Nu', TO_DATE('13/05/2002', 'dd/mm/yyyy'),
'48 Nguyen Thi Minh Khai','TP. Bao Loc', '0754396821','Khach hang');

INSERT INTO NguoiDung VALUES ('ND010','20521569@gm.uit.edu.vn', 'ngsek105',
'Nguyen Huu Long', 'Nam', TO_DATE('21/05/2002', 'dd/mm/yyyy'),
'12/4 TA20, quan 12','TP Quang Tri', '0365399621','Khach hang');

INSERT INTO NguoiDung VALUES ('ND011','nguyenvanbao@gmail.com', 'nvb890',
'Nguyen Van Bao', 'Nam', TO_DATE('19/7/1975', 'dd/mm/yyyy'),
'122 Binh Hung Hoa, phuong 3, Q. Binh Tan','TP. Ho Chi Minh','0766983421','Nhan vien');

INSERT INTO NguoiDung VALUES ('ND012','tranvanminh@gmail.com', 'tvm901',
'Tran Van Minh', 'Nam', TO_DATE('08/09/1979', 'dd/mm/yyyy'),
'58 Vo Thi Sau, phuong 1','TP. Vinh Long','0896578432','Nhan vien');

INSERT INTO NguoiDung VALUES ('ND013','nguyenquangtrung@gmail.com', 'nqt901',
'Nguyen Quang Trung', 'Nam', TO_DATE('07/04/1990', 'dd/mm/yyyy'),
'1 Phan Boi Chay, phuong 1','TP. Vinh Long','0768939450','Nhan vien');

INSERT INTO NguoiDung VALUES ('ND014','nguyentuankiet@gmail.com', 'nVE645',
'Nguyen Tuan Kiet', 'Nam', TO_DATE('09/08/1989', 'dd/mm/yyyy'),
'10 Le Minh, An Dong','TP. Hue','0768939450','Khach hang');

INSERT INTO NguoiDung VALUES ('ND015','20521083@gmail.uit.edu.vn', 'ttna0000',
'Tran Thi Ngoc Anh', 'Nu', TO_DATE('19/03/2002', 'dd/mm/yyyy'),
'28 Nguyen Thi Ly, phuong 2','TP. Quang Tri','0985766322','Khach hang');

--INSERT Xe TABLE

INSERT INTO Xe VALUES('XE001','59A-789.90', 'Xe may');
INSERT INTO Xe VALUES('XE002','59A-545.87', 'Xe may');
INSERT INTO Xe VALUES('XE003', NULL , 'Xe dap');
INSERT INTO Xe VALUES('XE004','49A-669.45', 'Xe may');
INSERT INTO Xe VALUES('XE005','49A-780.90', 'Xe may');
INSERT INTO Xe VALUES('XE006','72A-115.34', 'Xe may');
INSERT INTO Xe VALUES('XE007','72A-765.42', 'Xe may');
INSERT INTO Xe VALUES('XE008', NULL , 'Xe dap');
INSERT INTO Xe VALUES('XE009', NULL , 'Xe dap');
INSERT INTO Xe VALUES('XE010',NULL , 'Xe dap');
INSERT INTO Xe VALUES('XE011','75A-222.56', 'Xe may');
INSERT INTO Xe VALUES('XE012','49A-865.47', 'Xe may');
INSERT INTO Xe VALUES('XE013','58A-423.60', 'Xe may');
INSERT INTO Xe VALUES('XE014','51A-455.88', 'Xe may');
INSERT INTO Xe VALUES('XE015','66A-429.06', 'Xe may');
INSERT INTO Xe VALUES('XE016','62P-789.91', 'Xe may');
INSERT INTO Xe VALUES('XE017','70A-142.87', 'Xe may');
INSERT INTO Xe VALUES('XE018', NULL , 'Xe dap');
INSERT INTO Xe VALUES('XE019','47A-996.45', 'Xe may');
INSERT INTO Xe VALUES('XE020','49A-970.90', 'Xe may');
INSERT INTO Xe VALUES('XE021','71A-113.34', 'Xe may');
INSERT INTO Xe VALUES('XE022','83A-657.92', 'Xe may');
INSERT INTO Xe VALUES('XE023', NULL , 'Xe dap');
INSERT INTO Xe VALUES('XE024', NULL , 'Xe dap');
INSERT INTO Xe VALUES('XE025',NULL , 'Xe dap');
INSERT INTO Xe VALUES('XE026','72A-442.15', 'Xe may');
INSERT INTO Xe VALUES('XE027','49A-888.55', 'Xe may');
INSERT INTO Xe VALUES('XE028','62A-333.60', 'Xe may');
INSERT INTO Xe VALUES('XE029','59A-444.77', 'Xe may');
INSERT INTO Xe VALUES('XE030','66A-666.44', 'Xe may');



--INSERT NhanVien TABLE

INSERT INTO NhanVien  VALUES ('ND011', 'Bao ve');
INSERT INTO NhanVien  VALUES ('ND012', 'Quan ly');
INSERT INTO NhanVien  VALUES ('ND013', 'Bao ve');

--INSERT KHACHHANG TABLE

insert into KhachHang values ('ND001','XE001',90000);
insert into KhachHang values ('ND002','XE002',80000);
insert into KhachHang values ('ND003','XE003',70000);
insert into KhachHang values ('ND004','XE004',88000);
insert into KhachHang values ('ND005','XE005',12000);
insert into KhachHang values ('ND006','XE006',120000);
insert into KhachHang values ('ND007','XE007',390000);
insert into KhachHang values ('ND008','XE008',690000);
insert into KhachHang values ('ND009','XE009',790000);
insert into KhachHang values ('ND010','XE010',1190000);
insert into KhachHang values ('ND014','XE014',92000);
insert into KhachHang values ('ND015','XE015',99000);

-- Insert LoaiVe Table

INSERT INTO LoaiVe VALUES ('LVE01', 'Ve luot xe may', 3000);
INSERT INTO LoaiVe VALUES ('LVE02', 'Ve luot xe dap', 2000);
INSERT INTO LoaiVe VALUES ('LVE03', 'V� tuan', 25000);
INSERT INTO LoaiVe VALUES ('LVE04', 'Ve thang', 95000);


--INSERT Ve TABLE

INSERT INTO c_Ve VALUES ('VE001', 'LVE03','ND001',to_char(to_date(Sysdate,'dd/mm/yyyy hh24:mi:ss')),
to_char(to_date(Sysdate,'dd/mm/yyyy hh24:mi:ss')+7),1);
INSERT INTO c_Ve VALUES ('VE002', 'LVE04','ND002',to_char(to_date(Sysdate,'dd/mm/yyyy hh24:mi:ss')),
to_char(to_date(Sysdate,'dd/mm/yyyy hh24:mi:ss')+30),1);
INSERT INTO c_Ve VALUES ('VE003', 'LVE03','ND003',to_char(to_date(Sysdate,'dd/mm/yyyy hh24:mi:ss')),
to_char(to_date(Sysdate,'dd/mm/yyyy hh24:mi:ss')+7),1);
INSERT INTO c_Ve VALUES ('VE004', 'LVE04','ND004',to_char(to_date(Sysdate,'dd/mm/yyyy hh24:mi:ss')),
to_char(to_date(Sysdate,'dd/mm/yyyy hh24:mi:ss')+30),0);
INSERT INTO c_Ve VALUES ('VE005', 'LVE03','ND005',to_char(to_date(Sysdate,'dd/mm/yyyy hh24:mi:ss')),
to_char(to_date(Sysdate,'dd/mm/yyyy hh24:mi:ss')+7),0);
INSERT INTO c_Ve VALUES ('VE006', 'LVE03','ND006',to_char(to_date(Sysdate,'dd/mm/yyyy hh24:mi:ss')),
to_char(to_date(Sysdate,'dd/mm/yyyy hh24:mi:ss')+7),1);
INSERT INTO c_Ve VALUES ('VE007', 'LVE04','ND007',to_char(to_date(Sysdate,'dd/mm/yyyy hh24:mi:ss')),
to_char(to_date(Sysdate,'dd/mm/yyyy hh24:mi:ss')+30),1);
INSERT INTO c_Ve VALUES ('VE008', 'LVE03','ND008',to_char(to_date(Sysdate,'dd/mm/yyyy hh24:mi:ss')),
to_char(to_date(Sysdate,'dd/mm/yyyy hh24:mi:ss')+7),1);
INSERT INTO c_Ve VALUES ('VE009', 'LVE04','ND009',to_char(to_date(Sysdate,'dd/mm/yyyy hh24:mi:ss')),
to_char(to_date(Sysdate,'dd/mm/yyyy hh24:mi:ss')+30),1);
INSERT INTO c_Ve VALUES ('VE010', 'LVE03','ND010',to_char(to_date(Sysdate,'dd/mm/yyyy hh24:mi:ss')),
to_char(to_date(Sysdate,'dd/mm/yyyy hh24:mi:ss')+7),0);
INSERT INTO c_Ve VALUES ('VE014', 'LVE04','ND014',to_char(to_date(Sysdate,'dd/mm/yyyy hh24:mi:ss')),
to_char(to_date(Sysdate,'dd/mm/yyyy hh24:mi:ss')+30),0);
INSERT INTO c_Ve VALUES ('VE015', 'LVE04','ND015',to_char(to_date(Sysdate,'dd/mm/yyyy hh24:mi:ss')),
to_char(to_date(Sysdate,'dd/mm/yyyy hh24:mi:ss')+30),1);

--Insert KhachVangLai Table

INSERT INTO KhachVangLai VALUES('VL001', 'XE017');
INSERT INTO KhachVangLai VALUES('VL002', 'XE018');
INSERT INTO KhachVangLai VALUES('VL003', 'XE019');
INSERT INTO KhachVangLai VALUES('VL004', 'XE020');
INSERT INTO KhachVangLai VALUES('VL005', 'XE021');
INSERT INTO KhachVangLai VALUES('VL006', 'XE022');
INSERT INTO KhachVangLai VALUES('VL007', 'XE023');
INSERT INTO KhachVangLai VALUES('VL008', 'XE024');
INSERT INTO KhachVangLai VALUES('VL009', 'XE025');
INSERT INTO KhachVangLai VALUES('VL010', 'XE026');
INSERT INTO KhachVangLai VALUES('VL011', 'XE027');
INSERT INTO KhachVangLai VALUES('VL012', 'XE028');
INSERT INTO KhachVangLai VALUES('VL013', 'XE029');


--Insert ChiTietRaVao Table

INSERT INTO ChiTietRaVao VALUES('DT001', TO_DATE(sysdate, 'dd/mm/yyyy hh24:mi:ss'), TO_DATE(sysdate, 'dd/mm/yyyy hh24:mi:ss'), 'ND011', 'ND001', 'XE001', NULL);
INSERT INTO ChiTietRaVao VALUES('DT002', TO_DATE(sysdate, 'dd/mm/yyyy hh24:mi:ss'), TO_DATE(sysdate, 'dd/mm/yyyy hh24:mi:ss'), 'ND011', 'ND002', 'XE003', NULL);
INSERT INTO ChiTietRaVao VALUES('DT003', TO_DATE(sysdate, 'dd/mm/yyyy hh24:mi:ss'), TO_DATE(sysdate, 'dd/mm/yyyy hh24:mi:ss'), 'ND011', 'ND003', 'XE002', NULL);
INSERT INTO ChiTietRaVao VALUES('DT004', TO_DATE(sysdate, 'dd/mm/yyyy hh24:mi:ss'), TO_DATE(sysdate, 'dd/mm/yyyy hh24:mi:ss'), 'ND013', 'ND004', 'XE008', NULL);
INSERT INTO ChiTietRaVao VALUES('DT005', TO_DATE(sysdate, 'dd/mm/yyyy hh24:mi:ss'), TO_DATE(sysdate, 'dd/mm/yyyy hh24:mi:ss'), 'ND011', 'ND005', 'XE005', NULL);
INSERT INTO ChiTietRaVao VALUES('DT006', TO_DATE(sysdate, 'dd/mm/yyyy hh24:mi:ss'), TO_DATE(sysdate, 'dd/mm/yyyy hh24:mi:ss'), 'ND013', 'ND006', 'XE004', NULL);
INSERT INTO ChiTietRaVao VALUES('DT007', TO_DATE(sysdate, 'dd/mm/yyyy hh24:mi:ss'), TO_DATE(sysdate, 'dd/mm/yyyy hh24:mi:ss'), 'ND013', 'ND007', 'XE007', NULL);
INSERT INTO ChiTietRaVao VALUES('DT008', TO_DATE(sysdate, 'dd/mm/yyyy hh24:mi:ss'), TO_DATE(sysdate, 'dd/mm/yyyy hh24:mi:ss'), 'ND011', 'ND008', 'XE006', NULL);
INSERT INTO ChiTietRaVao VALUES('DT009', TO_DATE(sysdate, 'dd/mm/yyyy hh24:mi:ss'), TO_DATE(sysdate, 'dd/mm/yyyy hh24:mi:ss'), 'ND013', 'ND009', 'XE009', NULL);
INSERT INTO ChiTietRaVao VALUES('DT010', TO_DATE(sysdate, 'dd/mm/yyyy hh24:mi:ss'), TO_DATE(sysdate, 'dd/mm/yyyy hh24:mi:ss'), 'ND013', 'ND010', 'XE014', NULL);
INSERT INTO ChiTietRaVao VALUES('DT011', TO_DATE(sysdate, 'dd/mm/yyyy hh24:mi:ss'), TO_DATE(sysdate, 'dd/mm/yyyy hh24:mi:ss'), 'ND011', 'ND001', 'XE001', NULL);
INSERT INTO ChiTietRaVao VALUES('DT012', TO_DATE(sysdate, 'dd/mm/yyyy hh24:mi:ss'), TO_DATE(sysdate, 'dd/mm/yyyy hh24:mi:ss'), 'ND013', 'ND005', 'XE005', NULL);
INSERT INTO ChiTietRaVao VALUES('DT013', TO_DATE(sysdate, 'dd/mm/yyyy hh24:mi:ss'), TO_DATE(sysdate, 'dd/mm/yyyy hh24:mi:ss'), 'ND013', 'ND009', 'XE009', NULL);
INSERT INTO ChiTietRaVao VALUES('DT014', TO_DATE(sysdate, 'dd/mm/yyyy hh24:mi:ss'), TO_DATE(sysdate, 'dd/mm/yyyy hh24:mi:ss'), 'ND011', 'ND014', 'XE015', NULL);
INSERT INTO ChiTietRaVao VALUES('DT015', TO_DATE(sysdate, 'dd/mm/yyyy hh24:mi:ss'), TO_DATE(sysdate, 'dd/mm/yyyy hh24:mi:ss'), 'ND013', 'ND015', 'XE011', NULL);
INSERT INTO ChiTietRaVao VALUES('DT016', TO_DATE(sysdate, 'dd/mm/yyyy hh24:mi:ss'), TO_DATE(sysdate, 'dd/mm/yyyy hh24:mi:ss'), 'ND013', NULL, 'XE023', 'VL007');
INSERT INTO ChiTietRaVao VALUES('DT017', TO_DATE(sysdate, 'dd/mm/yyyy hh24:mi:ss'), TO_DATE(sysdate, 'dd/mm/yyyy hh24:mi:ss'), 'ND013', NULL, 'XE019', 'VL003');
INSERT INTO ChiTietRaVao VALUES('DT018', TO_DATE(sysdate, 'dd/mm/yyyy hh24:mi:ss'), TO_DATE(sysdate, 'dd/mm/yyyy hh24:mi:ss'), 'ND011', NULL, 'XE025', 'VL009');
INSERT INTO ChiTietRaVao VALUES('DT019', TO_DATE(sysdate, 'dd/mm/yyyy hh24:mi:ss'), TO_DATE(sysdate, 'dd/mm/yyyy hh24:mi:ss'), 'ND011', NULL, 'XE029', 'VL012');
INSERT INTO ChiTietRaVao VALUES('DT020', TO_DATE(sysdate, 'dd/mm/yyyy hh24:mi:ss'), TO_DATE(sysdate, 'dd/mm/yyyy hh24:mi:ss'), 'ND011', NULL, 'XE022', 'VL006');

--Insert HoaDonMuaVe Table

INSERT INTO HoaDonMuaVe VALUES('HD001', 'ND001', TO_DATE(sysdate, 'dd/mm/yyyy hh24:mi:ss'), 3000 );
INSERT INTO HoaDonMuaVe VALUES('HD002', 'ND002', TO_DATE(sysdate, 'dd/mm/yyyy hh24:mi:ss'), 3000 );
INSERT INTO HoaDonMuaVe VALUES('HD003', 'ND003', TO_DATE(sysdate, 'dd/mm/yyyy hh24:mi:ss'), 2000 );
INSERT INTO HoaDonMuaVe VALUES('HD004', 'ND004', TO_DATE(sysdate, 'dd/mm/yyyy hh24:mi:ss'), 3000 );
INSERT INTO HoaDonMuaVe VALUES('HD005', 'ND005', TO_DATE(sysdate, 'dd/mm/yyyy hh24:mi:ss'), 3000 );
INSERT INTO HoaDonMuaVe VALUES('HD006', 'ND006', TO_DATE(sysdate, 'dd/mm/yyyy hh24:mi:ss'), 3000 );
INSERT INTO HoaDonMuaVe VALUES('HD007', 'ND007', TO_DATE(sysdate, 'dd/mm/yyyy hh24:mi:ss'), 3000 );
INSERT INTO HoaDonMuaVe VALUES('HD008', 'ND008', TO_DATE(sysdate, 'dd/mm/yyyy hh24:mi:ss'), 2000 );
INSERT INTO HoaDonMuaVe VALUES('HD009', 'ND009', TO_DATE(sysdate, 'dd/mm/yyyy hh24:mi:ss'), 2000 );
INSERT INTO HoaDonMuaVe VALUES('HD010', 'ND010', TO_DATE(sysdate, 'dd/mm/yyyy hh24:mi:ss'), 2000 );
INSERT INTO HoaDonMuaVe VALUES('HD014', 'ND014', TO_DATE(sysdate, 'dd/mm/yyyy hh24:mi:ss'), 3000 );
INSERT INTO HoaDonMuaVe VALUES('HD015', 'ND015', TO_DATE(sysdate, 'dd/mm/yyyy hh24:mi:ss'), 3000 );

--Insert ChiTietHoaDonMuaVe Table

INSERT INTO ChiTietHoaDonMuaVe VALUES('HD001', 'LVE01', 3);
INSERT INTO ChiTietHoaDonMuaVe VALUES('HD002', 'LVE02', 1);
INSERT INTO ChiTietHoaDonMuaVe VALUES('HD003', 'LVE04', 1);
INSERT INTO ChiTietHoaDonMuaVe VALUES('HD004', 'LVE02', 1);
INSERT INTO ChiTietHoaDonMuaVe VALUES('HD005', 'LVE01', 2);
INSERT INTO ChiTietHoaDonMuaVe VALUES('HD006', 'LVE04', 1);
INSERT INTO ChiTietHoaDonMuaVe VALUES('HD007', 'LVE04', 1);
INSERT INTO ChiTietHoaDonMuaVe VALUES('HD008', 'LVE03', 1);
INSERT INTO ChiTietHoaDonMuaVe VALUES('HD009', 'LVE02', 2);
INSERT INTO ChiTietHoaDonMuaVe VALUES('HD010', 'LVE04', 1);
INSERT INTO ChiTietHoaDonMuaVe VALUES('HD014', 'LVE04', 1);
INSERT INTO ChiTietHoaDonMuaVe VALUES('HD015', 'LVE04', 1);


-- kiem tra khach hang co dang gui xe trong bai khong  
-- create function for insert to check MaKH is exist
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

--Ham tinh doanh thu theo ngay voi tham so la ngay nhap vao.
create function DoanhThu_TheoNgay(p_NgayHD Date )
return number
is DoanhThu HoaDonMuaVe.TongTrigia%type :=0;
begin
    select sum(TongTriGia) into DoanhThu
    from HoaDonMuaVe HD join ChiTietHoaDonMuaVe CTHD on HD.MaHD = CTHD.MaHD
    where to_char(HD.NgayHD,'dd-mm-yyyy') = to_char(p_NgayHD,'dd-mm-yyyy');
    if DoanhThu > 0 then 
        return DoanhThu;
    end if;
    exception when no_data_found then
        dbms_output.put_line('Khong co ngay phu hop!');
        return null;
end;

declare totalCusBuy number;
begin 
    totalCusBuy := DoanhThu_TheoNgay(sysdate);
    dbms_output.put_line(totalCusBuy);
end;


--So Luong Xe Vao theo ngayHd
create function SoLuongXeVao_TheoNgay(p_NgayHD Date)
return number
as SoLuongXeVao number;
begin
    select count(MaXe) into SoLuongXeVao
    from ChiTietRaVao CTRV 
    where to_char(CTRV.ThoiGianVao,'dd-mm-yyyy') = to_char(p_NgayHD,'dd-mm-yyyy');
    return SoLuongXeVao;
    exception when no_Data_found then
    dbms_output.put_line('Thong tin nhap khong hop le!');
    return null;
end;

drop function SoLuongXeVao_TheoNgay;
--So Luong Xe Ra theo ngayHd
create function SoLuongXeRa_TheoNgay(p_NgayHD Date)
return number
as SoLuongXeRa number;
   begin
    select count(MaXe) into SoLuongXeRa
    from ChiTietRaVao CTRV 
    where to_char(CTRV.ThoiGianRa,'dd-mm-yyyy') = to_char(p_NgayHD,'dd-mm-yyyy');
    return SoLuongXeRa;
    exception when no_Data_found then
    dbms_output.put_line('Thong tin nhap khong hop le!');
    return null;
end;
  
drop function SoLuongXeRa_TheoNgay;


select * from c_Ve;