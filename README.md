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

## Mục tiêu
Xây dựng được một hệ thống ứng dụng hoàn thiện để mọi người có nhu cầu gửi xe tại UIT có một hệ thống gửi xe một cách an toàn, thuận tiện, dễ quản lý. Người gửi xe có thể là sinh viên đang học tại trường UIT, cán bộ công nhân viên của trường, sinh viên các trường khác hay các phụ của sinh viên,...(gọi chung là khách vãng lai). Hệ thống sẽ cung cấp những chức năng dành riêng cho một bộ phận nào đó như: Sinh viên, cán bộ công nhân viên chức có nhu cầu gửi xe tại trường, quản lý số lần gửi xe, thời gian gửi xe tại trường; bộ phận bảo vệ có thể quản lý số lượng, thông tin xe được gửi; phòng kế hoạch tài chính dễ dàng nắm bắt được doanh thu theo ngày/tháng/năm,… Ngoài ra, hệ thống còn giúp cho sinh viên tiết kiệm thời gian thanh toán tiền (đối với sinh viên có thẻ thành viên), hạn chế được việc phải luôn có tiền lẻ để gửi xe hay hạn chế được tình trạng xếp hàng dài khi đợi thanh toán; tránh việc để xe không ngay ngắn, làm ảnh hưởng đến việc để xe của người khác,…  
Trong đó hệ thống sẽ đáp ứng được các yêu cầu sau: 
* Quản lý thông tin sinh viên, nhân viên, phương tiện (xe), thông tin hóa đơn, thông tin vé. Trong đó, thông tin sinh viên và nhân viên sẽ bao gồm thông tin tài khoản để đăng nhập vào app quản lý.
* Quản lý hoạt động gửi xe của sinh viên: sinh viên có thể tra cứu được thông tin số lần gửi xe, thời gian xe ở trong nhà xe, số tiền phải trả cho 1 lần gửi xe hay 1 tuần/tháng (áp dụng với gói đăng ký tuần/tháng) và những thông tin liên quan khác.
* Đảm bảo việc tra cứu thông tin xe: chủ sở hữu của xe, tình trạng xe (xe có còn trong nhà xe hay không), biển số xe,…
Quản lý doanh thu gửi xe trong 1 ngày/tháng/năm.  
* Quản lý được số lượng vé lượt/tuần/tháng bán ra.
* Quản lý thông tin hóa đơn ở mỗi lần gửi xe.
* Thông tin lưu trữ sao cho việc kết xuất dữ liệu được nhanh chóng, hiệu quả. 
* Hệ thống hỗ trợ phân quyền người dùng, đảm bảo tính bảo mật, an toàn của hệ thống.
## Nhóm SCDTeam
------------
| MSSV | Họ tên | SĐT | Facebook| Đánh giá %|
|--------------|-------|------|-------|------|
| 20520811 | Trần Trọng Tín | 0836346035 | [Trọng Tín](https://www.facebook.com/trongtin112)  | 25 |
| 20521569 | Nguyễn Hữu Long | 0775504619 | [Long Nguyễn](https://www.facebook.com/nui.gaxuong)  | 25 |
| 20520881 | Phạm Hoàng Ngọc Anh | 0327486284 | [Phạm Hoàng Ngọc Nấm](https://www.facebook.com/phamhoangngocanh0406) | 25 |
| 20521083 | Trần Thị Ngọc Ánh | 0363961281 | [Ngọc Ánh](https://www.facebook.com/Yinggg.TTNA) | 25 |

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

### Các chức năng, công nghệ mới (ngoài phạm vi môn học)
> * Sử dụng Regex để kiểm tra các ràng buộc đối với các trường dữ liệu, tối ưu tìm kiếm.
> * Sử dụng JavaMail để thực hiện việc gửi mail tự động khi người dùng có nhu cầu khôi phục lại mật khẩu.
> * Đóng gói dự án thành file Jar với các thư viện đi cùng.
> * Import dữ liệu từ file excel
> * Sử dụng kỹ thuật băm MD5 để băm mật khẩu, nhằm tăng bảo mật cho người dùng.
## Các ngôn ngữ, công nghệ sử dụng
> * Ngôn ngữ sử dụng: `Java`
> * IDE sử dụng: `Netbeans`
> * Công cụ lập trình giao diện: `JavaSwing`
> * Cơ sở dữ liệu: `Oracle`
> * Phần mềm quản lý dự án: `Git`, `Github`
> * Công cụ hỗ trợ report: `Ireport`
## Yêu cầu hệ thống
> * Sử dụng `JDK 17`
> * Sử dụng `ojdbc8.jar`
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

