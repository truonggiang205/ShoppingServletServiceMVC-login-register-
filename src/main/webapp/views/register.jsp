<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<link
	href="https://fonts.googleapis.com/css2?family=Inter:wght@300;400;600;700&display=swap"
	rel="stylesheet">

<style>
body {
	height: 100vh;
	margin: 0;
	font-family: 'Inter', sans-serif;
	display: flex;
	align-items: center;
	justify-content: center;
	/* 🌟 Background màu xanh */
	background: linear-gradient(135deg, #4a90e2, #0072ff);
}

.register-container {
	width: 420px;
	background: #ffffff;
	padding: 35px 40px;
	border-radius: 18px;
	box-shadow: 0 10px 40px rgba(0, 0, 0, 0.18);
	animation: fadeIn 0.4s ease;
}

h2 {
	text-align: center;
	font-weight: 700;
	margin-bottom: 30px;
	color: #222;
	font-size: 26px;
}

.form-label {
	font-weight: 600;
	margin-bottom: 6px;
	display: block;
	font-size: 15px;
}

.form-control {
	width: 100%;
	padding: 12px 14px;
	border: 1px solid #d0d0d0;
	border-radius: 10px;
	font-size: 15px;
	transition: .2s;
}

.form-control:focus {
	border-color: #4c8bff;
	box-shadow: 0 0 8px rgba(76, 139, 255, 0.4);
	outline: none;
}

.btn-primary {
	width: 100%;
	padding: 12px;
	margin-top: 15px;
	background: #4c8bff;
	border: none;
	color: white;
	font-size: 16px;
	font-weight: 600;
	border-radius: 10px;
	cursor: pointer;
	transition: 0.25s;
}

.btn-primary:hover {
	background: #2366f5;
	transform: translateY(-2px);
}

.login-text {
	text-align: center;
	margin-top: 18px;
	font-size: 15px;
	color: #222;
}

.login-text a {
	color: #2366f5;
	font-weight: 600;
	text-decoration: none;
}

.login-text a:hover {
	text-decoration: underline;
}

@
keyframes fadeIn {from { opacity:0;
	transform: translateY(10px);
}

to {
	opacity: 1;
	transform: translateY(0);
}
}
</style>

<div class="register-container">

	<form action="${pageContext.request.contextPath}/register"
		method="post">
		<h2>Đăng ký tài khoản</h2>

		<c:if test="${not empty alert}">
			<div class="alert alert-danger"
				style="padding: 10px; text-align: center; margin-bottom: 15px;">
				${alert}</div>
		</c:if>

		<label class="form-label">Tên đăng nhập</label> <input type="text"
			name="username" placeholder="Nhập tên đăng nhập" class="form-control"
			required> <label class="form-label" style="margin-top: 15px;">Mật
			khẩu</label> <input type="password" name="password"
			placeholder="Nhập mật khẩu" class="form-control" required> <label
			class="form-label" style="margin-top: 15px;">Xác nhận mật
			khẩu</label> <input type="password" name="re_password"
			placeholder="Nhập lại mật khẩu" class="form-control" required>

		<button type="submit" class="btn btn-primary">Đăng ký</button>

		<div class="login-text">
			Đã có tài khoản? <a href="${pageContext.request.contextPath}/login">Đăng
				nhập ngay</a>
		</div>

	</form>
</div>
