<%@ page language="java" contentType="text/html; charset=utf-8"	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<c:set var="contextPath" value="${pageContext.request.contextPath}"/>
<!DOCTYPE html>
<html>
<head>
	<meta charset="UTF-8">
	<link rel="stylesheet" href="${contextPath}/resources/css/main.css">
	<link rel="stylesheet" href="${contextPath}/resources/js/jquery.min.js">
	<link rel="stylesheet" href="${contextPath}/resources/js/bootstrap.min.js">
	<link rel="stylesheet" href="${contextPath}/resources/css/bootstrap.min.css">
	<link rel="stylesheet" href="https://pro.fontawesome.com/releases/v5.10.0/css/all.css"/>
	<link href="https://fonts.googleapis.com/css?family=Poppins" rel="stylesheet" />
</head>
<body>
	<div class="container">
		<div class="center-auto" style="width: 1000px;">
			<div class="navbar" style="margin-bottom: 0px;">
			<div style="float: left;">
				<a href="${contextPath}/index"><img class="img-logo" alt="..." src="${contextPath}/resources/images/JSShop_logo.png"></a> 
			</div>
			<div style="float: left; margin-top: 30px; text-align: center; width: 600px;">
				<form class="navbar-search">
		        	<div class="input-group" style="text-align: center; margin-left: 10px;">
		                <div class="input-group-btn">
		                    <button type="button" class="btn btn-search btn-default dropdown-toggle" data-toggle="dropdown">
		                        <span class="label-icon" style="font-size: small;">카테고리</span>
		                        <span class="caret"></span>
		                        <span class="glyphicon glyphicon-chevron-down ft-small"></span>
		                    </button>
		                    <ul class="dropdown-menu pull-left" role="menu">
		                        <li>
		                            <a href="#">
		                                <span class="label-icon">Search By User</span>
		                            </a>
		                        </li>
		                        <li>
		                            <a href="#">
		                                <span class="label-icon">Search By Organization</span>
		                            </a>
		                        </li>
		                    </ul>
		                </div>
		                <input type="text" class="form-control">
		               	<div class="input-group-btn">
		                    <button type="button" class="btn btn-search btn-default">
		                        <i class="glyphicon glyphicon-search"></i>
		                    </button>
		                </div>
		            </div>
		        </form>
        	</div>
	            <div class="nav-right">
	            	<a href="${contextPath}/cart"><i class="fas fa-shopping-cart" style="font-size: xx-large; color: black;"></i>
	            		<br><span class="nav-span">장바구니</span>
	            	</a>
	            	
	            </div>
	            <div class="nav-right">
	            	<a href="${contextPath}/order/history">
		            	<i class="fas fa-user-alt" style="font-size: xx-large; color: black;"></i>
		            	<br><span class="nav-span">주문내역</span>
	            	</a>
	            </div>
	            
        	</div>
        	
       	</div>  
	</div>
	
	<script type="text/javascript">
	$(function(){
	    
	    $(".input-group-btn .dropdown-menu li a").click(function(){

	        var selText = $(this).html();
	    
	        //working version - for single button //
	       //$('.btn:first-child').html(selText+'<span class="caret"></span>');  
	       
	       //working version - for multiple buttons //
	       $(this).parents('.input-group-btn').find('.btn-search').html(selText);

	   });

	});

	</script>
</body>
</html>