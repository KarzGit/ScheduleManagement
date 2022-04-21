'use strict'
const password = document.getElementById('password');
const cPassword = document.getElementById('confirmPassword');
const error = document.getElementById('duplicatePasswordError');
const subBtn = document.getElementById('subBtn');
password.addEventListener("keyup", () => {
	subBtn.disabled = true;
});

cPassword.addEventListener("keyup", () => {
	if (password.value != cPassword.value) {
		error.textContent = 'パスワードが一致しません';
		error.style.color = 'red';
		subBtn.disabled = true;
	}
	if (password.value == cPassword.value) {
		error.textContent = '';
		subBtn.disabled = false;
	}
});