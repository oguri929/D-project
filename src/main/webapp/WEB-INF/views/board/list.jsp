<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>listBoard</title>
	<style type="text/css">
		li {list-style: none; float: left; padding: 6px;}
	</style>
</head>
<body>
	<c:choose>
	<c:when test="${empty boardList }">
		<tr>
			<td>등록된 게시글이 없습니다.<br></br></td>
		</tr>
	</c:when>
	
	<c:when test="${!empty boardList }">
	<table border="1">
		<tr>
			<th>NO</th>
			<th>제목</th>
			<th>작성자</th>
			<th>작성일</th>
			<th>조회수</th>
		</tr>
		
		<c:forEach var="board" items="${boardList }" varStatus="boardNum">
			<tr>
				<td>${boardNum.count}</td>
				<td>
					<a href="<c:url value="/board/read/${board.num}" />">
					${board.title }</a>
				</td>
				<td>${board.writer}</td>
				<td>${board.regdate}</td>
				<td>${board.cnt}</td>
			</tr>
		</c:forEach>
		</table>
		</c:when>
		</c:choose>

	<a href="<c:url value="/board/write" />">[새 글 쓰기]</a>
	
<div>
  <ul>
    <c:if test="${pageMaker.prev}">
    	<li><a href="list${pageMaker.makeQuery(pageMaker.startPage - 1)}">이전</a></li>
    </c:if> 

    <c:forEach begin="${pageMaker.startPage}" end="${pageMaker.endPage}" var="idx">
    	<li><a href="list${pageMaker.makeQuery(idx)}">${idx}</a></li>
    </c:forEach>

    <c:if test="${pageMaker.next && pageMaker.endPage > 0}">
    	<li><a href="list${pageMaker.makeQuery(pageMaker.endPage + 1)}">다음</a></li>
    </c:if> 
  </ul>
</div>	
</body>
</html>