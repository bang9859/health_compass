package user.model;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.mindrot.jbcrypt.BCrypt;

import util.DBManager;

public class UserDao {
	private Connection conn;
	private PreparedStatement pstmt;
	private ResultSet rs;

	private UserDao() {
	}

	private static UserDao instance = new UserDao();

	public static UserDao getInstance() {
		return instance;
	}

	// Create
	public void createUser(UserRequestDto userDto) {
		conn = DBManager.getConnection();

		String sql = "INSERT INTO users(username, password, email, name, birth, gender, tel) VALUES(?,?,?,?,?,?,?)";

		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, userDto.getUsername());

			String rawPassword = userDto.getPassword();
			String hashedPassword = BCrypt.hashpw(rawPassword, BCrypt.gensalt());

			pstmt.setString(2, hashedPassword);
			pstmt.setString(3, userDto.getEmail());
			pstmt.setString(4, userDto.getName());
			java.util.Date utilBirth = userDto.getBirth();
			Date birth = new Date(utilBirth.getTime());
			pstmt.setDate(5, birth);
			pstmt.setString(6, userDto.getGender());
			pstmt.setString(7, userDto.getTel());

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

	// Read
	public User findUserByUsername(String username) {
		User user = null;

		conn = DBManager.getConnection();

		String sql = "SELECT * FROM users WHERE username=?";

		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, username);
			rs = pstmt.executeQuery();

			if (rs.next()) {
				int userCode = rs.getInt(1);
				String password = rs.getString(3);
				String email = rs.getString(4);
				String name = rs.getString(5);
				Date birth = rs.getDate(6);
				String gender = rs.getString(7);
				String tel = rs.getString(8);

				user = new User(userCode, username, password, email, name, birth, gender, tel);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				conn.close();
				pstmt.close();
				rs.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return user;
	}

	public User findUserByEmail(String email) {
		User user = null;

		conn = DBManager.getConnection();

		String sql = "SELECT * FROM users WHERE email=?";

		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, email);
			rs = pstmt.executeQuery();

			if (rs.next()) {
				String username = rs.getString(2);
				String password = rs.getString(3);
				String name = rs.getString(5);
				Date birth = rs.getDate(6);
				String gender = rs.getString(7);
				String tel = rs.getString(8);

				user = new User(username, password, email, name, birth, gender, tel);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				conn.close();
				pstmt.close();
				rs.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return user;
	}

	public User findUserByTel(String tel) {
		User user = null;

		conn = DBManager.getConnection();

		String sql = "SELECT * FROM users WHERE tel=?";

		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, tel);
			rs = pstmt.executeQuery();

			if (rs.next()) {
				String username = rs.getString(2);
				String password = rs.getString(3);
				String email = rs.getString(4);
				String name = rs.getString(5);
				Date birth = rs.getDate(6);
				String gender = rs.getString(7);

				user = new User(username, password, email, name, birth, gender, tel);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				conn.close();
				pstmt.close();
				rs.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return user;
	}
	
	public int findUserCodeByUsername(String username) {
		int code = 0;
		
		conn = DBManager.getConnection();

		String sql = "SELECT user_code FROM users WHERE username=?";

		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, username);
			rs = pstmt.executeQuery();
			
			if (rs.next()) {
				code = rs.getInt(1);
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				conn.close();
				pstmt.close();
				rs.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return code;
	}
	
	public List<String> allSearchBookmark(int userCode) {
		List<String> list = new ArrayList<String>();
		conn = DBManager.getConnection();

		String sql = "SELECT hospital_code FROM bookmark WHERE user_code=?";
		
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, userCode);
			rs = pstmt.executeQuery();
			
			while (rs.next()) {
				String hospitalCode = rs.getString(1);
				list.add(hospitalCode);
			}
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
		
		return list;
	}
	
	// Update
	public void updateUser(UserRequestDto userDto) {
		conn = DBManager.getConnection();

		String sql = "UPDATE users SET password=?, email=?, tel=? WHERE username=?";

		try {
			pstmt = conn.prepareStatement(sql);

			String hashedPassword = BCrypt.hashpw(userDto.getPassword(), BCrypt.gensalt());

			pstmt.setString(1, hashedPassword);
			pstmt.setString(2, userDto.getEmail());
			pstmt.setString(3, userDto.getTel());
			pstmt.setString(4, userDto.getUsername());

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

	// Delete
	public void deleteUserByUsername(String username) {
		conn = DBManager.getConnection();

		String sql = "DELETE FROM users WHERE username=?";

		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, username);

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
