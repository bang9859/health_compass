<%@page import="util.DBManager"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="jakarta.tags.core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<link rel="stylesheet" href="/resources/style/hospitalsForm.css">
<script type="text/javascript"
	src="//dapi.kakao.com/v2/maps/sdk.js?appkey=aa5282a38622982d20efb6e4e5a4b894"></script>
<script type="text/javascript"
	src="/resources/script/hospitalsForm-map.js"></script>
<title>병원 목록 페이지</title>
</head>
<c:import url="/header" />
<body>
	<div id="content-box">
		<c:import url="/nav" />
		<section id="main">
			<div class="hospitals-container">
				<div id="map"></div>
				<div id="hospitals">
					<div>
						<p>병원이름 :</p>
						<p>주소 :</p>
						<p>전화번호 :</p>
						<p>진료시간 :</p>
						<button class="bookmark-but">북마크 추가하기</button>
					</div>
					<div>
						<p>병원이름 :</p>
						<p>주소 :</p>
						<p>전화번호 :</p>
						<p>진료시간 :</p>
						<button class="bookmark-but">북마크 추가하기</button>
					</div>
					<div>
						<p>병원이름 :</p>
						<p>주소 :</p>
						<p>전화번호 :</p>
						<p>진료시간 :</p>
						<button class="bookmark-but">북마크 추가하기</button>
					</div>
					<div>
						<p>병원이름 :</p>
						<p>주소 :</p>
						<p>전화번호 :</p>
						<p>진료시간 :</p>
						<button class="bookmark-but">북마크 추가하기</button>
					</div>
					<div>
						<p>병원이름 :</p>
						<p>주소 :</p>
						<p>전화번호 :</p>
						<p>진료시간 :</p>
						<button class="bookmark-but">북마크 추가하기</button>
					</div>
					<div id="paging">
						<p>1페이지</p>
						<div id="paging-buts">
							<button>◀</button>
							<button>▶</button>
						</div>
					</div>
				</div>
			</div>
		</section>
	</div>
</body>
<c:import url="/footer" />
</html>