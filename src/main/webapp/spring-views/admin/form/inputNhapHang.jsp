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
</style>
<body>
	<div style="margin-top: 3%;" class="container card">
		<div class="card-header">
			<h4>Nhập Thông Tin Chi Phí</h4>
		</div>
		<form:form action="admin-home/formNhapHang.htm" modelAttribute="nh"
			method="post">
			<div class="form-group">
				<label>ID</label> <br>
				<form:input readonly="true" path="id" type="number" />
			</div>
			<div class="form-row">
				<div class="col-12">
					<label>Tên Nguyên Liệu</label> <br>
					<form:input path="tenNL" type="text" class="form-control"
						placeholder="Sữa đặc, Sữa chua,...." />
				</div>

			</div>
			<div class="form-group">
				<label>Số Lượng</label> <br>
				<form:input path="soLuong" type="number" placeholder="1234" />
			</div>

			<div class="form-row">
				<div class="form-group col-md-4">
					<label>Giá Mỗi Dịch Vụ</label> <br>
					<form:input path="giaMoiDV" type="number" />
				</div>
				<div class="form-group col-md-4">

					<label>Loại Dịch Vụ</label> <br>
					<form:input path="loai" type="text" />
				</div>
				<div class="form-group col-md-4">
					<div class="form-group col-md-6">
						<label>Dịch Vụ</label> <br>
						<form:input path="dv" type="text" />
					</div>
				</div>

			</div>
			<div class="form-row">
				<div class="form-group col-md-4">
					<label>Nhà Cung Cấp</label> <br>
					<form:input path="nhaCungCap" type="text" />
				</div>

				<div class="form-group col-md-4">
					<div class="form-group col-md-4">
						<label>Ghi Chú</label> <br>
						<form:input path="ghiChu" type="text" />
					</div>
				</div>


			</div>
			
			<div class="form-row">
					<div class="form-group col-md-6">
						<label>Ngày Nhập</label> <br> <input name="ngaynhaphang"
							type="datetime-local" id="set-date" value="${ngaynhaphang}" />
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

</body>
</html>