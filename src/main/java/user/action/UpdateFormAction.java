package user.action;

import java.io.IOException;

import controller.Action;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import user.model.User;
import user.model.UserDao;
import user.model.UserRequestDto;

public class UpdateFormAction implements Action {
	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String username = request.getParameter("username");
		String password = request.getParameter("password");
		String email = request.getParameter("email");
		String tel = request.getParameter("tel");
		UserDao userDao = UserDao.getInstance();
		User user = userDao.findUserByUsername(username);
		if (user != null) {
			UserRequestDto info = new UserRequestDto(username, password, email, tel);
			userDao.updateUser(info);
			ResponseAlert.alertMsg(response, "회원정보가 수정되었습니다. 재로그인 하주세요.");
			HttpSession session = request.getSession();
			session.removeAttribute("log");
			session.invalidate();
			System.err.println("수정이 완료되었습니다.");
		} else {
			response.sendRedirect("/mypage");
			System.err.println("오류가 발생했습니다.");
		}
	}
}