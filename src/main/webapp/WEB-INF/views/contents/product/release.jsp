<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<c:set var="contextPath" value="${pageContext.request.contextPath}" />
<!DOCTYPE html>
<html>
<head>
	<meta charset="utf-8">
	<meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
	<title>JS Shop</title>
	<script src="${contextPath}/resources/js/jquery.min.js"></script> 
	<script src="${contextPath}/resources/js/bootstrap-select.js"></script>
	<script src="${contextPath}/resources/js/bootstrap.min.js"></script>
	<link rel="stylesheet" href="${contextPath}/resources/css/bootstrap.css">
	
</head>
<body>
	<!-- 머릿글 -->
	<header>
		<jsp:include page="/WEB-INF/views/header/header.jsp"></jsp:include>
	</header>
	
	<ol class="breadcrumb container">
		<li><a href="${contextPath}/index">메인</a></li>
		<li class="active">상품등록</li>
	</ol>
	
	<div class="container">
		<section>
			<form class="form-horizontal form-width" method="post" onsubmit="return checkInfo()" enctype="multipart/form-data">
				<input type="hidden" name="seq_user_id" value="${userVO.seq_user_id}"> 
				<!-- 상품이름 -->
		    	<div id="productName" class="form-group has-feedback">
			    	<label class="col-sm-2 control-label">상품이름</label>
		    		<div class="col-sm-10">
			    		<input type="text" class="form-control" id="product_name" name="product_name" placeholder="상품이름을 입력하세요" maxlength="13">
			    		<span id="productNameIcon" class="glyphicon form-control-feedback" aria-hidden="true"></span>
	      				<span id="productNameStatus" class="sr-only"></span>
			    	</div>
			    	<span id="spanProductName"></span>
		  		</div><hr>
				<!-- 상품개수 -->
		  		<div id="productCnt" class="form-group has-feedback">
		    		<label class="col-sm-2 control-label">상품개수</label>
		    		<div class="col-sm-10">
		      			<input type="number" class="form-control" id="product_cnt" name="product_cnt" placeholder="상품개수를 입력하세요" maxlength="13">
		      			<span id="productCntIcon" class="glyphicon form-control-feedback" aria-hidden="true"></span>
	      				<span id="productCntStatus" class="sr-only"></span>
		    		</div>
		    		<span id="spanProductCnt"></span>
		  		</div><hr>
				<!-- 상품가격 -->
		  		<div id="productPrice" class="form-group has-feedback">
		    		<label class="col-sm-2 control-label">상품가격</label>
		    		<div class="col-sm-10">
		      			<input type="number" class="form-control" id="product_price" name="product_price" placeholder="상품가격을 입력하세요" maxlength="10">
		      			<span id="productPriceIcon" class="glyphicon form-control-feedback" aria-hidden="true"></span>
	      				<span id="productPriceStatus" class="sr-only"></span>
		    		</div>
		    		<span id="spanProductPrice"></span>
		  		</div><hr>
		  		<!-- 상품 상세정보 -->
		  		<div class="form-group">
		    		<label class="col-sm-2 control-label">상세정보</label>
		    		<div class="col-sm-10">
		      			<textarea class="form-control" rows="4" id="product_desc" name="product_desc"></textarea>
		    		</div>
		      		<span id="spanProductDesc"></span>
		  		</div><hr>
		  		<!-- 상품사진 -->
		  		<div class="form-group">
		    		<label class="col-sm-2 control-label">상품사진</label>
		    		<div class="col-sm-4">
		      			<input type="file" class="form-control" id="uploadFile" name="uploadFile" accept=".jpg, .png">
		    		</div>
					<span id="spanProductImg"></span>
		  		</div><hr>
		  		<!-- 카테고리 -->
		  		<div class="form-group">
		    		<label class="col-sm-2 control-label">카테고리</label>
		    		<div class="col-sm-10">
		    			<select id="category" name="category">
		    				<option value="100">전기/전자제품</option>
							<option value="200">화장품/향수</option>
						    <option value="300">식료품</option>
						    <option value="400">의류</option>
						    <option value="500">신발류</option>
						    <option value="600">악세서리</option>
						    <option value="700">사무용품</option>
						    <option value="800">주방용품</option>
						    <option value="900">음반/DVD</option>
						    <option value="1000">기타</option>
		    			</select>
		    		</div>
		    		<span id="spanCategory"></span>
		  		</div><hr>
		  		<!-- 판매유무 -->
		  		<div class="form-group">
		    		<label class="col-sm-2 control-label">판매유무</label>
		    		<div class="col-sm-10">
			    		<select id="sale" name="sale">
			    			<option value="Y">즉시판매</option>
							<option value="N">판매보류</option>
			    		</select>
		    		</div>
		    		<span id="spanSale"></span>
		  		</div><hr>
		  		<!-- 할인유무 -->
		  		<div class="form-group">
		    		<label class="col-sm-2 control-label">할인가격</label>
		    		<div class="col-sm-10">
		      			<input type="number" class="form-control" id="discount" name="discount" placeholder="할인이 없을경우 '0'을 입력해주세요" maxlength="10">
		    		</div>
		    		<span id="spanDiscount"></span>
		  		</div><hr>
		  		<div class="form-group" align="center">
	      			<button type="submit" class="btn btn-primary btn-lg">상품 등록</button>
	  			</div>
			</form>
		</section>
	</div>
	<!--바닥글-->
    <footer class="footer">
		<jsp:include page="/WEB-INF/views/footer/footer.jsp"></jsp:include>
    </footer>
</body>
<script type="text/javascript">
	var checkNumber = RegExp(/^[0-9]{1,10}$/);
	function checkInfo() {
		$('span').empty();
		if($('#product_name').val() == "") {
			$('#spanProductName').text("상품 이름을 입력해주세요").css("color", "red");
			$('#product_name').focus();
			return false;
		}
		if(!checkNumber.test($('#product_cnt').val())) {
			$('#spanProductCnt').text("상품 개수를 입력해주세요").css("color", "red");
			$('#product_cnt').focus();
			return false;
		}
		if(!checkNumber.test($('#product_price').val())) {
			$('#spanProductPrice').text("상품 가격을 입력해주세요").css("color", "red");
			$('#product_price').focus();
			return false;
		}
		if($('#product_desc').val() == "") {
			$('#spanProductDesc').text("상품 설명을 입력해주세요").css("color", "red");
			$('#product_desc').focus();
			return false;
		}
		if($('#discount').val() == "") {
			$('#spanDiscount').text("할인 가격을 입력해주세요.").css("color", "red");
			$('#discount').focus();
			return false;
		}
		return true;
	}
</script>
</html>