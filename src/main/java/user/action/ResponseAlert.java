package user.action;

import java.io.PrintWriter;

import jakarta.servlet.http.HttpServletResponse;

public class ResponseAlert {
	public static void alert(HttpServletResponse response, String text) {
		try {
			PrintWriter out = response.getWriter();
			response.setCharacterEncoding("utf-8");
			response.setContentType("text/html; charset=utf-8");
			out.println("<script> alert('"+text+"'); </script>");
			out.println("<script> history.go(-1); </script>");
			out.close();
		} catch(Exception e) {
			e.printStackTrace();
		}
	}
	public static void alertMsg(HttpServletResponse response, String text) {
		try {
			PrintWriter out = response.getWriter();
			response.setCharacterEncoding("utf-8");
			response.setContentType("text/html; charset=utf-8");
			out.println("<script> alert('"+text+"'); </script>");
			out.println("<script> location.href='/login'; </script>");
			out.close();
		} catch(Exception e) {
			e.printStackTrace();
		}
	}
}