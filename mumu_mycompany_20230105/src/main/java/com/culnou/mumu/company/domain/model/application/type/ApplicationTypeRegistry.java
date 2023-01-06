package com.culnou.mumu.company.domain.model.application.type;

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
public class ApplicationTypeRegistry {
	
	@Qualifier("applicationTypeJpaRepository")
	@Autowired
	private ApplicationTypeRepository repository;
	
	protected ApplicationTypeId nextIdentity() {
		UUID uuid = UUID.randomUUID();
        String str = uuid.toString();
		return new ApplicationTypeId(str);
	}
	
	protected void save(ApplicationType applicationType) throws Exception{
		//事前条件
		//エンティティ整合性を保証する（JavaのValidationを活用する）
		ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
        Validator validator = factory.getValidator();
		Set<ConstraintViolation<ApplicationType>> violations = validator.validate(applicationType);
		if (!violations.isEmpty()) {
			throw new Exception("entity_validation_error");
		  //Server Erroになるので使わない。
		  //throw new ConstraintViolationException(violations);
		}//if
		repository.save(applicationType);
	}
	
	protected void remove(ApplicationType applicationType) throws Exception{
		repository.remove(applicationType);
	}
	
	protected ApplicationType findApplicationTypeOfId(String applicationTypeId) throws Exception{
		return repository.findApplicationTypeOfId(new ApplicationTypeId(applicationTypeId));
	}
	
	protected List<ApplicationType> findApplicationTypesOfBusinessDomain(String businessDomainId) throws Exception{
		return repository.findApplicationTypesOfBusinessDomain(businessDomainId);
	}
	
	protected List<ApplicationType> findApplicationTypesOfJob(String jobId) throws Exception{
		return repository.findApplicationTypesOfJob(jobId);
	}
	
	protected List<ApplicationType> findApplicationTypesOfCompany(String companyId) throws Exception{
		return repository.findApplicationTypesOfCompany(companyId);
	}



}
