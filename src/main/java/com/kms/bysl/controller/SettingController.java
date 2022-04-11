package com.kms.bysl.controller;

import java.net.URI;
import java.net.URISyntaxException;
import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.Enumeration;
import java.util.List;
import java.util.stream.Collectors;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.transaction.interceptor.TransactionAspectSupport;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;

import com.kms.bysl.ResponseData;
import com.kms.bysl.StatusEnum;
import com.kms.bysl.dto.InviteDTO;
import com.kms.bysl.dto.PermissionDTO;
import com.kms.bysl.dto.TeamDTO;
import com.kms.bysl.dto.UserWorkspaceDTO;
import com.kms.bysl.exception.NullObjectException;
import com.kms.bysl.service.InviteService;
import com.kms.bysl.service.PermissionService;
import com.kms.bysl.service.TeamPermissionService;
import com.kms.bysl.service.TeamService;
import com.kms.bysl.service.UserWorkspaceService;

@Controller
@RequestMapping(value="/setting")
public class SettingController {
	
	@Autowired
	private UserWorkspaceService userWorkspaceService;
	
	@Autowired
	private TeamService teamService;
	
	@Autowired
	private TeamPermissionService teamPermissionService;
	
	@Autowired
	private PermissionService permissionService;
	
	@Autowired
	private InviteService inviteService;
	
	@RequestMapping(value="/{workspaceId}", method=RequestMethod.GET)
	public String settingForm(@PathVariable int workspaceId, HttpServletRequest request, HttpSession session) {
		List<UserWorkspaceDTO> userWorkspaces;
		List<TeamDTO> teams;
		List<InviteDTO> invites;
		UserWorkspaceDTO userWorkspace;
		
		userWorkspaces = userWorkspaceService.userWorkspaceSelectByWorkspaceId(workspaceId);
		teams = teamService.teamSelectByWorkspaceId(workspaceId);
		userWorkspace = (UserWorkspaceDTO) session.getAttribute("userWorkspace");
		invites = inviteService.inviteSelectAll(workspaceId);
		
		request.setAttribute("userWorkspaces", userWorkspaces);
		request.setAttribute("teams", teams);
		request.setAttribute("invites", invites);
		return "setting/main";
	}
	
	@RequestMapping(value="/{workspaceId}/addTeam", method=RequestMethod.GET)
	public String addTeamForm(HttpServletRequest request) {
		List<PermissionDTO> permissions = permissionService.permissionSelectAll();
		
		request.setAttribute("permissions", permissions);
		return "setting/addTeam";
	}
	
	@ResponseBody
	@RequestMapping(value="/{workspaceId}/addTeam", method=RequestMethod.POST)
	public ResponseEntity<ResponseData> addTeam(@PathVariable int workspaceId, @RequestParam(value="team_name") String teamName
			, @RequestParam(value="permissions[]", required=false) List<Integer> permissions, HttpServletRequest request) {
		TeamDTO team = new TeamDTO();
		team.setWorkspaceId(workspaceId);
		team.setName(teamName);
		int teamId = teamService.teamInsert(team);
		
		if(permissions != null) {
			teamPermissionService.teamPermissionInsert(teamId, permissions);
		}

		ResponseData responseData = new ResponseData();
		responseData.setMessage("팀 추가가 완료되었습니다.");
		return responseData.getResponseEntity(HttpStatus.OK);
	}
	
	@ResponseBody
	@RequestMapping(value="/{workspaceId}/setTeam", method=RequestMethod.POST)
	public ResponseEntity<ResponseData> setTeam(@PathVariable int workspaceId, @RequestParam(value="user_id") int userId
			, @RequestParam(value="team_id") int teamId, HttpSession session, UriComponentsBuilder uriBuilder) throws RuntimeException{
		userWorkspaceService.userWorkspaceUpdateTeamId(userId, teamId);
		
		ResponseData responseData = new ResponseData();
		responseData.setMessage("팀 변경이 완료되었습니다.");
		return responseData.getResponseEntity(HttpStatus.OK);
	}
	
	@ResponseBody
	@RequestMapping(value="/{workspaceId}/user/{userId}", method=RequestMethod.DELETE)
	public ResponseEntity<ResponseData> deleteUser(@PathVariable int workspaceId, @PathVariable int userId) {
		UserWorkspaceDTO userWorkspace = new UserWorkspaceDTO();
		userWorkspace.setWorkspaceId(workspaceId);
		userWorkspace.setUserId(userId);
		userWorkspaceService.userWorkspaceDelete(userWorkspace);
		
		inviteService.inviteDelete(userId, workspaceId);
		
		ResponseData responseData = new ResponseData();
		responseData.setMessage("사용자 추방이 완료되었습니다.");
		return responseData.getResponseEntity(HttpStatus.OK);
	}
	
	@ResponseBody
	@RequestMapping(value="/{workspaceId}/invite/{inviteId}", method=RequestMethod.DELETE)
	public ResponseEntity<ResponseData> rejectInvite(@PathVariable int inviteId) {
		inviteService.inviteDelete(inviteId);
		
		ResponseData responseData = new ResponseData();
		responseData.setMessage("초대 취소가 완료되었습니다.");
		return responseData.getResponseEntity(HttpStatus.OK);
	}
	
	@RequestMapping(value="/{workspaceId}/team/{teamId}", method=RequestMethod.GET)
	public String updateTeamForm(@PathVariable int workspaceId, @PathVariable int teamId, HttpServletRequest request) {
		TeamDTO team;
		List<PermissionDTO> allPermissions;
		List<PermissionDTO> availablePermissions;
		List<PermissionDTO> chosenPermissions;
		List<Integer> chosenPermissionIds = new ArrayList<Integer>();
		
		team = teamService.teamSelectByTeamId(teamId);
		allPermissions = permissionService.permissionSelectAll();
		chosenPermissions = permissionService.permissionSelectByTeamId(teamId);
		for(PermissionDTO permission : chosenPermissions){
			chosenPermissionIds.add(permission.getId());
		}
		availablePermissions = allPermissions.stream().filter(permission -> chosenPermissionIds.contains(permission.getId()) == false).collect(Collectors.toList());
		
		request.setAttribute("team", team);
		request.setAttribute("chosenPermissions", chosenPermissions);
		request.setAttribute("availablePermissions", availablePermissions);
		
		return "setting/updateTeam";
	}
	
	@ResponseBody
	@RequestMapping(value="/{workspaceId}/team/{teamId}", method=RequestMethod.POST)
	public ResponseEntity<ResponseData> updateTeam(@PathVariable int teamId, @RequestParam(value="team_name") String teamName
			, @RequestParam(value="permissions[]", required=false) List<Integer> permissions) {
		TeamDTO team = new TeamDTO();
		team.setId(teamId);
		team.setName(teamName);
		teamService.teamUpdate(team);
		
		teamPermissionService.teamPermissionDelete(teamId);
		if(permissions != null) {
			teamPermissionService.teamPermissionInsert(teamId, permissions);
		}

		ResponseData responseData = new ResponseData();
		responseData.setMessage("팀 수정이 완료되었습니다.");
		return responseData.getResponseEntity(HttpStatus.OK);
	}
	
	@ResponseBody
	@RequestMapping(value="/{workspaceId}/team/{teamId}", method=RequestMethod.DELETE)
	public ResponseEntity<ResponseData> deleteTeam(@PathVariable int teamId, @PathVariable int workspaceId) {
		TeamDTO guestTeam = teamService.guestTeamSelect(workspaceId);
		userWorkspaceService.userWorkspaceUpdateAllTeamId(teamId, guestTeam.getId());
		teamService.teamDelete(teamId);

		ResponseData responseData = new ResponseData();
		responseData.setMessage("팀 삭제가 완료되었습니다.");
		return responseData.getResponseEntity(HttpStatus.OK);
	}
}
