<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
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
    <title>findPassword</title>
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
		
		.form-findpw {
		  width: 100%;
		  max-width: 330px;
		  padding: 15px;
		  margin: auto;
		}
		
		.form-findpw .checkbox {
		  font-weight: 400;
		}
		
		.form-findpw .form-floating:focus-within {
		  z-index: 2;
		}
		
		.form-findpw input[type="text"] {
		  margin-bottom: -1px;
		  border-bottom-right-radius: 0;
		  border-bottom-left-radius: 0;
		}

    </style>
<script>
	$(function(){
		$("#findBtn").click(function(){
			$.ajax({
				url : "../user/findpw.do",
				type : "POST",
				data : {
					id : $("#id").val(),
					email : $("#email").val()
				},
				success : function(result) {
					alert(result);
				},
			})
		});
	})
</script>
</head>
<body class="text-center">
	<main class="form-findpw">	
		<h3 class="h3 mb-3 fw-normal">비밀번호 찾기</h3>			

		<div class="form-floating">
			<input type="text" id="id" name="id" class="form-control" id="floatingInput" placeholder="id" required>
			<label for="floatingInput">Id</label>
		</div>
		<div class="form-floating">
			<input type="text" id="email" name="email" class="form-control" id="floatingPassword" placeholder="password" required>
			<label for="floatingInput">email</label>
		</div>
		<br><br>
		<p>
			<button type="button" id="findBtn" class="w-100 btn btn-lg btn-primary">찾기</button><br><br>
			<a href='<c:url value="/login.do" />'>취소</a>  
		</p>
	</main>

</body>
</html>