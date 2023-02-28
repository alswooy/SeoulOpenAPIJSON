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

	public static final int COUNT_LIMIT = 1000;
	public static final String SAMPLE_URL = "http://openapi.seoul.go.kr:8088/sample/json/TbPublicWifiInfo/1/1";
	
	public static void loadWifi() throws IOException{
		
		int listTotalCount = getListTotalCount();
		int loopCount = listTotalCount / COUNT_LIMIT + 1;		
		
		for (int i = 0, j = 1; i < loopCount; i++, j += COUNT_LIMIT) {
			StringBuilder urlBuilder = new StringBuilder("http://openapi.seoul.go.kr:8088"); 
			urlBuilder.append("/" + URLEncoder.encode("434c545a586a6a613636494f775847","UTF-8")); 
			urlBuilder.append("/" + URLEncoder.encode("json","UTF-8"));
			urlBuilder.append("/" + URLEncoder.encode("TbPublicWifiInfo","UTF-8")); 
			urlBuilder.append("/" + URLEncoder.encode(String.valueOf(j), "UTF-8")); 
			urlBuilder.append("/" + URLEncoder.encode(String.valueOf(j + COUNT_LIMIT - 1),"UTF-8")); 
			
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
				String wifiMgrNo = row.getAsJsonObject().get("X_SWIFI_MGR_NO").getAsString();
				String wifiWrdofc = row.getAsJsonObject().get("X_SWIFI_WRDOFC").getAsString();
				String wifiMainNm = row.getAsJsonObject().get("X_SWIFI_MAIN_NM").getAsString(); 
				String wifiAdres1 = row.getAsJsonObject().get("X_SWIFI_ADRES1").getAsString();
				String wifiAdres2 = row.getAsJsonObject().get("X_SWIFI_ADRES2").getAsString();
				String wifiInstlFloor = row.getAsJsonObject().get("X_SWIFI_INSTL_FLOOR").getAsString();
				String wifiInstlTy = row.getAsJsonObject().get("X_SWIFI_INSTL_TY").getAsString();
				String wifiInstlMby = row.getAsJsonObject().get("X_SWIFI_INSTL_MBY").getAsString();
				String wifiSvcSe = row.getAsJsonObject().get("X_SWIFI_SVC_SE").getAsString();
				String wifiCmcwr = row.getAsJsonObject().get("X_SWIFI_CMCWR").getAsString();
				String wifiCnstcYear = row.getAsJsonObject().get("X_SWIFI_CNSTC_YEAR").getAsString();
				String wifiInoutDoor = row.getAsJsonObject().get("X_SWIFI_INOUT_DOOR").getAsString();
				String wifiRemars3 = row.getAsJsonObject().get("X_SWIFI_REMARS3").getAsString();
				Double lat = row.getAsJsonObject().get("LAT").getAsDouble();
				Double lnt = row.getAsJsonObject().get("LNT").getAsDouble();
				String workDttm = row.getAsJsonObject().get("WORK_DTTM").getAsString();
				
				WifiDto wifiInfo = new WifiDto();
				
				wifiInfo.setMGR_NO(wifiMgrNo);
				wifiInfo.setWRDOFC(wifiWrdofc);
				wifiInfo.setMAIN_NM(wifiMainNm);
				wifiInfo.setADRES1(wifiAdres1);
				wifiInfo.setADRES2(wifiAdres2);
				wifiInfo.setINSTL_FLOOR(wifiInstlFloor);
				wifiInfo.setINSTL_TY(wifiInstlTy);
				wifiInfo.setINSTL_MBY(wifiInstlMby);
				wifiInfo.setSVC_SE(wifiSvcSe);
				wifiInfo.setCMCWR(wifiCmcwr);
				wifiInfo.setCNSTC_YEAR(wifiCnstcYear);
				wifiInfo.setINOUT_DOOR(wifiInoutDoor);
				wifiInfo.setREMARS3(wifiRemars3);
				wifiInfo.setLAT(lat);
				wifiInfo.setLNT(lnt);
				wifiInfo.setWORK_DTTM(workDttm);
				
				ApiDB.apiInsert(wifiInfo);
				
			}
		}
	}
	
	public static int getListTotalCount() throws IOException {
	    
		URL url = new URL(SAMPLE_URL);
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