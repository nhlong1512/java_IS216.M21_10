-- T?O B?NG C_USER

create table c_User (
    UserID varchar2(5),
    Email varchar2(32) not null,
    C_Password varchar2(64) not null,
    FullName varchar2(50),
    Gender varchar2(3),
    DateOfBirth Date,
    Address varchar2(256),
    Hometown varchar2(256),
    PhoneNumber varchar2(10),
    UserRole varchar2(10),

    CONSTRAINT PK_UserID PRIMARY KEY (UserID)       
);

--T?O B?NG VEHICLE

create table Vehicle (
    VehicleID varchar2(5),
    LicensePlates varchar2(30),
    VehicleTypeID varchar2(5),
    
    CONSTRAINT PK_VehicleID PRIMARY KEY (VehicleID)      
);

--T?O B?NG VEHICKETYPE

create table VehicleType  (
    VehicleTypeID varchar2(5),
    VehicleTypeName varchar2(20),
    
    CONSTRAINT PK_VehicleTypeID PRIMARY KEY (VehicleTypeID)        
);

--T?O B?NG STAFF

create table Staff  (
    StaffID varchar2(5),
    StaffPosition varchar2(30),
    
    CONSTRAINT PK_StaffID PRIMARY KEY (StaffID) 
);

--T?O B?NG TICKET

create table Ticket(
    TicketID varchar2(5),
    UserID varchar2(5),
    
    CONSTRAINT PK_TicketID PRIMARY KEY (TicketID) 
);

--T?O B?NG TICKETTYPE

create table TicketType (
    TicketTypeID varchar2(5),
    TicketTypeName varchar2(30),
    TicketTypePrice Number,
    VehicleTypeID varchar2(5),
    
    CONSTRAINT PK_TicketTypeID PRIMARY KEY (TicketTypeID)
);

--T?O B?NG TICKETDETAIL

create table TicketDetail (
    TicketID varchar2(5),
    TicketTypeID varchar2(5),
    Status Varchar2(30),
    ActivationDate Date,
    ExpirationDate Date,
    
    CONSTRAINT PK_TicketDetail PRIMARY KEY (TicketID,TicketTypeID)
);

--T?O B?NG BILLAPP

create table BillApp (
    BillAppID varchar2(5),
    BillDate Date,
    TotalPrice Number,
    UserID Varchar2(5),
    
    CONSTRAINT PK_BillApp PRIMARY KEY (BillAppID)
);

--T?O B?NG BILLAPPDETAIL

create table BillAppDetail(
    BillAppID varchar2(5),
    TicketTypeID varchar2(5),
    BillTicketQty Number,
    
    CONSTRAINT PK_BillAppDetail PRIMARY KEY (BillAppID,TicketTypeID)
 );
 
--T?O B?NG USERWALLET

create table UserWallet(
    UserID varchar2(5),
    OnewayTicketQty Number,
    WeekTicketQty Number,
    MonthTicketQty Number,
    
    CONSTRAINT PK_UserWallet PRIMARY KEY (UserID)
);

--T?O B?NG DETAILINOUT

create table DetailInOut (
    DetailInOutID varchar2(5),
    TimeIn Date,
    c_TimeOut Date,
    StaffID varchar2(5),
    UserID varchar2(5),
    TicketTypeID varchar2(5),
    VehicleID varchar2(5),
    
    CONSTRAINT PK_DetailInOut PRIMARY KEY (DetailInOutID)
);

--T?O B?NG WALKGUEST

create table WalkInGuest (
    CardID varchar2(5),
    VehicleTypeID varchar2(5),
    CardTypeWGID varchar2(5),
    
    CONSTRAINT PK_WalkInGuest PRIMARY KEY (CardID)
);

--T?O B?NG CARDTYPEWG

 
create table CardTypeWG (
    CardTypeWGID varchar2(5),
    CardTypeName varchar2(10),
    CONSTRAINT PK_CardTypeWG PRIMARY KEY (CardTypeWGID)
);

--T?O KHÓA NGO?I
--FK CUA VEHICLE
ALTER TABLE Vehicle ADD CONSTRAINT FK_Vehicle_VehicleTypeID FOREIGN KEY(VehicleTypeID) REFERENCES VehicleType(VehicleTypeID);
ALTER TABLE WalkInGuest ADD CONSTRAINT FK_WalkInGuest_CardTypeWGID FOREIGN KEY(CardTypeWGID) REFERENCES CardTypeWG(CardTypeWGID);

--FK CUA STAFF
ALTER TABLE Staff ADD CONSTRAINT FK_Staff_StaffID FOREIGN KEY(StaffID) REFERENCES c_User(UserID);

--FK CUA Ticket
ALTER TABLE Ticket ADD CONSTRAINT FK_Ticket_UserID FOREIGN KEY(UserID) REFERENCES c_User(UserID);

--FK CUA TicketType
ALTER TABLE TicketType ADD CONSTRAINT FK_TicketType_VehicleTypeID FOREIGN KEY(VehicleTypeID) REFERENCES VehicleType(VehicleTypeID );

--FK CUA TicketDetail
ALTER TABLE TicketDetail ADD CONSTRAINT FK_TicketDetail_TicketID FOREIGN KEY(TicketID) REFERENCES Ticket(TicketID );
ALTER TABLE TicketDetail ADD CONSTRAINT FK_TicketDetail_TicketTypeID FOREIGN KEY(TicketTypeID) REFERENCES TicketType(TicketTypeID );

--FK CUA BillApp
ALTER TABLE BillApp ADD CONSTRAINT FK_BillApp_UserID FOREIGN KEY(UserID) REFERENCES c_User(UserID );

--FK CUA BillAppDetail
ALTER TABLE BillAppDetail ADD CONSTRAINT FK_BillAppDetail_BillAppID FOREIGN KEY(BillAppID) REFERENCES BillApp(BillAppID );
ALTER TABLE BillAppDetail ADD CONSTRAINT FK_BillAppDetail_TicketTypeID FOREIGN KEY(TicketTypeID) REFERENCES TicketType(TicketTypeID  );

--FK CUA UserWallet
ALTER TABLE UserWallet ADD CONSTRAINT FK_UserWallet_UserID FOREIGN KEY(UserID) REFERENCES c_User(UserID );

--FK CUA DetailInOut
ALTER TABLE DetailInOut ADD CONSTRAINT FK_DetailInOut_StaffID FOREIGN KEY(StaffID) REFERENCES Staff(StaffID);
ALTER TABLE DetailInOut ADD CONSTRAINT FK_DetailInOut_UserID FOREIGN KEY(UserID) REFERENCES c_User(UserID);
ALTER TABLE DetailInOut ADD CONSTRAINT FK_DetailInOut_TicketTypeID FOREIGN KEY(TicketTypeID) REFERENCES TicketType(TicketTypeID);
ALTER TABLE DetailInOut ADD CONSTRAINT FK_DetailInOut_VehicleID FOREIGN KEY(VehicleID) REFERENCES Vehicle(VehicleID);

--FK CUA WalkInGuest
ALTER TABLE WalkInGuest ADD CONSTRAINT FK_WalkInGuest_VehicleTypeID FOREIGN KEY(VehicleTypeID) REFERENCES VehicleType(VehicleTypeID);

--THÊM D? LI?U

alter session set nls_date_format = 'dd/MON/yyyy hh24:mi:ss';

-- INSERT c_USER TABLE

INSERT INTO c_USER VALUES('US001','20521122@gm.uit.edu.vn', 'nguyenvana123',
'Nguyen Van A', 'Nam',TO_DATE('31/03/2002', 'dd/mm/yyyy'),
'123 Quang Trung, p.10, Q.Go Vap', 'TP.HCM', '0123456789','Customer');

INSERT INTO c_USER VALUES('US002','20520945@gm.uit.edu.vn', 'trantiendung012',
'Tran Tien Dung', 'Nam',TO_DATE('1/05/2002', 'dd/mm/yyyy'),
'966 Le Van Sy, p.3', 'TP. Ho Chi Minh', '0983563214', 'Customer');

INSERT INTO c_USER VALUES ('US003','21520089@gm.uit.edu.vn', 'ntnga11',
'Nguyen Thi Nga', 'Nu', TO_DATE('6/07/2003', 'dd/mm/yyyy'),
'202 Nguyen Van Troi, p.6','TP. Da Lat', '0988746532','Customer');

INSERT INTO c_USER VALUES ('US004','20520089@gm.uit.edu.vn', 'vnu000',
'Tran The Son', 'Nam', TO_DATE('10/03/2002', 'dd/mm/yyyy'),
'43 Nguyen Van Linh, Phuoc Hiep, Ba Ria','TP. Ba Ria - Vung Tau', '0566744325','Customer');

INSERT INTO c_USER VALUES ('US005','20526793@gm.uit.edu.vn', 'pro178',
'Nguyen Thi Thanh Truc', 'Nu', TO_DATE('06/09/2002', 'dd/mm/yyyy'),
'983 Tran Phu, phuong 2','TP. Bao Loc', '0963412776','Customer');

INSERT INTO c_USER VALUES ('US006','20520881@gm.uit.edu.vn', 'phna1111',
'Pham Hoang Ngoc Anh', 'Nu', TO_DATE('04/06/2002', 'dd/mm/yyyy'),
'993 Nguyen Kiem, phuong 3, Q.Go Vap','TP. Ho Chi Minh', '0939508991','Customer');

INSERT INTO c_USER VALUES ('US007','20520811@gm.uit.edu.vn', 'nhoklokchok000',
'Tran Trong Tin', 'Nam', TO_DATE('11/01/2001', 'dd/mm/yyyy'),
'456 Nguyen Trai, phuong 6','TP. Ben Tre', '0377895602','Customer');

INSERT INTO c_USER VALUES ('US008','20521982@gm.uit.edu.vn', 'twg90',
'Le Nu Thu Giang', 'Nu', TO_DATE('13/10/2002', 'dd/mm/yyyy'),
'42 Ky Con, phuong 2','TP. Bao Loc', '0976832461','Customer');

INSERT INTO c_USER VALUES ('US009','20539872@gm.uit.edu.vn', 'bhik901',
'Pham Thi Thuy Uyen', 'Nu', TO_DATE('13/05/2002', 'dd/mm/yyyy'),
'48 Nguyen Thi Minh Khai','TP. Bao Loc', '0754396821','Customer');

INSERT INTO c_USER VALUES ('US010','20521569@gm.uit.edu.vn', 'ngsek105',
'Nguyen Huu Long', 'Nam', TO_DATE('21/05/2002', 'dd/mm/yyyy'),
'12/4 TA20, quan 12','TP Quang Tri', '0365399621','Customer');

INSERT INTO c_USER VALUES ('US011','nguyenvanbao@gmail.com', 'nvb890',
'Nguyen Van Bao', 'Nam', TO_DATE('19/7/1975', 'dd/mm/yyyy'),
'122 Binh Hung Hoa, phuong 3, Q. Binh Tan','TP. Ho Chi Minh','0766983421','Staff');

INSERT INTO c_USER VALUES ('US012','tranvanminh@gmail.com', 'tvm901',
'Tran Van Minh', 'Nam', TO_DATE('08/09/1979', 'dd/mm/yyyy'),
'58 Vo Thi Sau, phuong 1','TP. Vinh Long','0896578432','Staff');

INSERT INTO c_USER VALUES ('US013','nguyenquangtrung@gmail.com', 'nqt901',
'Nguyen Quang Trung', 'Nam', TO_DATE('07/04/1990', 'dd/mm/yyyy'),
'1 Phan Boi Chay, phuong 1','TP. Vinh Long','0768939450','Staff');

INSERT INTO c_USER VALUES ('US014','nguyentuankiet@gmail.com', 'ntk645',
'Nguyen Tuan Kiet', 'Nam', TO_DATE('09/08/1989', 'dd/mm/yyyy'),
'10 Le Minh, An Dong','TP. Hue','0768939450','Customer');

INSERT INTO c_USER VALUES ('US015','20521083@gmail.uit.edu.vn', 'ttna0000',
'Tran Thi Ngoc Anh', 'Nu', TO_DATE('19/03/2002', 'dd/mm/yyyy'),
'28 Nguyen Thi Ly, phuong 2','TP. Quang Tri','0985766322','Customer');

--INSERT VEHICLETYPE TABLE

INSERT INTO VEHICLETYPE VALUES('VT001', 'Xe may');
INSERT INTO VEHICLETYPE VALUES('VT002', 'Xe dap');

--INSERT CARDTYPEWG TABLE

INSERT INTO CARDTYPEWG VALUES('CTWG1', 'The xanh');
INSERT INTO CARDTYPEWG VALUES('CTWG2', 'The do');

--INSERT STAFF TABLE

INSERT INTO STAFF VALUES ('US011', 'Security');
INSERT INTO STAFF VALUES ('US012', 'Manager');
INSERT INTO STAFF VALUES ('US013', 'Security');

--INSERT VEHICLE TABLE
INSERT INTO VEHICLE VALUES('VH001','59A-789.090', 'VT001');
INSERT INTO VEHICLE VALUES('VH002','59A-545.687', 'VT001');
INSERT INTO VEHICLE VALUES('VH003', NULL , 'VT002');
INSERT INTO VEHICLE VALUES('VH004','49A-669.345', 'VT001');
INSERT INTO VEHICLE VALUES('VH005','49A-780.990', 'VT001');
INSERT INTO VEHICLE VALUES('VH006','72A-115.234', 'VT001');
INSERT INTO VEHICLE VALUES('VH007','72A-765.342', 'VT001');
INSERT INTO VEHICLE VALUES('VH008', NULL , 'VT002');
INSERT INTO VEHICLE VALUES('VH009', NULL , 'VT002');
INSERT INTO VEHICLE VALUES('VH010',NULL , 'VT002');
INSERT INTO VEHICLE VALUES('VH011','75A-222.656', 'VT001');
INSERT INTO VEHICLE VALUES('VH012','49A-865.347', 'VT001');
INSERT INTO VEHICLE VALUES('VH013','59A-323.760', 'VT001');
INSERT INTO VEHICLE VALUES('VH014','59A-555.888', 'VT001');
INSERT INTO VEHICLE VALUES('VH015','64A-469.406', 'VT001');

--INSERT TICKET TABLE

INSERT INTO TICKET VALUES ('TK001', 'US001');
INSERT INTO TICKET VALUES ('TK002', 'US002');
INSERT INTO TICKET VALUES ('TK003', 'US003');
INSERT INTO TICKET VALUES ('TK004', 'US004');
INSERT INTO TICKET VALUES ('TK005', 'US005');
INSERT INTO TICKET VALUES ('TK006', 'US006');
INSERT INTO TICKET VALUES ('TK007', 'US007');
INSERT INTO TICKET VALUES ('TK008', 'US008');
INSERT INTO TICKET VALUES ('TK009', 'US009');
INSERT INTO TICKET VALUES ('TK010', 'US010');
INSERT INTO TICKET VALUES ('TK014', 'US014');
INSERT INTO TICKET VALUES ('TK015', 'US015');


--INSERT BILLAPP TABLE

insert into BillApp values('BA001',TO_DATE(sysdate, 'dd/mm/yyyy hh24:mi:ss'), 3000, 'US001');
insert into BillApp values('BA002',TO_DATE(sysdate, 'dd/mm/yyyy hh24:mi:ss'), 3000, 'US002');
insert into BillApp values('BA003',TO_DATE(sysdate, 'dd/mm/yyyy hh24:mi:ss'), 2000, 'US003');
insert into BillApp values('BA004',TO_DATE(sysdate, 'dd/mm/yyyy hh24:mi:ss'), 3000, 'US004');
insert into BillApp values('BA005',TO_DATE(sysdate, 'dd/mm/yyyy hh24:mi:ss'), 3000, 'US005');
insert into BillApp values('BA006',TO_DATE(sysdate, 'dd/mm/yyyy hh24:mi:ss'), 3000, 'US006');
insert into BillApp values('BA007',TO_DATE(sysdate, 'dd/mm/yyyy hh24:mi:ss'), 3000, 'US007');
insert into BillApp values('BA008',TO_DATE(sysdate, 'dd/mm/yyyy hh24:mi:ss'), 2000, 'US008');
insert into BillApp values('BA009',TO_DATE(sysdate, 'dd/mm/yyyy hh24:mi:ss'), 2000, 'US009');
insert into BillApp values('BA010',TO_DATE(sysdate, 'dd/mm/yyyy hh24:mi:ss'), 2000, 'US010');
insert into BillApp values('BA014',TO_DATE(sysdate, 'dd/mm/yyyy hh24:mi:ss'), 3000, 'US014');
insert into BillApp values('BA015',TO_DATE(sysdate, 'dd/mm/yyyy hh24:mi:ss'), 3000, 'US015');

-- INSERT  TICKETTYPE TABLE

INSERT INTO TICKETTYPE VALUES ('TKT01', 'Ve luot xe may', '3000', 'VT001');
INSERT INTO TICKETTYPE VALUES ('TKT02', 'Ve luot xe dap', '2000', 'VT002');
INSERT INTO TICKETTYPE VALUES ('TKT03', 'Ve tuan', '25000', 'VT001');
INSERT INTO TICKETTYPE VALUES ('TKT04', 'Ve thang', '95000', 'VT001');

-- INSERT  USERWALLET TABLE

insert into UserWallet values('US001', 3, 0, 0);
insert into UserWallet values('US002', 0, 1, 0);
insert into UserWallet values('US003', 0, 0, 1);
insert into UserWallet values('US004', 1, 0, 0);
insert into UserWallet values('US005', 0, 0, 0);
insert into UserWallet values('US006', 1, 0, 1);
insert into UserWallet values('US007', 0, 0, 1);
insert into UserWallet values('US008', 1, 1, 0);
insert into UserWallet values('US009', 0, 1, 0);
insert into UserWallet values('US010', 0, 0, 1);
insert into UserWallet values('US014', 0, 0, 1);
insert into UserWallet values('US015', 0, 0, 1);

-- INSERT TICKETDETAIL TABLE

INSERT INTO TICKETDETAIL VALUES ('TK001', 'TKT01', 'Dang kich hoat', Null, Null);

INSERT INTO TICKETDETAIL VALUES ('TK002', 'TKT03', NULL , to_char(to_date(Sysdate,'dd/mm/yyyy hh24:mi:ss')),
to_char(to_date(Sysdate,'dd/mm/yyyy hh24:mi:ss')+7));

INSERT INTO TICKETDETAIL VALUES ('TK003', 'TKT04', NULL , to_char(to_date(Sysdate,'dd/mm/yyyy hh24:mi:ss')),
to_char(to_date(Sysdate,'dd/mm/yyyy hh24:mi:ss')+30));

INSERT INTO TICKETDETAIL VALUES ('TK004', 'TKT02', 'Dang kich hoat', Null, Null);

INSERT INTO TICKETDETAIL VALUES ('TK005', 'TKT01', 'Da su dung', Null, Null);

INSERT INTO TICKETDETAIL VALUES ('TK006', 'TKT04', NULL , to_char(to_date(Sysdate,'dd/mm/yyyy hh24:mi:ss')),
to_char(to_date(Sysdate,'dd/mm/yyyy hh24:mi:ss')+30));

INSERT INTO TICKETDETAIL VALUES ('TK007', 'TKT04', NULL , to_char(to_date(Sysdate,'dd/mm/yyyy hh24:mi:ss')),
to_char(to_date(Sysdate,'dd/mm/yyyy hh24:mi:ss')+30));

INSERT INTO TICKETDETAIL VALUES ('TK008', 'TKT03', NULL , to_char(to_date(Sysdate,'dd/mm/yyyy hh24:mi:ss')),
to_char(to_date(Sysdate,'dd/mm/yyyy hh24:mi:ss')+7));

INSERT INTO TICKETDETAIL VALUES ('TK009', 'TKT02', 'Da su dung', Null, Null);

INSERT INTO TICKETDETAIL VALUES ('TK010', 'TKT04', NULL , to_char(to_date(Sysdate,'dd/mm/yyyy hh24:mi:ss')),
to_char(to_date(Sysdate,'dd/mm/yyyy hh24:mi:ss')+30));

INSERT INTO TICKETDETAIL VALUES ('TK014', 'TKT04', NULL , to_char(to_date(Sysdate,'dd/mm/yyyy hh24:mi:ss')),
to_char(to_date(Sysdate,'dd/mm/yyyy hh24:mi:ss')+30));

INSERT INTO TICKETDETAIL VALUES ('TK015', 'TKT04', NULL , to_char(to_date(Sysdate,'dd/mm/yyyy hh24:mi:ss')),
to_char(to_date(Sysdate,'dd/mm/yyyy hh24:mi:ss')+30));

-- INSERT BILLAPPDETAIL TABLE

insert into BillAppDetail values('BA001', 'TKT01', 3);
insert into BillAppDetail values('BA002', 'TKT02', 1);
insert into BillAppDetail values('BA003', 'TKT04', 1);
insert into BillAppDetail values('BA004', 'TKT02', 1);
insert into BillAppDetail values('BA005', 'TKT01', 0);
insert into BillAppDetail values('BA006', 'TKT04', 1);
insert into BillAppDetail values('BA007', 'TKT04', 1);
insert into BillAppDetail values('BA008', 'TKT03', 1);
insert into BillAppDetail values('BA009', 'TKT02', 0);
insert into BillAppDetail values('BA010', 'TKT04', 1);
insert into BillAppDetail values('BA014', 'TKT04', 1);
insert into BillAppDetail values('BA015', 'TKT04', 1);

-- INSERT DETAILINOUT TABLE

insert into DetailInOut values('DT001', TO_DATE(sysdate, 'dd/mm/yyyy hh24:mi:ss'), TO_DATE(sysdate, 'dd/mm/yyyy hh24:mi:ss'), 'US011', 'US001', 'TKT01', 'VH001');
insert into DetailInOut values('DT002', TO_DATE(sysdate, 'dd/mm/yyyy hh24:mi:ss'), TO_DATE(sysdate, 'dd/mm/yyyy hh24:mi:ss'), 'US011', 'US002', 'TKT02', 'VH003');
insert into DetailInOut values('DT003', TO_DATE(sysdate, 'dd/mm/yyyy hh24:mi:ss'), TO_DATE(sysdate, 'dd/mm/yyyy hh24:mi:ss'), 'US011', 'US003', 'TKT04', 'VH002');
insert into DetailInOut values('DT004', TO_DATE(sysdate, 'dd/mm/yyyy hh24:mi:ss'), TO_DATE(sysdate, 'dd/mm/yyyy hh24:mi:ss'), 'US013', 'US004', 'TKT02', 'VH008');
insert into DetailInOut values('DT005', TO_DATE(sysdate, 'dd/mm/yyyy hh24:mi:ss'), TO_DATE(sysdate, 'dd/mm/yyyy hh24:mi:ss'), 'US011', 'US005', 'TKT01', 'VH005');
insert into DetailInOut values('DT006', TO_DATE(sysdate, 'dd/mm/yyyy hh24:mi:ss'), TO_DATE(sysdate, 'dd/mm/yyyy hh24:mi:ss'), 'US013', 'US006', 'TKT04', 'VH004');
insert into DetailInOut values('DT007', TO_DATE(sysdate, 'dd/mm/yyyy hh24:mi:ss'), TO_DATE(sysdate, 'dd/mm/yyyy hh24:mi:ss'), 'US013', 'US007', 'TKT04', 'VH007');
insert into DetailInOut values('DT008', TO_DATE(sysdate, 'dd/mm/yyyy hh24:mi:ss'), TO_DATE(sysdate, 'dd/mm/yyyy hh24:mi:ss'), 'US011', 'US008', 'TKT03', 'VH006');
insert into DetailInOut values('DT009', TO_DATE(sysdate, 'dd/mm/yyyy hh24:mi:ss'), TO_DATE(sysdate, 'dd/mm/yyyy hh24:mi:ss'), 'US013', 'US009', 'TKT02', 'VH009');
insert into DetailInOut values('DT010', TO_DATE(sysdate, 'dd/mm/yyyy hh24:mi:ss'), TO_DATE(sysdate, 'dd/mm/yyyy hh24:mi:ss'), 'US013', 'US010', 'TKT04', 'VH014');
insert into DetailInOut values('DT011', TO_DATE(sysdate, 'dd/mm/yyyy hh24:mi:ss'), TO_DATE(sysdate, 'dd/mm/yyyy hh24:mi:ss'), 'US011', 'US001', 'TKT01', 'VH001');
insert into DetailInOut values('DT012', TO_DATE(sysdate, 'dd/mm/yyyy hh24:mi:ss'), TO_DATE(sysdate, 'dd/mm/yyyy hh24:mi:ss'), 'US013', 'US005', 'TKT01', 'VH005');
insert into DetailInOut values('DT013', TO_DATE(sysdate, 'dd/mm/yyyy hh24:mi:ss'), TO_DATE(sysdate, 'dd/mm/yyyy hh24:mi:ss'), 'US013', 'US009', 'TKT02', 'VH009');
insert into DetailInOut values('DT014', TO_DATE(sysdate, 'dd/mm/yyyy hh24:mi:ss'), TO_DATE(sysdate, 'dd/mm/yyyy hh24:mi:ss'), 'US011', 'US014', 'TKT04', 'VH015');
insert into DetailInOut values('DT015', TO_DATE(sysdate, 'dd/mm/yyyy hh24:mi:ss'), TO_DATE(sysdate, 'dd/mm/yyyy hh24:mi:ss'), 'US013', 'US015', 'TKT04', 'VH011');

-- INSERT WALKGUEST TABLE

insert into WalkInGuest values('BC001', 'VT001', 'CTWG1');
insert into WalkInGuest values('BC002', 'VT001', 'CTWG1');
insert into WalkInGuest values('BC003', 'VT001', 'CTWG1');
insert into WalkInGuest values('BC004', 'VT001', 'CTWG1');
insert into WalkInGuest values('BC005', 'VT001', 'CTWG1');
insert into WalkInGuest values('RC001', 'VT002', 'CTWG2');
insert into WalkInGuest values('RC002', 'VT002', 'CTWG2');
insert into WalkInGuest values('RC003', 'VT002', 'CTWG2');
insert into WalkInGuest values('BC006', 'VT001', 'CTWG1');
insert into WalkInGuest values('BC007', 'VT002', 'CTWG2');
insert into WalkInGuest values('BC008', 'VT001', 'CTWG1');
insert into WalkInGuest values('BC009', 'VT002', 'CTWG2');
insert into WalkInGuest values('BC010', 'VT001', 'CTWG1');

