package com.culnou.mumu.company.domain.model.activity.work;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.culnou.mumu.company.application.CompanyService;

import com.culnou.mumu.compnay.controller.ActionDto;

@Service
@Transactional
public class WorkActionAdapter {
	
	@Autowired
	private CompanyService service;
	
	protected ActionDto findActionOfId(String actionId) throws Exception{
		return service.findActionById(actionId);
	}

}
