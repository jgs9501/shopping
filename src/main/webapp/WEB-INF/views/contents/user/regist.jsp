<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<% request.setCharacterEncoding("UTF-8"); %>
<c:set var="contextPath" value="${pageContext.request.contextPath}" />

<!DOCTYPE html>
<html>
<head>
	<meta charset="UTF-8">
	<meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
	<!-- Bootstrap CSS -->
	<link rel="stylesheet" href="${contextPath}/resources/css/bootstrap.css">
	<script src="${contextPath}/resources/js/jquery.min.js"></script>
	<script src="${contextPath}/resources/js/bootstrap.min.js"></script>
	<title>회원가입</title>
	<style type="text/css">
		.center-auto{
			margin-left: auto;
			margin-right: auto;
		}
		
		.form-width{
			min-width: 700px;
			width: 800px;
			position: relative;
		}
		.padding-left{
			padding-left: 50px;
		}
	</style>
	
	
</head>
<body>
	<header>
		<jsp:include page="/WEB-INF/views/header/header.jsp"></jsp:include>
	</header>
	
	<ol class="breadcrumb container">
		<li><a href="${contextPath}/index">메인</a></li>
		<li class="active">회원가입</li>
	</ol>
	<div class="container">
	<section>
		<form class="form-horizontal form-width center-auto" method="post" onsubmit="return checkInfo()">
			<!-- 아이디 -->
	    	<div id="inputId" class="form-group has-feedback">
		    	<label class="col-sm-2 control-label">아이디</label>
	    		<div class="col-sm-10">
		    		<input type="text" class="form-control" aria-describedby="inputIdStatus" id="user_id" name="user_id" placeholder="영문 포함 6-13자리를 입력해주세요" maxlength="13">
		    		<span id="inputIdIcon" class="glyphicon form-control-feedback" aria-hidden="true"></span>
      				<span id="inputIdStatus" class="sr-only"></span>
		    	</div>
		    	<span id="spanId"></span>
	  		</div><hr>
			<!-- 비밀번호 -->
	  		<div id="inputPw" class="form-group has-feedback">
	    		<label for="inputPassword" class="col-sm-2 control-label">비밀번호</label>
	    		<div class="col-sm-10">
	      			<input type="password" class="form-control" aria-describedby="inputPwStatus" id="password" name="password" placeholder="영문 숫자포함 6자리이상 입력해주세요" maxlength="13">
	      			<span id="inputPwIcon" class="glyphicon form-control-feedback" aria-hidden="true"></span>
      				<span id="inputPwStatus" class="sr-only"></span>
	    		</div>
	    		<span id="spanPassword"></span>
	  		</div><hr>
			<!-- 비밀번호 확인 -->
	  		<div id="inputCheckPw" class="form-group has-feedback">
	    		<label for="inputPasswordCheck" class="col-sm-2 control-label">비밀번호 확인</label>
	    		<div class="col-sm-10">
	      			<input type="password" class="form-control" id="checkPassword" name="checkPassword" placeholder="비밀번호를 다시 입력해주세요" maxlength="13">
	      			<span id="inputPwCheckIcon" class="glyphicon form-control-feedback" aria-hidden="true"></span>
      				<span id="inputPwCheckStatus" class="sr-only"></span>
	    		</div>
	    		<span id="spanCheckPassword"></span>
	  		</div><hr>
	  		<!-- 이름 -->
	  		<div class="form-group">
	    		<label for="inputUserName" class="col-sm-2 control-label">이 름</label>
	    		<div class="col-sm-10">
	      			<input type="text" class="form-control" id="user_name" name="user_name">
	    		</div>
	      		<span id="spanUserName"></span>
	  		</div><hr>
	  		<!-- 이메일 -->
	  		<div class="form-group">
	    		<label class="col-sm-2 control-label">이메일</label>
	    		<div>
		    		<div class="col-sm-4">
		      			<input type="text" class="form-control" id="user_email" name="user_email">
		    		</div>
			    	<div class="input-group col-sm-6">
						<span class="input-group-addon">@</span>
						<input type="text" class="form-control" id="user_domain" name="user_domain" placeholder="gmail.com" aria-describedby="sizing-addon2">
					</div>
	    		</div>
				<span id="spanUserEmail"></span>
	  		</div><hr>
	  		<!-- 생년월일 -->
	  		<div class="form-group">
	    		<label for="inputPassword3" class="col-sm-2 control-label">생년월일</label>
	    		<div class="col-sm-10">
	      			<input type="text" class="form-control" id="user_birthday" name="user_birthday" placeholder="ex) 1995-01-30" maxlength="10">
	    		</div>
	    		<span id="spanBirthday"></span>
	  		</div><hr>
	  		<!-- 성별 -->
	  		<div class="form-group">
	    		<label for="inputGender" class="col-sm-2 control-label">성 별</label>
	    		<label class="radio-inline padding-left">
					<input type="radio" name="user_gender" checked="checked" value="M"> 남자
				</label>
				<label class="radio-inline padding-left">
					<input type="radio" name="user_gender" value="G"> 여자
				</label>
	  		</div><hr>
	  		<!-- 전화번호 -->
	  		<div class="form-group">
	    		<label for="inputPassword3" class="col-sm-2 control-label">전화번호</label>
	    		<div class="col-sm-10">
	      			<input type="text" class="form-control" id="user_phone" name="user_phone" placeholder="' - ' 제외하고 입력해주세요." maxlength="11">
	    		</div>
	    		<span id="spanPhone"></span>
	  		</div><hr>
	  		
	  		<!-- 우편번호 -->
	  		<div class="form-group">
	    		<label class="col-sm-2 control-label">우편번호</label>
	    		<div class="col-sm-5">
	      			<input type="number" class="form-control" id="user_post" name="user_post" readonly="readonly">
	    		</div>
	    		<div>
	    			<jsp:include page="/WEB-INF/views/modal/postModal.jsp"></jsp:include>
	    			<button type="button" class="btn btn-default btn-sm" onclick="execDaumPostcode()">우편번호</button>
	    		</div>
	  		</div>
	  		<!-- 주소 -->
	  		<div class="form-group">
	    		<label for="inputPassword3" class="col-sm-2 control-label">주 소</label>
	    		<div class="col-sm-10">
	      			<input type="text" class="form-control" id="user_address" name="user_address" readonly="readonly">
	    		</div>
	  		</div>
	  		<!-- 상세주소 -->
	  		<div class="form-group">
	    		<label for="inputPassword3" class="col-sm-2 control-label">상세주소</label>
	    		<div class="col-sm-10">
	      			<input type="text" class="form-control" id="user_detail_address" name="user_detail_address">
	    		</div>
	  		<span id="spanPost"></span>
	  		</div><hr>
	  		<div class="form-group" align="center">
	      		<button type="submit" class="btn btn-primary btn-lg">회 원 가 입</button>
	  		</div>
		</form>
	</section>
	</div>
	
	<!--바닥글-->
    <footer class="footer">
		<jsp:include page="/WEB-INF/views/footer/footer.jsp"></jsp:include>
    </footer>
</body>
<script type="text/javascript">
		// 문자 유효성 검사 선언
		var userIdCheck = RegExp(/^[A-Za-z0-9]{5,20}$/);
		var passwordCheck = RegExp(/^[A-Za-z0-9!@#$%^*()\-_=+\\\|\[\]{};:\'",.<>\/?]{8,16}$/);
		var nameCheck = RegExp(/^[가-힣]{2,10}$/);
		var emailCheck = RegExp(/^[A-Za-z0-9_\.\-]{5,20}$/);
		var domainCheck = RegExp(/^[A-Za-z0-9_\.\-]+\.[A-Za-z0-9\-]+/);
		var birthdayCheck = RegExp(/^([0-9]{4})-?([0-9]{2})-?([0-9]{2})$/);
		var phoneCheck = RegExp(/^[0-9]{11}$/);
		var postCheck = RegExp(/^[\w\Wㄱ-ㅎㅏ-ㅣ가-힣]$/);
		// 아이디 유효성 체크 
		var idFlag = false;
		// 비밀번호 유효성 체크
		var pwFlag = false;
		// 비밀번호 동일 체크
		var pwCheckFlag = false;
		$(function () {
			// 아이디 중복체크
			$('#user_id').blur(function () {
				$.ajax({
					type : "POST",
					url : "checkId",
					data :{"user_id":$('#user_id').val()},
					// 데이터 수신 성공
					success : function (data) {
						if(data == 0) {
							// 텍스트에 문자를 입력했을 경우
							if($('#user_id').val() != '') {
								// 입력한 텍스트의 유효성 체크 (false)
								// - 영문 대/소문자 + 숫자
								if(!userIdCheck.test($('#user_id').val())) {
									$('#spanId').text("5자리 이상인 영문과 숫자만 입력해주세요.").css("color","red");
									$('#inputId').removeClass('has-success').addClass('has-error');
									$('#inputIdIcon').removeClass('glyphicon-ok').addClass('glyphicon-remove');
									idFlag = false;
								}
								// 입력한 텍스트의 유효성 체크 (true)
								else {
									$('#spanId').text("").css("color","green");
									$('#inputId').removeClass('has-error').addClass('has-success');
									$('#inputIdIcon').removeClass('glyphicon-remove').addClass('glyphicon-ok');
									idFlag = true;
								}
							}
							// 텍스트에 문자를 입력하지 않을 경우, 
							// 에러메세지 출력과 텍스트바 디자인 변경
							else {
								$('#spanId').text("아이디를 입력해주세요.").css("color","red");
								$('#inputId').removeClass('has-success').addClass('has-error');
								$('#inputIdIcon').removeClass('glyphicon-ok').addClass('glyphicon-remove');
								idFlag = false;
							}
						}
						// 입력한 ID가 DB(컬럼 : user_id)에 존재할 경우 실시
						else {
							if($('#user_id').val() != '') {
								$('#spanId').text("중복된 아이디입니다.").css("color","red");
								$('#inputId').removeClass('has-success').addClass('has-error');
								$('#inputIdIcon').removeClass('glyphicon-ok').addClass('glyphicon-remove');
								idFlag = false;
							}
						}
					}
				})
			})
			// 패스워드
			$('#password').blur(function () {
				// 입력한 텍스트의 유효성 체크 (false)
				// - 특수문자 + 영문 대/소문자 + 숫자
				if(!passwordCheck.test($('#password').val())) {
					$('#spanPassword').text("8자리 이상인 특수문자, 영문, 숫자만 입력가능합니다.").css("color","red");
					$('#inputPw').removeClass('has-success').addClass('has-error');
					$('#inputPwIcon').removeClass('glyphicon-ok').addClass('glyphicon-remove');
					pwFlag = false;
				}
				// 입력한 텍스트의 유효성 체크 (true)
				else {
					$('#spanPassword').text("");
					$('#inputPw').removeClass('has-error').addClass('has-success');
					$('#inputPwIcon').removeClass('glyphicon-remove').addClass('glyphicon-ok');
					pwFlag = true;
				}
			})
			// 패스워드 확인
			$('#checkPassword').blur(function () {
				// 입력한 텍스트의 유효성 체크 (false)
				// 입력한 패스워드를 이중체크하여 값이 동일하지 않을 시, 에러를 나타내는 문자출력과 에러 디자인으로 변경한다
				if($('#password').val() != $('#checkPassword').val()) {
					$('#spanCheckPassword').text("입력한 패스워드가 다릅니다.").css("color","red");
					$('#inputCheckPw').removeClass('has-success').addClass('has-error');
					$('#inputPwCheckIcon').removeClass('glyphicon-ok').addClass('glyphicon-remove');
					$('#checkPassword').text("");
					pwCheckFlag = false;
				}
				// 입력한 텍스트의 유효성 체크 (true)
				// 입력한 패스워드를 이중체크하여 값이 동일할 시, 확인 디자인으로 변경한다 
				else {
					$('#spanCheckPassword').text("");
					$('#inputCheckPw').removeClass('has-error').addClass('has-success');
					$('#inputPwCheckIcon').removeClass('glyphicon-remove').addClass('glyphicon-ok');
					pwCheckFlag = true;
				}
			})
		})
		function checkInfo() {
			$('span').empty();
				// 아이디 확인 : Ajax에서 idFlag의 값을 불러옴 (정상치 : true)
				if(!idFlag) {
					$('#spanId').text("아이디를 확인해주세요.").css("color","red");
					$('#user_id').focus();
					return false;
				}
				// 비밀번호 확인 : Ajax에서 pwFlag의 값을 불러옴 (정상치 : true)
				if(!pwFlag) {
					$('#spanPassword').text("비밀번호를 확인해주세요.").css("color","red");
					$('#password').focus();
					return false;
				}
				// 비밀번호 동일입력 확인
				if(!pwCheckFlag) {
					$('#spanCheckPassword').text("비밀번호를 다시 입력해주세요.").css("color","red");
					$('#checkPassword').focus();
					return false;
				}
				// 이름 확인
				// 공백 확인
				if($('#user_name').val() == "") {
					$('#spanUserName').text("이름을 입력해주세요.").css("color","red");
					$('#user_name').focus();
					return false;
				}
				// 이름 자릿수 확인
				if(!nameCheck.test($('#user_name').val())) {
					$('#spanUserName').text("한글과 2~10자리를 입력해주세요.").css("color","red");
					$('#user_name').focus();
					return false;
				}
				// 이메일 아이디 공백 확인 
				if(($('#user_email').val() == "") && ($('#user_domain').val() == "")) {
					$('#spanUserEmail').text("이메일을 입력해주세요.").css("color","red");
					$('#user_email').focus();
					return false;
				}
				// 이메일 아이디 확인 (user_id와 형식이 같기때문에 유효성 변수 같이 사용)
				if(!emailCheck.test($('#user_email').val())) {
					$('#spanUserEmail').text("이메일 아이디를 확인해주세요.").css("color","red");
					$('#user_email').focus();
					return false;
				}
				// 이메일 도메인 확인
				if(!domainCheck.test($('#user_domain').val())) {
					$('#spanUserEmail').text("이메일 도메인을 확인해주세요.").css("color","red");
					$('#user_domain').focus();
					return false;
				}
				// 생년월일 공백 확인
				if($('#user_birthday').val() == "") {
					$('#spanBirthday').text("생년월일을 입력해주세요.").css("color","red");
					$('#user_birthday').focus();
					return false;
				}
				// 생년월일 형식 확인
				if(!birthdayCheck.test($('#user_birthday').val())) {
					$('#spanBirthday').text("생년월일과 형식을 확인해주세요.").css("color","red");
					$('#user_birthday').focus();
					return false;
				}
				// 성별 확인 M 또는 G
				var user_gender = $('input[name=user_gender]').val();
				if(user_gender != 'M' && user_gender != 'G') {
					$('#user_gender').focus();
					return false;
				}
				// 전화번호 공백 확인
				if($('#user_phone').val() == "") {
					$('#spanPhone').text("전화번호를 입력해주세요.").css("color","red");
					$('#user_phone').focus();
					return false;
				}
				// 전화번호 형식 확인
				if(!phoneCheck.test($('#user_phone').val())) {
					$('#spanPhone').text("전화번호 형식을 확인해주세요.").css("color","red");
					$('#user_phone').focus();
					return false;
				}
				// 우편번호 공백 확인
				if($('#user_post').val() == "") {
					$('#spanPost').text("우편번호를 입력해주세요.").css("color","red");
					$('#user_post').focus();
					return false;
				}
				// 주소 공백 확인
				if($('#user_address').val() == "") {
					$('#spanPost').text("주소를 입력해주세요.").css("color","red");
					$('#user_address').focus();
					return false;
				}
				// 상세주소 공백 확인
				if($('#user_detail_address').val() == "") {
					$('#spanPost').text("상세주소를 입력해주세요.").css("color","red");
					$('#user_detail_address').focus();
					return false;
				}
			return true;
		}
</script>
</html>