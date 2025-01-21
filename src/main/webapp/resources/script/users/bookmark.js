window.onload = () => {
	const ul = document.getElementById("bookmark-list");

	ul.addEventListener('click', e => {
		console.log(e.target); // li
		console.log(e.currentTarget);  // ul
	});

}




function panTo() {
	// 이동할 위도 경도 위치를 생성합니다 
	var moveLatLon = new kakao.maps.LatLng(33.450580, 126.574942);

	// 지도 중심을 부드럽게 이동시킵니다
	// 만약 이동할 거리가 지도 화면보다 크면 부드러운 효과 없이 이동합니다
	map.panTo(moveLatLon);
} 