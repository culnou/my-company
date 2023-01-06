package com.culnou.mumu.company.domain.model.data.domain;

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
public class DataDomainRegistry {
	
	@Qualifier("dataDomainJpaRepository")
	@Autowired
	private DataDomainRepository repository;
	
	public DataDomainId nextIdentity() {
		UUID uuid = UUID.randomUUID();
        String str = uuid.toString();
		return new DataDomainId(str);
	}
	
	protected void save(DataDomain dataDomain) throws Exception{
		//事前条件
		//エンティティ整合性を保証する（JavaのValidationを活用する）
		ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
        Validator validator = factory.getValidator();
		Set<ConstraintViolation<DataDomain>> violations = validator.validate(dataDomain);
		if (!violations.isEmpty()) {
			throw new Exception("entity_validation_error");
		  //Server Erroになるので使わない。
		  //throw new ConstraintViolationException(violations);
		}//if
		repository.save(dataDomain);
	}
	
	protected void remove(DataDomain dataDomain) throws Exception{
		repository.remove(dataDomain);
	}
	
	protected DataDomain findDataDomainOfId(String dataDomainId) throws Exception{
		return repository.findDataDomainOfId(new DataDomainId(dataDomainId));
	}
	
	
	protected List<DataDomain> findDataDomainsOfCompany(String companyId) throws Exception{
		return repository.findDataDomainsOfCompany(companyId);
	}



}
