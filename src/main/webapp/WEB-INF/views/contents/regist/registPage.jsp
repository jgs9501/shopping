<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<c:set var="contextPath" value="${pageContext.request.contextPath}" />

<!DOCTYPE html>
<html>
<head>
	<meta charset="UTF-8">
	<meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
	<!-- Bootstrap CSS -->
	<link rel="stylesheet" href="${contextPath}/resources/css/bootstrap.css">
	<script src="${contextPath}/resources/js/jquery.min.js"></script>
	<script src="${contextPath}/resources/js/bootstrap.min.js"></script>
	<title>회원가입</title>
	<style type="text/css">
		.center-auto{
			margin-left: auto;
			margin-right: auto;
			width: 100%;
			
		}
		.form-width{
			float: left;
			padding-left: 100px;
			min-width: 500px;
			max-width: 800px;
			width: 600px;
			position: relative;
		}
	</style>
</head>
<body>
	<!-- 머릿글 -->
	<header>
		<jsp:include page="/WEB-INF/views/header/header.jsp"></jsp:include>
	</header>
	
	<ol class="breadcrumb center-auto">
		<li><a href="#">Home</a></li>
		<li><a href="#">Library</a></li>
		<li class="active">Data</li>
	</ol>
	<!-- 리스트 -->
	<div class="container">
		<aside>
			<jsp:include page="/WEB-INF/views/side/registList.jsp"></jsp:include>
		</aside>
	
	<!-- 회원가입 폼 -->
	<section>
		<form class="form-horizontal form-width center-auto">
			<!-- 아이디 -->
	    	<div class="form-group">
		    	<label for="inputEmail" class="col-sm-2 control-label">아이디</label>
	    		<div class="col-sm-8">
		    		<input type="text" class="form-control" id="userId" placeholder="영문 포함 6자리이상 입력해주세요">
		    	</div>
		    	<button type="button" class=" btn btn-default col-sm-1">
		    		<span class="glyphicon glyphicon-ok"></span>
		    	</button>
	  		</div><hr>
			<!-- 비밀번호 -->
	  		<div class="form-group">
	    		<label for="inputPassword3" class="col-sm-2 control-label">비밀번호</label>
	    		<div class="col-sm-10">
	      			<input type="password" class="form-control" id="password" placeholder="영문 숫자포함 6자리이상 입력해주세요">
	    		</div>
	  		</div><hr>
			<!-- 비밀번호 확인 -->
	  		<div class="form-group">
	    		<label for="inputPassword3" class="col-sm-2 control-label">비밀번호 확인</label>
	    		<div class="col-sm-10">
	      			<input type="password" class="form-control" id="passwordCheck" placeholder="비밀번호를 다시 입력해주세요">
	    		</div>
	  		</div><hr>
	  		<!-- 이름 -->
	  		<div class="form-group">
	    		<label for="inputPassword3" class="col-sm-2 control-label">이 름</label>
	    		<div class="col-sm-10">
	      			<input type="text" class="form-control" id="userName">
	    		</div>
	  		</div><hr>
	  		<!-- 이메일 -->
	  		<div class="form-group">
	    		<label class="col-sm-2 control-label">이메일</label>
	    		<div class="col-sm-4">
	      			<input type="text" class="form-control" id="emailId">
	    		</div>
		    	<div class="input-group">
					<span class="input-group-addon">@</span>
					<input type="text" class="form-control" placeholder="gmail.com" aria-describedby="sizing-addon2">
				</div>
	  		</div><hr>
	  		<!-- 생년월일 -->
	  		<div class="form-group">
	    		<label for="inputPassword3" class="col-sm-2 control-label">생년월일</label>
	    		<div class="col-sm-3">
	      			<input type="text" class="form-control" id="birthYear" placeholder="1995">
	    		</div>
	    		<div class="col-sm-3">
	      			<input type="text" class="form-control" id="birthMonth" placeholder="01">
	    		</div>
	    		<div class="col-sm-3">
	      			<input type="text" class="form-control" id="birthDay" placeholder="01">
	    		</div>
	  		</div><hr>
	  		<!-- 전화번호 -->
	  		<div class="form-group">
	    		<label for="inputPassword3" class="col-sm-2 control-label">전화번호</label>
	    		<div class="col-sm-3">
	      			<input type="text" class="form-control" id="phoneNumber1" placeholder="010">
	    		</div>
	    		<div class="col-sm-3">
	      			<input type="text" class="form-control" id="phoneNumber2" placeholder="1234">
	    		</div>
	    		<div class="col-sm-3">
	      			<input type="text" class="form-control" id="phoneNumber3" placeholder="5678">
	    		</div>
	  		</div><hr>
	  		
	  		<!-- 우편번호 -->
	  		<div class="form-group">
	    		<label class="col-sm-2 control-label">우편번호</label>
	    		<div class="col-sm-5">
	      			<input type="number" class="form-control" id="postNumber" readonly="readonly">
	    		</div>
	    		<div>
	    			<jsp:include page="/WEB-INF/views/modal/postModal.jsp"></jsp:include>
	    			<button type="button" class="btn btn-default btn-sm" onclick="execDaumPostcode()">우편번호</button>
	    		</div>
	  		</div>
	  		<!-- 주소 -->
	  		<div class="form-group">
	    		<label for="inputPassword3" class="col-sm-2 control-label">주 소</label>
	    		<div class="col-sm-10">
	      			<input type="text" class="form-control" id="address" readonly="readonly">
	    		</div>
	  		</div>
	  		<!-- 상세주소 -->
	  		<div class="form-group">
	    		<label for="inputPassword3" class="col-sm-2 control-label">상세주소</label>
	    		<div class="col-sm-10">
	      			<input type="text" class="form-control" id="detailAddress">
	    		</div>
	  		</div><hr>
	  		<div class="form-group" align="center">
	      		<button type="submit" class="btn btn-primary btn-lg">회 원 가 입</button>
	  		</div>
		</form>
	</section>
	
	<!--바닥글-->
	</div>
    <footer class="footer">
		<jsp:include page="/WEB-INF/views/footer/footer.jsp"></jsp:include>
    </footer>
    
</body>
</html>