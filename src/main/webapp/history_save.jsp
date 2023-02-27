<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@page import="Api.HistoryDto"%>
<%@page import="Api.ApiDB"%>
<%@page import="java.time.LocalDateTime"%>
<%@page import="java.time.format.DateTimeFormatter"%>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>history_save</title>
</head>

<body>
	<%
		String lat = request.getParameter("lat");
	
		String lnt = request.getParameter("lnt"); 
		
		LocalDateTime now = LocalDateTime.now();
		String date = now.toString().substring(0, 19);
			
	 	if (lat != null && lnt != null) {
			HistoryDto dto = new HistoryDto();
			dto.setLat(Double.valueOf(lat));
			dto.setLnt(Double.valueOf(lnt)); 
			dto.setDate(date);
			ApiDB.HistoryInsert(dto);	
		}  
	%>
	<script> 
		location.href="http://localhost:8080/ZeroBase_Mission_01/?lat=<%=lat%>&lnt=<%=lnt%>"
	</script> 

</body>
</html>