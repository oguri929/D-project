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
			<td>문제타입</td>
			<td>
				<select name="quizType" >
					<option value="1" <c:if test="${quiz.quizType==1}"> selected </c:if>>5지선다</option>
					<option value="2" <c:if test="${quiz.quizType==2}"> </c:if>>단답형</option>
					<option value="3" <c:if test="${quiz.quizType==3}">  </c:if>>주관식</option>
				</select>
			</td>
		</tr>
		<tr>
			<td>질문</td><td><textarea name="question">${quiz.question }</textarea></td>
		</tr>
		
		<tr>
			<td>보기1</td><td><input type="text" name="option1" value="${quiz.option1 }"/></td>
		</tr>
		<tr>
			<td>보기2</td><td><input type="text" name="option2" value="${quiz.option2 }"/></td>
		</tr>
		<tr>
			<td>보기3</td><td><input type="text" name="option3" value="${quiz.option3 }"/></td>
		</tr>
		<tr>
			<td>보기4</td><td><input type="text" name="option4" value="${quiz.option4 }"/></td>
		</tr>
		<tr>
			<td>보기5</td><td><input type="text" name="option5" value="${quiz.option5 }"/></td>
		</tr>
		<tr>
			<td>정답</td><td><input type="text" name="answer" value="${quiz.answer }"/></td>
		</tr>
		<tr>
			<td>해설</td><td><textarea name="explanation">${quiz.explanation }</textarea></td>
		</tr>
		
		<tr colspan="2">
			<td><input type="submit" value="문제 수정"/></td>
		</tr>
		
	
	</table>
</form>
</body>
</html>