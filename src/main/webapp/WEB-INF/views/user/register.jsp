<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<c:set var="contextPath"  value="${pageContext.request.contextPath}"  />
<!DOCTYPE html>
<html>
<head>
	<script src="//cdnjs.cloudflare.com/ajax/libs/jquery/3.2.1/jquery.min.js"></script>
	<title>회원가입 하기</title>
</head>
<script type="text/javascript">
		$(document).ready(function(){
			// 취소
			$(".cencle").on("click", function(){
				location.href = "/";
			})
			
			$("#submit").on("click", function(){
				if($("#id").val()==""){
					alert("아이디를 입력해주세요.");
					$("#id").focus();
					return false;
				}
				if($("#pw").val()==""){
					alert("비밀번호를 입력해주세요.");
					$("#pw").focus();
					return false;
				}
				if($("#name").val()==""){
					alert("이름을 입력해주세요.");
					$("#name").focus();
					return false;
				}
				var idChkVal = $("#idCheck").val();
				if(idChkVal == "N"){
					alert("중복확인 버튼을 눌러주세요.");
				}else if(idChkVal == "Y"){
					$("#regForm").submit();
				}
			});
		})
		
		function fn_idChk(){
			$.ajax({
				url : "${pageContext.request.contextPath}/user/idCheck.do",
				type : "post",
				dataType : "json",
				data : {"id" : $("#id").val()},
				success : function(data){
					if(data == 1){
						alert("중복된 아이디입니다.");
						
						$("#submit").attr("disabled","disabled");
					}else if(data == 0){
						$("#idCheck").attr("value", "Y");
						alert("사용가능한 아이디입니다.");
						
						$("#submit").removeAttr("disabled");
					}
				}
			})
		}
	</script>
<body>
<h1>
	회원가입
</h1>

<form action="${contextPath}/user/register.do" method="post">

	<p>
		<label for="id">아이디</label>
		<input type="text" id="id" name="id"/>
		<button class="idCheck" type="button" id="idCheck" onclick="fn_idChk();" value="N">중복확인</button>
	</p>
	<p>
		<label for="pw">비밀번호</label>
		<input type="password" id="pw" name="pw"/>
	</P>
	<p>
		<label for="name">이름</label>
		<input type="text" id="name" name="name"/>
	
	</P>
	<p>
		<label for="nickname">닉네임</label>
		<input type="text" id="nickname" name="nickname"/>
	</P>	
	<p>
		<label for="gender">성별</label>
		<input type="radio" name="gender" value="102" />
		여성<span style="padding-left:120px"></span>
		<input type="radio" name="gender" value="102" checked />남성
	</p>
	<p>
		<label for="local">지역</label>
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
	</P>	
	<p>
		<label for="birth">생일</label>
		<input type="text" id="birth" name="birth"/>
	</P>
	<p>
		<label for="email">이메일</label>
		<input type="text" id="email" name="email"/>
	</P>
		
	<button type="submit" id="submit" disabled="disabled">회원가입</button>
	<p><a href="../login.do">로그인하러가기</a></p>
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