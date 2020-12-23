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
	<div class="container">
		<section class="center-auto">
			<span class="glyphicon glyphicon-remove" style="width: 100px; height: 100px;"></span>
			<h3>부적절한 오류가 발생하였습니다</h3>
			<br><h3>${result}</h3>
			<br><p><a class="btn btn-primary btn-lg" href="${ContextPath}/index" role="button">홈으로</a></p>
		</section>
	</div>
	<!--바닥글-->
    <footer class="footer">
		<jsp:include page="/WEB-INF/views/footer/footer.jsp"></jsp:include>
    </footer>

</body>
</html>