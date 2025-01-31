<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="jakarta.tags.core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<link rel="stylesheet" href="/resources/style/header.css">
<!-- favicon image -->
	<link rel="shortcut icon" href="파비콘이미지.png">

	<meta property="og:title" content="링크타이틀">
	<meta property="og:description" content="링크에대한설명">
	<meta property="og:image" content="이미지주소">

	<!-- ... -->
</head>
<body>
	<header class="header">
		<div class="logo" onclick="location.href='/'">
			<h1>🧭건강나침반</h1>
			<h4>Health Compass</h4>
		</div>
		<div class="page-name">
			<h1 id=title>메인페이지</h1>
		</div>

		<div class="log">
			<c:choose>
				<c:when test="${empty log}">
					<input type="button" id="login" value="로그인"
						onclick="location.href='/login'">
					<input type="button" id="join" value="회원가입"
						onclick="location.href='/join'">
				</c:when>
				<c:otherwise>

					<span>사용자 : ${log.username}</span>
					<input type="button" id="mypage" value="마이페이지" onclick="location.href='/mypage'">
					<input type="button" id="logout" value="로그아웃" onclick="location.href='/service/users?command=logout'">
				</c:otherwise>
			</c:choose>
		</div>
	</header>
</body>
</html>