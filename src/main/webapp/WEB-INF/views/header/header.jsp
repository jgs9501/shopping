<%@ page language="java" contentType="text/html; charset=utf-8"	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<c:set var="contextPath" value="${pageContext.request.contextPath}" />
<!DOCTYPE html>
<html>
<head>
	<meta charset="UTF-8">
	<link rel="stylesheet" href="${contextPath}/resources/css/main.css">
	<link rel="stylesheet" href="${contextPath}/resources/css/modal.css">
	<link rel="stylesheet" href="https://pro.fontawesome.com/releases/v5.10.0/css/all.css"/>
</head>
<body>
	<nav class="navbar navbar-default">
		<div class="container">
			<div class="navbar-header">
				<button type="button" class="navbar-toggle collapsed"
					data-toggle="collapse" data-target="#bs-example-navbar-collapse-1">
					<span class="glyphicon glyphicon-menu-hamburger"></span>
				</button>
				<a class="navbar-brand" href="${contextPath}/index">JS Shop</a>
			</div>

			<div class="collapse navbar-collapse" id="bs-example-navbar-collapse-1">
				<ul class="nav navbar-nav">
					<c:choose>
						<c:when test="${userVO.auth ne 2}">
							<li><a href="${contextPath}/order/history">주문내역</a></li>
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
						<c:when test="${user eq null}">
							<li><a href="${contextPage}/contents/user/login">로그인</a></li>
						</c:when>
						<c:otherwise>
							<li>
								<div class="dropdown">
									<a class="btn margin-6px" id="dropdownMenu1" data-toggle="dropdown" aria-expanded="true"><i class="fas fa-bars"></i>&nbsp;${user}</a>
									<ul class="dropdown-menu" role="menu" aria-labelledby="dropdownMenu1">
										<li><a href="${contextPage}/contents/user/modify">회원수정</a></li>
										<li id="btn_point"><a href="#" data-toggle="modal" data-target="#pointModal">포인트추가</a></li>
										<li><a href="${contextPage}/contents/logout">로그아웃</a></li>
									</ul>
									<!-- 포인트추가 modal -->
									<div class="modal fade" id="pointModal" tabindex="-1" role="dialog" aria-labelledby="exampleModalLabel" aria-hidden="true">
										<div class="modal-dialog" role="document">
											<div class="modal-content">
												<div class="modal-header">
													<h5 class="modal-title" id="exampleModalLabel">보유 포인트 추가</h5>
													<button type="button" class="close" data-dismiss="modal" aria-label="Close">
														<span aria-hidden="true">×</span>
													</button>
												</div>
												<form action="${contextPath}/contents/user/point/get" method="post">
										      		<div class="modal-body">
											      		    물품을 결제하기 위해 사용하는 테스트용 보유포인트 추가하는 기능입니다.<br>
											      		     추가할 포인트를 입력해주세요. (10자리까지 가능합니다.)<br><br>
										      		     <input class="form-control" type="number" maxlength="10" id="point" name="point" value="0">
												    </div>
										      		<div class="modal-footer">
										        		<button type="button" class="btn btn-secondary" data-dismiss="modal">닫기</button>
										        		<button type="submit" class="btn btn-primary">추가하기</button>
										      		</div>
												</form>
									    	</div>
									  	</div>
									</div>
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
	</nav>
</body>
</html>