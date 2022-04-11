package com.kms.bysl.controller;

import java.sql.Timestamp;
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
import org.springframework.web.bind.annotation.ResponseBody;

import com.kms.bysl.ResponseData;
import com.kms.bysl.dto.DashboardDTO;
import com.kms.bysl.dto.MemberDTO;
import com.kms.bysl.dto.UserWorkspaceDTO;
import com.kms.bysl.service.DashboardService;
import com.kms.bysl.service.UserWorkspaceService;

@Controller
@RequestMapping(value="/dashboard")
public class DashboardController {
	
	@Autowired
	private DashboardService dashboardService;
	
	@Autowired
	private UserWorkspaceService userWorkspaceService;
	
	@RequestMapping(value="/{workspaceId}", method=RequestMethod.GET)
	public String dashboardList(@PathVariable int workspaceId, HttpServletRequest request) {
		List<DashboardDTO> dashboards;
		
		dashboards = dashboardService.dashboardSelectByWorkspaceId(workspaceId);
		
		request.setAttribute("dashboards", dashboards);
		
		return "dashboard/main";
	}
	
	@RequestMapping(value="/{workspaceId}/add", method=RequestMethod.GET)
	public String dashboardAddForm(@PathVariable int workspaceId, HttpServletRequest request) {
		List<UserWorkspaceDTO> userWorkspaces;
		
		userWorkspaces = userWorkspaceService.userWorkspaceSelectByWorkspaceId(workspaceId);
		
		request.setAttribute("userWorkspaces", userWorkspaces);
		return "dashboard/add";
	}
	
	@ResponseBody
	@RequestMapping(value="/{workspaceId}/add", method=RequestMethod.POST)
	public ResponseEntity<ResponseData> dashboardAdd(@PathVariable int workspaceId, DashboardDTO dashboard, HttpSession session, HttpServletRequest request) {
		UserWorkspaceDTO userWorkspace;
		UserWorkspaceDTO managerUserWorkspace;
		UserWorkspaceDTO exUserWorkspace;
		Date start_date = null;
		Date end_date = null;
		
		String start_date_str = request.getParameter("start_date");
		String end_date_str = request.getParameter("end_date");
		
		SimpleDateFormat transFormat = new SimpleDateFormat("yyyy-MM-dd");
		
		try {
			start_date = transFormat.parse(start_date_str);
			end_date = transFormat.parse(end_date_str);
		}catch(Exception e) {
			e.printStackTrace();
		}
		
		userWorkspace = (UserWorkspaceDTO) session.getAttribute("userWorkspace");
		exUserWorkspace = new UserWorkspaceDTO();
		exUserWorkspace.setUserId(dashboard.getManagerId());
		exUserWorkspace.setWorkspaceId(workspaceId);
		managerUserWorkspace = userWorkspaceService.userWorkspaceSelect(exUserWorkspace);
		
		dashboard.setWorkspaceId(workspaceId);
		dashboard.setStartDate(new Timestamp(start_date.getTime()));
		dashboard.setEndDate(new Timestamp(end_date.getTime()));
		
		dashboardService.dashboardInsert(dashboard, userWorkspace.getId(), managerUserWorkspace.getId());

		ResponseData responseData = new ResponseData();
		responseData.setMessage("대시보드 추가가 완료되었습니다.");
		return responseData.getResponseEntity(HttpStatus.OK);
	}
	
	@RequestMapping(value="/{workspaceId}/{dashboardId}", method=RequestMethod.GET)
	public String dashboardDetail(@PathVariable int workspaceId, @PathVariable int dashboardId, HttpServletRequest request) {
		DashboardDTO dashboard;
		
		dashboard = dashboardService.dashboardSelectByDashboardId(dashboardId);
		
		request.setAttribute("dashboard", dashboard);
		return "dashboard/detail";
	}
	
	@ResponseBody
	@RequestMapping(value="/{workspaceId}/{dashboardId}", method=RequestMethod.DELETE)
	public ResponseEntity<ResponseData> dashboardDelete(@PathVariable int dashboardId) {
		
		dashboardService.dashboardDelete(dashboardId);

		ResponseData responseData = new ResponseData();
		responseData.setMessage("대시보드 삭제가 완료되었습니다.");
		return responseData.getResponseEntity(HttpStatus.OK);
	}
	
	@RequestMapping(value="/{workspaceId}/update/{dashboardId}", method=RequestMethod.GET)
	public String dashboardUpdateForm(@PathVariable int workspaceId, @PathVariable int dashboardId, HttpServletRequest request) {
		DashboardDTO dashboard;
		List<UserWorkspaceDTO> userWorkspaces;
		
		dashboard = dashboardService.dashboardSelectByDashboardId(dashboardId);
		userWorkspaces = userWorkspaceService.userWorkspaceSelectByWorkspaceId(workspaceId);
		
		request.setAttribute("dashboard", dashboard);
		request.setAttribute("userWorkspaces", userWorkspaces);
		
		return "dashboard/update";
	}
	
	@ResponseBody
	@RequestMapping(value="/{workspaceId}/update/{dashboardId}", method=RequestMethod.POST)
	public ResponseEntity<ResponseData> dashboardUpdate(@PathVariable int workspaceId, @PathVariable int dashboardId, DashboardDTO dashboard, HttpSession session, HttpServletRequest request){
		UserWorkspaceDTO managerUserWorkspace;
		UserWorkspaceDTO exUserWorkspace;
		Date start_date = null;
		Date end_date = null;
		
		String start_date_str = request.getParameter("start_date");
		String end_date_str = request.getParameter("end_date");
		
		SimpleDateFormat transFormat = new SimpleDateFormat("yyyy-MM-dd");
		
		try {
			start_date = transFormat.parse(start_date_str);
			end_date = transFormat.parse(end_date_str);
		}catch(Exception e) {
			e.printStackTrace();
		}
		
		exUserWorkspace = new UserWorkspaceDTO();
		exUserWorkspace.setUserId(dashboard.getManagerId());
		exUserWorkspace.setWorkspaceId(workspaceId);
		managerUserWorkspace = userWorkspaceService.userWorkspaceSelect(exUserWorkspace);
		
		dashboard.setId(dashboardId);
		dashboard.setStartDate(new Timestamp(start_date.getTime()));
		dashboard.setEndDate(new Timestamp(end_date.getTime()));
		
		dashboardService.dashboardUpdate(dashboard, managerUserWorkspace.getId());
		ResponseData responseData = new ResponseData();
		responseData.setMessage("대시보드 수정이 완료되었습니다.");
		return responseData.getResponseEntity(HttpStatus.OK);
	}
}
