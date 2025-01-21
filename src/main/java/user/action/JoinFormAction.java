package user.action;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import user.model.UserDao;
import user.model.UserRequestDto;
import java.io.IOException;
import controller.Action;

public class JoinFormAction implements Action {
	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String username = request.getParameter("username");
		String password = request.getParameter("password");
		String email = request.getParameter("email");
		String name = request.getParameter("name");
		String birth = request.getParameter("birth");
		String gender = request.getParameter("gender");
		String tel = request.getParameter("tel");
		UserDao userDao = UserDao.getInstance();
		if (userDao.findUserByUsername(username) == null) {
			UserRequestDto userDto = new UserRequestDto(username, password, email, name, birth, gender, tel);
			userDao.createUser(userDto);
			response.sendRedirect("/");
			System.err.println("회원가입이 완료되었습니다.");
		} else {
			response.sendRedirect("/join");
			System.err.println("아이디가 중복됩니다.");
		}
	}
}