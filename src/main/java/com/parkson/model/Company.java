package com.parkson.model;

import java.sql.Timestamp;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;

@Entity
@Table(name= "mf11_m_compmast",  uniqueConstraints = {
	       @UniqueConstraint(columnNames = {"mf11_compCodeHRIS"}) })
public class Company {
	@Id
    @Column(name="mf11_compCode")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long code;
    @Column(name="mf11_compCodeHRIS")
    private String codeHris;
    @Column(name="mf11_compName")
    private String name;
    @Column(name="mf11_compAbbrName")
    private String abbreviatedName;
    @Column(name="mf11_compRegNo")
    private String registerationNumber;
    @Column(name="mf11_compLogo")
    private String logo;
    @Column(name="mf11_compActiveDate")
    private Timestamp activationDate;
    @Column(name="mf11_isActive")
    private boolean isActive;
    @Column(name="mf11_createdBy")
    private String createdBy;
    @Column(name="mf11_createdOn")
    private Timestamp createdOn;
    @Column(name="mf11_lastModifiedOn")
    private Timestamp lastModifiedOn;
    @Column(name="mf11_lastModifiedBy")
    private String lastModifiedBy;
    @Column(name="mf11_deactivatedBy")
    private String deactivatedBy;
    @Column(name="mf11_deactivatedOn")
    private Timestamp deactivatedOn;
    @Column(name="mf11_reactivatedBy")
    private String reactivatedBy;
    @Column(name="mf11_reactivatedOn")
    private Timestamp reactivatedOn;
    
	public long getCode() {
		return code;
	}
	public void setCode(long code) {
		this.code = code;
	}
	public String getCodeHris() {
		return codeHris;
	}
	public void setCodeHris(String codeHris) {
		this.codeHris = codeHris;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getAbbreviatedName() {
		return abbreviatedName;
	}
	public void setAbbreviatedName(String abbreviatedName) {
		this.abbreviatedName = abbreviatedName;
	}
	public String getRegisterationNumber() {
		return registerationNumber;
	}
	public void setRegisterationNumber(String registerationNumber) {
		this.registerationNumber = registerationNumber;
	}
	public String getLogo() {
		return logo;
	}
	public void setLogo(String logo) {
		this.logo = logo;
	}
	public Timestamp getActivationDate() {
		return activationDate;
	}
	public void setActivationDate(Timestamp activationDate) {
		this.activationDate = activationDate;
	}
	public boolean isActive() {
		return isActive;
	}
	public void setActive(boolean isActive) {
		this.isActive = isActive;
	}
	public String getCreatedBy() {
		return createdBy;
	}
	public void setCreatedBy(String createdBy) {
		this.createdBy = createdBy;
	}
	public Timestamp getCreatedOn() {
		return createdOn;
	}
	public void setCreatedOn(Timestamp createdOn) {
		this.createdOn = createdOn;
	}
	public Timestamp getLastModifiedOn() {
		return lastModifiedOn;
	}
	public void setLastModifiedOn(Timestamp lastModifiedOn) {
		this.lastModifiedOn = lastModifiedOn;
	}
	public String getLastModifiedBy() {
		return lastModifiedBy;
	}
	public void setLastModifiedBy(String lastModifiedBy) {
		this.lastModifiedBy = lastModifiedBy;
	}
	public String getDeactivatedBy() {
		return deactivatedBy;
	}
	public void setDeactivatedBy(String deactivatedBy) {
		this.deactivatedBy = deactivatedBy;
	}
	public Timestamp getDeactivatedOn() {
		return deactivatedOn;
	}
	public void setDeactivatedOn(Timestamp deactivatedOn) {
		this.deactivatedOn = deactivatedOn;
	}
	public String getReactivatedBy() {
		return reactivatedBy;
	}
	public void setReactivatedBy(String reactivatedBy) {
		this.reactivatedBy = reactivatedBy;
	}
	public Timestamp getReactivatedOn() {
		return reactivatedOn;
	}
	public void setReactivatedOn(Timestamp reactivatedOn) {
		this.reactivatedOn = reactivatedOn;
	}
    
    
}
