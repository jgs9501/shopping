<%@ page language="java" contentType="text/html; charset=utf-8"	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<c:set var="contextPath" value="${pageContext.request.contextPath}"/>
<!DOCTYPE html>
<html>
<head>
	<meta charset="UTF-8">
	<link rel="stylesheet" href="${contextPath}/resources/css/bootstrap.css">
	<link href='https://fonts.googleapis.com/css?family=Roboto:400,100,300,700' rel='stylesheet' type='text/css'>
	<link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/font-awesome/4.7.0/css/font-awesome.min.css">
	<link rel="stylesheet" href="${contextPath}/resources/js/jquery.min.js">
	<link rel="stylesheet" href="${contextPath}/resources/js/popper.js">
</head>
<body>
<nav class="navbar navbar-inverse">
      <div style="width: 900px; margin-left: auto; margin-right: auto;">
          <ul class="nav navbar-nav" style="font-size: small;">
            <li><a href="${contextPath}/categories/100">전기/전자제품</a></li>
            <li><a href="${contextPath}/categories/200">화장품/향수</a></li>
            <li><a href="${contextPath}/categories/300">식료품</a></li>
            <li><a href="${contextPath}/categories/400">의류</a></li>
            <li><a href="${contextPath}/categories/500">신발류</a></li>
            <li><a href="${contextPath}/categories/600">악세서리</a></li>
            <li><a href="${contextPath}/categories/700">사무용품</a></li>
            <li><a href="${contextPath}/categories/800">주방용품</a></li>
            <li><a href="${contextPath}/categories/900">음반/DVD</a></li>
            <li><a href="${contextPath}/categories/1000">기타용품</a></li>
          </ul>
      </div>
    </nav>

</body>
</html>