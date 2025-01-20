package user.action;

import java.io.IOException;

import controller.Action;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import user.model.User;
import user.model.UserDao;

public class LoginFormAction implements Action {
	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String username = request.getParameter("username");
		String password = request.getParameter("password");
		
		UserDao userDao = UserDao.getInstance();
		
		User user = userDao.findUserByUsername(username);
		
		if(user != null && user.checkPassword(password)) {
			HttpSession session = request.getSession();
			session.setAttribute("log", user);
			response.sendRedirect("/index.jsp");
			System.err.println("로그인 성공");
		} else {
			response.sendRedirect("/login");
			System.err.println("로그인 실패");
		}
	}
}
