package user.action;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import user.model.UserDao;
import user.model.UserRequestDto;
import java.io.IOException;
import controller.Action;

public class JoinFormAction implements Action{
	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String username = request.getParameter("username");
		String password = request.getParameter("password");
		String email = request.getParameter("email");
		String name = request.getParameter("name");
		String birth = request.getParameter("birth");
		String gender = request.getParameter("gender");
		String tel = request.getParameter("tel");
		
		UserRequestDto userDto = new UserRequestDto(username, password, email, name, birth, gender, tel);
		UserDao userDao = UserDao.getInstance();
		
		if(userDao.findUserByUsername(username) == null) {
			System.out.println("중복되는 이름이 없습니다");
		} else {
			System.out.println("이름이 중복됩니다");
		}
	}
}
