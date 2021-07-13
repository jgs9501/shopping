<%@ page language="java" contentType="text/html; charset=utf-8"	pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<%@ taglib prefix="security" uri="http://www.springframework.org/security/tags"%>
<c:set var="contextPath" value="${pageContext.request.contextPath}" />
<!DOCTYPE html>
<html>
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
				<a class="navbar-brand" href="/index">JS Shop</a>
			</div>
			<div class="collapse navbar-collapse" id="navbar1">
				<ul class="nav navbar-nav">
					<!-- auth: 1(소비자), 2(판매자) 3(관리자) -->
					<security:authorize access="hasRole('ROLE_STORE')"> 
						<li><a href="/product/release">상품등록</a></li>
						<li><a href="/product/release/${userVO.seq_user_id}">상품조회</a></li>
						<li><a href="#">Link</a></li>
						<li><a href="#">Link</a></li>
						<li><a href="#">Link</a></li>
						<li><a href="#">Link</a></li>
					</security:authorize>
					<security:authorize access="hasRole('ROLE_ADMIN')"> 
						<li><a href="${contextPath}/admin/notice">공지등록</a></li>
						<li><a href="${contextPath}/admin/qna">질의등록</a></li>
						<li><a href="#">권한설정</a></li>
					</security:authorize>
				</ul>
				<ul class="nav navbar-nav navbar-right">
					<security:authorize access="isAnonymous()">
						<li class="ft-small"><a href="/login">로그인</a></li>
						<li class="ft-small"><a href="/user/regist">회원가입</a></li>
						<li class="ft-small"><a href="/contact">고객센터</a></li>
					</security:authorize>
					<security:authorize access="isAuthenticated()">
						<li class="ft-small"><a href="/user/modify">회원정보</a></li>
						<li class="ft-small" id="btn_point"><a href="#" data-toggle="modal" data-target="#pointModal">포인트추가</a></li>
						<li class="ft-small"><a href="/contact">고객센터</a></li>
						<li class="ft-small"><a href="/logout">로그아웃</a></li>
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
									<form action="/user/point" method="post">
										<input type="hidden" name="${_csrf.parameterName }" value="${_csrf.token }">
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
					</security:authorize>
				</ul>
			</div>
		</div>
	</div>
</body>
</html>