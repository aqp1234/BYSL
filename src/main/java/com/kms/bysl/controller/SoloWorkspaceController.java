package com.kms.bysl.controller;

import java.sql.Timestamp;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
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
import com.kms.bysl.dto.MemberDTO;
import com.kms.bysl.dto.SoloCalendarDTO;
import com.kms.bysl.dto.SoloWorkspaceDTO;
import com.kms.bysl.service.SoloCalendarService;
import com.kms.bysl.service.SoloWorkspaceService;

@Controller
@RequestMapping(value="/solo/workspace")
public class SoloWorkspaceController {
	
	@Autowired
	private SoloWorkspaceService soloWorkspaceService;
	
	@Autowired
	private SoloCalendarService soloCalendarService;
	
	@ResponseBody
	@RequestMapping(value="/add", method=RequestMethod.POST)
	public ResponseEntity<ResponseData> addSoloWorkspace(SoloWorkspaceDTO soloWorkspace, HttpSession session) {
		MemberDTO member = (MemberDTO) session.getAttribute("member");
		
		soloWorkspace.setOwnerId(member.getId());
		soloWorkspaceService.soloWorkspaceInsert(soloWorkspace);

		ResponseData responseData = new ResponseData();
		responseData.setMessage("개인 워크스페이스 추가가 완료되었습니다.");
		return responseData.getResponseEntity(HttpStatus.SEE_OTHER, "/bysl");
	}
	
	@RequestMapping(value="/{soloWorkspaceId}", method=RequestMethod.GET)
	public String mainSoloWorkspace(@PathVariable int soloWorkspaceId, HttpSession session) {
		
		SoloWorkspaceDTO soloWorkspace = soloWorkspaceService.soloWorkspaceSelectById(soloWorkspaceId);
		
		session.setAttribute("soloWorkspace", soloWorkspace);
		
		return "soloCalendar/main";
	}
	
	@RequestMapping(value="/{soloWorkspaceId}/add", method=RequestMethod.GET)
	public String addSoloWorkspaceForm() {
		return "soloCalendar/add";
	}

	@ResponseBody
	@RequestMapping(value="/{soloWorkspaceId}/add", method=RequestMethod.POST)
	public ResponseEntity<ResponseData> addSoloWorkspace(HttpSession session, @PathVariable int soloWorkspaceId, SoloCalendarDTO soloCalendar, HttpServletRequest request){
		MemberDTO member = (MemberDTO) session.getAttribute("member");
		
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
		
		soloCalendar.setSoloWorkspaceId(soloWorkspaceId);
		soloCalendar.setOwnerId(member.getId());
		soloCalendar.setStartDate(start_date);
		soloCalendar.setEndDate(end_date);
		int soloCalendarId = soloCalendarService.soloCalendarInsert(soloCalendar);
		
		soloCalendar = soloCalendarService.soloCalendarSelectById(soloCalendarId);
		
		ResponseData responseData = new ResponseData();
		responseData.setMessage("일정 추가가 완료되었습니다.");
		responseData.setData(soloCalendar);
		return responseData.get201ResponseEntity("/bysl/soloCalendar/" + soloWorkspaceId + "/" + soloCalendarId);
	}
	
	@RequestMapping(value="/{soloWorkspaceId}/update/{soloCalendarId}", method=RequestMethod.GET)
	public String updateSoloCalendarForm(HttpServletRequest request, @PathVariable int soloCalendarId) {
		SoloCalendarDTO soloCalendar = soloCalendarService.soloCalendarSelectById(soloCalendarId);
		
		request.setAttribute("soloCalendar", soloCalendar);
		
		return "soloCalendar/update";
	}

	@ResponseBody
	@RequestMapping(value="/{soloWorkspaceId}/update/{soloCalendarId}", method=RequestMethod.POST)
	public ResponseEntity<ResponseData> updateSoloCalendar(@PathVariable int soloCalendarId, SoloCalendarDTO soloCalendar, HttpServletRequest request){
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

		soloCalendar.setStartDate(start_date);
		soloCalendar.setEndDate(end_date);
		soloCalendar.setId(soloCalendarId);
		soloCalendarService.soloCalendarUpdate(soloCalendar);
		
		ResponseData responseData = new ResponseData();
		responseData.setMessage("일정 수정이 완료되었습니다.");
		return responseData.getResponseEntity(HttpStatus.OK);
	}
	
	@ResponseBody
	@RequestMapping(value="/{soloWorkspaceId}/calendars")
	public ResponseEntity<ResponseData> getSoloCalendars(HttpSession session, @PathVariable int soloWorkspaceId, @RequestParam int year, @RequestParam int month){
		List<SoloCalendarDTO> soloCalendars = soloCalendarService.soloCalendarSelectByMonth(year, month);
		
		ResponseData responseData = new ResponseData();
		responseData.setMessage(year + "년 " + month + " 월 일정 정보");
		responseData.setData(soloCalendars);
		return responseData.getResponseEntity(HttpStatus.OK);
	}
	
	@ResponseBody
	@RequestMapping(value="/{soloWorkspaceId}/my_calendar_by_id")
	public ResponseEntity<ResponseData> getSoloCalendarById(@RequestParam int id){
		SoloCalendarDTO soloCalendar = soloCalendarService.soloCalendarSelectById(id);

		ResponseData responseData = new ResponseData();
		responseData.setMessage(id + " 일정 정보");
		responseData.setData(soloCalendar);
		return responseData.getResponseEntity(HttpStatus.OK);
	}
	
	@ResponseBody
	@RequestMapping(value="/{soloWorkspaceId}/my_calendar")
	public ResponseEntity<ResponseData> getSoloCalendarByDate(@RequestParam int year, @RequestParam int month, @RequestParam int date){
		List<SoloCalendarDTO> soloCalendars = soloCalendarService.soloCalendarSelectByDate(year, month, date);

		ResponseData responseData = new ResponseData();
		responseData.setMessage(year + "년 " + month + "월 " + date + "일 일정 정보");
		responseData.setData(soloCalendars);
		return responseData.getResponseEntity(HttpStatus.OK);
	}
	
	@ResponseBody
	@RequestMapping(value="/{soloWorkspaceId}/{soloCalendarId}", method=RequestMethod.DELETE)
	public ResponseEntity<ResponseData> deleteSoloCalendar(@PathVariable int soloCalendarId){
		soloCalendarService.soloCalendarDelete(soloCalendarId);
		
		ResponseData responseData = new ResponseData();
		responseData.setMessage("일정 삭제가 완료되었습니다.");
		return responseData.getResponseEntity(HttpStatus.OK);
	}
}
