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

import com.culnou.mumu.company.domain.model.financial.asset.category.FinancialAssetCategory;
import com.culnou.mumu.company.domain.model.financial.asset.category.FinancialAssetCategoryId;
import com.culnou.mumu.company.domain.model.financial.asset.category.FinancialAssetCategoryRegistry;
import com.culnou.mumu.company.domain.service.FinancialAssetCategoryChecker;
import com.culnou.mumu.compnay.controller.FinancialAssetCategoryDto;
import com.culnou.mumu.compnay.controller.MessageDto;

@Service
@Transactional
public class FinancialAssetCategoryApplicationService {
	@Qualifier("businessUnitJpaRepository")
	@Autowired
	private BusinessUnitRepository businessUnitRepository;
	@Autowired
	private FinancialAssetCategoryRegistry registry;
	@Autowired
	private FinancialAssetCategoryChecker checker;
	
	public MessageDto addFinancialAssetCategory(FinancialAssetCategoryDto dto) {
		MessageDto message = new MessageDto();
		try {
			//コントローラーの事前条件検証。
			if(dto.getBusinessUnitId() == null || dto.getBusinessUnitId().isEmpty()) {
				throw new IllegalArgumentException("The_businessUnitId_may_not_be_set_to_null");
			}
			if(dto.getCompanyId() == null || dto.getCompanyId().isEmpty()) {
				throw new IllegalArgumentException("The_companyId_may_not_be_set_to_null");
			}
			if(dto.getFinancialAssetCategoryName() == null || dto.getFinancialAssetCategoryName().isEmpty()) {
				throw new IllegalArgumentException("The_financialAssetCategoryName_may_not_be_set_to_null");
			}
			if(!(dto.getAssociatedFinancialAssetTypes().size() > 0)) {
				throw new IllegalArgumentException("The_financialAssetTypeId_may_not_be_set_to_null");
			}
			
			//ビジネスロジック
			BusinessUnit businessUnit = businessUnitRepository.businessUnitOfId(new BusinessUnitId(dto.getBusinessUnitId()));
			if(businessUnit == null) {
				throw new Exception("The_businessUnit_is_not_exist");
			}
			FinancialAssetCategoryId financialAssetId = registry.nextIdentity();
			FinancialAssetCategory financialAssetCategory = businessUnit.defineFinancialAssetCategory(financialAssetId, dto.getFinancialAssetCategoryName());
			financialAssetCategory.setAssociatedFinancialAssetTypes(dto.getAssociatedFinancialAssetTypes());
			financialAssetCategory.setFinancialAssetCategoryDescription(dto.getFinancialAssetCategoryDescription());
			if(!(dto.getUrl() == null || dto.getUrl().isEmpty())) {
				financialAssetCategory.setUrl(new Url(dto.getUrl()));
			}
			financialAssetCategory.setBusinessState(dto.getBusinessState());
			financialAssetCategory.setStartDate(dto.getStartDate());
			financialAssetCategory.setEndDate(dto.getEndDate());
			financialAssetCategory.setGoals(dto.getGoals());
			financialAssetCategory.setAchievements(dto.getAchievements());
			financialAssetCategory.setCreatedAt(new Date());
			registry.save(financialAssetCategory);
			message.setReturnValue(financialAssetId.getFinancialAssetCategoryId());
			message.setResult("OK");
		}catch(Exception ex) {
			message.setResult("NG");
			message.setErrorMessage(ex.getMessage());
			return message;
		}
		return message;
	}
	
	public MessageDto updateFinancialAssetCategory(String financialAssetCategoryId, FinancialAssetCategoryDto dto) {
		MessageDto message = new MessageDto();
		try {
			//コントローラーの事前条件検証。
			if(financialAssetCategoryId == null || financialAssetCategoryId.isEmpty()) {
				throw new IllegalArgumentException("The_financialAssetCategoryId_may_not_be_set_to_null");
			}
			if(dto.getCompanyId() == null || dto.getCompanyId().isEmpty()) {
				throw new IllegalArgumentException("The_companyId_may_not_be_set_to_null");
			}
			if(dto.getFinancialAssetCategoryName() == null || dto.getFinancialAssetCategoryName().isEmpty()) {
				throw new IllegalArgumentException("The_financialAssetCategoryName_may_not_be_set_to_null");
			}
			if(!(dto.getAssociatedFinancialAssetTypes().size() > 0)) {
				throw new IllegalArgumentException("The_financialAssetTypeId_may_not_be_set_to_null");
			}
			//ビジネスロジック
			//編集・削除チェック。
			if(!checker.avarable(financialAssetCategoryId).equals("OK")) {
				throw new Exception(checker.avarable(financialAssetCategoryId));
			}
			//NULLでも空文字でも更新するようにする。
			FinancialAssetCategory financialAssetCategory = registry.findFinancialAssetCategoryOfId(financialAssetCategoryId);
			if(financialAssetCategory == null) {
				throw new Exception("The_financialAssetCategory_is_not_exist");
			}
			financialAssetCategory.setAssociatedFinancialAssetTypes(dto.getAssociatedFinancialAssetTypes());
			financialAssetCategory.setFinancialAssetCategoryName(dto.getFinancialAssetCategoryName());
			financialAssetCategory.setFinancialAssetCategoryDescription(dto.getFinancialAssetCategoryDescription());
			if(!(dto.getUrl() == null || dto.getUrl().isEmpty())) {
				financialAssetCategory.setUrl(new Url(dto.getUrl()));
			}else {
				financialAssetCategory.setUrl(null);
			}
			financialAssetCategory.setBusinessState(dto.getBusinessState());
			financialAssetCategory.setStartDate(dto.getStartDate());
			financialAssetCategory.setEndDate(dto.getEndDate());
			financialAssetCategory.setGoals(dto.getGoals());
			financialAssetCategory.setAchievements(dto.getAchievements());
			financialAssetCategory.setUpdatedAt(new Date());
			registry.save(financialAssetCategory);
			message.setResult("OK");
		}catch(Exception ex){
			message.setResult("NG");
			message.setErrorMessage(ex.getMessage());
			return message;
		}
		return message;
	}
	
	public MessageDto removeFinancialAssetCategory(String financialAssetCategoryId) {
		MessageDto message = new MessageDto();
		try {
			if(financialAssetCategoryId == null || financialAssetCategoryId.isEmpty()) {
				throw new IllegalArgumentException("The_financialAssetCategoryId_may_not_be_set_to_null");
			}
			FinancialAssetCategory financialAssetCategory = registry.findFinancialAssetCategoryOfId(financialAssetCategoryId);
			if(financialAssetCategory == null) {
				throw new Exception("The_financialAssetCategory_is_not_exist");
			}
			//編集・削除チェック。
			if(!checker.avarable(financialAssetCategoryId).equals("OK")) {
				throw new Exception(checker.avarable(financialAssetCategoryId));
			}
			registry.remove(financialAssetCategory);
			message.setResult("OK");
		}catch(Exception ex) {
			message.setResult("NG");
			message.setErrorMessage(ex.getMessage());
			return message;
		}
		return message;
	}
	
	public List<FinancialAssetCategoryDto> findFinancialAssetCategoriesOfBusinessUnit(String businessUnitId) throws Exception{
		if(businessUnitId == null || businessUnitId.isEmpty()) {
			throw new IllegalArgumentException("The_businessUnitId_may_not_be_set_to_null");
		}
		List<FinancialAssetCategory> financialAssetCategories = registry.findFinancialAssetCategoriesOfBusinessUnit(businessUnitId);
		List<FinancialAssetCategoryDto> dtos = new ArrayList<>();
		for(FinancialAssetCategory entity : financialAssetCategories) {
			dtos.add(this.convertFinancialAssetCategory(entity));
		}
		return dtos;
	}
	
	public List<FinancialAssetCategoryDto> findFinancialAssetCategoriesOfCompany(String companyId) throws Exception{
		if(companyId == null || companyId.isEmpty()) {
			throw new IllegalArgumentException("The_companyId_may_not_be_set_to_null");
		}
		List<FinancialAssetCategory> financialAssetCategories = registry.findFinancialAssetCategoriesOfCompany(companyId);
		List<FinancialAssetCategoryDto> dtos = new ArrayList<>();
		for(FinancialAssetCategory entity : financialAssetCategories) {
			dtos.add(this.convertFinancialAssetCategory(entity));
		}
		return dtos;
	}
	
	public List<FinancialAssetCategoryDto> findFinancialAssetCategoriesOfFinancialAssetType(String financialAssetTypeId) throws Exception{
		if(financialAssetTypeId == null || financialAssetTypeId.isEmpty()) {
			throw new IllegalArgumentException("The_financialAssetTypeId_may_not_be_set_to_null");
		}
		List<FinancialAssetCategory> financialAssetCategories = registry.findFinancialAssetCategoriesOfFinancialAssetType(financialAssetTypeId);
		List<FinancialAssetCategoryDto> dtos = new ArrayList<>();
		for(FinancialAssetCategory entity : financialAssetCategories) {
			dtos.add(this.convertFinancialAssetCategory(entity));
		}
		return dtos;
	}
	
	public FinancialAssetCategoryDto findFinancialAssetCategoryOfId(String financialAssetCategoryId) throws Exception{
		if(financialAssetCategoryId == null || financialAssetCategoryId.isEmpty()) {
			throw new IllegalArgumentException("The_financialAssetCategoryId_may_not_be_set_to_null");
		}
		return this.convertFinancialAssetCategory(registry.findFinancialAssetCategoryOfId(financialAssetCategoryId));
	}
	
	private FinancialAssetCategoryDto convertFinancialAssetCategory(FinancialAssetCategory entity) {
		FinancialAssetCategoryDto dto = new FinancialAssetCategoryDto();
		dto.setFinancialAssetCategoryId(entity.getFinancialAssetCategoryId().getFinancialAssetCategoryId());
		dto.setFinancialAssetCategoryName(entity.getFinancialAssetCategoryName());
		dto.setFinancialAssetCategoryDescription(entity.getFinancialAssetCategoryDescription());
		dto.setCompanyId(entity.getCompanyId().id());
		dto.setBusinessUnitId(entity.getBusinessUnitId().businessUnitId());
		dto.setAssociatedFinancialAssetTypes(entity.getAssociatedFinancialAssetTypes());
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
