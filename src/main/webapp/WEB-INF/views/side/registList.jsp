<%@ page language="java" contentType="text/html; charset=UTF-8"	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<c:set var="contextPath" value="${pageContext.request.contextPath}" />
<div class="list-group">
	<a href="#" class="list-group-item list-group-item-text list-header disabled">회원관리</a>
	<a href="/user/modify" class="list-group-item list-group-item-action">회원정보수정</a>
	<a href="/user/modifyPassword" class="list-group-item list-group-item-action">비밀번호수정</a>
	<a href="/user/auth" class="list-group-item list-group-item-action">권한확인</a>
	<a href="/user/signOut" class="list-group-item list-group-item-action">회원탈퇴</a>
</div>
