package com.culnou.mumu.company.domain.model.financial.asset.type;

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
import com.culnou.mumu.company.domain.model.data.type.DataTypeId;

import com.culnou.mumu.company.infrastructure.application.CompanyServiceImpl;


import com.culnou.mumu.compnay.controller.CompanyDto;
import com.culnou.mumu.compnay.controller.FinancialAssetTypeDto;
import com.culnou.mumu.compnay.controller.MessageDto;

@Service
@Transactional
public class FinancialAssetTypeService {
	
	@Autowired
	CompanyServiceImpl companyService;
	@Autowired
	private CompanyAdapter companyAdapter;
	@Autowired
	private FinancialAssetTypeRegistry registry;
	@Autowired
	private EditFinancialAssetTypeChecker editChecker;
	@Autowired
	private DeleteFinancialAssetTypeChecker removeChecker;
	
	
	public List<FinancialAssetTypeDto> findFinancialAssetTypesOfCompany(String companyId) throws Exception{
		if(companyId == null) {
			throw new Exception("The_commentId_may_not_be_set_to_null");
		}
		if(companyId.isEmpty()) {
			throw new Exception("Must_provide_a_companyId");
		}
		return this.convertFinancialAssetTypes(registry.findFinancialAssetTypesOfCompany(companyId));
	}
	
	public List<FinancialAssetTypeDto> findFinancialAssetTypesOfBusinessDomain(String businessDomainId) throws Exception{
		if(businessDomainId == null) {
			throw new Exception("The_businessDomainId_may_not_be_set_to_null");
		}
		if(businessDomainId.isEmpty()) {
			throw new Exception("Must_provide_a_businessDomainId");
		}
		return this.convertFinancialAssetTypes(registry.findFinancialAssetTypesOfBusinessDomain(businessDomainId));
	}
	public FinancialAssetTypeDto findFinancialAssetTypesOfId(String id) throws Exception{
		if(id == null) {
			throw new Exception("The_financialAssetTypeId_may_not_be_set_to_null");
		}
		if(id.isEmpty()) {
			throw new Exception("Must_provide_a_financialAssetTypeId");
		}
		return this.convertFinancialAssetType(registry.findFinancialAssetTypeOfId(id));
	}
	
	public MessageDto defineFinancialAssetType(FinancialAssetTypeDto dto) {
		MessageDto message = new MessageDto();
		try {
			//事前条件
	        //コントローラーからの入力値の妥当性を保証する
			if(dto == null) {
				throw new Exception("The_FinancialAssetType_may_not_be_set_to_null");
			}
			if(dto.getFinancialAssetTypeName() == null) {
				throw new Exception("The_financialAssetTypeName_may_not_be_set_to_null");
			}
			if(dto.getFinancialAssetTypeName().isEmpty()) {
				throw new Exception("Must_provide_a_financialAssetTypeName");
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
			//ビジネスロジック
			FinancialAssetType entity = this.convertFiancialAssetTypeDto(dto);
			entity.setFinancialAssetTypeId(registry.nextIdentity());
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
	
	public MessageDto updateFinancialAssetType(FinancialAssetTypeDto dto) {
		MessageDto message = new MessageDto();
		try {
			//事前条件
	        //コントローラーからの入力値の妥当性を保証する
			
			if(dto == null) {
				throw new Exception("The_FinancialAssetType_may_not_be_set_to_null");
			}
			if(dto.getFinancialAssetTypeName() == null) {
				throw new Exception("The_financialAssetTypeName_may_not_be_set_to_null");
			}
			if(dto.getFinancialAssetTypeId() == null) {
				throw new Exception("The_financialAssetTypeId_may_not_be_set_to_null");
			}
			if(dto.getFinancialAssetTypeName().isEmpty()) {
				throw new Exception("Must_provide_a_financialAssetTypeName");
			}
			if(dto.getFinancialAssetTypeId().isEmpty()) {
				throw new Exception("Must_provide_a_financialAssetTypeId");
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
			FinancialAssetType result = registry.findFinancialAssetTypeOfId(dto.getFinancialAssetTypeId());
			if(result == null) {
				throw new Exception("The_FinancialAssetType_could_not_be_found");
			}
			//ビジネスロジック
			//必要な属性だけ更新する
			
			if(!editChecker.editable(dto.getFinancialAssetTypeId())) {
				throw new IllegalArgumentException("The_financialAssetType_has_already_used_at_edit");
			}
			
			FinancialAssetType entity = this.convertFiancialAssetTypeDto(dto);
			entity.setFinancialAssetTypeId(result.getFinancialAssetTypeId());
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
	
	public MessageDto removeFinancialAssetType(String financialAssetTypeId) {
		MessageDto message = new MessageDto();
		try {
			//事前条件
	        //コントローラーからの入力値の妥当性を保証する
			if(!removeChecker.removable(financialAssetTypeId)) {
				throw new IllegalArgumentException("The_financialAssetType_has_already_used_at_delete");
			}
			if(financialAssetTypeId == null) {
				throw new Exception("The_financialAssetTypeId_may_not_be_set_to_null");
			}
			if(financialAssetTypeId.isEmpty()) {
				throw new Exception("Must_provide_a_financialAssetTypeId");
			}
			//識別子に対する実体の存在を保証する（識別子オブジェクトにラップする）
			FinancialAssetType result = registry.findFinancialAssetTypeOfId(financialAssetTypeId);
			if(result == null) {
				throw new Exception("The_FinancialAssetType_could_not_be_found");
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
	
	
	private FinancialAssetTypeDto convertFinancialAssetType(FinancialAssetType entity) {
		FinancialAssetTypeDto dto = new FinancialAssetTypeDto();
		dto.setFinancialAssetTypeId(entity.getFinancialAssetTypeId().getFinancialAssetTypeId());
		dto.setCompanyId(entity.getCompanyId().id());
		if(entity.getBusinessDomainId() != null) {
			dto.setBusinessDomainId(entity.getBusinessDomainId().businessDomainId());
		}
		if(entity.getBusinessDomainName() != null) {
			dto.setBusinessDomainName(entity.getBusinessDomainName());
		}
		dto.setFinancialAssetTypeName(entity.getFinancialAssetTypeName());
		dto.setFinancialAssetTypeDescription(entity.getFinancialAssetTypeDescription());
		if(entity.getUrl() != null) {
			dto.setUrl(entity.getUrl().getUrl());
		}
		if(entity.getDataTypeId() != null) {
			dto.setDataTypeId(entity.getDataTypeId().getDataTypeId());
		}
		dto.setIndicators(entity.getIndicators());
		dto.setCreatedAt(entity.getCreatedAt());
		dto.setUpdatedAt(entity.getUpdatedAt());
		return dto;
	}
	
	private List<FinancialAssetTypeDto> convertFinancialAssetTypes(List<FinancialAssetType> entities){
		List<FinancialAssetTypeDto> dtos = new ArrayList<>();
		for(FinancialAssetType entity : entities) {
			dtos.add(this.convertFinancialAssetType(entity));
		}
		return dtos;
	}
	
	private FinancialAssetType convertFiancialAssetTypeDto(FinancialAssetTypeDto dto) {
		FinancialAssetType entity = new FinancialAssetType();
		if(dto.getFinancialAssetTypeId() != null && !dto.getFinancialAssetTypeId().isEmpty()) {
			entity.setFinancialAssetTypeId(new FinancialAssetTypeId(dto.getFinancialAssetTypeId()));
		}
		entity.setFinancialAssetTypeName(dto.getFinancialAssetTypeName());
		entity.setFinancialAssetTypeDescription(dto.getFinancialAssetTypeDescription());
		if(dto.getBusinessDomainId() != null && !dto.getBusinessDomainId().isEmpty()) {
			entity.setBusinessDomainId(new BusinessDomainId(dto.getBusinessDomainId()));
		}
		if(dto.getBusinessDomainName() != null && !dto.getBusinessDomainName().isEmpty()) {
			entity.setBusinessDomainName(dto.getBusinessDomainName());
		}
		entity.setCompanyId(new CompanyId(dto.getCompanyId()));
		if(dto.getUrl() != null && !dto.getUrl().isEmpty()) {
			entity.setUrl(new Url(dto.getUrl()));
		}
		if(dto.getDataTypeId() != null && !dto.getDataTypeId().isEmpty()) {
			entity.setDataTypeId(new DataTypeId(dto.getDataTypeId()));
		}
		
		entity.setIndicators(dto.getIndicators());
		entity.setCreatedAt(dto.getCreatedAt());
		entity.setUpdatedAt(dto.getUpdatedAt());
		
		return entity;
	}

}
