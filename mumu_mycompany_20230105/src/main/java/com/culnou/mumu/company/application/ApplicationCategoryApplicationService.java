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
import com.culnou.mumu.company.domain.model.Url;
import com.culnou.mumu.company.domain.model.application.category.ApplicationCategory;
import com.culnou.mumu.company.domain.model.application.category.ApplicationCategoryId;
import com.culnou.mumu.company.domain.model.application.category.ApplicationCategoryRegistry;
import com.culnou.mumu.company.domain.service.ApplicationCategoryChecker;
import com.culnou.mumu.compnay.controller.ApplicationCategoryDto;
import com.culnou.mumu.compnay.controller.MessageDto;

@Service
@Transactional
public class ApplicationCategoryApplicationService {
	@Qualifier("businessUnitJpaRepository")
	@Autowired
	private BusinessUnitRepository businessUnitRepository;
	@Autowired
	private ApplicationCategoryRegistry registry;
	@Autowired
	private ApplicationCategoryChecker checker;
	
	public MessageDto addApplicationCategory(ApplicationCategoryDto dto) {
		MessageDto message = new MessageDto();
		try {
			//コントローラーの事前条件検証。
			if(dto.getBusinessUnitId() == null || dto.getBusinessUnitId().isEmpty()) {
				throw new IllegalArgumentException("The_businessUnitId_may_not_be_set_to_null");
			}
			if(dto.getCompanyId() == null || dto.getCompanyId().isEmpty()) {
				throw new IllegalArgumentException("The_companyId_may_not_be_set_to_null");
			}
			if(dto.getApplicationCategoryName() == null || dto.getApplicationCategoryName().isEmpty()) {
				throw new IllegalArgumentException("The_applicationCategoryName_may_not_be_set_to_null");
			}
			if(!(dto.getAssociatedApplicationTypes().size() > 0)) {
				throw new IllegalArgumentException("The_applicationTypeId_may_not_be_set_to_null");
			}
			
			//ビジネスロジック
			BusinessUnit businessUnit = businessUnitRepository.businessUnitOfId(new BusinessUnitId(dto.getBusinessUnitId()));
			if(businessUnit == null) {
				throw new Exception("The_businessUnit_is_not_exist");
			}
			ApplicationCategoryId applicationId = registry.nextIdentity();
			ApplicationCategory applicationCategory = businessUnit.defineApplicationCategory(applicationId, dto.getApplicationCategoryName());
			applicationCategory.setAssociatedApplicationTypes(dto.getAssociatedApplicationTypes());
			applicationCategory.setApplicationCategoryDescription(dto.getApplicationCategoryDescription());
			if(!(dto.getUrl() == null || dto.getUrl().isEmpty())) {
				applicationCategory.setUrl(new Url(dto.getUrl()));
			}
			applicationCategory.setBusinessState(dto.getBusinessState());
			applicationCategory.setStartDate(dto.getStartDate());
			applicationCategory.setEndDate(dto.getEndDate());
			applicationCategory.setGoals(dto.getGoals());
			applicationCategory.setAchievements(dto.getAchievements());
			applicationCategory.setCreatedAt(new Date());
			registry.save(applicationCategory);
			message.setReturnValue(applicationId.getApplicationCategoryId());
			message.setResult("OK");
		}catch(Exception ex) {
			message.setResult("NG");
			message.setErrorMessage(ex.getMessage());
			return message;
		}
		return message;
	}
	
	public MessageDto updateApplicationCategory(String applicationCategoryId, ApplicationCategoryDto dto) {
		MessageDto message = new MessageDto();
		try {
			//コントローラーの事前条件検証。
			if(applicationCategoryId == null || applicationCategoryId.isEmpty()) {
				throw new IllegalArgumentException("The_applicationCategoryId_may_not_be_set_to_null");
			}
			if(dto.getCompanyId() == null || dto.getCompanyId().isEmpty()) {
				throw new IllegalArgumentException("The_companyId_may_not_be_set_to_null");
			}
			if(dto.getApplicationCategoryName() == null || dto.getApplicationCategoryName().isEmpty()) {
				throw new IllegalArgumentException("The_applicationCategoryName_may_not_be_set_to_null");
			}
			if(!(dto.getAssociatedApplicationTypes().size() > 0)) {
				throw new IllegalArgumentException("The_applicationTypeId_may_not_be_set_to_null");
			}
			//ビジネスロジック
			//編集・削除チェック。
			if(!checker.avarable(applicationCategoryId).equals("OK")) {
				throw new Exception(checker.avarable(applicationCategoryId));
			}
			//NULLでも空文字でも更新するようにする。
			ApplicationCategory applicationCategory = registry.findApplicationCategoryOfId(applicationCategoryId);
			if(applicationCategory == null) {
				throw new Exception("The_applicationCategory_is_not_exist");
			}
			applicationCategory.setAssociatedApplicationTypes(dto.getAssociatedApplicationTypes());
			applicationCategory.setApplicationCategoryName(dto.getApplicationCategoryName());
			applicationCategory.setApplicationCategoryDescription(dto.getApplicationCategoryDescription());
			if(!(dto.getUrl() == null || dto.getUrl().isEmpty())) {
				applicationCategory.setUrl(new Url(dto.getUrl()));
			}else {
				applicationCategory.setUrl(null);
			}
			applicationCategory.setBusinessState(dto.getBusinessState());
			applicationCategory.setStartDate(dto.getStartDate());
			applicationCategory.setEndDate(dto.getEndDate());
			applicationCategory.setGoals(dto.getGoals());
			applicationCategory.setAchievements(dto.getAchievements());
			applicationCategory.setUpdatedAt(new Date());
			registry.save(applicationCategory);
			message.setResult("OK");
		}catch(Exception ex){
			message.setResult("NG");
			message.setErrorMessage(ex.getMessage());
			return message;
		}
		return message;
	}
	
	public MessageDto removeApplicationCategory(String applicationCategoryId) {
		MessageDto message = new MessageDto();
		try {
			if(applicationCategoryId == null || applicationCategoryId.isEmpty()) {
				throw new IllegalArgumentException("The_applicationCategoryId_may_not_be_set_to_null");
			}
			ApplicationCategory applicationCategory = registry.findApplicationCategoryOfId(applicationCategoryId);
			if(applicationCategory == null) {
				throw new Exception("The_applicationCategory_is_not_exist");
			}
			//編集・削除チェック。
			if(!checker.avarable(applicationCategoryId).equals("OK")) {
				throw new Exception(checker.avarable(applicationCategoryId));
			}
			registry.remove(applicationCategory);
			message.setResult("OK");
		}catch(Exception ex) {
			message.setResult("NG");
			message.setErrorMessage(ex.getMessage());
			return message;
		}
		return message;
	}
	
	public List<ApplicationCategoryDto> findApplicationCategoriesOfBusinessUnit(String businessUnitId) throws Exception{
		if(businessUnitId == null || businessUnitId.isEmpty()) {
			throw new IllegalArgumentException("The_businessUnitId_may_not_be_set_to_null");
		}
		List<ApplicationCategory> applicationCategories = registry.findApplicationCategoriesOfBusinessUnit(businessUnitId);
		List<ApplicationCategoryDto> dtos = new ArrayList<>();
		for(ApplicationCategory entity : applicationCategories) {
			dtos.add(this.convertApplicationCategory(entity));
		}
		return dtos;
	}
	
	public List<ApplicationCategoryDto> findApplicationCategoriesOfCompany(String companyId) throws Exception{
		if(companyId == null || companyId.isEmpty()) {
			throw new IllegalArgumentException("The_companyId_may_not_be_set_to_null");
		}
		List<ApplicationCategory> applicationCategories = registry.findApplicationCategoriesOfCompany(companyId);
		List<ApplicationCategoryDto> dtos = new ArrayList<>();
		for(ApplicationCategory entity : applicationCategories) {
			dtos.add(this.convertApplicationCategory(entity));
		}
		return dtos;
	}
	
	public List<ApplicationCategoryDto> findApplicationCategoriesOfApplicationType(String applicationTypeId) throws Exception{
		if(applicationTypeId == null || applicationTypeId.isEmpty()) {
			throw new IllegalArgumentException("The_applicationTypeId_may_not_be_set_to_null");
		}
		List<ApplicationCategory> applicationCategories = registry.findApplicationCategoriesOfApplicationType(applicationTypeId);
		List<ApplicationCategoryDto> dtos = new ArrayList<>();
		for(ApplicationCategory entity : applicationCategories) {
			dtos.add(this.convertApplicationCategory(entity));
		}
		return dtos;
	}
	
	public ApplicationCategoryDto findApplicationCategoryOfId(String applicationCategoryId) throws Exception{
		if(applicationCategoryId == null || applicationCategoryId.isEmpty()) {
			throw new IllegalArgumentException("The_applicationCategoryId_may_not_be_set_to_null");
		}
		return this.convertApplicationCategory(registry.findApplicationCategoryOfId(applicationCategoryId));
	}
	
	private ApplicationCategoryDto convertApplicationCategory(ApplicationCategory entity) {
		ApplicationCategoryDto dto = new ApplicationCategoryDto();
		dto.setApplicationCategoryId(entity.getApplicationCategoryId().getApplicationCategoryId());
		dto.setApplicationCategoryName(entity.getApplicationCategoryName());
		dto.setApplicationCategoryDescription(entity.getApplicationCategoryDescription());
		dto.setCompanyId(entity.getCompanyId().id());
		dto.setBusinessUnitId(entity.getBusinessUnitId().businessUnitId());
		dto.setAssociatedApplicationTypes(entity.getAssociatedApplicationTypes());
		dto.setBusinessState(entity.getBusinessState());
		dto.setGoals(entity.getGoals());
		dto.setAchievements(entity.getAchievements());
		dto.setStartDate(entity.getStartDate());
		dto.setEndDate(entity.getEndDate());
		dto.setCreatedAt(entity.getCreatedAt());
		dto.setUpdatedAt(entity.getUpdatedAt());
		return dto;
	}

}
