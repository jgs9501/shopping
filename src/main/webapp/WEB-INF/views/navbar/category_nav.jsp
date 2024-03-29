<%@ page language="java" contentType="text/html; charset=utf-8"	pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<%@ taglib prefix="security" uri="http://www.springframework.org/security/tags"%>
<c:set var="contextPath" value="${pageContext.request.contextPath}"/>
<!DOCTYPE html>
<html>
<body>
<nav class="navbar navbar-inverse">
      <div style="width: 900px; margin-left: auto; margin-right: auto;">
          <ul class="nav navbar-nav" style="font-size: small;">
            <li><a href="/categories/100">전기/전자제품</a></li>
            <li><a href="/categories/200">화장품/향수</a></li>
            <li><a href="/categories/300">식료품</a></li>
            <li><a href="/categories/400">의류</a></li>
            <li><a href="/categories/500">신발류</a></li>
            <li><a href="/categories/600">악세서리</a></li>
            <li><a href="/categories/700">사무용품</a></li>
            <li><a href="/categories/800">주방용품</a></li>
            <li><a href="/categories/900">음반/DVD</a></li>
            <li><a href="/categories/1000">기타용품</a></li>
          </ul>
      </div>
    </nav>

</body>
</html>