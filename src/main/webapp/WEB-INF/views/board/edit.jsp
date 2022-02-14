<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jstl/core_rt" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<script src="//cdnjs.cloudflare.com/ajax/libs/jquery/3.2.1/jquery.min.js"></script>
<title>editBoard</title>
</head>
<script type="text/javascript">
	$(document).ready(function(){
		
		$(".list_btn").on("click", function(){
			event.preventDefault();
			location.href = "/board/list";
		})
	})
</script>
<body>
	<h2>공지사항 수정</h2>
	<form method="post" action="<c:url value="/board/edit"/>" enctype="multipart/form-data">
		<table border="1">
		<tr>
			<th>글번호</th>
			<td>${boardDto.num }</td>
			<input type="hidden" name=num value="${boardDto.num }" />
		</tr>
		<tr>
			<th>제목</th>
			<td><input type="text" name="title" value="${boardDto.title }"></td>
		</tr>
		<tr>
			<th>작성자</th>
			<td>${boardDto.memberDto.id }</td>
			<hidden type="text" name="writer" value="${boardDto.memberDto.id }" />
		</tr>
		<tr>
			<th>내용</th>
			<td><input type="text" name="content" value="${boardDto.title }"></td>
		</tr>
		<tr>
			<th>첨부파일</th>
			<td>
				<c:forEach var="file" items="${fileList}">
					<a href="<c:url value="/board/downFile/${file.FILE_NO}"/>">
					${file.ORG_FILE_NAME}</a>(${file.FILE_SIZE}kb)
					<input type="button" value="삭제" onclick="location.href='<c:url value="/board/delFile/${file.FILE_NO}"/>'">
					<br>
				</c:forEach>
				<input type="file" name="file">
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
			<button type="submit" class="edit_btn">수정완료</button>
			<input type="reset" value="다시작성">
			<button type="submit" class="list_btn">목록</button>
	</form>
</body>
</html>