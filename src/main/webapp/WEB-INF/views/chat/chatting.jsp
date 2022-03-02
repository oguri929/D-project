<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<!DOCTYPE html>
<html>
<head>
<!-- Required meta tags -->
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1">

    <!-- Bootstrap CSS -->
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-1BmE4kWBq78iYhFldvKuhfTAU6auU8tT94WrHftjDbrCEXSU1oBoqyl2QvZ6jIW3" crossorigin="anonymous">
    <script type="text/javascript" src="https://cdnjs.cloudflare.com/ajax/libs/moment.js/2.24.0/moment.min.js"></script>
	<script	src="https://cdnjs.cloudflare.com/ajax/libs/sockjs-client/1.5.2/sockjs.min.js"></script>
	<script	src="https://cdnjs.cloudflare.com/ajax/libs/stomp.js/2.3.3/stomp.min.js"></script>
	<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.6.0/jquery.min.js"></script>
	<script src="https://cdnjs.cloudflare.com/ajax/libs/jquery-modal/0.9.1/jquery.modal.min.js"></script>
	<link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/jquery-modal/0.9.1/jquery.modal.min.css" />
    <title>chatting</title>
	<style>
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
		.text-right{
		text-align:right;
		}

    </style>
</head>
<body>

<div id="chat-container" class="border">
<table class="table">
	<tr>
		<th style="position:sticky;top:0px;"> <h3 class="h3 mb-3 fw-normal text-center" >${roomNo }번방 채팅</h3></td>
	</tr>
	<tr >
		<td>
			<div class="content chatcontent" data-room-nom="${roomNo}"></div>
		</td>
	</tr>
	<tr>
		<td >
		<div id="list-guestbook" class="">
			<c:forEach items="${firstList }" var="chat">
				<c:choose>
					<c:when test="${sessionScope.user.num==chat.sender }">
						<li class="text-right" data-no="${chat.no}">
					</c:when>
					<c:otherwise>
						<li data-no="${chat.no}">
					</c:otherwise>
				</c:choose>
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
		</td>
	</tr>
	<tr>
		<td id="alertTd"  style="position:sticky;bottom:0px;">
			<div class="chat-fix" >
			<div id="alert" onclick="moveDown();" class="alert alert-success" role="alert" style="display:none;">
				<strong></strong>
			</div>
			</div>
		</td>
	</tr>
	<tr>
		<td  id="input" style="position:sticky;bottom:0px;">
			<div style="postion:fixed">	
				<input type="text" name ="msg" id="msgi" class="form-control input-lg" />
				<button type="button" class="send col-sm-2 btn btn-secondary">보내기</button>
				<input type="button" value="파일 올리기" class="col-sm-2 btn btn-secondary" onclick="window.open('<c:url value="/file?roomNo=${roomNo}"/>')"/>
			</div>
		</td>
	</tr>
</table>
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
	//location.replace("div[data-no=156]");
	
	$(window).scrollTop($(".chatcontent")[0].scrollHeight);
	$('#alertTd').css('position','sticky').css('bottom',$('#input').outerHeight());
	
	var chatBox=$(".box");
	var messageInput=$('input[name="msg"]');
	var roomNo="${roomNo}";
	var sock= new SockJS("${pageContext.request.contextPath}/endpoint");
	var endDiv=$("#list-guestbook li").last();
	var startDiv=$("#list-guestbook li").first();
	
	window.scrollTo({top:endDiv.offset().top,
		behavior:'instant'});
	var delta=$(document).height()-$(window).scrollTop();
	var isEnd=false;
	var isScrolled =false;
	
	client=Stomp.over(sock);
	
	client.connect({},function(){
		client.subscribe('/subscribe/chat/'+roomNo,function(chat){
			//alert(chat);
			var content=JSON.parse(chat.body);
			var endNo=content.no;
			var html =renderList(content,endNo);
			var getDelta=$(document).height()-$(window).scrollTop();
			$("#list-guestbook").append(html);
			if(getDelta==delta){
				window.scrollTo({top:endDiv.offset().top,
					behavior:'instant'})
			}else{
				newAlerts(content,endNo);
			}
			
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
			alert("대화의 처음입니다");
			return;
		}
		
		endDiv=$("#list-guestbook li").first();
		
		var endNo=$("#list-guestbook li").first().data("no") || 0;
		$.ajax({
			url : "${pageContext.request.contextPath}/chat/chatList.do?roomNo=${roomNo}"
				+"&endNo="+endNo,
				type : "GET",
				dataType : "json",
				success : function(result){
					
					var length=result.size;
					if(length<10){
						isEnd=true;
					}
					$.each(result,function(index,vo){
						var html=renderList(vo,0);
						$("#list-guestbook").prepend(html);
					})
					
					var position=$('[data-no='+endNo+']').prev().offset();
					console.log(position);
					window.scrollTo({top:position.top,
						behavior:'instant'});
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
		//var date=vo.nowTime;
		var html="";
		tem="";
		var chatMe=0;
		if(endNo==0) endNo=vo.no;
		var content="";
		if("${sessionScope.user.num}"==vo.sender){
			chatMe=1;
		}
		if(vo.dateType=="f"){
			content="<p class='text-muted p-2'><a href=\"<c:url value='/resources/uploadFile'/>/"+vo.sysName+"\" download>"+vo.chatContent+"</a></p>";
		}else{
		content="<p class='text-muted p-2'>"+vo.chatContent+"</p>";
		}
		if(chatMe==1){
			html="<li class='text-right' data-no='"+endNo+"'>"
		}else{
			html="<li data-no='"+endNo+"'>"
		}
		tem="<strong>"+vo.nickname+"</strong>"	
			+"<div>"
			+"<strong style='display:inline;' class='align-self-end'>"+date+ "</strong>"
			+ content
			+"</div>"
			+ "</li>";
		html=html+tem;
		return html;
	}
//	$(window).scroll(function () {//스크를 위치를 반환
//		var height = $(document).scrollTop();
//		if(height<10 && isScrolled ==false){
//			isScrolled = true;
//			fetchList();
//		}
//	});
	
	$(window).scroll(function(){
		var $window=$(this);
		var scrollTop=$window.scrollTop();
		startDiv=$("#list-guestbook li").first();
		if(scrollTop < startDiv.offset().top && isScrolled ==false){
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