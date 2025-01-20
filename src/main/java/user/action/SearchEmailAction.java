package user.action;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Timestamp;

import org.json.JSONObject;

import controller.Action;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import user.model.User;
import user.model.UserDao;

public class SearchEmailAction implements Action {
	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		StringBuilder builder = new StringBuilder();

		BufferedReader reader = request.getReader();
		while (reader.ready()) {
			builder.append(reader.readLine());
		}

		JSONObject reqData = new JSONObject(builder.toString());
		JSONObject resData = new JSONObject();

		if (!reqData.has("email")) {
			response.setStatus(HttpServletResponse.SC_BAD_REQUEST);
			resData.put("status", HttpServletResponse.SC_BAD_REQUEST);
			resData.put("error", "BAD REQUEST");
			resData.put("message", "잘못된 요청입니다. 필수 키 값이 누락되었습니다.");
			resData.put("timestamp", new Timestamp(System.currentTimeMillis()));
		} else {
			String username = reqData.has("username") ? reqData.getString("username") : null;
			String email = reqData.getString("email");

			UserDao userDao = UserDao.getInstance();
			User user = userDao.findUserByEmail(email);

			boolean isValid = user != null;

			if(user != null && user.getUsername().equals(username)) {
				isValid = !isValid;
			}
			
			resData.put("isValid", isValid);
		}

		response.setContentType("application/json");
		response.setCharacterEncoding("utf-8");

		PrintWriter out = response.getWriter();
		out.append(resData.toString());
		out.flush();
	}
}
