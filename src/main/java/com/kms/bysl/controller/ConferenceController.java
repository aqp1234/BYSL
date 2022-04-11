package com.kms.bysl.controller;

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
import com.kms.bysl.dto.ConferenceDTO;
import com.kms.bysl.dto.UserWorkspaceDTO;
import com.kms.bysl.service.ConferenceService;

@Controller
@RequestMapping(value="/conference")
public class ConferenceController {
	
	@Autowired
	private ConferenceService conferenceService;
	
	@RequestMapping(value="/{workspaceId}", method=RequestMethod.GET)
	public String conferenceList(@PathVariable int workspaceId, HttpServletRequest request) {
		List<ConferenceDTO> conferences;
		
		conferences = conferenceService.conferenceSelectByWorkspaceId(workspaceId);
		
		request.setAttribute("conferences", conferences);
		return "conference/main";
	}
	
	@RequestMapping(value="/{workspaceId}/add", method=RequestMethod.GET)
	public String conferenceAddForm() {
		return "conference/add";
	}
	
	@ResponseBody
	@RequestMapping(value="/{workspaceId}/add", method=RequestMethod.POST)
	public ResponseEntity<ResponseData> conferenceAdd(@PathVariable int workspaceId, ConferenceDTO conference, HttpSession session) {
		UserWorkspaceDTO userWorkspace;
		
		userWorkspace = (UserWorkspaceDTO) session.getAttribute("userWorkspace");
		
		conference.setWorkspaceId(workspaceId);
		
		conferenceService.conferenceInsert(conference, userWorkspace.getId());

		ResponseData responseData = new ResponseData();
		responseData.setMessage("회의록 추가가 완료되었습니다.");
		return responseData.getResponseEntity(HttpStatus.OK);
	}
	
	@RequestMapping(value="/{workspaceId}/{conferenceId}", method=RequestMethod.GET)
	public String conferenceDetail(@PathVariable int conferenceId, HttpServletRequest request) {
		ConferenceDTO conference;
		
		conference = conferenceService.conferenceSelectByConferenceId(conferenceId);
		
		request.setAttribute("conference", conference);
		return "conference/detail";
	}
	
	@ResponseBody
	@RequestMapping(value="/{workspaceId}/{conferenceId}", method=RequestMethod.DELETE)
	public ResponseEntity<ResponseData> conferneceDelete(@PathVariable int conferenceId) {
		conferenceService.conferenceDelete(conferenceId);

		ResponseData responseData = new ResponseData();
		responseData.setMessage("회의록 삭제가 완료되었습니다.");
		return responseData.getResponseEntity(HttpStatus.OK);
	}
	
	@RequestMapping(value="/{workspaceId}/update/{conferenceId}", method=RequestMethod.GET)
	public String conferenceUpdateForm(@PathVariable int conferenceId, HttpServletRequest request) {
		ConferenceDTO conference;
		
		conference = conferenceService.conferenceSelectByConferenceId(conferenceId);
		
		request.setAttribute("conference", conference);
		return "conference/update";
	}
	
	@ResponseBody
	@RequestMapping(value="/{workspaceId}/update/{conferenceId}", method=RequestMethod.POST)
	public ResponseEntity<ResponseData> conferenceUpdate(@PathVariable int conferenceId, ConferenceDTO conference) {
		conference.setId(conferenceId);
		conferenceService.conferenceUpdate(conference);

		ResponseData responseData = new ResponseData();
		responseData.setMessage("회의록 수정이 완료되었습니다.");
		return responseData.getResponseEntity(HttpStatus.OK);
	}
}
