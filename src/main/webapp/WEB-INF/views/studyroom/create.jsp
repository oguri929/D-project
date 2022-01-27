<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jstl/core_rt" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>createStudyroom</title>
</head>
<body>
	<form method="post" action="<c:url value="/studyroom/create"/>">
		<table border="1">
		<tr>
			<th>스터디이름</th>
			<td colspan="4"><input type="text" name="roomName"></td>
		</tr>
		<tr>
			<th>제한인원</th>
			<td><input type="number" name="memberLimit"></td>
			<th>채팅방 비밀번호</th>
			<td><input type="password" name="pw"></td>
		</tr>
		<tr>
			<th>방장</th>
			<td colspan="4"><input type="text" name="captain"></td>
		</tr>
		<tr>
			<th>지역</th>
			<td colspan="4"><input type="text" name="local"></td>
		</tr>
		<tr>
			<th>스터디정보</th>
			<td colspan="4"><textarea name="roomDiscript" cols="100" rows="20"></textarea></td>
		</tr>
		<tr>
			<th>과목정보</th>
			<td colspan="3">
				<c:forEach var="sub" items="${subjectList}">
					<input type="checkbox" name="subject" value="${sub.subject}">${sub.subject}
				</c:forEach>
			</td>
		</tr>
		<tr>
			<td colspan="4">
				<input type="submit" value="등록" />
				<input type="button" value="목록보기" onClick="location.href='<c:url value="/studyroom/list"/>'">
			</td>
		</tr>
		</table>
	</form>
</body>
</html>