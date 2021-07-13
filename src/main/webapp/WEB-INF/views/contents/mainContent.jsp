<%@ page language="java" contentType="text/html; charset=utf-8"	pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<%@ taglib prefix="security" uri="http://www.springframework.org/security/tags"%>
<c:set var="contextPath" value="${pageContext.request.contextPath}" />
<!DOCTYPE html>
<html>
<body>
	<div class="content-title">
		<h4>
			<span class="main-title"><i class="glyphicon glyphicon-chevron-right">&nbsp;</i>오늘의 상품</span>
			&nbsp;<small>오늘 엄선된 상품입니다!</small>
		</h4>
	</div>
	<div class="content-box">
		<div class="content content-half">
			<a href="/products/42"><img src="${contextPath }/resources/images/displayitem_a4b512cf-2055-40e1-8b4f-b3361724a2c5.jpg"></a>
		</div>
		<div class="content content-half">
			<a href="/products/41"><img src="${contextPath }/resources/images/C2_1.jpg"></a>
		</div>
		<div class="content content-quarter">
			<a href="/products/40"><img src="${contextPath }/resources/images/C2_4.jpg"></a>
		</div>
		<div class="content content-quarter">
			<a href="/products/43"><img src="${contextPath }/resources/images/quarter1.jpg"></a>
		</div>
		<div class="content content-quarter">
			<a href="/products/44"><img src="${contextPath }/resources/images/C2_8.jpg"></a>
		</div>
		<div class="content content-quarter">
			<a href="/products/45"><img src="${contextPath }/resources/images/C2_9_(1).jpg"></a>
		</div>
	</div>
	<hr>
	
	<script type="text/javascript">
		$(function () {
			$('.content-box img').hover(function () {
				var content = $(this);
				$(content).addClass('hover-img');
			})
		})
	</script>
</body>
</html>
