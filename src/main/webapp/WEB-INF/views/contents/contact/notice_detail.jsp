<%@ page language="java" contentType="text/html; charset=utf-8"	pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<%@ taglib prefix="security" uri="http://www.springframework.org/security/tags"%>
<c:set var="contextPath" value="${pageContext.request.contextPath}" />
<!DOCTYPE html>
<html>
<head>
	<title>고객센터</title>
	<meta charset="UTF-8">
	<meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
	<meta id="_csrf" name="_csrf" content="${_csrf.token}"/>
	<meta id="_csrf_header" name="_csrf_header" content="${_csrf.headerName}"/>
	<link rel="stylesheet" href="${contextPath}/resources/css/bootstrap.css">
	<link rel="stylesheet" href="${contextPath}/resources/css/swiper.css">
	<link rel="stylesheet" href="${contextPath}/resources/css/main.css">
	<link rel="stylesheet" href="https://pro.fontawesome.com/releases/v5.10.0/css/all.css" />
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
				<div class="col-xs-1" style="float: left; width: 12%;">
					<h4 style="color: #5a5a5a;">[${noticeVO.type}]</h4>
				</div>
				<div class="col-xs-7" style="float: left;">
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
				<div align="center" style="">
					<c:if test="${noticeVO.content_img1 ne null}">
						<img style="text-align: center; align-content: center; max-width: 1140px;" alt="..."
							src="${noticeVO.content_img1}">
						<br>
					</c:if>
					<c:if test="${noticeVO.content_img2 ne null}">
						<img style="text-align: center; align-content: center; max-width: 1140px;" alt="..."
							src="${noticeVO.content_img2}">
						<br>
					</c:if>
					<c:if test="${noticeVO.content_img3 ne null}">
						<img style="text-align: center; align-content: center; max-width: 1140px;" alt="..."
							src="${noticeVO.content_img3}">
					</c:if>
				</div>
				<h5 style="line-height: 23px;">${noticeVO.content}</h5>
			</div>
			<hr>
			<div style="float: left;">
				<a class="btn btn-primary btn-lg" href="/contact"
					style="color: white;"><i
					class="glyphicon glyphicon-chevron-left"></i>뒤로가기</a>
			</div>
			<security:authorize access="hasRole('ROLE_ADMIN')">
				<div style="float: right;">
					<form id="notice_form" action="/admin/notice/${noticeVO.notice_id}/delete" method="post">
						<input type="hidden" name="${_csrf.parameterName }" value="${_csrf.token }">
						<a class="btn btn-primary btn-lg" href="/admin/notice/${noticeVO.notice_id}/update"
							style="color: white;">수정</a>
						<input class="btn btn-primary btn-lg" type="submit" style="color: white;" value="삭제">
					</form>
				</div>
			</security:authorize>
		</div>
	</section>
	<footer>
		<jsp:include page="/WEB-INF/views/footer/footer.jsp"></jsp:include>
	</footer>
</body>
</html>