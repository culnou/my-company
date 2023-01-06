package com.culnou.mumu.company.domain.model.it.type;

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
public class ItTypeRegistry {
	
	@Qualifier("itTypeJpaRepository")
	@Autowired
	private ItTypeRepository repository;
	
	protected ItTypeId nextIdentity() {
		UUID uuid = UUID.randomUUID();
        String str = uuid.toString();
		return new ItTypeId(str);
	}
	
	protected void save(ItType itType) throws Exception{
		//事前条件
		//エンティティ整合性を保証する（JavaのValidationを活用する）
		ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
        Validator validator = factory.getValidator();
		Set<ConstraintViolation<ItType>> violations = validator.validate(itType);
		if (!violations.isEmpty()) {
			throw new Exception("entity_validation_error");
		  //Server Erroになるので使わない。
		  //throw new ConstraintViolationException(violations);
		}//if
		repository.save(itType);
	}
	
	protected void remove(ItType itType) throws Exception{
		repository.remove(itType);
	}
	
	protected ItType findItTypeOfId(String itTypeId) throws Exception{
		return repository.findItTypeOfId(new ItTypeId(itTypeId));
	}
	
	protected List<ItType> findItTypesOfBusinessDomain(String businessDomainId) throws Exception{
		return repository.findItTypesOfBusinessDomain(businessDomainId);
	}
	
	protected List<ItType> findItTypesOfCompany(String companyId) throws Exception{
		return repository.findItTypesOfCompany(companyId);
	}



}
