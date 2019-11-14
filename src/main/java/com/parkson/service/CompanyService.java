package com.parkson.service;

import java.util.List;

import com.parkson.model.Company;

public interface CompanyService {
	public void saveCompany(Company company);
	public void updateCompany(Company company);
	public void deleteCompany(long id);
	public Company getCompany(Long id);
	public List<Company> getAllCompanies();
}
