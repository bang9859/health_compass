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
    if (hospitalListJson == null) {
        hospitalListJson = "[]"; // 데이터가 없을 경우 빈 배열로 설정
    }
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<link rel="stylesheet" href="/resources/style/hospitalsForm.css">
<script>
    // 서버에서 전달된 JSON 데이터를 JavaScript로 변환
    const hospitalList = JSON.parse('<%= hospitalListJson.replace("\\", "\\\\").replace("\"", "\\\"").replace("\n", "\\n") %>');
    console.log("Hospital List in JSP:", hospitalList);
</script>
<script type="text/javascript"
	src="//dapi.kakao.com/v2/maps/sdk.js?appkey=aa5282a38622982d20efb6e4e5a4b894"></script>
<script src="/resources/script/hospitalsForm.js"></script>
<title>병원 목록 페이지</title>
</head>
<c:import url="/header" />
<body>
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