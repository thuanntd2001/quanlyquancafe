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
                    ĐẶT BÀN
                </div>
                <div class="original-info d-flex justify-content-center">
                    <div class=" mg-0-40">
                        Họ tên nhân viên: Huỳnh Ngọc Dương
                    </div>
                    <div class=" mg-0-40">
                        Ngày:
                        <span id="date-now"></span>
                    </div>
                    <div class=" mg-0-40">
                        Thời gian:
                        <span id="current-time"></span>
                    </div>
                </div>
                <div class="d-flex justify-content-around">
                    <div class="">
                        <a style="font-size: 16px; padding: 10px;" class="btn btn-secondary" href="trang-chu">
                            QUAY LẠI
                        </a>
                    </div>
                    <div class="">
                        <button style="padding: 10px; margin-bottom: 5px;" type="button" class="btn btn-primary"
                            data-toggle="modal" data-target="#exampleModal" data-whatever="@mdo">
                            <span>ĐẶT BÀN</span>
                        </button>

                        <div class="modal fade" id="exampleModal" tabindex="-1" role="dialog"
                            aria-labelledby="exampleModalLabel" aria-hidden="true">
                            <div class="modal-dialog" role="document">
                                <div class="modal-content">
                                    <div class="modal-header">
                                        <h5 class="modal-title" id="exampleModalLabel">ĐẶT BÀN</h5>
                                        <button type="button" class="close" data-dismiss="modal" aria-label="Close">
                                            <span aria-hidden="true">&times;</span>
                                        </button>
                                    </div>
                                    <div class="modal-body">
                                        <form action="" style="margin-top: 40px; margin-left: 20px;">

                                            <div class="mg-10">
                                                <label for="name-customer">Tên khách hàng:</label>
                                                <input type="text" id="name-customer">
                                            </div>
                                            <div class="mg-10">
                                                <label for="number" style="margin-right: 18px;">Số điện
                                                    thoại:</label>
                                                <input type="text" id="number">
                                            </div>

                                            <div class="mg-10">
                                                <label for="set-date" style="margin-right: 33px;">TG dự
                                                    kiến:</label>
                                                <input type="datetime-local" id="set-date">
                                            </div>
                                            <div class="mg-10">
                                                <label for="deposit" style="margin-right: 51px;">Tiền
                                                    cọc:</label>
                                                <input type="number" id="deposit">
                                            </div>
                                        </form>
                                    </div>
                                    <div class="modal-footer">
                                        <button type="button" class="btn btn-secondary"
                                            data-dismiss="modal">Thoát</button>
                                        <button type="button" class="btn btn-primary">ĐẶT BÀN</button>
                                    </div>
                                </div>
                            </div>
                        </div>
                    </div>
                </div>
                <table class="table table-striped datatable bg-white shadow-box">

                    <thead>
                        <tr>
                            <th>Tên khách hàng</th>
                            <th>SĐT</th>
                            <th>Tiền cọc</th>
                            <th>Ngày đặt</th>
                            <th>Thời gian dự kiến</th>
                        </tr>
                    </thead>
                    <tbody>
                        <tr>
                            <td>Huỳnh Ngọc Dương</td>
                            <td>1234567890</td>
                            <td>1</td>
                            <td>02:34 AM</td>
                            <td>25/02/2022</td>
                        </tr>
                        <tr>
                            <td>Huỳnh Ngọc Dương</td>
                            <td>1234567890</td>
                            <td>1</td>
                            <td>02:34 AM</td>
                            <td>25/02/2022</td>
                        </tr>
                        <tr>
                            <td>Huỳnh Ngọc Dương</td>
                            <td>1234567890</td>
                            <td>1</td>
                            <td>02:34 AM</td>
                            <td>25/02/2022</td>
                        </tr>

                    </tbody>

                </table>
            </div>

        </div>
    </div>
	<jsp:include page="/common/web/footer.jsp" />
	<script
		src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>
	<script
		src="https://maxcdn.bootstrapcdn.com/bootstrap/4.5.2/js/bootstrap.min.js"></script>
	<script src="<c:url value='/template/web/scipts.js'/>"></script>
</body>

</html>