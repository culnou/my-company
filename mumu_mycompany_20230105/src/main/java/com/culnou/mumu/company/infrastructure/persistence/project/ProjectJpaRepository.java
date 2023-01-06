package com.culnou.mumu.company.infrastructure.persistence.project;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.culnou.mumu.company.domain.model.BusinessUnitId;
import com.culnou.mumu.company.domain.model.CompanyId;
import com.culnou.mumu.company.domain.model.project.Project;
import com.culnou.mumu.company.domain.model.project.ProjectId;
import com.culnou.mumu.company.domain.model.project.ProjectRepository;

@Service("projectJpaRepository")
@Transactional
public class ProjectJpaRepository implements ProjectRepository{
	
	@Autowired
	private ProjectJpaDataRepository repository;

	@Override
	public void save(Project project) throws Exception {
		// TODO Auto-generated method stub
		repository.save(project);
		
	}
	
	public Project findProjectOfId(ProjectId projectId) throws Exception{
		return repository.findByProjectId(projectId);
	}

	@Override
	public List<Project> findProjectsOfBusinessUnsit(BusinessUnitId businessUnitId) throws Exception {
		// TODO Auto-generated method stub
		return repository.findProjectsOfBusinessUnsit(businessUnitId);
	}

	@Override
	public void remove(Project project) throws Exception {
		// TODO Auto-generated method stub
		repository.delete(project);
		
	}

	@Override
	public List<Project> findProjectsOfCompany(CompanyId companyId) throws Exception {
		// TODO Auto-generated method stub
		return repository.findProjectsOfCompany(companyId);
	}

	@Override
	public List<Project> findProjectsOfCustomerCategory(String customerCategoryId) throws Exception {
		// TODO Auto-generated method stub
		return repository.findProjectsOfCustomerCategory(customerCategoryId);
	}

	@Override
	public List<Project> findProjectsOfProductCategory(String productCategoryId) throws Exception {
		// TODO Auto-generated method stub
		return repository.findProjectsOfProductCategory(productCategoryId);
	}

	@Override
	public List<Project> findProjectsOfMemberCategory(String memberCategoryId) throws Exception {
		// TODO Auto-generated method stub
		return repository.findProjectsOfMemberCategory(memberCategoryId);
	}

	@Override
	public List<Project> findProjectsOfPartnerCategory(String partnerCategoryId) throws Exception {
		// TODO Auto-generated method stub
		return repository.findProjectsOfPartnerCategory(partnerCategoryId);
	}

	@Override
	public List<Project> findProjectsOfPlaceCategory(String placeCategoryId) throws Exception {
		// TODO Auto-generated method stub
		return repository.findProjectsOfPlaceCategory(placeCategoryId);
	}

	@Override
	public List<Project> findProjectsOfDataCategory(String dataCategoryId) throws Exception {
		// TODO Auto-generated method stub
		return repository.findProjectsOfDataCategory(dataCategoryId);
	}

	@Override
	public List<Project> findProjectsOfApplicationCategory(String applicationCategoryId) throws Exception {
		// TODO Auto-generated method stub
		return repository.findProjectsOfApplicationCategory(applicationCategoryId);
	}

	@Override
	public List<Project> findProjectsOfFinancialAssetCategory(String financialAssetCategoryId) throws Exception {
		// TODO Auto-generated method stub
		return repository.findProjectsOfFinancialAssetCategory(financialAssetCategoryId);
	}

	@Override
	public List<Project> findProjectsOfActionPlan(String actionPlanId) throws Exception {
		// TODO Auto-generated method stub
		return repository.findProjectsOfActionPlan(actionPlanId);
	}

	
	
}
