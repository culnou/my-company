package com.culnou.mumu.company.application;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import com.culnou.mumu.company.domain.model.BusinessProcessId;
import com.culnou.mumu.compnay.controller.BusinessProcessDto;
import com.culnou.mumu.compnay.controller.MessageDto;
import com.culnou.mumu.compnay.controller.TaskDto;


@Service
@Transactional
public class BusinessProcessApplicationService {
	
	@Qualifier("companyServiceImpl")
	@Autowired
	CompanyService companyService;
	
	public MessageDto defineBusinessProcess(BusinessProcessDto businessProcess) throws Exception{
		MessageDto message = new MessageDto();
		try {
			companyService.addBusinessProcess(businessProcess);
			message.setResult("OK");
		}catch(Exception ex) {
			message.setResult("NG");
			message.setErrorMessage(ex.getMessage());
			return message;
		}
		return message;
	}
	
	public MessageDto updateBusinessProcess(BusinessProcessDto businessProcess) throws Exception{
		MessageDto message = new MessageDto();
		try {
			companyService.updateBusinessProcess(businessProcess);
			message.setResult("OK");
		}catch(Exception ex) {
			message.setResult("NG");
			message.setErrorMessage(ex.getMessage());
			return message;
		}
		return message;
	}
	
	public MessageDto removeBusinessProcess(String businessProcessId) throws Exception{
		MessageDto message = new MessageDto();
		try {
			companyService.deleteBusinessProcess(businessProcessId);
			message.setResult("OK");
		}catch(Exception ex) {
			message.setResult("NG");
			message.setErrorMessage(ex.getMessage());
			return message;
		}
		return message;
	}
	
	public MessageDto assignTaskToBusinessProcess(TaskDto task,String businessProcessId) throws Exception{
		MessageDto message = new MessageDto();
		try {
			companyService.assignTaskToBusinessProcess(task, businessProcessId);
			message.setResult("OK");
		}catch(Exception ex) {
			message.setResult("NG");
			message.setErrorMessage(ex.getMessage());
			return message;
		}
		return message;
	}
	
	public MessageDto releaseTaskFromBusinessProcess(TaskDto task,String businessProcessId) throws Exception{
		MessageDto message = new MessageDto();
		try {
			companyService.releaseTaskFromBusinessProcess(task, businessProcessId);
			message.setResult("OK");
		}catch(Exception ex) {
			message.setResult("NG");
			message.setErrorMessage(ex.getMessage());
			return message;
		}
		return message;
	}
	
	public MessageDto replaceTaskOrder(TaskDto source, TaskDto target, BusinessProcessId businessProcessId) throws Exception{
		MessageDto message = new MessageDto();
		try {
			companyService.replaceTaskOrder(source, target, businessProcessId);
			message.setResult("OK");
		}catch(Exception ex) {
			message.setResult("NG");
			message.setErrorMessage(ex.getMessage());
			return message;
		}
		return message;
	}
	

}
