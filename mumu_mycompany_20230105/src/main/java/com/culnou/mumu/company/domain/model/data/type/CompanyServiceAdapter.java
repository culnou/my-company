package com.culnou.mumu.company.domain.model.data.type;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.culnou.mumu.company.domain.model.Company;
import com.culnou.mumu.company.domain.model.CompanyId;
import com.culnou.mumu.company.infrastructure.persistence.CompanyJpaRepository;

@Service
@Transactional
public class CompanyServiceAdapter {
	
	@Autowired
	private CompanyJpaRepository companyRepository;
	
	public Company findCompany(String companyId) throws Exception{
		return companyRepository.companyOfId(new CompanyId(companyId));
	}

}
