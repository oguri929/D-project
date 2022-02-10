<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
    
<!DOCTYPE html>
<html>
<head>
	<meta charset="UTF-8">
	<title>방만들기</title>
</head>
<body>
	<form action="<c:url value="/roomMake"/>" method="post">
		<table border="1">
			<tr>
				<th>방제목</th>
				<td><input type="text" name="roomname"></td>	
			</tr>
			
			<tr>
				<th>지역</th>
				<td><input type="text" name="local"></td>	
			</tr>
			
			<tr>
				<th>암호</th>
				<td><input type="password" name="password"></td>	
			</tr>
			
			<tr>
				<th>부가설명</th>
				<td><input type="text" name="roomdiscript"></td>	
			</tr>
			
			<tr>
				<th>멤버제한</th>
				<td><input type="text" name="memberlimit"></td>	
			</tr>
			
			<tr>
				<th>방장</th>
				<td><input type="text" name="captain"></td>	
			</tr>
			
			<tr>
				<th>과목코드</th>
				<td><input type="text" name="subjectnum"></td>	
			</tr>
		</table>
		
		<div>
			<input type="submit" value="개설하기">
			<input type="button" value="취소" onclick="location.href='<c:url value="/roomList"/>'">
		</div>
	</form>
</body>
</html>