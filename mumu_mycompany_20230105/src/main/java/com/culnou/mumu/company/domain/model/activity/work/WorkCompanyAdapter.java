package com.culnou.mumu.company.domain.model.activity.work;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.culnou.mumu.company.application.CompanyService;

import com.culnou.mumu.compnay.controller.CompanyDto;

@Service
@Transactional
public class WorkCompanyAdapter {
	@Autowired
	private CompanyService companyService;
	
	protected CompanyDto findCompayOfId(String companyId) throws Exception{
		return companyService.findCompanyById(companyId);
	}
	

}
