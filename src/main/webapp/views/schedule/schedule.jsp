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
<script src = "/resources/script/medicineAPI.js"></script>
<script type="text/javascript"
	src="http://apis.data.go.kr/1471000/DrbEasyDrugInfoService/getDrbEasyDrugList?serviceKey=oruvbo%2BL%2B8mY49TbDDPKgBJmt8%2BaC4EPCinp%2FKfYxFIgRIp7iRMVQoqyWxZle%2FBv%2B22H%2BLJTKBTKU02ylL3ZJg%3D%3D"></script>
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
			<form class="schedule-add-container">
				<h1>일정 등록</h1>
				<div class = "medicine-search-group">
				<span>약명 :</span>
				<input type="text" id="medicine-name" name="medicine-name">
				</div>
					<ul class="error-msg-group">
						<li id="error-msg-name-empty">약명 : 필수정보입니다.</li>
					</ul>
				<div class = "date-group">
					<input type="date" id="start-date" name="start-date">
					<p>~</p>
					<input type="date" id="end-date" name="end-date"> 
				</div>
					<ul class="error-msg-group">
						<li id="error-msg-start-date-empty">시작일 : 필수정보입니다.</li>
						<li id="error-msg-start-date-pattern">시작일 : 시작일이 종료일보다 늦습니다.</li>
						<li id="error-msg-end-date-empty">종료일 : 필수정보입니다.</li>
					</ul>
 				<div class="daily-frequency-group">
				<span>1일 복용 횟수 :</span>
				<input type="number" id=daily-frequency name="daily-frequency"> 
				</div>
				<ul class="error-msg-group">
						<li id="error-msg-number-empty">1일 복용 횟수 : 필수정보입니다.</li>
						<li id="error-msg-number-pattern">1일 복용 횟수 : 1~5까지만 입력 가능합니다.</li>
				</ul>
				<input type=submit value="등록">
			</form>
			</div>
		</section>
	</div>
</body>
<c:import url="/footer" />
</html>