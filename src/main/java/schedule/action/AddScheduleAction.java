package schedule.action;

import java.io.IOException;
import java.util.Map;

import controller.Action;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import schedule.model.ApiManager;
import schedule.model.ScheduleDao;
import schedule.model.ScheduleRequestDto;
import user.model.UserDao;

public class AddScheduleAction implements Action {
	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String username =request.getParameter("username");
		UserDao userDao = UserDao.getInstance();
	    int userCode = userDao.findUserCodeByUsername(username);
		int medicineCode = Integer.parseInt(request.getParameter("medicine-code"));
		System.out.println("받은 약품 번호: " + medicineCode);
		String startDate = request.getParameter("start-date");
		String endDate = request.getParameter("end-date");
		int dailyFrequency = Integer.parseInt(request.getParameter("daily-frequency"));

		ScheduleDao scheduleDao = ScheduleDao.getInstance();

		ScheduleRequestDto scheduleDto = new ScheduleRequestDto(userCode, medicineCode, startDate, endDate,
				dailyFrequency);
		scheduleDao.addSchedule(scheduleDto);
		try {
			response.sendRedirect("/schedule");
		} catch (IOException e) {
			e.printStackTrace();
		}

	}
}
