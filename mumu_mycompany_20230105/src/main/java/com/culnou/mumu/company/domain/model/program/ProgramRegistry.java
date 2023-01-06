package com.culnou.mumu.company.domain.model.program;

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
public class ProgramRegistry {
	
	@Qualifier("programJpaRepository")
	@Autowired
	private ProgramRepository repository;
	
	
	
	public ProgramId nextIdentity() {
		UUID uuid = UUID.randomUUID();
        String str = uuid.toString();
		return new ProgramId(str);
	}
	
	protected Program findProgramOfId(String programId) throws Exception{
		return repository.findProgramOfId(new ProgramId(programId));
	}
	
	protected List<Program> findProgramsOfBusinessUnit(BusinessUnitId businessUnitId) throws Exception{
		return repository.findProgramsOfBusinessUnsit(businessUnitId);
	}
	
	protected List<Program> findProgramsOfCompany(CompanyId companyId) throws Exception{
		return repository.findProgramsOfCompany(companyId);
	}
	
	protected List<Program> findProgramsOfCustomerCategory(String customerCategoryId) throws Exception{
		return repository.findProgramsOfCustomerCategory(customerCategoryId);
	}
	
	protected List<Program> findProgramsOfProductCategory(String productCategoryId) throws Exception{
		return repository.findProgramsOfProductCategory(productCategoryId);
	}
	
	protected List<Program> findProgramsOfMemberCategory(String memberCategoryId) throws Exception{
		return repository.findProgramsOfMemberCategory(memberCategoryId);
	}
	
	protected List<Program> findProgramsOfPartnerCategory(String partnerCategoryId) throws Exception{
		return repository.findProgramsOfPartnerCategory(partnerCategoryId);
	}
	
	protected List<Program> findProgramsOfPlaceCategory(String placeCategoryId) throws Exception{
		return repository.findProgramsOfPlaceCategory(placeCategoryId);
	}
	
	protected List<Program> findProgramsOfDataCategory(String dataCategoryId) throws Exception{
		return repository.findProgramsOfDataCategory(dataCategoryId);
	}
	
	protected List<Program> findProgramsOfApplicationCategory(String applicationCategoryId) throws Exception{
		return repository.findProgramsOfApplicationCategory(applicationCategoryId);
	}
	
	protected List<Program> findProgramsOfFinancialAssetCategory(String financialAssetCategoryId) throws Exception{
		return repository.findProgramsOfFinancialAssetCategory(financialAssetCategoryId);
	}
	
	protected List<Program> findProgramsOfActionPlan(String actionPlanId) throws Exception{
		return repository.findProgramsOfActionPlan(actionPlanId);
	}
	
	
	public void removeProgram(Program program) throws Exception{
		repository.remove(program);
	}
	
	public void registerProgram(Program program) throws Exception{
		//エンティティ整合性が保持できているかチェックする
		//Java Validationを使う
		ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
        Validator validator = factory.getValidator();
		Set<ConstraintViolation<Program>> violations = validator.validate(program);
		if (!violations.isEmpty()) {
			throw new Exception("entity_validation_error");
		  //Server Erroになるので使わない。
		  //throw new ConstraintViolationException(violations);
		}//if
		repository.save(program);
	}

}
