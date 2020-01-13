<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<!DOCTYPE html>
<html>
<head>
	<meta charset="utf-8">
	<meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
	<!-- Bootstrap CSS -->
	<link rel="stylesheet" href="./resources/bootstrap/css/bootstrap.min.css">
	<script src="./resources/bootstrap/js/jquery.min.js"></script> 
	<script src="./resources/bootstrap/js/bootstrap.min.js"></script>
	<title>JS Shop</title>
	<style type="text/css">
		.header {
			width: 100%;
			position: static;
			list-style-type: none;
			float: left;
		}
		.nav-main {
			margin-left: auto;
			margin-right: auto;
			width: 100%;
			min-width: 1000px;
			max-width: 1400px;
		}
		.set {
			width: 1518px;
		}
		.aside-main {
			width: 170px;
		}
		.section-main {
			float: left;
			width: 1348px;
			overflow: hidden;
		}
		.article-viewed-items {
			padding-left: 20px;
		}
		.footer {
			width: 100%;
			clear: both;
			position: relative;
		}
	</style>
</head>
<body>
	<!-- 머릿말 -->
    <header class="header">
		<jsp:include page="header/header.jsp"></jsp:include>
    </header>
    <!-- 광고차트 -->
    <nav class="nav-main">
    	<jsp:include page="contents/mainChartImage.jsp"></jsp:include>
    </nav>
	<div class="set">
		<!-- 상품목록 -->
	    <aside class="aside-main">
	    	<jsp:include page="side/shoppingList.jsp"></jsp:include>
	    </aside>
		<!-- 콘텐츠 -->
	    <section class="section-main">
	    	<article>
				<jsp:include page="contents/mainContent.jsp"></jsp:include>
	    	</article>
			<article><h5 class="article-viewed-items">최근 본 상품</h5>
				<jsp:include page="contents/mainViewedItems.jsp"></jsp:include>
			</article>
	    </section>
	</div>
	<!--바닥글-->
    <footer class="footer">
		<jsp:include page="footer/footer.jsp"></jsp:include>
    </footer>
</body>
</html>
