package com.kms.bysl.dto;

public class ScheduleDTO {
	private String id;
	private CalendarDTO schedule;
	
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public CalendarDTO getSchedule() {
		return schedule;
	}
	public void setSchedule(CalendarDTO schedule) {
		this.schedule = schedule;
	}
}
