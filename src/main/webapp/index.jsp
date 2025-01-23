<%@page import="util.DBManager"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="jakarta.tags.core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<link rel="stylesheet" href="/resources/style/main.css">
<script type="text/javascript" src="/resources/script/myAddress.js"></script>
<title>메인 페이지(증상선택)</title>
</head>
<c:import url="/header" />
<body>
	<div id="content-box">
		<c:import url="/nav" />
		<form id="main" action="/service/hospital" method="POST">
			<input type="hidden" name="command" value="search"> <input
				type="hidden" id="address-hidden" name="address" value=""> <input
				type="hidden" id="D001" name="internalCode" value=""> <input
				type="hidden" id="D005" name="dermatologyCode" value=""> <input
				type="hidden" id="D008" name="orthopedicCode" value=""> <input
				type="hidden" id="D009" name="neurosurgeryCode" value=""> <input
				type="hidden" id="D011" name="gynecologyCode" value=""> <input
				type="hidden" id="D012" name="ophthalmologyCode" value=""> <input
				type="hidden" id="D013" name="otolaryngologyCode" value=""> <input
				type="hidden" id="D026" name="dentistryCode" value="">
			<h3 id="address">현재 위치 :</h3>
			<div class="departments-container">
				<div class="department">
					<div class="department-title">
						<h2>내과</h2>
					</div>
					<div class="symptoms">
						<div class="symptom">
							<input type="checkbox" id="internal-medicine-symptom1"
								class="symptom" data-department="D001" data-group="internal"
								value="감기 증상"> <label for="internal-medicine-symptom1">감기
								증상</label>
						</div>
						<div class="symptom">
							<input type="checkbox" id="internal-medicine-symptom2"
								class="symptom" data-department="D001" data-group="internal"
								value="호흡 곤란"> <label for="internal-medicine-symptom2">호흡
								곤란</label>
						</div>
						<div class="symptom">
							<input type="checkbox" id="internal-medicine-symptom3"
								class="symptom" data-department="D001" data-group="internal"
								value="부종"> <label for="internal-medicine-symptom3">부종</label>
						</div>
						<div class="symptom">
							<input type="checkbox" id="internal-medicine-symptom4"
								class="symptom" data-department="D001" data-group="internal"
								value="복통"> <label for="internal-medicine-symptom4">복통</label>
						</div>
						<div class="symptom">
							<input type="checkbox" id="internal-medicine-symptom5"
								class="symptom" data-department="D001" data-group="internal"
								value="소화불량"> <label for="internal-medicine-symptom5">소화불량</label>
						</div>
						<div class="symptom">
							<input type="checkbox" id="internal-medicine-symptom6"
								class="symptom" data-department="D001" data-group="internal"
								value="속쓰림"> <label for="internal-medicine-symptom6">속쓰림</label>
						</div>
						<div class="symptom">
							<input type="checkbox" id="internal-medicine-symptom7"
								class="symptom" data-department="D001" data-group="internal"
								value="구토"> <label for="internal-medicine-symptom7">구토</label>
						</div>
						<div class="symptom">
							<input type="checkbox" id="internal-medicine-symptom8"
								class="symptom" data-department="D001" data-group="internal"
								value="설사"> <label for="internal-medicine-symptom8">설사</label>
						</div>
						<div class="symptom">
							<input type="checkbox" id="internal-medicine-symptom9"
								class="symptom" data-department="D001" data-group="internal"
								value="고혈압"> <label for="internal-medicine-symptom9">고혈압</label>
						</div>
					</div>
					<a href="/detail" class="more-link"><button type="button"
							class="search-button">더보기</button></a>
				</div>

				<div class="department">
					<div class="department-title">
						<h2>피부과</h2>
					</div>
					<div class="symptoms">
						<div class="symptom">
							<input type="checkbox" id="dermatology-symptom1" class="symptom"
								data-department="D005" data-group="dermatology" value="발전">
							<label for="dermatology-symptom1">발진</label>
						</div>
						<div class="symptom">
							<input type="checkbox" id="dermatology-symptom2" class="symptom"
								data-department="D005" data-group="dermatology" value="가려움증">
							<label for="dermatology-symptom2">가려움증</label>
						</div>
						<div class="symptom">
							<input type="checkbox" id="dermatology-symptom3" class="symptom"
								data-department="D005" data-group="dermatology" value="습진">
							<label for="dermatology-symptom3">습진</label>
						</div>
						<div class="symptom">
							<input type="checkbox" id="dermatology-symptom4" class="symptom"
								data-department="D005" data-group="dermatology" value="두드러기">
							<label for="dermatology-symptom4">두드러기</label>
						</div>
						<div class="symptom">
							<input type="checkbox" id="dermatology-symptom5" class="symptom"
								data-department="D005" data-group="dermatology" value="아토피 피부염">
							<label for="dermatology-symptom5">아토피 피부염</label>
						</div>
						<div class="symptom">
							<input type="checkbox" id="dermatology-symptom6" class="symptom"
								data-department="D005" data-group="dermatology" value="무종">
							<label for="dermatology-symptom6">무좀</label>
						</div>
						<div class="symptom">
							<input type="checkbox" id="dermatology-symptom7" class="symptom"
								data-department="D005" data-group="dermatology" value="접촉 피부염">
							<label for="dermatology-symptom7">접촉 피부염</label>
						</div>
						<div class="symptom">
							<input type="checkbox" id="dermatology-symptom8" class="symptom"
								data-department="D005" data-group="dermatology" value="여드름">
							<label for="dermatology-symptom8">여드름</label>
						</div>
						<div class="symptom">
							<input type="checkbox" id="dermatology-symptom9" class="symptom"
								data-department="D005" data-group="dermatology" value="건선">
							<label for="dermatology-symptom9">건선</label>
						</div>
						<div class="symptom">
							<input type="checkbox" id="dermatology-symptom10" class="symptom"
								data-department="D005" data-group="dermatology" value="백반증">
							<label for="dermatology-symptom10">백반증</label>
						</div>
					</div>
					<a href="/detail" class="more-link"><button type="button"
							class="search-button">더보기</button></a>
				</div>

				<div class="department">
					<div class="department-title">
						<h2>정형외과</h2>
					</div>
					<div class="symptoms">
						<div class="symptom">
							<input type="checkbox" id="orthopedics-symptom1" class="symptom"
								data-department="D008" data-group="orthopedic" value="관절통">
							<label for="orthopedics-symptom1">관절통</label>
						</div>
						<div class="symptom">
							<input type="checkbox" id="orthopedics-symptom2" class="symptom"
								data-department="D008" data-group="orthopedic" value="근육통">
							<label for="orthopedics-symptom2">근육통</label>
						</div>
						<div class="symptom">
							<input type="checkbox" id="orthopedics-symptom3" class="symptom"
								data-department="D008" data-group="orthopedic" value="넘어짐">
							<label for="orthopedics-symptom3">넘어짐</label>
						</div>
						<div class="symptom">
							<input type="checkbox" id="orthopedics-symptom4" class="symptom"
								data-department="D008" data-group="orthopedic" value="부딪힘">
							<label for="orthopedics-symptom4">부딪힘</label>
						</div>
					</div>
					<a href="/detail" class="more-link"><button type="button"
							class="search-button">더보기</button></a>
				</div>

				<div class="department">
					<div class="department-title">
						<h2>신경외과</h2>
					</div>
					<div class="symptoms">
						<div class="symptom">
							<input type="checkbox" id="neurosurgery-symptom1" class="symptom"
								data-department="D009" data-group="neurosurgery" value="허리">
							<label for="neurosurgery-symptom1">허리</label>
						</div>
						<div class="symptom">
							<input type="checkbox" id="neurosurgery-symptom2" class="symptom"
								data-department="D009" data-group="neurosurgery" value="어지러움">
							<label for="neurosurgery-symptom2">어지러움</label>
						</div>
						<div class="symptom">
							<input type="checkbox" id="neurosurgery-symptom3" class="symptom"
								data-department="D009" data-group="neurosurgery" value="간질">
							<label for="neurosurgery-symptom3">간질</label>
						</div>
						<div class="symptom">
							<input type="checkbox" id="neurosurgery-symptom4" class="symptom"
								data-department="D009" data-group="neurosurgery" value="손발 저림">
							<label for="neurosurgery-symptom4">손발 저림</label>
						</div>
						<div class="symptom">
							<input type="checkbox" id="neurosurgery-symptom5" class="symptom"
								data-department="D009" data-group="neurosurgery" value="근육감각이상">
							<label for="neurosurgery-symptom5">균형감각이상</label>
						</div>
						<div class="symptom">
							<input type="checkbox" id="neurosurgery-symptom6" class="symptom"
								data-department="D009" data-group="neurosurgery" value="만성통증">
							<label for="neurosurgery-symptom6">만성통증</label>
						</div>
					</div>
					<a href="/detail" class="more-link"><button type="button"
							class="search-button">더보기</button></a>
				</div>
			</div>

			<div class="departments-container">
				<div class="department">
					<div class="department-title">
						<h2>산부인과</h2>
					</div>
					<div class="symptoms">
						<div class="symptom">
							<input type="checkbox" id="gynecology-symptom1" class="symptom"
								data-department="D011" data-group="gynecology" value="복부 팽만감">
							<label for="gynecology-symptom1">복부 팽만감</label>
						</div>
						<div class="symptom">
							<input type="checkbox" id="gynecology-symptom2" class="symptom"
								data-department="D011" data-group="gynecology" value="복부 통증">
							<label for="gynecology-symptom2">복부 동통</label>
						</div>
						<div class="symptom">
							<input type="checkbox" id="gynecology-symptom3" class="symptom"
								data-department="D011" data-group="gynecology" value="생리통">
							<label for="gynecology-symptom3">생리통</label>
						</div>
						<div class="symptom">
							<input type="checkbox" id="gynecology-symptom4" class="symptom"
								data-department="D011" data-group="gynecology"
								value="비정상적 질 분비물"> <label for="gynecology-symptom4">비정상적
								질 분비물</label>
						</div>
						<div class="symptom">
							<input type="checkbox" id="gynecology-symptom5" class="symptom"
								data-department="D011" data-group="gynecology" value="골반 통증">
							<label for="gynecology-symptom5">골반 통증</label>
						</div>
						<div class="symptom">
							<input type="checkbox" id="gynecology-symptom6" class="symptom"
								data-department="D011" data-group="gynecology" value="열">
							<label for="gynecology-symptom6">열</label>
						</div>
						<div class="symptom">
							<input type="checkbox" id="gynecology-symptom7" class="symptom"
								data-department="D011" data-group="gynecology" value="월경 과다">
							<label for="gynecology-symptom7">월결 과다</label>
						</div>
					</div>
					<a href="/detail" class="more-link"><button type="button"
							class="search-button">더보기</button></a>
				</div>

				<div class="department">
					<div class="department-title">
						<h2>안과</h2>
					</div>
					<div class="symptoms">
						<div class="symptom">
							<input type="checkbox" id="ophthalmology-symptom1"
								class="symptom" data-department="D012"
								data-group="ophthalmology" value="눈부심"> <label
								for="ophthalmology-symptom1">눈부심</label>
						</div>
						<div class="symptom">
							<input type="checkbox" id="ophthalmology-symptom2"
								class="symptom" data-department="D012"
								data-group="ophthalmology" value="흐릿함"> <label
								for="ophthalmology-symptom2">흐릿함</label>
						</div>
						<div class="symptom">
							<input type="checkbox" id="ophthalmology-symptom3"
								class="symptom" data-department="D012"
								data-group="ophthalmology" value="안보임"> <label
								for="ophthalmology-symptom3">안보임</label>
						</div>
						<div class="symptom">
							<input type="checkbox" id="ophthalmology-symptom4"
								class="symptom" data-department="D012"
								data-group="ophthalmology" value="충혈"> <label
								for="ophthalmology-symptom4">충혈</label>
						</div>
						<div class="symptom">
							<input type="checkbox" id="ophthalmology-symptom5"
								class="symptom" data-department="D012"
								data-group="ophthalmology" value="눈물고임"> <label
								for="ophthalmology-symptom5">눈물고임</label>
						</div>
					</div>
					<a href="/detail" class="more-link"><button type="button"
							class="search-button">더보기</button></a>
				</div>

				<div class="department">
					<div class="department-title">
						<h2>이비인후과</h2>
					</div>
					<div class="symptoms">
						<div class="symptom">
							<input type="checkbox" id="otolaryngology-symptom1"
								class="symptom" data-department="D013"
								data-group="otolaryngology" value="코막힘"> <label
								for="otolaryngology-symptom1">코막힘</label>
						</div>
						<div class="symptom">
							<input type="checkbox" id="otolaryngology-symptom2"
								class="symptom" data-department="D013"
								data-group="otolaryngology" value="코피"> <label
								for="otolaryngology-symptom2">코피</label>
						</div>
						<div class="symptom">
							<input type="checkbox" id="otolaryngology-symptom3"
								class="symptom" data-department="D013"
								data-group="otolaryngology" value="비염"> <label
								for="otolaryngology-symptom3">비염</label>
						</div>
						<div class="symptom">
							<input type="checkbox" id="otolaryngology-symptom4"
								class="symptom" data-department="D013"
								data-group="otolaryngology" value="부비동염"> <label
								for="otolaryngology-symptom4">부비동염</label>
						</div>
						<div class="symptom">
							<input type="checkbox" id="otolaryngology-symptom5"
								class="symptom" data-department="D013"
								data-group="otolaryngology" value="이루"> <label
								for="otolaryngology-symptom5">이루</label>
						</div>
						<div class="symptom">
							<input type="checkbox" id="otolaryngology-symptom6"
								class="symptom" data-department="D013"
								data-group="otolaryngology" value="이염"> <label
								for="otolaryngology-symptom6">이명</label>
						</div>
						<div class="symptom">
							<input type="checkbox" id="otolaryngology-symptom7"
								class="symptom" data-department="D013"
								data-group="otolaryngology" value="난청"> <label
								for="otolaryngology-symptom7">난청</label>
						</div>
						<div class="symptom">
							<input type="checkbox" id="otolaryngology-symptom8"
								class="symptom" data-department="D013"
								data-group="otolaryngology" value="어지러움"> <label
								for="otolaryngology-symptom8">어지러움</label>
						</div>
					</div>
					<a href="/detail" class="more-link"><button type="button"
							class="search-button">더보기</button></a>
				</div>

				<div class="department">
					<div class="department-title">
						<h2>치과</h2>
					</div>
					<div class="symptoms">
						<div class="symptom">
							<input type="checkbox" id="dentistry-symptom1" class="symptom"
								data-department="D026" data-group="dentistry" value="치통">
							<label for="dentistry-symptom1">치통</label>
						</div>
						<div class="symptom">
							<input type="checkbox" id="dentistry-symptom2" class="symptom"
								data-department="D026" data-group="dentistry" value="시린이">
							<label for="dentistry-symptom2">시린이</label>
						</div>
						<div class="symptom">
							<input type="checkbox" id="dentistry-symptom3" class="symptom"
								data-department="D026" data-group="dentistry" value="잇몸 부종">
							<label for="dentistry-symptom3">잇몸 부종</label>
						</div>
						<div class="symptom">
							<input type="checkbox" id="dentistry-symptom4" class="symptom"
								data-department="D026" data-group="dentistry" value="치아 변색">
							<label for="dentistry-symptom4">치아 변색</label>
						</div>
						<div class="symptom">
							<input type="checkbox" id="dentistry-symptom5" class="symptom"
								data-department="D026" data-group="dentistry" value="치근낭">
							<label for="dentistry-symptom5">치근낭</label>
						</div>
					</div>
					<a href="/detail" class="more-link"><button type="button"
							class="search-button">더보기</button></a>
				</div>
			</div>
			<button type="submit" class="search-button">근처병원 검색</button>
		</form>
	</div>
</body>
<c:import url="/footer" />
</html>