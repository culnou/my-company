package com.culnou.mumu.company.domain.model.place.category;

import java.util.List;
import java.util.Set;
import java.util.UUID;

import javax.transaction.Transactional;
import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;
import javax.validation.ValidatorFactory;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import com.culnou.mumu.company.domain.model.BusinessUnitId;
import com.culnou.mumu.company.domain.model.CompanyId;


@Service
@Transactional
public class PlaceCategoryRegistry {
	@Qualifier("placeCategoryJpaRepository")
	@Autowired
	private PlaceCategoryRepository repository;
	
	public PlaceCategoryId nextIdentity() {
		UUID uuid = UUID.randomUUID();
        String str = uuid.toString();
		return new PlaceCategoryId(str);
	}
	
	public void save(PlaceCategory placeCategory) throws Exception{
		//事前条件
		//エンティティ整合性を保証する（JavaのValidationを活用する）
		ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
        Validator validator = factory.getValidator();
		Set<ConstraintViolation<PlaceCategory>> violations = validator.validate(placeCategory);
		if (!violations.isEmpty()) {
			throw new Exception("entity_validation_error");
		  //Server Erroになるので使わない。
		  //throw new ConstraintViolationException(violations);
		}//if
		repository.save(placeCategory);
	}
	
	public void remove(PlaceCategory placeCategory) throws Exception{
		repository.remove(placeCategory);
	}
	
	public PlaceCategory findPlaceCategoryOfId(String placeCategoryId) throws Exception{
		return repository.placeCategoryOfId(new PlaceCategoryId(placeCategoryId));
	}
	
	public List<PlaceCategory> findPlaceCategoriesOfPlaceType(String placeTypeId) throws Exception{
		return repository.placeCategoriesOfPlaceType(placeTypeId);
	}
	
	public List<PlaceCategory> findPlaceCategoriesOfBusinessUnit(String businessUnitId) throws Exception{
		return repository.placeCategoriesOfBusinessUnit(new BusinessUnitId(businessUnitId));
	}
	
	public List<PlaceCategory> findPlaceCategoriesOfCompany(String companyId) throws Exception{
		return repository.placeCategoriesOfCompany(new CompanyId(companyId));
	}

}
