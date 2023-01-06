package com.culnou.mumu.company.domain.service;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.culnou.mumu.company.application.ItApplicationService;
import com.culnou.mumu.compnay.controller.ItDto;

@Service
@Transactional
public class ItCategoryChecker {
	
	@Autowired
	private ItApplicationService itService;
	
	public String avarable(String itCategoryId) throws Exception{
		String message = "OK";
		List<ItDto> its = itService.findItsOfItCategory(itCategoryId);
		if(its.size() > 0) {
			message = "already_assigned_to_it";
		}
		return message;
	}

}
