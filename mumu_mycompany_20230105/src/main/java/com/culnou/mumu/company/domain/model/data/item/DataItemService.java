package com.culnou.mumu.company.domain.model.data.item;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


import com.culnou.mumu.company.domain.model.CompanyId;
import com.culnou.mumu.company.domain.model.Url;
import com.culnou.mumu.company.domain.model.common.CompanyAdapter;
import com.culnou.mumu.company.domain.model.data.domain.DataDomainId;
import com.culnou.mumu.company.domain.model.data.type.DataTypeId;
import com.culnou.mumu.company.domain.model.data.type.DataTypeService;
import com.culnou.mumu.company.infrastructure.application.CompanyServiceImpl;
import com.culnou.mumu.compnay.controller.CompanyDto;
import com.culnou.mumu.compnay.controller.DataItemDto;
import com.culnou.mumu.compnay.controller.DataTypeDto;
import com.culnou.mumu.compnay.controller.MessageDto;

@Service
@Transactional
public class DataItemService {
	
	@Autowired
	CompanyServiceImpl companyService;
	@Autowired
	private CompanyAdapter companyAdapter;
	@Autowired
	private DataTypeService dataTypeService;
	@Autowired
	private DataItemRegistry registry;
	
	public List<DataItemDto> findDataItemsOfCompany(String companyId) throws Exception{
		if(companyId == null) {
			throw new Exception("The_companyId_may_not_be_set_to_null");
		}
		if(companyId.isEmpty()) {
			throw new Exception("Must_provide_a_companyId");
		}
		return this.convertDataItems(registry.findDataItemsOfCompany(companyId));
	}
	
	public List<DataItemDto> findDataItemsOfDataType(String dataTypeId) throws Exception{
		if(dataTypeId == null) {
			throw new Exception("The_dataTypeId_may_not_be_set_to_null");
		}
		if(dataTypeId.isEmpty()) {
			throw new Exception("Must_provide_a_dataTypeId");
		}
		return this.convertDataItems(registry.findDataItemsOfDataType(dataTypeId));
	}
	public DataItemDto findDataItemsOfId(String id) throws Exception{
		if(id == null) {
			throw new Exception("The_dataItemId_may_not_be_set_to_null");
		}
		if(id.isEmpty()) {
			throw new Exception("Must_provide_a_dataItemId");
		}
		return this.convertDataItem(registry.findDataItemOfId(id));
	}
	
	public MessageDto defineDataItem(DataItemDto dto) {
		MessageDto message = new MessageDto();
		try {
			//事前条件
	        //コントローラーからの入力値の妥当性を保証する
			if(dto == null) {
				throw new Exception("The_DataItem_may_not_be_set_to_null");
			}
			if(dto.getDataTypeId() == null) {
				throw new Exception("The_dataTypeId_may_not_be_set_to_null");
			}
			if(dto.getDataTypeId().isEmpty()) {
				throw new Exception("Must_provide_a_dataTypeId");
			}
			
			if(dto.getDataItemName() == null) {
				throw new Exception("The_dataItemName_may_not_be_set_to_null");
			}
			if(dto.getDataItemName().isEmpty()) {
				throw new Exception("Must_provide_a_dataItemName");
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
			DataTypeDto dataType = dataTypeService.findDataTypesOfId(dto.getDataTypeId());
			if(dataType == null) {
				throw new Exception("The_DataType_could_not_be_found");
			}
			//ビジネスロジック
			DataItem entity = this.convertDataItemDto(dto);
			entity.setDataItemId(registry.nextIdentity());
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
	
	public MessageDto updateDataItem(DataItemDto dto) {
		MessageDto message = new MessageDto();
		try {
			//事前条件
	        //コントローラーからの入力値の妥当性を保証する
			if(dto == null) {
				throw new Exception("The_DataItem_may_not_be_set_to_null");
			}
			if(dto.getDataItemName() == null) {
				throw new Exception("The_dataItemName_may_not_be_set_to_null");
			}
			if(dto.getDataItemId() == null) {
				throw new Exception("The_dataItemId_may_not_be_set_to_null");
			}
			if(dto.getDataItemName().isEmpty()) {
				throw new Exception("Must_provide_a_dataItemName");
			}
			if(dto.getDataItemId().isEmpty()) {
				throw new Exception("Must_provide_a_dataItemId");
			}
			if(dto.getDataTypeId() == null) {
				throw new Exception("The_dataTypeId_may_not_be_set_to_null");
			}
			if(dto.getDataTypeId().isEmpty()) {
				throw new Exception("Must_provide_a_dataTypeId");
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
			DataTypeDto dataType = dataTypeService.findDataTypesOfId(dto.getDataTypeId());
			if(dataType == null) {
				throw new Exception("The_DataType_could_not_be_found");
			}
			DataItem result = registry.findDataItemOfId(dto.getDataItemId());
			if(result == null) {
				throw new Exception("The_DataItem_could_not_be_found");
			}
			//ビジネスロジック
			//必要な属性だけ更新する
			DataItem entity = this.convertDataItemDto(dto);
			entity.setDataItemId(result.getDataItemId());
			entity.setCompanyId(new CompanyId(company.getCompanyId()));
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
	
	public MessageDto removeDataItem(String dataItemId) {
		MessageDto message = new MessageDto();
		try {
			//事前条件
	        //コントローラーからの入力値の妥当性を保証する
			if(dataItemId == null) {
				throw new Exception("The_dataItemId_may_not_be_set_to_null");
			}
			if(dataItemId.isEmpty()) {
				throw new Exception("Must_provide_a_dataItemId");
			}
			//識別子に対する実体の存在を保証する（識別子オブジェクトにラップする）
			DataItem result = registry.findDataItemOfId(dataItemId);
			if(result == null) {
				throw new Exception("The_DataItem_could_not_be_found");
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
	
	
	private DataItemDto convertDataItem(DataItem entity) {
		DataItemDto dto = new DataItemDto();
		dto.setDataItemId(entity.getDataItemId().getDataItemId());
		dto.setCompanyId(entity.getCompanyId().id());
		dto.setDataTypeId(entity.getDataTypeId().getDataTypeId());
		dto.setDataTypeName(entity.getDataTypeName());
		
		dto.setPrimaryKey(entity.isPrimaryKey());
		dto.setForeignKey(entity.isForeignKey());
		dto.setReferenceAttributeId(entity.getReferenceAttributeId());
		dto.setDataSensitivityLevel(entity.getDataSensitivityLevel());	
		dto.setDataRestrictedCategory(entity.getDataRestrictedCategory());
		dto.setAuditDate(entity.getAuditDate());
		
		dto.setIndicators(entity.getIndicators());
		
		if(entity.getDataDomainId() != null) {
			dto.setDataDomainId(entity.getDataDomainId().getDataDomainId());
		}
		if(entity.getDataDomainName() != null) {
			dto.setDataDomainName(entity.getDataDomainName());
		}
		if(entity.getDataItemType() != null) {
			dto.setDataItemType(entity.getDataItemType());
		}
		if(entity.getDataItemDigit() != null) {
			dto.setDataItemDigit(entity.getDataItemDigit());
		}
		if(entity.getDataItemConstraints() != null) {
			dto.setDataItemConstraints(entity.getDataItemConstraints());
		}
		dto.setDataItemName(entity.getDataItemName());
		dto.setDataItemDescription(entity.getDataItemDescription());
		if(entity.getDataOwnerId() != null) {
			dto.setDataOwnerId(entity.getDataOwnerId());
		}
		if(entity.getUrl() != null) {
			dto.setUrl(entity.getUrl().getUrl());
		}
		dto.setCreatedAt(entity.getCreatedAt());
		dto.setUpdatedAt(entity.getUpdatedAt());
		return dto;
	}
	
	private List<DataItemDto> convertDataItems(List<DataItem> entities){
		List<DataItemDto> dtos = new ArrayList<>();
		for(DataItem entity : entities) {
			dtos.add(this.convertDataItem(entity));
		}
		return dtos;
	}
	
	private DataItem convertDataItemDto(DataItemDto dto){
		DataItem entity = new DataItem();
		if(dto.getDataItemId() != null && !dto.getDataItemId().isEmpty()) {
			entity.setDataItemId(new DataItemId(dto.getDataItemId()));
		}
		entity.setDataItemName(dto.getDataItemName());
		entity.setDataItemDescription(dto.getDataItemDescription());
		entity.setDataTypeId(new DataTypeId(dto.getDataTypeId()));
		entity.setDataTypeName(dto.getDataTypeName());
		
		entity.setPrimaryKey(dto.isPrimaryKey());
		entity.setForeignKey(dto.isForeignKey());
		entity.setReferenceAttributeId(dto.getReferenceAttributeId());
		entity.setDataSensitivityLevel(dto.getDataSensitivityLevel());	
		entity.setDataRestrictedCategory(dto.getDataRestrictedCategory());
		entity.setAuditDate(dto.getAuditDate());
		
		entity.setIndicators(dto.getIndicators());
		
		
		if(dto.getDataDomainId() != null && !dto.getDataDomainId().isEmpty()) {
			entity.setDataDomainId(new DataDomainId(dto.getDataDomainId()));
		}
		if(dto.getDataDomainName() != null && !dto.getDataDomainName().isEmpty()) {
			entity.setDataDomainName(dto.getDataDomainName());
		}
		if(dto.getDataItemType() != null) {
			entity.setDataItemType(dto.getDataItemType());
		}
		if(dto.getDataItemDigit() != null) {
			entity.setDataItemDigit(dto.getDataItemDigit());
		}
		if(dto.getDataItemConstraints() != null) {
			entity.setDataItemConstraints(dto.getDataItemConstraints());
		}
		entity.setCompanyId(new CompanyId(dto.getCompanyId()));
		if(dto.getDataOwnerId() != null && !dto.getDataOwnerId().isEmpty()) {
			entity.setDataOwnerId(dto.getDataOwnerId());
		}
		if(dto.getUrl() != null && !dto.getUrl().isEmpty()) {
			entity.setUrl(new Url(dto.getUrl()));
		}
		entity.setCreatedAt(dto.getCreatedAt());
		entity.setUpdatedAt(dto.getUpdatedAt());
		return entity;
	}



}
