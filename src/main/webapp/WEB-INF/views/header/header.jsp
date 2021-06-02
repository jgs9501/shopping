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
	<div class="navbar navbar-default">
		<div class="container">
			<div class="navbar-header">
				<button type="button" class="navbar-toggle collapsed" data-toggle="collapse" data-target="#navbar1">
				    <span class="sr-only">Toggle navigation</span>
			        <span class="icon-bar"></span>
			        <span class="icon-bar"></span>
			        <span class="icon-bar"></span>
				</button>
				<a class="navbar-brand" href="${contextPath}/index">JS Shop</a>
			</div>
			<div class="collapse navbar-collapse" id="navbar1">
				<ul class="nav navbar-nav">
					<c:choose>
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
							<li class="ft-small"><a href="${contextPage}/contents/user/login">로그인</a></li>
							<li class="ft-small"><a href="${contextPage}/contents/user/regist">회원가입</a></li>
						</c:when>
						<c:otherwise>
								<li class="ft-small"><a href="${contextPage}/contents/user/modify">회원정보</a></li>
								<li class="ft-small" id="btn_point"><a href="#" data-toggle="modal" data-target="#pointModal">포인트추가</a></li>
								<li class="ft-small"><a href="${contextPage}/contents/logout">로그아웃</a></li>
									<!-- 포인트추가 modal -->
									<div class="modal fade" id="pointModal" tabindex="-1" role="dialog" aria-labelledby="modalLabel" aria-hidden="true">
										<div class="modal-dialog" role="document">
											<div class="modal-content">
												<div class="modal-header">
													<span class="modal-title" id="modalLabel">보유 포인트 추가</span>
													<button type="button" class="close" data-dismiss="modal" aria-label="Close">
														<span aria-hidden="true">×</span>
													</button>
												</div>
												<form action="${contextPath}/contents/user/point/get" method="post">
										      		<div class="modal-body">
											      		  <p>물품을 결제하기 위해 사용하는 테스트용 보유포인트 추가하는 기능입니다.</p>
											      		  <p>추가할 포인트를 입력해주세요.</p><br>
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
						</c:otherwise>
					</c:choose>
				</ul>
			</div>
		</div>
	</div>
</body>
</html>