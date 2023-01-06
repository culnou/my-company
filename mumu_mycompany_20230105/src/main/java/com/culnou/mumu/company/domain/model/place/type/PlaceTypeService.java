package com.culnou.mumu.company.domain.model.place.type;

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
import com.culnou.mumu.compnay.controller.PlaceTypeDto;
import com.culnou.mumu.compnay.controller.MessageDto;

@Service
@Transactional
public class PlaceTypeService {
	
	@Autowired
	CompanyServiceImpl companyService;
	@Autowired
	private CompanyAdapter companyAdapter;
	@Autowired
	private PlaceTypeRegistry registry;
	@Autowired
	private EditPlaceTypeChecker editChecker;
	@Autowired
	private DeletePlaceTypeChecker removeChecker;
	
	public List<PlaceTypeDto> findPlaceTypesOfCompany(String companyId) throws Exception{
		if(companyId == null) {
			throw new Exception("The_commentId_may_not_be_set_to_null");
		}
		if(companyId.isEmpty()) {
			throw new Exception("Must_provide_a_companyId");
		}
		return this.convertPlaceTypes(registry.findPlaceTypesOfCompany(companyId));
	}
	
	public List<PlaceTypeDto> findPlaceTypesOfBusinessDomain(String businessDomainId) throws Exception{
		if(businessDomainId == null) {
			throw new Exception("The_businessDomainId_may_not_be_set_to_null");
		}
		if(businessDomainId.isEmpty()) {
			throw new Exception("Must_provide_a_businessDomainId");
		}
		return this.convertPlaceTypes(registry.findPlaceTypesOfBusinessDomain(businessDomainId));
	}
	public PlaceTypeDto findPlaceTypesOfId(String id) throws Exception{
		if(id == null) {
			throw new Exception("The_placeTypeId_may_not_be_set_to_null");
		}
		if(id.isEmpty()) {
			throw new Exception("Must_provide_a_placeTypeId");
		}
		return this.convertPlaceType(registry.findPlaceTypeOfId(id));
	}
	
	public MessageDto definePlaceType(PlaceTypeDto dto) {
		MessageDto message = new MessageDto();
		try {
			//事前条件
	        //コントローラーからの入力値の妥当性を保証する
			if(dto == null) {
				throw new Exception("The_PlaceType_may_not_be_set_to_null");
			}
			if(dto.getPlaceTypeName() == null) {
				throw new Exception("The_placeTypeName_may_not_be_set_to_null");
			}
			if(dto.getPlaceTypeName().isEmpty()) {
				throw new Exception("Must_provide_a_placeTypeName");
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
			PlaceType entity = this.convertFiancialAssetTypeDto(dto);
			entity.setPlaceTypeId(registry.nextIdentity());
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
	
	public MessageDto updatePlaceType(PlaceTypeDto dto) {
		MessageDto message = new MessageDto();
		try {
			//事前条件
	        //コントローラーからの入力値の妥当性を保証する
			if(dto == null) {
				throw new Exception("The_PlaceType_may_not_be_set_to_null");
			}
			if(dto.getPlaceTypeName() == null) {
				throw new Exception("The_placeTypeName_may_not_be_set_to_null");
			}
			if(dto.getPlaceTypeId() == null) {
				throw new Exception("The_placeTypeId_may_not_be_set_to_null");
			}
			if(dto.getPlaceTypeName().isEmpty()) {
				throw new Exception("Must_provide_a_placeTypeName");
			}
			if(dto.getPlaceTypeId().isEmpty()) {
				throw new Exception("Must_provide_a_placeTypeId");
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
			PlaceType result = registry.findPlaceTypeOfId(dto.getPlaceTypeId());
			if(result == null) {
				throw new Exception("The_PlaceType_could_not_be_found");
			}
			//ビジネスロジック
			//必要な属性だけ更新する
			
			if(!editChecker.editable(dto.getPlaceTypeId())) {
				throw new IllegalArgumentException("The_placeType_has_already_used_at_edit");
			}
			
			PlaceType entity = this.convertFiancialAssetTypeDto(dto);
			entity.setPlaceTypeId(result.getPlaceTypeId());
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
	
	public MessageDto removePlaceType(String placeTypeId) {
		MessageDto message = new MessageDto();
		try {
			//事前条件
	        //コントローラーからの入力値の妥当性を保証する
			if(!removeChecker.removable(placeTypeId)) {
				throw new IllegalArgumentException("The_placeType_has_already_used_at_delete");
			}
			if(placeTypeId == null) {
				throw new Exception("The_placeTypeId_may_not_be_set_to_null");
			}
			if(placeTypeId.isEmpty()) {
				throw new Exception("Must_provide_a_placeTypeId");
			}
			//識別子に対する実体の存在を保証する（識別子オブジェクトにラップする）
			PlaceType result = registry.findPlaceTypeOfId(placeTypeId);
			if(result == null) {
				throw new Exception("The_PlaceType_could_not_be_found");
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
	
	
	private PlaceTypeDto convertPlaceType(PlaceType entity) {
		PlaceTypeDto dto = new PlaceTypeDto();
		dto.setPlaceTypeId(entity.getPlaceTypeId().getPlaceTypeId());
		dto.setCompanyId(entity.getCompanyId().id());
		if(entity.getBusinessDomainId() != null) {
			dto.setBusinessDomainId(entity.getBusinessDomainId().businessDomainId());
		}
		if(entity.getBusinessDomainName() != null) {
			dto.setBusinessDomainName(entity.getBusinessDomainName());
		}
		dto.setPlaceTypeName(entity.getPlaceTypeName());
		dto.setPlaceTypeDescription(entity.getPlaceTypeDescription());
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
	
	private List<PlaceTypeDto> convertPlaceTypes(List<PlaceType> entities){
		List<PlaceTypeDto> dtos = new ArrayList<>();
		for(PlaceType entity : entities) {
			dtos.add(this.convertPlaceType(entity));
		}
		return dtos;
	}
	
	private PlaceType convertFiancialAssetTypeDto(PlaceTypeDto dto) {
		PlaceType entity = new PlaceType();
		if(dto.getPlaceTypeId() != null && !dto.getPlaceTypeId().isEmpty()) {
			entity.setPlaceTypeId(new PlaceTypeId(dto.getPlaceTypeId()));
		}
		entity.setPlaceTypeName(dto.getPlaceTypeName());
		entity.setPlaceTypeDescription(dto.getPlaceTypeDescription());
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
		if(dto.getDataTypeId() != null && !dto.getDataTypeId().isEmpty()) {
			entity.setDataTypeId(new DataTypeId(dto.getDataTypeId()));
		}
		entity.setCreatedAt(dto.getCreatedAt());
		entity.setUpdatedAt(dto.getUpdatedAt());
		return entity;
	}


}
