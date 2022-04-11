package com.kms.bysl.controller;

import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpSession;

import org.json.simple.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.client.RestTemplate;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.kms.bysl.ResponseData;
import com.kms.bysl.dto.CalendarDTO;
import com.kms.bysl.dto.MemberDTO;
import com.kms.bysl.dto.ScheduleDTO;
import com.kms.bysl.dto.WorkspaceDTO;
import com.kms.bysl.properties.applicationProperties;
import com.kms.bysl.service.CalendarService;
import com.kms.bysl.service.WorkspaceService;

@Controller
@RequestMapping(value="")
public class HomeController {
	
	@Autowired
	private applicationProperties properties;
	
	@Autowired
	private CalendarService calendarService;
	
	@Autowired
	private WorkspaceService workspaceService;
	
	@RequestMapping(value="")
	public String Home() {
		return "home";
	}
	
	@RequestMapping(value="/error")
	public String Error() {
		return "error";
	}
	
	@ResponseBody
	@RequestMapping(value="/schedules")
	public ResponseEntity<ResponseData> getSchedules(HttpSession session, @RequestParam String year, @RequestParam String month) throws IOException{
		MemberDTO member = (MemberDTO) session.getAttribute("member");
		
		final String url = "https://open.neis.go.kr/hub/SchoolSchedule?KEY=" + properties.getNICEKEY() + 
		        "&Type=json&ATPT_OFCDC_SC_CODE=" + member.getLocationCode() + 
		        "&pindex=1&pSize=100" + 
		        "&SD_SCHUL_CODE=" + member.getSchoolCode() + 
		        "&AA_YMD=" + year + month;

		RestTemplate restTemplate = new RestTemplate();
		ResponseEntity<String> resEntity = restTemplate.exchange(url, HttpMethod.GET, new HttpEntity<String>("", new HttpHeaders()), String.class);
		
		ObjectMapper mapper = new ObjectMapper();
		JsonNode data = mapper.readTree(resEntity.getBody());
		
		ResponseData responseData = new ResponseData();
		responseData.setMessage(year + "년 " + month + "월 스케줄 정보");
		responseData.setData(data);
		return responseData.getResponseEntity(HttpStatus.OK);
	}
	
	@ResponseBody
	@RequestMapping(value="/schedule")
	public ResponseEntity<ResponseData> getSchedule(HttpSession session, @RequestParam String YMD) throws IOException{
		MemberDTO member = (MemberDTO) session.getAttribute("member");
		
		final String url = "https://open.neis.go.kr/hub/SchoolSchedule?KEY=" + properties.getNICEKEY() + 
	            "&Type=json&ATPT_OFCDC_SC_CODE=" + member.getLocationCode() + 
	            "&pindex=1&pSize=100" + 
	            "&SD_SCHUL_CODE=" + member.getSchoolCode() + 
	            "&AA_YMD=" + YMD;
		
		RestTemplate restTemplate = new RestTemplate();
		ResponseEntity<String> resEntity = restTemplate.exchange(url, HttpMethod.GET, new HttpEntity<String>("", new HttpHeaders()), String.class);
		
		ObjectMapper mapper = new ObjectMapper();
		JsonNode data = mapper.readTree(resEntity.getBody());
		
		ResponseData responseData = new ResponseData();
		responseData.setMessage(YMD + " 스케줄 정보");
		responseData.setData(data);
		return responseData.getResponseEntity(HttpStatus.OK);
	}
	
	@ResponseBody
	@RequestMapping(value="/lunch_meals")
	public ResponseEntity<ResponseData> getLunchMeals(HttpSession session, @RequestParam String year, @RequestParam String month) throws IOException{
		MemberDTO member = (MemberDTO) session.getAttribute("member");
		
		final String url = "https://open.neis.go.kr/hub/mealServiceDietInfo?KEY=" + properties.getNICEKEY() + 
                "&Type=json&ATPT_OFCDC_SC_CODE=" + member.getLocationCode() + 
                "&pindex=1&pSize=100&MMEAL_SC_CODE=2" + 
                "&SD_SCHUL_CODE=" + member.getSchoolCode() + 
                "&MLSV_YMD=" + year + month;
		
		RestTemplate restTemplate = new RestTemplate();
		ResponseEntity<String> resEntity = restTemplate.exchange(url, HttpMethod.GET, new HttpEntity<String>("", new HttpHeaders()), String.class);
		
		ObjectMapper mapper = new ObjectMapper();
		JsonNode data = mapper.readTree(resEntity.getBody());
		
		ResponseData responseData = new ResponseData();
		responseData.setMessage(year + "년 " + month + "월 급식 정보");
		responseData.setData(data);
		return responseData.getResponseEntity(HttpStatus.OK);
	}
	
	@ResponseBody
	@RequestMapping(value="/lunch_meal")
	public ResponseEntity<ResponseData> getLunchMeal(HttpSession session, @RequestParam String YMD) throws IOException{
		MemberDTO member = (MemberDTO) session.getAttribute("member");
		
		final String url = "https://open.neis.go.kr/hub/mealServiceDietInfo?KEY=" + properties.getNICEKEY() + 
	            "&Type=json&ATPT_OFCDC_SC_CODE=" + member.getLocationCode() + 
	            "&pindex=1&pSize=100&MMEAL_SC_CODE=2" + 
	            "&SD_SCHUL_CODE=" + member.getSchoolCode() + 
	            "&MLSV_YMD=" + YMD;
		
		RestTemplate restTemplate = new RestTemplate();
		ResponseEntity<String> resEntity = restTemplate.exchange(url, HttpMethod.GET, new HttpEntity<String>("", new HttpHeaders()), String.class);
		
		ObjectMapper mapper = new ObjectMapper();
		JsonNode data = mapper.readTree(resEntity.getBody());
		
		ResponseData responseData = new ResponseData();
		responseData.setMessage(YMD + " 급식 정보");
		responseData.setData(data);
		return responseData.getResponseEntity(HttpStatus.OK);
	}
	
	@ResponseBody
	@RequestMapping(value="/my_schedules")
	public ResponseEntity<ResponseData> getMySchedules(@RequestParam int year, @RequestParam int month, HttpSession session){
		List<CalendarDTO> calendars = calendarService.calendarSelectByMonth(year, month);
		List<ScheduleDTO> schedules = new ArrayList<ScheduleDTO>();
		for(CalendarDTO calendar : calendars) {
			DateFormat transFormat = new SimpleDateFormat("YYMMdd");
			String id = transFormat.format(calendar.getStartDate());
			
			ScheduleDTO schedule = new ScheduleDTO();
			schedule.setId(id);
			schedule.setSchedule(calendar);
			schedules.add(schedule);
		}
		
		ResponseData responseData = new ResponseData();
		responseData.setMessage(year + "년 " + month + "월 스케줄 정보");
		responseData.setData(schedules);
		return responseData.getResponseEntity(HttpStatus.OK);
	}
	
	@ResponseBody
	@RequestMapping(value="/my_schedule")
	public ResponseEntity<ResponseData> getMySchedule(@RequestParam String YMD){
		int year = Integer.parseInt(YMD.substring(0, 4));
		int month = Integer.parseInt(YMD.substring(4, 6));
		int date = Integer.parseInt(YMD.substring(6, 8));
		
		List<CalendarDTO> calendars = calendarService.calendarSelectByDate(year, month, date);
		List<ScheduleDTO> schedules = new ArrayList<ScheduleDTO>();
		for(CalendarDTO calendar : calendars) {
			DateFormat transFormat = new SimpleDateFormat("YYMMdd");
			String id = transFormat.format(calendar.getStartDate());
			
			ScheduleDTO schedule = new ScheduleDTO();
			schedule.setId(id);
			schedule.setSchedule(calendar);
			schedules.add(schedule);
		}
		
		ResponseData responseData = new ResponseData();
		responseData.setMessage(year + "년 " + month + "월 " + date + "일 스케줄 정보");
		responseData.setData(schedules);
		return responseData.getResponseEntity(HttpStatus.OK);
	}

	@ResponseBody
	@RequestMapping(value="/my_schedule_by_id")
	public ResponseEntity<ResponseData> getMyScheduleById(@RequestParam int id){
		CalendarDTO calendar = calendarService.calendarSelectById(id);
		WorkspaceDTO workspace = workspaceService.workspaceSelectById(calendar.getWorkspaceId());
		
		JSONObject jsonObject = new JSONObject();
		jsonObject.put("schedule", calendar);
		jsonObject.put("workspace", workspace);
		
		ResponseData responseData = new ResponseData();
		responseData.setMessage(id + " 스케줄 정보");
		responseData.setData(jsonObject);
		return responseData.getResponseEntity(HttpStatus.OK);
	}
}
