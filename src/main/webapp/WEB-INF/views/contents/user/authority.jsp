<%@ page language="java" contentType="text/html; charset=UTF-8"	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
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
			<li><a href="/user/modify">회원정보수정</a></li>
			<li class="active">권한확인</li>
		</ol>
		<div class="container">
			<h2><strong>권한확인</strong></h2>
			<hr>
			<jsp:include page="/WEB-INF/views/side/registList.jsp"></jsp:include>
			<div class="padding-left-200 padding-top-50 center">
				<h4><strong><security:authentication property="principal"/></strong>님의 권한은 
					<strong>
						<security:authorize access="hasRole('ROLE_USER')">고객(유저)</security:authorize>
						<security:authorize access="hasRole('ROLE_STORE')">판매자(점포)</security:authorize>
						<security:authorize access="hasRole('ROLE_ADMIN')">관리자</security:authorize>
					</strong>입니다.
				</h4><br>
				<security:authorize access="hasRole('ROLE_USER')">
					<h5>판매자(점포)로 설정을 원하실 경우, 하단의 점포설정 버튼을 눌러주시길 바랍니다.</h5>
					<form action="/user/auth/store">
						<button class="btn btn-primary" type="submit">점포설정&nbsp;<i class="fa fa-angle-right"></i></button>
					</form>
				</security:authorize>
			</div>
		</div>
	</section>
	
    <footer class="footer">
		<jsp:include page="/WEB-INF/views/footer/footer.jsp"></jsp:include>
    </footer>
</body>
</html>