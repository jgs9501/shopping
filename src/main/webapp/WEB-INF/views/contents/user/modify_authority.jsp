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
			<li class="/user/auth">권한확인</li>
			<li class="active">점포설정</li>
		</ol>
		<div class="container">
			<h2><strong>점포설정</strong></h2>
			<hr>
			<jsp:include page="/WEB-INF/views/side/registList.jsp"></jsp:include>
			<div class="padding-left-200 padding-top-50 center">
				<form class="form-horizontal form-width" method="post" action="/user/auth/store">
					<input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}">
					<div class="form-group has-feedback">
						<label class="col-sm-2 control-label">점포이름</label>
						<div class="col-sm-10">
							<input type="text" class="form-control" name="store_name">
						</div>
					</div>
					<div class="form-group has-feedback">
						<label class="col-sm-2 control-label">점포연락처</label>
						<div class="col-sm-10">
							<input type="text" class="form-control" name="store_phone" maxlength="50">
						</div>
					</div>
					<button class="btn btn-primary" type="submit">점포변환</button>
				</form>
			</div>
		</div>
	</section>
    <footer class="footer">
		<jsp:include page="/WEB-INF/views/footer/footer.jsp"></jsp:include>
    </footer>
</body>
</html>