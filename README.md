# Quản lý bãi giữ xe UIT - UITParking
------------
![Image1 of UITParking](./src/icon/backgroundForReadme2.webp "Back ground for Readme")
## Lời cảm ơn
------------
Lời đầu tiên, SCD Team xin gửi lời cảm ơn đến Thầy **Nguyễn Minh Nhựt** – giảng viên môn Lập trình Java đã tận tình giúp đỡ, trực tiếp chỉ bảo, hướng dẫn và đóng góp những ý kiến quý báu giúp nhóm chúng em hoàn thành được đồ án môn học của mình.


## Giới thiệu đồ án
------------
Sinh viên tại trường Đại học Công nghệ Thông tin (UIT) sau một khoảng thời gian dài học online vì đại dịch Covid-19 và bắt đầu quay lại trường để học trực tiếp nên lượng xe của sinh viên và giáo viên ngày càng nhiều. Vì vậy, bãi giữ xe của trường đóng vai trò quan trọng trong việc phục vụ nhu cầu gửi xe. Nhưng do còn nhiều khó khăn trong công tác quản lý doanh thu, số lượt xe ra vào mỗi ngày, mỗi tháng và các thao tác như chuẩn bị tiền lẻ và đợi thối tiền còn thủ công và khá thô sơ nên dễ dẫn đến tình trạng ùn tắc, kẹt xe. Để khắc phục những vấn đề trên, chúng ta cần có những giải pháp về công nghệ, thiết kế để tạo ra hệ thống giữ xe hiện đại hơn, tiện lợi hơn, an toàn và thông minh hơn.
Do vậy việc phát triển phần mềm, đòi hỏi không chỉ giải quyết những vấn đề trên mà còn là sự chính xác, phải đáp ứng các yêu cầu khác như về tốc độ, giao diện thân thiện, mô hình hóa được thực tế vào máy tính, điện thoại để người sử dụng tiện lợi, quen thuộc, tính tương thích cao, bảo mật cao,… Phần mềm giúp tiết kiệm một lượng lớn thời gian, công sức của con người khi không phải bận tâm trả tiền mặt mỗi lần giữ xe, tất cả sẽ được tích hợp vào ứng dụng. Hơn nữa là tăng độ chính xác và hiệu quả trong việc quản lý doanh thu.
Nhận biết được sự cần thiết trong việc quản lý bãi giữ xe tại trường Đại học Công nghệ Thông tin, nhóm chúng em đã vận dụng những kiến thức đã học cùng với sự hiểu biết của mình để cùng nhau xây dựng và phát triển phần mềm hệ thống “Quản lý bãi giữ xe của trường Đại học Công nghệ Thông Tin”, chủ yếu tập trung vào việc lưu trữ; quản lý xe ra, vào; quản lý doanh thu; thanh toán phí giữ xe và phát triển giao diện dễ tương tác với học sinh, giáo viên.

## Nhóm SCDTeam
------------
| MSSV | Họ tên | SĐT | Ghi chú |
|--------------|-------|------|-------|
| 20520811 | Trần Trọng Tín | 0836346035 | Nhóm trưởng | 
| 20521569 | Nguyễn Hữu Long | 0775504619 |  | 
| 20520881 | Phạm Hoàng Ngọc Anh | 0327486284 |  | 
| 20521083 | Trần Thị Ngọc Ánh | 0363961281 | | 

## Các chức năng chính trong ứng dụng
----------------
### Chức năng chính cho khách hàng
> * Đăng nhập
> * Đăng ký
> * Chỉnh sửa mật khẩu
> * Xem và cập nhật thông tin cá nhân
> * Xem giỏ hàng
> * Mua Vé
> * Nạp tiền
> * Xem thông tin vé
### Chức năng người quản trị
> * Đăng nhập
> * Quản lý khách hàng
> * Quản lý khách vãng lai
> * Quản lý xe
> * Quản lý chi tiết ra vào
> * Quản lý nhân viên
> * Tra cứu hóa đơn
> * Tra cứu vé
> * Báo cáo thống kê


## Các ngôn ngữ, công nghệ sử dụng
> * Ngôn ngữ sử dụng: `Java`
> * IDE sử dụng: `Netbeans`
> * Công cụ lập trình giao diện: `JavaSwing`
> * Cơ sở dữ liệu: `Oracle`
> * Phần mềm quản lý dự án: `Git`, `Github`
> * Công cụ hỗ trợ report: `Ireport`
## Các chức năng, công nghệ mới (ngoài phạm vi môn học)
> * Sử dụng Regex để kiểm tra các ràng buộc đối với các trường dữ liệu, tối ưu tìm kiếm.
> * Sử dụng JavaMail để thực hiện việc gửi mail tự động khi người dùng có nhu cầu khôi phục lại mật khẩu.
> * Đóng gói dự án thành file Jar với các thư viện đi cùng.
## Hướng dẫn cài đặt chương trình
> * **Bước 1:** Clone project [java_IS216.M21_10](https://github.com/nhlong1512/java_IS216.M21_10.git)
> * **Bước 2:** Vào Netbeans chọn Open project và mở project vừa clone về.
> * **Bước 3:** Thêm tất cả các thư viện cần thiết trong mục src/UITParking/Libs.
> * **Bước 4:** Trong src/Database có file UITParking.sql và file Dữ_liệu.xlsx. Tiến hành vào oracle tạo user mới với username là UITParking và pass là uitparking, sau đó ta lần lượt import dữ liệu từ file Dữ_liệu.xlsx vào cơ sở dữ liệu.
> * **Bước 5:** Tiến hành chạy run file src/UITParking/GUI/DangNhap để chạy chương trình
>* ***Note:** Xem file hướng dẫn dưới đây để hiểu rõ thêm: [File hướng dẫn chi tiết](https://docs.google.com/document/d/12nlbwelXyroU7zfoc4HSwkQ9vcOK0F14/edit?usp=sharing&ouid=110784418486414862850&rtpof=true&sd=true)*

## Tài liệu kèm theo 
> * [Video Demo chức năng App UITParking](https://drive.google.com/drive/folders/1gOLhQ7nVdZ1-XEp0wkNsNTYG2l7ihB46?usp=sharing)
> * [Tài liệu tham khảo]()

