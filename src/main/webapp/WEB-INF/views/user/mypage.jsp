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
	
	  <a href="${contextPath}/user/update.do">개인정보 수정</a>    
	</form>
</body>
</html>