package schedule.action;

import schedule.model.ApiManager;
import controller.Action;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.util.Map;

public class GetMedicineInfoAction implements Action {
    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) {
        String medicineName = request.getParameter("medicine-name");

        if (medicineName == null || medicineName.isEmpty()) {
            System.out.println("약 이름을 입력하지 않았습니다.");
            return;
        }

        Map<String, String> medicineInfo = ApiManager.getMedicineInfo(medicineName);

        if (medicineInfo != null) {
            System.out.println("약 코드: " + medicineInfo.get("medicineCode"));
            System.out.println("약 이름: " + medicineInfo.get("medicineName"));
        } else {
            System.out.println("약 정보를 가져오지 못했습니다.");
        }
    }
}
