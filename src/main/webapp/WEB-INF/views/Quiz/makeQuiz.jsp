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
	<title>makeQuiz</title>
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
</head>
<script>
function selectSunjectNum(obj){
	this.value
	alert(this.value);
}

var quizNum=1;
function makeQuiz(){
	
	
	quizNum +=1;
}
function hideChoiceOption(){
	var choice_option_row=document.getElementsById('choice_option');
	choice_option_row.style.display='none';
	
		
}
function showChoiceOption(obj){
	
	var choice_option_row=$(".choice_option");
	var select_value=$('');
	var i=$(this).parent();
	alert(i.prop('name'));
	choice_option_row.style.display='';
		
}
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
	      <a class="nav-link active" aria-current="page" href="<c:url value='/'/>">???</a>
	    </li>
	    <li class="nav-item">
	      <a class="nav-link" href="<c:url value='/studyroom/list'/>">?????????</a>
	    </li>
	    <li class="nav-item">
	      <a class="nav-link" href="<c:url value='/listQuiz'/> ">??????</a>
	    </li>
	    <li class="nav-item">
	      <a class="nav-link" href="<c:url value='/mypage.do'/>">?????????</a>
	    </li>
	    <c:choose>
	    	<c:when test="${!empty user }">
	    		<li class="nav-item">
	    			${user.id}
	    		</li>
	     	<li class="nav-item">
	        <a class="nav-link" href="<c:url value='/logout.do'/>">????????????</a>
	      </li>
	    	</c:when>
	    	<c:otherwise>
	    		<li class="nav-item">
	        <a class="nav-link" href="<c:url value='/login.do'/>">?????????</a>
	      </li>
	    	</c:otherwise>
	    </c:choose>
	            </ul>
	        </div>
	    </div>
	</nav>
</header>

<c:choose>
	<c:when test="${chatroomList eq null }">
		<h2 class="text-center">?????? ?????????</h2>
		<form method="post">
		<c:forEach var="room" items="${chatroomList }" varStatus="status"> 
			<input type="hidden" name="roomName${room.subjectNum }" value="${room.chatroomNum }"/>
		</c:forEach>
		
		<table class="table table-bordered">	
			<tr>
				<th scope="row" class="w-25 p-3">??????</th>
				<td>
				<c:if test="${!empty chatroomList}">
				<select id="test" name="quizList[0].sSubjectNum" onChange="selectSunjectNum(this);">
					<c:forEach var="room" items="${chatroomList }" varStatus="status"> 
						<option value="${room.subjectNum },${room.chatroomNum }">${room.subject},${room.roomName }</option>
						
					</c:forEach>
				</select>
				
				</c:if>
				</td>
			</tr>
			<tr>
				<th scope="row" class="w-25 p-3">????????????</th>
				<td>
					<select name="quizList[0].quizType" >
						<option value="1" selected>5?????????</option>
						<option value="2">?????????</option>
						<option value="3">?????????</option>
					</select>
				</td>
			</tr>
			<tr>
				<th scope="row" class="w-25 p-3">??????</th>
				<td><textarea name="quizList[0].question"></textarea></td>
			</tr>
			
			<tr class="choice_option">
				<td>??????1</td>
				<td><input type="text" name="quizList[0].option1"/></td>
			</tr>
			<tr class="choice_option">
				<td>??????2</td>
				<td><input type="text" name="quizList[0].option2"/></td>
			</tr>
			<tr class="choice_option">
				<td>??????3</td>
				<td><input type="text" name="quizList[0].option3"/></td>
			</tr>
			<tr class="choice_option">
				<td>??????4</td>
				<td><input type="text" name="quizList[0].option4"/></td>
			</tr>
			<tr class="choice_option">
				<td>??????5</td>
				<td><input type="text" name="quizList[0].option5"/></td>
			</tr>
			<tr>
				<th scope="row" class="w-25 p-3">??????</th>
				<td><input type="text" name="quizList[0].answer"/></td>
			</tr>
			<tr>
				<th scope="row" class="w-25 p-3">??????</th>
				<td><textarea name="quizList[0].explanation"></textarea></td>
			</tr>
		</table>

		
		<div class="col text-center">
			<input type="submit" class="btn-primary me-md-2" value="?????? ??????"/>
		</div>
		</form>
	</c:when>
	<c:otherwise>
		<%@include file="../errorPage/noStudyroom.jsp"%>
	</c:otherwise>
</c:choose>
</body>
</html>