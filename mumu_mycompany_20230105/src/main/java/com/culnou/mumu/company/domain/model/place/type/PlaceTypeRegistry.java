package com.culnou.mumu.company.domain.model.place.type;

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



@Service
@Transactional
public class PlaceTypeRegistry {
	
	@Qualifier("placeTypeJpaRepository")
	@Autowired
	private PlaceTypeRepository repository;
	
	protected PlaceTypeId nextIdentity() {
		UUID uuid = UUID.randomUUID();
        String str = uuid.toString();
		return new PlaceTypeId(str);
	}
	
	protected void save(PlaceType placeType) throws Exception{
		//事前条件
		//エンティティ整合性を保証する（JavaのValidationを活用する）
		ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
        Validator validator = factory.getValidator();
		Set<ConstraintViolation<PlaceType>> violations = validator.validate(placeType);
		if (!violations.isEmpty()) {
			throw new Exception("entity_validation_error");
		  //Server Erroになるので使わない。
		  //throw new ConstraintViolationException(violations);
		}//if
		repository.save(placeType);
	}
	
	protected void remove(PlaceType placeType) throws Exception{
		repository.remove(placeType);
	}
	
	protected PlaceType findPlaceTypeOfId(String placeTypeId) throws Exception{
		return repository.findPlaceTypeOfId(new PlaceTypeId(placeTypeId));
	}
	
	protected List<PlaceType> findPlaceTypesOfBusinessDomain(String businessDomainId) throws Exception{
		return repository.findPlaceTypesOfBusinessDomain(businessDomainId);
	}
	
	protected List<PlaceType> findPlaceTypesOfCompany(String companyId) throws Exception{
		return repository.findPlaceTypesOfCompany(companyId);
	}


}
