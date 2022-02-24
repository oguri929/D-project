<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jstl/core_rt" %>
<!DOCTYPE html>
<html>
<head>
	<!-- Required meta tags -->
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1">

    <!-- Bootstrap CSS -->
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-1BmE4kWBq78iYhFldvKuhfTAU6auU8tT94WrHftjDbrCEXSU1oBoqyl2QvZ6jIW3" crossorigin="anonymous">
	<script src="//cdnjs.cloudflare.com/ajax/libs/jquery/3.2.1/jquery.min.js"></script>
	<title>readBoard</title>
	<style type="text/css">
		body {
		  padding-top: 5rem;
		  padding-bottom: 3rem;
		  color: #5a5a5a;
		}
		
		.table{
		  width: 100%;
		  max-width: 1000px;
		  padding: 15px;
		  margin: auto;
		}
	</style>
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
<header>
<!-- Navigation-->
	<nav class="navbar navbar-expand-md navbar-dark fixed-top bg-dark">
	    <div class="container px-5">
	        <a class="navbar-brand">StudyMatch</a>
	        <button class="navbar-toggler" type="button" data-bs-toggle="collapse" data-bs-target="#navbarResponsive" aria-controls="navbarResponsive" aria-expanded="false" aria-label="Toggle navigation"><span class="navbar-toggler-icon"></span></button>
	        <div class="collapse navbar-collapse" id="navbarResponsive">
	            <ul class="navbar-nav ms-auto">
	                <li class="nav-item">
	      <a class="nav-link active" aria-current="page" href="<c:url value='/'/>">홈</a>
	    </li>
	    <li class="nav-item">
	      <a class="nav-link" href="<c:url value='/studyroom/list'/>">스터디</a>
	    </li>
	    <li class="nav-item">
	      <a class="nav-link" href="<c:url value='/listQuiz'/> ">퀴즈</a>
	    </li>
	    <li class="nav-item">
	      <a class="nav-link" href="<c:url value='/mypage.do'/>">내정보</a>
	    </li>
	    <c:choose>
	    	<c:when test="${!empty user }">
	    		<li class="nav-item">
	    			${user.id}
	    		</li>
	     	<li class="nav-item">
	        <a class="nav-link" href="<c:url value='/logout.do'/>">로그아웃</a>
	      </li>
	    	</c:when>
	    	<c:otherwise>
	    		<li class="nav-item">
	        <a class="nav-link" href="<c:url value='/login.do'/>">로그인</a>
	      </li>
	    	</c:otherwise>
	    </c:choose>
	            </ul>
	        </div>
	    </div>
	</nav>
</header>
	<h2 class="text-center">공지사항</h2>
	<form name="readForm" method="post">
		<input type="hidden" name="num" value="${boardDto.num }"/>
	</form>
		<table class="table table-bordered">
		<tr>
			<th scope="row" class="w-25 p-3">글번호</th>
			<td>${boardDto.num }</td>
		</tr>
		<tr>
			<th scope="row" class="w-25 p-3">제목</th>
			<td>${boardDto.title }</td>
		</tr>
		<tr>
			<th scope="row" class="w-25 p-3">작성자</th>
			<td>${boardDto.memberDto.id }</td>
		</tr>
		<tr>
			<th scope="row" class="w-25 p-3">내용</th>
			<td>${boardDto.content }</td>
		</tr>
		<tr>
			<th scope="row" class="w-25 p-3">첨부파일</th>
			<td>
				<c:forEach var="file" items="${fileList}">
					<a href="<c:url value="/board/downFile/${file.FILE_NO}"/>">${file.ORG_FILE_NAME}</a>(${file.FILE_SIZE}kb)<br>
				</c:forEach>
			</td>
		</tr>
		<tr>
			<th scope="row" class="w-25 p-3">조회수</th>
			<td>${boardDto.cnt }</td>
		</tr>
		<tr>
			<th scope="row" class="w-25 p-3">등록일</th>
			<td>${boardDto.regdate }</td>
		</tr>
		</table>
		<div class="col text-center">
			<c:if test="${sessionScope.user.id eq 'admin' }">
				<button type="submit" class="edit_btn btn-primary me-md-2">수정</button>
				<button type="submit" class="delete_btn btn-primary me-md-2">삭제</button>
				<button type="submit" class="list_btn btn-primary me-md-2">목록</button>
			</c:if>
		</div>
</body>
</html>