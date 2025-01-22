package hospital.action;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.List;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;

import org.json.JSONArray;
import org.w3c.dom.Document;
import org.w3c.dom.NodeList;

import controller.Action;
import hospital.model.HospitalDto;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import util.XMLParser;

public class HospitalsSearchAction implements Action {

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String userAddress = request.getParameter("address");
		System.out.println("사용자 주소: " + userAddress);

		// 병원 들고오기
		List<HospitalDto> hospitalList = HospitalsSearch(userAddress);

		// 병원 리스트가 null이거나 비어 있는지 확인
		if (hospitalList == null || hospitalList.isEmpty()) {
			System.out.println("병원 리스트가 비어 있습니다!");
			request.getRequestDispatcher("/main");
			return;
		}

		// 출력
		System.out.println("병원 목록:");
		for (int i = 0; i < hospitalList.size(); i++) {
			System.out.println((i + 1) + " 번");
			System.out.println(hospitalList.get(i));
			System.out.println("------------------------");
		}
		System.out.println("병원 리스트 크기: " + hospitalList.size());

		// json 변환
		JSONArray hospitalListJson = new JSONArray(hospitalList);
		request.setAttribute("hospitalListJson", hospitalListJson.toString());
		request.getRequestDispatcher("/hospitalsForm").forward(request, response);
	}

	public List<HospitalDto> HospitalsSearch(String userAddress) {
		try {
			String[] address = userAddress.split(" ");

			String serviceKey = "%2BVuLyglK6Y6SHbveZuAdhvqRGwAEh7ozSxVRDMiZZdoIPDLEKkEVf0lYD7egwi%2FIHsaJmiMlIZjCECfdJeSd0w%3D%3D";

			StringBuilder urlBuilder = new StringBuilder(
					"http://apis.data.go.kr/B552657/HsptlAsembySearchService/getHsptlMdcncListInfoInqire");
			urlBuilder.append("?" + URLEncoder.encode("serviceKey", "UTF-8") + "=" + serviceKey);
			urlBuilder.append("&" + URLEncoder.encode("Q0", "UTF-8") + "=" + URLEncoder.encode(address[0], "UTF-8")); // 주소(시도)
			urlBuilder.append("&" + URLEncoder.encode("Q1", "UTF-8") + "=" + URLEncoder.encode(address[1], "UTF-8")); // 주소(시군구)
			// urlBuilder.append("&" + URLEncoder.encode("QZ", "UTF-8") + "=" +
			// URLEncoder.encode("B", "UTF-8")); // CODE_MST의
			// 'H000'
			// 참조
			// (B:병원)
			// urlBuilder.append("&" + URLEncoder.encode("QD", "UTF-8") + "=" +
			// URLEncoder.encode("D001", "UTF-8")); // CODE_MST의
			// 'D000'
			// 참조
			// (D001~D029)
			// urlBuilder.append("&" + URLEncoder.encode("QT", "UTF-8") + "=" +
			// URLEncoder.encode("1", "UTF-8")); // 월~일요일(1~7),
			// 공휴일(8)
			// urlBuilder.append("&" + URLEncoder.encode("ORD", "UTF-8") + "=" +
			// URLEncoder.encode("NAME", "UTF-8")); // 순서
			// urlBuilder.append("&" + URLEncoder.encode("pageNo", "UTF-8") + "=" +
			// URLEncoder.encode("1", "UTF-8")); // 페이지
			// 번호
			urlBuilder.append("&" + URLEncoder.encode("numOfRows", "UTF-8") + "=" + URLEncoder.encode("100", "UTF-8")); // 목록건수

			// URL 설정
			URL url = new URL(urlBuilder.toString());
			HttpURLConnection conn = (HttpURLConnection) url.openConnection();
			conn.setRequestMethod("GET");
			conn.setRequestProperty("Content-type", "application/json");

			// 응답 코드 확인
			System.out.println("Response code: " + conn.getResponseCode());
			BufferedReader rd;
			if (conn.getResponseCode() >= 200 && conn.getResponseCode() <= 300) {
				rd = new BufferedReader(new InputStreamReader(conn.getInputStream()));
			} else {
				rd = new BufferedReader(new InputStreamReader(conn.getErrorStream()));
			}

			// 응답 내용 읽기
			StringBuilder sb = new StringBuilder();
			String line;
			while ((line = rd.readLine()) != null) {
				sb.append(line);
			}
			rd.close();
			conn.disconnect();

			// XML 파싱
			List<HospitalDto> hospitalList = parseXmlResponse(sb.toString());
			return hospitalList;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	private List<HospitalDto> parseXmlResponse(String xml) {
		try {
			// XML 파싱
			DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
			DocumentBuilder builder = factory.newDocumentBuilder();
			Document doc = builder.parse(new java.io.ByteArrayInputStream(xml.getBytes("UTF-8")));

			// 병원 정보 추출
			NodeList items = XMLParser.getNodeListByTagName(doc,"item");

			// 병원 정보를 저장 리스트
			List<HospitalDto> hospitalList = new ArrayList<>();

			for (int i = 0; i < items.getLength(); i++) {
				NodeList nameNode = items.item(i).getChildNodes();
				String name = getNodeValue(nameNode, "dutyName"); // 병원 이름
				String address = getNodeValue(nameNode, "dutyAddr"); // 주소
				String phone = getNodeValue(nameNode, "dutyTel1"); // 전화번호
				String type = getNodeValue(nameNode, "dutyDivNam"); // 종류(의원, 병원, 종병..)
				String emergency = getNodeValue(nameNode, "dutyEryn"); // 응급실 여부(1: 운영, 2: 미운영)
				String hpid = getNodeValue(nameNode, "hpid"); // 기관코드
				double latitude = Double.parseDouble(getNodeValue(nameNode, "wgs84Lat")); // 위도
				double longitude = Double.parseDouble(getNodeValue(nameNode, "wgs84Lon")); // 경도
				String operatingHours = getOperatingHours(nameNode); // 진료 시간

				HospitalDto hospital = new HospitalDto(name, address, phone, type, emergency, hpid, operatingHours,
						latitude, longitude);

				hospitalList.add(hospital);
			}
			return hospitalList;
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

	private String getOperatingHours(NodeList nodeList) {
		StringBuilder hours = new StringBuilder();

		String[] days = { "월요일", "화요일", "수요일", "목요일", "금요일", "토요일" };

		for (int i = 1; i <= days.length; i++) {
			String startTime = getNodeValue(nodeList, "dutyTime" + i + "s");
			String endTime = getNodeValue(nodeList, "dutyTime" + i + "c");
			if (startTime != null && endTime != null) {
				hours.append(days[i - 1] + ": " + startTime + " ~ " + endTime + "\n");
			}
		}
		return hours.toString();
	}

}
