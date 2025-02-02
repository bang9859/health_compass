<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="jakarta.tags.core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>북마크</title>
<link rel="stylesheet" href="/resources/style/globals.css">
<link rel="stylesheet" href="/resources/style/bookmark.css">
<script type="text/javascript"
	src="http://dapi.kakao.com/v2/maps/sdk.js?autoload=false"></script>
<script type="text/javascript"
	src="//dapi.kakao.com/v2/maps/sdk.js?appkey=9106e9e2b71925e379208a30b9d98ed5&libraries=services"></script>
<script src="/resources/script/users/bookmark.js"></script>
</head>
<c:import url="/header" />
<body>
	<div id="content-box">
		<c:import url="/nav" />
		<section id="main">
			<div id="map"></div>
			<div class="box1" id="map-list">
				<h4 id="bookmark-title">북마크 목록</h4>
				<ul id="bookmark-list">
					<c:forEach var="item" items="${bookmark}" varStatus="status">
						<li><input type="button" id="bookmark-${status.index + 1}"
							class="bookmark-btn"
							value="${status.index + 1}. ${item.hospitalName}"
							onclick="handleBookmarkClick('${item.address}', '${item.hospitalName}')">
							<button class="delete-btn"
								onclick="deletecheckBookmark('${item.hospitalId}')"></button></li>
					</c:forEach>
				</ul>
			</div>
		</section>
	</div>
</body>
<c:import url="/footer" />
</html>