package user.action;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

import org.json.JSONObject;

import controller.Action;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import user.model.Bookmark;
import user.model.User;
import user.model.UserDao;

public class DeleteBookmark implements Action {
	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		StringBuilder builder = new StringBuilder();

		BufferedReader reader = request.getReader();
		while (reader.ready()) {
			builder.append(reader.readLine());
		}

		JSONObject reqData = new JSONObject(builder.toString());
		JSONObject resData = new JSONObject();

		if (!reqData.has("hospitalId")) {
			response.setStatus(HttpServletResponse.SC_BAD_REQUEST);
			resData.put("status", HttpServletResponse.SC_BAD_REQUEST);
			resData.put("error", "BAD REQUEST");
			resData.put("message", "잘못된 요청입니다. 필수 키 값이 누락되었습니다.");
			resData.put("timestamp", new Timestamp(System.currentTimeMillis()));
		} else {
			String hospitalId = reqData.getString("hospitalId");

			UserDao userDao = UserDao.getInstance();
			userDao.deleteBookmarkByHospitalId(hospitalId);
			
			HttpSession session = request.getSession();
			
			User user = (User) session.getAttribute("log");
					
			List<String> list = userDao.allSearchBookmark(user.getUserCode());
			List<Bookmark> bookmarkList = new ArrayList<Bookmark>();

			SearchHospital sh = new SearchHospital();
			for (int i = 0; i < list.size(); i++) {
				bookmarkList.add(sh.searchHospital(list.get(i)));
			}
			
			session.setAttribute("bookmark", bookmarkList);
			System.err.println("북마크 삭제 성공");
		}

		response.setContentType("application/json");
		response.setCharacterEncoding("utf-8");

		PrintWriter out = response.getWriter();
		out.append(resData.toString());
		out.flush();
	}
}