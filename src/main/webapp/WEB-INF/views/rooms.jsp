<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>


<head>
<meta charset="UTF-8">
<title>test Chatroom List</title>
</head>
	<body>
        <div class="container">
            <div>
                <ul >
                	<c:forEach var="room" items="${ list}" >
                    <li><a href="<c:url value='/chat/room/${room.roomId}'/>">[[${room.name}]]</a></li>
                	</c:forEach>
                </ul>
            </div>
        </div>
        <form action="<c:url value='/chat/rooms'/>" method="post">
            <input type="text" name="name" class="form-control">
            <button class="btn btn-secondary">개설하기</button>
        </form>
        <script src="//cdnjs.cloudflare.com/ajax/libs/jquery/3.2.1/jquery.min.js"></script>
        <script src="https://cdn.jsdelivr.net/npm/sockjs-client@1/dist/sockjs.min.js"></script>
        <script src="https://cdnjs.cloudflare.com/ajax/libs/stomp.js/2.3.3/stomp.min.js"></script>

		<script type="text/javascript">
            $(document).ready(function(){

                var roomName = ${roomName};

                if(roomName != null)
                    alert(roomName + "방이 개설되었습니다.");

                $(".btn-create").on("click", function (e){
                    e.preventDefault();

                    var name = $("input[name='name']").val();

                    if(name == "")
                        alert("Please write the name.")
                    else
                        $("form").submit();
                });

            });
        </script>
        </body>
</html>