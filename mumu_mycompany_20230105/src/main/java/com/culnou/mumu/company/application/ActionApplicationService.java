package com.culnou.mumu.company.application;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import com.culnou.mumu.compnay.controller.ActionDto;
import com.culnou.mumu.compnay.controller.MessageDto;

@Service
@Transactional
public class ActionApplicationService {
	@Qualifier("companyServiceImpl")
	@Autowired
	CompanyService companyService;
	
	public MessageDto addAction(ActionDto dto) {
		MessageDto message = new MessageDto();
		try {
			companyService.addAction(dto);
			message.setResult("OK");
		}catch(Exception ex) {
			message.setResult("NG");
			message.setErrorMessage(ex.getMessage());
			return message;
		}
		return message;
		
	}
	
	public MessageDto updateAction(ActionDto dto) {
		MessageDto message = new MessageDto();
		try {
			companyService.updateAction(dto);
			message.setResult("OK");
		}catch(Exception ex) {
			message.setResult("NG");
			message.setErrorMessage(ex.getMessage());
			return message;
		}
		return message;
		
	}
	
	public MessageDto removeAction(String actionId) {
		MessageDto message = new MessageDto();
		try {
			companyService.deleteAction(actionId);
			message.setResult("OK");
		}catch(Exception ex) {
			message.setResult("NG");
			message.setErrorMessage(ex.getMessage());
			return message;
		}
		return message;
		
	}

}
