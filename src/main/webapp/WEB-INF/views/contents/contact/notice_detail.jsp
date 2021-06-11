<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<c:set var="contextPath" value="${pageContext.request.contextPath}" />
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>고객센터</title>
<meta name="viewport"
	content="width=device-width, initial-scale=1, shrink-to-fit=no">
<link rel="stylesheet" href="${contextPath}/resources/css/bootstrap.css">
<link rel="stylesheet" href="${contextPath}/resources/css/swiper.css">
<link rel="stylesheet" href="${contextPath}/resources/css/main.css">
<link rel="stylesheet"
	href="https://pro.fontawesome.com/releases/v5.10.0/css/all.css" />
<link rel="stylesheet" href="${contextPath}/resources/css/modal.css">
<script src="${contextPath}/resources/js/jquery.min.js"></script>
<script src="${contextPath}/resources/js/jquery.number.min.js"></script>
<script src="${contextPath}/resources/js/bootstrap.min.js"></script>
<script src="${contextPath}/resources/js/swiper.min.js"></script>
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
		<div class="container">
			<h2>
				<strong>공지사항</strong>
			</h2>
			<br>
			<hr>
			<!-- 제목 -->
			<div style="width: 100%;">
				<div class="col-xs-1" style="float: left; text-align: right;">
					<h4 style="color: #5a5a5a;">[${noticeVO.type}]</h4>
				</div>
				<div class="col-xs-8" style="float: left; padding-left: 10px;">
					<h4>${noticeVO.title}</h4>
				</div>
				<div class="col-xs-2" style="float: right; text-align: right;">
					<h5>${noticeVO.reg_date}</h5>
					<c:if test="${noticeVO.reg_date ne noticeVO.mod_date}">
						<h6 style="color: #c8c8c8;">${noticeVO.mod_date}수정됨</h6>
					</c:if>
				</div>
				<div class="col-xs-1"
					style="color: #8c8c8c; text-align: right; float: right;">
					<h5>조회수 ${noticeVO.views}</h5>
				</div>
			</div>
			<br>
			<br>
			<hr>
			<!-- 내용 -->
			<div style="width: 100%;">
				<div align="center" style="max-width: 1200px;">
					<c:if test="${noticeVO.content_img1 ne null}">
						<img style="text-align: center; align-content: center;" alt="..."
							src="${noticeVO.content_img1}">
						<br>
					</c:if>
					<c:if test="${noticeVO.content_img2 ne null}">
						<img style="text-align: center;" alt="..."
							src="${noticeVO.content_img2}">
						<br>
					</c:if>
					<c:if test="${noticeVO.content_img3 ne null}">
						<img style="text-align: center;" alt="..."
							src="${noticeVO.content_img3}">
					</c:if>
				</div>
				<h5 style="line-height: 23px;">${noticeVO.content}</h5>
			</div>
			<hr>
			<a class="btn btn-primary btn-lg" href="${contextPath}/contact"
				style="color: white;"><i
				class="glyphicon glyphicon-chevron-left"></i>뒤로가기</a>
		</div>
	</section>
	<footer>
		<jsp:include page="/WEB-INF/views/footer/footer.jsp"></jsp:include>
	</footer>
</body>
</html>