<%@ page language="java" contentType="text/html; charset=UTF-8"	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
	<!-- Link Swiper's CSS -->
	<link rel="stylesheet" href="./resources/css/swiper.css">
	<!-- Swiper JS -->
	<script src="./resources/js/jquery.min.js"></script> 
	<script src="./resources/js/swiper.min.js"></script>
	<!-- Demo styles -->
    <style type="text/css">
	    .swiper-container {
	    	float: left;
	        height: 500px;
	        width: 100%;
	    }
	    .swiper-slide {
	        text-align: center;
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
	        overflow: hidden;
	    }
     </style>
</head>
<body>
	<!-- Swiper -->
    <div class="swiper-container">
        <div class="swiper-wrapper">
      		<img class="swiper-slide" src="./resources/images/bling-4756998_1280.png"></img>
      		<img class="swiper-slide" src="./resources/images/fa17e84458aad32afecc722b63f9e05694613aed8c5b81c47b50e1ba31d607fa.png"></img>
      		<img class="swiper-slide" src="./resources/images/38924b4292747c.jpg"></img>
    	</div>
    	<!-- Add Pagination -->
    	<div class="swiper-pagination"></div>
  	</div>
  	<!-- Initialize Swiper -->
    <script>
    	var swiper = new Swiper('.swiper-container', {
        	spaceBetween: 30,
        	centeredSlides: true,
        	autoplay: {
          		delay: 5000,
          		disableOnInteraction: false,
        	},
        	pagination: {
	        	el: '.swiper-pagination',
	            clickable: true,
	        },
	    });
    </script>
</body>
</html>