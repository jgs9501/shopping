<%@ page language="java" contentType="text/html; charset=utf-8"	pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<%@ taglib prefix="security" uri="http://www.springframework.org/security/tags"%>
<c:set var="contextPath" value="${pageContext.request.contextPath}" />
<div class="container">
	<div class="center-auto" style="width: 1000px;">
		<div class="navbar" style="margin-bottom: 0px;">
			<div style="float: left;">
				<a href="/index"><img class="img-logo" alt="..."
					src="/resources/images/JSShop_logo.png"></a>
			</div>
			<div style="float: left; margin-top: 30px; text-align: center; width: 600px;">
				<form class="navbar-search" action="/categories?search=${search}" method="get">
					<div class="input-group"
						style="text-align: center; margin-left: 10px;">
						<div class="input-group-btn">
							<button type="button" class="btn btn-search btn-default dropdown-toggle" data-toggle="dropdown">
								<span class="label-icon">카테고리</span>
								<span class="caret"></span>
								<span class="glyphicon glyphicon-chevron-down ft-small"></span>
							</button>
							<ul class="dropdown-menu pull-left" role="menu">
								<li value=""><a href="#"><span class="label-icon" style="font-size: small;">전체</span></a></li>
								<li value="100"><a href="#"><span class="label-icon" style="font-size: small;">전기/전자제품</span></a></li>
								<li value="200"><a href="#"><span class="label-icon" style="font-size: small;">화장품/향수</span></a></li>
								<li value="300"><a href="#"><span class="label-icon" style="font-size: small;">식료품</span></a></li>
								<li value="400"><a href="#"><span class="label-icon" style="font-size: small;">의류</span></a></li>
								<li value="500"><a href="#"><span class="label-icon" style="font-size: small;">신발류</span></a></li>
								<li value="600"><a href="#"><span class="label-icon" style="font-size: small;">악세서리</span></a></li>
								<li value="700"><a href="#"><span class="label-icon" style="font-size: small;">사무용품</span></a></li>
								<li value="800"><a href="#"><span class="label-icon" style="font-size: small;">주방용품</span></a></li>
								<li value="900"><a href="#"><span class="label-icon" style="font-size: small;">음반/DVD</span></a></li>
								<li value="1000"><a href="#"><span class="label-icon" style="font-size: small;">기타용품</span></a></li>
							</ul>
						</div>
						<input type="hidden" id="search_hidden" value="${search}">
						<input class="form-control" type="text" id="search" name="search">
						<div class="input-group-btn">
							<button type="submit" id="btn_search" class="btn btn-search btn-default" disabled="disabled">
								<i class="glyphicon glyphicon-search"></i>
							</button>
						</div>
					</div>
				</form>
			</div>
			<div class="nav-right">
				<a href="/cart"><i class="fas fa-shopping-cart" style="font-size: xx-large; color: black;"></i>
					<br><span class="nav-span">장바구니</span>
				</a>
			</div>
			<div class="nav-right">
				<a href="/order">
					<i class="fas fa-user-alt" style="font-size: xx-large; color: black;"></i>
					<br>
					<span class="nav-span">주문내역</span>
				</a>
			</div>

		</div>

	</div>
</div>

<script type="text/javascript">
	$(function() {
		$('.input-group-btn .dropdown-menu li').click(function() {
			
			let selText = $(this).text();
			let category = $(this).val();
			let search = $('#search').val();
			$(this).parents('.input-group-btn')
					.find('.btn-search')
					.html(selText + '&nbsp;&nbsp;<span class="glyphicon glyphicon-chevron-down ft-small"></span>');
			if(category != "" || category != 0) {
				$('#btn_search').removeAttr('disabled');
			} else if (category == 0){
				$('#btn_search').attr('disabled', 'disabled');
			}
			$('.navbar-search').attr('action', '/categories/'+category+'?search='+search);
		});
		
	});
</script>