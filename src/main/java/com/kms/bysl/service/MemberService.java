package com.kms.bysl.service;

import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.codehaus.jackson.JsonNode;
import org.json.simple.JSONObject;

import com.kms.bysl.dto.MemberDTO;

public interface MemberService {
	public boolean login(MemberDTO dto, HttpSession session);
	public void memberJoin(MemberDTO dto);
	public MemberDTO memberView(MemberDTO dto);
	public void memberDelete(MemberDTO dto);
	public void logout(HttpSession session);
	public Object findSchool(String word);
	public Object sendEmail(String email);
	public boolean checkCertNo(String certNo, String inputNum);
}
