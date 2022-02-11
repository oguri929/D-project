<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page session="false" %>
<html>
<head>
	<meta http-equiv = "Content-type" content="text/html; charset=UTF-8">
	<title>개설된 방 목록</title>
	
</head>
<body>
	<div>
		<a href=<c:url value="/roomMake"/>>방만들기</a>
	</div>
	
	<table border="1">
		<tr>
			<th>NO.</th>
			<th>방제</th>
			<th>지역</th>
			<th>설명</th>
			<th>제한인원</th>
		</tr>
		
		<c:forEach var="room" items="${roomList}" varStatus="loop">
			<tr>
				<td>${room.code}</td>
				<td><a href="<c:url value="/chat?roomname=${room.roomname}"/>">${room.roomname}</a></td>
				<td>${room.local}</td>
				<td>${room.roomdiscript}</td>
				<td>${room.memberlimit}</td>
			</tr>
		</c:forEach>
	</table>
</body>
</html>
