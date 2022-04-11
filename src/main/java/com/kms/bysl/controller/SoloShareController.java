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
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.kms.bysl.ResponseData;
import com.kms.bysl.dto.MemberDTO;
import com.kms.bysl.dto.SoloShareDTO;
import com.kms.bysl.service.MemberService;
import com.kms.bysl.service.SoloShareService;

@Controller
@RequestMapping(value="/soloShare")
public class SoloShareController {
	
	@Autowired
	private SoloShareService soloShareService;
	
	@Autowired
	private MemberService memberService;
	
	@RequestMapping(value="", method=RequestMethod.GET)
	public String soloShareAddForm(@RequestParam String url, HttpSession session, HttpServletRequest request) {
		MemberDTO member = (MemberDTO) session.getAttribute("member");
		List<SoloShareDTO> soloShares = soloShareService.soloShareSelect(member.getId(), url);
		
		request.setAttribute("soloShares", soloShares);
		return "soloShare/main";
	}
	
	@ResponseBody
	@RequestMapping(value="", method=RequestMethod.POST)
	public ResponseEntity<ResponseData> soloShareAdd(SoloShareDTO soloShare, HttpSession session) {
		MemberDTO member = (MemberDTO) session.getAttribute("member");
		MemberDTO manager = new MemberDTO();
		manager.setEmail(soloShare.getManagerEmail());
		manager = memberService.memberView(manager);
		
		soloShare.setOwnerId(member.getId());
		soloShare.setName(member.getName());
		if(manager != null) {
			soloShare.setManagerId(manager.getId());
			soloShare.setManagerName(manager.getName());
		}
		
		soloShareService.soloShareInsert(soloShare);

		ResponseData responseData = new ResponseData();
		responseData.setMessage("공유가 완료되었습니다.");
		return responseData.getResponseEntity(HttpStatus.OK);
	}
	
	@ResponseBody
	@RequestMapping(value="/{soloShareId}", method=RequestMethod.DELETE)
	public ResponseEntity<ResponseData> soloShareDelete(@PathVariable int soloShareId) {
		soloShareService.soloShareDelete(soloShareId);

		ResponseData responseData = new ResponseData();
		responseData.setMessage("삭제가 완료되었습니다.");
		return responseData.getResponseEntity(HttpStatus.OK);
	}
}
