package com.culnou.mumu.company.domain.model.data.type;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


import com.culnou.mumu.company.domain.model.BusinessDomainId;

import com.culnou.mumu.company.domain.model.CompanyId;
import com.culnou.mumu.company.domain.model.Url;
import com.culnou.mumu.company.domain.model.common.CompanyAdapter;
import com.culnou.mumu.company.domain.model.common.Email;
import com.culnou.mumu.company.domain.service.DataTypeChecker;
import com.culnou.mumu.company.infrastructure.application.CompanyServiceImpl;
import com.culnou.mumu.compnay.controller.CompanyDto;
import com.culnou.mumu.compnay.controller.DataTypeDto;
import com.culnou.mumu.compnay.controller.MessageDto;


@Service
@Transactional
public class DataTypeService{
	@Autowired
	CompanyServiceImpl companyService;
	@Autowired
	private CompanyAdapter companyAdapter;
	@Autowired
	private DataTypeRegistry registry;
	@Autowired
	private DataTypeChecker checker;
	
	public List<DataTypeDto> findDataTypesOfCompany(String companyId) throws Exception{
		if(companyId == null) {
			throw new Exception("The_companyId_may_not_be_set_to_null");
		}
		if(companyId.isEmpty()) {
			throw new Exception("Must_provide_a_companyId");
		}
		return this.convertDataTypes(registry.findDataTypesOfCompany(companyId));
	}
	
	public List<DataTypeDto> findDataTypesOfBusinessDomain(String businessDomainId) throws Exception{
		if(businessDomainId == null) {
			throw new Exception("The_businessDomainId_may_not_be_set_to_null");
		}
		if(businessDomainId.isEmpty()) {
			throw new Exception("Must_provide_a_businessDomainId");
		}
		return this.convertDataTypes(registry.findDataTypesOfBusinessDomain(businessDomainId));
	}
	
	public List<DataTypeDto> findDataTypesOfBusinessDomainAndDataClass(String businessDomainId, DataClass dataClass) throws Exception{
		if(businessDomainId == null) {
			throw new Exception("The_businessDomainId_may_not_be_set_to_null");
		}
		if(businessDomainId.isEmpty()) {
			throw new Exception("Must_provide_a_businessDomainId");
		}
		if(dataClass == null) {
			throw new Exception("The_dataClass_may_not_be_set_to_null");
		}
		return this.convertDataTypes(registry.findDataTypesOfBusinessDomainAndDataClass(businessDomainId, dataClass));
	}
	
	public DataTypeDto findDataTypesOfId(String id) throws Exception{
		if(id == null) {
			throw new Exception("The_dataTypeId_may_not_be_set_to_null");
		}
		if(id.isEmpty()) {
			throw new Exception("Must_provide_a_dataTypeId");
		}
		return this.convertDataType(registry.findDataTypeOfId(id));
	}
	
	public MessageDto defineDataType(DataTypeDto dto) {
		MessageDto message = new MessageDto();
		try {
			//事前条件
	        //コントローラーからの入力値の妥当性を保証する
			if(dto == null) {
				throw new Exception("The_DataType_may_not_be_set_to_null");
			}
			if(dto.getDataTypeName() == null) {
				throw new Exception("The_dataTypeName_may_not_be_set_to_null");
			}
			if(dto.getDataTypeName().isEmpty()) {
				throw new Exception("Must_provide_a_dataTypeName");
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
			//ビジネスロジック
			DataType entity = this.convertFiancialAssetTypeDto(dto);
			entity.setDataTypeId(registry.nextIdentity());
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
	
	public MessageDto updateDataType(DataTypeDto dto) {
		MessageDto message = new MessageDto();
		try {
			//事前条件
	        //コントローラーからの入力値の妥当性を保証する
			if(dto == null) {
				throw new Exception("The_DataType_may_not_be_set_to_null");
			}
			if(dto.getDataTypeName() == null) {
				throw new Exception("The_dataTypeName_may_not_be_set_to_null");
			}
			if(dto.getDataTypeId() == null) {
				throw new Exception("The_dataTypeId_may_not_be_set_to_null");
			}
			if(dto.getDataTypeName().isEmpty()) {
				throw new Exception("Must_provide_a_dataTypeName");
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
			DataType result = registry.findDataTypeOfId(dto.getDataTypeId());
			if(result == null) {
				throw new Exception("The_DataType_could_not_be_found");
			}
			//ビジネスロジック
			if(!checker.avarable(dto.getDataTypeId()).equals("OK")) {
				throw new Exception(checker.avarable(dto.getDataTypeId()));
			}
			//必要な属性だけ更新する
			DataType entity = this.convertFiancialAssetTypeDto(dto);
			entity.setDataTypeId(result.getDataTypeId());
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
	
	public MessageDto removeDataType(String dataTypeId) {
		MessageDto message = new MessageDto();
		try {
			//事前条件
	        //コントローラーからの入力値の妥当性を保証する
			if(dataTypeId == null) {
				throw new Exception("The_dataTypeId_may_not_be_set_to_null");
			}
			if(dataTypeId.isEmpty()) {
				throw new Exception("Must_provide_a_dataTypeId");
			}
			//識別子に対する実体の存在を保証する（識別子オブジェクトにラップする）
			DataType result = registry.findDataTypeOfId(dataTypeId);
			if(result == null) {
				throw new Exception("The_DataType_could_not_be_found");
			}
			if(!checker.avarable(dataTypeId).equals("OK")) {
				throw new Exception(checker.avarable(dataTypeId));
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
	
	
	private DataTypeDto convertDataType(DataType entity) {
		DataTypeDto dto = new DataTypeDto();
		dto.setDataTypeId(entity.getDataTypeId().getDataTypeId());
		dto.setCompanyId(entity.getCompanyId().id());
		dto.setDataClass(entity.getDataClass());
		dto.setDataOwner(entity.getDataOwner());
		dto.setParentId(entity.getParentId());
		
		if(entity.getAddress() != null) {
			dto.setAddress(entity.getAddress().address());
		}
		dto.setExistenceConstraint(entity.getExistenceConstraint());
		dto.setAssociatedConstraint(entity.getAssociatedConstraint());
		dto.setDataAmount(entity.getDataAmount());
		if(entity.getBusinessDomainId() != null) {
			dto.setBusinessDomainId(entity.getBusinessDomainId().businessDomainId());
		}
		if(entity.getBusinessDomainName() != null) {
			dto.setBusinessDomainName(entity.getBusinessDomainName());
		}
		dto.setDataTypeName(entity.getDataTypeName());
		dto.setDataTypeDescription(entity.getDataTypeDescription());
		if(entity.getDataOwnerId() != null) {
			dto.setDataOwnerId(entity.getDataOwnerId());
		}
		if(entity.getUrl() != null) {
			dto.setUrl(entity.getUrl().getUrl());
		}
		dto.setIndicators(entity.getIndicators());
		dto.setCreatedAt(entity.getCreatedAt());
		dto.setUpdatedAt(entity.getUpdatedAt());
		return dto;
	}
	
	private List<DataTypeDto> convertDataTypes(List<DataType> entities){
		List<DataTypeDto> dtos = new ArrayList<>();
		for(DataType entity : entities) {
			dtos.add(this.convertDataType(entity));
		}
		return dtos;
	}
	
	private DataType convertFiancialAssetTypeDto(DataTypeDto dto) {
		DataType entity = new DataType();
		if(dto.getDataTypeId() != null && !dto.getDataTypeId().isEmpty()) {
			entity.setDataTypeId(new DataTypeId(dto.getDataTypeId()));
		}
		entity.setDataTypeName(dto.getDataTypeName());
		entity.setDataClass(dto.getDataClass());
		entity.setDataOwner(dto.getDataOwner());
		entity.setAssociatedConstraint(dto.getAssociatedConstraint());
		entity.setExistenceConstraint(dto.getExistenceConstraint());
		entity.setDataAmount(dto.getDataAmount());
		entity.setParentId(dto.getParentId());
		
		if(dto.getAddress() != null) {
			entity.setAddress(new Email(dto.getAddress()));
		}else {
			entity.setAddress(null);
		}
		entity.setDataTypeDescription(dto.getDataTypeDescription());
		if(dto.getBusinessDomainId() != null && !dto.getBusinessDomainId().isEmpty()) {
			entity.setBusinessDomainId(new BusinessDomainId(dto.getBusinessDomainId()));
		}
		if(dto.getBusinessDomainName() != null && !dto.getBusinessDomainName().isEmpty()) {
			entity.setBusinessDomainName(dto.getBusinessDomainName());
		}
		entity.setCompanyId(new CompanyId(dto.getCompanyId()));
		if(dto.getDataOwnerId() != null && !dto.getDataOwnerId().isEmpty()) {
			entity.setDataOwnerId(dto.getDataOwnerId());
		}
		if(dto.getUrl() != null && !dto.getUrl().isEmpty()) {
			entity.setUrl(new Url(dto.getUrl()));
		}else {
			entity.setUrl(null);
		}
		entity.setIndicators(dto.getIndicators());
		entity.setCreatedAt(dto.getCreatedAt());
		entity.setUpdatedAt(dto.getUpdatedAt());
		return entity;
	}


}
