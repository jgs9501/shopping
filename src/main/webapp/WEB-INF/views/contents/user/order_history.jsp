<%@ page language="java" contentType="text/html; charset=utf-8"	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<c:set var="contextPath" value="${pageContext.request.contextPath}"/>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<script src="${contextPath}/resources/js/jquery.min.js"></script> 
<script src="${contextPath}/resources/js/bootstrap.min.js"></script>
<link rel="stylesheet" href="${contextPath}/resources/css/bootstrap.css">
<link rel="stylesheet" href="${contextPath}/resources/css/main.css">
<link rel="stylesheet" href="https://pro.fontawesome.com/releases/v5.10.0/css/all.css"/>
<title>주문내역</title>
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
			<h2><strong>주문내역</strong></h2>
			<hr>
			<c:choose>
				<c:when test="${fn:length(orders) < 1}">
					<div class="pt100 center-auto">
						<h2>주문내역이 없습니다.</h2>
					</div>
				</c:when>
				<c:otherwise>
				<c:forEach var="order" items="${orders}" varStatus="o">
					<form id="frmSubmit" name="frmSubmit" method="post">
						<fmt:parseDate var="date" value="${order.order_date}" pattern="yyyy-MM-dd HH:mm:ss"></fmt:parseDate>
						<span style="font-size: x-large;"><strong><fmt:formatDate value="${date}" pattern="yyyy.MM.dd"/>&nbsp;</strong></span>
						<span style="font-size: large;">주문번호&nbsp;</span>
						<span style="font-size: medium;">${order.order_id}</span><br>
						<span id="order_status" style="font-size: x-large; color: green;">${order.order_status}</span>
						<table id="table" class="table table-hover table-condensed">
			    			<thead>
								<tr>
									<th style="width:60%; text-align: center;">상품정보</th>
									<th style="width:10%">가격</th>
									<th style="width:10%">개수</th>
									<th style="width:10%" class="text-center">총 가격</th>
									<th style="width:0%"></th>
								</tr>
							</thead>
							<c:forEach var="detail" items="${details}" varStatus="d">
								<c:if test="${order.order_id eq detail.order_id }">
									<tbody>
										<tr>
											<td data-th="Product">
												<div class="row">
													<div class="col-sm-2 hidden-xs"><img src="${detail.product_thumbImg }" alt="..." class="img-responsive"/></div>
													<div class="col-sm-10">
														<h4 class="nomargin"><a href="${contextPath}/products/${detail.product_id}">${detail.product_name}</a></h4>
													</div>
												</div>
											</td>
											<td data-th="Price">
												<fmt:formatNumber type="number" maxFractionDigits="3" value="${detail.product_price}"/>&nbsp;원
											</td>
											<td data-th="Quantity">
												${detail.amount}
											</td>
											<td data-th="Subtotal" class="text-center">
												<fmt:formatNumber type="number" maxFractionDigits="3" value="${detail.product_price*detail.amount}"/>&nbsp;원</td>
											<td class="actions">
												<input type="hidden" id="oi${o.count}${d.count}" value="${order.order_id}">
												<input type="hidden" id="sui${o.count}${d.count}" value="${order.seq_user_id}">
												<input type="hidden" id="od${o.count}${d.count}" value="${order.order_date}">
												<input type="hidden" id="os${o.count}${d.count}" value="${order.order_status}">
												<input type="hidden" id="req${o.count}${d.count}" value="${order.request}">
												<input type="hidden" id="tp${o.count}${d.count}" value="${order.total_price}">
												<input type="hidden" id="odi${o.count}${d.count}" value="${detail.order_detail_id}">
												<input type="hidden" id="pi${o.count}${d.count}" value="${detail.product_id}">
												<input type="hidden" id="am${o.count}${d.count}" value="${detail.amount}">
												<input type="hidden" id="pp${o.count}${d.count}" value="${detail.product_price}">
												<button class="btn btn-primary btn-sm" id="btn_cancel${o.index}${d.index}" onclick="subBtn(${o.count}${d.count})" type="button">교환/반품신청</button>
											</td>
										</tr>
									</tbody>
								</c:if>
							</c:forEach>
							<tfoot>
								<tr>
									<td></td>
									<td></td>
									<td></td>
									<td></td>
									<td></td>
								</tr>
							</tfoot>
						</table>
						<hr>
					</form>
					</c:forEach>
				</c:otherwise>
			</c:choose>
		</div>
	</section>
	<footer class="footer">
		<jsp:include page="/WEB-INF/views/footer/footer.jsp"></jsp:include>
    </footer>
    
    <script type="text/javascript">
    
    function subBtn(idx) {
		let order_id = $('#oi'+idx).val();
		let seq_user_id = $('#sui'+idx).val();
		let order_date = $('#od'+idx).val();
		let order_status = $('#os'+idx).val();
		let request = $('#req'+idx).val();
		let total_price = $('#tp'+idx).val();
		let order_detail_id = $('#odi'+idx).val();
		let product_id = $('#pi'+idx).val();
		let amount = $('#am'+idx).val();
		let product_price = $('#pp'+idx).val();
		
		var param = {
				seq_user_id : seq_user_id,
				order_id : order_id,
				order_date : order_date,
				order_status : order_status,
				request : request,
				total_price : total_price,
				order_detail_id : order_detail_id,
				product_id : product_id,
				amount : amount,
				product_price : product_price
		};
		
		var result = confirm("주문을 취소하시겠습니까?");
		if(result) {
			$.ajax({
				type : "POST",
				url : "/order/cancel",
				data : JSON.stringify(param),
				contentType : "application/json",
				success : function () {
					location.replace("/order/history");
				},
				error : function () {
				}
			});
		}
		
	
	}
    
    function cancelOrder(idx) {
    	
    	var index = idx;
		var result = confirm("주문을 취소하시겠습니까?");
		var form = document.frmSubmit;
		console.log(index);
		if(result) {
			form.method = "POST";
			form.action = "order/cancel";
			form.submit();
		}
	}
    
    $(function () {
    	
    	/* $('.actions #btn_cancel').each(function (index, item) {
			$(item).attr("id", "btn_cancel"+index);
		}) */
    	
    	$('section #order_status').each(function (index, item) {
			$(item).attr("id", "order_status"+index);
			var status = $('#order_status'+index).text();
	    	
			var result = change_status(status);
			$('#order_status'+index).text(result);
		})
    	
		function change_status(status) {
    		var result = "";
			if(status == 'N') {
				result = "주문완료";
			}
			else if(status == 'C') {
				result = "주문취소";
			}
			else if(status == 'P') {
				result = "배송중";
			}
			else if(status == 'E') {
				result = "배송완료";
			}
			return result;
		}
    	
	})
    </script>
</body>
</html>