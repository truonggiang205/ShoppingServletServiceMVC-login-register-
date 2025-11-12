<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Đăng nhập hệ thống</title>
<style>
body {
    font-family: Arial, sans-serif;
    background: linear-gradient(135deg, #74ABE2, #5563DE);
    height: 100vh;
    display: flex;
    justify-content: center;
    align-items: center;
    margin: 0;
}
.login-container {
    background-color: white;
    padding: 40px 50px;
    border-radius: 15px;
    box-shadow: 0 8px 20px rgba(0, 0, 0, 0.2);
    width: 350px;
    text-align: center;
}
h2 { margin-bottom: 25px; color: #333; }
input[type="text"], input[type="password"] {
    width: 100%; padding: 12px; margin: 10px 0;
    border: 1px solid #ccc; border-radius: 8px; box-sizing: border-box;
}
button {
    width: 100%; background-color: #5563DE; color: white;
    padding: 12px; border: none; border-radius: 8px; cursor: pointer;
    font-size: 16px; transition: background 0.3s ease;
}
button:hover { background-color: #3747c0; }
.error { color: red; margin-top: 10px; font-weight: bold; }
.footer { margin-top: 20px; font-size: 13px; color: #666; }
a { color: #5563DE; text-decoration: none; }
a:hover { text-decoration: underline; }
</style>
</head>
<body>
<div class="login-container">
    <h2>Đăng nhập</h2>

    <!-- FORM SỬA ACTION -->
    <form action="<%= request.getContextPath() %>/login" method="post">
        <input type="text" name="username" placeholder="Tên đăng nhập"
               value="<%= request.getAttribute("username") != null ? request.getAttribute("username") : "" %>"
               required>
        <input type="password" name="password" placeholder="Mật khẩu" required>
        <button type="submit">Đăng nhập</button>
    </form>

    <!-- HIỂN THỊ LỖI DÙNG "alert" -->
    <%
    String alert = (String) request.getAttribute("alert");
    if (alert != null) {
    %>
        <div class="error"><%= alert %></div>
    <%
    }
    %>

    <div class="footer">
        Chưa có tài khoản? <a href="register.jsp">Đăng ký ngay</a>
    </div>
</div>
</body>
</html>