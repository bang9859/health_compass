package schedule.model;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.HashMap;
import java.util.Map;

public class ApiManager {
    private static final String API_URL = "http://apis.data.go.kr/1471000/DrbEasyDrugInfoService/getDrbEasyDrugList";
    private static final String API_KEY = "oruvbo%2BL%2B8mY49TbDDPKgBJmt8%2BaC4EPCinp%2FKfYxFIgRIp7iRMVQoqyWxZle%2FBv%2B22H%2BLJTKBTKU02ylL3ZJg%3D%3D";

    public static Map<String, String> getMedicineInfo(String medicineName) {
        try {
            String apiUrl = API_URL + "?serviceKey=" + API_KEY + "&itemName=" + medicineName;
            URL url = new URL(apiUrl);
            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
            conn.setRequestMethod("GET");

            BufferedReader br = new BufferedReader(new InputStreamReader(conn.getInputStream()));
            StringBuilder sb = new StringBuilder();
            String line;

            while ((line = br.readLine()) != null) {
                sb.append(line);
            }
            br.close();

            // 파싱 (간단히 예제만 구현)
            // 실제 데이터는 XML 또는 JSON 파싱 필요
            Map<String, String> result = new HashMap<>();
            result.put("medicineCode", "12345"); // 예제 데이터
            result.put("medicineName", medicineName);
            return result;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }
}
