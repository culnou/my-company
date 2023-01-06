package com.culnou.mumu.company.domain.model.project;

import java.util.List;

import com.culnou.mumu.company.domain.model.BusinessUnitId;
import com.culnou.mumu.company.domain.model.CompanyId;

public interface ProjectRepository {
	
	public void save(Project project) throws Exception;
	
	public void remove(Project project) throws Exception;
	
	public Project findProjectOfId(ProjectId projectId) throws Exception;
	
	public List<Project> findProjectsOfBusinessUnsit(BusinessUnitId businessUnitId) throws Exception;

	public List<Project> findProjectsOfCompany(CompanyId companyId) throws Exception;
	
	public List<Project> findProjectsOfCustomerCategory(String customerCategoryId) throws Exception;
	
	public List<Project> findProjectsOfProductCategory(String productCategoryId) throws Exception;
	
	public List<Project> findProjectsOfMemberCategory(String memberCategoryId) throws Exception;
	
	public List<Project> findProjectsOfPartnerCategory(String partnerCategoryId) throws Exception;
	
	public List<Project> findProjectsOfPlaceCategory(String placeCategoryId) throws Exception;
	
	public List<Project> findProjectsOfDataCategory(String dataCategoryId) throws Exception;
	
	public List<Project> findProjectsOfApplicationCategory(String applicationCategoryId) throws Exception;
	
	public List<Project> findProjectsOfFinancialAssetCategory(String financialAssetCategoryId) throws Exception;
	
	public List<Project> findProjectsOfActionPlan(String actionPlanId) throws Exception;
	
	
}
