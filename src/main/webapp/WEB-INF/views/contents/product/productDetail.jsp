<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<c:set var="contextPath" value="${pageContext.request.contextPath}" />
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>JS SHOP</title>
<script src="${contextPath}/resources/js/jquery.min.js"></script> 
<script src="${contextPath}/resources/js/swiper.min.js"></script>
<script src="https://unpkg.com/swiper/swiper-bundle.min.js"></script>
<script src="${contextPath}/resources/js/bootstrap.min.js"></script>
<link rel="stylesheet" href="${contextPath}/resources/css/swiper.css">
<link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/font-awesome/4.7.0/css/font-awesome.min.css"/>
<link rel="stylesheet" href="${contextPath}/resources/css/bootstrap.css">
<link rel="stylesheet" href="${contextPath}/resources/css/product-detail.css">
<link rel="stylesheet" href="${contextPath}/resources/css/main.css">
</head>
<body>
	<header>
		<jsp:include page="/WEB-INF/views/header/header.jsp"></jsp:include>
	</header>
	<ol class="breadcrumb container">
		<li><a href="${contextPath}/index">메인</a></li>
		<li><a href="${contextPath}/categories/${pdVO.productVO.category}">${categoryName}</a></li>
		<li class="active">${pdVO.productVO.product_name}</li>
	</ol>
	<section>
		<div class="container">
		<div class="card">
			<div class="container-fliud">
				<div class="wrapper row">
					<div class="preview col-md-6">
						<div class="preview-pic tab-content">
						  <div class="tab-pane active" id="pic-1"><img src="${pdVO.productVO.product_img}" /></div>
						</div>
					</div>
					<div class="details col-md-6">
						<div>
							<h3 class="product-title">${pdVO.productVO.product_name}</h3>
						</div>
						<div>
							<h5 class="h5">${pdVO.storeVO.store_name}</h5>
						</div>
						<div class="rating">
							<div class="stars">
								<span class="fa fa-star checked"></span>
								<span class="fa fa-star checked"></span>
								<span class="fa fa-star checked"></span>
								<span class="fa fa-star-half-o"></span>
								<span class="fa fa-star-o"></span>
								<span class="review-no">41개 상품평</span>
							</div>
						</div>
						<p class="product-description">${pdVO.productVO.product_desc}</p>
						<c:if test="${pdVO.productVO.discount eq 0}">
							<h4 class="price">가격: 
								<span><fmt:formatNumber type="number" pattern="0,000" minIntegerDigits="1" value="${pdVO.productVO.product_price}"/> 원</span>
							</h4>
						</c:if>
						<c:if test="${pdVO.productVO.discount ne 0}">
							<h4 class="price">가격: 
								<span class="discount" style="color: gray;">
									<fmt:formatNumber type="number" pattern="0,000" minIntegerDigits="1" value="${pdVO.productVO.product_price}"/>
								</span>
								<span>
									<fmt:formatNumber type="number" pattern="0,000" minIntegerDigits="1" value="${pdVO.productVO.product_price - pdVO.productVO.discount}"/> 
									원</span>
							</h4>
						</c:if>
						<p class="vote"><strong>91%</strong> of buyers enjoyed this product! <strong>(87 votes)</strong></p>
						<h5 class="sizes">sizes:
							<span class="size" data-toggle="tooltip" title="small">s</span>
							<span class="size" data-toggle="tooltip" title="medium">m</span>
							<span class="size" data-toggle="tooltip" title="large">l</span>
							<span class="size" data-toggle="tooltip" title="xtra large">xl</span>
						</h5>
						<h5 class="colors">colors:
							<span class="color orange not-available" data-toggle="tooltip" title="Not In store"></span>
							<span class="color green"></span>
							<span class="color blue"></span>
						</h5>
						<div class="action">
							<button class="add-to-cart btn btn-default" type="button">장바구니 담기</button>
							<button class="like btn btn-default" type="button"><span class="fa fa-heart"></span></button>
						</div>
					</div>
				</div>
			</div>
			<hr>
			<h4 class="h4">출품한 업체의 다른 물품</h4>
			<div class="row">
				<div class="swiper-container swiper-per-group">
				    <div class="swiper-wrapper">
				    	<c:forEach var="product" items="${listProduct}">
					        <div class="swiper-slide thumbnail">
					        	<div>
					        		<a href="${contextPath}/products/${product.product_id}"><img src="${product.product_thumbImg}"></a>
						        	<div class="text-left">
							        	<a href="${contextPath}/products/${product.product_id}"><strong>${product.product_name}</strong></a>
						        	</div>
						        	<div class="text-right price">
							        	<h5>${product.product_price-product.discount} 원</h5>
						        	</div>
					        	</div>
					        </div>
				    	
				    	</c:forEach>
				    </div>
				    <div class="swiper-pagination"></div>
				    <div class="swiper-button-next"></div>
				    <div class="swiper-button-prev"></div>
				</div>
			</div>
		</div>
		<ul class="nav nav-tabs nav-justified" style="margin-top: 20px;">
			<li class="active" >
				<a class="nav-link" data-toggle="tab" href="#product_info" >상품정보</a>
		  	</li>
		  	<li>
		    	<a class="nav-link" data-toggle="tab" href="#product_rating">상품평</a>
		  	</li>
		  	<li>
		    	<a class="nav-link" data-toggle="tab" href="#product_qa">상품문의</a>
		  	</li>
		</ul>

		<div class="tab-content">
			<div class="tab-pane fade active in" id="product_info">
		  		<p class="table-title">제품 정보</p>
				<table class="info">
					<tbody>
						<tr>
							<th>용량(중량)</th>
							<td>${pdVO.productVO.weight}</td>
							<th>제품 주요 사항</th>
							<td>${pdVO.productVO.attention}</td>
						</tr>
						<tr>
							<th>유효기한</th>
							<td>${pdVO.productVO.valid_date}</td>
							<th>사용방법</th>
							<td>${pdVO.productVO.use_info}</td>
						</tr>
						<tr>
							<th>판매업자</th>
							<td>${pdVO.storeVO.store_name}</td>
							<th>제조국</th>
							<td>${pdVO.productVO.country}</td>
						</tr>
					</tbody>
				</table>
		  	</div>
		  	<div class="tab-pane fade" id="product_rating">
		    	<p>Nunc vitae turpis id nibh sodales commodo et non augue. Proin fringilla ex nunc. Integer tincidunt risus ut facilisis tristique.</p>
		  	</div>
			<div class="tab-pane fade" id="product_qa">
		    	<p>Curabitur dignissim quis nunc vitae laoreet. Etiam ut mattis leo, vel fermentum tellus. Sed sagittis rhoncus venenatis. Quisque commodo consectetur faucibus. Aenean eget ultricies justo.</p>
			</div>
		</div>
		
	</div>
	</section>
	<footer>
		<jsp:include page="/WEB-INF/views/footer/footer.jsp"></jsp:include>
	</footer>

    <script>
    var swiper = new Swiper('.swiper-per-group', {
    	slidesPerView: 4,
        spaceBetween: 30,
        slidesPerGroup: 3,
   	    pagination: {
        el: '.swiper-pagination',
        clickable: true,
  	    },
  	navigation: {
        nextEl: '.swiper-button-next',
        prevEl: '.swiper-button-prev',
        },
	});
</script>
</body>
</html>