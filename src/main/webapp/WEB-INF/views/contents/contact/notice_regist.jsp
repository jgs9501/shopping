<%@ page language="java" contentType="text/html; charset=utf-8"	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<c:set var="contextPath" value="${pageContext.request.contextPath}"/>
<!DOCTYPE html>
<html>
<head>
	<meta charset="UTF-8">
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
</head>
<body>
	<header>
		<jsp:include page="/WEB-INF/views/header/header.jsp"></jsp:include>
	</header>
	<nav>
    	<jsp:include page="/WEB-INF/views/navbar/search_nav.jsp"></jsp:include>
	    <jsp:include page="/WEB-INF/views/navbar/category_nav.jsp"></jsp:include>
    </nav>
    <section>
    	<div class="container">
    		<h2><strong>공지사항등록</strong></h2>
    		<hr>
    		<form class="form-horizontal" method="post" onsubmit="return checkInfo()" enctype="multipart/form-data">
    			<!-- 제목 -->
    			<div class="form-group">
	    			<label class="col-sm-1 control-label"><span class="emphasis">*</span>제목</label>
	    			<div class="col-sm-10">
				    	<input type="text" class="form-control" id="title" name="title" placeholder="제목을 입력하세요" maxlength="50">
				    	<span id="spanTitle"></span>
				    </div>
    			</div>
    			<hr>
    			<!-- 공지 종류 -->
    			<div class="form-group">
	    			<label class="col-sm-1 control-label"><span class="emphasis">*</span>종류</label>
	    			<div class="col-sm-10">
				    	<select id="type" name="type">
		    				<option value="PI">상품문의</option>
							<option value="DI">배송문의</option>
						    <option value="UI">회원문의</option>
						    <option value="OI">주문/결제</option>
						    <option value="ETC">기타</option>
		    			</select>
				    </div>
    			</div>
    			<hr>
			    <!-- 내용 -->
			    <div class="form-group">
				    <label class="col-sm-1 control-label"><span class="emphasis">*</span>내용</label>
	    			<div class="col-sm-10">
			      		<textarea class="form-control" rows="4" id="content" name="content"></textarea>
			    	</div>
			      	<span id="spanContent"></span>
			    </div>
			    <hr>
			    <!-- 사진 -->
		  		<div class="form-group">
		    		<label class="col-sm-1 control-label">사진1</label>
		    		<div class="col-sm-6">
		      			<input type="file" class="form-control" id="uploadFile1" name="uploadFile1" accept=".jpg, .png">
		    		</div>
				</div>
		  		<div class="form-group">
		    		<label class="col-sm-1 control-label">사진2</label>
		    		<div class="col-sm-6">
		      			<input type="file" class="form-control" id="uploadFile2" name="uploadFile2" accept=".jpg, .png">
		    		</div>
				</div>
		  		<div class="form-group">
		    		<label class="col-sm-1 control-label">사진3</label>
		    		<div class="col-sm-6">
		      			<input type="file" class="form-control" id="uploadFile3" name="uploadFile3" accept=".jpg, .png">
		    		</div>
				</div>
				<hr>
				<div class="form-group" align="center">
					<input type="hidden" name="seq_user_id" value="${userVO.seq_user_id}">
	      			<button type="submit" class="btn btn-primary btn-lg">확인</button>
	  			</div>
    		</form>
    	</div>
    </section>
    <footer>
		<jsp:include page="/WEB-INF/views/footer/footer.jsp"></jsp:include>
	</footer>
	
	<script type="text/javascript">
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
</body>
</html>