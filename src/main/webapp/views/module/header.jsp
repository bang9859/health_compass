<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="jakarta.tags.core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<link rel="stylesheet" href="/resources/style/header.css">

</head>
<body>
	<header class="header">
		<div class="logo" onclick="location.href='/'">
			<h1>🧭건강나침반</h1>
			<h4>Health Compass</h4>
		</div>
		<div class="page-name">
			<h1 id=title>
				<%
				String requestURI = request.getRequestURI();
				String pageTitle = "메인페이지"; // 기본 제목 설정
				if (requestURI.contains("/login")) {
					pageTitle = "로그인";
				} else if (requestURI.contains("/join")) {
					pageTitle = "회원가입";
				} else if (requestURI.contains("/mypage")) {
					pageTitle = "마이페이지";
				} else if (requestURI.contains("/delete")) {
					pageTitle = "마이페이지";
				} else if (requestURI.contains("/detail")) {
					pageTitle = "진료과 상세 페이지";
				} else if (requestURI.contains("/schedule")) {
					pageTitle = "일정";
				} else if (requestURI.contains("/bookmark")) {
					pageTitle = "북마크";
				} else if (requestURI.contains("/service/hospitals")) {
					pageTitle = "병원 목록 페이지";
				}
				out.print(pageTitle);
				%>
			</h1>
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
					<input type="button" id="mypage" value="마이페이지"
						onclick="location.href='/mypage'">
					<input type="button" id="logout" value="로그아웃"
						onclick="location.href='/service/users?command=logout'">
				</c:otherwise>
			</c:choose>
		</div>
	</header>
</body>
</html>