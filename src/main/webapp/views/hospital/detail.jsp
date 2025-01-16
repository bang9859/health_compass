<%@page import="util.DBManager"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="jakarta.tags.core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>진료과 상세 페이지</title>
<link rel="stylesheet" href="/resources/style/detail.css">
</head>
<c:import url="/header" />
<body>
	<div id="content-box">
		<c:import url="/nav" />
		<section id="main">
			<div class="departments-container">
				<div class="department">
					<div class="department-title">
						<h2>내과</h2>
					</div>
					<p>내과는 장의 기관에 생긴 병을 외과적 수술 없이 고치는 의학 분야입니다. 내과 질환은 만성질환, 위장질환,
						간질환, 갑상선질환, 골다공증, 천식, 알레르기, 만성호흡기질환, 빈혈, 통풍, 류마티스질환 등이 있습니다.</p>
					<div class="symptoms">
						<h3 class="symptom-title">소화기계 질환의 증상</h3>
						<ul class="symptom-list">
							<li>연하곤란</li>
							<li>식사와 관련된 가슴 통증</li>
							<li>복통</li>
							<li>오심이나 구토</li>
							<li>변비나 설사</li>
							<li>토혈이나 혈변</li>
							<li>황달</li>
						</ul>
						<h3 class="symptom-title">순환기계 질환의 증상</h3>
						<ul class="symptom-list">
							<li>고혈압</li>
							<li>고지혈증</li>
							<li>흉통</li>
							<li>협심증</li>
							<li>부정맥</li>
						</ul>
						<h3 class="symptom-title">호흡기계 질환의 증상</h3>
						<ul class="symptom-list">
							<li>천식</li>
							<li>알레르기</li>
							<li>고지혈증</li>
							<li>만성호흡기질환</li>
						</ul>
						<h3 class="symptom-title">신장계 질환의 증상</h3>
						<ul class="symptom-list">
							<li>배뇨증상</li>
							<li>옆구리 통증</li>
							<li>혈뇨</li>
							<li>거품뇨</li>
							<li>전신부종</li>
						</ul>
					</div>
				</div>
				<div class="department">
					<div class="department-title">
						<h2>피부과</h2>
					</div>
					<p>피부 질환은 피부, 털, 손발톱, 관련된 근육과 분비선 등 피부계통에 영향을 미치는 병입니다.</p>
					<div class="symptoms">
						<h3 class="symptom-title">발진</h3>
						<ul class="symptom-list">
							<li>여드름</li>
							<li>무좀</li>
							<li>대상포진</li>
							<li>구순포진</li>
						</ul>
						<h3 class="symptom-title">가려움증</h3>
						<ul class="symptom-list">
							<li>아토피 피부염</li>
							<li>습진</li>
							<li>접촉 피부염</li>
						</ul>
						<h3 class="symptom-title">두드러기</h3>
						<ul class="symptom-list">
							<li>피부과 외래에서 흔하게 접하는 피부진환</li>
						</ul>
						<h3 class="symptom-title">염증</h3>
						<ul class="symptom-list">
							<li>지루 피부염</li>
							<li>결정성 홍반</li>
						</ul>
						<h3 class="symptom-title">탈모증</h3>
						<ul class="symptom-list">
							<li>피부과에서 진료하는 진환 중 하나</li>
						</ul>
						<h3 class="symptom-title">딱지</h3>
						<ul class="symptom-list">
							<li>대상포진 후 신경통</li>
						</ul>
						<h3 class="symptom-title">통증</h3>
						<ul class="symptom-list">
							<li>환부 통증</li>
						</ul>
					</div>
				</div>
				<div class="department">
					<div class="department-title">
						<h2>정형외과</h2>
					</div>
					<p>정형외과 증상은 뼈, 혈관, 신경, 힘줄, 근육, 인대 등에 발생하는 질환이나 외상과 관련된 증상입니다.</p>
					<div class="symptoms">
						<h3 class="symptom-title">고관절염</h3>
						<ul class="symptom-list">
							<li>무릎</li>
							<li>견주관절</li>
							<li>족관절</li>
							<li>등에서 나타는 통증, 뚝뚟리, 부기등</li>
						</ul>
						<h3 class="symptom-title">척추 변형 및 기형</h3>
						<ul class="symptom-list">
							<li>측만증</li>
							<li>후만증</li>
							<li>등의 척추 변형과 관련된 증상</li>
						</ul>
						<h3 class="symptom-title">외상</h3>
						<ul class="symptom-list">
							<li>골절</li>
							<li>인대 손상</li>
						</ul>
						<h3 class="symptom-title">스포츠 손상</h3>
						<ul class="symptom-list">
							<li>스포츠를 하면서 발생하는 손상과 관련된 증상</li>
						</ul>
						<h3 class="symptom-title">신경 관련 증상</h3>
						<ul class="symptom-list">
							<li>손, 팔, 다리의 저림이나 찌릿찌릿한 통증</li>
						</ul>
						<h3 class="symptom-title">고관절 이형성증</h3>
						<ul class="symptom-list">
							<li>오리걸음</li>
							<li>귀두가 울혈됨</li>
							<li>다리의 길이가 틀림</li>
							<li>다리가 잘 안 벌어짐</li>
						</ul>
					</div>
				</div>
				<div class="department">
					<div class="department-title">
						<h2>신경외과</h2>
					</div>
					<p>신경외과에서 치료하는 증상으로는 의식 수준 변경, 혼란, 감각 상실, 근육 약화, 마비, 조정력 부족,
						고통, 발작등있습니다. 신경계 질환은 뇌나 척수에서 발생하는 경우가 많습니다.</p>
					<div class="symptoms">
						<h3 class="symptom-title">신경외과에서 치료하는 질환</h3>
						<ul class="symptom-list">
							<li>뇌-척수의 선천성 기형</li>
							<li>수두증</li>
							<li>두개강내 낭종</li>
							<li>뇌종양</li>
							<li>척추 및 척수 종양</li>
							<li>척수 혈관기형</li>
							<li>후종인대골화증</li>
						</ul>
						<h3 class="symptom-title">신경계 질환의 증상</h3>
						<ul class="symptom-list">
							<li>두통</li>
							<li>어지럼증</li>
							<li>팔다리의 마비나 감각이상</li>
							<li>저림</li>
							<li>발음 또는 언어장애</li>
							<li>기억력저하</li>
							<li>보행장애</li>
							<li>경련발작</li>
							<li>의식저하</li>
							<li>실신</li>
						</ul>
					</div>
				</div>
			</div>
			<div class="departments-container">
				<div class="department">
					<div class="department-title">
						<h2>산부인과</h2>
					</div>
					<p>산부인과 질환의 증상은 복부 통증, 비정상 출혈, 생리통 등이 있습니다.</p>
					<div class="symptoms">
						<h3 class="symptom-title">질환의 증상</h3>
						<ul class="symptom-list">
							<li>복부 팽만감, 복부 통증, 아랫배를 누르는 느낌</li>
							<li>월경일이 아닌데도 출혈이 있는 비정상 출혈</li>
							<li>월경 과다, 월경 불순</li>
							<li>아랫배의 뻐근한 통증</li>
							<li>평소보다 과도한 생리양과 극심한 생리통</li>
							<li>만성 골반통</li>
							<li>반 내의 장기를 압박해서 생기는 빈뇨</li>
							<li>냉 또는 대하증</li>
						</ul>
						<h3 class="symptom-title">산부인과 질환</h3>
						<ul class="symptom-list">
							<li>자궁내막증식증</li>
							<li>자궁근종</li>
							<li>난소 낭종</li>
							<li>질염</li>
						</ul>
					</div>
				</div>
				<div class="department">
					<div class="department-title">
						<h2>안과</h2>
					</div>
					<p>안과 증상은 눈의 충혈, 눈물고임, 시력 감소, 눈부심, 눈의 피로 등이 있습니다.</p>
					<div class="symptoms">
						<h3 class="symptom-title">안과 증상</h3>
						<ul class="symptom-list">
							<li>눈꼽, 눈의 자극감, 눈의 충혈, 눈부심, 눈의 피로 등의 증상은 결막염의 증상.</li>
							<li>시력 감소, 두통 등의 증상은 근시의 증상.</li>
							<li>눈을 자주 비비거나 밝은 빛 아래에서 심한 눈부심을 겪는 경우 덧눈꺼풀을 의심해볼 수 있습니다.</li>
							<li>눈 앞이 흐릿하고 잘 안 보일 때, 어둡고 침침할 때 등은 시력을 떨어뜨리는 안과 질환의 증상입니다.</li>
							<li>밝은 빛으로 인한 통증(광선공포증)은 포도막염, 각막염 등의 증상일 수 있습니다.</li>
							<li>두통, 메스꺼움, 구토, 시야 좁아짐 등의 증상은 안압이 높아진 증상일 수 있습니다.</li>
							<li>갑자기 눈에 벌레 같은 게 떠다니는 것 같다 던가, 사물이 왜곡되어 보인 적이 있다면 망막박리,
								당뇨망막병증, 황반변성 등의 실명질환을 의심해볼 수 있습니다.</li>
						</ul>
					</div>
				</div>
				<div class="department">
					<div class="department-title">
						<h2>이빈후과</h2>
					</div>
					<p>이비인후과에서 진료받는 증상에는 코, 귀, 평형기능 등 관련 증상이 있습니다.</p>
					<div class="symptoms">
						<h3 class="symptom-title">코의 증상</h3>
						<ul class="symptom-list">
							<li>감기</li>
							<li>코막힘(비폐색)</li>
							<li>콧물(비루)</li>
							<li>후각장애</li>
							<li>코피(비출혈)</li>
							<li>만성 비후성 비염</li>
							<li>알레르기성 비염</li>
							<li>축농증(부비동염)</li>
						</ul>
						<h3 class="symptom-title">귀의 증상</h3>
						<ul class="symptom-list">
							<li>이명(귀울림)</li>
							<li>난청(청력장애)</li>
							<li>어지러움</li>
							<li>귀의 통증</li>
							<li>이루</li>
						</ul>
						<h3 class="symptom-title">평형기능의 증상</h3>
						<ul class="symptom-list">
							<li>주위가 빙빙 도는 느낌</li>
							<li>흔들거리는 느낌</li>
							<li>중심 잡기가 어렵다</li>
							<li>구토가 나거나 구역질</li>
							<li>두통</li>
						</ul>
						<h3 class="symptom-title">이비인후과에서 진료받는 증상은 이외</h3>
						<ul class="symptom-list">
							<li>후비루</li>
							<li>기침</li>
							<li>얼굴 통증</li>
							<li>치아 통증</li>
							<li>악취</li>
						</ul>
					</div>
				</div>
				<div class="department">
					<div class="department-title">
						<h2>치과</h2>
					</div>
					<p>치과 질환의 증상은 잇몸이 붓고 피가 나거나, 치아가 흔들리거나, 구취가 나거나 하는 등 다양합니다.</p>
					<div class="symptoms">
						<h3 class="symptom-title">치아우식증(충치)</h3>
						<ul class="symptom-list">
							<li>구강 내 세균이 치아 표면을 녹이는 산을 만들어 발생하는 감염성 세균질환입니다</li>
						</ul>
						<h3 class="symptom-title">치주질환</h3>
						<ul class="symptom-list">
							<li>잇몸과 치아를 지지하는 치조골에 염증이 생기는 질환으로, 풍치라고도 불립니다</li>
							<li>조기에 치료를 받지 않으면 잇몸에서 피가 나거나 붓거나 곪는 증상, 치아가 시리고 흔들리는 증상이 나타납니다.</li>
							<li>치은염과 치주염으로 나뉘며, 치은염이 악화되어 치조골에까지 염증이 확산되면 치주염이 됩니다</li>
							<li>잇몸이 붓고 붉어지며, 칫솔질 시 피가 나거나, 입 냄새가 나거나, 치아가 흔들리는 등의 증상이
								나타납니다</li>
						</ul>
						<h3 class="symptom-title">치은염</h3>
						<ul class="symptom-list">
							<li>잇몸에만 염증이 생긴 질환입니다 </li>
							<li>잇몸이 붓고 붉어지며, 칫솔질 시 피가 나는 등의 증상이 나타납니다</li>
						</ul>
						<h3 class="symptom-title">치과 질환의 증상</h3>
						<ul class="symptom-list">
							<li>잇몸이 빨갛게 붓고 출혈이 있다</li>
							<li>잇몸을 만지면 통증이 있다</li>
							<li>충치가 없지만, 구취가 심하게 난다</li>
							<li>치아 사이가 벌어지고 길어진 느낌이 난다</li>
							<li>치아가 흔들리고 잇몸에서 고름이 난다</li>
							<li>잇몸이 내려가고 이 사이가 뜨기 시작하며 치아가 약간씩 흔들리기 시작한다</li>
							<li>저작 시에 불편감을 호소하게 된다</li>
							<li>잇몸은 둥글게 부풀어 오르고, 표면은 붉고 윤택합니다</li>
						</ul>
					</div>
				</div>
			</div>
			<button id="search-button">뒤로가기</button>
		</section>
	</div>
</body>
<c:import url="/footer" />
</html>