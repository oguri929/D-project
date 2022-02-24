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
	<script src="//cdnjs.cloudflare.com/ajax/libs/jquery/3.2.1/jquery.min.js"></script>
	<title>createStudyroom</title>
	<style type="text/css">
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
	</style>
</head>
<body>
<header>
<!-- Navigation-->
	<nav class="navbar navbar-expand-md navbar-dark fixed-top bg-dark">
	    <div class="container px-5">
	        <a class="navbar-brand">StudyMatch</a>
	        <button class="navbar-toggler" type="button" data-bs-toggle="collapse" data-bs-target="#navbarResponsive" aria-controls="navbarResponsive" aria-expanded="false" aria-label="Toggle navigation"><span class="navbar-toggler-icon"></span></button>
	        <div class="collapse navbar-collapse" id="navbarResponsive">
	            <ul class="navbar-nav ms-auto">
	                <li class="nav-item">
	      <a class="nav-link active" aria-current="page" href="<c:url value='/'/>">홈</a>
	    </li>
	    <li class="nav-item">
	      <a class="nav-link" href="<c:url value='/studyroom/list'/>">스터디</a>
	    </li>
	    <li class="nav-item">
	      <a class="nav-link" href="<c:url value='/listQuiz'/> ">퀴즈</a>
	    </li>
	    <li class="nav-item">
	      <a class="nav-link" href="<c:url value='/mypage.do'/>">마이룸</a>
	    </li>
	    <c:choose>
	    	<c:when test="${!empty user }">
	    		<li class="nav-item">
	    			${user.id}
	    		</li>
	     	<li class="nav-item">
	        <a class="nav-link" href="<c:url value='/logout.do'/>">로그아웃</a>
	      </li>
	    	</c:when>
	    	<c:otherwise>
	    		<li class="nav-item">
	        <a class="nav-link" href="<c:url value='/login.do'/>">로그인</a>
	      </li>
	    	</c:otherwise>
	    </c:choose>
	            </ul>
	        </div>
	    </div>
	</nav>
</header>
	<h2 class="text-center">스터디 만들기</h2>
	
	<form method="post" action="<c:url value="/studyroom/create"/>">
		<table class="table table-bordered">
		<tr>
			<th scope="row" class="w-25 p-3">스터디이름</th>
			<td colspan="4"><input type="text" name="roomName"></td>
		</tr>
		<tr>
			<th scope="row" class="w-25 p-3">제한인원</th>
			<td><input type="number" name="memberLimit"></td>
			<th scope="row" class="w-25 p-3">채팅방 비밀번호</th>
			<td><input type="password" name="pw"></td>
		</tr>
		<tr>
			<th scope="row" class="w-25 p-3">방장</th>
			<td colspan="4">${sessionScope.user.id}</td>
							<input type="hidden" name="captain" value="${sessionScope.user.num }" />
		</tr>
		<tr>
			<th scope="row" class="w-25 p-3">지역</th>
			<td colspan="4">
				<select name="h_area1" id="select1">
					  <option>-선택-</option>
					  <option>서울</option>
					  <option>부산</option>
					  <option>대구</option>
					  <option>인천</option>
					  <option>광주</option>
					  <option>대전</option>
					  <option>울산</option>
					  <option>강원</option>
					  <option>경기</option>
					  <option>경남</option>
					  <option>경북</option>
					  <option >전남</option>
					  <option>전북</option>
					  <option>제주</option>
					  <option>충남</option>
					  <option>충북</option>
				</select>
				<select name="h_area2" id="select2">
					  <option>-선택-</option>
				</select>
			</td>
		</tr>
		<tr>
			<th scope="row" class="w-25 p-3">스터디정보</th>
			<td colspan="4"><textarea name="roomDiscript" cols="100" rows="15"></textarea></td>
		</tr>
		<tr>
			<th scope="row" class="w-25 p-3">과목정보</th>
			<td colspan="3">
				<c:forEach var="sub" items="${subjectList}">
					<input type="radio" name="subjectNum" value="${sub.subjectNum}">${sub.subject}
				</c:forEach>
			</td>
		</tr>
		<tr class="col text-center">
			<td colspan="4">
				<input type="submit" value="등록" class="btn-primary me-md-2" />
				<input type="button" value="목록보기" class="btn-primary me-md-2" onClick="location.href='<c:url value="/studyroom/list"/>'">
			</td>
		</tr>
		</table>
	</form>
<script type="text/javascript">
 	$(function() {

        $('#select1').change(function() {

            var seoul = ['강남구','강동구','강북구','강서구','관악구','광진구','구로구','금천구','노원구','도봉구','동대문구','동작구','마포구','서대문구','서초구','성동구','성북구','송파구','양천구','영등포구','용산구','은평구','종로구','중구','중랑구'];
            var busan = ['강서구','금정구','남구','동구','동래구','부산진구','북구','사상구','사하구','서구','수영구','연제구','영도구','중구','해운대구','기장군'];
			var daegu = ['남구','달서구','동구','북구','서구','수성구','중구','달성군'];
			var incheon = ['계양구','남구','남동구','동구','부평구','서구','연수구','중구','강화군','옹진군'];
            var gwangju = ['광산구','남구','동구','북구','서구'];
            var daejeon = ['대덕구','동구','서구','유성구','중구'];
            var ulsan = ['남구','동구','북구','중구','울주군'];
            var gangwon = ['강릉시','동해시','삼척시','속초시','원주시','춘천시','태백시','고성군','양구군','양양군','영월군','인제군','정선군','철원군','평창군','홍천군','화천군','횡성군'];
            var gyeonggi = ['고양시 덕양구','고양시 일산구','과천시','광명시','광주시','구리시','군포시','김포시','남양주시','동두천시','부천시 소사구','부천시 오정구','부천시 원미구','성남시 분당구','성남시 수정구','성남시 중원구','수원시 권선구','수원시 장안구','수원시 팔달구','시흥시','안산시 단원구','안산시 상록구','안성시','안양시 동안구','안양시 만안구','오산시','용인시','의왕시','의정부시','이천시','파주시','평택시','하남시','화성시','가평군','양주군','양평군','여주군','연천군','포천군'];
            var gyeongnam = ['거제시','김해시','마산시','밀양시','사천시','양산시','진주시','진해시','창원시','통영시','거창군','고성군','남해군','산청군','의령군','창녕군','하동군','함안군','함양군','합천군'];
            var gyeongbuk = ['경산시','경주시','구미시','김천시','문경시','상주시','안동시','영주시','영천시','포항시 남구','포항시 북구','고령군','군위군','봉화군','성주군','영덕군','영양군','예천군','울릉군','울진군','의성군','청도군','청송군','칠곡군'];
            var jeollanam = ['광양시','나주시','목포시','순천시','여수시','강진군','고흥군','곡성군','구례군','담양군','무안군','보성군','신안군','영광군','영암군','완도군','장성군','장흥군','진도군','함평군','해남군','화순군'];
            var jeollabuk = ['군산시','김제시','남원시','익산시','전주시 덕진구','전주시 완산구','정읍시','고창군','무주군','부안군','순창군','완주군','임실군','장수군','진안군'];
            var jeju = ['서귀포시','제주시','남제주군','북제주군'];
            var chungnam = ['공주시','논산시','보령시','서산시','아산시','천안시','금산군','당진군','부여군','서천군','연기군','예산군','청양군','태안군','홍성군'];
            var chungbuk = ['제천시','청주시 상당구','청주시 흥덕구','충주시','괴산군','단양군','보은군','영동군','옥천군','음성군','진천군','청원군'];
            
            var changeItem;

            if (this.value == "서울") {
                changeItem = seoul;
            } else if (this.value == "부산") {
                changeItem = busan;
            } else if (this.value == "대구") {
                changeItem = busan;
            } else if (this.value == "인천") {
                changeItem = incheon;
            } else if (this.value == "광주") {
                changeItem = gwangju;
            } else if (this.value == "대전") {
                changeItem = daejeon;
            } else if (this.value == "울산") {
                changeItem = ulsan;
            } else if (this.value == "강원") {
                changeItem = gangwon;
            } else if (this.value == "경기") {
                changeItem = gyeonggi;
            } else if (this.value == "경남") {
                changeItem = gyeongnam;
            } else if (this.value == "경북") {
                changeItem = gyeongbuk;
            } else if (this.value == "전남") {
                changeItem = jeollanam;
            } else if (this.value == "전북") {
                changeItem = jeollabuk;
            } else if (this.value == "제주") {
                changeItem = jeju;
            } else if (this.value == "충남") {
                changeItem = chungnam;
            } else if (this.value == "충북") {
                changeItem = chungbuk;
            }

            $('#select2').empty();

            for (var count = 0; count < changeItem.length; count++) {

                var option = $("<option>" + changeItem[count] + "</option>");

                $('#select2').append(option);
            }
        });
    });
</script>
</body>
</html>