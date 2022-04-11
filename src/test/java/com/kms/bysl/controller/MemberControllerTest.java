package com.kms.bysl.controller;

import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;
import java.util.TreeMap;

import org.codehaus.jackson.map.ObjectMapper;
import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.context.WebApplicationContext;

import com.kms.bysl.dto.MemberDTO;

@RunWith(SpringJUnit4ClassRunner.class)
@WebAppConfiguration
@ContextConfiguration(locations = {"file:src/main/webapp/WEB-INF/spring/**/*.xml"})
public class MemberControllerTest{
	
	@Autowired
	private WebApplicationContext wac;
	ObjectMapper mapper = new ObjectMapper();
	private MockMvc mockMvc;
	
	
	@Before
	public void setUp() throws Exception{
		this.mockMvc = MockMvcBuilders.webAppContextSetup(this.wac).build();
	}
	
	@Test
	@Ignore
	public void memberJoinTest() throws Exception{
		MultiValueMap<String, String> map = new LinkedMultiValueMap<String, String>();
		map.add("email", "aqp12345@naver.com");
		map.add("name", "��μ�");
		map.add("password", "asd4831846!");
		map.add("phone", "01053966845");
		map.add("location_name", "����Ư����");
		map.add("school_name", "���ϰ���б�");
		mockMvc.perform(MockMvcRequestBuilders.post("/member/memJoin")
				.contentType(MediaType.APPLICATION_JSON)
				.params(map))
				.andDo(print());
	}
	
	@Test
	@Ignore
	public void memberLoginTest() throws Exception{
		MultiValueMap<String, String> map = new LinkedMultiValueMap<String, String>();
		map.add("email", "aqp1234@naver.com");
		map.add("password", "asd4831846!");
		mockMvc.perform(MockMvcRequestBuilders.post("/member/login")
				.contentType(MediaType.APPLICATION_JSON)
				.params(map))
				.andExpect(status().is(302))
				.andDo(print());
	}
	
	@Test
	@Ignore
	public void findSchoolTest() throws Exception{
		Map<String, String> content = new TreeMap<String, String>();
		content.put("word", "����");
		mockMvc.perform(MockMvcRequestBuilders.post("/member/findschool")
				.contentType(MediaType.APPLICATION_JSON)
				.content(mapper.writeValueAsString(content)))
				.andDo(print());
	}
	
	@Test
	@Ignore
	public void sendEmail() throws Exception{
		Map<String, String> content = new TreeMap<String, String>();
		content.put("email", "aqp0222@naver.com");
		mockMvc.perform(MockMvcRequestBuilders.post("/member/memJoin/sendEmail")
				.contentType(MediaType.APPLICATION_JSON)
				.content(mapper.writeValueAsString(content)))
				.andDo(print());
	}
	
	@Test
	public void test() {
		//int i = 1;
		//String parameter = String.join(",", Collections.nCopies(25, "(" + 1 + ", " + i++ + ")"));
		String parameter = "";
		for(int i = 1; i <= 25; i++) {
			parameter += "(" + 1 + ", " + i + ")";
			if(i != 25) {
				parameter += ",";
			}
		}
		System.out.println(parameter);
	}
}
