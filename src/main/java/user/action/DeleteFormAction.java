package user.action;

import java.io.IOException;

import controller.Action;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import user.model.User;
import user.model.UserDao;

public class DeleteFormAction implements Action {
	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session = request.getSession();
		User user = (User) session.getAttribute("log");
		String password = request.getParameter("password");

		if (user != null && (user.checkPassword(password) || user.checkCryptPassword(password))) {
			UserDao userDao = UserDao.getInstance();
			userDao.deleteUserByUsername(user.getUsername());
			session.removeAttribute("log");
			session.invalidate();
			response.sendRedirect("/main");
		} else {
			ResponseAlert.alert(response, "비밀번호가 일치하지 않습니다.");
		}
	}
}