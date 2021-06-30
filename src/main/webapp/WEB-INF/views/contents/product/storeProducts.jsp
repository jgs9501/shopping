<%@ page language="java" contentType="text/html; charset=utf-8"	pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<%@ taglib prefix="security" uri="http://www.springframework.org/security/tags"%>
<c:set var="contextPath" value="${pageContext.request.contextPath}" />
<!DOCTYPE html>
<html>
<body>
	<h4 class="h4">출품한 업체의 다른 물품</h4>
	<div class="swiper-container swiper-per-group">
		<div class="swiper-wrapper">
			<c:forEach var="store_product" items="${listProduct }">
				<div class="swiper-slide">
					<div class="product-grid">
						<div class="product-image">
							<a href="/products/${store_product.product_id}">
								<img src="${store_product.product_thumbImg }">
							</a>
						</div>
						<div class="product-content">
							<h3 class="title">
								<a href="/products/${store_product.product_id}">${store_product.product_name}</a>
							</h3>
							<div class="price">
								<span> <fmt:formatNumber type="number"
										minIntegerDigits="1" pattern="0,000"
										value="${store_product.product_price - store_product.discount}" />&nbsp;원
								</span>
								<c:if test="${store_product.discount ne 0}">
									<span class="discount"> <fmt:formatNumber type="number"
											minIntegerDigits="1" pattern="0,000"
											value="${store_product.product_price}" />&nbsp;원
									</span>
								</c:if>
							</div>
						</div>
					</div>
				</div>
			</c:forEach>
		</div>
		<div class="swiper-pagination"></div>
		<div class="swiper-button-next"></div>
		<div class="swiper-button-prev"></div>
	</div>

	<script type="text/javascript">
		var swiper = new Swiper('.swiper-per-group', {
			slidesPerView : 4,
			spaceBetween : 30,
			slidesPerGroup : 4,
			pagination : {
				el : '.swiper-pagination',
				clickable : true,
			},
			navigation : {
				nextEl : '.swiper-button-next',
				prevEl : '.swiper-button-prev',
			},
		});
	</script>
</body>
</html>