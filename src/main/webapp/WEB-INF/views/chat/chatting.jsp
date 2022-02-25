<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<script type="text/javascript" src="https://cdnjs.cloudflare.com/ajax/libs/moment.js/2.24.0/moment.min.js"></script>
<script	src="https://cdnjs.cloudflare.com/ajax/libs/sockjs-client/1.5.2/sockjs.min.js"></script>
<script	src="https://cdnjs.cloudflare.com/ajax/libs/stomp.js/2.3.3/stomp.min.js"></script>
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.6.0/jquery.min.js"></script>
<script src="https://cdnjs.cloudflare.com/ajax/libs/jquery-modal/0.9.1/jquery.modal.min.js"></script>
<link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/jquery-modal/0.9.1/jquery.modal.min.css" />
</head>
<body>
<h1>${roomNo }</h1>
<div id="chat-container" class="border">
	<div class="content chatcontent" data-room-nom="${roomNo}">
		<div id="list-guestbook" class="">
			<c:forEach items="${firstList }" var="chat">
				<li data-no="${chat.no}">
				<strong>${chat.nickname}</strong>
				<div>
					<strong style="display : inline;"><fmt:formatDate value="${chat.sendDate}" pattern="yy/MM/dd HH:mm"/></strong>
					<c:choose>
					<c:when test="${chat.dateType == 'f' }"> 
					<p class="chat text-left p-2"><a href="<c:url value='/resources/uploadFile'/>/${chat.sysName}" download>${chat.chatContent}</a></p>
					</c:when>
					<c:otherwise>
					<p class="chat text-left p-2">${chat.chatContent}</p>
					</c:otherwise>
					</c:choose>
				</div>
				</li>
			</c:forEach>
			
		</div>
	</div>
	<div class="chat-fix">
		<div id="alert" onclick="moveDown();" class="alert alert-success" role="alert">
			<strong></strong>
		</div>
	</div>
	<div class="fix_btn row">
		
		<input type="text" name ="msg" id="msgi" />
		<button type="button" class="send col-sm-4 btn btn-secondary">보내기</button>
		<input type="button" value="파일 올리기" onclick="window.open('<c:url value="/file?roomNo=${roomNo}"/>')"/>
		<p><a href="#ex1" rel="modal:open">파일 올리기</a></p>
		<button type="button" class="submit">파일 올리기</button>
	</div>
	<div id="ex1" class="modal">
	<!-- 
	  <div style="text-align:right; margin-right:170px">
		<a id="goback" href="#" rel="modal:close">돌아가기</a>
	</div>
	<div>	
		<form id="submit" action="<c:url value="/file"/>" enctype="multipart/form-data" method="post" style="text-align:center;">
	    	<input type="file" id="file" name="file1"><p>
	    	<input type="hidden" name="roomNo" value="${roomNo}">
	    	<input type="submit" id="btn" value="파일업로드">
	    	
		</form>
	</div>
	 -->
	</div>
</div>
<!--  <script>
$(function(){
	$("button.submit").on('click',function(){
		$('div.modal').modal({
			remote:'file.jsp'
		});
	});
});
</script>-->
<script>
var client;
function moveDown(){
	$(".chatcontent").scrollTop($(".chatcontent")[0].scrollHeight);
	$("#alert").css('display','none');
	
}

$(document).ready(function(){
	
	$(".chatcontent").scrollTop($(".chatcontent")[0].scrollHeight);
	var chatBox=$(".box");
	var messageInput=$('input[name="msg"]');
	var roomNo="${roomNo}";
	var sock= new SockJS("${pageContext.request.contextPath}/endpoint");
	client=Stomp.over(sock);
	
	client.connect({},function(){
		client.subscribe('/subscribe/chat/'+roomNo,function(chat){
			//alert(chat);
			var content=JSON.parse(chat.body);
			var endNo=content.no;
			var html =renderList(content,endNo);
			$("#list-guestbook").append(html);
			newAlerts(content,endNo);
		});
	});
	
	function sendmsg(){
		
		var message=messageInput.val();
		if(message=="" || message==null){
			return false;
		}
		
		client.send('/app/hello/'+roomNo,{},JSON.stringify({
			chatContent:message,
			srNo:"${roomNo}",
			dateType:"c",
			sender:"${sessionScope.user.num}",
			nickname:"${sessionScope.user.nickname}"
			
		}));
		messageInput.val('');
	}
	
	$('.send').click(function(){
		
		sendmsg();
	});
	$('input[name="msg"]').keydown(function(key){
		if(key.keyCode==13){
			
			sendmsg();
		}
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
		//var date=moment(vo.sendDate).format('YY/MM/DD HH:MM');
		var date=vo.nowTime;
		var html="";
		if(endNo==0) endNo=vo.no;
		var content="";
		if(vo.dateType=="f"){
			content="<p class='text-muted p-2'><a href=\"<c:url value='/resources/uploadFile'/>/"+vo.sysName+"\" download>"+vo.chatContent+"</a></p>";
		}else{
		content="<p class='text-muted p-2'>"+vo.chatContent+"</p>";
		}
		html="<li class='pr-2' data-no='"+endNo+"'>"
			+"<strong>"+vo.nickname+"</strong>"	
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
		$("#alert").html("<strong>"+content.nickname+"</strong>님이 메시지를 보냈습니다.")
	}
	$("#btn").on("click",function(){
		
		var form=$("#file")[0].files[0];
		var formData=new FormData();
		
		$.ajax({
			type:"POST",
			enctype:"multipart/form-data",
			data:formData,
			processData:false,
			contentType:flase,
			cache:false,
			timeout:600000,
			success:function(data){
				$("#goback").click;
			},
			error:function(e){
				alert("파일전송 실패")
			}
			
		});
	});
});
</script>
</body>
</html>