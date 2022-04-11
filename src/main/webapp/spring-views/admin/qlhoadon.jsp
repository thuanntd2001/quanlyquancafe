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

	<!-- CONTEND -->
	<div class="row main">


		<jsp:include page="/common/admin/menubar.jsp" />
		 <div class="col-md-10 col-lg-10">
      <div class="container">
        <h2 style="margin-top: 3%; margin-bottom: 3%;">QUẢN LÍ HÓA ĐƠN</h2>
        <!-- <div class="input-group">
                <input type="search" class="form-control rounded" placeholder="Search" aria-label="Search" aria-describedby="search-addon" />
                <button type="button" class="btn btn-outline-primary">search</button>
              </div> -->
        <table class="table table-striped">
          <thead>
            <tr>
              <th scope="col">ID</th>
         
              <th scope="col">Nhân Viên Thực Hiện</th>
              <th scope="col">Bàn</th>
              <th scope="col">Ngày Thực Hiện</th>
            </tr>
          </thead>
          <tbody>
          <c:forEach var="hd" items="${hoadon}">
            <tr>
              <th scope="row">${hd.id}</th>
             <td>${hd.hdnv.hoTen}</td>
              <td>${hd.ban.id}</td>
               <td>${hd.ngayThucHien}</td>
            </tr>
            </c:forEach>
          
          </tbody>
        </table>
        <nav style="text-align: center;" aria-label="Page navigation example">
          <ul class="pagination">
            <li class="page-item"><a class="page-link" href="#">Previous</a></li>
            <li class="page-item"><a class="page-link" href="#">1</a></li>
            <li class="page-item"><a class="page-link" href="#">2</a></li>
            <li class="page-item"><a class="page-link" href="#">3</a></li>
            <li class="page-item"><a class="page-link" href="#">Next</a></li>
          </ul>
        </nav>
      </div>
    </div>
		
				<nav style="text-align: center;"
					aria-label="Page navigation example">
					<ul class="pagination">
						<li class="page-item"><a class="page-link" href="#">Previous</a></li>
						<li class="page-item"><a class="page-link" href="#">1</a></li>
						<li class="page-item"><a class="page-link" href="#">2</a></li>
						<li class="page-item"><a class="page-link" href="#">3</a></li>
						<li class="page-item"><a class="page-link" href="#">Next</a></li>
					</ul>
				</nav>
			</div>
		</div>


	</div>


	<jsp:include page="/common/admin/footer.jsp" />



</body>

</html>