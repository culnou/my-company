package com.culnou.mumu.company.application;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;


import com.culnou.mumu.compnay.controller.DepartmentDto;
import com.culnou.mumu.compnay.controller.MessageDto;

@Service
@Transactional
public class DepartmentApplicationService {
	@Qualifier("companyServiceImpl")
	@Autowired
	CompanyService companyService;
	
	public MessageDto addDepartment(DepartmentDto dto) {
		MessageDto message = new MessageDto();
		try {
			companyService.addDepartment(dto);
			message.setResult("OK");
		}catch(Exception ex) {
			message.setResult("NG");
			message.setErrorMessage(ex.getMessage());
			return message;
		}
		return message;
		
	}
	
	public MessageDto updateDepartment(DepartmentDto dto) {
		MessageDto message = new MessageDto();
		try {
			companyService.updateDepartment(dto);
			message.setResult("OK");
		}catch(Exception ex) {
			message.setResult("NG");
			message.setErrorMessage(ex.getMessage());
			return message;
		}
		return message;
		
	}
	
	public MessageDto removeDepartment(String departmentId) {
		MessageDto message = new MessageDto();
		try {
			companyService.deleteDepartment(departmentId);
			message.setResult("OK");
		}catch(Exception ex) {
			message.setResult("NG");
			message.setErrorMessage(ex.getMessage());
			return message;
		}
		return message;
		
	}
	
	
	
	
	
}
