<%@ page language="java" contentType="text/html; charset=utf-8"	pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<%@ taglib prefix="security" uri="http://www.springframework.org/security/tags"%>
<c:set var="contextPath" value="${pageContext.request.contextPath}"/>
<!DOCTYPE html>
<html>
<head>
	<meta charset="UTF-8">
	<meta id="_csrf" name="_csrf" content="${_csrf.token}"/>
	<meta id="_csrf_header" name="_csrf_header" content="${_csrf.headerName}"/>
	<script src="${contextPath}/resources/js/jquery.min.js"></script> 
	<script src="${contextPath}/resources/js/jquery.number.min.js"></script> 
	<script src="${contextPath}/resources/js/bootstrap.min.js"></script>
	<script src="https://cdn.iamport.kr/js/iamport.payment-1.1.5.js"></script>
	<link rel="stylesheet" href="${contextPath}/resources/css/bootstrap.css">
	<link rel="stylesheet" href="${contextPath}/resources/css/main.css">
	<link rel="stylesheet" href="${contextPath}/resources/css/cart.css">
	<link rel="stylesheet" href="${contextPath}/resources/css/payment.css">
	<link rel="stylesheet" href="https://pro.fontawesome.com/releases/v5.10.0/css/all.css"/>
	<title>결제</title>
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
		<form id="frmSubmit" name="frmSubmit">
			<input type="hidden" name="${_csrf.parameterName }" value="${_csrf.token }">
			<div class="container section">
				<h2><strong>주문/결제</strong></h2>
				<hr>
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
					</tbody>
				</table>
				<p class="table-title">받는사람정보</p>
				<table class="payInfo">
					<tbody>
						<tr>
							<th>이름</th>
							<td>${userVO.user_name}</td>
						</tr>
						<tr>
							<th>배송주소</th>
							<td>${userVO.user_address} ${userVO.user_detail_address}</td>
						</tr>
						<tr>
							<th>연락처</th>
							<td>${userVO.user_phone}</td>
						</tr>
						<tr>
							<th>배송 요구사항</th>
							<td><input type="text" class="form-control" id="request" name="request" maxlength="50"></td>
						</tr>
						
					</tbody>
				</table>
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
					<c:forEach var="cartList" items="${cartList}" varStatus="status">
					<input type="hidden" name="orderDetailsVO[${status.index}].product_id" value="${cartList.product_id}">
					<tbody>
						<tr>
							<td>
								<div class="row">
									<div class="col-sm-2 hidden-xs">
										<input type="hidden" name="orderDetailsVO[${status.index}].product_thumbImg" value="${cartList.product_thumbImg}">
										<img src="${cartList.product_thumbImg}" alt="..." class="img-responsive"/></div>
									<div class="col-sm-10">
										<input type="hidden" name="orderDetailsVO[${status.index}].product_name" value="${cartList.product_name }">
										<h4 class="nomargin">${cartList.product_name}</h4>
									</div>
								</div>
							</td>
							<td>
								<input type="hidden" name="orderDetailsVO[${status.index}].product_price" value="${cartList.product_price}">
								<fmt:formatNumber type="number" maxFractionDigits="3" value="${cartList.product_price}"/> 원
							</td>
							<td>
								<input type="hidden" name="orderDetailsVO[${status.index}].amount" value="${cartList.amount}">
								<h5>${cartList.amount}</h5>
							</td>
							<td>
								<fmt:formatNumber type="number" maxFractionDigits="3" value="${cartList.product_price*cartList.amount}"/> 원
							</td>
						</tr>
					</tbody>
					<c:set var="totalPrice" value="${totalPrice + (cartList.product_price*cartList.amount)}"/>
					</c:forEach>
				</table>
				
				<p class="table-title">결제정보</p>
				<table class="payInfo">
					<tbody>
						<tr>
							<th>총 상품가격</th>
							<td><fmt:formatNumber type="number" maxFractionDigits="3" value="${totalPrice}"/> 원</td>
						</tr>
						<tr>
							<th>사용 포인트</th>
							<td>
								<input type="number" id="point_text" name="point_text" value="0" min="0" max="${totalPrice}" maxlength="6">
								<input type="button" id="btn_point" onclick="useAllPoint(${point})" value="전액사용">  <small>보유 포인트 : ${point}</small>
							</td>
						</tr>
						<tr>
							<th>결제 가격</th>
							<td><div id="payPrice"><fmt:formatNumber type="number" maxFractionDigits="3" value="${totalPrice}"/> 원</div></td>
						</tr>
						<tr>
							<th>결제방법</th>
							<td>
								<input type="radio" name="chk_pay" value="kakaopay" checked="checked">
								<span>카카오페이</span>
								<input type="radio" name="chk_pay" value="point">
								<span>포인트</span>
							</td>
						</tr>
					</tbody>
				</table>
				<hr>
				<div style="text-align: center; padding-bottom: 20px;">
					<input type="hidden" id="totalPrice" name="totalPrice" value="${totalPrice}">
					<input type="hidden" id="usePoint" name="usePoint" value="0">
					<input type="hidden" id="pay_type" name="pay_type" value="null">
					<button type="button" class="btn btn-primary btn-lg" id="btn_payment">결 제 하 기</button>
				</div>
			</div>
		</form>
	</section>
	<footer>
		<jsp:include page="/WEB-INF/views/footer/footer.jsp"></jsp:include>
	</footer>

<script>
	var resultPrice = $('#totalPrice').val();
	const price = $('#totalPrice').val();
	// 포인트 전액 버튼 함수
	function useAllPoint(point) {
		// 가격보다 포인트가 높을 경우 전액 포인트 값 대입
		if(price < point) {
			point = price;
		}
		resultPrice = price - point;
		
		$('#point_text').val(point);
		$('#usePoint').val($('#point_text').val());
		$('#payPrice').empty();
		$('#payPrice').append($.number(resultPrice) + " 원");
	}
	
	// 포인트 기입 시 값 변경 함수
	$('#point_text').change(function () {
		var point = $('#point_text').val();
		if(price < point) {
			point = price;
		}
		resultPrice = price - point;
		
		$('#point_text').val(point);
		$('#usePoint').val(point);
		$('#payPrice').empty();
		$('#payPrice').append($.number(resultPrice) + " 원");
	})

	// 결제하기 버튼 누를 시 이벤트 함수
	$('#btn_payment').click(function () {
		var payment_type = $('input:radio[name=chk_pay]:checked').val();
		$('#pay_type').val(payment_type);
		var form = document.frmSubmit;
		
		if(payment_type == "point") {
			form.method = "POST";
			form.action = "/payment/point";
			let price = $('#totalPrice').val();
			let point = $('#point_text').val();
			
			price == point ? form.submit() : alert("포인트가 부족합니다.");
		}
		else if(payment_type == "kakaopay") {
		    form.method = "POST";
			form.action = "/payment/kakaopay";
			const phone = '${userVO.user_phone}';
			//가맹점 식별코드
			IMP.init('imp03024031');
			IMP.request_pay({
				pg : 'kakaopay',
				pay_method : 'card',
				merchant_uid : 'merchant_' + new Date().getTime(),
				name : '카카오페이 결제 테스트',
				amount : resultPrice,
				buyer_email : '${userVO.user_email}@${userVO.user_domain}',
				buyer_name : '${userVO.user_name}',
				buyer_addr : '${userVO.user_address} ${userVO.user_detail_address}',
				buyer_tel : phone.substring(0,3)+'-'+phone.substring(3,7)+'-'+phone.substring(7,11)
			}, function(rsp) {
				console.log(rsp);
				if(rsp.success) {
					var msg = '결제가 완료되었습니다.';
			        msg += '\n고유ID : ' + rsp.imp_uid;
			        msg += '\n상점 거래ID : ' + rsp.merchant_uid;
			        msg += '\n결제 금액 : ' + rsp.paid_amount;
			        msg += '\n카드 승인번호 : ' + rsp.apply_num;
					form.submit();
				} 
				else {
					var msg = '빌링키 발급 실패 : ' + rsp.error_msg;
				}
				alert(msg);
			});
		}
	})
</script>
</body>
</html>