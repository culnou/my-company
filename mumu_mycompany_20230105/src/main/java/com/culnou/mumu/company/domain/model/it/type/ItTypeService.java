package com.culnou.mumu.company.domain.model.it.type;

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

import com.culnou.mumu.company.infrastructure.application.CompanyServiceImpl;
import com.culnou.mumu.compnay.controller.CompanyDto;
import com.culnou.mumu.compnay.controller.MessageDto;
import com.culnou.mumu.compnay.controller.ItTypeDto;

@Service
@Transactional
public class ItTypeService {
	@Autowired
	CompanyServiceImpl companyService;
	@Autowired
	private CompanyAdapter companyAdapter;
	@Autowired
	private ItTypeRegistry registry;
	@Autowired
	private EditItTypeChecker editChecker;
	@Autowired
	private DeleteItTypeChecker removeChecker;
	
	public List<ItTypeDto> findItTypesOfCompany(String companyId) throws Exception{
		if(companyId == null) {
			throw new Exception("The_commentId_may_not_be_set_to_null");
		}
		if(companyId.isEmpty()) {
			throw new Exception("Must_provide_a_companyId");
		}
		return this.convertItTypes(registry.findItTypesOfCompany(companyId));
	}
	
	public List<ItTypeDto> findItTypesOfBusinessDomain(String businessDomainId) throws Exception{
		if(businessDomainId == null) {
			throw new Exception("The_businessDomainId_may_not_be_set_to_null");
		}
		if(businessDomainId.isEmpty()) {
			throw new Exception("Must_provide_a_businessDomainId");
		}
		return this.convertItTypes(registry.findItTypesOfBusinessDomain(businessDomainId));
	}
	public ItTypeDto findItTypesOfId(String id) throws Exception{
		if(id == null) {
			throw new Exception("The_itTypeId_may_not_be_set_to_null");
		}
		if(id.isEmpty()) {
			throw new Exception("Must_provide_a_itTypeId");
		}
		return this.convertItType(registry.findItTypeOfId(id));
	}
	
	public MessageDto defineItType(ItTypeDto dto) {
		MessageDto message = new MessageDto();
		try {
			//事前条件
	        //コントローラーからの入力値の妥当性を保証する
			if(dto == null) {
				throw new Exception("The_ItType_may_not_be_set_to_null");
			}
			if(dto.getItTypeName() == null) {
				throw new Exception("The_itTypeName_may_not_be_set_to_null");
			}
			if(dto.getItTypeName().isEmpty()) {
				throw new Exception("Must_provide_a_itTypeName");
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
			ItType entity = this.convertFiancialAssetTypeDto(dto);
			entity.setItTypeId(registry.nextIdentity());
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
	
	public MessageDto updateItType(ItTypeDto dto) {
		MessageDto message = new MessageDto();
		try {
			//事前条件
	        //コントローラーからの入力値の妥当性を保証する
			if(dto == null) {
				throw new Exception("The_ItType_may_not_be_set_to_null");
			}
			if(dto.getItTypeName() == null) {
				throw new Exception("The_itTypeName_may_not_be_set_to_null");
			}
			if(dto.getItTypeId() == null) {
				throw new Exception("The_itTypeId_may_not_be_set_to_null");
			}
			if(dto.getItTypeName().isEmpty()) {
				throw new Exception("Must_provide_a_itTypeName");
			}
			if(dto.getItTypeId().isEmpty()) {
				throw new Exception("Must_provide_a_itTypeId");
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
			ItType result = registry.findItTypeOfId(dto.getItTypeId());
			if(result == null) {
				throw new Exception("The_ItType_could_not_be_found");
			}
			//ビジネスロジック
			//必要な属性だけ更新する
			
			if(!editChecker.editable(dto.getItTypeId())) {
				throw new IllegalArgumentException("The_itType_has_already_used_at_edit");
			}
			ItType entity = this.convertFiancialAssetTypeDto(dto);
			entity.setItTypeId(result.getItTypeId());
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
	
	public MessageDto removeItType(String itTypeId) {
		MessageDto message = new MessageDto();
		try {
			//事前条件
	        //コントローラーからの入力値の妥当性を保証する
			if(itTypeId == null) {
				throw new Exception("The_itTypeId_may_not_be_set_to_null");
			}
			if(itTypeId.isEmpty()) {
				throw new Exception("Must_provide_a_itTypeId");
			}
			
			if(!removeChecker.removable(itTypeId)) {
				throw new IllegalArgumentException("The_itType_has_already_used_at_delete");
			}
			
			//識別子に対する実体の存在を保証する（識別子オブジェクトにラップする）
			ItType result = registry.findItTypeOfId(itTypeId);
			if(result == null) {
				throw new Exception("The_ItType_could_not_be_found");
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
	
	
	private ItTypeDto convertItType(ItType entity) {
		ItTypeDto dto = new ItTypeDto();
		dto.setItTypeId(entity.getItTypeId().getItTypeId());
		dto.setCompanyId(entity.getCompanyId().id());
		if(entity.getBusinessDomainId() != null) {
			dto.setBusinessDomainId(entity.getBusinessDomainId().businessDomainId());
		}
		if(entity.getBusinessDomainName() != null) {
			dto.setBusinessDomainName(entity.getBusinessDomainName());
		}
		dto.setItTypeName(entity.getItTypeName());
		dto.setItTypeDescription(entity.getItTypeDescription());
		if(entity.getUrl() != null) {
			dto.setUrl(entity.getUrl().getUrl());
		}
		dto.setIndicators(entity.getIndicators());
		dto.setCreatedAt(entity.getCreatedAt());
		dto.setUpdatedAt(entity.getUpdatedAt());
		return dto;
	}
	
	private List<ItTypeDto> convertItTypes(List<ItType> entities){
		List<ItTypeDto> dtos = new ArrayList<>();
		for(ItType entity : entities) {
			dtos.add(this.convertItType(entity));
		}
		return dtos;
	}
	
	private ItType convertFiancialAssetTypeDto(ItTypeDto dto) {
		ItType entity = new ItType();
		if(dto.getItTypeId() != null && !dto.getItTypeId().isEmpty()) {
			entity.setItTypeId(new ItTypeId(dto.getItTypeId()));
		}
		entity.setItTypeName(dto.getItTypeName());
		entity.setItTypeDescription(dto.getItTypeDescription());
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
		entity.setIndicators(dto.getIndicators());
		entity.setCreatedAt(dto.getCreatedAt());
		entity.setUpdatedAt(dto.getUpdatedAt());
		return entity;
	}


}
