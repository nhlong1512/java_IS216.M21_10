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

-- FK CUA ChiTietRaVao
ALTER TABLE ChiTietRaVao ADD CONSTRAINT FK_CTRaVao_MaNV FOREIGN KEY(MaNV) REFERENCES NhanVien(MaNV);
ALTER TABLE ChiTietRaVao ADD CONSTRAINT FK_CTRaVao_MaKH FOREIGN KEY(MaKH) REFERENCES KhachHang(MaKH);
ALTER TABLE ChiTietRaVao ADD CONSTRAINT FK_CTRaVao_MaTheKVL FOREIGN KEY(MaTheKVL) REFERENCES KhachVangLai(MaTheKVL);
ALTER TABLE ChiTietRaVao ADD CONSTRAINT FK_CTRaVao_MaXe FOREIGN KEY(MaXe) REFERENCES Xe(MaXe);


alter session set nls_date_format = 'dd/MON/yyyy hh24:mi:ss';

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
