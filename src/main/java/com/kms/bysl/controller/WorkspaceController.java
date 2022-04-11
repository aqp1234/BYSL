package com.kms.bysl.controller;

import javax.mail.internet.MimeMessage;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.mail.javamail.JavaMailSenderImpl;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.security.crypto.bcrypt.BCrypt;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.kms.bysl.ResponseData;
import com.kms.bysl.StatusEnum;
import com.kms.bysl.dto.InviteDTO;
import com.kms.bysl.dto.MemberDTO;
import com.kms.bysl.dto.TeamDTO;
import com.kms.bysl.dto.UserWorkspaceDTO;
import com.kms.bysl.dto.WorkspaceDTO;
import com.kms.bysl.service.InviteService;
import com.kms.bysl.service.TeamPermissionService;
import com.kms.bysl.service.TeamService;
import com.kms.bysl.service.UserWorkspaceService;
import com.kms.bysl.service.WorkspaceService;

@Controller
@RequestMapping(value="/workspace")
public class WorkspaceController {
	
	@Autowired
	private WorkspaceService workspaceService;
	
	@Autowired
	private InviteService inviteService;
	
	@Autowired
	private JavaMailSenderImpl mailSender;
	
	@Autowired
	private UserWorkspaceService userWorkspaceService;
	
	@Autowired
	private TeamService teamService;
	
	@Autowired
	private TeamPermissionService teamPermissionService;
	
	@RequestMapping(value="/add", method=RequestMethod.GET)
	public String addWorkspaceForm() {
		return "workspace/add";
	}
	
	@RequestMapping(value="/add", method=RequestMethod.POST)
	public ResponseEntity<ResponseData> addWorkspace(WorkspaceDTO workspace, UserWorkspaceDTO userWorkspace, HttpSession session) {
		MemberDTO member = (MemberDTO) session.getAttribute("member");
		int workspaceId;
		
		workspace.setOwnerId(member.getId());
		workspaceId = workspaceService.workspaceInsert(workspace);
		
		TeamDTO team = new TeamDTO();
		team.setWorkspaceId(workspaceId);
		team.setName("관리자");
		int adminTeamId = teamService.adminTeamInsert(team);
		
		team.setName("게스트");
		teamService.guestTeamInsert(team);
		
		teamPermissionService.adminTeamPermissionInsert(adminTeamId);
		
		userWorkspace.setWorkspaceId(workspaceId);
		userWorkspace.setUserId(member.getId());
		userWorkspace.setTeamId(adminTeamId);
		userWorkspaceService.userWorkspaceInsert(userWorkspace);

		ResponseData responseData = new ResponseData();
		responseData.setMessage("워크스페이스 추가가 완료되었습니다.");
		return responseData.getResponseEntity(HttpStatus.OK, "/bysl/");
	}
	
	@ResponseBody
	@RequestMapping(value="/{workspaceId}/send_invite_mail", method=RequestMethod.POST)
	public ResponseEntity<ResponseData> sendInviteEmail(@PathVariable int workspaceId, String email, HttpServletRequest request, HttpSession session) {
		UserWorkspaceDTO userWorkspace = (UserWorkspaceDTO) session.getAttribute("userWorkspace");
		MemberDTO member = (MemberDTO) session.getAttribute("member");
		int randomNum = (int) Math.floor(Math.random() * 1000000);
		String key = BCrypt.hashpw(Integer.toString(randomNum), BCrypt.gensalt());
		String url = "http://localhost:8090/bysl/workspace/" +workspaceId + "/invite?key=" + key;
		
		String setFrom = "aqp0222@naver.com";
		String toMail = email;
		String title = "[BYSL] BYSL 초대 링크입니다.";
		String content = "초대를 받으시려면 옆의 링크를 클릭해주세요.<a href=" + url + ">초대받기</a>";
		
		try {
			System.setProperty("https.protocols", "TLSv1,TLSv1.1,TLSv1.2");
			MimeMessage message = mailSender.createMimeMessage();
			MimeMessageHelper helper = new MimeMessageHelper(message, true, "utf-8");
			helper.setFrom(setFrom);
			helper.setTo(toMail);
			helper.setSubject(title);
			helper.setText(content, true);
			mailSender.send(message);
		}catch(Exception e) {
			e.printStackTrace();
			ResponseData responseData = new ResponseData();
			responseData.setMessage("이메일 전송에 실패했습니다.");
			responseData.setStatus(StatusEnum.BAD_REQUEST);
			return responseData.getResponseEntity(HttpStatus.BAD_REQUEST);
		}
		
		InviteDTO invite = new InviteDTO();
		invite.setEmail(email);
		invite.setInviteKey(key);
		invite.setWorkspaceId(workspaceId);
		inviteService.inviteInsert(invite, userWorkspace.getId());

		ResponseData responseData = new ResponseData();
		responseData.setMessage("이메일 전송이 완료되었습니다.");
		return responseData.getResponseEntity(HttpStatus.OK);
	}
	
	@RequestMapping(value="/{workspaceId}/invite", method=RequestMethod.GET)
	public String inviteWorkspaceForm(@PathVariable int workspaceId, HttpSession session, @RequestParam String key, HttpServletRequest request) {
		MemberDTO member = (MemberDTO) session.getAttribute("member");
		InviteDTO invite = new InviteDTO();
		invite.setInviteKey(key);
		invite.setWorkspaceId(workspaceId);
		InviteDTO selectInvite = inviteService.inviteSelectByInviteKey(invite);
		if(selectInvite == null) {
			request.setAttribute("errorMsg", "요청이 만료되었습니다. 다시 초대를 받아주세요.");
			return "error";
		}else if(!selectInvite.getEmail().equals(member.getEmail())) {
			request.setAttribute("errorMsg", "초대받은 이메일과 동일한 아이디로 로그인 해주세요.");
			return "error";
		}
		return "workspace/invite";
	}
	
	@ResponseBody
	@RequestMapping(value="/{workspaceId}/invite", method=RequestMethod.POST)
	public ResponseEntity<ResponseData> inviteWorkspace(@PathVariable int workspaceId, HttpSession session, UserWorkspaceDTO userWorkspace, @RequestParam String key) {
		MemberDTO member = (MemberDTO) session.getAttribute("member");
		TeamDTO team = teamService.guestTeamSelect(workspaceId);
		
		userWorkspace.setWorkspaceId(workspaceId);
		userWorkspace.setUserId(member.getId());
		userWorkspace.setUserEmail(member.getEmail());
		userWorkspace.setTeamId(team.getId());
		
		userWorkspaceService.userWorkspaceInsert(userWorkspace);

		InviteDTO invite = new InviteDTO();
		invite.setInviteKey(key);
		invite.setWorkspaceId(workspaceId);
		InviteDTO selectInvite = inviteService.inviteSelectByInviteKey(invite);
		inviteService.inviteAcceptUpdate(selectInvite.getId());

		ResponseData responseData = new ResponseData();
		responseData.setMessage("워크스페이스 가입을 완료했습니다.");
		return responseData.getResponseEntity(HttpStatus.OK);
	}
}
