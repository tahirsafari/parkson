package com.parkson.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.parkson.model.Company;

public interface CompanyRepository extends JpaRepository<Company, Long>{

}
