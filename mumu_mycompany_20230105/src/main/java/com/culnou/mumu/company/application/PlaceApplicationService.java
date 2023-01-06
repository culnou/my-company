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

import com.culnou.mumu.company.domain.model.place.category.PlaceCategory;
import com.culnou.mumu.company.domain.model.place.category.PlaceCategoryId;
import com.culnou.mumu.company.domain.model.place.category.PlaceCategoryRepository;
import com.culnou.mumu.company.domain.model.place.instance.Place;
import com.culnou.mumu.company.domain.model.place.instance.PlaceRegistry;
import com.culnou.mumu.company.domain.service.PlaceChecker;
import com.culnou.mumu.compnay.controller.MessageDto;
import com.culnou.mumu.compnay.controller.PlaceDto;
@Service
@Transactional
public class PlaceApplicationService {
	@Qualifier("businessUnitJpaRepository")
	@Autowired
	private BusinessUnitRepository businessUnitRepository;
	@Qualifier("placeCategoryJpaRepository")
	@Autowired
	private PlaceCategoryRepository placeCategoryRepository;
	@Autowired
	private PlaceRegistry registry;
	@Autowired
	private PlaceChecker checker;
	
	public MessageDto addPlace(PlaceDto dto) {
		MessageDto message = new MessageDto();
		try {
			if(dto == null) {
				throw new IllegalArgumentException("The_dto_may_not_be_set_to_null");
			}
			if(dto.getCompanyId() == null || dto.getCompanyId().isEmpty()) {
				throw new IllegalArgumentException("The_companyId_may_not_be_set_to_null");
			}
			if(dto.getPlaceName() == null || dto.getPlaceName().isEmpty()) {
				throw new IllegalArgumentException("The_placeName_may_not_be_set_to_null");
			}
			if(dto.getBusinessUnitId() == null || dto.getBusinessUnitId().isEmpty()) {
				throw new IllegalArgumentException("The_businessUnitId_may_not_be_set_to_null");
			}
			if(dto.getPlaceCategoryId() == null || dto.getPlaceCategoryId().isEmpty()) {
				throw new IllegalArgumentException("The_placeCategoryId_may_not_be_set_to_null");
			}
			//ビジネスロジック
			BusinessUnit businessUnit = businessUnitRepository.businessUnitOfId(new BusinessUnitId(dto.getBusinessUnitId()));
			if(businessUnit == null) {
				throw new Exception("The_businessUnit_is_not_exist");
			}
			PlaceCategory placeCategory = placeCategoryRepository.placeCategoryOfId(new PlaceCategoryId(dto.getPlaceCategoryId()));
			if(placeCategory == null) {
				throw new Exception("The_placeCategory_is_not_exist");
			}
			Place place = placeCategory.definePlace(registry.nextIdentplacey(), dto.getPlaceName(), new CompanyId(dto.getCompanyId()), new BusinessUnitId(dto.getBusinessUnitId()));
			place.setPlaceDescription(dto.getPlaceDescription());
			place.setPlaceCategoryName(dto.getPlaceCategoryName());
			
			if(!(dto.getUrl() == null || dto.getUrl().isEmpty())) {
				place.setUrl(new Url(dto.getUrl()));
			}
			place.setStartDate(dto.getStartDate());
			place.setEndDate(dto.getEndDate());
			place.setCreatedAt(new Date());
	        registry.save(place);
	        message.setReturnValue(place.getPlaceId().getPlaceId());
			message.setResult("OK");
		}catch(Exception ex) {
			message.setResult("NG");
			message.setErrorMessage(ex.getMessage());
			return message;
		}
		return message;
		
	}
	
	public MessageDto updatePlace(PlaceDto dto) {
		MessageDto message = new MessageDto();
		try {
			if(dto == null) {
				throw new IllegalArgumentException("The_dto_may_not_be_set_to_null");
			}
			if(dto.getPlaceId() == null || dto.getPlaceId().isEmpty()) {
				throw new IllegalArgumentException("The_placeId_may_not_be_set_to_null");
			}
			if(dto.getCompanyId() == null || dto.getCompanyId().isEmpty()) {
				throw new IllegalArgumentException("The_companyId_may_not_be_set_to_null");
			}
			if(dto.getPlaceName() == null || dto.getPlaceName().isEmpty()) {
				throw new IllegalArgumentException("The_placeName_may_not_be_set_to_null");
			}
			if(dto.getBusinessUnitId() == null || dto.getBusinessUnitId().isEmpty()) {
				throw new IllegalArgumentException("The_businessUnitId_may_not_be_set_to_null");
			}
			if(dto.getPlaceCategoryId() == null || dto.getPlaceCategoryId().isEmpty()) {
				throw new IllegalArgumentException("The_placeCategoryId_may_not_be_set_to_null");
			}
			//ビジネスロジック
			//IT基盤に割当てられている場合編集・削除できない。
			if(!checker.avarable(dto.getPlaceId()).equals("OK")) {
				throw new Exception(checker.avarable(dto.getPlaceId()));
			}
			Place place = registry.findPlaceOfId(dto.getPlaceId());
			if(place == null) {
				throw new Exception("The_place_is_not_exist");
			}
			if(!(dto.getUrl() == null || dto.getUrl().isEmpty())) {
				place.setUrl(new Url(dto.getUrl()));
			}else {
				place.setUrl(null);
			}
			place.setStartDate(dto.getStartDate());
			place.setEndDate(dto.getEndDate());
			place.setPlaceName(dto.getPlaceName());
			place.setPlaceDescription(dto.getPlaceDescription());
			place.setPlaceCategoryId(new PlaceCategoryId(dto.getPlaceCategoryId()));
			place.setPlaceCategoryName(dto.getPlaceCategoryName());
			
			place.setUpdatedAt(new Date());
			registry.save(place);
			message.setResult("OK");
		}catch(Exception ex) {
			message.setResult("NG");
			message.setErrorMessage(ex.getMessage());
			return message;
		}
		return message;
		
	}
	
	public MessageDto removePlace(String placeId) {
		MessageDto message = new MessageDto();
		try {
			if(placeId == null || placeId.isEmpty()) {
				throw new IllegalArgumentException("The_placeId_may_not_be_set_to_null");
			}
			Place place = registry.findPlaceOfId(placeId);
			if(place == null) {
				throw new Exception("The_place_is_not_exist");
			}
			//ビジネスロジック
			//IT基盤に割当てられている場合編集・削除できない。
			if(!checker.avarable(placeId).equals("OK")) {
				throw new Exception(checker.avarable(placeId));
			}
			registry.remove(place);
			message.setResult("OK");
		}catch(Exception ex) {
			message.setResult("NG");
			message.setErrorMessage(ex.getMessage());
			return message;
		}
		return message;
		
	}
	public PlaceDto findPlaceOfId(String placeId) throws Exception{
		PlaceDto dto = null;
		if(placeId == null || placeId.isEmpty()) {
			throw new IllegalArgumentException("The_placeId_may_not_be_set_to_null");
		}
		Place place = registry.findPlaceOfId(placeId);
		if(place != null) {
			dto = this.convertPlace(place);
		}
		return dto;
	}
	
	public List<PlaceDto> findPlacesOfBusinessUnit(String businessUnitId) throws Exception{
		if(businessUnitId == null || businessUnitId.isEmpty()) {
			throw new IllegalArgumentException("The_businessUnitId_may_not_be_set_to_null");
		}
		List<Place> places = registry.findPlacesOfBusinessUnitId(businessUnitId);
		List<PlaceDto> dtos = new ArrayList<>();
		for(Place entplacey : places) {
			dtos.add(this.convertPlace(entplacey));
		}
		return dtos;
	}
	
	public List<PlaceDto> findPlacesOfCompany(String companyId) throws Exception{
		if(companyId == null || companyId.isEmpty()) {
			throw new IllegalArgumentException("The_companyId_may_not_be_set_to_null");
		}
		List<Place> places = registry.findPlacesOfCompany(companyId);
		List<PlaceDto> dtos = new ArrayList<>();
		for(Place entplacey : places) {
			dtos.add(this.convertPlace(entplacey));
		}
		return dtos;
	}
	
	public List<PlaceDto> findPlacesOfPlaceCategory(String placeCategoryId) throws Exception{
		if(placeCategoryId == null || placeCategoryId.isEmpty()) {
			throw new IllegalArgumentException("The_placeCategoryId_may_not_be_set_to_null");
		}
		List<Place> places = registry.findPlacesOfPlaceCategory(placeCategoryId);
		List<PlaceDto> dtos = new ArrayList<>();
		for(Place entplacey : places) {
			dtos.add(this.convertPlace(entplacey));
		}
		return dtos;
	}
	
	private PlaceDto convertPlace(Place entplacey) {
		PlaceDto dto = new PlaceDto();
		dto.setPlaceId(entplacey.getPlaceId().getPlaceId());
		dto.setPlaceName(entplacey.getPlaceName());
		dto.setPlaceDescription(entplacey.getPlaceDescription());
		dto.setCompanyId(entplacey.getCompanyId().id());
		dto.setBusinessUnitId(entplacey.getBusinessUnitId().businessUnitId());
		dto.setPlaceCategoryId(entplacey.getPlaceCategoryId().getPlaceCategoryId());
		dto.setPlaceCategoryName(entplacey.getPlaceCategoryName());
		if(entplacey.getUrl() != null) {
			dto.setUrl(entplacey.getUrl().getUrl());
		}
		dto.setStartDate(entplacey.getStartDate());
		dto.setEndDate(entplacey.getEndDate());
		dto.setCreatedAt(entplacey.getCreatedAt());
		dto.setUpdatedAt(entplacey.getUpdatedAt());
		return dto;
	}
}
