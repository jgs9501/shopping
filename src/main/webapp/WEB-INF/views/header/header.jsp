<%@ page language="java" contentType="text/html; charset=utf-8"	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<c:set var="contextPath" value="${pageContext.request.contextPath}" />
<!DOCTYPE html>
<html>
<head>
	<meta charset="UTF-8">
	<link rel="stylesheet" href="${contextPath}/resources/css/main.css">
	<link rel="stylesheet" href="https://pro.fontawesome.com/releases/v5.10.0/css/all.css"/>
</head>
<body>
	<nav class="navbar navbar-default">
		<div class="container">
			<!-- Brand and toggle get grouped for better mobile display -->
			<div class="navbar-header">
				<button type="button" class="navbar-toggle collapsed"
					data-toggle="collapse" data-target="#bs-example-navbar-collapse-1">
					<span class="sr-only">Toggle navigation</span> 
					<span class="glyphicon glyphicon-menu-hamburger"></span>
				</button>
				<a class="navbar-brand" href="${contextPath}/index">JS Shop</a>
			</div>

			<div class="collapse navbar-collapse" id="bs-example-navbar-collapse-1">
				<ul class="nav navbar-nav">
					<c:choose>
						<c:when test="${userVO.auth ne 2}">
							<li><a href="#">Link</a></li>
							<li><a href="#">Link</a></li>
							<li><a href="#">Link</a></li>
							<li><a href="#">Link</a></li>
							<li><a href="#">Link</a></li>
							<li><a href="#">Link</a></li>
						</c:when>
						<c:when test="${userVO.auth eq 2}">
							<li><a href="${contextPath}/product/release">상품등록</a></li>
							<li><a href="${contextPath}/product/release/${userVO.seq_user_id}">상품조회</a></li>
							<li><a href="#">Link</a></li>
							<li><a href="#">Link</a></li>
							<li><a href="#">Link</a></li>
							<li><a href="#">Link</a></li>
						</c:when>
					</c:choose>
				</ul>
				<ul class="nav navbar-nav navbar-right">
					<c:choose>
						<c:when test="${userVO eq null}">
							<li><a href="${contextPage}/contents/user/login">로그인</a></li>
						</c:when>
						<c:otherwise>
							<li>
								<div class="dropdown">
									<a class="btn margin-6px" id="dropdownMenu1" data-toggle="dropdown" aria-expanded="true">${user}</a>
									<ul class="dropdown-menu" role="menu" aria-labelledby="dropdownMenu1">
										<li><a href="${contextPage}/contents/user/modify">회원수정</a></li>
										<li><a href="${contextPage}/contents/logout">로그아웃</a></li>
									</ul>
								</div>
							</li>
							<li>
								<a href="${contextPage}/cart" class="fas fa-shopping-cart"></a>
							</li>
						</c:otherwise>
					</c:choose>
				</ul>
			</div>
		</div>
		<!-- /.container-fluid -->
	</nav>
</body>
</html>