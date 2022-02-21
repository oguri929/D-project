<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<c:set var="contextPath"  value="${pageContext.request.contextPath}"  />
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>아이디 찾기</title>
</head>
<body>
	<h2>아이디 찾기</h2>
	<h3>이메일과 생년월일을 입력해주세요</h3>
<form method="post" class="form-signin" action="${contextPath}/user/FindId.do" name="findform">
		<div class="form-label-group">
			<label for="email">이메일</label>
			<input type="text" id="email" name="email" class="form-control"/>
		</div>
		
		<div class="form-label-group">
			<label for="birth">생년월일</label>
			<input type="text" id="birth" name="birth" class="form-control"/>
		</div>

		<div class="form-label-group">
			<input class="btn btn-lg btn-secondary btn-block text-uppercase"
				type="submit" value="아이디 찾기">
		</div>

		
		<c:if test="${check == 1}">
			<script>
				opener.document.findform.email.value = "";
				opener.document.findform.birth.value = "";
			</script>
			<label>일치하는 정보가 존재하지 않습니다.</label>
			<hr>
			  <a href="../login.do">로그인 화면으로 돌아가기</a>
		</c:if>

		
		<c:if test="${check == 0 }">
		<label>찾으시는 아이디는 '${id}' 입니다.</label>
		<div class="form-label-group">
				<p><a href="../login.do">로그인</a></p>
		</div>
		</c:if>

	</form>
	
	<script type="text/javascript">
		function closethewindow(){
			self.close();
		}
	</script>
</body>
</html>

