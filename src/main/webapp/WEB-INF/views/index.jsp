<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<c:set var="contextPath" value="${pageContext.request.contextPath}" />

<!DOCTYPE html>
<html>
<head>
	<meta charset="utf-8">
	<meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
	<!-- Bootstrap CSS -->
	<link rel="stylesheet" href="${contextPath}/resources/css/bootstrap.css">
	<script src="${contextPath}/resources/js/jquery.min.js"></script> 
	<script src="${contextPath}/resources/js/bootstrap.min.js"></script>
	<link rel="stylesheet" href="${contextPath}/resources/css/swiper.css">
	<script src="${contextPath}/resources/js/swiper.min.js"></script>
	<link rel="stylesheet" href="${contextPath}/resources/css/main.css">
	<title>JS Shop</title>
	<!-- <script>
	$(window).scroll(function(){
		var $fixed = $('.fixed-aside'); 
		var $footer = $('.footer');
		startPoint = parseInt($('.nav-main').outerHeight()),
		scrollPoint = $(window).scrollTop() + $(window).height(),
		limit = 0;
		if($footer.length) {
			limit = $('.footer').offset().top;
		}
		console.log ( 'startPoint : ' + startPoint );
		  console.log ( 'scrollPoint : ' + scrollPoint );
		    console.log ( 'limit : ' + limit );
		    console.log ( 'limit3 : ' + (limit - $footer.height()) );

		if(parseInt(startPoint) < parseInt(scrollPoint) && parseInt(scrollPoint) < parseInt(limit-200)) {
			$fixed.addClass('fixed').css('top', startPoint);
			$fixed.removeClass('abs');
		} else {
			$fixed.removeClass('fixed');
			$fixed.addClass('abs').css('top', 800);
		}
	});
	</script> -->
</head>
<body>
    <header class="header">
		<jsp:include page="header/header.jsp"></jsp:include>
    </header>
    <nav>
    	<jsp:include page="contents/search.jsp"></jsp:include>
    </nav>
    <nav class="nav-main">
    	<jsp:include page="contents/mainChartImage.jsp"></jsp:include>
    </nav>
	<div class="main-container">
	    <aside class="aside-list fixed-aside">
	    	<jsp:include page="side/shoppingList.jsp"></jsp:include>
	    </aside>
		<!-- 콘텐츠 -->
	    <section class="section-main">
	    	<article>
				<jsp:include page="contents/mainContent.jsp"></jsp:include>
	    	</article>
			<article>
				<jsp:include page="contents/mainViewedItems.jsp"></jsp:include>
			</article>
	    </section>
	</div>
	
    <footer class="footer">
		<jsp:include page="footer/footer.jsp"></jsp:include>
    </footer>
</body>
</html>
