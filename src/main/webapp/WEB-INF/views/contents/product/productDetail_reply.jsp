<%@ page language="java" contentType="text/html; charset=utf-8"	pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<%@ taglib prefix="security" uri="http://www.springframework.org/security/tags"%>
<c:set var="contextPath" value="${pageContext.request.contextPath}" />
<!DOCTYPE html>
<html>
<body>
	<div class="container card" id="reply_card">
		<c:forEach var="reply" items="${listReply}" varStatus="status">
			<div class="card-body">
				<div class="row">
					<div class="col-xs-12" style="padding-top: 20px;">
						<p>
							<span class="float-left"><strong>${reply.user_name}</strong></span>
							<c:forEach begin="1" end="${reply.rating}">
								<span><i class="fa fa-star checked"></i></span>
							</c:forEach>
							<c:forEach begin="1" end="${5 - reply.rating}">
								<span><i class="fa fa-star-o"></i></span>
							</c:forEach>
							<span>${reply.reg_date}</span>
						</p>
						<p>${reply.content}</p>
						<c:set var="pId" value="${pdVO.productVO.seq_user_id}" />
						<c:set var="uId" value="${userVO.seq_user_id }" />
						<c:if test="${(uId eq pId) and (reply.answer eq null)}">
							<p>
								<input type="hidden" id="store_name"
									value="${pdVO.storeVO.store_name}"> <a
									class="float-left btn btn-default ml-2"
									id="answerBtn${status.index}"
									onclick="answer(${reply.seq_user_id}, ${reply.product_id}, ${status.index})">
									<i class="fa fa-reply"></i> 답글
								</a>
							</p>
							<div id="form-answer${status.index}" class="form-group"></div>
						</c:if>
					</div>
				</div>
			</div>
			<div id="answer_inner${status.index}">
				<c:if test="${(reply.answer ne null)}">
					<div class="card col-xs-12">
						<div class="card-body">
							<div class="row">
								<i class="fas fa-share"></i>
								<span>
									<strong>${pdVO.storeVO.store_name}</strong>
								</span>
								<div id="p_answer${status.index}">
									<p>${reply.answer}</p>
								</div>
								<c:if test="${uId eq pId}">
									<a class="float-right btn btn-default ml-2"
										onclick="answerUpdate(${reply.seq_user_id}, ${reply.product_id}, ${status.index})">수정</a>
									<a class="float-right btn btn-default ml-2"
										onclick="answerDelete(${reply.seq_user_id}, ${reply.product_id}, ${status.index})">삭제</a>
								</c:if>
							</div>
						</div>
					</div>
				</c:if>
			</div>
		</c:forEach>
		<div style="text-align: center;">
			<c:set var="info" value="${pagination}" />
			<ul class="pagination">
				<c:if test="${info.curPage ne 1}">
					<li><a href="javascript:void(0);"
						onclick="fn_paging(${info.prevPage})" aria-label="Previous"><span
							aria-hidden="true">&laquo;</span></a></li>
				</c:if>
				<c:forEach var="pageNum" begin="${info.startPage}"
					end="${info.endPage}">
					<c:choose>
						<c:when test="${pageNum eq info.curPage}">
							<li class="active"><a href=javascript:void(0);
								onclick="fn_paging(${pageNum})">${pageNum} <span
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
</body>
</html>