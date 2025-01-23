<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="jakarta.tags.core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>북마크</title>
<link rel="stylesheet" href="/resources/style/globals.css">
<link rel="stylesheet" href="/resources/style/bookmark.css">
<script type="text/javascript" src="//dapi.kakao.com/v2/maps/sdk.js?appkey=9106e9e2b71925e379208a30b9d98ed5"></script>
<script src="/resources/script/users/bookmark.js"></script>
</head>
<c:import url="/header" />
<body>
	<div id="content-box">
		<c:import url="/nav" />
		<section id="main">
			<div id="map"></div>
			<div id="map-list">
				<h4>북마크 목록</h4>
				<ul id="bookmark-list">
					<li><input type="button" value="${bookmark[1].hospitalName}" onclick="location.href='/service/users?command=search-bookmark'"></li>
					<li>2. OO병원</li>
					<li>3. OO병원</li>
					<li>4. OO병원</li>
					<li>5. OO병원</li>
					<li>6. OO병원</li>
					<li>7. OO병원</li>
				</ul>
			</div>
		</section>
	</div>
</body>
<c:import url="/footer" />
</html>