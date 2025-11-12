package vn.truonggiang.config;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.ResultSet;
import java.sql.PreparedStatement;

public class MySqlConnect {
	private static final String DB_URL = "jdbc:mysql://localhost:3306/testdb";
	private static final String USER_NAME = "root";
	private static final String PASSWORD = "123456";

	public static Connection getConnection() throws SQLException {
		Connection con = null;

		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
			throw new SQLException("Không tìm thấy MySQL JDBC Driver!");
		}

		con = DriverManager.getConnection(DB_URL, USER_NAME, PASSWORD);
		return con;
	}

	public static void main(String[] args) {
		try (Connection conn = getConnection()) {
			if (conn != null) {
				System.out.println("Đã mở kết nối thành công!");

				// 🔹 Thêm dữ liệu vào bảng User
				String insertSQL = "INSERT INTO User (username, email, password) VALUES (?, ?, ?)";
				try (PreparedStatement stmt = conn.prepareStatement(insertSQL)) {
					stmt.setString(1, "truonggiang");
					stmt.setString(2, "giang@example.com");
					stmt.setString(3, "123456");
					stmt.executeUpdate();
					System.out.println("Thêm dữ liệu thành công!");
				}

				// 🔹 Truy vấn dữ liệu từ bảng User
				String selectSQL = "SELECT * FROM User";
				try (PreparedStatement stmt = conn.prepareStatement(selectSQL); ResultSet rs = stmt.executeQuery()) {

					System.out.println("\nDanh sách User:");
					while (rs.next()) {
						System.out.println("ID: " + rs.getInt("id") + " | Username: " + rs.getString("username")
								+ " | Email: " + rs.getString("email") + " | Password: " + rs.getString("password"));
					}
				}

			} else {
				System.out.println("⚠️ Không thể kết nối tới cơ sở dữ liệu.");
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
