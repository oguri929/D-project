<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jstl/core_rt" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>deleteBoard</title>
</head>
<body>
	<h2>공지사항 삭제</h2>
	<form action="<c:url value="/board/delete" />" method="post">
		<input size="1" name="num" value="${num }">번 게시글을 삭제하시겠습니까?
	<input type="submit" value="예">
	<a href="<c:url value="/board/read/${num}"/>">[취소]</a>
	</form>
</body>
</html>