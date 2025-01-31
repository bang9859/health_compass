import { checkDuplEmail2, checkDuplTel2, validatePassword, validateEmail, validateTel, formatPhoneString } from "./validation.js";

window.onload = () => {
	const form = document.getElementById("form-update");

	const username = document.getElementById("username");
	const password = document.getElementById("password");
	const email = document.getElementById("email");
	const tel = document.getElementById("tel");

	const px = "29.44px";
	const mb = "10.3px";

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
			errPw.style.marginBottom = '14.3px';
			password.style.marginBottom = mb;
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
			errEmail.style.marginBottom = '14.3px';
			email.style.marginBottom = mb;
		} else if (isValidEmail) {
			errEmail.style.display = "none";
			errDupl.style.display = "block";
			errDupl.style.marginBottom = '14.3px';
			email.style.marginBottom = mb;
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
			errTel.style.marginBottom = '14.3px';
			tel.style.marginBottom = mb;
		} else if (isValidTel) {
			errTel.style.display = "none";
			errDupl.style.display = "block";
			errDupl.style.marginBottom = '14.3px';
			tel.style.marginBottom = mb;
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
			errEmpty.style.marginBottom = '14.3px';
			password.style.marginBottom = mb;
		}

		const errEmail = document.getElementById("err-msg-email");
		const errDuplEmail = document.getElementById("err-msg-duplEmail");
		isValidEmail = await checkDuplEmail2(username.value, email.value);
		if (email.value === "" || !validateEmail(email.value)) {
			errEmail.style.display = "block";
			errEmail.style.marginBottom = '14.3px';
			email.style.marginBottom = mb;
		} else if (isValidEmail) {
			errEmail.style.display = "none";
			errDuplEmail.style.display = "block";
			errDuplEmail.style.marginBottom = '14.3px';
			email.style.marginBottom = mb;
		} else {
			isPassEmail = true;
			errEmail.style.display = "none";
			errDuplEmail.style.display = "none";
			email.style.marginBottom = px;
		}
		
		const errTel = document.getElementById("err-msg-tel");
		const errDuplTel = document.getElementById("err-msg-duplTel");
		isValidTel = await checkDuplTel2(username.value, tel.value);
		if (tel.value === "" || !validateTel(tel.value)) {
			errTel.style.display = "block";
			errTel.style.marginBottom = '14.3px';
			tel.style.marginBottom = mb;
		} else if (isValidTel) {
			errTel.style.display = "none";
			errDuplTel.style.display = "block";
			errDuplTel.style.marginBottom = '14.3px';
			tel.style.marginBottom = mb;
		} else {
			isPassTel = true;
			errTel.style.display = "none";
			errDuplTel.style.display = "none";
			tel.style.marginBottom = px;
		}
				
		if (isPassPassword && isPassEmail && isPassTel) {
			form.submit();
		}
	});
}