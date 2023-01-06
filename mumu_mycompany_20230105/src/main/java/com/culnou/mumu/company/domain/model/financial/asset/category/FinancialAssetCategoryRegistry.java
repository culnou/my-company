package com.culnou.mumu.company.domain.model.financial.asset.category;

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
public class FinancialAssetCategoryRegistry {
	@Qualifier("financialAssetCategoryJpaRepository")
	@Autowired
	private FinancialAssetCategoryRepository repository;
	
	public FinancialAssetCategoryId nextIdentity() {
		UUID uuid = UUID.randomUUID();
        String str = uuid.toString();
		return new FinancialAssetCategoryId(str);
	}
	
	public void save(FinancialAssetCategory financialAssetCategory) throws Exception{
		//事前条件
		//エンティティ整合性を保証する（JavaのValidationを活用する）
		ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
        Validator validator = factory.getValidator();
		Set<ConstraintViolation<FinancialAssetCategory>> violations = validator.validate(financialAssetCategory);
		if (!violations.isEmpty()) {
			throw new Exception("entity_validation_error");
		  //Server Erroになるので使わない。
		  //throw new ConstraintViolationException(violations);
		}//if
		repository.save(financialAssetCategory);
	}
	
	public void remove(FinancialAssetCategory financialAssetCategory) throws Exception{
		repository.remove(financialAssetCategory);
	}
	
	public FinancialAssetCategory findFinancialAssetCategoryOfId(String financialAssetCategoryId) throws Exception{
		return repository.financialAssetCategoryOfId(new FinancialAssetCategoryId(financialAssetCategoryId));
	}
	
	public List<FinancialAssetCategory> findFinancialAssetCategoriesOfFinancialAssetType(String financialAssetTypeId) throws Exception{
		return repository.financialAssetCategoriesOfFinancialAssetType(financialAssetTypeId);
	}
	
	public List<FinancialAssetCategory> findFinancialAssetCategoriesOfBusinessUnit(String businessUnitId) throws Exception{
		return repository.financialAssetCategoriesOfBusinessUnit(new BusinessUnitId(businessUnitId));
	}
	
	public List<FinancialAssetCategory> findFinancialAssetCategoriesOfCompany(String companyId) throws Exception{
		return repository.financialAssetCategoriesOfCompany(new CompanyId(companyId));
	}

}
