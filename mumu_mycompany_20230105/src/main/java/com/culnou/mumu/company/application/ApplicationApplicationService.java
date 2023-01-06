package com.culnou.mumu.company.application;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import com.culnou.mumu.company.domain.model.BusinessUnit;
import com.culnou.mumu.company.domain.model.BusinessUnitId;
import com.culnou.mumu.company.domain.model.BusinessUnitRepository;
import com.culnou.mumu.company.domain.model.CompanyId;
import com.culnou.mumu.company.domain.model.Url;
import com.culnou.mumu.company.domain.model.application.category.ApplicationCategory;
import com.culnou.mumu.company.domain.model.application.category.ApplicationCategoryId;
import com.culnou.mumu.company.domain.model.application.category.ApplicationCategoryRepository;
import com.culnou.mumu.company.domain.model.application.instance.Application;
import com.culnou.mumu.company.domain.model.application.instance.ApplicationRegistry;

import com.culnou.mumu.company.domain.model.it.instance.ItId;

import com.culnou.mumu.compnay.controller.ApplicationDto;

import com.culnou.mumu.compnay.controller.MessageDto;

@Service
@Transactional
public class ApplicationApplicationService {
	@Qualifier("businessUnitJpaRepository")
	@Autowired
	private BusinessUnitRepository businessUnitRepository;
	@Qualifier("applicationCategoryJpaRepository")
	@Autowired
	private ApplicationCategoryRepository applicationCategoryRepository;
	@Autowired
	private ApplicationRegistry registry;
	
	public MessageDto addApplication(ApplicationDto dto) {
		MessageDto message = new MessageDto();
		try {
			if(dto == null) {
				throw new IllegalArgumentException("The_dto_may_not_be_set_to_null");
			}
			if(dto.getCompanyId() == null || dto.getCompanyId().isEmpty()) {
				throw new IllegalArgumentException("The_companyId_may_not_be_set_to_null");
			}
			if(dto.getApplicationName() == null || dto.getApplicationName().isEmpty()) {
				throw new IllegalArgumentException("The_applicationName_may_not_be_set_to_null");
			}
			if(dto.getBusinessUnitId() == null || dto.getBusinessUnitId().isEmpty()) {
				throw new IllegalArgumentException("The_businessUnitId_may_not_be_set_to_null");
			}
			if(dto.getApplicationCategoryId() == null || dto.getApplicationCategoryId().isEmpty()) {
				throw new IllegalArgumentException("The_applicationCategoryId_may_not_be_set_to_null");
			}
			//ビジネスロジック
			BusinessUnit businessUnit = businessUnitRepository.businessUnitOfId(new BusinessUnitId(dto.getBusinessUnitId()));
			if(businessUnit == null) {
				throw new Exception("The_businessUnit_is_not_exist");
			}
			ApplicationCategory applicationCategory = applicationCategoryRepository.applicationCategoryOfId(new ApplicationCategoryId(dto.getApplicationCategoryId()));
			if(applicationCategory == null) {
				throw new Exception("The_applicationCategory_is_not_exist");
			}
			Application application = applicationCategory.defineApplication(registry.nextIdentapplicationy(), dto.getApplicationName(), new CompanyId(dto.getCompanyId()), new BusinessUnitId(dto.getBusinessUnitId()));
			application.setApplicationDescription(dto.getApplicationDescription());
			application.setApplicationCategoryName(dto.getApplicationCategoryName());
			if(!(dto.getItId() == null || dto.getItId().isEmpty())) {
				application.setItId(new ItId(dto.getItId()));
			}
			
			if(!(dto.getUrl() == null || dto.getUrl().isEmpty())) {
				application.setUrl(new Url(dto.getUrl()));
			}
			application.setStartDate(dto.getStartDate());
			application.setEndDate(dto.getEndDate());
			application.setCreatedAt(new Date());
	        registry.save(application);
	        message.setReturnValue(application.getApplicationId().getApplicationId());
			message.setResult("OK");
		}catch(Exception ex) {
			message.setResult("NG");
			message.setErrorMessage(ex.getMessage());
			return message;
		}
		return message;
		
	}
	
	public MessageDto updateApplication(ApplicationDto dto) {
		MessageDto message = new MessageDto();
		try {
			if(dto == null) {
				throw new IllegalArgumentException("The_dto_may_not_be_set_to_null");
			}
			if(dto.getApplicationId() == null || dto.getApplicationId().isEmpty()) {
				throw new IllegalArgumentException("The_applicationId_may_not_be_set_to_null");
			}
			if(dto.getCompanyId() == null || dto.getCompanyId().isEmpty()) {
				throw new IllegalArgumentException("The_companyId_may_not_be_set_to_null");
			}
			if(dto.getApplicationName() == null || dto.getApplicationName().isEmpty()) {
				throw new IllegalArgumentException("The_applicationName_may_not_be_set_to_null");
			}
			if(dto.getBusinessUnitId() == null || dto.getBusinessUnitId().isEmpty()) {
				throw new IllegalArgumentException("The_businessUnitId_may_not_be_set_to_null");
			}
			if(dto.getApplicationCategoryId() == null || dto.getApplicationCategoryId().isEmpty()) {
				throw new IllegalArgumentException("The_applicationCategoryId_may_not_be_set_to_null");
			}
			//ビジネスロジック
			Application application = registry.findApplicationOfId(dto.getApplicationId());
			if(application == null) {
				throw new Exception("The_application_is_not_exist");
			}else {
				application.setItId(null);			
			}
			if(!(dto.getUrl() == null || dto.getUrl().isEmpty())) {
				application.setUrl(new Url(dto.getUrl()));
			}else {
				application.setUrl(null);
			}
			application.setStartDate(dto.getStartDate());
			application.setEndDate(dto.getEndDate());
			application.setApplicationName(dto.getApplicationName());
			application.setApplicationDescription(dto.getApplicationDescription());
			application.setApplicationCategoryId(new ApplicationCategoryId(dto.getApplicationCategoryId()));
			application.setApplicationCategoryName(dto.getApplicationCategoryName());
			if(!(dto.getItId() == null || dto.getItId().isEmpty())) {
				application.setItId(new ItId(dto.getItId()));
			}
			application.setUpdatedAt(new Date());
			registry.save(application);
			message.setResult("OK");
		}catch(Exception ex) {
			message.setResult("NG");
			message.setErrorMessage(ex.getMessage());
			return message;
		}
		return message;
		
	}
	
	public MessageDto removeApplication(String applicationId) {
		MessageDto message = new MessageDto();
		try {
			if(applicationId == null || applicationId.isEmpty()) {
				throw new IllegalArgumentException("The_applicationId_may_not_be_set_to_null");
			}
			Application application = registry.findApplicationOfId(applicationId);
			if(application == null) {
				throw new Exception("The_application_is_not_exist");
			}
			registry.remove(application);
			message.setResult("OK");
		}catch(Exception ex) {
			message.setResult("NG");
			message.setErrorMessage(ex.getMessage());
			return message;
		}
		return message;
		
	}
	public ApplicationDto findApplicationOfId(String applicationId) throws Exception{
		if(applicationId == null || applicationId.isEmpty()) {
			throw new IllegalArgumentException("The_applicationId_may_not_be_set_to_null");
		}
		return this.convertApplication(registry.findApplicationOfId(applicationId));
	}
	
	public List<ApplicationDto> findApplicationsOfBusinessUnit(String businessUnitId) throws Exception{
		if(businessUnitId == null || businessUnitId.isEmpty()) {
			throw new IllegalArgumentException("The_businessUnitId_may_not_be_set_to_null");
		}
		List<Application> applications = registry.findApplicationsOfBusinessUnitId(businessUnitId);
		List<ApplicationDto> dtos = new ArrayList<>();
		for(Application entapplicationy : applications) {
			dtos.add(this.convertApplication(entapplicationy));
		}
		return dtos;
	}
	
	public List<ApplicationDto> findApplicationsOfCompany(String companyId) throws Exception{
		if(companyId == null || companyId.isEmpty()) {
			throw new IllegalArgumentException("The_companyId_may_not_be_set_to_null");
		}
		List<Application> applications = registry.findApplicationsOfCompany(companyId);
		List<ApplicationDto> dtos = new ArrayList<>();
		for(Application entapplicationy : applications) {
			dtos.add(this.convertApplication(entapplicationy));
		}
		return dtos;
	}
	
	public List<ApplicationDto> findApplicationsOfApplicationCategory(String applicationCategoryId) throws Exception{
		if(applicationCategoryId == null || applicationCategoryId.isEmpty()) {
			throw new IllegalArgumentException("The_applicationCategoryId_may_not_be_set_to_null");
		}
		List<Application> applications = registry.findApplicationsOfApplicationCategory(applicationCategoryId);
		List<ApplicationDto> dtos = new ArrayList<>();
		for(Application entapplicationy : applications) {
			dtos.add(this.convertApplication(entapplicationy));
		}
		return dtos;
	}
	
	private ApplicationDto convertApplication(Application entapplicationy) {
		ApplicationDto dto = new ApplicationDto();
		dto.setApplicationId(entapplicationy.getApplicationId().getApplicationId());
		dto.setApplicationName(entapplicationy.getApplicationName());
		dto.setApplicationDescription(entapplicationy.getApplicationDescription());
		dto.setCompanyId(entapplicationy.getCompanyId().id());
		dto.setBusinessUnitId(entapplicationy.getBusinessUnitId().businessUnitId());
		dto.setApplicationCategoryId(entapplicationy.getApplicationCategoryId().getApplicationCategoryId());
		dto.setApplicationCategoryName(entapplicationy.getApplicationCategoryName());
		if(entapplicationy.getItId() != null) {
			dto.setItId(entapplicationy.getItId().getItId());
		}
		if(entapplicationy.getUrl() != null) {
			dto.setUrl(entapplicationy.getUrl().getUrl());
		}
		dto.setStartDate(entapplicationy.getStartDate());
		dto.setEndDate(entapplicationy.getEndDate());
		dto.setCreatedAt(entapplicationy.getCreatedAt());
		dto.setUpdatedAt(entapplicationy.getUpdatedAt());
		return dto;
	}

}
