window.onload = () => {
	const form = document.getElementById("form-delete");

	const password = document.getElementById("password");

	let isPassPassword = false;

	password.addEventListener("focusout", async e => {
		const input = e.target.value;
		const errPw = document.getElementById("err-msg-pw");

		const px = "29.46px";

		if (input === "") {
			errPw.style.display = "block";
			errPw.style.marginBottom = '15px';
			password.style.marginBottom = "0";
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
			errEmpty.style.marginBottom = '15px';
			password.style.marginBottom = "0";
		}

		if (isPassPassword) {
			form.submit();
		}
	});
}