<%@ page language="java" contentType="text/html; charset=utf-8"	pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<c:set var="contextPath" value="${pageContext.request.contextPath}" />
<!DOCTYPE html>
<html>
<body>
	<div class="content-title">
		<h4>
			<span class="main-title"><i class="glyphicon glyphicon-chevron-right">&nbsp;</i>인기 상품</span>
			&nbsp;<small>다른 고객들에게 인기 많은 상품!</small>
		</h4>
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

    <script type="text/javascript">
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