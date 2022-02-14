<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<script src="//cdnjs.cloudflare.com/ajax/libs/jquery/3.2.1/jquery.min.js"></script>
<title>editStudyroom</title>
</head>
<script type="text/javascript">
	$(document).ready(function(){
		
		$(".list_btn").on("click", function(){
			event.preventDefault();
			location.href = "/studyroom/list";
		})
	})
</script>
<body>
	<form method="post" action="<c:url value="/studyroom/edit"/>" >
		<table border="1">
		<tr>
			<th>방번호</th>
			<td colspan="4">${studyroomDto.num }</td>
			<input type="hidden" name="num" value="${studyroomDto.num }" />
		</tr>
		<tr>
			<th>스터디이름</th>
			<td colspan="4"><input type="text" name="roomName" value="${studyroomDto.roomName }"></td>
		</tr>
		<tr>
			<th>제한인원</th>
			<td><input type="number" name="memberLimit" value="${studyroomDto.memberLimit }"></td>
			<th>채팅방 비밀번호</th>
			<td><input type="password" name="pw" value="${studyroomDto.pw }"></td>
		</tr>
		<tr>
			<th>방장</th>
			<td colspan="4">${studyroomDto.memberDto.id }</td>
			<input type="hidden" name="captain" value="${studyroomDto.memberDto.num }" />
		</tr>
		<tr>
			<th>지역</th>
			<td colspan="4"><input type="text" name="local" value="${studyroomDto.local }"></td>
		</tr>
		<tr>
			<th>스터디정보</th>
			<td colspan="4"><textarea name="roomDiscript" cols="100" rows="20">${studyroomDto.roomDiscript }</textarea></td>
		</tr>
		<tr>
			<th>과목정보</th>
			<td colspan="3">
				<c:forEach var="sub" items="${subjectList }">
					<c:choose>
						<c:when test="${sub.subjectNum == studyroomDto.subjectNum }">
							<input type="radio" name="subjectNum" value="${sub.subjectNum}" checked>${sub.subject}	
						</c:when>
						<c:otherwise>							
							<input type="radio" name="subjectNum" value="${sub.subjectNum}">${sub.subject}	
						</c:otherwise>
					</c:choose>			
				</c:forEach>
			</td>
		</tr>
		<tr>
			<th>조회수</th>
			<td colspan="4">${studyroomDto.cnt }</td>
		</tr>
		<tr>
			<th>등록일</th>
			<td colspan="4">${studyroomDto.regdate }</td>
		</tr>
		</table>
			<button type="submit" class="edit_btn">수정완료</button>
			<input type="reset" value="다시작성">
			<button type="submit" class="list_btn">목록</button>
	</form>
	<br></br>
	
	현재 가입된 회원 목록
	<table border="1">
		<c:forEach var="mem" items="${memberList }">
			<tr>
				<td>
					${mem.id } <input type="button" value="스터디내보내기" onclick="location.href='<c:url value="/studyroom/leave?memberNum=${mem.num }&chatroomNum=${studyroomDto.num }"/>'">
				</td>
			</tr>
		</c:forEach>
	</table>
	<br></br>
	

</body>
</html>