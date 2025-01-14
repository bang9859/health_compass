<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@taglib prefix="c" uri="jakarta.tags.core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
</head>
<body>
	<div class = "logo">
	<h1>건강 나침반</h1>
	<h4>Health Compass</h4>
	</div>
	
	<div class = "title">
	<input type="text" id="username" name="username"  value="${title}">
	</div>
	<div class = "log">
	<c:if test = "${empty log}">
		<h4 id = "login">로그인</h4>
		<h4 id = "join">회원가입</h4>
	</c:if>
	<c:if test = "${!empty log}">
		<h4 id = "mypage">마이페이지</h4>
		<h4 id = "logout">로그아웃</h4>
	</c:if>
	</div>
	
</body>
</html>