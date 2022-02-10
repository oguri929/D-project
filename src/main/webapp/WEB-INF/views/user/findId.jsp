<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<c:set var="contextPath"  value="${pageContext.request.contextPath}"  />
<!DOCTYPE html>
<html>
<head>
	<title>ID 찾기</title>
</head>
<body>
<h1>
	ID 찾기
</h1>
<form action="${contextPath}/user/findId.do" method="post">

	<p>
		<label for="id">아이디</label>
		<input type="text" id="id" name="id"/>
	</P>
	
	<p>
		<label for="name">이름</label>
		<input type="text" id="name" name="name"/>
	</P>
	<p>
		<label for="birth">생일</label>
		<input type="text" id="birth" name="birth" placeholder="0000-00-00"/>
	</P>
	<p>
		<label for="email">이메일</label>
		<input type="text" id="email" name="email"/>
	</P>
		
	<button type="submit">id 찾기</button>
	<p><a href="../login.do">로그인하러가기</a></p>
</form>
</body>
</html>