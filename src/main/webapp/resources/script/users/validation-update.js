import { checkDuplEmail2, checkDuplTel2, validatePassword, validateEmail, validateTel, formatPhoneString } from "./validation.js";

window.onload = () => {
	const form = document.getElementById("form-update");

	const username = document.getElementById("username");
	const password = document.getElementById("password");
	const email = document.getElementById("email");
	const tel = document.getElementById("tel");

	const px = "29.46px";

	let isValidEmail = validateEmail(email.value);
	let isValidTel = validateTel(tel.value);
	
	let isPassPassword = false;
	let isPassEmail = false;
	let isPassTel = false;

	password.addEventListener("focusout", async e => {
		const input = e.target.value;
		const errPw = document.getElementById("err-msg-pw");

		isPassPassword = false;

		if (input === "" || !validatePassword(input)) {
			errPw.style.display = "block";
			errPw.style.marginBottom = '15px';
			password.style.marginBottom = '0';
		} else {
			isPassPassword = true;
			errPw.style.display = "none";
			password.style.marginBottom = px;
		}
	});

	email.addEventListener("focusout", async e => {
		const input = e.target.value;
		const errEmail = document.getElementById("err-msg-email");
		const errDupl = document.getElementById("err-msg-duplEmail");

		isPassEmail = false;
		isValidEmail = await checkDuplEmail2(username.value, email.value);

		if (input === "" || !validateEmail(input)) {
			errEmail.style.display = "block";
			errDupl.style.display = "none";
			errEmail.style.marginBottom = '15px';
			email.style.marginBottom = '0';
		} else if (isValidEmail) {
			errEmail.style.display = "none";
			errDupl.style.display = "block";
			errDupl.style.marginBottom = '15px';
			email.style.marginBottom = '0';
		} else {
			isPassEmail = true;
			errEmail.style.display = "none";
			errDupl.style.display = "none";
			email.style.marginBottom = px;
		}
	});

	tel.addEventListener("focusout", async e => {
		const input = e.target.value;
		const errTel = document.getElementById("err-msg-tel");
		const errDupl = document.getElementById("err-msg-duplTel");

		isPassTel = false;
		tel.value = formatPhoneString(input);
		isValidTel = await checkDuplTel2(username.value, tel.value);
				
		if (input === "" || !validateTel(tel.value)) {
			errTel.style.display = "block";
			errDupl.style.display = "none";
			errTel.style.marginBottom = '15px';
			tel.style.marginBottom = '0';
		} else if (isValidTel) {
			errTel.style.display = "none";
			errDupl.style.display = "block";
			errDupl.style.marginBottom = '15px';
			tel.style.marginBottom = '0';
		} else {
			isPassTel = true;
			errTel.style.display = "none";
			errDupl.style.display = "none";
			tel.style.marginBottom = px;
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

		if (email.value === "") {
			const errEmpty = document.getElementById("err-msg-email");
			errEmpty.style.display = "block";
			errEmpty.style.marginBottom = '15px';
			email.style.marginBottom = "0";
		}

		if (tel.value === "") {
			const errEmpty = document.getElementById("err-msg-tel");
			errEmpty.style.display = "block";
			errEmpty.style.marginBottom = '15px';
			tel.style.marginBottom = "0";
		}
		if (isPassPassword && isPassEmail && isPassTel) {
			form.submit();
		}
	});
}