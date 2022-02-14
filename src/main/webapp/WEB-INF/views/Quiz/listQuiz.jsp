<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<!DOCTYPE html>
<html>
<head>
<script src="//cdnjs.cloudflare.com/ajax/libs/jquery/3.2.1/jquery.min.js"></script>
<meta charset="UTF-8">
<title>listStudyroom</title>
	<style type="text/css">
		li {list-style: none; float: left; padding: 6px;}
	</style>
</head>
<body>
	<h2>스터디룸 리스트</h2>
	${sessionScope.user.id }님 환영합니다!<br></br>
	<a href="<c:url value="/makeQuiz" />">[퀴즈 만들기]</a><br>
	<a href="<c:url value='/solveQuiz'/> ">[퀴즈 풀기 바로 가기]</a>

		
	<c:choose>
	<c:when test="${empty quizList }">
		<tr>
			<td>등록된 퀴즈가 없습니다.<br></br></td>
		</tr>
	</c:when>
	
	<c:when test="${!empty quizList}">
	<div class="search">
	    <select name="searchType">
	      <option value="n"<c:out value="${scri.searchType == null ? 'selected' : ''}"/>>-----</option>
	      <option value="t"<c:out value="${scri.searchType eq 't' ? 'selected' : ''}"/>>제목</option>
	      <option value="c"<c:out value="${scri.searchType eq 'c' ? 'selected' : ''}"/>>내용</option>
	      <option value="w"<c:out value="${scri.searchType eq 'w' ? 'selected' : ''}"/>>작성자</option>
	      <option value="tc"<c:out value="${scri.searchType eq 'tc' ? 'selected' : ''}"/>>제목+내용</option>
	    </select>
	
	    <input type="text" name="keyword" id="keywordInput" value="${scri.keyword}"/>
	
	    <button id="searchBtn" type="button">검색</button>
	    <script>
	      $(function(){
	        $('#searchBtn').click(function() {
	          self.location = "list" + '${pageMaker.makeQuery(1)}' + "&searchType=" + $("select option:selected").val() + "&keyword=" + encodeURIComponent($('#keywordInput').val());
	        });
	      });   
	    </script>
  	</div>
	<table border="1">
		<tr>
			<th>NO</th>
			<th>과목</th>
			<th>질문</th>
			<th>작성자</th>
			<th>출제수</th>
			<th>정답률</th>
			<th>삭제버튼</th>
		</tr>
		
		<c:forEach var="quiz" items="${quizList }" varStatus="quizListStatus">
			<tr>
				<td>${quizListStatus.count}</td>
				<td>
					
					${quiz.subjectName }
				</td>
				<td><a href="/updateQuizDetail/num/${quiz.num}">${quiz.question}</a></td>
				<td>${quiz.nickName }</td>
				<td>${quiz.numOfQuiz}</td>
				<td>${quiz.correctRate}</td>
				<td><button type="button" onClick="location.href='/deleteQuiz/num/${quiz.num}'" >삭제</button></td>
			</tr>
		</c:forEach>
		</table>
		</c:when>
		</c:choose>

	
<div>

  <ul>
 
    <c:if test="${pageMaker.prev}">
    	<li><a href="listQuiz${pageMaker.makeSearch(pageMaker.startPage - 1)}">이전</a></li>
    </c:if> 
	    <c:forEach begin="${pageMaker.startPage}" end="${pageMaker.endPage}" var="idx">
	    	<li><a href="listQuiz${pageMaker.makeSearch(idx)}">${idx}</a></li>
	    </c:forEach>
	
    <c:if test="${pageMaker.next && pageMaker.endPage > 0}">
    	<li><a href="listQuiz${pageMaker.makeSearch(pageMaker.endPage + 1)}">다음</a></li>
    </c:if> 
  </ul>
</div>
</body>
</html>