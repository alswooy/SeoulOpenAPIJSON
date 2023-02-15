<%@ page language="java" contentType="text/html; charset=EUC-KR"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="kr">
<head>
	<meta charset="UTF-8">
	<title>와이파이 정보 구하기</title>
</head>
<body>

	<h1>와이파이 정보 구하기</h1>
	<div class="link-all">
		<a class="link-a" href="">홈</a>|
		<a class="link-a" href="">위치 히스토리 목록</a>|
		<a class="link-a" href="">Open API 와이파이 정보 가져오기</a>
	</div>
	<br>
	<form method="get" action="/load.jsp">
        LAT : <input type="text" id="location_lat" name="lat" value/>
        LNT : <input type="text" id="location_lnt" name="lnt" value/>
        <input type="button", value="내 위치 가져오기" onclick="on()"/>
        <input type="submit", value="근처 Wi-Fi 정보 보기"/>
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
    	<tr height="100%">
            <td colspan = "17" align="center">위치 정보를 입력한 후에 조회해 주세요.</td>
        </tr>
    </table>
	
<p></p>
</body>
</html>