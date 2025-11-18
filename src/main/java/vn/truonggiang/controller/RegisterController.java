package vn.truonggiang.controller;

import java.io.IOException;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import vn.truonggiang.model.User;
import vn.truonggiang.service.UserService;
import vn.truonggiang.service.impl.UserServiceImpl;

@WebServlet("/register")
public class RegisterController extends HttpServlet {

    private static final long serialVersionUID = 1L;

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        RequestDispatcher rd = req.getRequestDispatcher("/views/register.jsp");
        rd.forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String username = req.getParameter("username");
        String password = req.getParameter("password");
        String re_password = req.getParameter("re_password"); 
        String alertMsg = "";

        // ----- BƯỚC 1: Kiểm tra dữ liệu đầu vào -----
        if (username.isEmpty() || password.isEmpty() || re_password.isEmpty()) {
            alertMsg = "Vui lòng nhập đầy đủ thông tin!";
            req.setAttribute("alert", alertMsg);
            req.getRequestDispatcher("/views/register.jsp").forward(req, resp);
            return;
        }

        if (!password.equals(re_password)) {
            alertMsg = "Mật khẩu nhập lại không trùng khớp!";
            req.setAttribute("alert", alertMsg);
            req.getRequestDispatcher("/views/register.jsp").forward(req, resp);
            return;
        }

        UserService service = new UserServiceImpl();

        User existingUser = service.get(username); 

        if (existingUser != null) {
            // Nếu user đã tồn tại, báo lỗi
            alertMsg = "Tên đăng nhập đã tồn tại!";
            req.setAttribute("alert", alertMsg);
            req.getRequestDispatcher("/views/register.jsp").forward(req, resp);
        } else {
            // Nếu user chưa tồn tại, tiến hành đăng ký
            User newUser = new User();
            newUser.setUsername(username);
            newUser.setPassword(password);
            
            // Gọi hàm register từ service
            service.register(newUser);

            // ----- BƯỚC 3: Chuyển hướng về trang đăng nhập -----
            resp.sendRedirect(req.getContextPath() + "/login"); 
        }
    }
}