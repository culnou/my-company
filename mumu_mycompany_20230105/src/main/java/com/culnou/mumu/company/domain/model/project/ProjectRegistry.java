package com.culnou.mumu.company.domain.model.project;

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
public class ProjectRegistry {
	
	@Qualifier("projectJpaRepository")
	@Autowired
	private ProjectRepository repository;
	
	
	
	public ProjectId nextIdentity() {
		UUID uuid = UUID.randomUUID();
        String str = uuid.toString();
		return new ProjectId(str);
	}
	
	protected Project findProjectOfId(String projectId) throws Exception{
		return repository.findProjectOfId(new ProjectId(projectId));
	}
	
	protected List<Project> findProjectsOfBusinessUnit(BusinessUnitId businessUnitId) throws Exception{
		return repository.findProjectsOfBusinessUnsit(businessUnitId);
	}
	
	protected List<Project> findProjectsOfCompany(CompanyId companyId) throws Exception{
		return repository.findProjectsOfCompany(companyId);
	}
	
	protected List<Project> findProjectsOfCustomerCategory(String customerCategoryId) throws Exception{
		return repository.findProjectsOfCustomerCategory(customerCategoryId);
	}
	
	protected List<Project> findProjectsOfProductCategory(String productCategoryId) throws Exception{
		return repository.findProjectsOfProductCategory(productCategoryId);
	}
	
	protected List<Project> findProjectsOfMemberCategory(String memberCategoryId) throws Exception{
		return repository.findProjectsOfMemberCategory(memberCategoryId);
	}
	
	protected List<Project> findProjectsOfPartnerCategory(String partnerCategoryId) throws Exception{
		return repository.findProjectsOfPartnerCategory(partnerCategoryId);
	}
	
	protected List<Project> findProjectsOfPlaceCategory(String placeCategoryId) throws Exception{
		return repository.findProjectsOfPlaceCategory(placeCategoryId);
	}
	
	protected List<Project> findProjectsOfDataCategory(String dataCategoryId) throws Exception{
		return repository.findProjectsOfDataCategory(dataCategoryId);
	}
	
	protected List<Project> findProjectsOfApplicationCategory(String applicationCategoryId) throws Exception{
		return repository.findProjectsOfApplicationCategory(applicationCategoryId);
	}
	
	protected List<Project> findProjectsOfFinancialAssetCategory(String financialAssetCategoryId) throws Exception{
		return repository.findProjectsOfFinancialAssetCategory(financialAssetCategoryId);
	}
	
	protected List<Project> findProjectsOfActionPlan(String actionPlanId) throws Exception{
		return repository.findProjectsOfActionPlan(actionPlanId);
	}
	
	public void removeProject(Project project) throws Exception{
		repository.remove(project);
	}
	
	public void registerProject(Project project) throws Exception{
		//エンティティ整合性が保持できているかチェックする
		//Java Validationを使う
		ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
        Validator validator = factory.getValidator();
		Set<ConstraintViolation<Project>> violations = validator.validate(project);
		if (!violations.isEmpty()) {
			throw new Exception("entity_validation_error");
		  //Server Erroになるので使わない。
		  //throw new ConstraintViolationException(violations);
		}//if
		repository.save(project);
	}

}
