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
import com.culnou.mumu.company.domain.model.common.BusinessState;
import com.culnou.mumu.company.domain.model.data.category.DataCategory;
import com.culnou.mumu.company.domain.model.data.category.DataCategoryId;
import com.culnou.mumu.company.domain.model.data.category.DataCategoryRegistry;
import com.culnou.mumu.company.domain.model.data.category.DataCategoryService;
import com.culnou.mumu.company.domain.model.data.type.DataClass;
import com.culnou.mumu.company.domain.service.DataCategoryChecker;
import com.culnou.mumu.compnay.controller.DataCategoryDto;

import com.culnou.mumu.compnay.controller.MessageDto;

@Service
@Transactional
public class DataCategoryApplicationService {
	@Qualifier("businessUnitJpaRepository")
	@Autowired
	private BusinessUnitRepository businessUnitRepository;
	@Autowired
	private DataCategoryRegistry registry;
	@Autowired
	private DataCategoryService dataCategoryService;
	@Autowired
	private DataCategoryChecker checker;
	
	public MessageDto addDataCategory(DataCategoryDto dto) {
		MessageDto message = new MessageDto();
		try {
			//コントローラーの事前条件検証。
			if(dto.getBusinessUnitId() == null || dto.getBusinessUnitId().isEmpty()) {
				throw new IllegalArgumentException("The_businessUnitId_may_not_be_set_to_null");
			}
			if(dto.getCompanyId() == null || dto.getCompanyId().isEmpty()) {
				throw new IllegalArgumentException("The_companyId_may_not_be_set_to_null");
			}
			if(dto.getDataCategoryName() == null || dto.getDataCategoryName().isEmpty()) {
				throw new IllegalArgumentException("The_dataCategoryName_may_not_be_set_to_null");
			}
			if(!(dto.getAssociatedDataTypes().size() > 0)) {
				throw new IllegalArgumentException("The_dataTypeId_may_not_be_set_to_null");
			}
			if(dto.getDataClass() == null) {
				throw new IllegalArgumentException("The_dataClass_may_not_be_set_to_null");
			}
			
			
			//ビジネスロジック
			BusinessUnit businessUnit = businessUnitRepository.businessUnitOfId(new BusinessUnitId(dto.getBusinessUnitId()));
			if(businessUnit == null) {
				throw new Exception("The_businessUnit_is_not_exist");
			}
			DataCategoryId dataId = registry.nextIdentity();
			DataCategory dataCategory = businessUnit.defineDataCategory(dataId, dto.getDataCategoryName(), dto.getDataClass());
			dataCategory.setAssociatedDataTypes(dto.getAssociatedDataTypes());
			dataCategory.setDataCategoryDescription(dto.getDataCategoryDescription());
			if(!(dto.getUrl() == null || dto.getUrl().isEmpty())) {
				dataCategory.setUrl(new Url(dto.getUrl()));
			}
			dataCategory.setBusinessState(dto.getBusinessState());
			dataCategory.setStartDate(dto.getStartDate());
			dataCategory.setEndDate(dto.getEndDate());
			dataCategory.setGoals(dto.getGoals());
			dataCategory.setAchievements(dto.getAchievements());
			dataCategory.setCreatedAt(new Date());
			//DataCategoryServiceのfindInputDataやfindDeriverablesでNullエラーになるので以下を追加。2022/11/28
            if(dto.getBusinessState() == null) {
            	dataCategory.setBusinessState(BusinessState.Planned);
			}
			registry.save(dataCategory);
			message.setReturnValue(dataId.getDataCategoryId());
			message.setResult("OK");
		}catch(Exception ex) {
			message.setResult("NG");
			message.setErrorMessage(ex.getMessage());
			return message;
		}
		return message;
	}
	
	public MessageDto updateDataCategory(String dataCategoryId, DataCategoryDto dto) {
		MessageDto message = new MessageDto();
		try {
			//コントローラーの事前条件検証。
			if(dataCategoryId == null || dataCategoryId.isEmpty()) {
				throw new IllegalArgumentException("The_dataCategoryId_may_not_be_set_to_null");
			}
			if(dto.getCompanyId() == null || dto.getCompanyId().isEmpty()) {
				throw new IllegalArgumentException("The_companyId_may_not_be_set_to_null");
			}
			if(dto.getDataCategoryName() == null || dto.getDataCategoryName().isEmpty()) {
				throw new IllegalArgumentException("The_dataCategoryName_may_not_be_set_to_null");
			}
			if(!(dto.getAssociatedDataTypes().size() > 0)) {
				throw new IllegalArgumentException("The_dataTypeId_may_not_be_set_to_null");
			}
			
			//ビジネスロジック
			//編集・削除チェック。
			if(!checker.avarable(dataCategoryId).equals("OK")) {
				throw new Exception(checker.avarable(dataCategoryId));
			}
			//NULLでも空文字でも更新するようにする。
			DataCategory dataCategory = registry.findDataCategoryOfId(dataCategoryId);
			if(dataCategory == null) {
				throw new Exception("The_dataCategory_is_not_exist");
			}
			
			dataCategory.setAssociatedDataTypes(dto.getAssociatedDataTypes());
			dataCategory.setDataCategoryName(dto.getDataCategoryName());
			dataCategory.setDataCategoryDescription(dto.getDataCategoryDescription());
			if(!(dto.getUrl() == null || dto.getUrl().isEmpty())) {
				dataCategory.setUrl(new Url(dto.getUrl()));
			}else {
				dataCategory.setUrl(null);
			}
			dataCategory.setBusinessState(dto.getBusinessState());
			dataCategory.setStartDate(dto.getStartDate());
			dataCategory.setEndDate(dto.getEndDate());
			dataCategory.setGoals(dto.getGoals());
			dataCategory.setAchievements(dto.getAchievements());
			dataCategory.setUpdatedAt(new Date());
			registry.save(dataCategory);
			message.setResult("OK");
		}catch(Exception ex){
			message.setResult("NG");
			message.setErrorMessage(ex.getMessage());
			return message;
		}
		return message;
	}
	
	public MessageDto removeDataCategory(String dataCategoryId) {
		MessageDto message = new MessageDto();
		try {
			if(dataCategoryId == null || dataCategoryId.isEmpty()) {
				throw new IllegalArgumentException("The_dataCategoryId_may_not_be_set_to_null");
			}
			DataCategory dataCategory = registry.findDataCategoryOfId(dataCategoryId);
			if(dataCategory == null) {
				throw new Exception("The_dataCategory_is_not_exist");
			}
			//編集・削除チェック。
			if(!checker.avarable(dataCategoryId).equals("OK")) {
				throw new Exception(checker.avarable(dataCategoryId));
			}
			registry.remove(dataCategory);
			message.setResult("OK");
		}catch(Exception ex) {
			message.setResult("NG");
			message.setErrorMessage(ex.getMessage());
			return message;
		}
		return message;
	}
	
	public List<DataCategoryDto> findDataCategoriesOfBusinessUnit(String businessUnitId) throws Exception{
		if(businessUnitId == null || businessUnitId.isEmpty()) {
			throw new IllegalArgumentException("The_businessUnitId_may_not_be_set_to_null");
		}
		List<DataCategory> dataCategories = registry.findDataCategoriesOfBusinessUnit(businessUnitId);
		List<DataCategoryDto> dtos = new ArrayList<>();
		for(DataCategory entity : dataCategories) {
			dtos.add(this.convertDataCategory(entity));
		}
		return dtos;
	}
	
	public List<DataCategoryDto> findDataCategoriesOfBusinessUnitAndDataClass(String businessUnitId, DataClass dataClass) throws Exception{
		if(businessUnitId == null || businessUnitId.isEmpty()) {
			throw new IllegalArgumentException("The_businessUnitId_may_not_be_set_to_null");
		}
		if(dataClass == null) {
			throw new IllegalArgumentException("The_dataClass_may_not_be_set_to_null");
		}
		List<DataCategory> dataCategories = registry.findDataCategoriesOfBusinessUnitAndDataClass(businessUnitId, dataClass);
		List<DataCategoryDto> dtos = new ArrayList<>();
		for(DataCategory entity : dataCategories) {
			dtos.add(this.convertDataCategory(entity));
		}
		return dtos;
	}
	
	
	public List<DataCategoryDto> findDataCategoriesOfCompany(String companyId) throws Exception{
		if(companyId == null || companyId.isEmpty()) {
			throw new IllegalArgumentException("The_companyId_may_not_be_set_to_null");
		}
		List<DataCategory> dataCategories = registry.findDataCategoriesOfCompany(companyId);
		List<DataCategoryDto> dtos = new ArrayList<>();
		for(DataCategory entity : dataCategories) {
			dtos.add(this.convertDataCategory(entity));
		}
		return dtos;
	}
	
	public List<DataCategoryDto> findDataCategoriesOfDataType(String dataTypeId) throws Exception{
		if(dataTypeId == null || dataTypeId.isEmpty()) {
			throw new IllegalArgumentException("The_dataTypeId_may_not_be_set_to_null");
		}
		List<DataCategory> dataCategories = registry.findDataCategoriesOfDataType(dataTypeId);
		List<DataCategoryDto> dtos = new ArrayList<>();
		for(DataCategory entity : dataCategories) {
			dtos.add(this.convertDataCategory(entity));
		}
		return dtos;
	}
	//ワークの成果物を探す。
	public List<DataCategoryDto> findDeliverables(String workId) throws Exception{
		if(workId == null || workId.isEmpty()) {
			throw new IllegalArgumentException("The_workId_may_not_be_set_to_null");
		}
		List<DataCategory> dataCategories = dataCategoryService.findDeliverables(workId);
		List<DataCategoryDto> dtos = new ArrayList<>();
		for(DataCategory entity : dataCategories) {
			dtos.add(this.convertDataCategory(entity));
		}
		return dtos;
	}
	
	//ワークの成果物を探す。
	public List<DataCategoryDto> findInputData(String workId) throws Exception{
		if(workId == null || workId.isEmpty()) {
			throw new IllegalArgumentException("The_workId_may_not_be_set_to_null");
		}
		List<DataCategory> dataCategories = dataCategoryService.findInputData(workId);
		List<DataCategoryDto> dtos = new ArrayList<>();
		for(DataCategory entity : dataCategories) {
			dtos.add(this.convertDataCategory(entity));
		}
		return dtos;
	}
	
	public DataCategoryDto findDataCategoryOfId(String dataCategoryId) throws Exception{
		if(dataCategoryId == null || dataCategoryId.isEmpty()) {
			throw new IllegalArgumentException("The_dataCategoryId_may_not_be_set_to_null");
		}
		return this.convertDataCategory(registry.findDataCategoryOfId(dataCategoryId));
	}
	
	private DataCategoryDto convertDataCategory(DataCategory entity) {
		DataCategoryDto dto = new DataCategoryDto();
		dto.setDataCategoryId(entity.getDataCategoryId().getDataCategoryId());
		dto.setDataCategoryName(entity.getDataCategoryName());
		dto.setDataCategoryDescription(entity.getDataCategoryDescription());
		dto.setCompanyId(entity.getCompanyId().id());
		dto.setBusinessUnitId(entity.getBusinessUnitId().businessUnitId());
		dto.setDataClass(entity.getDataClass());
		if(entity.getUrl() != null) {
			dto.setUrl(entity.getUrl().getUrl());
		}
		dto.setAssociatedDataTypes(entity.getAssociatedDataTypes());
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
