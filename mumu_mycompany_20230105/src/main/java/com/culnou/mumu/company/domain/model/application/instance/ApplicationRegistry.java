package com.culnou.mumu.company.domain.model.application.instance;

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
public class ApplicationRegistry {
	
	@Qualifier("applicationJpaRepository")
	@Autowired
	private ApplicationRepository repository;
	
	public ApplicationId nextIdentapplicationy() {
		UUID uuid = UUID.randomUUID();
        String str = uuid.toString();
		return new ApplicationId(str);
	}
	
	public void save(Application application) throws Exception{
		//事前条件
		//エンティティ整合性を保証する（JavaのValidationを活用する）
		ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
        Validator validator = factory.getValidator();
		Set<ConstraintViolation<Application>> violations = validator.validate(application);
		if (!violations.isEmpty()) {
			throw new Exception("entapplicationy_validation_error");
		  //Server Erroになるので使わない。
		  //throw new ConstraintViolationException(violations);
		}//if
		repository.save(application);
	}
	
	public void remove(Application application) throws Exception{
		repository.remove(application);
	}
	
	public Application findApplicationOfId(String applicationId) throws Exception{
		return repository.applicationOfId(new ApplicationId(applicationId));
	}
	
	public List<Application> findApplicationsOfApplicationCategory(String applicationCategoryId) throws Exception{
		return repository.applicationsOfApplicationCategory(applicationCategoryId);
	}
	
	public List<Application> findApplicationsOfIt(String itId) throws Exception{
		return repository.applicationsOfIt(itId);
	}
	
	public List<Application> findApplicationsOfBusinessUnitId(String businessUnitId) throws Exception{
		return repository.applicationsOfBusinessUnit(new BusinessUnitId(businessUnitId));
	}
	
	public List<Application> findApplicationsOfCompany(String companyId) throws Exception{
		return repository.applicationsOfCompany(new CompanyId(companyId));
	}

}
