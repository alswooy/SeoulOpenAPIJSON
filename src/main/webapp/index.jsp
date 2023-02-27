<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@page import="Api.HistoryDto"%>
<%@page import="Api.WifiDto"%>
<%@page import="Api.ApiDB"%>
<%@page import="java.util.List"%>

<!DOCTYPE html>
<html>

<script>
	function here(){
		navigator.geolocation.getCurrentPosition(function(pos) {
		    console.log(pos);
		    var latitude = pos.coords.latitude;
		    var longitude = pos.coords.longitude;
		    latitude= latitude
		    document.getElementById("lat").value=latitude;
		    document.getElementById("lnt").value=longitude;
		    
		});
	}
	
</script>
<head>

	<meta charset="UTF-8">
	<title>와이파이 정보 구하기</title>
	<style>
	  table, th, td {
	    border: 1px solid #bcbcbc;
	    text-align: center;
	    
	  }
	  table {
	    width: 100%;
	    
	  }
	</style>
	
	
</head>
<body>
	
	<h1>와이파이 정보 구하기</h1>
	<div class="link-all">
		<a class="link-a" href="index.jsp">홈</a>|
		<a class="link-a" href="history.jsp">위치 히스토리 목록</a>|
		<a class="link-a" href="wifi_load.jsp">Open API 와이파이 정보 가져오기</a>
		
	</div>
	<br>
	
	<%
	String lat = request.getParameter("lat");
	System.out.println(lat);
	String lnt = request.getParameter("lnt"); 
	System.out.println(lnt);
	%>
	<form method="get" action="/ZeroBase_Mission_01/history_save.jsp">
        LAT : <input type="text" id="lat" name="lat" value="<%=lat != null ? lat : "0.0"%>">,
        LNT : <input type="text" id="lnt" name="lnt" value="<%=lnt != null ? lnt : "0.0"%>">
        
        <input type="button"  value="내 위치 가져오기" onclick="here()"/>
        <input type="submit" value="근처 Wi-Fi 정보 보기"/>
    </form>
    <table border="1"> 
    	<tr>
    		<td>거리(Km)</td>
    		<td>관리번호</td>
    		<td>자치구</td>
    		<td>와이파이명</td>
    		<td>도로명주소</td>
    		<td>상세주소</td>
    		<td>설치위치(층)</td>
    		<td>설치유형</td>
    		<td>설치기관</td>
    		<td>서비스구분</td>
    		<td>망종류</td>
    		<td>설치년도</td>
    		<td>실내외구분</td>
    		<td>WIFI접속환경</td>
    		<td>X좌표</td>
    		<td>Y좌표</td>
    		<td>작업일자</td>
    	</tr>
    	<%
    	 if (lat != null && lnt != null) {
   	    		List<WifiDto> list = ApiDB.getApiList();
   	    		
   	    		for(WifiDto dto : list){
   	    			
    	    			
    	%>
    	<tr>	
    		<td>
    			<%=String.format("%.4f",dto.getDistance()) %>
    		</td>
    		<td>
    			<%=dto.getMGR_NO() %>
    		</td>
    		<td>
    			<%=dto.getWRDOFC() %>
    		</td>
    		<td>
    			<%=dto.getMAIN_NM() %>
    		</td>
    		<td>
    			<%=dto.getADRES1() %>
    		</td>
    		<td>
    			<%=dto.getADRES2() %>
    		</td>
    		<td>
    			<%=dto.getINSTL_FLOOR() %>
    		</td>
    		<td>
    			<%=dto.getINSTL_TY() %>
    		</td>
    		<td>
    			<%=dto.getINSTL_MBY() %>
    		</td>
    		<td>
    			<%=dto.getSVC_SE() %>
    		</td>
    		<td>
    			<%=dto.getCMCWR() %>
    		</td>
    		<td>
    			<%=dto.getCNSTC_YEAR() %>
    		</td>
    		<td>
    			<%=dto.getINOUT_DOOR() %>
    		</td>
    		<td>
    			<%=dto.getREMARS3() %>
    		</td>
    		<td>
    			<%=dto.getLNT() %>
    		</td>
    		<td>
    			<%=dto.getLAT() %>
    		</td>
    		<td>
    			<%=dto.getWORK_DTTM() %>
    		</td>
    		
    	
    	
    	</tr>
    	<%
    		}
    	 }else{ %>
    		<tr height="100%">
        	
        	
            <td colspan = "17" align="center">위치 정보를 입력한 후에 조회해 주세요.</td>
        </tr>
    	<%	
    	}
	%>
    </table>
	
<p></p>
</body>
</html>