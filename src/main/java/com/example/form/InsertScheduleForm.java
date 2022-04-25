package com.example.form;

import javax.validation.constraints.NotBlank;

public class InsertScheduleForm {

	@NotBlank(message = "タイトルを入力してください")
	private String title;
	private String description;
	private String kinds;
	@NotBlank(message = "開始日を入力してください")
	private String startDate;
	@NotBlank(message = "開始時刻を入力してください")
	private String startTime;
	@NotBlank(message = "終了日を入力してください")
	private String endDate;
	@NotBlank(message = "終了時刻を入力してください")
	private String endTime;
	private String memberMail;

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getKinds() {
		return kinds;
	}

	public void setKinds(String kinds) {
		this.kinds = kinds;
	}

	public String getStartDate() {
		return startDate;
	}

	public void setStartDate(String startDate) {
		this.startDate = startDate;
	}

	public String getStartTime() {
		return startTime;
	}

	public void setStartTime(String startTime) {
		this.startTime = startTime;
	}

	public String getEndDate() {
		return endDate;
	}

	public void setEndDate(String endDate) {
		this.endDate = endDate;
	}

	public String getEndTime() {
		return endTime;
	}

	public void setEndTime(String endTime) {
		this.endTime = endTime;
	}

	public String getMemberMail() {
		return memberMail;
	}

	public void setMemberMail(String memberMail) {
		this.memberMail = memberMail;
	}

	@Override
	public String toString() {
		return "InsertScheduleForm [title=" + title + ", description=" + description + ", kinds=" + kinds
				+ ", startDate=" + startDate + ", startTime=" + startTime + ", endDate=" + endDate + ", endTime="
				+ endTime + ", memberMail=" + memberMail + "]";
	}

}
