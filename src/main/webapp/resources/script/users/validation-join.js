import { validateUsername, validatePassword, validateEmail, validateName, validateTel, formatPhoneString } from "./validation.js";

window.onload = () => {
	const form = document.getElementById("form-join");

	const username = document.getElementById("username");
	const duplUsername = document.getElementById("checkdupl");
	const password = document.getElementById("password");
	const email = document.getElementById("email");
	const name = document.getElementById("name");
	const tel = document.getElementById("tel");
	
	let isValidUsername = validateUsername(username.value);
	let isValidEmail = validatePassword(email.value);
	let isValidTel = validatePassword(tel.value);
	
	username.addEventListener("focusout", async e => {
		const input = e.target.value;
		const errId = document.getElementById("err-msg-id");
		const errDupl = document.getElementById("err-msg-duplId");
		
		isValidUsername = await checkDuplUsername(username.value);
		
		if (input === "" || !validateUsername(input)) {
			errId.style.display = "block";
			errDupl.style.display = "none";
			errId.style.marginBottom = '15px';
			username.style.marginBottom = '0';
			duplUsername.style.marginBottom = '0';
		} else if (isValidUsername) {
			errId.style.display = "none";
			errDupl.style.display = "block";
			errDupl.style.marginBottom = '15px';
			username.style.marginBottom = '0';
		} else {
			errDupl.style.display = "none";
			errId.style.display = "none";
			username.style.marginBottom = '15px';
		}
	});
	
	duplUsername.addEventListener("click", async e => {
		const errId = document.getElementById("err-msg-id");
		const errDupl = document.getElementById("err-msg-dupl");
		
		isValidUsername = await checkDuplUsername(username.value);
		
		if(isValidUsername){
			errDupl.style.display = "block";
			errDupl.style.marginBottom = '15px';
			username.style.marginBottom = '0';
			errId.style.marginBottom = '0';
		}
	});
	
	password.addEventListener("focusout", async e => {
		const input = e.target.value;
		const errPw = document.getElementById("err-msg-pw");
		
		if (input === "" || !validatePassword(input)) {
			errPw.style.display = "block";
			errPw.style.marginBottom = '15px';
			password.style.marginBottom = '0';
		} else {
			errPw.style.display = "none";
			password.style.marginBottom = '15px';
		}
	});
	
	email.addEventListener("focusout", async e => {
		const input = e.target.value;
		const errEmail = document.getElementById("err-msg-email");
		const errDupl = document.getElementById("err-msg-duplEmail");
		
		isValidEmail = await checkDuplEmail(email.value);
		
		if (input === "" || !validateEmail(input)) {
			errEmail.style.display = "block";
			errEmail.style.marginBottom = '15px';
			email.style.marginBottom = '0';
		} else if(isValidEmail) {
			errEmail.style.display = "none";
			errDupl.style.display = "block";
			errDupl.style.marginBottom = '15px';
			email.style.marginBottom = '0';
		} else {
			errEmail.style.display = "none";
			email.style.marginBottom = '15px';
		}
	});
	
	name.addEventListener("focusout", async e => {
		const input = e.target.value;
		const errName = document.getElementById("err-msg-name");
		
		if (input === "" || !validateName(input)) {
			errName.style.display = "block";
			errName.style.marginBottom = '15px';
			name.style.marginBottom = '0';
		} else {
			errName.style.display = "none";
			name.style.marginBottom = '15px';
		}
	});
	
	tel.addEventListener("focusout", async e => {
		const input = e.target.value;
		const errTel = document.getElementById("err-msg-tel");
		const errDupl = document.getElementById("err-msg-duplTel");
		
		isValidTel = await checkDuplTel(tel.value);
		tel.value = formatPhoneString(input);
		
		if (input === "" || !validateTel(tel.value)) {
			errTel.style.display = "block";
			errTel.style.marginBottom = '15px';
			tel.style.marginBottom = '0';
		} else if(isValidTel) {
			errTel.style.display = "none";
			errDupl.style.display = "block";
			errDupl.style.marginBottom = '15px';
			tel.style.marginBottom = '0';
		} else {
			errTel.style.display = "none";
			tel.style.marginBottom = '15px';
		}
	});
}

async function checkDuplUsername(username) {
	const response = await fetch("/service/users?command=search-username", {
		method: "POST",
		headers: {
			"Content-Type": "application/json"
		},
		body: JSON.stringify({
			"username": username
		})
	});
	const json = await response.json();
	return json.isValid;
}

async function checkDuplEmail(email) {
	const response = await fetch("/service/users?command=search-email", {
		method: "POST",
		headers: {
			"Content-Type": "application/json"
		},
		body: JSON.stringify({
			"username": email
		})
	});
	const json = await response.json();
	return json.isValid;
}

async function checkDuplTel(tel) {
	const response = await fetch("/service/users?command=search-tel", {
		method: "POST",
		headers: {
			"Content-Type": "application/json"
		},
		body: JSON.stringify({
			"username": tel
		})
	});
	const json = await response.json();
	return json.isValid;
}