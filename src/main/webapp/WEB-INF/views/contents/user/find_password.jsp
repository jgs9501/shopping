<%@ page language="java" contentType="text/html; charset=UTF-8"	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<c:set var="contextPath" value="${pageContext.request.contextPath}" />
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
	<script src="${contextPath}/resources/js/jquery.min.js"></script> 
	<script src="${contextPath}/resources/js/bootstrap.min.js"></script>
	<script src="${contextPath}/resources/js/jquery.cookie.js"></script>
	<link rel="stylesheet" href="${contextPath}/resources/css/bootstrap.css">
	<link rel="stylesheet" href="${contextPath}/resources/css/main.css">
	<link rel="stylesheet" href="https://pro.fontawesome.com/releases/v5.10.0/css/all.css"/>
<title>비밀번호 찾기</title>
</head>
<body>
	<header>
		<jsp:include page="/WEB-INF/views/header/header.jsp"></jsp:include>
	</header>
	<section class="container">
		<form action="${contextPath}/contents/user/password/modify" method="post">
			<h2><strong>비밀번호 찾기</strong></h2>
			<hr>
			<div class="center-auto" style="width: 400px;">
				<h5><strong>이메일로 비밀번호 찾기</strong></h5>
				<h6>회원가입 시 등록한 이메일을 입력해주세요.</h6>
			</div>
			<div class="center-auto" style="padding-top: 20px; width: 400px;">
				<label><strong>아이디</strong></label>
				<input class="form-control" type="text" id="user_id" name="user_id">
			</div>
			<div class="form-group center-auto" style="padding-top: 30px; padding-bottom: 50px; width: 400px;">
				<div>
					<label><strong>이메일 주소</strong></label>
				</div>
				<div class="col-xs-9" style="padding-left: 0px; padding-right: 0px;">
					<div class="col-xs-5" style="padding-left: 0px;">
						<input class="form-control" type="text" id="user_email" name="user_email">
					</div>
					<div class="col-xs-7" style="padding-left: 0px;">
						<input class="form-control" type="text" id="user_domain" name="user_domain" placeholder="ex) gmail.com">
					</div>
				</div>
				<div class="col-xs-2">
					<input class="btn btn-primary" id="btn_auth" type="button" value="인증번호">
				</div>
				<div style="padding-top: 50px; width: 400px;">
					<input class="form-control" type="text" id="key" name="key" autocomplete="off" placeholder="인증번호를 입력해주세요">
					<span id="status"></span>
				</div>
			</div>
			<div class="center-auto" style=" width: 400px;">
				<input class="btn btn-primary btn-lg btn-block" type="submit" id="btn_submit" value="확인">
			</div>
			<hr>
		</form>
	</section>
	<footer>
		<jsp:include page="/WEB-INF/views/footer/footer.jsp"></jsp:include>
    </footer>
    
	<script type="text/javascript">
		var key = "";
		
		$("#btn_submit").click(function() {
			if(key == "") {
				$('#status').text("인증번호를 입력해주세요.").css("color","red");
				return false;
			}
			else if(key != $('#key').val()){
				$('#status').text("입력한 인증번호가 다릅니다.").css("color","red");
				return false;
			}
			else {
				return true;
			}
		})
		
		$("#btn_auth").click(function() {// 메일 입력 유효성 검사
			var user_id = $("#user_id").val();
			var mail = $("#user_email").val(); //사용자의 이메일 입력값. 
			var domain = $("#user_domain").val();
			
			if(user_id == "") {
				alert("아이디를 입력해주세요.");
			}
			if (mail == "" || domain == "") {
				alert("메일 주소가 입력되지 않았습니다.");
			} 
			else {
				mail = mail+"@"+domain; //셀렉트 박스에 @뒤 값들을 더함.
				$.ajax({
					type : 'post',
					url : '/checkMail',
					data : {
						user_id:user_id,
						mail:mail
						},
					dataType :'json',
					success : function (data) {
						alert("인증번호가 전송되었습니다.") 
						key = data;
					},
					error : function () {
						alert("이메일 정보가 잘못되었습니다.");
					}
				});
				isCertification=true; //추후 인증 여부를 알기위한 값
			}
		});
	</script>
</body>
</html>