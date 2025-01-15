<%@page import="util.DBManager"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="jakarta.tags.core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<link rel="stylesheet" href="/resources/style/globals.css">
<title>일정</title>
</head>
<c:import url="/header" />
<body>
	<c:if test = "${empty log}">
		<c:redirect url="/login"/>
	</c:if>
	<div id="content-box">
		<c:import url="/nav" />
		<section id="main">
			<div class = "calander-container">
				
			</div>
			<div class = "schedule-list">
				
			</div>
			<div class = "schedule-">
				<input type="text" id= "medicine-name" name = "medicine-name" placeholder = "약명">
				<input type="date" id = "start-date" name = "start-date">
				<input type="date" id = "end-date" name = "end-date">
				<input type= "number" id = daily-frequency name = "daily-frequency">
				<input type= submit value = "등록">
				
			</div>
		</section>
	</div>
</body>
<c:import url="/footer" />
</html>