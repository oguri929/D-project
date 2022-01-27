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
	<c:choose>
	<c:when test="${empty studyroomList }">
		<tr>
			<td>등록된 스터디가 없습니다.<br></br></td>
		</tr>
	</c:when>
	
	<c:when test="${!empty studyroomList}">
	<form action=""></form>
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
			<th>방제목</th>
			<th>방장</th>
			<th>제한인원</th>
			<th>과목정보</th>
			<th>작성일</th>
			<th>조회수</th>
		</tr>
		
		<c:forEach var="studyroom" items="${studyroomList }" varStatus="studyroomNum">
			<tr>
				<td>${studyroomNum.count}</td>
				<td>
					<a href="/studyroom/read/${studyroom.num}">
					${studyroom.roomName }</a>
				</td>
				<td>${studyroom.captain}</td>
				<td>${studyroom.memberLimit}</td>
				<td>${studyroom.subject }</td>
				<td>${studyroom.regdate}</td>
				<td>${studyroom.cnt}</td>
			</tr>
		</c:forEach>
		</table>
		</c:when>
		</c:choose>

	<a href="<c:url value="/studyroom/create" />">[스터디룸 만들기]</a>
	
<div>
  <ul>
    <c:if test="${pageMaker.prev}">
    	<li><a href="list${pageMaker.makeSearch(pageMaker.startPage - 1)}">이전</a></li>
    </c:if> 

    <c:forEach begin="${pageMaker.startPage}" end="${pageMaker.endPage}" var="idx">
    	<li><a href="list${pageMaker.makeSearch(idx)}">${idx}</a></li>
    </c:forEach>

    <c:if test="${pageMaker.next && pageMaker.endPage > 0}">
    	<li><a href="list${pageMaker.makeSearch(pageMaker.endPage + 1)}">다음</a></li>
    </c:if> 
  </ul>
</div>
</body>
</html>