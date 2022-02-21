<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<c:set var="contextPath"  value="${pageContext.request.contextPath}"  />
<!DOCTYPE html>
<html>
<head>
	<script src="//cdnjs.cloudflare.com/ajax/libs/jquery/3.2.1/jquery.min.js"></script>
	<title>회원가입 하기</title>
</head>
<script type="text/javascript">
		$(document).ready(function(){
			// 취소
			$(".cencle").on("click", function(){
				location.href = "/";
			})
			
			$("#submit").on("click", function(){
				if($("#id").val()==""){
					alert("아이디를 입력해주세요.");
					$("#id").focus();
					return false;
				}
				if($("#pw").val()==""){
					alert("비밀번호를 입력해주세요.");
					$("#pw").focus();
					return false;
				}
				if($("#name").val()==""){
					alert("이름을 입력해주세요.");
					$("#name").focus();
					return false;
				}
				var idChkVal = $("#idCheck").val();
				if(idChkVal == "N"){
					alert("중복확인 버튼을 눌러주세요.");
				}else if(idChkVal == "Y"){
					$("#regForm").submit();
				}
			});
		})
		
		function fn_idChk(){
			$.ajax({
				url : "${pageContext.request.contextPath}/user/idCheck.do",
				type : "post",
				dataType : "json",
				data : {"id" : $("#id").val()},
				success : function(data){
					if(data == 1){
						alert("중복된 아이디입니다.");
						
						$("#submit").attr("disabled","disabled");
					}else if(data == 0){
						$("#idCheck").attr("value", "Y");
						alert("사용가능한 아이디입니다.");
						
						$("#submit").removeAttr("disabled");
					}
				}
			})
		}
	</script>
<body>
<h1>
	회원가입
</h1>

<form action="${contextPath}/user/register.do" method="post">

	<p>
		<label for="id">아이디</label>
		<input type="text" id="id" name="id"/>
		<button class="idCheck" type="button" id="idCheck" onclick="fn_idChk();" value="N">중복확인</button>
	</p>
	<p>
		<label for="pw">비밀번호</label>
		<input type="password" id="pw" name="pw"/>
	</P>
	<p>
		<label for="name">이름</label>
		<input type="text" id="name" name="name"/>
	
	</P>
	<p>
		<label for="nickname">닉네임</label>
		<input type="text" id="nickname" name="nickname"/>
	</P>	
	<p>
		<label for="gender">성별</label>
		<input type="radio" name="gender" value="102" />
		여성<span style="padding-left:120px"></span>
		<input type="radio" name="gender" value="102" checked />남성
	</p>
	<p>
		<label for="local">지역</label>
		<input type="text" id="local" name="local"/>
	</P>	
	<p>
		<label for="birth">생일</label>
		<input type="text" id="birth" name="birth"/>
	</P>
	<p>
		<label for="email">이메일</label>
		<input type="text" id="email" name="email"/>
	</P>
		
	<button type="submit" id="submit" disabled="disabled">회원가입</button>
	<p><a href="../login.do">로그인하러가기</a></p>
</form>
</body>
</html>