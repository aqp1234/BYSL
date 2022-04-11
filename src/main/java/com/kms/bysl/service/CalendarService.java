package com.kms.bysl.service;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import com.kms.bysl.dto.CalendarDTO;

public interface CalendarService {
	public int calendarInsert(CalendarDTO calendar, int ownerUserWorkspaceId);
	public CalendarDTO calendarSelectById(int id);
	public List<CalendarDTO> calendarSelectByMonth(int year, int month, int workspaceId);
	public List<CalendarDTO> calendarSelectByMonth(int year, int month);
	public List<CalendarDTO> calendarSelectByDate(int year, int month, int date, int workspaceId);
	public List<CalendarDTO> calendarSelectByDate(int year, int month, int date);
	public void calendarUpdate(CalendarDTO calendar);
	public void calendarDelete(CalendarDTO calendar);
}
