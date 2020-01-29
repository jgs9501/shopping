<%@ page language="java" contentType="text/html; charset=UTF-8"	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
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
	<title>login</title>
	<style type="text/css">
		body {
			background-color: #eee;
		}
		
		.center-sort {
			margin-left: auto;
			margin-right: auto;
		}
		.width-limit {
			max-width: 700px;
			min-width: 400px;
		}
		.center-padding {
			padding-top: 100px;
			padding-left: 25%;
			padding-right: 25%;
			padding-bottom: 100px;
		}
	</style>
</head>
<body>
	<!-- 머릿글 -->
	<header>
		<jsp:include page="/WEB-INF/views/header/header.jsp"></jsp:include>
	</header>
	
	<!-- 로그인 화면 -->
	<section>
		<form class="center-sort center-padding">
	 		<fieldset class="well well-lg center-padding width-limit">
				<h1 class="text-center">JS Shop</h1><br>
		    	<div class="form-group">
		    		<label for="TextInput">아이디</label>
			    	<input type="text" id="userId" class="form-control" placeholder="아이디를 입력하세요">
			    </div>
				<div class="form-group">
		    		<label for="TextInput">비밀번호</label>
			    	<input type="password" id="userPassword" class="form-control" placeholder="비밀번호 6자리 이상 입력하세요">
			    </div>
			    <div class="checkbox">
				    <label>
						<input type="checkbox" id="checkboxSuccess" value="option1">아이디 기억하기
				    </label>
					
				</div>
			    <div>
			      	<button type="button" class="btn btn-primary btn-lg btn-block">로 그 인</button>
			    </div>
			    <h6 style="float: right;"><a href="">비밀번호를 잊으셨나요?</a></h6>
			    <hr class="my-4">
				<br><h6 style="float: right;">회원가입을 원하실 경우는 <a href="">여기</a></h6>
				
		    </fieldset>
		</form>
	</section>
</body>
</html>