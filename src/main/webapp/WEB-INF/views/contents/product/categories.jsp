<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<c:set var="contextPath" value="${pageContext.request.contextPath}" />
<!DOCTYPE html>
<html>
<head>
<title>JS SHOP</title>
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
		<ol class="breadcrumb container">
			<li><a href="${contextPath}/index">메인</a></li>
			<li class="active">${categoryName}</li>
		</ol>
		<div class="main-container">
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
						                    	<c:choose>
						                    		<c:when test="${product.rating ne null}">
						                    			<fmt:formatNumber var="rating" value="${product.rating}" pattern=".0"/>
						                    			<fmt:formatNumber var="avg" value="${rating*10}"/>
						                    			<c:forEach begin="10" end="50" step="10" varStatus="idx">
															<c:choose>
																<c:when test="${avg >= idx.current}">
																	<li class="fa fa-star"></li>
																</c:when>
																<c:otherwise>
																	<c:if test="${(avg - idx.current) > 0}">
																		<li class="fa fa-star-half-o"></li>
																	</c:if>
																	<c:if test="${(avg - idx.current) <= 0}">
																		<li class="fa fa-star disable"></li>
																	</c:if>
																</c:otherwise>
															</c:choose>
														</c:forEach>
						                    		</c:when>
						                    		<c:otherwise>
						                    			<c:forEach begin="0" end="5">
							                    			<li class="fa fa-star disable"></li>
						                    			</c:forEach>
						                    		</c:otherwise>
						                    	</c:choose>
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
				<h3 class="h3">${categoryName} <em class="total-product-count">(<c:out value="${categoryProductCount}"></c:out>)</em></h3>
				<div class="row">
				    	<c:if test="${fn:length(productList) > 0}">
					    	<c:forEach items="${productList}" var="product">
					    		<c:set var="category_id" value="${product.category}"/>
						        <div class="col-xs-3 col-xs-6">
						            <div class="product-grid">
						                <div class="product-image">
						                	<input type="hidden" value="${product.seq_user_id}">
						                    <a href="${contextPath}/products/${product.product_id}">
						                        <img class="pic-1" src="${product.product_thumbImg}">
						                    </a>
						                    <ul class="social">
						                        <li><a href="#"><i class="fa fa-shopping-bag"></i></a></li>
						                        <li><a href="#" onclick="cartBtn()"><i class="fa fa-shopping-cart"></i></a></li>
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
						                    	<c:choose>
						                    		<c:when test="${product.rating ne null}">
						                    			<fmt:formatNumber var="rating" value="${product.rating}" pattern="0.0"/>
						                    			<fmt:formatNumber var="avg" value="${rating*10}"/>
						                    			<c:forEach begin="10" end="50" step="10" varStatus="idx">
															<c:choose>
																<c:when test="${avg >= idx.current}">
																	<li class="fa fa-star"></li>
																</c:when>
																<c:otherwise>
																	<c:if test="${(avg - idx.current) > 0}">
																		<li class="fa fa-star-half-o"></li>
																	</c:if>
																	<c:if test="${(avg - idx.current) <= 0}">
																		<li class="fa fa-star disable"></li>
																	</c:if>
																</c:otherwise>
															</c:choose>
														</c:forEach>
						                    		</c:when>
						                    		<c:otherwise>
						                    			<c:forEach begin="0" end="5">
							                    			<li class="fa fa-star disable"></li>
						                    			</c:forEach>
						                    		</c:otherwise>
						                    	</c:choose>
						                    </ul>
						                </div>
					                </div>
					            </div>
					       	</c:forEach>
					       	
				    	</c:if>
				    	<c:if test="${fn:length(productList) < 1}">
				    		<br>
				    		<div>
				    			<h4 class="h4" style="margin-left: 30px;">${totalProductResult}</h4>
				    		</div>
				    	</c:if>
					</div>
					<div style="text-align: center;">
						<c:set var="info" value="${pagination}" />
					  	<ul class="pagination">
						  	<c:if test="${info.curPage ne 1}">
							    <li><a href="javascript:void(0);" onclick="fn_paging(${category_id},${info.prevPage})" aria-label="Previous"><span aria-hidden="true">&laquo;</span></a></li>
						  	</c:if>
						  	<c:forEach var="pageNum" begin="${info.startPage}" end="${info.endPage}">
						  		<c:choose>
						  			<c:when test="${pageNum eq info.curPage}">
									    <li class="active"><a href=javascript:void(0); onclick="fn_paging(${category_id},${pageNum})">${pageNum} <span class="sr-only">(current)</span></a></li>
						  			</c:when>
						  			<c:otherwise>
									    <li><a href="javascript:void(0);" onclick="fn_paging(${category_id},${pageNum})">${pageNum}</a></li>
						  			</c:otherwise>
						  		</c:choose>
						  	</c:forEach>
						  	<c:if test="${info.curPage ne info.pageCnt && info.pageCnt > 0}">
							    <li><a href="javascript:void(0);" onclick="fn_paging(${category_id},${info.nextPage})" aria-label="Next"><span aria-hidden="true">&raquo;</span></a></li>
						  	</c:if>
					 	</ul> 
					</div>
				</div>
			</article>
		</div>
	</section>
	
	<footer>
		<jsp:include page="/WEB-INF/views/footer/footer.jsp"></jsp:include>
	</footer>
	
	<script>
		function fn_paging(category, curPage) {
			location.href = "/categories/"+category+"?curPage="+curPage;
		}
		function cartBtn() {
			var flag = confirm("장바구니에 추가하시겠습니까?");
			if(flag) {
				location.replace("${contextPath}/cart");
			}
		}
	</script>
</body>
</html>