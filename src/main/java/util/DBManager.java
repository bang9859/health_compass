package util;

import java.sql.Connection;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.sql.DataSource;

public class DBManager {
	public static Connection getConnection() {
		Connection conn = null;
		
		Context init;
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			
			init = new InitialContext();
			Context ctx = (Context) init.lookup("java:comp/env");
			
			DataSource dataSource = (DataSource) ctx.lookup("jdbc/health_compass");
			conn = dataSource.getConnection();
			
			System.out.println("DATABASE 연결성공");
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			System.out.println("DATABASE 연결실패");
		}
		
		return conn;
	}
}
