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

import com.culnou.mumu.company.domain.model.partner.category.PartnerCategory;
import com.culnou.mumu.company.domain.model.partner.category.PartnerCategoryId;
import com.culnou.mumu.company.domain.model.partner.category.PartnerCategoryRegistry;
import com.culnou.mumu.company.domain.service.PartnerCategoryChecker;
import com.culnou.mumu.compnay.controller.PartnerCategoryDto;
import com.culnou.mumu.compnay.controller.MessageDto;

@Service
@Transactional
public class PartnerCategoryApplicationService {
	@Qualifier("businessUnitJpaRepository")
	@Autowired
	private BusinessUnitRepository businessUnitRepository;
	@Autowired
	private PartnerCategoryRegistry registry;
	@Autowired
	private PartnerCategoryChecker checker;
	
	public MessageDto addPartnerCategory(PartnerCategoryDto dto) {
		MessageDto message = new MessageDto();
		try {
			//コントローラーの事前条件検証。
			if(dto.getBusinessUnitId() == null || dto.getBusinessUnitId().isEmpty()) {
				throw new IllegalArgumentException("The_businessUnitId_may_not_be_set_to_null");
			}
			if(dto.getCompanyId() == null || dto.getCompanyId().isEmpty()) {
				throw new IllegalArgumentException("The_companyId_may_not_be_set_to_null");
			}
			if(dto.getPartnerCategoryName() == null || dto.getPartnerCategoryName().isEmpty()) {
				throw new IllegalArgumentException("The_partnerCategoryName_may_not_be_set_to_null");
			}
			if(!(dto.getAssociatedPartnerTypes().size() > 0)) {
				throw new IllegalArgumentException("The_partnerTypeId_may_not_be_set_to_null");
			}
			
			//ビジネスロジック
			BusinessUnit businessUnit = businessUnitRepository.businessUnitOfId(new BusinessUnitId(dto.getBusinessUnitId()));
			if(businessUnit == null) {
				throw new Exception("The_businessUnit_is_not_exist");
			}
			PartnerCategoryId partnerId = registry.nextIdentity();
			PartnerCategory partnerCategory = businessUnit.definePartnerCategory(partnerId, dto.getPartnerCategoryName());
			partnerCategory.setAssociatedPartnerTypes(dto.getAssociatedPartnerTypes());
			partnerCategory.setPartnerCategoryDescription(dto.getPartnerCategoryDescription());
			if(!(dto.getUrl() == null || dto.getUrl().isEmpty())) {
				partnerCategory.setUrl(new Url(dto.getUrl()));
			}
			partnerCategory.setBusinessState(dto.getBusinessState());
			partnerCategory.setStartDate(dto.getStartDate());
			partnerCategory.setEndDate(dto.getEndDate());
			partnerCategory.setGoals(dto.getGoals());
			partnerCategory.setAchievements(dto.getAchievements());
			partnerCategory.setCreatedAt(new Date());
			registry.save(partnerCategory);
			message.setReturnValue(partnerId.getPartnerCategoryId());
			message.setResult("OK");
		}catch(Exception ex) {
			message.setResult("NG");
			message.setErrorMessage(ex.getMessage());
			return message;
		}
		return message;
	}
	
	public MessageDto updatePartnerCategory(String partnerCategoryId, PartnerCategoryDto dto) {
		MessageDto message = new MessageDto();
		try {
			//コントローラーの事前条件検証。
			if(partnerCategoryId == null || partnerCategoryId.isEmpty()) {
				throw new IllegalArgumentException("The_partnerCategoryId_may_not_be_set_to_null");
			}
			if(dto.getCompanyId() == null || dto.getCompanyId().isEmpty()) {
				throw new IllegalArgumentException("The_companyId_may_not_be_set_to_null");
			}
			if(dto.getPartnerCategoryName() == null || dto.getPartnerCategoryName().isEmpty()) {
				throw new IllegalArgumentException("The_partnerCategoryName_may_not_be_set_to_null");
			}
			if(!(dto.getAssociatedPartnerTypes().size() > 0)) {
				throw new IllegalArgumentException("The_partnerTypeId_may_not_be_set_to_null");
			}
			//ビジネスロジック
			//プロジェクトやプログラムに割当てられている場合編集・削除できない。
			if(!checker.avarable(partnerCategoryId).equals("OK")) {
				throw new Exception(checker.avarable(partnerCategoryId));
			}
			//NULLでも空文字でも更新するようにする。
			PartnerCategory partnerCategory = registry.findPartnerCategoryOfId(partnerCategoryId);
			if(partnerCategory == null) {
				throw new Exception("The_partnerCategory_is_not_exist");
			}
			partnerCategory.setAssociatedPartnerTypes(dto.getAssociatedPartnerTypes());
			partnerCategory.setPartnerCategoryName(dto.getPartnerCategoryName());
			partnerCategory.setPartnerCategoryDescription(dto.getPartnerCategoryDescription());
			if(!(dto.getUrl() == null || dto.getUrl().isEmpty())) {
				partnerCategory.setUrl(new Url(dto.getUrl()));
			}else {
				partnerCategory.setUrl(null);
			}
			partnerCategory.setBusinessState(dto.getBusinessState());
			partnerCategory.setStartDate(dto.getStartDate());
			partnerCategory.setEndDate(dto.getEndDate());
			partnerCategory.setGoals(dto.getGoals());
			partnerCategory.setAchievements(dto.getAchievements());
			partnerCategory.setUpdatedAt(new Date());
			registry.save(partnerCategory);
			message.setResult("OK");
		}catch(Exception ex){
			message.setResult("NG");
			message.setErrorMessage(ex.getMessage());
			return message;
		}
		return message;
	}
	
	public MessageDto removePartnerCategory(String partnerCategoryId) {
		MessageDto message = new MessageDto();
		try {
			if(partnerCategoryId == null || partnerCategoryId.isEmpty()) {
				throw new IllegalArgumentException("The_partnerCategoryId_may_not_be_set_to_null");
			}
			PartnerCategory partnerCategory = registry.findPartnerCategoryOfId(partnerCategoryId);
			if(partnerCategory == null) {
				throw new Exception("The_partnerCategory_is_not_exist");
			}
			//編集・削除チェック。
			if(!checker.avarable(partnerCategoryId).equals("OK")) {
				throw new Exception(checker.avarable(partnerCategoryId));
			}
			registry.remove(partnerCategory);
			message.setResult("OK");
		}catch(Exception ex) {
			message.setResult("NG");
			message.setErrorMessage(ex.getMessage());
			return message;
		}
		return message;
	}
	
	public List<PartnerCategoryDto> findPartnerCategoriesOfBusinessUnit(String businessUnitId) throws Exception{
		if(businessUnitId == null || businessUnitId.isEmpty()) {
			throw new IllegalArgumentException("The_businessUnitId_may_not_be_set_to_null");
		}
		List<PartnerCategory> partnerCategories = registry.findPartnerCategoriesOfBusinessUnit(businessUnitId);
		List<PartnerCategoryDto> dtos = new ArrayList<>();
		for(PartnerCategory entity : partnerCategories) {
			dtos.add(this.convertPartnerCategory(entity));
		}
		return dtos;
	}
	
	public List<PartnerCategoryDto> findPartnerCategoriesOfCompany(String companyId) throws Exception{
		if(companyId == null || companyId.isEmpty()) {
			throw new IllegalArgumentException("The_companyId_may_not_be_set_to_null");
		}
		List<PartnerCategory> partnerCategories = registry.findPartnerCategoriesOfCompany(companyId);
		List<PartnerCategoryDto> dtos = new ArrayList<>();
		for(PartnerCategory entity : partnerCategories) {
			dtos.add(this.convertPartnerCategory(entity));
		}
		return dtos;
	}
	
	public List<PartnerCategoryDto> findPartnerCategoriesOfPartnerType(String partnerTypeId) throws Exception{
		if(partnerTypeId == null || partnerTypeId.isEmpty()) {
			throw new IllegalArgumentException("The_partnerTypeId_may_not_be_set_to_null");
		}
		List<PartnerCategory> partnerCategories = registry.findPartnerCategoriesOfPartnerType(partnerTypeId);
		List<PartnerCategoryDto> dtos = new ArrayList<>();
		for(PartnerCategory entity : partnerCategories) {
			dtos.add(this.convertPartnerCategory(entity));
		}
		return dtos;
	}
	
	public PartnerCategoryDto findPartnerCategoryOfId(String partnerCategoryId) throws Exception{
		if(partnerCategoryId == null || partnerCategoryId.isEmpty()) {
			throw new IllegalArgumentException("The_partnerCategoryId_may_not_be_set_to_null");
		}
		return this.convertPartnerCategory(registry.findPartnerCategoryOfId(partnerCategoryId));
	}
	
	private PartnerCategoryDto convertPartnerCategory(PartnerCategory entity) {
		PartnerCategoryDto dto = new PartnerCategoryDto();
		dto.setPartnerCategoryId(entity.getPartnerCategoryId().getPartnerCategoryId());
		dto.setPartnerCategoryName(entity.getPartnerCategoryName());
		dto.setPartnerCategoryDescription(entity.getPartnerCategoryDescription());
		dto.setCompanyId(entity.getCompanyId().id());
		dto.setBusinessUnitId(entity.getBusinessUnitId().businessUnitId());
		dto.setAssociatedPartnerTypes(entity.getAssociatedPartnerTypes());
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
