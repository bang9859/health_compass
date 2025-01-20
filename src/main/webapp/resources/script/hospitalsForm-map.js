document.addEventListener("DOMContentLoaded", function() {
	console.log("JavaScript 실행됨");

	const hospitalList = window.hospitalList;
	console.log("Hospital List:", hospitalList);
	
	navigator.geolocation.getCurrentPosition(function(position) {
		const userLat = position.coords.latitude; // 위도
		const userLon = position.coords.longitude; // 경도

		var mapContainer = document.getElementById('map'), // 지도를 표시할 div 
			mapOption = {
				center: new kakao.maps.LatLng(userLat, userLon), // 지도의 중심좌표
				level: 3 // 지도의 확대 레벨
			};

		// 지도를 생성합니다    
		var map = new kakao.maps.Map(mapContainer, mapOption);

		// 현재 위치 마커 표시
		var markerPosition = new kakao.maps.LatLng(userLat, userLon);

		var positions = [
			{
				title: '현재위치',
				latlng: new kakao.maps.LatLng(userLat, userLon)
			},
			{
				title: '강남역',
				latlng: new kakao.maps.LatLng(37.496486063, 127.028361548)
			},
			{
				title: '교대역',
				latlng: new kakao.maps.LatLng(37.493922974, 127.014393729)
			}
		]

		var imageSrc = "https://t1.daumcdn.net/localimg/localimages/07/mapapidoc/markerStar.png";

		for (var i = 0; i < positions.length; i++) {

			// 마커 이미지의 이미지 크기 입니다
			var imageSize = new kakao.maps.Size(24, 35);

			// 마커 이미지를 생성합니다    
			var markerImage = new kakao.maps.MarkerImage(imageSrc, imageSize);

			// 마커를 생성합니다
			var marker = new kakao.maps.Marker({
				map: map, // 마커를 표시할 지도
				position: positions[i].latlng, // 마커를 표시할 위치
				title: positions[i].title, // 마커의 타이틀, 마커에 마우스를 올리면 타이틀이 표시됩니다
				image: markerImage // 마커 이미지 
			});

			// 위치에 말풍선 표시
			var infowindow = new kakao.maps.InfoWindow({
				content: '<div style="width:150px;text-align:center;padding:6px 0;">현재 위치</div>'
			});
			infowindow.open(map, marker);
		}
	});
});