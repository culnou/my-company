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
import com.culnou.mumu.company.domain.model.CompanyId;
import com.culnou.mumu.company.domain.model.Url;
import com.culnou.mumu.company.domain.model.it.category.ItCategory;
import com.culnou.mumu.company.domain.model.it.category.ItCategoryId;
import com.culnou.mumu.company.domain.model.it.category.ItCategoryRepository;
import com.culnou.mumu.company.domain.model.it.instance.It;
import com.culnou.mumu.company.domain.model.it.instance.ItRegistry;
import com.culnou.mumu.company.domain.model.place.instance.PlaceId;
import com.culnou.mumu.company.domain.service.ItChecker;
import com.culnou.mumu.compnay.controller.ItDto;
import com.culnou.mumu.compnay.controller.MessageDto;

@Service
@Transactional
public class ItApplicationService {
	
	@Qualifier("businessUnitJpaRepository")
	@Autowired
	private BusinessUnitRepository businessUnitRepository;
	@Qualifier("itCategoryJpaRepository")
	@Autowired
	private ItCategoryRepository itCategoryRepository;
	@Autowired
	private ItRegistry registry;
	@Autowired
	private ItChecker checker;
	
	public MessageDto addIt(ItDto dto) {
		MessageDto message = new MessageDto();
		try {
			if(dto == null) {
				throw new IllegalArgumentException("The_dto_may_not_be_set_to_null");
			}
			if(dto.getCompanyId() == null || dto.getCompanyId().isEmpty()) {
				throw new IllegalArgumentException("The_companyId_may_not_be_set_to_null");
			}
			if(dto.getItName() == null || dto.getItName().isEmpty()) {
				throw new IllegalArgumentException("The_itName_may_not_be_set_to_null");
			}
			if(dto.getBusinessUnitId() == null || dto.getBusinessUnitId().isEmpty()) {
				throw new IllegalArgumentException("The_businessUnitId_may_not_be_set_to_null");
			}
			if(dto.getItCategoryId() == null || dto.getItCategoryId().isEmpty()) {
				throw new IllegalArgumentException("The_itCategoryId_may_not_be_set_to_null");
			}
			//ビジネスロジック
			BusinessUnit businessUnit = businessUnitRepository.businessUnitOfId(new BusinessUnitId(dto.getBusinessUnitId()));
			if(businessUnit == null) {
				throw new Exception("The_businessUnit_is_not_exist");
			}
			ItCategory itCategory = itCategoryRepository.itCategoryOfId(new ItCategoryId(dto.getItCategoryId()));
			if(itCategory == null) {
				throw new Exception("The_itCategory_is_not_exist");
			}
			It it = itCategory.defineIt(registry.nextIdentity(), dto.getItName(), new CompanyId(dto.getCompanyId()), new BusinessUnitId(dto.getBusinessUnitId()));
			it.setItDescription(dto.getItDescription());
			it.setItCategoryName(dto.getItCategoryName());
			if(!(dto.getPlaceId() == null || dto.getPlaceId().isEmpty())) {
				it.setPlaceId(new PlaceId(dto.getPlaceId()));
			}
			if(!(dto.getUrl() == null || dto.getUrl().isEmpty())) {
				it.setUrl(new Url(dto.getUrl()));
			}
			it.setStartDate(dto.getStartDate());
			it.setEndDate(dto.getEndDate());
			it.setCreatedAt(new Date());
	        registry.save(it);
	        message.setReturnValue(it.getItId().getItId());
			message.setResult("OK");
		}catch(Exception ex) {
			message.setResult("NG");
			message.setErrorMessage(ex.getMessage());
			return message;
		}
		return message;
		
	}
	
	public MessageDto updateIt(ItDto dto) {
		MessageDto message = new MessageDto();
		try {
			if(dto == null) {
				throw new IllegalArgumentException("The_dto_may_not_be_set_to_null");
			}
			if(dto.getItId() == null || dto.getItId().isEmpty()) {
				throw new IllegalArgumentException("The_itId_may_not_be_set_to_null");
			}
			if(dto.getCompanyId() == null || dto.getCompanyId().isEmpty()) {
				throw new IllegalArgumentException("The_companyId_may_not_be_set_to_null");
			}
			if(dto.getItName() == null || dto.getItName().isEmpty()) {
				throw new IllegalArgumentException("The_itName_may_not_be_set_to_null");
			}
			if(dto.getBusinessUnitId() == null || dto.getBusinessUnitId().isEmpty()) {
				throw new IllegalArgumentException("The_businessUnitId_may_not_be_set_to_null");
			}
			if(dto.getItCategoryId() == null || dto.getItCategoryId().isEmpty()) {
				throw new IllegalArgumentException("The_itCategoryId_may_not_be_set_to_null");
			}
			//ビジネスロジック
			It it = registry.findItOfId(dto.getItId());
			if(it == null) {
				throw new Exception("The_it_is_not_exist");
			}
			if(!(dto.getUrl() == null || dto.getUrl().isEmpty())) {
				it.setUrl(new Url(dto.getUrl()));
			}else {
				it.setUrl(null);
			}
			it.setStartDate(dto.getStartDate());
			it.setEndDate(dto.getEndDate());
			it.setItName(dto.getItName());
			it.setItDescription(dto.getItDescription());
			it.setItCategoryId(new ItCategoryId(dto.getItCategoryId()));
			it.setItCategoryName(dto.getItCategoryName());
			if(!(dto.getPlaceId() == null || dto.getPlaceId().isEmpty())) {
				it.setPlaceId(new PlaceId(dto.getPlaceId()));
			}else {
				it.setPlaceId(null);
			}
			it.setUpdatedAt(new Date());
			registry.save(it);
			message.setResult("OK");
		}catch(Exception ex) {
			message.setResult("NG");
			message.setErrorMessage(ex.getMessage());
			return message;
		}
		return message;
		
	}
	
	public MessageDto removeIt(String itId) {
		MessageDto message = new MessageDto();
		try {
			if(itId == null || itId.isEmpty()) {
				throw new IllegalArgumentException("The_itId_may_not_be_set_to_null");
			}
			It it = registry.findItOfId(itId);
			if(it == null) {
				throw new Exception("The_it_is_not_exist");
			}
			//場所に割当てられている場合削除できない。
			if(!checker.avarable(itId).equals("OK")) {
				throw new Exception(checker.avarable(itId));
			}
			registry.remove(it);
			message.setResult("OK");
		}catch(Exception ex) {
			message.setResult("NG");
			message.setErrorMessage(ex.getMessage());
			return message;
		}
		return message;
		
	}
	public ItDto findItOfId(String itId) throws Exception{
		ItDto dto = null;
		if(itId == null || itId.isEmpty()) {
			throw new IllegalArgumentException("The_itId_may_not_be_set_to_null");
		}
		It it = registry.findItOfId(itId);
		if(it != null) {
			dto = this.convertIt(it);
		}
		return dto;
	}
	
	public List<ItDto> findItsOfBusinessUnit(String businessUnitId) throws Exception{
		if(businessUnitId == null || businessUnitId.isEmpty()) {
			throw new IllegalArgumentException("The_businessUnitId_may_not_be_set_to_null");
		}
		List<It> its = registry.findItsOfBusinessUnit(businessUnitId);
		List<ItDto> dtos = new ArrayList<>();
		for(It entity : its) {
			dtos.add(this.convertIt(entity));
		}
		return dtos;
	}
	
	public List<ItDto> findItsOfCompany(String companyId) throws Exception{
		if(companyId == null || companyId.isEmpty()) {
			throw new IllegalArgumentException("The_companyId_may_not_be_set_to_null");
		}
		List<It> its = registry.findItsOfCompany(companyId);
		List<ItDto> dtos = new ArrayList<>();
		for(It entity : its) {
			dtos.add(this.convertIt(entity));
		}
		return dtos;
	}
	
	public List<ItDto> findItsOfItCategory(String itCategoryId) throws Exception{
		if(itCategoryId == null || itCategoryId.isEmpty()) {
			throw new IllegalArgumentException("The_itCategoryId_may_not_be_set_to_null");
		}
		List<It> its = registry.findItsOfItCategory(itCategoryId);
		List<ItDto> dtos = new ArrayList<>();
		for(It entity : its) {
			dtos.add(this.convertIt(entity));
		}
		return dtos;
	}
	
	public List<ItDto> findItsOfPlace(String placeId) throws Exception{
		if(placeId == null || placeId.isEmpty()) {
			throw new IllegalArgumentException("The_placeId_may_not_be_set_to_null");
		}
		List<It> its = registry.findItsOfPlace(placeId);
		List<ItDto> dtos = new ArrayList<>();
		for(It entity : its) {
			dtos.add(this.convertIt(entity));
		}
		return dtos;
	}
	
	private ItDto convertIt(It entity) {
		ItDto dto = new ItDto();
		dto.setItId(entity.getItId().getItId());
		dto.setItName(entity.getItName());
		dto.setItDescription(entity.getItDescription());
		dto.setCompanyId(entity.getCompanyId().id());
		dto.setBusinessUnitId(entity.getBusinessUnitId().businessUnitId());
		dto.setItCategoryId(entity.getItCategoryId().getItCategoryId());
		dto.setItCategoryName(entity.getItCategoryName());
		if(entity.getPlaceId() != null) {
			dto.setPlaceId(entity.getPlaceId().getPlaceId());
		}
		if(entity.getUrl() != null) {
			dto.setUrl(entity.getUrl().getUrl());
		}
		dto.setStartDate(entity.getStartDate());
		dto.setEndDate(entity.getEndDate());
		dto.setCreatedAt(entity.getCreatedAt());
		dto.setUpdatedAt(entity.getUpdatedAt());
		return dto;
	}

}
