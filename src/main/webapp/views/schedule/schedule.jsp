<%@page import="util.DBManager"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="jakarta.tags.core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<link rel="stylesheet" href="/resources/style/schedule.css">
<script src = "/resources/script/schedule.js"></script>
<title>일정</title>
</head>
<c:import url="/header" />
<body>

	<div id="content-box">
		<c:import url="/nav" />
		<section id="main">
			<div class="calendar-container">
				<div id="calendar-header">
					<div id = "month">
						<button id="btnPrev">◁이전달</button>
						<h1 id="calendar-month">1월</h1>
						<button id="btnNext">다음달▷</button>
					</div>
					<table id= calendar border="1" width="65%">
						<thead>
							<tr>
								<th>일</th>
								<th>월</th>
								<th>화</th>
								<th>수</th>
								<th>목</th>
								<th>금</th>
								<th>토</th>
							</tr>
						</thead>
						<tbody id= "calendar-date">
						</tbody>
					</table>
				</div>
			</div>
			<div class = "calander-foot">
			<div class="schedule-list-container">
			<h1>일정 목록</h1>
			</div>
			<div class="schedule-add-container">
				<h1>일정 등록</h1>
				<div class = "medicine-search-group">
				<span>약명 :</span>
				<input type="text" id="medicine-name" name="medicine-name">
				</div> 
				<div class = "date-group">
				<input type="date" id="start-date" name="start-date">
				<p>~</p>
				<input type="date" id="end-date" name="end-date"> 
				</div>
				<div class = daily-frequency-group>
				<span>1일 복용 횟수 :</span>
				<input type="number" id=daily-frequency name="daily-frequency"> 
				</div>
				<input type=submit value="등록">
			</div>
			</div>
		</section>
	</div>
</body>
<c:import url="/footer" />
</html>