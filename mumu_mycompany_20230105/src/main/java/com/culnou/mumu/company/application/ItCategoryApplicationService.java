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

import com.culnou.mumu.company.domain.model.it.category.ItCategory;
import com.culnou.mumu.company.domain.model.it.category.ItCategoryId;
import com.culnou.mumu.company.domain.model.it.category.ItCategoryRegistry;
import com.culnou.mumu.company.domain.service.ItCategoryChecker;
import com.culnou.mumu.compnay.controller.ItCategoryDto;
import com.culnou.mumu.compnay.controller.MessageDto;

@Service
@Transactional
public class ItCategoryApplicationService {
	@Qualifier("businessUnitJpaRepository")
	@Autowired
	private BusinessUnitRepository businessUnitRepository;
	@Autowired
	private ItCategoryRegistry registry;
	@Autowired
	private ItCategoryChecker checker;
	
	public MessageDto addItCategory(ItCategoryDto dto) {
		MessageDto message = new MessageDto();
		try {
			//コントローラーの事前条件検証。
			if(dto.getBusinessUnitId() == null || dto.getBusinessUnitId().isEmpty()) {
				throw new IllegalArgumentException("The_businessUnitId_may_not_be_set_to_null");
			}
			if(dto.getCompanyId() == null || dto.getCompanyId().isEmpty()) {
				throw new IllegalArgumentException("The_companyId_may_not_be_set_to_null");
			}
			if(dto.getItCategoryName() == null || dto.getItCategoryName().isEmpty()) {
				throw new IllegalArgumentException("The_itCategoryName_may_not_be_set_to_null");
			}
			if(!(dto.getAssociatedItTypes().size() > 0)) {
				throw new IllegalArgumentException("The_itTypeId_may_not_be_set_to_null");
			}
			
			//ビジネスロジック
			BusinessUnit businessUnit = businessUnitRepository.businessUnitOfId(new BusinessUnitId(dto.getBusinessUnitId()));
			if(businessUnit == null) {
				throw new Exception("The_businessUnit_is_not_exist");
			}
			ItCategoryId itId = registry.nextIdentity();
			ItCategory itCategory = businessUnit.defineItCategory(itId, dto.getItCategoryName());
			itCategory.setAssociatedItTypes(dto.getAssociatedItTypes());
			itCategory.setItCategoryDescription(dto.getItCategoryDescription());
			if(!(dto.getUrl() == null || dto.getUrl().isEmpty())) {
				itCategory.setUrl(new Url(dto.getUrl()));
			}
			itCategory.setBusinessState(dto.getBusinessState());
			itCategory.setStartDate(dto.getStartDate());
			itCategory.setEndDate(dto.getEndDate());
			itCategory.setGoals(dto.getGoals());
			itCategory.setAchievements(dto.getAchievements());
			itCategory.setCreatedAt(new Date());
			registry.save(itCategory);
			message.setReturnValue(itId.getItCategoryId());
			message.setResult("OK");
		}catch(Exception ex) {
			message.setResult("NG");
			message.setErrorMessage(ex.getMessage());
			return message;
		}
		return message;
	}
	
	public MessageDto updateItCategory(String itCategoryId, ItCategoryDto dto) {
		MessageDto message = new MessageDto();
		try {
			//コントローラーの事前条件検証。
			if(itCategoryId == null || itCategoryId.isEmpty()) {
				throw new IllegalArgumentException("The_itCategoryId_may_not_be_set_to_null");
			}
			if(dto.getCompanyId() == null || dto.getCompanyId().isEmpty()) {
				throw new IllegalArgumentException("The_companyId_may_not_be_set_to_null");
			}
			if(dto.getItCategoryName() == null || dto.getItCategoryName().isEmpty()) {
				throw new IllegalArgumentException("The_itCategoryName_may_not_be_set_to_null");
			}
			if(!(dto.getAssociatedItTypes().size() > 0)) {
				throw new IllegalArgumentException("The_itTypeId_may_not_be_set_to_null");
			}
			//ビジネスロジック
			//IT基盤に割当てられている場合編集・削除できない。
			if(!checker.avarable(itCategoryId).equals("OK")) {
				throw new Exception(checker.avarable(itCategoryId));
			}
			//NULLでも空文字でも更新するようにする。
			ItCategory itCategory = registry.findItCategoryOfId(itCategoryId);
			if(itCategory == null) {
				throw new Exception("The_itCategory_is_not_exist");
			}
			itCategory.setAssociatedItTypes(dto.getAssociatedItTypes());
			itCategory.setItCategoryName(dto.getItCategoryName());
			itCategory.setItCategoryDescription(dto.getItCategoryDescription());
			if(!(dto.getUrl() == null || dto.getUrl().isEmpty())) {
				itCategory.setUrl(new Url(dto.getUrl()));
			}else {
				itCategory.setUrl(null);
			}
			itCategory.setBusinessState(dto.getBusinessState());
			itCategory.setStartDate(dto.getStartDate());
			itCategory.setEndDate(dto.getEndDate());
			itCategory.setGoals(dto.getGoals());
			itCategory.setAchievements(dto.getAchievements());
			itCategory.setUpdatedAt(new Date());
			registry.save(itCategory);
			message.setResult("OK");
		}catch(Exception ex){
			message.setResult("NG");
			message.setErrorMessage(ex.getMessage());
			return message;
		}
		return message;
	}
	
	public MessageDto removeItCategory(String itCategoryId) {
		MessageDto message = new MessageDto();
		try {
			if(itCategoryId == null || itCategoryId.isEmpty()) {
				throw new IllegalArgumentException("The_itCategoryId_may_not_be_set_to_null");
			}
			ItCategory itCategory = registry.findItCategoryOfId(itCategoryId);
			if(itCategory == null) {
				throw new Exception("The_itCategory_is_not_exist");
			}
			//IT基盤に割当てられている場合編集・削除できない。
			if(!checker.avarable(itCategoryId).equals("OK")) {
				throw new Exception(checker.avarable(itCategoryId));
			}
			registry.remove(itCategory);
			message.setResult("OK");
		}catch(Exception ex) {
			message.setResult("NG");
			message.setErrorMessage(ex.getMessage());
			return message;
		}
		return message;
	}
	
	public List<ItCategoryDto> findItCategoriesOfBusinessUnit(String businessUnitId) throws Exception{
		if(businessUnitId == null || businessUnitId.isEmpty()) {
			throw new IllegalArgumentException("The_businessUnitId_may_not_be_set_to_null");
		}
		List<ItCategory> itCategories = registry.findItCategoriesOfBusinessUnit(businessUnitId);
		List<ItCategoryDto> dtos = new ArrayList<>();
		for(ItCategory entity : itCategories) {
			dtos.add(this.convertItCategory(entity));
		}
		return dtos;
	}
	
	public List<ItCategoryDto> findItCategoriesOfCompany(String companyId) throws Exception{
		if(companyId == null || companyId.isEmpty()) {
			throw new IllegalArgumentException("The_companyId_may_not_be_set_to_null");
		}
		List<ItCategory> itCategories = registry.findItCategoriesOfCompany(companyId);
		List<ItCategoryDto> dtos = new ArrayList<>();
		for(ItCategory entity : itCategories) {
			dtos.add(this.convertItCategory(entity));
		}
		return dtos;
	}
	
	public List<ItCategoryDto> findItCategoriesOfItType(String itTypeId) throws Exception{
		if(itTypeId == null || itTypeId.isEmpty()) {
			throw new IllegalArgumentException("The_itTypeId_may_not_be_set_to_null");
		}
		List<ItCategory> itCategories = registry.findItCategoriesOfItType(itTypeId);
		List<ItCategoryDto> dtos = new ArrayList<>();
		for(ItCategory entity : itCategories) {
			dtos.add(this.convertItCategory(entity));
		}
		return dtos;
	}
	
	public ItCategoryDto findItCategoryOfId(String itCategoryId) throws Exception{
		if(itCategoryId == null || itCategoryId.isEmpty()) {
			throw new IllegalArgumentException("The_itCategoryId_may_not_be_set_to_null");
		}
		return this.convertItCategory(registry.findItCategoryOfId(itCategoryId));
	}
	
	private ItCategoryDto convertItCategory(ItCategory entity) {
		ItCategoryDto dto = new ItCategoryDto();
		dto.setItCategoryId(entity.getItCategoryId().getItCategoryId());
		dto.setItCategoryName(entity.getItCategoryName());
		dto.setItCategoryDescription(entity.getItCategoryDescription());
		dto.setCompanyId(entity.getCompanyId().id());
		dto.setBusinessUnitId(entity.getBusinessUnitId().businessUnitId());
		dto.setAssociatedItTypes(entity.getAssociatedItTypes());
		dto.setBusinessState(entity.getBusinessState());
		if(entity.getUrl() != null) {
			dto.setUrl(entity.getUrl().getUrl());
		}
		dto.setGoals(entity.getGoals());
		dto.setAchievements(entity.getAchievements());
		dto.setStartDate(entity.getStartDate());
		dto.setEndDate(entity.getEndDate());
		dto.setCreatedAt(entity.getCreatedAt());
		dto.setUpdatedAt(entity.getUpdatedAt());
		return dto;
	}


}
