<%@ page language="java" contentType="text/html; charset=UTF-8"	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<c:set var="contextPath" value="${pageContext.request.contextPath}" />
<!DOCTYPE html>
<html>
<head>
	<link rel="stylesheet" href="./resources/css/swiper.css">
	<script src="./resources/js/jquery.min.js"></script> 
	<script src="./resources/js/swiper.min.js"></script>
	<link rel="stylesheet" href="${contextPath}/resources/css/main.css">
</head>
<body>
<!-- Swiper -->
	<div class="card">
		<div class="card-body">
			<h4 class="glyphicon glyphicon-chevron-right" style="font-weight: bold;"> 인기상품
			<small>다른 고객들에게 인기많은 상품!</small></h4>
		</div>
	</div>
    <div class="swiper-container viewed-items-containers">
        <div class="swiper-wrapper">
        <%for(int i=0;i<10; i++){ %>
        	<div class="thumbnail swiper-slide">
	        	<div>
		            <img style="height: 240px; width: 100%; display: block;"
		            src="./resources/images/242x200.png">
					<div>
						<h3 style="text-align: left;">TEST</h3>
						<h4 style="text-align: right; font-weight: bold;">test</h4>
					</div>
    	    	</div>
        	</div>
        <%} %>
    	</div>
   		<div class="swiper-pagination"></div>
	    <div class="swiper-button-next"></div>
	    <div class="swiper-button-prev"></div>
  	</div>

    <script>
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