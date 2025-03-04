package hospital.action;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

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
		LocalTime currentTime = LocalTime.now();

		List<HospitalDto> hospitalList = null;
		if (currentTime.isAfter(LocalTime.of(18, 0))) {
			// 18시 이후
			hospitalList = HospitalsEmergencySearch(userAddress);
		} else {
			// 18시 이전
			String internalCode = request.getParameter("internalCode");
			String dermatologyCode = request.getParameter("dermatologyCode");
			String orthopedicCode = request.getParameter("orthopedicCode");
			String neurosurgeryCode = request.getParameter("neurosurgeryCode");
			String gynecologyCode = request.getParameter("gynecologyCode");
			String ophthalmologyCode = request.getParameter("ophthalmologyCode");
			String otolaryngologyCode = request.getParameter("otolaryngologyCode");
			String dentistryCode = request.getParameter("dentistryCode");

			// 병원 들고오기
			hospitalList = HospitalsList(userAddress, internalCode, dermatologyCode, orthopedicCode, neurosurgeryCode,
					gynecologyCode, ophthalmologyCode, otolaryngologyCode, dentistryCode);
		}

		// 출력
//		System.out.println("병원 목록:");
//		for (int i = 0; i < hospitalList.size(); i++) {
//			System.out.println((i + 1) + " 번");
//			System.out.println(hospitalList.get(i));
//			System.out.println("------------------------");
//		}
//		System.out.println("병원 리스트 크기: " + hospitalList.size());

		// json 변환
		JSONArray hospitalListJson = new JSONArray(hospitalList);
		request.setAttribute("hospitalListJson", hospitalListJson.toString());
		request.getRequestDispatcher("/hospitalsForm").forward(request, response);
	}

	public List<HospitalDto> HospitalsList(String userAddress, String internalCode, String dermatologyCode,
			String orthopedicCode, String neurosurgeryCode, String gynecologyCode, String ophthalmologyCode,
			String otolaryngologyCode, String dentistryCode) {
		List<HospitalDto> hospitalsList = new ArrayList<>();
		List<HospitalDto> hospitaInternalList = new ArrayList<>();
		List<HospitalDto> hospitaDermatologyList = new ArrayList<>();
		List<HospitalDto> hospitaOrthopedicList = new ArrayList<>();
		List<HospitalDto> hospitaNeurosurgeryList = new ArrayList<>();
		List<HospitalDto> hospitaGynecologlList = new ArrayList<>();
		List<HospitalDto> hospitaOphthalmologyList = new ArrayList<>();
		List<HospitalDto> hospitaOtolaryngologyList = new ArrayList<>();
		List<HospitalDto> hospitaDentistryList = new ArrayList<>();

		// 출력
//		System.out.println("internalCode " + internalCode);
//		System.out.println("dermatologyCode " + dermatologyCode);
//		System.out.println("orthopedicCode " + orthopedicCode);
//		System.out.println("neurosurgeryCode " + neurosurgeryCode);
//		System.out.println("gynecologyCode " + gynecologyCode);
//		System.out.println("ophthalmologyCode " + ophthalmologyCode);
//		System.out.println("otolaryngologyCode " + otolaryngologyCode);
//		System.out.println("dentistryCode " + dentistryCode);

		if ("D001".equals(internalCode)) {
		    hospitaInternalList = HospitalsSearch(userAddress, internalCode, "내과");
		    hospitalsList.addAll(hospitaInternalList);
		}
		if ("D005".equals(dermatologyCode)) {
		    hospitaDermatologyList = HospitalsSearch(userAddress, dermatologyCode, "피부과");
		    hospitalsList.addAll(hospitaDermatologyList);
		}
		if ("D008".equals(orthopedicCode)) {
		    hospitaOrthopedicList = HospitalsSearch(userAddress, orthopedicCode, "정형외과");
		    hospitalsList.addAll(hospitaOrthopedicList);
		}
		if ("D009".equals(neurosurgeryCode)) { 
		    hospitaNeurosurgeryList = HospitalsSearch(userAddress, neurosurgeryCode, "신경외과");
		    hospitalsList.addAll(hospitaNeurosurgeryList);
		}
		if ("D011".equals(gynecologyCode)) { 
		    hospitaGynecologlList = HospitalsSearch(userAddress, gynecologyCode, "산부인과");
		    hospitalsList.addAll(hospitaGynecologlList);
		}
		if ("D012".equals(ophthalmologyCode)) {
		    hospitaOphthalmologyList = HospitalsSearch(userAddress, ophthalmologyCode, "안과");
		    hospitalsList.addAll(hospitaOphthalmologyList);
		}
		if ("D013".equals(otolaryngologyCode)) { 
		    hospitaOtolaryngologyList = HospitalsSearch(userAddress, otolaryngologyCode, "이비인후과");
		    hospitalsList.addAll(hospitaOtolaryngologyList);
		}
		if ("D026".equals(dentistryCode)) {
		    hospitaDentistryList = HospitalsSearch(userAddress, dentistryCode, "치과");
		    hospitalsList.addAll(hospitaDentistryList);
		}


		return mergeHospitals(hospitalsList);
	}

	public List<HospitalDto> mergeHospitals(List<HospitalDto> hospitalsList) {
		Map<String, HospitalDto> hospitalMap = new LinkedHashMap<>();

		for (HospitalDto hospital : hospitalsList) {
			String key = hospital.getOperatingHours(); // 병원의 고유 식별자 (기관코드)

			if (hospitalMap.containsKey(key)) {
				// 이미 존재하는 병원인 경우
				HospitalDto existingHospital = hospitalMap.get(key);

				// 기존 진료과에 새 진료과 추가
				String existingDepartments = existingHospital.getTitle();
				String newDepartment = hospital.getTitle();

				// 중복 방지하며 진료과 추가
				if (!existingDepartments.contains(newDepartment)) {
					existingHospital.setTitle(existingDepartments + ", " + newDepartment);
				}
			} else {
				// 새로운 병원인 경우 맵에 추가
				hospitalMap.put(key, hospital);
			}
		}

		// 병합된 병원을 리스트로 변환
		List<HospitalDto> mergedList = new ArrayList<>(hospitalMap.values());

		// 진료과 개수를 기준으로 정렬 (진료과가 많은 병원이 맨 앞에 오도록)
		mergedList.sort((h1, h2) -> {
			int h1Count = h1.getTitle().split(",").length;
			int h2Count = h2.getTitle().split(",").length;
			return Integer.compare(h2Count, h1Count); // 내림차순 정렬
		});

		return mergedList;
	}

	// 6시 이전
	public List<HospitalDto> HospitalsSearch(String userAddress, String code, String title) {
		try {
			System.out.println("userAddress " + userAddress);
			System.out.println("code " + code);
			System.out.println("title " + title);
			
			String[] address = userAddress.split(" ");

			String serviceKey = "%2BVuLyglK6Y6SHbveZuAdhvqRGwAEh7ozSxVRDMiZZdoIPDLEKkEVf0lYD7egwi%2FIHsaJmiMlIZjCECfdJeSd0w%3D%3D";

			StringBuilder urlBuilder = new StringBuilder(
					"http://apis.data.go.kr/B552657/HsptlAsembySearchService/getHsptlMdcncListInfoInqire");
			urlBuilder.append("?" + URLEncoder.encode("serviceKey", "UTF-8") + "=" + serviceKey);
			urlBuilder.append("&" + URLEncoder.encode("Q0", "UTF-8") + "=" + URLEncoder.encode(address[0], "UTF-8")); // 주소(시도)
			urlBuilder.append("&" + URLEncoder.encode("Q1", "UTF-8") + "=" + URLEncoder.encode(address[1], "UTF-8")); // 주소(시군구)
			urlBuilder.append("&" + URLEncoder.encode("QD", "UTF-8") + "=" + URLEncoder.encode(code, "UTF-8")); // CODE_MST의
			urlBuilder.append("&" + URLEncoder.encode("numOfRows", "UTF-8") + "=" + URLEncoder.encode("100", "UTF-8")); // 목록건수

			// URL 설정
			URL url = new URL(urlBuilder.toString());
			HttpURLConnection conn = (HttpURLConnection) url.openConnection();
			conn.setRequestMethod("GET");
			conn.setRequestProperty("Content-type", "application/json");

			// 응답 코드 확인
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
			List<HospitalDto> hospitalList = parseXmlResponse(sb.toString(), title);
			return hospitalList;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	private List<HospitalDto> parseXmlResponse(String xml, String title) {
		try {
			// XML 파싱
			Document doc = (Document) XMLParser.parseXML(xml);

			// 병원 정보 추출
			NodeList items = XMLParser.getNodeListByTagName(doc, "item");

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

				HospitalDto hospital = new HospitalDto(title, name, address, phone, type, emergency, hpid,
						operatingHours, latitude, longitude);

				hospitalList.add(hospital);

			}
			return hospitalList;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	public List<HospitalDto> HospitalsEmergencySearch(String userAddress) {
		try {
			String[] address = userAddress.split(" ");

			String serviceKey = "%2BVuLyglK6Y6SHbveZuAdhvqRGwAEh7ozSxVRDMiZZdoIPDLEKkEVf0lYD7egwi%2FIHsaJmiMlIZjCECfdJeSd0w%3D%3D";

			StringBuilder urlBuilder = new StringBuilder(
					"http://apis.data.go.kr/B552657/HsptlAsembySearchService/getHsptlMdcncListInfoInqire");
			urlBuilder.append("?" + URLEncoder.encode("serviceKey", "UTF-8") + "=" + serviceKey);
			urlBuilder.append("&" + URLEncoder.encode("Q0", "UTF-8") + "=" + URLEncoder.encode(address[0], "UTF-8")); // 주소(시도)
			urlBuilder.append("&" + URLEncoder.encode("Q1", "UTF-8") + "=" + URLEncoder.encode(address[1], "UTF-8")); // 주소(시군구)
			urlBuilder.append("&" + URLEncoder.encode("numOfRows", "UTF-8") + "=" + URLEncoder.encode("3000", "UTF-8")); // 목록건수

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
			String title = "응급실";
			List<HospitalDto> hospitalList = EmergencyParseXmlResponse(sb.toString(), title);
			return hospitalList;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	private List<HospitalDto> EmergencyParseXmlResponse(String xml, String title) {
		try {
			// XML 파싱
			Document doc = (Document) XMLParser.parseXML(xml);

			// 병원 정보 추출
			NodeList items = XMLParser.getNodeListByTagName(doc, "item");

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

				if (emergency.equals("1")) {
					HospitalDto hospital = new HospitalDto(title, name, address, phone, type, emergency, hpid,
							operatingHours, latitude, longitude);

					hospitalList.add(hospital);
				}

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
