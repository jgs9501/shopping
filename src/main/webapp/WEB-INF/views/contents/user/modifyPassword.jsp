<%@ page language="java" contentType="text/html; charset=UTF-8"	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<% request.setCharacterEncoding("UTF-8"); %>
<!DOCTYPE html>
<html>
<meta charset="UTF-8">
	<title>비밀번호변경</title>
	<meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
	<meta id="_csrf" name="_csrf" content="${_csrf.token}"/>
	<meta id="_csrf_header" name="_csrf_header" content="${_csrf.headerName}"/>
	<!-- Bootstrap CSS -->
	<link rel="stylesheet" href="${contextPath}/resources/css/bootstrap.css">
	<script src="${contextPath}/resources/js/jquery.min.js"></script>
	<script src="${contextPath}/resources/js/jquery.cookie.js"></script>
	<script src="${contextPath}/resources/js/bootstrap.min.js"></script>
	<link rel="stylesheet" href="${contextPath}/resources/css/main.css">	
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	<!-- 머릿글 -->
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
			<li class="active">비밀번호수정</li>
		</ol>
		<c:choose>
			<c:when test="${not empty sessionScope.user}">
				<div class="container">
					<aside>
						<jsp:include page="/WEB-INF/views/side/registList.jsp"></jsp:include>
					</aside>
					
					<section style="padding-left: 200px; padding-right: 200px;">
						<form class="form-horizontal form-width" method="post" onsubmit="return checkInfo()">
							<!-- 아이디 -->
					    	<div id="inputId" class="form-group has-feedback">
						    	<label class="col-sm-2 control-label">아이디</label>
					    		<div class="col-sm-10">
						    		<input type="text" class="form-control" aria-describedby="inputIdStatus" id="user_id" name="user_id" value="${userVO.user_id}" readonly="readonly">
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
					  		<div class="form-group" align="center">
					      		<button type="submit" class="btn btn-primary btn-lg">회 원 수 정</button>
					  		</div>
				  		</form>
					</section>
				</div>
			</c:when>
			<c:otherwise>
			</c:otherwise>
		</c:choose>
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
		var passwordCheck = RegExp(/^[A-Za-z0-9!@#$%^*()\-_=+\\\|\[\]{};:\'",.<>\/?]{8,16}$/);
		var pwFlag = false;
		// 비밀번호 동일 체크
		var pwCheckFlag = false;
		$(function () {
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
		}
	</script>
</body>
</html>