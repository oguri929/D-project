<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
	<!-- Required meta tags -->
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1">

    <!-- Bootstrap CSS -->
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-1BmE4kWBq78iYhFldvKuhfTAU6auU8tT94WrHftjDbrCEXSU1oBoqyl2QvZ6jIW3" crossorigin="anonymous">
	<script src="//cdnjs.cloudflare.com/ajax/libs/jquery/3.2.1/jquery.min.js"></script>
	<title>deleteMember</title>
	<style type="text/css">
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
		
		.form-delMem {
		  width: 100%;
		  max-width: 330px;
		  padding: 15px;
		  margin: auto;
		}
		
		.form-delMem .checkbox {
		  font-weight: 400;
		}
		
		.form-delMem .form-floating:focus-within {
		  z-index: 2;
		}
		
		.form-delMem input[type="text"] {
		  margin-bottom: -1px;
		  border-bottom-right-radius: 0;
		  border-bottom-left-radius: 0;
		}
		
		.form-delMem input[type="password"] {
		  margin-bottom: -1px;
		  border-bottom-right-radius: 0;
		  border-bottom-left-radius: 0;
		}
	</style>
</head>
<body class="text-center">
	<main class="form-delMem">
	<h3 class="h3 mb-3 fw-normal">탈퇴하기</h3>
	<form action="<c:url value="/user/deleteMember"/>" method="post">
		<div class="form-floating">
			<input type="text" name="id" class="form-control" id="floatingInput" placeholder="Id" required />
			<label for="floatingInput">Id</label>
		</div>
		<div class="form-floating">
			<input type="password" name="pw" class="form-control" id="floatingInput" placeholder="Password" required />
			<label for="floatingInput">Password</label>
		</div>
		<br><br>
		<p>
			<input class="w-100 btn btn-lg btn-primary" type="submit" value="회원탈퇴" />
		</p>
	</form>
</main>
</body>
</html>