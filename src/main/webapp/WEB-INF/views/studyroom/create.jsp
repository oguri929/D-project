<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jstl/core_rt" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>createStudyroom</title>
<script>
	
	function change_sub(obj){
		var add_subject=document.getElementById("add_subject");
		
		var itemValue=obj.options[add_subject.selectedIndex].value;
		alert(itemValue);
		if(itemValue==-1){
			document.getElementById("add_subject").style.display="";
		}else{
			document.getElementById("add_subject").style.display="none";
		}
	}
</script>
</head>
<body>
	<form method="post" action="<c:url value="/studyroom/create"/>">
		<table border="1">
		<tr>
			<th>스터디이름</th>
			<td colspan="4"><input type="text" name="roomName"></td>
		</tr>
		<tr>
			<th>제한인원</th>
			<td><input type="number" name="memberLimit"></td>
			<th>채팅방 비밀번호</th>
			<td><input type="password" name="pw"></td>
		</tr>
		<!-- 
		<tr>
			<th>방장</th>
			<td colspan="4"><input type="text" name="captain"></td>
		</tr>
		 -->
		<tr>
			<th>지역</th>
			<td colspan="4"><input type="text" name="local"></td>
		</tr>
		<tr>
			<th>스터디정보</th>
			<td colspan="4"><textarea name="roomDiscript" cols="100" rows="20"></textarea></td>
		</tr>
		<tr>
			<th>과목정보</th>
			<td colspan="3">
				<select name="subject" onChange="change_sub(this);">
					<c:forEach var="sub" items="${subjectList}">
						<option value="${sub.num }">${sub.subject }</option>
					</c:forEach>
					<option value=1>test</option>
					<option value=-1>기타</option>
				
				</select>
					<input type="text" id="add_subject" name="addSubject" style="display:none;"/>
				<c:forEach var="sub" items="${subjectList}">
					<c:choose>
						<c:when test="${sub.subject eq '기타' }">
							<input type="radio" name="subject" value="${sub.num}">${sub.subject}
							<input type="text" name="addSubject"><br>
						</c:when>
						<c:otherwise>
							<input type="radio" name="subject" value="${sub.num}">${sub.subject}
						</c:otherwise>
					</c:choose>
				</c:forEach>
			</td>
		</tr>
		<tr>
			<td colspan="4">
				<input type="submit" value="등록" />
				<input type="button" value="목록보기" onClick="location.href='<c:url value="/studyroom/list"/>'">
			</td>
		</tr>
		</table>
	</form>
</body>
</html>