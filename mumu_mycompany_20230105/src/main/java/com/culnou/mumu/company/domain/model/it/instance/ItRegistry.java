package com.culnou.mumu.company.domain.model.it.instance;

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
public class ItRegistry {
	@Qualifier("itJpaRepository")
	@Autowired
	private ItRepository repository;
	
	public ItId nextIdentity() {
		UUID uuid = UUID.randomUUID();
        String str = uuid.toString();
		return new ItId(str);
	}
	
	public void save(It it) throws Exception{
		//事前条件
		//エンティティ整合性を保証する（JavaのValidationを活用する）
		ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
        Validator validator = factory.getValidator();
		Set<ConstraintViolation<It>> violations = validator.validate(it);
		if (!violations.isEmpty()) {
			throw new Exception("entity_validation_error");
		  //Server Erroになるので使わない。
		  //throw new ConstraintViolationException(violations);
		}//if
		repository.save(it);
	}
	
	public void remove(It it) throws Exception{
		repository.remove(it);
	}
	
	public It findItOfId(String itId) throws Exception{
		return repository.itOfId(new ItId(itId));
	}
	
	public List<It> findItsOfItCategory(String itCategoryId) throws Exception{
		return repository.itsOfItCategory(itCategoryId);
	}
	
	public List<It> findItsOfPlace(String placeId) throws Exception{
		return repository.itsOfPlace(placeId);
	}
	
	public List<It> findItsOfBusinessUnit(String businessUnitId) throws Exception{
		return repository.itsOfBusinessUnit(new BusinessUnitId(businessUnitId));
	}
	
	public List<It> findItsOfCompany(String companyId) throws Exception{
		return repository.itsOfCompany(new CompanyId(companyId));
	}

}
