package user.action;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import controller.Action;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import user.model.Bookmark;
import user.model.User;
import user.model.UserDao;

public class LoginFormAction implements Action {
	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String username = request.getParameter("username");
		String password = request.getParameter("password");
		UserDao userDao = UserDao.getInstance();
		User user = userDao.findUserByUsername(username);
		if (user != null && (user.checkPassword(password) || user.checkCryptPassword(password))) {
			List<String> list = userDao.allSearchBookmark(user.getUserCode());
			List<Bookmark> bookmarkList = new ArrayList<Bookmark>();
			
			SearchHospital sh = new SearchHospital();
			for(int i=0; i<list.size(); i++) {
				bookmarkList.add(sh.searchHospital(list.get(i)));
			}
			HttpSession session = request.getSession();
			session.setAttribute("log", user);
			session.setAttribute("bookmark", bookmarkList);
			response.sendRedirect("/");
			System.err.println("로그인 성공");
		} else {
			ResponseAlert.alert(response, "아이디 혹은 비밀번호가 일치하지 않습니다.");
			System.err.println("로그인 실패");
		}
	}
}
