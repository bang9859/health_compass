window.onload = () => {
	const form = document.getElementById("form-delete");

	const password = document.getElementById("password");
	
	const px = "29.44px";
	const mb = "10.3px";
			
	let isPassPassword = false;

	password.addEventListener("focusout", async e => {
		const input = e.target.value;
		const errPw = document.getElementById("err-msg-pw");

		if (input === "") {
			errPw.style.display = "block";
			errPw.style.marginBottom = '14.3px';
			password.style.marginBottom = mb;
		} else {
			isPassPassword = true;
			errPw.style.display = "none";
			password.style.marginBottom = px;
		}
	});

	form.addEventListener("submit", async e => {
		e.preventDefault();

		if (password.value === "") {
			const errEmpty = document.getElementById("err-msg-pw");
			
			errEmpty.style.display = "block";
			errEmpty.style.marginBottom = '14.3px';
			password.style.marginBottom = mb;
		}

		if (isPassPassword) {
			form.submit();
		}
	});
}