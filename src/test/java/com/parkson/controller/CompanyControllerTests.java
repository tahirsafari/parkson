package com.parkson.controller;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.sql.Timestamp;
import java.time.Instant;

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
import com.parkson.dto.AddCompanyDto;

@RunWith(SpringRunner.class)
@ActiveProfiles(value="test")
@SpringBootTest (classes = ParksonApplication.class) 
public class CompanyControllerTests {
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
	public void saveCompany() throws Exception{
		AddCompanyDto company = new AddCompanyDto();
		company.setCode(1234);
		company.setCodeHris("CUS2");
		company.setName("");
		company.setAbbreviatedName("Co");
		company.setActive(true);
		company.setLogo("base64");
		company.setRegisterationNumber("1234-A");
		Instant instant = Instant.now();
		long timeStampMillis = instant.toEpochMilli();
		company.setActivatedOn(new Timestamp(timeStampMillis));
//
		MediaType MEDIA_TYPE_JSON_UTF8 = new MediaType("application", "json", java.nio.charset.Charset.forName("UTF-8"));
		String request = objectMapper.writeValueAsString(company);
		mockMvc.perform(post("/company/add").contentType(MEDIA_TYPE_JSON_UTF8)
			    .content(request))
			.andExpect(status().isBadRequest())
			//.andExpect(content().contentType(MediaType.APPLICATION_JSON))
			.andDo(print());
	}
	
	@Test
	public void getAllCompanies() throws Exception {
		MediaType MEDIA_TYPE_JSON_UTF8 = new MediaType("application", "json", java.nio.charset.Charset.forName("UTF-8"));
		mockMvc.perform(get("/company/all")
			    .contentType(MEDIA_TYPE_JSON_UTF8))
				.andExpect(status().isOk())
				.andDo(print());
	}
	
	
}
