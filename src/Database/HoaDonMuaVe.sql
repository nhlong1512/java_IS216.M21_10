-- Tao bang HoaDonMuaVe
create table HoaDonMuaVe(
    MaHD varchar2(5),
    MaKH Varchar2(5),
    NgayHD Date,
    TongTriGia Number,
    
    CONSTRAINT PK_MaHD PRIMARY KEY (MaHD)
);


-- FK CUA HoaDonMuaVe
ALTER TABLE HoaDonMuaVe ADD CONSTRAINT FK_HoaDonMuaVe_MaKH FOREIGN KEY(MaKH) REFERENCES KhachHang(MaKH);


alter session set nls_date_format = 'dd/MON/yyyy hh24:mi:ss';

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
