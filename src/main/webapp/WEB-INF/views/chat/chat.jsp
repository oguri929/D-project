<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jstl/core_rt" %>

<!DOCTYPE html>
<html>
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
	
	<title>채팅 프로그램</title>
	<!--src 이 세개로 바꾸기  -->
	<script	src="https://cdnjs.cloudflare.com/ajax/libs/sockjs-client/1.5.2/sockjs.min.js"></script>
	<script	src="https://cdnjs.cloudflare.com/ajax/libs/stomp.js/2.3.3/stomp.min.js"></script>
	<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.6.0/jquery.min.js"></script>
	<!-- script 내용 아래 하나로 만들기 -->
	<script type="text/javascript">
	var client;
	function moveDown(){
		$("#divCahData").scrollTop($("#divCahData")[0].scrollHeight);
		$("#alert").css('display','none');
		
	}
	$(document).ready(function(){
		$("#test1").css("color","red");
		$("#divCahData").scrollTop($("#divCahData")[0].scrollHeight);
		
		
		var chatBox=$(".box");
		var messageInput=$('textarea[name="msg"]');
		var roomNo="${roomNo}";
		var sock= new SockJS("${pageContext.request.contextPath}/endpoint");
		client=Stomp.over(sock);
		
		client.connect({},function(){
			client.subscribe('/subscribe/chat/'+roomNo,function(chat){
				alert(chat);
				var content=JSON.parse(chat.body);
				var endNo=content.no;
				var html =renderList(content,endNo);
				$("#list-guestbook").append(html);
				newAlerts(content,endNo);
			});
		});
		
		function sendmsg(){
			
			var message=messageInput.val();
			if(message==""){
				return false;
			}
			
			client.send('/app/hello/'+roomNo,{},JSON.stringify({
				chatContent:message,
				srNo:"${roomNo}",
				memberId:"me~long"
				
			}));
			messageInput.val('');
		}
		
		$('.send').click(function(){
			
			sendmsg();
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
		
		
		
		var isEnd=false;
		var isScrolled =false;
		var fetchList= function(){
			if(isEnd==true){
				return;
			}
			var endNo=$("#list-guestbook li").first().data("no") || 0;
			console.log("endNo" +endNo);
			$.ajax({
				url : "${pageContext.request.contextPath}/chat/chatList.do?endNo"
					+endNo+"&roomNo=${roomNo}",
					type : "GET",
					dataType : "json",
					success : function(result){
						alert("json");
						var lenth=result.size;
						if(length<10){
							isEnd=true;
						}
						$.each(result,function(index,vo){
							var html=renderList(vo,0);
							$("#list-guestbook").prepend(html);
						})
						
						var position=$('[data-no='+endNo+']').prev().offset();
						console.log(position);
						document.querySelector('.chatcontent').scrollTo({top:position.top,
							behavior:'auto'});
						isScrolled=false;
					},
					error : function(xhr,status,err){
						console.log("처리실패!");
						console.log(xhr);
						console.log(status);
						console.log(err);
					}
			});
			
		}
		
		var renderList=function(vo,endNo){
			var date=moment(vo.sendDate).format('YY/MM/DD HH:MM');
			var html="";
			if(endNo==0) endNo=vo.no;
			var content="";
			content="<p class='text-muted p-2'>"+vo.chatContent+"</p>";
			alert(vo.chatContent);
			html="<li class='pr-2' data-no='"+endNo+"'>"
				+"<strong>"+vo.memberId+"</strong>"	
				+"<div>"
				+"<strong style='display:inline;' class='align-self-end'>"+date+ "</strong>"
				+ content
				+"</div>"
				+ "</li>";
			return html;
		}
		$(".chatcontent").scroll(function(){
			var $window=$(this);
			var scrollTop=$window.scrollTop();
			var windowheight=$window.hegith();
			var documentHeight=$(document).height();
			
			if(scrollTop < 1 && isScrolled ==false){
				isScrolled = true;
				fetchList();
			}
			
		});
		function newAlerts(content,endNo){
			$("#alert").css('display','block');
			$("#alert").html("<strong>"+content.memberId+"</strong>님이 메시지를 보냈습니다.")
		}
		
	</script>	
</head>
<body>
	<form action="<c:url value='/solveQuiz'/>">
		<input type="hidden" name="roomNum" value="${roomNum }"/>
		<input type="submit" value="퀴즈 풀기">
	</form>
	<div style="text-align:right; margin-right:170px">
		<a href="<c:url value="/studyroom/list"/>">채팅방 나가기</a>
	</div>
	
	<div style="width: 1200px; height: 500px; padding: 10px; border: solid 1px #e1e3e9; margin-left:150px;">
		<div id="divChatData"></div>
	</div>
	
	<div style="margin-left:160px;">
	 <input type="text" id="message" size="150" onkeypress="if(event.keyCode==13){webSocket.sendChat();}" />
	 <input type="button" id="btnSend" value="채팅 전송" onclick="webSocket.sendChat();" />
	 <input type="button" value="파일 올리기" onclick="location.href='<c:url value="/file"/>'"/>
	 <input type="button" value="파일 올리기" onclick="window.open('<c:url value="/file"/>')"/>
	</div>
</body>
</html>