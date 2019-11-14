package com.parkson.dto;

import java.sql.Timestamp;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

public class AddCompanyDto {
	@NotNull(message = "Please provide code")
	@Min(message = "Code should be greater than {value}", value = 1)
	private Long code;
	@NotNull(message = "Name can not null")
	@NotEmpty(message = "Name shall not be empty")
	private String name;
	@NotNull(message = "Please provide Registeration Number")
	@NotEmpty(message = "Registeration Number shall not be empty")
	private String registerationNumber;
	@NotNull(message = "Activated On shall not be empty")
	private Timestamp activatedOn;
	@NotNull(message = "Please provide Code Hris")
	@NotEmpty(message = "Code Hris shall not be empty")
	private String codeHris;
	@NotNull(message = "Please provide Abbreviated Name")
	@NotEmpty(message = "AbbreviatedName shall not be empty")
	private String abbreviatedName;
	@NotNull(message = "Please choose logo")
	@NotEmpty(message = "Please choose logo")
	private String logo;
	@NotNull(message = "Please choose Status")
	private Boolean active;

	private Timestamp createdOn;
	private String createdBy;
	
	public long getCode() {
		return code;
	}
	public void setCode(long code) {
		this.code = code;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getRegisterationNumber() {
		return registerationNumber;
	}
	public void setRegisterationNumber(String registerationNumber) {
		this.registerationNumber = registerationNumber;
	}
	public Timestamp getActivatedOn() {
		return activatedOn;
	}
	public void setActivatedOn(Timestamp activatedOn) {
		this.activatedOn = activatedOn;
	}
	public String getCodeHris() {
		return codeHris;
	}
	public void setCodeHris(String codeHris) {
		this.codeHris = codeHris;
	}
	public String getAbbreviatedName() {
		return abbreviatedName;
	}
	public void setAbbreviatedName(String abbreviatedName) {
		this.abbreviatedName = abbreviatedName;
	}
	public String getLogo() {
		return logo;
	}
	public void setLogo(String logo) {
		this.logo = logo;
	}
	public boolean isActive() {
		return active;
	}
	public void setActive(boolean active) {
		this.active = active;
	}
	public Timestamp getCreatedOn() {
		return createdOn;
	}
	public void setCreatedOn(Timestamp createdOn) {
		this.createdOn = createdOn;
	}
	public String getCreatedBy() {
		return createdBy;
	}
	public void setCreatedBy(String createdBy) {
		this.createdBy = createdBy;
	}
	
	@Override
	public String toString() {
		return "AddCompanyDto [code=" + code + ", name=" + name + ", registerationNumber=" + registerationNumber
				+ ", activatedOn=" + activatedOn + ", codeHris=" + codeHris + ", abbreviatedName=" + abbreviatedName
				+ ", logo=" + logo + ", active=" + active + ", createdOn=" + createdOn + ", createdBy=" + createdBy
				+ "]";
	}


}
