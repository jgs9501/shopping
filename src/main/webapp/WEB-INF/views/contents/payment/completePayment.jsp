<%@ page language="java" contentType="text/html; charset=utf-8"	pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<%@ taglib prefix="security" uri="http://www.springframework.org/security/tags"%>"%>
<c:set var="contextPath" value="${pageContext.request.contextPath}"/>
<!DOCTYPE html>
<html>
<head>
	<meta charset="UTF-8">
	<script src="${contextPath}/resources/js/jquery.min.js"></script> 
	<script src="${contextPath}/resources/js/jquery.number.min.js"></script> 
	<script src="${contextPath}/resources/js/bootstrap.min.js"></script>
	<link rel="stylesheet" href="${contextPath}/resources/css/bootstrap.css">
	<link rel="stylesheet" href="${contextPath}/resources/css/main.css">
	<link rel="stylesheet" href="${contextPath}/resources/css/cart.css">
	<link rel="stylesheet" href="${contextPath}/resources/css/payment.css">
	<link rel="stylesheet" href="https://pro.fontawesome.com/releases/v5.10.0/css/all.css"/>
	<title>결제완료</title>
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
		<div class="container section">
			<h2><strong>주문완료</strong></h2>
			<h5>주문해 주셔서 감사합니다.</h5>
			<hr>
			<p class="table-title">구매상품정보</p>
			<table class="table table-hover table-condensed">
    			<thead>
					<tr>
						<th style="width:40%">상품</th>
						<th style="width:10%">가격</th>
						<th style="width:8%">개수</th>
						<th style="width:10%">총 가격</th>
					</tr>
				</thead>
				<c:forEach var="list" items="${orderDetailList}">
				<tbody>
					<tr>
						<td>
							<div class="row">
								<div class="col-sm-2 hidden-xs">
									<img src="${list.product_thumbImg}" alt="..." class="img-responsive"/></div>
								<div class="col-sm-10">
									<h4 class="nomargin"><a href="${contextPath}/products/${list.product_id}">${list.product_name}</a></h4>
								</div>
							</div>
						</td>
						<td>
							<fmt:formatNumber type="number" maxFractionDigits="3" value="${list.product_price}"/> 원
						</td>
						<td>
							<h5>${list.amount}</h5>
						</td>
						<td>
							<fmt:formatNumber type="number" maxFractionDigits="3" value="${list.product_price*list.amount}"/> 원
						</td>
					</tr>
				</tbody>
				<c:set var="totalPrice" value="${totalPrice + (list.product_price*cartList.amount)}"/>
				</c:forEach>
			</table>
			
			<p class="table-title">구매자정보</p>
			<table class="payInfo">
				<tbody>
					<tr>
						<th>이름</th>
						<td>${userVO.user_name}</td>
					</tr>
					<tr>
						<th>이메일</th>
						<td>${userVO.user_email}@${userVO.user_domain}</td>
					</tr>
					<tr>
						<th>휴대폰 번호</th>
						<td>${userVO.user_phone}</td>
					</tr>
					<tr>
						<th>배송주소</th>
						<td>${userVO.user_address} ${userVO.user_detail_address}</td>
					</tr>
					<tr>
						<th>요청사항</th>
						<td>${order.request}</td>
					</tr>
				</tbody>
			</table>
			<p class="table-title">결제정보</p>
			<table class="payInfo">
				<tbody>
					<tr>
						<th>주문번호</th>
						<td>${order.order_id}</td>
					</tr>
					<tr>
						<th>결제일</th>
						<td>${order.order_date}</td>
					</tr>
					<tr>
						<th>결제방법</th>
						<td>${order.pay_type}</td>
					</tr>
					<tr>
						<th>결제금액</th>
						<td><fmt:formatNumber type="number" maxFractionDigits="3" value="${order.total_price}"/> 원</td>
					</tr>
				</tbody>
			</table>
			<div style="text-align: center; padding-bottom: 20px;">
				<a href="/index">
					<button type="button" class="btn btn-primary btn-lg">홈으로</button>
				</a> 
			</div>
		</div>
	</section>
	<footer>
		<jsp:include page="/WEB-INF/views/footer/footer.jsp"></jsp:include>
	</footer>
</body>
</html>