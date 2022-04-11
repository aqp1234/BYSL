package com.kms.bysl.service;

import java.io.IOException;
import java.util.Random;

import javax.mail.internet.MimeMessage;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.codehaus.jackson.JsonNode;
import org.codehaus.jackson.JsonProcessingException;
import org.codehaus.jackson.map.ObjectMapper;
import org.json.simple.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.mail.javamail.JavaMailSenderImpl;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.security.crypto.bcrypt.BCrypt;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.kms.bysl.dao.MemberDAO;
import com.kms.bysl.dto.MemberDTO;
import com.kms.bysl.exception.DuplicateUserWorkspaceException;
import com.kms.bysl.properties.applicationProperties;

@Service
public class MemberServiceImpl implements MemberService{
	
	@Autowired
	private JavaMailSenderImpl mailSender;
	
	@Autowired
	private applicationProperties ap;
	
	@Autowired
	private MemberDAO dao;

	@Override
	public boolean login(MemberDTO dto, HttpSession session) {
		MemberDTO loginedMember = (MemberDTO) session.getAttribute("member");
		if(loginedMember != null) {
			session.removeAttribute("member");
		}
		
		boolean result = dao.loginCheck(dto);
		if(result) {
			MemberDTO member = dao.memberView(dto);
			session.setAttribute("member", member);
		}
		return result;
	}

	@Override
	public void memberJoin(MemberDTO dto) {
		HttpHeaders headers = new HttpHeaders();
		headers.setContentType(MediaType.APPLICATION_JSON);
		
		String encryptpassword = BCrypt.hashpw(dto.getPassword(), BCrypt.gensalt());
		dto.setPassword(encryptpassword);
		
		final String url = "https://open.neis.go.kr/hub/schoolInfo?KEY=" + ap.getNICEKEY() + "&Type=json&pindex=1&pSize=100&SCHUL_NM=" + dto.getSchoolName() + "&LCTN_SC_NM=" + dto.getLocationName();
		RestTemplate restTemplate = new RestTemplate();
		ResponseEntity<String> respEntity = restTemplate.exchange(url, HttpMethod.GET, new HttpEntity<String>("", headers),String.class);
		ObjectMapper objectMapper = new ObjectMapper();
		JsonNode jsonNode = null;
		try {
			jsonNode = objectMapper.readTree(respEntity.getBody());
			System.out.println(url);
			System.out.println(jsonNode);
			String location_code = jsonNode.get("schoolInfo").get(1).get("row").get(0).get("ATPT_OFCDC_SC_CODE").toString();
			String school_code = jsonNode.get("schoolInfo").get(1).get("row").get(0).get("SD_SCHUL_CODE").toString();
			location_code = location_code.substring(1, location_code.length() - 1);
			school_code = school_code.substring(1, school_code.length() - 1);
			dto.setLocationCode(location_code);
			dto.setSchoolCode(school_code);
		} catch (JsonProcessingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		try{
			dao.memberJoin(dto);
		}catch(DuplicateKeyException e) {
			e.printStackTrace();
			throw new DuplicateUserWorkspaceException(e, "이미 가입된 이메일 입니다.");
		}
	}

	@Override
	public MemberDTO memberView(MemberDTO dto) {
		return dao.memberView(dto);
	}

	@Override
	public void memberDelete(MemberDTO dto) {
		dao.memberDelete(dto);
	}

	@Override
	public void logout(HttpSession session) {
		session.invalidate();
	}
	
	@Override
	public Object findSchool(String word) {
		HttpHeaders headers = new HttpHeaders();
		
		final String url = "https://www.career.go.kr/cnet/openapi/getOpenApi?apiKey=2d9b15f6e339269848ed94121f3a9ce9&svcType=api&svcCode=SCHOOL&contentType=json&gubun=high_list&perPage=10000&searchSchulNm=" + word;
		
		RestTemplate restTemplate = new RestTemplate();
		ResponseEntity<Object> resEntity = restTemplate.exchange(url, HttpMethod.GET, new HttpEntity<String>("", headers),Object.class);
		
		return resEntity.getBody();
	}

	@Override
	public Object sendEmail(String email) {
		
		Random random = new Random();
		int rannum = random.nextInt(888888) + 111111; // 111111~999999
		
		String setFrom = "aqp0222@naver.com";
		String toMail = email;
		String title = "[BYSL] 인증번호 메일입니다.";
		String content = "인증번호는 " + rannum + " 입니다.";
		
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
			throw new RuntimeException("이메일 전송에 실패했습니다.");
		}
		
		String num = Integer.toString(rannum);
		String encryptnum = BCrypt.hashpw(num, BCrypt.gensalt());
		
		JSONObject data = new JSONObject();
		data.put("number", encryptnum);
		
		return data;
	}

	@Override
	public boolean checkCertNo(String certNo, String inputNum) {
		if(BCrypt.checkpw(inputNum, certNo)) {
			return true;
		}
		return false;
	}

}
