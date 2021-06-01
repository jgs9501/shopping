<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<c:set var="contextPath" value="${pageContext.request.contextPath}" />

<!DOCTYPE html>
<html>
<head>
	<meta charset="utf-8">
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
	<title>JS Shop</title>
</head>
<body>
    <header>
		<jsp:include page="/WEB-INF/views/header/header.jsp"></jsp:include>
    </header>
    <nav>
    	<jsp:include page="/WEB-INF/views/navbar/search_nav.jsp"></jsp:include>
	    <jsp:include page="/WEB-INF/views/navbar/category_nav.jsp"></jsp:include>
    	<jsp:include page="/WEB-INF/views/contents/mainChartImage.jsp"></jsp:include>
    </nav>
	<div class="main-container">
	    <%-- <aside class="aside-list fixed-aside">
	    	<jsp:include page="side/shoppingList.jsp"></jsp:include>
	    </aside> --%>
		<!-- 콘텐츠 -->
	    <section class="section-main">
	    	<article>
				<jsp:include page="/WEB-INF/views/contents/mainContent.jsp"></jsp:include>
	    	</article>
			<article>
				<jsp:include page="/WEB-INF/views/contents/mainViewedItems.jsp"></jsp:include>
			</article>
	    </section>
	</div>
	
    <footer class="footer">
		<jsp:include page="/WEB-INF/views/footer/footer.jsp"></jsp:include>
    </footer>
</body>
</html>
