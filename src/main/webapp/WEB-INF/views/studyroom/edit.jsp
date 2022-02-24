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
	<title>editStudyroom</title>
	<style type="text/css">
		body {
		  padding-top: 5rem;
		  padding-bottom: 3rem;
		  color: #5a5a5a;
		}
		
		.table{
		  width: 100%;
		  max-width: 1000px;
		  padding: 15px;
		  margin: auto;
		}
	</style>
</head>
<script type="text/javascript">
	$(document).ready(function(){
		
		$(".list_btn").on("click", function(){
			event.preventDefault();
			location.href = "/studyroom/list";
		})
	})
</script>
<body>
<header>
<!-- Navigation-->
	<nav class="navbar navbar-expand-md navbar-dark fixed-top bg-dark">
	    <div class="container px-5">
	        <a class="navbar-brand">StudyMatch</a>
	        <button class="navbar-toggler" type="button" data-bs-toggle="collapse" data-bs-target="#navbarResponsive" aria-controls="navbarResponsive" aria-expanded="false" aria-label="Toggle navigation"><span class="navbar-toggler-icon"></span></button>
	        <div class="collapse navbar-collapse" id="navbarResponsive">
	            <ul class="navbar-nav ms-auto">
	                <li class="nav-item">
	      <a class="nav-link active" aria-current="page" href="<c:url value='/'/>">홈</a>
	    </li>
	    <li class="nav-item">
	      <a class="nav-link" href="<c:url value='/studyroom/list'/>">스터디</a>
	    </li>
	    <li class="nav-item">
	      <a class="nav-link" href="<c:url value='/listQuiz'/> ">퀴즈</a>
	    </li>
	    <li class="nav-item">
	      <a class="nav-link" href="<c:url value='/mypage.do'/>">마이룸</a>
	    </li>
	    <c:choose>
	    	<c:when test="${!empty user }">
	    		<li class="nav-item">
	    			${user.id}
	    		</li>
	     	<li class="nav-item">
	        <a class="nav-link" href="<c:url value='/logout.do'/>">로그아웃</a>
	      </li>
	    	</c:when>
	    	<c:otherwise>
	    		<li class="nav-item">
	        <a class="nav-link" href="<c:url value='/login.do'/>">로그인</a>
	      </li>
	    	</c:otherwise>
	    </c:choose>
	            </ul>
	        </div>
	    </div>
	</nav>
</header>
	<h2 class="text-center">스터디 수정</h2>
	
	<form method="post" action="<c:url value="/studyroom/edit"/>" >
		<table class="table table-bordered">
		<tr>
			<th scope="row" class="w-25 p-3">방번호</th>
			<td colspan="4">${studyroomDto.num }</td>
			<input type="hidden" name="num" value="${studyroomDto.num }" />
		</tr>
		<tr>
			<th scope="row" class="w-25 p-3">스터디이름</th>
			<td colspan="4"><input type="text" name="roomName" value="${studyroomDto.roomName }"></td>
		</tr>
		<tr>
			<th scope="row" class="w-25 p-3">제한인원</th>
			<td><input type="number" name="memberLimit" value="${studyroomDto.memberLimit }"></td>
			<th scope="row" class="w-25 p-3">채팅방 비밀번호</th>
			<td><input type="password" name="pw" value="${studyroomDto.pw }"></td>
		</tr>
		<tr>
			<th scope="row" class="w-25 p-3">방장</th>
			<td colspan="4">${studyroomDto.memberDto.id }</td>
			<input type="hidden" name="captain" value="${studyroomDto.memberDto.num }" />
		</tr>
		<tr>
			<th scope="row" class="w-25 p-3">지역</th>
			<td colspan="4">${studyroomDto.local }</td>
			<input type="hidden" name="local" value="${studyroomDto.local }"/>
		</tr>
		<tr>
			<th scope="row" class="w-25 p-3">스터디정보</th>
			<td colspan="4"><textarea name="roomDiscript" cols="100" rows="20">${studyroomDto.roomDiscript }</textarea></td>
		</tr>
		<tr>
			<th scope="row" class="w-25 p-3">과목정보</th>
			<td colspan="3">
				<c:forEach var="sub" items="${subjectList }">
					<c:choose>
						<c:when test="${sub.subjectNum == studyroomDto.subjectNum }">
							<input type="radio" name="subjectNum" value="${sub.subjectNum}" checked>${sub.subject}	
						</c:when>
						<c:otherwise>							
							<input type="radio" name="subjectNum" value="${sub.subjectNum}">${sub.subject}	
						</c:otherwise>
					</c:choose>			
				</c:forEach>
			</td>
		</tr>
		<tr>
			<th scope="row" class="w-25 p-3">조회수</th>
			<td colspan="4">${studyroomDto.cnt }</td>
		</tr>
		<tr>
			<th scope="row" class="w-25 p-3">등록일</th>
			<td colspan="4">${studyroomDto.regdate }</td>
		</tr>
		<tr class="col text-center">
			<td colspan="4">
				<button type="submit" class="edit_btn btn-primary me-md-2">수정완료</button>
				<button type="submit" class="list_btn btn-primary me-md-2">목록</button>
			</td>
		</tr>
		</table>
	</form>
	<br></br>
	
	<table class="table table-bordered">
			<tr class="col text-center">
				<th scope="col">멤버 목록</th>
			</tr>
			<c:forEach var="mem" items="${memberList }">
				<tr class="col text-center">
					<td>
						${mem.id } <input type="button" value="스터디내보내기" onclick="location.href='<c:url value="/studyroom/leave?memberNum=${mem.num }&chatroomNum=${studyroomDto.num }"/>'">
					</td>
				</tr>
			</c:forEach>
	</table>
	<br></br>
	

</body>
</html>