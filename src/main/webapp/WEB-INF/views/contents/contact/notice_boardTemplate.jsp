<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<c:set var="contextPath" value="${pageContext.request.contextPath}" />
<div>
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
			<c:when test="${totalNoticeCount > 1}">
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
							<td><a href="${contextPath}/contact/${notice.notice_id}">[${notice.type}]&nbsp;${notice.title}</a></td>
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
</div>
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
