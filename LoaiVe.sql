-- Tao bang LoaiVe
create table LoaiVe(
    MaLoaiVe varchar2(5),
    TenLoaiVe varchar2(30),
    GiaVe number,
    
    CONSTRAINT PK_MaLoaiVe PRIMARY KEY (MaLoaiVe)
);

-- Insert LoaiVe Table

INSERT INTO LoaiVe VALUES ('LVE01', 'Ve luot xe may', 3000);
INSERT INTO LoaiVe VALUES ('LVE02', 'Ve luot xe dap', 2000);
INSERT INTO LoaiVe VALUES ('LVE03', 'Ve tuan', 25000);
INSERT INTO LoaiVe VALUES ('LVE04', 'Ve thang', 95000);