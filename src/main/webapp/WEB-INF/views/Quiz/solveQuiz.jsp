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
			<td>과목이름</td><td><input type="number" name="subjectNum"/></td>
		</tr>
		<tr>
			<td>문제수</td><td><input type="number" name="numOfQuestion"/></td>
		
			<td><input type="submit" value="검색"/>
		</tr>
	</table>
	</form>
	
		<form method="post" action=<c:url value="/resultQuiz"/>>
		<table>
		<c:forEach var="dto" items="${dtos}" varStatus="status">
		<tr>
			<td>퀴즈 고유번호</td>
			<td><input type="number" name="answerList[${dto.key}].num" value="${dto.value.num}" /></td>
		</tr>
		<tr>
			<td>과목 명</td>
			<td><input type="number" name="answerList[${dto.key}].subjectNum" value="${dto.value.subjectNum}" /></td>
		</tr>
		<tr>
		
			<td>문제타입</td>
			<td>
				<input type="number" name="answerList[${dto.key}].quizType" value="${dto.value.quizType}" />
			</td>
		</tr>
		<tr>
			<td>질문</td>
				<td>
				<input type="text" name="answerList[${dto.key}].question" value="${dto.value.question}" />
				</td>
		</tr>
		
		<tr>
			<td>보기1</td><td><input type="radio" name="answerList[${dto.key}].answer" value="1"/>${dto.value.option1 }</td>
		</tr>
		<tr>
			<td>보기2</td><td><input type="radio" name="answerList[${dto.key}].answer" value="2"/>${dto.value.option2 }</td>
		</tr>
		<tr>
			<td>보기3</td><td><input type="radio" name="answerList[${dto.key}].answer" value="3"/>${dto.value.option3 }</td>
		</tr>
		<tr>
			<td>보기4</td><td><input type="radio" name="answerList[${dto.key}].answer" value="4"/>${dto.value.option4 }</td>
		</tr>
		<tr>
			<td>보기5</td><td><input type="radio" name="answerList[${dto.key}].answer" value="5"/>${dto.value.option5 }</td>
		</tr>
		<tr>
			<td>단답형 정답</td><td><textarea name="answerList[${dto.key}].answer"></textarea></td>
		</tr>
		</c:forEach>
		<tr colspan="2">
			<td><input type="submit" value="답지 제출"/></td>
		</tr>
		
	
	</table>
</form>
</body>
</html>