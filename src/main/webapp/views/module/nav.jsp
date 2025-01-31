<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<link rel="stylesheet" href="/resources/style/nav.css">
<script src="/resources/script/nav.js"></script>
</head>
<body>
	<nav>
		<div id="menu-list">
			<h3>메뉴목록</h3>
			<ul id = "menu">
				<li id = "search-hospital-link" onclick="location.href='/'">🏥병원찾기</li>
				<li id = "calender-link" onclick="location.href='/schedule'">📅캘린더</li>
				<li id = "bookmark-link" onclick="location.href='/bookmark'">🔖북마크</li>
			</ul>
		</div>
		<div id="img-box"></div>
		<div id="modal">
        	<span class="close">&times;</span>
        <img src="" alt="Modal Image">
    </div>
	</nav>
</body>
</html>