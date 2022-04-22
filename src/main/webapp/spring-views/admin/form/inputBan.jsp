<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<base href="${pageContext.servletContext.contextPath}/">
<link rel="stylesheet"
	href="https://maxcdn.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css">
<title>Thêm Bàn</title>
</head>
<body>
	<div style="margin-top: 5%;" class="container">
		<form:form action="admin-home/formBan.htm" modelAttribute="b"
			method="post">
			<div class="form-row">
				<div class="form-group col-md-6">
					<label>Số Ghế</label>
					<form:input path="soGhe" type="number" placeholder="2,4,10...." />
				</div>

			</div>
			<div class="form-group">
				<label>Loại Bàn</label>
				<form:select path="loaiBan">
					<form:options items="${loaiban.getLoaiBans}" />
				</form:select>
				
			</div>
			<div class="form-row">
				<div class="form-group col-md-6">
					<label>Giá Thành</label>
					<form:select path="loaiBan.giaDat" items="${loaiban.giaDat}" />
				</div>
			</div>


			<button class="btn btn-primary" type="submit"
				name="${btnupdate ? 'btnupdate' : 'Insert'}">${btnupdate ? 'Update' : 'Insert'}</button>
			<a style="font-size: 16px; padding: 10px;" class="btn btn-secondary"
				href="admin-home/admin-qlban.htm"> QUAY LẠI </a>
		</form:form>
	</div>

</body>
</html>