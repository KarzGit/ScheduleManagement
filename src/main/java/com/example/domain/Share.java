package com.example.domain;

public class Share {
	private Integer parentUserId;
	private Integer sharedUserId;
	private Integer scheduleId;

	public Integer getParentUserId() {
		return parentUserId;
	}

	public void setParentUserId(Integer parentUserId) {
		this.parentUserId = parentUserId;
	}

	public Integer getSharedUserId() {
		return sharedUserId;
	}

	public void setSharedUserId(Integer sharedUserId) {
		this.sharedUserId = sharedUserId;
	}

	public Integer getScheduleId() {
		return scheduleId;
	}

	public void setScheduleId(Integer scheduleId) {
		this.scheduleId = scheduleId;
	}

	@Override
	public String toString() {
		return "share [parentUserId=" + parentUserId + ", sharedUserId=" + sharedUserId + ", scheduleId=" + scheduleId
				+ "]";
	}

}
