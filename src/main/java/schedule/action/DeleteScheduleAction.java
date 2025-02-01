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

public class DeleteScheduleAction implements Action {
	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		int scheduleCode = Integer.parseInt(request.getParameter("schedule-code"));
		String username =request.getParameter("username");
		ScheduleDao scheduleDao = ScheduleDao.getInstance();

		
		scheduleDao.deleteSchedule(scheduleCode);
		
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