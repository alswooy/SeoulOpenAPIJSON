<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@page import="java.util.ArrayList"%>
<%@page import="java.util.List"%>
<%@page import="Api.HistoryDto" %>
<%@page import="Api.ApiDB" %>
<!DOCTYPE html>
<html>
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
		<a class="link-a" href="http://localhost:8080/ZeroBase_Mission_01/">홈</a>|
		<a class="link-a" href="history.jsp">위치 히스토리 목록</a>|
		<a class="link-a" href="wifi_load.jsp">Open API 와이파이 정보 가져오기</a>
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
    		<%	
   			List<HistoryDto> historyList = ApiDB.getHistoryList();
			for (HistoryDto list : historyList) {
				
			%>
	        <td><%=list.getId() %></td>
	        <td><%=list.getLat() %></td>
	        <td><%=list.getLnt() %></td>
	        <td><%=list.getDate() %></td>
	        <td>
            <form action="delete.jsp">
                <input type="submit" value="삭제">
                <input type="hidden" name="idx" value="">
            </form>
        	</td>
    	</tr>
    	<%} %>
    </table>

</body>
</html>