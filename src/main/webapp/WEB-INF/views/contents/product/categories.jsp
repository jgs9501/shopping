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
<link rel="stylesheet" href="${contextPath}/resources/css/bootstrap.css">
<script src="${contextPath}/resources/js/jquery.min.js"></script> 
<script src="${contextPath}/resources/js/bootstrap.min.js"></script>
<link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/font-awesome/4.7.0/css/font-awesome.min.css"/>
<link rel="stylesheet" href="${contextPath}/resources/css/main.css">
</head>
<body>
	<header>
		<jsp:include page="/WEB-INF/views/header/header.jsp"></jsp:include>
	</header>
	<ol class="breadcrumb container">
		<li><a href="${contextPath}/index">메인</a></li>
		<li class="active">${categoryName}</li>
	</ol>
	<div class="main-container">
		<aside>
			<jsp:include page="/WEB-INF/views/side/shoppingList.jsp"></jsp:include>
		</aside>
		<section>
			<article>
				<div class="product-container">
				    <h3 class="h3">최근 출시된 상품</h3>
				    <div class="row">
				    	<c:if test="${fn:length(recentlyProducts) > 0}">
					    	<c:forEach items="${recentlyProducts}" var="product">
						        <div class="col-xs-3 col-xs-6">
						            <div class="product-grid">
						                <div class="product-image">
						                	<input type="hidden" value="${product.seq_user_id}">
						                    <a href="${contextPath}/products/${product.product_id}">
						                        <img class="pic-1" src="${product.product_thumbImg}">
						                    </a>
						                    <ul class="social">
						                        <li><a href="#"><i class="fa fa-shopping-bag"></i></a></li>
						                        <li><a href="#"><i class="fa fa-shopping-cart"></i></a></li>
						                    </ul>
						                    <jsp:useBean id="today" class="java.util.Date"/>
										    <fmt:formatDate var="now" value="${today}" pattern="yyyyMMdd"/>
										    <fmt:parseDate var="parseReg" value="${product.reg_date}" pattern="yyyy-MM-dd HH:mm:ss"/>
										    <fmt:formatDate var="reg" value="${parseReg}" pattern="yyyyMMdd"/>
						                    <c:if test="${now-reg eq 0}">
						  	                  <span class="product-new-label">New</span>
						                    </c:if>
					                	</div>
						                <div class="product-content">
						                    <h3 class="title"><a href="${contextPath}/products/${product.product_id}">${product.product_name}</a></h3>
						   	     	        <div class="price">
						   	     	           <fmt:formatNumber type="number" minIntegerDigits="1" pattern="0,000" value="${product.product_price - product.discount}"></fmt:formatNumber>원
							                    <c:if test="${product.discount ne 0}">
							   	     	           <span><fmt:formatNumber type="number" minIntegerDigits="1" pattern="0,000" value="${product.product_price}"></fmt:formatNumber>원</span>
							                    </c:if>
						   	     	        </div>
						                    <ul class="rating">
						                        <li class="fa fa-star"></li>
						                        <li class="fa fa-star"></li>
						                        <li class="fa fa-star"></li>
						                        <li class="fa fa-star-half-o"></li>
						                        <li class="fa fa-star disable"></li>
						                    </ul>
						                </div>
					                </div>
					            </div>
					       	</c:forEach>
				    	</c:if>
				    	<c:if test="${fn:length(recentlyProducts) < 1}">
				    		<br>
				    		<div>
				    			<h4 class="h4" style="margin-left: 30px;">${recentlyProductResult}</h4>
				    		</div>
				    	</c:if>
					</div>
				
				<hr>
				<h3 class="h3">${categoryName} <em class="total-product-count">(<c:out value="${fn:length(totalProducts)}"></c:out>)</em></h3>
				<div class="row">
				    	<c:if test="${fn:length(totalProducts) > 0}">
					    	<c:forEach items="${totalProducts}" var="product">
						        <div class="col-xs-3 col-xs-6">
						            <div class="product-grid">
						                <div class="product-image">
						                	<input type="hidden" value="${product.seq_user_id}">
						                    <a href="${contextPath}/products/${product.product_id}">
						                        <img class="pic-1" src="${product.product_thumbImg}">
						                    </a>
						                    <ul class="social">
						                        <li><a href="#"><i class="fa fa-shopping-bag"></i></a></li>
						                        <li><a href="#"><i class="fa fa-shopping-cart"></i></a></li>
						                    </ul>
					                	</div>
						                <div class="product-content">
						                    <h3 class="title"><a href="${contextPath}/products/${product.product_id}">${product.product_name}</a></h3>
						   	     	        <div class="price">
						   	     	           <fmt:formatNumber type="number" minIntegerDigits="1" pattern="0,000" value="${product.product_price - product.discount}"></fmt:formatNumber>원
							                    <c:if test="${product.discount ne 0}">
							   	     	           <span><fmt:formatNumber type="number" minIntegerDigits="1" pattern="0,000" value="${product.product_price}"></fmt:formatNumber>원</span>
							                    </c:if>
						   	     	        </div>
						                    <ul class="rating">
						                        <li class="fa fa-star"></li>
						                        <li class="fa fa-star"></li>
						                        <li class="fa fa-star"></li>
						                        <li class="fa fa-star-half-o"></li>
						                        <li class="fa fa-star disable"></li>
						                    </ul>
						                </div>
					                </div>
					            </div>
					       	</c:forEach>
				    	</c:if>
				    	<c:if test="${fn:length(totalProducts) < 1}">
				    		<br>
				    		<div>
				    			<h4 class="h4" style="margin-left: 30px;">${totalProductResult}</h4>
				    		</div>
				    	</c:if>
					</div>
				</div>
			</article>
		</section>
	</div>
	
	<footer>
		<jsp:include page="/WEB-INF/views/footer/footer.jsp"></jsp:include>
	</footer>
</body>
</html>