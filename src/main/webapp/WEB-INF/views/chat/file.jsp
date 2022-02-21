<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jstl/core_rt" %>
    
<!DOCTYPE html>
<html>
<head>
	<meta charset="UTF-8">
	<title>파일 업로드</title>
</head>
<body>
	<div style="text-align:right; margin-right:170px">
		<a href="<c:url value="/chat"/>">돌아가기</a>
	</div>
	
	<form action="<c:url value="/chat"/>" enctype="multipart/form-data" method="post" style="text-align:center;">
    	<input type="file" name="file1"><p>
    	<input type="submit" value="파일업로드">
	</form>
</body>
</html>