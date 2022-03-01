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
	<script src="https://cdn.jsdelivr.net/npm/jquery-validation@1.19.3/dist/jquery.validate.min.js"></script>
	<title>editSubject</title>
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
		
		.error{
		  colkr:red;
		  font-weight:bold;
		}
	</style>
</head>
<script type="text/javascript">
	$(document).ready(function(){	
		var formObj = $("form[name='editForm']");
		
		$(".list_btn").on("click", function(){
			event.preventDefault();
			location.href = "${pageContext.request.contextPath}/studyroom/list";
		})
	})
	
function edit(){
		$.ajax({
			url : "${pageContext.request.contextPath}/subject/edit",
			type : "post",
			dataType : "json",
			data : {
				"subjectNum" : $("#subjectNum").val(),
				"subject" : $("#subject").val()
				},
			success : function(result){
				if(result == 1){
					alert("과목이 수정되었습니다.");
					location.reload();
				}else if(result == 0){
					alert("과목 수정에 오류가 발생하였습니다.");
				}
			},
			error:function(request,status,error){
		        alert("code:"+request.status+"\n"+"message:"+request.responseText+"\n"+"error:"+error);
		    }
		})
	}
</script>
<body class="text-center">
	<h2 class="text-center">분류 추가 및 수정</h2>
	<form name="editForm">
		<table class="table">
			<tr>
				<th>과목번호</th>
				<th>과목명</th>
				<th>수정</th>
			</tr>
			
			<c:forEach var="sub" items="${subjectList }">
			<tr>
				<td>
					${sub.subjectNum }
					<input type="hidden" name="subjectNum" id="subjectNum" value="${sub.subjectNum }"/>					
				</td>
				<td>
					<input type="text" name="subject" id="subject" value="${sub.subject }" />
				</td>
				<td>
					<input type="button" value="수정" class="btn-primary me-md-2" onclick="edit();">
				</td>
			</tr>
			</c:forEach>
			
			<tr class="col text-center">
				<td colsapn="3">
					<button type="button" class="list_btn btn-primary me-md-2">목록</button>
				</td>
			</tr>
		</table>
	</form>
</body>
</html>