<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>editStudyroom</title>
</head>
<body>
	<form method="post" action="<c:url value="/studyroom/edit/${studyroomDto.num}"/>" >
		<table border="1">
		<tr>
			<th>방번호</th>
			<td colspan="4">${studyroomDto.num }</td>
		</tr>
		<tr>
			<th>스터디이름</th>
			<td colspan="4"><input type="text" name="roomName" value="${studyroomDto.roomName }"></td>
		</tr>
		<tr>
			<th>제한인원</th>
			<td><input type="number" name="memberLimit" value="${studyroomDto.memberLimit }"></td>
			<th>채팅방 비밀번호</th>
			<td><input type="password" name="pw" value="${studyroomDto.pw }"></td>
		</tr>
		<tr>
			<th>방장</th>
			<td colspan="4">${studyroomDto.captain }</td>
		</tr>
		<tr>
			<th>지역</th>
			<td colspan="4"><input type="text" name="local" value="${studyroomDto.local }"></td>
		</tr>
		<tr>
			<th>스터디정보</th>
			<td colspan="4"><textarea name="roomDiscript" cols="100" rows="20">${studyroomDto.roomDiscript }</textarea></td>
		</tr>
		<tr>
			<th>과목정보</th>
			<td colspan="3">
				<c:forEach var="sub" items="${subjectList }">
					<input type="radio" name="subject" value="${sub.num}">${sub.subject}				
				</c:forEach>
			</td>
		</tr>
		<tr>
			<th>조회수</th>
			<td colspan="4">${studyroomDto.cnt }</td>
		</tr>
		<tr>
			<th>등록일</th>
			<td colspan="4">${studyroomDto.regdate }</td>
		</tr>
		</table>
		<input type="submit" value="수정완료">
		<input type="reset" value="다시작성">
		<input type="button" value="목록보기" onClick="location.href='<c:url value="/studyroom/list"/>'">
	</form>

</body>
</html>