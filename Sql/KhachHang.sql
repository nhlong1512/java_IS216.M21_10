-- Tao bang KhachHang
create table KhachHang (
    MaKH varchar2(5),
    MaXe varchar2(5),
    SoDu Number,
    
    CONSTRAINT PK_MaKH PRIMARY KEY (MAKH)
);

-- FK CUA KhachHang
ALTER TABLE KhachHang ADD CONSTRAINT FK_KhachHang_MaKH FOREIGN KEY(MaKH) REFERENCES NguoiDung(MaND);
ALTER TABLE KhachHang ADD CONSTRAINT FK_KhachHang_MaXe FOREIGN KEY(MaXe) REFERENCES Xe(MaXe);


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
