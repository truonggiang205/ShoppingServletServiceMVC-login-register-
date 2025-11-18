package vn.truonggiang.controller;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.OutputStream; // Import thêm

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet; // Import WebServlet
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import vn.truonggiang.config.Constant; // Import lớp Constant của bạn

@SuppressWarnings("serial") // Đặt annotation ở đây
@WebServlet(urlPatterns = "/image") // ?fname=abc.png
public class DownloadImageController extends HttpServlet {
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		
		String fileName = req.getParameter("fname");
		if (fileName == null || fileName.isEmpty()) {
			resp.sendError(HttpServletResponse.SC_BAD_REQUEST, "Thiếu tham số 'fname'.");
			return;
		}
		
		File file = new File(Constant.DIR + "/" + fileName);
		
		if (file.exists() && !file.isDirectory()) {
			// Cải tiến: Tự động nhận diện kiểu file (PNG, JPG, v.v.)
			String mimeType = getServletContext().getMimeType(file.getAbsolutePath());
			if (mimeType == null) {
				mimeType = "application/octet-stream";
			}
			resp.setContentType(mimeType);
			resp.setContentLength((int) file.length());
			
			// === Code thay thế cho IOUtils.copy ===
			// Dùng Java chuẩn để đọc/ghi file
			try (FileInputStream fis = new FileInputStream(file);
				 OutputStream os = resp.getOutputStream()) {
				
				byte[] buffer = new byte[4096]; // Tạo bộ đệm 4KB
				int bytesRead;
				while ((bytesRead = fis.read(buffer)) != -1) {
					os.write(buffer, 0, bytesRead);
				}
			}
			// === Kết thúc code thay thế ===
			
		} else {
			// Gửi lỗi 404 nếu không tìm thấy file
			resp.sendError(HttpServletResponse.SC_NOT_FOUND, "Không tìm thấy hình ảnh.");
		}
	}
}