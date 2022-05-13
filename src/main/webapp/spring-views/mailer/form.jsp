<%@ page pageEncoding="utf-8"%>
<!DOCTYPE html>
<html>
<head>
<base href="${pageContext.servletContext.contextPath}/">
<meta charset="utf-8" />
<title>Send email</title>
<link rel="stylesheet"
	href="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css"
	integrity="sha384-ggOyR0iXCbMQv3Xipma34MD+dH/1fQ784/j6cY/iJTQUOhcWr7x9JvoRxT2MZw1T"
	crossorigin="anonymous">
<link rel="stylesheet"
	href="https://maxcdn.bootstrapcdn.com/font-awesome/4.7.0/css/font-awesome.min.css">
<style type="text/css">
.errors {
	color: red;
	font-style: italic;
}
</style>
<script src='https://www.google.com/recaptcha/api.js'></script>
</head>
<body>
	<h3 style="text-align: center;">${message}</h3>
	<form class="container" action="mailer/send.htm" method="post">

		<br> <input value="nhonamstg@gmail.com" style="display: none;"
			name="from" placeholder="From">
		<div class="form-group">
			<label for="exampleFormControlInput1">Nhập địa chỉ email của
				bạn</label> <input name="to" type="email" class="form-control"
				id="exampleFormControlInput1" placeholder="name@example.com">
		</div>

		<div class="form-group">
			<div class="g-recaptcha"
				data-sitekey="6Ld1TpAfAAAAAOR_gKzy4BMOirgICntMGSp-XR5B"></div>
		</div>
		<div class="mb-1">
			<h6 class="mb-0 text-sm errors">${reCaptra}</h6>
		</div>


		<button>Send</button>
	</form>
</body>
</html>