<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@include file="/common/taglib.jsp"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<base href="${pageContext.servletContext.contextPath}/">
<link rel="stylesheet"
	href="https://maxcdn.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css">
</head>
<body>
	<div style="margin-top: 5%;" class="container">
		<form:form action="admin-home/formBan.htm" modelAttribute="b"
			method="post">
			<div class="form-row">
				<div class="form-group col-md-6">
					<label>Số Ghế</label> <input value="${soGhe1}" name="soGhe"
						type="number" placeholder="2,4,10...." />
				</div>
				<form:input readonly="true" path="id" />

			</div>
			<div class="form-group">

				<!-- 	test -->


				<h5>
					LOAI BAN <select name="loaiBan">
						<c:forEach items="${loaibans}" var="loai">

							<option value="${loai.id}"
								<c:if test="${loai.id == idLoai}" >selected</c:if>>
								${loai.tenLoai}</option>
						</c:forEach>
					</select>
				</h5>
			</div>

			<button class="btn btn-primary" type="submit"
				name="${btnupdate ? 'btnupdate' : 'Insert'}">${btnupdate ? 'Update' : 'Insert'}</button>
			<a style="font-size: 16px; padding: 10px;" class="btn btn-secondary"
				href="admin-home/admin-qlban.htm"> QUAY LẠI </a>
		</form:form>
	</div>
	<script src="<c:url value='/template/web/scipts.js'/>"></script>
</body>
</html>