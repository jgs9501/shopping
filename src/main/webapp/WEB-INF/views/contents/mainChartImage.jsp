<%@ page language="java" contentType="text/html; charset=utf-8"	pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<%@ taglib prefix="security" uri="http://www.springframework.org/security/tags"%>
<c:set var="contextPath" value="${pageContext.request.contextPath}" />
<!DOCTYPE html>
<html>
<body>
    <div class="swiper-container swiper-chart">
        <div class="swiper-wrapper">
      		<img class="swiper-slide" src="./resources/images/bling-4756998_1280.png"></img>
      		<img class="swiper-slide" src="./resources/images/fa17e84458aad32afecc722b63f9e05694613aed8c5b81c47b50e1ba31d607fa.png"></img>
      		<img class="swiper-slide" src="./resources/images/38924b4292747c.jpg"></img>
    	</div>
    	<div class="swiper-pagination"></div>
  	</div>
  	<!-- Initialize Swiper -->
    <script>
    	var swiper = new Swiper('.swiper-chart', {
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