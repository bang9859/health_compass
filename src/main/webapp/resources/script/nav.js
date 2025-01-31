window.addEventListener("load", function () {
    let currentImageIndex = 0;
    const images = [
        "https://www.seochonoin.org/data/editor/2110/thumb-1847822453_1635121464.061_1400x1980.jpg",
        "https://www.dailydental.co.kr/data/photos/20200936/art_1599189905_a38ed7.bmp",
        "https://www.kdca.go.kr/html/a2/namoimage/images/000055/%EA%B1%B4%EA%B0%95%ED%95%9C_%EA%B2%A8%EC%9A%B8%EB%82%98%EA%B8%B0_%ED%8F%AC%EC%8A%A4%ED%84%B0.jpg"
    ];

    const imgBox = document.getElementById('img-box');
    if (!imgBox) {
        console.error('No image container found.');
        return;
    }

    // 이미지 요소 동적 생성
    images.forEach((src, index) => {
        const img = document.createElement('img');
        img.src = src;
		img.style.width = '200px';  // 이미지 크기 조정
		img.style.height = '250px'; // 높이는 자동으로 조정
        img.style.display = index === currentImageIndex ? 'block' : 'none';
        img.style.cursor = 'pointer'; // 클릭 가능하게 설정
        img.addEventListener('click', () => openModal(src)); // 클릭 이벤트 추가
        imgBox.appendChild(img);
    });

    const imgElements = imgBox.getElementsByTagName('img');
    if (imgElements.length === 0) {
        console.error('No image elements found.');
        return;
    }

    function changeImage() {
        imgElements[currentImageIndex].style.display = 'none';
        currentImageIndex = (currentImageIndex + 1) % images.length;
        imgElements[currentImageIndex].style.display = 'block';
    }

    setInterval(changeImage, 5000);


    // 모달 관련 요소 가져오기
    const modal = document.getElementById('modal');
    const modalImg = modal.querySelector('img');
    const closeModalBtn = modal.querySelector('.close');

    // 모달 열기
    function openModal(src) {
        modalImg.src = src;
        modal.style.display = 'flex';
    }

    // 모달 닫기
    closeModalBtn.addEventListener('click', () => {
        modal.style.display = 'none';
    });

    // 모달 배경 클릭 시 닫기
    modal.addEventListener('click', (e) => {
        if (e.target === modal) {
            modal.style.display = 'none';
        }
    });
});

