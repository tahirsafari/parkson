package com.parkson.controller;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.parkson.ParksonApplication;
import com.parkson.dto.UserDto;

@RunWith(SpringRunner.class)
@ActiveProfiles(value="dev")
@SpringBootTest (classes = ParksonApplication.class) 
public class AuthControllerTests {
	@Autowired
	WebApplicationContext wac;
	@Autowired
	protected ObjectMapper objectMapper;
	protected MockMvc mockMvc;
	
	@Before
	public void init(){
		MockitoAnnotations.initMocks(this);
        this.mockMvc = MockMvcBuilders
        		.webAppContextSetup(wac)
                .build();
	}
	
	@Test
	public void setupUser() throws Exception{
		UserDto user = new UserDto("tahir", "tahir123");
//
		MediaType MEDIA_TYPE_JSON_UTF8 = new MediaType("application", "json", java.nio.charset.Charset.forName("UTF-8"));
		String request = objectMapper.writeValueAsString(user);
		mockMvc.perform(post("/auth/signup").contentType(MEDIA_TYPE_JSON_UTF8)
			    .content(request))
			//.andExpect(status().isOk())
			//.andExpect(content().contentType(MediaType.APPLICATION_JSON))
			.andDo(print());
	}
}
