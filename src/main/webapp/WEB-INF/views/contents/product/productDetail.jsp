<%@ page language="java" contentType="text/html; charset=utf-8"	pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<%@ taglib prefix="security" uri="http://www.springframework.org/security/tags"%>
<c:set var="contextPath" value="${pageContext.request.contextPath}" />
<!DOCTYPE html>
<html>
<head>
<title>JS SHOP</title>
<meta charset="UTF-8">
<meta id="_csrf" name="_csrf" content="${_csrf.token}"/>
<meta id="_csrf_header" name="_csrf_header" content="${_csrf.headerName}"/>
<script src="${contextPath}/resources/js/jquery.min.js"></script> 
<script src="${contextPath}/resources/js/swiper.min.js"></script>
<script src="https://unpkg.com/swiper/swiper-bundle.min.js"></script>
<script src="${contextPath}/resources/js/bootstrap.min.js"></script>
<link rel="stylesheet" href="${contextPath}/resources/css/swiper.css">
<link rel="stylesheet" href="${contextPath}/resources/css/bootstrap.css">
<link rel="stylesheet" href="${contextPath}/resources/css/product-detail.css">
<link rel="stylesheet" href="${contextPath}/resources/css/reply.css">
<link rel="stylesheet" href="${contextPath}/resources/css/main.css">
<link rel="stylesheet" href="https://pro.fontawesome.com/releases/v5.10.0/css/all.css"/>
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
		<li><a href="/index">메인</a></li>
		<li><a href="/categories/${pdVO.productVO.category}">${categoryName}</a></li>
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
										<c:when test="${(avg - idx.current) >= 0 }">
											<span class="fa fa-star checked"></span>
										</c:when>
										<c:otherwise>
											<c:choose>
												<c:when test="${(idx.current - avg) == 0 or (idx.current - avg) >= 10}">
													<span class="fa fa-star-o"></span>
												</c:when>
												<c:otherwise>
													<span class="fa fa-star-half-o"></span>
												</c:otherwise>
											</c:choose>
										</c:otherwise>
									</c:choose>
								</c:forEach>
								<span class="review-no">${replyCount}개 상품평</span>
							</div>
						</div>
						<p class="product-description">${pdVO.productVO.product_desc}</p>
						<div class="price">
							<span>
								<fmt:formatNumber type="number" minIntegerDigits="1" pattern="0,000" value="${pdVO.productVO.product_price - pdVO.productVO.discount}"/>&nbsp;원
							</span>
							<c:if test="${pdVO.productVO.discount ne 0}">
								<span class="discount">
									<fmt:formatNumber type="number" minIntegerDigits="1" pattern="0,000" value="${pdVO.productVO.product_price}"/>&nbsp;원
								</span>
							</c:if>
						</div>
						<form class="form-horizontal form-width" method="post" action="/cart">
							<input type="hidden" name="${_csrf.parameterName }" value="${_csrf.token }">
						    <input type="hidden" name="product_id" value="${productVO.product_id}"/>
							<strong>상품개수  </strong>
							<select name="amount">
								<c:forEach begin="1" end="10" var="i">
									<option value="${i}">${i}</option>
								</c:forEach>
							</select>
							<div>
								<button type="submit" class="add-to-cart btn btn-default" style="margin-top: 10px;">장바구니 담기</button>
							</div>
						</form>
					</div>
				</div>
			</div>
			<hr>
			<jsp:include page="/WEB-INF/views/contents/product/storeProducts.jsp"></jsp:include>
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
                <jsp:include page="/WEB-INF/views/contents/product/productDetail_reply.jsp"/>
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

	$(function () {
		var token = $("meta[name='_csrf']").attr("content");
		var header = $("meta[name='_csrf_header']").attr("content");
		
		$(document).ajaxSend(function(e, xhr, options) {
			xhr.setRequestHeader(header, token);
		});
	})

    var rate = 0;
    $(function () {
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
    })
    
    $(document).on('click', '#btnReply', function(e) {
		let form = {
			seq_user_id : $('#seq_user_id').val(),
			product_id : $('#product_id').val(),
			user_name : $('#user_name').val(),
			content : $('#content').val(),
			rating : rate
		};
		
		$.ajax({
			type: "POST",
			url: "/postProductReply",
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
		
		
	})
	function getStar(cnt) {
			var html = "";
			for(var i=0; i<cnt; i++) {
				html += "<span><i class='fa fa-star checked'></i></span>";
			} 
			for(var i=cnt; i<5; i++) {
				html += "<span><i class='fa fa-star-o'></i></span>";
			}
			return html;
		}
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
				url: "/postProductReplyAnswer",
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
				url: "/postProductReplyAnswer",
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
			url : "/postProductReplyAnswerDelete",
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
	
	function fn_paging(curPage) {
		var product_id = $('#product_id').val();
		var param = {
				product_id : $('#product_id').val(),
				curPage : curPage
		};
 		$.ajax({
			type: "POST",
			url: "/ajaxProductReply",
			dataType: "html",
			contentType: "application/json",
			data: JSON.stringify(param),
			success: function (data) {
				$('#reply_card').empty();
				$('#reply_card').prepend(data);
			},
			error : function(request,status,error){
				console.log("code = "+ request.status + " message = " + request.responseText + " error = " + error); // 실패 시 처리
			}
		}) 
	}
		
	function cartBtn(){
		alert("장바구니에 추가되었습니다.");
	}
</script>
</body>
</html>