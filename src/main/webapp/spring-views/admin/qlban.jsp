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

	<!-- CONTEND -->
	<div class="container-fluid main">

			<div class="container">
			<div class="header-content d-flex justify-content-center">QUẢN LÍ BÀN</div>
				<h4>${message}</h4>
			<div>
				<a href="/CNPM/admin-home/formBan.htm"> <!-- 	<button type="button" class="btn btn-warning">Thêm</button> -->
					<button style="width: 184px; height: 33px; margin-bottom: 5px;"
						type="button" class="btn btn-primary">Thêm Bàn</button>
				</a>

			</div>
			<jsp:useBean id="pagedListHolder" scope="request"
				type="org.springframework.beans.support.PagedListHolder" />
			<c:url value="admin-qlban.htm" var="pagedLink">
				<c:param name="p" value="~" />
			</c:url>
			<form class="input-group" style="margin: 20px 0" method="post">
				<div>
					<input id="search-input" type="search" name="searchInput"
						class="form-control" placeholder="Tìm kiếm" />
				</div>
				<button id="search-button" type="submit" class="btn btn-primary"
					name="btnsearch">
					<i class="fas fa-search"></i>
				</button>
			</form>
				<!-- ENDTHEEM -->
				<table class="table table-striped shadow-box bg-white">
					<thead>
						<tr>
							<th scope="row">ID</th>
							<th scope="row">Số Ghế</th>
							<th scope="row">Loại Bàn</th>
							<th scope="row">Giá Thành</th>
							<th></th>
						</tr>
					</thead>
					<tbody>
			<c:forEach var="b" items="${pagedListHolder.pageList}">
						<tr>
							<th scope="row">${b.id}</th>
							<td>${b.soGhe}</td>
							<td>${b.loaiBan.tenLoai}</td>
							<td>${b.loaiBan.giaDat}</td>
							<td>
								<div style="width: 95px;" class="row">
									<div class="col-6">
										<button style="font-size: 10px; width: 44px;" type="button"
											class="btn" data-toggle="modal" data-target="#exampleModal1"
											data-whatever="@mdo">SỬA</button>


										

										<!-- Button trigger modal -->
									</div>
									<div class="col-6">
										<!-- <a href="">XÓA</a> -->
										<!-- Button trigger modal -->
										<button style="font-size: 10px;" type="button"
											class="btn btn-primary" data-toggle="modal"
											data-target="#exampleModal2">XÓA</button>

										<!-- Modal -->
										<div class="modal fade" id="exampleModal2" tabindex="-1"
											role="dialog" aria-labelledby="exampleModalLabel"
											aria-hidden="true">
											<div class="modal-dialog" role="document">
												<div class="modal-content">
													<div class="modal-header">
														<h5 class="modal-title" id="exampleModalLabel">!!!</h5>
														<button type="button" class="close" data-dismiss="modal"
															aria-label="Close">
															<span aria-hidden="true">&times;</span>
														</button>
													</div>
													<div class="modal-body">Bạn có chắc muốn xóa!!!</div>
													<div class="modal-footer">
														<button type="button" class="btn btn-secondary"
															data-dismiss="modal">Thoát</button>
														<button type="button" class="btn btn-primary">Xóa</button>
													</div>
												</div>
											</div>
										</div>
									</div>

								</div>

							</td>

						</tr>
						</c:forEach>
					</tbody>
				</table>
				<tg:paging pagedLink="${pagedLink}"
				pagedListHolder="${pagedListHolder}"></tg:paging>
			</div>
		</div>



	<jsp:include page="/common/admin/footer.jsp" />



</body>

</html>