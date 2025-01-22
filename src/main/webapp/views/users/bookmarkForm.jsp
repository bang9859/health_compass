<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="jakarta.tags.core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>북마크</title>
<link rel="stylesheet" href="/resources/style/globals.css">
<link rel="stylesheet" href="/resources/style/usersForm.css">
</head>
<c:import url="/header" />
<body>
	<div id="content-box">
		<c:import url="/nav" />
		<section id="main">
			<div id="map"></div>
			<script type="text/javascript"
				src="//dapi.kakao.com/v2/maps/sdk.js?appkey=9106e9e2b71925e379208a30b9d98ed5"></script>
			<script>
				var container = document.getElementById('map');
				var options = {
					center : new kakao.maps.LatLng(33.450701, 126.570667),
					level : 3
				};

				var map = new kakao.maps.Map(container, options);
			</script>
			<div>
				<h4>북마크 목록</h4>
				<ul id="bookmark-list">
					<li>1. OO병원</li>
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