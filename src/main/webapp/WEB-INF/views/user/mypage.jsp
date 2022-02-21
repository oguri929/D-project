<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<c:set var="contextPath"  value="${pageContext.request.contextPath}"  />
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>마이페이지 화면</title>
</head>
<body>
	<H3>마이페이지</H3>
	<form action="${contextPath}/mypage.do" method="post">
	
	  <a href="${contextPath}/user/update.do">개인정보 수정</a><br>
	  <a href="${contextPath}/user/getStudyrooms">가입한 채팅 목록 가져오기</a>
	  <a href="${contextPath}/home">메인 페이지</a> 

	</form>
</body>
</html>