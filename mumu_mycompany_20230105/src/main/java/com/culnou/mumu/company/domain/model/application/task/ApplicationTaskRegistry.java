package com.culnou.mumu.company.domain.model.application.task;

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
public class ApplicationTaskRegistry {
	
	@Qualifier("applicationTaskJpaRepository")
	@Autowired
	private ApplicationTaskRepository repository;
	
	protected ApplicationTaskId nextIdentity() {
		UUID uuid = UUID.randomUUID();
        String str = uuid.toString();
		return new ApplicationTaskId(str);
	}
	
	protected void save(ApplicationTask applicationTask) throws Exception{
		//事前条件
		//エンティティ整合性を保証する（JavaのValidationを活用する）
		ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
        Validator validator = factory.getValidator();
		Set<ConstraintViolation<ApplicationTask>> violations = validator.validate(applicationTask);
		if (!violations.isEmpty()) {
			throw new Exception("entity_validation_error");
		  //Server Erroになるので使わない。
		  //throw new ConstraintViolationException(violations);
		}//if
		repository.save(applicationTask);
	}
	
	protected void remove(ApplicationTask applicationTask) throws Exception{
		repository.remove(applicationTask);
	}
	
	protected ApplicationTask findApplicationTaskOfId(String applicationTaskId) throws Exception{
		return repository.findApplicationTaskOfId(new ApplicationTaskId(applicationTaskId));
	}
	
	protected List<ApplicationTask> findApplicationTasksOfApplicationType(String applicationTypeId) throws Exception{
		return repository.findApplicationTasksOfApplicationType(applicationTypeId);
	}
	
	protected List<ApplicationTask> findApplicationTasksOfCompany(String companyId) throws Exception{
		return repository.findApplicationTasksOfCompany(companyId);
	}




}
