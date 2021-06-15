<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<c:set var="contextPath" value="${pageContext.request.contextPath}" />
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>고객센터</title>
<meta name="viewport"
	content="width=device-width, initial-scale=1, shrink-to-fit=no">
<link rel="stylesheet" href="${contextPath}/resources/css/bootstrap.css">
<link rel="stylesheet" href="${contextPath}/resources/css/swiper.css">
<link rel="stylesheet" href="${contextPath}/resources/css/main.css">
<link rel="stylesheet"
	href="https://pro.fontawesome.com/releases/v5.10.0/css/all.css" />
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
			<h2>
				<strong>고객센터</strong>
			</h2>
			<br>
			<ul class="nav nav-tabs nav-justified">
				<li class="nav-item active"><a class="nav-link" href="#notice" data-toggle="tab">공지사항</a></li>
				<li class="nav-item"><a class="nav-link" href="#qna_tab" data-toggle="tab">자주묻는질문</a></li>
			</ul>
			<div class="alert alert-warning" role="alert">
				<h5>
					<i class="glyphicon glyphicon-warning-sign"></i><strong>&nbsp;허위/불법
						거래 관련 문의</strong>
				</h5>
				<p>
					허위/불법 거래 관련 상품 및 점포관련 문의는 고객센터 이메일 <u>js.shop.test@gmail.com</u> 으로
					해 주시기 바랍니다.
				</p>
			</div>
			<br>
			<div class="tab-content">
				<!-- 공지사항 탭 -->
				<div class="tab-pane active" id="notice">
					<table class="table">
						<thead>
							<tr>
								<th class="col-xs-1" scope="col">번호</th>
								<th class="col-xs-8" scope="col">제목</th>
								<th class="col-xs-1" scope="col">조회수</th>
								<th class="col-xs-2" scope="col">날짜</th>
							</tr>
						</thead>
						<c:choose>
							<c:when test="${totalNoticeCount < 1}">
								<div style="text-align: center;">
									<h3>
										<strong>작성된 공지사항이 없습니다.</strong>
									</h3>
								</div>
							</c:when>
							<c:otherwise>
								<tbody>
									<c:forEach var="notice" items="${noticeVO_list}">
										<tr>
											<th scope="row">${notice.notice_id }</th>
											<td><a href="${contextPath}/contact/notice/${notice.notice_id}">[${notice.type}]&nbsp;${notice.title}</a></td>
											<td>${notice.views}</td>
											<td>${notice.reg_date}</td>
										</tr>
									</c:forEach>
								</tbody>
								<tfoot>
									<tr>
										<td></td>
										<td></td>
										<td></td>
										<td></td>
									</tr>
								</tfoot>
							</c:otherwise>
						</c:choose>
					</table>
					
					<div style="text-align: center;">
						<c:set var="info" value="${pagination}" />
						<ul class="pagination">
							<c:if test="${info.curPage ne 1}">
								<li><a href="javascript:void(0);"
									onclick="fn_paging(${info.startPage})" aria-label="Previous"><span
										aria-hidden="true">&laquo;</span></a></li>
							</c:if>
							<c:forEach var="pageNum" begin="${info.startPage}"
								end="${info.endPage}">
								<c:choose>
									<c:when test="${pageNum eq info.curPage}">
										<li class="active"><a href="#">${pageNum} <span
												class="sr-only">(current)</span></a></li>
									</c:when>
									<c:otherwise>
										<li><a href="javascript:void(0);"
											onclick="fn_paging(${pageNum})">${pageNum}</a></li>
									</c:otherwise>
								</c:choose>
							</c:forEach>
							<c:if test="${info.curPage ne info.pageCnt && info.pageCnt > 0}">
								<li><a href="javascript:void(0);"
									onclick="fn_paging(${info.nextPage})" aria-label="Next"><span
										aria-hidden="true">&raquo;</span></a></li>
							</c:if>
						</ul>
					</div>
				</div>

				<!-- 자주묻는질문 탭 -->
				<div class="tab-pane" id="qna_tab">
					<ul class="nav nav-pills nav-justified">
						<li class="nav-item active" value="PI"><a class="nav-link" href="#pi" data-toggle="tab">상품문의</a></li>
						<li class="nav-item" value="DI"><a class="nav-link" href="#di" data-toggle="tab">배송문의</a></li>
						<li class="nav-item" value="UI"><a class="nav-link" href="#ui"	data-toggle="tab">회원문의</a></li>
						<li class="nav-item" value="OI"><a class="nav-link" href="#oi" data-toggle="tab">주문/결제</a></li>
						<li class="nav-item" value="ETC"><a class="nav-link" href="#etc" data-toggle="tab">기타문의</a></li>
					</ul>
					<br>
	            	
					<div class="tab-content" id="type_tab">
						<div class="tab-pane active" id="pi">
							<div class="panel-group" id="accordion">
								<c:forEach var="qna" items="${qnaVO_list }" varStatus="index">
									<c:if test="${qna.type eq 'PI' }">
										<div class="panel panel-default">
											<div class="panel-heading">
												<h4 class="panel-title">
													<i class="far fa-question-circle col-xs-1"></i>
													<a class="col-xs-10" data-toggle="collapse" data-parent="#accordion" href="#collapse${index.count}">${qna.title}</a>
													<i class="glyphicon glyphicon-chevron-down" style="text-align: right;"></i>
												</h4>
											</div>
											<div id="collapse${index.count}" class="panel-collapse collapse">
												<div class="panel-body">
													<span>${qna.answer}</span>
													<c:if test="${userVO.auth eq 3}">
														<div style="float: right;">
															<form id="delete_form" action="" method="post">
																<a class="btn btn-primary btn-lg" href="${contextPath}/qna/${qna.qna_id}" style="color: white;">수정</a>
																<input class="btn btn-primary btn-lg" type="button" onclick="deleteQna(${qna.qna_id});" style="color: white;" value="삭제">
															</form>
														</div>
													</c:if>
												</div>
											</div>
										</div>
									</c:if>
								</c:forEach>
							</div>
						</div>
					</div>
				</div>
			</div>
		</div>
	</section>
	<footer>
		<jsp:include page="/WEB-INF/views/footer/footer.jsp"></jsp:include>
	</footer>

	<script type="text/javascript">
		function deleteQna(qna_id) {
			var flag = confirm("정말로 삭제하시겠습니까?");
			if(flag) {
				$('#delete_form').attr('action', '/qna/'+qna_id+'/delete');
				$('#delete_form').submit();
			}
		}
	
		$('#qna_tab').on('click', 'li', function () {
			
			var type = $(this).attr('value');
			var html = '';
			$.ajax({
				url : '/ajaxQnaList',
				type : 'POST',
				dataType : "json",
				contentType : "application/json",
				data : JSON.stringify({type : type}),
				success : function (data) {
					$('#type_tab').empty();
					
					html += '<div class="tab-pane active" id="'+type+'">';
					html += '<div class="panel-group" id="accordion">';
					$.each(data, function (index, item) {
						html += '<div class="panel panel-default">';
						html += '<div class="panel-heading">';
						html += '<h4 class="panel-title">';
						html += '<i class="far fa-question-circle col-xs-1"></i>';
						html += '<a class="col-xs-10" data-toggle="collapse" data-parent="#accordion" href="#collapse'+index+'">'+item.title+'</a>';
						html += '<i class="glyphicon glyphicon-chevron-down" style="text-align: right;"></i>';
						html += '</h4></div>';
						html += '<div id="collapse'+index+'" class="panel-collapse collapse">';
						html += '<div class="panel-body">';
						html += '<span>'+item.answer+'</span>';
						html += '<c:if test="${userVO.auth eq 3}">';
						html += '<div style="float: right;">';
						html += '<form id="delete_form" action="" method="post">';
						html += '<a class="btn btn-primary btn-lg" href="${contextPath}/qna/'+item.qna_id+'" style="color: white;">수정</a> ';
						html += '<input class="btn btn-primary btn-lg" type="button" onclick="deleteQna(${qna.qna_id});" style="color: white;" value="삭제">';
						html += '</form>';
						html += '</div></c:if>';
						html += '</div></div></div>';
					});
					html += '</div></div>';
					$('#type_tab').prepend(html);
				},
				error : function(request,status,error){
					console.log("code = "+ request.status + " message = " + request.responseText + " error = " + error); // 실패 시 처리
				}
			})
			
		})
	
		function fn_paging(curPage) {
			var param = {curPage : curPage,
							pageSize : 10};
			
			$.ajax({
				url : '/ajaxNoticeTemplate',
				type : 'POST',
				dataType : "html",
				contentType : "application/json",
				data : JSON.stringify(param),
				success : function (data) {
					// 공지사항 템플릿
					$('#notice').empty();
					$('#notice').prepend(data);
				},
				error : function(request,status,error){
					console.log("code = "+ request.status + " message = " + request.responseText + " error = " + error); // 실패 시 처리
				}
			})
		}
	</script>
</body>
</html>