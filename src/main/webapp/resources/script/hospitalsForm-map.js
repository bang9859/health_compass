document.addEventListener("DOMContentLoaded", function() {
	if (navigator.geolocation) {
	    navigator.geolocation.getCurrentPosition(function(position) {
	        const lat = position.coords.latitude; // 위도
	        const lon = position.coords.longitude; // 경도

	        // 카카오맵에 지도 표시
	        const mapContainer = document.getElementById('map'); 
	        const mapOption = {
	            center: new kakao.maps.LatLng(lat, lon), // 지도의 중심 좌표
	            level: 3 // 지도의 확대 레벨
	        };

	        const map = new kakao.maps.Map(mapContainer, mapOption); // 지도 생성

	        // 마커 표시
	        const markerPosition = new kakao.maps.LatLng(lat, lon); // 마커 위치
	        const marker = new kakao.maps.Marker({
	            position: markerPosition
	        });
	        marker.setMap(map); // 지도에 마커 표시

	    }, function(error) {
	        console.error('Geolocation 에러:', error);
	    });
	} else {
	    alert('Geolocation을 지원하지 않는 브라우저입니다.');
	}
});