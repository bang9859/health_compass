package schedule.action;

import java.io.IOException;
import java.util.Map;

import controller.Action;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import schedule.model.ApiManager;
import schedule.model.ScheduleDao;

public class AddScheduleAction implements Action {
    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) {
        String medicineName = request.getParameter("medicine-name");
        String startDate = request.getParameter("start-date");
        String endDate = request.getParameter("end-date");
        int dailyFrequency = Integer.parseInt(request.getParameter("daily-frequency"));

        Map<String, String> medicineInfo = ApiManager.getMedicineInfo(medicineName);

        if (medicineInfo == null) {
            request.setAttribute("errorMessage", "약 정보를 찾을 수 없습니다.");
            try {
				request.getRequestDispatcher("/error.jsp").forward(request, response);
			} catch (ServletException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
            return;
        }

        ScheduleDao dao = new ScheduleDao();
        //관리자 계정으로 임시 저장
        boolean isSaved = dao.addSchedule(1007,
                                          Integer.parseInt(medicineInfo.get("medicineCode")),
                                          startDate, endDate, dailyFrequency);

        if (isSaved) {
            try {
				response.sendRedirect("/schedule");
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
        } else {
            request.setAttribute("errorMessage", "일정을 저장할 수 없습니다.");
            try {
				request.getRequestDispatcher("/error").forward(request, response);
			} catch (ServletException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
        }
    }
}
