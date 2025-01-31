package schedule.action;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import controller.Action;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import schedule.model.Schedule;
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
		String medicineName = request.getParameter("medicine-name");
		String depositMethod = request.getParameter("medicine-deposit-method");
		System.out.println("받은 약품 번호: " + medicineCode);
		String startDate = request.getParameter("start-date");
		String endDate = request.getParameter("end-date");
		int dailyFrequency = Integer.parseInt(request.getParameter("daily-frequency"));

		ScheduleDao scheduleDao = ScheduleDao.getInstance();

		ScheduleRequestDto scheduleDto = new ScheduleRequestDto(userCode, medicineCode,medicineName,depositMethod, startDate, endDate,
				dailyFrequency);
		scheduleDao.addSchedule(scheduleDto);
		try {

			List<Schedule> userAllScheduleList = scheduleDao.findSchedulesByUsername(username);
			List<Schedule> scheduleList = new ArrayList<Schedule>();
			
			for(int i=0; i<userAllScheduleList.size(); i++) {
				scheduleList.add(userAllScheduleList.get(i));
			}
			
			HttpSession session = request.getSession();
			session.setAttribute("schedule", scheduleList);
			response.sendRedirect("/schedule");
		} catch (IOException e) {
			e.printStackTrace();
		}

	}
}
