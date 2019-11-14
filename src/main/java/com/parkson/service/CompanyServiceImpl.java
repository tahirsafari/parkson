package com.parkson.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.parkson.model.Company;
import com.parkson.repository.CompanyRepository;

@Service
public class CompanyServiceImpl implements CompanyService{
	@Autowired
	private CompanyRepository companyRepository;
	@Override
	public void saveCompany(Company company) {
		companyRepository.save(company);
	}

	@Override
	public void updateCompany(Company company) {
		companyRepository.save(company);
	}

	@Override
	public void deleteCompany(long id) {
		companyRepository.deleteById(id);
	}

	@Override
	public Company getCompany(Long id) {
		return companyRepository.getOne(id);
	}

	@Override
	public List<Company> getAllCompanies() {
		return companyRepository.findAll();
	}

}
