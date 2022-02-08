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
	<table>
		<tr>
			<td>과목 설정</td><td><input type="text" name="subjectNum"/></td>
		</tr>
		<tr colspan="2">
			<td><input type="submit" value="퀴즈 검색"/></td>
		</tr>
	</table>
	</form>
	<c:if test="${!empty quizList}">
	<!-- 문제 삭제 버튼 만들기 -->
	<table border="1">
	<tr>
		<td>문제</td>
		<td>과목</td>
		<td>문제 유형</td>
		<td>문제 정답</td>
		<td>문제 해설</td>
		<td></td>
	</tr>
		<c:forEach var="quiz" items="${quizList}">
			<tr>
				<td><a href="<c:url value='/updateQuizDetail/num/${quiz.num}'/>">${quiz.question }</a></td>
				<td>${quiz.subjectNum }</td>
				<td>${quiz.quizType}</td>
				<td>${quiz.answer}</td>
				<td>${quiz.explanation}</td>
				<td><a href="<c:url value='/deleteQuiz/num/${quiz.num}'/>">문제 삭제</a></td>	
			</tr>
		</c:forEach>
	</table>
	</c:if>
</body>
</html>