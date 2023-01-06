package com.culnou.mumu.company.domain.model.place.instance;

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
public class PlaceRegistry {
	
	@Qualifier("placeJpaRepository")
	@Autowired
	private PlaceRepository repository;
	
	public PlaceId nextIdentplacey() {
		UUID uuid = UUID.randomUUID();
        String str = uuid.toString();
		return new PlaceId(str);
	}
	
	public void save(Place place) throws Exception{
		//事前条件
		//エンティティ整合性を保証する（JavaのValidationを活用する）
		ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
        Validator validator = factory.getValidator();
		Set<ConstraintViolation<Place>> violations = validator.validate(place);
		if (!violations.isEmpty()) {
			throw new Exception("entplacey_validation_error");
		  //Server Erroになるので使わない。
		  //throw new ConstraintViolationException(violations);
		}//if
		repository.save(place);
	}
	
	public void remove(Place place) throws Exception{
		repository.remove(place);
	}
	
	public Place findPlaceOfId(String placeId) throws Exception{
		return repository.placeOfId(new PlaceId(placeId));
	}
	
	public List<Place> findPlacesOfPlaceCategory(String placeCategoryId) throws Exception{
		return repository.placesOfPlaceCategory(placeCategoryId);
	}
	
	public List<Place> findPlacesOfBusinessUnitId(String businessUnitId) throws Exception{
		return repository.placesOfBusinessUnit(new BusinessUnitId(businessUnitId));
	}
	
	public List<Place> findPlacesOfCompany(String companyId) throws Exception{
		return repository.placesOfCompany(new CompanyId(companyId));
	}

}

