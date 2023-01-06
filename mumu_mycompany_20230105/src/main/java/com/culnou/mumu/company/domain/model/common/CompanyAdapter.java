package com.culnou.mumu.company.domain.model.common;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.culnou.mumu.company.application.CompanyService;
import com.culnou.mumu.compnay.controller.CompanyDto;

@Service
@Transactional
public class CompanyAdapter {
	
	@Autowired
	private CompanyService companyService;
	
	public CompanyDto findCompayOfId(String companyId) throws Exception{
		return companyService.findCompanyById(companyId);
	}

}
