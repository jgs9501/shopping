<%@ page language="java" contentType="text/html; charset=utf-8"	pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<%@ taglib prefix="security" uri="http://www.springframework.org/security/tags"%>
<c:set var="contextPath" value="${pageContext.request.contextPath}" />
<!DOCTYPE html>
<html>
<head>
	<meta charset="utf-8">
	<meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
	<meta id="_csrf" name="_csrf" content="${_csrf.token}"/>
	<meta id="_csrf_header" name="_csrf_header" content="${_csrf.headerName}"/>
	<link rel="stylesheet" href="${contextPath}/resources/css/bootstrap.css">
	<link rel="stylesheet" href="${contextPath}/resources/css/swiper.css">
	<link rel="stylesheet" href="${contextPath}/resources/css/main.css">
	<link rel="stylesheet" href="https://pro.fontawesome.com/releases/v5.10.0/css/all.css"/>
	<link rel="stylesheet" href="${contextPath}/resources/css/modal.css">
	<script src="${contextPath}/resources/js/jquery.min.js"></script> 
	<script src="${contextPath}/resources/js/swiper.min.js"></script>
	<title>JS Shop</title>
</head>
<body>
	<header>
		<jsp:include page="/WEB-INF/views/header/header.jsp"></jsp:include>
	</header>
	
	<section>
		<ol class="breadcrumb container">
			<li><a href="${contextPath}/index">메인</a></li>
			<li class="active">상품조회</li>
		</ol>
		
		<div class="container">
		    <div class="row">
		        <div class="col-sm-12 col-md-10 col-md-offset-1">
		            <table class="table table-hover">
		                <thead>
		                    <tr>
		                        <th>상품</th>
		                        <th>카테고리</th>
		                        <th class="text-center">재고량</th>
		                        <th class="text-center">가격</th>
	                   	 	</tr>
	                	</thead>
	                	<tbody>
	                		<c:forEach var="product" items="${listResult}">
	                			<c:choose>
	                				<c:when test="${fn:length(listResult) > 0}">
				                    	<tr>
				                      		<td class="col-sm-8 col-sm-6">
				                        		<div class="media">
				                        			<div class="media-thumbnail pull-left">
					                            		<a href="/product/release/${product.seq_user_id}/${product.product_id}"> 
					                            			<img src="${product.product_thumbImg}"> 
					                            		</a>
				                        			</div>
					                            	<div class="media-body" style="padding-left: 20px;">
					                                <h4 class="media-heading"><a href="/product/release/${product.seq_user_id}/${product.product_id}">${product.product_name }</a></h4>
					                                <c:if test="${product.sale == 'Y' }">
						                                <span class="text-success"><strong>판매중</strong></span>
					                                </c:if>
					                                <c:if test="${product.sale == 'N' }">
					                                	<span class="text-fail"><strong>판매중지</strong></span>
					                                </c:if>
				                            		</div>
				                        		</div>
				                        	</td>
					                        <td id="categoryId" class="col-sm-1 col-md-1 text-center"><strong>${product.category }</strong></td>
					                        <td class="col-sm-1 col-md-1 text-center"><strong><fmt:formatNumber value="${product.product_cnt}" pattern="0,000" minIntegerDigits="1"></fmt:formatNumber>개</strong></td>
					                        <td class="col-sm-1 col-md-1 text-center"><strong><fmt:formatNumber value="${product.product_price}" pattern="0,000" minIntegerDigits="1"></fmt:formatNumber>원</strong></td>
				                   		</tr>
	                				</c:when>
	                				<c:otherwise>
	                					<h4>${listResult}</h4>
	                				</c:otherwise>
	                			</c:choose>
	                		</c:forEach>
	                   		<tr>
		                        <td></td>
		                        <td></td>
		                        <td></td>
		                        <td></td>
	                    	</tr>
	                	</tbody>
		            </table>
		        </div>
		    </div>
		</div>
	</section>
	<footer class="footer">
		<jsp:include page="/WEB-INF/views/footer/footer.jsp"></jsp:include>
    </footer>
</body>
</html>