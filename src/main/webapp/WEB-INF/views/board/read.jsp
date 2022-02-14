<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jstl/core_rt" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<script src="//cdnjs.cloudflare.com/ajax/libs/jquery/3.2.1/jquery.min.js"></script>
<title>readBoard</title>
</head>
<script type="text/javascript">
$(document).ready(function(){
    var formObj = $("form[name='readForm']");
    //수정
    $(".edit_btn").on("click", function(){
        formObj.attr("action", "/board/edit");
        formObj.attr("method", "get");
        formObj.submit();
    })
  
    //삭제
    $(".delete_btn").on("click", function(){
        formObj.attr("action", "/board/delete");
        formObj.attr("method", "post");
        formObj.submit();
    })
    
    //목록으로 돌아가기
    $(".list_btn").on("click", function(){	
		location.href = "/board/list";
	})
})
</script>
<body>
	<h2>공지사항 읽기</h2>
	<form name="readForm" method="post">
		<input type="hidden" name="num" value="${boardDto.num }"/>
	</form>
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
			<button type="submit" class="edit_btn">수정</button>
			<button type="submit" class="delete_btn">삭제</button>
			<button type="submit" class="list_btn">목록</button>
		</c:if>
</body>
</html>