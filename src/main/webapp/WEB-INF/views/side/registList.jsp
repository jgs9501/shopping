<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<c:set var="contextPath" value="${pageContext.request.contextPath}" />
<!DOCTYPE html>
<html>
<head>
	<meta charset="UTF-8">
	<style type="text/css">
		.list-group{
			float: left;
		    width: 170px;
		    font-size: x-small;
		}
		.list-header{
			font-size: small;
			font-weight: bolder;
			background-color: #eee;
		}
	</style>
</head>
<body>
	<div class="list-group">
        <a href="#" class="list-group-item list-group-item-text list-header disabled">회원관리</a>
        <a href="${contextPage}/contents/modify/modifyForm" class="list-group-item list-group-item-action">회원정보수정</a>
        <a href="${contextPage}/contents/modify/modifyPassword" class="list-group-item list-group-item-action">비밀번호수정</a>
        <a href="${contextPage}/contents/user/signOut" class="list-group-item list-group-item-action">회원탈퇴</a>
    </div>
</body>
</html>