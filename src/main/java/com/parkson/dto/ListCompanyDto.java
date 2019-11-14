package com.parkson.dto;

import java.sql.Timestamp;

public class ListCompanyDto {
	private long code;
	private String name;
	private String lastModifiedBy;
	private Timestamp lastModifiedOn;
	
	
	public ListCompanyDto(long code, String name, String lastModifiedBy, Timestamp lastModifiedOn) {
		this.code = code;
		this.name = name;
		this.lastModifiedBy = lastModifiedBy;
		this.lastModifiedOn = lastModifiedOn;
	}
	
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
	public String getLastModifiedBy() {
		return lastModifiedBy;
	}
	public void setLastModifiedBy(String lastModifiedBy) {
		this.lastModifiedBy = lastModifiedBy;
	}
	public Timestamp getLastModifiedOn() {
		return lastModifiedOn;
	}
	public void setLastModifiedOn(Timestamp lastModifiedOn) {
		this.lastModifiedOn = lastModifiedOn;
	}
	
	
	
}
