
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import okhttp3.MediaType;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;
import okhttp3.ResponseBody;

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
        System.out.println("전체 response = " + result);

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
            
            System.out.println(bodyResult.size());
            if (bodyResult.size() > 0){
                for(int i=0; i<bodyResult.size(); i++){
                    JSONObject jsonObj = (JSONObject)bodyResult.get(i);
                    String MGR_NO=(String)jsonObj.get("X_SWIFI_MGR_NO");
                    System.out.println("관리번호 : "+MGR_NO); 
                }
            }
            if (bodyResult.size() > 0){
                for(int i=0; i<bodyResult.size(); i++){
                    JSONObject jsonObj = (JSONObject)bodyResult.get(i);
                    String WRDOFC=(String)jsonObj.get("X_SWIFI_WRDOFC");
                    System.out.println("자치구 : "+WRDOFC); 
                }
            }
            if (bodyResult.size() > 0){
                for(int i=0; i<bodyResult.size(); i++){
                    JSONObject jsonObj = (JSONObject)bodyResult.get(i);
                    String MAIN_NM=(String)jsonObj.get("X_SWIFI_MAIN_NM");
                    System.out.println("와이파이명 : "+MAIN_NM); 
                }
            }
            if (bodyResult.size() > 0){
                for(int i=0; i<bodyResult.size(); i++){
                    JSONObject jsonObj = (JSONObject)bodyResult.get(i);
                    String ADRES1=(String)jsonObj.get("X_SWIFI_ADRES1");
                    System.out.println("도로명주소 : "+ADRES1); 
                }
            }
            if (bodyResult.size() > 0){
                for(int i=0; i<bodyResult.size(); i++){
                    JSONObject jsonObj = (JSONObject)bodyResult.get(i);
                    String ADRES2=(String)jsonObj.get("X_SWIFI_ADRES2");
                    System.out.println("상세주소 : "+ADRES2); 
                }
            }
            if (bodyResult.size() > 0){
                for(int i=0; i<bodyResult.size(); i++){
                    JSONObject jsonObj = (JSONObject)bodyResult.get(i);
                    String INSTL_FLOOR=(String)jsonObj.get("X_SWIFI_INSTL_FLOOR");
                    System.out.println("설치위치(층) : "+INSTL_FLOOR); 
                }
            }
            if (bodyResult.size() > 0){
                for(int i=0; i<bodyResult.size(); i++){
                    JSONObject jsonObj = (JSONObject)bodyResult.get(i);
                    String INSTL_TY=(String)jsonObj.get("X_SWIFI_INSTL_TY");
                    System.out.println("설치유형 : "+INSTL_TY); 
                }
            }
            if (bodyResult.size() > 0){
                for(int i=0; i<bodyResult.size(); i++){
                    JSONObject jsonObj = (JSONObject)bodyResult.get(i);
                    String INSTL_MBY=(String)jsonObj.get("X_SWIFI_INSTL_MBY");
                    System.out.println("설치기관 : "+INSTL_MBY); 
                }
            }
            if (bodyResult.size() > 0){
                for(int i=0; i<bodyResult.size(); i++){
                    JSONObject jsonObj = (JSONObject)bodyResult.get(i);
                    String SVC_SE=(String)jsonObj.get("X_SWIFI_SVC_SE");
                    System.out.println("서비스구분 : "+SVC_SE); 
                }
            }
            if (bodyResult.size() > 0){
                for(int i=0; i<bodyResult.size(); i++){
                    JSONObject jsonObj = (JSONObject)bodyResult.get(i);
                    String CMCWR=(String)jsonObj.get("X_SWIFI_CMCWR");
                    System.out.println("망종류 : "+CMCWR); 
                }
            }
            if (bodyResult.size() > 0){
                for(int i=0; i<bodyResult.size(); i++){
                    JSONObject jsonObj = (JSONObject)bodyResult.get(i);
                    String CNSTC_YEAR=(String)jsonObj.get("X_SWIFI_CNSTC_YEAR");
                    System.out.println("설치년도 : "+CNSTC_YEAR); 
                }
            }
            if (bodyResult.size() > 0){
                for(int i=0; i<bodyResult.size(); i++){
                    JSONObject jsonObj = (JSONObject)bodyResult.get(i);
                    String INOUT_DOOR=(String)jsonObj.get("X_SWIFI_INOUT_DOOR");
                    System.out.println("실내외구분 : "+INOUT_DOOR); 
                }
            }

            if (bodyResult.size() > 0){
                for(int i=0; i<bodyResult.size(); i++){
                    JSONObject jsonObj = (JSONObject)bodyResult.get(i);
                    String REMARS3=(String)jsonObj.get("X_SWIFI_REMARS3");
                    System.out.println("WIFI접속환경 : "+REMARS3); 
                }
            }
            
            if (bodyResult.size() > 0){
                for(int i=0; i<bodyResult.size(); i++){
                    JSONObject jsonObj = (JSONObject)bodyResult.get(i);
                    String LNT=(String)jsonObj.get("LNT");
                    System.out.println("X좌표 : "+LNT); 
                }
            }
            if (bodyResult.size() > 0){
                for(int i=0; i<bodyResult.size(); i++){
                    JSONObject jsonObj = (JSONObject)bodyResult.get(i);
                    String LAT=(String)jsonObj.get("LAT");
                    System.out.println("Y좌표 : "+LAT); 
                }
            }
            if (bodyResult.size() > 0){  //작업일자
                for(int i=0; i<bodyResult.size(); i++){
                    JSONObject jsonObj = (JSONObject)bodyResult.get(i);
                    String WORK_DTTM=(String)jsonObj.get("WORK_DTTM");
                    System.out.println("작업일자 : "+WORK_DTTM); 
                    
                }
                
            }
            
            
           
        } catch (ParseException e) {
        	System.out.println(e.getMessage());
        }
        
        


    }



}
