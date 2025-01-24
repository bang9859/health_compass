package hospital.model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import util.DBManager;

public class BookmarkDao {
	private Connection conn;
	private PreparedStatement pstmt;
	private ResultSet rs;

	private BookmarkDao() {
	}

	private static BookmarkDao instance = new BookmarkDao();

	public static BookmarkDao getInstance() {
		return instance;
	}

	// Create
	public void createBookMark(int userCode, String hospitalCode) {
		conn = DBManager.getConnection();

		String sql = "INSERT INTO bookmark (user_code, hospital_code, reg_date) VALUES (?, ?, CURRENT_TIMESTAMP)";

		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, userCode);
			pstmt.setString(2, hospitalCode);

			pstmt.execute();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				conn.close();
				pstmt.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
	}

	// 중복확인
	public boolean isDuplicate(int userCode, String hospitalCode) {
	    String sql = "SELECT COUNT(*) FROM bookmark WHERE user_code = ? AND hospital_code = ?";
	    conn = DBManager.getConnection();

	    try {
	        pstmt = conn.prepareStatement(sql);
	        pstmt.setInt(1, userCode);
	        pstmt.setString(2, hospitalCode);

	        rs = pstmt.executeQuery();
	        if (rs.next()) {
	            return rs.getInt(1) > 0; 
	        }
	    } catch (SQLException e) {
	        e.printStackTrace();
	    } finally {
	        try {
	            if (rs != null) rs.close();
	            if (pstmt != null) pstmt.close(); 
	            if (conn != null) conn.close();
	        } catch (SQLException e) {
	            e.printStackTrace();
	        }
	    }
	    return false;
	}


	// delete
	public void deleteBookmark(String hospitalCode) {
		conn = DBManager.getConnection();

		String sql = "DELETE FROM bookmark WHERE hospital_code=?";

		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, hospitalCode);

			pstmt.execute();

		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				conn.close();
				pstmt.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
	}

}
