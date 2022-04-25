<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
        <%@ taglib uri="http://www.springframework.org/tags/form" prefix = "form"%>
<%@ taglib uri="http://java.sun.com/jstl/core_rt" prefix = "c" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Thêm Thực Đơn</title>
  <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css">

</head>
<body>
<div style="margin-top: 5%;" class="container">
        <form:form action="admin-home/formThucDon.htm" modelAttribute="td" method="post">
            <div class="form-row">
              <div class="form-group col-md-6">
                <label >ID</label>
                <form:input path="id" type="text" placeholder=""/>
              </div>
             
            </div>
            <div class="form-group">
              <label >Loại Thức Uống</label>
              <form:input path="loaiThucUong" type="text" class="form-control" placeholder="1234"/>
            </div>
            <div class="form-row">
              <div class="form-group col-md-6">
                <label >Tên Thức Uống</label>
                <form:input path="ten" type="text"  />
              </div>
              <div class="form-group col-md-4">
                
                <label >Giá Thành</label>
                <form:input path="gia" type="number" />
              </div>
              </div>
      

            	<button class="btn btn-primary" type = "submit" name= "${btnupdate ? 'btnupdate' : 'Insert'}" >${btnupdate ? 'Update' : 'Insert'}</button>
            <a style="font-size: 16px; padding: 10px;" class="btn btn-secondary" href="admin-home/admin-qlthucdon.htm"> QUAY LẠI </a>
          </form:form>
      </div>

</body>
</html>