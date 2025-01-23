window.onload = () => {
	const form = document.getElementById("form-login");

	const username = document.getElementById("username");
	const password = document.getElementById("password");

	const px = "29.44px";
	const mb = "10.3px";
	
	let isPassUsername = false;
	let isPassPassword = false;
	
	username.addEventListener("focusout", async e => {
		const input = e.target.value;
		const errId = document.getElementById("err-msg-id");

		isPassUsername = false;
		
		if (input === "") {
			errId.style.display = "block";
			errId.style.marginBottom = '14.3px';
			username.style.marginBottom = mb;
		} else {
			isPassUsername = true;
			errId.style.display = "none";
			username.style.marginBottom = px;
		}
	});

	password.addEventListener("focusout", async e => {
		const input = e.target.value;
		const errPw = document.getElementById("err-msg-pw");

		isPassPassword = false;
		
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

		if (username.value === "") {
			const errEmpty = document.getElementById("err-msg-id");
			errEmpty.style.display = "block";
			errEmpty.style.marginBottom = '14.3px';
			username.style.marginBottom = mb;
		}
		
		if (password.value === "") {
			const errEmpty = document.getElementById("err-msg-pw");
			errEmpty.style.display = "block";
			errEmpty.style.marginBottom = '14.3px';
			password.style.marginBottom = mb;
		}
		
		if (isPassUsername && isPassPassword) {
			form.submit();
		}
	});
}