package com.kms.bysl.controller;

import java.sql.Timestamp;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.kms.bysl.ResponseData;
import com.kms.bysl.dto.CalendarDTO;
import com.kms.bysl.dto.UserWorkspaceDTO;
import com.kms.bysl.service.CalendarService;

@Controller
@RequestMapping(value="/calendar")
public class CalendarController {
	
	@Autowired
	private CalendarService calendarService;
	
	@RequestMapping(value="/{workspaceId}", method=RequestMethod.GET)
	public String calendarMain() {
		return "calendar/main";
	}
	
	@RequestMapping(value="/{workspaceId}/add", method=RequestMethod.GET)
	public String calendarAddForm() {
		return "calendar/add";
	}
	
	@ResponseBody
	@RequestMapping(value="/{workspaceId}/add", method=RequestMethod.POST)
	public ResponseEntity<ResponseData> calendarAdd(@PathVariable int workspaceId, CalendarDTO calendar, HttpSession session, HttpServletRequest request){
		UserWorkspaceDTO userWorkspace;
		Timestamp start_date = null;
		Timestamp end_date = null;
		int calendarId;
		
		DateFormat transFormat = new SimpleDateFormat("yyyy-MM-dd");
		try {
			start_date = new Timestamp(transFormat.parse(request.getParameter("start_date")).getTime());
			end_date = new Timestamp(transFormat.parse(request.getParameter("end_date")).getTime());
		}catch(Exception e) {
			e.printStackTrace();
		}
		
		calendar.setWorkspaceId(workspaceId);
		calendar.setStartDate(start_date);
		calendar.setEndDate(end_date);

		userWorkspace = (UserWorkspaceDTO) session.getAttribute("userWorkspace");
		calendarId = calendarService.calendarInsert(calendar, userWorkspace.getId());
		
		calendar = calendarService.calendarSelectById(calendarId);
		
		ResponseData responseData = new ResponseData();
		responseData.setMessage("캘린더 추가가 완료되었습니다.");
		responseData.setData(calendar);
		return responseData.get201ResponseEntity("/bysl/calendar/" + workspaceId + "/" + calendarId, "/bysl/calendar/" + workspaceId);
	}
	
	@RequestMapping(value="/{workspaceId}/update/{calendarId}", method=RequestMethod.GET)
	public String calendarUpdateForm(@PathVariable int calendarId, HttpServletRequest request) {
		CalendarDTO calendar = calendarService.calendarSelectById(calendarId);
		request.setAttribute("calendar", calendar);
		return "calendar/update";
	}
	
	@ResponseBody
	@RequestMapping(value="/{workspaceId}/update/{calendarId}", method=RequestMethod.POST)
	public ResponseEntity<ResponseData> calendarUpdate(CalendarDTO calendar, HttpServletRequest request) {
		Timestamp start_date = null;
		Timestamp end_date = null;
		
		String start_date_str = request.getParameter("start_date");
		String end_date_str = request.getParameter("end_date");
		
		DateFormat transFormat = new SimpleDateFormat("yyyy-MM-dd");
		
		try {
			start_date = new Timestamp(transFormat.parse(start_date_str).getTime());
			end_date = new Timestamp(transFormat.parse(end_date_str).getTime());
		}catch(Exception e) {
			e.printStackTrace();
		}
		
		calendar.setStartDate(start_date);
		calendar.setEndDate(end_date);
		calendarService.calendarUpdate(calendar);

		ResponseData responseData = new ResponseData();
		responseData.setMessage("캘린더 수정이 완료되었습니다.");
		return responseData.getResponseEntity(HttpStatus.OK);
	}
	
	@ResponseBody
	@RequestMapping(value="/{workspaceId}/delete/{calendarId}", method=RequestMethod.POST)
	public ResponseEntity<ResponseData> calendarDeleteForm(@PathVariable int calendarId, CalendarDTO calendar) {
		calendar.setId(calendarId);
		calendarService.calendarDelete(calendar);

		ResponseData responseData = new ResponseData();
		responseData.setMessage("캘린더 삭제가 완료되었습니다.");
		return responseData.getResponseEntity(HttpStatus.OK);
	}
	
	@ResponseBody
	@RequestMapping(value="/{workspaceId}/calendars", method=RequestMethod.GET)
	public ResponseEntity<ResponseData> getCalendarsByMonth(@PathVariable int workspaceId, @RequestParam int year, @RequestParam int month) {
		List<CalendarDTO> calendars = calendarService.calendarSelectByMonth(year, month, workspaceId);

		ResponseData responseData = new ResponseData();
		responseData.setData(calendars);
		return responseData.getResponseEntity(HttpStatus.OK);
	}
	
	@ResponseBody
	@RequestMapping(value="/{workspaceId}/calendar_by_id", method=RequestMethod.GET)
	public ResponseEntity<ResponseData> getCalendarById(@PathVariable int workspaceId, @RequestParam int id) {
		CalendarDTO calendar = calendarService.calendarSelectById(id);

		ResponseData responseData = new ResponseData();
		responseData.setData(calendar);
		return responseData.getResponseEntity(HttpStatus.OK);
	}
	
	@ResponseBody
	@RequestMapping(value="/{workspaceId}/calendar", method=RequestMethod.GET)
	public ResponseEntity<ResponseData> getCalendarByDate(@PathVariable int workspaceId, @RequestParam int year, @RequestParam int month, @RequestParam int date){
		List<CalendarDTO> calendars = calendarService.calendarSelectByDate(year, month, date, workspaceId);

		ResponseData responseData = new ResponseData();
		responseData.setData(calendars);
		return responseData.getResponseEntity(HttpStatus.OK);
	}
}
