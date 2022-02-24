<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"
	 isELIgnored="false" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<c:set var="contextPath"  value="${pageContext.request.contextPath}"  />
<!DOCTYPE html >
<html>
<head>
	<!-- Required meta tags -->
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1">

    <!-- Bootstrap CSS -->
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-1BmE4kWBq78iYhFldvKuhfTAU6auU8tT94WrHftjDbrCEXSU1oBoqyl2QvZ6jIW3" crossorigin="anonymous">
    <title>login</title>
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
		
		.form-signin {
		  width: 100%;
		  max-width: 330px;
		  padding: 15px;
		  margin: auto;
		}
		
		.form-signin .checkbox {
		  font-weight: 400;
		}
		
		.form-signin .form-floating:focus-within {
		  z-index: 2;
		}
		
		.form-signin input[type="text"] {
		  margin-bottom: -1px;
		  border-bottom-right-radius: 0;
		  border-bottom-left-radius: 0;
		}
		
		.form-signin input[type="password"] {
		  margin-bottom: 10px;
		  border-top-left-radius: 0;
		  border-top-right-radius: 0;
		}
    </style>
</head>
<body class="text-center">
	<main class="form-signin">
		<form action="${contextPath}/login.do" method="post">
			<img class="mb-4" src="#" alt="" width="72" height="57">
		    <h1 class="h3 mb-3 fw-normal">로그인을 해주세요</h1>
	    
			<div class="form-floating">
				<input type="text" class="form-control" id="floatingInput" name="id" size="20" placeholder="id">
				<label for="floatingInput">Id</label>
			</div>
			<div class="form-floating">
		      <input type="password" class="form-control" id="floatingPassword" size="20" name="pw" placeholder="password">
		      <label for="floatingPassword">Password</label>
	    	</div>
			<br><br>
			<button class="w-100 btn btn-lg btn-primary" type="submit">로그인</button>
			
			<br><br>
			   <a href="${contextPath}/user/GoFindId.do">아이디 찾기</a>  | 
			   <a href="${contextPath}/user/findpw.do">비밀번호 찾기</a> | 
			   <a href="${contextPath}/user/register.do">회원가입</a>  
						   
		</form>	
	</main>
</body>
</html>