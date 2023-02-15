<%@ page language="java" contentType="text/html; charset=EUC-KR"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="kr">
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
		<a class="link-a" href="">홈</a>|
		<a class="link-a" href="">위치 히스토리 목록</a>|
		<a class="link-a" href="">Open API 와이파이 정보 가져오기</a>|
		<a class="link-a" href="">북마크 보기</a>|
		<a class="link-a" href="">북마크 그룹 관리</a>
	</div>
	<br>
	<div>
	<select name="bookmark-select">
	    <option value="" selected="selected">북마크 그룹 이름 선택</option>
	    <option value="내 집 근처">내 집 근처</option>
	    <option value="내 회사 근처">내 회사 근처</option>
	    <option value="자주가는 카페 근처">자주가는 카페 근처</option>
	    <form method="get" action="/bookmark-add.jsp">
        
	        <input type="submit" value="북마크 추가하기">
	        <input type="hidden" name="idx" value="">
    	</form>
	</select>
	
    </div>
    <table border="1">
    	<tr>
    		<td>거리(Km)</td>
    		<td>내용</td>
    	</tr>
    	<tr>
    		<td>관리번호</td>
    		<td>내용</td>
    	</tr>
    	<tr>
    		<td>자치구</td>
    		<td>내용</td>
    	</tr>
    	<tr>
    		<td>와이파이명</td>
    		<td>내용</td>
    	</tr>
    	<tr>
    		<td>도로명주소</td>
    		<td>내용</td>
    	</tr>
    	<tr>
    		<td>상세주소</td>
    		<td>내용</td>
    	</tr>
    	<tr>
    		<td>설치위치(층)</td>
    		<td>내용</td>
    	</tr>
    	<tr>
    		<td>설치유형</td>
    		<td>내용</td>
    	</tr>
    	<tr>
    		<td>설치기관</td>
    		<td>내용</td>
    	</tr>
    	<tr>
    		<td>서비스구분</td>
    		<td>내용</td>
    	</tr>
    	<tr>
    		<td>망종류</td>
    		<td>내용</td>
    	</tr>
    	<tr>
    		<td>설치년도</td>
    		<td>내용</td>
    	</tr>
    	<tr>
    		<td>실내외구분</td>
    		<td>내용</td>
    	</tr>
    	<tr>
    		<td>WIFI접속환경</td>
    		<td>내용</td>
    	</tr>
    	<tr>
    		<td>X좌표</td>
    		<td>내용</td>
    	</tr>
    	<tr>
    		<td>Y좌표</td>
    		<td>내용</td>
    	</tr>
    	<tr>
    		<td>작업일자</td>
    		<td>내용</td>
    	</tr>
    </table>
	
<p></p>
</body>
</html>