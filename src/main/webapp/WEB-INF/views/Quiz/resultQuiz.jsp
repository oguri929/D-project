<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	<form method="post">
	<table border="1">
		<c:forEach var="quiz" items="${answerList}" varStatus="status">
		<tr>
			<td>문제타입</td>
			<td>
				${quiz.value.quizType}
			</td>
		</tr>
		<tr>
			<td>질문</td><td>${quiz.value.question }</td>
		</tr>
		
		<tr>
			<td>보기1</td><td>${quiz.value.option1 }</td>
		</tr>
		<tr>
			<td>보기2</td><td>${quiz.value.option2 }</td>
		</tr>
		<tr>
			<td>보기3</td><td>${quiz.value.option3 }</td>
		</tr>
		<tr>
			<td>보기4</td><td>${quiz.value.option4 }</td>
		</tr>
		<tr>
			<td>보기5</td><td>${quiz.value.option5 }</td>
		</tr>
		<tr>
			<td>나의 정답</td><td>${quiz.key.answer }</td>
		</tr>
		<tr>
			<td>문제 정답</td><td>${quiz.value.answer }</td>
		</tr>
		<tr>
			<td>해설</td><td>${quiz.value.explanation }</td>
		</tr>
		</c:forEach>
		
	
	</table>
</form>
</body>
</html>