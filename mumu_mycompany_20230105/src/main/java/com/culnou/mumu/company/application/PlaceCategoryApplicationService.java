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

import com.culnou.mumu.company.domain.model.place.category.PlaceCategory;
import com.culnou.mumu.company.domain.model.place.category.PlaceCategoryId;
import com.culnou.mumu.company.domain.model.place.category.PlaceCategoryRegistry;
import com.culnou.mumu.company.domain.service.PlaceCategoryChecker;
import com.culnou.mumu.compnay.controller.PlaceCategoryDto;
import com.culnou.mumu.compnay.controller.MessageDto;

@Service
@Transactional
public class PlaceCategoryApplicationService {
	@Qualifier("businessUnitJpaRepository")
	@Autowired
	private BusinessUnitRepository businessUnitRepository;
	@Autowired
	private PlaceCategoryRegistry registry;
	@Autowired
	private PlaceCategoryChecker checker;
	
	public MessageDto addPlaceCategory(PlaceCategoryDto dto) {
		MessageDto message = new MessageDto();
		try {
			//コントローラーの事前条件検証。
			if(dto.getBusinessUnitId() == null || dto.getBusinessUnitId().isEmpty()) {
				throw new IllegalArgumentException("The_businessUnitId_may_not_be_set_to_null");
			}
			if(dto.getCompanyId() == null || dto.getCompanyId().isEmpty()) {
				throw new IllegalArgumentException("The_companyId_may_not_be_set_to_null");
			}
			if(dto.getPlaceCategoryName() == null || dto.getPlaceCategoryName().isEmpty()) {
				throw new IllegalArgumentException("The_placeCategoryName_may_not_be_set_to_null");
			}
			if(!(dto.getAssociatedPlaceTypes().size() > 0)) {
				throw new IllegalArgumentException("The_placeTypeId_may_not_be_set_to_null");
			}
			
			//ビジネスロジック
			BusinessUnit businessUnit = businessUnitRepository.businessUnitOfId(new BusinessUnitId(dto.getBusinessUnitId()));
			if(businessUnit == null) {
				throw new Exception("The_businessUnit_is_not_exist");
			}
			PlaceCategoryId placeId = registry.nextIdentity();
			PlaceCategory placeCategory = businessUnit.definePlaceCategory(placeId, dto.getPlaceCategoryName());
			placeCategory.setAssociatedPlaceTypes(dto.getAssociatedPlaceTypes());
			placeCategory.setPlaceCategoryDescription(dto.getPlaceCategoryDescription());
			if(!(dto.getUrl() == null || dto.getUrl().isEmpty())) {
				placeCategory.setUrl(new Url(dto.getUrl()));
			}
			placeCategory.setBusinessState(dto.getBusinessState());
			placeCategory.setStartDate(dto.getStartDate());
			placeCategory.setEndDate(dto.getEndDate());
			placeCategory.setGoals(dto.getGoals());
			placeCategory.setAchievements(dto.getAchievements());
			placeCategory.setCreatedAt(new Date());
			registry.save(placeCategory);
			message.setReturnValue(placeId.getPlaceCategoryId());
			message.setResult("OK");
		}catch(Exception ex) {
			message.setResult("NG");
			message.setErrorMessage(ex.getMessage());
			return message;
		}
		return message;
	}
	
	public MessageDto updatePlaceCategory(String placeCategoryId, PlaceCategoryDto dto) {
		MessageDto message = new MessageDto();
		try {
			//コントローラーの事前条件検証。
			if(placeCategoryId == null || placeCategoryId.isEmpty()) {
				throw new IllegalArgumentException("The_placeCategoryId_may_not_be_set_to_null");
			}
			if(dto.getCompanyId() == null || dto.getCompanyId().isEmpty()) {
				throw new IllegalArgumentException("The_companyId_may_not_be_set_to_null");
			}
			if(dto.getPlaceCategoryName() == null || dto.getPlaceCategoryName().isEmpty()) {
				throw new IllegalArgumentException("The_placeCategoryName_may_not_be_set_to_null");
			}
			if(!(dto.getAssociatedPlaceTypes().size() > 0)) {
				throw new IllegalArgumentException("The_placeTypeId_may_not_be_set_to_null");
			}
			//ビジネスロジック
			//プロジェクトやプログラムに割当てられている場合編集・削除できない。
			if(!checker.avarable(placeCategoryId).equals("OK")) {
				throw new Exception(checker.avarable(placeCategoryId));
			}
			//NULLでも空文字でも更新するようにする。
			PlaceCategory placeCategory = registry.findPlaceCategoryOfId(placeCategoryId);
			if(placeCategory == null) {
				throw new Exception("The_placeCategory_is_not_exist");
			}
			placeCategory.setAssociatedPlaceTypes(dto.getAssociatedPlaceTypes());
			placeCategory.setPlaceCategoryName(dto.getPlaceCategoryName());
			placeCategory.setPlaceCategoryDescription(dto.getPlaceCategoryDescription());
			if(!(dto.getUrl() == null || dto.getUrl().isEmpty())) {
				placeCategory.setUrl(new Url(dto.getUrl()));
			}else {
				placeCategory.setUrl(null);
			}
			placeCategory.setBusinessState(dto.getBusinessState());
			placeCategory.setStartDate(dto.getStartDate());
			placeCategory.setEndDate(dto.getEndDate());
			placeCategory.setGoals(dto.getGoals());
			placeCategory.setAchievements(dto.getAchievements());
			placeCategory.setUpdatedAt(new Date());
			registry.save(placeCategory);
			message.setResult("OK");
		}catch(Exception ex){
			message.setResult("NG");
			message.setErrorMessage(ex.getMessage());
			return message;
		}
		return message;
	}
	
	public MessageDto removePlaceCategory(String placeCategoryId) {
		MessageDto message = new MessageDto();
		try {
			if(placeCategoryId == null || placeCategoryId.isEmpty()) {
				throw new IllegalArgumentException("The_placeCategoryId_may_not_be_set_to_null");
			}
			PlaceCategory placeCategory = registry.findPlaceCategoryOfId(placeCategoryId);
			if(placeCategory == null) {
				throw new Exception("The_placeCategory_is_not_exist");
			}
			//プロジェクトやプログラムに割当てられている場合編集・削除できない。
			if(!checker.avarable(placeCategoryId).equals("OK")) {
				throw new Exception(checker.avarable(placeCategoryId));
			}
			registry.remove(placeCategory);
			message.setResult("OK");
		}catch(Exception ex) {
			message.setResult("NG");
			message.setErrorMessage(ex.getMessage());
			return message;
		}
		return message;
	}
	
	public List<PlaceCategoryDto> findPlaceCategoriesOfBusinessUnit(String businessUnitId) throws Exception{
		if(businessUnitId == null || businessUnitId.isEmpty()) {
			throw new IllegalArgumentException("The_businessUnitId_may_not_be_set_to_null");
		}
		List<PlaceCategory> placeCategories = registry.findPlaceCategoriesOfBusinessUnit(businessUnitId);
		List<PlaceCategoryDto> dtos = new ArrayList<>();
		for(PlaceCategory entity : placeCategories) {
			dtos.add(this.convertPlaceCategory(entity));
		}
		return dtos;
	}
	
	public List<PlaceCategoryDto> findPlaceCategoriesOfCompany(String companyId) throws Exception{
		if(companyId == null || companyId.isEmpty()) {
			throw new IllegalArgumentException("The_companyId_may_not_be_set_to_null");
		}
		List<PlaceCategory> placeCategories = registry.findPlaceCategoriesOfCompany(companyId);
		List<PlaceCategoryDto> dtos = new ArrayList<>();
		for(PlaceCategory entity : placeCategories) {
			dtos.add(this.convertPlaceCategory(entity));
		}
		return dtos;
	}
	
	public List<PlaceCategoryDto> findPlaceCategoriesOfPlaceType(String placeTypeId) throws Exception{
		if(placeTypeId == null || placeTypeId.isEmpty()) {
			throw new IllegalArgumentException("The_placeTypeId_may_not_be_set_to_null");
		}
		List<PlaceCategory> placeCategories = registry.findPlaceCategoriesOfPlaceType(placeTypeId);
		List<PlaceCategoryDto> dtos = new ArrayList<>();
		for(PlaceCategory entity : placeCategories) {
			dtos.add(this.convertPlaceCategory(entity));
		}
		return dtos;
	}
	
	public PlaceCategoryDto findPlaceCategoryOfId(String placeCategoryId) throws Exception{
		if(placeCategoryId == null || placeCategoryId.isEmpty()) {
			throw new IllegalArgumentException("The_placeCategoryId_may_not_be_set_to_null");
		}
		return this.convertPlaceCategory(registry.findPlaceCategoryOfId(placeCategoryId));
	}
	
	private PlaceCategoryDto convertPlaceCategory(PlaceCategory entity) {
		PlaceCategoryDto dto = new PlaceCategoryDto();
		dto.setPlaceCategoryId(entity.getPlaceCategoryId().getPlaceCategoryId());
		dto.setPlaceCategoryName(entity.getPlaceCategoryName());
		dto.setPlaceCategoryDescription(entity.getPlaceCategoryDescription());
		dto.setCompanyId(entity.getCompanyId().id());
		dto.setBusinessUnitId(entity.getBusinessUnitId().businessUnitId());
		dto.setAssociatedPlaceTypes(entity.getAssociatedPlaceTypes());
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
