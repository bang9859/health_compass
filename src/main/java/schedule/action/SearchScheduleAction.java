package schedule.action;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.List;
import com.google.gson.Gson;


import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;

import org.w3c.dom.Document;
import org.w3c.dom.NodeList;

import controller.Action;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import schedule.model.Schedule;
import schedule.model.ScheduleDao;
import schedule.model.ScheduleRequestDto;
import util.XMLParser;

public class SearchScheduleAction implements Action {

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// 현재 로그인된 사용자 확인
		String username = request.getParameter("usernameForSerachSchedule");
		if (username == null) {
			response.sendRedirect("/login");
			return;
		}

		// 1. DB에서 사용자 스케줄 가져오기
		ScheduleDao scheduleDao = ScheduleDao.getInstance();
		List<Schedule> scheduleList = scheduleDao.findSchedulesByUsername(username);

		if (scheduleList == null || scheduleList.isEmpty()) {
			request.setAttribute("message", "등록된 일정이 없습니다.");
			request.getRequestDispatcher("/schedule").forward(request, response);
			return;
		}

		// 2. 스케줄에 API 데이터 추가
		List<ScheduleRequestDto> enrichedScheduleList = new ArrayList<>();
		for (Schedule schedule : scheduleList) {
			ScheduleRequestDto enrichedSchedule = enrichScheduleWithApiData(schedule);
			enrichedScheduleList.add(enrichedSchedule);
		}

		// 3. JSON 변환 후 JSP로 전달
		Gson gson = new Gson();
		String scheduleListJson = gson.toJson(enrichedScheduleList); // JSON 변환
		request.setAttribute("scheduleListJson", scheduleListJson);
		request.getRequestDispatcher("/schedule").forward(request, response);
	}

	private ScheduleRequestDto enrichScheduleWithApiData(Schedule schedule) {
		try {
			// API 호출 준비
			String serviceKey = "oruvbo%2BL%2B8mY49TbDDPKgBJmt8%2BaC4EPCinp%2FKfYxFIgRIp7iRMVQoqyWxZle%2FBv%2B22H%2BLJTKBTKU02ylL3ZJg%3D%3D";
			StringBuilder urlBuilder = new StringBuilder(
					"http://apis.data.go.kr/1471000/DrbEasyDrugInfoService/getDrbEasyDrugList");
			urlBuilder.append("?" + URLEncoder.encode("serviceKey", "UTF-8") + "=" + serviceKey);
			urlBuilder.append("&" + URLEncoder.encode("itemSeq", "UTF-8") + "=" + schedule.getMedicineCode());

			URL url = new URL(urlBuilder.toString());
			HttpURLConnection conn = (HttpURLConnection) url.openConnection();
			conn.setRequestMethod("GET");
			conn.setRequestProperty("Content-type", "application/json");

			BufferedReader rd;
			if (conn.getResponseCode() >= 200 && conn.getResponseCode() <= 300) {
				rd = new BufferedReader(new InputStreamReader(conn.getInputStream()));
			} else {
				rd = new BufferedReader(new InputStreamReader(conn.getErrorStream()));
			}

			StringBuilder sb = new StringBuilder();
			String line;
			while ((line = rd.readLine()) != null) {
				sb.append(line);
			}
			rd.close();
			conn.disconnect();

			// XML 응답 파싱 및 데이터 추가
			return parseApiResponse(sb.toString(), schedule);

		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	private ScheduleRequestDto parseApiResponse(String xml, Schedule schedule) {
		try {
			// XML 파싱
			DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
			DocumentBuilder builder = factory.newDocumentBuilder();
			Document doc = builder.parse(new java.io.ByteArrayInputStream(xml.getBytes("UTF-8")));

			NodeList items = XMLParser.getNodeListByTagName(doc, "item");
			String medicineName = "정보 없음";
			String depositMethod = "정보 없음";

			if (items.getLength() > 0) {
				NodeList childNodes = items.item(0).getChildNodes();
				medicineName = getNodeValue(childNodes, "itemName");
				depositMethod = getNodeValue(childNodes, "depositMethodQesitm");
			}

			// ScheduleRequestDto에 API 데이터를 추가
			return new ScheduleRequestDto(schedule.getUserCode(), schedule.getMedicineCode(), schedule.getStartDate(),
					schedule.getEndDate(), schedule.getDailyFrequency(), medicineName, depositMethod);

		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	private String getNodeValue(NodeList nodeList, String tagName) {
		for (int i = 0; i < nodeList.getLength(); i++) {
			if (nodeList.item(i).getNodeName().equals(tagName)) {
				return nodeList.item(i).getTextContent();
			}
		}
		return "정보 없음";
	}
}
