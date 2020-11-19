<%@ page language="java" contentType="text/html; charset=UTF-8"	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<c:set var="contextPath" value="${pageContext.request.contextPath}" />
<!DOCTYPE html>
<html>
<head>
	<!-- Link Swiper's CSS -->
	<link rel="stylesheet" href="./resources/css/swiper.css">
	<script src="./resources/js/jquery.min.js"></script> 
	<!-- Swiper JS -->
	<script src="./resources/js/swiper.min.js"></script>
	<!-- Demo styles -->
	<style type="text/css">
	    .viewed-items-containers {
	    	float: left;
	    	padding-bottom: 50px;
	    	padding-left: 100px;
	    	padding-right: 100px;
	        width: 1200px;
	        height: 100%;
	    }
	    .swiper-slide {
	        text-align: center;
	        font-size: 18px;
	        background: #fff;
	        /* Center slide text vertically */
	        display: -webkit-box;
	        display: -ms-flexbox;
	        display: -webkit-flex;
	        display: flex;
	        -webkit-box-pack: center;
	        -ms-flex-pack: center;
	        -webkit-justify-content: center;
	        justify-content: center;
	        -webkit-box-align: center;
	        -ms-flex-align: center;
	        -webkit-align-items: center;
	        align-items: center;
	    }
	    
	    .swiper-margin {
			margin-left: 100px;
			margin-top: 10px;
			margin-right: 100px;
			margin-bottom: 10px;
		}
	</style>
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