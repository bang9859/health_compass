<%@page import="schedule.model.ScheduleRequestDto"%>
<%@page import="util.DBManager"%>
<%@page import="java.util.ArrayList"%>
<%@page import="java.util.List"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="jakarta.tags.core"%>
<%
// JSON 데이터 가져오기
String scheduleListJson = (String) request.getAttribute("scheduleListJson");
if (scheduleListJson == null || scheduleListJson.isEmpty()) {
	scheduleListJson = "[]"; // 기본값으로 빈 배열
}
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<link rel="stylesheet" href="/resources/style/schedule.css">
<script src="/resources/script/calendar.js"></script>
<script src="/resources/script/schedule.js"></script>

<title>일정</title>
</head>
<c:import url="/header" />
<body>
	<!-- 약품 검색 모달 -->
	<!-- 배경 오버레이 -->
	<div id="modal-overlay" onclick="toggleMedicineSearchModal()"></div>

	<!-- 약품 검색 모달 -->
	<div id="medicine-search-modal">
		<!-- 닫기 버튼 -->
		<button class="close-btn" onclick="toggleMedicineSearchModal()">×</button>
		<input type="text" id="search-medicine" placeholder="약명 검색">
		<button onclick="searchMedicine()">검색</button>
		<!-- 검색 버튼 -->
		<ul id="medicine-result-list"></ul>
	</div>


	<div id="content-box">
		<c:import url="/nav" />
		<section id="main">
			<div class="calendar-container">
				<div id="calendar-header">
					<div id="month">
						<button id="btnPrev">◁이전달</button>
						<h1 id="calendar-month">1월</h1>
						<button id="btnNext">다음달▷</button>
					</div>
					<table id=calendar border="1" width="65%">
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
						<tbody id="calendar-date">
						</tbody>
					</table>
				</div>
			</div>
			<div class="calander-foot">
				<div class="schedule-list-container">
					<h1>일정 목록</h1>
					<!-- GET 요청을 통해 일정 데이터 가져오기 -->
					<form id="form-schedule-list" method="POST"
						action="service/schedule">
						<input type="hidden" name="command" value="search">
						<input type="hidden" name="usernameForSerachSchedule" value="${log.username}">
						<!-- 폼을 자동 제출하여 페이지 접근 시 일정 목록 로드 -->
						<script>
                        document.getElementById("form-schedule-list").submit();
                    </script>
					</form>

					<!-- 일정 데이터를 표시하는 테이블 -->
					<table border="1" id="schedule-list">
						<thead>
							<tr>
								<th>약품명</th>
								<th>보관방법</th>
								<th>시작일</th>
								<th>종료일</th>
								<th>1일 복용 횟수</th>
							</tr>
						</thead>
						<tbody>
							<c:forEach var="ScheduleRequestDto" items="${scheduleListJson}">
								<tr>
									<td>${ScheduleRequestDto.medicineName}</td>
									<td>${ScheduleRequestDto.depositMethod}</td>
									<td>${ScheduleRequestDto.startDate}</td>
									<td>${ScheduleRequestDto.endDate}</td>
									<td>${ScheduleRequestDto.dailyFrequency}</td>
								</tr>
							</c:forEach>
						</tbody>
					</table>
				</div>
				<form id="form-schedule" class="schedule-add-container"
					method="POST" action="service/schedule">
					<input type="hidden" name="command" value="add"> <input
						type="hidden" id="username" name="username"
						value="${log.username}">
					<h1>일정 등록</h1>
					<div class="medicine-search-group">
						<p>
							선택된 약품: <span id="selected-medicine">없음</span>
						</p>
						<input type="hidden" id="medicine-code" name="medicine-code">
						<button type="button" id="modal-btn"
							onclick="toggleMedicineSearchModal()">🔍</button>
					</div>
					<ul class="error-msg-group">
						<li id="error-msg-name-empty">약명 : 필수정보입니다.</li>
					</ul>
					<div class="date-group">
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
						<span>1일 복용 횟수 :</span> <input type="number" id=daily-frequency
							name="daily-frequency">
					</div>
					<ul class="error-msg-group">
						<li id="error-msg-number-empty">1일 복용 횟수 : 필수정보입니다.</li>
						<li id="error-msg-number-pattern">1일 복용 횟수 : 1~5까지만 입력 가능합니다.</li>
					</ul>
					<input id="submit-button" type="submit" value="등록">
				</form>
			</div>
		</section>
	</div>


</body>
<c:import url="/footer" />
</html>