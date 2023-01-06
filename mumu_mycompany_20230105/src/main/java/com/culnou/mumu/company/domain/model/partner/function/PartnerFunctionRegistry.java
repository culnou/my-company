package com.culnou.mumu.company.domain.model.partner.function;

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
import com.culnou.mumu.company.domain.model.TaskId;

import com.culnou.mumu.company.domain.model.partner.category.PartnerCategoryId;

@Service
@Transactional
public class PartnerFunctionRegistry {
	
	@Qualifier("partnerFunctionJpaRepository")
	@Autowired
	private PartnerFunctionRepository repository;
	
	public PartnerFunctionId nextIdentity() {
		UUID uuid = UUID.randomUUID();
        String str = uuid.toString();
		return new PartnerFunctionId(str);
	}
	
	public void save(PartnerFunction partnerFunction) throws Exception{
		//事前条件
		//エンティティ整合性を保証する（JavaのValidationを活用する）
		ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
        Validator validator = factory.getValidator();
		Set<ConstraintViolation<PartnerFunction>> violations = validator.validate(partnerFunction);
		if (!violations.isEmpty()) {
			throw new Exception("entity_validation_error");
		  //Server Erroになるので使わない。
		  //throw new ConstraintViolationException(violations);
		}//if
		repository.save(partnerFunction);
	}
	
	public void remove(PartnerFunction partnerFunction) throws Exception{
		repository.remove(partnerFunction);
	}
	
	public PartnerFunction partnerFunctionOfId(PartnerFunctionId partnerFunctionId) throws Exception {
		return repository.partnerFunctionOfId(partnerFunctionId);
	}
	
	public List<PartnerFunction> partnerFunctionsOfCompany(CompanyId companyId) throws Exception {
		return repository.partnerFunctionsOfCompany(companyId);
	}
	
	public List<PartnerFunction> partnerFunctionsOfPartnerCategory(PartnerCategoryId partnerCategoryId) throws Exception {
		return repository.partnerFunctionsOfPartnerCategory(partnerCategoryId);
	}
	
	public List<PartnerFunction> partnerFunctionsOfPartnerTask(TaskId taskId) throws Exception {
		return repository.partnerFunctionsOfTask(taskId);
	}

}
