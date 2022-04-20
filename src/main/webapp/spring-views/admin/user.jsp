<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@include file="/common/taglib.jsp"%>
<!DOCTYPE html>
<html lang="en">

<head>
<jsp:include page="/common/admin/head.jsp" />

</head>
<body>
	<jsp:include page="/common/admin/header.jsp" />
			<jsp:include page="/common/admin/menubar.jsp" />

	<div class="container-fluid main">
		<div class="container">
			<div class="content">
				<div class="header-content d-flex justify-content-center">
					THÔNG TIN TÀI KHOẢN</div>
				<section class="section profile">
					<div class="row">
						<div class="col-xl-4">

							<div class="card">
								<form:form modelAttribute="user"
									class="card-body profile-card pt-4 d-flex flex-column align-items-center">
									<img src="files/${sessionScope.USERMODEL.icon}" alt="Profile"
										class="rounded-circle">
									<h2>${user.usernv.hoTen }</h2>
									<h3>${user.userName }</h3>
								</form:form>
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

										<li class="nav-item">
											<button class="nav-link" data-bs-toggle="tab"
												data-bs-target="#avata-edit">Đổi ảnh đại diện</button>
										</li>
									</ul>
									<div class="tab-content pt-2">
										<div class="tab-pane fade show active profile-overview"
											id="profile-overview">
											<form:form modelAttribute="nv,user">
												<h5 class="card-title">Thông Tin Cá Nhân</h5>
												<div class="row">
													<div class="col-lg-3 col-md-4 label ">Mã Nhân Viên</div>
													<div class="col-lg-9 col-md-8">${nv.maNV }</div>
												</div>

												<div class="row">
													<div class="col-lg-3 col-md-4 label">Họ Tên</div>
													<div class="col-lg-9 col-md-8">${nv.hoTen }</div>
												</div>

												<div class="row">
													<div class="col-lg-3 col-md-4 label">Giới Tính</div>
													<div class="col-lg-9 col-md-8">${nv.gioiTinh?'Nam':'Nữ' }</div>
												</div>

												<div class="row">
													<div class="col-lg-3 col-md-4 label">Ngày Sinh</div>
													<div class="col-lg-9 col-md-8">${nv.ngaySinh }</div>
												</div>

												<div class="row">
													<div class="col-lg-3 col-md-4 label">Lương</div>
													<div class="col-lg-9 col-md-8">${nv.luong }</div>
												</div>

												<div class="row">
													<div class="col-lg-3 col-md-4 label">SDT</div>
													<div class="col-lg-9 col-md-8">${nv.sdt }</div>
												</div>

												<div class="row">
													<div class="col-lg-3 col-md-4 label">CMND</div>
													<div class="col-lg-9 col-md-8">${nv.cmnd }</div>
												</div>

												<div class="row">
													<div class="col-lg-3 col-md-4 label">Email</div>
													<div class="col-lg-9 col-md-8">${user.email }</div>
												</div>

												<div class="row">
													<div class="col-lg-3 col-md-4 label">Địa Chỉ</div>
													<div class="col-lg-9 col-md-8">${nv.diaChi }</div>
												</div>

												<div class="row">
													<div class="col-lg-3 col-md-4 label">Ngày vào làm</div>
													<div class="col-lg-9 col-md-8">${nv.ngayVaoLam }</div>
												</div>

												<div class="row">
													<div class="col-lg-3 col-md-4 label">Tên Tài Khoản</div>
													<div class="col-lg-9 col-md-8">${user.userName }</div>
												</div>
											</form:form>
									</div>

									<div class="tab-pane fade profile-edit pt-3" id="profile-edit">
										<!-- Profile Edit Form -->
										<form:form modelAttribute="nv" method="post" action="user.htm">
													${message }
													<div class="row mb-3">
												<label class="col-md-4 col-lg-3 col-form-label"></label>
												<div class="col-md-8 col-lg-9">
													<form:hidden cssClass="form-control" path="daNghi" />
												</div>
											</div>

											<div class="row mb-3">
												<label class="col-md-4 col-lg-3 col-form-label">Mã
													Nhân Viên</label>
												<div class="col-md-8 col-lg-9">
													<form:input path="maNV" cssClass="form-control"
														readonly="true" />
												</div>
											</div>


											<div class="row mb-3">
												<label class="col-md-4 col-lg-3 col-form-label">Họ
													Tên</label>
												<div class="col-md-8 col-lg-9">
													<form:input cssClass="form-control" path="hoTen"
														readonly="true" />
												</div>
											</div>

											<div class="row mb-3">
												<label class="col-md-4 col-lg-3 col-form-label">Giới
													Tính</label>
												<div class="col-md-8 col-lg-9">
													<form:radiobutton path="gioiTinh" value="True" label="Nam" />
													<form:radiobutton path="gioiTinh" value="false" label="Nữ" />
												</div>
											</div>

											<div class="row mb-3">
												<label class="col-md-4 col-lg-3 col-form-label">Ngày
													Sinh</label>
												<div class="col-md-8 col-lg-9">
													<form:input path="ngaySinh" type="date"/>
												</div>
											</div>

											<div class="row mb-3">
												<label class="col-md-4 col-lg-3 col-form-label">SDT</label>
												<div class="col-md-8 col-lg-9">
													<form:input cssClass="form-control" path="sdt" />
												</div>
											</div>

											<div class="row mb-3">
												<label for="cmnd" class="col-md-4 col-lg-3 col-form-label">Lương</label>
												<div class="col-md-8 col-lg-9">
													<form:input cssClass="form-control" path="luong"
														readonly="true" type="number" />
												</div>
											</div>

											<div class="row mb-3">
												<label for="cmnd" class="col-md-4 col-lg-3 col-form-label">CMND</label>
												<div class="col-md-8 col-lg-9">
													<form:input cssClass="form-control" path="cmnd" />
												</div>
											</div>



											<div class="row mb-3">
												<label for="address"
													class="col-md-4 col-lg-3 col-form-label">Địa Chỉ</label>
												<div class="col-md-8 col-lg-9">
													<form:input cssClass="form-control" path="diaChi" />
												</div>
											</div>

											<div class="row mb-3">
												<label for="address"
													class="col-md-4 col-lg-3 col-form-label">Ngày Vào
													Làm</label>
												<div class="col-md-8 col-lg-9">
													<form:input cssClass="form-control" path="ngayVaoLam"
														readonly="true" />
												</div>
											</div>

											<div class="row mb-3">
												<label class="col-md-4 col-lg-3 col-form-label">Email</label>
												<div class="col-md-8 col-lg-9">
													<input class="form-control" type="email" name="email"
														value="${user.email }" />
												</div>
											</div>

											<div class="row mb-3">
												<label for="username"
													class="col-md-4 col-lg-3 col-form-label">Tên Tài
													Khoản</label>
												<div class="col-md-8 col-lg-9">
													<input class="form-control" type="text"
														value="${user.userName }" readonly="readonly" />
												</div>
											</div>
											<div class="text-center">
												<button type="submit" class="btn btn-primary"
													name="btnupdate-info">Lưu Thay Đổi</button>
											</div>
										</form:form>
										<!-- End Profile Edit Form -->

									</div>

									<div class="tab-pane fade pt-3" id="profile-change-password">
										<!-- Change Password Form -->
										<form action="user.htm" method="post">
											${message}
											<div class="row mb-3">
												<label for="currentPassword"
													class="col-md-4 col-lg-3 col-form-label">Mật Khẩu
													Cũ</label>
												<div class="col-md-8 col-lg-9">
													<input name="password" type="password" class="form-control" />
												</div>
											</div>

											<div class="row mb-3">
												<label for="newPassword"
													class="col-md-4 col-lg-3 col-form-label">Mật Khẩu
													Mới</label>
												<div class="col-md-8 col-lg-9">
													<input name="newpassword" type="password"
														class="form-control" />
												</div>
											</div>

											<div class="row mb-3">
												<label for="renewPassword"
													class="col-md-4 col-lg-3 col-form-label">Nhập Lại
													Mật Khẩu Mới</label>
												<div class="col-md-8 col-lg-9">
													<input name="renewpassword" type="password"
														class="form-control" />
												</div>
											</div>

											<div class="text-center">
												<button type="submit" class="btn btn-primary"
													name="btnChangePw">Đổi Mật Khẩu</button>
												<button type="reset" class="btn btn-danger">Hủy</button>
											</div>
										</form>
										<!-- End Change Password Form -->

									</div>
									<!-- ICON Tabs -->
									<div class="tab-pane fade pt-3" id="avata-edit">
										<label> ${message} </label>
										<form action="user-avt.htm" method="post"
											enctype="multipart/form-data" class="row mb-3">
											<label for="profileImage"
												class="col-md-4 col-lg-3 col-form-label">Ảnh Đại
												Diện</label>
											<div class="col-md-8 col-lg-9" style="width: 100px;">
												<img src="files/${sessionScope.USERMODEL.icon}"
													alt="Ảnh đại diện" class="avt-pic" />
												<div class="pt-2">
													<div class="btn btn-primary btn-sm"
														style="position: relative;">
														<input type="file" accept=".jpg, .png" class="file-upload"
															name="avt" /> <i class="fas fa-upload"></i>
													</div>
													<a href="#" class="btn btn-danger btn-sm"
														title="Remove my profile image"> <i
														class="fas fa-trash-alt"></i></a> <input type="submit"
														value="Lưu" class="btn btn-info btn-sm">
												</div>
											</div>
										</form>
									</div>
								</div>
								<!-- End ICON Tabs -->

							</div>
						</div>

					</div>
			</div>
			</section>

		</div>

	</div>
	</div>
	<jsp:include page="/common/admin/footer.jsp" />
	<script
		src="https://cdn.jsdelivr.net/npm/bootstrap@5.0.1/dist/js/bootstrap.bundle.min.js"></script>
	<script
		src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>
	<script
		src="https://maxcdn.bootstrapcdn.com/bootstrap/4.5.2/js/bootstrap.min.js"></script>

</body>
</body>

</html>