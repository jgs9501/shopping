<%@ page language="java" contentType="text/html; charset=UTF-8"	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<c:set var="contextPath" value="${pageContext.request.contextPath}" />
<!DOCTYPE html>
<html>
<head>
	<link rel="stylesheet" href="${contextPath}/resources/css/main.css">
</head>
<body>
	<div class="card">
		<div class="card-body">
			<h4 class="glyphicon glyphicon-chevron-right"> 오늘의 상품
			<small>오늘 엄선된 상품입니다!</small></h4>
		</div>
	</div>
	<div class="swiper-container swiper-per-group">
	    <div class="swiper-wrapper swiper-margin">
	      <div class="swiper-slide thumbnail"><img src="./resources/images/test.png"></div>
	      <div class="swiper-slide thumbnail"><img src="./resources/images/test.png"></div>
	      <div class="swiper-slide thumbnail"><img src="./resources/images/test.png"></div>
	      <div class="swiper-slide thumbnail"><img src="./resources/images/test.png"></div>
	      <div class="swiper-slide thumbnail"><img src="./resources/images/test.png"></div>
	      <div class="swiper-slide thumbnail"><img src="./resources/images/test.png"></div>
	    </div>
	    <div class="swiper-pagination"></div>
	    <div class="swiper-button-next"></div>
	    <div class="swiper-button-prev"></div>
	  </div>
	<script src="https://unpkg.com/swiper/swiper-bundle.min.js"></script>
    <script>
    var swiper = new Swiper('.swiper-per-group', {
    	slidesPerView: 4,
        spaceBetween: 30,
        slidesPerGroup: 4,
        loop: true,
        loopFillGroupWithBlank: true,
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
