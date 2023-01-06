package com.culnou.mumu.company.domain.model.partner.type;

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
public class PartnerTypeRegistry {
	
	@Qualifier("partnerTypeJpaRepository")
	@Autowired
	private PartnerTypeRepository repository;
	
	public PartnerTypeId nextIdentity() {
		UUID uuid = UUID.randomUUID();
        String str = uuid.toString();
		return new PartnerTypeId(str);
	}
	
	public void save(PartnerType partnerType) throws Exception{
		//事前条件
		//エンティティ整合性を保証する（JavaのValidationを活用する）
		ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
        Validator validator = factory.getValidator();
		Set<ConstraintViolation<PartnerType>> violations = validator.validate(partnerType);
		if (!violations.isEmpty()) {
			throw new Exception("entity_validation_error");
		  //Server Erroになるので使わない。
		  //throw new ConstraintViolationException(violations);
		}//if
		
		repository.save(partnerType);
	}
	
	public void remove(PartnerType partnerType) throws Exception{
		repository.remove(partnerType);
	}
	
	public PartnerType findPartnerTypeOfId(String partnerTypeId) throws Exception{
		return repository.findPartnerTypeOfId(new PartnerTypeId(partnerTypeId));
	}
	
	public List<PartnerType> findPartnerTypesOfBusinessDomain(String businessDomainId) throws Exception{
		return repository.findPartnerTypesOfBusinessDomain(businessDomainId);
	}
	
	public List<PartnerType> findPartnerTypesOfCompany(String companyId) throws Exception{
		return repository.findPartnerTypesOfCompany(companyId);
	}


}
