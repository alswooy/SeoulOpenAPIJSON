package Api;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;
import java.io.BufferedReader;
import java.io.IOException;

import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonParser;
import com.google.gson.JsonObject;


public class ApiExplorer {

	public static final int COUNT = 1000;
	public static final String URL = "http://openapi.seoul.go.kr:8088/sample/json/TbPublicWifiInfo/1/1";
	
	public static void loadWifi() throws IOException{
		
		int listTotalCount = getListTotalCount();
		int loopCount = listTotalCount / COUNT+ 1;		
		
		for (int i = 0, j = 1; i < loopCount; i++, j += COUNT) {
			StringBuilder urlBuilder = new StringBuilder("http://openapi.seoul.go.kr:8088"); 
			urlBuilder.append("/" + URLEncoder.encode("434c545a586a6a613636494f775847","UTF-8")); 
			urlBuilder.append("/" + URLEncoder.encode("json","UTF-8"));
			urlBuilder.append("/" + URLEncoder.encode("TbPublicWifiInfo","UTF-8")); 
			urlBuilder.append("/" + URLEncoder.encode(String.valueOf(j), "UTF-8")); 
			urlBuilder.append("/" + URLEncoder.encode(String.valueOf(j + COUNT- 1),"UTF-8")); 
			
			URL url = new URL(urlBuilder.toString());
			HttpURLConnection conn = (HttpURLConnection) url.openConnection();
			conn.setRequestMethod("GET");
			System.out.println("Response code: " + conn.getResponseCode());
			
			
			BufferedReader rd;
			if(conn.getResponseCode() >= 200 && conn.getResponseCode() <= 300) {
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
				
			JsonParser parser = new JsonParser();
			JsonElement element = parser.parse(sb.toString());
			JsonObject tbPublicWifiInfo = element.getAsJsonObject().get("TbPublicWifiInfo").getAsJsonObject();
			JsonArray rows = tbPublicWifiInfo.get("row").getAsJsonArray();
			
			for (JsonElement row : rows) {
				WifiDto dto = new WifiDto();
				String MGR_NO = row.getAsJsonObject().get("X_SWIFI_MGR_NO").getAsString();
				dto.setMGR_NO(MGR_NO);
				String WRDOFC = row.getAsJsonObject().get("X_SWIFI_WRDOFC").getAsString();
				dto.setWRDOFC(WRDOFC);
				String MAIN_NM = row.getAsJsonObject().get("X_SWIFI_MAIN_NM").getAsString(); 
				dto.setMAIN_NM(MAIN_NM);
				String ADRES1 = row.getAsJsonObject().get("X_SWIFI_ADRES1").getAsString();
				dto.setADRES1(ADRES1);
				String ADRES2 = row.getAsJsonObject().get("X_SWIFI_ADRES2").getAsString();
				dto.setADRES2(ADRES2);
				String INSTL_FLOOR = row.getAsJsonObject().get("X_SWIFI_INSTL_FLOOR").getAsString();
				dto.setINSTL_FLOOR(INSTL_FLOOR);
				String INSTL_TY = row.getAsJsonObject().get("X_SWIFI_INSTL_TY").getAsString();
				dto.setINSTL_TY(INSTL_TY);
				String INSTL_MBY = row.getAsJsonObject().get("X_SWIFI_INSTL_MBY").getAsString();
				dto.setINSTL_MBY(INSTL_MBY);
				String SVC_SE = row.getAsJsonObject().get("X_SWIFI_SVC_SE").getAsString();
				dto.setSVC_SE(SVC_SE);
				String CMCWR = row.getAsJsonObject().get("X_SWIFI_CMCWR").getAsString();
				dto.setCMCWR(CMCWR);
				String CNSTC_YEAR = row.getAsJsonObject().get("X_SWIFI_CNSTC_YEAR").getAsString();
				dto.setCNSTC_YEAR(CNSTC_YEAR);
				String INOUT_DOOR = row.getAsJsonObject().get("X_SWIFI_INOUT_DOOR").getAsString();
				dto.setINOUT_DOOR(INOUT_DOOR);
				String REMARS3 = row.getAsJsonObject().get("X_SWIFI_REMARS3").getAsString();
				dto.setREMARS3(REMARS3);
				Double LAT = row.getAsJsonObject().get("LAT").getAsDouble();
				dto.setLAT(LAT);
				Double LNT = row.getAsJsonObject().get("LNT").getAsDouble();
				dto.setLNT(LNT);
				String WORK_DTTM = row.getAsJsonObject().get("WORK_DTTM").getAsString();
				dto.setWORK_DTTM(WORK_DTTM);

				ApiDB.apiInsert(dto);
				
			}
		}
	}
	
	public static int getListTotalCount() throws IOException {
	    
		URL url = new URL(URL);
		HttpURLConnection conn = (HttpURLConnection) url.openConnection();
		conn.setRequestMethod("GET");

		BufferedReader rd;
		if(conn.getResponseCode() >= 200 && conn.getResponseCode() <= 300) {
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
		
		JsonParser parser = new JsonParser();
		JsonElement element = parser.parse(sb.toString());
		JsonObject tbPublicWifiInfo = element.getAsJsonObject().get("TbPublicWifiInfo").getAsJsonObject();
		int listTotalCount = tbPublicWifiInfo.getAsJsonObject().get("list_total_count").getAsInt();
		System.out.println(listTotalCount);
		return listTotalCount;
	}
}