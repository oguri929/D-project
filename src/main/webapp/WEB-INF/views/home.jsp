<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page session="false" %>

<html>
<head>
	<title>Home</title>
</head>
<body>
<h1>
	메인 게시판
</h1>
<c:if test="${!empty user}">
	<p> ${user.id} 환영합니다
	<br>
</c:if>
<c:choose>
	<c:when test="${!empty user}">
	<a href="<c:url value='/logout.do'/> ">로그아웃</a>
	<br>
	<br>
<a href="<c:url value='/board/list'/> ">공지사항 게시판 바로 가기</a>
<br>
<a href="<c:url value='/studyroom/list'/> ">매칭 게시판 바로 가기</a><br>
<br>
<a href="<c:url value='/makeQuiz'/> ">퀴즈 만들기 바로 가기</a><br>
<br>
<a href="<c:url value='/solveQuiz'/> ">퀴즈 풀기 바로 가기</a><br>
<a href="<c:url value='/listQuiz'/> ">퀴즈 리스트 바로 가기</a><br>
	</c:when>
	<c:otherwise>
	<a href="<c:url value='/login.do'/> ">로그인</a>
	<br>
	</c:otherwise>
</c:choose>



</body>
</html>
