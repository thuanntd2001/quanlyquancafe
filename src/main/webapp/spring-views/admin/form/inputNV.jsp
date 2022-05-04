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
<link rel="stylesheet"
	href=<c:url value="/common/vendor/themify-icons/themify-icons.css"/>>
<title>Document</title>
</head>
<style>
label {
	font-weight: bold;
}

body {
	background-color:#d7d7d7;
}
</style>
<body >
	<div style="margin-top: 2%; border-style: groove;" class="container card">
	<div class="card-header">
	<h4>Nhập Thông Tin Nhân Viên</h4>
	</div>
		<form:form class ="card-body" action="admin-home/form.htm" modelAttribute="nv"
			method="post">
			<div class="form-row">
				<div class="form-group col-md-6">
					<label>Họ Tên</label>

					<div class="input-group">
						<div class="input-group-prepend">
							<span class="input-group-text" id="basic-addon1"> <i
								class="ti-user" aria-hidden="true"></i>
							</span>
						</div>
						<form:input type="text" class="form-control"
							placeholder="Nguyễn Văn A" path="hoTen" aria-label="Username"
							aria-describedby="basic-addon1" />

					</div>



				</div>
				<div class="form-group col-md-6">
					<label>Mã Nhân Viên</label> <br>
					<form:input path="maNV" readonly="true" type="text"
						placeholder="Mã nhân viên" value="${id}" />
				</div>
			</div>
			<div class="form-group">
				<label>Địa chỉ</label>
				<form:input path="diaChi" type="text" class="form-control"
					placeholder="1234 Main St" />
			</div>
			<div class="form-row">
				<div class="form-group col-md-6">
					<label>CMND/CCCD</label> <br>
					<form:input title="phải nhập đúng định dạng là số" pattern="{1,9}"
						path="cmnd" type="number" />
				</div>
				<div class="form-group col-md-4">


					<label>Tiền lương</label>
					<%-- <form:input path="luong" type="number" /> --%>

					<div class="input-group">
						<div class="input-group-prepend">
							<span class="input-group-text" id="basic-addon1"> </span>
						</div>
						<form:input type="text" class="form-control" path="luong"
							aria-label="luong" aria-describedby="basic-addon1" />
						<div class="input-group-append">
							<span class="input-group-text">vnđ</span>
						</div>
					</div>
				</div>


			</div>
			<div class="form-row">


				<div class="form-group col-md-6">
					<div class="form-group">
						<label>Ngày Sinh</label> <br> <input type="date"
							name="ngaysinh" value="${ngaysinh}" />
					</div>
				</div>

				<div class="form-group col-md-6">
					<div class="form-group">
						<label>Ngày vào làm</label> <br> <input type="date"
							name="ngayvaolam" value="${ngayvaolam}" />
					</div>
				</div>



			</div>
			<div class="form-row row">
				<div class="form-group col-md-6">
					<label>Số Điện Thoại</label> <br>
					<form:input path="sdt" pattern="[1-9]" min="10"
						title="Username should only contain lowercase letters. e.g. john" />
				</div>
			</div>

			<div class="form-group row">
				<label class="col-sm-2 col-form-label">Giới Tính</label>
				<div class="col-sm-10">
					<div class="form-check form-check-inline">
						<form:radiobutton path="gioiTinh" value="1" />
						<label class="form-check-label">Nam</label>
					</div>
					<div class="form-check form-check-inline">
						<form:radiobutton path="gioiTinh" value="0" />
						<label class="form-check-label">Nữ</label>
					</div>
				</div>
			</div>

			<div class="form-group row">
				<label class="col-sm-2 col-form-label">Nghỉ làm</label>
				<div class="col-sm-10">
					<div class="form-check form-check-inline">
						<form:radiobutton path="daNghi" value="0" />
						<label class="form-check-label">Đang làm</label>
						<div class="form-check form-check-inline">
							<form:radiobutton path="daNghi" value="1" />
							<label class="form-check-label">Đã nghỉ</label>
						</div>

					</div>
				</div>
			</div>

            <div class="card-footer">
            <button class="btn btn-primary" type="submit"
				name="${btnupdate ? 'btnupdate' : 'Insert'}">${btnupdate ? 'Update' : 'Insert'}</button>
			<a style="font-size: 16px; padding: 10px;" class="btn btn-secondary"
				href="admin-home/index.htm"> QUAY LẠI </a>
            </div>

			
		</form:form>
		
	</div>
</body>
</html>