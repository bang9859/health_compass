package schedule.action;


import java.io.IOException;
import java.util.List;

import controller.Action;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import schedule.model.Schedule;
import schedule.model.ScheduleDao;

public class SearchScheduleAction implements Action {
	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String username = (String) request.getSession().getAttribute("username");
        ScheduleDao scheduleDao = ScheduleDao.getInstance();
       
        List<Schedule> schedules = scheduleDao.findSchedulesByUserCode(username);
        System.out.println(schedules);

        // JSP에 데이터 전달
        request.setAttribute("schedules", schedules);
        request.getRequestDispatcher("/schedule.jsp").forward(request, response);
    }
}