<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib uri="http://java.sun.com/jstl/core_rt" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Thêm Đơn Nhập Hàng</title>
<base href="${pageContext.servletContext.contextPath}/">
<link rel="stylesheet"
	href="https://maxcdn.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css">
</head>

<style>
label {
	font-weight: bold;
}

body {
	background-color: #d7d7d7;
}
.form-message{
	color:red;
}
</style>
<body>
	<div style="margin-top: 3%;" class="container card">
		<div class="card-header">
			<h4>Nhập Thông Tin Chi Phí</h4>
		</div>
		<form:form id="form-1" action="admin-home/formNhapHang.htm" modelAttribute="nh"
			method="post">
			<div class="form-group">
				<label>ID</label> <br>
				<form:input readonly="true" path="id" type="number" />
			</div>
			<div class="form-row">
				<div class="col-8">
					<label>Tên Nguyên Liệu</label> <br>
					<form:input id="NL" path="tenNL" type="text" class="form-control"
						placeholder="Sữa đặc, Sữa chua,...." />
							<span class="form-message"></span>
				</div>

			</div>
			<div class="form-group">
				<label>Số Lượng</label> <br>
				<form:input id="SL" path="soLuong" type="number" placeholder="1234" />
				<span class="form-message"></span>
			</div>

			<div class="form-row">
				<div class="form-group col-md-4">
					<label>Giá Mỗi Dịch Vụ</label> <br>
					<form:input id="giaMoiDV" path="giaMoiDV" type="number" />
						<span class="form-message"></span>
				</div>
				<div class="form-group col-md-4">

					<label>Loại Dịch Vụ</label> <br>
					<form:input id="loaiDV" path="loai" type="text" />
						<span class="form-message"></span>
				</div>
				<div class="form-group col-md-4">
					<div class="form-group col-md-6">
						<label>Dịch Vụ</label> <br>
						<form:input id="DV" path="dv" type="text" />
						<span class="form-message"></span>
					</div>
				</div>

			</div>
			<div class="form-row">
				<div class="form-group col-md-4">
					<label>Nhà Cung Cấp</label> <br>
					<form:input id="nhaCC" path="nhaCungCap" type="text" />
					<span class="form-message"></span>
				</div>

				<div class="form-group col-md-4">
					<div class="form-group col-md-4">
						<label>Ghi Chú</label> <br>
						<form:input id="ghichu" path="ghiChu" type="text" />
						<span class="form-message"></span>
					</div>
				</div>


			</div>
			
			<div class="form-row">
					<div class="form-group col-md-6">
						<label>Ngày Nhập</label> <br> <input  name="ngaynhaphang"
							type="datetime-local" id="set-date" value="${ngaynhaphang}" />
							<span class="form-message"></span>
					</div>
				</div>

			<div class="card-footer">
				<button class="btn btn-primary" type="submit"
					name="${btnupdate ? 'btnupdate' : 'Insert'}">${btnupdate ? 'Update' : 'Insert'}</button>
				<a style="font-size: 16px; padding: 10px;" class="btn btn-secondary"
					href="admin-home/admin-nhaphang.htm"> QUAY LẠI </a>
			</div>
		</form:form>
	</div>
	<script src="<c:url value='/template/admin/validation.js'/>"></script>
	 <script>
  
        document.addEventListener('DOMContentLoaded', function () {
          // Mong muốn của chúng ta
          Validator({
            form: '#form-1',
            formGroupSelector: '.form-group',
            errorSelector: '.form-message',
            rules: [
              Validator.isRequired('#NL', 'Vui lòng nhập tên Nguyên liệu'),
              Validator.isRequired('#SL', 'Vui lòng nhập số lượng'),
              Validator.isRequired('#giaMoiDV', 'Vui lòng nhập giá mỗi dịch vụ'),
              Validator.isRequired('#set-date', 'Vui lòng nhập vào ngày nhập hàng'),
              Validator.isRequired('#ghichu', 'Vui lòng nhập ghi chú'),
              Validator.isRequired('#nhaCC', 'Vui lòng nhập thông tin nhà cung cấp'),
              Validator.isRequired('#DV', 'Vui lòng nhập tên dịch vụ'),
              Validator.isRequired('#loaiDV', 'Vui lòng loại dịch vụ'),
           /*  Validator.isEmail('#email'),
              Validator.minLength('#password', 6),
              Validator.isRequired('#password_confirmation'),
              Validator.isConfirmed('#password_confirmation',  function () {
                return document.querySelector('#form-1 #password').value;
              }, 'Mật khẩu nhập lại không chính xác') */
            ],
           
          });
  
        }); 
  
      </script>

</body>
</html>