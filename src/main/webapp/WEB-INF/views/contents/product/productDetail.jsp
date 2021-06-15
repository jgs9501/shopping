<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<c:set var="contextPath" value="${pageContext.request.contextPath}" />
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>JS SHOP</title>
<script src="${contextPath}/resources/js/jquery.min.js"></script> 
<script src="${contextPath}/resources/js/swiper.min.js"></script>
<script src="https://unpkg.com/swiper/swiper-bundle.min.js"></script>
<script src="${contextPath}/resources/js/bootstrap.min.js"></script>
<link rel="stylesheet" href="${contextPath}/resources/css/swiper.css">
<link rel="stylesheet" href="${contextPath}/resources/css/bootstrap.css">
<link rel="stylesheet" href="${contextPath}/resources/css/product-detail.css">
<link rel="stylesheet" href="${contextPath}/resources/css/reply.css">
<link rel="stylesheet" href="${contextPath}/resources/css/main.css">
<link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/font-awesome/5.10.0/css/font-awesome.min.css"/>
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
	<ol class="breadcrumb container">
		<li><a href="${contextPath}/index">메인</a></li>
		<li><a href="${contextPath}/categories/${pdVO.productVO.category}">${categoryName}</a></li>
		<li class="active">${pdVO.productVO.product_name}</li>
	</ol>
		<input type="hidden" id="seq_user_id" name="seq_user_id" value="${userVO.seq_user_id}"/>
	    <input type="hidden" id="user_name" name="user_name" value="${userVO.user_id}">
	    <input type="hidden" id="product_id" name="product_id" value="${productVO.product_id}"/>
		<div class="container">
		<div class="card">
			<div class="container-fliud">
				<div class="wrapper row">
					<div class="preview col-md-6">
						<div class="preview-pic tab-content">
						  <div class="tab-pane active" id="pic-1"><img src="${pdVO.productVO.product_img}" /></div>
						</div>
					</div>
					<div class="details col-md-6">
						<div>
							<h3 class="product-title">${pdVO.productVO.product_name}</h3>
						</div>
						<div>
							<h5 class="h5">${pdVO.storeVO.store_name}</h5>
						</div>
						<div class="rating">
							<div class="stars">
								<fmt:formatNumber var="avg" value="${rating_avg*10}"/>
								<c:forEach begin="10" end="50" step="10" varStatus="idx">
									<c:choose>
										<c:when test="${avg >= idx.current}">
											<span class="fa fa-star checked"></span>
										</c:when>
										<c:otherwise>
											<c:if test="${(avg - idx.current) > 0}">
												<span class="fa fa-star-half-o"></span>
											</c:if>
											<c:if test="${(avg - idx.current) <= 0}">
												<span class="fa fa-star-o"></span>
											</c:if>
										</c:otherwise>
									</c:choose>
								</c:forEach>
								<span class="review-no">${replyCount}개 상품평</span>
							</div>
						</div>
						<p class="product-description">${pdVO.productVO.product_desc}</p>
						<c:if test="${pdVO.productVO.discount eq 0}">
							<h4 class="price">가격: 
								<span><fmt:formatNumber type="number" pattern="0,000" minIntegerDigits="1" value="${pdVO.productVO.product_price}"/> 원</span>
							</h4>
						</c:if>
						<c:if test="${pdVO.productVO.discount ne 0}">
							<h4 class="price">가격: 
								<span class="discount" style="color: gray;">
									<fmt:formatNumber type="number" pattern="0,000" minIntegerDigits="1" value="${pdVO.productVO.product_price}"/>
								</span>
								<span>
									<fmt:formatNumber type="number" pattern="0,000" minIntegerDigits="1" value="${pdVO.productVO.product_price - pdVO.productVO.discount}"/> 
									원</span>
							</h4>
						</c:if>
						<form class="form-horizontal form-width" method="post" action="${contextPath}/insertCart">
						    <input type="hidden" name="product_id" value="${productVO.product_id}"/>
							<strong>상품개수  </strong>
							<select name="amount">
								<c:forEach begin="1" end="10" var="i">
									<option value="${i}">${i}</option>
								</c:forEach>
							</select>
							<div>
								<button class="add-to-cart btn btn-default" style="margin-top: 10px;" onclick="javascript:cartBtn()">장바구니 담기</button>
							</div>
						</form>
					</div>
				</div>
			</div>
			<hr>
			<h4 class="h4">출품한 업체의 다른 물품</h4>
			<div class="row">
				<div class="swiper-container swiper-per-group">
				    <div class="swiper-wrapper">
				    	<c:forEach var="product" items="${listProduct}">
					        <div class="swiper-slide thumbnail">
					        	<div>
					        		<a href="${contextPath}/products/${product.product_id}"><img src="${product.product_thumbImg}"></a>
						        	<div class="text-left">
							        	<a href="${contextPath}/products/${product.product_id}"><strong>${product.product_name}</strong></a>
						        	</div>
						        	<div class="text-right price">
							        	<h5>${product.product_price-product.discount} 원</h5>
						        	</div>
					        	</div>
					        </div>
				    	
				    	</c:forEach>
				    </div>
				    <div class="swiper-pagination"></div>
				    <div class="swiper-button-next"></div>
				    <div class="swiper-button-prev"></div>
				</div>
			</div>
		</div>
		<ul class="nav nav-tabs nav-justified" style="margin-top: 20px;">
			<li class="active" >
				<a class="nav-link" data-toggle="tab" href="#product_info" >상품정보</a>
		  	</li>
		  	<li>
		    	<a class="nav-link" data-toggle="tab" href="#product_rating">상품평</a>
		  	</li>
		  	<li>
		    	<a class="nav-link" data-toggle="tab" href="#product_qa">상품문의</a>
		  	</li>
		</ul>

		<div class="tab-content">
			<div class="tab-pane fade active in" id="product_info">
		  		<p class="table-title">제품 정보</p>
				<table class="info">
					<tbody>
						<tr>
							<th>용량(중량)</th>
							<td>${pdVO.productVO.weight}</td>
							<th>제품 주요 사항</th>
							<td>${pdVO.productVO.attention}</td>
						</tr>
						<tr>
							<th>유효기한</th>
							<td>${pdVO.productVO.valid_date}</td>
							<th>사용방법</th>
							<td>${pdVO.productVO.use_info}</td>
						</tr>
						<tr>
							<th>판매업자</th>
							<td>${pdVO.storeVO.store_name}</td>
							<th>제조국</th>
							<td>${pdVO.productVO.country}</td>
						</tr>
					</tbody>
				</table>
			  	<hr>
		  	</div>
		  	<!-- 상품평 -->
		  	<div class="tab-pane fade" id="product_rating">
		  		<div class="col">
                    <div class="panel-body">
                        <form role="form">
                        	
                            <fieldset>
                                <div class="form-group">
						  			<div id="star">
								        <span class="star">★</span>
									    <span class="star">★</span>
									    <span class="star">★</span>
									    <span class="star">★</span>
									    <span class="star">★</span>
									</div>
    								<textarea class="form-control" id="content" name="content" rows="3" placeholder="댓글을 작성해주세요" autofocus="autofocus"></textarea>
                                </div>
                        		<button id="btnReply" type="button" class="btn btn-success" data-loading-text="Loading...">댓글 작성</button>
                            </fieldset>
                        </form>
                    </div>
                </div>
                
                <div class="container card" id="reply_card">
					<c:forEach var="reply" items="${listReply}" varStatus="status">
					    <div class="card-body">
						    <div class="row">
					        	<div class="col-xs-12" style="padding-top: 20px;">
					        	    <p>
					        	        <span class="float-left"><strong>${reply.user_name}</strong></span>
					        	        <c:forEach begin="1" end="${reply.rating}" >
							                <span><i class="fa fa-star checked"></i></span>
					        	        </c:forEach>
					        	        <c:forEach begin="1" end="${5 - reply.rating}">
							                <span><i class="fa fa-star-o"></i></span>
					        	        </c:forEach>
					        	        <span>${reply.reg_date}</span>
					        	    </p>
					        	    <p>${reply.content}</p>
					        	    <div id="">
					        	    <c:set var="pId" value="${pdVO.productVO.seq_user_id}"/>
					        	    <c:set var="uId" value="${userVO.seq_user_id }"></c:set>
					        	    <c:if test="${(uId eq pId) and (reply.answer eq null)}">
						        	    <p>
						        	    	<input type="hidden" id="store_name" value="${pdVO.storeVO.store_name}">
						        	      	<a class="float-left btn btn-default ml-2" id="answerBtn${status.index}" onclick="answer(${reply.seq_user_id}, ${reply.product_id}, ${status.index})"><i class="fa fa-reply"></i> 답글</a>
						        	    </p>
						        	   	<div id="form-answer${status.index}" class="form-group"></div>
					        	    </c:if>
					        	    </div>
					        	</div>
						    </div>
						    <div id="answer_inner${status.index}">
						    <c:if test="${(reply.answer ne null)}">
						        <div class="card card-inner">
					            	<div class="card-body">
					            	    <div class="row">
					                    	<div class="col-xs-12">
					                    	    <p><strong>${pdVO.storeVO.store_name}</strong></p>
					                    	    <div id="p_answer${status.index}"><p>${reply.answer}</p></div>
					                    	    <c:if test="${uId eq pId}">
					                    	    	<a class="float-right btn btn-default ml-2" onclick="answerUpdate(${reply.seq_user_id}, ${reply.product_id}, ${status.index})">수정</a>
					                    	    	<a class="float-right btn btn-default ml-2" onclick="answerDelete(${reply.seq_user_id}, ${reply.product_id}, ${status.index})">삭제</a>
					                    	    </c:if>
					                    	</div>
					            	    </div>
					            	</div>
						        </div>
						    </c:if>
						    </div>
					    </div>
					</c:forEach>
				</div>
				<nav style="text-align: center;">
					<c:set var="info" value="${pagination}" />
					<input type="hidden" id="productId" value="${productVO.product_id}">
					<ul class="pagination">
						<c:if test="${info.curPage ne 1}">
							<li><a href="javascript:void(0);" onclick="fn_paging(${info.prevPage})" aria-label="Previous"><span aria-hidden="true">&laquo;</span></a></li>
						</c:if>
						<c:forEach var="pageNum" begin="${info.startPage}" end="${info.endPage}">
						    <c:choose>
					            <c:when test="${pageNum eq info.curPage}">
								    <li class="active"><a href=javascript:void(0); onclick="fn_paging(${pageNum})">${pageNum} <span class="sr-only">(current)</span></a></li>
							 	</c:when>
							  	<c:otherwise>
									<li><a href="javascript:void(0);" onclick="fn_paging(${pageNum})">${pageNum}</a></li>
							  	</c:otherwise>
							</c:choose>
						</c:forEach>
						<c:if test="${info.curPage ne info.pageCnt && info.pageCnt > 0}">
							<li><a href="javascript:void(0);" onclick="fn_paging(${info.nextPage})" aria-label="Next"><span aria-hidden="true">&raquo;</span></a></li>
						</c:if>
					</ul> 
				</nav>
		  	</div>
			<div class="tab-pane fade" id="product_qa">
				<p class="table-title">상품문의</p>
		    	<ul>
		    		<li>상품문의 및 후기게시판을 통해 취소나 환불, 반품 등은 처리되지 않습니다.</li>
		    		<li>가격, 판매자, 교환/환불 및 배송 등 해당 상품 자체와 관련 없는 문의는 고객센터 내 1:1 문의하기를 이용해주세요.</li>
		    		<li>"해당 상품 자체"와 관계없는 글, 양도, 광고성, 욕설, 비방, 도배 등의 글은 예고 없이 이동, 노출제한, 삭제 등의 조치가 취해질 수 있습니다.</li>
		    		<li>공개 게시판이므로 전화번호, 메일 주소 등 고객님의 소중한 개인정보는 절대 남기지 말아주세요.</li>
		    	</ul>
			</div>
		</div>
	</div>
	</section>
	<footer>
		<jsp:include page="/WEB-INF/views/footer/footer.jsp"></jsp:include>
	</footer>

<script>
    var swiper = new Swiper('.swiper-per-group', {
    	slidesPerView: 4,
        spaceBetween: 30,
        slidesPerGroup: 3,
   	    pagination: {
        el: '.swiper-pagination',
        clickable: true,
  	    },
  	navigation: {
        nextEl: '.swiper-button-next',
        prevEl: '.swiper-button-prev',
        },
	});
    
    var rate = 0;
    (function () {
        var starEls = document.querySelectorAll('#star span.star');
        loop(starEls, function (el, index) {
            el.addEventListener('click', function () {
                rating(index + 1);
            });
        });

        function loop(list, func) {
            Array.prototype.forEach.call(list, func);
        }

        function rating(score) {
            loop(starEls, function (el, index) {
                if (index < score) {
                    el.classList.add('on');
                } else {
                    el.classList.remove('on');
                }
            });
            rate = score;
        }
    })();
    
    $(document).on('click', '#btnReply', function(e) {
		let form = {
			seq_user_id : $('#seq_user_id').val(),
			product_id : "${pdVO.productVO.product_id}",
			user_name : $('#user_name').val(),
			content : $('#content').val(),
			rating : rate
		};
		
		$.ajax({
			type: "POST",
			url: "${contextPath}/postProductReply",
			dataType: "json",
			contentType: "application/json",
			data: JSON.stringify(form),
			success: function (data) {
				if(data != null) {
					let rating_flg = Number(data.rating);
					var html = "<div class='card-body'>";
						html += "<div class='row'>";
						html += "<div class='col-md-12'><p>";
						html += "<span class='float-left'><strong>"+data.user_name+"</strong></span>";
						html += getStar(rating_flg);
						html += "<span style='margin-left:5px;'>"+data.reg_date+"</span></p>"
						html += "<div class='clearfix'>";
						html += "<p>"+data.content+"</p>";
						html += "</div>";
						html += "</div>";
						html += "</div>";
						html += "</div>";
					$('#reply_card').prepend(html);
				}
			},
			error: function () {
				alert("중복된 댓글이거나 구입하지않은 상품은 댓글을 작성할 수 없습니다.");
			}
		});
		
		function getStar(cnt) {
			var html = "";
			for(var i=0; i<cnt; i++) {
				html += "<span><i class='fa fa-star checked' style='margin-left:5px;'></i></span>";
			} 
			for(var i=cnt; i<5; i++) {
				html += "<span><i class='fa fa-star-o' style='margin-left:5px;'></i></span>";
			}
			return html;
		}
	})
	
	// seq : seq_user_id의 값, idx : foreach의 index값
	function answer(uId, pId, idx) {
		
    	$('#answerBtn'+idx).hide();
    	let str = "<textarea class='form-control' id='answer_content"+idx+"' name='answer_content"+idx+"' rows='3' placeholder='댓글을 작성해주세요' autofocus='autofocus'></textarea>";
		str += "<button id='answerSubmitBtn"+idx+"' type='button' class='btn btn-success'>댓글 작성</button>";
		str += "<button id='answerCancelBtn"+idx+"' type='button' class='btn btn-default'>취소</button>";
		$('#form-answer'+idx).append(str);
		
		$('#answerSubmitBtn'+idx).click(function (){
			const store_name = $('#store_name').val();
			var form = {
				seq_user_id : uId,
				product_id : pId,
				answer : $('#answer_content'+idx).val()
			};
			$.ajax({
				type: "POST",
				url: "${contextPath}/postProductReplyAnswer",
				dataType: "json",
				contentType: "application/json",
				data: JSON.stringify(form),
				success: function (data) {
					let html ="";
					html +=	'<div class="card card-inner">';
	            	html += '<div class="card-body">';
	            	html += '<div class="row">';
	                html += '<div class="col-md-12">';
	                html += '<p><strong>'+store_name+'</strong></p>';
	                html += '<p>'+data.answer+'</p>';
	                html += '</div></div></div></div>';
	                $('#form-answer'+idx).empty();
	                $('#answer_inner'+idx).append(html);
				},
				error: function (request, error, data) {
					alert("댓글을 달지 못하였습니다.");
					console.log("code:"+request.status+"\n"+"message:"+request.responseText+"\n"+"error:"+error)
				}
			})
		}) 
		$('#answerCancelBtn'+idx).click(function (){
			$('#form-answer'+idx).empty();
			$('#answerBtn'+idx).show();
		})
	}

	function answerUpdate(uId, pId, idx) {
		var text = $('#p_answer'+idx).text();
		$('#p_answer'+idx).html("<div class='form-group'><textarea class='form-control' id='answer_content"+idx+"' name='answer_content"+idx+"' rows='3' placeholder='댓글을 작성해주세요' autofocus='autofocus'>"+text+"</textarea></div>");
		
		$('#answerSubmitBtn'+idx).click(function (){
			var form = {
				seq_user_id : uId,
				product_id : pId,
				answer : $('#answer_content'+idx).val()
			};
			$.ajax({
				type: "POST",
				url: "${contextPath}/postProductReplyAnswer",
				dataType: "json",
				contentType: "application/json",
				data: JSON.stringify(form),
				success: function (data) {
					let html ="";
					html +=	'<div class="card card-inner">';
	            	html += '<div class="card-body">';
	            	html += '<div class="row">';
	                html += '<div class="col-md-12">';
	                html += '<p><strong>'+store_name+'</strong></p>';
	                html += '<div id="p_answer'+idx+'"><p>'+data.answer+'</p></div>';
	                html += '<a class="float-right btn btn-default ml-2" onclick="answerUpdate('+data.seq_user_id+','+data.product_id+', '+idx+'">수정</a>';
        	    	html += '<a class="float-right btn btn-default ml-2" onclick="answerDelete('+data.seq_user_id+','+data.product_id+', '+idx+'">삭제</a>';
	                html += '</div></div></div></div>';
	                $('#answer_inner'+idx).empty();
	                $('#answer_inner'+idx).append(html);
				},
				error: function (request, error, data) {
					alert("댓글을 달지 못하였습니다.");
					console.log("code:"+request.status+"\n"+"message:"+request.responseText+"\n"+"error:"+error);
				}
			})
		}) 
		$('#answerCancelBtn'+idx).click(function (){
			$('#answer_inner'+idx).show();
		})
	}
	
	function answerDelete(uId, pId, idx) {
		
		var form = {
			seq_user_id : uId,
			product_id : pId
		};
		
		$.ajax({
			type : "POST",
			url : "${contextPath}/postProductReplyAnswerDelete",
			dataType : "json",
			contentType : "application/json",
			data : JSON.stringify(form),
			success: function (data) {
				if(data){
					$('#answer_inner'+idx).remove();
					$('#answerBtn'+idx).show();
				}
				else {
					alert("삭제를 실패했습니다.")
				}
			},
			error: function (request, error, data) {
				alert("답글 삭제를 실패했습니다.");
				console.log("code:"+request.status+"\n"+"message:"+request.responseText+"\n"+"error:"+error);
			}
		})
	}
	
	// 나중에 ajax 댓글 추가하자
	function fn_paging(curPage) {
		var product_id = $('#productId').val();
		location.href = "/products/" + product_id + "?curPage=" + curPage;
		
/* 		$.ajax({
			type: "POST",
			url: "${contextPath}/postProductReplies",
			dataType: "json",
			contentType: "aplication/json",
			data: {product_id: $('#productId').val(),
				   curPage: curPage},
			success: function (data) {
				
			}
		}) */
	}
		
	function cartBtn(){
		alert("장바구니에 추가되었습니다.");
	}
</script>
</body>
</html>