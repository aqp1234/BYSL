package com.kms.bysl.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.kms.bysl.dao.SoloCalendarDAO;
import com.kms.bysl.dto.SoloCalendarDTO;
import com.kms.bysl.exception.NullObjectException;

@Service
public class SoloCalendarServiceImpl implements SoloCalendarService{
	
	@Autowired
	private SoloCalendarDAO dao;

	@Override
	public int soloCalendarInsert(SoloCalendarDTO soloCalendar) {
		int soloCalendarId = dao.soloCalendarInsert(soloCalendar);
		return soloCalendarId;
	}

	@Override
	public SoloCalendarDTO soloCalendarSelectById(int soloCalendarId) {
		List<SoloCalendarDTO> soloCalendars = dao.soloCalendarSelectById(soloCalendarId);
		if(soloCalendars.size() == 0) {
			throw new NullObjectException(new Exception(), "없거나 삭제된 일정입니다.");
		}
		return soloCalendars.get(0);
	}

	@Override
	public List<SoloCalendarDTO> soloCalendarSelectBySoloWorkspaceId(int soloWorkspaceId) {
		List<SoloCalendarDTO> soloCalendars = dao.soloCalendarSelectBySoloWorkspaceId(soloWorkspaceId);
		return soloCalendars;
	}

	@Override
	public List<SoloCalendarDTO> soloCalendarSelectByMonth(int year, int month) {
		List<SoloCalendarDTO> soloCalendars = dao.soloCalendarSelectByMonth(year, month);
		return soloCalendars;
	}

	@Override
	public List<SoloCalendarDTO> soloCalendarSelectByDate(int year, int month, int date) {
		List<SoloCalendarDTO> soloCalendars = dao.soloCalendarSelectByDate(year, month, date);
		return soloCalendars;
	}

	@Override
	public void soloCalendarUpdate(SoloCalendarDTO soloCalendar) {
		dao.soloCalendarUpdate(soloCalendar);
	}

	@Override
	public void soloCalendarDelete(int soloCalendarId) {
		dao.soloCalendarDelete(soloCalendarId);
	}
	
}
