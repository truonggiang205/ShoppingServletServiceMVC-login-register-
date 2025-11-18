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

@WebServlet(urlPatterns = { "/admin/category/edit" })
@MultipartConfig(
    fileSizeThreshold = 1024 * 1024 * 2, // 2MB
    maxFileSize = 1024 * 1024 * 10,      // 10MB
    maxRequestSize = 1024 * 1024 * 50    // 50MB
)
public class CategoryEditController extends HttpServlet {
    
    private static final long serialVersionUID = 1L;
    CategoryService cateService = new CategoryServiceImpl();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String id = req.getParameter("id");
        // Kiểm tra null để tránh lỗi
        if (id != null) {
            Category category = cateService.get(Integer.parseInt(id));
            req.setAttribute("category", category);
        }
        RequestDispatcher dispatcher = req.getRequestDispatcher("/views/admin/editcategory.jsp");
        dispatcher.forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setCharacterEncoding("UTF-8");
        resp.setCharacterEncoding("UTF-8");
        
        try {
            // 1. Lấy dữ liệu text (Cực kỳ đơn giản với @MultipartConfig)
            // Không cần vòng lặp for kiểm tra FieldName nữa!
            int id = Integer.parseInt(req.getParameter("id"));
            String name = req.getParameter("name");

            // 2. Tạo đối tượng Category
            Category category = new Category();
            category.setCateid(id);
            category.setCatename(name); // Giữ logic cũ của bạn
            
            // 3. Lấy file cũ (nếu muốn giữ lại ảnh cũ khi không upload ảnh mới)
            Category oldCategory = cateService.get(id);
            String iconPath = oldCategory.getIcon();

            // 4. Xử lý file upload
            Part part = req.getPart("icon"); // "icon" là name trong form jsp
            
            if (part != null && part.getSize() > 0) {
                String filename = Paths.get(part.getSubmittedFileName()).getFileName().toString();
                
                // Đổi tên file để tránh trùng (thêm timestamp)
                int index = filename.lastIndexOf(".");
                String ext = filename.substring(index + 1);
                String fname = System.currentTimeMillis() + "." + ext;
                
                // Tạo thư mục lưu file
                File uploadDir = new File(Constant.DIR + "/category");
                if (!uploadDir.exists()) {
                    uploadDir.mkdirs();
                }
                
                // Lưu file
                part.write(Constant.DIR + "/category/" + fname);
                
                // Cập nhật đường dẫn mới
                iconPath = "category/" + fname;
            }
            
            category.setIcon(iconPath);
            
            // 5. Gọi service update
            cateService.edit(category);
            
            resp.sendRedirect(req.getContextPath() + "/admin/category/list");

        } catch (Exception e) {
            e.printStackTrace();
            // Có thể forward về trang lỗi nếu cần
        }
    }
}