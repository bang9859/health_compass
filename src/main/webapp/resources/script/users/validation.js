export function validateUsername(username) {
	const regex = /^[가-힣\w\_]{5,}$/;
	return regex.test(username);
}

export function validatePassword(password) {
	const regex = /^[\w-\!\@\#\$\%\^\&]{8,}$/;
	return regex.test(password);
}

export function validateEmail(email) {
	const regex = /^[\w-\.]+@([\w-]+\.)+[\w-]{2,4}$/;
	return regex.test(email);
}

export function validateName(name) {
	const regex = /^[가-힣]{2,}$/;
	return regex.test(name);
}

export function validateTel(tel) {
	const regex = /^[0-9]{3}-[0-9]{4}-[0-9]{4}$/;
	return regex.test(tel);
}

export function formatPhoneString(str) {
	let result = "";
	str = str.replaceAll(/\D/g, "");
	if(str.length === 11 && /^[010]/.test(str)) {
		const head = str.substring(0, 3);
		const mid = str.substring(3,7);
		const tail = str.substring(7,11);		
		result = `${head}-${mid}-${tail}`;
	} else if(str.length === 10 && /^[011]/.test(str)) {
		const head = str.substring(0, 3);
		const mid = str.substring(3,6);
		const tail = str.substring(6,10);		
		result = `${head}-${mid}-${tail}`;
	} else {
		return str;
	}
	return result;
}