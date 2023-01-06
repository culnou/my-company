package com.culnou.mumu.company.application;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import com.culnou.mumu.company.domain.model.CompanyId;
import com.culnou.mumu.company.domain.model.Url;
import com.culnou.mumu.company.domain.model.application.category.ApplicationCategory;
import com.culnou.mumu.company.domain.model.application.category.ApplicationCategoryId;
import com.culnou.mumu.company.domain.model.application.category.ApplicationCategoryRepository;
import com.culnou.mumu.company.domain.model.application.function.ApplicationData;
import com.culnou.mumu.company.domain.model.application.function.ApplicationFunction;
import com.culnou.mumu.company.domain.model.application.function.ApplicationFunctionId;
import com.culnou.mumu.company.domain.model.application.function.ApplicationFunctionRegistry;
import com.culnou.mumu.company.domain.model.application.function.ApplicationFunctionService;
import com.culnou.mumu.company.domain.model.application.task.ApplicationTaskId;
import com.culnou.mumu.company.domain.service.ApplicationFunctionChecker;
import com.culnou.mumu.compnay.controller.ApplicationFunctionDto;
import com.culnou.mumu.compnay.controller.MessageDto;

@Service
@Transactional
public class ApplicationFunctionApplicationService {
	
	@Autowired
	private ApplicationFunctionRegistry registry;
	
	@Qualifier("applicationCategoryJpaRepository")
	@Autowired
	private ApplicationCategoryRepository applicationCategoryRepository;
	@Autowired
	private ApplicationFunctionService service;
	@Autowired
	private ApplicationFunctionChecker checker;
	public MessageDto addApplicationFunction(ApplicationFunctionDto dto) {
		MessageDto message = new MessageDto();
		try {
			if(dto.getCompanyId() == null || dto.getCompanyId().isEmpty()) {
				throw new IllegalArgumentException("The_companyId_may_not_be_set_to_null");
			}
			if(dto.getApplicationFunctionName() == null || dto.getApplicationFunctionName().isEmpty()) {
				throw new IllegalArgumentException("The_applicationFunctionName_may_not_be_set_to_null");
			}
			if(dto.getApplicationCategoryId() == null || dto.getApplicationCategoryId().isEmpty()) {
				throw new IllegalArgumentException("The_applicationCategoryId_may_not_be_set_to_null");
			}
			if(dto.getApplicationCategoryName() == null || dto.getApplicationCategoryName().isEmpty()) {
				throw new IllegalArgumentException("The_applicationCategoryName_may_not_be_set_to_null");
			}
			if(dto.getApplicationTaskId() == null || dto.getApplicationTaskId().isEmpty()) {
				throw new IllegalArgumentException("The_applicationTaskId_may_not_be_set_to_null");
			}
			if(dto.getApplicationTaskName() == null || dto.getApplicationTaskName().isEmpty()) {
				throw new IllegalArgumentException("The_applicationTaskName_may_not_be_set_to_null");
			}
			ApplicationCategory applicationCategory = applicationCategoryRepository.applicationCategoryOfId(new ApplicationCategoryId(dto.getApplicationCategoryId()));
			if(applicationCategory == null) {
				throw new IllegalArgumentException("The_applicationCategory_is_not_exist");
			}
			ApplicationFunctionId id = registry.nextIdentity();
			ApplicationFunction function = applicationCategory.defineApplicationFunction(id, dto.getApplicationFunctionName());
			function.setApplicationTaskId(new ApplicationTaskId(dto.getApplicationTaskId()));
			function.setApplicationTaskName(dto.getApplicationTaskName());
			if(dto.getApplicationFunctionDescription() != null && !dto.getApplicationFunctionDescription().isEmpty()) {
				function.setApplicationFunctionDescription(dto.getApplicationFunctionDescription());
			}
			if(dto.getUrl() != null && !dto.getUrl().isEmpty()) {
				function.setUrl(new Url(dto.getUrl()));
			}
			message.setReturnValue(id.getApplicationFunctionId());
			function.setCreatedAt(new Date());
			registry.save(function);
			message.setResult("OK");
		}catch(Exception ex) {
			message.setResult("NG");
			message.setErrorMessage(ex.getMessage());
			return message;
		}
		return message;
	}
	
	public MessageDto updateApplicationFunction(ApplicationFunctionDto dto) {
		MessageDto message = new MessageDto();
		try {
			if(dto.getApplicationFunctionId() == null || dto.getApplicationFunctionId().isEmpty()) {
				throw new IllegalArgumentException("The_applicationFunctionId_may_not_be_set_to_null");
			}
			ApplicationFunction function = registry.applicationFunctionOfId(new ApplicationFunctionId(dto.getApplicationFunctionId()));
			if(function == null) {
				throw new IllegalArgumentException("The_applicationFunction_dose_not_exist");
			}
			if(dto.getApplicationFunctionName() != null && !dto.getApplicationFunctionName().isEmpty()) {
				function.setApplicationFunctionName(dto.getApplicationFunctionName());
			}
			if(dto.getApplicationFunctionDescription() != null && !dto.getApplicationFunctionDescription().isEmpty()) {
				function.setApplicationFunctionDescription(dto.getApplicationFunctionDescription());
			}
			if(dto.getApplicationTaskId() != null && !dto.getApplicationTaskId().isEmpty()) {
				function.setApplicationTaskId(new ApplicationTaskId(dto.getApplicationTaskId()));
			}
			if(dto.getApplicationTaskName() != null && !dto.getApplicationTaskName().isEmpty()) {
				function.setApplicationTaskName(dto.getApplicationTaskName());
			}
			if(dto.getUrl() != null && !dto.getUrl().isEmpty()) {
				function.setUrl(new Url(dto.getUrl()));
			}else {
				
				function.setUrl(null);
			}
			//編集・削除チェック。
			if(!checker.avarable(dto.getApplicationFunctionId()).equals("OK")) {
				throw new Exception(checker.avarable(dto.getApplicationFunctionId()));
			}
			function.setUpdatedAt(new Date());
			registry.save(function);
			message.setResult("OK");
		}catch(Exception ex) {
			message.setResult("NG");
			message.setErrorMessage(ex.getMessage());
			return message;
		}
		return message;
	}
	
	public MessageDto removeApplicationFunction(String applicationFunctionId) {
		MessageDto message = new MessageDto();
		try {
			if(applicationFunctionId == null || applicationFunctionId.isEmpty()) {
				throw new IllegalArgumentException("The_applicationFunctionId_may_not_be_set_to_null");
			}
			ApplicationFunction function = registry.applicationFunctionOfId(new ApplicationFunctionId(applicationFunctionId));
			if(function == null) {
				throw new IllegalArgumentException("The_applicationFunction_dose_not_exist");
			}
			
			//編集・削除チェック。
			if(!checker.avarable(applicationFunctionId).equals("OK")) {
				throw new Exception(checker.avarable(applicationFunctionId));
			}
			registry.remove(function);
			message.setResult("OK");
		}catch(Exception ex) {
			message.setResult("NG");
			message.setErrorMessage(ex.getMessage());
			return message;
		}
		return message;
	}
	
	public ApplicationFunctionDto findApplicationFunctionOfId(String applicationFunctionId) throws Exception{
		if(applicationFunctionId == null || applicationFunctionId.isEmpty()) {
			throw new IllegalArgumentException("The_applicationFunctionId_may_not_be_set_to_null");
		}
		ApplicationFunction function = registry.applicationFunctionOfId(new ApplicationFunctionId(applicationFunctionId));
		return this.convertEntityToDto(function);
	}
	
	public List<ApplicationFunctionDto> findApplicationFunctionsOfCompany(String companyId) throws Exception{
		if(companyId == null || companyId.isEmpty()) {
			throw new IllegalArgumentException("The_companyId_may_not_be_set_to_null");
		}
		return this.convertDtos(registry.applicationFunctionsOfCompany(new CompanyId(companyId)));
	}
	
	public List<ApplicationFunctionDto> findApplicationFunctionsOfApplicationCategory(String applicationCategoryId) throws Exception{
		if(applicationCategoryId == null || applicationCategoryId.isEmpty()) {
			throw new IllegalArgumentException("The_applicationCategoryId_may_not_be_set_to_null");
		}
		return this.convertDtos(registry.applicationFunctionsOfApplicationCategory(new ApplicationCategoryId(applicationCategoryId)));
	}
	
	public List<ApplicationFunctionDto> findApplicationFunctionsOfApplicationTask(String applicationTaskId) throws Exception{
		if(applicationTaskId == null || applicationTaskId.isEmpty()) {
			throw new IllegalArgumentException("The_applicationTaskId_may_not_be_set_to_null");
		}
		return this.convertDtos(registry.applicationFunctionsOfApplicationTask(new ApplicationTaskId(applicationTaskId)));
	}
	
	//アプリケーション機能がアクセスすべきデータとアクセス方法を取得する
	public List<ApplicationData> findApplicationData(String applicationFunctionId) throws Exception{
		return service.findApplicationData(applicationFunctionId);
	}
	
	/*
	 * 変換処理
	 */
	private ApplicationFunctionDto convertEntityToDto(ApplicationFunction entity) {
		ApplicationFunctionDto pojo = new ApplicationFunctionDto();
		if(entity.getApplicationFunctionId() != null) {
			pojo.setApplicationFunctionId(entity.getApplicationFunctionId().getApplicationFunctionId());
		}
		if(entity.getCompanyId() != null) {
			pojo.setCompanyId(entity.getCompanyId().id());
		}
		if(entity.getApplicationCategoryId() != null) {
			pojo.setApplicationCategoryId(entity.getApplicationCategoryId().getApplicationCategoryId());
		}
		if(entity.getApplicationCategoryName() != null) {
			pojo.setApplicationCategoryName(entity.getApplicationCategoryName());
		}
		if(entity.getApplicationTaskId() != null) {
			pojo.setApplicationTaskId(entity.getApplicationTaskId().getApplicationTaskId());
		}
		if(entity.getApplicationTaskName() != null) {
			pojo.setApplicationTaskName(entity.getApplicationTaskName());
		}
		if(entity.getApplicationFunctionName() != null) {
			pojo.setApplicationFunctionName(entity.getApplicationFunctionName());
		}
		if(entity.getApplicationFunctionDescription() != null) {
			pojo.setApplicationFunctionDescription(entity.getApplicationFunctionDescription());
		}
		if(entity.getUrl() != null) {
			pojo.setUrl(entity.getUrl().getUrl());
		}
		
		
		
		return pojo;
	}
	
	private List<ApplicationFunctionDto> convertDtos(List<ApplicationFunction> functions) {
		List<ApplicationFunctionDto> dtos = new ArrayList<>();
		for(ApplicationFunction function : functions) {
			dtos.add(this.convertEntityToDto(function));
		}
		return dtos;
	}

}
