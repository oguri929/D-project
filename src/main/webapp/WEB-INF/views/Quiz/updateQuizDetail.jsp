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
	<title>updateQuiz</title>
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
	<h2 class="text-center">퀴즈 내용</h2>
	<form method="post">
	<input type="hidden" value="${quiz.subjectNum }" name="subjectNum"/>
	<input type="hidden" value="${quiz.chatroomNum }" name="chatroomNum"/>
	<table class="table table-bordered">
		<tr>
			<th scope="row" class="w-25 p-3">문제타입</th>
			<td>
				<select name="quizType" >
					<option value="1" <c:if test="${quiz.quizType==1}"> selected </c:if>>5지선다</option>
					<option value="2" <c:if test="${quiz.quizType==2}"> </c:if>>단답형</option>
					<option value="3" <c:if test="${quiz.quizType==3}">  </c:if>>주관식</option>
				</select>
			</td>
		</tr>
		<tr>
			<th scope="row" class="w-25 p-3">질문</th>
			<td><textarea name="question">${quiz.question }</textarea></td>
		</tr>
		
		<tr>
			<td>보기1</td>
			<td><input type="text" name="option1" value="${quiz.option1 }"/></td>
		</tr>
		<tr>
			<td>보기2</td>
			<td><input type="text" name="option2" value="${quiz.option2 }"/></td>
		</tr>
		<tr>
			<td>보기3</td>
			<td><input type="text" name="option3" value="${quiz.option3 }"/></td>
		</tr>
		<tr>
			<td>보기4</td>
			<td><input type="text" name="option4" value="${quiz.option4 }"/></td>
		</tr>
		<tr>
			<td>보기5</td>
			<td><input type="text" name="option5" value="${quiz.option5 }"/></td>
		</tr>
		<tr>
			<th scope="row" class="w-25 p-3">정답</th>
			<td><input type="text" name="answer" value="${quiz.answer }"/></td>
		</tr>
		<tr>
			<th scope="row" class="w-25 p-3">해설</th>
			<td><textarea name="explanation">${quiz.explanation }</textarea></td>
		</tr>	
		<tr class="col text-center">
			<td colspan="2">
				<input type="submit" class="btn-primary me-md-2" value="문제 수정"/>
				<input type="button" value="퀴즈 목록보기" class="btn-primary me-md-2" onClick="location.href='<c:url value="/listQuiz"/>'">
			</td>
		</tr>
		
	
	</table>
</form>
</body>
</html>