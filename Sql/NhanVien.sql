-- Tao bang NhanVien
create table NhanVien  (
    MaNV varchar2(5),
    ViTriNhanVien varchar2(30),
    
    CONSTRAINT PK_MaNV PRIMARY KEY (MaNV) 
);

-- FK CUA NhanVien
ALTER TABLE NhanVien ADD CONSTRAINT FK_NhanVien_MaNV FOREIGN KEY(MaNV) REFERENCES NguoiDung(MaND);


--INSERT NhanVien TABLE

INSERT INTO NhanVien  VALUES ('ND011', 'Bao ve');
INSERT INTO NhanVien  VALUES ('ND012', 'Quan Ly');
INSERT INTO NhanVien  VALUES ('ND013', 'Bao ve');
