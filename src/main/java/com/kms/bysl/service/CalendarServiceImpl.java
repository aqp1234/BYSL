package com.kms.bysl.service;

import java.sql.Timestamp;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.kms.bysl.dao.CalendarDAO;
import com.kms.bysl.dto.CalendarDTO;
import com.kms.bysl.dto.MemberDTO;
import com.kms.bysl.dto.UserWorkspaceDTO;
import com.kms.bysl.exception.NullObjectException;

@Service
public class CalendarServiceImpl implements CalendarService{

	@Autowired
	private CalendarDAO dao;
	
	@Override
	public int calendarInsert(CalendarDTO calendar, int ownerUserWorkspaceId) {
		int calendarId;
		
		calendarId = dao.calendarInsert(calendar, ownerUserWorkspaceId);
		
		return calendarId;
	}

	@Override
	public CalendarDTO calendarSelectById(int id) {
		List<CalendarDTO> calendars = dao.calendarSelectById(id);
		if(calendars.size() == 0) {
			throw new NullObjectException(new Exception(), "없거나 삭제된 캘린더 입니다.");
		}
		return calendars.get(0);
	}

	@Override
	public List<CalendarDTO> calendarSelectByMonth(int year, int month, int workspaceId) {
		List<CalendarDTO> calendars = dao.calendarSelectByMonth(year, month, workspaceId);
		return calendars;
	}

	@Override
	public List<CalendarDTO> calendarSelectByMonth(int year, int month) {
		List<CalendarDTO> calendars = dao.calendarSelectByMonth(year, month);
		return calendars;
	}

	@Override
	public List<CalendarDTO> calendarSelectByDate(int year, int month, int date, int workspaceId) {
		List<CalendarDTO> calendars = dao.calendarSelectByDate(year, month, date, workspaceId);
		return calendars;
	}

	@Override
	public List<CalendarDTO> calendarSelectByDate(int year, int month, int date) {
		List<CalendarDTO> calendars = dao.calendarSelectByDate(year, month, date);
		return calendars;
	}

	@Override
	public void calendarUpdate(CalendarDTO calendar) {
		dao.calendarUpdate(calendar);
	}

	@Override
	public void calendarDelete(CalendarDTO calendar) {
		dao.calendarDelete(calendar);
	}

}
