<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jstl/core_rt" %>
    
<!DOCTYPE html>
<html>
<head>
	<meta charset="UTF-8">
	<title>파일 업로드</title>
	<script	src="https://cdnjs.cloudflare.com/ajax/libs/sockjs-client/1.5.2/sockjs.min.js"></script>
	<script	src="https://cdnjs.cloudflare.com/ajax/libs/stomp.js/2.3.3/stomp.min.js"></script>
	<script type="text/javascript" src="https://ajax.googleapis.com/ajax/libs/jquery/3.6.0/jquery.min.js"></script>
	
</head>
<body>
	<div style="text-align:right; margin-right:170px">
		<a href="<c:url value="/chat"/>">돌아가기</a>
	</div>
	
	<form id="submit" action="<c:url value="/file"/>" enctype="multipart/form-data" method="post" style="text-align:center;">
    	<input type="file" name="file1"><p>
    	<input type="hidden" name="roomNo" value="${roomNo}">
    	<input type="submit" id="btn" value="파일업로드">
    	
	</form>
	<script>
	
	$(document).ready(function(){
		alert("file");
		$("#btn").on("click",function(){
			alert("send");
			$.ajax({
				url:'<c:url value="/file"/>',
				data:'multipart/form-data',
				success:function(xh){
					alert("success");
					window.close();
					this.opener.document.focus();
				}
			});
		});	
	});
	</script>
	<!--  
	<script>
	alert("test3");
	$(document).ready(function(){
		var sock=new SockJS("${pageContext.request.contextPath}/endpoint")
		var roomNo="${roomNo}";
		var message=$("input[name='file1']").val();
		client=stomp.over(sock);
		client.connect({},function(){
			client.subscribe('/subscribe/chat/'+roomNo,function(chat){
				window.close();
				this.opener.document.focus();
				
			});
		});
		
		
		function sendmsg(){
			client.send('/app/hello/'+roomNo,{},JSON.stringify({
				chatContent:message,
				srNo:"${roomNo}",
				dateType:"f",
				sender:"${sessionScope.user.num}",
				nickname:"${sessionScope.user.nickname}"
				
			}));
			messageInput.val('');
		}
		function goBack(){
			window.close();
			this.opener.document.focus();
		}
		$("button[id='btn']").click(function{
			sendmsg();
			$(#submit).submit();
			
			
			
		});
		function disconnect(){
			if(client !=null){
				client.disconnect();
			}
		}
		window.onbeforenuload=function(e){
			disconnect();
		}
		function closeConnection() {
			sock.close();
		}
		
	});
	</script>-->
</body>
</html>