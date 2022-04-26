package com.example.form;

public class UpdateScheduleForm {
	
	private Integer userId;
	private String kinds;
	private String startDate;
	private String startTime;
	private String endDate;
	private String endTime;
	private String title;
	private String description;
	public Integer getUserId() {
		return userId;
	}
	public void setUserId(Integer userId) {
		this.userId = userId;
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
	@Override
	public String toString() {
		return "UpdateScheduleForm [userId=" + userId + ", kinds=" + kinds + ", startDate=" + startDate + ", startTime="
				+ startTime + ", endDate=" + endDate + ", endTime=" + endTime + ", title=" + title + ", description="
				+ description + "]";
	}
	

}
