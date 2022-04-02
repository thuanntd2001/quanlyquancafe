<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@include file="/common/taglib.jsp"%>
<!DOCTYPE html>
<html lang="en">

<head>
<meta charset="UTF-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<title>Highland Coffee</title>
<base href="${pageContext.servletContext.contextPath}/">
<link rel="stylesheet"
	href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/5.15.4/css/all.min.css">
<link rel="stylesheet"
	href="https://maxcdn.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css">
<link
	href="https://fonts.googleapis.com/css?family=Open+Sans:300,300i,400,400i,600,600i,700,700i|Nunito:300,300i,400,400i,600,600i,700,700i|Poppins:300,300i,400,400i,500,500i,600,600i,700,700i"
	rel="stylesheet">

<link href="<c:url value='/template/web/styles.css'/>" rel="stylesheet"
	type="text/css">

</head>
<body>
	<jsp:include page="/common/web/header.jsp" />
	<jsp:include page="/common/web/menubar.jsp" />

	<div class="container-fluid main">
		<div class="container">
			<div class="content">
				<div class="header-content d-flex justify-content-center">
					THÔNG TIN TÀI KHOẢN</div>
				<section class="section profile">
					<div class="row">
						<div class="col-xl-4">

							<div class="card">
								<div
									class="card-body profile-card pt-4 d-flex flex-column align-items-center">
									<img src="<c:url value='./common/images/avt.jpg' />"
										alt="Profile" class="rounded-circle">
									<h2>Huỳnh Ngọc Dương</h2>
									<h3>nv1</h3>
								</div>
							</div>

						</div>
						<div class="col-xl-8">
							<div class="card">
								<div class="card-body pt-3">
									<!-- Bordered Tabs -->
									<ul class="nav nav-tabs nav-tabs-bordered">

										<li class="nav-item">
											<button class="nav-link active" data-bs-toggle="tab"
												data-bs-target="#profile-overview">Thông Tin</button>
										</li>

										<li class="nav-item">
											<button class="nav-link" data-bs-toggle="tab"
												data-bs-target="#profile-edit">Sửa Thông Tin</button>
										</li>

										<li class="nav-item">
											<button class="nav-link" data-bs-toggle="tab"
												data-bs-target="#profile-change-password">Đổi Mật
												Khẩu</button>
										</li>

									</ul>
									<div class="tab-content pt-2">

										<div class="tab-pane fade show active profile-overview"
											id="profile-overview">

											<h5 class="card-title">Thông Tin Cá Nhân</h5>

											<div class="row">
												<div class="col-lg-3 col-md-4 label ">Mã Nhân Viên</div>
												<div class="col-lg-9 col-md-8">01</div>
											</div>

											<div class="row">
												<div class="col-lg-3 col-md-4 label">Họ Tên</div>
												<div class="col-lg-9 col-md-8">Huỳnh Ngọc Dương</div>
											</div>

											<div class="row">
												<div class="col-lg-3 col-md-4 label">Giới Tính</div>
												<div class="col-lg-9 col-md-8">Nam</div>
											</div>

											<div class="row">
												<div class="col-lg-3 col-md-4 label">Ngày Sinh</div>
												<div class="col-lg-9 col-md-8">01/01/2001</div>
											</div>

											<div class="row">
												<div class="col-lg-3 col-md-4 label">SDT</div>
												<div class="col-lg-9 col-md-8">123456789</div>
											</div>

											<div class="row">
												<div class="col-lg-3 col-md-4 label">CMND</div>
												<div class="col-lg-9 col-md-8">123456789</div>
											</div>

											<div class="row">
												<div class="col-lg-3 col-md-4 label">Email</div>
												<div class="col-lg-9 col-md-8">abc@gmail.com</div>
											</div>

											<div class="row">
												<div class="col-lg-3 col-md-4 label">Địa Chỉ</div>
												<div class="col-lg-9 col-md-8">52 Man Thiện, Tăng Nhơn
													Phú A, Q9, TPHCM</div>
											</div>

											<div class="row">
												<div class="col-lg-3 col-md-4 label">Tên Tài Khoản</div>
												<div class="col-lg-9 col-md-8">user1</div>
											</div>
										</div>

										<div class="tab-pane fade profile-edit pt-3" id="profile-edit">

											<!-- Profile Edit Form -->
											<form action="user-avt.htm" method="post">
												<div class="row mb-3">
													<label for="profileImage"
														class="col-md-4 col-lg-3 col-form-label">Ảnh Đại
														Diện</label>
													<div class="col-md-8 col-lg-9">
														<img src="<c:url value='./common/images/avt.jpg' />"
															alt="ảnh đại diện" class="avt-pic">
														<div class="pt-2">
															<div class="btn btn-primary btn-sm"
																style="position: relative;">
																<input type="file" accept=".jpg, .png"
																	class="file-upload" name="avt" /> <i
																	class="fas fa-upload"></i>
															</div>
															<a href="#" class="btn btn-danger btn-sm"
																title="Remove my profile image"> <i
																class="fas fa-trash-alt"></i></a>
														</div>
													</div>
												<input type="submit">
											</form>
											<form action="">
												<div class="row mb-3">
													<label for="myid" class="col-md-4 col-lg-3 col-form-label">Mã
														Nhân Viên</label>
													<div class="col-md-8 col-lg-9">
														<input name="myid" type="text" class="form-control"
															id="myid" value="01" disabled />
													</div>
												</div>


												<div class="row mb-3">
													<label for="fullname"
														class="col-md-4 col-lg-3 col-form-label">Họ Tên</label>
													<div class="col-md-8 col-lg-9">
														<input name="fullname" type="text" class="form-control"
															id="fullname" value="Huỳnh Ngọc Dương" disabled />
													</div>
												</div>

												<div class="row mb-3">
													<label for="sex" class="col-md-4 col-lg-3 col-form-label">Giới
														Tính</label>
													<div class="col-md-8 col-lg-9">
														<input id="male" type="radio" name="sex" value="Nam"
															checked /> <label for="male">Nam</label> <input
															id="female" type="radio" name="sex" value="Nữ" /> <label
															for="female">Nữ</label>
													</div>
												</div>

												<div class="row mb-3">
													<label for="birth" class="col-md-4 col-lg-3 col-form-label">Ngày
														Sinh</label>
													<div class="col-md-8 col-lg-9">
														<input type="date" name="" id="birth" value="2001-01-01" />
													</div>
												</div>

												<div class="row mb-3">
													<label for="phone" class="col-md-4 col-lg-3 col-form-label">SDT</label>
													<div class="col-md-8 col-lg-9">
														<input name="phone" type="text" class="form-control"
															id="phone" value="123456789" />
													</div>
												</div>

												<div class="row mb-3">
													<label for="cmnd" class="col-md-4 col-lg-3 col-form-label">CMND</label>
													<div class="col-md-8 col-lg-9">
														<input name="cmnd" type="text" class="form-control"
															id="cmnd" value="123456789" />
													</div>
												</div>

												<div class="row mb-3">
													<label for="Email" class="col-md-4 col-lg-3 col-form-label">Email</label>
													<div class="col-md-8 col-lg-9">
														<input name="email" type="email" class="form-control"
															id="Email" value="abc@gmail.com" />
													</div>
												</div>

												<div class="row mb-3">
													<label for="address"
														class="col-md-4 col-lg-3 col-form-label">Địa Chỉ</label>
													<div class="col-md-8 col-lg-9">
														<input name="address" type="text" class="form-control"
															id="address"
															value="52 Man Thiện, Tăng Nhơn Phú A, Q9, TPHCM" />
													</div>
												</div>

												<div class="row mb-3">
													<label for="username"
														class="col-md-4 col-lg-3 col-form-label">Tên Tài
														Khoản</label>
													<div class="col-md-8 col-lg-9">
														<input name="username" type="text" class="form-control"
															id="username" value="user1" disabled />
													</div>
												</div>

												<div class="text-center">
													<button type="submit" class="btn btn-primary">Lưu
														Thay Đổi</button>
												</div>
											</form>
											</form>
											<!-- End Profile Edit Form -->

										</div>

										<div class="tab-pane fade pt-3" id="profile-change-password">
											<!-- Change Password Form -->
											<form>

												<div class="row mb-3">
													<label for="currentPassword"
														class="col-md-4 col-lg-3 col-form-label">Mật Khẩu
														Cũ</label>
													<div class="col-md-8 col-lg-9">
														<input name="password" type="password"
															class="form-control" id="currentPassword" />
													</div>
												</div>

												<div class="row mb-3">
													<label for="newPassword"
														class="col-md-4 col-lg-3 col-form-label">Mật Khẩu
														Mới</label>
													<div class="col-md-8 col-lg-9">
														<input name="newpassword" type="password"
															class="form-control" id="newPassword" />
													</div>
												</div>

												<div class="row mb-3">
													<label for="renewPassword"
														class="col-md-4 col-lg-3 col-form-label">Nhập Lại
														Mật Khẩu Mới</label>
													<div class="col-md-8 col-lg-9">
														<input name="renewpassword" type="password"
															class="form-control" id="renewPassword" />
													</div>
												</div>

												<div class="text-center">
													<button type="submit" class="btn btn-primary">Đổi
														Mật Khẩu</button>
												</div>
											</form>
											<!-- End Change Password Form -->

										</div>

									</div>
									<!-- End Bordered Tabs -->

								</div>
							</div>

						</div>
					</div>
				</section>

			</div>

		</div>
	</div>
	<jsp:include page="/common/web/footer.jsp" />
	<script
		src="https://cdn.jsdelivr.net/npm/bootstrap@5.0.1/dist/js/bootstrap.bundle.min.js"></script>
	<script
		src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>
	<script
		src="https://maxcdn.bootstrapcdn.com/bootstrap/4.5.2/js/bootstrap.min.js"></script>
</body>
</body>

</html>