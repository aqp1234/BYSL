package com.kms.bysl.controller;

import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;

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
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.context.WebApplicationContext;

@RunWith(SpringJUnit4ClassRunner.class)
@WebAppConfiguration
@ContextConfiguration(locations = {"file:src/main/webapp/WEB-INF/spring/**/*.xml"})
public class WorkspaceControllerTest {
	@Autowired
	private WebApplicationContext wac;
	ObjectMapper mapper = new ObjectMapper();
	private MockMvc mockMvc;
	
	
	@Before
	public void setUp() throws Exception{
		this.mockMvc = MockMvcBuilders.webAppContextSetup(this.wac).build();
		
	}
	
	@Test
	public void sendEmailTest() throws Exception{
		MultiValueMap<String, String> map = new LinkedMultiValueMap<String, String>();
		map.add("email", "aqp1234@naver.com");
		mockMvc.perform(MockMvcRequestBuilders.post("/workspace/15/send_invite_mail")
				.contentType(MediaType.APPLICATION_JSON)
				.params(map))
				.andDo(print());
	}
}
