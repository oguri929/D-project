<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page session="false" %>
<html>
<head>
	<title>Home</title>
</head>
<body>
<h1>
	Hello world!  
</h1>
<c:if test="${!empty user}">
	<p> ${user.id}
</c:if>
user= ${user.id}
<P>  The time on the server is ${serverTime}. </P>
</body>
</html>
