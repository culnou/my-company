package com.culnou.mumu.company.domain.model.application.function;

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

import com.culnou.mumu.company.domain.model.CompanyId;
import com.culnou.mumu.company.domain.model.application.category.ApplicationCategoryId;
import com.culnou.mumu.company.domain.model.application.task.ApplicationTaskId;




@Service
@Transactional
public class ApplicationFunctionRegistry {
	@Qualifier("applicationFunctionJpaRepository")
	@Autowired
	private ApplicationFunctionRepository repository;
	
	public ApplicationFunctionId nextIdentity() {
		UUID uuid = UUID.randomUUID();
        String str = uuid.toString();
		return new ApplicationFunctionId(str);
	}
	
	public void save(ApplicationFunction applicationFunction) throws Exception{
		//事前条件
		//エンティティ整合性を保証する（JavaのValidationを活用する）
		ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
        Validator validator = factory.getValidator();
		Set<ConstraintViolation<ApplicationFunction>> violations = validator.validate(applicationFunction);
		if (!violations.isEmpty()) {
			throw new Exception("entity_validation_error");
		  //Server Erroになるので使わない。
		  //throw new ConstraintViolationException(violations);
		}//if
		repository.save(applicationFunction);
	}
	
	public void remove(ApplicationFunction applicationFunction) throws Exception{
		repository.remove(applicationFunction);
	}
	
	public ApplicationFunction applicationFunctionOfId(ApplicationFunctionId applicationFunctionId) throws Exception {
		return repository.applicationFunctionOfId(applicationFunctionId);
	}
	
	public List<ApplicationFunction> applicationFunctionsOfCompany(CompanyId companyId) throws Exception {
		return repository.applicationFunctionsOfCompany(companyId);
	}
	
	public List<ApplicationFunction> applicationFunctionsOfApplicationCategory(ApplicationCategoryId applicationCategoryId) throws Exception {
		return repository.applicationFunctionsOfApplicationCategory(applicationCategoryId);
	}
	
	public List<ApplicationFunction> applicationFunctionsOfApplicationTask(ApplicationTaskId applicationTaskId) throws Exception {
		return repository.applicationFunctionsOfApplicationTask(applicationTaskId);
	}


}
