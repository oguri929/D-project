<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
	<!-- Required meta tags -->
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1">

    <!-- Bootstrap CSS -->
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-1BmE4kWBq78iYhFldvKuhfTAU6auU8tT94WrHftjDbrCEXSU1oBoqyl2QvZ6jIW3" crossorigin="anonymous">
	<script src="//cdnjs.cloudflare.com/ajax/libs/jquery/3.2.1/jquery.min.js"></script>
	<title>myStudyroomList</title>
	<style type="text/css">
		body {
		  padding-top: 5rem;
		  padding-bottom: 3rem;
		  color: #5a5a5a;
		}
		
		.table {
	    display: table;
	    width: 100%;
		max-width: 1000px;
		padding: 15px;
		margin: auto;
		}
	</style>

</head>
<body>
	<h2 class="text-center">나의 스터디룸</h2>
	<table class="table table-bordered">
		<tr>
			<th scope="row" class="w-25 p-3">방제목</th>
			<th scope="row" class="w-25 p-3">과목</th>
			<th scope="row" class="w-25 p-3">채팅방 바로가기</th>
			<th scope="row" class="w-25 p-3">탈퇴</th>
		</tr>
		<c:forEach var="chatroom" items="${chatroomList }">
		<tr>
			<td>
				<a href='<c:url value="/studyroom/read/${chatroom.chatroomNum }"/>'>
				${chatroom.roomName }</a>
			</td>
			<td>${chatroom.subject }</td>
			<td>
				<input type="button" value="채팅방 들어가기" class="btn-primary me-md-2" onclick="location.href='<c:url value="/chat?chatroomNum=${chatroom.chatroomNum }"/>'">
			</td>				
			<td>
				<input type="button" value="스터디탈퇴" class="btn-primary me-md-2" onclick="location.href='<c:url value="/studyroom/leave?memberNum=${sessionScope.user.num }&chatroomNum=${chatroom.chatroomNum }"/>'">
			</td>
		</tr>
		</c:forEach>
		<tr class="col text-center">
			<td colspan="4">
				<button type="button" onclick="javascript:window.history.back();" class="btn-primary me-md-2">이전</button>
			</td>			
		</tr>
	</table><br>
</body>
</html>