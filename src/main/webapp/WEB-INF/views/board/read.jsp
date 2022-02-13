<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jstl/core_rt" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>readBoard</title>
</head>
<body>
	<h2>공지사항 읽기</h2>
	<form>
		<table border="1">
		<tr>
			<th>글번호</th>
			<td>${boardDto.num }</td>
		</tr>
		<tr>
			<th>제목</th>
			<td>${boardDto.title }</td>
		</tr>
		<tr>
			<th>작성자</th>
			<td>${boardDto.memberDto.id }</td>
		</tr>
		<tr>
			<th>내용</th>
			<td>${boardDto.content }</td>
		</tr>
		<tr>
			<th>첨부파일</th>
			<td>
				<c:forEach var="file" items="${fileList}">
					<a href="<c:url value="/board/downFile/${file.FILE_NO}"/>">${file.ORG_FILE_NAME}</a>(${file.FILE_SIZE}kb)<br>
				</c:forEach>
			</td>
		</tr>
		<tr>
			<th>조회수</th>
			<td>${boardDto.cnt }</td>
		</tr>
		<tr>
			<th>등록일</th>
			<td>${boardDto.regdate }</td>
		</tr>
		</table>
		<c:if test="${sessionScope.user.id eq 'admin' }">
			<a href="<c:url value="/board/edit/${boardDto.num}"/>">[수정]</a>
			<a href="<c:url value="/board/delete/${boardDto.num}"/>">[삭제]</a>
			<input type="button" value="목록보기" onClick="location.href='<c:url value="/board/list"/>'">
		</c:if>
	</form>
</body>
</html>