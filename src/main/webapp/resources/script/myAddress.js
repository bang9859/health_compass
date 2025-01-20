async function getAddress() {
	try {
		if (!navigator.geolocation) {
			console.error('Geolocation is not supported by your browser.');
			return;
		}

		navigator.geolocation.getCurrentPosition(async (position) => {
			const latitude = position.coords.latitude;
			const longitude = position.coords.longitude;

			const kakaoApiKey = 'de8a6d507a41e9775f196fc213f6e602';
			const apiUrl = `https://dapi.kakao.com/v2/local/geo/coord2address.json?x=${longitude}&y=${latitude}`;

			const response = await fetch(apiUrl, {
				method: 'GET',
				headers: {
					'Authorization': `KakaoAK ${kakaoApiKey}`
				}
			});

			if (!response.ok) {
				console.error('주소 정보를 가져오지 못했습니다:', response.status);
				return;
			}

			const data = await response.json();

			const address = data.documents[0]?.address?.address_name || 'No address found';
			const addressElement = document.getElementById('address');
			if (addressElement) {
				addressElement.textContent = `현재 위치: ${address}`;
			}
		}, (error) => {
			console.error('위치를 가져오는 동안 오류가 발생했습니다:', error);
		});

	} catch (error) {
		console.error('오류가 발생했습니다:', error);
	}
}

window.onload = getAddress();
