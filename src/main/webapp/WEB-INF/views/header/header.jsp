<%@ page language="java" contentType="text/html; charset=utf-8"	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<c:set var="contextPath" value="${pageContext.request.contextPath}" />
<!DOCTYPE html>
<html>
<head>
	<meta charset="UTF-8">
	<link rel="stylesheet" href="${contextPath}/resources/css/main.css">
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
				<!-- 검색툴바 -->
				<div class="navbar-form navbar-left">
				    <div class="input-group">
				    	<input type="text" class="form-control" placeholder="상품을 검색하세요">
				      	<span class="input-group-btn">
					        <button class="btn btn-default" type="button">
					        	<span class="glyphicon glyphicon-search" aria-hidden="true"></span>
					        </button>
				      	</span>
				  </div>
				</div>
							
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
				<% if(session.getAttribute("userVO")==null){ %>
					<li><a href="${contextPage}/contents/user/login">로그인</a></li>
					<li><a href="${contextPage}/contents/user/regist">회원가입</a></li>
				<% }else{ %>
					<li><a href="${contextPage}/contents/user/modify"><%=session.getAttribute("user") %></a></li>
					<li><a href="${contextPage}/contents/logout">로그아웃</a></li>
				<%} %>
				</ul>
			</div>
		</div>
		<!-- /.container-fluid -->
	</nav>
</body>
</html>