package com.kms.bysl.controller;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.codehaus.jackson.JsonNode;
import org.json.simple.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.kms.bysl.ResponseData;
import com.kms.bysl.StatusEnum;
import com.kms.bysl.dto.MemberDTO;
import com.kms.bysl.service.MemberService;

@Controller
@RequestMapping("/member")
public class MemberController {
	
	@Autowired
	MemberService memberService;
	
	@RequestMapping(value="/memJoin", method=RequestMethod.GET)
	public String memberJoinForm(Model model) {
		return "auth/join";
	}
	
	@ResponseBody
	@RequestMapping(value="/memJoin", method=RequestMethod.POST)
	public ResponseEntity<ResponseData> memberJoin(MemberDTO member, HttpServletRequest request) {
		memberService.memberJoin(member);

		ResponseData responseData = new ResponseData();
		responseData.setMessage("회원가입이 완료되었습니다.");
		return responseData.getResponseEntity(HttpStatus.OK);
	}
	
	@RequestMapping(value="/login", method=RequestMethod.GET)
	public String loginForm(Model model) {
		return "auth/login";
	}
	
	@RequestMapping(value="/login", method=RequestMethod.POST)
	public ResponseEntity<ResponseData> login(MemberDTO dto, HttpSession session, @RequestParam(required=false) String next, HttpServletRequest request) {
		boolean is_login = memberService.login(dto, session);
		
		ResponseData responseData = new ResponseData();
		if(next != null) {
			return responseData.getResponseEntity(HttpStatus.SEE_OTHER, next);
		}
		return responseData.getResponseEntity(HttpStatus.SEE_OTHER, "/bysl");
	}
	
	@RequestMapping(value="/logout", method=RequestMethod.GET)
	public String logout(Model model, HttpSession session) {
		memberService.logout(session);
		return "redirect:/";
	}
	
	@RequestMapping(value="/findSchool", method=RequestMethod.GET)
	public String findSchoolForm(Model model, @RequestParam(required=false, defaultValue="") String word) {
		return "auth/findSchool";
	}
	
	@ResponseBody
	@RequestMapping(value="/findSchool", method=RequestMethod.POST)
	public ResponseEntity<ResponseData> findSchool(@RequestParam String word) {
		Object schools = memberService.findSchool(word);

		ResponseData responseData = new ResponseData();
		responseData.setData(schools);
		return responseData.getResponseEntity(HttpStatus.OK);
	}
	
	@ResponseBody
	@RequestMapping(value="/memJoin/sendEmail", method=RequestMethod.POST)
	public ResponseEntity<ResponseData> sendEmail(@RequestParam String email) {
		Object number = memberService.sendEmail(email);

		ResponseData responseData = new ResponseData();
		responseData.setMessage("이메일 전송에 성공하였습니다.");
		responseData.setData(number);
		return responseData.getResponseEntity(HttpStatus.OK);
	}
	
	@ResponseBody
	@RequestMapping(value="/memJoin/checkCertNo", method=RequestMethod.POST)
	public ResponseEntity<ResponseData> checkCertNo(@RequestParam String certNo, @RequestParam String inputNum){
		ResponseData responseData = new ResponseData();
		if(memberService.checkCertNo(certNo, inputNum)) {
			responseData.setMessage("인증에 성공했습니다.");
			return responseData.getResponseEntity(HttpStatus.OK);
		}
		responseData.setMessage("인증에 실패했습니다. 인증번호를 다시 확인해주세요.");
		responseData.setStatus(StatusEnum.BAD_REQUEST);
		return responseData.getResponseEntity(HttpStatus.UNAUTHORIZED);
	}
}
