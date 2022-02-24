<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<c:set var="contextPath"  value="${pageContext.request.contextPath}"  />
<!DOCTYPE html>
<html>
<head>
	<!-- Required meta tags -->
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1">

    <!-- Bootstrap CSS -->
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-1BmE4kWBq78iYhFldvKuhfTAU6auU8tT94WrHftjDbrCEXSU1oBoqyl2QvZ6jIW3" crossorigin="anonymous">
	<script src="http://code.jquery.com/jquery-latest.js"></script>
    <title>findId</title>
    <style>
      html,body {
		  height: 100%;
		}

		body {
		  display: flex;
		  align-items: center;
		  padding-top: 40px;
		  padding-bottom: 40px;
		  background-color: #f5f5f5;
		}
		
		.form-findId {
		  width: 100%;
		  max-width: 330px;
		  padding: 15px;
		  margin: auto;
		}
		
		.form-findId .checkbox {
		  font-weight: 400;
		}
		
		.form-findId .form-floating:focus-within {
		  z-index: 2;
		}
		
		.form-findId input[type="text"] {
		  margin-bottom: -1px;
		  border-bottom-right-radius: 0;
		  border-bottom-left-radius: 0;
		}
	
    </style>
</head>
<body class="text-center">
	<main class="form-findId">
	<h3 class="h3 mb-3 fw-normal">아이디 찾기</h3>
	<form method="post" action="${contextPath}/user/FindId.do" name="findform">
			<div class="form-floating">
				<input type="text" id="email" name="email" class="form-control" id="floatingInput" placeholder="email" required/>
				<label for="floatingInput">email</label>
			</div>
			
			<div class="form-floating">
				<input type="text" id="birth" name="birth" class="form-control" id="floatingInput" placeholder="생일" required/>
				<label for="birth">생일</label>
			</div>
			<br><br>
	
			<p>
				<input class="w-100 btn btn-lg btn-primary" type="submit" value="아이디 찾기">
			</p>
	
			
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
			<hr>
			<div class="form-label-group">
					<p><a href="../login.do">로그인</a></p>
			</div>
			</c:if>
	
		</form>
</main>
	
	<script type="text/javascript">
		function closethewindow(){
			self.close();
		}
	</script>
</body>
</html>

