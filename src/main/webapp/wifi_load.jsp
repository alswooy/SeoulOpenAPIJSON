<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@page import="Api.ApiExplorer"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Open API 와이파이 정보 가져오기 실행 화면</title>
</head>
<body>
<%ApiExplorer.loadWifi(); %>
	<h2 align="center">
		<%=
		ApiExplorer.getListTotalCount()
		%>
		개의 WIFI 정보를 정상적으로 저장하였습니다.
		<br>
	
	<a class="home" href="http://localhost:8080/ZeroBase_Mission_01/">홈으로 가기</a>
	</h2>
</body>
</html>