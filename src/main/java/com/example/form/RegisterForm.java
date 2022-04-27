package com.example.form;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

import org.springframework.web.multipart.MultipartFile;

public class RegisterForm {

	@NotBlank(message = "名前を入力してください")
	private String name;
	@NotBlank(message = "メールアドレスを入力してください")
	@Email(message = "メールアドレスの形式が不正です")
	private String mail;
	@NotBlank(message = "パスワードを入力して下さい")
	@Size(min = 8, max = 16, message = "パスワードは８文字以上１６文字以内で設定してください")
	private String password;
	private String confirmPassword;
	@NotBlank(message = "郵便番号を入力して下さい")
	@Pattern(regexp = "^[0-9]{7}$", message = "郵便番号はXXX-XXXXの形式で入力してください")
	private String zipcode;
	private MultipartFile iconImageFile;
	private String mailNotification;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getMail() {
		return mail;
	}

	public void setMail(String mail) {
		this.mail = mail;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getConfirmPassword() {
		return confirmPassword;
	}

	public void setConfirmPassword(String confirmPassword) {
		this.confirmPassword = confirmPassword;
	}

	public String getZipcode() {
		return zipcode;
	}

	public void setZipcode(String zipcode) {
		this.zipcode = zipcode;
	}

	public MultipartFile getIconImageFile() {
		return iconImageFile;
	}

	public void setIconImagePath(MultipartFile iconImageFile) {
		this.iconImageFile = iconImageFile;
	}

	public String getMailNotification() {
		return mailNotification;
	}

	public void setMailNotification(String mailNotification) {
		this.mailNotification = mailNotification;
	}

	@Override
	public String toString() {
		return "RegisterForm [name=" + name + ", mail=" + mail + ", password=" + password + ", confirmPassword="
				+ confirmPassword + ", zipcode=" + zipcode + ", iconImageFile=" + iconImageFile + ", mailNotification="
				+ mailNotification + "]";
	}

}