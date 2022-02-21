<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1">
<script src="http://code.jquery.com/jquery-latest.js"></script>
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
<title>비밀번호 찾기</title>
</head>
<body>	
				<h3>비밀번호 찾기</h3>			
			<div>
				<p>
					<label>아이디</label>
					<input type="text" id="id" name="id" required>
				</p>
				<p>
					<label>이메일</label>
					<input type="text" id="email" name="email" required>
				</p>
				<p>
					<button type="button" id="findBtn">찾기</button>
					<button type="button" onclick="history.go(-1);" >취소</button>
				</p>
			</div>

</body>
</html>