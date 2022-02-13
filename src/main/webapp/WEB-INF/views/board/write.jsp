<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jstl/core_rt" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>writeBoard</title>
</head>
<body>
	<h2>공지사항 쓰기</h2>
	<form method="post" action="<c:url value="/board/write"/>" enctype="multipart/form-data">
		<table border="1">
		<tr>
			<th>제목</th>
			<td colspan="2"><input type="text" name="title"></td>
		</tr>
		<tr>
			<th>작성자</th>
			<td colspan="2">${sessionScope.user.id }</td>
			<input type="hidden" name="writer" value="${sessionScope.user.num }">
		</tr>
		<tr>
			<th>내용</th>
			<td colspan="2"><textarea name="content" cols="100" rows="20"></textarea></td>
		</tr>
		<tr>
			<th>첨부파일</th>
			<td><input type="file" name="file" /></td>
		</tr>
		<tr>
			<td colspan="2">
				<input type="submit" value="등록" />
				<input type="button" value="목록보기" onClick="location.href='<c:url value="/board/list"/>'">
			</td>
		</tr>
		</table>
	</form>
</body>
</html>