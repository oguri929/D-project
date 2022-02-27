<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<!DOCTYPE html>
<html>
<head>
	<!-- Required meta tags -->
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1">

    <!-- Bootstrap CSS -->
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-1BmE4kWBq78iYhFldvKuhfTAU6auU8tT94WrHftjDbrCEXSU1oBoqyl2QvZ6jIW3" crossorigin="anonymous">
	<script src="//cdnjs.cloudflare.com/ajax/libs/jquery/3.2.1/jquery.min.js"></script>
	<title>listStudyroom</title>
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
		
		li{
			list-style: none; 
			float: left; 
			padding: 6px;
		}
	</style>
</head>
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
	      <a class="nav-link" href="<c:url value='/mypage.do'/>">마이룸</a>
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
	<h2 class="text-center">스터디 찾기</h2>
	
	<div class="searchByTag col text-center">
	<table class="table">
		<tr>
			<td>
			<c:forEach var="sub" items="${subjectList }" varStatus="idx">
				<button type="button" class="btn btn-light" onclick="reply_click(this.value)" value="${sub.subjectNum }">${sub.subject }</button>
			</c:forEach>
		<script>
	      function reply_click(checked_value){
	  	       self.location = "list" + '${pageMaker.makeQuery(1)}' + "&searchType=tag" + "&subjectNum=" + checked_value;
	      }  
	    </script>
			</td>
		</tr>
	</table>
	</div>
		
	<c:choose>
	<c:when test="${empty studyroomList }">
		<p class="text-center">등록된 스터디가 없습니다.</p>
	</c:when>
	
	<c:when test="${!empty studyroomList}">
	<div class="search col text-end">
	<table class="table">
		<tr>
			<td>
			    <select name="searchType">
			      <option value="n"<c:out value="${scri.searchType == null ? 'selected' : ''}"/>>-----</option>
			      <option value="t"<c:out value="${scri.searchType eq 't' ? 'selected' : ''}"/>>제목</option>
			      <option value="c"<c:out value="${scri.searchType eq 'c' ? 'selected' : ''}"/>>내용</option>
			      <option value="w"<c:out value="${scri.searchType eq 'w' ? 'selected' : ''}"/>>작성자</option>
			      <option value="l"<c:out value="${scri.searchType eq 'l' ? 'selected' : ''}"/>>지역</option>
			      <option value="tc"<c:out value="${scri.searchType eq 'tc' ? 'selected' : ''}"/>>제목+내용</option>
			      <option value="tcl"<c:out value="${scri.searchType eq 'tcl' ? 'selected' : ''}"/>>제목+내용+지역</option>
			    </select>
			
			    <input type="text" name="keyword" id="keywordInput" value="${scri.keyword}"/>
			
			    <button id="searchBtn" type="button">검색</button>
		    </td>
	    </tr>
	  </table>
	    <script>
	      $(function(){
	        $('#searchBtn').click(function() {
	          self.location = "list" + '${pageMaker.makeQuery(1)}' + "&searchType=" + $("select option:selected").val() + "&keyword=" + encodeURIComponent($('#keywordInput').val());
	        });
	      });   
	    </script>
  	</div>
	<table class="table table-striped table-bordered">
		<tr>
			<th scope="col">NO</th>
			<th scope="col">방제목</th>
			<th scope="col">방장</th>
			<th scope="col">현재인원/제한인원</th>
			<th scope="col">과목정보</th>
			<th scope="col">지역</th>
			<th scope="col">작성일</th>
			<th scope="col">조회수</th>
		</tr>
		
		<c:forEach var="studyroom" items="${studyroomList }" varStatus="studyroomNum">
			<tr>
				<th scope="row">${studyroomNum.count}</th>
				<td>
					<a href="<c:url value="/studyroom/read/${studyroom.num}" />">${studyroom.roomName }</a>
				</td>
				<td>${studyroom.memberDto.id}</td>
				<td>${studyroom.totMember}/${studyroom.memberLimit}</td>
				<td>${studyroom.subjectDto.subject }</td>
				<td>${studyroom.local }</td>
				<td>${studyroom.regdate}</td>
				<td>${studyroom.cnt}</td>
			</tr>
		</c:forEach>
		</table>
		</c:when>
		</c:choose>

	<a class="btn btn-primary d-grid gap-2 col-2 mx-auto" href="<c:url value="/studyroom/create" />" role="button">
		스터디룸 만들기
	</a>

<div>
  <ul class="nav justify-content-center">
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