package vn.truonggiang.controller;

import java.io.File;
import java.io.IOException;
import java.nio.file.Paths; 

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.MultipartConfig; 
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.Part; 
import vn.truonggiang.config.Constant;
import vn.truonggiang.model.Category;
import vn.truonggiang.service.CategoryService;
import vn.truonggiang.service.impl.CategoryServiceImpl;


@WebServlet(urlPatterns = { "/admin/category/add" })
@MultipartConfig(
    fileSizeThreshold = 1024 * 1024 * 2, // 2MB 
    maxFileSize = 1024 * 1024 * 10,      // 10MB 
    maxRequestSize = 1024 * 1024 * 50    // 50MB 
)
public class CategoryAddController extends HttpServlet {

    private static final long serialVersionUID = 1L;
    private CategoryService cateService = new CategoryServiceImpl();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
        // Code này của bạn đã đúng
        RequestDispatcher dispatcher =
                req.getRequestDispatcher("/views/admin/add-category.jsp");
        dispatcher.forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
        
        req.setCharacterEncoding("UTF-8");
        resp.setContentType("text/html");
        resp.setCharacterEncoding("UTF-8");

        try {
            // 1. Lấy dữ liệu text (dễ hơn trước rất nhiều)
            String name = req.getParameter("name"); 

            // 2. Lấy file
            Part filePart = req.getPart("icon"); // "icon" là name của input[type=file]

            Category category = new Category();
            category.setCatename(name); // Hoặc setCatename(name) tùy model của bạn

            // 3. Xử lý file
            if (filePart != null && filePart.getSize() > 0) {
                // Lấy tên file gốc
                String originalFileName = Paths.get(filePart.getSubmittedFileName()).getFileName().toString();
                
                // Tạo tên file duy nhất (giống logic cũ của bạn)
                int index = originalFileName.lastIndexOf(".");
                String ext = originalFileName.substring(index + 1);
                String fileName = System.currentTimeMillis() + "." + ext;
                
                // Đảm bảo thư mục tồn tại
                File uploadDir = new File(Constant.DIR + "/category");
                if (!uploadDir.exists()) {
                    uploadDir.mkdirs(); // Tự động tạo thư mục nếu chưa có
                }
                
                // Ghi file
                String fullPath = Constant.DIR + "/category/" + fileName;
                filePart.write(fullPath);
                
                category.setIcon("category/" + fileName);
            }

            // 4. Lưu vào CSDL và chuyển hướng
            cateService.insert(category);
            resp.sendRedirect(req.getContextPath() + "/admin/category/list");

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}