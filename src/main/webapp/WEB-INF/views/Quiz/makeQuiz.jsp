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
<script>
var quizNum=1;
function makeQuiz(){
	
	
	quizNum +=1
}
function hideChoiceOption(){
	var choice_option_row=document.getElementsById('choice_option'))
	choice_option_row.style.display='none';
	
		
}
function showChoiceOption(){
	var choice_option_row=document.getElementsById('choice_option'))
	choice_option_row.style.display='';
		
}
</script>
</head>
<body>
	<form method="post">
	<table>
	<!-- 여기서 부터 -->
		<tr>
			<td>방번호</td><td><input type="number" name="quizList[0].roomNum"/></td>
		</tr>
		<tr>
			<td>주제번호</td><td><input type="number" name="quizList[0].subjectNum"/></td>
		</tr>
		<tr>
			<td>만든사람</td><td><input type="number" name="quizList[0].makerNum"/></td>
		</tr>
	<!-- 여기까진 나중에 삭제 -->
	
		<tr>
			<td>문제타입</td>
			<td>
				<select name="quizType" >
					<option value="1">5지선다</option>
					<option value="2">단답형</option>
					<option value="3">주관식</option>
				</select>
			</td>
		</tr>
		<tr>
			<td>질문</td><td><textarea name="quizList[0].question"></textarea></td>
		</tr>
		
		<tr class="choice_option">
			<td>보기1</td><td><input type="text" name="quizList[0].option1"/></td>
		</tr>
		<tr class="choice_option">
			<td>보기2</td><td><input type="text" name="quizList[0].option2"/></td>
		</tr>
		<tr class="choice_option">
			<td>보기3</td><td><input type="text" name="quizList[0].option3"/></td>
		</tr>
		<tr class="choice_option">
			<td>보기4</td><td><input type="text" name="quizList[0].option4"/></td>
		</tr>
		<tr class="choice_option">
			<td>보기5</td><td><input type="text" name="quizList[0].option5"/></td>
		</tr>
		<tr>
			<td>정답</td><td><input type="text" name="quizList[0].answer"/></td>
		</tr>
		<tr>
			<td>해설</td><td><textarea name="quizList[0].explanation"></textarea></td>
		</tr>
	
		<!-- 여기서 부터 -->
		<tr>
			<td>방번호</td><td><input type="number" name="quizList[1].roomNum"/></td>
		</tr>
		<tr>
			<td>주제번호</td><td><input type="number" name="quizList[1].subjectNum"/></td>
		</tr>
		<tr>
			<td>만든사람</td><td><input type="number" name="quizList[1].makerNum"/></td>
		</tr>
	</table>
	
	<!-- 여기까진 나중에 삭제 -->
	<table>
		<tr>
			<td>문제타입</td>
			<td>
				<select name="quizList[1].quizType" >
					<option value="1">5지선다</option>
					<option value="2">단답형</option>
					<option value="3">주관식</option>
				</select>
			</td>
		</tr>
		<tr>
			<td>질문</td><td><textarea name="quizList[1].question"></textarea></td>
		</tr>
		
		<tr>
			<td>보기1</td><td><input type="text" name="quizList[1].option1"/></td>
		</tr>
		<tr>
			<td>보기2</td><td><input type="text" name="quizList[1].option2"/></td>
		</tr>
		<tr>
			<td>보기3</td><td><input type="text" name="quizList[1].option3"/></td>
		</tr>
		<tr>
			<td>보기4</td><td><input type="text" name="quizList[1].option4"/></td>
		</tr>
		<tr>
			<td>보기5</td><td><input type="text" name="quizList[1].option5"/></td>
		</tr>
		<tr>
			<td>정답</td><td><input type="text" name="quizList[1].answer"/></td>
		</tr>
		<tr>
			<td>해설</td><td><textarea name="quizList[1].explanation"></textarea></td>
		</tr>
		<tr colspan="2">
			<td><input type="submit" value="문제 제출"/></td>
		</tr>
	</table>
</form>
</body>
</html>