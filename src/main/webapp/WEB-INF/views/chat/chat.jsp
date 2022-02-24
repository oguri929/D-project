<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jstl/core_rt" %>

<!DOCTYPE html>
<html>
<head>
	<!-- Required meta tags -->
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1">

    <!-- Bootstrap CSS -->
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-1BmE4kWBq78iYhFldvKuhfTAU6auU8tT94WrHftjDbrCEXSU1oBoqyl2QvZ6jIW3" crossorigin="anonymous">
	<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>
	<script src="https://cdnjs.cloudflare.com/ajax/libs/sockjs-client/1.4.0/sockjs.js"></script>
	<title>chat</title>
	<style type="text/css">
		.quiz_btn{
			position:absolute;
			top:30px;
			left:40px;
		}
	</style>
	<script type="text/javascript">
		var webSocket = {
			init: function(param) {
				this._url = param.url;
				this._initSocket();
			},
			sendChat: function() {
				this._sendMessage($('#message').val());
				$('#message').val('');
				
			},
			receiveMessage: function(str) {
				$('#divChatData').append('<div>' + str + '</div>');				
			},
			closeMessage: function(str) {
				$('#divChatData').append('<div>' + '연결 끊김 : ' + str + '</div>');
			},
			disconnect: function() {
				this._socket.close();
			},
			_initSocket: function() {
				this._socket = new SockJS(this._url);
				this._socket.onmessage = function(evt) {
					webSocket.receiveMessage(evt.data);
				};
				this._socket.onclose = function(evt) {
					webSocket.closeMessage(evt.data);
				}
			},
			_sendMessage: function(str) {
				this._socket.send(str);
			}
		};
	</script>	
	<script type="text/javascript">
		$(document).ready(function() {
			webSocket.init({ url: '<c:url value="/chat" />' });			
		});
	</script>
</head>
<body>
	<div class="quiz_btn">
	<form action="<c:url value='/solveQuiz'/>">
		<input type="hidden" name="roomNum" value="${roomNum }"/>
		<input type="submit" class="btn btn-primary" value="퀴즈 풀기">
	</form>
	</div>
	
	<div style="text-align:right; margin-right:170px">
		<a href="<c:url value="/studyroom/list"/>">채팅방 나가기</a>
	</div>
	
	<div style="width: 1200px; height: 500px; padding: 10px; border: solid 1px #e1e3e9; margin-left:150px;">
		<div id="divChatData"></div>
	</div>
	
	<div style="margin-left:160px;">
		 <input type="text" id="message" size="150" onkeypress="if(event.keyCode==13){webSocket.sendChat();}" />
		 <input type="button" class="btn-primary me-md-2" id="btnSend" value="채팅 전송" onclick="webSocket.sendChat();" />
	</div>
</body>
</html>