<%@ page language="java" contentType="text/html; charset=EUC-KR"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="kr">
<head>
<style>
  table, th, td {
    border: 1px solid #bcbcbc;
    text-align: center;
    
  }
  table {
    width: 100%;
    
  }
</style>
	<meta charset="UTF-8">
	<title>위치 히스토리 목록</title>
</head>
<body>

	<h1>위치 히스토리 목록</h1>
	<div class="link-all">
		<a class="link-a" href="index.jsp">홈</a>|
		<a class="link-a" href="history.jsp">위치 히스토리 목록</a>|
		<a class="link-a" href="load-wifi.jsp">Open API 와이파이 정보 가져오기</a>
	</div>
	<br>
    <table border="1">
    	<tr>
    		<td>ID</td>
    		<td>X좌표</td>
    		<td>Y좌표</td>
    		<td>조회일자</td>
    		<td>비고</td>
    	</tr>
    	<tr>
	        <td>내용</td>
	        <td>내용</td>
	        <td>내용</td>
	        <td>내용</td>
	        <td>
            <form action="delete.jsp">
                <input type="submit" value="삭제">
                <input type="hidden" name="idx" value="">
            </form>
        </td>
    </tr>
    </table>
	
<p></p>
</body>
</html>