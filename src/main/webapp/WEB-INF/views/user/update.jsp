<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<c:set var="contextPath"  value="${pageContext.request.contextPath}"  />
<!DOCTYPE html>
<html>
<head>
	<title>회원정보 수정하기</title>
</head>
<body>
<h1>
	회원정보 수정
</h1>



<form action="${contextPath}/user/update.do" method="post">

	<p>
		<label for="id">아이디 : ${user.id }</label>
		<input type="hidden" name="id" value="${user.id }">
	</P>
	<p>
		<label for="name">이름</label>
		<input type="text" id="name" name="name" value="${user.name }" />
	</P>
	<p>
		<label for="pw">*비밀번호</label>
		<input type="password" id="pw" name="pw"/>
	</P>
	<p>
		<label for="nickname">닉네임</label>
		<input type="text" id="nickname" name="nickname" value="${user.nickname }"/>
	</P>	
	<p>
		<label for="gender">성별</label>
		<c:choose>
		<c:when test="${user.gender eq 'female' }">
		<input type="radio" name="gender" value="female" checked />여성
		<input type="radio" name="gender" value="male"/>남성	
		</c:when>
		<c:otherwise>
		<input type="radio" name="gender" value="female"/>여성
		<input type="radio" name="gender" value="male" checked />남성	
		</c:otherwise>
		</c:choose>
		
	</p>
	<p>
		<label for="local">*지역</label>
		<input type="text" id="local" name="local" value="${user.local }"/>
	</P>	
	<p>
		<label for="birth">생년월일</label>
		<input type="text" id="birth" name="birth" value="${user.birth }" />
	</P>
	<p>
		<label for="email">이메일</label>
		<input type="text" id="email" name="email" value="${user.email }"/>
	</P>
		
	<button type="submit">수정하기</button>
	<p><a href="../login.do">취소</a></p>
	
	

	
</form>
</body>
</html>