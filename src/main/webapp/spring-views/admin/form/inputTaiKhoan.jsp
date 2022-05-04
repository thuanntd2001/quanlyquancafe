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
<body>
	<div style="margin-top: 5%;" class="container">
		<form:form action="admin-home/formTaiKhoan.htm" modelAttribute="tk"
			method="post">
			<div class="form-row">
				<div class="form-group col-md-6">
					<label>UserName</label>
					<form:input  path="userName" type="text"  />
				</div>
			</div>
			<div class="form-group">
				<label>Mã Nhân Viên</label> <input value="${maNV}" name="manv" type="number"
					class="form-control" />
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

				<div class="form-group col-md-4">


					<label>Email</label> <form:input path="email"
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
			<button class="btn btn-primary" type="submit"
				name="${btnupdate ? 'btnupdate' : 'Insert'}">${btnupdate ? 'Update' : 'Insert'}</button>
			<a style="font-size: 16px; padding: 10px;" class="btn btn-secondary"
				href="admin-home/admin-taikhoan.htm"> QUAY LẠI </a>
		</form:form>
	</div>
</body>
</html>