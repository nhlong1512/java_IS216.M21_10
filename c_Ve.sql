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

-- FK CUA c_Ve 
ALTER TABLE c_Ve ADD CONSTRAINT FK_c_Ve_MaLoaiVe FOREIGN KEY(MaLoaiVe) REFERENCES LoaiVe(MaLoaiVe);
ALTER TABLE c_Ve ADD CONSTRAINT FK_c_Ve_MaKH FOREIGN KEY(MaKH) REFERENCES KhachHang(MaKH);


alter session set nls_date_format = 'dd/MON/yyyy hh24:mi:ss';

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
