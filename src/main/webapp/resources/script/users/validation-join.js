import { checkDuplUsername, checkDuplEmail, checkDuplTel, validateUsername, validatePassword, validateEmail, validateName, validateTel, formatPhoneString } from "./validation.js";

window.onload = () => {
	const form = document.getElementById("form-join");

	const username = document.getElementById("username");
	const password = document.getElementById("password");
	const email = document.getElementById("email");
	const name = document.getElementById("name");
	const birth = document.getElementById("birth");
	const tel = document.getElementById("tel");

	const px = "29.46px";
		
	let isValidUsername = validateUsername(username.value);
	let isValidEmail = validateEmail(email.value);
	let isValidTel = validateTel(tel.value);

	let isPassUsername = false;
	let isPassPassword = false;
	let isPassEmail = false;
	let isPassName = false;
	let isPassBirth = false;
	let isPassTel = false;

	username.addEventListener("focusout", async e => {
		const input = e.target.value;
		const errId = document.getElementById("err-msg-id");
		const errDupl = document.getElementById("err-msg-duplId");

		isPassUsername = false;
		isValidUsername = await checkDuplUsername(username.value);

		if (input === "" || !validateUsername(input)) {
			errId.style.display = "block";
			errDupl.style.display = "none";
			errId.style.marginBottom = '15px';
			username.style.marginBottom = '10.5px';
		} else if (isValidUsername) {
			errId.style.display = "none";
			errDupl.style.display = "block";
			errDupl.style.marginBottom = '15px';
			username.style.marginBottom = '10.5px';
		} else {
			isPassUsername = true;
			errDupl.style.display = "none";
			errId.style.display = "none";
			username.style.marginBottom = px;
		}
	});

	password.addEventListener("focusout", async e => {
		const input = e.target.value;
		const errPw = document.getElementById("err-msg-pw");

		isPassPassword = false;

		if (input === "" || !validatePassword(input)) {
			errPw.style.display = "block";
			errPw.style.marginBottom = '15px';
			password.style.marginBottom = '10.5px';
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
		isValidEmail = await checkDuplEmail(email.value);

		if (input === "" || !validateEmail(input)) {
			errEmail.style.display = "block";
			errDupl.style.display = "none";
			errEmail.style.marginBottom = '15px';
			email.style.marginBottom = '10.5px';
		} else if (isValidEmail) {
			errEmail.style.display = "none";
			errDupl.style.display = "block";
			errDupl.style.marginBottom = '15px';
			email.style.marginBottom = '10.5px';
		} else {
			isPassEmail = true;
			errEmail.style.display = "none";
			errDupl.style.display = "none";
			email.style.marginBottom = px;
		}
	});

	name.addEventListener("focusout", async e => {
		const input = e.target.value;
		const errName = document.getElementById("err-msg-name");

		isPassName = false;

		if (input === "" || !validateName(input)) {
			errName.style.display = "block";
			errName.style.marginBottom = '15px';
			name.style.marginBottom = '10.5px';
		} else {
			isPassName = true;
			errName.style.display = "none";
			name.style.marginBottom = px;
		}
	});
	
	birth.addEventListener("focusout", async e => {
		const input = e.target.value;
		const errBirth = document.getElementById("err-msg-birth");
		
		isPassBirth = false;
		
		if (input == "") {
			errBirth.style.display = "block";
			errBirth.style.marginBottom = '15px';
			birth.style.marginBottom = '10.5px';
		} else {
			isPassBirth = true;
			errBirth.style.display = "none";
			birth.style.marginBottom = px;
		}
		
	});
		
	tel.addEventListener("focusout", async e => {
		const input = e.target.value;
		const errTel = document.getElementById("err-msg-tel");
		const errDupl = document.getElementById("err-msg-duplTel");

		isPassTel = false;
		tel.value = formatPhoneString(input);
		isValidTel = await checkDuplTel(tel.value);

		if (input === "" || !validateTel(tel.value)) {
			errTel.style.display = "block";
			errDupl.style.display = "none";
			errTel.style.marginBottom = '15px';
			tel.style.marginBottom = '10.5px';
		} else if (isValidTel) {
			errTel.style.display = "none";
			errDupl.style.display = "block";
			errDupl.style.marginBottom = '15px';
			tel.style.marginBottom = '10.5px';
		} else {
			isPassTel = true;
			errTel.style.display = "none";
			errDupl.style.display = "none";
			tel.style.marginBottom = px;
		}
	});

	form.addEventListener("submit", async e => {
		e.preventDefault();

		if (username.value === "") {
			const errEmpty = document.getElementById("err-msg-id");
			errEmpty.style.display = "block";
			errEmpty.style.marginBottom = '15px';
			username.style.marginBottom = "10.5px";
		}
		if (password.value === "") {
			const errEmpty = document.getElementById("err-msg-pw");
			errEmpty.style.display = "block";
			errEmpty.style.marginBottom = '15px';
			password.style.marginBottom = "10.5px";
		}

		if (email.value === "") {
			const errEmpty = document.getElementById("err-msg-email");
			errEmpty.style.display = "block";
			errEmpty.style.marginBottom = '15px';
			email.style.marginBottom = "10.5px";
		}

		if (name.value === "") {
			const errEmpty = document.getElementById("err-msg-name");
			errEmpty.style.display = "block";
			errEmpty.style.marginBottom = '15px';
			name.style.marginBottom = "10.5px";
		}

		if (birth.value === "") {
			const errEmpty = document.getElementById("err-msg-birth");
			errEmpty.style.display = "block";
			errEmpty.style.marginBottom = '15px';
			birth.style.marginBottom = "10.5px";
		}

		if (tel.value === "") {
			const errEmpty = document.getElementById("err-msg-tel");
			errEmpty.style.display = "block";
			errEmpty.style.marginBottom = '15px';
			tel.style.marginBottom = "10.5px";
		}
		if (isPassUsername && isPassPassword && isPassEmail && isPassName && isPassBirth && isPassTel) {
			form.submit();
		}
	});
}