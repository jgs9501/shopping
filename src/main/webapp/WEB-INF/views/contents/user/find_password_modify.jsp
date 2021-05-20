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
		<form action="${contextPath}/contents/user/modifyPassword" method="post">
			<h2><strong>비밀번호 변경</strong></h2>
			<hr>
			<div class="center-auto" style="width: 400px;">
				<input type="hidden" id="user_id" name="user_id" value="${user_id }">
				<h5><strong>${user_id}</strong>&nbsp;님의 비밀번호를 변경합니다.</h5>
			</div>
			<div class="center-auto" style="padding-top: 20px; width: 400px;">
				<label><strong>비밀번호</strong></label>
				<input class="form-control" type="password" id="password" name="password">
			</div>
			<div class="center-auto" style="padding-top: 20px; width: 400px;">
				<label><strong>비밀번호 확인</strong></label>
				<input class="form-control" type="password" id="checkPassword" name="checkPassword">
			</div>
			<hr>
			<div class="center-auto" style=" width: 400px; padding-top: 30px; padding-bottom: 50px;">
				<input class="btn btn-primary btn-lg btn-block" type="submit" id="btn_submit" value="비밀번호 변경">
			</div>
		</form>
	</section>
	<footer>
		<jsp:include page="/WEB-INF/views/footer/footer.jsp"></jsp:include>
    </footer>
    
    <script type="text/javascript">
    	
    	$('#btn_submit').click(function (){
			var password = $('#password').val();
			var chk_password = $('#checkPassword').val();
			var passwordCheck = RegExp(/^[A-Za-z0-9!@#$%^*()\-_=+\\\|\[\]{};:\'",.<>\/?]{8,16}$/);
			
			if(!passwordCheck.test(password)) {
				alert('8자리 이상인 특수문자, 영문, 숫자만 입력가능합니다.');
				return false;
			}
			
			if(password == chk_password) {
				return true;
			}
			else {
				alert('입력한 비밀번호가 다릅니다. 다시 확인해주세요.');
				return false;
			}
		})
    
    </script>
	</body>
</html>