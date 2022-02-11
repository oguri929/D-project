<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jstl/core_rt" %>
    
<!DOCTYPE html>
<html>
<head>
	<meta charset="UTF-8">
	<title>방만들기</title>
</head>
<body>
	<form method="post" action="<c:url value="/roomMake"/>">
		<table border="1"> 
			<tr>
				<th>스터디이름</th>
				<td colspan="4"><input type="text" name="roomname"></td>
			</tr>
			
			<tr>
				<th>제한인원</th>
				<td><input type="number" name="memberlimit"></td>
			</tr>
			
			<tr>	
				<th>비밀번호</th>
				<td><input type="password" name="password"></td>
			</tr>
			
			<tr>
				<th>방장</th>
				<td colspan="4">
					<input type="number" name="captain"/>
				</td>			
			</tr>
		<tr>
			<th>지역</th>
			<td colspan="4"><input type="text" name="local"></td>
		</tr>
		
		<tr>
			<th>스터디정보</th>
			<td colspan="4"><textarea name="roomdiscript" cols="100" rows="20"></textarea></td>
		</tr>
		
		<tr>
			<th>과목정보</th>
			<td colspan="3">
				<input type="text" name="subjectnum"/>
			</td>
		</tr>
		
		<tr style="text-align:center">
			<td colspan="4">
				<input type="submit" value="등록" />
				<input type="button" value="취소" onClick="location.href='<c:url value="/roomList"/>'"
				style="margin-left:20px;">
			</td>
		</tr>
		</table>
	</form>
</body>
</html>