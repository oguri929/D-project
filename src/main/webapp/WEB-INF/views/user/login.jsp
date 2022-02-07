<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"
	 isELIgnored="false" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<c:set var="contextPath"  value="${pageContext.request.contextPath}"  />
<!DOCTYPE html >
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
</head>
<body>
	<H3>회원 로그인</H3>
	<form action="${contextPath}/user/logincheck.do" method="post">
		<table>
			<tr>
				<th>아이디</th>
				<td><input name="id" type="text" size="20" /></td>
			</tr>
			<tr>
				<th>비밀번호</th>
				<td><input name="pw" type="password" size="20" /></td>
			</tr>
		</table>
		<br><br>
		<input type="submit" value="로그인"> 
		
		<br><br>
		   <a href="#">아이디 찾기</a>  | 
		   <a href="#">비밀번호 찾기</a> | 
		   <a href="${contextPath}/user/register.do">회원가입</a>    | 
		   <a href="${contextPath}/user/update.do">마이페이지</a>
					   
	</form>	

</body>
</html>