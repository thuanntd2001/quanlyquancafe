<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>

<%@include file="/common/taglib.jsp"%>
<!DOCTYPE html>
<html lang="en">


<head>
<meta charset="UTF-8" />
<meta http-equiv="X-UA-Compatible" content="IE=edge" />
<meta name="viewport" content="width=device-width, initial-scale=1.0" />
<base href="${pageContext.servletContext.contextPath}/">
<link rel="stylesheet"
	href="https://maxcdn.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css">
<title>Document</title>
</head>
<body>
	<div style="margin-top: 5%;" class="container">
		<form:form action="admin-home/formTaiKhoan.htm" modelAttribute="tk"
			method="post">
			<div class="form-row">
				<div class="form-group col-md-6">
					<label>UserName</label>
					<form:input path="userName" type="text"  />
				</div>


			</div>
			<div class="form-group">
				<label>Mã Nhân Viên</label>
				<input name="manv" type="text" class="form-control"
					 />
			</div>
			<div class="form-row">
				<div class="form-group col-md-6">
					<label>Mật Khẩu</label>
					<form:input path="passwd" type="text" />
				</div>
				<div class="form-group col-md-4">


					<label>Nhập Lại Mật Khẩu</label> <input name="comfirm"
						type="number" />
				</div>


			</div>
			<div class="form-row">
				<div class="form-group col-md-6">
					<label>Chức Vụ</label>
					
					<form:select path="tenChucVu" items="${chucvus}" />
				</div>

				<div class="form-group col-md-2">
					<div class="form-group col-md-2">

						<form:hidden path="status" type="number" value="1" />
					</div>
				</div>
			</div>
			<button class="btn btn-primary" type="submit"
				name="${btnupdate ? 'btnupdate' : 'Insert'}">${btnupdate ? 'Update' : 'Insert'}</button>
			<a style="font-size: 16px; padding: 10px;" class="btn btn-secondary"
				href="admin-home/index.htm"> QUAY LẠI </a>
		</form:form>
	</div>
</body>
</html>