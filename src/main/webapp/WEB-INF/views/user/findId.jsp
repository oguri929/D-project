<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<c:set var="contextPath"  value="${pageContext.request.contextPath}"  />
<!DOCTYPE html>
<html>
<head>
	<title>회원가입 하기</title>
</head>
<body>
<h1>
	회원가입
</h1>
<form action="${contextPath}/user/registerCheck.do" method="post">

	<p>
		<label for="id">아이디</label>
		<input type="text" id="id" name="id"/>
	</P>
	<p>
		<label for="pw">비밀번호</label>
		<input type="password" id="pw" name="pw"/>
	</P>
	<p>
		<label for="name">이름</label>
		<input type="text" id="name" name="name"/>
	</P>
	<p>
		<label for="nickname">닉네임</label>
		<input type="text" id="nickname" name="nickname"/>
	</P>	
	<p>
		<label for="gender">성별</label>
		<input type="radio" name="gender" value="102" />
		여성<span style="padding-left:120px"></span>
		<input type="radio" name="gender" value="102" checked />남성
	</p>
	<p>
		<label for="local">지역</label>
		<input type="text" id="local" name="local"/>
	</P>	
	<p>
		<label for="birth">생일</label>
		<input type="text" id="birth" name="birth" placeholder="0000-00-00"/>
	</P>
	<p>
		<label for="email">이메일</label>
		<input type="text" id="email" name="email"/>
	</P>
		
	<button type="submit">회원가입하기</button>
	<p><a href="../login.do">로그인하러가기</a></p>
</form>
</body>
</html>