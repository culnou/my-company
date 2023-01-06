package com.culnou.mumu.company.domain.model.application.category;

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
public class ApplicationCategoryRegistry {
	
	@Qualifier("applicationCategoryJpaRepository")
	@Autowired
	private ApplicationCategoryRepository repository;
	
	public ApplicationCategoryId nextIdentity() {
		UUID uuid = UUID.randomUUID();
        String str = uuid.toString();
		return new ApplicationCategoryId(str);
	}
	
	public void save(ApplicationCategory applicationCategory) throws Exception{
		//事前条件
		//エンティティ整合性を保証する（JavaのValidationを活用する）
		ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
        Validator validator = factory.getValidator();
		Set<ConstraintViolation<ApplicationCategory>> violations = validator.validate(applicationCategory);
		if (!violations.isEmpty()) {
			throw new Exception("entity_validation_error");
		  //Server Erroになるので使わない。
		  //throw new ConstraintViolationException(violations);
		}//if
		repository.save(applicationCategory);
	}
	
	public void remove(ApplicationCategory applicationCategory) throws Exception{
		repository.remove(applicationCategory);
	}
	
	public ApplicationCategory findApplicationCategoryOfId(String applicationCategoryId) throws Exception{
		return repository.applicationCategoryOfId(new ApplicationCategoryId(applicationCategoryId));
	}
	
	public List<ApplicationCategory> findApplicationCategoriesOfApplicationType(String applicationTypeId) throws Exception{
		return repository.applicationCategoriesOfApplicationType(applicationTypeId);
	}
	
	public List<ApplicationCategory> findApplicationCategoriesOfBusinessUnit(String businessUnitId) throws Exception{
		return repository.applicationCategoriesOfBusinessUnit(new BusinessUnitId(businessUnitId));
	}
	
	public List<ApplicationCategory> findApplicationCategoriesOfCompany(String companyId) throws Exception{
		return repository.applicationCategoriesOfCompany(new CompanyId(companyId));
	}
	
	
	
	

}
