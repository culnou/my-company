package com.culnou.mumu.company.domain.model.application.task;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


import com.culnou.mumu.company.domain.model.CompanyId;
import com.culnou.mumu.company.domain.model.TaskId;
import com.culnou.mumu.company.domain.model.Url;

import com.culnou.mumu.company.domain.model.application.type.ApplicationTypeId;
import com.culnou.mumu.company.domain.model.application.type.ApplicationTypeService;
import com.culnou.mumu.company.domain.model.common.CompanyAdapter;
import com.culnou.mumu.company.infrastructure.application.CompanyServiceImpl;
import com.culnou.mumu.compnay.controller.ApplicationTaskDto;
import com.culnou.mumu.compnay.controller.ApplicationTypeDto;
import com.culnou.mumu.compnay.controller.CompanyDto;
import com.culnou.mumu.compnay.controller.MessageDto;

@Service
@Transactional
public class ApplicationTaskService {
	
	@Autowired
	CompanyServiceImpl companyService;
	@Autowired
	private CompanyAdapter companyAdapter;
	@Autowired
	private ApplicationTypeService applicationTypeService;
	@Autowired
	private ApplicationTaskRegistry registry;
	
	public List<ApplicationTaskDto> findApplicationTasksOfCompany(String companyId) throws Exception{
		if(companyId == null) {
			throw new Exception("The_commentId_may_not_be_set_to_null");
		}
		if(companyId.isEmpty()) {
			throw new Exception("Must_provide_a_companyId");
		}
		return this.convertApplicationTasks(registry.findApplicationTasksOfCompany(companyId));
	}
	
	public List<ApplicationTaskDto> findApplicationTasksOfApplicationType(String applicationTypeId) throws Exception{
		if(applicationTypeId == null) {
			throw new Exception("The_applicationTypeId_may_not_be_set_to_null");
		}
		if(applicationTypeId.isEmpty()) {
			throw new Exception("Must_provide_a_applicationTypeId");
		}
		return this.convertApplicationTasks(registry.findApplicationTasksOfApplicationType(applicationTypeId));
	}
	public ApplicationTaskDto findApplicationTasksOfId(String id) throws Exception{
		if(id == null) {
			throw new Exception("The_applicationTaskId_may_not_be_set_to_null");
		}
		if(id.isEmpty()) {
			throw new Exception("Must_provide_a_applicationTaskId");
		}
		return this.convertApplicationTask(registry.findApplicationTaskOfId(id));
	}
	
	public MessageDto defineApplicationTask(ApplicationTaskDto dto) {
		MessageDto message = new MessageDto();
		try {
			//事前条件
	        //コントローラーからの入力値の妥当性を保証する
			if(dto == null) {
				throw new Exception("The_ApplicationTask_may_not_be_set_to_null");
			}
			if(dto.getApplicationTaskName() == null) {
				throw new Exception("The_applicationTaskName_may_not_be_set_to_null");
			}
			if(dto.getApplicationTaskName().isEmpty()) {
				throw new Exception("Must_provide_a_applicationTaskName");
			}
			if(dto.getApplicationTypeId() == null) {
				throw new Exception("The_applicationTypeId_may_not_be_set_to_null");
			}
			if(dto.getApplicationTypeId().isEmpty()) {
				throw new Exception("Must_provide_a_applicationTypeId");
			}
			if(dto.getApplicationTypeName() == null) {
				throw new Exception("The_applicationTypeName_may_not_be_set_to_null");
			}
			if(dto.getApplicationTypeName().isEmpty()) {
				throw new Exception("Must_provide_a_applicationTypeName");
			}
			if(dto.getCompanyId() == null) {
				throw new Exception("The_companyId_may_not_be_set_to_null");
			}
			if(dto.getCompanyId().isEmpty()) {
				throw new Exception("Must_provide_a_companyId");
			}
			//識別子に対する実体の存在を保証する（識別子オブジェクトにラップする）
			CompanyDto company = companyAdapter.findCompayOfId(dto.getCompanyId());
			if(company == null) {
				throw new Exception("The_company_may_not_exist");
			}
			ApplicationTypeDto applicationType = applicationTypeService.findApplicationTypesOfId(dto.getApplicationTypeId());
			if(applicationType == null) {
				throw new Exception("The_ApplicationType_could_not_be_found");
			}
			//ビジネスロジック
			ApplicationTask entity = this.convertApplicationTaskDto(dto);
			entity.setApplicationTaskId(registry.nextIdentity());
			entity.setCreatedAt(new Date());
			registry.save(entity);
			message.setResult("OK");
		}catch(Exception ex) {
			message.setResult("NG");
			message.setErrorMessage(ex.getMessage());
			return message;
		}
		return message;
	}
	
	public MessageDto updateApplicationTask(ApplicationTaskDto dto) {
		MessageDto message = new MessageDto();
		try {
			//事前条件
	        //コントローラーからの入力値の妥当性を保証する
			if(dto == null) {
				throw new Exception("The_ApplicationTask_may_not_be_set_to_null");
			}
			if(dto.getApplicationTaskName() == null) {
				throw new Exception("The_applicationTaskName_may_not_be_set_to_null");
			}
			if(dto.getApplicationTaskId() == null) {
				throw new Exception("The_applicationTaskId_may_not_be_set_to_null");
			}
			if(dto.getApplicationTypeId().isEmpty()) {
				throw new Exception("Must_provide_a_applicationTypeId");
			}
			if(dto.getApplicationTypeName() == null) {
				throw new Exception("The_applicationTypeName_may_not_be_set_to_null");
			}
			if(dto.getApplicationTypeName().isEmpty()) {
				throw new Exception("Must_provide_a_applicationTypeName");
			}
			if(dto.getApplicationTaskName().isEmpty()) {
				throw new Exception("Must_provide_a_applicationTaskName");
			}
			if(dto.getApplicationTaskId().isEmpty()) {
				throw new Exception("Must_provide_a_applicationTaskId");
			}
			if(dto.getCompanyId() == null) {
				throw new Exception("The_commentId_may_not_be_set_to_null");
			}
			if(dto.getCompanyId().isEmpty()) {
				throw new Exception("Must_provide_a_companyId");
			}
			//識別子に対する実体の存在を保証する（識別子オブジェクトにラップする）
			CompanyDto company = companyAdapter.findCompayOfId(dto.getCompanyId());
			if(company == null) {
				throw new Exception("The_company_may_not_exist");
			}
			ApplicationTypeDto applicationType = applicationTypeService.findApplicationTypesOfId(dto.getApplicationTypeId());
			if(applicationType == null) {
				throw new Exception("The_ApplicationType_could_not_be_found");
			}
			ApplicationTask result = registry.findApplicationTaskOfId(dto.getApplicationTaskId());
			if(result == null) {
				throw new Exception("The_ApplicationTask_could_not_be_found");
			}
			//ビジネスロジック
			//必要な属性だけ更新する
			ApplicationTask entity = this.convertApplicationTaskDto(dto);
			entity.setApplicationTaskId(result.getApplicationTaskId());
			entity.setCompanyId(new CompanyId(company.getCompanyId()));
			entity.setApplicationTypeId(result.getApplicationTypeId());
			entity.setApplicationTypeName(result.getApplicationTypeName());
			entity.setCreatedAt(dto.getCreatedAt());			
			entity.setUpdatedAt(new Date());
			registry.save(entity);
			message.setResult("OK");
		}catch(Exception ex) {
			message.setResult("NG");
			message.setErrorMessage(ex.getMessage());
			return message;
		}
		return message;
	}
	
	public MessageDto removeApplicationTask(String applicationTaskId) {
		MessageDto message = new MessageDto();
		try {
			//事前条件
	        //コントローラーからの入力値の妥当性を保証する
			if(applicationTaskId == null) {
				throw new Exception("The_applicationTaskId_may_not_be_set_to_null");
			}
			if(applicationTaskId.isEmpty()) {
				throw new Exception("Must_provide_a_applicationTaskId");
			}
			//識別子に対する実体の存在を保証する（識別子オブジェクトにラップする）
			ApplicationTask result = registry.findApplicationTaskOfId(applicationTaskId);
			if(result == null) {
				throw new Exception("The_ApplicationTask_could_not_be_found");
			}
			registry.remove(result);
			message.setResult("OK");
		}catch(Exception ex) {
			message.setResult("NG");
			message.setErrorMessage(ex.getMessage());
			return message;
		}
		return message;
	}
	
	
	private ApplicationTaskDto convertApplicationTask(ApplicationTask entity) {
		ApplicationTaskDto dto = new ApplicationTaskDto();
		dto.setApplicationTaskId(entity.getApplicationTaskId().getApplicationTaskId());
		dto.setCompanyId(entity.getCompanyId().id());
		dto.setApplicationTypeId(entity.getApplicationTypeId().getApplicationTypeId());
		dto.setApplicationTypeName(entity.getApplicationTypeName());
		dto.setApplicationTaskName(entity.getApplicationTaskName());
		dto.setApplicationTaskDescription(entity.getApplicationTaskDescription());
		if(entity.getTaskId() != null) {
		    dto.setTaskId(entity.getTaskId().taskId());
		}
		if(entity.getTaskName() != null) {
			dto.setTaskName(entity.getTaskName());
		}
		if(entity.getUrl() != null) {
			dto.setUrl(entity.getUrl().getUrl());
		}
		dto.setCreatedAt(entity.getCreatedAt());
		dto.setUpdatedAt(entity.getUpdatedAt());
		return dto;
	}
	
	private List<ApplicationTaskDto> convertApplicationTasks(List<ApplicationTask> entities){
		List<ApplicationTaskDto> dtos = new ArrayList<>();
		for(ApplicationTask entity : entities) {
			dtos.add(this.convertApplicationTask(entity));
		}
		return dtos;
	}
	
	private ApplicationTask convertApplicationTaskDto(ApplicationTaskDto dto) {
		ApplicationTask entity = new ApplicationTask();
		if(dto.getApplicationTaskId() != null && !dto.getApplicationTaskId().isEmpty()) {
			entity.setApplicationTaskId(new ApplicationTaskId(dto.getApplicationTaskId()));
		}
		entity.setApplicationTaskName(dto.getApplicationTaskName());
		entity.setApplicationTaskDescription(dto.getApplicationTaskDescription());
		entity.setApplicationTypeId(new ApplicationTypeId(dto.getApplicationTypeId()));
		entity.setApplicationTypeName(dto.getApplicationTypeName());
		entity.setCompanyId(new CompanyId(dto.getCompanyId()));
		if(dto.getTaskId() != null && !dto.getTaskId().isEmpty()) {
			entity.setTaskId(new TaskId(dto.getTaskId()));
		}
		if(dto.getTaskName() != null && !dto.getTaskName().isEmpty()) {
			entity.setTaskName(dto.getTaskName());
		}
		if(dto.getUrl() != null && !dto.getUrl().isEmpty()) {
			entity.setUrl(new Url(dto.getUrl()));
		}
		entity.setCreatedAt(dto.getCreatedAt());
		entity.setUpdatedAt(dto.getUpdatedAt());
		return entity;
	}



}
