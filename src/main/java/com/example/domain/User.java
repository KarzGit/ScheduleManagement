package com.example.domain;

import java.util.List;

public class User {
	private Integer id;
	private String name;
	private String mail;
	private String password;
	private Integer zipcode;
	private String iconImagePath;
	private Integer mailNotification;
	private List<Integer> followedList;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

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

	public Integer getZipcode() {
		return zipcode;
	}

	public void setZipcode(Integer zipcode) {
		this.zipcode = zipcode;
	}

	public String getIconImagePath() {
		return iconImagePath;
	}

	public void setIconImagePath(String iconImagePath) {
		this.iconImagePath = iconImagePath;
	}

	public Integer getMailNotification() {
		return mailNotification;
	}

	public void setMailNotification(Integer mailNotification) {
		this.mailNotification = mailNotification;
	}

	public List<Integer> getFollowedList() {
		return followedList;
	}

	public void setFollowedList(List<Integer> followedList) {
		this.followedList = followedList;
	}

	@Override
	public String toString() {
		return "User [id=" + id + ", name=" + name + ", mail=" + mail + ", password=" + password + ", zipcode="
				+ zipcode + ", iconImagePath=" + iconImagePath + ", mailNotification=" + mailNotification
				+ ", followedList=" + followedList + "]";
	}
	

}
