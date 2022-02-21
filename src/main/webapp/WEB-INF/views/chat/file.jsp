<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jstl/core_rt" %>
    
<!DOCTYPE html>
<html>
<head>
	<meta charset="UTF-8">
	<title>파일 업로드</title>
	<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>
	<script src="https://cdnjs.cloudflare.com/ajax/libs/sockjs-client/1.4.0/sockjs.js"></script>
	
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
	<div style="text-align:right; margin-right:170px">
		<a href="<c:url value="/chat"/>">돌아가기</a>
	</div>
	
	<form action="<c:url value="/file"/>" enctype="multipart/form-data" method="post" style="text-align:center;">
    	<input type="file" name="file1"><p>
    	<input type="submit" value="파일업로드">
	</form>
</body>
</html>