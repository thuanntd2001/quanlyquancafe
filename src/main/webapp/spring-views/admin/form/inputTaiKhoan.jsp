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
<title>Thêm tài khoản</title>
</head>

<style>
label {
	font-weight: bold;
}

body {
	background-color:#d7d7d7;
}
</style>
<body>
	<div style="margin-top: 5%;" class="container card">
	<div class="card-header">
			<h4>Nhập Thông Tài Khoản</h4>
		</div>
		<form:form  class ="card-body"	 action="admin-home/formTaiKhoan.htm" modelAttribute="tk"
			method="post">
			<div class="form-row">
				<div class="form-group col-md-6">
					<label>UserName</label>
					<br>
					<form:input  path="userName" type="text"  />
				</div>
			</div>
			<div class="form-group">
				<label>Mã Nhân Viên</label><br> <input value="${maNV}" name="manv" type="number"
					class="form-control" />
			</div>
			<div class="form-row">
				<div class="form-group col-md-6">
					<label>Mật Khẩu</label><br>
					<form:input path="passwd" type="text" />
				</div>
				<div class="form-group col-md-4">


					<label>Nhập Lại Mật Khẩu</label> <br><input name="comfirm"
						type="number" />
				</div>

				<div class="form-group col-md-4">


					<label>Email</label> <br><form:input path="email"
						type="email" />
				</div>

			</div>
			<div class="form-row">
				<div class="form-group">
					<div class="select-menu">
						
						<h5>
						<select name="chucvu">
							<c:forEach items="${chucvus}" var="cv">

								<option value="${cv.id}"
									<c:if test="${cv.id == idCV}" >selected</c:if>>
									${cv.tenChucVu}</option>
							</c:forEach>
						</select>
					</h5>


					</div>
				</div>
			</div>
			<div class="card-footer">
			<button class="btn btn-primary" type="submit"
				name="${btnupdate ? 'btnupdate' : 'Insert'}">${btnupdate ? 'Update' : 'Insert'}</button>
			<a style="font-size: 16px; padding: 10px;" class="btn btn-secondary"
				href="admin-home/admin-taikhoan.htm"> QUAY LẠI </a>
				</div>
		</form:form>
	</div>
</body>
</html>