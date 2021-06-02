<%@ page language="java" contentType="text/html; charset=utf-8"	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<c:set var="contextPath" value="${pageContext.request.contextPath}"/>
<!DOCTYPE html>
<html>
<head>
	<meta charset="UTF-8">
	<meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
	<link rel="stylesheet" href="${contextPath}/resources/css/bootstrap.css">
	<link rel="stylesheet" href="${contextPath}/resources/css/swiper.css">
	<link rel="stylesheet" href="${contextPath}/resources/css/main.css">
	<link rel="stylesheet" href="https://pro.fontawesome.com/releases/v5.10.0/css/all.css"/>
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
		<div class="container section">
			<h2><i class="fas fa-shopping-cart"></i>&nbsp;<strong>장바구니</strong></h2>
			<hr>
			<c:choose>
				<c:when test="${fn:length(cartList) < 1}">
					<div style="text-align: center; padding-top: 100px;">
						<strong>장바구니에 담은 물품이 존재하지 않습니다</strong>
						<br>
						<div style="padding-top: 50px;">
							<a href="${contextPath}/categories/100" class="btn btn-warning"><i class="fa fa-angle-left"></i>&nbsp;쇼핑하기</a>
						</div>
					</div>
				</c:when>
				<c:otherwise>
					<table id="table" class="table table-hover table-condensed">
		    			<thead>
							<tr>
								<th style="width:50%">상품</th>
								<th style="width:10%">가격</th>
								<th style="width:8%">개수</th>
								<th style="width:22%" class="text-center">총 가격</th>
								<th style="width:10%">물품삭제</th>
							</tr>
						</thead>
						<c:forEach var="cartList" items="${cartList}" varStatus="i">
						<tbody>
							<tr id="cartTr">
								<td data-th="Product">
									<div class="row">
										<div class="col-sm-2 hidden-xs"><img src="${cartList.product_thumbImg}" alt="..." class="img-responsive"/></div>
										<div class="col-sm-10">
											<h4 class="nomargin"><a href="${contextPath}/products/${cartList.product_id}">${cartList.product_name}</a></h4>
										</div>
									</div>
								</td>
								<td data-th="Price" id="priceTd${i.index}">
									<fmt:formatNumber type="number" maxFractionDigits="3" value="${cartList.product_price}"/> 원
								</td>
								<td data-th="Quantity" id="qtyTd${i.index}">
									<input type="number" class="form-control text-center" id="selectAmountId" onchange="changeSelectAmount(this, ${i.index}, ${cartList.product_price})" value="${cartList.amount}" min="1" max="20">
								</td>
								<td data-th="Subtotal" class="text-center" id="subTotalTd${i.index}">
									<fmt:formatNumber type="number" maxFractionDigits="3" value="${cartList.product_price*cartList.amount}"/> 원</td>
								<td class="actions">
									<button class="btn btn-danger btn-sm" onclick="deleteCartItem(${cartList.seq_user_id}, ${cartList.cart_id})"><i class="fas fa-trash-alt"></i></button>								
								</td>
								<c:set var="totalPrice" value="${totalPrice + (cartList.product_price*cartList.amount)}"/>
							</tr>
						</tbody>
						</c:forEach>
						<tfoot>
							<tr>
								<td><a href="${contextPath}/categories/100" class="btn btn-warning"><i class="fa fa-angle-left"></i> 계속 쇼핑하기</a></td>
								<td colspan="2" class="hidden-xs text-right"><strong>총합</strong></td>
								<td class="hidden-xs text-center" id="totalPriceId"><strong><fmt:formatNumber type="number" maxFractionDigits="3" value="${totalPrice}"/> 원</strong></td>
								<td>
									<a href="${contextPath}/payment">
										<button class="btn btn-success btn-block">구 매 하 기<i class="fa fa-angle-right"></i></button>
									</a>
								</td>
							</tr>
						</tfoot>
					</table>
					</c:otherwise>
				</c:choose>
		</div>
	</section>
	
	<footer>
		<jsp:include page="/WEB-INF/views/footer/footer.jsp"></jsp:include>
    </footer>

<script>
	function deleteCartItem(seq_user_id, cart_id) {
		var flag = confirm("해당 상품을 장바구니에서 제거하시겠습니까?");
		if (flag) {
			var url = "${contextPath}/cart/"+seq_user_id+"/delete/"+cart_id;
			location.href = url;
		}
	}
	
	function changeSelectAmount(qtyId, index, price) {
	
		var qty = $(qtyId).val();
		var price = price;
		var subTotal = qty * price;
		
		subTotalCalc(index, price, qty);
		totalCalc();
	}

	function subTotalCalc(index, price, qty) {
		const subTotal = price * qty;
		const result = $.number(subTotal);
		$('#subTotalTd'+index).text(result+" 원");
	}
	
	function totalCalc() {
		const tr = $('#table #cartTr');
		const td = tr.children();
		const length = tr.length;
		let total = 0;
		
		for(i=0; i<length; i++) {
			const subTotal = td.eq(3+(i*5)).text();
			const rep = subTotal.replace(/[^0-9]/g,"");
			total += parseInt(rep,10);
		}
		
		var result = $.number(total);
		$('#totalPriceId').html("<strong>"+result+" 원</strong>");
		
		return total;
	}
	
</script>
</body>
</html>