<%@ page language="java" contentType="text/html; charset=utf-8"	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<c:set var="contextPath" value="${pageContext.request.contextPath}" />
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
	<meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
	<title>JS Shop</title>
	<!-- Bootstrap CSS -->
	<link rel="stylesheet" href="${contextPath}/resources/css/bootstrap.css">
	<script src="${contextPath}/resources/js/jquery.min.js"></script>
	<script src="${contextPath}/resources/js/jquery.cookie.js"></script>
	<script src="${contextPath}/resources/js/bootstrap.min.js"></script>
	<link rel="stylesheet" href="${contextPath}/resources/css/main.css">
</head>
<body>
	<!-- 머릿글 -->
	<header>
		<jsp:include page="/WEB-INF/views/header/header.jsp"></jsp:include>
	</header>
	<section class="container">
		<div style="padding-top: 30px;">
			<h2><strong>${result}</strong></h2>
			<hr>
			<div style="text-align: center; margin-top: 30px;">
				<h1><i class="fas fa-user-check"></i></h1>
				<h2>${result}이 완료되었습니다.</h2>
			</div>
			<div style="text-align: center;">
				<br>
				<a class="btn btn-primary btn-lg" href="${ContextPath}/index" role="button">홈으로</a>
			</div>
		</div>
	</section>
	<!--바닥글-->
    <footer class="footer">
		<jsp:include page="/WEB-INF/views/footer/footer.jsp"></jsp:include>
    </footer>

</body>
</html>