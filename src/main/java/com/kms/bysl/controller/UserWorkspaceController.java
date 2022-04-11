package com.kms.bysl.controller;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletResponse;
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
import com.kms.bysl.dto.UserWorkspaceDTO;
import com.kms.bysl.exception.DuplicateUserWorkspaceException;
import com.kms.bysl.service.UserWorkspaceService;

@Controller
@RequestMapping(value="/profile")
public class UserWorkspaceController {
	
	@Autowired
	private UserWorkspaceService userWorkspaceService;
	
	@RequestMapping(value="/{workspaceId}", method=RequestMethod.GET)
	public String profileForm() {
		return "profile/main";
	}
	
	@ResponseBody
	@RequestMapping(value="/{workspaceId}", method=RequestMethod.POST)
	public ResponseEntity<ResponseData> profileUpdate(@PathVariable int workspaceId, UserWorkspaceDTO userWorkspace, HttpSession session) {
		UserWorkspaceDTO sessionUserWorkspace = (UserWorkspaceDTO) session.getAttribute("userWorkspace");
		
		userWorkspace.setId(sessionUserWorkspace.getId());
		userWorkspaceService.userWorkspaceUpdate(userWorkspace);

		ResponseData responseData = new ResponseData();
		responseData.setMessage("프로필 수정이 완료되었습니다.");
		return responseData.getResponseEntity(HttpStatus.OK);
	}

}
