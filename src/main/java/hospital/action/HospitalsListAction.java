package hospital.action;

import java.io.IOException;
import java.util.List;

import controller.Action;
import hospital.model.HospitalDto;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

public class HospitalsListAction implements Action{

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	    // 세션에서 병원 리스트 가져오기
//	    HttpSession session = request.getSession();
//	    List<HospitalDto> hospitalList = (List<HospitalDto>) session.getAttribute("hospitalList");
//
//	    if (hospitalList == null) {
//	        System.out.println("병원 리스트가 없습니다.");
//	    } else {
//			// 출력
//			System.out.println("병원 목록:");
//			for (int i = 0; i < hospitalList.size(); i++) {
//				System.out.println((i + 1) + " 번");
//				System.out.println(hospitalList.get(i));
//				System.out.println("------------------------");
//			}
//	    }

//	    // 결과 페이지로 포워딩
//	    request.setAttribute("hospitalList", hospitalList);
//	    request.getRequestDispatcher("/hospitals").forward(request, response);
		
	}

}
