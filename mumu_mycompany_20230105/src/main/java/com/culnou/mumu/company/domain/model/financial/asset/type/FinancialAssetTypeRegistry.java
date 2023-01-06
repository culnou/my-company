package com.culnou.mumu.company.domain.model.financial.asset.type;

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
public class FinancialAssetTypeRegistry {
	
	@Qualifier("financialAssetTypeJpaRepository")
	@Autowired
	private FinancialAssetTypeRepository repository;
	
	protected FinancialAssetTypeId nextIdentity() {
		UUID uuid = UUID.randomUUID();
        String str = uuid.toString();
		return new FinancialAssetTypeId(str);
	}
	
	protected void save(FinancialAssetType financialAssetType) throws Exception{
		//事前条件
		//エンティティ整合性を保証する（JavaのValidationを活用する）
		ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
        Validator validator = factory.getValidator();
		Set<ConstraintViolation<FinancialAssetType>> violations = validator.validate(financialAssetType);
		if (!violations.isEmpty()) {
			throw new Exception("entity_validation_error");
		  //Server Erroになるので使わない。
		  //throw new ConstraintViolationException(violations);
		}//if
		
		repository.save(financialAssetType);
	}
	
	protected void remove(FinancialAssetType financialAssetType) throws Exception{
		repository.remove(financialAssetType);
	}
	
	protected FinancialAssetType findFinancialAssetTypeOfId(String financialAssetTypeId) throws Exception{
		return repository.findFinancialAssetTypeOfId(new FinancialAssetTypeId(financialAssetTypeId));
	}
	
	protected List<FinancialAssetType> findFinancialAssetTypesOfBusinessDomain(String businessDomainId) throws Exception{
		return repository.findFinancialAssetTypesOfBusinessDomain(businessDomainId);
	}
	
	protected List<FinancialAssetType> findFinancialAssetTypesOfCompany(String companyId) throws Exception{
		return repository.findFinancialAssetTypesOfCompany(companyId);
	}


}
