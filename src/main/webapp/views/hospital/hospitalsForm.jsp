<%@page import="java.util.ArrayList"%>
<%@page import="hospital.model.HospitalDto"%>
<%@page import="java.util.List"%>
<%@page import="util.DBManager"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="jakarta.tags.core"%>
<%
// JSON 데이터 가져오기
String hospitalListJson = (String) request.getAttribute("hospitalListJson");
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<link rel="stylesheet" href="/resources/style/hospitalsForm.css">
<script type="text/javascript"
	src="//dapi.kakao.com/v2/maps/sdk.js?appkey=9106e9e2b71925e379208a30b9d98ed5"></script>
<script src="/resources/script/hospitalsForm.js"></script>
<title>병원 목록 페이지</title>
</head>
<c:import url="/header" />
<body>
	<div id="hospitalData"
		data-hospitals='<%=hospitalListJson.replace("\\", "\\\\").replace("\'", "\\\'").replace("\n", "\\n")%>'></div>
	<div id="content-box">
		<c:import url="/nav" />
		<section id="main">
			<div class="hospitals-container">
				<div class="map_wrap">
					<div id="map"></div>
				</div>
				<div id="hospitals" class="scrollable"></div>
				<div id="paging"></div>
			</div>
		</section>
	</div>
</body>
<c:import url="/footer" />