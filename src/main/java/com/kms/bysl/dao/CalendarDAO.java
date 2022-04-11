package com.kms.bysl.dao;

import java.util.Date;
import java.util.List;

import com.kms.bysl.dto.CalendarDTO;

public interface CalendarDAO {
	public int calendarInsert(CalendarDTO calendar, int ownerUserWorkspaceId);
	public List<CalendarDTO> calendarSelectById(int calendarId);
	public List<CalendarDTO> calendarSelectByWorkspaceId(int workspaceId);
	public List<CalendarDTO> calendarSelectByMonth(int year, int month, int workspaceId);
	public List<CalendarDTO> calendarSelectByMonth(int year, int month);
	public List<CalendarDTO> calendarSelectByDate(int year, int month, int date, int workspaceId);
	public List<CalendarDTO> calendarSelectByDate(int year, int month, int date);
	public void calendarUpdate(CalendarDTO calendar);
	public void calendarDelete(CalendarDTO calendar);
}
