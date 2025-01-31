package hospital.action;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.*;
import user.model.User;

import java.io.*;
import org.json.JSONObject;

import controller.Action;
import hospital.model.BookmarkDao;

public class AddBookmarkAction implements Action {
	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("application/json");
		JSONObject jsonResponse = new JSONObject();

		HttpSession session = request.getSession();
		User user = (User) session.getAttribute("log");

		try {
			// 요청 데이터 읽기
			BufferedReader reader = request.getReader();
			StringBuilder sb = new StringBuilder();
			String line;
			while ((line = reader.readLine()) != null) {
				sb.append(line);
			}
			JSONObject jsonRequest = new JSONObject(sb.toString());

			if (user == null) {
				jsonResponse.put("error", "notLog");
				jsonResponse.put("message", "로그인 상태가 아닙니다.");
				PrintWriter out = response.getWriter();
				out.print(jsonResponse.toString());
				return;
			}

			// 요청 데이터 추출
			String hospitalCode = jsonRequest.getString("hospitalCode");
			int userCode = user.getUserCode();
			// DAO 객체 생성
			BookmarkDao bookmarkDao = BookmarkDao.getInstance();

			// 중복 확인
			if (bookmarkDao.isDuplicate(userCode, hospitalCode)) {
				jsonResponse.put("error", "duplicate");
				jsonResponse.put("message", "이미 북마크에 추가된 병원입니다.");
			} else {
				// 북마크 추가
				bookmarkDao.createBookMark(userCode, hospitalCode);
				jsonResponse.put("success", true);
				jsonResponse.put("message", "북마크가 성공적으로 추가되었습니다!");
			}
		} catch (Exception e) {
			e.printStackTrace();
			jsonResponse.put("error", "server_error");
			jsonResponse.put("message", "서버 오류가 발생했습니다.");
		}

		// 응답 반환
		PrintWriter out = response.getWriter();
		out.print(jsonResponse.toString());

	}
}
