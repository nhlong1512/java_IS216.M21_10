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
);

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