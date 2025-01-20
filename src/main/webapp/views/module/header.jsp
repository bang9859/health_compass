<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="jakarta.tags.core"%>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<link rel="stylesheet" href="/resources/style/header.css">
<script type="module" src="/resources/script/header.js"></script>
</head>
<body>
	<header class="header">
		<div class="logo">
			<h1>🧭건강나침반</h1>
			<h4>Health Compass</h4>
		</div>
		<div class="page-name">
			<h1 id=title>메인 페이지</h1>
		</div>

		<div class="log">
			<c:choose>
				<c:when test="${empty log}">
					<input type="button" id="login" value="로그인">
					<input type="button" id="join" value="회원가입">
				</c:when>
				<c:otherwise>
					<input type="button" id="mypage" value="마이페이지">
					<input type="button" id="logout" value="로그아웃">
				</c:otherwise>
			</c:choose>
		</div>
	</header>
</body>
</html>