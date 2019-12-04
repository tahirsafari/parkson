package com.parkson.controller;

import java.io.IOException;
import java.sql.Timestamp;
import java.time.Instant;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import javax.inject.Inject;
import javax.validation.Valid;

import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
//import org.apache.logging.log4j.LogManager;
//import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.env.Environment;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.parkson.config.ApiResponse;
import com.parkson.dto.AddCompanyDto;
import com.parkson.dto.ListCompanyDto;
import com.parkson.model.Company;
import com.parkson.service.CompanyService;
import com.parkson.util.MediaManager;

@RestController
@RequestMapping("/company")
@Validated
public class CompanyController {
	private static final Logger logger = LogManager.getLogger(CompanyController.class);
	@Autowired
	private CompanyService companyService;
	@Autowired
	private Environment environment;
	@Inject
	@Value("${media.files.uploads.foldername}")
	private String folderName;
	
	@PostMapping("/add")
	public ResponseEntity<?> addCompany(@Valid @RequestBody AddCompanyDto company) throws IOException {
		logger.info("dto object "+company);
		Company newCompany = new Company();
		newCompany.setCode(company.getCode());
		newCompany.setName(company.getName());
		newCompany.setAbbreviatedName(company.getAbbreviatedName());
		newCompany.setActive(company.isActive());
		newCompany.setRegisterationNumber(company.getRegisterationNumber());
		newCompany.setCodeHris(company.getCodeHris());
		newCompany.setCreatedBy(currentUserName());
		Instant instant = Instant.now();
		long timeStampMillis = instant.toEpochMilli();
		newCompany.setCreatedOn(new Timestamp(timeStampMillis));
		Random random = new Random();
		int randomNumber = random.ints(1,1000).findFirst().getAsInt();
		String imagePath = "logo-"+randomNumber+".jpeg";
		MediaManager.saveBase64EncodedFile(company.getLogo(), imagePath);
		newCompany.setLogo(imagePath);
		newCompany.setLastModifiedBy(currentUserName());
		newCompany.setLastModifiedOn(new Timestamp(timeStampMillis));
		
		companyService.saveCompany(newCompany);
		return new ResponseEntity<ApiResponse>(new ApiResponse(true, "Company added successfully"),
	            HttpStatus.OK);
	}
	
	@GetMapping("/all")
	public List<ListCompanyDto> getAllCompanies() {
		List<Company> companyList = companyService.getAllCompanies();
		List<ListCompanyDto> listCustomerDto = new ArrayList<>();
		for(Company company:companyList) {
			listCustomerDto.add(new ListCompanyDto(company.getCode(), company.getName(), company.getLastModifiedBy(), company.getLastModifiedOn()));
		}
		return listCustomerDto;
	}
	@DeleteMapping("/delete/{id}")
	public ResponseEntity<?> deleteCustomer(@PathVariable("id") long id) {
		companyService.deleteCompany(id);
		return new ResponseEntity<ApiResponse>(new ApiResponse(true, "Company deleted successfully"),
                HttpStatus.OK);
	}


	public String currentUserName() {
		this.environment.getActiveProfiles();
		 Authentication auth = SecurityContextHolder.getContext().getAuthentication();
	     return auth.getName();
	}
	
}
