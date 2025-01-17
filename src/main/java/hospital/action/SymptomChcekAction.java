package hospital.action;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;

import org.w3c.dom.Document;
import org.w3c.dom.NodeList;

import controller.Action;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

public class SymptomChcekAction implements Action {

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		test();

		response.sendRedirect("/hospitals");
	}

	public void test() {
		try {
			String serviceKey = "%2BVuLyglK6Y6SHbveZuAdhvqRGwAEh7ozSxVRDMiZZdoIPDLEKkEVf0lYD7egwi%2FIHsaJmiMlIZjCECfdJeSd0w%3D%3D";

			StringBuilder urlBuilder = new StringBuilder(
					"http://apis.data.go.kr/B552657/HsptlAsembySearchService/getHsptlMdcncListInfoInqire");
			urlBuilder.append("?" + URLEncoder.encode("serviceKey", "UTF-8") + "=" + serviceKey); // Service Key
			urlBuilder.append("&" + URLEncoder.encode("Q0", "UTF-8") + "=" + URLEncoder.encode("서울특별시", "UTF-8")); // 주소(시도)
			urlBuilder.append("&" + URLEncoder.encode("Q1", "UTF-8") + "=" + URLEncoder.encode("강남구", "UTF-8")); // 주소(시군구)
			//urlBuilder.append("&" + URLEncoder.encode("QZ", "UTF-8") + "=" + URLEncoder.encode("B", "UTF-8")); // CODE_MST의
																												// 'H000'
																												// 참조
																												// (B:병원)
			urlBuilder.append("&" + URLEncoder.encode("QD", "UTF-8") + "=" + URLEncoder.encode("D001", "UTF-8")); // CODE_MST의
																													// 'D000'
																													// 참조
																													// (D001~D029)
			urlBuilder.append("&" + URLEncoder.encode("QT", "UTF-8") + "=" + URLEncoder.encode("1", "UTF-8")); // 월~일요일(1~7),
																												// 공휴일(8)
			urlBuilder.append("&" + URLEncoder.encode("ORD", "UTF-8") + "=" + URLEncoder.encode("NAME", "UTF-8")); // 순서
			urlBuilder.append("&" + URLEncoder.encode("pageNo", "UTF-8") + "=" + URLEncoder.encode("1", "UTF-8")); // 페이지
																													// 번호
			urlBuilder.append("&" + URLEncoder.encode("numOfRows", "UTF-8") + "=" + URLEncoder.encode("100", "UTF-8")); // 목록
																														// 건수

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

			// 결과 출력
			System.out.println(sb.toString());

			// XML 파싱
			parseXmlResponse(sb.toString());

		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	private void parseXmlResponse(String xml) {
	    try {
	        // XML 파싱
	        DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
	        DocumentBuilder builder = factory.newDocumentBuilder();
	        Document doc = builder.parse(new java.io.ByteArrayInputStream(xml.getBytes("UTF-8")));

	        // 병원 정보 추출
	        NodeList items = doc.getElementsByTagName("item");

	        // 병원 정보 출력
	        System.out.println("병원 목록:");
	        for (int i = 0; i < items.getLength(); i++) {
	            NodeList nameNode = items.item(i).getChildNodes();
	            String name = getNodeValue(nameNode, "dutyName");
	            String address = getNodeValue(nameNode, "dutyAddr");
	            String phone = getNodeValue(nameNode, "dutyTel1");
	            String type = getNodeValue(nameNode, "dutyDivNam");
	            String emergencyClass = getNodeValue(nameNode, "dutyEmclsName");
	            String operatingHours = getOperatingHours(nameNode);

	            System.out.println("병원명: " + name);
	            System.out.println("주소: " + address);
	            System.out.println("전화번호: " + phone);
	            System.out.println("병원 종류: " + type);
	            System.out.println("응급의료기관 종류: " + emergencyClass);
	            System.out.println("진료 시간: " + operatingHours);
	            System.out.println("------------------------");
	        }
	    } catch (Exception e) {
	        e.printStackTrace();
	    }
	}

	private String getNodeValue(NodeList nodeList, String tagName) {
	    for (int i = 0; i < nodeList.getLength(); i++) {
	        if (nodeList.item(i).getNodeName().equals(tagName)) {
	            return nodeList.item(i).getTextContent();
	        }
	    }
	    return "정보 없음"; // 태그가 없을 경우 기본 값
	}

	private String getOperatingHours(NodeList nodeList) {
	    StringBuilder hours = new StringBuilder();
	    for (int i = 1; i <= 6; i++) {
	        String startTime = getNodeValue(nodeList, "dutyTime" + i + "s");
	        String endTime = getNodeValue(nodeList, "dutyTime" + i + "c");
	        if (startTime != null && endTime != null) {
	            hours.append("일 " + i + ": " + startTime + " ~ " + endTime + "\n");
	        }
	    }
	    return hours.toString();
	}


}
