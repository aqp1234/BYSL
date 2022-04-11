package com.kms.bysl.dao;

import java.util.List;

import com.kms.bysl.dto.SoloCalendarDTO;

public interface SoloCalendarDAO {
	public int soloCalendarInsert(SoloCalendarDTO soloCalendar);
	public List<SoloCalendarDTO> soloCalendarSelectById(int soloCalendarId);
	public List<SoloCalendarDTO> soloCalendarSelectBySoloWorkspaceId(int soloWorkspaceId);
	public List<SoloCalendarDTO> soloCalendarSelectByMonth(int year, int month);
	public List<SoloCalendarDTO> soloCalendarSelectByDate(int year, int month, int date);
	public void soloCalendarUpdate(SoloCalendarDTO soloCalendar);
	public void soloCalendarDelete(int soloCalendarId);
}
