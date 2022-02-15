<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<style type="text/css">
	.table {
    display: table;
}
.title {
    display: table-caption;
    text-align: center;
    font-weight: bold;
    font-size: larger;
}
.heading {
    display: table-row;
    font-weight: bold;
    text-align: center;
}
.row {
    display: table-row;
}
.cell {
    display: table-cell;
    border: solid;
    border-width: thin;
    padding-left: 5px;
    padding-right: 5px;
}

</style>
<title>Insert title here</title>
<script>
function selectSunjectNum(obj){
	this.value
}

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
	<c:forEach var="room" items="${chatroomList }" varStatus="status"> 
		<input type="hidden" name="roomName${room.subjectNum }" value="${room.chatroomNum }"/>
	</c:forEach>
	<table>
	
		
		<tr>
			<td>과목</td><td>
			<c:if test="${!empty chatroomList}">
			<select id="test" name="quizList[0].SubjectNum" onChange="selectSunjectNum(this);">
				<c:forEach var="room" items="${chatroomList }" varStatus="status"> 
					<option value="${room.subjectNum },${room.chatroomNum }">${room.subject},${room.roomName }</option>
					
				</c:forEach>
			</select>
			
			</c:if>
			</td>
		</tr>
		
	
	
		<tr>
			<td>문제타입</td>
			<td>
				<select name="quizList[0].quizType" >
					<option value="1" selected>5지선다</option>
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
	
		
	</table>
	
	
	<!-- 
	<div class="table">
                <div class="row">
                    <div class="cell">
                        <p>과목</p>
                    </div>
                    <div class="cell">
                        <p>                
			                <c:if test="${!empty chatroomList}">
			  	              <select name=quizList[1].subjectNum">
									<c:forEach var="room" items="${chatroomList }" varStatus="status"> 
										<option value="${room.subjectNum }">${room.subject}</option>
									</c:forEach>
								</select>
							</c:if>
						</p>
                    </div>
                </div>
                
                <div class="row">
                    <div class="cell">
                        <p>방제목</p>
                    </div>
                    <div class="cell">
                        <p>                
			                <c:if test="${!empty chatroomList}">
			  	              <select name=quizList[1].subjectNum">
									<c:forEach var="room" items="${chatroomList }" varStatus="status"> 
										<option value="${room.subjectNum }">${room.subject}</option>
									</c:forEach>
								</select>
							</c:if>
						</p>
                    </div>
                </div>
                
                <div class="row">
                    <div class="cell">
                        <p>문제타입</p>
                    </div>
                    <div class="cell">
                        <p><select name="quizType" >
								<option value="1">5지선다</option>
								<option value="2">단답형</option>
								<option value="3">주관식</option>
							</select></p>
                    </div>
                </div>
                <div class="row">
                    <div class="cell">
                        <p>질문</p>
                    </div>
                    <div class="cell">
                        <p><textarea name="quizList[1].question"></textarea></p>
                    </div>
                </div>
                 <div class="row option">
                    <div class="cell">
                        <p>보기1</p>
                    </div>
                    <div class="cell">
                        <p><input type="text" name="quizList[1].option1"/></p>
                    </div>
                </div>
                <div class="row option">
                    <div class="cell">
                        <p>보기2</p>
                    </div>
                    <div class="cell">
                        <p><input type="text" name="quizList[1].option2"/></p>
                    </div>
                </div>
                <div class="row option">
                    <div class="cell">
                        <p>보기3</p>
                    </div>
                    <div class="cell">
                        <p><input type="text" name="quizList[1].option3"/></p>
                    </div>
                </div>
                <div class="row option">
                    <div class="cell">
                        <p>보기4</p>
                    </div>
                    <div class="cell">
                        <p><input type="text" name="quizList[1].option4"/></p>
                    </div>
                </div>
                <div class="row option">
                    <div class="cell">
                        <p>보기5</p>
                    </div>
                    <div class="cell">
                        <p><input type="text" name="quizList[1].option5"/></p>
                    </div>
                </div>
                <div class="row">
                    <div class="cell">
                        <p>정답</p>
                    </div>
                    <div class="cell">
                        <p><input type="text" name="quizList[1].answer"/></p>
                    </div>
                </div>
                <div class="row">
                    <div class="cell">
                        <p>해설</p>
                    </div>
                    <div class="cell">
                        <p><input type="text" name="quizList[1].explanation"/></p>
                    </div>
                </div>
            </div> -->
<input type="submit" value="문제 제출"/>
</form>

</body>
</html>