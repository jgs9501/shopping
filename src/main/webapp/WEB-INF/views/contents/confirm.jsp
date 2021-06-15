<%@ page language="java" contentType="text/html; charset=utf-8"	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<c:set var="contextPath" value="${pageContext.request.contextPath}" />
<!DOCTYPE html>
<html>
<head>
	<meta charset="UTF-8">
	<meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
	<title>유저확인</title>
	<!-- Bootstrap CSS -->
	<link rel="stylesheet" href="${contextPath}/resources/css/bootstrap.css">
	<script src="${contextPath}/resources/js/jquery.min.js"></script>
	<script src="${contextPath}/resources/js/jquery.cookie.js"></script>
	<script src="${contextPath}/resources/js/bootstrap.min.js"></script>
	<link rel="stylesheet" href="${contextPath}/resources/css/main.css">
	<style type="text/css">
		body {
			background-color: #eee;
		}
	</style>
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
	<!-- 로그인 화면 -->
	<section>
		<form action="loginPost" class="center-sort center-padding" method="post">
	 		<fieldset class="well well-lg center-padding width-limit">
				<h1 class="text-center">JS Shop</h1><br>
		    	<div class="form-group">
		    		<label for="TextInput">아이디</label>
			    	<input type="text" class="form-control" id="user_id" name="user_id" value="${userVO.user_id}" readonly="readonly">
			    </div>
				<div class="form-group">
		    		<label for="TextInput">비밀번호</label>
			    	<input type="password" id="password" name="password" class="form-control" placeholder="비밀번호를 입력하세요">
			    </div>
			    <div>
			      	<button type="submit" class="btn btn-primary btn-lg btn-block">로 그 인</button>
			    </div>
		    </fieldset>
		</form>
	</section>
</body>
</html>