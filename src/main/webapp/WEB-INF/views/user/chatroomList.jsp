<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>chatroomList</title>
</head>
<body>
	<h2>내가 가입한 채팅방 목록</h2>
	<table border="1">
		<tr>
			<th>방제목</th>
			<th>과목</th>
		</tr>
		<c:forEach var="chatroom" items="${chatroomList }">
		<tr>
			<td>
				<a href='<c:url value="/studyroom/read/${chatroom.chatroomNum }"/>'>
				${chatroom.roomName }</a>
			</td>
			<td>${chatroom.subject }</td>
		</tr>
		</c:forEach>
	</table><br>
	<button type="button" onclick="javascript:window.history.back();">이전</button>
</body>
</html>