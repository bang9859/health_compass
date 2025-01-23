package user.action;

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
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import user.model.Bookmark;
import util.XMLParser;

public class SearchHospital {
	public Bookmark searchHospital(String hospitalId) {
		List<Bookmark> bookmarkList2 = new ArrayList<Bookmark>();
		System.out.println("병원 목록:");
		for (int i = 0; i < bookmarkList2.size(); i++) {
			System.out.println((i + 1) + " 번");
			System.out.println(bookmarkList2.get(i).getHospitalId());
			System.out.println(bookmarkList2.get(i).getHospitalName());
			System.out.println(bookmarkList2.get(i).getAddress());
			System.out.println("------------------------");
		}
		
		JSONArray hospitalListJson = new JSONArray(bookmarkList2);
		System.out.println(hospitalListJson.toString());
		try {
			String serviceKey = "B2QVmFyyb9dQL1IOBqqY6rUoGEALjBoTtsgY5rb9NC4RXVF04Fmww9gPFLyJ22DLnmajbV%2Fq1EPEiRu1EnxtxQ%3D%3D";

			StringBuilder urlBuilder = new StringBuilder("http://apis.data.go.kr/B552657/HsptlAsembySearchService/getHsptlBassInfoInqire");
			urlBuilder.append("?" + URLEncoder.encode("serviceKey", "UTF-8") + "=" + serviceKey);
			urlBuilder.append("&" + URLEncoder.encode("HPID", "UTF-8") + "=" + URLEncoder.encode(hospitalId, "UTF-8"));

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
			Bookmark bookmark = parseXmlResponse(sb.toString());
			return bookmark;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}
	
	private Bookmark parseXmlResponse(String xml) {
		Bookmark bookmark = null;
		try {
			// XML 파싱
			DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
			DocumentBuilder builder = factory.newDocumentBuilder();
			Document doc = builder.parse(new java.io.ByteArrayInputStream(xml.getBytes("UTF-8")));

			// 병원 정보 추출
			NodeList items = XMLParser.getNodeListByTagName(doc,"item");

			for (int i = 0; i < items.getLength(); i++) {
				NodeList nameNode = items.item(i).getChildNodes();
				
				String hospitalId = getNodeValue(nameNode, "hpid");
				String name = getNodeValue(nameNode, "dutyName");
				String address = getNodeValue(nameNode, "dutyAddr");

				bookmark = new Bookmark(hospitalId, name, address);
			}
			return bookmark;
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