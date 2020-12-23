<%@ page language="java" contentType="text/html; charset=UTF-8"	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<c:set var="contextPath" value="${pageContext.request.contextPath}" />
<!DOCTYPE html>
<html>
<head>
	<meta charset="UTF-8">
	<meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
	<title>login</title>
	<!-- Bootstrap CSS -->
	<link rel="stylesheet" href="${contextPath}/resources/css/bootstrap.css">
	<script src="${contextPath}/resources/js/jquery.min.js"></script>
	<script src="${contextPath}/resources/js/jquery.cookie.js"></script>
	<script src="${contextPath}/resources/js/bootstrap.min.js"></script>
	<link rel="stylesheet" href="${contextPath}/resources/css/main.css">
	<script type="text/javascript">
		$(document).ready(function(){
			var user_id = $.cookie('key');
			if(user_id != ""){
				$('#user_id').val(user_id);
			    $('#saveId').attr('checked', true);
			}
			
			$('#saveId').change(function(){
				if($('#saveId').is(':checked')){
				    $.cookie('key', $('#user_id').val(), { expires: 7 });
				}else{
					$('#saveId').removeAttr('checked');
				    $.removeCookie('key');
				}
			});
			     
			$('#user_id').keyup(function(){
			    if($('#saveId').is(':checked')){
			        $.cookie('key', $('#user_id').val(), { expires: 7 });
			    }
			});
		});
	</script>
</head>
<body style="background: #eee">
	<!-- 머릿글 -->
	<header>
		<jsp:include page="/WEB-INF/views/header/header.jsp"></jsp:include>
	</header>
	
	<!-- 로그인 화면 -->
	<section>
		<form action="loginPost" class="center-sort center-padding" method="post">
	 		<fieldset class="well well-lg center-padding width-limit">
		 		<c:if test="${result ne null}">
			 		<div class="alert alert-danger" role="alert">
						<span class="glyphicon glyphicon-exclamation-sign" aria-hidden="true"></span>
						<span class="sr-only">Error:</span>
						${result}가 잘못되었습니다
					</div>
		 		</c:if>
				<h1 class="text-center">JS Shop</h1><br>
		    	<div class="form-group">
		    		<label for="TextInput">아이디</label>
			    	<input type="text" id="user_id" name="user_id" class="form-control">
			    </div>
				<div class="form-group">
		    		<label for="TextInput">비밀번호</label>
			    	<input type="password" id="password" name="password" class="form-control">
			    </div>
			    <div class="checkbox">
				    <label>
						<input type="checkbox" id="saveId" name="saveId">아이디 기억하기
				    </label>
					
				</div>
			    <div>
			      	<button type="submit" class="btn btn-primary btn-lg btn-block">로 그 인</button>
			    </div>
			    <h6 style="float: right;"><a href="">비밀번호를 잊으셨나요?</a></h6>
 			    <hr class="my-4">
				<h6 style="float: right;">회원가입을 원하실 경우는 <a href="${contextPage}/contents/user/regist">여기</a></h6>
		    </fieldset>
		</form>
	</section>
</body>
</html>