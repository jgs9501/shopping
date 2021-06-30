<%@ page language="java" contentType="text/html; charset=utf-8"	pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<c:set var="contextPath" value="${pageContext.request.contextPath}" />
<!DOCTYPE html>
<html>
<body>
	<div class="content-title">
		<h4>
			<span class="main-title"><i class="glyphicon glyphicon-chevron-right">&nbsp;</i>인기 상품</span>
			&nbsp;<small>다른 고객들에게 인기 많은 상품!</small>
		</h4>
	</div>
    <div class="swiper-container viewed-items-containers">
        <div class="swiper-wrapper">
	       	<c:forEach var="fav" items="${favorite_list }">
	       		<div class="swiper-slide">
	       			<div class="product-grid">
			        	<div class="product-image">
			        		<a href="/products/${fav.product_id}">
					            <img src="${fav.product_thumbImg }">
			        		</a>
			        		<ul class="social">
								<li><a href="#"><i class="fa fa-shopping-bag"></i></a></li>
								<li><a href="#" onclick="cartBtn()"><i class="fa fa-shopping-cart"></i></a></li>
							</ul>
		    	    	</div>
						<div class="product-content">
							<h3 class="title">
								<a href="/products/${fav.product_id}">${fav.product_name}</a>
							</h3>
							<div class="price">
								<span>
									<fmt:formatNumber type="number" minIntegerDigits="1" pattern="0,000" value="${fav.product_price - fav.discount}"/>&nbsp;원
								</span>
								<c:if test="${fav.discount ne 0}">
									<span class="discount">
										<fmt:formatNumber type="number" minIntegerDigits="1" pattern="0,000" value="${fav.product_price}"/>&nbsp;원
									</span>
								</c:if>
							</div>
							<div class="rating">
									<c:choose>
										<c:when test="${fav.rating ne null}">
											<fmt:formatNumber var="rating" value="${fav.rating}" pattern="0.0" />
											<fmt:formatNumber var="avg" value="${rating*10}"/>
											<c:forEach begin="10" end="50" step="10" varStatus="idx">
												<c:choose>
													<c:when test="${avg >= idx.current}">
														<span class="fa fa-star"></span>
													</c:when>
													<c:otherwise>
														<c:if test="${(avg - idx.current) > 0}">
															<span class="fa fa-star-half-o"></span>
														</c:if>
														<c:if test="${(avg - idx.current) <= 0}">
															<span class="fa fa-star disable"></span>
														</c:if>
													</c:otherwise>
												</c:choose>
											</c:forEach>
										</c:when>
										<c:otherwise>
											<c:forEach begin="0" end="5">
												<span class="fa fa-star disable"></span>
											</c:forEach>
										</c:otherwise>
									</c:choose>
									<span class="reply-count">(${fav.reply_count })</span>
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
        var swiper = new Swiper('.viewed-items-containers', {
            slidesPerView: 4,
            spaceBetween: 30,
            slidesPerGroup: 4,
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