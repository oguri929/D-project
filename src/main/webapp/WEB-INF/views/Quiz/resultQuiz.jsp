<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<!DOCTYPE html>
<html>
<head>
	<!-- Required meta tags -->
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1">

    <!-- Bootstrap CSS -->
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-1BmE4kWBq78iYhFldvKuhfTAU6auU8tT94WrHftjDbrCEXSU1oBoqyl2QvZ6jIW3" crossorigin="anonymous">
	<script src="//cdnjs.cloudflare.com/ajax/libs/jquery/3.2.1/jquery.min.js"></script>
	<title>solveQuiz</title>
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
	<h2 class="text-center">퀴즈 풀기</h2>
	<form method="post">
	<table class="table table-bordered">
		<c:forEach var="quiz" items="${answerList}" varStatus="status">
		<tr>
			<th scope="row" class="w-25 p-3">문제타입</th>
			<td>
				<c:choose>
				<c:when test="${quiz.value.quizType==1 }">
				<p>5지선다</p>
				</c:when>
				<c:when test="${quiz.value.quizType==2 }">
				<p>단답형</p>
				</c:when>
				<c:when test="${quiz.value.quizType==3 }">
				<p>주관식</p>
				</c:when>
				</c:choose>
			</td>
		</tr>
		<tr>
			<th scope="row" class="w-25 p-3">질문</th>
			<td>${quiz.value.question }</td>
		</tr>
		<c:choose>
			<c:when test="${dto.value.quizType==1 }">
				<tr>
					<td>보기1</td>
					<td>${quiz.value.option1 }</td>
				</tr>
				<tr>
					<td>보기2</td>
					<td>${quiz.value.option2 }</td>
				</tr>
				<tr>
					<td>보기3</td>
					<td>${quiz.value.option3 }</td>
				</tr>
				<tr>
					<td>보기4</td>
					<td>${quiz.value.option4 }</td>
				</tr>
				<tr>
					<td>보기5</td>
					<td>${quiz.value.option5 }</td>
				</tr>
			</c:when>
		</c:choose>
		<tr>
			<th scope="row" class="w-25 p-3">나의 정답</th>
			<td>${quiz.key.answer }</td>
		</tr>
		<tr>
			<th scope="row" class="w-25 p-3">문제 정답</th>
			<td>${quiz.value.answer }</td>
		</tr>
		<tr>
			<th scope="row" class="w-25 p-3">해설</th>
			<td>${quiz.value.explanation }</td>
		</tr>
		<tr class="col text-center">
			<td colspan="2">
				<input type="button" value="퀴즈 목록보기" class="btn-primary me-md-2" onClick="location.href='<c:url value="/listQuiz"/>'">
			</td>
		</tr>
		</c:forEach>
	</table>
</form>
</body>
</html>