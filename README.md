# 🧭건강나침반 (증상에 따른 병원 위치 검색) 


### 프로젝트 소개
- 건강나침반은 자신이 선택한 증상에 따른 진료과를 분류합니다.
- 분류된 진료과중 현재 위치를 기준으로 해당 지역의 병원을 안내해주는 페이지입니다.
- 또한 투약 일정을 관리하는 투약 캘린더 기능을 가지고 있습니다.


### 1. 팀원 구성 및 역할 분담

#### 🥝 방서준 (팀장)
 - 기능 : 투약 캘린더
 	- 일정등록
	- 일정 조회
	- 일정 제거
	- 달력 랜더링
 	- 약명을 통한 약 정보 검색

#### 🍋 박현균 (서기)
 - 기능 : 병원 찾기
	- 증상체크 및 병원조회
	- 병원데이터 검색
	- 회원 위치 및 주소찾기
	- 회원 및 병원 지도에 마킹
	- 선택한 병원 북마크 저장

#### 🍈 이준호 (팀원)
 - 기능 : 회원 관리
	- 회원가입
	- 로그인/로그아웃
	- 회원탈퇴
	- 회원정보 수정
	- 북마크 확인/삭제


### 2. 개발환경
- Back-End : Java Development Kit(JDK) 17, Apache Tomcat 10.1.33, Servelt
- Front-End : JSP/JSTL, HTML/CSS ,JavaScript
- Database : MySQL 8.0.33
- External API : Kakao API, Geolocation API, 공공데이터포털 API


### 3. 개발 기간 및 작업 관리
- 프로젝트 기회 및 DB Modeling : 1/5~1/8
- 서비스 개발 : 1/9~1/22
- 피드백 수정 및 배포 : 2/23~1/31


### 4. 문서
- [프로젝트 기획서](https://docs.google.com/presentation/d/1SwNYwLcoK7BGYUV5Aj_K-gylToSablpI/edit?usp=sharing&ouid=103071439001641622197&rtpof=true&sd=true)
- [요구사항 정의서](https://docs.google.com/spreadsheets/d/1FlX8jJGzbZbl3qQseZRvB1lcUoSA_XZN/edit?usp=sharing&ouid=103071439001641622197&rtpof=true&sd=true)
- [화면 설계서](https://docs.google.com/presentation/d/1-UwIm_ufqPJt3gWkfR_vxtdoUtlZRDN1/edit?usp=sharing&ouid=103071439001641622197&rtpof=true&sd=true)
- [테이블 정의서](https://docs.google.com/spreadsheets/d/1wRIv9m293xThOJqp4AM4j8pMLC35WMNd/edit?usp=sharing&ouid=103071439001641622197&rtpof=true&sd=true)
- [인터페이스 정의서](https://docs.google.com/spreadsheets/d/1hGnLrNdW7znagVEfUlED4_wNCyW71eKa/edit?usp=sharing&ouid=103071439001641622197&rtpof=true&sd=true)


### 5. 페이지별 기능

### 투약 캘린더

#### [달력 랜더링]
- 오늘 날짜의 달에 대한 달력을 출력합니다
- 이전달과 다음달 버튼을 누를시 해당 달의 달력을 출력합니다.
  	- 오늘 날짜의 정보들을 받아 첫째날 이전의 빈칸을 채워주고 해당 날짜를 채워 출력합니다.
  	- 오늘 날짜에 작은 원을 표시하여 달력 사용을 더욱 편리하게 하였습니다.
  	  
![Image](https://github.com/user-attachments/assets/c8b0f8eb-eede-4523-a8b4-c32cd427ba87)

#### [일정 등록 및 조회]
- 약품 검색 버튼을 눌러 약품의 정보를 받아옵니다.
- 받아온 정보와 나머지 정보들을 입력 후 등록하여 바로 최신화 하도록 구현하였습니다.

![Image](https://github.com/user-attachments/assets/c9483173-5a43-4430-89af-880fbf29a612)

#### [일정 삭제]
- 약품 목록의 우측에 위치한 일정 삭제 버튼을 누르면 일정이 삭제됩니다.
- 등록과 마찬가지로 삭제 버튼을 눌렀다면 바로 최신화 하도록 구현하였습니다.

![Image](https://github.com/user-attachments/assets/98604e95-fc7d-4630-924b-5797b2843e83)

### 병원 찾기

### [병원 찾기 (로그인 ❌)]
- **병원 검색** | 증상을 선택 후, 증상에 해당하는 진료과가 있는 병원을 따로 따로불러옵니다.
- **병원 목록 정리** | 중복 병원을 제거하고, **진료과가 많은 순서**대로 정렬했습니다.
- **지도 표시** | **현재 페이지에 있는 병원만 지도에 표시**됩니다.
- **목록 → 지도 이동** | 목록에 마우스를 올리면 **해당 병원으로 지도 중앙으로 이동**후 **병원 이름**이 표시됩니다.
- **현재 위치 이동** | 현재 위치 버튼 클릭 시, **지도 중앙에 현재 위치로 이동**합니다.
- **북마크 제한** | ❌ 로그인하지 않으면 북마크 추가 불가능합니다.
  
![Image](https://youtu.be/CLtKzUFtxn4)

### [병원 찾기 로그인 ✅]
- **북마크 추가 가능** | ✅ 로그인 후 북마크 추가 가능합니다.
- **중복 북마크 방지** | ❌ 같은 병원 중복 추가 불가능합니다.
 
![Image](https://youtu.be/_8TyHDnu_Z4)

### [6시 이후 응급실 진료 가능한 병원만 표시]
- **응급실 병원 검색** |  **오후 6시 이후** 병원 찾기 실행 시, **응급실 운영 병원만 표시**합니다.
  
![Image](https://youtu.be/IxPY-lip8UY)

### 6. 발표 영상
- https://www.youtube.com/watch?v=ZctVnA1C72I


### 7. 프로젝트 후기

### 🥝방서준 

### 🍋 박현균

### 🍈 이준호
