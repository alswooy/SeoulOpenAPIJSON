
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.List;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;


import okhttp3.OkHttpClient;



public class ApIExplorer {

    public static void main(String[] args) throws IOException{

        // 1. URL을 만들기 위한 StringBuilder.
    	StringBuilder urlBuilder = new StringBuilder("http://openapi.seoul.go.kr:8088/434c545a586a6a613636494f775847/json/TbPublicWifiInfo/1/100/"); 
    	/*URL*/
    	urlBuilder.append("/" + URLEncoder.encode("sample","UTF-8") ); /*인증키
    	(sample사용시에는 호출시 제한됩니다.)*/
    	urlBuilder.append("/" + URLEncoder.encode("json","UTF-8") ); /*요청파일타입
    	(xml,xmlf,xls,json) */
    	urlBuilder.append("/" + URLEncoder.encode("CardSubwayStatsNew","UTF-8")); 
    	/*서비스명 (대소문자 구분 필수입니다.)*/
    	urlBuilder.append("/" + URLEncoder.encode("1","UTF-8")); /*요청시작위치
    	(sample인증키 사용시 5이내 숫자)*/
    	urlBuilder.append("/" + URLEncoder.encode("100","UTF-8")); 
    	/*요청종료위치(sample인증키 사용시 5이상 숫자 선택 안 됨)*/
    	// 상위 5개는 필수적으로 순서바꾸지 않고 호출해야 합니다.
    	// 서비스별 추가 요청 인자이며 자세한 내용은 각 서비스별 '요청인자'부분에
    	urlBuilder.append("/" + URLEncoder.encode("20220301","UTF-8")); 

        // 3. URL 객체 생성 (toString으로 string으로 변환)
        URL url = new URL(urlBuilder.toString());

        // 4. 요청하고자 하는 URL과 통신하기 위한 Connection 객체 생성.
        HttpURLConnection conn = (HttpURLConnection)url.openConnection();
        OkHttpClient client = new OkHttpClient();

        // 5. 통신을 위한 메소드 SET (Get 요청)
        conn.setRequestMethod("GET");

        // 6. 통신을 위한 Content-type SET. (json으로 설정해야됨)
        conn.setRequestProperty("Content-tupe", "application/json");

        // 7. 통신 응답 코드 확인.
        System.out.println("Response Code " + conn.getResponseCode());

        // 8. 전달받은 데이터를 BufferedReader 객체로 저장. 오류가 날 경우 error 발생
        BufferedReader rd;
        if (conn.getResponseCode() >= 200 && conn.getResponseCode() <= 300){
            rd = new BufferedReader(new InputStreamReader(conn.getInputStream()));
        } else {
            rd = new BufferedReader(new InputStreamReader((conn.getErrorStream())));
        }

        // 9. 저장된 데이터를 라인별로 읽어 StringBuilder 객체로 저장.
        StringBuilder sb = new StringBuilder();
        String line;
        while ((line = rd.readLine()) != null) {
            sb.append(line);
        }

        // 문자열로 바꾸기
        String result = sb.toString();
        
        
        
        // 10. 객체 해제
        rd.close();
        conn.disconnect();


        try{
            // 1. 문자열 형태의 JSON을 파싱하기 위한 JSONParser 객체 생성.
            JSONParser parser = new JSONParser();
            // 2. 문자열을 JSON 형태로 JSONObject 객체에 저장.
            JSONObject obj = (JSONObject)parser.parse(result);
            
            JSONObject responseResult = (JSONObject)obj.get("TbPublicWifiInfo");
            JSONArray bodyResult = (JSONArray)responseResult.get("row");
            System.out.println(responseResult);
            System.out.println(bodyResult);
            
            System.out.println(bodyResult.size()); //개수
            if (bodyResult.size() > 0){
                for(int i=0; i<bodyResult.size(); i++){
                    JSONObject jsonObj = (JSONObject)bodyResult.get(i);
                    
                    apidto api= new apidto();
                    List<apidto> dtos = new ArrayList<apidto>();
                    
                    
                    String MGR_NO=(String)jsonObj.get("X_SWIFI_MGR_NO");
//                    System.out.println("관리번호 : "+MGR_NO);
                    api.setMGR_NO(MGR_NO);
                    
                    
                    String WRDOFC=(String)jsonObj.get("X_SWIFI_WRDOFC");
//                    System.out.println("자치구 : "+WRDOFC);
                    api.setWRDOFC(WRDOFC);
                    
                    String MAIN_NM=(String)jsonObj.get("X_SWIFI_MAIN_NM");
//                    System.out.println("와이파이명 : "+MAIN_NM);
                    api.setMAIN_NM(MAIN_NM);
                    
                    String ADRES1=(String)jsonObj.get("X_SWIFI_ADRES1");
//                    System.out.println("도로명주소 : "+ADRES1);
                    api.setADRES1(ADRES1);
                    
                    String ADRES2=(String)jsonObj.get("X_SWIFI_ADRES2");
//                    System.out.println("상세주소 : "+ADRES2); 
                    api.setADRES2(ADRES2);
                    
                    String INSTL_FLOOR=(String)jsonObj.get("X_SWIFI_INSTL_FLOOR");
//                    System.out.println("설치위치(층) : "+INSTL_FLOOR); 
                    api.setINSTL_FLOOR(INSTL_FLOOR);
                    
                    String INSTL_TY=(String)jsonObj.get("X_SWIFI_INSTL_TY");
//                    System.out.println("설치유형 : "+INSTL_TY); 
                    api.setINSTL_TY(INSTL_TY);
                    
                    String INSTL_MBY=(String)jsonObj.get("X_SWIFI_INSTL_MBY");
//                    System.out.println("설치기관 : "+INSTL_MBY); 
                    api.setINSTL_MBY(INSTL_MBY);
                    
                    String SVC_SE=(String)jsonObj.get("X_SWIFI_SVC_SE");
//                    System.out.println("서비스구분 : "+SVC_SE);
                    api.setSVC_SE(SVC_SE);
                    
                    String CMCWR=(String)jsonObj.get("X_SWIFI_CMCWR");
//                    System.out.println("망종류 : "+CMCWR); 
                    api.setCMCWR(CMCWR);
                    
                    String CNSTC_YEAR=(String)jsonObj.get("X_SWIFI_CNSTC_YEAR");
//                    System.out.println("설치년도 : "+CNSTC_YEAR);
                    api.setCNSTC_YEAR(CNSTC_YEAR);
                    
                    String INOUT_DOOR=(String)jsonObj.get("X_SWIFI_INOUT_DOOR");
//                    System.out.println("실내외구분 : "+INOUT_DOOR); 
                    api.setINOUT_DOOR(INOUT_DOOR);
                    
                    String REMARS3=(String)jsonObj.get("X_SWIFI_REMARS3");
//                    System.out.println("WIFI접속환경 : "+REMARS3); 
                    api.setREMARS3(REMARS3);
                    
                    String LNT=(String)jsonObj.get("LNT");
//                    System.out.println("X좌표 : "+LNT); 
                    api.setLNT(LNT);
                    
                    String LAT=(String)jsonObj.get("LAT");
//                    System.out.println("Y좌표 : "+LAT);
                    api.setLAT(LAT);
                    
                    String WORK_DTTM=(String)jsonObj.get("WORK_DTTM");
//                    System.out.println("작업일자 : "+WORK_DTTM+"\n"); 
                    api.setWORK_DTTM(WORK_DTTM);
                    dtos.add(api);
                    
                    for(int v = 0;v<dtos.size();v++) {
                    	System.out.println(dtos.get(v).getLAT());
                    	System.out.println(dtos.get(v).getLNT());
                    }
                    
                    
                    
                }
                
            }
        } catch (ParseException e) {
        	System.out.println(e.getMessage());
        }
    }
}
