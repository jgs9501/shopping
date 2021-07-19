<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<%@ taglib prefix="security" uri="http://www.springframework.org/security/tags"%>
<c:set var="contextPath" value="${pageContext.request.contextPath}" />
<!DOCTYPE html>
<html>
<head>
	<meta charset="utf-8">
	<meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
	<meta id="_csrf" name="_csrf" content="${_csrf.token}"/>
	<meta id="_csrf_header" name="_csrf_header" content="${_csrf.headerName}"/>
	<link rel="stylesheet" href="${contextPath}/resources/css/bootstrap.css">
	<link rel="stylesheet" href="${contextPath}/resources/css/swiper.css">
	<link rel="stylesheet" href="${contextPath}/resources/css/main.css">
	<link rel="stylesheet" href="https://pro.fontawesome.com/releases/v5.10.0/css/all.css"/>
	<link rel="stylesheet" href="${contextPath}/resources/css/modal.css">
	<script src="${contextPath}/resources/js/jquery.min.js"></script> 
	<script src="${contextPath}/resources/js/jquery.cookie.js"></script> 
	<script src="${contextPath}/resources/js/bootstrap.min.js"></script>
	<script src="${contextPath}/resources/js/swiper.min.js"></script>
	<title>JS Shop</title>
</head>
<body>
	<header>
		<jsp:include page="/WEB-INF/views/header/header.jsp"></jsp:include>
	</header>
	<nav>
		<jsp:include page="/WEB-INF/views/navbar/search_nav.jsp"></jsp:include>
		<jsp:include page="/WEB-INF/views/navbar/category_nav.jsp"></jsp:include>
	</nav>
	<section>
		<ol class="breadcrumb container">
			<li><a href="/index">메인</a></li>
			<li class="active">회원정보수정</li>
		</ol>
		<div class="container">
			<h2><strong>회원정보수정</strong></h2>
			<hr>
			<jsp:include page="/WEB-INF/views/side/registList.jsp"></jsp:include>
			<div style="margin-left: 150px; margin-right: 150px;">
				<form class="form-horizontal form-width" method="post"
					onsubmit="return checkInfo()">
					<!-- 아이디 -->
					<div id="inputId" class="form-group has-feedback">
						<label class="col-sm-2 control-label">아이디</label>
						<div class="col-sm-10">
							<input type="text" class="form-control"
								aria-describedby="inputIdStatus" id="user_id" name="user_id"
								value="${userVO.user_id}" readonly="readonly"> <span
								id="inputIdStatus" class="sr-only"></span>
						</div>
						<span id="spanId"></span>
					</div>
					<hr>
					<!-- 이름 -->
					<div class="form-group">
						<label for="inputUserName" class="col-sm-2 control-label">이
							름</label>
						<div class="col-sm-10">
							<input type="text" class="form-control" id="user_name"
								name="user_name" value="${userVO.user_name}" disabled="disabled">
						</div>
						<span id="spanUserName"></span>
					</div>
					<hr>
					<!-- 이메일 -->
					<div class="form-group">
						<label class="col-sm-2 control-label">이메일</label>
						<div class="col-sm-4">
							<input type="text" class="form-control" id="user_email"
								name="user_email" value="${userVO.user_email}">
						</div>
						<div class="input-group col-sm-6">
							<span class="input-group-addon">@</span> <input type="text"
								class="form-control" id="user_domain" name="user_domain"
								value="${userVO.user_domain}" placeholder="gmail.com"
								aria-describedby="sizing-addon2">
						</div>
						<span id="spanUserEmail"></span>
					</div>
					<hr>
					<!-- 전화번호 -->
					<div class="form-group">
						<label for="inputPassword3" class="col-sm-2 control-label">전화번호</label>
						<div class="col-sm-10">
							<input type="text" class="form-control" id="user_phone"
								name="user_phone" value="${userVO.user_phone}"
								placeholder="' - ' 제외하고 입력해주세요." maxlength="11">
						</div>
						<span id="spanPhone"></span>
					</div>
					<hr>

					<!-- 우편번호 -->
					<div class="form-group">
						<label class="col-sm-2 control-label">우편번호</label>
						<div class="col-sm-5">
							<input type="number" class="form-control" id="user_post"
								name="user_post" value="${userVO.user_post}" readonly="readonly">
						</div>
						<div>
							<jsp:include page="/WEB-INF/views/modal/postModal.jsp"></jsp:include>
							<button type="button" class="btn btn-default btn-sm"
								onclick="execDaumPostcode()">우편번호</button>
						</div>
					</div>
					<!-- 주소 -->
					<div class="form-group">
						<label for="inputPassword3" class="col-sm-2 control-label">주
							소</label>
						<div class="col-sm-10">
							<input type="text" class="form-control" id="user_address"
								name="user_address" value="${userVO.user_address}"
								readonly="readonly">
						</div>
					</div>
					<!-- 상세주소 -->
					<div class="form-group">
						<label for="inputPassword3" class="col-sm-2 control-label">상세주소</label>
						<div class="col-sm-10">
							<input type="text" class="form-control" id="user_detail_address"
								name="user_detail_address" value="${userVO.user_detail_address}">
						</div>
						<span id="spanPost"></span>
					</div>
					<hr>
					<div class="form-group" align="center">
						<button type="submit" class="btn btn-primary btn-lg">회 원
							수 정</button>
					</div>
				</form>
			</div>
		</div>
	</section>
	<!--바닥글-->
	<footer class="footer">
		<jsp:include page="/WEB-INF/views/footer/footer.jsp"></jsp:include>
	</footer>

	<script type="text/javascript">
	
		$(function csrf() {
			var token = $("meta[name='_csrf']").attr("content");
			var header = $("meta[name='_csrf_header']").attr("content");
			$(function() {
			    $(document).ajaxSend(function(e, xhr, options) {
			        xhr.setRequestHeader(header, token);
			    });
			});
		})
		// 문자 유효성 검사 선언
		var nameCheck = RegExp(/^[가-힣]{2,10}$/);
		//var emailCheck = RegExp(/^[A-Za-z0-9]{5,20}$/);
		var domainCheck = RegExp(/^[A-Za-z0-9_\.\-]+\.[A-Za-z0-9\-]+/);
		var birthdayCheck = RegExp(/^([0-9]{4})-?([0-9]{2})-?([0-9]{2})$/);
		var phoneCheck = RegExp(/^[0-9]{11}$/);
		var postCheck = RegExp(/^[\w\Wㄱ-ㅎㅏ-ㅣ가-힣]$/);

		function checkInfo() {
			// 이메일 아이디 공백 확인 
			if (($('#user_email').val() == "")
					&& ($('#user_domain').val() == "")) {
				$('#spanUserEmail').text("이메일을 입력해주세요.").css("color", "red");
				$('#user_email').focus();
				return false;
			}
			// 이메일 아이디 확인 (user_id와 형식이 같기때문에 유효성 변수 같이 사용)
			//if(!emailCheck.test($('#user_email').val())) {
			//	$('#spanUserEmail').text("이메일 아이디를 확인해주세요.").css("color","red");
			//	$('#user_email').focus();
			//	return false;
			//}

			// 이메일 도메인 확인
			if (!domainCheck.test($('#user_domain').val())) {
				$('#spanUserEmail').text("이메일 도메인을 확인해주세요.")
						.css("color", "red");
				$('#user_domain').focus();
				return false;
			}
			// 생년월일 공백 확인
			if ($('#user_birthday').val() == "") {
				$('#spanBirthday').text("생년월일을 입력해주세요.").css("color", "red");
				$('#user_birthday').focus();
				return false;
			}
			// 전화번호 공백 확인
			if ($('#user_phone').val() == "") {
				$('#spanPhone').text("전화번호를 입력해주세요.").css("color", "red");
				$('#user_phone').focus();
				return false;
			}
			// 전화번호 형식 확인
			if (!phoneCheck.test($('#user_phone').val())) {
				$('#spanPhone').text("전화번호 형식을 확인해주세요.").css("color", "red");
				$('#user_phone').focus();
				return false;
			}
			// 우편번호 공백 확인
			if ($('#user_post').val() == "") {
				$('#spanPost').text("우편번호를 입력해주세요.").css("color", "red");
				$('#user_post').focus();
				return false;
			}
			// 주소 공백 확인
			if ($('#user_address').val() == "") {
				$('#spanPost').text("주소를 입력해주세요.").css("color", "red");
				$('#user_address').focus();
				return false;
			}
			// 상세주소 공백 확인
			if ($('#user_detail_address').val() == "") {
				$('#spanPost').text("상세주소를 입력해주세요.").css("color", "red");
				$('#user_detail_address').focus();
				return false;
			}
			return true;
		}
	</script>
</body>
</html>